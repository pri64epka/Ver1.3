package com.example.android.ver13;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.ver13.data.Contract;
import com.example.android.ver13.data.TennisHelper;

import static com.example.android.ver13.SearchedMatchsActivity.selId;


public class AA_KingTieBreakActivity extends AppCompatActivity {

    LinearLayout N_activity_kingTieBreak;
    public static String[] typeGoals = new String[5];
    int FPscore = 0;
    int SPscore = 0;
    private boolean stopMatchIsEnabled = true;
    String pl1_1, pl1_2, pl2_1, pl2_2, reasonsSelectedItem;
    public int lastIdfromUT, curNum, i;
    public int undoCounter = 0;
    TextView pointFP, pointSP, plFirst, plSecond, tableScore;
    private TennisHelper mDbHelper;
    Button goalFP, goalSP;
    ImageButton redoButton, undo, saveBtn;

    Spinner spinnerFP, spinnerSP;
    AlertDialog.Builder ad;
    Context context;
    private String KTBStatusMatch = "Paused";

    private static final String TAG = "AA_KingTieBreakActivity";

    int goalsCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aa_activity_king_tie_break);


        goalsCounter = 0;
        context = AA_KingTieBreakActivity.this;
        mDbHelper = new TennisHelper(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.table_score));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        N_activity_kingTieBreak = (LinearLayout) findViewById(R.id.activity_kingTieBreak);
        tableScore = (TextView) findViewById(R.id.addToTableScoreTV);
        tableScore.setText(R.string.King_tie_break);//
        plFirst = (TextView) findViewById(R.id.playerFirstTV);
        plSecond = (TextView) findViewById(R.id.playerSecondTV);
        TextView nameFirstPlayer = (TextView) findViewById(R.id.nameFirstPlayerTV);
        TextView nameSecondPlayer = (TextView) findViewById(R.id.nameSecondPlayerTV);
        pointFP = (TextView) findViewById(R.id.pointFPTV);
        pointSP = (TextView) findViewById(R.id.pointSPTV);

        if (getIntent().getExtras().getBoolean("isNew")) {

            pl1_1 = getIntent().getExtras().getString("user");
            pl1_2 = getIntent().getExtras().getString("userSur");
            pl2_1 = getIntent().getExtras().getString("der");
            pl2_2 = getIntent().getExtras().getString("derSur");
            pointFP.setText(String.valueOf(0));
            pointSP.setText(String.valueOf(0));

        } else {

            pl1_1 = mDbHelper.getNameById("name_A_1", selId);
            pl1_2 = mDbHelper.getNameById("name_A_2", selId);
            pl2_1 = mDbHelper.getNameById("name_B_1", selId);
            pl2_2 = mDbHelper.getNameById("name_B_2", selId);

            pointFP.setText(String.valueOf(mDbHelper.getNumById("ktbfpsST", selId)));
            FPscore = mDbHelper.getNumById("ktbfpsST", selId);
            pointSP.setText(String.valueOf(mDbHelper.getNumById("ktbspsST", selId)));
            SPscore = mDbHelper.getNumById("ktbspsST", selId);

            mDbHelper.insertKTBFPSandKTBSPS(mDbHelper.getNumById("ktbfpsST", selId), mDbHelper.getNumById("ktbspsST", selId));
            mDbHelper.insertPlayers(pl1_1, pl1_2, pl2_1, pl2_2);

            stopMatchIsEnabled = mDbHelper.getNumById("stopMatchIsEnabled", selId) > 0;
            Log.d(TAG, "stopMatchIsEnabled = " + stopMatchIsEnabled);

        }

        setNames(plFirst, pl1_1, pl1_2);
        setNames(plSecond, pl2_1, pl2_2);
        setAddNames(nameFirstPlayer, pl1_1, pl1_2);
        setAddNames(nameSecondPlayer, pl2_1, pl2_2);

        typeGoals[0] = getResources().getString(R.string.select_gt_for_spinner);
        typeGoals[1] = getResources().getString(R.string.Winner);
        typeGoals[2] = getResources().getString(R.string.Ace);
        typeGoals[3] = getResources().getString(R.string.Unenforced_error_for_spinner);
        typeGoals[4] = getResources().getString(R.string.Double_error_for_spinner);

        spinnerFP = (Spinner) findViewById(R.id.spinnerFPsp);
        spinnerSP = (Spinner) findViewById(R.id.spinnerSPsp);

        setadapter(typeGoals, spinnerFP, this);
        setadapter(typeGoals, spinnerSP, this);

        goalFP = (Button) findViewById(R.id.goalFPButton);
        goalSP = (Button) findViewById(R.id.goalSPButton);

        undo = (ImageButton) findViewById(R.id.undoButton);
        redoButton = (ImageButton) findViewById(R.id.forwardButton);
        saveBtn = (ImageButton) findViewById(R.id._statButton);

        noEnabled(undo, getDrawable(R.drawable.ic_undo_black_24dp_0_3));
        noEnabled(redoButton, getDrawable(R.drawable.ic_redo_black_24dp_0_3));
        noEnabled(saveBtn, getDrawable(R.drawable.ic_save_black_24dp_0_3));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.score_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if (!stopMatchIsEnabled) {
            menu.getItem(1).setEnabled(false);
        } else {
            menu.getItem(1).setEnabled(true);
        }
        return true;
    }

    @Override
    public void onBackPressed() {

            ad = new AlertDialog.Builder(context);
            ad.setMessage(getString(R.string.ad_alert_1));
            ad.setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int arg1) {

                    saveResultGoToMM();
                }
            });
            ad.setNeutralButton(getString(R.string.Continue_match),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int id) {
                            dialog.cancel();
                        }
                    });

            ad.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int arg1) {
                    noSaveResultGoToMM();

                    if (!getIntent().getExtras().getBoolean("isNew")) {
                        mDbHelper.putFreeRowInStatisticSaveTable(selId);
                    }
                }
            });
            ad.setCancelable(true);
            ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialog) {
                }
            });
            ad.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.statistic_item:
                Intent intent1 = new Intent(this, AA_StatisticActivity.class);
                startActivity(intent1);
                break;

            case R.id.stopAGame_item:

                final String na_1 = getString(R.string.Retired) + " " + plFirst.getText().toString() + getString(R.string.loss);
                final String na_2 = getString(R.string.Retired) + " " + plSecond.getText().toString() + getString(R.string.loss);
                final String na_3 = getString(R.string.Another_Match_is_paused);
                final String na_4 = getString(R.string.Another_Match_is_finished);
                final String[] reasons = {na_1, na_2, na_3, na_4};
                ad = new AlertDialog.Builder(this);
                ad.setTitle(getString(R.string.Choose_the_reason))
                        .setCancelable(false)
                        .setNeutralButton(getString(R.string.Back),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        dialog.cancel();
                                    }
                                })
                        .setPositiveButton(getString(R.string.Confirm),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int item) {


                                        ad = new AlertDialog.Builder(context);

                                        ad.setMessage(getString(R.string.Do_you_want));

                                        ad.setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int arg1) {
                                                    if (reasonsSelectedItem.equals(na_1)) {

                                                        saveResultFromStat("secondPlayer");

                                                    } else if (reasonsSelectedItem.equals(na_2)) {

                                                        saveResultFromStat("firstPlayer");
                                                    } else if (reasonsSelectedItem.equals(na_3)) {

                                                        saveResultGoToMM();

                                                    } else if (reasonsSelectedItem.equals(na_4)) {
                                                        saveResultFromStat("Finished");
                                                    }
                                            }
                                        });
                                        ad.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int arg1) {
                                                noSaveResultGoToMM();
                                                if (!getIntent().getExtras().getBoolean("isNew")) {
                                                    mDbHelper.putFreeRowInStatisticSaveTable(selId);
                                                }
                                            }
                                        });
                                        ad.setCancelable(true);
                                        ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                            public void onCancel(DialogInterface dialog) {
                                            }
                                        });
                                        ad.show();
                                    }
                                })
                        .setSingleChoiceItems(reasons, -1,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int item) {
                                        reasonsSelectedItem = reasons[item];
                                    }
                                });
                ad.show();
                break;

            case R.id.help_item:

                goToHelp();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void beEnabled(ImageButton ib, Drawable Draw) {
        ib.setEnabled(true);
        ib.setImageDrawable(Draw);
    }

    public static void noEnabled(ImageButton ib, Drawable Draw) {
        ib.setEnabled(false);
        ib.setImageDrawable(Draw);
    }


    public AA_KingTieBreakActivity() {
    }

    public static void setadapter(String huy[], Spinner sp1, Context context) {
        ArrayAdapter<String> adapterFP = new ArrayAdapter<>(context, R.layout.aa_layout_for_tog_spinner, huy);
        adapterFP.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapterFP);

    }

    public static int getSpinnerItem(Spinner ss) {
        ss.getSelectedItemPosition();
        return ss.getSelectedItemPosition();
    }

    public static void setNames(TextView z1, String c1, String c2) {
        if (c2.equals("")) {
            z1.setText(c1);
        } else {
            z1.setText(c1 + "\n" + c2);
        }
    }

    public void undo(View view) {

        ad = new AlertDialog.Builder(context);

        ad.setMessage(getString(R.string.really_undo));

        ad.setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                undo1();
            }
        });
        ad.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
            }
        });
        ad.setCancelable(true);
        ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
            }
        });
        ad.show();
    }

    public static void setAddNames(TextView zz1, String cc1, String cc2) {
        if (cc2.equals("")) {
            zz1.setText(cc1);
        } else {
            zz1.setText(cc1 + " " + cc2);
        }
    }

    public void firstPlayerGoal(View v) {

        switch (getSpinnerItem(spinnerFP)) {
            case 0: {
                Toast.makeText(getBaseContext(), getString(R.string.select_gt), Toast.LENGTH_SHORT).show();
                break;
            }
            case 1: {

                deleteRow(undoCounter);
                noEnabled(redoButton, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(7) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper.getLastId() - 7)});

                setCurKTBFPS();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper.getLastId() - 7);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurrKTBFPS();
                intCurrKTBSPS();
                goalsCounter++;

                break;
            }
            case 2: {

                deleteRow(undoCounter);
                noEnabled(redoButton, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(6) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper.getLastId() - 6)});

                setCurKTBFPS();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper.getLastId() - 6);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurrKTBFPS();
                intCurrKTBSPS();
                goalsCounter++;

                break;
            }
            case 3: {

                deleteRow(undoCounter);
                noEnabled(redoButton, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(1) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper.getLastId() - 1)});

                setCurKTBFPS();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper.getLastId() - 1);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurrKTBFPS();
                intCurrKTBSPS();
                goalsCounter++;

                break;
            }
            case 4: {

                deleteRow(undoCounter);
                noEnabled(redoButton, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(0) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper.getLastId())});

                setCurKTBFPS();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper.getLastId());
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurrKTBFPS();
                intCurrKTBSPS();
                goalsCounter++;

                break;
            }
        }

        Log.d(TAG, "goalsCounter = " + goalsCounter);

        spinnerFP.setSelection(0);
        spinnerSP.setSelection(0);
    }

    public void secondPlayerGoal(View v) {
        switch (getSpinnerItem(spinnerSP)) {
            case 0: {
                Toast.makeText(getBaseContext(), getString(R.string.select_gt), Toast.LENGTH_SHORT).show();
                break;
            }
            case 1: {

                deleteRow(undoCounter);
                noEnabled(redoButton, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(3) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper.getLastId() - 3)});

                setCurKTBSPS();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper.getLastId() - 3);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurrKTBFPS();
                intCurrKTBSPS();
                goalsCounter++;

                break;
            }

            case 2: {

                deleteRow(undoCounter);
                noEnabled(redoButton, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(2) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper.getLastId() - 2)});

                setCurKTBSPS();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper.getLastId() - 2);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurrKTBFPS();
                intCurrKTBSPS();
                goalsCounter++;

                break;
            }
            case 3: {

                deleteRow(undoCounter);
                noEnabled(redoButton, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(5) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper.getLastId() - 5)});

                setCurKTBSPS();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper.getLastId() - 5);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurrKTBFPS();
                intCurrKTBSPS();
                goalsCounter++;

                break;
            }
            case 4: {

                deleteRow(undoCounter);
                noEnabled(redoButton, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(4) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper.getLastId() - 4)});

                setCurKTBSPS();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper.getLastId() - 4);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurrKTBFPS();
                intCurrKTBSPS();
                goalsCounter++;

                break;
            }
        }


        Log.d(TAG, "goalsCounter = " + goalsCounter);
        spinnerSP.setSelection(0);
        spinnerFP.setSelection(0);
    }

    public void intCurrKTBFPS() {

        String curKTBFPS = pointFP.getText().toString();

        ContentValues values = new ContentValues();
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        if (curKTBFPS.equals("Ad")) {
            values.put(Contract.UndoNumbers.COLUMN_KTBFPS, -2);
        } else {
            values.put(Contract.UndoNumbers.COLUMN_KTBFPS, Integer.valueOf(curKTBFPS));
        }
        db.update(Contract.UndoNumbers.TABLE_NAME_1, values, Contract.UndoNumbers.COLUMN_NUM_NUM + " = ?", new String[]{Integer.toString(mDbHelper.getMaxNumNum())});
    }

    public void intCurrKTBSPS() {

        String curKTBSPS = pointSP.getText().toString();

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        if (curKTBSPS.equals("Ad")) {
            values.put(Contract.UndoNumbers.COLUMN_KTBSPS, -2);
        } else {
            values.put(Contract.UndoNumbers.COLUMN_KTBSPS, Integer.valueOf(curKTBSPS));
        }
        db.update(Contract.UndoNumbers.TABLE_NAME_1, values, Contract.UndoNumbers.COLUMN_NUM_NUM + " = ?", new String[]{Integer.toString(mDbHelper.getMaxNumNum())});
    }

    public void setCurKTBFPS() {

        ++FPscore;
        pointFP.setText(String.valueOf(FPscore));
        if (FPscore == 10 & SPscore <= FPscore - 2 | FPscore > 10 & SPscore <= FPscore - 2) {

            Toast.makeText(this, pl1_1 + " " + pl1_2 + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();

            setButNotEnabled(goalFP, goalSP, spinnerFP, spinnerSP);

            stopMatchIsEnabled = false;
            KTBStatusMatch = "firstPlayer";
            plFirst.setBackgroundResource(R.drawable.frame_winner);

            beEnabled(saveBtn, getDrawable(R.drawable.ic_save_black_24dp));
        }
    }

    public void setCurKTBSPS() {

        ++SPscore;
        pointSP.setText(String.valueOf(SPscore));
        if (SPscore == 10 & FPscore <= SPscore - 2 | SPscore > 10 & FPscore <= SPscore - 2) {

            Toast.makeText(this, pl2_1 + " " + pl2_2 + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();

            setButNotEnabled(goalFP, goalSP, spinnerFP, spinnerSP);

            stopMatchIsEnabled = false;
            beEnabled(saveBtn, getDrawable(R.drawable.ic_save_black_24dp));
            KTBStatusMatch = "secondPlayer";
            plSecond.setBackgroundResource(R.drawable.frame_winner);
        }
    }

    public int getLastIdfromUndoTable() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT " + Contract.UndoNumbers.COLUMN_NUM_ID + " FROM "
                + Contract.UndoNumbers.TABLE_NAME_1 + " WHERE " + Contract.UndoNumbers.COLUMN_NUM_NUM +
                " = (SELECT MAX( " + Contract.UndoNumbers.COLUMN_NUM_NUM + " ) FROM " + Contract.UndoNumbers.TABLE_NAME_1 + " ) - " + (undoCounter - 1), new String[]{});
        cursor.moveToFirst();
        lastIdfromUT = (int) cursor.getLong(0);
        cursor.close();
        return lastIdfromUT;
    }

    public void updateColumnNumberDown() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        String insertQuery = " UPDATE " + Contract.ContractNames.TABLE_NAME + " SET " + Contract.ContractNames.COLUMN_NUMBER
                + " = ((SELECT " + Contract.ContractNames.COLUMN_NUMBER + " FROM " + Contract.ContractNames.TABLE_NAME +
                " WHERE " + Contract.ContractNames._ID + " = " + getLastIdfromUndoTable() + ") - 1 ) WHERE " + Contract.ContractNames._ID + " = " + getLastIdfromUndoTable();
        db.execSQL(insertQuery);
    }

    public void updateColumnNumberUp() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        String insertQuery = " UPDATE " + Contract.ContractNames.TABLE_NAME + " SET " + Contract.ContractNames.COLUMN_NUMBER
                + " = ((SELECT " + Contract.ContractNames.COLUMN_NUMBER + " FROM " + Contract.ContractNames.TABLE_NAME +
                " WHERE " + Contract.ContractNames._ID + " = " + getLastIdfromUndoTable() + ") + 1 ) WHERE " + Contract.ContractNames._ID + " = " + getLastIdfromUndoTable();
        db.execSQL(insertQuery);
    }

    public void undo1() {

        goalFP.setEnabled(true);
        goalSP.setEnabled(true);
        spinnerFP.setEnabled(true);
        spinnerSP.setEnabled(true);
        spinnerFP.setSelection(0);
        spinnerSP.setSelection(0);
        noEnabled(saveBtn, getDrawable(R.drawable.ic_save_black_24dp_0_3));

        plFirst.setBackgroundResource(R.drawable.frame_player);
        plSecond.setBackgroundResource(R.drawable.frame_player);

        undoCounter = undoCounter + 1;
        stopMatchIsEnabled = true;
        KTBStatusMatch = "Paused";

        pointFP.setText(mDbHelper.getStringFromNumDataFromUndoTable("ktbfps", undoCounter));
        pointSP.setText(mDbHelper.getStringFromNumDataFromUndoTable("ktbsps", undoCounter));

        FPscore = Integer.valueOf(pointFP.getText().toString());
        SPscore = Integer.valueOf(pointSP.getText().toString());

        if (Integer.valueOf(pointFP.getText().toString()) == 0 & Integer.valueOf(pointSP.getText().toString()) == 0) {
            undo.setEnabled(false);
        }
        updateColumnNumberDown();
        beEnabled(redoButton, getDrawable(R.drawable.ic_redo_black_24dp));

        goalsCounter--;

        if (goalsCounter <= 0) {
            noEnabled(undo, getDrawable(R.drawable.ic_undo_black_24dp_0_3));
        }
        Log.d(TAG, "goalsCounter = " + goalsCounter);
    }

    public void redo1() {

        beEnabled(undo, getDrawable(R.drawable.ic_undo_black_24dp));
        updateColumnNumberUp();

        undoCounter = undoCounter - 1;

        pointFP.setText(mDbHelper.getStringFromNumDataFromUndoTable("ktbfps", undoCounter));
        pointSP.setText(mDbHelper.getStringFromNumDataFromUndoTable("ktbsps", undoCounter));

        FPscore = Integer.valueOf(pointFP.getText().toString());
        SPscore = Integer.valueOf(pointSP.getText().toString());


        if (undoCounter == 0) {

            noEnabled(redoButton, getDrawable(R.drawable.ic_redo_black_24dp_0_3));
        }
        if (Integer.valueOf(pointSP.getText().toString()) == 10 & Integer.valueOf(pointFP.getText().toString()) <= Integer.valueOf(pointSP.getText().toString()) - 2
                | Integer.valueOf(pointSP.getText().toString()) > 10 & Integer.valueOf(pointFP.getText().toString()) <= Integer.valueOf(pointSP.getText().toString()) - 2
                | Integer.valueOf(pointFP.getText().toString()) == 10 & Integer.valueOf(pointSP.getText().toString()) <= Integer.valueOf(pointFP.getText().toString()) - 2
                | Integer.valueOf(pointFP.getText().toString()) > 10 & Integer.valueOf(pointSP.getText().toString()) <= Integer.valueOf(pointFP.getText().toString()) - 2) {

            setButNotEnabled(goalFP, goalSP, spinnerFP, spinnerSP);
            beEnabled(saveBtn, getDrawable(R.drawable.ic_save_black_24dp));
            stopMatchIsEnabled = false;

        }
        if ((Integer.valueOf(pointSP.getText().toString()) == 10 & Integer.valueOf(pointFP.getText().toString()) <= Integer.valueOf(pointSP.getText().toString()) - 2)
                | (Integer.valueOf(pointSP.getText().toString()) > 10 & Integer.valueOf(pointFP.getText().toString()) <= Integer.valueOf(pointSP.getText().toString()) - 2)) {

            plSecond.setBackgroundResource(R.drawable.frame_winner);
            KTBStatusMatch = "secondPlayer";

        } else if ((Integer.valueOf(pointFP.getText().toString()) == 10 & Integer.valueOf(pointSP.getText().toString()) <= Integer.valueOf(pointFP.getText().toString()) - 2)
                | (Integer.valueOf(pointFP.getText().toString()) > 10 & Integer.valueOf(pointSP.getText().toString()) <= Integer.valueOf(pointFP.getText().toString()) - 2)) {

            plFirst.setBackgroundResource(R.drawable.frame_winner);
            KTBStatusMatch = "firstPlayer";
        }

        goalsCounter++;
        if (goalsCounter > 0) {
            beEnabled(undo, getDrawable(R.drawable.ic_undo_black_24dp));
        }
        Log.d(TAG, "goalsCounter = " + goalsCounter);
    }

    public void redo(View view) {

        ad = new AlertDialog.Builder(context);

        ad.setMessage(getString(R.string.really_redo));

        ad.setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                redo1();
            }
        });
        ad.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
            }
        });
        ad.setCancelable(true);
        ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
            }
        });
        ad.show();
    }

    public void deleteRow(int a) {

        beEnabled(undo, getDrawable(R.drawable.ic_undo_black_24dp));
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String insertQuery = " DELETE FROM " + Contract.UndoNumbers.TABLE_NAME_1 + " WHERE " + Contract.UndoNumbers.COLUMN_NUM_NUM +
                " IN ( SELECT " + Contract.UndoNumbers.COLUMN_NUM_NUM + " FROM " + Contract.UndoNumbers.TABLE_NAME_1 +
                " ORDER BY " + Contract.UndoNumbers.COLUMN_NUM_NUM + " DESC LIMIT " + a + ")";
        db.execSQL(insertQuery);
        undoCounter = 0;
    }


    public void saveResultGoToMM() {

        saveResult();
        mDbHelper.clearTable(Contract.UndoNumbers.TABLE_NAME_1);
        mDbHelper.clearTable(Contract.ContractNames.TABLE_NAME);
        finish();
        Intent intent1 = new Intent(this, AA_MainActivity.class);
        startActivity(intent1);
    }

    public void noSaveResultGoToMM() {
        mDbHelper.clearTable(Contract.UndoNumbers.TABLE_NAME_1);
        mDbHelper.clearTable(Contract.ContractNames.TABLE_NAME);
        finish();
        Intent intent1 = new Intent(this, AA_MainActivity.class);
        startActivity(intent1);
    }

    public void goToHelp() {
        Intent helpIntent = new Intent(this, DataBase.class);
        startActivity(helpIntent);
    }


    public void saveResult() {

        if (getIntent().getExtras().getBoolean("isNew")) {

            mDbHelper.putName13("fullName", "matchMode", "courtSurface", pl1_1, pl1_2, pl2_1, pl2_2, KTBStatusMatch,
                    mDbHelper.getStringFromNumDataFromUndoTable("ktbfps", undoCounter), mDbHelper.getStringFromNumDataFromUndoTable("ktbsps", undoCounter),
                    "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, 7, 3, 6, 2, 5, 1, 4, 0, 0, 0, 0, getDFB(stopMatchIsEnabled), 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        } else {

            mDbHelper.putName122("fullName", "matchMode", "courtSurface", pl1_1, pl1_2, pl2_1, pl2_2, KTBStatusMatch,
                    mDbHelper.getStringFromNumDataFromUndoTable("ktbfps", undoCounter), mDbHelper.getStringFromNumDataFromUndoTable("ktbsps", undoCounter),
                    "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, 7, 3, 6, 2, 5, 1, 4, 0, 0, 0, 0, getDFB(stopMatchIsEnabled), 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

        }

    }

    public void saveResultFromStat(String st) {

        KTBStatusMatch = st;


        if (getIntent().getExtras().getBoolean("isNew")) {
            mDbHelper.putName13("fullName", "matchMode", "courtSurface", pl1_1, pl1_2, pl2_1, pl2_2, KTBStatusMatch,
                    mDbHelper.getStringFromNumDataFromUndoTable("ktbfps", undoCounter), mDbHelper.getStringFromNumDataFromUndoTable("ktbsps", undoCounter),
                    "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, 7, 3, 6, 2, 5, 1, 4, 0, 0, 0, 0, getDFB(stopMatchIsEnabled), 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        } else {

            mDbHelper.putName122("fullName", "matchMode", "courtSurface", pl1_1, pl1_2, pl2_1, pl2_2, KTBStatusMatch,
                    mDbHelper.getStringFromNumDataFromUndoTable("ktbfps", undoCounter), mDbHelper.getStringFromNumDataFromUndoTable("ktbsps", undoCounter),
                    "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, 7, 3, 6, 2, 5, 1, 4, 0, 0, 0, 0, getDFB(stopMatchIsEnabled), 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        mDbHelper.clearTable(Contract.UndoNumbers.TABLE_NAME_1);
        mDbHelper.clearTable(Contract.ContractNames.TABLE_NAME);
        finish();
        Intent intent1 = new Intent(this, AA_MainActivity.class);
        startActivity(intent1);
    }

    public int getDFB(Boolean b) {
        int u = 0;
        if (b) {
            u = 1;
        }
        return u;
    }

    public int curNumberOfPoints(int p) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT number FROM statistic  WHERE _ID = " + Integer.toString(mDbHelper.getLastId() - p), new String[]{});
        cursor.moveToFirst();
        curNum = (int) cursor.getLong(0);
        cursor.close();
        return curNum;
    }

    public void saveAndGo(View view) {
            saveResultGoToMM();
    }

    public static void setButNotEnabled(Button b1, Button b2, Spinner sp1, Spinner sp2) {
        b1.setEnabled(false);
        b2.setEnabled(false);
        sp1.setEnabled(false);
        sp2.setEnabled(false);
    }

    @Override
    public void onDestroy() {
        mDbHelper.clearTable(Contract.UndoNumbers.TABLE_NAME_1);
        mDbHelper.clearTable(Contract.ContractNames.TABLE_NAME);
        super.onDestroy();
    }
}

