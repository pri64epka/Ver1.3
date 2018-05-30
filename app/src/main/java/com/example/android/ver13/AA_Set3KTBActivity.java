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
import static com.example.android.ver13.AA_Set1Activity.degreeDigits;
import static com.example.android.ver13.AA_Set1Activity.tp;

public class AA_Set3KTBActivity extends AppCompatActivity {
    TextView playerFirst3SetKTB, playerSecond3SetKTB, pointFP3SetKTB, pointSP3SetKTB,
            pointFPFirstSet3SetKTB, pointSPFirstSet3SetKTB, pointFPSecondSet3SetKTB, pointSPSecondSet3SetKTB, pointFPKTB3SetKTB, pointSPKTB3SetKTB;
    Spinner spinner1p3sKTB, spinner2p3sKTB;
    Button goalFP3SetKTB, goalSP3SetKTB;
    ImageButton undo_3SetKTB, redo_3SetKTB, saveButton3SetKTB;

    String reasonsSelectedItem, pl1_1_3setKTB, pl1_2_3setKTB, pl2_1_3setKTB, pl2_2_3setKTB, sp1_set3KTB, sp2set3KTB;
    String adv_3SetKTB = "Ad";
    String str = "";
    String str1s = "";

    private int i3sk = 0;
    private int k3sk = 0;
    private int fpls3KTB = 0;
    private int spls3KTB = 0;
    private int undoCounter_3SetKTB = 0;

    int curNum3SetKTB, n2, tbfp, tbsp, A_pl_tb1 = 0, B_pl_tb1 = 0, A_pl_tb2 = 0, B_pl_tb2 = 0, lastIdfromUT1Set;

    private boolean SET_3_KTB_1_SET_a_DONE = false;
    private boolean SET_3_KTB_2_SET_DONE = false;
    private boolean SET_3_KTB_1_SET_b_DONE = false;
    private boolean SET_3_KTB_1_SET_DONE = false;
    private boolean SET_3_KTB_1_SET_tb_DONE = false;
    private boolean SET_3_KTB_2_SET_tb_DONE = false;
    private boolean SET_3_KTB_1_Set = true;
    private boolean SET_3_KTB_2_Set = false;
    private boolean SET_3_KTB_ktb = false;
    private boolean SET_3_KTB_KTB_DONE = false;
    private boolean SET_3_KTB_MATCH_DONE = false;
    private boolean SET_3_KTB_KTB_START = false;
    private boolean tieBreakDone_3SetKTB = false;
    private boolean tb1pl_3SetKTB = false;
    private boolean tb2pl_3SetKTB = false;
    private boolean tb_3SetKTB = false;

    private boolean stopMatchIsEnabled3SetKTB = true;

    AlertDialog.Builder ad3KTBSet;
    Context context;
    private String Set3KTBStatusMatch = "Paused";

    private TennisHelper mDbHelper3SetKTB;

    int goalsCounter3SetKTB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aa_activity_set3_ktb);

        goalsCounter3SetKTB = 0;

        if (getSupportActionBar() != null) {

            getSupportActionBar().setTitle(getString(R.string.table_score));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mDbHelper3SetKTB = new TennisHelper(this);
        context = AA_Set3KTBActivity.this;

        TextView tableScore3SetKTB = (TextView) findViewById(R.id.addToTableScoreTV3SetKTB);
        tableScore3SetKTB.setText(R.string.three_sets_KTB);

        playerFirst3SetKTB = (TextView) findViewById(R.id.playerFirstTV3SetKTB);
        playerSecond3SetKTB = (TextView) findViewById(R.id.playerSecondTV3SetKTB);

        TextView nameFirstPlayer = (TextView) findViewById(R.id.nameFirstPlayerTV);
        TextView nameSecondPlayer = (TextView) findViewById(R.id.nameSecondPlayerTV);

        pointFP3SetKTB = (TextView) findViewById(R.id.pointFPTV3SetKTB);
        pointSP3SetKTB = (TextView) findViewById(R.id.pointSPTV3SetKTB);

        pointFPFirstSet3SetKTB = (TextView) findViewById(R.id.pointFPFirstSet3SetKTBTV);
        pointSPFirstSet3SetKTB = (TextView) findViewById(R.id.pointSPFirstSet3SetKTBTV);

        pointFPSecondSet3SetKTB = (TextView) findViewById(R.id.pointFPSecondSet3SetKTBTV);
        pointSPSecondSet3SetKTB = (TextView) findViewById(R.id.pointSPSecondSet3SetKTBTV);

        pointFPKTB3SetKTB = (TextView) findViewById(R.id.pointFPKTB3SetKTBTV);
        pointSPKTB3SetKTB = (TextView) findViewById(R.id.pointSPKTB3SetKTBTV);


        if (getIntent().getExtras().getBoolean("isNew")) {

            pl1_1_3setKTB = getIntent().getExtras().getString("user");
            pl1_2_3setKTB = getIntent().getExtras().getString("userSur");
            pl2_1_3setKTB = getIntent().getExtras().getString("der");
            pl2_2_3setKTB = getIntent().getExtras().getString("derSur");

            pointSP3SetKTB.setText(String.valueOf(tp[k3sk]));
            pointFP3SetKTB.setText(String.valueOf(tp[i3sk]));

            pointFPFirstSet3SetKTB.setText(String.valueOf(fpls3KTB));
            pointSPFirstSet3SetKTB.setText(String.valueOf(spls3KTB));

            pointFPSecondSet3SetKTB.setText(String.valueOf("0"));
            pointSPSecondSet3SetKTB.setText(String.valueOf("0"));

            pointFPKTB3SetKTB.setText(String.valueOf("0"));
            pointSPKTB3SetKTB.setText(String.valueOf("0"));


        } else {

            pl1_1_3setKTB = mDbHelper3SetKTB.getNameById("name_A_1", selId);
            pl1_2_3setKTB = mDbHelper3SetKTB.getNameById("name_A_2", selId);
            pl2_1_3setKTB = mDbHelper3SetKTB.getNameById("name_B_1", selId);
            pl2_2_3setKTB = mDbHelper3SetKTB.getNameById("name_B_2", selId);

            if (mDbHelper3SetKTB.getNumById("ktbfpsST", selId) == -2) {
                pointFP3SetKTB.setText(adv_3SetKTB);
            } else {
                pointFP3SetKTB.setText(String.valueOf(mDbHelper3SetKTB.getNumById("ktbfpsST", selId)));
            }

            if (mDbHelper3SetKTB.getNumById("ktbspsST", selId) == -2) {
                pointSP3SetKTB.setText(adv_3SetKTB);
            } else {
                pointSP3SetKTB.setText(String.valueOf(mDbHelper3SetKTB.getNumById("ktbspsST", selId)));
            }

            if (mDbHelper3SetKTB.getNumById("set1aST_tb", selId) > 0 || mDbHelper3SetKTB.getNumById("set1bST_tb", selId) > 0) {

                pointFPFirstSet3SetKTB.setText(String.valueOf(mDbHelper3SetKTB.getNumById("set1aST", selId)));
                pointSPFirstSet3SetKTB.setText(String.valueOf(mDbHelper3SetKTB.getNumById("set1bST", selId)));

                convertUndo(mDbHelper3SetKTB.getNumById("set1aST_tb", selId), mDbHelper3SetKTB.getNumById("set1bST_tb", selId), pointFPFirstSet3SetKTB, pointSPFirstSet3SetKTB);
            } else {

                pointFPFirstSet3SetKTB.setText(String.valueOf(mDbHelper3SetKTB.getNumById("set1aST", selId)));
                pointSPFirstSet3SetKTB.setText(String.valueOf(mDbHelper3SetKTB.getNumById("set1bST", selId)));

            }

            if (mDbHelper3SetKTB.getNumById("set2aST_tb", selId) > 0 || mDbHelper3SetKTB.getNumById("set2bST_tb", selId) > 0) {
                pointFPSecondSet3SetKTB.setText(String.valueOf(mDbHelper3SetKTB.getNumById("set2aST", selId)));
                pointSPSecondSet3SetKTB.setText(String.valueOf(mDbHelper3SetKTB.getNumById("set2bST", selId)));
                convertUndo(mDbHelper3SetKTB.getNumById("set2aST_tb", selId), mDbHelper3SetKTB.getNumById("set2bST_tb", selId), pointFPSecondSet3SetKTB, pointSPSecondSet3SetKTB);

            } else {
                pointFPSecondSet3SetKTB.setText(String.valueOf(mDbHelper3SetKTB.getNumById("set2aST", selId)));
                pointSPSecondSet3SetKTB.setText(String.valueOf(mDbHelper3SetKTB.getNumById("set2bST", selId)));

            }
            if ((mDbHelper3SetKTB.getNumById("set2aST", selId) == 6 || mDbHelper3SetKTB.getNumById("set2aST", selId) == 7)
                    && mDbHelper3SetKTB.getNumById("set2bST", selId) <= (mDbHelper3SetKTB.getNumById("set2aST", selId) - 2)) {
                pointFPSecondSet3SetKTB.setBackgroundResource(R.drawable.frame_winner);
            }
            if ((mDbHelper3SetKTB.getNumById("set2bST", selId) == 6 || mDbHelper3SetKTB.getNumById("set2bST", selId) == 7)
                    && mDbHelper3SetKTB.getNumById("set2aST", selId) <= (mDbHelper3SetKTB.getNumById("set2bST", selId) - 2)) {
                pointSPSecondSet3SetKTB.setBackgroundResource(R.drawable.frame_winner);
            }


            pointFPKTB3SetKTB.setText(String.valueOf(mDbHelper3SetKTB.getNumById("set3aST", selId)));
            pointSPKTB3SetKTB.setText(String.valueOf(mDbHelper3SetKTB.getNumById("set3bST", selId)));


            SET_3_KTB_1_SET_a_DONE = mDbHelper3SetKTB.getNumById("_1_set_a_done", selId) > 0;//
            AA_Set3Activity.setWinnerFrame(SET_3_KTB_1_SET_a_DONE, pointFPFirstSet3SetKTB);

            SET_3_KTB_2_SET_DONE = mDbHelper3SetKTB.getNumById("_2_set_b_done", selId) > 0;//

            SET_3_KTB_1_SET_b_DONE = mDbHelper3SetKTB.getNumById("_1_set_b_done", selId) > 0;//
            AA_Set3Activity.setWinnerFrame(SET_3_KTB_1_SET_b_DONE, pointSPFirstSet3SetKTB);

            SET_3_KTB_1_SET_DONE = mDbHelper3SetKTB.getNumById("_2_set_a_done", selId) > 0;//

            SET_3_KTB_1_SET_tb_DONE = mDbHelper3SetKTB.getNumById("_1_set_tb_done", selId) > 0;//

            SET_3_KTB_2_SET_tb_DONE = mDbHelper3SetKTB.getNumById("_2_set_tb_done", selId) > 0;//

            SET_3_KTB_1_Set = mDbHelper3SetKTB.getNumById("_1_set", selId) > 0;//

            SET_3_KTB_2_Set = mDbHelper3SetKTB.getNumById("_2_set", selId) > 0;//

            SET_3_KTB_ktb = mDbHelper3SetKTB.getNumById("_3_set", selId) > 0;//

            SET_3_KTB_KTB_DONE = mDbHelper3SetKTB.getNumById("_3_set_tb_done", selId) > 0;//

            SET_3_KTB_MATCH_DONE = mDbHelper3SetKTB.getNumById("match_done", selId) > 0;//

            SET_3_KTB_KTB_START = mDbHelper3SetKTB.getNumById("_5_set_tb_done", selId) > 0;//

            tieBreakDone_3SetKTB = mDbHelper3SetKTB.getNumById("tieBreakDone", selId) > 0;//

            tb1pl_3SetKTB = mDbHelper3SetKTB.getNumById("tb1pl", selId) > 0;//

            tb2pl_3SetKTB = mDbHelper3SetKTB.getNumById("tb2pl", selId) > 0;//

            tb_3SetKTB = mDbHelper3SetKTB.getNumById("tb", selId) > 0;//

            stopMatchIsEnabled3SetKTB = mDbHelper3SetKTB.getNumById("stopMatchIsEnabled", selId) > 0;//

            A_pl_tb1 = mDbHelper3SetKTB.getNumById("set1aST_tb", selId);
            B_pl_tb1 = mDbHelper3SetKTB.getNumById("set1bST_tb", selId);
            A_pl_tb2 = mDbHelper3SetKTB.getNumById("set2aST_tb", selId);
            B_pl_tb2 = mDbHelper3SetKTB.getNumById("set2bST_tb", selId);


            mDbHelper3SetKTB.insert3SetKTBScore(mDbHelper3SetKTB.getNumById("ktbfpsST", selId), mDbHelper3SetKTB.getNumById("ktbspsST", selId),
                    mDbHelper3SetKTB.getNumById("set1aST", selId), mDbHelper3SetKTB.getNumById("set1bST", selId), mDbHelper3SetKTB.getNumById("set2aST", selId),
                    mDbHelper3SetKTB.getNumById("set2bST", selId), mDbHelper3SetKTB.getNumById("set3aST", selId), mDbHelper3SetKTB.getNumById("set3bST", selId));

            mDbHelper3SetKTB.insertPlayers(pl1_1_3setKTB, pl1_2_3setKTB, pl2_1_3setKTB, pl2_2_3setKTB);

        }


        setNames(playerFirst3SetKTB, pl1_1_3setKTB, pl1_2_3setKTB);
        setNames(playerSecond3SetKTB, pl2_1_3setKTB, pl2_2_3setKTB);
        AA_KingTieBreakActivity.setAddNames(nameFirstPlayer, pl1_1_3setKTB, pl1_2_3setKTB);
        AA_KingTieBreakActivity.setAddNames(nameSecondPlayer, pl2_1_3setKTB, pl2_2_3setKTB);


        spinner1p3sKTB = (Spinner) findViewById(R.id.spinner1p3sKTBTV);
        spinner2p3sKTB = (Spinner) findViewById(R.id.spinner2p3sKTBTV);

        typeGoals[0] = getResources().getString(R.string.select_gt_for_spinner);
        typeGoals[1] = getResources().getString(R.string.Winner);
        typeGoals[2] = getResources().getString(R.string.Ace);
        typeGoals[3] = getResources().getString(R.string.Unenforced_error_for_spinner);
        typeGoals[4] = getResources().getString(R.string.Double_error_for_spinner);

        AA_KingTieBreakActivity.setadapter(typeGoals, spinner1p3sKTB, this);
        AA_KingTieBreakActivity.setadapter(typeGoals, spinner2p3sKTB, this);

        goalFP3SetKTB = (Button) findViewById(R.id.goalFPButton3SetKTB);
        goalSP3SetKTB = (Button) findViewById(R.id.goalSPButton3SetKTB);

        undo_3SetKTB = (ImageButton) findViewById(R.id.undo3SetKTBButton);
        redo_3SetKTB = (ImageButton) findViewById(R.id.redo3SetKTBtButton);
        saveButton3SetKTB = (ImageButton) findViewById(R.id._saveButton3SetKTB);


        noEnabled(undo_3SetKTB, getDrawable(R.drawable.ic_undo_black_24dp_0_3));
        noEnabled(redo_3SetKTB, getDrawable(R.drawable.ic_redo_black_24dp_0_3));
        noEnabled(saveButton3SetKTB, getDrawable(R.drawable.ic_save_black_24dp_0_3));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.score_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if (!stopMatchIsEnabled3SetKTB) {
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

                final String na_1 = getString(R.string.Retired) + " " + playerFirst3SetKTB.getText().toString() + getString(R.string.loss);
                final String na_2 = getString(R.string.Retired) + " " + playerSecond3SetKTB.getText().toString() + getString(R.string.loss);
                final String na_3 = getString(R.string.Another_Match_is_paused);
                final String na_4 = getString(R.string.Another_Match_is_finished);

                final String[] reasons = {na_1, na_2, na_3, na_4};
                ad3KTBSet = new AlertDialog.Builder(this);
                ad3KTBSet.setTitle(getString(R.string.Choose_the_reason))
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


                                        ad3KTBSet = new AlertDialog.Builder(context);

                                        ad3KTBSet.setMessage(getString(R.string.Do_you_want));

                                        ad3KTBSet.setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener() {
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
                                        ad3KTBSet.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int arg1) {
                                                noSaveResultGoToMM();
                                                if (!getIntent().getExtras().getBoolean("isNew")) {
                                                    mDbHelper3SetKTB.putFreeRowInStatisticSaveTable(selId);
                                                }
                                            }
                                        });
                                        ad3KTBSet.setCancelable(true);
                                        ad3KTBSet.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                            public void onCancel(DialogInterface dialog) {
                                            }
                                        });
                                        ad3KTBSet.show();
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
                ad3KTBSet.show();
                break;

            case R.id.help_item:

                goToHelp();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {



            ad3KTBSet = new AlertDialog.Builder(context);
            ad3KTBSet.setMessage(getString(R.string.ad_alert_1));
            ad3KTBSet.setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int arg1) {

                    saveResultGoToMM();
                }
            });
            ad3KTBSet.setNeutralButton(getString(R.string.Continue_match),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int id) {
                            dialog.cancel();
                        }
                    });

            ad3KTBSet.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int arg1) {
                    noSaveResultGoToMM();
                    if (!getIntent().getExtras().getBoolean("isNew")) {
                        mDbHelper3SetKTB.putFreeRowInStatisticSaveTable(selId);
                    }
                }
            });
            ad3KTBSet.setCancelable(true);
            ad3KTBSet.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialog) {
                }
            });
            ad3KTBSet.show();
    }

    public void firstPlayerGoal3SetKTB(View v) {

        switch (AA_KingTieBreakActivity.getSpinnerItem(spinner1p3sKTB)) {
            case 0: {
                Toast.makeText(getBaseContext(), getString(R.string.select_gt), Toast.LENGTH_SHORT).show();
                break;
            }
            case 1: {
                deleteRow3SetKTB();
                noEnabled(redo_3SetKTB, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper3SetKTB.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(7) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper3SetKTB.getLastId() - 7)});

                setFirstPlScore1Set();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper3SetKTB.getLastId() - 7);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper3SetKTB.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet3KTBAllplScore(pointFP3SetKTB, pointSP3SetKTB, pointFPFirstSet3SetKTB, pointSPFirstSet3SetKTB, pointFPSecondSet3SetKTB, pointSPSecondSet3SetKTB, pointFPKTB3SetKTB, pointSPKTB3SetKTB);
                goalsCounter3SetKTB++;
                break;
            }

            case 2: {
                deleteRow3SetKTB();
                noEnabled(redo_3SetKTB, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper3SetKTB.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(6) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper3SetKTB.getLastId() - 6)});

                setFirstPlScore1Set();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper3SetKTB.getLastId() - 6);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper3SetKTB.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet3KTBAllplScore(pointFP3SetKTB, pointSP3SetKTB, pointFPFirstSet3SetKTB, pointSPFirstSet3SetKTB, pointFPSecondSet3SetKTB, pointSPSecondSet3SetKTB, pointFPKTB3SetKTB, pointSPKTB3SetKTB);
                goalsCounter3SetKTB++;
                break;
            }

            case 3: {
                deleteRow3SetKTB();
                noEnabled(redo_3SetKTB, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper3SetKTB.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(1) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper3SetKTB.getLastId() - 1)});

                setFirstPlScore1Set();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper3SetKTB.getLastId() - 1);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper3SetKTB.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet3KTBAllplScore(pointFP3SetKTB, pointSP3SetKTB, pointFPFirstSet3SetKTB, pointSPFirstSet3SetKTB, pointFPSecondSet3SetKTB, pointSPSecondSet3SetKTB, pointFPKTB3SetKTB, pointSPKTB3SetKTB);
                goalsCounter3SetKTB++;
                break;
            }

            case 4: {
                deleteRow3SetKTB();
                noEnabled(redo_3SetKTB, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper3SetKTB.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(0) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper3SetKTB.getLastId())});

                setFirstPlScore1Set();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper3SetKTB.getLastId());
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper3SetKTB.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet3KTBAllplScore(pointFP3SetKTB, pointSP3SetKTB, pointFPFirstSet3SetKTB, pointSPFirstSet3SetKTB, pointFPSecondSet3SetKTB, pointSPSecondSet3SetKTB, pointFPKTB3SetKTB, pointSPKTB3SetKTB);
                goalsCounter3SetKTB++;
                break;
            }
        }

        spinner1p3sKTB.setSelection(0);
        spinner2p3sKTB.setSelection(0);
    }

    public void secondPlayerGoal3SetKTB(View v) {


        switch (AA_KingTieBreakActivity.getSpinnerItem(spinner2p3sKTB)) {
            case 0: {
                Toast.makeText(getBaseContext(), getString(R.string.select_gt), Toast.LENGTH_SHORT).show();
                break;
            }
            case 1: {
                deleteRow3SetKTB();
                noEnabled(redo_3SetKTB, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper3SetKTB.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(3) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper3SetKTB.getLastId() - 3)});

                setSecondPlScore1Set();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper3SetKTB.getLastId() - 3);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper3SetKTB.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet3KTBAllplScore(pointFP3SetKTB, pointSP3SetKTB, pointFPFirstSet3SetKTB, pointSPFirstSet3SetKTB, pointFPSecondSet3SetKTB, pointSPSecondSet3SetKTB, pointFPKTB3SetKTB, pointSPKTB3SetKTB);
                goalsCounter3SetKTB++;
                break;
            }

            case 2: {
                deleteRow3SetKTB();
                noEnabled(redo_3SetKTB, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper3SetKTB.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(2) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper3SetKTB.getLastId() - 2)});

                setSecondPlScore1Set();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper3SetKTB.getLastId() - 2);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper3SetKTB.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet3KTBAllplScore(pointFP3SetKTB, pointSP3SetKTB, pointFPFirstSet3SetKTB, pointSPFirstSet3SetKTB, pointFPSecondSet3SetKTB, pointSPSecondSet3SetKTB, pointFPKTB3SetKTB, pointSPKTB3SetKTB);
                goalsCounter3SetKTB++;
                break;
            }

            case 3: {
                deleteRow3SetKTB();
                noEnabled(redo_3SetKTB, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper3SetKTB.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(5) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper3SetKTB.getLastId() - 5)});

                setSecondPlScore1Set();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper3SetKTB.getLastId() - 5);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper3SetKTB.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet3KTBAllplScore(pointFP3SetKTB, pointSP3SetKTB, pointFPFirstSet3SetKTB, pointSPFirstSet3SetKTB, pointFPSecondSet3SetKTB, pointSPSecondSet3SetKTB, pointFPKTB3SetKTB, pointSPKTB3SetKTB);
                goalsCounter3SetKTB++;
                break;
            }

            case 4: {
                deleteRow3SetKTB();
                noEnabled(redo_3SetKTB, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper3SetKTB.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(4) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper3SetKTB.getLastId() - 4)});

                setSecondPlScore1Set();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper3SetKTB.getLastId() - 4);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper3SetKTB.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet3KTBAllplScore(pointFP3SetKTB, pointSP3SetKTB, pointFPFirstSet3SetKTB, pointSPFirstSet3SetKTB, pointFPSecondSet3SetKTB, pointSPSecondSet3SetKTB, pointFPKTB3SetKTB, pointSPKTB3SetKTB);
                goalsCounter3SetKTB++;
                break;
            }
        }

        spinner1p3sKTB.setSelection(0);
        spinner2p3sKTB.setSelection(0);
    }


    public void setFirstPlScore1Set() {

        tieBreakDone_3SetKTB = false;

        tb1pl_3SetKTB = true;

        if (SET_3_KTB_KTB_START) {

            KTB_3SetKTB(tb1pl_3SetKTB, tb2pl_3SetKTB);
            tb1pl_3SetKTB = false;

        } else if (tb_3SetKTB) {

            tieBreak(tb1pl_3SetKTB, tb2pl_3SetKTB);
        } else {

            i3sk = getKorI(pointFP3SetKTB);
            k3sk = getKorI(pointSP3SetKTB);

            if (SET_3_KTB_1_SET_a_DONE | SET_3_KTB_1_SET_b_DONE) {

                fpls3KTB = Integer.valueOf(pointFPSecondSet3SetKTB.getText().toString());
                spls3KTB = Integer.valueOf(pointSPSecondSet3SetKTB.getText().toString());
            } else {
                fpls3KTB = Integer.valueOf(pointFPFirstSet3SetKTB.getText().toString());
                spls3KTB = Integer.valueOf(pointSPFirstSet3SetKTB.getText().toString());
            }

            sp1_set3KTB = pointFP3SetKTB.getText().toString();
            switch (sp1_set3KTB) {
                case "0":
                    i3sk = 1;
                    break;
                case "15":
                    i3sk = 2;
                    break;
                case "30":
                    i3sk = 3;
                    break;
                case "40":
                    if (i3sk > k3sk) {
                        i3sk = k3sk = 0;
                        fpls3KTB++;
                    } else if (i3sk == k3sk) {
                        i3sk = 4;
                        break;
                    } else {
                        i3sk = k3sk = 3;
                    }
                    break;
                case "Ad":
                    i3sk = k3sk = 0;
                    fpls3KTB++;
                    break;
            }
            if (i3sk == 4) {
                pointFP3SetKTB.setText(adv_3SetKTB);
                pointSP3SetKTB.setText(String.valueOf(tp[k3sk]));
                tb1pl_3SetKTB = false;

            } else {

                pointFP3SetKTB.setText(String.valueOf(tp[i3sk]));
                pointSP3SetKTB.setText(String.valueOf(tp[k3sk]));

                if (SET_3_KTB_1_SET_a_DONE | SET_3_KTB_1_SET_b_DONE) {

                    pointFPSecondSet3SetKTB.setText(String.valueOf(fpls3KTB));
                } else {
                    pointFPFirstSet3SetKTB.setText(String.valueOf(fpls3KTB));
                }
                tb1pl_3SetKTB = false;
            }
            checkSetNumber(fpls3KTB, spls3KTB);
        }
    }

    public void setSecondPlScore1Set() {

        tieBreakDone_3SetKTB = false;

        tb2pl_3SetKTB = true;

        if (SET_3_KTB_KTB_START) {

            KTB_3SetKTB(tb1pl_3SetKTB, tb2pl_3SetKTB);

            tb2pl_3SetKTB = false;
        } else if (tb_3SetKTB) {

            tieBreak(tb1pl_3SetKTB, tb2pl_3SetKTB);

        } else {

            i3sk = getKorI(pointFP3SetKTB);
            k3sk = getKorI(pointSP3SetKTB);

            if (SET_3_KTB_1_SET_a_DONE | SET_3_KTB_1_SET_b_DONE) {
                fpls3KTB = Integer.valueOf(pointFPSecondSet3SetKTB.getText().toString());
                spls3KTB = Integer.valueOf(pointSPSecondSet3SetKTB.getText().toString());
            } else {
                fpls3KTB = Integer.valueOf(pointFPFirstSet3SetKTB.getText().toString());
                spls3KTB = Integer.valueOf(pointSPFirstSet3SetKTB.getText().toString());
            }

            sp2set3KTB = pointSP3SetKTB.getText().toString();
            switch (sp2set3KTB) {
                case "0":
                    k3sk = 1;
                    break;
                case "15":
                    k3sk = 2;
                    break;
                case "30":
                    k3sk = 3;
                    break;
                case "40":
                    if (k3sk > i3sk) {
                        k3sk = i3sk = 0;
                        spls3KTB++;
                    } else if (k3sk == i3sk) {
                        k3sk = 4;
                    } else {
                        i3sk = k3sk = 3;
                    }
                    break;
                case "Ad":
                    i3sk = k3sk = 0;
                    spls3KTB++;
                    break;
            }
            if (k3sk == 4) {
                pointSP3SetKTB.setText(adv_3SetKTB);
                pointFP3SetKTB.setText(String.valueOf(tp[i3sk]));
                tb2pl_3SetKTB = false;
            } else {
                pointSP3SetKTB.setText(String.valueOf(tp[k3sk]));
                pointFP3SetKTB.setText(String.valueOf(tp[i3sk]));
                if (SET_3_KTB_1_SET_a_DONE | SET_3_KTB_1_SET_b_DONE) {
                    pointSPSecondSet3SetKTB.setText(String.valueOf(spls3KTB));
                } else {
                    pointSPFirstSet3SetKTB.setText(String.valueOf(spls3KTB));
                }
                tb2pl_3SetKTB = false;
            }
            checkSetNumber(fpls3KTB, spls3KTB);
        }
    }


    public int curNumberOfPoints(int p) {
        SQLiteDatabase db = mDbHelper3SetKTB.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT number FROM statistic  WHERE _ID = " + Integer.toString(mDbHelper3SetKTB.getLastId() - p), new String[]{});
        cursor.moveToFirst();
        curNum3SetKTB = (int) cursor.getLong(0);
        cursor.close();
        return curNum3SetKTB;
    }


    public void checkSetNumber(int y1, int y2) {

        if (SET_3_KTB_1_SET_a_DONE) {

            if (y1 == 6 & y2 <= y1 - 2 | y1 == 7 & y2 <= y1 - 2) {

                SET_3_KTB_MATCH_DONE = true;
                SET_3_KTB_2_SET_DONE = true;

                Toast.makeText(this, pl1_1_3setKTB + " " + pl1_2_3setKTB + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();

                AA_KingTieBreakActivity.setButNotEnabled(goalFP3SetKTB, goalSP3SetKTB, spinner1p3sKTB, spinner2p3sKTB);
                beEnabled(saveButton3SetKTB, getDrawable(R.drawable.ic_save_black_24dp));
                playerFirst3SetKTB.setBackgroundResource(R.drawable.frame_winner);
                pointFPSecondSet3SetKTB.setBackgroundResource(R.drawable.frame_winner);
                Set3KTBStatusMatch = "firstPlayer";
                stopMatchIsEnabled3SetKTB = false;

            } else if (y2 == 6 & y1 <= y2 - 2 | y2 == 7 & y1 <= y2 - 2) {

                Toast.makeText(getBaseContext(), getString(R.string.KTB_starts), Toast.LENGTH_SHORT).show();
                pointSPSecondSet3SetKTB.setBackgroundResource(R.drawable.frame_winner);

                SET_3_KTB_2_Set = false;
                SET_3_KTB_ktb = true;

                SET_3_KTB_2_SET_DONE = true;
                SET_3_KTB_KTB_START = true;

            } else if (y2 == 6 & y1 == 6) {

                Toast.makeText(getBaseContext(), getString(R.string.Tie_break_starts), Toast.LENGTH_SHORT).show();

                tb1pl_3SetKTB = false;
                tb2pl_3SetKTB = false;

                tbfp = 0;
                tbsp = 0;

                pointFP3SetKTB.setText(String.valueOf(tbfp));
                pointSP3SetKTB.setText(String.valueOf(tbsp));

                tb_3SetKTB = true;
            }

        } else if (SET_3_KTB_1_SET_b_DONE) {

            if (y1 == 6 & y2 <= y1 - 2 | y1 == 7 & y2 <= y1 - 2) {

                Toast.makeText(getBaseContext(), getString(R.string.KTB_starts), Toast.LENGTH_LONG).show();
                pointFPSecondSet3SetKTB.setBackgroundResource(R.drawable.frame_winner);

                SET_3_KTB_2_SET_DONE = true;
                SET_3_KTB_KTB_START = true;

                SET_3_KTB_ktb = true;
                SET_3_KTB_2_Set = false;

            } else if (y2 == 6 & y1 <= y2 - 2 | y2 == 7 & y1 <= y2 - 2) {

                SET_3_KTB_MATCH_DONE = true;

                Toast.makeText(this, pl2_1_3setKTB + " " + pl2_2_3setKTB + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();

                AA_KingTieBreakActivity.setButNotEnabled(goalFP3SetKTB, goalSP3SetKTB, spinner1p3sKTB, spinner2p3sKTB);

                beEnabled(saveButton3SetKTB, getDrawable(R.drawable.ic_save_black_24dp));
                playerSecond3SetKTB.setBackgroundResource(R.drawable.frame_winner);
                pointSPSecondSet3SetKTB.setBackgroundResource(R.drawable.frame_winner);
                Set3KTBStatusMatch = "secondPlayer";
                stopMatchIsEnabled3SetKTB = false;


            } else if (y2 == 6 & y1 == 6) {

                Toast.makeText(getBaseContext(), getString(R.string.Tie_break_starts), Toast.LENGTH_SHORT).show();

                tb1pl_3SetKTB = false;
                tb2pl_3SetKTB = false;

                tbfp = 0;
                tbsp = 0;

                pointFP3SetKTB.setText(String.valueOf(tbfp));
                pointSP3SetKTB.setText(String.valueOf(tbsp));

                tb_3SetKTB = true;
            }
        } else {

            if (y1 == 6 & y2 <= y1 - 2 | y1 == 7 & y2 <= y1 - 2) {

                Toast.makeText(this, pl1_1_3setKTB + " " + pl1_2_3setKTB + " " + getString(R.string.wins_1_set), Toast.LENGTH_LONG).show();
                pointFPFirstSet3SetKTB.setBackgroundResource(R.drawable.frame_winner);

                SET_3_KTB_1_SET_a_DONE = true;
                SET_3_KTB_2_Set = true;
                SET_3_KTB_1_Set = false;
                SET_3_KTB_1_SET_DONE = true;

            } else if (y2 == 6 & y1 <= y2 - 2 | y2 == 7 & y1 <= y2 - 2) {

                Toast.makeText(this, pl2_1_3setKTB + " " + pl2_2_3setKTB + " " + getString(R.string.wins_1_set), Toast.LENGTH_LONG).show();
                pointSPFirstSet3SetKTB.setBackgroundResource(R.drawable.frame_winner);

                SET_3_KTB_1_SET_b_DONE = true;
                SET_3_KTB_2_Set = true;
                SET_3_KTB_1_Set = false;
                SET_3_KTB_1_SET_DONE = true;

            } else if (y2 == 6 & y1 == 6) {

                Toast.makeText(getBaseContext(), getString(R.string.Tie_break_starts), Toast.LENGTH_SHORT).show();

                tb1pl_3SetKTB = false;
                tb2pl_3SetKTB = false;

                tbfp = 0;
                tbsp = 0;

                pointFP3SetKTB.setText(String.valueOf(tbfp));
                pointSP3SetKTB.setText(String.valueOf(tbsp));

                tb_3SetKTB = true;
            }
        }
    }

    public int getKorI(TextView ki) {
        if (ki.getText().toString().equals("0")) {
            n2 = 0;
        } else if (ki.getText().toString().equals("15")) {
            n2 = 1;
        } else if (ki.getText().toString().equals("30")) {
            n2 = 2;
        } else if (ki.getText().toString().equals("40")) {
            n2 = 3;
        } else if (ki.getText().toString().equals("Ad")) {
            n2 = 4;
        }
        return n2;
    }

    public void tieBreak(boolean pla, boolean plb) {

        if (pla) {

            tbfp = Integer.valueOf(pointFP3SetKTB.getText().toString());
            tbsp = Integer.valueOf(pointSP3SetKTB.getText().toString());

            tbfp++;

            pointFP3SetKTB.setText(String.valueOf(tbfp));
            pointSP3SetKTB.setText(String.valueOf(tbsp));
            tb1pl_3SetKTB = false;

        } else if (plb) {

            tbfp = Integer.valueOf(pointFP3SetKTB.getText().toString());
            tbsp = Integer.valueOf(pointSP3SetKTB.getText().toString());

            tbsp++;

            pointFP3SetKTB.setText(String.valueOf(tbfp));
            pointSP3SetKTB.setText(String.valueOf(tbsp));
            tb2pl_3SetKTB = false;
        }
        checkTieBreakNumber(tbfp, tbsp);
    }

    public void checkTieBreakNumber(int x1, int x2) {

        if (x1 == 7 & x2 <= x1 - 2 | x1 > 7 & x2 <= x1 - 2) {

            if (SET_3_KTB_1_SET_a_DONE) {

                pointFPSecondSet3SetKTB.setText(String.valueOf(7));

                SET_3_KTB_MATCH_DONE = true;
                SET_3_KTB_2_SET_tb_DONE = true;
                tieBreakDone_3SetKTB = true;
                A_pl_tb2 = x1;
                B_pl_tb2 = x2;
                convert(x1, x2, pointFPSecondSet3SetKTB, pointSPSecondSet3SetKTB);


                Toast.makeText(this, pl1_1_3setKTB + " " + pl1_2_3setKTB + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();


                AA_KingTieBreakActivity.setButNotEnabled(goalFP3SetKTB, goalSP3SetKTB, spinner1p3sKTB, spinner2p3sKTB);

                beEnabled(saveButton3SetKTB, getDrawable(R.drawable.ic_save_black_24dp));
                playerFirst3SetKTB.setBackgroundResource(R.drawable.frame_winner);
                pointFPSecondSet3SetKTB.setBackgroundResource(R.drawable.frame_winner);
                Set3KTBStatusMatch = "firstPlayer";
                stopMatchIsEnabled3SetKTB = false;


            } else if (SET_3_KTB_1_SET_b_DONE) {

                pointFPSecondSet3SetKTB.setText(String.valueOf(7));
                SET_3_KTB_2_SET_DONE = true;
                SET_3_KTB_2_SET_tb_DONE = true;
                SET_3_KTB_KTB_START = true;
                SET_3_KTB_2_Set = false;
                SET_3_KTB_ktb = true;
                A_pl_tb2 = x1;
                B_pl_tb2 = x2;
                convert(x1, x2, pointFPSecondSet3SetKTB, pointSPSecondSet3SetKTB);

                Toast.makeText(this, pl1_1_3setKTB + " " + pl1_2_3setKTB + " " + getString(R.string.wins_2_set), Toast.LENGTH_LONG).show();
                pointFPSecondSet3SetKTB.setBackgroundResource(R.drawable.frame_winner);

            } else {

                pointFPFirstSet3SetKTB.setText(String.valueOf(7));

                SET_3_KTB_1_SET_a_DONE = true;
                SET_3_KTB_1_SET_tb_DONE = true;
                SET_3_KTB_2_Set = true;
                SET_3_KTB_1_Set = false;
                A_pl_tb1 = x1;
                B_pl_tb1 = x2;

                convert(x1, x2, pointFPFirstSet3SetKTB, pointSPFirstSet3SetKTB);

                Toast.makeText(this, pl1_1_3setKTB + " " + pl1_2_3setKTB + " " + getString(R.string.wins_1_set), Toast.LENGTH_LONG).show();
                pointFPFirstSet3SetKTB.setBackgroundResource(R.drawable.frame_winner);
            }

        } else if (x2 == 7 & x1 <= x2 - 2 | x2 > 7 & x1 <= x2 - 2) {

            if (SET_3_KTB_1_SET_b_DONE) {

                pointSPSecondSet3SetKTB.setText(String.valueOf(7));

                SET_3_KTB_MATCH_DONE = true;
                SET_3_KTB_2_SET_tb_DONE = true;
                A_pl_tb2 = x1;
                B_pl_tb2 = x2;
                convert(x1, x2, pointFPSecondSet3SetKTB, pointSPSecondSet3SetKTB);

                Toast.makeText(this, pl2_1_3setKTB + " " + pl2_2_3setKTB + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();

                AA_KingTieBreakActivity.setButNotEnabled(goalFP3SetKTB, goalSP3SetKTB, spinner1p3sKTB, spinner2p3sKTB);

                playerSecond3SetKTB.setBackgroundResource(R.drawable.frame_winner);
                pointSPSecondSet3SetKTB.setBackgroundResource(R.drawable.frame_winner);
                Set3KTBStatusMatch = "secondPlayer";
                stopMatchIsEnabled3SetKTB = false;

                beEnabled(saveButton3SetKTB, getDrawable(R.drawable.ic_save_black_24dp));


            } else if (SET_3_KTB_1_SET_a_DONE) {

                pointSPSecondSet3SetKTB.setText(String.valueOf(7));
                SET_3_KTB_2_SET_DONE = true;
                SET_3_KTB_2_SET_tb_DONE = true;
                SET_3_KTB_2_Set = false;
                SET_3_KTB_ktb = true;
                SET_3_KTB_KTB_START = true;
                A_pl_tb2 = x1;
                B_pl_tb2 = x2;
                convert(x1, x2, pointFPSecondSet3SetKTB, pointSPSecondSet3SetKTB);

                Toast.makeText(this, pl2_1_3setKTB + " " + pl2_2_3setKTB + " " + getString(R.string.wins_2_set), Toast.LENGTH_LONG).show();
                pointSPSecondSet3SetKTB.setBackgroundResource(R.drawable.frame_winner);

            } else {

                pointSPFirstSet3SetKTB.setText(String.valueOf(7));
                A_pl_tb1 = x1;
                B_pl_tb1 = x2;
                SET_3_KTB_1_Set = false;
                SET_3_KTB_2_Set = true;
                SET_3_KTB_1_SET_b_DONE = true;
                SET_3_KTB_1_SET_tb_DONE = true;
                convert(x1, x2, pointFPFirstSet3SetKTB, pointSPFirstSet3SetKTB);

                Toast.makeText(this, pl2_1_3setKTB + " " + pl2_2_3setKTB + " " + getString(R.string.wins_1_set), Toast.LENGTH_LONG).show();
                pointSPFirstSet3SetKTB.setBackgroundResource(R.drawable.frame_winner);
            }
        }
    }

    public void convert(int mp, int yu, TextView s1, TextView s2) {

        for (int i = 0; i < String.valueOf(mp).length(); i++) {
            int k = String.valueOf(mp).length() - i;
            str = degreeDigits[Character.getNumericValue(String.valueOf(mp).charAt(k - 1))] + str;
        }

        for (int kol = 0; kol < String.valueOf(yu).length(); kol++) {
            int kik = String.valueOf(yu).length() - kol;
            str1s = degreeDigits[Character.getNumericValue(String.valueOf(yu).charAt(kik - 1))] + str1s;
        }
        s1.setText(s1.getText() + str);
        s2.setText(s2.getText() + str1s);

        tieBreakDone_3SetKTB = true;
        tb_3SetKTB = false;

        pointFP3SetKTB.setText(String.valueOf(0));
        pointSP3SetKTB.setText(String.valueOf(0));

        str = "";
        str1s = "";

        fpls3KTB = 0;
        spls3KTB = 0;
        tbfp = 0;
        tbsp = 0;
    }

    public void convertUndo(int mp1, int yu1, TextView s11, TextView s21) {

        for (int i = 0; i < String.valueOf(mp1).length(); i++) {
            int k = String.valueOf(mp1).length() - i;
            str = degreeDigits[Character.getNumericValue(String.valueOf(mp1).charAt(k - 1))] + str;
        }

        for (int kol = 0; kol < String.valueOf(yu1).length(); kol++) {
            int kik = String.valueOf(yu1).length() - kol;
            str1s = degreeDigits[Character.getNumericValue(String.valueOf(yu1).charAt(kik - 1))] + str1s;
        }

        if (s11.getText().equals("7")) {

            s11.setBackgroundResource(R.drawable.frame_winner);
        } else {
            s21.setBackgroundResource(R.drawable.frame_winner);
        }
        s11.setText(s11.getText() + str);
        s21.setText(s21.getText() + str1s);


        str = "";
        str1s = "";
    }

    public void KTB_3SetKTB(Boolean g1, Boolean g2) {

        tbfp = Integer.valueOf(pointFP3SetKTB.getText().toString());
        tbsp = Integer.valueOf(pointSP3SetKTB.getText().toString());

        if (g1) {
            ++tbfp;
            pointFP3SetKTB.setText(String.valueOf(tbfp));

            if (tbfp == 10 & tbsp <= tbfp - 2 | tbfp > 10 & tbsp <= tbfp - 2) {

                pointFPKTB3SetKTB.setText(String.valueOf(tbfp));
                pointSPKTB3SetKTB.setText(String.valueOf(tbsp));
                SET_3_KTB_MATCH_DONE = true;
                SET_3_KTB_KTB_DONE = true;
                tbfp = 0;
                tbsp = 0;
                pointFP3SetKTB.setText(String.valueOf(tbfp));
                pointSP3SetKTB.setText(String.valueOf(tbsp));


                Toast.makeText(this, pl1_1_3setKTB + " " + pl1_2_3setKTB + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();

                AA_KingTieBreakActivity.setButNotEnabled(goalFP3SetKTB, goalSP3SetKTB, spinner1p3sKTB, spinner2p3sKTB);


                playerFirst3SetKTB.setBackgroundResource(R.drawable.frame_winner);
                pointFPKTB3SetKTB.setBackgroundResource(R.drawable.frame_winner);

                beEnabled(saveButton3SetKTB, getDrawable(R.drawable.ic_save_black_24dp));
                Set3KTBStatusMatch = "firstPlayer";
                stopMatchIsEnabled3SetKTB = false;

            }
        } else if (g2) {

            ++tbsp;
            pointSP3SetKTB.setText(String.valueOf(tbsp));

            if (tbsp == 10 & tbfp <= tbsp - 2 | tbsp > 10 & tbfp <= tbsp - 2) {

                pointSPKTB3SetKTB.setText(String.valueOf(tbsp));
                pointFPKTB3SetKTB.setText(String.valueOf(tbfp));
                SET_3_KTB_MATCH_DONE = true;
                SET_3_KTB_KTB_DONE = true;
                tbfp = 0;
                tbsp = 0;
                pointFP3SetKTB.setText(String.valueOf(tbfp));
                pointSP3SetKTB.setText(String.valueOf(tbsp));

                Toast.makeText(this, pl1_1_3setKTB + " " + pl1_2_3setKTB + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                AA_KingTieBreakActivity.setButNotEnabled(goalFP3SetKTB, goalSP3SetKTB, spinner1p3sKTB, spinner2p3sKTB);

                playerSecond3SetKTB.setBackgroundResource(R.drawable.frame_winner);
                pointSPKTB3SetKTB.setBackgroundResource(R.drawable.frame_winner);
                beEnabled(saveButton3SetKTB, getDrawable(R.drawable.ic_save_black_24dp));
                Set3KTBStatusMatch = "secondPlayer";
                stopMatchIsEnabled3SetKTB = false;

            }
        }
    }

    public void intCurSet3KTBAllplScore(TextView p1s, TextView p2s, TextView p1s1s, TextView p2s1s, TextView p1s2s, TextView p2s2s, TextView p1ktb, TextView p2ktb) {

        SQLiteDatabase db = mDbHelper3SetKTB.getWritableDatabase();
        ContentValues values = new ContentValues();

        if (SET_3_KTB_MATCH_DONE) {
            if (SET_3_KTB_KTB_DONE) {
                String j7 = p1ktb.getText().toString();
                String j8 = p2ktb.getText().toString();
                values.put(Contract.UndoNumbers.COLUMN_SET3A, Integer.valueOf(j7));
                values.put(Contract.UndoNumbers.COLUMN_SET3B, Integer.valueOf(j8));
                if (SET_3_KTB_1_SET_tb_DONE & SET_3_KTB_2_SET_tb_DONE) {
                    if (A_pl_tb1 > B_pl_tb1) {
                        values.put(Contract.UndoNumbers.COLUMN_SET1A, 7);
                        values.put(Contract.UndoNumbers.COLUMN_SET1B, 6);
                    } else {
                        values.put(Contract.UndoNumbers.COLUMN_SET1A, 6);
                        values.put(Contract.UndoNumbers.COLUMN_SET1B, 7);
                    }
                    if (A_pl_tb2 > B_pl_tb2) {
                        values.put(Contract.UndoNumbers.COLUMN_SET2A, 7);
                        values.put(Contract.UndoNumbers.COLUMN_SET2B, 6);
                    } else {
                        values.put(Contract.UndoNumbers.COLUMN_SET2A, 6);
                        values.put(Contract.UndoNumbers.COLUMN_SET2B, 7);
                    }
                } else if (SET_3_KTB_1_SET_tb_DONE & !SET_3_KTB_2_SET_tb_DONE) {
                    String j5 = p1s2s.getText().toString();
                    String j6 = p2s2s.getText().toString();
                    values.put(Contract.UndoNumbers.COLUMN_SET2A, Integer.valueOf(j5));
                    values.put(Contract.UndoNumbers.COLUMN_SET2B, Integer.valueOf(j6));
                    if (A_pl_tb1 > B_pl_tb1) {
                        values.put(Contract.UndoNumbers.COLUMN_SET1A, 7);
                        values.put(Contract.UndoNumbers.COLUMN_SET1B, 6);
                    } else {
                        values.put(Contract.UndoNumbers.COLUMN_SET1A, 6);
                        values.put(Contract.UndoNumbers.COLUMN_SET1B, 7);
                    }
                } else if (!SET_3_KTB_1_SET_tb_DONE & SET_3_KTB_2_SET_tb_DONE) {
                    String j3 = p1s1s.getText().toString();
                    String j4 = p2s1s.getText().toString();
                    values.put(Contract.UndoNumbers.COLUMN_SET1A, Integer.valueOf(j3));
                    values.put(Contract.UndoNumbers.COLUMN_SET1B, Integer.valueOf(j4));
                    if (A_pl_tb2 > B_pl_tb2) {
                        values.put(Contract.UndoNumbers.COLUMN_SET2A, 7);
                        values.put(Contract.UndoNumbers.COLUMN_SET2B, 6);
                    } else {
                        values.put(Contract.UndoNumbers.COLUMN_SET2A, 6);
                        values.put(Contract.UndoNumbers.COLUMN_SET2B, 7);
                    }
                } else {
                    String j3 = p1s1s.getText().toString();
                    String j4 = p2s1s.getText().toString();
                    String j5 = p1s2s.getText().toString();
                    String j6 = p2s2s.getText().toString();
                    values.put(Contract.UndoNumbers.COLUMN_SET1A, Integer.valueOf(j3));
                    values.put(Contract.UndoNumbers.COLUMN_SET1B, Integer.valueOf(j4));
                    values.put(Contract.UndoNumbers.COLUMN_SET2A, Integer.valueOf(j5));
                    values.put(Contract.UndoNumbers.COLUMN_SET2B, Integer.valueOf(j6));
                }
            } else if (SET_3_KTB_1_SET_a_DONE) {
                if (SET_3_KTB_1_SET_tb_DONE) {
                    values.put(Contract.UndoNumbers.COLUMN_SET1A, 7);
                    values.put(Contract.UndoNumbers.COLUMN_SET1B, 6);
                    if (SET_3_KTB_2_SET_tb_DONE) {
                        values.put(Contract.UndoNumbers.COLUMN_SET2A, 7);
                        values.put(Contract.UndoNumbers.COLUMN_SET2B, 6);
                        String j1 = p1s.getText().toString();
                        String j2 = p2s.getText().toString();
                        values.put(Contract.UndoNumbers.COLUMN_KTBFPS, Integer.valueOf(j1));
                        values.put(Contract.UndoNumbers.COLUMN_KTBSPS, Integer.valueOf(j2));//B_pl_tb2 change j2
                    } else {
                        String j5 = p1s2s.getText().toString();
                        String j6 = p2s2s.getText().toString();
                        values.put(Contract.UndoNumbers.COLUMN_SET2A, Integer.valueOf(j5));
                        values.put(Contract.UndoNumbers.COLUMN_SET2B, Integer.valueOf(j6));
                        String j1 = p1s.getText().toString();
                        String j2 = p2s.getText().toString();
                        values.put(Contract.UndoNumbers.COLUMN_KTBFPS, Integer.valueOf(j1));
                        values.put(Contract.UndoNumbers.COLUMN_KTBSPS, Integer.valueOf(j2));
                    }
                } else if (!SET_3_KTB_1_SET_tb_DONE) {
                    String j3 = p1s1s.getText().toString();
                    String j4 = p2s1s.getText().toString();
                    values.put(Contract.UndoNumbers.COLUMN_SET1A, Integer.valueOf(j3));
                    values.put(Contract.UndoNumbers.COLUMN_SET1B, Integer.valueOf(j4));
                    if (SET_3_KTB_2_SET_tb_DONE) {
                        values.put(Contract.UndoNumbers.COLUMN_SET2A, 7);
                        values.put(Contract.UndoNumbers.COLUMN_SET2B, 6);
                        String j1 = p1s.getText().toString();
                        String j2 = p2s.getText().toString();
                        values.put(Contract.UndoNumbers.COLUMN_KTBFPS, Integer.valueOf(j1));
                        values.put(Contract.UndoNumbers.COLUMN_KTBSPS, Integer.valueOf(j2));//B_pl_tb2 change j2
                    } else {
                        String j5 = p1s2s.getText().toString();
                        String j6 = p2s2s.getText().toString();
                        values.put(Contract.UndoNumbers.COLUMN_SET2A, Integer.valueOf(j5));
                        values.put(Contract.UndoNumbers.COLUMN_SET2B, Integer.valueOf(j6));
                        String j1 = p1s.getText().toString();
                        String j2 = p2s.getText().toString();
                        values.put(Contract.UndoNumbers.COLUMN_KTBFPS, Integer.valueOf(j1));
                        values.put(Contract.UndoNumbers.COLUMN_KTBSPS, Integer.valueOf(j2));
                    }
                }
            } else {
                if (SET_3_KTB_1_SET_tb_DONE) {
                    values.put(Contract.UndoNumbers.COLUMN_SET1A, 6);
                    values.put(Contract.UndoNumbers.COLUMN_SET1B, 7);
                    if (SET_3_KTB_2_SET_tb_DONE) {
                        values.put(Contract.UndoNumbers.COLUMN_SET2A, 6);
                        values.put(Contract.UndoNumbers.COLUMN_SET2B, 7);
                        String j1 = p1s.getText().toString();
                        String j2 = p2s.getText().toString();
                        values.put(Contract.UndoNumbers.COLUMN_KTBFPS, Integer.valueOf(j1));
                        values.put(Contract.UndoNumbers.COLUMN_KTBSPS, Integer.valueOf(j2));//B_pl_tb2 change j2
                    } else {
                        String j5 = p1s2s.getText().toString();
                        String j6 = p2s2s.getText().toString();
                        values.put(Contract.UndoNumbers.COLUMN_SET2A, Integer.valueOf(j5));
                        values.put(Contract.UndoNumbers.COLUMN_SET2B, Integer.valueOf(j6));
                        String j1 = p1s.getText().toString();
                        String j2 = p2s.getText().toString();
                        values.put(Contract.UndoNumbers.COLUMN_KTBFPS, Integer.valueOf(j1));
                        values.put(Contract.UndoNumbers.COLUMN_KTBSPS, Integer.valueOf(j2));
                    }
                } else if (!SET_3_KTB_1_SET_tb_DONE) {
                    String j3 = p1s1s.getText().toString();
                    String j4 = p2s1s.getText().toString();
                    values.put(Contract.UndoNumbers.COLUMN_SET1A, Integer.valueOf(j3));
                    values.put(Contract.UndoNumbers.COLUMN_SET1B, Integer.valueOf(j4));
                    if (SET_3_KTB_2_SET_tb_DONE) {
                        values.put(Contract.UndoNumbers.COLUMN_SET2A, 6);
                        values.put(Contract.UndoNumbers.COLUMN_SET2B, 7);
                        String j1 = p1s.getText().toString();
                        String j2 = p2s.getText().toString();
                        values.put(Contract.UndoNumbers.COLUMN_KTBFPS, Integer.valueOf(j1));
                        values.put(Contract.UndoNumbers.COLUMN_KTBSPS, Integer.valueOf(j2));//B_pl_tb2 change j2
                    } else {
                        String j5 = p1s2s.getText().toString();
                        String j6 = p2s2s.getText().toString();
                        values.put(Contract.UndoNumbers.COLUMN_SET2A, Integer.valueOf(j5));
                        values.put(Contract.UndoNumbers.COLUMN_SET2B, Integer.valueOf(j6));
                        String j1 = p1s.getText().toString();
                        String j2 = p2s.getText().toString();
                        values.put(Contract.UndoNumbers.COLUMN_KTBFPS, Integer.valueOf(j1));
                        values.put(Contract.UndoNumbers.COLUMN_KTBSPS, Integer.valueOf(j2));
                    }
                }
            }
        } else if (SET_3_KTB_ktb) {
            if (!tieBreakDone_3SetKTB) {
                String j1 = p1s.getText().toString();
                String j2 = p2s.getText().toString();
                values.put(Contract.UndoNumbers.COLUMN_KTBFPS, Integer.valueOf(j1));
                values.put(Contract.UndoNumbers.COLUMN_KTBSPS, Integer.valueOf(j2));
                if (SET_3_KTB_1_SET_tb_DONE & SET_3_KTB_2_SET_tb_DONE) {
                    if (A_pl_tb1 > B_pl_tb1) {
                        values.put(Contract.UndoNumbers.COLUMN_SET1A, 7);
                        values.put(Contract.UndoNumbers.COLUMN_SET1B, 6);
                    } else {
                        values.put(Contract.UndoNumbers.COLUMN_SET1A, 6);
                        values.put(Contract.UndoNumbers.COLUMN_SET1B, 7);
                    }
                    if (A_pl_tb2 > B_pl_tb2) {
                        values.put(Contract.UndoNumbers.COLUMN_SET2A, 7);
                        values.put(Contract.UndoNumbers.COLUMN_SET2B, 6);
                    } else {
                        values.put(Contract.UndoNumbers.COLUMN_SET2A, 6);
                        values.put(Contract.UndoNumbers.COLUMN_SET2B, 7);
                    }
                } else if (!SET_3_KTB_1_SET_tb_DONE & SET_3_KTB_2_SET_tb_DONE) {
                    String j3 = p1s1s.getText().toString();
                    String j4 = p2s1s.getText().toString();
                    values.put(Contract.UndoNumbers.COLUMN_SET1A, Integer.valueOf(j3));
                    values.put(Contract.UndoNumbers.COLUMN_SET1B, Integer.valueOf(j4));
                    if (A_pl_tb2 > B_pl_tb2) {
                        values.put(Contract.UndoNumbers.COLUMN_SET2A, 7);
                        values.put(Contract.UndoNumbers.COLUMN_SET2B, 6);
                    } else {
                        values.put(Contract.UndoNumbers.COLUMN_SET2A, 6);
                        values.put(Contract.UndoNumbers.COLUMN_SET2B, 7);
                    }
                } else if (SET_3_KTB_1_SET_tb_DONE & !SET_3_KTB_2_SET_tb_DONE) {
                    if (A_pl_tb1 > B_pl_tb1) {
                        values.put(Contract.UndoNumbers.COLUMN_SET1A, 7);
                        values.put(Contract.UndoNumbers.COLUMN_SET1B, 6);
                    } else {
                        values.put(Contract.UndoNumbers.COLUMN_SET1A, 6);
                        values.put(Contract.UndoNumbers.COLUMN_SET1B, 7);
                    }
                    String j5 = p1s2s.getText().toString();
                    String j6 = p2s2s.getText().toString();
                    values.put(Contract.UndoNumbers.COLUMN_SET2A, Integer.valueOf(j5));
                    values.put(Contract.UndoNumbers.COLUMN_SET2B, Integer.valueOf(j6));
                } else {
                    String j3 = p1s1s.getText().toString();
                    String j4 = p2s1s.getText().toString();
                    String j5 = p1s2s.getText().toString();
                    String j6 = p2s2s.getText().toString();
                    values.put(Contract.UndoNumbers.COLUMN_SET1A, Integer.valueOf(j3));
                    values.put(Contract.UndoNumbers.COLUMN_SET1B, Integer.valueOf(j4));
                    values.put(Contract.UndoNumbers.COLUMN_SET2A, Integer.valueOf(j5));
                    values.put(Contract.UndoNumbers.COLUMN_SET2B, Integer.valueOf(j6));
                }
            } else {
                if (A_pl_tb2 > B_pl_tb2) {
                    values.put(Contract.UndoNumbers.COLUMN_SET2A, 7);
                    values.put(Contract.UndoNumbers.COLUMN_SET2B, 6);
                } else {
                    values.put(Contract.UndoNumbers.COLUMN_SET2A, 6);
                    values.put(Contract.UndoNumbers.COLUMN_SET2B, 7);
                }
                String j1 = p1s.getText().toString();
                String j2 = p2s.getText().toString();
                values.put(Contract.UndoNumbers.COLUMN_KTBFPS, Integer.valueOf(j1));
                values.put(Contract.UndoNumbers.COLUMN_KTBSPS, Integer.valueOf(j2));//B_pl_tb2 change j2
                if (SET_3_KTB_1_SET_tb_DONE) {
                    if (A_pl_tb1 > B_pl_tb1) {
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
            }
        } else if (SET_3_KTB_2_Set) {
            if (!tieBreakDone_3SetKTB) {
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
                String j5 = p1s2s.getText().toString();
                String j6 = p2s2s.getText().toString();
                values.put(Contract.UndoNumbers.COLUMN_SET2A, Integer.valueOf(j5));
                values.put(Contract.UndoNumbers.COLUMN_SET2B, Integer.valueOf(j6));
                if (SET_3_KTB_1_SET_tb_DONE) {
                    if (A_pl_tb1 > B_pl_tb1) {
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
            } else {
                if (A_pl_tb1 > B_pl_tb1) {
                    values.put(Contract.UndoNumbers.COLUMN_SET1A, 7);
                    values.put(Contract.UndoNumbers.COLUMN_SET1B, 6);
                } else {
                    values.put(Contract.UndoNumbers.COLUMN_SET1A, 6);
                    values.put(Contract.UndoNumbers.COLUMN_SET1B, 7);
                }
                String j1 = p1s.getText().toString();
                String j2 = p2s.getText().toString();
                values.put(Contract.UndoNumbers.COLUMN_KTBFPS, Integer.valueOf(j1));
                values.put(Contract.UndoNumbers.COLUMN_KTBSPS, Integer.valueOf(j2));//B_pl_tb1 change j2
            }
        } else {
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
            String j3 = p1s1s.getText().toString();
            String j4 = p2s1s.getText().toString();
            values.put(Contract.UndoNumbers.COLUMN_SET1A, Integer.valueOf(j3));
            values.put(Contract.UndoNumbers.COLUMN_SET1B, Integer.valueOf(j4));
        }
        db.update(Contract.UndoNumbers.TABLE_NAME_1, values, Contract.UndoNumbers.COLUMN_NUM_NUM + " = ?", new String[]{Integer.toString(mDbHelper3SetKTB.getMaxNumNum())});
    }


    public void undo3SetKTB(View view) {

        ad3KTBSet = new AlertDialog.Builder(context);

        ad3KTBSet.setMessage(getString(R.string.really_undo));

        ad3KTBSet.setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                undo3SetKTB1();
            }
        });
        ad3KTBSet.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
            }
        });
        ad3KTBSet.setCancelable(true);
        ad3KTBSet.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
            }
        });
        ad3KTBSet.show();
    }

    public void undo3SetKTB1() {

        goalFP3SetKTB.setEnabled(true);
        goalSP3SetKTB.setEnabled(true);
        spinner1p3sKTB.setEnabled(true);
        spinner2p3sKTB.setEnabled(true);

        noEnabled(saveButton3SetKTB, getDrawable(R.drawable.ic_save_black_24dp_0_3));
        playerFirst3SetKTB.setBackgroundResource(R.drawable.frame_player);
        playerSecond3SetKTB.setBackgroundResource(R.drawable.frame_player);
        Set3KTBStatusMatch = "Paused";
        stopMatchIsEnabled3SetKTB = true;

        undoCounter_3SetKTB = undoCounter_3SetKTB + 1;

        updateColumnNumberDown3SetKTB();

        if (SET_3_KTB_MATCH_DONE) {
            pointSPKTB3SetKTB.setText("0");
            pointFPKTB3SetKTB.setText("0");
        }

        if (mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("ktbfps", undoCounter_3SetKTB).equals("-2")) {

            pointFP3SetKTB.setText(adv_3SetKTB);
            pointSP3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("ktbsps", undoCounter_3SetKTB));

        } else if (mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("ktbsps", undoCounter_3SetKTB).equals("-2")) {

            pointSP3SetKTB.setText(adv_3SetKTB);
            pointFP3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("ktbfps", undoCounter_3SetKTB));

        } else {
            pointFP3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("ktbfps", undoCounter_3SetKTB));
            pointSP3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("ktbsps", undoCounter_3SetKTB));
        }

        if (mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2a", undoCounter_3SetKTB).equals("7") &
                mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2b", undoCounter_3SetKTB).equals("6")) {

            pointFPSecondSet3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2a", undoCounter_3SetKTB));
            pointSPSecondSet3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2b", undoCounter_3SetKTB));
            convertUndo(A_pl_tb2, B_pl_tb2, pointFPSecondSet3SetKTB, pointSPSecondSet3SetKTB);

        } else if (mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2a", undoCounter_3SetKTB).equals("6") &
                mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2b", undoCounter_3SetKTB).equals("7")) {

            pointFPSecondSet3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2a", undoCounter_3SetKTB));
            pointSPSecondSet3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2b", undoCounter_3SetKTB));
            convertUndo(A_pl_tb2, B_pl_tb2, pointFPSecondSet3SetKTB, pointSPSecondSet3SetKTB);

        } else {

            pointFPSecondSet3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2a", undoCounter_3SetKTB));
            pointSPSecondSet3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2b", undoCounter_3SetKTB));
        }

        if (mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1a", undoCounter_3SetKTB).equals("7")
                & mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1b", undoCounter_3SetKTB).equals("6")) {

            pointFPFirstSet3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1a", undoCounter_3SetKTB));
            pointSPFirstSet3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1b", undoCounter_3SetKTB));
            convertUndo(A_pl_tb1, B_pl_tb1, pointFPFirstSet3SetKTB, pointSPFirstSet3SetKTB);

        } else if (mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1a", undoCounter_3SetKTB).equals("6") &
                mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1b", undoCounter_3SetKTB).equals("7")) {

            pointFPFirstSet3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1a", undoCounter_3SetKTB));
            pointSPFirstSet3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1b", undoCounter_3SetKTB));
            convertUndo(A_pl_tb1, B_pl_tb1, pointFPFirstSet3SetKTB, pointSPFirstSet3SetKTB);

        } else {

            pointFPFirstSet3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1a", undoCounter_3SetKTB));
            pointSPFirstSet3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1b", undoCounter_3SetKTB));
        }

        SET_3_KTB_MATCH_DONE = false;

        pointFPKTB3SetKTB.setBackgroundResource(R.drawable.frame_player);
        pointSPKTB3SetKTB.setBackgroundResource(R.drawable.frame_player);


        if ((mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2a", undoCounter_3SetKTB).equals("6") &
                !"6".equals(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2b", undoCounter_3SetKTB)) &
                !"5".equals(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2b", undoCounter_3SetKTB))) |
                (mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2a", undoCounter_3SetKTB).equals("7") &
                        mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2b", undoCounter_3SetKTB).equals("5")) |
                (mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2b", undoCounter_3SetKTB).equals("6") &
                        !"6".equals(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2a", undoCounter_3SetKTB)) &
                        !"5".equals(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2a", undoCounter_3SetKTB))) |
                (mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2b", undoCounter_3SetKTB).equals("7") &
                        mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2a", undoCounter_3SetKTB).equals("5"))) {

            SET_3_KTB_2_Set = false;

        } else {
            SET_3_KTB_2_Set = true;
        }

        if (SET_3_KTB_2_Set) {

            pointFPSecondSet3SetKTB.setBackgroundResource(R.drawable.frame_player);
            pointSPSecondSet3SetKTB.setBackgroundResource(R.drawable.frame_player);

            SET_3_KTB_ktb = false;
            SET_3_KTB_KTB_START = false;
            SET_3_KTB_2_SET_DONE = false;
        }

        if ((mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1a", undoCounter_3SetKTB).equals("6") & !"6".equals(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1b", undoCounter_3SetKTB)) & !"5".equals(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1b", undoCounter_3SetKTB))) |
                (mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1a", undoCounter_3SetKTB).equals("7") & mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1b", undoCounter_3SetKTB).equals("5")) |
                (mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1b", undoCounter_3SetKTB).equals("6") & !"6".equals(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1a", undoCounter_3SetKTB)) & !"5".equals(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1a", undoCounter_3SetKTB))) |
                (mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1b", undoCounter_3SetKTB).equals("7") & mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1a", undoCounter_3SetKTB).equals("5"))) {

            SET_3_KTB_1_Set = false;

        } else {

            SET_3_KTB_1_Set = true;
            SET_3_KTB_2_Set = false;
        }

        if (SET_3_KTB_1_Set) {
            SET_3_KTB_1_SET_DONE = false;
            SET_3_KTB_1_SET_b_DONE = false;
            SET_3_KTB_1_SET_a_DONE = false;
            SET_3_KTB_1_SET_tb_DONE = false;

            pointFPFirstSet3SetKTB.setBackgroundResource(R.drawable.frame_player);
            pointSPFirstSet3SetKTB.setBackgroundResource(R.drawable.frame_player);
        }

        if (mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1a", undoCounter_3SetKTB).equals("6") &
                mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1b", undoCounter_3SetKTB).equals("6")) {

            tb_3SetKTB = true;
            SET_3_KTB_1_SET_tb_DONE = false;
            SET_3_KTB_1_SET_a_DONE = false;
            SET_3_KTB_1_SET_b_DONE = false;
            tieBreakDone_3SetKTB = false;

            pointFPFirstSet3SetKTB.setBackgroundResource(R.drawable.frame_player);
            pointSPFirstSet3SetKTB.setBackgroundResource(R.drawable.frame_player);

        } else if (mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2a", undoCounter_3SetKTB).equals("6") & mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2b", undoCounter_3SetKTB).equals("6")) {

            tb_3SetKTB = true;
            SET_3_KTB_2_SET_tb_DONE = false;
            SET_3_KTB_2_SET_DONE = false;
            tieBreakDone_3SetKTB = false;
            pointFPSecondSet3SetKTB.setBackgroundResource(R.drawable.frame_player);
            pointSPSecondSet3SetKTB.setBackgroundResource(R.drawable.frame_player);

        } else {

            tb_3SetKTB = false;
        }

        if (SET_3_KTB_ktb & mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("ktbfps", undoCounter_3SetKTB).equals("0") & mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("ktbsps", undoCounter_3SetKTB).equals("0")) {
            if ((mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2a", undoCounter_3SetKTB).equals("7") & mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2b", undoCounter_3SetKTB).equals("6")) |
                    (mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2a", undoCounter_3SetKTB).equals("6") & mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2b", undoCounter_3SetKTB).equals("7"))) {
                tieBreakDone_3SetKTB = true;
            }
        }

        if (mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2a", undoCounter_3SetKTB).equals("0") & mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2b", undoCounter_3SetKTB).equals("0")) {

            if ((mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1a", undoCounter_3SetKTB).equals("7") & mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1b", undoCounter_3SetKTB).equals("6")) |
                    (mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1a", undoCounter_3SetKTB).equals("6") & mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1b", undoCounter_3SetKTB).equals("7")) & mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("ktbfps", undoCounter_3SetKTB).equals("0") & mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("ktbsps", undoCounter_3SetKTB).equals("0")) {
                tieBreakDone_3SetKTB = true;
            }
        }
        beEnabled(redo_3SetKTB, getDrawable(R.drawable.ic_redo_black_24dp));

        goalsCounter3SetKTB--;

        if (goalsCounter3SetKTB <= 0) {
            noEnabled(undo_3SetKTB, getDrawable(R.drawable.ic_undo_black_24dp_0_3));
        }
    }

    public void redo3SetKTB(View view) {

        ad3KTBSet = new AlertDialog.Builder(context);

        ad3KTBSet.setMessage(getString(R.string.really_redo));

        ad3KTBSet.setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                redo3SetKTB1();
            }
        });
        ad3KTBSet.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
            }
        });
        ad3KTBSet.setCancelable(true);
        ad3KTBSet.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
            }
        });
        ad3KTBSet.show();
    }

    public void redo3SetKTB1() {

        beEnabled(undo_3SetKTB, getDrawable(R.drawable.ic_undo_black_24dp));
        spinner1p3sKTB.setSelection(0);
        spinner2p3sKTB.setSelection(0);
        updateColumnNumberUp3SetKTB();

        undoCounter_3SetKTB = undoCounter_3SetKTB - 1;


        if (SET_3_KTB_ktb) {

            if (Integer.valueOf(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set3a", undoCounter_3SetKTB)) == 10 & Integer.valueOf(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set3b", undoCounter_3SetKTB)) <= Integer.valueOf(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set3a", undoCounter_3SetKTB)) - 2
                    | Integer.valueOf(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set3a", undoCounter_3SetKTB)) > 10 & Integer.valueOf(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set3b", undoCounter_3SetKTB)) <= Integer.valueOf(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set3a", undoCounter_3SetKTB)) - 2) {

                SET_3_KTB_MATCH_DONE = true;
                SET_3_KTB_KTB_DONE = true;

                Toast.makeText(this, pl1_1_3setKTB + " " + pl1_2_3setKTB + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();

                beEnabled(saveButton3SetKTB, getDrawable(R.drawable.ic_save_black_24dp));
                playerFirst3SetKTB.setBackgroundResource(R.drawable.frame_winner);
                pointFPKTB3SetKTB.setBackgroundResource(R.drawable.frame_winner);
                Set3KTBStatusMatch = "firstPlayer";
                stopMatchIsEnabled3SetKTB = false;

            } else if (Integer.valueOf(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set3b", undoCounter_3SetKTB)) == 10 & Integer.valueOf(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set3a", undoCounter_3SetKTB)) <= Integer.valueOf(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set3b", undoCounter_3SetKTB)) - 2 |
                    Integer.valueOf(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set3b", undoCounter_3SetKTB)) > 10 & Integer.valueOf(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set3a", undoCounter_3SetKTB)) <= Integer.valueOf(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set3b", undoCounter_3SetKTB)) - 2) {

                SET_3_KTB_MATCH_DONE = true;
                SET_3_KTB_KTB_DONE = true;

                Toast.makeText(this, pl2_1_3setKTB + " " + pl2_2_3setKTB + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();

                beEnabled(saveButton3SetKTB, getDrawable(R.drawable.ic_save_black_24dp));
                playerSecond3SetKTB.setBackgroundResource(R.drawable.frame_winner);
                pointSPKTB3SetKTB.setBackgroundResource(R.drawable.frame_winner);
                Set3KTBStatusMatch = "secondPlayer";
                stopMatchIsEnabled3SetKTB = false;
            }
        }

        if (mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("ktbfps", undoCounter_3SetKTB).equals("-2")) {

            pointFP3SetKTB.setText(adv_3SetKTB);
            pointSP3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("ktbsps", undoCounter_3SetKTB));

        } else if (mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("ktbsps", undoCounter_3SetKTB).equals("-2")) {

            pointFP3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("ktbfps", undoCounter_3SetKTB));
            pointSP3SetKTB.setText(adv_3SetKTB);

        } else {

            pointFP3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("ktbfps", undoCounter_3SetKTB));
            pointSP3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("ktbsps", undoCounter_3SetKTB));
        }

        if (SET_3_KTB_2_Set & tb_3SetKTB & (mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2a", undoCounter_3SetKTB).equals("7"))) {
            pointFPSecondSet3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2a", undoCounter_3SetKTB));
            pointSPSecondSet3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2b", undoCounter_3SetKTB));

            convertUndo(A_pl_tb2, B_pl_tb2, pointFPSecondSet3SetKTB, pointSPSecondSet3SetKTB);

            SET_3_KTB_2_SET_tb_DONE = true;
            tb_3SetKTB = false;
            SET_3_KTB_1_Set = false;
            tieBreakDone_3SetKTB = true;
            SET_3_KTB_2_Set = false;
            SET_3_KTB_2_SET_DONE = true;

            pointFPSecondSet3SetKTB.setBackgroundResource(R.drawable.frame_winner);


            if (SET_3_KTB_1_SET_b_DONE) {

                SET_3_KTB_ktb = true;
                SET_3_KTB_KTB_START = true;

                Toast.makeText(getBaseContext(), getString(R.string.KTB_starts), Toast.LENGTH_LONG).show();

            } else {

                SET_3_KTB_MATCH_DONE = true;

                Toast.makeText(this, pl1_1_3setKTB + " " + pl1_2_3setKTB + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();

                playerFirst3SetKTB.setBackgroundResource(R.drawable.frame_winner);
                pointFPSecondSet3SetKTB.setBackgroundResource(R.drawable.frame_winner);
                beEnabled(saveButton3SetKTB, getDrawable(R.drawable.ic_save_black_24dp));
                Set3KTBStatusMatch = "firstPlayer";
                stopMatchIsEnabled3SetKTB = false;
            }
        } else if (SET_3_KTB_2_Set & tb_3SetKTB & (mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2b", undoCounter_3SetKTB).equals("7"))) {

            pointFPSecondSet3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2a", undoCounter_3SetKTB));
            pointSPSecondSet3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2b", undoCounter_3SetKTB));

            convertUndo(A_pl_tb2, B_pl_tb2, pointFPSecondSet3SetKTB, pointSPSecondSet3SetKTB);

            SET_3_KTB_2_SET_tb_DONE = true;
            tb_3SetKTB = false;
            SET_3_KTB_1_Set = false;
            tieBreakDone_3SetKTB = true;
            SET_3_KTB_2_Set = false;
            SET_3_KTB_2_SET_DONE = true;

            pointSPSecondSet3SetKTB.setBackgroundResource(R.drawable.frame_winner);

            if (SET_3_KTB_1_SET_a_DONE) {

                SET_3_KTB_ktb = true;
                SET_3_KTB_KTB_START = true;

                Toast.makeText(getBaseContext(), getString(R.string.KTB_starts), Toast.LENGTH_LONG).show();

            } else {

                SET_3_KTB_MATCH_DONE = true;

                Toast.makeText(this, pl2_1_3setKTB + " " + pl2_2_3setKTB + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();

                playerSecond3SetKTB.setBackgroundResource(R.drawable.frame_winner);
                pointSPSecondSet3SetKTB.setBackgroundResource(R.drawable.frame_winner);
                beEnabled(saveButton3SetKTB, getDrawable(R.drawable.ic_save_black_24dp));
                Set3KTBStatusMatch = "secondPlayer";
                stopMatchIsEnabled3SetKTB = false;
            }
        } else if (!SET_3_KTB_2_Set & ((mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2b", undoCounter_3SetKTB).equals("7")
                & mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2a", undoCounter_3SetKTB).equals("6")) |
                (mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2a", undoCounter_3SetKTB).equals("7")
                        & mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2b", undoCounter_3SetKTB).equals("6")))) {

            pointFPSecondSet3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2a", undoCounter_3SetKTB));
            pointSPSecondSet3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2b", undoCounter_3SetKTB));

            convertUndo(A_pl_tb2, B_pl_tb2, pointFPSecondSet3SetKTB, pointSPSecondSet3SetKTB);

        } else if (mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2b", undoCounter_3SetKTB).equals("6") &
                mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2a", undoCounter_3SetKTB).equals("6") &
                mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("ktbfps", undoCounter_3SetKTB).equals("0") &
                mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("ktbsps", undoCounter_3SetKTB).equals("0")) {

            pointFPSecondSet3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2a", undoCounter_3SetKTB));
            pointSPSecondSet3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2b", undoCounter_3SetKTB));

            Toast.makeText(getBaseContext(), getString(R.string.Tie_break_starts), Toast.LENGTH_SHORT).show();

            tb_3SetKTB = true;

        } else {

            pointFPSecondSet3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2a", undoCounter_3SetKTB));
            pointSPSecondSet3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2b", undoCounter_3SetKTB));

            if (SET_3_KTB_2_Set) {

                if (tb_3SetKTB) {

                    checkTieBreakNumber(Integer.parseInt(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("ktbfps", undoCounter_3SetKTB)), Integer.parseInt(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("ktbsps", undoCounter_3SetKTB)));

                } else {
                    checkSetNumber(Integer.parseInt(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2a", undoCounter_3SetKTB)), Integer.parseInt(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2b", undoCounter_3SetKTB)));
                }
            }
        }

        if (SET_3_KTB_1_Set & tb_3SetKTB & (mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1a", undoCounter_3SetKTB).equals("7"))) {

            pointFPFirstSet3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1a", undoCounter_3SetKTB));
            pointSPFirstSet3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1b", undoCounter_3SetKTB));
            convertUndo(A_pl_tb1, B_pl_tb1, pointFPFirstSet3SetKTB, pointSPFirstSet3SetKTB);

            SET_3_KTB_1_SET_a_DONE = true;
            SET_3_KTB_1_SET_tb_DONE = true;
            SET_3_KTB_1_SET_DONE = true;
            tb_3SetKTB = false;
            SET_3_KTB_1_Set = false;
            tieBreakDone_3SetKTB = true;
            SET_3_KTB_2_Set = true;

            Toast.makeText(this, pl1_1_3setKTB + " " + pl1_2_3setKTB + " " + getString(R.string.wins_1_set), Toast.LENGTH_LONG).show();
            pointFPFirstSet3SetKTB.setBackgroundResource(R.drawable.frame_winner);

        } else if (SET_3_KTB_1_Set & tb_3SetKTB & (mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1b", undoCounter_3SetKTB).equals("7"))) {

            pointFPFirstSet3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1a", undoCounter_3SetKTB));
            pointSPFirstSet3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1b", undoCounter_3SetKTB));
            convertUndo(A_pl_tb1, B_pl_tb1, pointFPFirstSet3SetKTB, pointSPFirstSet3SetKTB);

            SET_3_KTB_1_SET_b_DONE = true;
            SET_3_KTB_1_SET_tb_DONE = true;
            SET_3_KTB_1_SET_DONE = true;
            tb_3SetKTB = false;
            SET_3_KTB_1_Set = false;
            tieBreakDone_3SetKTB = true;
            SET_3_KTB_2_Set = true;

            Toast.makeText(this, pl2_1_3setKTB + " " + pl2_2_3setKTB + " " + getString(R.string.wins_1_set), Toast.LENGTH_LONG).show();
            pointSPFirstSet3SetKTB.setBackgroundResource(R.drawable.frame_winner);

        } else if (!SET_3_KTB_1_Set & ((mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1b", undoCounter_3SetKTB).equals("7") & mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1a", undoCounter_3SetKTB).equals("6")) |
                (mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1a", undoCounter_3SetKTB).equals("7") & mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1b", undoCounter_3SetKTB).equals("6")))) {

            pointFPFirstSet3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1a", undoCounter_3SetKTB));
            pointSPFirstSet3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1b", undoCounter_3SetKTB));
            convertUndo(A_pl_tb1, B_pl_tb1, pointFPFirstSet3SetKTB, pointSPFirstSet3SetKTB);

        } else if (mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1b", undoCounter_3SetKTB).equals("6") &
                mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1a", undoCounter_3SetKTB).equals("6") &
                mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("ktbfps", undoCounter_3SetKTB).equals("0") &
                mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("ktbsps", undoCounter_3SetKTB).equals("0")) {

            pointFPFirstSet3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1a", undoCounter_3SetKTB));
            pointSPFirstSet3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1b", undoCounter_3SetKTB));

            tb_3SetKTB = true;

            Toast.makeText(getBaseContext(), getString(R.string.Tie_break_starts), Toast.LENGTH_SHORT).show();

        } else {

            pointFPFirstSet3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1a", undoCounter_3SetKTB));
            pointSPFirstSet3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1b", undoCounter_3SetKTB));

            if (SET_3_KTB_1_Set) {

                if (tb_3SetKTB) {

                    checkTieBreakNumber(Integer.parseInt(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("ktbfps", undoCounter_3SetKTB)), Integer.parseInt(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("ktbsps", undoCounter_3SetKTB)));

                } else {
                    checkSetNumber(Integer.parseInt(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1a", undoCounter_3SetKTB)), Integer.parseInt(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1b", undoCounter_3SetKTB)));
                }
            }
        }

        pointFPKTB3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set3a", undoCounter_3SetKTB));
        pointSPKTB3SetKTB.setText(mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set3b", undoCounter_3SetKTB));

        if (undoCounter_3SetKTB == 0) {

            noEnabled(redo_3SetKTB, getDrawable(R.drawable.ic_redo_black_24dp_0_3));
        }

        if (SET_3_KTB_MATCH_DONE) {

            AA_KingTieBreakActivity.setButNotEnabled(goalFP3SetKTB, goalSP3SetKTB, spinner1p3sKTB, spinner2p3sKTB);
        }
        goalsCounter3SetKTB++;

        if (goalsCounter3SetKTB > 0) {
            beEnabled(undo_3SetKTB, getDrawable(R.drawable.ic_undo_black_24dp));
        }
    }


    public void updateColumnNumberUp3SetKTB() {
        SQLiteDatabase db = mDbHelper3SetKTB.getWritableDatabase();
        String insertQuery = " UPDATE " + Contract.ContractNames.TABLE_NAME + " SET " + Contract.ContractNames.COLUMN_NUMBER
                + " = ((SELECT " + Contract.ContractNames.COLUMN_NUMBER + " FROM " + Contract.ContractNames.TABLE_NAME +
                " WHERE " + Contract.ContractNames._ID + " = " + getLastIdfromUndoTable1Set() + ") + 1 ) WHERE " + Contract.ContractNames._ID + " = " + getLastIdfromUndoTable1Set();
        db.execSQL(insertQuery);
    }

    public int getLastIdfromUndoTable1Set() {
        SQLiteDatabase db = mDbHelper3SetKTB.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT " + Contract.UndoNumbers.COLUMN_NUM_ID + " FROM "
                + Contract.UndoNumbers.TABLE_NAME_1 + " WHERE " + Contract.UndoNumbers.COLUMN_NUM_NUM +
                " = (SELECT MAX( " + Contract.UndoNumbers.COLUMN_NUM_NUM + " ) FROM " + Contract.UndoNumbers.TABLE_NAME_1 + " ) - " + (undoCounter_3SetKTB - 1), new String[]{});
        cursor.moveToFirst();
        lastIdfromUT1Set = (int) cursor.getLong(0);
        cursor.close();
        return lastIdfromUT1Set;
    }

    public void updateColumnNumberDown3SetKTB() {
        SQLiteDatabase db = mDbHelper3SetKTB.getWritableDatabase();
        String insertQuery = " UPDATE " + Contract.ContractNames.TABLE_NAME + " SET " + Contract.ContractNames.COLUMN_NUMBER
                + " = ((SELECT " + Contract.ContractNames.COLUMN_NUMBER + " FROM " + Contract.ContractNames.TABLE_NAME +
                " WHERE " + Contract.ContractNames._ID + " = " + getLastIdfromUndoTable1Set() + ") - 1 ) WHERE " + Contract.ContractNames._ID + " = " + getLastIdfromUndoTable1Set();
        db.execSQL(insertQuery);
    }

    public void deleteRow3SetKTB() {

        beEnabled(undo_3SetKTB, getDrawable(R.drawable.ic_undo_black_24dp));

        SQLiteDatabase db = mDbHelper3SetKTB.getReadableDatabase();
        String insertQuery = " DELETE FROM " + Contract.UndoNumbers.TABLE_NAME_1 + " WHERE " + Contract.UndoNumbers.COLUMN_NUM_NUM +
                " IN ( SELECT " + Contract.UndoNumbers.COLUMN_NUM_NUM + " FROM " + Contract.UndoNumbers.TABLE_NAME_1 +
                " ORDER BY " + Contract.UndoNumbers.COLUMN_NUM_NUM + " DESC LIMIT " + undoCounter_3SetKTB + ")";
        db.execSQL(insertQuery);

        undoCounter_3SetKTB = 0;
    }

    public void saveResult3SetKTB() {


        if (getIntent().getExtras().getBoolean("isNew")) {
            mDbHelper3SetKTB.putName13("fullName", "matchMode", "courtSurface", pl1_1_3setKTB, pl1_2_3setKTB, pl2_1_3setKTB, pl2_2_3setKTB, Set3KTBStatusMatch,
                    mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("ktbfps", undoCounter_3SetKTB), mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("ktbsps", undoCounter_3SetKTB),
                    mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1a", undoCounter_3SetKTB), A_pl_tb1, mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1b", undoCounter_3SetKTB), B_pl_tb1,
                    mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2a", undoCounter_3SetKTB), A_pl_tb2, mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2b", undoCounter_3SetKTB), B_pl_tb2,
                    mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set3a", undoCounter_3SetKTB), -2, mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set3b", undoCounter_3SetKTB), -2,
                    "0", -2, "0", -2, "0", -2, "0", -2, 7, 3, 6, 2, 5, 1, 4, 0, getDFB_3SetKTB(tb1pl_3SetKTB), getDFB_3SetKTB(tb2pl_3SetKTB), getDFB_3SetKTB(tieBreakDone_3SetKTB),
                    getDFB_3SetKTB(stopMatchIsEnabled3SetKTB), getDFB_3SetKTB(tb_3SetKTB), getDFB_3SetKTB(SET_3_KTB_1_SET_a_DONE), getDFB_3SetKTB(SET_3_KTB_1_SET_b_DONE),
                    getDFB_3SetKTB(SET_3_KTB_1_SET_DONE), getDFB_3SetKTB(SET_3_KTB_2_SET_DONE), 0, 0, 0, 0, getDFB_3SetKTB(SET_3_KTB_1_Set), getDFB_3SetKTB(SET_3_KTB_2_Set),
                    getDFB_3SetKTB(SET_3_KTB_ktb), 0, 0, getDFB_3SetKTB(SET_3_KTB_1_SET_tb_DONE), getDFB_3SetKTB(SET_3_KTB_2_SET_tb_DONE), getDFB_3SetKTB(SET_3_KTB_KTB_DONE), 0,
                    getDFB_3SetKTB(SET_3_KTB_KTB_START), getDFB_3SetKTB(SET_3_KTB_MATCH_DONE));

        } else {

            mDbHelper3SetKTB.putName122("fullName", "matchMode", "courtSurface", pl1_1_3setKTB, pl1_2_3setKTB, pl2_1_3setKTB, pl2_2_3setKTB, Set3KTBStatusMatch,
                    mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("ktbfps", undoCounter_3SetKTB), mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("ktbsps", undoCounter_3SetKTB),
                    mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1a", undoCounter_3SetKTB), A_pl_tb1, mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1b", undoCounter_3SetKTB), B_pl_tb1,
                    mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2a", undoCounter_3SetKTB), A_pl_tb2, mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2b", undoCounter_3SetKTB), B_pl_tb2,
                    mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set3a", undoCounter_3SetKTB), -2, mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set3b", undoCounter_3SetKTB), -2,
                    "0", -2, "0", -2, "0", -2, "0", -2, 7, 3, 6, 2, 5, 1, 4, 0, getDFB_3SetKTB(tb1pl_3SetKTB), getDFB_3SetKTB(tb2pl_3SetKTB), getDFB_3SetKTB(tieBreakDone_3SetKTB),
                    getDFB_3SetKTB(stopMatchIsEnabled3SetKTB), getDFB_3SetKTB(tb_3SetKTB), getDFB_3SetKTB(SET_3_KTB_1_SET_a_DONE), getDFB_3SetKTB(SET_3_KTB_1_SET_b_DONE),
                    getDFB_3SetKTB(SET_3_KTB_1_SET_DONE), getDFB_3SetKTB(SET_3_KTB_2_SET_DONE), 0, 0, 0, 0, getDFB_3SetKTB(SET_3_KTB_1_Set), getDFB_3SetKTB(SET_3_KTB_2_Set),
                    getDFB_3SetKTB(SET_3_KTB_ktb), 0, 0, getDFB_3SetKTB(SET_3_KTB_1_SET_tb_DONE), getDFB_3SetKTB(SET_3_KTB_2_SET_tb_DONE), getDFB_3SetKTB(SET_3_KTB_KTB_DONE), 0,
                    getDFB_3SetKTB(SET_3_KTB_KTB_START), getDFB_3SetKTB(SET_3_KTB_MATCH_DONE));
        }
    }

    public void saveResultGoToMM() {
        saveResult3SetKTB();
        mDbHelper3SetKTB.clearTable(Contract.UndoNumbers.TABLE_NAME_1);
        mDbHelper3SetKTB.clearTable(Contract.ContractNames.TABLE_NAME);
        finish();
        Intent intent1 = new Intent(this, AA_MainActivity.class);
        startActivity(intent1);
    }

    public void noSaveResultGoToMM() {
        mDbHelper3SetKTB.clearTable(Contract.UndoNumbers.TABLE_NAME_1);
        mDbHelper3SetKTB.clearTable(Contract.ContractNames.TABLE_NAME);
        finish();
        Intent intent1 = new Intent(this, AA_MainActivity.class);
        startActivity(intent1);
    }

    public void goToHelp() {
        Intent helpIntent = new Intent(this, DataBase.class);
        startActivity(helpIntent);
    }

    public void saveAndGo3SetKTB(View view) {
        saveResultGoToMM();
    }


    public void saveResultFromStat(String st) {

        Set3KTBStatusMatch = st;

        if (getIntent().getExtras().getBoolean("isNew")) {
            mDbHelper3SetKTB.putName13("fullName", "matchMode", "courtSurface", pl1_1_3setKTB, pl1_2_3setKTB, pl2_1_3setKTB, pl2_2_3setKTB, Set3KTBStatusMatch,
                    mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("ktbfps", undoCounter_3SetKTB), mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("ktbsps", undoCounter_3SetKTB),
                    mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1a", undoCounter_3SetKTB), A_pl_tb1, mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1b", undoCounter_3SetKTB), B_pl_tb1,
                    mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2a", undoCounter_3SetKTB), A_pl_tb2, mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2b", undoCounter_3SetKTB), B_pl_tb2,
                    mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set3a", undoCounter_3SetKTB), -2, mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set3b", undoCounter_3SetKTB), -2,
                    "0", -2, "0", -2, "0", -2, "0", -2, 7, 3, 6, 2, 5, 1, 4, 0, getDFB_3SetKTB(tb1pl_3SetKTB), getDFB_3SetKTB(tb2pl_3SetKTB), getDFB_3SetKTB(tieBreakDone_3SetKTB),
                    getDFB_3SetKTB(stopMatchIsEnabled3SetKTB), getDFB_3SetKTB(tb_3SetKTB), getDFB_3SetKTB(SET_3_KTB_1_SET_a_DONE), getDFB_3SetKTB(SET_3_KTB_1_SET_b_DONE),
                    getDFB_3SetKTB(SET_3_KTB_1_SET_DONE), getDFB_3SetKTB(SET_3_KTB_2_SET_DONE), 0, 0, 0, 0, getDFB_3SetKTB(SET_3_KTB_1_Set), getDFB_3SetKTB(SET_3_KTB_2_Set),
                    getDFB_3SetKTB(SET_3_KTB_ktb), 0, 0, getDFB_3SetKTB(SET_3_KTB_1_SET_tb_DONE), getDFB_3SetKTB(SET_3_KTB_2_SET_tb_DONE), getDFB_3SetKTB(SET_3_KTB_KTB_DONE), 0,
                    getDFB_3SetKTB(SET_3_KTB_KTB_START), getDFB_3SetKTB(SET_3_KTB_MATCH_DONE));

        } else {

            mDbHelper3SetKTB.putName122("fullName", "matchMode", "courtSurface", pl1_1_3setKTB, pl1_2_3setKTB, pl2_1_3setKTB, pl2_2_3setKTB, Set3KTBStatusMatch,
                    mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("ktbfps", undoCounter_3SetKTB), mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("ktbsps", undoCounter_3SetKTB),
                    mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1a", undoCounter_3SetKTB), A_pl_tb1, mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set1b", undoCounter_3SetKTB), B_pl_tb1,
                    mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2a", undoCounter_3SetKTB), A_pl_tb2, mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set2b", undoCounter_3SetKTB), B_pl_tb2,
                    mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set3a", undoCounter_3SetKTB), -2, mDbHelper3SetKTB.getStringFromNumDataFromUndoTable("set3b", undoCounter_3SetKTB), -2,
                    "0", -2, "0", -2, "0", -2, "0", -2, 7, 3, 6, 2, 5, 1, 4, 0, getDFB_3SetKTB(tb1pl_3SetKTB), getDFB_3SetKTB(tb2pl_3SetKTB), getDFB_3SetKTB(tieBreakDone_3SetKTB),
                    getDFB_3SetKTB(stopMatchIsEnabled3SetKTB), getDFB_3SetKTB(tb_3SetKTB), getDFB_3SetKTB(SET_3_KTB_1_SET_a_DONE), getDFB_3SetKTB(SET_3_KTB_1_SET_b_DONE),
                    getDFB_3SetKTB(SET_3_KTB_1_SET_DONE), getDFB_3SetKTB(SET_3_KTB_2_SET_DONE), 0, 0, 0, 0, getDFB_3SetKTB(SET_3_KTB_1_Set), getDFB_3SetKTB(SET_3_KTB_2_Set),
                    getDFB_3SetKTB(SET_3_KTB_ktb), 0, 0, getDFB_3SetKTB(SET_3_KTB_1_SET_tb_DONE), getDFB_3SetKTB(SET_3_KTB_2_SET_tb_DONE), getDFB_3SetKTB(SET_3_KTB_KTB_DONE), 0,
                    getDFB_3SetKTB(SET_3_KTB_KTB_START), getDFB_3SetKTB(SET_3_KTB_MATCH_DONE));
        }

        mDbHelper3SetKTB.clearTable(Contract.UndoNumbers.TABLE_NAME_1);
        mDbHelper3SetKTB.clearTable(Contract.ContractNames.TABLE_NAME);
        finish();
        Intent intent1 = new Intent(this, AA_MainActivity.class);
        startActivity(intent1);
    }

    public int getDFB_3SetKTB(Boolean b) {
        int u = 0;
        if (b) {
            u = 1;
        }
        return u;
    }

    @Override
    public void onDestroy() {
        mDbHelper3SetKTB.clearTable(Contract.UndoNumbers.TABLE_NAME_1);
        mDbHelper3SetKTB.clearTable(Contract.ContractNames.TABLE_NAME);
        super.onDestroy();
    }
}



