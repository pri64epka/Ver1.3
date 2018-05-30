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

public class AA_Set5Activity extends AppCompatActivity {
    TextView playerFirst5Set, playerSecond5Set, pointFP5Set, pointSP5Set, pointFPFirstSet5Set, pointSPFirstSet5Set, pointFPSecondSet5Set, pointSPSecondSet5Set,
            pointFPThirdSet5Set, pointSPThirdSet5Set, pointFPFourSet5Set, pointSPFourSet5Set, pointFPFiveSet5Set, pointSPFiveSet5Set;
    Spinner spinner1p5s, spinner2p5s;
    Button goalFP5Set, goalSP5Set;
    ImageButton undo_5Set, redo_5Set, saveButton5Set;
    int curNum5Set, n8, tbfp, tbsp, A_pl_5Set_tb4, B_pl_5Set_tb4,
            A_pl_5Set_tb3, B_pl_5Set_tb3, A_pl_5Set_tb2, B_pl_5Set_tb2, A_pl_5Set_tb1, B_pl_5Set_tb1, prevNum5Set, lastIdfromUT5Set;
    String scoreP1_set5, scoreP2_set5, pl1_1_5set, pl1_2_5set, pl2_1_5set, pl2_2_5set, reasonsSelectedItem;
    String adv_5Set = "Ad";
    String r1 = "";
    String r2 = "";
    int undoCounter5Set = 0;
    private int i5u = 0;
    private int k5u = 0;
    private int fpls5 = 0;
    private int spls5 = 0;
    private TennisHelper mDbHelper5Set;

    private boolean SET_5_1_Set = true;
    private boolean SET_5_2_Set = false;
    private boolean SET_5_3_Set = false;
    private boolean SET_5_4_Set = false;
    private boolean SET_5_5_Set = false;
    private boolean tieBreakDone_5Set = false;
    private boolean tb1pl_5Set = false;
    private boolean tb2pl_5Set = false;
    private boolean tb_5Set = false;
    private boolean SET_5_MATCH_DONE = false;
    private boolean SET_5_1_SET_a_DONE = false;
    private boolean SET_5_1_SET_b_DONE = false;
    private boolean SET_5_2_SET_a_DONE = false;
    private boolean SET_5_2_SET_b_DONE = false;
    private boolean SET_5_3_SET_a_DONE = false;
    private boolean SET_5_3_SET_b_DONE = false;
    private boolean SET_5_4_SET_a_DONE = false;
    private boolean SET_5_4_SET_b_DONE = false;
    private boolean SET_5_1_SET_tb_DONE = false;
    private boolean SET_5_2_SET_tb_DONE = false;
    private boolean SET_5_3_SET_tb_DONE = false;
    private boolean SET_5_4_SET_tb_DONE = false;
    private boolean stopMatchIsEnabled5Set = true;

    private String Set5StatusMatch = "Paused";
    int goalsCounter5Set;

    Context context;
    AlertDialog.Builder ad5Set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aa_activity_set5);

        goalsCounter5Set = 0;

        if (getSupportActionBar() != null) {

            getSupportActionBar().setTitle(getString(R.string.table_score));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mDbHelper5Set = new TennisHelper(this);
        context = AA_Set5Activity.this;

        TextView tableScore5Set = (TextView) findViewById(R.id.addToTableScoreTV5Set);
        tableScore5Set.setText(R.string.five_sets);

        playerFirst5Set = (TextView) findViewById(R.id.playerFirst5SetTV);
        playerSecond5Set = (TextView) findViewById(R.id.playerSecond5SetTV);

        TextView nameFirstPlayer = (TextView) findViewById(R.id.nameFirstPlayerTV);
        TextView nameSecondPlayer = (TextView) findViewById(R.id.nameSecondPlayerTV);

        pointFP5Set = (TextView) findViewById(R.id.pointFP5SetTV);
        pointSP5Set = (TextView) findViewById(R.id.pointSP5SetTV);

        pointFPFirstSet5Set = (TextView) findViewById(R.id.pointFPFirstSet5SetTV);
        pointSPFirstSet5Set = (TextView) findViewById(R.id.pointSPFirstSet5SetTV);

        pointFPSecondSet5Set = (TextView) findViewById(R.id.pointFPSecondSet5SetTV);
        pointSPSecondSet5Set = (TextView) findViewById(R.id.pointSPSecondSet5SetTV);

        pointFPThirdSet5Set = (TextView) findViewById(R.id.pointFPThirdSet5SetTV);
        pointSPThirdSet5Set = (TextView) findViewById(R.id.pointSPThirdSet5SetTV);

        pointFPFourSet5Set = (TextView) findViewById(R.id.pointFPFourSet5SetTV);
        pointSPFourSet5Set = (TextView) findViewById(R.id.pointSPFourSet5SetTV);

        pointFPFiveSet5Set = (TextView) findViewById(R.id.pointFPFiveSet5SetTV);
        pointSPFiveSet5Set = (TextView) findViewById(R.id.pointSPFiveSet5SetTV);


        if (getIntent().getExtras().getBoolean("isNew")) {

            pl1_1_5set = getIntent().getExtras().getString("user");
            pl1_2_5set = getIntent().getExtras().getString("userSur");
            pl2_1_5set = getIntent().getExtras().getString("der");
            pl2_2_5set = getIntent().getExtras().getString("derSur");

            pointFP5Set.setText(String.valueOf(tp[i5u]));
            pointSP5Set.setText(String.valueOf(tp[k5u]));

            pointFPFirstSet5Set.setText(String.valueOf(fpls5));
            pointSPFirstSet5Set.setText(String.valueOf(spls5));

            pointFPSecondSet5Set.setText(String.valueOf("0"));
            pointSPSecondSet5Set.setText(String.valueOf("0"));

            pointFPThirdSet5Set.setText(String.valueOf("0"));
            pointSPThirdSet5Set.setText(String.valueOf("0"));

            pointFPFourSet5Set.setText(String.valueOf("0"));
            pointSPFourSet5Set.setText(String.valueOf("0"));

            pointFPFiveSet5Set.setText(String.valueOf("0"));
            pointSPFiveSet5Set.setText(String.valueOf("0"));

        } else {

            pl1_1_5set = mDbHelper5Set.getNameById("name_A_1", selId);
            pl1_2_5set = mDbHelper5Set.getNameById("name_A_2", selId);
            pl2_1_5set = mDbHelper5Set.getNameById("name_B_1", selId);
            pl2_2_5set = mDbHelper5Set.getNameById("name_B_2", selId);

            if (mDbHelper5Set.getNumById("ktbfpsST", selId) == -2) {
                pointFP5Set.setText(adv_5Set);
            } else {
                pointFP5Set.setText(String.valueOf(mDbHelper5Set.getNumById("ktbfpsST", selId)));
            }

            if (mDbHelper5Set.getNumById("ktbspsST", selId) == -2) {
                pointSP5Set.setText(adv_5Set);
            } else {
                pointSP5Set.setText(String.valueOf(mDbHelper5Set.getNumById("ktbspsST", selId)));
            }

            if (mDbHelper5Set.getNumById("set1aST_tb", selId) > 0 || mDbHelper5Set.getNumById("set1bST_tb", selId) > 0) {

                pointFPFirstSet5Set.setText(String.valueOf(mDbHelper5Set.getNumById("set1aST", selId)));
                pointSPFirstSet5Set.setText(String.valueOf(mDbHelper5Set.getNumById("set1bST", selId)));

                convertUndo(mDbHelper5Set.getNumById("set1aST_tb", selId), mDbHelper5Set.getNumById("set1bST_tb", selId), pointFPFirstSet5Set, pointSPFirstSet5Set);
            } else {

                pointFPFirstSet5Set.setText(String.valueOf(mDbHelper5Set.getNumById("set1aST", selId)));
                pointSPFirstSet5Set.setText(String.valueOf(mDbHelper5Set.getNumById("set1bST", selId)));
            }

            if (mDbHelper5Set.getNumById("set2aST_tb", selId) > 0 || mDbHelper5Set.getNumById("set2bST_tb", selId) > 0) {
                pointFPSecondSet5Set.setText(String.valueOf(mDbHelper5Set.getNumById("set2aST", selId)));
                pointSPSecondSet5Set.setText(String.valueOf(mDbHelper5Set.getNumById("set2bST", selId)));
                convertUndo(mDbHelper5Set.getNumById("set2aST_tb", selId), mDbHelper5Set.getNumById("set2bST_tb", selId), pointFPSecondSet5Set, pointSPSecondSet5Set);
            } else {
                pointFPSecondSet5Set.setText(String.valueOf(mDbHelper5Set.getNumById("set2aST", selId)));
                pointSPSecondSet5Set.setText(String.valueOf(mDbHelper5Set.getNumById("set2bST", selId)));
            }

            if (mDbHelper5Set.getNumById("set3aST_tb", selId) > 0 || mDbHelper5Set.getNumById("set3bST_tb", selId) > 0) {
                pointFPThirdSet5Set.setText(String.valueOf(mDbHelper5Set.getNumById("set3aST", selId)));
                pointSPThirdSet5Set.setText(String.valueOf(mDbHelper5Set.getNumById("set3bST", selId)));
                convertUndo(mDbHelper5Set.getNumById("set3aST_tb", selId), mDbHelper5Set.getNumById("set3bST_tb", selId), pointFPThirdSet5Set, pointSPThirdSet5Set);

            } else {
                pointFPThirdSet5Set.setText(String.valueOf(mDbHelper5Set.getNumById("set3aST", selId)));
                pointSPThirdSet5Set.setText(String.valueOf(mDbHelper5Set.getNumById("set3bST", selId)));
            }

            if (mDbHelper5Set.getNumById("set4aST_tb", selId) > 0 || mDbHelper5Set.getNumById("set4bST_tb", selId) > 0) {
                pointFPFourSet5Set.setText(String.valueOf(mDbHelper5Set.getNumById("set4aST", selId)));
                pointSPFourSet5Set.setText(String.valueOf(mDbHelper5Set.getNumById("set4bST", selId)));
                convertUndo(mDbHelper5Set.getNumById("set4aST_tb", selId), mDbHelper5Set.getNumById("set4bST_tb", selId), pointFPFourSet5Set, pointSPFourSet5Set);
            } else {
                pointFPFourSet5Set.setText(String.valueOf(mDbHelper5Set.getNumById("set4aST", selId)));
                pointSPFourSet5Set.setText(String.valueOf(mDbHelper5Set.getNumById("set4bST", selId)));
            }

            pointFPFiveSet5Set.setText(String.valueOf(mDbHelper5Set.getNumById("set5aST", selId)));
            pointSPFiveSet5Set.setText(String.valueOf(mDbHelper5Set.getNumById("set5bST", selId)));

            SET_5_1_Set = mDbHelper5Set.getNumById("_1_set", selId) > 0;//

            SET_5_2_Set = mDbHelper5Set.getNumById("_2_set", selId) > 0;//

            SET_5_3_Set = mDbHelper5Set.getNumById("_3_set", selId) > 0;//

            SET_5_4_Set = mDbHelper5Set.getNumById("_4_set", selId) > 0;//

            SET_5_5_Set = mDbHelper5Set.getNumById("_5_set", selId) > 0;//

            tieBreakDone_5Set = mDbHelper5Set.getNumById("tieBreakDone", selId) > 0;//

            tb1pl_5Set = mDbHelper5Set.getNumById("tb1pl", selId) > 0;//

            tb2pl_5Set = mDbHelper5Set.getNumById("tb2pl", selId) > 0;//

            tb_5Set = mDbHelper5Set.getNumById("tb", selId) > 0;//

            SET_5_MATCH_DONE = mDbHelper5Set.getNumById("match_done", selId) > 0;//

            SET_5_1_SET_a_DONE = mDbHelper5Set.getNumById("_1_set_a_done", selId) > 0;//
            setWinnerFrame(SET_5_1_SET_a_DONE,pointFPFirstSet5Set);

            SET_5_1_SET_b_DONE = mDbHelper5Set.getNumById("_1_set_b_done", selId) > 0;//
            setWinnerFrame(SET_5_1_SET_b_DONE,pointSPFirstSet5Set);

            SET_5_2_SET_a_DONE = mDbHelper5Set.getNumById("_2_set_a_done", selId) > 0;//
            setWinnerFrame(SET_5_2_SET_a_DONE,pointFPSecondSet5Set);

            SET_5_2_SET_b_DONE = mDbHelper5Set.getNumById("_2_set_b_done", selId) > 0;//
            setWinnerFrame(SET_5_2_SET_b_DONE,pointSPSecondSet5Set);

            SET_5_3_SET_a_DONE = mDbHelper5Set.getNumById("_3_set_a_done", selId) > 0;//
            setWinnerFrame(SET_5_3_SET_a_DONE,pointFPThirdSet5Set);

            SET_5_3_SET_b_DONE = mDbHelper5Set.getNumById("_3_set_b_done", selId) > 0;//
            setWinnerFrame(SET_5_3_SET_b_DONE,pointSPThirdSet5Set);

            SET_5_4_SET_a_DONE = mDbHelper5Set.getNumById("_4_set_a_done", selId) > 0;//
            setWinnerFrame(SET_5_4_SET_a_DONE,pointFPFourSet5Set);

            SET_5_4_SET_b_DONE = mDbHelper5Set.getNumById("_4_set_b_done", selId) > 0;//
            setWinnerFrame(SET_5_4_SET_b_DONE,pointSPFourSet5Set);

            SET_5_1_SET_tb_DONE = mDbHelper5Set.getNumById("_1_set_tb_done", selId) > 0;//

            SET_5_2_SET_tb_DONE = mDbHelper5Set.getNumById("_2_set_tb_done", selId) > 0;//

            SET_5_3_SET_tb_DONE = mDbHelper5Set.getNumById("_3_set_tb_done", selId) > 0;//

            SET_5_4_SET_tb_DONE = mDbHelper5Set.getNumById("_4_set_tb_done", selId) > 0;//

            stopMatchIsEnabled5Set = mDbHelper5Set.getNumById("stopMatchIsEnabled", selId) > 0;//

            A_pl_5Set_tb1 = mDbHelper5Set.getNumById("set1aST_tb", selId);
            B_pl_5Set_tb1 = mDbHelper5Set.getNumById("set1bST_tb", selId);
            A_pl_5Set_tb2 = mDbHelper5Set.getNumById("set2aST_tb", selId);
            B_pl_5Set_tb2 = mDbHelper5Set.getNumById("set2bST_tb", selId);
            A_pl_5Set_tb3 = mDbHelper5Set.getNumById("set3aST_tb", selId);
            B_pl_5Set_tb3 = mDbHelper5Set.getNumById("set3bST_tb", selId);
            A_pl_5Set_tb4 = mDbHelper5Set.getNumById("set4aST_tb", selId);
            B_pl_5Set_tb4 = mDbHelper5Set.getNumById("set4bST_tb", selId);

            mDbHelper5Set.insert5SetUSScore(
                    mDbHelper5Set.getNumById("ktbfpsST", selId), mDbHelper5Set.getNumById("ktbspsST", selId),
                    mDbHelper5Set.getNumById("set1aST", selId), mDbHelper5Set.getNumById("set1bST", selId),
                    mDbHelper5Set.getNumById("set2aST", selId), mDbHelper5Set.getNumById("set2bST", selId),
                    mDbHelper5Set.getNumById("set3aST", selId), mDbHelper5Set.getNumById("set3bST", selId),
                    mDbHelper5Set.getNumById("set4aST", selId), mDbHelper5Set.getNumById("set4bST", selId),
                    mDbHelper5Set.getNumById("set5aST", selId), mDbHelper5Set.getNumById("set5bST", selId));

            mDbHelper5Set.insertPlayers(pl1_1_5set, pl1_2_5set, pl2_1_5set, pl2_2_5set);
        }

        setNames(playerFirst5Set, pl1_1_5set, pl1_2_5set);
        setNames(playerSecond5Set, pl2_1_5set, pl2_2_5set);

        AA_KingTieBreakActivity.setAddNames(nameFirstPlayer, pl1_1_5set, pl1_2_5set);
        AA_KingTieBreakActivity.setAddNames(nameSecondPlayer, pl2_1_5set, pl2_2_5set);

        typeGoals[0] = getResources().getString(R.string.select_gt_for_spinner);
        typeGoals[1] = getResources().getString(R.string.Winner);
        typeGoals[2] = getResources().getString(R.string.Ace);
        typeGoals[3] = getResources().getString(R.string.Unenforced_error_for_spinner);
        typeGoals[4] = getResources().getString(R.string.Double_error_for_spinner);

        spinner1p5s = (Spinner) findViewById(R.id.spinner1p5sTV);
        spinner2p5s = (Spinner) findViewById(R.id.spinner2p5sTV);
        AA_KingTieBreakActivity.setadapter(typeGoals, spinner1p5s, this);
        AA_KingTieBreakActivity.setadapter(typeGoals, spinner2p5s, this);

        goalFP5Set = (Button) findViewById(R.id.goalFP5SetButton);
        goalSP5Set = (Button) findViewById(R.id.goalSP5SetButton);

        undo_5Set = (ImageButton) findViewById(R.id.undo5SetButton);
        redo_5Set = (ImageButton) findViewById(R.id.redo5SetButton);
        saveButton5Set = (ImageButton) findViewById(R.id._saveButton5Set);


        noEnabled(undo_5Set, getDrawable(R.drawable.ic_undo_black_24dp_0_3));
        noEnabled(redo_5Set, getDrawable(R.drawable.ic_redo_black_24dp_0_3));
        noEnabled(saveButton5Set, getDrawable(R.drawable.ic_save_black_24dp_0_3));
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if (!stopMatchIsEnabled5Set) {
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

                final String na_1 = getString(R.string.Retired) + " " + playerFirst5Set.getText().toString() + getString(R.string.loss);
                final String na_2 = getString(R.string.Retired) + " " + playerSecond5Set.getText().toString() + getString(R.string.loss);
                final String na_3 = getString(R.string.Another_Match_is_paused);
                final String na_4 = getString(R.string.Another_Match_is_finished);
                final String[] reasons = {na_1, na_2, na_3, na_4};
                ad5Set = new AlertDialog.Builder(this);
                ad5Set.setTitle(getString(R.string.Choose_the_reason))
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


                                        ad5Set = new AlertDialog.Builder(context);

                                        ad5Set.setMessage(getString(R.string.Do_you_want));

                                        ad5Set.setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener() {
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
                                        ad5Set.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int arg1) {
                                                noSaveResultGoToMM();
                                                if (!getIntent().getExtras().getBoolean("isNew")) {
                                                    mDbHelper5Set.putFreeRowInStatisticSaveTable(selId);
                                                }
                                            }
                                        });
                                        ad5Set.setCancelable(true);
                                        ad5Set.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                            public void onCancel(DialogInterface dialog) {
                                            }
                                        });
                                        ad5Set.show();
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
                ad5Set.show();
                break;

            case R.id.help_item:

                goToHelp();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void onBackPressed() {



            ad5Set = new AlertDialog.Builder(context);
            ad5Set.setMessage(getString(R.string.ad_alert_1));
            ad5Set.setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int arg1) {

                    saveResultGoToMM();
                }
            });
            ad5Set.setNeutralButton(getString(R.string.Continue_match),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int id) {
                            dialog.cancel();
                        }
                    });

            ad5Set.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int arg1) {
                    noSaveResultGoToMM();
                    if (!getIntent().getExtras().getBoolean("isNew")) {
                        mDbHelper5Set.putFreeRowInStatisticSaveTable(selId);
                    }
                }
            });
            ad5Set.setCancelable(true);
            ad5Set.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialog) {
                }
            });
            ad5Set.show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void firstPlayerGoal5Set(View v) {

        switch (AA_KingTieBreakActivity.getSpinnerItem(spinner1p5s)) {
            case 0: {
                Toast.makeText(getBaseContext(), getString(R.string.select_gt), Toast.LENGTH_SHORT).show();
                break;
            }
            case 1: {
                deleteRow5Set();
                noEnabled(redo_5Set, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper5Set.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(7) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper5Set.getLastId() - 7)});

                setFirstPlScore5Set();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper5Set.getLastId() - 7);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper5Set.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet5AllplScore(pointFP5Set, pointSP5Set,
                        pointFPFirstSet5Set, pointSPFirstSet5Set, pointFPSecondSet5Set, pointSPSecondSet5Set, pointFPThirdSet5Set, pointSPThirdSet5Set,
                        pointFPFourSet5Set, pointSPFourSet5Set, pointFPFiveSet5Set, pointSPFiveSet5Set);
                goalsCounter5Set++;
                break;
            }
            case 2: {
                deleteRow5Set();
                noEnabled(redo_5Set, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper5Set.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(6) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper5Set.getLastId() - 6)});

                setFirstPlScore5Set();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper5Set.getLastId() - 6);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper5Set.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet5AllplScore(pointFP5Set, pointSP5Set,
                        pointFPFirstSet5Set, pointSPFirstSet5Set, pointFPSecondSet5Set, pointSPSecondSet5Set, pointFPThirdSet5Set, pointSPThirdSet5Set,
                        pointFPFourSet5Set, pointSPFourSet5Set, pointFPFiveSet5Set, pointSPFiveSet5Set);
                goalsCounter5Set++;
                break;
            }

            case 3: {
                deleteRow5Set();
                noEnabled(redo_5Set, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper5Set.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(1) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper5Set.getLastId() - 1)});

                setFirstPlScore5Set();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper5Set.getLastId() - 1);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper5Set.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet5AllplScore(pointFP5Set, pointSP5Set,
                        pointFPFirstSet5Set, pointSPFirstSet5Set, pointFPSecondSet5Set, pointSPSecondSet5Set, pointFPThirdSet5Set, pointSPThirdSet5Set,
                        pointFPFourSet5Set, pointSPFourSet5Set, pointFPFiveSet5Set, pointSPFiveSet5Set);
                goalsCounter5Set++;
                break;
            }

            case 4: {
                deleteRow5Set();
                noEnabled(redo_5Set, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper5Set.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(0) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper5Set.getLastId())});

                setFirstPlScore5Set();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper5Set.getLastId());
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper5Set.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet5AllplScore(pointFP5Set, pointSP5Set,
                        pointFPFirstSet5Set, pointSPFirstSet5Set, pointFPSecondSet5Set, pointSPSecondSet5Set, pointFPThirdSet5Set, pointSPThirdSet5Set,
                        pointFPFourSet5Set, pointSPFourSet5Set, pointFPFiveSet5Set, pointSPFiveSet5Set);
                goalsCounter5Set++;
                break;
            }
        }
        spinner1p5s.setSelection(0);
        spinner2p5s.setSelection(0);
    }

    public void secondPlayerGoal5Set(View v) {


        switch (AA_KingTieBreakActivity.getSpinnerItem(spinner2p5s)) {
            case 0: {
                Toast.makeText(getBaseContext(), getString(R.string.select_gt), Toast.LENGTH_SHORT).show();
                break;
            }
            case 1: {
                deleteRow5Set();
                noEnabled(redo_5Set, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper5Set.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(3) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper5Set.getLastId() - 3)});

                setSecondPlScore5Set();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper5Set.getLastId() - 3);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper5Set.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet5AllplScore(pointFP5Set, pointSP5Set,
                        pointFPFirstSet5Set, pointSPFirstSet5Set, pointFPSecondSet5Set, pointSPSecondSet5Set, pointFPThirdSet5Set, pointSPThirdSet5Set,
                        pointFPFourSet5Set, pointSPFourSet5Set, pointFPFiveSet5Set, pointSPFiveSet5Set);
                goalsCounter5Set++;
                break;
            }
            case 2: {
                deleteRow5Set();
                noEnabled(redo_5Set, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper5Set.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(2) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper5Set.getLastId() - 2)});

                setSecondPlScore5Set();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper5Set.getLastId() - 2);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper5Set.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet5AllplScore(pointFP5Set, pointSP5Set,
                        pointFPFirstSet5Set, pointSPFirstSet5Set, pointFPSecondSet5Set, pointSPSecondSet5Set, pointFPThirdSet5Set, pointSPThirdSet5Set,
                        pointFPFourSet5Set, pointSPFourSet5Set, pointFPFiveSet5Set, pointSPFiveSet5Set);
                goalsCounter5Set++;
                break;
            }
            case 3: {
                deleteRow5Set();
                noEnabled(redo_5Set, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper5Set.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(5) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper5Set.getLastId() - 5)});

                setSecondPlScore5Set();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper5Set.getLastId() - 5);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper5Set.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet5AllplScore(pointFP5Set, pointSP5Set,
                        pointFPFirstSet5Set, pointSPFirstSet5Set, pointFPSecondSet5Set, pointSPSecondSet5Set, pointFPThirdSet5Set, pointSPThirdSet5Set,
                        pointFPFourSet5Set, pointSPFourSet5Set, pointFPFiveSet5Set, pointSPFiveSet5Set);
                goalsCounter5Set++;
                break;
            }
            case 4: {
                deleteRow5Set();
                noEnabled(redo_5Set, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper5Set.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(4) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper5Set.getLastId() - 4)});

                setSecondPlScore5Set();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper5Set.getLastId() - 4);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper5Set.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet5AllplScore(pointFP5Set, pointSP5Set,
                        pointFPFirstSet5Set, pointSPFirstSet5Set, pointFPSecondSet5Set, pointSPSecondSet5Set, pointFPThirdSet5Set, pointSPThirdSet5Set,
                        pointFPFourSet5Set, pointSPFourSet5Set, pointFPFiveSet5Set, pointSPFiveSet5Set);
                goalsCounter5Set++;
                break;
            }
        }
        spinner1p5s.setSelection(0);
        spinner2p5s.setSelection(0);
    }

    public void setFirstPlScore5Set() {

        tieBreakDone_5Set = false;

        tb1pl_5Set = true;

        if (tb_5Set) {

            tieBreak(tb1pl_5Set, tb2pl_5Set);
        } else {

            i5u = getKorI(pointFP5Set);
            k5u = getKorI(pointSP5Set);

            if (SET_5_5_Set) {

                fpls5 = Integer.valueOf(pointFPFiveSet5Set.getText().toString());
                spls5 = Integer.valueOf(pointSPFiveSet5Set.getText().toString());

            } else if (SET_5_4_Set) {

                fpls5 = Integer.valueOf(pointFPFourSet5Set.getText().toString());
                spls5 = Integer.valueOf(pointSPFourSet5Set.getText().toString());

            } else if (SET_5_3_Set) {

                fpls5 = Integer.valueOf(pointFPThirdSet5Set.getText().toString());
                spls5 = Integer.valueOf(pointSPThirdSet5Set.getText().toString());

            } else if (SET_5_2_Set) {

                fpls5 = Integer.valueOf(pointFPSecondSet5Set.getText().toString());
                spls5 = Integer.valueOf(pointSPSecondSet5Set.getText().toString());

            } else {

                fpls5 = Integer.valueOf(pointFPFirstSet5Set.getText().toString());
                spls5 = Integer.valueOf(pointSPFirstSet5Set.getText().toString());
            }

            scoreP1_set5 = pointFP5Set.getText().toString();

            switch (scoreP1_set5) {
                case "0":
                    i5u = 1;
                    break;
                case "15":
                    i5u = 2;
                    break;
                case "30":
                    i5u = 3;
                    break;
                case "40":
                    if (i5u > k5u) {
                        i5u = k5u = 0;
                        fpls5++;
                    } else if (i5u == k5u) {
                        i5u = 4;
                        break;
                    } else {
                        i5u = k5u = 3;
                    }
                    break;
                case "Ad":
                    i5u = k5u = 0;
                    fpls5++;
                    break;
            }
            if (i5u == 4) {

                pointFP5Set.setText(adv_5Set);
                pointSP5Set.setText(String.valueOf(tp[k5u]));
                tb1pl_5Set = false;

            } else {

                pointFP5Set.setText(String.valueOf(tp[i5u]));
                pointSP5Set.setText(String.valueOf(tp[k5u]));

                if (SET_5_5_Set) {

                    pointFPFiveSet5Set.setText(String.valueOf(fpls5));

                } else if (SET_5_4_Set) {

                    pointFPFourSet5Set.setText(String.valueOf(fpls5));

                } else if (SET_5_3_Set) {

                    pointFPThirdSet5Set.setText(String.valueOf(fpls5));

                } else if (SET_5_2_Set) {

                    pointFPSecondSet5Set.setText(String.valueOf(fpls5));

                } else {

                    pointFPFirstSet5Set.setText(String.valueOf(fpls5));
                }

                tb1pl_5Set = false;
            }
            checkSetNumber(fpls5, spls5);
        }

    }

    public void setSecondPlScore5Set() {

        tieBreakDone_5Set = false;

        tb2pl_5Set = true;

        if (tb_5Set) {

            tieBreak(tb1pl_5Set, tb2pl_5Set);

        } else {

            i5u = getKorI(pointFP5Set);
            k5u = getKorI(pointSP5Set);

            if (SET_5_5_Set) {

                fpls5 = Integer.valueOf(pointFPFiveSet5Set.getText().toString());
                spls5 = Integer.valueOf(pointSPFiveSet5Set.getText().toString());

            } else if (SET_5_4_Set) {

                fpls5 = Integer.valueOf(pointFPFourSet5Set.getText().toString());
                spls5 = Integer.valueOf(pointSPFourSet5Set.getText().toString());

            } else if (SET_5_3_Set) {

                fpls5 = Integer.valueOf(pointFPThirdSet5Set.getText().toString());
                spls5 = Integer.valueOf(pointSPThirdSet5Set.getText().toString());

            } else if (SET_5_2_Set) {

                fpls5 = Integer.valueOf(pointFPSecondSet5Set.getText().toString());
                spls5 = Integer.valueOf(pointSPSecondSet5Set.getText().toString());

            } else {

                fpls5 = Integer.valueOf(pointFPFirstSet5Set.getText().toString());
                spls5 = Integer.valueOf(pointSPFirstSet5Set.getText().toString());
            }

            scoreP2_set5 = pointSP5Set.getText().toString();

            switch (scoreP2_set5) {
                case "0":
                    k5u = 1;
                    break;
                case "15":
                    k5u = 2;
                    break;
                case "30":
                    k5u = 3;
                    break;
                case "40":
                    if (k5u > i5u) {
                        k5u = i5u = 0;
                        spls5++;
                    } else if (k5u == i5u) {
                        k5u = 4;
                    } else {
                        i5u = k5u = 3;
                    }
                    break;
                case "Ad":
                    i5u = k5u = 0;
                    spls5++;
                    break;
            }
            if (k5u == 4) {

                pointSP5Set.setText(adv_5Set);
                pointFP5Set.setText(String.valueOf(tp[i5u]));
                tb2pl_5Set = false;

            } else {

                pointSP5Set.setText(String.valueOf(tp[k5u]));
                pointFP5Set.setText(String.valueOf(tp[i5u]));

                if (SET_5_5_Set) {

                    pointSPFiveSet5Set.setText(String.valueOf(spls5));

                } else if (SET_5_4_Set) {

                    pointSPFourSet5Set.setText(String.valueOf(spls5));

                } else if (SET_5_3_Set) {

                    pointSPThirdSet5Set.setText(String.valueOf(spls5));

                } else if (SET_5_2_Set) {

                    pointSPSecondSet5Set.setText(String.valueOf(spls5));

                } else {

                    pointSPFirstSet5Set.setText(String.valueOf(spls5));
                }
                tb2pl_5Set = false;
            }

            checkSetNumber(fpls5, spls5);
        }
    }

    public void intCurSet5AllplScore(TextView p1s, TextView p2s, TextView p1s1s, TextView p2s1s, TextView p1s2s, TextView p2s2s, TextView p1s3s, TextView p2s3s
            , TextView p1s4s, TextView p2s4s, TextView p1s5s, TextView p2s5s) {

        SQLiteDatabase db = mDbHelper5Set.getWritableDatabase();
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


        if (SET_5_1_SET_tb_DONE) {

            if (A_pl_5Set_tb1 > B_pl_5Set_tb1) {

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

        if (SET_5_2_SET_tb_DONE) {

            if (A_pl_5Set_tb2 > B_pl_5Set_tb2) {
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

        if (SET_5_3_SET_tb_DONE) {

            if (A_pl_5Set_tb3 > B_pl_5Set_tb3) {
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
        if (SET_5_4_SET_tb_DONE) {

            if (A_pl_5Set_tb4 > B_pl_5Set_tb4) {
                values.put(Contract.UndoNumbers.COLUMN_SET4A, 7);
                values.put(Contract.UndoNumbers.COLUMN_SET4B, 6);
            } else {
                values.put(Contract.UndoNumbers.COLUMN_SET4A, 6);
                values.put(Contract.UndoNumbers.COLUMN_SET4B, 7);
            }
        } else {
            String j9 = p1s4s.getText().toString();
            String j10 = p2s4s.getText().toString();
            values.put(Contract.UndoNumbers.COLUMN_SET4A, Integer.valueOf(j9));
            values.put(Contract.UndoNumbers.COLUMN_SET4B, Integer.valueOf(j10));
        }
        String j11 = p1s5s.getText().toString();
        String j12 = p2s5s.getText().toString();
        values.put(Contract.UndoNumbers.COLUMN_SET5A, Integer.valueOf(j11));
        values.put(Contract.UndoNumbers.COLUMN_SET5B, Integer.valueOf(j12));

        db.update(Contract.UndoNumbers.TABLE_NAME_1, values, Contract.UndoNumbers.COLUMN_NUM_NUM + " = ?", new String[]{Integer.toString(mDbHelper5Set.getMaxNumNum())});
    }

    public void tieBreak(boolean pla, boolean plb) {

        if (pla) {

            tbfp = Integer.valueOf(pointFP5Set.getText().toString());
            tbsp = Integer.valueOf(pointSP5Set.getText().toString());

            tbfp++;

            pointFP5Set.setText(String.valueOf(tbfp));
            pointSP5Set.setText(String.valueOf(tbsp));
            tb1pl_5Set = false;

        } else if (plb) {

            tbfp = Integer.valueOf(pointFP5Set.getText().toString());
            tbsp = Integer.valueOf(pointSP5Set.getText().toString());

            tbsp++;

            pointFP5Set.setText(String.valueOf(tbfp));
            pointSP5Set.setText(String.valueOf(tbsp));
            tb2pl_5Set = false;
        }
        checkTieBreakNumber(tbfp, tbsp);
    }

    public void checkTieBreakNumber(int x1, int x2) {

        if ((x1 == 7 & x2 <= x1 - 2) | (x1 > 7 & x2 <= x1 - 2)) {

            if (SET_5_4_Set) {

                if ((SET_5_1_SET_a_DONE & SET_5_2_SET_a_DONE & SET_5_3_SET_b_DONE)
                        | (SET_5_1_SET_a_DONE & SET_5_2_SET_b_DONE & SET_5_3_SET_a_DONE)
                        | (SET_5_1_SET_b_DONE & SET_5_2_SET_a_DONE & SET_5_3_SET_a_DONE)) {

                    pointFPFourSet5Set.setText(String.valueOf(7));
                    SET_5_MATCH_DONE = true;
                    SET_5_4_SET_a_DONE = true;
                    SET_5_4_SET_tb_DONE = true;
                    tieBreakDone_5Set = true;
                    A_pl_5Set_tb4 = x1;
                    B_pl_5Set_tb4 = x2;
                    convert(x1, x2, pointFPFourSet5Set, pointSPFourSet5Set);

                    Toast.makeText(this, pl1_1_5set + " " + pl1_2_5set + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                    setWinnerSet_5(playerFirst5Set, pointFPFourSet5Set, "firstPlayer");
                    setEnab();

                } else {

                    pointFPFourSet5Set.setText(String.valueOf(7));
                    SET_5_4_SET_a_DONE = true;
                    SET_5_4_SET_tb_DONE = true;
                    tieBreakDone_5Set = true;
                    SET_5_4_Set = false;
                    SET_5_5_Set = true;
                    A_pl_5Set_tb4 = x1;
                    B_pl_5Set_tb4 = x2;
                    convert(x1, x2, pointFPFourSet5Set, pointSPFourSet5Set);

                    Toast.makeText(this, pl1_1_5set + " " + pl1_2_5set + " " + getString(R.string.wins_4_set), Toast.LENGTH_LONG).show();
                    pointFPFourSet5Set.setBackgroundResource(R.drawable.frame_winner);
                }

            } else if (SET_5_3_Set) {

                if (SET_5_1_SET_a_DONE & SET_5_2_SET_a_DONE) {

                    pointFPThirdSet5Set.setText(String.valueOf(7));
                    SET_5_MATCH_DONE = true;
                    SET_5_3_SET_a_DONE = true;
                    SET_5_3_SET_tb_DONE = true;
                    tieBreakDone_5Set = true;
                    A_pl_5Set_tb3 = x1;
                    B_pl_5Set_tb3 = x2;
                    convert(x1, x2, pointFPThirdSet5Set, pointSPThirdSet5Set);

                    Toast.makeText(this, pl1_1_5set + " " + pl1_2_5set + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                    setWinnerSet_5(playerFirst5Set, pointFPThirdSet5Set, "firstPlayer");
                    setEnab();

                } else {

                    pointFPThirdSet5Set.setText(String.valueOf(7));
                    SET_5_3_SET_a_DONE = true;
                    SET_5_3_SET_tb_DONE = true;
                    SET_5_3_Set = false;
                    SET_5_4_Set = true;
                    A_pl_5Set_tb3 = x1;
                    B_pl_5Set_tb3 = x2;
                    convert(x1, x2, pointFPThirdSet5Set, pointSPThirdSet5Set);

                    Toast.makeText(this, pl1_1_5set + " " + pl1_2_5set + " " + getString(R.string.wins_3_set), Toast.LENGTH_LONG).show();
                    pointFPThirdSet5Set.setBackgroundResource(R.drawable.frame_winner);
                }
            } else if (SET_5_2_Set) {

                pointFPSecondSet5Set.setText(String.valueOf(7));
                SET_5_2_SET_a_DONE = true;
                SET_5_2_SET_tb_DONE = true;
                SET_5_2_Set = false;
                SET_5_3_Set = true;
                A_pl_5Set_tb2 = x1;
                B_pl_5Set_tb2 = x2;
                convert(x1, x2, pointFPSecondSet5Set, pointSPSecondSet5Set);

                Toast.makeText(this, pl1_1_5set + " " + pl1_2_5set + " " + getString(R.string.wins_2_set), Toast.LENGTH_LONG).show();
                pointFPSecondSet5Set.setBackgroundResource(R.drawable.frame_winner);

            } else if (SET_5_1_Set) {

                pointFPFirstSet5Set.setText(String.valueOf(7));
                SET_5_1_SET_a_DONE = true;
                SET_5_1_SET_tb_DONE = true;
                SET_5_1_Set = false;
                SET_5_2_Set = true;
                A_pl_5Set_tb1 = x1;
                B_pl_5Set_tb1 = x2;
                convert(x1, x2, pointFPFirstSet5Set, pointSPFirstSet5Set);

                Toast.makeText(this, pl1_1_5set + " " + pl1_2_5set + " " + getString(R.string.wins_1_set), Toast.LENGTH_LONG).show();
                pointFPFirstSet5Set.setBackgroundResource(R.drawable.frame_winner);
            }
        } else if ((x2 == 7 & x1 <= x2 - 2) | (x2 > 7 & x1 <= x2 - 2)) {

            if (SET_5_4_Set) {

                if ((SET_5_1_SET_b_DONE & SET_5_2_SET_a_DONE & SET_5_3_SET_b_DONE)
                        | (SET_5_1_SET_b_DONE & SET_5_2_SET_b_DONE & SET_5_3_SET_a_DONE)
                        | (SET_5_1_SET_a_DONE & SET_5_2_SET_b_DONE & SET_5_3_SET_b_DONE)) {

                    pointSPFourSet5Set.setText(String.valueOf(7));
                    SET_5_MATCH_DONE = true;
                    SET_5_4_SET_tb_DONE = true;
                    SET_5_4_SET_b_DONE = true;
                    tieBreakDone_5Set = true;
                    A_pl_5Set_tb4 = x1;
                    B_pl_5Set_tb4 = x2;
                    convert(x1, x2, pointFPFourSet5Set, pointSPFourSet5Set);

                    Toast.makeText(this, pl2_1_5set + " " + pl2_2_5set + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                    setWinnerSet_5(playerSecond5Set, pointSPFourSet5Set, "secondPlayer");
                    setEnab();

                } else {

                    pointSPFourSet5Set.setText(String.valueOf(7));
                    SET_5_4_SET_b_DONE = true;
                    SET_5_4_SET_tb_DONE = true;
                    tieBreakDone_5Set = true;
                    SET_5_4_Set = false;
                    SET_5_5_Set = true;
                    A_pl_5Set_tb4 = x1;
                    B_pl_5Set_tb4 = x2;
                    convert(x1, x2, pointFPFourSet5Set, pointSPFourSet5Set);

                    Toast.makeText(this, pl2_1_5set + " " + pl2_2_5set + " " + getString(R.string.wins_4_set), Toast.LENGTH_LONG).show();
                    pointSPFourSet5Set.setBackgroundResource(R.drawable.frame_winner);
                }

            } else if (SET_5_3_Set) {

                if (SET_5_1_SET_b_DONE & SET_5_2_SET_b_DONE) {

                    pointSPThirdSet5Set.setText(String.valueOf(7));
                    SET_5_MATCH_DONE = true;
                    SET_5_3_SET_tb_DONE = true;
                    SET_5_3_SET_b_DONE = true;
                    tieBreakDone_5Set = true;
                    A_pl_5Set_tb3 = x1;
                    B_pl_5Set_tb3 = x2;
                    convert(x1, x2, pointFPThirdSet5Set, pointSPThirdSet5Set);

                    Toast.makeText(this, pl2_1_5set + " " + pl2_2_5set + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                    setWinnerSet_5(playerSecond5Set, pointSPThirdSet5Set, "secondPlayer");
                    setEnab();

                } else {

                    pointSPThirdSet5Set.setText(String.valueOf(7));
                    SET_5_3_SET_b_DONE = true;
                    SET_5_3_SET_tb_DONE = true;
                    tieBreakDone_5Set = true;
                    SET_5_3_Set = false;
                    SET_5_4_Set = true;
                    A_pl_5Set_tb3 = x1;
                    B_pl_5Set_tb3 = x2;
                    convert(x1, x2, pointFPThirdSet5Set, pointSPThirdSet5Set);

                    Toast.makeText(this, pl2_1_5set + " " + pl2_2_5set + " " + getString(R.string.wins_3_set), Toast.LENGTH_LONG).show();
                    pointSPThirdSet5Set.setBackgroundResource(R.drawable.frame_winner);
                }
            } else if (SET_5_2_Set) {

                pointSPSecondSet5Set.setText(String.valueOf(7));
                SET_5_2_SET_b_DONE = true;
                SET_5_2_SET_tb_DONE = true;
                SET_5_2_Set = false;
                SET_5_3_Set = true;
                A_pl_5Set_tb2 = x1;
                B_pl_5Set_tb2 = x2;
                convert(x1, x2, pointFPSecondSet5Set, pointSPSecondSet5Set);

                Toast.makeText(this, pl2_1_5set + " " + pl2_2_5set + " " + getString(R.string.wins_2_set), Toast.LENGTH_LONG).show();
                pointSPSecondSet5Set.setBackgroundResource(R.drawable.frame_winner);

            } else if (SET_5_1_Set) {

                pointSPFirstSet5Set.setText(String.valueOf(7));
                SET_5_1_SET_b_DONE = true;
                SET_5_1_SET_tb_DONE = true;
                SET_5_1_Set = false;
                SET_5_2_Set = true;
                A_pl_5Set_tb1 = x1;
                B_pl_5Set_tb1 = x2;
                convert(x1, x2, pointFPFirstSet5Set, pointSPFirstSet5Set);

                Toast.makeText(this, pl2_1_5set + " " + pl2_2_5set + " " + getString(R.string.wins_1_set), Toast.LENGTH_LONG).show();
                pointSPFirstSet5Set.setBackgroundResource(R.drawable.frame_winner);
            }
        }
    }

    public void convert(int mp, int yu, TextView s1, TextView s2) {

        for (int i = 0; i < String.valueOf(mp).length(); i++) {
            int k = String.valueOf(mp).length() - i;
            r1 = degreeDigits[Character.getNumericValue(String.valueOf(mp).charAt(k - 1))] + r1;
        }

        for (int kol = 0; kol < String.valueOf(yu).length(); kol++) {
            int kik = String.valueOf(yu).length() - kol;
            r2 = degreeDigits[Character.getNumericValue(String.valueOf(yu).charAt(kik - 1))] + r2;
        }
        s1.setText(s1.getText() + r1);
        s2.setText(s2.getText() + r2);

        tieBreakDone_5Set = true;
        tb_5Set = false;

        pointFP5Set.setText(String.valueOf(0));
        pointSP5Set.setText(String.valueOf(0));

        r1 = "";
        r2 = "";

        fpls5 = 0;
        spls5 = 0;
        tbfp = 0;
        tbsp = 0;
    }

    public int getKorI(TextView ki) {
        if (ki.getText().toString().equals("0")) {
            n8 = 0;
        } else if (ki.getText().toString().equals("15")) {
            n8 = 1;
        } else if (ki.getText().toString().equals("30")) {
            n8 = 2;
        } else if (ki.getText().toString().equals("40")) {
            n8 = 3;
        } else if (ki.getText().toString().equals("Ad")) {
            n8 = 4;
        }
        return n8;
    }

    public void checkSetNumber(int y1, int y2) {

        if (SET_5_5_Set) {

            if ((y1 == 6 | y1 >= 7) & y2 <= y1 - 2) {

                SET_5_MATCH_DONE = true;

                Toast.makeText(this, pl1_1_5set + " " + pl1_2_5set + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                setWinnerSet_5(playerFirst5Set, pointFPFiveSet5Set, "firstPlayer");
                setEnab();

            } else if ((y2 == 6 | y2 >= 7) & y1 <= y2 - 2) {

                SET_5_MATCH_DONE = true;

                Toast.makeText(this, pl2_1_5set + " " + pl2_2_5set + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                setWinnerSet_5(playerSecond5Set, pointSPFiveSet5Set, "secondPlayer");
                setEnab();
            }

        } else if (SET_5_4_Set) {

            if ((y1 == 6 | y1 == 7) & y2 <= y1 - 2) {

                if ((SET_5_1_SET_a_DONE & SET_5_2_SET_a_DONE & SET_5_3_SET_b_DONE)
                        | (SET_5_1_SET_a_DONE & SET_5_2_SET_b_DONE & SET_5_3_SET_a_DONE)
                        | (SET_5_1_SET_b_DONE & SET_5_2_SET_a_DONE & SET_5_3_SET_a_DONE)) {

                    SET_5_MATCH_DONE = true;

                    Toast.makeText(this, pl1_1_5set + " " + pl1_2_5set + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                    setWinnerSet_5(playerFirst5Set, pointFPFourSet5Set, "firstPlayer");
                    setEnab();

                } else {

                    SET_5_4_Set = false;
                    SET_5_5_Set = true;
                    SET_5_4_SET_a_DONE = true;
                    pointFPFourSet5Set.setBackgroundResource(R.drawable.frame_winner);
                    Toast.makeText(this, pl1_1_5set + " " + pl1_2_5set + " " + getString(R.string.wins_4_set), Toast.LENGTH_LONG).show();
                }

            } else if ((y2 == 6 | y2 == 7) & y1 <= y2 - 2) {

                if ((SET_5_1_SET_b_DONE & SET_5_2_SET_a_DONE & SET_5_3_SET_b_DONE)
                        | (SET_5_1_SET_b_DONE & SET_5_2_SET_b_DONE & SET_5_3_SET_a_DONE)
                        | (SET_5_1_SET_a_DONE & SET_5_2_SET_b_DONE & SET_5_3_SET_b_DONE)) {

                    SET_5_MATCH_DONE = true;

                    Toast.makeText(this, pl2_1_5set + " " + pl2_2_5set + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                    setWinnerSet_5(playerSecond5Set, pointSPFourSet5Set, "secondPlayer");
                    setEnab();

                } else {

                    SET_5_4_Set = false;
                    SET_5_5_Set = true;
                    SET_5_4_SET_b_DONE = true;

                    pointSPFourSet5Set.setBackgroundResource(R.drawable.frame_winner);
                    Toast.makeText(this, pl2_1_5set + " " + pl2_2_5set + " " + getString(R.string.wins_4_set), Toast.LENGTH_LONG).show();
                }

            } else if (y2 == 6 & y1 == 6) {

                setTbmode();
            }
        } else if (SET_5_3_Set) {

            if ((y1 == 6 | y1 == 7) & y2 <= y1 - 2) {

                if (SET_5_1_SET_a_DONE & SET_5_2_SET_a_DONE) {

                    SET_5_MATCH_DONE = true;

                    Toast.makeText(this, pl1_1_5set + " " + pl1_2_5set + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                    setWinnerSet_5(playerFirst5Set, pointFPThirdSet5Set, "firstPlayer");
                    setEnab();

                } else {

                    SET_5_3_Set = false;
                    SET_5_4_Set = true;
                    SET_5_3_SET_a_DONE = true;

                    pointFPThirdSet5Set.setBackgroundResource(R.drawable.frame_winner);
                    Toast.makeText(this, pl1_1_5set + " " + pl1_2_5set + " " + getString(R.string.wins_3_set), Toast.LENGTH_LONG).show();

                }
            } else if ((y2 == 6 | y2 == 7) & y1 <= y2 - 2) {

                if (SET_5_1_SET_b_DONE & SET_5_2_SET_b_DONE) {

                    SET_5_MATCH_DONE = true;

                    Toast.makeText(this, pl2_1_5set + " " + pl2_2_5set + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                    setWinnerSet_5(playerSecond5Set, pointSPThirdSet5Set, "secondPlayer");
                    setEnab();

                } else {

                    SET_5_3_Set = false;
                    SET_5_4_Set = true;
                    SET_5_3_SET_b_DONE = true;

                    pointSPThirdSet5Set.setBackgroundResource(R.drawable.frame_winner);
                    Toast.makeText(this, pl2_1_5set + " " + pl2_2_5set + " " + getString(R.string.wins_3_set), Toast.LENGTH_LONG).show();
                }
            } else if (y2 == 6 & y1 == 6) {

                setTbmode();
            }
        } else if (SET_5_2_Set) {

            if ((y1 == 6 | y1 == 7) & y2 <= y1 - 2) {

                SET_5_2_Set = false;
                SET_5_3_Set = true;
                SET_5_2_SET_a_DONE = true;
                pointFPSecondSet5Set.setBackgroundResource(R.drawable.frame_winner);
                Toast.makeText(this, pl1_1_5set + " " + pl1_2_5set + " " + getString(R.string.wins_2_set), Toast.LENGTH_LONG).show();

            } else if ((y2 == 6 | y2 == 7) & y1 <= y2 - 2) {

                SET_5_2_Set = false;
                SET_5_3_Set = true;
                SET_5_2_SET_b_DONE = true;
                pointSPSecondSet5Set.setBackgroundResource(R.drawable.frame_winner);
                Toast.makeText(this, pl2_1_5set + " " + pl2_2_5set + " " + getString(R.string.wins_2_set), Toast.LENGTH_LONG).show();

            } else if (y2 == 6 & y1 == 6) {

                setTbmode();
            }
        } else if (SET_5_1_Set) {

            if ((y1 == 6 | y1 == 7) & y2 <= y1 - 2) {

                SET_5_1_Set = false;
                SET_5_2_Set = true;
                SET_5_1_SET_a_DONE = true;

                pointFPFirstSet5Set.setBackgroundResource(R.drawable.frame_winner);
                Toast.makeText(this, pl1_1_5set + " " + pl1_2_5set + " " + getString(R.string.wins_1_set), Toast.LENGTH_LONG).show();

            } else if ((y2 == 6 | y2 == 7) & y1 <= y2 - 2) {

                SET_5_1_Set = false;
                SET_5_2_Set = true;
                SET_5_1_SET_b_DONE = true;

                pointSPFirstSet5Set.setBackgroundResource(R.drawable.frame_winner);
                Toast.makeText(this, pl2_1_5set + " " + pl2_2_5set + " " + getString(R.string.wins_1_set), Toast.LENGTH_LONG).show();

            } else if (y2 == 6 & y1 == 6) {

                setTbmode();
            }
        }
    }

    public int curNumberOfPoints(int p) {

        SQLiteDatabase db = mDbHelper5Set.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT number FROM statistic  WHERE _ID = " + Integer.toString(mDbHelper5Set.getLastId() - p), new String[]{});
        cursor.moveToFirst();
        curNum5Set = (int) cursor.getLong(0);
        cursor.close();
        return curNum5Set;
    }

    public void setEnab() {
        goalFP5Set.setEnabled(false);
        goalSP5Set.setEnabled(false);
        spinner1p5s.setEnabled(false);
        spinner2p5s.setEnabled(false);
    }

    public void setTbmode() {
        Toast.makeText(getBaseContext(), getString(R.string.Tie_break_starts), Toast.LENGTH_SHORT).show();
        tb1pl_5Set = false;
        tb2pl_5Set = false;
        tbfp = 0;
        tbsp = 0;
        pointFP5Set.setText(String.valueOf(tbfp));
        pointSP5Set.setText(String.valueOf(tbsp));
        tb_5Set = true;
    }

    public void undo5Set(View view) {

        ad5Set = new AlertDialog.Builder(context);

        ad5Set.setMessage(getString(R.string.really_undo));

        ad5Set.setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                undo5Set1();
            }
        });
        ad5Set.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
            }
        });
        ad5Set.setCancelable(true);
        ad5Set.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
            }
        });
        ad5Set.show();
    }

    public void undo5Set1() {

        goalFP5Set.setEnabled(true);
        goalSP5Set.setEnabled(true);
        spinner1p5s.setEnabled(true);
        spinner2p5s.setEnabled(true);
        beEnabled(redo_5Set, getDrawable(R.drawable.ic_redo_black_24dp));
        noEnabled(saveButton5Set, getDrawable(R.drawable.ic_save_black_24dp_0_3));
        Set5StatusMatch = "Paused";
        playerFirst5Set.setBackgroundResource(R.drawable.frame_player);
        playerSecond5Set.setBackgroundResource(R.drawable.frame_player);
        stopMatchIsEnabled5Set = true;

        undoCounter5Set = undoCounter5Set + 1;

        updateColumnNumberDown5Set();

        SET_5_MATCH_DONE = false;

        if (getNum5Set("ktbfps").equals("-2")) {

            pointFP5Set.setText(adv_5Set);
            pointSP5Set.setText(getNum5Set("ktbsps"));

        } else if (getNum5Set("ktbsps").equals("-2")) {

            pointSP5Set.setText(adv_5Set);
            pointFP5Set.setText(getNum5Set("ktbfps"));

        } else {
            pointFP5Set.setText(getNum5Set("ktbfps"));
            pointSP5Set.setText(getNum5Set("ktbsps"));
        }

        if (getNum5Set("set1a").equals("7") & getNum5Set("set1b").equals("6")) {

            setUndoNum(pointFPFirstSet5Set, pointSPFirstSet5Set, "set1a", "set1b", A_pl_5Set_tb1, B_pl_5Set_tb1);


        } else if (getNum5Set("set1a").equals("6") & getNum5Set("set1b").equals("7")) {

            setUndoNum(pointFPFirstSet5Set, pointSPFirstSet5Set, "set1a", "set1b", A_pl_5Set_tb1, B_pl_5Set_tb1);

        } else {

            pointFPFirstSet5Set.setText(getNum5Set("set1a"));
            pointSPFirstSet5Set.setText(getNum5Set("set1b"));
        }

        if (getNum5Set("set2a").equals("7") & getNum5Set("set2b").equals("6")) {

            setUndoNum(pointFPSecondSet5Set, pointSPSecondSet5Set, "set2a", "set2b", A_pl_5Set_tb2, B_pl_5Set_tb2);

        } else if (getNum5Set("set2a").equals("6") & getNum5Set("set2b").equals("7")) {

            setUndoNum(pointFPSecondSet5Set, pointSPSecondSet5Set, "set2a", "set2b", A_pl_5Set_tb2, B_pl_5Set_tb2);

        } else {

            pointFPSecondSet5Set.setText(getNum5Set("set2a"));
            pointSPSecondSet5Set.setText(getNum5Set("set2b"));
        }

        if (getNum5Set("set3a").equals("7") & getNum5Set("set3b").equals("6")) {

            setUndoNum(pointFPThirdSet5Set, pointSPThirdSet5Set, "set3a", "set3b", A_pl_5Set_tb3, B_pl_5Set_tb3);

        } else if (getNum5Set("set3a").equals("6") & getNum5Set("set3b").equals("7")) {

            setUndoNum(pointFPThirdSet5Set, pointSPThirdSet5Set, "set3a", "set3b", A_pl_5Set_tb3, B_pl_5Set_tb3);

        } else {

            pointFPThirdSet5Set.setText(getNum5Set("set3a"));
            pointSPThirdSet5Set.setText(getNum5Set("set3b"));
        }

        if (getNum5Set("set4a").equals("7") & getNum5Set("set4b").equals("6")) {

            setUndoNum(pointFPFourSet5Set, pointSPFourSet5Set, "set4a", "set4b", A_pl_5Set_tb4, B_pl_5Set_tb4);

        } else if (getNum5Set("set4a").equals("6") & getNum5Set("set4b").equals("7")) {

            setUndoNum(pointFPFourSet5Set, pointSPFourSet5Set, "set4a", "set4b", A_pl_5Set_tb4, B_pl_5Set_tb4);

        } else {

            pointFPFourSet5Set.setText(getNum5Set("set4a"));
            pointSPFourSet5Set.setText(getNum5Set("set4b"));
        }

        pointFPFiveSet5Set.setText(getNum5Set("set5a"));
        pointSPFiveSet5Set.setText(getNum5Set("set5b"));

        if (SET_5_5_Set) {

            pointFPFiveSet5Set.setBackgroundResource(R.drawable.frame_player);
            pointSPFiveSet5Set.setBackgroundResource(R.drawable.frame_player);

            if ((getNum5Set("set4a").equals("7") & (getNum5Set("set4b").equals("6") | getNum5Set("set4b").equals("5"))) |
                    (getNum5Set("set4b").equals("7") & (getNum5Set("set4a").equals("6") | getNum5Set("set4a").equals("5"))) |
                    (getNum5Set("set4a").equals("6") & !"6".equals(getNum5Set("set4b")) & !"5".equals(getNum5Set("set4b"))) |
                    (getNum5Set("set4b").equals("6") & !"6".equals(getNum5Set("set4a")) & !"5".equals(getNum5Set("set4a")))) {

                SET_5_5_Set = true;

            } else {

                SET_5_5_Set = false;
                SET_5_4_Set = true;
                SET_5_4_SET_a_DONE = false;
                SET_5_4_SET_b_DONE = false;

            }
        }

        if (SET_5_4_Set) {

            pointFPFourSet5Set.setBackgroundResource(R.drawable.frame_player);
            pointSPFourSet5Set.setBackgroundResource(R.drawable.frame_player);

            SET_5_4_SET_a_DONE = false;
            SET_5_4_SET_b_DONE = false;

            if (SET_5_4_SET_tb_DONE) {

                SET_5_4_SET_tb_DONE = false;
                tb_5Set = true;
                tieBreakDone_5Set = false;
            }
            if (tb_5Set) {

                if (!"6".equals(getNum5Set("set4a")) | !"6".equals(getNum5Set("set4b"))) {
                    tb_5Set = false;
                }
            } else if (!tb_5Set) {

                if ((getNum5Set("set3a").equals("7") & (getNum5Set("set3b").equals("6") | getNum5Set("set3b").equals("5"))) |
                        (getNum5Set("set3b").equals("7") & (getNum5Set("set3a").equals("6") | getNum5Set("set3a").equals("5"))) |
                        (getNum5Set("set3a").equals("6") & !"6".equals(getNum5Set("set3b")) & !"5".equals(getNum5Set("set3b"))) |
                        (getNum5Set("set3b").equals("6") & !"6".equals(getNum5Set("set3a")) & !"5".equals(getNum5Set("set3a")))) {

                    SET_5_4_Set = true;

                } else {

                    SET_5_3_Set = true;
                    SET_5_4_Set = false;
                    SET_5_4_SET_a_DONE = false;
                    SET_5_4_SET_b_DONE = false;
                }
            }
        }
        if (SET_5_3_Set) {

            pointFPThirdSet5Set.setBackgroundResource(R.drawable.frame_player);
            pointSPThirdSet5Set.setBackgroundResource(R.drawable.frame_player);

            SET_5_3_SET_a_DONE = false;
            SET_5_3_SET_b_DONE = false;

            if (SET_5_3_SET_tb_DONE) {

                SET_5_3_SET_tb_DONE = false;
                tb_5Set = true;
                tieBreakDone_5Set = false;
            }
            if (tb_5Set) {

                if (!"6".equals(getNum5Set("set3a")) | !"6".equals(getNum5Set("set3b"))) {
                    tb_5Set = false;

                }
            } else if (!tb_5Set) {

                if ((getNum5Set("set2a").equals("7") & (getNum5Set("set2b").equals("6") | getNum5Set("set2b").equals("5"))) |
                        (getNum5Set("set2b").equals("7") & (getNum5Set("set2a").equals("6") | getNum5Set("set2a").equals("5"))) |
                        (getNum5Set("set2a").equals("6") & !"6".equals(getNum5Set("set2b")) & !"5".equals(getNum5Set("set2b"))) |
                        (getNum5Set("set2b").equals("6") & !"6".equals(getNum5Set("set2a")) & !"5".equals(getNum5Set("set2a")))) {

                    SET_5_3_Set = true;

                } else {

                    SET_5_2_Set = true;
                    SET_5_3_Set = false;
                    SET_5_2_SET_a_DONE = false;
                    SET_5_2_SET_b_DONE = false;
                }
            }
        }

        if (SET_5_2_Set) {

            pointFPSecondSet5Set.setBackgroundResource(R.drawable.frame_player);
            pointSPSecondSet5Set.setBackgroundResource(R.drawable.frame_player);

            SET_5_2_SET_a_DONE = false;
            SET_5_2_SET_b_DONE = false;

            if (SET_5_2_SET_tb_DONE) {

                SET_5_2_SET_tb_DONE = false;
                tb_5Set = true;
                tieBreakDone_5Set = false;
            }
            if (tb_5Set) {

                if (!"6".equals(getNum5Set("set2a")) | !"6".equals(getNum5Set("set2b"))) {
                    tb_5Set = false;

                }
            } else if (!tb_5Set) {

                if ((getNum5Set("set1a").equals("7") & (getNum5Set("set1b").equals("6") | getNum5Set("set1b").equals("5"))) |
                        (getNum5Set("set1b").equals("7") & (getNum5Set("set1a").equals("6") | getNum5Set("set1a").equals("5"))) |
                        (getNum5Set("set1a").equals("6") & !"6".equals(getNum5Set("set1b")) & !"5".equals(getNum5Set("set1b"))) |
                        (getNum5Set("set1b").equals("6") & !"6".equals(getNum5Set("set1a")) & !"5".equals(getNum5Set("set1a")))) {

                    SET_5_2_Set = true;

                } else {

                    SET_5_1_Set = true;
                    SET_5_2_Set = false;
                    SET_5_1_SET_a_DONE = false;
                    SET_5_1_SET_b_DONE = false;
                }
            }
        }


        if (SET_5_1_Set) {

            pointFPFirstSet5Set.setBackgroundResource(R.drawable.frame_player);
            pointSPFirstSet5Set.setBackgroundResource(R.drawable.frame_player);

            if (SET_5_1_SET_tb_DONE) {
                SET_5_1_SET_tb_DONE = false;
                tb_5Set = true;
                tieBreakDone_5Set = false;
            }

            if (tb_5Set) {
                if (!"6".equals(getNum5Set("set1a")) | !"6".equals(getNum5Set("set1b"))) {
                    tb_5Set = false;
                }
            }
        }
        goalsCounter5Set--;
        if (goalsCounter5Set <= 0) {
            noEnabled(undo_5Set, getDrawable(R.drawable.ic_undo_black_24dp_0_3));
        }
    }

    public String getNum5Set(String jg) {

        SQLiteDatabase db = mDbHelper5Set.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT " + jg + " FROM "
                + Contract.UndoNumbers.TABLE_NAME_1 + " WHERE " + Contract.UndoNumbers.COLUMN_NUM_NUM +
                " = (SELECT MAX( " + Contract.UndoNumbers.COLUMN_NUM_NUM + " ) FROM " + Contract.UndoNumbers.TABLE_NAME_1 + " ) - " + undoCounter5Set, new String[]{});
        cursor.moveToFirst();
        prevNum5Set = (int) cursor.getLong(0);
        cursor.close();
        return String.valueOf(prevNum5Set);
    }

    public void setUndoNum(TextView tv1, TextView tv2, String st1, String st2, int tn1, int tn2) {
        tv1.setText(getNum5Set(st1));
        tv2.setText(getNum5Set(st2));
        convertUndo(tn1, tn2, tv1, tv2);
    }

    public void convertUndo(int mp1, int yu1, TextView s11, TextView s21) {
        for (int i = 0; i < String.valueOf(mp1).length(); i++) {
            int k = String.valueOf(mp1).length() - i;
            r1 = degreeDigits[Character.getNumericValue(String.valueOf(mp1).charAt(k - 1))] + r1;
        }

        for (int kol = 0; kol < String.valueOf(yu1).length(); kol++) {
            int kik = String.valueOf(yu1).length() - kol;
            r2 = degreeDigits[Character.getNumericValue(String.valueOf(yu1).charAt(kik - 1))] + r2;
        }
        if (s11.getText().equals("7")) {

            s11.setBackgroundResource(R.drawable.frame_winner);
        } else {
            s21.setBackgroundResource(R.drawable.frame_winner);
        }
        s11.setText(s11.getText() + r1);
        s21.setText(s21.getText() + r2);
        r1 = "";
        r2 = "";
    }

    public void deleteRow5Set() {
        beEnabled(undo_5Set, getDrawable(R.drawable.ic_undo_black_24dp));
        SQLiteDatabase db = mDbHelper5Set.getReadableDatabase();
        String insertQuery = " DELETE FROM " + Contract.UndoNumbers.TABLE_NAME_1 + " WHERE " + Contract.UndoNumbers.COLUMN_NUM_NUM +
                " IN ( SELECT " + Contract.UndoNumbers.COLUMN_NUM_NUM + " FROM " + Contract.UndoNumbers.TABLE_NAME_1 +
                " ORDER BY " + Contract.UndoNumbers.COLUMN_NUM_NUM + " DESC LIMIT " + undoCounter5Set + ")";
        db.execSQL(insertQuery);
        undoCounter5Set = 0;
    }

    public void redo5Set(View view) {

        ad5Set = new AlertDialog.Builder(context);

        ad5Set.setMessage(getString(R.string.really_redo));

        ad5Set.setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                redo5Set1();
            }
        });
        ad5Set.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
            }
        });
        ad5Set.setCancelable(true);
        ad5Set.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
            }
        });
        ad5Set.show();
    }

    public void redo5Set1() {

        beEnabled(undo_5Set, getDrawable(R.drawable.ic_redo_black_24dp));
        spinner1p5s.setSelection(0);
        spinner2p5s.setSelection(0);

        updateColumnNumberUp5Set();

        undoCounter5Set = undoCounter5Set - 1;

        if (getNum5Set("ktbfps").equals("-2")) {

            pointFP5Set.setText(adv_5Set);
            pointSP5Set.setText(getNum5Set("ktbsps"));

        } else if (getNum5Set("ktbsps").equals("-2")) {

            pointFP5Set.setText(getNum5Set("ktbfps"));
            pointSP5Set.setText(adv_5Set);

        } else {

            pointFP5Set.setText(getNum5Set("ktbfps"));
            pointSP5Set.setText(getNum5Set("ktbsps"));
        }

        if ((getNum5Set("set1a").equals("7") & getNum5Set("set1b").equals("6")) | (getNum5Set("set1a").equals("6") & getNum5Set("set1b").equals("7"))) {

            setUndoNum(pointFPFirstSet5Set, pointSPFirstSet5Set, "set1a", "set1b", A_pl_5Set_tb1, B_pl_5Set_tb1);

        } else {

            pointFPFirstSet5Set.setText(getNum5Set("set1a"));
            pointSPFirstSet5Set.setText(getNum5Set("set1b"));

        }

        if ((getNum5Set("set2a").equals("7") & getNum5Set("set2b").equals("6")) | (getNum5Set("set2a").equals("6") & getNum5Set("set2b").equals("7"))) {

            setUndoNum(pointFPSecondSet5Set, pointSPSecondSet5Set, "set2a", "set2b", A_pl_5Set_tb2, B_pl_5Set_tb2);

        } else {

            pointFPSecondSet5Set.setText(getNum5Set("set2a"));
            pointSPSecondSet5Set.setText(getNum5Set("set2b"));

        }

        if ((getNum5Set("set3a").equals("7") & getNum5Set("set3b").equals("6")) | (getNum5Set("set3a").equals("6") & getNum5Set("set3b").equals("7"))) {

            setUndoNum(pointFPThirdSet5Set, pointSPThirdSet5Set, "set3a", "set3b", A_pl_5Set_tb3, B_pl_5Set_tb3);

        } else {

            pointFPThirdSet5Set.setText(getNum5Set("set3a"));
            pointSPThirdSet5Set.setText(getNum5Set("set3b"));

        }

        if ((getNum5Set("set4a").equals("7") & getNum5Set("set4b").equals("6")) | (getNum5Set("set4a").equals("6") & getNum5Set("set4b").equals("7"))) {

            setUndoNum(pointFPFourSet5Set, pointSPFourSet5Set, "set4a", "set4b", A_pl_5Set_tb4, B_pl_5Set_tb4);

        } else {

            pointFPFourSet5Set.setText(getNum5Set("set4a"));
            pointSPFourSet5Set.setText(getNum5Set("set4b"));

        }

        pointFPFiveSet5Set.setText(getNum5Set("set5a"));
        pointSPFiveSet5Set.setText(getNum5Set("set5b"));


        if (SET_5_1_Set) {

            if (getNum5Set("set1a").equals("6") & getNum5Set("set1b").equals("6")) {

                tb_5Set = true;
                checkTieBreakNumber(Integer.valueOf(getNum5Set("ktbfps")), Integer.valueOf(getNum5Set("ktbsps")));

                if (getNum5Set("ktbfps").equals("0") && getNum5Set("ktbsps").equals("0")) {

                    Toast.makeText(getBaseContext(), getString(R.string.Tie_break_starts), Toast.LENGTH_SHORT).show();
                }

            } else if (getNum5Set("set1a").equals("7") & getNum5Set("set1b").equals("6") & getNum5Set("ktbfps").equals("0") & getNum5Set("ktbsps").equals("0")) {

                tb_5Set = false;
                SET_5_1_SET_a_DONE = true;
                SET_5_1_SET_tb_DONE = true;
                tieBreakDone_5Set = true;
                SET_5_1_Set = false;
                SET_5_2_Set = true;

                pointFPFirstSet5Set.setBackgroundResource(R.drawable.frame_winner);
                Toast.makeText(this, pl1_1_5set + " " + pl1_2_5set + " " + getString(R.string.wins_1_set), Toast.LENGTH_LONG).show();

            } else if (getNum5Set("set1a").equals("7") & getNum5Set("set1b").equals("6")
                    & (!"0".equals(getNum5Set("ktbfps")) | !"0".equals(getNum5Set("ktbsps")))) {

                tieBreakDone_5Set = false;

            } else if (getNum5Set("set1a").equals("6") & getNum5Set("set1b").equals("7")
                    & (!"0".equals(getNum5Set("ktbfps")) | !"0".equals(getNum5Set("ktbsps")))) {

                tieBreakDone_5Set = false;

            } else if (getNum5Set("set1a").equals("6") & getNum5Set("set1b").equals("7") & getNum5Set("ktbfps").equals("0") & getNum5Set("ktbsps").equals("0")) {

                tb_5Set = false;
                SET_5_1_SET_b_DONE = true;
                SET_5_1_SET_tb_DONE = true;
                tieBreakDone_5Set = true;
                SET_5_1_Set = false;
                SET_5_2_Set = true;

                pointSPFirstSet5Set.setBackgroundResource(R.drawable.frame_winner);
                Toast.makeText(this, pl2_1_5set + " " + pl2_2_5set + " " + getString(R.string.wins_1_set), Toast.LENGTH_LONG).show();

            } else {
                checkSetNumber(Integer.valueOf(getNum5Set("set1a")), Integer.valueOf(getNum5Set("set1b")));
            }
        }

        if (SET_5_2_Set) {

            if (getNum5Set("set2a").equals("6") & getNum5Set("set2b").equals("6")) {

                tb_5Set = true;

                checkTieBreakNumber(Integer.valueOf(getNum5Set("ktbfps")), Integer.valueOf(getNum5Set("ktbsps")));

                if (getNum5Set("ktbfps").equals("0") && getNum5Set("ktbsps").equals("0")) {

                    Toast.makeText(getBaseContext(), getString(R.string.Tie_break_starts), Toast.LENGTH_SHORT).show();
                }

            } else if (getNum5Set("set1a").equals("7") & getNum5Set("set1b").equals("6")

                    & (!"0".equals(getNum5Set("ktbfps")) | !"0".equals(getNum5Set("ktbsps")))) {

                tieBreakDone_5Set = false;

            } else if (getNum5Set("set1a").equals("6") & getNum5Set("set1b").equals("7")

                    & (!"0".equals(getNum5Set("ktbfps")) | !"0".equals(getNum5Set("ktbsps")))) {

                tieBreakDone_5Set = false;

            } else if (getNum5Set("set2a").equals("7") & getNum5Set("set2b").equals("6") & getNum5Set("ktbfps").equals("0") & getNum5Set("ktbsps").equals("0")) {

                SET_5_2_SET_a_DONE = true;
                SET_5_2_SET_tb_DONE = true;
                SET_5_2_Set = false;
                SET_5_3_Set = true;
                tb_5Set = false;
                tieBreakDone_5Set = true;

                pointFPSecondSet5Set.setBackgroundResource(R.drawable.frame_winner);
                Toast.makeText(this, pl1_1_5set + " " + pl1_2_5set + " " + getString(R.string.wins_2_set), Toast.LENGTH_LONG).show();

            } else if (getNum5Set("set2a").equals("6") & getNum5Set("set2b").equals("7") & getNum5Set("ktbfps").equals("0") & getNum5Set("ktbsps").equals("0")) {

                SET_5_2_SET_b_DONE = true;
                SET_5_2_SET_tb_DONE = true;
                SET_5_2_Set = false;
                SET_5_3_Set = true;
                tb_5Set = false;
                tieBreakDone_5Set = true;

                pointSPSecondSet5Set.setBackgroundResource(R.drawable.frame_winner);
                Toast.makeText(this, pl2_1_5set + " " + pl2_2_5set + " " + getString(R.string.wins_2_set), Toast.LENGTH_LONG).show();

            } else {
                checkSetNumber(Integer.valueOf(getNum5Set("set2a")), Integer.valueOf(getNum5Set("set2b")));
            }
        }

        if (SET_5_3_Set) {

            if (getNum5Set("set3a").equals("6") & getNum5Set("set3b").equals("6")) {

                tb_5Set = true;

                checkTieBreakNumber(Integer.valueOf(getNum5Set("ktbfps")), Integer.valueOf(getNum5Set("ktbsps")));

                if (getNum5Set("ktbfps").equals("0") && getNum5Set("ktbsps").equals("0")) {

                    Toast.makeText(getBaseContext(), getString(R.string.Tie_break_starts), Toast.LENGTH_SHORT).show();
                }

            } else if (getNum5Set("set2a").equals("7") & getNum5Set("set2b").equals("6")

                    & (!"0".equals(getNum5Set("ktbfps")) | !"0".equals(getNum5Set("ktbsps")))) {

                tieBreakDone_5Set = false;

            } else if (getNum5Set("set2a").equals("6") & getNum5Set("set2b").equals("7")

                    & (!"0".equals(getNum5Set("ktbfps")) | !"0".equals(getNum5Set("ktbsps")))) {

                tieBreakDone_5Set = false;

            } else if (getNum5Set("set3a").equals("7") & getNum5Set("set3b").equals("6") & getNum5Set("ktbfps").equals("0") & getNum5Set("ktbsps").equals("0")) {

                if (SET_5_1_SET_a_DONE & SET_5_2_SET_a_DONE) {

                    SET_5_MATCH_DONE = true;
                    SET_5_3_SET_tb_DONE = true;
                    tb_5Set = false;
                    tieBreakDone_5Set = true;

                    Toast.makeText(this, pl1_1_5set + " " + pl1_2_5set + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                    setWinnerSet_5(playerFirst5Set, pointFPThirdSet5Set, "firstPlayer");
                    setEnab();

                } else {

                    SET_5_3_SET_a_DONE = true;
                    SET_5_3_SET_tb_DONE = true;
                    tb_5Set = false;
                    tieBreakDone_5Set = true;
                    SET_5_3_Set = false;
                    SET_5_4_Set = true;

                    Toast.makeText(this, pl1_1_5set + " " + pl1_2_5set + " " + getString(R.string.wins_3_set), Toast.LENGTH_LONG).show();
                    pointFPThirdSet5Set.setBackgroundResource(R.drawable.frame_winner);
                }
            } else if (getNum5Set("set3a").equals("6") & getNum5Set("set3b").equals("7") & getNum5Set("ktbfps").equals("0") & getNum5Set("ktbsps").equals("0")) {

                if (SET_5_1_SET_b_DONE & SET_5_2_SET_b_DONE) {

                    SET_5_MATCH_DONE = true;
                    SET_5_3_SET_tb_DONE = true;
                    tb_5Set = false;
                    tieBreakDone_5Set = true;

                    Toast.makeText(this, pl2_1_5set + " " + pl2_2_5set + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                    setWinnerSet_5(playerSecond5Set, pointSPThirdSet5Set, "secondPlayer");
                    setEnab();

                } else {

                    SET_5_3_SET_b_DONE = true;
                    SET_5_3_SET_tb_DONE = true;
                    tb_5Set = false;
                    tieBreakDone_5Set = true;
                    SET_5_3_Set = false;
                    SET_5_4_Set = true;

                    Toast.makeText(this, pl2_1_5set + " " + pl2_2_5set + " " + getString(R.string.wins_3_set), Toast.LENGTH_LONG).show();
                    pointSPThirdSet5Set.setBackgroundResource(R.drawable.frame_winner);
                }
            } else {
                checkSetNumber(Integer.valueOf(getNum5Set("set3a")), Integer.valueOf(getNum5Set("set3b")));
            }
        }

        if (SET_5_4_Set) {

            if (getNum5Set("set4a").equals("6") & getNum5Set("set4b").equals("6")) {

                tb_5Set = true;

                checkTieBreakNumber(Integer.valueOf(getNum5Set("ktbfps")), Integer.valueOf(getNum5Set("ktbsps")));

                if (getNum5Set("ktbfps").equals("0") && getNum5Set("ktbsps").equals("0")) {

                    Toast.makeText(getBaseContext(), getString(R.string.Tie_break_starts), Toast.LENGTH_SHORT).show();
                }

            } else if (getNum5Set("set3a").equals("7") & getNum5Set("set3b").equals("6")

                    & (!"0".equals(getNum5Set("ktbfps")) | !"0".equals(getNum5Set("ktbsps")))) {

                tieBreakDone_5Set = false;

            } else if (getNum5Set("set3a").equals("6") & getNum5Set("set3b").equals("7")

                    & (!"0".equals(getNum5Set("ktbfps")) | !"0".equals(getNum5Set("ktbsps")))) {

                tieBreakDone_5Set = false;

            } else if (getNum5Set("set4a").equals("7") & getNum5Set("set4b").equals("6") & getNum5Set("ktbfps").equals("0") & getNum5Set("ktbsps").equals("0")) {

                if ((SET_5_1_SET_a_DONE & SET_5_2_SET_a_DONE & SET_5_3_SET_b_DONE)
                        | (SET_5_1_SET_a_DONE & SET_5_2_SET_b_DONE & SET_5_3_SET_a_DONE)
                        | (SET_5_1_SET_b_DONE & SET_5_2_SET_a_DONE & SET_5_3_SET_a_DONE)) {

                    SET_5_MATCH_DONE = true;
                    SET_5_4_SET_tb_DONE = true;
                    tb_5Set = false;
                    tieBreakDone_5Set = true;

                    Toast.makeText(this, pl1_1_5set + " " + pl1_2_5set + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                    setWinnerSet_5(playerFirst5Set, pointFPFourSet5Set, "firstPlayer");
                    setEnab();

                } else {

                    SET_5_4_SET_a_DONE = true;
                    SET_5_4_SET_tb_DONE = true;
                    tb_5Set = false;
                    tieBreakDone_5Set = true;
                    SET_5_4_Set = false;
                    SET_5_5_Set = true;

                    Toast.makeText(this, pl1_1_5set + " " + pl1_2_5set + " " + getString(R.string.wins_4_set), Toast.LENGTH_LONG).show();
                    pointFPFourSet5Set.setBackgroundResource(R.drawable.frame_winner);
                }
            } else if (getNum5Set("set4a").equals("6") & getNum5Set("set4b").equals("7") & getNum5Set("ktbfps").equals("0") & getNum5Set("ktbsps").equals("0")) {

                if ((SET_5_1_SET_b_DONE & SET_5_2_SET_a_DONE & SET_5_3_SET_b_DONE)
                        | (SET_5_1_SET_b_DONE & SET_5_2_SET_b_DONE & SET_5_3_SET_a_DONE)
                        | (SET_5_1_SET_a_DONE & SET_5_2_SET_b_DONE & SET_5_3_SET_b_DONE)) {

                    SET_5_MATCH_DONE = true;
                    SET_5_4_SET_tb_DONE = true;
                    tb_5Set = false;
                    tieBreakDone_5Set = true;

                    Toast.makeText(this, pl2_1_5set + " " + pl2_2_5set + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                    setWinnerSet_5(playerSecond5Set, pointSPFourSet5Set, "secondPlayer");
                    setEnab();

                } else {

                    SET_5_4_SET_b_DONE = true;
                    SET_5_4_SET_tb_DONE = true;
                    tb_5Set = false;
                    tieBreakDone_5Set = true;
                    SET_5_4_Set = false;
                    SET_5_5_Set = true;

                    Toast.makeText(this, pl2_1_5set + " " + pl2_2_5set + " " + getString(R.string.wins_4_set), Toast.LENGTH_LONG).show();
                    pointSPFourSet5Set.setBackgroundResource(R.drawable.frame_winner);
                }
            } else {
                checkSetNumber(Integer.valueOf(getNum5Set("set4a")), Integer.valueOf(getNum5Set("set4b")));
            }
        }
        if (SET_5_5_Set) {

            checkSetNumber(Integer.valueOf(getNum5Set("set5a")), Integer.valueOf(getNum5Set("set5b")));
        }

        if (undoCounter5Set == 0) {
            noEnabled(redo_5Set, getDrawable(R.drawable.ic_redo_black_24dp_0_3));
        }

        goalsCounter5Set++;
        if (goalsCounter5Set > 0) {
            beEnabled(undo_5Set, getDrawable(R.drawable.ic_undo_black_24dp));
        }
    }

    public void updateColumnNumberDown5Set() {

        SQLiteDatabase db = mDbHelper5Set.getWritableDatabase();
        String insertQuery = " UPDATE " + Contract.ContractNames.TABLE_NAME + " SET " + Contract.ContractNames.COLUMN_NUMBER
                + " = ((SELECT " + Contract.ContractNames.COLUMN_NUMBER + " FROM " + Contract.ContractNames.TABLE_NAME +
                " WHERE " + Contract.ContractNames._ID + " = " + getLastIdfromUndoTable1Set() + ") - 1 ) WHERE " + Contract.ContractNames._ID + " = " + getLastIdfromUndoTable1Set();
        db.execSQL(insertQuery);
    }

    public int getLastIdfromUndoTable1Set() {

        SQLiteDatabase db = mDbHelper5Set.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT " + Contract.UndoNumbers.COLUMN_NUM_ID + " FROM "
                + Contract.UndoNumbers.TABLE_NAME_1 + " WHERE " + Contract.UndoNumbers.COLUMN_NUM_NUM +
                " = (SELECT MAX( " + Contract.UndoNumbers.COLUMN_NUM_NUM + " ) FROM " + Contract.UndoNumbers.TABLE_NAME_1 + " ) - " + (undoCounter5Set - 1), new String[]{});
        cursor.moveToFirst();
        lastIdfromUT5Set = (int) cursor.getLong(0);
        cursor.close();
        return lastIdfromUT5Set;
    }

    public void updateColumnNumberUp5Set() {
        SQLiteDatabase db = mDbHelper5Set.getWritableDatabase();
        String insertQuery = " UPDATE " + Contract.ContractNames.TABLE_NAME + " SET " + Contract.ContractNames.COLUMN_NUMBER
                + " = ((SELECT " + Contract.ContractNames.COLUMN_NUMBER + " FROM " + Contract.ContractNames.TABLE_NAME +
                " WHERE " + Contract.ContractNames._ID + " = " + getLastIdfromUndoTable1Set() + ") + 1 ) WHERE " + Contract.ContractNames._ID + " = " + getLastIdfromUndoTable1Set();
        db.execSQL(insertQuery);
    }

    public void noSaveResultGoToMM() {
        mDbHelper5Set.clearTable(Contract.UndoNumbers.TABLE_NAME_1);
        mDbHelper5Set.clearTable(Contract.ContractNames.TABLE_NAME);
        finish();
        Intent intent1 = new Intent(this, AA_MainActivity.class);
        startActivity(intent1);
    }

    public void goToHelp() {
        Intent helpIntent = new Intent(this, DataBase.class);
        startActivity(helpIntent);
    }

    public void saveResultGoToMM() {
        saveResult5Set();
        mDbHelper5Set.clearTable(Contract.UndoNumbers.TABLE_NAME_1);
        mDbHelper5Set.clearTable(Contract.ContractNames.TABLE_NAME);
        finish();
        Intent intent1 = new Intent(this, AA_MainActivity.class);
        startActivity(intent1);
    }

    public void saveResult5Set() {

        if (getIntent().getExtras().getBoolean("isNew")) {

            mDbHelper5Set.putName13("fullName", "matchMode", "courtSurface", pl1_1_5set, pl1_2_5set, pl2_1_5set, pl2_2_5set, Set5StatusMatch,
                    mDbHelper5Set.getStringFromNumDataFromUndoTable("ktbfps", undoCounter5Set), mDbHelper5Set.getStringFromNumDataFromUndoTable("ktbsps", undoCounter5Set),
                    mDbHelper5Set.getStringFromNumDataFromUndoTable("set1a", undoCounter5Set), A_pl_5Set_tb1, mDbHelper5Set.getStringFromNumDataFromUndoTable("set1b", undoCounter5Set), B_pl_5Set_tb1,
                    mDbHelper5Set.getStringFromNumDataFromUndoTable("set2a", undoCounter5Set), A_pl_5Set_tb2, mDbHelper5Set.getStringFromNumDataFromUndoTable("set2b", undoCounter5Set), B_pl_5Set_tb2,
                    mDbHelper5Set.getStringFromNumDataFromUndoTable("set3a", undoCounter5Set), A_pl_5Set_tb3, mDbHelper5Set.getStringFromNumDataFromUndoTable("set3b", undoCounter5Set), B_pl_5Set_tb3,
                    mDbHelper5Set.getStringFromNumDataFromUndoTable("set4a", undoCounter5Set), A_pl_5Set_tb4, mDbHelper5Set.getStringFromNumDataFromUndoTable("set4b", undoCounter5Set), B_pl_5Set_tb4,
                    mDbHelper5Set.getStringFromNumDataFromUndoTable("set5a", undoCounter5Set), -2, mDbHelper5Set.getStringFromNumDataFromUndoTable("set5b", undoCounter5Set), -2,
                    7, 3, 6, 2, 5, 1, 4, 0, getDFB_5Set(tb1pl_5Set), getDFB_5Set(tb2pl_5Set), getDFB_5Set(tieBreakDone_5Set), getDFB_5Set(stopMatchIsEnabled5Set),
                    getDFB_5Set(tb_5Set), getDFB_5Set(SET_5_1_SET_a_DONE), getDFB_5Set(SET_5_1_SET_b_DONE), getDFB_5Set(SET_5_2_SET_a_DONE),
                    getDFB_5Set(SET_5_2_SET_b_DONE), getDFB_5Set(SET_5_3_SET_a_DONE), getDFB_5Set(SET_5_3_SET_b_DONE), getDFB_5Set(SET_5_4_SET_a_DONE),
                    getDFB_5Set(SET_5_4_SET_b_DONE), getDFB_5Set(SET_5_1_Set), getDFB_5Set(SET_5_2_Set), getDFB_5Set(SET_5_3_Set), getDFB_5Set(SET_5_4_Set),
                    getDFB_5Set(SET_5_5_Set), getDFB_5Set(SET_5_1_SET_tb_DONE), getDFB_5Set(SET_5_2_SET_tb_DONE), getDFB_5Set(SET_5_3_SET_tb_DONE),
                    getDFB_5Set(SET_5_4_SET_tb_DONE), 0, getDFB_5Set(SET_5_MATCH_DONE));
        } else {
            mDbHelper5Set.putName122("fullName", "matchMode", "courtSurface", pl1_1_5set, pl1_2_5set, pl2_1_5set, pl2_2_5set, Set5StatusMatch,
                    mDbHelper5Set.getStringFromNumDataFromUndoTable("ktbfps", undoCounter5Set), mDbHelper5Set.getStringFromNumDataFromUndoTable("ktbsps", undoCounter5Set),
                    mDbHelper5Set.getStringFromNumDataFromUndoTable("set1a", undoCounter5Set), A_pl_5Set_tb1, mDbHelper5Set.getStringFromNumDataFromUndoTable("set1b", undoCounter5Set), B_pl_5Set_tb1,
                    mDbHelper5Set.getStringFromNumDataFromUndoTable("set2a", undoCounter5Set), A_pl_5Set_tb2, mDbHelper5Set.getStringFromNumDataFromUndoTable("set2b", undoCounter5Set), B_pl_5Set_tb2,
                    mDbHelper5Set.getStringFromNumDataFromUndoTable("set3a", undoCounter5Set), A_pl_5Set_tb3, mDbHelper5Set.getStringFromNumDataFromUndoTable("set3b", undoCounter5Set), B_pl_5Set_tb3,
                    mDbHelper5Set.getStringFromNumDataFromUndoTable("set4a", undoCounter5Set), A_pl_5Set_tb4, mDbHelper5Set.getStringFromNumDataFromUndoTable("set4b", undoCounter5Set), B_pl_5Set_tb4,
                    mDbHelper5Set.getStringFromNumDataFromUndoTable("set5a", undoCounter5Set), -2, mDbHelper5Set.getStringFromNumDataFromUndoTable("set5b", undoCounter5Set), -2,
                    7, 3, 6, 2, 5, 1, 4, 0, getDFB_5Set(tb1pl_5Set), getDFB_5Set(tb2pl_5Set), getDFB_5Set(tieBreakDone_5Set), getDFB_5Set(stopMatchIsEnabled5Set),
                    getDFB_5Set(tb_5Set), getDFB_5Set(SET_5_1_SET_a_DONE), getDFB_5Set(SET_5_1_SET_b_DONE), getDFB_5Set(SET_5_2_SET_a_DONE),
                    getDFB_5Set(SET_5_2_SET_b_DONE), getDFB_5Set(SET_5_3_SET_a_DONE), getDFB_5Set(SET_5_3_SET_b_DONE), getDFB_5Set(SET_5_4_SET_a_DONE),
                    getDFB_5Set(SET_5_4_SET_b_DONE), getDFB_5Set(SET_5_1_Set), getDFB_5Set(SET_5_2_Set), getDFB_5Set(SET_5_3_Set), getDFB_5Set(SET_5_4_Set),
                    getDFB_5Set(SET_5_5_Set), getDFB_5Set(SET_5_1_SET_tb_DONE), getDFB_5Set(SET_5_2_SET_tb_DONE), getDFB_5Set(SET_5_3_SET_tb_DONE),
                    getDFB_5Set(SET_5_4_SET_tb_DONE), 0, getDFB_5Set(SET_5_MATCH_DONE));

        }

    }

    public void saveResultFromStat(String st) {

        Set5StatusMatch = st;
        if (getIntent().getExtras().getBoolean("isNew")) {

            mDbHelper5Set.putName13("fullName", "matchMode", "courtSurface", pl1_1_5set, pl1_2_5set, pl2_1_5set, pl2_2_5set, Set5StatusMatch,
                    mDbHelper5Set.getStringFromNumDataFromUndoTable("ktbfps", undoCounter5Set), mDbHelper5Set.getStringFromNumDataFromUndoTable("ktbsps", undoCounter5Set),
                    mDbHelper5Set.getStringFromNumDataFromUndoTable("set1a", undoCounter5Set), A_pl_5Set_tb1, mDbHelper5Set.getStringFromNumDataFromUndoTable("set1b", undoCounter5Set), B_pl_5Set_tb1,
                    mDbHelper5Set.getStringFromNumDataFromUndoTable("set2a", undoCounter5Set), A_pl_5Set_tb2, mDbHelper5Set.getStringFromNumDataFromUndoTable("set2b", undoCounter5Set), B_pl_5Set_tb2,
                    mDbHelper5Set.getStringFromNumDataFromUndoTable("set3a", undoCounter5Set), A_pl_5Set_tb3, mDbHelper5Set.getStringFromNumDataFromUndoTable("set3b", undoCounter5Set), B_pl_5Set_tb3,
                    mDbHelper5Set.getStringFromNumDataFromUndoTable("set4a", undoCounter5Set), A_pl_5Set_tb4, mDbHelper5Set.getStringFromNumDataFromUndoTable("set4b", undoCounter5Set), B_pl_5Set_tb4,
                    mDbHelper5Set.getStringFromNumDataFromUndoTable("set5a", undoCounter5Set), -2, mDbHelper5Set.getStringFromNumDataFromUndoTable("set5b", undoCounter5Set), -2,
                    7, 3, 6, 2, 5, 1, 4, 0, getDFB_5Set(tb1pl_5Set), getDFB_5Set(tb2pl_5Set), getDFB_5Set(tieBreakDone_5Set), getDFB_5Set(stopMatchIsEnabled5Set),
                    getDFB_5Set(tb_5Set), getDFB_5Set(SET_5_1_SET_a_DONE), getDFB_5Set(SET_5_1_SET_b_DONE), getDFB_5Set(SET_5_2_SET_a_DONE),
                    getDFB_5Set(SET_5_2_SET_b_DONE), getDFB_5Set(SET_5_3_SET_a_DONE), getDFB_5Set(SET_5_3_SET_b_DONE), getDFB_5Set(SET_5_4_SET_a_DONE),
                    getDFB_5Set(SET_5_4_SET_b_DONE), getDFB_5Set(SET_5_1_Set), getDFB_5Set(SET_5_2_Set), getDFB_5Set(SET_5_3_Set), getDFB_5Set(SET_5_4_Set),
                    getDFB_5Set(SET_5_5_Set), getDFB_5Set(SET_5_1_SET_tb_DONE), getDFB_5Set(SET_5_2_SET_tb_DONE), getDFB_5Set(SET_5_3_SET_tb_DONE),
                    getDFB_5Set(SET_5_4_SET_tb_DONE), 0, getDFB_5Set(SET_5_MATCH_DONE));
        } else {
            mDbHelper5Set.putName122("fullName", "matchMode", "courtSurface", pl1_1_5set, pl1_2_5set, pl2_1_5set, pl2_2_5set, Set5StatusMatch,
                    mDbHelper5Set.getStringFromNumDataFromUndoTable("ktbfps", undoCounter5Set), mDbHelper5Set.getStringFromNumDataFromUndoTable("ktbsps", undoCounter5Set),
                    mDbHelper5Set.getStringFromNumDataFromUndoTable("set1a", undoCounter5Set), A_pl_5Set_tb1, mDbHelper5Set.getStringFromNumDataFromUndoTable("set1b", undoCounter5Set), B_pl_5Set_tb1,
                    mDbHelper5Set.getStringFromNumDataFromUndoTable("set2a", undoCounter5Set), A_pl_5Set_tb2, mDbHelper5Set.getStringFromNumDataFromUndoTable("set2b", undoCounter5Set), B_pl_5Set_tb2,
                    mDbHelper5Set.getStringFromNumDataFromUndoTable("set3a", undoCounter5Set), A_pl_5Set_tb3, mDbHelper5Set.getStringFromNumDataFromUndoTable("set3b", undoCounter5Set), B_pl_5Set_tb3,
                    mDbHelper5Set.getStringFromNumDataFromUndoTable("set4a", undoCounter5Set), A_pl_5Set_tb4, mDbHelper5Set.getStringFromNumDataFromUndoTable("set4b", undoCounter5Set), B_pl_5Set_tb4,
                    mDbHelper5Set.getStringFromNumDataFromUndoTable("set5a", undoCounter5Set), -2, mDbHelper5Set.getStringFromNumDataFromUndoTable("set5b", undoCounter5Set), -2,
                    7, 3, 6, 2, 5, 1, 4, 0, getDFB_5Set(tb1pl_5Set), getDFB_5Set(tb2pl_5Set), getDFB_5Set(tieBreakDone_5Set), getDFB_5Set(stopMatchIsEnabled5Set),
                    getDFB_5Set(tb_5Set), getDFB_5Set(SET_5_1_SET_a_DONE), getDFB_5Set(SET_5_1_SET_b_DONE), getDFB_5Set(SET_5_2_SET_a_DONE),
                    getDFB_5Set(SET_5_2_SET_b_DONE), getDFB_5Set(SET_5_3_SET_a_DONE), getDFB_5Set(SET_5_3_SET_b_DONE), getDFB_5Set(SET_5_4_SET_a_DONE),
                    getDFB_5Set(SET_5_4_SET_b_DONE), getDFB_5Set(SET_5_1_Set), getDFB_5Set(SET_5_2_Set), getDFB_5Set(SET_5_3_Set), getDFB_5Set(SET_5_4_Set),
                    getDFB_5Set(SET_5_5_Set), getDFB_5Set(SET_5_1_SET_tb_DONE), getDFB_5Set(SET_5_2_SET_tb_DONE), getDFB_5Set(SET_5_3_SET_tb_DONE),
                    getDFB_5Set(SET_5_4_SET_tb_DONE), 0, getDFB_5Set(SET_5_MATCH_DONE));

        }

        mDbHelper5Set.clearTable(Contract.UndoNumbers.TABLE_NAME_1);
        mDbHelper5Set.clearTable(Contract.ContractNames.TABLE_NAME);
        finish();
        Intent intent1 = new Intent(this, AA_MainActivity.class);
        startActivity(intent1);
    }

    public void saveAndGo5Set(View view) {
            saveResultGoToMM();

    }

    private void setWinnerSet_5(TextView tv1, TextView tv2, String str) {
        tv1.setBackgroundResource(R.drawable.frame_winner);
        tv2.setBackgroundResource(R.drawable.frame_winner);
        beEnabled(saveButton5Set, getDrawable(R.drawable.ic_save_black_24dp));
        stopMatchIsEnabled5Set = false;
        Set5StatusMatch = str;
    }

    public int getDFB_5Set(Boolean b) {
        int u = 0;
        if (b) {
            u = 1;
        }
        return u;
    }
    @Override
    public void onDestroy() {
        mDbHelper5Set.clearTable(Contract.UndoNumbers.TABLE_NAME_1);
        mDbHelper5Set.clearTable(Contract.ContractNames.TABLE_NAME);
        super.onDestroy();
    }
}

