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

public class AA_Set5USActivity extends AppCompatActivity {

    TextView playerFirst5SetUS, playerSecond5SetUS, pointFP5SetUS, pointSP5SetUS, pointFPFirstSet5SetUS, pointSPFirstSet5SetUS, pointFPSecondSet5SetUS, pointSPSecondSet5SetUS,
            pointFPThirdSet5SetUS, pointSPThirdSet5SetUS, pointFPFourSet5SetUS, pointSPFourSet5SetUS, pointFPFiveSet5SetUS, pointSPFiveSet5SetUS;
    Spinner spinner1p5sUS, spinner2p5sUS;
    Button goalFP5SetUS, goalSP5SetUS;
    ImageButton undo_5SetUS, redo_5SetUS, saveButton5SetUS;
    private int i5su = 0;
    private int k5su = 0;
    private int fpls5US = 0;
    private int spls5US = 0;
    String adv_5SetUS = "Ad";
    String sstr1 = "";
    String sstr1s1 = "";
    int undoCounter5SetUS = 0;
    private TennisHelper mDbHelper5SetUS;
    int curNum5SetUS, prevNum5SetUS, n7, tbfp, tbsp, A_pl_5SetUS_tb5, B_pl_5SetUS_tb5,
            A_pl_5SetUS_tb4, B_pl_5SetUS_tb4, A_pl_5SetUS_tb3, B_pl_5SetUS_tb3, A_pl_5SetUS_tb2, B_pl_5SetUS_tb2, A_pl_5SetUS_tb1, B_pl_5SetUS_tb1, lastIdfromUT5SetUS;

    String scoreP1_set5US, scoreP2_set5US, pl1_1_5setUS, pl1_2_5setUS, pl2_1_5setUS, pl2_2_5setUS, reasonsSelectedItem;

    private boolean SET_5_US_1_Set = true;//
    private boolean SET_5_US_2_Set = false;//
    private boolean SET_5_US_3_Set = false;//
    private boolean SET_5_US_4_Set = false;//
    private boolean SET_5_US_5_Set = false;//
    private boolean tieBreakDone_5SetUS = false;//
    private boolean tb1pl_5SetUS = false;//
    private boolean tb2pl_5SetUS = false;//
    private boolean tb_5SetUS = false;//
    private boolean SET_5_US_MATCH_DONE = false;//
    private boolean SET_5_US_1_SET_a_DONE = false;//
    private boolean SET_5_US_1_SET_b_DONE = false;//
    private boolean SET_5_US_2_SET_a_DONE = false;//
    private boolean SET_5_US_2_SET_b_DONE = false;//
    private boolean SET_5_US_3_SET_a_DONE = false;//
    private boolean SET_5_US_3_SET_b_DONE = false;//
    private boolean SET_5_US_4_SET_a_DONE = false;//
    private boolean SET_5_US_4_SET_b_DONE = false;//
    private boolean SET_5_US_1_SET_tb_DONE = false;//
    private boolean SET_5_US_2_SET_tb_DONE = false;//
    private boolean SET_5_US_3_SET_tb_DONE = false;//
    private boolean SET_5_US_4_SET_tb_DONE = false;//
    private boolean SET_5_US_5_SET_tb_DONE = false;//

    private boolean stopMatchIsEnabled5SetUS = true;//

    private String Set5USStatusMatch = "Paused";

    int goalsCounter5SetUS;

    Context context;
    AlertDialog.Builder ad5SetUS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aa_activity_set5_us);

        goalsCounter5SetUS = 0;

        if (getSupportActionBar() != null) {

            getSupportActionBar().setTitle(getString(R.string.table_score));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        context = AA_Set5USActivity.this;
        mDbHelper5SetUS = new TennisHelper(this);

        TextView tableScore5SetUS = (TextView) findViewById(R.id.addToTableScoreTV5SetUS);
        tableScore5SetUS.setText(R.string.five_sets_US);

        playerFirst5SetUS = (TextView) findViewById(R.id.playerFirst5SetUSTV);
        playerSecond5SetUS = (TextView) findViewById(R.id.playerSecond5SetUSTV);

        TextView nameFirstPlayer = (TextView) findViewById(R.id.nameFirstPlayerTV);
        TextView nameSecondPlayer = (TextView) findViewById(R.id.nameSecondPlayerTV);

        pointFP5SetUS = (TextView) findViewById(R.id.pointFP5SetUSTV);
        pointSP5SetUS = (TextView) findViewById(R.id.pointSP5SetUSTV);

        pointFPFirstSet5SetUS = (TextView) findViewById(R.id.pointFPFirstSet5SetUSTV);
        pointSPFirstSet5SetUS = (TextView) findViewById(R.id.pointSPFirstSet5SetUSTV);

        pointFPSecondSet5SetUS = (TextView) findViewById(R.id.pointFPSecondSet5SetUSTV);
        pointSPSecondSet5SetUS = (TextView) findViewById(R.id.pointSPSecondSet5SetUSTV);

        pointFPThirdSet5SetUS = (TextView) findViewById(R.id.pointFPThirdSet5SetUSTV);
        pointSPThirdSet5SetUS = (TextView) findViewById(R.id.pointSPThirdSet5SetUSTV);

        pointFPFourSet5SetUS = (TextView) findViewById(R.id.pointFPFourSet5SetUSTV);
        pointSPFourSet5SetUS = (TextView) findViewById(R.id.pointSPFourSet5SetUSTV);

        pointFPFiveSet5SetUS = (TextView) findViewById(R.id.pointFPFiveSet5SetUSTV);
        pointSPFiveSet5SetUS = (TextView) findViewById(R.id.pointSPFiveSet5SetUSTV);


        if (getIntent().getExtras().getBoolean("isNew")) {

            pl1_1_5setUS = getIntent().getExtras().getString("user");
            pl1_2_5setUS = getIntent().getExtras().getString("userSur");
            pl2_1_5setUS = getIntent().getExtras().getString("der");
            pl2_2_5setUS = getIntent().getExtras().getString("derSur");

            pointFP5SetUS.setText(String.valueOf(tp[i5su]));
            pointSP5SetUS.setText(String.valueOf(tp[k5su]));

            pointFPFirstSet5SetUS.setText(String.valueOf(fpls5US));
            pointSPFirstSet5SetUS.setText(String.valueOf(spls5US));

            pointFPSecondSet5SetUS.setText(String.valueOf("0"));
            pointSPSecondSet5SetUS.setText(String.valueOf("0"));

            pointFPThirdSet5SetUS.setText(String.valueOf("0"));
            pointSPThirdSet5SetUS.setText(String.valueOf("0"));

            pointFPFourSet5SetUS.setText(String.valueOf("0"));
            pointSPFourSet5SetUS.setText(String.valueOf("0"));

            pointFPFiveSet5SetUS.setText(String.valueOf("0"));
            pointSPFiveSet5SetUS.setText(String.valueOf("0"));

        } else {

            pl1_1_5setUS = mDbHelper5SetUS.getNameById("name_A_1", selId);
            pl1_2_5setUS = mDbHelper5SetUS.getNameById("name_A_2", selId);
            pl2_1_5setUS = mDbHelper5SetUS.getNameById("name_B_1", selId);
            pl2_2_5setUS = mDbHelper5SetUS.getNameById("name_B_2", selId);

            if (mDbHelper5SetUS.getNumById("ktbfpsST", selId) == -2) {
                pointFP5SetUS.setText(adv_5SetUS);
            } else {
                pointFP5SetUS.setText(String.valueOf(mDbHelper5SetUS.getNumById("ktbfpsST", selId)));
            }

            if (mDbHelper5SetUS.getNumById("ktbspsST", selId) == -2) {
                pointSP5SetUS.setText(adv_5SetUS);
            } else {
                pointSP5SetUS.setText(String.valueOf(mDbHelper5SetUS.getNumById("ktbspsST", selId)));
            }

            if (mDbHelper5SetUS.getNumById("set1aST_tb", selId) > 0 || mDbHelper5SetUS.getNumById("set1bST_tb", selId) > 0) {

                pointFPFirstSet5SetUS.setText(String.valueOf(mDbHelper5SetUS.getNumById("set1aST", selId)));
                pointSPFirstSet5SetUS.setText(String.valueOf(mDbHelper5SetUS.getNumById("set1bST", selId)));

                convertUndo(mDbHelper5SetUS.getNumById("set1aST_tb", selId), mDbHelper5SetUS.getNumById("set1bST_tb", selId), pointFPFirstSet5SetUS, pointSPFirstSet5SetUS);
            } else {

                pointFPFirstSet5SetUS.setText(String.valueOf(mDbHelper5SetUS.getNumById("set1aST", selId)));
                pointSPFirstSet5SetUS.setText(String.valueOf(mDbHelper5SetUS.getNumById("set1bST", selId)));
            }

            if (mDbHelper5SetUS.getNumById("set2aST_tb", selId) > 0 || mDbHelper5SetUS.getNumById("set2bST_tb", selId) > 0) {
                pointFPSecondSet5SetUS.setText(String.valueOf(mDbHelper5SetUS.getNumById("set2aST", selId)));
                pointSPSecondSet5SetUS.setText(String.valueOf(mDbHelper5SetUS.getNumById("set2bST", selId)));
                convertUndo(mDbHelper5SetUS.getNumById("set2aST_tb", selId), mDbHelper5SetUS.getNumById("set2bST_tb", selId), pointFPSecondSet5SetUS, pointSPSecondSet5SetUS);
            } else {
                pointFPSecondSet5SetUS.setText(String.valueOf(mDbHelper5SetUS.getNumById("set2aST", selId)));
                pointSPSecondSet5SetUS.setText(String.valueOf(mDbHelper5SetUS.getNumById("set2bST", selId)));
            }

            if (mDbHelper5SetUS.getNumById("set3aST_tb", selId) > 0 || mDbHelper5SetUS.getNumById("set3bST_tb", selId) > 0) {
                pointFPThirdSet5SetUS.setText(String.valueOf(mDbHelper5SetUS.getNumById("set3aST", selId)));
                pointSPThirdSet5SetUS.setText(String.valueOf(mDbHelper5SetUS.getNumById("set3bST", selId)));
                convertUndo(mDbHelper5SetUS.getNumById("set3aST_tb", selId), mDbHelper5SetUS.getNumById("set3bST_tb", selId), pointFPThirdSet5SetUS, pointSPThirdSet5SetUS);

            } else {
                pointFPThirdSet5SetUS.setText(String.valueOf(mDbHelper5SetUS.getNumById("set3aST", selId)));
                pointSPThirdSet5SetUS.setText(String.valueOf(mDbHelper5SetUS.getNumById("set3bST", selId)));
            }
            /////////////////////////////////////////////////////////////////////////////////////////////
            if (mDbHelper5SetUS.getNumById("set4aST_tb", selId) > 0 || mDbHelper5SetUS.getNumById("set4bST_tb", selId) > 0) {
                pointFPFourSet5SetUS.setText(String.valueOf(mDbHelper5SetUS.getNumById("set4aST", selId)));
                pointSPFourSet5SetUS.setText(String.valueOf(mDbHelper5SetUS.getNumById("set4bST", selId)));
                convertUndo(mDbHelper5SetUS.getNumById("set4aST_tb", selId), mDbHelper5SetUS.getNumById("set4bST_tb", selId), pointFPFourSet5SetUS, pointSPFourSet5SetUS);
            } else {
                pointFPFourSet5SetUS.setText(String.valueOf(mDbHelper5SetUS.getNumById("set4aST", selId)));
                pointSPFourSet5SetUS.setText(String.valueOf(mDbHelper5SetUS.getNumById("set4bST", selId)));
            }
            /////////////////////////////////////////////////////////////////////////////////////////////
            pointFPFiveSet5SetUS.setText(String.valueOf(mDbHelper5SetUS.getNumById("set5aST", selId)));
            pointSPFiveSet5SetUS.setText(String.valueOf(mDbHelper5SetUS.getNumById("set5bST", selId)));
            ////////////////////////////////////////////////////////////////////////////////////////////
            SET_5_US_1_Set = mDbHelper5SetUS.getNumById("_1_set", selId) > 0;//

            SET_5_US_2_Set = mDbHelper5SetUS.getNumById("_2_set", selId) > 0;//

            SET_5_US_3_Set = mDbHelper5SetUS.getNumById("_3_set", selId) > 0;//

            SET_5_US_4_Set = mDbHelper5SetUS.getNumById("_4_set", selId) > 0;//

            SET_5_US_5_Set = mDbHelper5SetUS.getNumById("_5_set", selId) > 0;//

            tieBreakDone_5SetUS = mDbHelper5SetUS.getNumById("tieBreakDone", selId) > 0;//

            tb1pl_5SetUS = mDbHelper5SetUS.getNumById("tb1pl", selId) > 0;//

            tb2pl_5SetUS = mDbHelper5SetUS.getNumById("tb2pl", selId) > 0;//

            tb_5SetUS = mDbHelper5SetUS.getNumById("tb", selId) > 0;//

            SET_5_US_MATCH_DONE = mDbHelper5SetUS.getNumById("match_done", selId) > 0;//

            SET_5_US_1_SET_a_DONE = mDbHelper5SetUS.getNumById("_1_set_a_done", selId) > 0;//
            setWinnerFrame(SET_5_US_1_SET_a_DONE, pointFPFirstSet5SetUS);

            SET_5_US_1_SET_b_DONE = mDbHelper5SetUS.getNumById("_1_set_b_done", selId) > 0;//
            setWinnerFrame(SET_5_US_1_SET_b_DONE, pointSPFirstSet5SetUS);

            SET_5_US_2_SET_a_DONE = mDbHelper5SetUS.getNumById("_2_set_a_done", selId) > 0;//
            setWinnerFrame(SET_5_US_2_SET_a_DONE, pointFPSecondSet5SetUS);

            SET_5_US_2_SET_b_DONE = mDbHelper5SetUS.getNumById("_2_set_b_done", selId) > 0;//
            setWinnerFrame(SET_5_US_2_SET_b_DONE, pointSPSecondSet5SetUS);

            SET_5_US_3_SET_a_DONE = mDbHelper5SetUS.getNumById("_3_set_a_done", selId) > 0;//
            setWinnerFrame(SET_5_US_3_SET_a_DONE, pointFPThirdSet5SetUS);

            SET_5_US_3_SET_b_DONE = mDbHelper5SetUS.getNumById("_3_set_b_done", selId) > 0;//
            setWinnerFrame(SET_5_US_3_SET_b_DONE, pointSPThirdSet5SetUS);

            SET_5_US_4_SET_a_DONE = mDbHelper5SetUS.getNumById("_4_set_a_done", selId) > 0;//
            setWinnerFrame(SET_5_US_4_SET_a_DONE, pointFPFourSet5SetUS);

            SET_5_US_4_SET_b_DONE = mDbHelper5SetUS.getNumById("_4_set_b_done", selId) > 0;//
            setWinnerFrame(SET_5_US_4_SET_b_DONE, pointSPFourSet5SetUS);

            SET_5_US_1_SET_tb_DONE = mDbHelper5SetUS.getNumById("_1_set_tb_done", selId) > 0;//

            SET_5_US_2_SET_tb_DONE = mDbHelper5SetUS.getNumById("_2_set_tb_done", selId) > 0;//

            SET_5_US_3_SET_tb_DONE = mDbHelper5SetUS.getNumById("_3_set_tb_done", selId) > 0;//

            SET_5_US_4_SET_tb_DONE = mDbHelper5SetUS.getNumById("_4_set_tb_done", selId) > 0;//

            SET_5_US_5_SET_tb_DONE = mDbHelper5SetUS.getNumById("_5_set_tb_done", selId) > 0;//

            stopMatchIsEnabled5SetUS = mDbHelper5SetUS.getNumById("stopMatchIsEnabled", selId) > 0;//


            A_pl_5SetUS_tb1 = mDbHelper5SetUS.getNumById("set1aST_tb", selId);
            B_pl_5SetUS_tb1 = mDbHelper5SetUS.getNumById("set1bST_tb", selId);
            A_pl_5SetUS_tb2 = mDbHelper5SetUS.getNumById("set2aST_tb", selId);
            B_pl_5SetUS_tb2 = mDbHelper5SetUS.getNumById("set2bST_tb", selId);
            A_pl_5SetUS_tb3 = mDbHelper5SetUS.getNumById("set3aST_tb", selId);
            B_pl_5SetUS_tb3 = mDbHelper5SetUS.getNumById("set3bST_tb", selId);
            A_pl_5SetUS_tb4 = mDbHelper5SetUS.getNumById("set4aST_tb", selId);
            B_pl_5SetUS_tb4 = mDbHelper5SetUS.getNumById("set4bST_tb", selId);
            A_pl_5SetUS_tb5 = mDbHelper5SetUS.getNumById("set5aST_tb", selId);
            B_pl_5SetUS_tb5 = mDbHelper5SetUS.getNumById("set5bST_tb", selId);


            mDbHelper5SetUS.insert5SetUSScore(
                    mDbHelper5SetUS.getNumById("ktbfpsST", selId), mDbHelper5SetUS.getNumById("ktbspsST", selId),
                    mDbHelper5SetUS.getNumById("set1aST", selId), mDbHelper5SetUS.getNumById("set1bST", selId),
                    mDbHelper5SetUS.getNumById("set2aST", selId), mDbHelper5SetUS.getNumById("set2bST", selId),
                    mDbHelper5SetUS.getNumById("set3aST", selId), mDbHelper5SetUS.getNumById("set3bST", selId),
                    mDbHelper5SetUS.getNumById("set4aST", selId), mDbHelper5SetUS.getNumById("set4bST", selId),
                    mDbHelper5SetUS.getNumById("set5aST", selId), mDbHelper5SetUS.getNumById("set5bST", selId));

            mDbHelper5SetUS.insertPlayers(pl1_1_5setUS, pl1_2_5setUS, pl2_1_5setUS, pl2_2_5setUS);


        }


        setNames(playerFirst5SetUS, pl1_1_5setUS, pl1_2_5setUS);
        setNames(playerSecond5SetUS, pl2_1_5setUS, pl2_2_5setUS);

        AA_KingTieBreakActivity.setAddNames(nameFirstPlayer, pl1_1_5setUS, pl1_2_5setUS);
        AA_KingTieBreakActivity.setAddNames(nameSecondPlayer, pl2_1_5setUS, pl2_2_5setUS);

        spinner1p5sUS = (Spinner) findViewById(R.id.spinner1p5sUSTV);
        spinner2p5sUS = (Spinner) findViewById(R.id.spinner2p5sUSTV);

        typeGoals[0] = getResources().getString(R.string.select_gt_for_spinner);
        typeGoals[1] = getResources().getString(R.string.Winner);
        typeGoals[2] = getResources().getString(R.string.Ace);
        typeGoals[3] = getResources().getString(R.string.Unenforced_error_for_spinner);
        typeGoals[4] = getResources().getString(R.string.Double_error_for_spinner);

        AA_KingTieBreakActivity.setadapter(typeGoals, spinner1p5sUS, this);
        AA_KingTieBreakActivity.setadapter(typeGoals, spinner2p5sUS, this);

        goalFP5SetUS = (Button) findViewById(R.id.goalFP5SetUSButton);
        goalSP5SetUS = (Button) findViewById(R.id.goalSP5SetUSButton);

        undo_5SetUS = (ImageButton) findViewById(R.id.undo5SetUSButton);
        redo_5SetUS = (ImageButton) findViewById(R.id.redo5SetUSButton);
        saveButton5SetUS = (ImageButton) findViewById(R.id._saveButton5SetUS);

        noEnabled(undo_5SetUS, getDrawable(R.drawable.ic_undo_black_24dp_0_3));
        noEnabled(redo_5SetUS, getDrawable(R.drawable.ic_redo_black_24dp_0_3));
        noEnabled(saveButton5SetUS, getDrawable(R.drawable.ic_save_black_24dp_0_3));
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if (!stopMatchIsEnabled5SetUS) {
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

                final String na_1 = getString(R.string.Retired) + " " + playerFirst5SetUS.getText().toString() + getString(R.string.loss);
                final String na_2 = getString(R.string.Retired) + " " + playerSecond5SetUS.getText().toString() + getString(R.string.loss);
                final String na_3 = getString(R.string.Another_Match_is_paused);
                final String na_4 = getString(R.string.Another_Match_is_finished);
                final String[] reasons = {na_1, na_2, na_3, na_4};
                ad5SetUS = new AlertDialog.Builder(this);
                ad5SetUS.setTitle(getString(R.string.Choose_the_reason))
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


                                        ad5SetUS = new AlertDialog.Builder(context);

                                        ad5SetUS.setMessage(getString(R.string.Do_you_want));

                                        ad5SetUS.setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener() {
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
                                        ad5SetUS.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int arg1) {
                                                noSaveResultGoToMM();
                                                if (!getIntent().getExtras().getBoolean("isNew")) {
                                                    mDbHelper5SetUS.putFreeRowInStatisticSaveTable(selId);
                                                }
                                            }
                                        });
                                        ad5SetUS.setCancelable(true);
                                        ad5SetUS.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                            public void onCancel(DialogInterface dialog) {
                                            }
                                        });
                                        ad5SetUS.show();
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
                ad5SetUS.show();
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


            ad5SetUS = new AlertDialog.Builder(context);
            ad5SetUS.setMessage(getString(R.string.ad_alert_1));
            ad5SetUS.setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int arg1) {

                    saveResultGoToMM();
                }
            });
            ad5SetUS.setNeutralButton(getString(R.string.Continue_match),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int id) {
                            dialog.cancel();
                        }
                    });

            ad5SetUS.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int arg1) {
                    noSaveResultGoToMM();
                    if (!getIntent().getExtras().getBoolean("isNew")) {
                        mDbHelper5SetUS.putFreeRowInStatisticSaveTable(selId);
                    }
                }
            });
            ad5SetUS.setCancelable(true);
            ad5SetUS.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialog) {
                }
            });
            ad5SetUS.show();
    }

    public void firstPlayerGoal5SetUS(View v) {

        switch (AA_KingTieBreakActivity.getSpinnerItem(spinner1p5sUS)) {
            case 0: {
                Toast.makeText(getBaseContext(), getString(R.string.select_gt), Toast.LENGTH_SHORT).show();
                break;
            }
            case 1: {
                deleteRow5SetUS();
                noEnabled(redo_5SetUS, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper5SetUS.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(7) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper5SetUS.getLastId() - 7)});

                setFirstPlScore5SetUS();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper5SetUS.getLastId() - 7);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper5SetUS.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet5USAllplScore(pointFP5SetUS, pointSP5SetUS,
                        pointFPFirstSet5SetUS, pointSPFirstSet5SetUS, pointFPSecondSet5SetUS, pointSPSecondSet5SetUS, pointFPThirdSet5SetUS, pointSPThirdSet5SetUS,
                        pointFPFourSet5SetUS, pointSPFourSet5SetUS, pointFPFiveSet5SetUS, pointSPFiveSet5SetUS);
                goalsCounter5SetUS++;
                break;
            }

            case 2: {

                deleteRow5SetUS();
                noEnabled(redo_5SetUS, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper5SetUS.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(6) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper5SetUS.getLastId() - 6)});

                setFirstPlScore5SetUS();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper5SetUS.getLastId() - 6);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper5SetUS.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet5USAllplScore(pointFP5SetUS, pointSP5SetUS,
                        pointFPFirstSet5SetUS, pointSPFirstSet5SetUS, pointFPSecondSet5SetUS, pointSPSecondSet5SetUS, pointFPThirdSet5SetUS, pointSPThirdSet5SetUS,
                        pointFPFourSet5SetUS, pointSPFourSet5SetUS, pointFPFiveSet5SetUS, pointSPFiveSet5SetUS);
                goalsCounter5SetUS++;
                break;
            }

            case 3: {

                deleteRow5SetUS();
                noEnabled(redo_5SetUS, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper5SetUS.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(1) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper5SetUS.getLastId() - 1)});

                setFirstPlScore5SetUS();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper5SetUS.getLastId() - 1);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper5SetUS.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet5USAllplScore(pointFP5SetUS, pointSP5SetUS,
                        pointFPFirstSet5SetUS, pointSPFirstSet5SetUS, pointFPSecondSet5SetUS, pointSPSecondSet5SetUS, pointFPThirdSet5SetUS, pointSPThirdSet5SetUS,
                        pointFPFourSet5SetUS, pointSPFourSet5SetUS, pointFPFiveSet5SetUS, pointSPFiveSet5SetUS);
                goalsCounter5SetUS++;
                break;
            }

            case 4: {

                deleteRow5SetUS();
                noEnabled(redo_5SetUS, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper5SetUS.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(0) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper5SetUS.getLastId())});

                setFirstPlScore5SetUS();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper5SetUS.getLastId());
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper5SetUS.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet5USAllplScore(pointFP5SetUS, pointSP5SetUS,
                        pointFPFirstSet5SetUS, pointSPFirstSet5SetUS, pointFPSecondSet5SetUS, pointSPSecondSet5SetUS, pointFPThirdSet5SetUS, pointSPThirdSet5SetUS,
                        pointFPFourSet5SetUS, pointSPFourSet5SetUS, pointFPFiveSet5SetUS, pointSPFiveSet5SetUS);
                goalsCounter5SetUS++;
                break;
            }
        }

        spinner1p5sUS.setSelection(0);
        spinner2p5sUS.setSelection(0);
    }

    public void secondPlayerGoal5SetUS(View v) {


        switch (AA_KingTieBreakActivity.getSpinnerItem(spinner2p5sUS)) {
            case 0: {
                Toast.makeText(getBaseContext(), getString(R.string.select_gt), Toast.LENGTH_SHORT).show();
                break;
            }
            case 1: {
                deleteRow5SetUS();
                noEnabled(redo_5SetUS, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper5SetUS.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(3) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper5SetUS.getLastId() - 3)});

                setSecondPlScore5SetUS();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper5SetUS.getLastId() - 3);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper5SetUS.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet5USAllplScore(pointFP5SetUS, pointSP5SetUS,
                        pointFPFirstSet5SetUS, pointSPFirstSet5SetUS, pointFPSecondSet5SetUS, pointSPSecondSet5SetUS, pointFPThirdSet5SetUS, pointSPThirdSet5SetUS,
                        pointFPFourSet5SetUS, pointSPFourSet5SetUS, pointFPFiveSet5SetUS, pointSPFiveSet5SetUS);
                goalsCounter5SetUS++;
                break;
            }

            case 2: {
                deleteRow5SetUS();
                noEnabled(redo_5SetUS, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper5SetUS.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(2) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper5SetUS.getLastId() - 2)});

                setSecondPlScore5SetUS();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper5SetUS.getLastId() - 2);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper5SetUS.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet5USAllplScore(pointFP5SetUS, pointSP5SetUS,
                        pointFPFirstSet5SetUS, pointSPFirstSet5SetUS, pointFPSecondSet5SetUS, pointSPSecondSet5SetUS, pointFPThirdSet5SetUS, pointSPThirdSet5SetUS,
                        pointFPFourSet5SetUS, pointSPFourSet5SetUS, pointFPFiveSet5SetUS, pointSPFiveSet5SetUS);
                goalsCounter5SetUS++;
                break;
            }

            case 3: {
                deleteRow5SetUS();
                noEnabled(redo_5SetUS, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper5SetUS.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(5) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper5SetUS.getLastId() - 5)});

                setSecondPlScore5SetUS();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper5SetUS.getLastId() - 5);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper5SetUS.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet5USAllplScore(pointFP5SetUS, pointSP5SetUS,
                        pointFPFirstSet5SetUS, pointSPFirstSet5SetUS, pointFPSecondSet5SetUS, pointSPSecondSet5SetUS, pointFPThirdSet5SetUS, pointSPThirdSet5SetUS,
                        pointFPFourSet5SetUS, pointSPFourSet5SetUS, pointFPFiveSet5SetUS, pointSPFiveSet5SetUS);
                goalsCounter5SetUS++;
                break;
            }

            case 4: {
                deleteRow5SetUS();
                noEnabled(redo_5SetUS, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper5SetUS.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(4) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper5SetUS.getLastId() - 4)});

                setSecondPlScore5SetUS();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper5SetUS.getLastId() - 4);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper5SetUS.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet5USAllplScore(pointFP5SetUS, pointSP5SetUS,
                        pointFPFirstSet5SetUS, pointSPFirstSet5SetUS, pointFPSecondSet5SetUS, pointSPSecondSet5SetUS, pointFPThirdSet5SetUS, pointSPThirdSet5SetUS,
                        pointFPFourSet5SetUS, pointSPFourSet5SetUS, pointFPFiveSet5SetUS, pointSPFiveSet5SetUS);
                goalsCounter5SetUS++;
                break;
            }
        }

        spinner1p5sUS.setSelection(0);
        spinner2p5sUS.setSelection(0);
    }

    public void setFirstPlScore5SetUS() {

        tieBreakDone_5SetUS = false;

        tb1pl_5SetUS = true;

        if (tb_5SetUS) {

            tieBreak(tb1pl_5SetUS, tb2pl_5SetUS);
        } else {

            i5su = getKorI(pointFP5SetUS);
            k5su = getKorI(pointSP5SetUS);

            if (SET_5_US_5_Set) {

                fpls5US = Integer.valueOf(pointFPFiveSet5SetUS.getText().toString());
                spls5US = Integer.valueOf(pointSPFiveSet5SetUS.getText().toString());

            } else if (SET_5_US_4_Set) {

                fpls5US = Integer.valueOf(pointFPFourSet5SetUS.getText().toString());
                spls5US = Integer.valueOf(pointSPFourSet5SetUS.getText().toString());

            } else if (SET_5_US_3_Set) {

                fpls5US = Integer.valueOf(pointFPThirdSet5SetUS.getText().toString());
                spls5US = Integer.valueOf(pointSPThirdSet5SetUS.getText().toString());

            } else if (SET_5_US_2_Set) {

                fpls5US = Integer.valueOf(pointFPSecondSet5SetUS.getText().toString());
                spls5US = Integer.valueOf(pointSPSecondSet5SetUS.getText().toString());

            } else {

                fpls5US = Integer.valueOf(pointFPFirstSet5SetUS.getText().toString());
                spls5US = Integer.valueOf(pointSPFirstSet5SetUS.getText().toString());
            }

            scoreP1_set5US = pointFP5SetUS.getText().toString();

            switch (scoreP1_set5US) {
                case "0":
                    i5su = 1;
                    break;
                case "15":
                    i5su = 2;
                    break;
                case "30":
                    i5su = 3;
                    break;
                case "40":
                    if (i5su > k5su) {
                        i5su = k5su = 0;
                        fpls5US++;
                    } else if (i5su == k5su) {
                        i5su = 4;
                        break;
                    } else {
                        i5su = k5su = 3;
                    }
                    break;
                case "Ad":
                    i5su = k5su = 0;
                    fpls5US++;
                    break;
            }
            if (i5su == 4) {

                pointFP5SetUS.setText(adv_5SetUS);
                pointSP5SetUS.setText(String.valueOf(tp[k5su]));
                tb1pl_5SetUS = false;

            } else {

                pointFP5SetUS.setText(String.valueOf(tp[i5su]));
                pointSP5SetUS.setText(String.valueOf(tp[k5su]));

                if (SET_5_US_5_Set) {

                    pointFPFiveSet5SetUS.setText(String.valueOf(fpls5US));

                } else if (SET_5_US_4_Set) {

                    pointFPFourSet5SetUS.setText(String.valueOf(fpls5US));

                } else if (SET_5_US_3_Set) {

                    pointFPThirdSet5SetUS.setText(String.valueOf(fpls5US));

                } else if (SET_5_US_2_Set) {

                    pointFPSecondSet5SetUS.setText(String.valueOf(fpls5US));

                } else {

                    pointFPFirstSet5SetUS.setText(String.valueOf(fpls5US));
                }

                tb1pl_5SetUS = false;
            }
            checkSetNumber(fpls5US, spls5US);
        }

    }

    public void setSecondPlScore5SetUS() {

        tieBreakDone_5SetUS = false;

        tb2pl_5SetUS = true;

        if (tb_5SetUS) {

            tieBreak(tb1pl_5SetUS, tb2pl_5SetUS);

        } else {

            i5su = getKorI(pointFP5SetUS);
            k5su = getKorI(pointSP5SetUS);

            if (SET_5_US_5_Set) {

                fpls5US = Integer.valueOf(pointFPFiveSet5SetUS.getText().toString());
                spls5US = Integer.valueOf(pointSPFiveSet5SetUS.getText().toString());

            } else if (SET_5_US_4_Set) {

                fpls5US = Integer.valueOf(pointFPFourSet5SetUS.getText().toString());
                spls5US = Integer.valueOf(pointSPFourSet5SetUS.getText().toString());

            } else if (SET_5_US_3_Set) {

                fpls5US = Integer.valueOf(pointFPThirdSet5SetUS.getText().toString());
                spls5US = Integer.valueOf(pointSPThirdSet5SetUS.getText().toString());

            } else if (SET_5_US_2_Set) {

                fpls5US = Integer.valueOf(pointFPSecondSet5SetUS.getText().toString());
                spls5US = Integer.valueOf(pointSPSecondSet5SetUS.getText().toString());

            } else {

                fpls5US = Integer.valueOf(pointFPFirstSet5SetUS.getText().toString());
                spls5US = Integer.valueOf(pointSPFirstSet5SetUS.getText().toString());
            }

            scoreP2_set5US = pointSP5SetUS.getText().toString();

            switch (scoreP2_set5US) {
                case "0":
                    k5su = 1;
                    break;
                case "15":
                    k5su = 2;
                    break;
                case "30":
                    k5su = 3;
                    break;
                case "40":
                    if (k5su > i5su) {
                        k5su = i5su = 0;
                        spls5US++;
                    } else if (k5su == i5su) {
                        k5su = 4;
                    } else {
                        i5su = k5su = 3;
                    }
                    break;
                case "Ad":
                    i5su = k5su = 0;
                    spls5US++;
                    break;
            }
            if (k5su == 4) {

                pointSP5SetUS.setText(adv_5SetUS);
                pointFP5SetUS.setText(String.valueOf(tp[i5su]));
                tb2pl_5SetUS = false;

            } else {

                pointSP5SetUS.setText(String.valueOf(tp[k5su]));
                pointFP5SetUS.setText(String.valueOf(tp[i5su]));

                if (SET_5_US_5_Set) {

                    pointSPFiveSet5SetUS.setText(String.valueOf(spls5US));

                } else if (SET_5_US_4_Set) {

                    pointSPFourSet5SetUS.setText(String.valueOf(spls5US));

                } else if (SET_5_US_3_Set) {

                    pointSPThirdSet5SetUS.setText(String.valueOf(spls5US));

                } else if (SET_5_US_2_Set) {

                    pointSPSecondSet5SetUS.setText(String.valueOf(spls5US));

                } else {

                    pointSPFirstSet5SetUS.setText(String.valueOf(spls5US));
                }
                tb2pl_5SetUS = false;
            }

            checkSetNumber(fpls5US, spls5US);
        }
    }

    public void intCurSet5USAllplScore(TextView p1s, TextView p2s, TextView p1s1s, TextView p2s1s, TextView p1s2s, TextView p2s2s, TextView p1s3s, TextView p2s3s
            , TextView p1s4s, TextView p2s4s, TextView p1s5s, TextView p2s5s) {

        SQLiteDatabase db = mDbHelper5SetUS.getWritableDatabase();
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


        if (SET_5_US_1_SET_tb_DONE) {

            if (A_pl_5SetUS_tb1 > B_pl_5SetUS_tb1) {

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

        if (SET_5_US_2_SET_tb_DONE) {

            if (A_pl_5SetUS_tb2 > B_pl_5SetUS_tb2) {
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

        if (SET_5_US_3_SET_tb_DONE) {

            if (A_pl_5SetUS_tb3 > B_pl_5SetUS_tb3) {
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
        if (SET_5_US_4_SET_tb_DONE) {

            if (A_pl_5SetUS_tb4 > B_pl_5SetUS_tb4) {
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
        if (SET_5_US_5_SET_tb_DONE) {

            if (A_pl_5SetUS_tb5 > B_pl_5SetUS_tb5) {
                values.put(Contract.UndoNumbers.COLUMN_SET5A, 7);
                values.put(Contract.UndoNumbers.COLUMN_SET5B, 6);
            } else {
                values.put(Contract.UndoNumbers.COLUMN_SET5A, 6);
                values.put(Contract.UndoNumbers.COLUMN_SET5B, 7);
            }
        } else {

            String j11 = p1s5s.getText().toString();
            String j12 = p2s5s.getText().toString();
            values.put(Contract.UndoNumbers.COLUMN_SET5A, Integer.valueOf(j11));
            values.put(Contract.UndoNumbers.COLUMN_SET5B, Integer.valueOf(j12));
        }


        db.update(Contract.UndoNumbers.TABLE_NAME_1, values, Contract.UndoNumbers.COLUMN_NUM_NUM + " = ?", new String[]{Integer.toString(mDbHelper5SetUS.getMaxNumNum())});
    }

    public void checkSetNumber(int y1, int y2) {

        if (SET_5_US_5_Set) {

            if (y1 == 6 & y2 <= y1 - 2 | y1 == 7 & y2 <= y1 - 2) {

                SET_5_US_MATCH_DONE = true;

                Toast.makeText(this, pl1_1_5setUS + " " + pl1_2_5setUS + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                setWinnerSet_5US(playerFirst5SetUS, pointFPFiveSet5SetUS, "firstPlayer");
                setEnab();

            } else if (y2 == 6 & y1 <= y2 - 2 | y2 == 7 & y1 <= y2 - 2) {

                SET_5_US_MATCH_DONE = true;

                Toast.makeText(this, pl2_1_5setUS + " " + pl2_2_5setUS + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                setWinnerSet_5US(playerSecond5SetUS, pointSPFiveSet5SetUS, "secondPlayer");
                setEnab();

            } else if (y2 == 6 & y1 == 6) {

                setTbmode_5US();
            }
        } else if (SET_5_US_4_Set) {

            if ((y1 == 6 & y2 <= y1 - 2 | y1 == 7 & y2 <= y1 - 2)) {

                if ((SET_5_US_1_SET_a_DONE & SET_5_US_2_SET_a_DONE & SET_5_US_3_SET_b_DONE)
                        | (SET_5_US_1_SET_a_DONE & SET_5_US_2_SET_b_DONE & SET_5_US_3_SET_a_DONE)
                        | (SET_5_US_1_SET_b_DONE & SET_5_US_2_SET_a_DONE & SET_5_US_3_SET_a_DONE)) {

                    SET_5_US_MATCH_DONE = true;

                    Toast.makeText(this, pl1_1_5setUS + " " + pl1_2_5setUS + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                    setWinnerSet_5US(playerFirst5SetUS, pointFPFourSet5SetUS, "firstPlayer");
                    setEnab();

                } else {

                    SET_5_US_4_Set = false;
                    SET_5_US_5_Set = true;
                    SET_5_US_4_SET_a_DONE = true;

                    Toast.makeText(this, pl1_1_5setUS + " " + pl1_2_5setUS + " " + getString(R.string.wins_4_set), Toast.LENGTH_LONG).show();
                    pointFPFourSet5SetUS.setBackgroundResource(R.drawable.frame_winner);
                }

            } else if ((y2 == 6 & y1 <= y2 - 2) | (y2 == 7 & y1 <= y2 - 2)) {

                if ((SET_5_US_1_SET_b_DONE & SET_5_US_2_SET_a_DONE & SET_5_US_3_SET_b_DONE)
                        | (SET_5_US_1_SET_b_DONE & SET_5_US_2_SET_b_DONE & SET_5_US_3_SET_a_DONE)
                        | (SET_5_US_1_SET_a_DONE & SET_5_US_2_SET_b_DONE & SET_5_US_3_SET_b_DONE)) {

                    SET_5_US_MATCH_DONE = true;

                    Toast.makeText(this, pl2_1_5setUS + " " + pl2_2_5setUS + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                    setWinnerSet_5US(playerSecond5SetUS, pointSPFourSet5SetUS, "secondPlayer");
                    setEnab();

                } else {

                    SET_5_US_4_Set = false;
                    SET_5_US_5_Set = true;
                    SET_5_US_4_SET_b_DONE = true;

                    pointSPFourSet5SetUS.setBackgroundResource(R.drawable.frame_winner);
                    Toast.makeText(this, pl2_1_5setUS + " " + pl2_2_5setUS + " " + getString(R.string.wins_4_set), Toast.LENGTH_LONG).show();
                }

            } else if (y2 == 6 & y1 == 6) {

                setTbmode_5US();
            }
        } else if (SET_5_US_3_Set) {

            if ((y1 == 6 & y2 <= y1 - 2 | y1 == 7 & y2 <= y1 - 2)) {

                if (SET_5_US_1_SET_a_DONE & SET_5_US_2_SET_a_DONE) {

                    SET_5_US_MATCH_DONE = true;

                    Toast.makeText(this, pl1_1_5setUS + " " + pl1_2_5setUS + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                    setWinnerSet_5US(playerFirst5SetUS, pointFPThirdSet5SetUS, "firstPlayer");
                    setEnab();

                } else {

                    SET_5_US_3_Set = false;
                    SET_5_US_4_Set = true;
                    SET_5_US_3_SET_a_DONE = true;

                    pointFPThirdSet5SetUS.setBackgroundResource(R.drawable.frame_winner);
                    Toast.makeText(this, pl1_1_5setUS + " " + pl1_2_5setUS + " " + getString(R.string.wins_3_set), Toast.LENGTH_LONG).show();
                }
            } else if ((y2 == 6 & y1 <= y2 - 2) | (y2 == 7 & y1 <= y2 - 2)) {

                if (SET_5_US_1_SET_b_DONE & SET_5_US_2_SET_b_DONE) {

                    SET_5_US_MATCH_DONE = true;

                    Toast.makeText(this, pl2_1_5setUS + " " + pl2_2_5setUS + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                    setWinnerSet_5US(playerSecond5SetUS, pointSPThirdSet5SetUS, "secondPlayer");
                    setEnab();

                } else {

                    SET_5_US_3_Set = false;
                    SET_5_US_4_Set = true;
                    SET_5_US_3_SET_b_DONE = true;

                    pointSPThirdSet5SetUS.setBackgroundResource(R.drawable.frame_winner);
                    Toast.makeText(this, pl2_1_5setUS + " " + pl2_2_5setUS + " " + getString(R.string.wins_3_set), Toast.LENGTH_LONG).show();
                }
            } else if (y2 == 6 & y1 == 6) {

                setTbmode_5US();
            }
        } else if (SET_5_US_2_Set) {

            if ((y1 == 6 & y2 <= y1 - 2 | y1 == 7 & y2 <= y1 - 2)) {

                SET_5_US_2_Set = false;
                SET_5_US_3_Set = true;
                SET_5_US_2_SET_a_DONE = true;

                pointFPSecondSet5SetUS.setBackgroundResource(R.drawable.frame_winner);
                Toast.makeText(this, pl1_1_5setUS + " " + pl1_2_5setUS + " " + getString(R.string.wins_2_set), Toast.LENGTH_LONG).show();

            } else if ((y2 == 6 & y1 <= y2 - 2) | (y2 == 7 & y1 <= y2 - 2)) {

                SET_5_US_2_Set = false;
                SET_5_US_3_Set = true;
                SET_5_US_2_SET_b_DONE = true;

                pointSPSecondSet5SetUS.setBackgroundResource(R.drawable.frame_winner);
                Toast.makeText(this, pl2_1_5setUS + " " + pl2_2_5setUS + " " + getString(R.string.wins_2_set), Toast.LENGTH_LONG).show();

            } else if (y2 == 6 & y1 == 6) {

                setTbmode_5US();
            }
        } else if (SET_5_US_1_Set) {

            if ((y1 == 6 & y2 <= y1 - 2 | y1 == 7 & y2 <= y1 - 2)) {

                SET_5_US_1_Set = false;
                SET_5_US_2_Set = true;
                SET_5_US_1_SET_a_DONE = true;

                pointFPFirstSet5SetUS.setBackgroundResource(R.drawable.frame_winner);
                Toast.makeText(this, pl1_1_5setUS + " " + pl1_2_5setUS + " " + getString(R.string.wins_1_set), Toast.LENGTH_LONG).show();

            } else if ((y2 == 6 & y1 <= y2 - 2) | (y2 == 7 & y1 <= y2 - 2)) {

                SET_5_US_1_Set = false;
                SET_5_US_2_Set = true;
                SET_5_US_1_SET_b_DONE = true;

                pointSPFirstSet5SetUS.setBackgroundResource(R.drawable.frame_winner);
                Toast.makeText(this, pl2_1_5setUS + " " + pl2_2_5setUS + " " + getString(R.string.wins_1_set), Toast.LENGTH_LONG).show();

            } else if (y2 == 6 & y1 == 6) {

                setTbmode_5US();
            }
        }
    }

    public void tieBreak(boolean pla, boolean plb) {

        if (pla) {

            tbfp = Integer.valueOf(pointFP5SetUS.getText().toString());
            tbsp = Integer.valueOf(pointSP5SetUS.getText().toString());

            tbfp++;

            pointFP5SetUS.setText(String.valueOf(tbfp));
            pointSP5SetUS.setText(String.valueOf(tbsp));
            tb1pl_5SetUS = false;

        } else if (plb) {

            tbfp = Integer.valueOf(pointFP5SetUS.getText().toString());
            tbsp = Integer.valueOf(pointSP5SetUS.getText().toString());

            tbsp++;

            pointFP5SetUS.setText(String.valueOf(tbfp));
            pointSP5SetUS.setText(String.valueOf(tbsp));
            tb2pl_5SetUS = false;
        }
        checkTieBreakNumber(tbfp, tbsp);
    }

    public void checkTieBreakNumber(int x1, int x2) {

        if ((x1 == 7 & x2 <= x1 - 2) | (x1 > 7 & x2 <= x1 - 2)) {

            if (SET_5_US_5_Set) {

                pointFPFiveSet5SetUS.setText(String.valueOf(7));
                SET_5_US_MATCH_DONE = true;
                SET_5_US_5_SET_tb_DONE = true;
                tieBreakDone_5SetUS = true;
                A_pl_5SetUS_tb5 = x1;
                B_pl_5SetUS_tb5 = x2;
                convert(x1, x2, pointFPFiveSet5SetUS, pointSPFiveSet5SetUS);

                Toast.makeText(this, pl1_1_5setUS + " " + pl1_2_5setUS + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                setWinnerSet_5US(playerFirst5SetUS, pointFPFiveSet5SetUS, "firstPlayer");
                setEnab();

            } else if (SET_5_US_4_Set) {

                if ((SET_5_US_1_SET_a_DONE & SET_5_US_2_SET_a_DONE & SET_5_US_3_SET_b_DONE)
                        | (SET_5_US_1_SET_a_DONE & SET_5_US_2_SET_b_DONE & SET_5_US_3_SET_a_DONE)
                        | (SET_5_US_1_SET_b_DONE & SET_5_US_2_SET_a_DONE & SET_5_US_3_SET_a_DONE)) {

                    pointFPFourSet5SetUS.setText(String.valueOf(7));
                    SET_5_US_MATCH_DONE = true;
                    SET_5_US_4_SET_a_DONE = true;
                    SET_5_US_4_SET_tb_DONE = true;
                    tieBreakDone_5SetUS = true;
                    A_pl_5SetUS_tb4 = x1;
                    B_pl_5SetUS_tb4 = x2;
                    convert(x1, x2, pointFPFourSet5SetUS, pointSPFourSet5SetUS);

                    Toast.makeText(this, pl1_1_5setUS + " " + pl1_2_5setUS + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                    setWinnerSet_5US(playerFirst5SetUS, pointFPFourSet5SetUS, "firstPlayer");
                    setEnab();

                } else {

                    pointFPFourSet5SetUS.setText(String.valueOf(7));
                    SET_5_US_4_SET_a_DONE = true;
                    SET_5_US_4_SET_tb_DONE = true;
                    tieBreakDone_5SetUS = true;
                    SET_5_US_4_Set = false;
                    SET_5_US_5_Set = true;
                    A_pl_5SetUS_tb4 = x1;
                    B_pl_5SetUS_tb4 = x2;
                    convert(x1, x2, pointFPFourSet5SetUS, pointSPFourSet5SetUS);

                    Toast.makeText(this, pl1_1_5setUS + " " + pl1_2_5setUS + " " + getString(R.string.wins_4_set), Toast.LENGTH_LONG).show();
                    pointFPFourSet5SetUS.setBackgroundResource(R.drawable.frame_winner);
                }

            } else if (SET_5_US_3_Set) {

                if (SET_5_US_1_SET_a_DONE & SET_5_US_2_SET_a_DONE) {

                    pointFPThirdSet5SetUS.setText(String.valueOf(7));
                    SET_5_US_MATCH_DONE = true;
                    SET_5_US_3_SET_a_DONE = true;
                    SET_5_US_3_SET_tb_DONE = true;
                    tieBreakDone_5SetUS = true;
                    A_pl_5SetUS_tb3 = x1;
                    B_pl_5SetUS_tb3 = x2;
                    convert(x1, x2, pointFPThirdSet5SetUS, pointSPThirdSet5SetUS);

                    Toast.makeText(this, pl1_1_5setUS + " " + pl1_2_5setUS + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                    setWinnerSet_5US(playerFirst5SetUS, pointFPThirdSet5SetUS, "firstPlayer");
                    setEnab();

                } else {

                    pointFPThirdSet5SetUS.setText(String.valueOf(7));
                    SET_5_US_3_SET_a_DONE = true;
                    SET_5_US_3_SET_tb_DONE = true;
                    SET_5_US_3_Set = false;
                    SET_5_US_4_Set = true;
                    A_pl_5SetUS_tb3 = x1;
                    B_pl_5SetUS_tb3 = x2;
                    convert(x1, x2, pointFPThirdSet5SetUS, pointSPThirdSet5SetUS);

                    Toast.makeText(this, pl1_1_5setUS + " " + pl1_2_5setUS + " " + getString(R.string.wins_3_set), Toast.LENGTH_LONG).show();
                    pointFPThirdSet5SetUS.setBackgroundResource(R.drawable.frame_winner);
                }
            } else if (SET_5_US_2_Set) {

                pointFPSecondSet5SetUS.setText(String.valueOf(7));
                SET_5_US_2_SET_a_DONE = true;
                SET_5_US_2_SET_tb_DONE = true;
                SET_5_US_2_Set = false;
                SET_5_US_3_Set = true;
                A_pl_5SetUS_tb2 = x1;
                B_pl_5SetUS_tb2 = x2;
                convert(x1, x2, pointFPSecondSet5SetUS, pointSPSecondSet5SetUS);

                Toast.makeText(this, pl1_1_5setUS + " " + pl1_2_5setUS + " " + getString(R.string.wins_2_set), Toast.LENGTH_LONG).show();
                pointFPSecondSet5SetUS.setBackgroundResource(R.drawable.frame_winner);

            } else if (SET_5_US_1_Set) {

                pointFPFirstSet5SetUS.setText(String.valueOf(7));
                SET_5_US_1_SET_a_DONE = true;
                SET_5_US_1_SET_tb_DONE = true;
                SET_5_US_1_Set = false;
                SET_5_US_2_Set = true;
                A_pl_5SetUS_tb1 = x1;
                B_pl_5SetUS_tb1 = x2;
                convert(x1, x2, pointFPFirstSet5SetUS, pointSPFirstSet5SetUS);

                Toast.makeText(this, pl1_1_5setUS + " " + pl1_2_5setUS + " " + getString(R.string.wins_1_set), Toast.LENGTH_LONG).show();
                pointFPFirstSet5SetUS.setBackgroundResource(R.drawable.frame_winner);
            }
        } else if ((x2 == 7 & x1 <= x2 - 2) | (x2 > 7 & x1 <= x2 - 2)) {

            if (SET_5_US_5_Set) {

                pointSPFiveSet5SetUS.setText(String.valueOf(7));
                SET_5_US_MATCH_DONE = true;
                SET_5_US_5_SET_tb_DONE = true;
                tieBreakDone_5SetUS = true;
                A_pl_5SetUS_tb5 = x1;
                B_pl_5SetUS_tb5 = x2;
                convert(x1, x2, pointFPFiveSet5SetUS, pointSPFiveSet5SetUS);

                Toast.makeText(this, pl2_1_5setUS + " " + pl2_2_5setUS + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                setWinnerSet_5US(playerSecond5SetUS, pointSPFiveSet5SetUS, "secondPlayer");
                setEnab();

            } else if (SET_5_US_4_Set) {

                if ((SET_5_US_1_SET_b_DONE & SET_5_US_2_SET_a_DONE & SET_5_US_3_SET_b_DONE)
                        | (SET_5_US_1_SET_b_DONE & SET_5_US_2_SET_b_DONE & SET_5_US_3_SET_a_DONE)
                        | (SET_5_US_1_SET_a_DONE & SET_5_US_2_SET_b_DONE & SET_5_US_3_SET_b_DONE)) {

                    pointSPFourSet5SetUS.setText(String.valueOf(7));
                    SET_5_US_MATCH_DONE = true;
                    SET_5_US_4_SET_tb_DONE = true;
                    SET_5_US_4_SET_b_DONE = true;
                    tieBreakDone_5SetUS = true;
                    A_pl_5SetUS_tb4 = x1;
                    B_pl_5SetUS_tb4 = x2;
                    convert(x1, x2, pointFPFourSet5SetUS, pointSPFourSet5SetUS);

                    Toast.makeText(this, pl2_1_5setUS + " " + pl2_2_5setUS + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                    setWinnerSet_5US(playerSecond5SetUS, pointSPFourSet5SetUS, "secondPlayer");
                    setEnab();

                } else {

                    pointSPFourSet5SetUS.setText(String.valueOf(7));
                    SET_5_US_4_SET_b_DONE = true;
                    SET_5_US_4_SET_tb_DONE = true;
                    tieBreakDone_5SetUS = true;
                    SET_5_US_4_Set = false;
                    SET_5_US_5_Set = true;
                    A_pl_5SetUS_tb4 = x1;
                    B_pl_5SetUS_tb4 = x2;
                    convert(x1, x2, pointFPFourSet5SetUS, pointSPFourSet5SetUS);

                    Toast.makeText(this, pl2_1_5setUS + " " + pl2_2_5setUS + " " + getString(R.string.wins_4_set), Toast.LENGTH_LONG).show();
                    pointSPFourSet5SetUS.setBackgroundResource(R.drawable.frame_winner);
                }
            } else if (SET_5_US_3_Set) {

                if (SET_5_US_1_SET_b_DONE & SET_5_US_2_SET_b_DONE) {

                    pointSPThirdSet5SetUS.setText(String.valueOf(7));
                    SET_5_US_MATCH_DONE = true;
                    SET_5_US_3_SET_tb_DONE = true;
                    SET_5_US_3_SET_b_DONE = true;
                    tieBreakDone_5SetUS = true;
                    A_pl_5SetUS_tb3 = x1;
                    B_pl_5SetUS_tb3 = x2;
                    convert(x1, x2, pointFPThirdSet5SetUS, pointSPThirdSet5SetUS);

                    Toast.makeText(this, pl2_1_5setUS + " " + pl2_2_5setUS + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                    setWinnerSet_5US(playerSecond5SetUS, pointSPThirdSet5SetUS, "secondPlayer");
                    setEnab();

                } else {

                    pointSPThirdSet5SetUS.setText(String.valueOf(7));
                    SET_5_US_3_SET_b_DONE = true;
                    SET_5_US_3_SET_tb_DONE = true;
                    tieBreakDone_5SetUS = true;
                    SET_5_US_3_Set = false;
                    SET_5_US_4_Set = true;
                    A_pl_5SetUS_tb3 = x1;
                    B_pl_5SetUS_tb3 = x2;
                    convert(x1, x2, pointFPThirdSet5SetUS, pointSPThirdSet5SetUS);

                    Toast.makeText(this, pl2_1_5setUS + " " + pl2_2_5setUS + " " + getString(R.string.wins_3_set), Toast.LENGTH_LONG).show();
                    pointSPThirdSet5SetUS.setBackgroundResource(R.drawable.frame_winner);
                }
            } else if (SET_5_US_2_Set) {

                pointSPSecondSet5SetUS.setText(String.valueOf(7));
                SET_5_US_2_SET_b_DONE = true;
                SET_5_US_2_SET_tb_DONE = true;
                SET_5_US_2_Set = false;
                SET_5_US_3_Set = true;
                A_pl_5SetUS_tb2 = x1;
                B_pl_5SetUS_tb2 = x2;
                convert(x1, x2, pointFPSecondSet5SetUS, pointSPSecondSet5SetUS);

                Toast.makeText(this, pl2_1_5setUS + " " + pl2_2_5setUS + " " + getString(R.string.wins_2_set), Toast.LENGTH_LONG).show();
                pointSPSecondSet5SetUS.setBackgroundResource(R.drawable.frame_winner);

            } else if (SET_5_US_1_Set) {

                pointSPFirstSet5SetUS.setText(String.valueOf(7));
                SET_5_US_1_SET_b_DONE = true;
                SET_5_US_1_SET_tb_DONE = true;
                SET_5_US_1_Set = false;
                SET_5_US_2_Set = true;
                A_pl_5SetUS_tb1 = x1;
                B_pl_5SetUS_tb1 = x2;
                convert(x1, x2, pointFPFirstSet5SetUS, pointSPFirstSet5SetUS);

                Toast.makeText(this, pl2_1_5setUS + " " + pl2_2_5setUS + " " + getString(R.string.wins_1_set), Toast.LENGTH_LONG).show();
                pointSPFirstSet5SetUS.setBackgroundResource(R.drawable.frame_winner);
            }
        }
    }

    public void convert(int mp, int yu, TextView s1, TextView s2) {

        for (int i = 0; i < String.valueOf(mp).length(); i++) {
            int k = String.valueOf(mp).length() - i;
            sstr1 = degreeDigits[Character.getNumericValue(String.valueOf(mp).charAt(k - 1))] + sstr1;
        }

        for (int kol = 0; kol < String.valueOf(yu).length(); kol++) {
            int kik = String.valueOf(yu).length() - kol;
            sstr1s1 = degreeDigits[Character.getNumericValue(String.valueOf(yu).charAt(kik - 1))] + sstr1s1;
        }

        s1.setText(s1.getText() + sstr1);
        s2.setText(s2.getText() + sstr1s1);

        tieBreakDone_5SetUS = true;
        tb_5SetUS = false;

        pointFP5SetUS.setText(String.valueOf(0));
        pointSP5SetUS.setText(String.valueOf(0));

        sstr1 = "";
        sstr1s1 = "";

        fpls5US = 0;
        spls5US = 0;
        tbfp = 0;
        tbsp = 0;
    }

    public void convertUndo(int mp1, int yu1, TextView s11, TextView s21) {
        for (int i = 0; i < String.valueOf(mp1).length(); i++) {
            int k = String.valueOf(mp1).length() - i;
            sstr1 = degreeDigits[Character.getNumericValue(String.valueOf(mp1).charAt(k - 1))] + sstr1;
        }

        for (int kol = 0; kol < String.valueOf(yu1).length(); kol++) {
            int kik = String.valueOf(yu1).length() - kol;
            sstr1s1 = degreeDigits[Character.getNumericValue(String.valueOf(yu1).charAt(kik - 1))] + sstr1s1;
        }
        if (s11.getText().equals("7")) {

            s11.setBackgroundResource(R.drawable.frame_winner);
        } else {
            s21.setBackgroundResource(R.drawable.frame_winner);
        }
        s11.setText(s11.getText() + sstr1);
        s21.setText(s21.getText() + sstr1s1);
        sstr1 = "";
        sstr1s1 = "";
    }

    public int getKorI(TextView ki) {
        if (ki.getText().toString().equals("0")) {
            n7 = 0;
        } else if (ki.getText().toString().equals("15")) {
            n7 = 1;
        } else if (ki.getText().toString().equals("30")) {
            n7 = 2;
        } else if (ki.getText().toString().equals("40")) {
            n7 = 3;
        } else if (ki.getText().toString().equals("Ad")) {
            n7 = 4;
        }
        return n7;
    }

    public int curNumberOfPoints(int p) {
        SQLiteDatabase db = mDbHelper5SetUS.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT number FROM statistic  WHERE _ID = " + Integer.toString(mDbHelper5SetUS.getLastId() - p), new String[]{});
        cursor.moveToFirst();
        curNum5SetUS = (int) cursor.getLong(0);
        cursor.close();
        return curNum5SetUS;
    }

    public void setEnab() {
        goalFP5SetUS.setEnabled(false);
        goalSP5SetUS.setEnabled(false);
        spinner1p5sUS.setEnabled(false);
        spinner2p5sUS.setEnabled(false);
    }

    private void setTbmode_5US() {

        Toast.makeText(getBaseContext(), getString(R.string.Tie_break_starts), Toast.LENGTH_SHORT).show();

        tb1pl_5SetUS = false;
        tb2pl_5SetUS = false;

        tbfp = 0;
        tbsp = 0;

        pointFP5SetUS.setText(String.valueOf(tbfp));
        pointSP5SetUS.setText(String.valueOf(tbsp));

        tb_5SetUS = true;
    }


    public void undo5SetUS(View view) {

        ad5SetUS = new AlertDialog.Builder(context);

        ad5SetUS.setMessage(getString(R.string.really_undo));

        ad5SetUS.setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                undo5SetUS1();
            }
        });
        ad5SetUS.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
            }
        });
        ad5SetUS.setCancelable(true);
        ad5SetUS.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
            }
        });
        ad5SetUS.show();
    }

    public void undo5SetUS1() {

        goalFP5SetUS.setEnabled(true);
        goalSP5SetUS.setEnabled(true);
        spinner1p5sUS.setEnabled(true);
        spinner2p5sUS.setEnabled(true);
        beEnabled(redo_5SetUS, getDrawable(R.drawable.ic_redo_black_24dp));

        noEnabled(saveButton5SetUS, getDrawable(R.drawable.ic_save_black_24dp_0_3));
        playerFirst5SetUS.setBackgroundResource(R.drawable.frame_player);
        playerSecond5SetUS.setBackgroundResource(R.drawable.frame_player);
        Set5USStatusMatch = "Paused";
        stopMatchIsEnabled5SetUS = true;

        undoCounter5SetUS = undoCounter5SetUS + 1;

        updateColumnNumberDown5SetUS();

        SET_5_US_MATCH_DONE = false;

        if (getNum5SetUS("ktbfps").equals("-2")) {

            pointFP5SetUS.setText(adv_5SetUS);
            pointSP5SetUS.setText(getNum5SetUS("ktbsps"));

        } else if (getNum5SetUS("ktbsps").equals("-2")) {

            pointSP5SetUS.setText(adv_5SetUS);
            pointFP5SetUS.setText(getNum5SetUS("ktbfps"));

        } else {
            pointFP5SetUS.setText(getNum5SetUS("ktbfps"));
            pointSP5SetUS.setText(getNum5SetUS("ktbsps"));
        }

        if (getNum5SetUS("set1a").equals("7") & getNum5SetUS("set1b").equals("6")) {

            setUndoNum(pointFPFirstSet5SetUS, pointSPFirstSet5SetUS, "set1a", "set1b", A_pl_5SetUS_tb1, B_pl_5SetUS_tb1);


        } else if (getNum5SetUS("set1a").equals("6") & getNum5SetUS("set1b").equals("7")) {

            setUndoNum(pointFPFirstSet5SetUS, pointSPFirstSet5SetUS, "set1a", "set1b", A_pl_5SetUS_tb1, B_pl_5SetUS_tb1);

        } else {

            pointFPFirstSet5SetUS.setText(getNum5SetUS("set1a"));
            pointSPFirstSet5SetUS.setText(getNum5SetUS("set1b"));
        }

        if (getNum5SetUS("set2a").equals("7") & getNum5SetUS("set2b").equals("6")) {

            setUndoNum(pointFPSecondSet5SetUS, pointSPSecondSet5SetUS, "set2a", "set2b", A_pl_5SetUS_tb2, B_pl_5SetUS_tb2);

        } else if (getNum5SetUS("set2a").equals("6") & getNum5SetUS("set2b").equals("7")) {

            setUndoNum(pointFPSecondSet5SetUS, pointSPSecondSet5SetUS, "set2a", "set2b", A_pl_5SetUS_tb2, B_pl_5SetUS_tb2);

        } else {

            pointFPSecondSet5SetUS.setText(getNum5SetUS("set2a"));
            pointSPSecondSet5SetUS.setText(getNum5SetUS("set2b"));
        }

        if (getNum5SetUS("set3a").equals("7") & getNum5SetUS("set3b").equals("6")) {

            setUndoNum(pointFPThirdSet5SetUS, pointSPThirdSet5SetUS, "set3a", "set3b", A_pl_5SetUS_tb3, B_pl_5SetUS_tb3);

        } else if (getNum5SetUS("set3a").equals("6") & getNum5SetUS("set3b").equals("7")) {

            setUndoNum(pointFPThirdSet5SetUS, pointSPThirdSet5SetUS, "set3a", "set3b", A_pl_5SetUS_tb3, B_pl_5SetUS_tb3);

        } else {

            pointFPThirdSet5SetUS.setText(getNum5SetUS("set3a"));
            pointSPThirdSet5SetUS.setText(getNum5SetUS("set3b"));
        }

        if (getNum5SetUS("set4a").equals("7") & getNum5SetUS("set4b").equals("6")) {

            setUndoNum(pointFPFourSet5SetUS, pointSPFourSet5SetUS, "set4a", "set4b", A_pl_5SetUS_tb4, B_pl_5SetUS_tb4);

        } else if (getNum5SetUS("set4a").equals("6") & getNum5SetUS("set4b").equals("7")) {

            setUndoNum(pointFPFourSet5SetUS, pointSPFourSet5SetUS, "set4a", "set4b", A_pl_5SetUS_tb4, B_pl_5SetUS_tb4);

        } else {

            pointFPFourSet5SetUS.setText(getNum5SetUS("set4a"));
            pointSPFourSet5SetUS.setText(getNum5SetUS("set4b"));
        }

        pointFPFiveSet5SetUS.setText(getNum5SetUS("set5a"));
        pointSPFiveSet5SetUS.setText(getNum5SetUS("set5b"));


        if (SET_5_US_5_SET_tb_DONE) {

            SET_5_US_5_SET_tb_DONE = false;
            SET_5_US_5_Set = true;
            tb_5SetUS = true;
            tieBreakDone_5SetUS = false;

            pointFPFiveSet5SetUS.setBackgroundResource(R.drawable.frame_player);
            pointSPFiveSet5SetUS.setBackgroundResource(R.drawable.frame_player);
        }

        if (SET_5_US_5_Set) {

            if (tb_5SetUS) {

                if (!"6".equals(getNum5SetUS("set5a")) | !"6".equals(getNum5SetUS("set5b"))) {

                    tb_5SetUS = false;
                }

            } else if (!tb_5SetUS) {

                if ((getNum5SetUS("set4a").equals("7") & (getNum5SetUS("set4b").equals("6") | getNum5SetUS("set4b").equals("5"))) |
                        (getNum5SetUS("set4b").equals("7") & (getNum5SetUS("set4a").equals("6") | getNum5SetUS("set4a").equals("5"))) |
                        (getNum5SetUS("set4a").equals("6") & !"6".equals(getNum5SetUS("set4b")) & !"5".equals(getNum5SetUS("set4b"))) |
                        (getNum5SetUS("set4b").equals("6") & !"6".equals(getNum5SetUS("set4a")) & !"5".equals(getNum5SetUS("set4a")))) {

                    SET_5_US_5_Set = true;

                } else {

                    SET_5_US_5_Set = false;
                    SET_5_US_4_Set = true;
                    SET_5_US_4_SET_a_DONE = false;
                    SET_5_US_4_SET_b_DONE = false;
                }
            }
            pointFPFiveSet5SetUS.setBackgroundResource(R.drawable.frame_player);
            pointSPFiveSet5SetUS.setBackgroundResource(R.drawable.frame_player);
        }
        if (SET_5_US_4_Set) {

            SET_5_US_4_SET_a_DONE = false;
            SET_5_US_4_SET_b_DONE = false;

            pointFPFourSet5SetUS.setBackgroundResource(R.drawable.frame_player);
            pointSPFourSet5SetUS.setBackgroundResource(R.drawable.frame_player);

            if (SET_5_US_4_SET_tb_DONE) {

                SET_5_US_4_SET_tb_DONE = false;
                tb_5SetUS = true;
                tieBreakDone_5SetUS = false;
            }
            if (tb_5SetUS) {

                if (!"6".equals(getNum5SetUS("set4a")) | !"6".equals(getNum5SetUS("set4b"))) {
                    tb_5SetUS = false;
                }
            } else if (!tb_5SetUS) {

                if ((getNum5SetUS("set3a").equals("7") & (getNum5SetUS("set3b").equals("6") | getNum5SetUS("set3b").equals("5"))) |
                        (getNum5SetUS("set3b").equals("7") & (getNum5SetUS("set3a").equals("6") | getNum5SetUS("set3a").equals("5"))) |
                        (getNum5SetUS("set3a").equals("6") & !"6".equals(getNum5SetUS("set3b")) & !"5".equals(getNum5SetUS("set3b"))) |
                        (getNum5SetUS("set3b").equals("6") & !"6".equals(getNum5SetUS("set3a")) & !"5".equals(getNum5SetUS("set3a")))) {

                    SET_5_US_4_Set = true;

                } else {

                    SET_5_US_3_Set = true;
                    SET_5_US_4_Set = false;
                    SET_5_US_4_SET_a_DONE = false;
                    SET_5_US_4_SET_b_DONE = false;
                }
            }
        }
        if (SET_5_US_3_Set) {

            SET_5_US_3_SET_a_DONE = false;
            SET_5_US_3_SET_b_DONE = false;

            pointFPThirdSet5SetUS.setBackgroundResource(R.drawable.frame_player);
            pointSPThirdSet5SetUS.setBackgroundResource(R.drawable.frame_player);

            if (SET_5_US_3_SET_tb_DONE) {

                SET_5_US_3_SET_tb_DONE = false;
                tb_5SetUS = true;
                tieBreakDone_5SetUS = false;
            }
            if (tb_5SetUS) {

                if (!"6".equals(getNum5SetUS("set3a")) | !"6".equals(getNum5SetUS("set3b"))) {
                    tb_5SetUS = false;

                }
            } else if (!tb_5SetUS) {

                if ((getNum5SetUS("set2a").equals("7") & (getNum5SetUS("set2b").equals("6") | getNum5SetUS("set2b").equals("5"))) |
                        (getNum5SetUS("set2b").equals("7") & (getNum5SetUS("set2a").equals("6") | getNum5SetUS("set2a").equals("5"))) |
                        (getNum5SetUS("set2a").equals("6") & !"6".equals(getNum5SetUS("set2b")) & !"5".equals(getNum5SetUS("set2b"))) |
                        (getNum5SetUS("set2b").equals("6") & !"6".equals(getNum5SetUS("set2a")) & !"5".equals(getNum5SetUS("set2a")))) {

                    SET_5_US_3_Set = true;

                } else {

                    SET_5_US_2_Set = true;
                    SET_5_US_3_Set = false;
                    SET_5_US_2_SET_a_DONE = false;
                    SET_5_US_2_SET_b_DONE = false;
                }
            }
        }

        if (SET_5_US_2_Set) {

            SET_5_US_2_SET_a_DONE = false;
            SET_5_US_2_SET_b_DONE = false;

            pointFPSecondSet5SetUS.setBackgroundResource(R.drawable.frame_player);
            pointSPSecondSet5SetUS.setBackgroundResource(R.drawable.frame_player);

            if (SET_5_US_2_SET_tb_DONE) {

                SET_5_US_2_SET_tb_DONE = false;
                tb_5SetUS = true;
                tieBreakDone_5SetUS = false;
            }
            if (tb_5SetUS) {

                if (!"6".equals(getNum5SetUS("set2a")) | !"6".equals(getNum5SetUS("set2b"))) {
                    tb_5SetUS = false;

                }
            } else if (!tb_5SetUS) {

                if ((getNum5SetUS("set1a").equals("7") & (getNum5SetUS("set1b").equals("6") | getNum5SetUS("set1b").equals("5"))) |
                        (getNum5SetUS("set1b").equals("7") & (getNum5SetUS("set1a").equals("6") | getNum5SetUS("set1a").equals("5"))) |
                        (getNum5SetUS("set1a").equals("6") & !"6".equals(getNum5SetUS("set1b")) & !"5".equals(getNum5SetUS("set1b"))) |
                        (getNum5SetUS("set1b").equals("6") & !"6".equals(getNum5SetUS("set1a")) & !"5".equals(getNum5SetUS("set1a")))) {

                    SET_5_US_2_Set = true;

                } else {

                    SET_5_US_1_Set = true;
                    SET_5_US_2_Set = false;
                    SET_5_US_1_SET_a_DONE = false;
                    SET_5_US_1_SET_b_DONE = false;
                }
            }
        }


        if (SET_5_US_1_Set) {

            pointFPFirstSet5SetUS.setBackgroundResource(R.drawable.frame_player);
            pointSPFirstSet5SetUS.setBackgroundResource(R.drawable.frame_player);

            if (SET_5_US_1_SET_tb_DONE) {
                SET_5_US_1_SET_tb_DONE = false;
                tb_5SetUS = true;
                tieBreakDone_5SetUS = false;
            }

            if (tb_5SetUS) {
                if (!"6".equals(getNum5SetUS("set1a")) | !"6".equals(getNum5SetUS("set1b"))) {
                    tb_5SetUS = false;
                }
            }
        }

        goalsCounter5SetUS--;
        if (goalsCounter5SetUS <= 0) {
            noEnabled(undo_5SetUS, getDrawable(R.drawable.ic_undo_black_24dp_0_3));
        }
    }


    public String getNum5SetUS(String jg) {
        SQLiteDatabase db = mDbHelper5SetUS.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT " + jg + " FROM "
                + Contract.UndoNumbers.TABLE_NAME_1 + " WHERE " + Contract.UndoNumbers.COLUMN_NUM_NUM +
                " = (SELECT MAX( " + Contract.UndoNumbers.COLUMN_NUM_NUM + " ) FROM " + Contract.UndoNumbers.TABLE_NAME_1 + " ) - " + undoCounter5SetUS, new String[]{});
        cursor.moveToFirst();
        prevNum5SetUS = (int) cursor.getLong(0);
        cursor.close();
        return String.valueOf(prevNum5SetUS);
    }

    public void setUndoNum(TextView tv1, TextView tv2, String st1, String st2, int tn1, int tn2) {
        tv1.setText(getNum5SetUS(st1));
        tv2.setText(getNum5SetUS(st2));
        convertUndo(tn1, tn2, tv1, tv2);
    }


    public void redo5SetUS(View view) {

        ad5SetUS = new AlertDialog.Builder(context);

        ad5SetUS.setMessage(getString(R.string.really_redo));

        ad5SetUS.setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                redo5SetUS1();
            }
        });
        ad5SetUS.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
            }
        });
        ad5SetUS.setCancelable(true);
        ad5SetUS.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
            }
        });
        ad5SetUS.show();
    }

    public void redo5SetUS1() {

        beEnabled(undo_5SetUS, getDrawable(R.drawable.ic_undo_black_24dp));
        spinner1p5sUS.setSelection(0);
        spinner2p5sUS.setSelection(0);

        updateColumnNumberUp5SetUS();

        undoCounter5SetUS = undoCounter5SetUS - 1;

        if (getNum5SetUS("ktbfps").equals("-2")) {

            pointFP5SetUS.setText(adv_5SetUS);
            pointSP5SetUS.setText(getNum5SetUS("ktbsps"));

        } else if (getNum5SetUS("ktbsps").equals("-2")) {

            pointFP5SetUS.setText(getNum5SetUS("ktbfps"));
            pointSP5SetUS.setText(adv_5SetUS);

        } else {

            pointFP5SetUS.setText(getNum5SetUS("ktbfps"));
            pointSP5SetUS.setText(getNum5SetUS("ktbsps"));
        }

        if ((getNum5SetUS("set1a").equals("7") & getNum5SetUS("set1b").equals("6")) | (getNum5SetUS("set1a").equals("6") & getNum5SetUS("set1b").equals("7"))) {

            setUndoNum(pointFPFirstSet5SetUS, pointSPFirstSet5SetUS, "set1a", "set1b", A_pl_5SetUS_tb1, B_pl_5SetUS_tb1);

        } else {

            pointFPFirstSet5SetUS.setText(getNum5SetUS("set1a"));
            pointSPFirstSet5SetUS.setText(getNum5SetUS("set1b"));
        }

        if ((getNum5SetUS("set2a").equals("7") & getNum5SetUS("set2b").equals("6")) | (getNum5SetUS("set2a").equals("6") & getNum5SetUS("set2b").equals("7"))) {

            setUndoNum(pointFPSecondSet5SetUS, pointSPSecondSet5SetUS, "set2a", "set2b", A_pl_5SetUS_tb2, B_pl_5SetUS_tb2);

        } else {

            pointFPSecondSet5SetUS.setText(getNum5SetUS("set2a"));
            pointSPSecondSet5SetUS.setText(getNum5SetUS("set2b"));

        }

        if ((getNum5SetUS("set3a").equals("7") & getNum5SetUS("set3b").equals("6")) | (getNum5SetUS("set3a").equals("6") & getNum5SetUS("set3b").equals("7"))) {

            setUndoNum(pointFPThirdSet5SetUS, pointSPThirdSet5SetUS, "set3a", "set3b", A_pl_5SetUS_tb3, B_pl_5SetUS_tb3);

        } else {

            pointFPThirdSet5SetUS.setText(getNum5SetUS("set3a"));
            pointSPThirdSet5SetUS.setText(getNum5SetUS("set3b"));

        }

        if ((getNum5SetUS("set4a").equals("7") & getNum5SetUS("set4b").equals("6")) | (getNum5SetUS("set4a").equals("6") & getNum5SetUS("set4b").equals("7"))) {

            setUndoNum(pointFPFourSet5SetUS, pointSPFourSet5SetUS, "set4a", "set4b", A_pl_5SetUS_tb4, B_pl_5SetUS_tb4);

        } else {

            pointFPFourSet5SetUS.setText(getNum5SetUS("set4a"));
            pointSPFourSet5SetUS.setText(getNum5SetUS("set4b"));

        }

        if ((getNum5SetUS("set5a").equals("7") & getNum5SetUS("set5b").equals("6")) | (getNum5SetUS("set5a").equals("6") & getNum5SetUS("set5b").equals("7"))) {

            setUndoNum(pointFPFiveSet5SetUS, pointSPFiveSet5SetUS, "set5a", "set5b", A_pl_5SetUS_tb5, B_pl_5SetUS_tb5);

        } else {

            pointFPFiveSet5SetUS.setText(getNum5SetUS("set5a"));
            pointSPFiveSet5SetUS.setText(getNum5SetUS("set5b"));

        }


        if (SET_5_US_1_Set) {

            if (getNum5SetUS("set1a").equals("6") & getNum5SetUS("set1b").equals("6")) {

                tb_5SetUS = true;
                checkTieBreakNumber(Integer.valueOf(getNum5SetUS("ktbfps")), Integer.valueOf(getNum5SetUS("ktbsps")));

                if (getNum5SetUS("ktbfps").equals("0") && getNum5SetUS("ktbsps").equals("0")) {
                    Toast.makeText(getBaseContext(), getString(R.string.Tie_break_starts), Toast.LENGTH_SHORT).show();
                }

            } else if (getNum5SetUS("set1a").equals("7") & getNum5SetUS("set1b").equals("6") & getNum5SetUS("ktbfps").equals("0") & getNum5SetUS("ktbsps").equals("0")) {

                tb_5SetUS = false;
                SET_5_US_1_SET_a_DONE = true;
                SET_5_US_1_SET_tb_DONE = true;
                tieBreakDone_5SetUS = true;
                SET_5_US_1_Set = false;
                SET_5_US_2_Set = true;

                pointFPFirstSet5SetUS.setBackgroundResource(R.drawable.frame_winner);
                Toast.makeText(this, pl1_1_5setUS + " " + pl1_2_5setUS + " " + getString(R.string.wins_1_set), Toast.LENGTH_LONG).show();


            } else if (getNum5SetUS("set1a").equals("7") & getNum5SetUS("set1b").equals("6")
                    & (!"0".equals(getNum5SetUS("ktbfps")) | !"0".equals(getNum5SetUS("ktbsps")))) {

                tieBreakDone_5SetUS = false;

            } else if (getNum5SetUS("set1a").equals("6") & getNum5SetUS("set1b").equals("7")
                    & (!"0".equals(getNum5SetUS("ktbfps")) | !"0".equals(getNum5SetUS("ktbsps")))) {

                tieBreakDone_5SetUS = false;

            } else if (getNum5SetUS("set1a").equals("6") & getNum5SetUS("set1b").equals("7") & getNum5SetUS("ktbfps").equals("0") & getNum5SetUS("ktbsps").equals("0")) {

                tb_5SetUS = false;
                SET_5_US_1_SET_b_DONE = true;
                SET_5_US_1_SET_tb_DONE = true;
                tieBreakDone_5SetUS = true;
                SET_5_US_1_Set = false;
                SET_5_US_2_Set = true;

                pointSPFirstSet5SetUS.setBackgroundResource(R.drawable.frame_winner);
                Toast.makeText(this, pl2_1_5setUS + " " + pl2_2_5setUS + " " + getString(R.string.wins_1_set), Toast.LENGTH_LONG).show();

            } else {
                checkSetNumber(Integer.valueOf(getNum5SetUS("set1a")), Integer.valueOf(getNum5SetUS("set1b")));
            }
        }

        if (SET_5_US_2_Set) {

            if (getNum5SetUS("set2a").equals("6") & getNum5SetUS("set2b").equals("6")) {

                tb_5SetUS = true;

                checkTieBreakNumber(Integer.valueOf(getNum5SetUS("ktbfps")), Integer.valueOf(getNum5SetUS("ktbsps")));

                if (getNum5SetUS("ktbfps").equals("0") && getNum5SetUS("ktbsps").equals("0")) {

                    Toast.makeText(getBaseContext(), getString(R.string.Tie_break_starts), Toast.LENGTH_SHORT).show();
                }
            } else if (getNum5SetUS("set1a").equals("7") & getNum5SetUS("set1b").equals("6")

                    & (!"0".equals(getNum5SetUS("ktbfps")) | !"0".equals(getNum5SetUS("ktbsps")))) {

                tieBreakDone_5SetUS = false;

            } else if (getNum5SetUS("set1a").equals("6") & getNum5SetUS("set1b").equals("7")

                    & (!"0".equals(getNum5SetUS("ktbfps")) | !"0".equals(getNum5SetUS("ktbsps")))) {

                tieBreakDone_5SetUS = false;

            } else if (getNum5SetUS("set2a").equals("7") & getNum5SetUS("set2b").equals("6") & getNum5SetUS("ktbfps").equals("0") & getNum5SetUS("ktbsps").equals("0")) {

                SET_5_US_2_SET_a_DONE = true;
                SET_5_US_2_SET_tb_DONE = true;
                SET_5_US_2_Set = false;
                SET_5_US_3_Set = true;
                tb_5SetUS = false;
                tieBreakDone_5SetUS = true;

                pointFPSecondSet5SetUS.setBackgroundResource(R.drawable.frame_winner);
                Toast.makeText(this, pl1_1_5setUS + " " + pl1_2_5setUS + " " + getString(R.string.wins_2_set), Toast.LENGTH_LONG).show();

            } else if (getNum5SetUS("set2a").equals("6") & getNum5SetUS("set2b").equals("7") & getNum5SetUS("ktbfps").equals("0") & getNum5SetUS("ktbsps").equals("0")) {

                SET_5_US_2_SET_b_DONE = true;
                SET_5_US_2_SET_tb_DONE = true;
                SET_5_US_2_Set = false;
                SET_5_US_3_Set = true;
                tb_5SetUS = false;
                tieBreakDone_5SetUS = true;

                pointSPSecondSet5SetUS.setBackgroundResource(R.drawable.frame_winner);
                Toast.makeText(this, pl2_1_5setUS + " " + pl2_2_5setUS + " " + getString(R.string.wins_2_set), Toast.LENGTH_LONG).show();

            } else {
                checkSetNumber(Integer.valueOf(getNum5SetUS("set2a")), Integer.valueOf(getNum5SetUS("set2b")));
            }
        }

        if (SET_5_US_3_Set) {

            if (getNum5SetUS("set3a").equals("6") & getNum5SetUS("set3b").equals("6")) {

                tb_5SetUS = true;

                checkTieBreakNumber(Integer.valueOf(getNum5SetUS("ktbfps")), Integer.valueOf(getNum5SetUS("ktbsps")));

                if (getNum5SetUS("ktbfps").equals("0") && getNum5SetUS("ktbsps").equals("0")) {

                    Toast.makeText(getBaseContext(), getString(R.string.Tie_break_starts), Toast.LENGTH_SHORT).show();
                }
            } else if (getNum5SetUS("set2a").equals("7") & getNum5SetUS("set2b").equals("6")

                    & (!"0".equals(getNum5SetUS("ktbfps")) | !"0".equals(getNum5SetUS("ktbsps")))) {

                tieBreakDone_5SetUS = false;

            } else if (getNum5SetUS("set2a").equals("6") & getNum5SetUS("set2b").equals("7")

                    & (!"0".equals(getNum5SetUS("ktbfps")) | !"0".equals(getNum5SetUS("ktbsps")))) {

                tieBreakDone_5SetUS = false;

            } else if (getNum5SetUS("set3a").equals("7") & getNum5SetUS("set3b").equals("6") & getNum5SetUS("ktbfps").equals("0") & getNum5SetUS("ktbsps").equals("0")) {

                if (SET_5_US_1_SET_a_DONE & SET_5_US_2_SET_a_DONE) {

                    SET_5_US_MATCH_DONE = true;
                    SET_5_US_3_SET_tb_DONE = true;
                    tb_5SetUS = false;
                    tieBreakDone_5SetUS = true;

                    Toast.makeText(this, pl1_1_5setUS + " " + pl1_2_5setUS + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                    setWinnerSet_5US(playerFirst5SetUS, pointFPThirdSet5SetUS, "firstPlayer");
                    setEnab();

                } else {

                    SET_5_US_3_SET_a_DONE = true;
                    SET_5_US_3_SET_tb_DONE = true;
                    tb_5SetUS = false;
                    tieBreakDone_5SetUS = true;
                    SET_5_US_3_Set = false;
                    SET_5_US_4_Set = true;

                    Toast.makeText(this, pl1_1_5setUS + " " + pl1_2_5setUS + " " + getString(R.string.wins_3_set), Toast.LENGTH_LONG).show();
                    pointFPThirdSet5SetUS.setBackgroundResource(R.drawable.frame_winner);
                }
            } else if (getNum5SetUS("set3a").equals("6") & getNum5SetUS("set3b").equals("7") & getNum5SetUS("ktbfps").equals("0") & getNum5SetUS("ktbsps").equals("0")) {

                if (SET_5_US_1_SET_b_DONE & SET_5_US_2_SET_b_DONE) {

                    SET_5_US_MATCH_DONE = true;
                    SET_5_US_3_SET_tb_DONE = true;
                    tb_5SetUS = false;
                    tieBreakDone_5SetUS = true;

                    Toast.makeText(this, pl2_1_5setUS + " " + pl2_2_5setUS + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                    setWinnerSet_5US(playerSecond5SetUS, pointSPThirdSet5SetUS, "secondPlayer");
                    setEnab();

                } else {

                    SET_5_US_3_SET_b_DONE = true;
                    SET_5_US_3_SET_tb_DONE = true;
                    tb_5SetUS = false;
                    tieBreakDone_5SetUS = true;
                    SET_5_US_3_Set = false;
                    SET_5_US_4_Set = true;

                    Toast.makeText(this, pl2_1_5setUS + " " + pl2_2_5setUS + " " + getString(R.string.wins_3_set), Toast.LENGTH_LONG).show();
                    pointSPThirdSet5SetUS.setBackgroundResource(R.drawable.frame_winner);
                }

            } else {
                checkSetNumber(Integer.valueOf(getNum5SetUS("set3a")), Integer.valueOf(getNum5SetUS("set3b")));
            }
        }

        if (SET_5_US_4_Set) {

            if (getNum5SetUS("set4a").equals("6") & getNum5SetUS("set4b").equals("6")) {

                tb_5SetUS = true;

                checkTieBreakNumber(Integer.valueOf(getNum5SetUS("ktbfps")), Integer.valueOf(getNum5SetUS("ktbsps")));

                if (getNum5SetUS("ktbfps").equals("0") && getNum5SetUS("ktbsps").equals("0")) {

                    Toast.makeText(getBaseContext(), getString(R.string.Tie_break_starts), Toast.LENGTH_SHORT).show();
                }
            } else if (getNum5SetUS("set3a").equals("7") & getNum5SetUS("set3b").equals("6")

                    & (!"0".equals(getNum5SetUS("ktbfps")) | !"0".equals(getNum5SetUS("ktbsps")))) {

                tieBreakDone_5SetUS = false;

            } else if (getNum5SetUS("set3a").equals("6") & getNum5SetUS("set3b").equals("7")

                    & (!"0".equals(getNum5SetUS("ktbfps")) | !"0".equals(getNum5SetUS("ktbsps")))) {

                tieBreakDone_5SetUS = false;

            } else if (getNum5SetUS("set4a").equals("7") & getNum5SetUS("set4b").equals("6") & getNum5SetUS("ktbfps").equals("0") & getNum5SetUS("ktbsps").equals("0")) {

                if ((SET_5_US_1_SET_a_DONE & SET_5_US_2_SET_a_DONE & SET_5_US_3_SET_b_DONE)
                        | (SET_5_US_1_SET_a_DONE & SET_5_US_2_SET_b_DONE & SET_5_US_3_SET_a_DONE)
                        | (SET_5_US_1_SET_b_DONE & SET_5_US_2_SET_a_DONE & SET_5_US_3_SET_a_DONE)) {

                    SET_5_US_MATCH_DONE = true;
                    SET_5_US_4_SET_tb_DONE = true;
                    tb_5SetUS = false;
                    tieBreakDone_5SetUS = true;

                    Toast.makeText(this, pl1_1_5setUS + " " + pl1_2_5setUS + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                    setWinnerSet_5US(playerFirst5SetUS, pointFPFourSet5SetUS, "firstPlayer");
                    setEnab();

                } else {

                    SET_5_US_4_SET_a_DONE = true;
                    SET_5_US_4_SET_tb_DONE = true;
                    tb_5SetUS = false;
                    tieBreakDone_5SetUS = true;
                    SET_5_US_4_Set = false;
                    SET_5_US_5_Set = true;

                    Toast.makeText(this, pl1_1_5setUS + " " + pl1_2_5setUS + " " + getString(R.string.wins_4_set), Toast.LENGTH_LONG).show();
                    pointFPFourSet5SetUS.setBackgroundResource(R.drawable.frame_winner);
                }
            } else if (getNum5SetUS("set4a").equals("6") & getNum5SetUS("set4b").equals("7") & getNum5SetUS("ktbfps").equals("0") & getNum5SetUS("ktbsps").equals("0")) {

                if ((SET_5_US_1_SET_b_DONE & SET_5_US_2_SET_a_DONE & SET_5_US_3_SET_b_DONE)
                        | (SET_5_US_1_SET_b_DONE & SET_5_US_2_SET_b_DONE & SET_5_US_3_SET_a_DONE)
                        | (SET_5_US_1_SET_a_DONE & SET_5_US_2_SET_b_DONE & SET_5_US_3_SET_b_DONE)) {

                    SET_5_US_MATCH_DONE = true;
                    SET_5_US_4_SET_tb_DONE = true;
                    tb_5SetUS = false;
                    tieBreakDone_5SetUS = true;

                    Toast.makeText(this, pl2_1_5setUS + " " + pl2_2_5setUS + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                    setWinnerSet_5US(playerSecond5SetUS, pointSPFourSet5SetUS, "secondPlayer");
                    setEnab();

                } else {

                    SET_5_US_4_SET_b_DONE = true;
                    SET_5_US_4_SET_tb_DONE = true;
                    tb_5SetUS = false;
                    tieBreakDone_5SetUS = true;
                    SET_5_US_4_Set = false;
                    SET_5_US_5_Set = true;

                    Toast.makeText(this, pl2_1_5setUS + " " + pl2_2_5setUS + " " + getString(R.string.wins_4_set), Toast.LENGTH_LONG).show();
                    pointSPFourSet5SetUS.setBackgroundResource(R.drawable.frame_winner);
                }
            } else {
                checkSetNumber(Integer.valueOf(getNum5SetUS("set4a")), Integer.valueOf(getNum5SetUS("set4b")));
            }
        }
        if (SET_5_US_5_Set) {

            if (getNum5SetUS("set5a").equals("6") & getNum5SetUS("set5b").equals("6")) {

                tb_5SetUS = true;

                checkTieBreakNumber(Integer.valueOf(getNum5SetUS("ktbfps")), Integer.valueOf(getNum5SetUS("ktbsps")));

                if (getNum5SetUS("ktbfps").equals("0") && getNum5SetUS("ktbsps").equals("0")) {

                    Toast.makeText(getBaseContext(), getString(R.string.Tie_break_starts), Toast.LENGTH_SHORT).show();
                }
            } else if (getNum5SetUS("set4a").equals("7") & getNum5SetUS("set4b").equals("6")

                    & (!"0".equals(getNum5SetUS("ktbfps")) | !"0".equals(getNum5SetUS("ktbsps")))) {

                tieBreakDone_5SetUS = false;

            } else if (getNum5SetUS("set4a").equals("6") & getNum5SetUS("set4b").equals("7")

                    & (!"0".equals(getNum5SetUS("ktbfps")) | !"0".equals(getNum5SetUS("ktbsps")))) {

                tieBreakDone_5SetUS = false;

            } else if (getNum5SetUS("set5a").equals("7") & getNum5SetUS("set5b").equals("6") & getNum5SetUS("ktbfps").equals("0") & getNum5SetUS("ktbsps").equals("0")) {

                SET_5_US_MATCH_DONE = true;
                SET_5_US_5_SET_tb_DONE = true;
                tb_5SetUS = false;
                tieBreakDone_5SetUS = true;

                Toast.makeText(this, pl1_1_5setUS + " " + pl1_2_5setUS + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                setWinnerSet_5US(playerFirst5SetUS, pointFPFiveSet5SetUS, "firstPlayer");
                setEnab();

            } else if (getNum5SetUS("set5a").equals("6") & getNum5SetUS("set5b").equals("7") & getNum5SetUS("ktbfps").equals("0") & getNum5SetUS("ktbsps").equals("0")) {

                SET_5_US_MATCH_DONE = true;
                SET_5_US_5_SET_tb_DONE = true;
                tb_5SetUS = false;
                tieBreakDone_5SetUS = true;

                Toast.makeText(this, pl2_1_5setUS + " " + pl2_2_5setUS + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                setWinnerSet_5US(playerSecond5SetUS, pointSPFiveSet5SetUS, "secondPlayer");
                setEnab();

            } else {
                checkSetNumber(Integer.valueOf(getNum5SetUS("set5a")), Integer.valueOf(getNum5SetUS("set5b")));
            }
        }

        if (undoCounter5SetUS == 0) {
            noEnabled(redo_5SetUS, getDrawable(R.drawable.ic_redo_black_24dp_0_3));
        }

        goalsCounter5SetUS++;
        if (goalsCounter5SetUS > 0) {
            beEnabled(undo_5SetUS, getDrawable(R.drawable.ic_undo_black_24dp));
        }
    }

    public void updateColumnNumberDown5SetUS() {

        SQLiteDatabase db = mDbHelper5SetUS.getWritableDatabase();
        String insertQuery = " UPDATE " + Contract.ContractNames.TABLE_NAME + " SET " + Contract.ContractNames.COLUMN_NUMBER
                + " = ((SELECT " + Contract.ContractNames.COLUMN_NUMBER + " FROM " + Contract.ContractNames.TABLE_NAME +
                " WHERE " + Contract.ContractNames._ID + " = " + getLastIdfromUndoTable1Set() + ") - 1 ) WHERE " + Contract.ContractNames._ID + " = " + getLastIdfromUndoTable1Set();
        db.execSQL(insertQuery);
    }

    public int getLastIdfromUndoTable1Set() {
        SQLiteDatabase db = mDbHelper5SetUS.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT " + Contract.UndoNumbers.COLUMN_NUM_ID + " FROM "
                + Contract.UndoNumbers.TABLE_NAME_1 + " WHERE " + Contract.UndoNumbers.COLUMN_NUM_NUM +
                " = (SELECT MAX( " + Contract.UndoNumbers.COLUMN_NUM_NUM + " ) FROM " + Contract.UndoNumbers.TABLE_NAME_1 + " ) - " + (undoCounter5SetUS - 1), new String[]{});
        cursor.moveToFirst();
        lastIdfromUT5SetUS = (int) cursor.getLong(0);
        cursor.close();
        return lastIdfromUT5SetUS;
    }

    public void updateColumnNumberUp5SetUS() {
        SQLiteDatabase db = mDbHelper5SetUS.getWritableDatabase();
        String insertQuery = " UPDATE " + Contract.ContractNames.TABLE_NAME + " SET " + Contract.ContractNames.COLUMN_NUMBER
                + " = ((SELECT " + Contract.ContractNames.COLUMN_NUMBER + " FROM " + Contract.ContractNames.TABLE_NAME +
                " WHERE " + Contract.ContractNames._ID + " = " + getLastIdfromUndoTable1Set() + ") + 1 ) WHERE " + Contract.ContractNames._ID + " = " + getLastIdfromUndoTable1Set();
        db.execSQL(insertQuery);
    }

    public void deleteRow5SetUS() {
        beEnabled(undo_5SetUS, getDrawable(R.drawable.ic_undo_black_24dp));
        SQLiteDatabase db = mDbHelper5SetUS.getReadableDatabase();
        String insertQuery = " DELETE FROM " + Contract.UndoNumbers.TABLE_NAME_1 + " WHERE " + Contract.UndoNumbers.COLUMN_NUM_NUM +
                " IN ( SELECT " + Contract.UndoNumbers.COLUMN_NUM_NUM + " FROM " + Contract.UndoNumbers.TABLE_NAME_1 +
                " ORDER BY " + Contract.UndoNumbers.COLUMN_NUM_NUM + " DESC LIMIT " + undoCounter5SetUS + ")";
        db.execSQL(insertQuery);
        undoCounter5SetUS = 0;
    }

    public void noSaveResultGoToMM() {
        mDbHelper5SetUS.clearTable(Contract.UndoNumbers.TABLE_NAME_1);
        mDbHelper5SetUS.clearTable(Contract.ContractNames.TABLE_NAME);
        finish();
        Intent intent1 = new Intent(this, AA_MainActivity.class);
        startActivity(intent1);
    }

    public void goToHelp() {
        Intent helpIntent = new Intent(this, DataBase.class);
        startActivity(helpIntent);
    }

    public void saveResultGoToMM() {
        saveResult5SetUS();
        mDbHelper5SetUS.clearTable(Contract.UndoNumbers.TABLE_NAME_1);
        mDbHelper5SetUS.clearTable(Contract.ContractNames.TABLE_NAME);
        finish();
        Intent intent1 = new Intent(this, AA_MainActivity.class);
        startActivity(intent1);
    }

    public void saveResult5SetUS() {

        if (getIntent().getExtras().getBoolean("isNew")) {

            mDbHelper5SetUS.putName13("fullName", "matchMode", "courtSurface", pl1_1_5setUS, pl1_2_5setUS, pl2_1_5setUS, pl2_2_5setUS, Set5USStatusMatch,
                    mDbHelper5SetUS.getStringFromNumDataFromUndoTable("ktbfps", undoCounter5SetUS), mDbHelper5SetUS.getStringFromNumDataFromUndoTable("ktbsps", undoCounter5SetUS),
                    mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set1a", undoCounter5SetUS), A_pl_5SetUS_tb1, mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set1b", undoCounter5SetUS), B_pl_5SetUS_tb1,
                    mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set2a", undoCounter5SetUS), A_pl_5SetUS_tb2, mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set2b", undoCounter5SetUS), B_pl_5SetUS_tb2,
                    mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set3a", undoCounter5SetUS), A_pl_5SetUS_tb3, mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set3b", undoCounter5SetUS), B_pl_5SetUS_tb3,
                    mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set4a", undoCounter5SetUS), A_pl_5SetUS_tb4, mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set4b", undoCounter5SetUS), B_pl_5SetUS_tb4,
                    mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set5a", undoCounter5SetUS), A_pl_5SetUS_tb5, mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set5b", undoCounter5SetUS), B_pl_5SetUS_tb5,
                    7, 3, 6, 2, 5, 1, 4, 0, getDFB_5SetUS(tb1pl_5SetUS), getDFB_5SetUS(tb2pl_5SetUS), getDFB_5SetUS(tieBreakDone_5SetUS), getDFB_5SetUS(stopMatchIsEnabled5SetUS),
                    getDFB_5SetUS(tb_5SetUS), getDFB_5SetUS(SET_5_US_1_SET_a_DONE), getDFB_5SetUS(SET_5_US_1_SET_b_DONE), getDFB_5SetUS(SET_5_US_2_SET_a_DONE),
                    getDFB_5SetUS(SET_5_US_2_SET_b_DONE), getDFB_5SetUS(SET_5_US_3_SET_a_DONE), getDFB_5SetUS(SET_5_US_3_SET_b_DONE), getDFB_5SetUS(SET_5_US_4_SET_a_DONE),
                    getDFB_5SetUS(SET_5_US_4_SET_b_DONE), getDFB_5SetUS(SET_5_US_1_Set), getDFB_5SetUS(SET_5_US_2_Set), getDFB_5SetUS(SET_5_US_3_Set), getDFB_5SetUS(SET_5_US_4_Set),
                    getDFB_5SetUS(SET_5_US_5_Set), getDFB_5SetUS(SET_5_US_1_SET_tb_DONE), getDFB_5SetUS(SET_5_US_2_SET_tb_DONE), getDFB_5SetUS(SET_5_US_3_SET_tb_DONE),
                    getDFB_5SetUS(SET_5_US_4_SET_tb_DONE), getDFB_5SetUS(SET_5_US_5_SET_tb_DONE), getDFB_5SetUS(SET_5_US_MATCH_DONE));
        } else {
            mDbHelper5SetUS.putName122("fullName", "matchMode", "courtSurface", pl1_1_5setUS, pl1_2_5setUS, pl2_1_5setUS, pl2_2_5setUS, Set5USStatusMatch,
                    mDbHelper5SetUS.getStringFromNumDataFromUndoTable("ktbfps", undoCounter5SetUS), mDbHelper5SetUS.getStringFromNumDataFromUndoTable("ktbsps", undoCounter5SetUS),
                    mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set1a", undoCounter5SetUS), A_pl_5SetUS_tb1, mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set1b", undoCounter5SetUS), B_pl_5SetUS_tb1,
                    mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set2a", undoCounter5SetUS), A_pl_5SetUS_tb2, mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set2b", undoCounter5SetUS), B_pl_5SetUS_tb2,
                    mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set3a", undoCounter5SetUS), A_pl_5SetUS_tb3, mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set3b", undoCounter5SetUS), B_pl_5SetUS_tb3,
                    mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set4a", undoCounter5SetUS), A_pl_5SetUS_tb4, mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set4b", undoCounter5SetUS), B_pl_5SetUS_tb4,
                    mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set5a", undoCounter5SetUS), A_pl_5SetUS_tb5, mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set5b", undoCounter5SetUS), B_pl_5SetUS_tb5,
                    7, 3, 6, 2, 5, 1, 4, 0, getDFB_5SetUS(tb1pl_5SetUS), getDFB_5SetUS(tb2pl_5SetUS), getDFB_5SetUS(tieBreakDone_5SetUS), getDFB_5SetUS(stopMatchIsEnabled5SetUS),
                    getDFB_5SetUS(tb_5SetUS), getDFB_5SetUS(SET_5_US_1_SET_a_DONE), getDFB_5SetUS(SET_5_US_1_SET_b_DONE), getDFB_5SetUS(SET_5_US_2_SET_a_DONE),
                    getDFB_5SetUS(SET_5_US_2_SET_b_DONE), getDFB_5SetUS(SET_5_US_3_SET_a_DONE), getDFB_5SetUS(SET_5_US_3_SET_b_DONE), getDFB_5SetUS(SET_5_US_4_SET_a_DONE),
                    getDFB_5SetUS(SET_5_US_4_SET_b_DONE), getDFB_5SetUS(SET_5_US_1_Set), getDFB_5SetUS(SET_5_US_2_Set), getDFB_5SetUS(SET_5_US_3_Set), getDFB_5SetUS(SET_5_US_4_Set),
                    getDFB_5SetUS(SET_5_US_5_Set), getDFB_5SetUS(SET_5_US_1_SET_tb_DONE), getDFB_5SetUS(SET_5_US_2_SET_tb_DONE), getDFB_5SetUS(SET_5_US_3_SET_tb_DONE),
                    getDFB_5SetUS(SET_5_US_4_SET_tb_DONE), getDFB_5SetUS(SET_5_US_5_SET_tb_DONE), getDFB_5SetUS(SET_5_US_MATCH_DONE));

        }

    }

    public void saveAndGo5SetUS(View view) {


            saveResultGoToMM();
        }


    public void saveResultFromStat(String st) {

        Set5USStatusMatch = st;
        if (getIntent().getExtras().getBoolean("isNew")) {

            mDbHelper5SetUS.putName13("fullName", "matchMode", "courtSurface", pl1_1_5setUS, pl1_2_5setUS, pl2_1_5setUS, pl2_2_5setUS, Set5USStatusMatch,
                    mDbHelper5SetUS.getStringFromNumDataFromUndoTable("ktbfps", undoCounter5SetUS), mDbHelper5SetUS.getStringFromNumDataFromUndoTable("ktbsps", undoCounter5SetUS),
                    mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set1a", undoCounter5SetUS), A_pl_5SetUS_tb1, mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set1b", undoCounter5SetUS), B_pl_5SetUS_tb1,
                    mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set2a", undoCounter5SetUS), A_pl_5SetUS_tb2, mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set2b", undoCounter5SetUS), B_pl_5SetUS_tb2,
                    mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set3a", undoCounter5SetUS), A_pl_5SetUS_tb3, mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set3b", undoCounter5SetUS), B_pl_5SetUS_tb3,
                    mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set4a", undoCounter5SetUS), A_pl_5SetUS_tb4, mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set4b", undoCounter5SetUS), B_pl_5SetUS_tb4,
                    mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set5a", undoCounter5SetUS), A_pl_5SetUS_tb5, mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set5b", undoCounter5SetUS), B_pl_5SetUS_tb5,
                    7, 3, 6, 2, 5, 1, 4, 0, getDFB_5SetUS(tb1pl_5SetUS), getDFB_5SetUS(tb2pl_5SetUS), getDFB_5SetUS(tieBreakDone_5SetUS), getDFB_5SetUS(stopMatchIsEnabled5SetUS),
                    getDFB_5SetUS(tb_5SetUS), getDFB_5SetUS(SET_5_US_1_SET_a_DONE), getDFB_5SetUS(SET_5_US_1_SET_b_DONE), getDFB_5SetUS(SET_5_US_2_SET_a_DONE),
                    getDFB_5SetUS(SET_5_US_2_SET_b_DONE), getDFB_5SetUS(SET_5_US_3_SET_a_DONE), getDFB_5SetUS(SET_5_US_3_SET_b_DONE), getDFB_5SetUS(SET_5_US_4_SET_a_DONE),
                    getDFB_5SetUS(SET_5_US_4_SET_b_DONE), getDFB_5SetUS(SET_5_US_1_Set), getDFB_5SetUS(SET_5_US_2_Set), getDFB_5SetUS(SET_5_US_3_Set), getDFB_5SetUS(SET_5_US_4_Set),
                    getDFB_5SetUS(SET_5_US_5_Set), getDFB_5SetUS(SET_5_US_1_SET_tb_DONE), getDFB_5SetUS(SET_5_US_2_SET_tb_DONE), getDFB_5SetUS(SET_5_US_3_SET_tb_DONE),
                    getDFB_5SetUS(SET_5_US_4_SET_tb_DONE), getDFB_5SetUS(SET_5_US_5_SET_tb_DONE), getDFB_5SetUS(SET_5_US_MATCH_DONE));
        } else {
            mDbHelper5SetUS.putName122("fullName", "matchMode", "courtSurface", pl1_1_5setUS, pl1_2_5setUS, pl2_1_5setUS, pl2_2_5setUS, Set5USStatusMatch,
                    mDbHelper5SetUS.getStringFromNumDataFromUndoTable("ktbfps", undoCounter5SetUS), mDbHelper5SetUS.getStringFromNumDataFromUndoTable("ktbsps", undoCounter5SetUS),
                    mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set1a", undoCounter5SetUS), A_pl_5SetUS_tb1, mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set1b", undoCounter5SetUS), B_pl_5SetUS_tb1,
                    mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set2a", undoCounter5SetUS), A_pl_5SetUS_tb2, mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set2b", undoCounter5SetUS), B_pl_5SetUS_tb2,
                    mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set3a", undoCounter5SetUS), A_pl_5SetUS_tb3, mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set3b", undoCounter5SetUS), B_pl_5SetUS_tb3,
                    mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set4a", undoCounter5SetUS), A_pl_5SetUS_tb4, mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set4b", undoCounter5SetUS), B_pl_5SetUS_tb4,
                    mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set5a", undoCounter5SetUS), A_pl_5SetUS_tb5, mDbHelper5SetUS.getStringFromNumDataFromUndoTable("set5b", undoCounter5SetUS), B_pl_5SetUS_tb5,
                    7, 3, 6, 2, 5, 1, 4, 0, getDFB_5SetUS(tb1pl_5SetUS), getDFB_5SetUS(tb2pl_5SetUS), getDFB_5SetUS(tieBreakDone_5SetUS), getDFB_5SetUS(stopMatchIsEnabled5SetUS),
                    getDFB_5SetUS(tb_5SetUS), getDFB_5SetUS(SET_5_US_1_SET_a_DONE), getDFB_5SetUS(SET_5_US_1_SET_b_DONE), getDFB_5SetUS(SET_5_US_2_SET_a_DONE),
                    getDFB_5SetUS(SET_5_US_2_SET_b_DONE), getDFB_5SetUS(SET_5_US_3_SET_a_DONE), getDFB_5SetUS(SET_5_US_3_SET_b_DONE), getDFB_5SetUS(SET_5_US_4_SET_a_DONE),
                    getDFB_5SetUS(SET_5_US_4_SET_b_DONE), getDFB_5SetUS(SET_5_US_1_Set), getDFB_5SetUS(SET_5_US_2_Set), getDFB_5SetUS(SET_5_US_3_Set), getDFB_5SetUS(SET_5_US_4_Set),
                    getDFB_5SetUS(SET_5_US_5_Set), getDFB_5SetUS(SET_5_US_1_SET_tb_DONE), getDFB_5SetUS(SET_5_US_2_SET_tb_DONE), getDFB_5SetUS(SET_5_US_3_SET_tb_DONE),
                    getDFB_5SetUS(SET_5_US_4_SET_tb_DONE), getDFB_5SetUS(SET_5_US_5_SET_tb_DONE), getDFB_5SetUS(SET_5_US_MATCH_DONE));

        }

        mDbHelper5SetUS.clearTable(Contract.UndoNumbers.TABLE_NAME_1);
        mDbHelper5SetUS.clearTable(Contract.ContractNames.TABLE_NAME);
        finish();
        Intent intent1 = new Intent(this, AA_MainActivity.class);
        startActivity(intent1);
    }

    private void setWinnerSet_5US(TextView tv1, TextView tv2, String str) {
        tv1.setBackgroundResource(R.drawable.frame_winner);
        tv2.setBackgroundResource(R.drawable.frame_winner);
        beEnabled(saveButton5SetUS, getDrawable(R.drawable.ic_save_black_24dp));
        stopMatchIsEnabled5SetUS = false;
        Set5USStatusMatch = str;
    }

    public int getDFB_5SetUS(Boolean b) {
        int u = 0;
        if (b) {
            u = 1;
        }
        return u;
    }
    @Override
    public void onDestroy() {
        mDbHelper5SetUS.clearTable(Contract.UndoNumbers.TABLE_NAME_1);
        mDbHelper5SetUS.clearTable(Contract.ContractNames.TABLE_NAME);
        super.onDestroy();
    }
}
