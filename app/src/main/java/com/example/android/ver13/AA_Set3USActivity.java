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
import static com.example.android.ver13.AA_Set3Activity.setWinnerFrame;
import static com.example.android.ver13.SearchedMatchsActivity.selId;
import static com.example.android.ver13.AA_Set1Activity.degreeDigits;
import static com.example.android.ver13.AA_Set1Activity.tp;

public class AA_Set3USActivity extends AppCompatActivity {

    TextView playerFirst3SetUS, playerSecond3SetUS, pointFP3SetUS, pointSP3SetUS, pointFPFirstSet3SetUS, pointSPFirstSet3SetUS,
            pointFPSecondSet3SetUS, pointSPSecondSet3SetUS, pointFPThirdSet3SetUS, pointSPThirdSet3SetUS;
    Spinner spinner1p3sUS, spinner2p3sUS;
    Button goalFP3SetUS, goalSP3SetUS;
    ImageButton undo_3SetUS, redo_3SetUS, saveButton3SetUS;
    private int i3su = 0;
    private int k3su = 0;
    private int fpls3US = 0;
    private int spls3US = 0;
    String sstr = "";
    String sstr1s = "";
    String adv_3SetUS = "Ad";
    String scoreP1_set3US, scoreP2_set3US, pl1_1_3setUS, pl1_2_3setUS, pl2_1_3setUS, pl2_2_3setUS, reasonsSelectedItem;
    private TennisHelper mDbHelper3SetUS;
    int curNum3SetUS, tbfp, tbsp, prevNum3SetUS, lastIdfromUT3SetUS, n3, A_pl_3SetUS_tb3, A_pl_3SetUS_tb2, A_pl_3SetUS_tb1, B_pl_3SetUS_tb3, B_pl_3SetUS_tb2, B_pl_3SetUS_tb1;
    int undoCounter3SetUS = 0;
    private boolean tb1pl_3SetUS = false;
    private boolean tb2pl_3SetUS = false;
    private boolean tb_3SetUS = false;
    private boolean SET_3_US_1_SET_a_DONE = false;
    private boolean SET_3_US_1_SET_b_DONE = false;
    private boolean SET_3_US_2_SET_a_DONE = false;
    private boolean SET_3_US_2_SET_b_DONE = false;
    private boolean SET_3_US_3_Set = false;
    private boolean SET_3_US_2_Set = false;
    private boolean SET_3_US_1_Set = true;
    private boolean SET_3_US_MATCH_DONE = false;
    private boolean tieBreakDone_3SetUS = false;
    private boolean SET_3_US_1_SET_tb_DONE = false;
    private boolean SET_3_US_2_SET_tb_DONE = false;
    private boolean SET_3_US_3_SET_tb_DONE = false;

    private boolean stopMatchIsEnabled3SetUS = true;

    private String Set3USStatusMatch = "Paused";

    AlertDialog.Builder ad3SetUS;
    Context context;

    int goalsCounter3SetUS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aa_activity_set3_us);

        goalsCounter3SetUS = 0;

        if (getSupportActionBar() != null) {

            getSupportActionBar().setTitle(getString(R.string.table_score));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        context = AA_Set3USActivity.this;
        mDbHelper3SetUS = new TennisHelper(this);

        TextView tableScore3SetKTB = (TextView) findViewById(R.id.addToTableScoreTV3SetUS);
        tableScore3SetKTB.setText(R.string.three_sets_US);

        playerFirst3SetUS = (TextView) findViewById(R.id.playerFirst3SetUSTV);
        playerSecond3SetUS = (TextView) findViewById(R.id.playerSecond3SetUSTV);

        TextView nameFirstPlayer = (TextView) findViewById(R.id.nameFirstPlayerTV);
        TextView nameSecondPlayer = (TextView) findViewById(R.id.nameSecondPlayerTV);

        pointFP3SetUS = (TextView) findViewById(R.id.pointFP3SetUSTV);
        pointSP3SetUS = (TextView) findViewById(R.id.pointSP3SetUSTV);

        pointFPFirstSet3SetUS = (TextView) findViewById(R.id.pointFPFirstSet3SetUSTV);
        pointSPFirstSet3SetUS = (TextView) findViewById(R.id.pointSPFirstSet3SetUSTV);

        pointFPSecondSet3SetUS = (TextView) findViewById(R.id.pointFPSecondSet3SetUSTV);
        pointSPSecondSet3SetUS = (TextView) findViewById(R.id.pointSPSecondSet3SetUSTV);

        pointFPThirdSet3SetUS = (TextView) findViewById(R.id.pointFPThirdSet3SetUSTV);
        pointSPThirdSet3SetUS = (TextView) findViewById(R.id.pointSPThirdSet3SetUSTV);

        if (getIntent().getExtras().getBoolean("isNew")) {

            pl1_1_3setUS = getIntent().getExtras().getString("user");
            pl1_2_3setUS = getIntent().getExtras().getString("userSur");
            pl2_1_3setUS = getIntent().getExtras().getString("der");
            pl2_2_3setUS = getIntent().getExtras().getString("derSur");

            pointFP3SetUS.setText(String.valueOf(tp[i3su]));
            pointSP3SetUS.setText(String.valueOf(tp[k3su]));

            pointFPFirstSet3SetUS.setText(String.valueOf(fpls3US));
            pointSPFirstSet3SetUS.setText(String.valueOf(spls3US));

            pointFPSecondSet3SetUS.setText(String.valueOf("0"));
            pointSPSecondSet3SetUS.setText(String.valueOf("0"));

            pointFPThirdSet3SetUS.setText(String.valueOf("0"));
            pointSPThirdSet3SetUS.setText(String.valueOf("0"));

        } else {

            pl1_1_3setUS = mDbHelper3SetUS.getNameById("name_A_1", selId);
            pl1_2_3setUS = mDbHelper3SetUS.getNameById("name_A_2", selId);
            pl2_1_3setUS = mDbHelper3SetUS.getNameById("name_B_1", selId);
            pl2_2_3setUS = mDbHelper3SetUS.getNameById("name_B_2", selId);

            if (mDbHelper3SetUS.getNumById("ktbfpsST", selId) == -2) {
                pointFP3SetUS.setText(adv_3SetUS);
            } else {
                pointFP3SetUS.setText(String.valueOf(mDbHelper3SetUS.getNumById("ktbfpsST", selId)));
            }

            if (mDbHelper3SetUS.getNumById("ktbspsST", selId) == -2) {
                pointSP3SetUS.setText(adv_3SetUS);
            } else {
                pointSP3SetUS.setText(String.valueOf(mDbHelper3SetUS.getNumById("ktbspsST", selId)));
            }

            if (mDbHelper3SetUS.getNumById("set1aST_tb", selId) > 0 || mDbHelper3SetUS.getNumById("set1bST_tb", selId) > 0) {

                pointFPFirstSet3SetUS.setText(String.valueOf(mDbHelper3SetUS.getNumById("set1aST", selId)));
                pointSPFirstSet3SetUS.setText(String.valueOf(mDbHelper3SetUS.getNumById("set1bST", selId)));

                convertUndo(mDbHelper3SetUS.getNumById("set1aST_tb", selId), mDbHelper3SetUS.getNumById("set1bST_tb", selId), pointFPFirstSet3SetUS, pointSPFirstSet3SetUS);
            } else {

                pointFPFirstSet3SetUS.setText(String.valueOf(mDbHelper3SetUS.getNumById("set1aST", selId)));
                pointSPFirstSet3SetUS.setText(String.valueOf(mDbHelper3SetUS.getNumById("set1bST", selId)));

            }

            if (mDbHelper3SetUS.getNumById("set2aST_tb", selId) > 0 || mDbHelper3SetUS.getNumById("set2bST_tb", selId) > 0) {
                pointFPSecondSet3SetUS.setText(String.valueOf(mDbHelper3SetUS.getNumById("set2aST", selId)));
                pointSPSecondSet3SetUS.setText(String.valueOf(mDbHelper3SetUS.getNumById("set2bST", selId)));
                convertUndo(mDbHelper3SetUS.getNumById("set2aST_tb", selId), mDbHelper3SetUS.getNumById("set2bST_tb", selId), pointFPSecondSet3SetUS, pointSPSecondSet3SetUS);

            } else {
                pointFPSecondSet3SetUS.setText(String.valueOf(mDbHelper3SetUS.getNumById("set2aST", selId)));
                pointSPSecondSet3SetUS.setText(String.valueOf(mDbHelper3SetUS.getNumById("set2bST", selId)));

            }

            pointFPThirdSet3SetUS.setText(String.valueOf(mDbHelper3SetUS.getNumById("set3aST", selId)));
            pointSPThirdSet3SetUS.setText(String.valueOf(mDbHelper3SetUS.getNumById("set3bST", selId)));

            tb1pl_3SetUS = mDbHelper3SetUS.getNumById("tb1pl", selId) > 0;//

            tb2pl_3SetUS = mDbHelper3SetUS.getNumById("tb2pl", selId) > 0;//

            tb_3SetUS = mDbHelper3SetUS.getNumById("tb", selId) > 0;//

            SET_3_US_1_SET_a_DONE = mDbHelper3SetUS.getNumById("_1_set_a_done", selId) > 0;//
            setWinnerFrame(SET_3_US_1_SET_a_DONE, pointFPFirstSet3SetUS);

            SET_3_US_1_SET_b_DONE = mDbHelper3SetUS.getNumById("_1_set_b_done", selId) > 0;//
            setWinnerFrame(SET_3_US_1_SET_b_DONE, pointSPFirstSet3SetUS);

            SET_3_US_2_SET_a_DONE = mDbHelper3SetUS.getNumById("_2_set_a_done", selId) > 0;//
            setWinnerFrame(SET_3_US_2_SET_a_DONE, pointFPSecondSet3SetUS);

            SET_3_US_2_SET_b_DONE = mDbHelper3SetUS.getNumById("_2_set_b_done", selId) > 0;//
            setWinnerFrame(SET_3_US_2_SET_b_DONE, pointSPSecondSet3SetUS);

            SET_3_US_3_Set = mDbHelper3SetUS.getNumById("_3_set", selId) > 0;//

            SET_3_US_2_Set = mDbHelper3SetUS.getNumById("_2_set", selId) > 0;//

            SET_3_US_1_Set = mDbHelper3SetUS.getNumById("_1_set", selId) > 0;//

            SET_3_US_MATCH_DONE = mDbHelper3SetUS.getNumById("match_done", selId) > 0;//

            tieBreakDone_3SetUS = mDbHelper3SetUS.getNumById("tieBreakDone", selId) > 0;//

            SET_3_US_1_SET_tb_DONE = mDbHelper3SetUS.getNumById("_1_set_tb_done", selId) > 0;//

            SET_3_US_2_SET_tb_DONE = mDbHelper3SetUS.getNumById("_2_set_tb_done", selId) > 0;//

            SET_3_US_3_SET_tb_DONE = mDbHelper3SetUS.getNumById("_3_set_tb_done", selId) > 0;//

            stopMatchIsEnabled3SetUS = mDbHelper3SetUS.getNumById("stopMatchIsEnabled", selId) > 0;//

            A_pl_3SetUS_tb1 = mDbHelper3SetUS.getNumById("set1aST_tb", selId);
            B_pl_3SetUS_tb1 = mDbHelper3SetUS.getNumById("set1bST_tb", selId);
            A_pl_3SetUS_tb2 = mDbHelper3SetUS.getNumById("set2aST_tb", selId);
            B_pl_3SetUS_tb2 = mDbHelper3SetUS.getNumById("set2bST_tb", selId);
            A_pl_3SetUS_tb3 = mDbHelper3SetUS.getNumById("set3aST_tb", selId);
            B_pl_3SetUS_tb3 = mDbHelper3SetUS.getNumById("set3bST_tb", selId);

            mDbHelper3SetUS.insert3SetKTBScore(
                    mDbHelper3SetUS.getNumById("ktbfpsST", selId), mDbHelper3SetUS.getNumById("ktbspsST", selId),
                    mDbHelper3SetUS.getNumById("set1aST", selId), mDbHelper3SetUS.getNumById("set1bST", selId),
                    mDbHelper3SetUS.getNumById("set2aST", selId), mDbHelper3SetUS.getNumById("set2bST", selId),
                    mDbHelper3SetUS.getNumById("set3aST", selId), mDbHelper3SetUS.getNumById("set3bST", selId));

            mDbHelper3SetUS.insertPlayers(pl1_1_3setUS, pl1_2_3setUS, pl2_1_3setUS, pl2_2_3setUS);


        }


        setNames(playerFirst3SetUS, pl1_1_3setUS, pl1_2_3setUS);
        setNames(playerSecond3SetUS, pl2_1_3setUS, pl2_2_3setUS);
        AA_KingTieBreakActivity.setAddNames(nameFirstPlayer, pl1_1_3setUS, pl1_2_3setUS);
        AA_KingTieBreakActivity.setAddNames(nameSecondPlayer, pl2_1_3setUS, pl2_2_3setUS);

        spinner1p3sUS = (Spinner) findViewById(R.id.spinner1p3sUSTV);
        spinner2p3sUS = (Spinner) findViewById(R.id.spinner2p3sUSTV);

        typeGoals[0] = getResources().getString(R.string.select_gt_for_spinner);
        typeGoals[1] = getResources().getString(R.string.Winner);
        typeGoals[2] = getResources().getString(R.string.Ace);
        typeGoals[3] = getResources().getString(R.string.Unenforced_error_for_spinner);
        typeGoals[4] = getResources().getString(R.string.Double_error_for_spinner);

        AA_KingTieBreakActivity.setadapter(typeGoals, spinner1p3sUS, this);
        AA_KingTieBreakActivity.setadapter(typeGoals, spinner2p3sUS, this);

        goalFP3SetUS = (Button) findViewById(R.id.goalFP3SetUSButton);
        goalSP3SetUS = (Button) findViewById(R.id.goalSP3SetUSButton);

        undo_3SetUS = (ImageButton) findViewById(R.id.undo3SetUSButton);
        redo_3SetUS = (ImageButton) findViewById(R.id.redo3SetUSButton);
        saveButton3SetUS = (ImageButton) findViewById(R.id._saveButton3SetUS);


        noEnabled(undo_3SetUS, getDrawable(R.drawable.ic_undo_black_24dp_0_3));
        noEnabled(redo_3SetUS, getDrawable(R.drawable.ic_redo_black_24dp_0_3));
        noEnabled(saveButton3SetUS, getDrawable(R.drawable.ic_save_black_24dp_0_3));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.score_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if (!stopMatchIsEnabled3SetUS) {
            menu.getItem(1).setEnabled(false);
        } else {
            menu.getItem(1).setEnabled(true);
        }
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
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

                final String na_1 = getString(R.string.Retired) + " " + playerFirst3SetUS.getText().toString() + getString(R.string.loss);
                final String na_2 = getString(R.string.Retired) + " " + playerSecond3SetUS.getText().toString() + getString(R.string.loss);
                final String na_3 = getString(R.string.Another_Match_is_paused);
                final String na_4 = getString(R.string.Another_Match_is_finished);
                final String[] reasons = {na_1, na_2, na_3, na_4};
                ad3SetUS = new AlertDialog.Builder(this);
                ad3SetUS.setTitle(getString(R.string.Choose_the_reason))
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


                                        ad3SetUS = new AlertDialog.Builder(context);

                                        ad3SetUS.setMessage(getString(R.string.Do_you_want));

                                        ad3SetUS.setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener() {
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
                                        ad3SetUS.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int arg1) {
                                                noSaveResultGoToMM();
                                                if (!getIntent().getExtras().getBoolean("isNew")) {
                                                    mDbHelper3SetUS.putFreeRowInStatisticSaveTable(selId);
                                                }
                                            }
                                        });
                                        ad3SetUS.setCancelable(true);
                                        ad3SetUS.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                            public void onCancel(DialogInterface dialog) {
                                            }
                                        });
                                        ad3SetUS.show();
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
                ad3SetUS.show();
                break;

            case R.id.help_item:

                goToHelp();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {



            ad3SetUS = new AlertDialog.Builder(context);
            ad3SetUS.setMessage(getString(R.string.ad_alert_1));
            ad3SetUS.setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int arg1) {

                    saveResultGoToMM();
                }
            });
            ad3SetUS.setNeutralButton(getString(R.string.Continue_match),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int id) {
                            dialog.cancel();
                        }
                    });

            ad3SetUS.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int arg1) {
                    noSaveResultGoToMM();
                    if (!getIntent().getExtras().getBoolean("isNew")) {
                        mDbHelper3SetUS.putFreeRowInStatisticSaveTable(selId);
                    }
                }
            });
            ad3SetUS.setCancelable(true);
            ad3SetUS.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialog) {
                }
            });
            ad3SetUS.show();
    }

    public void firstPlayerGoal3SetUS(View v) {

        switch (AA_KingTieBreakActivity.getSpinnerItem(spinner1p3sUS)) {
            case 0: {
                Toast.makeText(getBaseContext(), getString(R.string.select_gt), Toast.LENGTH_SHORT).show();
                break;
            }
            case 1: {
                deleteRow3SetUS();
                noEnabled(redo_3SetUS, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper3SetUS.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(7) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper3SetUS.getLastId() - 7)});

                setFirstPlScore3SetUS();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper3SetUS.getLastId() - 7);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper3SetUS.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet3USAllplScore(pointFP3SetUS, pointSP3SetUS, pointFPFirstSet3SetUS, pointSPFirstSet3SetUS, pointFPSecondSet3SetUS, pointSPSecondSet3SetUS, pointFPThirdSet3SetUS, pointSPThirdSet3SetUS);
                goalsCounter3SetUS++;
                break;
            }

            case 2: {
                deleteRow3SetUS();
                noEnabled(redo_3SetUS, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper3SetUS.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(6) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper3SetUS.getLastId() - 6)});

                setFirstPlScore3SetUS();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper3SetUS.getLastId() - 6);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper3SetUS.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet3USAllplScore(pointFP3SetUS, pointSP3SetUS, pointFPFirstSet3SetUS, pointSPFirstSet3SetUS, pointFPSecondSet3SetUS, pointSPSecondSet3SetUS, pointFPThirdSet3SetUS, pointSPThirdSet3SetUS);
                goalsCounter3SetUS++;
                break;
            }

            case 3: {
                deleteRow3SetUS();
                noEnabled(redo_3SetUS, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper3SetUS.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(1) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper3SetUS.getLastId() - 1)});

                setFirstPlScore3SetUS();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper3SetUS.getLastId() - 1);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper3SetUS.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet3USAllplScore(pointFP3SetUS, pointSP3SetUS, pointFPFirstSet3SetUS, pointSPFirstSet3SetUS, pointFPSecondSet3SetUS, pointSPSecondSet3SetUS, pointFPThirdSet3SetUS, pointSPThirdSet3SetUS);
                goalsCounter3SetUS++;
                break;
            }

            case 4: {
                deleteRow3SetUS();
                noEnabled(redo_3SetUS, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper3SetUS.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(0) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper3SetUS.getLastId())});

                setFirstPlScore3SetUS();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper3SetUS.getLastId());
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper3SetUS.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet3USAllplScore(pointFP3SetUS, pointSP3SetUS, pointFPFirstSet3SetUS, pointSPFirstSet3SetUS, pointFPSecondSet3SetUS, pointSPSecondSet3SetUS, pointFPThirdSet3SetUS, pointSPThirdSet3SetUS);
                goalsCounter3SetUS++;
                break;
            }
        }
        spinner1p3sUS.setSelection(0);
        spinner2p3sUS.setSelection(0);
    }

    public void secondPlayerGoal3SetUS(View v) {


        switch (AA_KingTieBreakActivity.getSpinnerItem(spinner2p3sUS)) {
            case 0: {
                Toast.makeText(getBaseContext(), getString(R.string.select_gt), Toast.LENGTH_SHORT).show();
                break;
            }
            case 1: {
                deleteRow3SetUS();
                noEnabled(redo_3SetUS, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper3SetUS.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(3) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper3SetUS.getLastId() - 3)});

                setSecondPlScore3SetUS();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper3SetUS.getLastId() - 3);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper3SetUS.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet3USAllplScore(pointFP3SetUS, pointSP3SetUS, pointFPFirstSet3SetUS, pointSPFirstSet3SetUS, pointFPSecondSet3SetUS, pointSPSecondSet3SetUS, pointFPThirdSet3SetUS, pointSPThirdSet3SetUS);
                goalsCounter3SetUS++;
                break;
            }

            case 2: {
                deleteRow3SetUS();
                noEnabled(redo_3SetUS, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper3SetUS.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(2) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper3SetUS.getLastId() - 2)});

                setSecondPlScore3SetUS();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper3SetUS.getLastId() - 2);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper3SetUS.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet3USAllplScore(pointFP3SetUS, pointSP3SetUS, pointFPFirstSet3SetUS, pointSPFirstSet3SetUS, pointFPSecondSet3SetUS, pointSPSecondSet3SetUS, pointFPThirdSet3SetUS, pointSPThirdSet3SetUS);
                goalsCounter3SetUS++;
                break;
            }

            case 3: {
                deleteRow3SetUS();
                noEnabled(redo_3SetUS, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper3SetUS.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(5) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper3SetUS.getLastId() - 5)});

                setSecondPlScore3SetUS();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper3SetUS.getLastId() - 5);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper3SetUS.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet3USAllplScore(pointFP3SetUS, pointSP3SetUS, pointFPFirstSet3SetUS, pointSPFirstSet3SetUS, pointFPSecondSet3SetUS, pointSPSecondSet3SetUS, pointFPThirdSet3SetUS, pointSPThirdSet3SetUS);
                goalsCounter3SetUS++;
                break;
            }

            case 4: {
                deleteRow3SetUS();
                noEnabled(redo_3SetUS, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper3SetUS.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(4) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper3SetUS.getLastId() - 4)});

                setSecondPlScore3SetUS();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper3SetUS.getLastId() - 4);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper3SetUS.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet3USAllplScore(pointFP3SetUS, pointSP3SetUS, pointFPFirstSet3SetUS, pointSPFirstSet3SetUS, pointFPSecondSet3SetUS, pointSPSecondSet3SetUS, pointFPThirdSet3SetUS, pointSPThirdSet3SetUS);
                goalsCounter3SetUS++;
                break;
            }
        }

        spinner1p3sUS.setSelection(0);
        spinner2p3sUS.setSelection(0);
    }


    public void setFirstPlScore3SetUS() {

        tieBreakDone_3SetUS = false;

        tb1pl_3SetUS = true;

        if (tb_3SetUS) {

            tieBreak(tb1pl_3SetUS, tb2pl_3SetUS);
        } else {

            i3su = getKorI(pointFP3SetUS);
            k3su = getKorI(pointSP3SetUS);

            if (SET_3_US_3_Set) {

                fpls3US = Integer.valueOf(pointFPThirdSet3SetUS.getText().toString());
                spls3US = Integer.valueOf(pointSPThirdSet3SetUS.getText().toString());

            } else if (SET_3_US_2_Set) {

                fpls3US = Integer.valueOf(pointFPSecondSet3SetUS.getText().toString());
                spls3US = Integer.valueOf(pointSPSecondSet3SetUS.getText().toString());

            } else {

                fpls3US = Integer.valueOf(pointFPFirstSet3SetUS.getText().toString());
                spls3US = Integer.valueOf(pointSPFirstSet3SetUS.getText().toString());
            }

            scoreP1_set3US = pointFP3SetUS.getText().toString();

            switch (scoreP1_set3US) {
                case "0":
                    i3su = 1;
                    break;
                case "15":
                    i3su = 2;
                    break;
                case "30":
                    i3su = 3;
                    break;
                case "40":
                    if (i3su > k3su) {
                        i3su = k3su = 0;
                        fpls3US++;
                    } else if (i3su == k3su) {
                        i3su = 4;
                        break;
                    } else {
                        i3su = k3su = 3;
                    }
                    break;
                case "Ad":
                    i3su = k3su = 0;
                    fpls3US++;
                    break;
            }
            if (i3su == 4) {

                pointFP3SetUS.setText(adv_3SetUS);
                pointSP3SetUS.setText(String.valueOf(tp[k3su]));
                tb1pl_3SetUS = false;//???????

            } else {

                pointFP3SetUS.setText(String.valueOf(tp[i3su]));
                pointSP3SetUS.setText(String.valueOf(tp[k3su]));

                if (SET_3_US_3_Set) {

                    pointFPThirdSet3SetUS.setText(String.valueOf(fpls3US));

                } else if (SET_3_US_2_Set) {

                    pointFPSecondSet3SetUS.setText(String.valueOf(fpls3US));

                } else {

                    pointFPFirstSet3SetUS.setText(String.valueOf(fpls3US));
                }
                tb1pl_3SetUS = false;
            }
            checkSetNumber(fpls3US, spls3US);
        }
    }

    public void setSecondPlScore3SetUS() {

        tieBreakDone_3SetUS = false;

        tb2pl_3SetUS = true;

        if (tb_3SetUS) {

            tieBreak(tb1pl_3SetUS, tb2pl_3SetUS);

        } else {

            i3su = getKorI(pointFP3SetUS);
            k3su = getKorI(pointSP3SetUS);

            if (SET_3_US_3_Set) {

                fpls3US = Integer.valueOf(pointFPThirdSet3SetUS.getText().toString());
                spls3US = Integer.valueOf(pointSPThirdSet3SetUS.getText().toString());

            } else if (SET_3_US_2_Set) {

                fpls3US = Integer.valueOf(pointFPSecondSet3SetUS.getText().toString());
                spls3US = Integer.valueOf(pointSPSecondSet3SetUS.getText().toString());

            } else {

                fpls3US = Integer.valueOf(pointFPFirstSet3SetUS.getText().toString());
                spls3US = Integer.valueOf(pointSPFirstSet3SetUS.getText().toString());
            }

            scoreP2_set3US = pointSP3SetUS.getText().toString();

            switch (scoreP2_set3US) {
                case "0":
                    k3su = 1;
                    break;
                case "15":
                    k3su = 2;
                    break;
                case "30":
                    k3su = 3;
                    break;
                case "40":
                    if (k3su > i3su) {
                        k3su = i3su = 0;
                        spls3US++;
                    } else if (k3su == i3su) {
                        k3su = 4;
                    } else {
                        i3su = k3su = 3;
                    }
                    break;
                case "Ad":
                    i3su = k3su = 0;
                    spls3US++;
                    break;
            }
            if (k3su == 4) {

                pointSP3SetUS.setText(adv_3SetUS);
                pointFP3SetUS.setText(String.valueOf(tp[i3su]));
                tb2pl_3SetUS = false;

            } else {

                pointSP3SetUS.setText(String.valueOf(tp[k3su]));
                pointFP3SetUS.setText(String.valueOf(tp[i3su]));

                if (SET_3_US_3_Set) {

                    pointSPThirdSet3SetUS.setText(String.valueOf(spls3US));

                } else if (SET_3_US_2_Set) {

                    pointSPSecondSet3SetUS.setText(String.valueOf(spls3US));

                } else {

                    pointSPFirstSet3SetUS.setText(String.valueOf(spls3US));
                }
                tb2pl_3SetUS = false;
            }
            checkSetNumber(fpls3US, spls3US);
        }
    }


    public void intCurSet3USAllplScore(TextView p1s, TextView p2s, TextView p1s1s, TextView p2s1s, TextView p1s2s, TextView p2s2s, TextView p1s3s, TextView p2s3s) {

        SQLiteDatabase db = mDbHelper3SetUS.getWritableDatabase();
        ContentValues values = new ContentValues();

        String j1 = p1s.getText().toString();
        String j2 = p2s.getText().toString();

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


        if (SET_3_US_1_SET_tb_DONE) {

            if (A_pl_3SetUS_tb1 > B_pl_3SetUS_tb1) {

                values.put(Contract.UndoNumbers.COLUMN_SET1A, 7);
                values.put(Contract.UndoNumbers.COLUMN_SET1B, 6);
            } else {
                values.put(Contract.UndoNumbers.COLUMN_SET1A, 6);
                values.put(Contract.UndoNumbers.COLUMN_SET1B, 7);
            }
        } else {
            String j3 = p1s1s.getText().toString();
            String j4 = p2s1s.getText().toString();
            values.put(Contract.UndoNumbers.COLUMN_SET1A, Integer.valueOf(j3));
            values.put(Contract.UndoNumbers.COLUMN_SET1B, Integer.valueOf(j4));
        }

        if (SET_3_US_2_SET_tb_DONE) {

            if (A_pl_3SetUS_tb2 > B_pl_3SetUS_tb2) {
                values.put(Contract.UndoNumbers.COLUMN_SET2A, 7);
                values.put(Contract.UndoNumbers.COLUMN_SET2B, 6);
            } else {
                values.put(Contract.UndoNumbers.COLUMN_SET2A, 6);
                values.put(Contract.UndoNumbers.COLUMN_SET2B, 7);
            }
        } else {
            String j5 = p1s2s.getText().toString();
            String j6 = p2s2s.getText().toString();

            values.put(Contract.UndoNumbers.COLUMN_SET2A, Integer.valueOf(j5));
            values.put(Contract.UndoNumbers.COLUMN_SET2B, Integer.valueOf(j6));
        }

        if (SET_3_US_3_SET_tb_DONE) {
            if (A_pl_3SetUS_tb3 > B_pl_3SetUS_tb3) {
                values.put(Contract.UndoNumbers.COLUMN_SET3A, 7);
                values.put(Contract.UndoNumbers.COLUMN_SET3B, 6);
            } else {
                values.put(Contract.UndoNumbers.COLUMN_SET3A, 6);
                values.put(Contract.UndoNumbers.COLUMN_SET3B, 7);
            }
        } else {
            String j7 = p1s3s.getText().toString();
            String j8 = p2s3s.getText().toString();
            values.put(Contract.UndoNumbers.COLUMN_SET3A, Integer.valueOf(j7));
            values.put(Contract.UndoNumbers.COLUMN_SET3B, Integer.valueOf(j8));
        }

        db.update(Contract.UndoNumbers.TABLE_NAME_1, values, Contract.UndoNumbers.COLUMN_NUM_NUM + " = ?", new String[]{Integer.toString(mDbHelper3SetUS.getMaxNumNum())});
    }

    public void checkSetNumber(int y1, int y2) {

        if ((SET_3_US_1_SET_a_DONE & SET_3_US_2_SET_b_DONE) | (SET_3_US_1_SET_b_DONE & SET_3_US_2_SET_a_DONE)) {

            if (y1 == 6 & y2 <= y1 - 2 | y1 == 7 & y2 <= y1 - 2) {

                SET_3_US_MATCH_DONE = true;

                Toast.makeText(this, pl1_1_3setUS + " " + pl1_2_3setUS + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                AA_KingTieBreakActivity.setButNotEnabled(goalFP3SetUS, goalSP3SetUS, spinner1p3sUS, spinner2p3sUS);
                setWinnerSet_3US(playerFirst3SetUS, pointFPThirdSet3SetUS, "firstPlayer");

            } else if (y2 == 6 & y1 <= y2 - 2 | y2 == 7 & y1 <= y2 - 2) {

                SET_3_US_MATCH_DONE = true;

                Toast.makeText(this, pl2_1_3setUS + " " + pl2_2_3setUS + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                AA_KingTieBreakActivity.setButNotEnabled(goalFP3SetUS, goalSP3SetUS, spinner1p3sUS, spinner2p3sUS);
                setWinnerSet_3US(playerSecond3SetUS, pointSPThirdSet3SetUS, "secondPlayer");

            } else if (y2 == 6 & y1 == 6) {

                setTbmode_3US();

            }

        } else if (SET_3_US_1_SET_b_DONE) {

            if (y1 == 6 & y2 <= y1 - 2 | y1 == 7 & y2 <= y1 - 2) {

                SET_3_US_2_SET_a_DONE = true;
                SET_3_US_2_Set = false;
                SET_3_US_3_Set = true;

                Toast.makeText(this, pl1_1_3setUS + " " + pl1_2_3setUS + " " + getString(R.string.wins_2_set), Toast.LENGTH_LONG).show();
                pointFPSecondSet3SetUS.setBackgroundResource(R.drawable.frame_winner);

            } else if (y2 == 6 & y1 <= y2 - 2 | y2 == 7 & y1 <= y2 - 2) {

                SET_3_US_MATCH_DONE = true;
                SET_3_US_2_SET_b_DONE = true;

                Toast.makeText(this, pl2_1_3setUS + " " + pl2_2_3setUS + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                AA_KingTieBreakActivity.setButNotEnabled(goalFP3SetUS, goalSP3SetUS, spinner1p3sUS, spinner2p3sUS);
                setWinnerSet_3US(playerSecond3SetUS, pointSPSecondSet3SetUS, "secondPlayer");

            } else if (y2 == 6 & y1 == 6) {

                setTbmode_3US();
            }
        } else if (SET_3_US_1_SET_a_DONE) {

            if (y1 == 6 & y2 <= y1 - 2 | y1 == 7 & y2 <= y1 - 2) {

                SET_3_US_MATCH_DONE = true;
                SET_3_US_2_SET_a_DONE = true;

                Toast.makeText(this, pl1_1_3setUS + " " + pl1_2_3setUS + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                AA_KingTieBreakActivity.setButNotEnabled(goalFP3SetUS, goalSP3SetUS, spinner1p3sUS, spinner2p3sUS);
                setWinnerSet_3US(playerFirst3SetUS, pointFPSecondSet3SetUS, "firstPlayer");

            } else if (y2 == 6 & y1 <= y2 - 2 | y2 == 7 & y1 <= y2 - 2) {

                SET_3_US_2_SET_b_DONE = true;
                SET_3_US_2_Set = false;
                SET_3_US_3_Set = true;

                Toast.makeText(this, pl2_1_3setUS + " " + pl2_2_3setUS + " " + getString(R.string.wins_2_set), Toast.LENGTH_LONG).show();
                pointSPSecondSet3SetUS.setBackgroundResource(R.drawable.frame_winner);

            } else if (y2 == 6 & y1 == 6) {

                setTbmode_3US();
            }
        } else {

            if (y1 == 6 & y2 <= y1 - 2 | y1 == 7 & y2 <= y1 - 2) {

                SET_3_US_1_SET_a_DONE = true;
                SET_3_US_2_Set = true;
                SET_3_US_1_Set = false;

                Toast.makeText(this, pl1_1_3setUS + " " + pl1_2_3setUS + " " + getString(R.string.wins_1_set), Toast.LENGTH_LONG).show();
                pointFPFirstSet3SetUS.setBackgroundResource(R.drawable.frame_winner);

            } else if (y2 == 6 & y1 <= y2 - 2 | y2 == 7 & y1 <= y2 - 2) {

                SET_3_US_1_SET_b_DONE = true;
                SET_3_US_2_Set = true;
                SET_3_US_1_Set = false;

                Toast.makeText(this, pl2_1_3setUS + " " + pl2_2_3setUS + " " + getString(R.string.wins_1_set), Toast.LENGTH_LONG).show();
                pointSPFirstSet3SetUS.setBackgroundResource(R.drawable.frame_winner);

            } else if (y2 == 6 & y1 == 6) {

                setTbmode_3US();
            }
        }
    }

    private void setTbmode_3US() {

        Toast.makeText(getBaseContext(), getString(R.string.Tie_break_starts), Toast.LENGTH_SHORT).show();

        tb1pl_3SetUS = false;
        tb2pl_3SetUS = false;

        tbfp = 0;
        tbsp = 0;

        pointFP3SetUS.setText(String.valueOf(tbfp));
        pointSP3SetUS.setText(String.valueOf(tbsp));

        tb_3SetUS = true;
    }

    public void tieBreak(boolean pla, boolean plb) {

        if (pla) {

            tbfp = Integer.valueOf(pointFP3SetUS.getText().toString());
            tbsp = Integer.valueOf(pointSP3SetUS.getText().toString());

            tbfp++;

            pointFP3SetUS.setText(String.valueOf(tbfp));
            pointSP3SetUS.setText(String.valueOf(tbsp));
            tb1pl_3SetUS = false;

        } else if (plb) {

            tbfp = Integer.valueOf(pointFP3SetUS.getText().toString());
            tbsp = Integer.valueOf(pointSP3SetUS.getText().toString());

            tbsp++;

            pointFP3SetUS.setText(String.valueOf(tbfp));
            pointSP3SetUS.setText(String.valueOf(tbsp));
            tb2pl_3SetUS = false;
        }
        checkTieBreakNumber(tbfp, tbsp);
    }

    public void checkTieBreakNumber(int x1, int x2) {

        if ((x1 == 7 & x2 <= x1 - 2) | (x1 > 7 & x2 <= x1 - 2)) {

            if ((SET_3_US_1_SET_a_DONE & SET_3_US_2_SET_b_DONE) | (SET_3_US_1_SET_b_DONE & SET_3_US_2_SET_a_DONE)) {

                pointFPThirdSet3SetUS.setText(String.valueOf(7));

                SET_3_US_MATCH_DONE = true;
                SET_3_US_3_SET_tb_DONE = true;
                tieBreakDone_3SetUS = true;
                A_pl_3SetUS_tb3 = x1;
                B_pl_3SetUS_tb3 = x2;
                convert(x1, x2, pointFPThirdSet3SetUS, pointSPThirdSet3SetUS);

                Toast.makeText(this, pl1_1_3setUS + " " + pl1_2_3setUS + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                AA_KingTieBreakActivity.setButNotEnabled(goalFP3SetUS, goalSP3SetUS, spinner1p3sUS, spinner2p3sUS);
                setWinnerSet_3US(playerFirst3SetUS, pointFPThirdSet3SetUS, "firstPlayer");

            } else if (SET_3_US_1_SET_b_DONE) {

                pointFPSecondSet3SetUS.setText(String.valueOf(7));

                SET_3_US_2_SET_tb_DONE = true;
                SET_3_US_2_SET_a_DONE = true;
                SET_3_US_3_Set = true;
                SET_3_US_2_Set = false;
                A_pl_3SetUS_tb2 = x1;
                B_pl_3SetUS_tb2 = x2;
                convert(x1, x2, pointFPSecondSet3SetUS, pointSPSecondSet3SetUS);

                Toast.makeText(this, pl1_1_3setUS + " " + pl1_2_3setUS + " " + getString(R.string.wins_2_set), Toast.LENGTH_LONG).show();
                pointFPSecondSet3SetUS.setBackgroundResource(R.drawable.frame_winner);

            } else if (SET_3_US_1_SET_a_DONE) {

                pointFPSecondSet3SetUS.setText(String.valueOf(7));

                SET_3_US_2_SET_tb_DONE = true;
                SET_3_US_2_SET_a_DONE = true;
                SET_3_US_MATCH_DONE = true;
                A_pl_3SetUS_tb2 = x1;
                B_pl_3SetUS_tb2 = x2;
                convert(x1, x2, pointFPSecondSet3SetUS, pointSPSecondSet3SetUS);

                Toast.makeText(this, pl1_1_3setUS + " " + pl1_2_3setUS + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                AA_KingTieBreakActivity.setButNotEnabled(goalFP3SetUS, goalSP3SetUS, spinner1p3sUS, spinner2p3sUS);
                setWinnerSet_3US(playerFirst3SetUS, pointFPSecondSet3SetUS, "firstPlayer");

            } else {

                pointFPFirstSet3SetUS.setText(String.valueOf(7));

                SET_3_US_1_SET_a_DONE = true;
                SET_3_US_1_SET_tb_DONE = true;
                SET_3_US_2_Set = true;
                SET_3_US_1_Set = false;
                A_pl_3SetUS_tb1 = x1;
                B_pl_3SetUS_tb1 = x2;

                convert(x1, x2, pointFPFirstSet3SetUS, pointSPFirstSet3SetUS);

                Toast.makeText(this, pl1_1_3setUS + " " + pl1_2_3setUS + " " + getString(R.string.wins_1_set), Toast.LENGTH_LONG).show();
                pointFPFirstSet3SetUS.setBackgroundResource(R.drawable.frame_winner);
            }

        } else if ((x2 == 7 & x1 <= x2 - 2) | (x2 > 7 & x1 <= x2 - 2))

        {

            if ((SET_3_US_1_SET_a_DONE & SET_3_US_2_SET_b_DONE) | (SET_3_US_1_SET_b_DONE & SET_3_US_2_SET_a_DONE)) {

                pointSPThirdSet3SetUS.setText(String.valueOf(7));

                SET_3_US_MATCH_DONE = true;
                SET_3_US_3_SET_tb_DONE = true;
                A_pl_3SetUS_tb3 = x1;
                B_pl_3SetUS_tb3 = x2;
                convert(x1, x2, pointFPThirdSet3SetUS, pointSPThirdSet3SetUS);


                Toast.makeText(this, pl2_1_3setUS + " " + pl2_2_3setUS + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                AA_KingTieBreakActivity.setButNotEnabled(goalFP3SetUS, goalSP3SetUS, spinner1p3sUS, spinner2p3sUS);
                setWinnerSet_3US(playerSecond3SetUS, pointSPThirdSet3SetUS, "secondPlayer");

            } else if (SET_3_US_1_SET_a_DONE) {

                SET_3_US_2_SET_b_DONE = true;
                SET_3_US_2_SET_tb_DONE = true;

                SET_3_US_2_Set = false;
                SET_3_US_3_Set = true;


                A_pl_3SetUS_tb2 = x1;
                B_pl_3SetUS_tb2 = x2;

                pointSPSecondSet3SetUS.setText(String.valueOf(7));

                convert(x1, x2, pointFPSecondSet3SetUS, pointSPSecondSet3SetUS);

                Toast.makeText(this, pl2_1_3setUS + " " + pl2_2_3setUS + " " + getString(R.string.wins_2_set), Toast.LENGTH_LONG).show();
                pointSPSecondSet3SetUS.setBackgroundResource(R.drawable.frame_winner);


            } else if (SET_3_US_1_SET_b_DONE) {

                SET_3_US_2_SET_b_DONE = true;
                SET_3_US_2_SET_tb_DONE = true;


                SET_3_US_MATCH_DONE = true;

                A_pl_3SetUS_tb2 = x1;
                B_pl_3SetUS_tb2 = x2;

                pointSPSecondSet3SetUS.setText(String.valueOf(7));

                convert(x1, x2, pointFPSecondSet3SetUS, pointSPSecondSet3SetUS);

                Toast.makeText(this, pl2_1_3setUS + " " + pl2_2_3setUS + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                AA_KingTieBreakActivity.setButNotEnabled(goalFP3SetUS, goalSP3SetUS, spinner1p3sUS, spinner2p3sUS);
                setWinnerSet_3US(playerSecond3SetUS, pointSPSecondSet3SetUS, "secondPlayer");

            } else {

                pointSPFirstSet3SetUS.setText(String.valueOf(7));
                A_pl_3SetUS_tb1 = x1;
                B_pl_3SetUS_tb1 = x2;
                SET_3_US_1_Set = false;
                SET_3_US_2_Set = true;

                convert(x1, x2, pointFPFirstSet3SetUS, pointSPFirstSet3SetUS);

                SET_3_US_1_SET_b_DONE = true;
                SET_3_US_1_SET_tb_DONE = true;

                Toast.makeText(this, pl2_1_3setUS + " " + pl2_2_3setUS + " " + getString(R.string.wins_1_set), Toast.LENGTH_LONG).show();
                pointSPFirstSet3SetUS.setBackgroundResource(R.drawable.frame_winner);
            }
        }
    }

    public void convert(int mp, int yu, TextView s1, TextView s2) {

        for (int i = 0; i < String.valueOf(mp).length(); i++) {
            int k = String.valueOf(mp).length() - i;
            sstr = degreeDigits[Character.getNumericValue(String.valueOf(mp).charAt(k - 1))] + sstr;
        }

        for (int kol = 0; kol < String.valueOf(yu).length(); kol++) {
            int kik = String.valueOf(yu).length() - kol;
            sstr1s = degreeDigits[Character.getNumericValue(String.valueOf(yu).charAt(kik - 1))] + sstr1s;
        }
        s1.setText(s1.getText() + sstr);
        s2.setText(s2.getText() + sstr1s);

        tieBreakDone_3SetUS = true;
        tb_3SetUS = false;

        pointFP3SetUS.setText(String.valueOf(0));
        pointSP3SetUS.setText(String.valueOf(0));

        sstr = "";
        sstr1s = "";

        fpls3US = 0;
        spls3US = 0;
        tbfp = 0;
        tbsp = 0;
    }

    public void convertUndo(int mp1, int yu1, TextView s11, TextView s21) {
        for (int i = 0; i < String.valueOf(mp1).length(); i++) {
            int k = String.valueOf(mp1).length() - i;
            sstr = degreeDigits[Character.getNumericValue(String.valueOf(mp1).charAt(k - 1))] + sstr;
        }

        for (int kol = 0; kol < String.valueOf(yu1).length(); kol++) {
            int kik = String.valueOf(yu1).length() - kol;
            sstr1s = degreeDigits[Character.getNumericValue(String.valueOf(yu1).charAt(kik - 1))] + sstr1s;
        }

        if (s11.getText().equals("7")) {

            s11.setBackgroundResource(R.drawable.frame_winner);
        } else {
            s21.setBackgroundResource(R.drawable.frame_winner);
        }
        s11.setText(s11.getText() + sstr);
        s21.setText(s21.getText() + sstr1s);
        sstr = "";
        sstr1s = "";
    }


    public int curNumberOfPoints(int p) {
        SQLiteDatabase db = mDbHelper3SetUS.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT number FROM statistic  WHERE _ID = " + Integer.toString(mDbHelper3SetUS.getLastId() - p), new String[]{});
        cursor.moveToFirst();
        curNum3SetUS = (int) cursor.getLong(0);
        cursor.close();
        return curNum3SetUS;
    }


    public int getKorI(TextView ki) {
        if (ki.getText().toString().equals("0")) {
            n3 = 0;
        } else if (ki.getText().toString().equals("15")) {
            n3 = 1;
        } else if (ki.getText().toString().equals("30")) {
            n3 = 2;
        } else if (ki.getText().toString().equals("40")) {
            n3 = 3;
        } else if (ki.getText().toString().equals("Ad")) {
            n3 = 4;
        }
        return n3;
    }

    public String getNum3SetUS(String jg) {

        SQLiteDatabase db = mDbHelper3SetUS.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT " + jg + " FROM "
                + Contract.UndoNumbers.TABLE_NAME_1 + " WHERE " + Contract.UndoNumbers.COLUMN_NUM_NUM +
                " = (SELECT MAX( " + Contract.UndoNumbers.COLUMN_NUM_NUM + " ) FROM " + Contract.UndoNumbers.TABLE_NAME_1 + " ) - " + undoCounter3SetUS, new String[]{});
        cursor.moveToFirst();
        prevNum3SetUS = (int) cursor.getLong(0);
        cursor.close();
        return String.valueOf(prevNum3SetUS);
    }

    public void undo3SetUS(View view) {

        ad3SetUS = new AlertDialog.Builder(context);

        ad3SetUS.setMessage(getString(R.string.really_undo));

        ad3SetUS.setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                undo3SetUS1();
            }
        });
        ad3SetUS.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
            }
        });
        ad3SetUS.setCancelable(true);
        ad3SetUS.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
            }
        });
        ad3SetUS.show();
    }

    public void undo3SetUS1() {

        goalFP3SetUS.setEnabled(true);
        goalSP3SetUS.setEnabled(true);
        spinner1p3sUS.setEnabled(true);
        spinner2p3sUS.setEnabled(true);
        beEnabled(redo_3SetUS, getDrawable(R.drawable.ic_redo_black_24dp));

        noEnabled(saveButton3SetUS, getDrawable(R.drawable.ic_save_black_24dp_0_3));
        Set3USStatusMatch = "Paused";
        playerFirst3SetUS.setBackgroundResource(R.drawable.frame_player);
        playerSecond3SetUS.setBackgroundResource(R.drawable.frame_player);
        stopMatchIsEnabled3SetUS = true;

        undoCounter3SetUS = undoCounter3SetUS + 1;

        updateColumnNumberDown3SetUS();

        SET_3_US_MATCH_DONE = false;

        if (getNum3SetUS("ktbfps").equals("-2")) {

            pointFP3SetUS.setText(adv_3SetUS);
            pointSP3SetUS.setText(getNum3SetUS("ktbsps"));

        } else if (getNum3SetUS("ktbsps").equals("-2")) {

            pointSP3SetUS.setText(adv_3SetUS);
            pointFP3SetUS.setText(getNum3SetUS("ktbfps"));

        } else {
            pointFP3SetUS.setText(getNum3SetUS("ktbfps"));
            pointSP3SetUS.setText(getNum3SetUS("ktbsps"));
        }

        if (getNum3SetUS("set1a").equals("7") & getNum3SetUS("set1b").equals("6")) {

            pointFPFirstSet3SetUS.setText(getNum3SetUS("set1a"));
            pointSPFirstSet3SetUS.setText(getNum3SetUS("set1b"));
            convertUndo(A_pl_3SetUS_tb1, B_pl_3SetUS_tb1, pointFPFirstSet3SetUS, pointSPFirstSet3SetUS);

        } else if (getNum3SetUS("set1a").equals("6") & getNum3SetUS("set1b").equals("7")) {

            pointFPFirstSet3SetUS.setText(getNum3SetUS("set1a"));
            pointSPFirstSet3SetUS.setText(getNum3SetUS("set1b"));
            convertUndo(A_pl_3SetUS_tb1, B_pl_3SetUS_tb1, pointFPFirstSet3SetUS, pointSPFirstSet3SetUS);

        } else {

            pointFPFirstSet3SetUS.setText(getNum3SetUS("set1a"));
            pointSPFirstSet3SetUS.setText(getNum3SetUS("set1b"));
        }

        if (getNum3SetUS("set2a").equals("7") & getNum3SetUS("set2b").equals("6")) {

            pointFPSecondSet3SetUS.setText(getNum3SetUS("set2a"));
            pointSPSecondSet3SetUS.setText(getNum3SetUS("set2b"));
            convertUndo(A_pl_3SetUS_tb2, B_pl_3SetUS_tb2, pointFPSecondSet3SetUS, pointSPSecondSet3SetUS);

        } else if (getNum3SetUS("set2a").equals("6") & getNum3SetUS("set2b").equals("7")) {

            pointFPSecondSet3SetUS.setText(getNum3SetUS("set2a"));
            pointSPSecondSet3SetUS.setText(getNum3SetUS("set2b"));
            convertUndo(A_pl_3SetUS_tb2, B_pl_3SetUS_tb2, pointFPSecondSet3SetUS, pointSPSecondSet3SetUS);

        } else {

            pointFPSecondSet3SetUS.setText(getNum3SetUS("set2a"));
            pointSPSecondSet3SetUS.setText(getNum3SetUS("set2b"));
        }
        pointFPThirdSet3SetUS.setText(getNum3SetUS("set3a"));
        pointSPThirdSet3SetUS.setText(getNum3SetUS("set3b"));


        if (SET_3_US_3_SET_tb_DONE) {

            pointFPThirdSet3SetUS.setBackgroundResource(R.drawable.frame_player);
            pointSPThirdSet3SetUS.setBackgroundResource(R.drawable.frame_player);

            SET_3_US_3_SET_tb_DONE = false;
            SET_3_US_3_Set = true;
            tb_3SetUS = true;
            tieBreakDone_3SetUS = false;
        }

        if (SET_3_US_3_Set) {

            pointFPThirdSet3SetUS.setBackgroundResource(R.drawable.frame_player);
            pointSPThirdSet3SetUS.setBackgroundResource(R.drawable.frame_player);

            if (tb_3SetUS) {

                if (!"6".equals(getNum3SetUS("set3a")) | !"6".equals(getNum3SetUS("set3b"))) {

                    tb_3SetUS = false;
                }
            } else if (!tb_3SetUS) {

                if ((getNum3SetUS("set2a").equals("7") & (getNum3SetUS("set2b").equals("6") | getNum3SetUS("set2b").equals("5"))) |
                        (getNum3SetUS("set2b").equals("7") & (getNum3SetUS("set2a").equals("6") | getNum3SetUS("set2a").equals("5"))) |
                        (getNum3SetUS("set2a").equals("6") & !"6".equals(getNum3SetUS("set2b")) & !"5".equals(getNum3SetUS("set2b"))) |
                        (getNum3SetUS("set2b").equals("6") & !"6".equals(getNum3SetUS("set2a")) & !"5".equals(getNum3SetUS("set2a")))) {

                    SET_3_US_3_Set = true;

                } else {

                    SET_3_US_3_Set = false;
                    SET_3_US_2_Set = true;
                    SET_3_US_2_SET_a_DONE = false;
                    SET_3_US_2_SET_b_DONE = false;

                }
            }
        }
        if (SET_3_US_2_Set) {

            pointFPSecondSet3SetUS.setBackgroundResource(R.drawable.frame_player);
            pointSPSecondSet3SetUS.setBackgroundResource(R.drawable.frame_player);

            SET_3_US_2_SET_a_DONE = false;
            SET_3_US_2_SET_b_DONE = false;

            if (SET_3_US_2_SET_tb_DONE) {

                SET_3_US_2_SET_tb_DONE = false;
                tb_3SetUS = true;
                tieBreakDone_3SetUS = false;
            }
            if (tb_3SetUS) {

                if (!"6".equals(getNum3SetUS("set2a")) | !"6".equals(getNum3SetUS("set2b"))) {
                    tb_3SetUS = false;

                }
            } else if (!tb_3SetUS) {

                if ((getNum3SetUS("set1a").equals("7") & (getNum3SetUS("set1b").equals("6") | getNum3SetUS("set1b").equals("5"))) |
                        (getNum3SetUS("set1b").equals("7") & (getNum3SetUS("set1a").equals("6") | getNum3SetUS("set1a").equals("5"))) |
                        (getNum3SetUS("set1a").equals("6") & !"6".equals(getNum3SetUS("set1b")) & !"5".equals(getNum3SetUS("set1b"))) |
                        (getNum3SetUS("set1b").equals("6") & !"6".equals(getNum3SetUS("set1a")) & !"5".equals(getNum3SetUS("set1a")))) {

                    SET_3_US_2_Set = true;

                } else {

                    SET_3_US_1_Set = true;
                    SET_3_US_2_Set = false;
                    SET_3_US_1_SET_a_DONE = false;
                    SET_3_US_1_SET_b_DONE = false;
                }
            }
        }
        if (SET_3_US_1_Set) {

            pointFPFirstSet3SetUS.setBackgroundResource(R.drawable.frame_player);
            pointSPFirstSet3SetUS.setBackgroundResource(R.drawable.frame_player);

            if (SET_3_US_1_SET_tb_DONE) {
                SET_3_US_1_SET_tb_DONE = false;
                tb_3SetUS = true;
                tieBreakDone_3SetUS = false;
            }

            if (tb_3SetUS) {
                if (!"6".equals(getNum3SetUS("set1a")) | !"6".equals(getNum3SetUS("set1b"))) {
                    tb_3SetUS = false;
                }
            }
        }

        goalsCounter3SetUS--;

        if (goalsCounter3SetUS <= 0) {
            noEnabled(undo_3SetUS, getDrawable(R.drawable.ic_undo_black_24dp_0_3));
        }
    }


    public void redo3SetUS(View view) {

        ad3SetUS = new AlertDialog.Builder(context);

        ad3SetUS.setMessage(getString(R.string.really_redo));

        ad3SetUS.setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                redo3SetUS1();
            }
        });
        ad3SetUS.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
            }
        });
        ad3SetUS.setCancelable(true);
        ad3SetUS.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
            }
        });
        ad3SetUS.show();
    }

    public void redo3SetUS1() {

        beEnabled(undo_3SetUS, getDrawable(R.drawable.ic_undo_black_24dp));
        spinner1p3sUS.setSelection(0);
        spinner2p3sUS.setSelection(0);

        updateColumnNumberUp3SetUS();

        undoCounter3SetUS = undoCounter3SetUS - 1;

        if (getNum3SetUS("ktbfps").equals("-2")) {

            pointFP3SetUS.setText(adv_3SetUS);
            pointSP3SetUS.setText(getNum3SetUS("ktbsps"));

        } else if (getNum3SetUS("ktbsps").equals("-2")) {

            pointFP3SetUS.setText(getNum3SetUS("ktbfps"));
            pointSP3SetUS.setText(adv_3SetUS);

        } else {

            pointFP3SetUS.setText(getNum3SetUS("ktbfps"));
            pointSP3SetUS.setText(getNum3SetUS("ktbsps"));
        }
///////////////////////////////////////
        if (getNum3SetUS("set1a").equals("7") & getNum3SetUS("set1b").equals("6")) {

            pointFPFirstSet3SetUS.setText(getNum3SetUS("set1a"));
            pointSPFirstSet3SetUS.setText(getNum3SetUS("set1b"));
            convertUndo(A_pl_3SetUS_tb1, B_pl_3SetUS_tb1, pointFPFirstSet3SetUS, pointSPFirstSet3SetUS);


        } else if (getNum3SetUS("set1a").equals("6") & getNum3SetUS("set1b").equals("7")) {

            pointFPFirstSet3SetUS.setText(getNum3SetUS("set1a"));
            pointSPFirstSet3SetUS.setText(getNum3SetUS("set1b"));
            convertUndo(A_pl_3SetUS_tb1, B_pl_3SetUS_tb1, pointFPFirstSet3SetUS, pointSPFirstSet3SetUS);

        } else {

            pointFPFirstSet3SetUS.setText(getNum3SetUS("set1a"));
            pointSPFirstSet3SetUS.setText(getNum3SetUS("set1b"));

        }
////////////////////////////////////////
        if (getNum3SetUS("set2a").equals("7") & getNum3SetUS("set2b").equals("6")) {

            pointFPSecondSet3SetUS.setText(getNum3SetUS("set2a"));
            pointSPSecondSet3SetUS.setText(getNum3SetUS("set2b"));
            convertUndo(A_pl_3SetUS_tb2, B_pl_3SetUS_tb2, pointFPSecondSet3SetUS, pointSPSecondSet3SetUS);

        } else if (getNum3SetUS("set2a").equals("6") & getNum3SetUS("set2b").equals("7")) {

            pointFPSecondSet3SetUS.setText(getNum3SetUS("set2a"));
            pointSPSecondSet3SetUS.setText(getNum3SetUS("set2b"));
            convertUndo(A_pl_3SetUS_tb2, B_pl_3SetUS_tb2, pointFPSecondSet3SetUS, pointSPSecondSet3SetUS);

        } else {

            pointFPSecondSet3SetUS.setText(getNum3SetUS("set2a"));
            pointSPSecondSet3SetUS.setText(getNum3SetUS("set2b"));
        }
////////////////////////////////////////
        if (getNum3SetUS("set3a").equals("7") & getNum3SetUS("set3b").equals("6")) {

            pointFPThirdSet3SetUS.setText(getNum3SetUS("set3a"));
            pointSPThirdSet3SetUS.setText(getNum3SetUS("set3b"));
            convertUndo(A_pl_3SetUS_tb3, B_pl_3SetUS_tb3, pointFPThirdSet3SetUS, pointSPThirdSet3SetUS);

        } else if (getNum3SetUS("set3a").equals("6") & getNum3SetUS("set3b").equals("7")) {

            pointFPThirdSet3SetUS.setText(getNum3SetUS("set3a"));
            pointSPThirdSet3SetUS.setText(getNum3SetUS("set3b"));
            convertUndo(A_pl_3SetUS_tb3, B_pl_3SetUS_tb3, pointFPThirdSet3SetUS, pointSPThirdSet3SetUS);

        } else {

            pointFPThirdSet3SetUS.setText(getNum3SetUS("set3a"));
            pointSPThirdSet3SetUS.setText(getNum3SetUS("set3b"));
        }
///////////////////////////////////
        if (SET_3_US_1_Set) {

            if (getNum3SetUS("set1a").equals("6") & getNum3SetUS("set1b").equals("6")) {

                tb_3SetUS = true;

                checkTieBreakNumber(Integer.valueOf(getNum3SetUS("ktbfps")), Integer.valueOf(getNum3SetUS("ktbsps")));

                if (getNum3SetUS("ktbfps").equals("0") && getNum3SetUS("ktbsps").equals("0")) {

                    Toast.makeText(getBaseContext(), getString(R.string.Tie_break_starts), Toast.LENGTH_SHORT).show();
                }

            } else if (getNum3SetUS("set1a").equals("7") & getNum3SetUS("set1b").equals("6") & getNum3SetUS("ktbfps").equals("0") & getNum3SetUS("ktbsps").equals("0")) {

                tb_3SetUS = false;
                SET_3_US_1_SET_a_DONE = true;
                SET_3_US_1_SET_tb_DONE = true;
                tieBreakDone_3SetUS = true;
                SET_3_US_1_Set = false;
                SET_3_US_2_Set = true;

                Toast.makeText(this, pl1_1_3setUS + " " + pl1_2_3setUS + " " + getString(R.string.wins_1_set), Toast.LENGTH_LONG).show();
                pointFPFirstSet3SetUS.setBackgroundResource(R.drawable.frame_winner);

            } else if (getNum3SetUS("set1a").equals("7") & getNum3SetUS("set1b").equals("6")
                    & (!"0".equals(getNum3SetUS("ktbfps")) | !"0".equals(getNum3SetUS("ktbsps")))) {

                tieBreakDone_3SetUS = false;

            } else if (getNum3SetUS("set1a").equals("6") & getNum3SetUS("set1b").equals("7")
                    & (!"0".equals(getNum3SetUS("ktbfps")) | !"0".equals(getNum3SetUS("ktbsps")))) {

                tieBreakDone_3SetUS = false;

            } else if (getNum3SetUS("set1a").equals("6") & getNum3SetUS("set1b").equals("7") & getNum3SetUS("ktbfps").equals("0") & getNum3SetUS("ktbsps").equals("0")) {

                tb_3SetUS = false;
                SET_3_US_1_SET_b_DONE = true;
                SET_3_US_1_SET_tb_DONE = true;
                tieBreakDone_3SetUS = true;
                SET_3_US_1_Set = false;
                SET_3_US_2_Set = true;

                Toast.makeText(this, pl2_1_3setUS + " " + pl2_2_3setUS + " " + getString(R.string.wins_1_set), Toast.LENGTH_LONG).show();
                pointSPFirstSet3SetUS.setBackgroundResource(R.drawable.frame_winner);

            } else {
                checkSetNumber(Integer.valueOf(getNum3SetUS("set1a")), Integer.valueOf(getNum3SetUS("set1b")));
            }

        }
//////////////////////////////
        if (SET_3_US_2_Set) {

            if (getNum3SetUS("set2a").equals("6") & getNum3SetUS("set2b").equals("6")) {

                tb_3SetUS = true;

                checkTieBreakNumber(Integer.valueOf(getNum3SetUS("ktbfps")), Integer.valueOf(getNum3SetUS("ktbsps")));

                if (getNum3SetUS("ktbfps").equals("0") && getNum3SetUS("ktbsps").equals("0")) {

                    Toast.makeText(getBaseContext(), getString(R.string.Tie_break_starts), Toast.LENGTH_SHORT).show();
                }

            } else if (getNum3SetUS("set1a").equals("7") & getNum3SetUS("set1b").equals("6")

                    & (!"0".equals(getNum3SetUS("ktbfps")) | !"0".equals(getNum3SetUS("ktbsps")))) {

                tieBreakDone_3SetUS = false;

            } else if (getNum3SetUS("set1a").equals("6") & getNum3SetUS("set1b").equals("7")

                    & (!"0".equals(getNum3SetUS("ktbfps")) | !"0".equals(getNum3SetUS("ktbsps")))) {

                tieBreakDone_3SetUS = false;

            } else if (getNum3SetUS("set2a").equals("7") & getNum3SetUS("set2b").equals("6") & getNum3SetUS("ktbfps").equals("0") & getNum3SetUS("ktbsps").equals("0")) {

                if (SET_3_US_1_SET_a_DONE) {

                    SET_3_US_2_SET_a_DONE = true;
                    SET_3_US_2_SET_tb_DONE = true;
                    SET_3_US_MATCH_DONE = true;

                    Toast.makeText(this, pl1_1_3setUS + " " + pl1_2_3setUS + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                    AA_KingTieBreakActivity.setButNotEnabled(goalFP3SetUS, goalSP3SetUS, spinner1p3sUS, spinner2p3sUS);
                    setWinnerSet_3US(playerFirst3SetUS, pointFPSecondSet3SetUS, "firstPlayer");

                } else if (SET_3_US_1_SET_b_DONE) {

                    SET_3_US_2_SET_a_DONE = true;
                    SET_3_US_2_SET_tb_DONE = true;
                    SET_3_US_2_Set = false;
                    SET_3_US_3_Set = true;

                    Toast.makeText(this, pl1_1_3setUS + " " + pl1_2_3setUS + " " + getString(R.string.wins_2_set), Toast.LENGTH_LONG).show();
                    pointFPSecondSet3SetUS.setBackgroundResource(R.drawable.frame_winner);
                }
                tb_3SetUS = false;
                tieBreakDone_3SetUS = true;

            } else if (getNum3SetUS("set2a").equals("6") & getNum3SetUS("set2b").equals("7") & getNum3SetUS("ktbfps").equals("0") & getNum3SetUS("ktbsps").equals("0")) {

                if (SET_3_US_1_SET_a_DONE) {

                    SET_3_US_2_SET_b_DONE = true;
                    SET_3_US_2_SET_tb_DONE = true;
                    SET_3_US_2_Set = false;
                    SET_3_US_3_Set = true;

                    Toast.makeText(this, pl2_1_3setUS + " " + pl2_2_3setUS + " " + getString(R.string.wins_2_set), Toast.LENGTH_LONG).show();
                    pointSPSecondSet3SetUS.setBackgroundResource(R.drawable.frame_winner);

                } else if (SET_3_US_1_SET_b_DONE) {

                    SET_3_US_2_SET_b_DONE = true;
                    SET_3_US_2_SET_tb_DONE = true;
                    SET_3_US_MATCH_DONE = true;

                    Toast.makeText(this, pl2_1_3setUS + " " + pl2_2_3setUS + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                    AA_KingTieBreakActivity.setButNotEnabled(goalFP3SetUS, goalSP3SetUS, spinner1p3sUS, spinner2p3sUS);
                    setWinnerSet_3US(playerSecond3SetUS, pointSPSecondSet3SetUS, "secondPlayer");
                }
                tb_3SetUS = false;
                tieBreakDone_3SetUS = true;
            } else {
                checkSetNumber(Integer.valueOf(getNum3SetUS("set2a")), Integer.valueOf(getNum3SetUS("set2b")));
            }
        }

        if (SET_3_US_3_Set) {

            if (getNum3SetUS("set3a").equals("6") & getNum3SetUS("set3b").equals("6")) {

                tb_3SetUS = true;

                checkTieBreakNumber(Integer.valueOf(getNum3SetUS("ktbfps")), Integer.valueOf(getNum3SetUS("ktbsps")));

                if (getNum3SetUS("ktbfps").equals("0") && getNum3SetUS("ktbsps").equals("0")) {

                    Toast.makeText(getBaseContext(), getString(R.string.Tie_break_starts), Toast.LENGTH_SHORT).show();
                }

            } else if (getNum3SetUS("set2a").equals("7") & getNum3SetUS("set2b").equals("6")

                    & (!"0".equals(getNum3SetUS("ktbfps")) | !"0".equals(getNum3SetUS("ktbsps")))) {

                tieBreakDone_3SetUS = false;

            } else if (getNum3SetUS("set2a").equals("6") & getNum3SetUS("set2b").equals("7")

                    & (!"0".equals(getNum3SetUS("ktbfps")) | !"0".equals(getNum3SetUS("ktbsps")))) {

                tieBreakDone_3SetUS = false;

            } else if (getNum3SetUS("set3a").equals("7") & getNum3SetUS("set3b").equals("6") & getNum3SetUS("ktbfps").equals("0") & getNum3SetUS("ktbsps").equals("0")) {

                SET_3_US_MATCH_DONE = true;
                SET_3_US_3_SET_tb_DONE = true;
                tb_3SetUS = false;
                tieBreakDone_3SetUS = true;

                Toast.makeText(this, pl1_1_3setUS + " " + pl1_2_3setUS + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                AA_KingTieBreakActivity.setButNotEnabled(goalFP3SetUS, goalSP3SetUS, spinner1p3sUS, spinner2p3sUS);
                setWinnerSet_3US(playerFirst3SetUS, pointFPThirdSet3SetUS, "firstPlayer");

            } else if (getNum3SetUS("set3a").equals("6") & getNum3SetUS("set3b").equals("7") & getNum3SetUS("ktbfps").equals("0") & getNum3SetUS("ktbsps").equals("0")) {

                SET_3_US_MATCH_DONE = true;
                SET_3_US_3_SET_tb_DONE = true;
                tb_3SetUS = false;
                tieBreakDone_3SetUS = true;

                Toast.makeText(this, pl2_1_3setUS + " " + pl2_2_3setUS + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                AA_KingTieBreakActivity.setButNotEnabled(goalFP3SetUS, goalSP3SetUS, spinner1p3sUS, spinner2p3sUS);
                setWinnerSet_3US(playerSecond3SetUS, pointFPThirdSet3SetUS, "secondPlayer");

            } else {
                checkSetNumber(Integer.valueOf(getNum3SetUS("set3a")), Integer.valueOf(getNum3SetUS("set3b")));
            }
        }

        if (undoCounter3SetUS == 0) {

            noEnabled(redo_3SetUS, getDrawable(R.drawable.ic_redo_black_24dp_0_3));
        }

        goalsCounter3SetUS++;

        if (goalsCounter3SetUS > 0) {
            beEnabled(undo_3SetUS, getDrawable(R.drawable.ic_undo_black_24dp));
        }
    }

    public void updateColumnNumberDown3SetUS() {

        SQLiteDatabase db = mDbHelper3SetUS.getWritableDatabase();
        String insertQuery = " UPDATE " + Contract.ContractNames.TABLE_NAME + " SET " + Contract.ContractNames.COLUMN_NUMBER
                + " = ((SELECT " + Contract.ContractNames.COLUMN_NUMBER + " FROM " + Contract.ContractNames.TABLE_NAME +
                " WHERE " + Contract.ContractNames._ID + " = " + getLastIdfromUndoTable1Set() + ") - 1 ) WHERE " + Contract.ContractNames._ID + " = " + getLastIdfromUndoTable1Set();
        db.execSQL(insertQuery);
    }

    public void updateColumnNumberUp3SetUS() {
        SQLiteDatabase db = mDbHelper3SetUS.getWritableDatabase();
        String insertQuery = " UPDATE " + Contract.ContractNames.TABLE_NAME + " SET " + Contract.ContractNames.COLUMN_NUMBER
                + " = ((SELECT " + Contract.ContractNames.COLUMN_NUMBER + " FROM " + Contract.ContractNames.TABLE_NAME +
                " WHERE " + Contract.ContractNames._ID + " = " + getLastIdfromUndoTable1Set() + ") + 1 ) WHERE " + Contract.ContractNames._ID + " = " + getLastIdfromUndoTable1Set();
        db.execSQL(insertQuery);
    }

    public int getLastIdfromUndoTable1Set() {
        SQLiteDatabase db = mDbHelper3SetUS.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT " + Contract.UndoNumbers.COLUMN_NUM_ID + " FROM "
                + Contract.UndoNumbers.TABLE_NAME_1 + " WHERE " + Contract.UndoNumbers.COLUMN_NUM_NUM +
                " = (SELECT MAX( " + Contract.UndoNumbers.COLUMN_NUM_NUM + " ) FROM " + Contract.UndoNumbers.TABLE_NAME_1 + " ) - " + (undoCounter3SetUS - 1), new String[]{});
        cursor.moveToFirst();
        lastIdfromUT3SetUS = (int) cursor.getLong(0); //The 0 is the column index, we only have 1 column, so the index is 0
        cursor.close();
        return lastIdfromUT3SetUS;
    }


    public void deleteRow3SetUS() {

        beEnabled(undo_3SetUS, getDrawable(R.drawable.ic_undo_black_24dp));
        SQLiteDatabase db = mDbHelper3SetUS.getReadableDatabase();
        String insertQuery = " DELETE FROM " + Contract.UndoNumbers.TABLE_NAME_1 + " WHERE " + Contract.UndoNumbers.COLUMN_NUM_NUM +
                " IN ( SELECT " + Contract.UndoNumbers.COLUMN_NUM_NUM + " FROM " + Contract.UndoNumbers.TABLE_NAME_1 +
                " ORDER BY " + Contract.UndoNumbers.COLUMN_NUM_NUM + " DESC LIMIT " + undoCounter3SetUS + ")";
        db.execSQL(insertQuery);
        undoCounter3SetUS = 0;
    }

    public void goToHelp() {
        Intent helpIntent = new Intent(this, DataBase.class);
        startActivity(helpIntent);
    }

    public void saveResultGoToMM() {
        saveResult3SetUS();
        mDbHelper3SetUS.clearTable(Contract.UndoNumbers.TABLE_NAME_1);
        mDbHelper3SetUS.clearTable(Contract.ContractNames.TABLE_NAME);
        finish();
        Intent intent1 = new Intent(this, AA_MainActivity.class);
        startActivity(intent1);
    }

    public void noSaveResultGoToMM() {
        mDbHelper3SetUS.clearTable(Contract.UndoNumbers.TABLE_NAME_1);
        mDbHelper3SetUS.clearTable(Contract.ContractNames.TABLE_NAME);
        finish();
        Intent intent1 = new Intent(this, AA_MainActivity.class);
        startActivity(intent1);
    }

    public void saveResult3SetUS() {

        if (getIntent().getExtras().getBoolean("isNew")) {

            mDbHelper3SetUS.putName13("fullName", "matchMode", "courtSurface", pl1_1_3setUS, pl1_2_3setUS, pl2_1_3setUS, pl2_2_3setUS, Set3USStatusMatch,
                    mDbHelper3SetUS.getStringFromNumDataFromUndoTable("ktbfps", undoCounter3SetUS), mDbHelper3SetUS.getStringFromNumDataFromUndoTable("ktbsps", undoCounter3SetUS),
                    mDbHelper3SetUS.getStringFromNumDataFromUndoTable("set1a", undoCounter3SetUS), A_pl_3SetUS_tb1, mDbHelper3SetUS.getStringFromNumDataFromUndoTable("set1b", undoCounter3SetUS), B_pl_3SetUS_tb1,
                    mDbHelper3SetUS.getStringFromNumDataFromUndoTable("set2a", undoCounter3SetUS), A_pl_3SetUS_tb2, mDbHelper3SetUS.getStringFromNumDataFromUndoTable("set2b", undoCounter3SetUS), B_pl_3SetUS_tb2,
                    mDbHelper3SetUS.getStringFromNumDataFromUndoTable("set3a", undoCounter3SetUS), A_pl_3SetUS_tb3, mDbHelper3SetUS.getStringFromNumDataFromUndoTable("set3b", undoCounter3SetUS), B_pl_3SetUS_tb3,
                    "0", -2, "0", -2, "0", -2, "0", -2, 7, 3, 6, 2, 5, 1, 4, 0, getDFB_3SetUS(tb1pl_3SetUS), getDFB_3SetUS(tb2pl_3SetUS), getDFB_3SetUS(tieBreakDone_3SetUS),
                    getDFB_3SetUS(stopMatchIsEnabled3SetUS), getDFB_3SetUS(tb_3SetUS), getDFB_3SetUS(SET_3_US_1_SET_a_DONE), getDFB_3SetUS(SET_3_US_1_SET_b_DONE),
                    getDFB_3SetUS(SET_3_US_2_SET_a_DONE), getDFB_3SetUS(SET_3_US_2_SET_b_DONE), 0, 0, 0, 0, getDFB_3SetUS(SET_3_US_1_Set), getDFB_3SetUS(SET_3_US_2_Set),
                    getDFB_3SetUS(SET_3_US_3_Set), 0, 0, getDFB_3SetUS(SET_3_US_1_SET_tb_DONE), getDFB_3SetUS(SET_3_US_2_SET_tb_DONE), getDFB_3SetUS(SET_3_US_3_SET_tb_DONE),
                    0, 0, getDFB_3SetUS(SET_3_US_MATCH_DONE));

        } else {
            mDbHelper3SetUS.putName122("fullName", "matchMode", "courtSurface", pl1_1_3setUS, pl1_2_3setUS, pl2_1_3setUS, pl2_2_3setUS, Set3USStatusMatch,
                    mDbHelper3SetUS.getStringFromNumDataFromUndoTable("ktbfps", undoCounter3SetUS), mDbHelper3SetUS.getStringFromNumDataFromUndoTable("ktbsps", undoCounter3SetUS),
                    mDbHelper3SetUS.getStringFromNumDataFromUndoTable("set1a", undoCounter3SetUS), A_pl_3SetUS_tb1, mDbHelper3SetUS.getStringFromNumDataFromUndoTable("set1b", undoCounter3SetUS), B_pl_3SetUS_tb1,
                    mDbHelper3SetUS.getStringFromNumDataFromUndoTable("set2a", undoCounter3SetUS), A_pl_3SetUS_tb2, mDbHelper3SetUS.getStringFromNumDataFromUndoTable("set2b", undoCounter3SetUS), B_pl_3SetUS_tb2,
                    mDbHelper3SetUS.getStringFromNumDataFromUndoTable("set3a", undoCounter3SetUS), A_pl_3SetUS_tb3, mDbHelper3SetUS.getStringFromNumDataFromUndoTable("set3b", undoCounter3SetUS), B_pl_3SetUS_tb3,
                    "0", -2, "0", -2, "0", -2, "0", -2, 7, 3, 6, 2, 5, 1, 4, 0, getDFB_3SetUS(tb1pl_3SetUS), getDFB_3SetUS(tb2pl_3SetUS), getDFB_3SetUS(tieBreakDone_3SetUS),
                    getDFB_3SetUS(stopMatchIsEnabled3SetUS), getDFB_3SetUS(tb_3SetUS), getDFB_3SetUS(SET_3_US_1_SET_a_DONE), getDFB_3SetUS(SET_3_US_1_SET_b_DONE),
                    getDFB_3SetUS(SET_3_US_2_SET_a_DONE), getDFB_3SetUS(SET_3_US_2_SET_b_DONE), 0, 0, 0, 0, getDFB_3SetUS(SET_3_US_1_Set), getDFB_3SetUS(SET_3_US_2_Set),
                    getDFB_3SetUS(SET_3_US_3_Set), 0, 0, getDFB_3SetUS(SET_3_US_1_SET_tb_DONE), getDFB_3SetUS(SET_3_US_2_SET_tb_DONE), getDFB_3SetUS(SET_3_US_3_SET_tb_DONE),
                    0, 0, getDFB_3SetUS(SET_3_US_MATCH_DONE));
        }
    }

    public void saveResultFromStat(String st) {

        Set3USStatusMatch = st;
        if (getIntent().getExtras().getBoolean("isNew")) {

            mDbHelper3SetUS.putName13("fullName", "matchMode", "courtSurface", pl1_1_3setUS, pl1_2_3setUS, pl2_1_3setUS, pl2_2_3setUS, Set3USStatusMatch,
                    mDbHelper3SetUS.getStringFromNumDataFromUndoTable("ktbfps", undoCounter3SetUS), mDbHelper3SetUS.getStringFromNumDataFromUndoTable("ktbsps", undoCounter3SetUS),
                    mDbHelper3SetUS.getStringFromNumDataFromUndoTable("set1a", undoCounter3SetUS), A_pl_3SetUS_tb1, mDbHelper3SetUS.getStringFromNumDataFromUndoTable("set1b", undoCounter3SetUS), B_pl_3SetUS_tb1,
                    mDbHelper3SetUS.getStringFromNumDataFromUndoTable("set2a", undoCounter3SetUS), A_pl_3SetUS_tb2, mDbHelper3SetUS.getStringFromNumDataFromUndoTable("set2b", undoCounter3SetUS), B_pl_3SetUS_tb2,
                    mDbHelper3SetUS.getStringFromNumDataFromUndoTable("set3a", undoCounter3SetUS), A_pl_3SetUS_tb3, mDbHelper3SetUS.getStringFromNumDataFromUndoTable("set3b", undoCounter3SetUS), B_pl_3SetUS_tb3,
                    "0", -2, "0", -2, "0", -2, "0", -2, 7, 3, 6, 2, 5, 1, 4, 0, getDFB_3SetUS(tb1pl_3SetUS), getDFB_3SetUS(tb2pl_3SetUS), getDFB_3SetUS(tieBreakDone_3SetUS),
                    getDFB_3SetUS(stopMatchIsEnabled3SetUS), getDFB_3SetUS(tb_3SetUS), getDFB_3SetUS(SET_3_US_1_SET_a_DONE), getDFB_3SetUS(SET_3_US_1_SET_b_DONE),
                    getDFB_3SetUS(SET_3_US_2_SET_a_DONE), getDFB_3SetUS(SET_3_US_2_SET_b_DONE), 0, 0, 0, 0, getDFB_3SetUS(SET_3_US_1_Set), getDFB_3SetUS(SET_3_US_2_Set),
                    getDFB_3SetUS(SET_3_US_3_Set), 0, 0, getDFB_3SetUS(SET_3_US_1_SET_tb_DONE), getDFB_3SetUS(SET_3_US_2_SET_tb_DONE), getDFB_3SetUS(SET_3_US_3_SET_tb_DONE),
                    0, 0, getDFB_3SetUS(SET_3_US_MATCH_DONE));

        } else {
            mDbHelper3SetUS.putName122("fullName", "matchMode", "courtSurface", pl1_1_3setUS, pl1_2_3setUS, pl2_1_3setUS, pl2_2_3setUS, Set3USStatusMatch,
                    mDbHelper3SetUS.getStringFromNumDataFromUndoTable("ktbfps", undoCounter3SetUS), mDbHelper3SetUS.getStringFromNumDataFromUndoTable("ktbsps", undoCounter3SetUS),
                    mDbHelper3SetUS.getStringFromNumDataFromUndoTable("set1a", undoCounter3SetUS), A_pl_3SetUS_tb1, mDbHelper3SetUS.getStringFromNumDataFromUndoTable("set1b", undoCounter3SetUS), B_pl_3SetUS_tb1,
                    mDbHelper3SetUS.getStringFromNumDataFromUndoTable("set2a", undoCounter3SetUS), A_pl_3SetUS_tb2, mDbHelper3SetUS.getStringFromNumDataFromUndoTable("set2b", undoCounter3SetUS), B_pl_3SetUS_tb2,
                    mDbHelper3SetUS.getStringFromNumDataFromUndoTable("set3a", undoCounter3SetUS), A_pl_3SetUS_tb3, mDbHelper3SetUS.getStringFromNumDataFromUndoTable("set3b", undoCounter3SetUS), B_pl_3SetUS_tb3,
                    "0", -2, "0", -2, "0", -2, "0", -2, 7, 3, 6, 2, 5, 1, 4, 0, getDFB_3SetUS(tb1pl_3SetUS), getDFB_3SetUS(tb2pl_3SetUS), getDFB_3SetUS(tieBreakDone_3SetUS),
                    getDFB_3SetUS(stopMatchIsEnabled3SetUS), getDFB_3SetUS(tb_3SetUS), getDFB_3SetUS(SET_3_US_1_SET_a_DONE), getDFB_3SetUS(SET_3_US_1_SET_b_DONE),
                    getDFB_3SetUS(SET_3_US_2_SET_a_DONE), getDFB_3SetUS(SET_3_US_2_SET_b_DONE), 0, 0, 0, 0, getDFB_3SetUS(SET_3_US_1_Set), getDFB_3SetUS(SET_3_US_2_Set),
                    getDFB_3SetUS(SET_3_US_3_Set), 0, 0, getDFB_3SetUS(SET_3_US_1_SET_tb_DONE), getDFB_3SetUS(SET_3_US_2_SET_tb_DONE), getDFB_3SetUS(SET_3_US_3_SET_tb_DONE),
                    0, 0, getDFB_3SetUS(SET_3_US_MATCH_DONE));
        }

        mDbHelper3SetUS.clearTable(Contract.UndoNumbers.TABLE_NAME_1);
        mDbHelper3SetUS.clearTable(Contract.ContractNames.TABLE_NAME);
        finish();
        Intent intent1 = new Intent(this, AA_MainActivity.class);
        startActivity(intent1);
    }

    public void saveAndGo3SetUS(View view) {

            saveResultGoToMM();

    }

    private void setWinnerSet_3US(TextView tv1, TextView tv2, String str) {

        tv1.setBackgroundResource(R.drawable.frame_winner);
        tv2.setBackgroundResource(R.drawable.frame_winner);
        beEnabled(saveButton3SetUS, getDrawable(R.drawable.ic_save_black_24dp));
        stopMatchIsEnabled3SetUS = false;
        Set3USStatusMatch = str;
    }

    public int getDFB_3SetUS(Boolean b) {
        int u = 0;
        if (b) {
            u = 1;
        }
        return u;
    }
    @Override
    public void onDestroy() {
        mDbHelper3SetUS.clearTable(Contract.UndoNumbers.TABLE_NAME_1);
        mDbHelper3SetUS.clearTable(Contract.ContractNames.TABLE_NAME);
        super.onDestroy();
    }
}
