package com.example.android.ver13;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.ver13.data.Contract;
import com.example.android.ver13.data.TennisHelper;

import static com.example.android.ver13.AA_KingTieBreakActivity.beEnabled;
import static com.example.android.ver13.AA_KingTieBreakActivity.noEnabled;
import static com.example.android.ver13.AA_KingTieBreakActivity.setNames;
import static com.example.android.ver13.AA_KingTieBreakActivity.typeGoals;
import static com.example.android.ver13.SearchedMatchsActivity.selId;

public class AA_Set1Activity extends AppCompatActivity {
    public static int tp[] = {0, 15, 30, 40, -2};
    private int i = 0;
    private int k = 0;
    int n1;
    String sp1, pl1_1_1set, pl1_2_1set, pl2_1_1set, pl2_2_1set, reasonsSelectedItem;
    private int undoCounter1Set = 0;
    String sp2;
    String adv = "Ad";
    private int fpls1 = 0;
    private int spls1 = 0;
    boolean tb = false;
    boolean tb1pl = false;
    boolean tb2pl = false;
    boolean tieBreakDone = false;
    private boolean stopMatchIsEnabled1Set = true;




    int tbfp = 0;
    int tbsp = 0;
    public static String degreeDigits[] = {"\u2070", "\u00B9", "\u2072", "\u2073", "\u2074", "\u2075", "\u2076", "\u2077", "\u2078", "\u2079"};
    String str = "";
    String str1s = "";
    Spinner spinner1p1s, spinner2p1s;
    TextView playerFirst1Set, playerSecond1Set, pointFP1Set, pointSP1Set, pointFPFirstSet1Set, tableScore1Set, pointSPFirstSet1Set;
    private TennisHelper mDbHelper1Set;
    int curNum1Set, lastIdfromUT1Set;
    Button goalFP1Set, goalSP1Set;
    ImageButton undo_1Set, redo_1Set, saveButton1Set;
    private String Set1StatusMatch = "Paused";
    AlertDialog.Builder ad1Set;
    Context context;

    int goalsCounter1Set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aa_activity_set1);

        goalsCounter1Set = 0;

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.table_score));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mDbHelper1Set = new TennisHelper(this);
        context = AA_Set1Activity.this;

        typeGoals[0] = getResources().getString(R.string.select_gt_for_spinner);
        typeGoals[1] = getResources().getString(R.string.Winner);
        typeGoals[2] = getResources().getString(R.string.Ace);
        typeGoals[3] = getResources().getString(R.string.Unenforced_error_for_spinner);
        typeGoals[4] = getResources().getString(R.string.Double_error_for_spinner);

        tableScore1Set = (TextView) findViewById(R.id.addToTableScoreTV1Set);
        tableScore1Set.setText(R.string.one_set);

        spinner1p1s = (Spinner) findViewById(R.id.spinner1p1sTV);
        spinner2p1s = (Spinner) findViewById(R.id.spinner2p1sTV);
        AA_KingTieBreakActivity.setadapter(typeGoals, spinner1p1s, this);
        AA_KingTieBreakActivity.setadapter(typeGoals, spinner2p1s, this);


        playerFirst1Set = (TextView) findViewById(R.id.playerFirstTV1Set);
        playerSecond1Set = (TextView) findViewById(R.id.playerSecondTV1Set);
        TextView nameFirstPlayer = (TextView) findViewById(R.id.nameFirstPlayerTV);
        TextView nameSecondPlayer = (TextView) findViewById(R.id.nameSecondPlayerTV);

        pointFP1Set = (TextView) findViewById(R.id.pointFPTV1Set);
        pointSP1Set = (TextView) findViewById(R.id.pointSPTV1Set);

        pointFPFirstSet1Set = (TextView) findViewById(R.id.pointFPFirstSet1SetTV);
        pointSPFirstSet1Set = (TextView) findViewById(R.id.pointSPFirstSet1SetTV);

        if (getIntent().getExtras().getBoolean("isNew")) {


            pl1_1_1set = getIntent().getExtras().getString("user");
            pl1_2_1set = getIntent().getExtras().getString("userSur");
            pl2_1_1set = getIntent().getExtras().getString("der");
            pl2_2_1set = getIntent().getExtras().getString("derSur");

            pointFP1Set.setText(String.valueOf(tp[i]));
            pointSP1Set.setText(String.valueOf(tp[k]));

            pointFPFirstSet1Set.setText(String.valueOf(0));
            pointSPFirstSet1Set.setText(String.valueOf(0));

        } else {

            pl1_1_1set = mDbHelper1Set.getNameById("name_A_1", selId);
            pl1_2_1set = mDbHelper1Set.getNameById("name_A_2", selId);
            pl2_1_1set = mDbHelper1Set.getNameById("name_B_1", selId);
            pl2_2_1set = mDbHelper1Set.getNameById("name_B_2", selId);

            if (mDbHelper1Set.getNumById("ktbfpsST", selId) == -2) {
                pointFP1Set.setText(adv);
            } else {
                pointFP1Set.setText(String.valueOf(mDbHelper1Set.getNumById("ktbfpsST", selId)));
            }

            if (mDbHelper1Set.getNumById("ktbspsST", selId) == -2) {
                pointSP1Set.setText(adv);
            } else {
                pointSP1Set.setText(String.valueOf(mDbHelper1Set.getNumById("ktbspsST", selId)));
            }

            pointFPFirstSet1Set.setText(String.valueOf(mDbHelper1Set.getNumById("set1aST", selId)));
            pointSPFirstSet1Set.setText(String.valueOf(mDbHelper1Set.getNumById("set1bST", selId)));

            tb = mDbHelper1Set.getNumById("tb", selId) > 0;
            tb1pl = mDbHelper1Set.getNumById("tb1pl", selId) > 0;
            tb2pl = mDbHelper1Set.getNumById("tb2pl", selId) > 0;
            tieBreakDone = mDbHelper1Set.getNumById("tieBreakDone", selId) > 0;
            stopMatchIsEnabled1Set = mDbHelper1Set.getNumById("stopMatchIsEnabled", selId) > 0;


            tbfp = mDbHelper1Set.getNumById("set1aST_tb", selId);
            tbsp = mDbHelper1Set.getNumById("set1bST_tb", selId);


            mDbHelper1Set.insert1SetScore(mDbHelper1Set.getNumById("ktbfpsST", selId), mDbHelper1Set.getNumById("ktbspsST", selId),
                    mDbHelper1Set.getNumById("set1aST", selId), mDbHelper1Set.getNumById("set1bST", selId));

            mDbHelper1Set.insertPlayers(pl1_1_1set, pl1_2_1set, pl2_1_1set, pl2_2_1set);
        }

        setNames(playerFirst1Set, pl1_1_1set, pl1_2_1set);
        setNames(playerSecond1Set, pl2_1_1set, pl2_2_1set);
        AA_KingTieBreakActivity.setAddNames(nameFirstPlayer, pl1_1_1set, pl1_2_1set);
        AA_KingTieBreakActivity.setAddNames(nameSecondPlayer, pl2_1_1set, pl2_2_1set);

        undo_1Set = (ImageButton) findViewById(R.id.undo1SetButton);
        redo_1Set = (ImageButton) findViewById(R.id.redo1SetButton);
        saveButton1Set = (ImageButton) findViewById(R.id._saveButton1Set);


        noEnabled(redo_1Set, getDrawable(R.drawable.ic_redo_black_24dp_0_3));
        noEnabled(undo_1Set, getDrawable(R.drawable.ic_undo_black_24dp_0_3));
        noEnabled(saveButton1Set, getDrawable(R.drawable.ic_save_black_24dp_0_3));

        goalFP1Set = (Button) findViewById(R.id.goalFPButton1Set);
        goalSP1Set = (Button) findViewById(R.id.goalSPButton1Set);

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if (!stopMatchIsEnabled1Set) {
            menu.getItem(1).setEnabled(false);
        } else {
            menu.getItem(1).setEnabled(true);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.score_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.statistic_item:
                Intent intent1 = new Intent(this, AA_StatisticActivity.class);
                startActivity(intent1);
                break;

            case R.id.stopAGame_item:

                final String na_1 = getString(R.string.Retired) + " " + playerFirst1Set.getText().toString() + getString(R.string.loss);
                final String na_2 = getString(R.string.Retired) + " " + playerSecond1Set.getText().toString() + getString(R.string.loss);
                final String na_3 = getString(R.string.Another_Match_is_paused);
                final String na_4 = getString(R.string.Another_Match_is_finished);
                final String[] reasons = {na_1, na_2, na_3, na_4};
                ad1Set = new AlertDialog.Builder(this);
                ad1Set.setTitle(getString(R.string.Choose_the_reason))
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


                                        ad1Set = new AlertDialog.Builder(context);

                                        ad1Set.setMessage(getString(R.string.Do_you_want));

                                        ad1Set.setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener() {
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
                                        ad1Set.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int arg1) {
                                                noSaveResultGoToMM();
                                                if (!getIntent().getExtras().getBoolean("isNew")) {
                                                    mDbHelper1Set.putFreeRowInStatisticSaveTable(selId);
                                                }
                                            }
                                        });
                                        ad1Set.setCancelable(true);
                                        ad1Set.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                            public void onCancel(DialogInterface dialog) {
                                            }
                                        });
                                        ad1Set.show();
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
                ad1Set.show();
                break;

            case R.id.help_item:

                goToHelp();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void onBackPressed() {



            ad1Set = new AlertDialog.Builder(context);
            ad1Set.setMessage(getString(R.string.ad_alert_1));
            ad1Set.setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int arg1) {

                    saveResultGoToMM();
                }
            });
            ad1Set.setNeutralButton(getString(R.string.Continue_match),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int id) {
                            dialog.cancel();
                        }
                    });

            ad1Set.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int arg1) {
                    noSaveResultGoToMM();
                    if (!getIntent().getExtras().getBoolean("isNew")) {
                        mDbHelper1Set.putFreeRowInStatisticSaveTable(selId);
                    }
                }
            });
            ad1Set.setCancelable(true);
            ad1Set.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialog) {
                }
            });
            ad1Set.show();
    }

    public void firstPlayerGoal1Set(View v) {

        switch (AA_KingTieBreakActivity.getSpinnerItem(spinner1p1s)) {
            case 0: {
                Toast.makeText(getBaseContext(), getString(R.string.select_gt), Toast.LENGTH_SHORT).show();
                break;
            }
            case 1: {
                deleteRow1Set();
                noEnabled(redo_1Set, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper1Set.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(7) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper1Set.getLastId() - 7)});

                setFirstPlScore1Set();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper1Set.getLastId() - 7);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper1Set.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet1AllplScore(pointFP1Set, pointSP1Set, pointFPFirstSet1Set, pointSPFirstSet1Set, tieBreakDone);
                goalsCounter1Set++;
                break;
            }

            case 2: {
                deleteRow1Set();
                noEnabled(redo_1Set, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper1Set.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(6) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper1Set.getLastId() - 6)});

                setFirstPlScore1Set();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper1Set.getLastId() - 6);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper1Set.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet1AllplScore(pointFP1Set, pointSP1Set, pointFPFirstSet1Set, pointSPFirstSet1Set, tieBreakDone);
                goalsCounter1Set++;
                break;
            }

            case 3: {
                deleteRow1Set();
                noEnabled(redo_1Set, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper1Set.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(1) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper1Set.getLastId() - 1)});

                setFirstPlScore1Set();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper1Set.getLastId() - 1);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper1Set.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet1AllplScore(pointFP1Set, pointSP1Set, pointFPFirstSet1Set, pointSPFirstSet1Set, tieBreakDone);
                goalsCounter1Set++;
                break;
            }

            case 4: {
                deleteRow1Set();
                noEnabled(redo_1Set, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper1Set.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(0) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper1Set.getLastId())});

                setFirstPlScore1Set();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper1Set.getLastId());
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper1Set.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet1AllplScore(pointFP1Set, pointSP1Set, pointFPFirstSet1Set, pointSPFirstSet1Set, tieBreakDone);
                goalsCounter1Set++;
                break;
            }
        }
        spinner1p1s.setSelection(0);
        spinner2p1s.setSelection(0);
    }

    public void secondPlayerGoal1Set(View v) {

        switch (AA_KingTieBreakActivity.getSpinnerItem(spinner2p1s)) {
            case 0: {
                Toast.makeText(getBaseContext(), getString(R.string.select_gt), Toast.LENGTH_SHORT).show();
                break;
            }
            case 1: {
                deleteRow1Set();
                noEnabled(redo_1Set, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper1Set.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(3) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper1Set.getLastId() - 3)});

                setSecondPlScore1Set();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper1Set.getLastId() - 3);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper1Set.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet1AllplScore(pointFP1Set, pointSP1Set, pointFPFirstSet1Set, pointSPFirstSet1Set, tieBreakDone);
                goalsCounter1Set++;
                break;
            }

            case 2: {
                deleteRow1Set();
                noEnabled(redo_1Set, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper1Set.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(2) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper1Set.getLastId() - 2)});

                setSecondPlScore1Set();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper1Set.getLastId() - 2);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper1Set.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet1AllplScore(pointFP1Set, pointSP1Set, pointFPFirstSet1Set, pointSPFirstSet1Set, tieBreakDone);
                goalsCounter1Set++;
                break;
            }

            case 3: {
                deleteRow1Set();
                noEnabled(redo_1Set, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper1Set.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(5) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper1Set.getLastId() - 5)});

                setSecondPlScore1Set();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper1Set.getLastId() - 5);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper1Set.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet1AllplScore(pointFP1Set, pointSP1Set, pointFPFirstSet1Set, pointSPFirstSet1Set, tieBreakDone);
                goalsCounter1Set++;
                break;
            }

            case 4: {
                deleteRow1Set();
                noEnabled(redo_1Set, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper1Set.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(4) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper1Set.getLastId() - 4)});

                setSecondPlScore1Set();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper1Set.getLastId() - 4);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper1Set.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet1AllplScore(pointFP1Set, pointSP1Set, pointFPFirstSet1Set, pointSPFirstSet1Set, tieBreakDone);
                goalsCounter1Set++;
                break;
            }
        }
        spinner1p1s.setSelection(0);
        spinner2p1s.setSelection(0);
    }


    public int curNumberOfPoints(int p) {
        SQLiteDatabase db = mDbHelper1Set.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT number FROM statistic  WHERE _ID = " + Integer.toString(mDbHelper1Set.getLastId() - p), new String[]{});
        cursor.moveToFirst();
        curNum1Set = (int) cursor.getLong(0);
        cursor.close();
        return curNum1Set;
    }


    public void setFirstPlScore1Set() {

        tieBreakDone = false;

        tb1pl = true;

        if (tb) {
            tieBreak(tb1pl, tb2pl);
        } else {

            k = getKorI(pointSP1Set);
            i = getKorI(pointFP1Set);

            fpls1 = Integer.valueOf(pointFPFirstSet1Set.getText().toString());
            spls1 = Integer.valueOf(pointSPFirstSet1Set.getText().toString());

            sp1 = pointFP1Set.getText().toString();
            switch (sp1) {
                case "0":
                    i = 1;
                    break;
                case "15":
                    i = 2;
                    break;
                case "30":
                    i = 3;
                    break;
                case "40":
                    if (i > k) {
                        i = k = 0;
                        fpls1++;
                    } else if (i == k) {
                        i = 4;
                        break;
                    } else {
                        i = k = 3;
                    }
                    break;
                case "Ad":
                    i = k = 0;
                    fpls1++;
                    break;
            }
            if (i == 4) {
                pointFP1Set.setText(adv);
                pointSP1Set.setText(String.valueOf(tp[k]));
                pointFPFirstSet1Set.setText(String.valueOf(fpls1));
                tb1pl = false;
            } else {
                pointFP1Set.setText(String.valueOf(tp[i]));
                pointSP1Set.setText(String.valueOf(tp[k]));
                pointFPFirstSet1Set.setText(String.valueOf(fpls1));
                tb1pl = false;
            }
            checkSetNumber(fpls1, spls1);
        }
    }

    public void setSecondPlScore1Set() {

        tieBreakDone = false;
        tb2pl = true;
        if (tb) {
            tieBreak(tb1pl, tb2pl);
        } else {
            i = getKorI(pointFP1Set);
            k = getKorI(pointSP1Set);

            fpls1 = Integer.valueOf(pointFPFirstSet1Set.getText().toString());
            spls1 = Integer.valueOf(pointSPFirstSet1Set.getText().toString());

            sp2 = pointSP1Set.getText().toString();
            switch (sp2) {
                case "0":
                    k = 1;
                    break;
                case "15":
                    k = 2;
                    break;
                case "30":
                    k = 3;
                    break;
                case "40":
                    if (k > i) {
                        k = i = 0;
                        spls1++;
                    } else if (k == i) {
                        k = 4;
                    } else {
                        i = k = 3;
                    }
                    break;
                case "Ad":
                    i = k = 0;
                    spls1++;
                    break;
            }
            if (k == 4) {
                pointSP1Set.setText(adv);
                pointFP1Set.setText(String.valueOf(tp[i]));
                pointSPFirstSet1Set.setText(String.valueOf(spls1));
                tb2pl = false;
            } else {
                pointSP1Set.setText(String.valueOf(tp[k]));
                pointFP1Set.setText(String.valueOf(tp[i]));
                pointSPFirstSet1Set.setText(String.valueOf(spls1));
                tb2pl = false;
            }

            checkSetNumber(fpls1, spls1);
        }
    }


    public void checkSetNumber(int y1, int y2) {

        if (y1 == 6 & y2 <= y1 - 2 | y1 == 7 & y2 <= y1 - 2) {

            Toast.makeText(this, pl1_1_1set + " " + pl1_2_1set + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();

            Set1StatusMatch = "firstPlayer";
            playerFirst1Set.setBackgroundResource(R.drawable.frame_winner);
            // saveButton1Set.setEnabled(true);
            beEnabled(saveButton1Set, getDrawable(R.drawable.ic_save_black_24dp));
            stopMatchIsEnabled1Set = false;

            AA_KingTieBreakActivity.setButNotEnabled(goalFP1Set, goalSP1Set, spinner1p1s, spinner2p1s);

        } else if (y2 == 6 & y1 <= y2 - 2 | y2 == 7 & y1 <= y2 - 2) {

            Toast.makeText(this, pl2_1_1set + " " + pl2_2_1set + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();

            Set1StatusMatch = "secondPlayer";
            playerSecond1Set.setBackgroundResource(R.drawable.frame_winner);
            // saveButton1Set.setEnabled(true);
            beEnabled(saveButton1Set, getDrawable(R.drawable.ic_save_black_24dp));
            stopMatchIsEnabled1Set = false;

            AA_KingTieBreakActivity.setButNotEnabled(goalFP1Set, goalSP1Set, spinner1p1s, spinner2p1s);


        } else if (y2 == 6 & y1 == 6) {

            Toast.makeText(getBaseContext(), getString(R.string.Tie_break_starts), Toast.LENGTH_SHORT).show();

            tb1pl = false;
            tb2pl = false;
            tbfp = 0;
            tbsp = 0;
            tb = true;
        }
    }

    public void tieBreak(boolean pla, boolean plb) {

        if (pla) {

            tbfp = Integer.valueOf(pointFP1Set.getText().toString());
            tbsp = Integer.valueOf(pointSP1Set.getText().toString());

            tbfp++;

            pointFP1Set.setText(String.valueOf(tbfp));
            pointSP1Set.setText(String.valueOf(tbsp));
            tb1pl = false;

        } else if (plb) {

            tbfp = Integer.valueOf(pointFP1Set.getText().toString());
            tbsp = Integer.valueOf(pointSP1Set.getText().toString());

            tbsp++;
            pointFP1Set.setText(String.valueOf(tbfp));
            pointSP1Set.setText(String.valueOf(tbsp));
            tb2pl = false;
        }
        checkTieBreakNumber(tbfp, tbsp);
    }

    public void checkTieBreakNumber(int x1, int x2) {

        if (x1 == 7 & x2 <= x1 - 2 | x1 > 7 & x2 <= x1 - 2) {

            pointFPFirstSet1Set.setText(String.valueOf(7));

            convert(x1, x2);

            Toast.makeText(this, pl1_1_1set + " " + pl1_2_1set + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();

            AA_KingTieBreakActivity.setButNotEnabled(goalFP1Set, goalSP1Set, spinner1p1s, spinner2p1s);

            Set1StatusMatch = "firstPlayer";
            playerFirst1Set.setBackgroundResource(R.drawable.frame_winner);
            // saveButton1Set.setEnabled(true);
            beEnabled(saveButton1Set, getDrawable(R.drawable.ic_save_black_24dp));
            stopMatchIsEnabled1Set = false;


        } else if (x2 == 7 & x1 <= x2 - 2 | x2 > 7 & x1 <= x2 - 2) {

            pointSPFirstSet1Set.setText(String.valueOf(7));

            convert(x1, x2);

            Toast.makeText(this, pl2_1_1set + " " + pl2_2_1set + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();

            AA_KingTieBreakActivity.setButNotEnabled(goalFP1Set, goalSP1Set, spinner1p1s, spinner2p1s);

            Set1StatusMatch = "secondPlayer";
            playerSecond1Set.setBackgroundResource(R.drawable.frame_winner);
            // saveButton1Set.setEnabled(true);
            beEnabled(saveButton1Set, getDrawable(R.drawable.ic_save_black_24dp));
            stopMatchIsEnabled1Set = false;
        }
    }

    public void convert(int mp, int yu) {

        for (int i = 0; i < String.valueOf(mp).length(); i++) {
            int k = String.valueOf(mp).length() - i;
            str = degreeDigits[Character.getNumericValue(String.valueOf(mp).charAt(k - 1))] + str;
        }

        for (int kol = 0; kol < String.valueOf(yu).length(); kol++) {
            int kik = String.valueOf(yu).length() - kol;
            str1s = degreeDigits[Character.getNumericValue(String.valueOf(yu).charAt(kik - 1))] + str1s;
        }
        pointSPFirstSet1Set.setText(pointSPFirstSet1Set.getText() + str1s);
        pointFPFirstSet1Set.setText(pointFPFirstSet1Set.getText() + str);
        tieBreakDone = true;
        tb = false;
        pointFP1Set.setText(String.valueOf(0));
        pointSP1Set.setText(String.valueOf(0));
        str = "";
        str1s = "";
        fpls1 = 0;
        spls1 = 0;
        goalFP1Set.setEnabled(false);
        goalSP1Set.setEnabled(false);
        spinner1p1s.setEnabled(false);
        spinner2p1s.setEnabled(false);
    }

    public void intCurSet1AllplScore(TextView p1s, TextView p2s, TextView p1s1s, TextView p2s1s, boolean kl) {

        SQLiteDatabase db = mDbHelper1Set.getWritableDatabase();
        ContentValues values = new ContentValues();

        if (!kl) {
            String j1 = p1s.getText().toString();
            String j2 = p2s.getText().toString();
            String j3 = p1s1s.getText().toString();
            String j4 = p2s1s.getText().toString();
            if (j1.equals("Ad")) {
                values.put(Contract.UndoNumbers.COLUMN_KTBFPS, -2);
                values.put(Contract.UndoNumbers.COLUMN_KTBSPS, Integer.valueOf(j2));

            } else if (j2.equals("Ad")) {
                values.put(Contract.UndoNumbers.COLUMN_KTBSPS, -2);
                values.put(Contract.UndoNumbers.COLUMN_KTBFPS, Integer.valueOf(j1));
            } else {
                values.put(Contract.UndoNumbers.COLUMN_KTBFPS, Integer.valueOf(j1));
                values.put(Contract.UndoNumbers.COLUMN_KTBSPS, Integer.valueOf(j2));
            }

            values.put(Contract.UndoNumbers.COLUMN_SET1A, Integer.valueOf(j3));
            values.put(Contract.UndoNumbers.COLUMN_SET1B, Integer.valueOf(j4));

        } else {

            if (tbfp > tbsp) {
                values.put(Contract.UndoNumbers.COLUMN_SET1A, 7);
                values.put(Contract.UndoNumbers.COLUMN_SET1B, 6);

            } else {
                values.put(Contract.UndoNumbers.COLUMN_SET1A, 6);
                values.put(Contract.UndoNumbers.COLUMN_SET1B, 7);
            }

            values.put(Contract.UndoNumbers.COLUMN_KTBFPS, tbfp);
            values.put(Contract.UndoNumbers.COLUMN_KTBSPS, tbsp);

        }
        db.update(Contract.UndoNumbers.TABLE_NAME_1, values, Contract.UndoNumbers.COLUMN_NUM_NUM + " = ?", new String[]{Integer.toString(mDbHelper1Set.getMaxNumNum())});
    }


    public void undo1Set(View view) {

        ad1Set = new AlertDialog.Builder(context);

        ad1Set.setMessage(getString(R.string.really_undo));

        ad1Set.setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                undo1Set1();
            }
        });
        ad1Set.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
            }
        });
        ad1Set.setCancelable(true);
        ad1Set.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
            }
        });
        ad1Set.show();
    }

    public void undo1Set1() {

        goalFP1Set.setEnabled(true);
        goalSP1Set.setEnabled(true);
        spinner1p1s.setEnabled(true);
        spinner2p1s.setEnabled(true);

        noEnabled(saveButton1Set, getDrawable(R.drawable.ic_save_black_24dp_0_3));

        Set1StatusMatch = "Paused";
        playerFirst1Set.setBackgroundResource(R.drawable.frame_player);
        playerSecond1Set.setBackgroundResource(R.drawable.frame_player);
        stopMatchIsEnabled1Set = true;

        undoCounter1Set = undoCounter1Set + 1;

        updateColumnNumberDown1Set();

        if (mDbHelper1Set.getStringFromNumDataFromUndoTable("ktbfps", undoCounter1Set).equals("-2")) {

            pointFP1Set.setText(adv);
            pointSP1Set.setText(mDbHelper1Set.getStringFromNumDataFromUndoTable("ktbsps", undoCounter1Set));

        } else if (mDbHelper1Set.getStringFromNumDataFromUndoTable("ktbsps", undoCounter1Set).equals("-2")) {

            pointSP1Set.setText(adv);
            pointFP1Set.setText(mDbHelper1Set.getStringFromNumDataFromUndoTable("ktbfps", undoCounter1Set));

        } else {

            pointFP1Set.setText(mDbHelper1Set.getStringFromNumDataFromUndoTable("ktbfps", undoCounter1Set));
            pointSP1Set.setText(mDbHelper1Set.getStringFromNumDataFromUndoTable("ktbsps", undoCounter1Set));
        }
        pointFPFirstSet1Set.setText(mDbHelper1Set.getStringFromNumDataFromUndoTable("set1a", undoCounter1Set));
        pointSPFirstSet1Set.setText(mDbHelper1Set.getStringFromNumDataFromUndoTable("set1b", undoCounter1Set));

        if (Integer.valueOf(pointFPFirstSet1Set.getText().toString()) == 0 & Integer.valueOf(pointSPFirstSet1Set.getText().toString()) == 0
                & pointFP1Set.getText().toString().equals("0") & pointSP1Set.getText().toString().equals("0")) {

            noEnabled(undo_1Set, getDrawable(R.drawable.ic_undo_black_24dp_0_3));
        }

        if (mDbHelper1Set.getStringFromNumDataFromUndoTable("set1a", undoCounter1Set).equals("6") & mDbHelper1Set.getStringFromNumDataFromUndoTable("set1b", undoCounter1Set).equals("6")) {
            tb = true;
        } else {
            tb = false;
        }

        beEnabled(redo_1Set, getDrawable(R.drawable.ic_redo_black_24dp));

        goalsCounter1Set--;
        if (goalsCounter1Set <= 0) {
            noEnabled(undo_1Set, getDrawable(R.drawable.ic_undo_black_24dp_0_3));
        }
    }

    public int getKorI(TextView ki) {
        if (ki.getText().toString().equals("0")) {
            n1 = 0;
        } else if (ki.getText().toString().equals("15")) {
            n1 = 1;
        } else if (ki.getText().toString().equals("30")) {
            n1 = 2;
        } else if (ki.getText().toString().equals("40")) {
            n1 = 3;
        } else if (ki.getText().toString().equals("Ad")) {
            n1 = 4;
        }
        return n1;
    }

    public void redo1Set(View view) {

        ad1Set = new AlertDialog.Builder(context);

        ad1Set.setMessage(getString(R.string.really_redo));

        ad1Set.setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                redo1Set1();
            }
        });
        ad1Set.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
            }
        });
        ad1Set.setCancelable(true);
        ad1Set.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
            }
        });
        ad1Set.show();
    }

    public void redo1Set1() {

        beEnabled(undo_1Set, getDrawable(R.drawable.ic_undo_black_24dp));
        spinner1p1s.setSelection(0);
        spinner2p1s.setSelection(0);
        updateColumnNumberUp1Set();

        undoCounter1Set = undoCounter1Set - 1;

        if (mDbHelper1Set.getStringFromNumDataFromUndoTable("ktbfps", undoCounter1Set).equals("-2")) {
            pointFP1Set.setText(adv);
            pointSP1Set.setText(mDbHelper1Set.getStringFromNumDataFromUndoTable("ktbsps", undoCounter1Set));
            pointFPFirstSet1Set.setText(mDbHelper1Set.getStringFromNumDataFromUndoTable("set1a", undoCounter1Set));
            pointSPFirstSet1Set.setText(mDbHelper1Set.getStringFromNumDataFromUndoTable("set1b", undoCounter1Set));

        } else if (mDbHelper1Set.getStringFromNumDataFromUndoTable("ktbsps", undoCounter1Set).equals("-2")) {

            pointFP1Set.setText(mDbHelper1Set.getStringFromNumDataFromUndoTable("ktbfps", undoCounter1Set));
            pointSP1Set.setText(adv);
            pointFPFirstSet1Set.setText(mDbHelper1Set.getStringFromNumDataFromUndoTable("set1a", undoCounter1Set));
            pointSPFirstSet1Set.setText(mDbHelper1Set.getStringFromNumDataFromUndoTable("set1b", undoCounter1Set));
        } else {
            pointFP1Set.setText(mDbHelper1Set.getStringFromNumDataFromUndoTable("ktbfps", undoCounter1Set));
            pointSP1Set.setText(mDbHelper1Set.getStringFromNumDataFromUndoTable("ktbsps", undoCounter1Set));
            pointFPFirstSet1Set.setText(mDbHelper1Set.getStringFromNumDataFromUndoTable("set1a", undoCounter1Set));
            pointSPFirstSet1Set.setText(mDbHelper1Set.getStringFromNumDataFromUndoTable("set1b", undoCounter1Set));
        }
        checkSetNumber(Integer.parseInt(mDbHelper1Set.getStringFromNumDataFromUndoTable("set1a", undoCounter1Set)), Integer.parseInt(mDbHelper1Set.getStringFromNumDataFromUndoTable("set1b", undoCounter1Set)));

        if (tieBreakDone) {
            checkTieBreakNumber(Integer.parseInt(mDbHelper1Set.getStringFromNumDataFromUndoTable("ktbfps", undoCounter1Set)), Integer.parseInt(mDbHelper1Set.getStringFromNumDataFromUndoTable("ktbsps", undoCounter1Set)));
        }

        if (undoCounter1Set == 0) {

            noEnabled(redo_1Set, getDrawable(R.drawable.ic_redo_black_24dp_0_3));
        }
        goalsCounter1Set++;

        if (goalsCounter1Set > 0) {
            beEnabled(undo_1Set, getDrawable(R.drawable.ic_undo_black_24dp));
        }
    }

    public void deleteRow1Set() {

        beEnabled(undo_1Set, getDrawable(R.drawable.ic_undo_black_24dp));
        SQLiteDatabase db = mDbHelper1Set.getReadableDatabase();
        String insertQuery = " DELETE FROM " + Contract.UndoNumbers.TABLE_NAME_1 + " WHERE " + Contract.UndoNumbers.COLUMN_NUM_NUM +
                " IN ( SELECT " + Contract.UndoNumbers.COLUMN_NUM_NUM + " FROM " + Contract.UndoNumbers.TABLE_NAME_1 +
                " ORDER BY " + Contract.UndoNumbers.COLUMN_NUM_NUM + " DESC LIMIT " + undoCounter1Set + ")";
        db.execSQL(insertQuery);
        undoCounter1Set = 0;
    }

    public int getLastIdfromUndoTable1Set() {
        SQLiteDatabase db = mDbHelper1Set.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT " + Contract.UndoNumbers.COLUMN_NUM_ID + " FROM "
                + Contract.UndoNumbers.TABLE_NAME_1 + " WHERE " + Contract.UndoNumbers.COLUMN_NUM_NUM +
                " = (SELECT MAX( " + Contract.UndoNumbers.COLUMN_NUM_NUM + " ) FROM " + Contract.UndoNumbers.TABLE_NAME_1 + " ) - " + (undoCounter1Set - 1), new String[]{});
        cursor.moveToFirst();
        lastIdfromUT1Set = (int) cursor.getLong(0);
        cursor.close();
        return lastIdfromUT1Set;
    }

    public void updateColumnNumberUp1Set() {
        SQLiteDatabase db = mDbHelper1Set.getWritableDatabase();
        String insertQuery = " UPDATE " + Contract.ContractNames.TABLE_NAME + " SET " + Contract.ContractNames.COLUMN_NUMBER
                + " = ((SELECT " + Contract.ContractNames.COLUMN_NUMBER + " FROM " + Contract.ContractNames.TABLE_NAME +
                " WHERE " + Contract.ContractNames._ID + " = " + getLastIdfromUndoTable1Set() + ") + 1 ) WHERE " + Contract.ContractNames._ID + " = " + getLastIdfromUndoTable1Set();
        db.execSQL(insertQuery);
    }

    public void updateColumnNumberDown1Set() {
        SQLiteDatabase db = mDbHelper1Set.getWritableDatabase();
        String insertQuery = " UPDATE " + Contract.ContractNames.TABLE_NAME + " SET " + Contract.ContractNames.COLUMN_NUMBER
                + " = ((SELECT " + Contract.ContractNames.COLUMN_NUMBER + " FROM " + Contract.ContractNames.TABLE_NAME +
                " WHERE " + Contract.ContractNames._ID + " = " + getLastIdfromUndoTable1Set() + ") - 1 ) WHERE " + Contract.ContractNames._ID + " = " + getLastIdfromUndoTable1Set();
        db.execSQL(insertQuery);
    }

    public void saveResultGoToMM() {

        saveResult();
        mDbHelper1Set.clearTable(Contract.UndoNumbers.TABLE_NAME_1);
        mDbHelper1Set.clearTable(Contract.ContractNames.TABLE_NAME);
        finish();
        Intent intent1 = new Intent(this, AA_MainActivity.class);
        startActivity(intent1);
    }

    public void saveResult() {

        if (getIntent().getExtras().getBoolean("isNew")) {

            mDbHelper1Set.putName13("fullName", "matchMode", "courtSurface", pl1_1_1set, pl1_2_1set, pl2_1_1set, pl2_2_1set, Set1StatusMatch,
                    mDbHelper1Set.getStringFromNumDataFromUndoTable("ktbfps", undoCounter1Set), mDbHelper1Set.getStringFromNumDataFromUndoTable("ktbsps", undoCounter1Set),
                    mDbHelper1Set.getStringFromNumDataFromUndoTable("set1a", undoCounter1Set), tbfp, mDbHelper1Set.getStringFromNumDataFromUndoTable("set1b", undoCounter1Set), tbsp,
                    "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, 7, 3, 6, 2, 5, 1, 4, 0, getDFB_1Set(tb1pl), getDFB_1Set(tb2pl), getDFB_1Set(tieBreakDone),
                    getDFB_1Set(stopMatchIsEnabled1Set), getDFB_1Set(tb), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        } else {

            mDbHelper1Set.putName122("fullName", "matchMode", "courtSurface", pl1_1_1set, pl1_2_1set, pl2_1_1set, pl2_2_1set, Set1StatusMatch,
                    mDbHelper1Set.getStringFromNumDataFromUndoTable("ktbfps", undoCounter1Set), mDbHelper1Set.getStringFromNumDataFromUndoTable("ktbsps", undoCounter1Set),
                    mDbHelper1Set.getStringFromNumDataFromUndoTable("set1a", undoCounter1Set), tbfp, mDbHelper1Set.getStringFromNumDataFromUndoTable("set1b", undoCounter1Set), tbsp,
                    "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, 7, 3, 6, 2, 5, 1, 4, 0, getDFB_1Set(tb1pl), getDFB_1Set(tb2pl), getDFB_1Set(tieBreakDone),
                    getDFB_1Set(stopMatchIsEnabled1Set), getDFB_1Set(tb), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }
    }

    public void goToHelp() {
        Intent helpIntent = new Intent(this, DataBase.class);
        startActivity(helpIntent);
    }

    public void noSaveResultGoToMM() {
        mDbHelper1Set.clearTable(Contract.UndoNumbers.TABLE_NAME_1);
        mDbHelper1Set.clearTable(Contract.ContractNames.TABLE_NAME);
        finish();
        Intent intent1 = new Intent(this, AA_MainActivity.class);
        startActivity(intent1);
    }

    public void saveAndGo1Set(View view) {
            saveResultGoToMM();
    }

    public void saveResultFromStat(String st) {

        Set1StatusMatch = st;

        if (getIntent().getExtras().getBoolean("isNew")) {

            mDbHelper1Set.putName13("fullName", "matchMode", "courtSurface", pl1_1_1set, pl1_2_1set, pl2_1_1set, pl2_2_1set, Set1StatusMatch,
                    mDbHelper1Set.getStringFromNumDataFromUndoTable("ktbfps", undoCounter1Set), mDbHelper1Set.getStringFromNumDataFromUndoTable("ktbsps", undoCounter1Set),
                    mDbHelper1Set.getStringFromNumDataFromUndoTable("set1a", undoCounter1Set), tbfp, mDbHelper1Set.getStringFromNumDataFromUndoTable("set1b", undoCounter1Set), tbsp,
                    "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, 7, 3, 6, 2, 5, 1, 4, 0, getDFB_1Set(tb1pl), getDFB_1Set(tb2pl), getDFB_1Set(tieBreakDone),
                    getDFB_1Set(stopMatchIsEnabled1Set), getDFB_1Set(tb), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        } else {

            mDbHelper1Set.putName122("fullName", "matchMode", "courtSurface", pl1_1_1set, pl1_2_1set, pl2_1_1set, pl2_2_1set, Set1StatusMatch,
                    mDbHelper1Set.getStringFromNumDataFromUndoTable("ktbfps", undoCounter1Set), mDbHelper1Set.getStringFromNumDataFromUndoTable("ktbsps", undoCounter1Set),
                    mDbHelper1Set.getStringFromNumDataFromUndoTable("set1a", undoCounter1Set), tbfp, mDbHelper1Set.getStringFromNumDataFromUndoTable("set1b", undoCounter1Set), tbsp,
                    "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, "0", -2, 7, 3, 6, 2, 5, 1, 4, 0, getDFB_1Set(tb1pl), getDFB_1Set(tb2pl), getDFB_1Set(tieBreakDone),
                    getDFB_1Set(stopMatchIsEnabled1Set), getDFB_1Set(tb), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        mDbHelper1Set.clearTable(Contract.UndoNumbers.TABLE_NAME_1);
        mDbHelper1Set.clearTable(Contract.ContractNames.TABLE_NAME);
        finish();
        Intent intent1 = new Intent(this, AA_MainActivity.class);
        startActivity(intent1);
    }

    public int getDFB_1Set(Boolean b) {
        int u = 0;
        if (b) {
            u = 1;
        }
        return u;
    }
    @Override
    public void onDestroy() {
        mDbHelper1Set.clearTable(Contract.UndoNumbers.TABLE_NAME_1);
        mDbHelper1Set.clearTable(Contract.ContractNames.TABLE_NAME);
        super.onDestroy();
    }
}






