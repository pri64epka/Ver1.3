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

public class AA_Set3Activity extends AppCompatActivity {

    TextView playerFirst3Set, playerSecond3Set, pointFP3Set, pointSP3Set, pointFPFirstSet3Set, pointSPFirstSet3Set,
            pointFPSecondSet3Set, pointSPSecondSet3Set, pointFPThirdSet3Set, pointSPThirdSet3Set;
    private int i3s = 0;
    private int k3s = 0;
    private int fpls3 = 0;
    private int spls3 = 0;
    String sstr = "";
    String sstr1s = "";
    String adv_3Set = "Ad";
    int undoCounter3Set = 0;
    Spinner spinner1p3s, spinner2p3s;
    Button goalFP3Set, goalSP3Set;
    ImageButton undo_3Set, redo_3Set, saveButton3Set;
    String scoreP1_set3, scoreP2_set3, pl1_1_3set, pl1_2_3set, pl2_1_3set, pl2_2_3set, reasonsSelectedItem;
    int curNum3Set, n4, tbfp, tbsp, A_pl_3Set_tb1, B_pl_3Set_tb1, A_pl_3Set_tb2, B_pl_3Set_tb2, prevNum3Set, lastIdfromUT3Set;

    private TennisHelper mDbHelper3Set;

    private boolean SET_3_3_Set = false;//
    private boolean SET_3_2_Set = false;//
    private boolean SET_3_1_Set = true;//
    private boolean tieBreakDone_3Set = false;//
    private boolean tb1pl_3Set = false;//
    private boolean tb2pl_3Set = false;//
    private boolean tb_3Set = false;//
    private boolean SET_3_1_SET_a_DONE = false;//
    private boolean SET_3_1_SET_b_DONE = false;//
    private boolean SET_3_2_SET_a_DONE = false;//
    private boolean SET_3_2_SET_b_DONE = false;//
    private boolean SET_3_1_SET_tb_DONE = false;//
    private boolean SET_3_2_SET_tb_DONE = false;//
    private boolean SET_3_MATCH_DONE = false;//
    private boolean stopMatchIsEnabled3Set = true;//

    private String Set3StatusMatch = "Paused";

    int goalsCounter3Set;

    Context context;
    AlertDialog.Builder ad3Set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aa_activity_set3);

        goalsCounter3Set = 0;

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.table_score));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        context = AA_Set3Activity.this;
        mDbHelper3Set = new TennisHelper(this);

        TextView tableScore3Set = (TextView) findViewById(R.id.addToTableScoreTV3Set);
        tableScore3Set.setText(R.string.three_sets);


        playerFirst3Set = (TextView) findViewById(R.id.playerFirst3SetTV);
        playerSecond3Set = (TextView) findViewById(R.id.playerSecond3SetTV);

        TextView nameFirstPlayer = (TextView) findViewById(R.id.nameFirstPlayerTV);
        TextView nameSecondPlayer = (TextView) findViewById(R.id.nameSecondPlayerTV);

        pointFP3Set = (TextView) findViewById(R.id.pointFP3SetTV);
        pointSP3Set = (TextView) findViewById(R.id.pointSP3SetTV);

        pointFPFirstSet3Set = (TextView) findViewById(R.id.pointFPFirstSet3SetTV);
        pointSPFirstSet3Set = (TextView) findViewById(R.id.pointSPFirstSet3SetTV);

        pointFPSecondSet3Set = (TextView) findViewById(R.id.pointFPSecondSet3SetTV);
        pointSPSecondSet3Set = (TextView) findViewById(R.id.pointSPSecondSet3SetTV);

        pointFPThirdSet3Set = (TextView) findViewById(R.id.pointFPThirdSet3SetTV);
        pointSPThirdSet3Set = (TextView) findViewById(R.id.pointSPThirdSet3SetTV);

        if (getIntent().getExtras().getBoolean("isNew")) {

            pl1_1_3set = getIntent().getExtras().getString("user");
            pl1_2_3set = getIntent().getExtras().getString("userSur");
            pl2_1_3set = getIntent().getExtras().getString("der");
            pl2_2_3set = getIntent().getExtras().getString("derSur");

            pointFP3Set.setText(String.valueOf(tp[i3s]));
            pointSP3Set.setText(String.valueOf(tp[k3s]));

            pointFPFirstSet3Set.setText(String.valueOf(fpls3));
            pointSPFirstSet3Set.setText(String.valueOf(spls3));

            pointFPSecondSet3Set.setText(String.valueOf("0"));
            pointSPSecondSet3Set.setText(String.valueOf("0"));

            pointFPThirdSet3Set.setText(String.valueOf("0"));
            pointSPThirdSet3Set.setText(String.valueOf("0"));

        } else {

            pl1_1_3set = mDbHelper3Set.getNameById("name_A_1", selId);
            pl1_2_3set = mDbHelper3Set.getNameById("name_A_2", selId);
            pl2_1_3set = mDbHelper3Set.getNameById("name_B_1", selId);
            pl2_2_3set = mDbHelper3Set.getNameById("name_B_2", selId);

            if (mDbHelper3Set.getNumById("ktbfpsST", selId) == -2) {
                pointFP3Set.setText(adv_3Set);
            } else {
                pointFP3Set.setText(String.valueOf(mDbHelper3Set.getNumById("ktbfpsST", selId)));
            }

            if (mDbHelper3Set.getNumById("ktbspsST", selId) == -2) {
                pointSP3Set.setText(adv_3Set);
            } else {
                pointSP3Set.setText(String.valueOf(mDbHelper3Set.getNumById("ktbspsST", selId)));
            }

            if (mDbHelper3Set.getNumById("set1aST_tb", selId) > 0 || mDbHelper3Set.getNumById("set1bST_tb", selId) > 0) {

                pointFPFirstSet3Set.setText(String.valueOf(mDbHelper3Set.getNumById("set1aST", selId)));
                pointSPFirstSet3Set.setText(String.valueOf(mDbHelper3Set.getNumById("set1bST", selId)));

                convertUndo(mDbHelper3Set.getNumById("set1aST_tb", selId), mDbHelper3Set.getNumById("set1bST_tb", selId), pointFPFirstSet3Set, pointSPFirstSet3Set);
            } else {

                pointFPFirstSet3Set.setText(String.valueOf(mDbHelper3Set.getNumById("set1aST", selId)));
                pointSPFirstSet3Set.setText(String.valueOf(mDbHelper3Set.getNumById("set1bST", selId)));
            }

            if (mDbHelper3Set.getNumById("set2aST_tb", selId) > 0 || mDbHelper3Set.getNumById("set2bST_tb", selId) > 0) {
                pointFPSecondSet3Set.setText(String.valueOf(mDbHelper3Set.getNumById("set2aST", selId)));
                pointSPSecondSet3Set.setText(String.valueOf(mDbHelper3Set.getNumById("set2bST", selId)));
                convertUndo(mDbHelper3Set.getNumById("set2aST_tb", selId), mDbHelper3Set.getNumById("set2bST_tb", selId), pointFPSecondSet3Set, pointSPSecondSet3Set);

            } else {
                pointFPSecondSet3Set.setText(String.valueOf(mDbHelper3Set.getNumById("set2aST", selId)));
                pointSPSecondSet3Set.setText(String.valueOf(mDbHelper3Set.getNumById("set2bST", selId)));
            }
            pointFPThirdSet3Set.setText(String.valueOf(mDbHelper3Set.getNumById("set3aST", selId)));
            pointSPThirdSet3Set.setText(String.valueOf(mDbHelper3Set.getNumById("set3bST", selId)));

            tb1pl_3Set = mDbHelper3Set.getNumById("tb1pl", selId) > 0;//

            tb2pl_3Set = mDbHelper3Set.getNumById("tb2pl", selId) > 0;//

            tb_3Set = mDbHelper3Set.getNumById("tb", selId) > 0;//

            SET_3_1_SET_a_DONE = mDbHelper3Set.getNumById("_1_set_a_done", selId) > 0;//
            setWinnerFrame(SET_3_1_SET_a_DONE,pointFPFirstSet3Set);

            SET_3_1_SET_b_DONE = mDbHelper3Set.getNumById("_1_set_b_done", selId) > 0;//
            setWinnerFrame(SET_3_1_SET_b_DONE,pointSPFirstSet3Set);

            SET_3_2_SET_a_DONE = mDbHelper3Set.getNumById("_2_set_a_done", selId) > 0;//
            setWinnerFrame(SET_3_2_SET_a_DONE,pointFPSecondSet3Set);

            SET_3_2_SET_b_DONE = mDbHelper3Set.getNumById("_2_set_b_done", selId) > 0;//
            setWinnerFrame(SET_3_2_SET_b_DONE,pointSPSecondSet3Set);

            SET_3_3_Set = mDbHelper3Set.getNumById("_3_set", selId) > 0;//

            SET_3_2_Set = mDbHelper3Set.getNumById("_2_set", selId) > 0;//

            SET_3_1_Set = mDbHelper3Set.getNumById("_1_set", selId) > 0;//

            SET_3_MATCH_DONE = mDbHelper3Set.getNumById("match_done", selId) > 0;//

            tieBreakDone_3Set = mDbHelper3Set.getNumById("tieBreakDone", selId) > 0;//

            SET_3_1_SET_tb_DONE = mDbHelper3Set.getNumById("_1_set_tb_done", selId) > 0;//

            SET_3_2_SET_tb_DONE = mDbHelper3Set.getNumById("_2_set_tb_done", selId) > 0;//

            stopMatchIsEnabled3Set = mDbHelper3Set.getNumById("stopMatchIsEnabled", selId) > 0;//

            A_pl_3Set_tb1 = mDbHelper3Set.getNumById("set1aST_tb", selId);
            B_pl_3Set_tb1 = mDbHelper3Set.getNumById("set1bST_tb", selId);
            A_pl_3Set_tb2 = mDbHelper3Set.getNumById("set2aST_tb", selId);
            B_pl_3Set_tb2 = mDbHelper3Set.getNumById("set2bST_tb", selId);

            mDbHelper3Set.insert3SetKTBScore(mDbHelper3Set.getNumById("ktbfpsST", selId), mDbHelper3Set.getNumById("ktbspsST", selId),
                    mDbHelper3Set.getNumById("set1aST", selId), mDbHelper3Set.getNumById("set1bST", selId), mDbHelper3Set.getNumById("set2aST", selId),
                    mDbHelper3Set.getNumById("set2bST", selId), mDbHelper3Set.getNumById("set3aST", selId), mDbHelper3Set.getNumById("set3bST", selId));

            mDbHelper3Set.insertPlayers(pl1_1_3set, pl1_2_3set, pl2_1_3set, pl2_2_3set);


        }


        typeGoals[0] = getResources().getString(R.string.select_gt_for_spinner);
        typeGoals[1] = getResources().getString(R.string.Winner);
        typeGoals[2] = getResources().getString(R.string.Ace);
        typeGoals[3] = getResources().getString(R.string.Unenforced_error_for_spinner);
        typeGoals[4] = getResources().getString(R.string.Double_error_for_spinner);

        setNames(playerFirst3Set, pl1_1_3set, pl1_2_3set);
        setNames(playerSecond3Set, pl2_1_3set, pl2_2_3set);

        AA_KingTieBreakActivity.setAddNames(nameFirstPlayer, pl1_1_3set, pl1_2_3set);
        AA_KingTieBreakActivity.setAddNames(nameSecondPlayer, pl2_1_3set, pl2_2_3set);

        spinner1p3s = (Spinner) findViewById(R.id.spinner1p3sTV);
        spinner2p3s = (Spinner) findViewById(R.id.spinner2p3sTV);
        AA_KingTieBreakActivity.setadapter(typeGoals, spinner1p3s, this);
        AA_KingTieBreakActivity.setadapter(typeGoals, spinner2p3s, this);

        goalFP3Set = (Button) findViewById(R.id.goalFP3SetButton);
        goalSP3Set = (Button) findViewById(R.id.goalSP3SetButton);

        undo_3Set = (ImageButton) findViewById(R.id.undo3SetButton);
        redo_3Set = (ImageButton) findViewById(R.id.redo3SetButton);
        saveButton3Set = (ImageButton) findViewById(R.id._saveButton3Set);

        noEnabled(undo_3Set, getDrawable(R.drawable.ic_undo_black_24dp_0_3));
        noEnabled(redo_3Set, getDrawable(R.drawable.ic_redo_black_24dp_0_3));
        noEnabled(saveButton3Set, getDrawable(R.drawable.ic_save_black_24dp_0_3));
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if (!stopMatchIsEnabled3Set) {
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

                final String na_1 = getString(R.string.Retired) + " " + playerFirst3Set.getText().toString() + getString(R.string.loss);
                final String na_2 = getString(R.string.Retired) + " " + playerSecond3Set.getText().toString() + getString(R.string.loss);
                final String na_3 = getString(R.string.Another_Match_is_paused);
                final String na_4 = getString(R.string.Another_Match_is_finished);
                final String[] reasons = {na_1, na_2, na_3, na_4};
                ad3Set = new AlertDialog.Builder(this);
                ad3Set.setTitle(getString(R.string.Choose_the_reason))
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


                                        ad3Set = new AlertDialog.Builder(context);

                                        ad3Set.setMessage(getString(R.string.Do_you_want));

                                        ad3Set.setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener() {
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
                                        ad3Set.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int arg1) {
                                                noSaveResultGoToMM();
                                                if (!getIntent().getExtras().getBoolean("isNew")) {
                                                    mDbHelper3Set.putFreeRowInStatisticSaveTable(selId);
                                                }
                                            }
                                        });
                                        ad3Set.setCancelable(true);
                                        ad3Set.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                            public void onCancel(DialogInterface dialog) {
                                            }
                                        });
                                        ad3Set.show();
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
                ad3Set.show();
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



            ad3Set = new AlertDialog.Builder(context);
            ad3Set.setMessage(getString(R.string.ad_alert_1));
            ad3Set.setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int arg1) {

                    saveResultGoToMM();
                }
            });
            ad3Set.setNeutralButton((getString(R.string.Continue_match)),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int id) {
                            dialog.cancel();
                        }
                    });

            ad3Set.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int arg1) {
                    noSaveResultGoToMM();
                    if (!getIntent().getExtras().getBoolean("isNew")) {
                        mDbHelper3Set.putFreeRowInStatisticSaveTable(selId);
                    }
                }
            });
            ad3Set.setCancelable(true);
            ad3Set.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialog) {
                }
            });
            ad3Set.show();
    }

    public static void setWinnerFrame(Boolean b,TextView tv){

        if(b){
            tv.setBackgroundResource(R.drawable.frame_winner);
        }
    }


    public void firstPlayerGoal3Set(View v) {

        switch (AA_KingTieBreakActivity.getSpinnerItem(spinner1p3s)) {
            case 0: {
                Toast.makeText(getBaseContext(), getString(R.string.select_gt), Toast.LENGTH_SHORT).show();
                break;
            }
            case 1: {
                deleteRow3Set();
                noEnabled(redo_3Set, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper3Set.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(7) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper3Set.getLastId() - 7)});

                setFirstPlScore3Set();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper3Set.getLastId() - 7);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper3Set.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet3AllplScore(pointFP3Set, pointSP3Set, pointFPFirstSet3Set, pointSPFirstSet3Set, pointFPSecondSet3Set, pointSPSecondSet3Set, pointFPThirdSet3Set, pointSPThirdSet3Set);
                goalsCounter3Set++;
                break;
            }

            case 2: {
                deleteRow3Set();
                noEnabled(redo_3Set, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper3Set.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(6) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper3Set.getLastId() - 6)});

                setFirstPlScore3Set();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper3Set.getLastId() - 6);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper3Set.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet3AllplScore(pointFP3Set, pointSP3Set, pointFPFirstSet3Set, pointSPFirstSet3Set, pointFPSecondSet3Set, pointSPSecondSet3Set, pointFPThirdSet3Set, pointSPThirdSet3Set);
                goalsCounter3Set++;
                break;
            }

            case 3: {
                deleteRow3Set();
                noEnabled(redo_3Set, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper3Set.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(1) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper3Set.getLastId() - 1)});

                setFirstPlScore3Set();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper3Set.getLastId() - 1);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper3Set.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet3AllplScore(pointFP3Set, pointSP3Set, pointFPFirstSet3Set, pointSPFirstSet3Set, pointFPSecondSet3Set, pointSPSecondSet3Set, pointFPThirdSet3Set, pointSPThirdSet3Set);
                goalsCounter3Set++;
                break;
            }

            case 4: {
                deleteRow3Set();
                noEnabled(redo_3Set, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper3Set.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(0) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper3Set.getLastId())});

                setFirstPlScore3Set();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper3Set.getLastId());
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper3Set.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet3AllplScore(pointFP3Set, pointSP3Set, pointFPFirstSet3Set, pointSPFirstSet3Set, pointFPSecondSet3Set, pointSPSecondSet3Set, pointFPThirdSet3Set, pointSPThirdSet3Set);
                goalsCounter3Set++;
                break;
            }
        }

        spinner1p3s.setSelection(0);
        spinner2p3s.setSelection(0);
    }

    public void secondPlayerGoal3Set(View v) {


        switch (AA_KingTieBreakActivity.getSpinnerItem(spinner2p3s)) {
            case 0: {
                Toast.makeText(getBaseContext(), getString(R.string.select_gt), Toast.LENGTH_SHORT).show();
                break;
            }
            case 1: {
                deleteRow3Set();
                noEnabled(redo_3Set, getDrawable(R.drawable.ic_redo_black_24dp_0_3));
                SQLiteDatabase db = mDbHelper3Set.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(3) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper3Set.getLastId() - 3)});

                setSecondPlScore3Set();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper3Set.getLastId() - 3);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper3Set.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet3AllplScore(pointFP3Set, pointSP3Set, pointFPFirstSet3Set, pointSPFirstSet3Set, pointFPSecondSet3Set, pointSPSecondSet3Set, pointFPThirdSet3Set, pointSPThirdSet3Set);
                goalsCounter3Set++;
                break;
            }

            case 2: {
                deleteRow3Set();
                noEnabled(redo_3Set, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper3Set.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(2) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper3Set.getLastId() - 2)});

                setSecondPlScore3Set();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper3Set.getLastId() - 2);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper3Set.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet3AllplScore(pointFP3Set, pointSP3Set, pointFPFirstSet3Set, pointSPFirstSet3Set, pointFPSecondSet3Set, pointSPSecondSet3Set, pointFPThirdSet3Set, pointSPThirdSet3Set);
                goalsCounter3Set++;
                break;
            }

            case 3: {
                deleteRow3Set();
                noEnabled(redo_3Set, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper3Set.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(5) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper3Set.getLastId() - 5)});

                setSecondPlScore3Set();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper3Set.getLastId() - 5);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper3Set.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet3AllplScore(pointFP3Set, pointSP3Set, pointFPFirstSet3Set, pointSPFirstSet3Set, pointFPSecondSet3Set, pointSPSecondSet3Set, pointFPThirdSet3Set, pointSPThirdSet3Set);
                goalsCounter3Set++;
                break;
            }

            case 4: {
                deleteRow3Set();
                noEnabled(redo_3Set, getDrawable(R.drawable.ic_redo_black_24dp_0_3));

                SQLiteDatabase db = mDbHelper3Set.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Contract.ContractNames.COLUMN_NUMBER, curNumberOfPoints(4) + 1);
                db.update(Contract.ContractNames.TABLE_NAME, values, Contract.ContractNames._ID + " = ?", new String[]{Integer.toString(mDbHelper3Set.getLastId() - 4)});

                setSecondPlScore3Set();

                ContentValues values1 = new ContentValues();
                values1.put(Contract.UndoNumbers.COLUMN_NUM_ID, mDbHelper3Set.getLastId() - 4);
                values1.put(Contract.UndoNumbers.COLUMN_NUM_NUM, mDbHelper3Set.getMaxNumNum() + 1);
                db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values1);

                intCurSet3AllplScore(pointFP3Set, pointSP3Set, pointFPFirstSet3Set, pointSPFirstSet3Set, pointFPSecondSet3Set, pointSPSecondSet3Set, pointFPThirdSet3Set, pointSPThirdSet3Set);
                goalsCounter3Set++;
                break;
            }
        }

        spinner1p3s.setSelection(0);
        spinner2p3s.setSelection(0);
    }


    public void setFirstPlScore3Set() {

        tieBreakDone_3Set = false;

        tb1pl_3Set = true;

        if (tb_3Set) {

            tieBreak(tb1pl_3Set, tb2pl_3Set);

        } else {

            i3s = getKorI(pointFP3Set);
            k3s = getKorI(pointSP3Set);

            if (SET_3_3_Set) {

                fpls3 = Integer.valueOf(pointFPThirdSet3Set.getText().toString());
                spls3 = Integer.valueOf(pointSPThirdSet3Set.getText().toString());

            } else if (SET_3_2_Set) {

                fpls3 = Integer.valueOf(pointFPSecondSet3Set.getText().toString());
                spls3 = Integer.valueOf(pointSPSecondSet3Set.getText().toString());

            } else {

                fpls3 = Integer.valueOf(pointFPFirstSet3Set.getText().toString());
                spls3 = Integer.valueOf(pointSPFirstSet3Set.getText().toString());
            }

            scoreP1_set3 = pointFP3Set.getText().toString();

            switch (scoreP1_set3) {
                case "0":
                    i3s = 1;
                    break;
                case "15":
                    i3s = 2;
                    break;
                case "30":
                    i3s = 3;
                    break;
                case "40":
                    if (i3s > k3s) {
                        i3s = k3s = 0;
                        fpls3++;
                    } else if (i3s == k3s) {
                        i3s = 4;
                        break;
                    } else {
                        i3s = k3s = 3;
                    }
                    break;
                case "Ad":
                    i3s = k3s = 0;
                    fpls3++;
                    break;
            }
            if (i3s == 4) {

                pointFP3Set.setText(adv_3Set);
                pointSP3Set.setText(String.valueOf(tp[k3s]));
                tb1pl_3Set = false;//???????

            } else {

                pointFP3Set.setText(String.valueOf(tp[i3s]));
                pointSP3Set.setText(String.valueOf(tp[k3s]));

                if (SET_3_3_Set) {

                    pointFPThirdSet3Set.setText(String.valueOf(fpls3));

                } else if (SET_3_2_Set) {

                    pointFPSecondSet3Set.setText(String.valueOf(fpls3));

                } else {

                    pointFPFirstSet3Set.setText(String.valueOf(fpls3));
                }
                tb1pl_3Set = false;
            }
            checkSetNumber(fpls3, spls3);
        }
    }

    public void setSecondPlScore3Set() {

        tieBreakDone_3Set = false;

        tb2pl_3Set = true;

        if (tb_3Set) {

            tieBreak(tb1pl_3Set, tb2pl_3Set);

        } else {

            i3s = getKorI(pointFP3Set);
            k3s = getKorI(pointSP3Set);

            if (SET_3_3_Set) {

                fpls3 = Integer.valueOf(pointFPThirdSet3Set.getText().toString());
                spls3 = Integer.valueOf(pointSPThirdSet3Set.getText().toString());

            } else if (SET_3_2_Set) {

                fpls3 = Integer.valueOf(pointFPSecondSet3Set.getText().toString());
                spls3 = Integer.valueOf(pointSPSecondSet3Set.getText().toString());

            } else {

                fpls3 = Integer.valueOf(pointFPFirstSet3Set.getText().toString());
                spls3 = Integer.valueOf(pointSPFirstSet3Set.getText().toString());
            }

            scoreP2_set3 = pointSP3Set.getText().toString();

            switch (scoreP2_set3) {
                case "0":
                    k3s = 1;
                    break;
                case "15":
                    k3s = 2;
                    break;
                case "30":
                    k3s = 3;
                    break;
                case "40":
                    if (k3s > i3s) {
                        k3s = i3s = 0;
                        spls3++;
                    } else if (k3s == i3s) {
                        k3s = 4;
                    } else {
                        i3s = k3s = 3;
                    }
                    break;
                case "Ad":
                    i3s = k3s = 0;
                    spls3++;
                    break;
            }
            if (k3s == 4) {

                pointSP3Set.setText(adv_3Set);
                pointFP3Set.setText(String.valueOf(tp[i3s]));
                tb2pl_3Set = false;

            } else {

                pointSP3Set.setText(String.valueOf(tp[k3s]));
                pointFP3Set.setText(String.valueOf(tp[i3s]));

                if (SET_3_3_Set) {

                    pointSPThirdSet3Set.setText(String.valueOf(spls3));

                } else if (SET_3_2_Set) {

                    pointSPSecondSet3Set.setText(String.valueOf(spls3));

                } else {

                    pointSPFirstSet3Set.setText(String.valueOf(spls3));
                }
                tb2pl_3Set = false;
            }

            checkSetNumber(fpls3, spls3);
        }
    }


    public void intCurSet3AllplScore(TextView p1s, TextView p2s, TextView p1s1s, TextView p2s1s, TextView p1s2s, TextView p2s2s, TextView p1s3s, TextView p2s3s) {

        SQLiteDatabase db = mDbHelper3Set.getWritableDatabase();
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


        if (SET_3_1_SET_tb_DONE) {

            if (A_pl_3Set_tb1 > B_pl_3Set_tb1) {

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

        if (SET_3_2_SET_tb_DONE) {

            if (A_pl_3Set_tb2 > B_pl_3Set_tb2) {
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

        String j7 = p1s3s.getText().toString();
        String j8 = p2s3s.getText().toString();
        values.put(Contract.UndoNumbers.COLUMN_SET3A, Integer.valueOf(j7));
        values.put(Contract.UndoNumbers.COLUMN_SET3B, Integer.valueOf(j8));

        db.update(Contract.UndoNumbers.TABLE_NAME_1, values, Contract.UndoNumbers.COLUMN_NUM_NUM + " = ?", new String[]{Integer.toString(mDbHelper3Set.getMaxNumNum())});
    }


    public void checkSetNumber(int y1, int y2) {

        if ((SET_3_1_SET_a_DONE & SET_3_2_SET_b_DONE) | (SET_3_1_SET_b_DONE & SET_3_2_SET_a_DONE)) {

            if ((y1 == 6 | y1 >= 7) & y2 <= y1 - 2) {

                SET_3_MATCH_DONE = true;

                Toast.makeText(this, pl1_1_3set + " " + pl1_2_3set + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                setWinnerSet_3(playerFirst3Set, pointFPThirdSet3Set, "firstPlayer");
                AA_KingTieBreakActivity.setButNotEnabled(goalFP3Set, goalSP3Set, spinner1p3s, spinner2p3s);

            } else if ((y2 == 6 | y2 >= 7) & y1 <= y2 - 2) {

                SET_3_MATCH_DONE = true;

                Toast.makeText(this, pl2_1_3set + " " + pl2_2_3set + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                setWinnerSet_3(playerSecond3Set, pointSPThirdSet3Set, "secondPlayer");
                AA_KingTieBreakActivity.setButNotEnabled(goalFP3Set, goalSP3Set, spinner1p3s, spinner2p3s);
            }
        } else if (SET_3_1_SET_b_DONE) {

            if ((y1 == 6 | y1 == 7) & y2 <= y1 - 2) {

                SET_3_2_SET_a_DONE = true;
                SET_3_2_Set = false;
                SET_3_3_Set = true;

                Toast.makeText(this, pl1_1_3set + " " + pl1_2_3set + " " + getString(R.string.wins_2_set), Toast.LENGTH_LONG).show();
                pointFPSecondSet3Set.setBackgroundResource(R.drawable.frame_winner);

            } else if ((y2 == 6 | y2 == 7) & y1 <= y2 - 2) {

                SET_3_MATCH_DONE = true;
                SET_3_2_SET_b_DONE = true;

                Toast.makeText(this, pl2_1_3set + " " + pl2_2_3set + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                setWinnerSet_3(playerSecond3Set, pointSPSecondSet3Set, "secondPlayer");
                AA_KingTieBreakActivity.setButNotEnabled(goalFP3Set, goalSP3Set, spinner1p3s, spinner2p3s);

            } else if (y2 == 6 & y1 == 6) {

                setTbmode_3();
            }
        } else if (SET_3_1_SET_a_DONE) {

            if ((y1 == 6 | y1 == 7) & y2 <= y1 - 2) {

                SET_3_MATCH_DONE = true;
                SET_3_2_SET_a_DONE = true;

                Toast.makeText(this, pl1_1_3set + " " + pl1_2_3set + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                setWinnerSet_3(playerFirst3Set, pointFPSecondSet3Set, "firstPlayer");
                AA_KingTieBreakActivity.setButNotEnabled(goalFP3Set, goalSP3Set, spinner1p3s, spinner2p3s);

            } else if ((y2 == 6 | y2 >= 7) & y1 <= y2 - 2) {

                SET_3_2_SET_b_DONE = true;
                SET_3_2_Set = false;
                SET_3_3_Set = true;

                Toast.makeText(this, pl2_1_3set + " " + pl2_2_3set + " " + getString(R.string.wins_2_set), Toast.LENGTH_LONG).show();
                pointSPSecondSet3Set.setBackgroundResource(R.drawable.frame_winner);

            } else if (y2 == 6 & y1 == 6) {

                setTbmode_3();
            }
        } else {

            if ((y1 == 6 | y1 == 7) & y2 <= y1 - 2) {

                SET_3_1_SET_a_DONE = true;
                SET_3_2_Set = true;
                SET_3_1_Set = false;

                Toast.makeText(this, pl1_1_3set + " " + pl1_2_3set + " " + getString(R.string.wins_1_set), Toast.LENGTH_LONG).show();
                pointFPFirstSet3Set.setBackgroundResource(R.drawable.frame_winner);

            } else if ((y2 == 6 | y2 >= 7) & y1 <= y2 - 2) {

                SET_3_1_SET_b_DONE = true;
                SET_3_2_Set = true;
                SET_3_1_Set = false;

                Toast.makeText(this, pl2_1_3set + " " + pl2_2_3set + " " + getString(R.string.wins_1_set), Toast.LENGTH_LONG).show();
                pointSPFirstSet3Set.setBackgroundResource(R.drawable.frame_winner);

            } else if (y2 == 6 & y1 == 6) {

                setTbmode_3();
            }
        }
    }

    private void setTbmode_3() {

        Toast.makeText(getBaseContext(), getString(R.string.Tie_break_starts), Toast.LENGTH_SHORT).show();

        tb1pl_3Set = false;
        tb2pl_3Set = false;

        tbfp = 0;
        tbsp = 0;

        pointFP3Set.setText(String.valueOf(tbfp));
        pointSP3Set.setText(String.valueOf(tbsp));

        tb_3Set = true;
    }

    public void tieBreak(boolean pla, boolean plb) {

        if (pla) {

            tbfp = Integer.valueOf(pointFP3Set.getText().toString());
            tbsp = Integer.valueOf(pointSP3Set.getText().toString());

            tbfp++;

            pointFP3Set.setText(String.valueOf(tbfp));
            pointSP3Set.setText(String.valueOf(tbsp));
            tb1pl_3Set = false;

        } else if (plb) {

            tbfp = Integer.valueOf(pointFP3Set.getText().toString());
            tbsp = Integer.valueOf(pointSP3Set.getText().toString());

            tbsp++;

            pointFP3Set.setText(String.valueOf(tbfp));
            pointSP3Set.setText(String.valueOf(tbsp));
            tb2pl_3Set = false;
        }
        checkTieBreakNumber(tbfp, tbsp);
    }

    public void checkTieBreakNumber(int x1, int x2) {

        if ((x1 == 7 & x2 <= x1 - 2) | (x1 > 7 & x2 <= x1 - 2)) {

            if (SET_3_1_SET_b_DONE) {

                pointFPSecondSet3Set.setText(String.valueOf(7));

                SET_3_2_SET_tb_DONE = true;
                SET_3_2_SET_a_DONE = true;
                SET_3_3_Set = true;
                SET_3_2_Set = false;
                A_pl_3Set_tb2 = x1;
                B_pl_3Set_tb2 = x2;

                convert(x1, x2, pointFPSecondSet3Set, pointSPSecondSet3Set);

                Toast.makeText(this, pl1_1_3set + " " + pl1_2_3set + " " + getString(R.string.wins_2_set), Toast.LENGTH_LONG).show();
                pointFPSecondSet3Set.setBackgroundResource(R.drawable.frame_winner);

            } else if (SET_3_1_SET_a_DONE) {

                pointFPSecondSet3Set.setText(String.valueOf(7));

                SET_3_2_SET_tb_DONE = true;
                SET_3_2_SET_a_DONE = true;
                SET_3_MATCH_DONE = true;
                A_pl_3Set_tb2 = x1;
                B_pl_3Set_tb2 = x2;

                convert(x1, x2, pointFPSecondSet3Set, pointSPSecondSet3Set);

                Toast.makeText(this, pl1_1_3set + " " + pl1_2_3set + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                setWinnerSet_3(playerFirst3Set, pointFPSecondSet3Set, "firstPlayer");
                AA_KingTieBreakActivity.setButNotEnabled(goalFP3Set, goalSP3Set, spinner1p3s, spinner2p3s);

            } else {

                pointFPFirstSet3Set.setText(String.valueOf(7));

                SET_3_1_SET_a_DONE = true;
                SET_3_1_SET_tb_DONE = true;
                SET_3_2_Set = true;
                SET_3_1_Set = false;
                A_pl_3Set_tb1 = x1;
                B_pl_3Set_tb1 = x2;

                convert(x1, x2, pointFPFirstSet3Set, pointSPFirstSet3Set);

                Toast.makeText(this, pl1_1_3set + " " + pl1_2_3set + " " + getString(R.string.wins_1_set), Toast.LENGTH_LONG).show();
                pointFPFirstSet3Set.setBackgroundResource(R.drawable.frame_winner);
            }

        } else if ((x2 == 7 & x1 <= x2 - 2) | (x2 > 7 & x1 <= x2 - 2))

        {

            if (SET_3_1_SET_a_DONE) {

                SET_3_2_SET_b_DONE = true;
                SET_3_2_SET_tb_DONE = true;

                SET_3_2_Set = false;
                SET_3_3_Set = true;


                A_pl_3Set_tb2 = x1;
                B_pl_3Set_tb2 = x2;

                pointSPSecondSet3Set.setText(String.valueOf(7));

                convert(x1, x2, pointFPSecondSet3Set, pointSPSecondSet3Set);

                Toast.makeText(this, pl2_1_3set + " " + pl2_2_3set + " " + getString(R.string.wins_2_set), Toast.LENGTH_LONG).show();
                pointSPSecondSet3Set.setBackgroundResource(R.drawable.frame_winner);

            } else if (SET_3_1_SET_b_DONE) {

                SET_3_2_SET_b_DONE = true;
                SET_3_2_SET_tb_DONE = true;


                SET_3_MATCH_DONE = true;

                A_pl_3Set_tb2 = x1;
                B_pl_3Set_tb2 = x2;

                pointSPSecondSet3Set.setText(String.valueOf(7));

                convert(x1, x2, pointFPSecondSet3Set, pointSPSecondSet3Set);

                Toast.makeText(this, pl2_1_3set + " " + pl2_2_3set + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                setWinnerSet_3(playerSecond3Set, pointSPSecondSet3Set, "secondPlayer");
                AA_KingTieBreakActivity.setButNotEnabled(goalFP3Set, goalSP3Set, spinner1p3s, spinner2p3s);

            } else {

                pointSPFirstSet3Set.setText(String.valueOf(7));
                A_pl_3Set_tb1 = x1;
                B_pl_3Set_tb1 = x2;
                SET_3_1_Set = false;
                SET_3_2_Set = true;
                SET_3_1_SET_b_DONE = true;
                SET_3_1_SET_tb_DONE = true;

                convert(x1, x2, pointFPFirstSet3Set, pointSPFirstSet3Set);

                Toast.makeText(this, pl2_1_3set + " " + pl2_2_3set + " " + getString(R.string.wins_1_set), Toast.LENGTH_LONG).show();
                pointSPFirstSet3Set.setBackgroundResource(R.drawable.frame_winner);
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

        tieBreakDone_3Set = true;
        tb_3Set = false;

        pointFP3Set.setText(String.valueOf(0));
        pointSP3Set.setText(String.valueOf(0));

        sstr = "";
        sstr1s = "";

        fpls3 = 0;
        spls3 = 0;
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


    public int getKorI(TextView ki) {
        if (ki.getText().toString().equals("0")) {
            n4 = 0;
        } else if (ki.getText().toString().equals("15")) {
            n4 = 1;
        } else if (ki.getText().toString().equals("30")) {
            n4 = 2;
        } else if (ki.getText().toString().equals("40")) {
            n4 = 3;
        } else if (ki.getText().toString().equals("Ad")) {
            n4 = 4;
        }
        return n4;
    }


    public int curNumberOfPoints(int p) {
        SQLiteDatabase db = mDbHelper3Set.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT number FROM statistic  WHERE _ID = " + Integer.toString(mDbHelper3Set.getLastId() - p), new String[]{});
        cursor.moveToFirst();
        curNum3Set = (int) cursor.getLong(0);
        cursor.close();
        return curNum3Set;
    }


    public void undo3Set(View view) {

        ad3Set = new AlertDialog.Builder(context);

        ad3Set.setMessage(getString(R.string.really_undo));

        ad3Set.setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                undo3Set1();
            }
        });
        ad3Set.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
            }
        });
        ad3Set.setCancelable(true);
        ad3Set.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
            }
        });
        ad3Set.show();
    }

    public void undo3Set1() {

        goalFP3Set.setEnabled(true);
        goalSP3Set.setEnabled(true);
        spinner1p3s.setEnabled(true);
        spinner2p3s.setEnabled(true);
        beEnabled(redo_3Set, getDrawable(R.drawable.ic_redo_black_24dp));

        noEnabled(saveButton3Set, getDrawable(R.drawable.ic_save_black_24dp_0_3));
        Set3StatusMatch = "Paused";
        playerFirst3Set.setBackgroundResource(R.drawable.frame_player);
        playerSecond3Set.setBackgroundResource(R.drawable.frame_player);
        stopMatchIsEnabled3Set = true;

        undoCounter3Set = undoCounter3Set + 1;

        updateColumnNumberDown3Set();

        SET_3_MATCH_DONE = false;

        if (getNum3Set("ktbfps").equals("-2")) {

            pointFP3Set.setText(adv_3Set);
            pointSP3Set.setText(getNum3Set("ktbsps"));

        } else if (getNum3Set("ktbsps").equals("-2")) {

            pointSP3Set.setText(adv_3Set);
            pointFP3Set.setText(getNum3Set("ktbfps"));

        } else {
            pointFP3Set.setText(getNum3Set("ktbfps"));
            pointSP3Set.setText(getNum3Set("ktbsps"));
        }

        if (getNum3Set("set1a").equals("7") & getNum3Set("set1b").equals("6")) {

            pointFPFirstSet3Set.setText(getNum3Set("set1a"));
            pointSPFirstSet3Set.setText(getNum3Set("set1b"));
            convertUndo(A_pl_3Set_tb1, B_pl_3Set_tb1, pointFPFirstSet3Set, pointSPFirstSet3Set);

        } else if (getNum3Set("set1a").equals("6") & getNum3Set("set1b").equals("7")) {

            pointFPFirstSet3Set.setText(getNum3Set("set1a"));
            pointSPFirstSet3Set.setText(getNum3Set("set1b"));
            convertUndo(A_pl_3Set_tb1, B_pl_3Set_tb1, pointFPFirstSet3Set, pointSPFirstSet3Set);

        } else {

            pointFPFirstSet3Set.setText(getNum3Set("set1a"));
            pointSPFirstSet3Set.setText(getNum3Set("set1b"));
        }

        if (getNum3Set("set2a").equals("7") & getNum3Set("set2b").equals("6")) {

            pointFPSecondSet3Set.setText(getNum3Set("set2a"));
            pointSPSecondSet3Set.setText(getNum3Set("set2b"));
            convertUndo(A_pl_3Set_tb2, B_pl_3Set_tb2, pointFPSecondSet3Set, pointSPSecondSet3Set);

        } else if (getNum3Set("set2a").equals("6") & getNum3Set("set2b").equals("7")) {

            pointFPSecondSet3Set.setText(getNum3Set("set2a"));
            pointSPSecondSet3Set.setText(getNum3Set("set2b"));
            convertUndo(A_pl_3Set_tb2, B_pl_3Set_tb2, pointFPSecondSet3Set, pointSPSecondSet3Set);

        } else {

            pointFPSecondSet3Set.setText(getNum3Set("set2a"));
            pointSPSecondSet3Set.setText(getNum3Set("set2b"));
        }
        pointFPThirdSet3Set.setText(getNum3Set("set3a"));
        pointSPThirdSet3Set.setText(getNum3Set("set3b"));

        if (SET_3_3_Set) {

            pointFPThirdSet3Set.setBackgroundResource(R.drawable.frame_player);
            pointSPThirdSet3Set.setBackgroundResource(R.drawable.frame_player);

            if ((getNum3Set("set2a").equals("7") & (getNum3Set("set2b").equals("6") | getNum3Set("set2b").equals("5"))) |
                    (getNum3Set("set2b").equals("7") & (getNum3Set("set2a").equals("6") | getNum3Set("set2a").equals("5"))) |
                    (getNum3Set("set2a").equals("6") & !"6".equals(getNum3Set("set2b")) & !"5".equals(getNum3Set("set2b"))) |
                    (getNum3Set("set2b").equals("6") & !"6".equals(getNum3Set("set2a")) & !"5".equals(getNum3Set("set2a")))) {

                SET_3_3_Set = true;

            } else {

                SET_3_3_Set = false;
                SET_3_2_Set = true;
                SET_3_2_SET_a_DONE = false;
                SET_3_2_SET_b_DONE = false;
            }
        }

        if (SET_3_2_Set) {

            pointFPSecondSet3Set.setBackgroundResource(R.drawable.frame_player);
            pointSPSecondSet3Set.setBackgroundResource(R.drawable.frame_player);

            SET_3_2_SET_a_DONE = false;
            SET_3_2_SET_b_DONE = false;

            if (SET_3_2_SET_tb_DONE) {

                SET_3_2_SET_tb_DONE = false;
                tb_3Set = true;
                tieBreakDone_3Set = false;
            }
            if (tb_3Set) {

                if (!"6".equals(getNum3Set("set2a")) | !"6".equals(getNum3Set("set2b"))) {
                    tb_3Set = false;

                }
            } else if (!tb_3Set) {

                if ((getNum3Set("set1a").equals("7") & (getNum3Set("set1b").equals("6") | getNum3Set("set1b").equals("5"))) |
                        (getNum3Set("set1b").equals("7") & (getNum3Set("set1a").equals("6") | getNum3Set("set1a").equals("5"))) |
                        (getNum3Set("set1a").equals("6") & !"6".equals(getNum3Set("set1b")) & !"5".equals(getNum3Set("set1b"))) |
                        (getNum3Set("set1b").equals("6") & !"6".equals(getNum3Set("set1a")) & !"5".equals(getNum3Set("set1a")))) {

                    SET_3_2_Set = true;

                } else {

                    SET_3_1_Set = true;
                    SET_3_2_Set = false;
                    SET_3_1_SET_a_DONE = false;
                    SET_3_1_SET_b_DONE = false;
                }
            }
        }
        if (SET_3_1_Set) {

            pointFPFirstSet3Set.setBackgroundResource(R.drawable.frame_player);
            pointSPFirstSet3Set.setBackgroundResource(R.drawable.frame_player);

            if (SET_3_1_SET_tb_DONE) {
                SET_3_1_SET_tb_DONE = false;
                tb_3Set = true;
                tieBreakDone_3Set = false;
            }

            if (tb_3Set) {
                if (!"6".equals(getNum3Set("set1a")) | !"6".equals(getNum3Set("set1b"))) {
                    tb_3Set = false;
                }
            }
        }

        goalsCounter3Set--;
        if (goalsCounter3Set <= 0) {
            noEnabled(undo_3Set, getDrawable(R.drawable.ic_undo_black_24dp_0_3));
        }

    }


    public void redo3Set(View view) {

        ad3Set = new AlertDialog.Builder(context);

        ad3Set.setMessage(getString(R.string.really_redo));

        ad3Set.setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                redo3Set1();
            }
        });
        ad3Set.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
            }
        });
        ad3Set.setCancelable(true);
        ad3Set.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
            }
        });
        ad3Set.show();
    }

    public void redo3Set1() {

        beEnabled(undo_3Set, getDrawable(R.drawable.ic_undo_black_24dp));
        spinner1p3s.setSelection(0);
        spinner2p3s.setSelection(0);

        updateColumnNumberUp3Set();

        undoCounter3Set = undoCounter3Set - 1;

        if (getNum3Set("ktbfps").equals("-2")) {

            pointFP3Set.setText(adv_3Set);
            pointSP3Set.setText(getNum3Set("ktbsps"));

        } else if (getNum3Set("ktbsps").equals("-2")) {

            pointFP3Set.setText(getNum3Set("ktbfps"));
            pointSP3Set.setText(adv_3Set);

        } else {

            pointFP3Set.setText(getNum3Set("ktbfps"));
            pointSP3Set.setText(getNum3Set("ktbsps"));
        }
///////////////////////////////////////
        if (getNum3Set("set1a").equals("7") & getNum3Set("set1b").equals("6")) {

            pointFPFirstSet3Set.setText(getNum3Set("set1a"));
            pointSPFirstSet3Set.setText(getNum3Set("set1b"));
            convertUndo(A_pl_3Set_tb1, B_pl_3Set_tb1, pointFPFirstSet3Set, pointSPFirstSet3Set);


        } else if (getNum3Set("set1a").equals("6") & getNum3Set("set1b").equals("7")) {

            pointFPFirstSet3Set.setText(getNum3Set("set1a"));
            pointSPFirstSet3Set.setText(getNum3Set("set1b"));
            convertUndo(A_pl_3Set_tb1, B_pl_3Set_tb1, pointFPFirstSet3Set, pointSPFirstSet3Set);

        } else {

            pointFPFirstSet3Set.setText(getNum3Set("set1a"));
            pointSPFirstSet3Set.setText(getNum3Set("set1b"));

        }

        if (getNum3Set("set2a").equals("7") & getNum3Set("set2b").equals("6")) {

            pointFPSecondSet3Set.setText(getNum3Set("set2a"));
            pointSPSecondSet3Set.setText(getNum3Set("set2b"));
            convertUndo(A_pl_3Set_tb2, B_pl_3Set_tb2, pointFPSecondSet3Set, pointSPSecondSet3Set);

        } else if (getNum3Set("set2a").equals("6") & getNum3Set("set2b").equals("7")) {

            pointFPSecondSet3Set.setText(getNum3Set("set2a"));
            pointSPSecondSet3Set.setText(getNum3Set("set2b"));
            convertUndo(A_pl_3Set_tb2, B_pl_3Set_tb2, pointFPSecondSet3Set, pointSPSecondSet3Set);

        } else {

            pointFPSecondSet3Set.setText(getNum3Set("set2a"));
            pointSPSecondSet3Set.setText(getNum3Set("set2b"));
        }

        pointFPThirdSet3Set.setText(getNum3Set("set3a"));
        pointSPThirdSet3Set.setText(getNum3Set("set3b"));

        if (SET_3_1_Set) {

            if (getNum3Set("set1a").equals("6") & getNum3Set("set1b").equals("6")) {

                tb_3Set = true;
                checkTieBreakNumber(Integer.valueOf(getNum3Set("ktbfps")), Integer.valueOf(getNum3Set("ktbsps")));

                if (getNum3Set("ktbfps").equals("0") && getNum3Set("ktbsps").equals("0")) {

                    Toast.makeText(getBaseContext(), getString(R.string.Tie_break_starts), Toast.LENGTH_SHORT).show();
                }

            } else if (getNum3Set("set1a").equals("7") & getNum3Set("set1b").equals("6") & getNum3Set("ktbfps").equals("0") & getNum3Set("ktbsps").equals("0")) {

                tb_3Set = false;
                SET_3_1_SET_a_DONE = true;
                SET_3_1_SET_tb_DONE = true;
                tieBreakDone_3Set = true;
                SET_3_1_Set = false;
                SET_3_2_Set = true;

                Toast.makeText(this, pl1_1_3set + " " + pl1_2_3set + " " + getString(R.string.wins_1_set), Toast.LENGTH_LONG).show();
                pointFPFirstSet3Set.setBackgroundResource(R.drawable.frame_winner);

            } else if (getNum3Set("set1a").equals("7") & getNum3Set("set1b").equals("6")
                    & (!"0".equals(getNum3Set("ktbfps")) | !"0".equals(getNum3Set("ktbsps")))) {

                tieBreakDone_3Set = false;

            } else if (getNum3Set("set1a").equals("6") & getNum3Set("set1b").equals("7")
                    & (!"0".equals(getNum3Set("ktbfps")) | !"0".equals(getNum3Set("ktbsps")))) {

                tieBreakDone_3Set = false;

            } else if (getNum3Set("set1a").equals("6") & getNum3Set("set1b").equals("7") & getNum3Set("ktbfps").equals("0") & getNum3Set("ktbsps").equals("0")) {

                tb_3Set = false;
                SET_3_1_SET_b_DONE = true;
                SET_3_1_SET_tb_DONE = true;
                tieBreakDone_3Set = true;
                SET_3_1_Set = false;
                SET_3_2_Set = true;

                Toast.makeText(this, pl2_1_3set + " " + pl2_2_3set + " " + getString(R.string.wins_1_set), Toast.LENGTH_LONG).show();
                pointSPFirstSet3Set.setBackgroundResource(R.drawable.frame_winner);

            } else {
                checkSetNumber(Integer.valueOf(getNum3Set("set1a")), Integer.valueOf(getNum3Set("set1b")));
            }
        }

        if (SET_3_2_Set) {

            if (getNum3Set("set2a").equals("6") & getNum3Set("set2b").equals("6")) {

                tb_3Set = true;

                checkTieBreakNumber(Integer.valueOf(getNum3Set("ktbfps")), Integer.valueOf(getNum3Set("ktbsps")));

                if (getNum3Set("ktbfps").equals("0") && getNum3Set("ktbsps").equals("0")) {

                    Toast.makeText(getBaseContext(), getString(R.string.Tie_break_starts), Toast.LENGTH_SHORT).show();
                }

            } else if (getNum3Set("set1a").equals("7") & getNum3Set("set1b").equals("6")

                    & (!"0".equals(getNum3Set("ktbfps")) | !"0".equals(getNum3Set("ktbsps")))) {

                tieBreakDone_3Set = false;

            } else if (getNum3Set("set1a").equals("6") & getNum3Set("set1b").equals("7")

                    & (!"0".equals(getNum3Set("ktbfps")) | !"0".equals(getNum3Set("ktbsps")))) {

                tieBreakDone_3Set = false;

            } else if (getNum3Set("set2a").equals("7") & getNum3Set("set2b").equals("6") & getNum3Set("ktbfps").equals("0") & getNum3Set("ktbsps").equals("0")) {

                if (SET_3_1_SET_a_DONE) {

                    SET_3_2_SET_a_DONE = true;
                    SET_3_2_SET_tb_DONE = true;
                    SET_3_MATCH_DONE = true;

                    Toast.makeText(this, pl1_1_3set + " " + pl1_2_3set + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                    setWinnerSet_3(playerFirst3Set, pointFPSecondSet3Set, "firstPlayer");
                    AA_KingTieBreakActivity.setButNotEnabled(goalFP3Set, goalSP3Set, spinner1p3s, spinner2p3s);

                } else if (SET_3_1_SET_b_DONE) {

                    SET_3_2_SET_a_DONE = true;
                    SET_3_2_SET_tb_DONE = true;
                    SET_3_2_Set = false;
                    SET_3_3_Set = true;

                    Toast.makeText(this, pl1_1_3set + " " + pl1_2_3set + " " + getString(R.string.wins_2_set), Toast.LENGTH_LONG).show();
                    pointFPSecondSet3Set.setBackgroundResource(R.drawable.frame_winner);
                }
                tb_3Set = false;
                tieBreakDone_3Set = true;

            } else if (getNum3Set("set2a").equals("6") & getNum3Set("set2b").equals("7") & getNum3Set("ktbfps").equals("0") & getNum3Set("ktbsps").equals("0")) {

                if (SET_3_1_SET_a_DONE) {

                    SET_3_2_SET_b_DONE = true;
                    SET_3_2_SET_tb_DONE = true;
                    SET_3_2_Set = false;
                    SET_3_3_Set = true;

                    Toast.makeText(this, pl2_1_3set + " " + pl2_2_3set + " " + getString(R.string.wins_2_set), Toast.LENGTH_LONG).show();
                    pointSPSecondSet3Set.setBackgroundResource(R.drawable.frame_winner);

                } else if (SET_3_1_SET_b_DONE) {

                    SET_3_2_SET_b_DONE = true;
                    SET_3_2_SET_tb_DONE = true;
                    SET_3_MATCH_DONE = true;

                    Toast.makeText(this, pl2_1_3set + " " + pl2_2_3set + " " + getString(R.string.WINS), Toast.LENGTH_LONG).show();
                    setWinnerSet_3(playerSecond3Set, pointSPSecondSet3Set, "secondPlayer");
                    AA_KingTieBreakActivity.setButNotEnabled(goalFP3Set, goalSP3Set, spinner1p3s, spinner2p3s);

                }
                tb_3Set = false;
                tieBreakDone_3Set = true;

            } else {
                checkSetNumber(Integer.valueOf(getNum3Set("set2a")), Integer.valueOf(getNum3Set("set2b")));
            }
        }

        if (SET_3_3_Set) {

            checkSetNumber(Integer.valueOf(getNum3Set("set3a")), Integer.valueOf(getNum3Set("set3b")));
        }


        if (undoCounter3Set == 0) {

            noEnabled(redo_3Set, getDrawable(R.drawable.ic_redo_black_24dp_0_3));
        }

        goalsCounter3Set++;
        if (goalsCounter3Set > 0) {
            beEnabled(undo_3Set, getDrawable(R.drawable.ic_undo_black_24dp));
        }
    }


    public String getNum3Set(String jg) {

        SQLiteDatabase db = mDbHelper3Set.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT " + jg + " FROM "
                + Contract.UndoNumbers.TABLE_NAME_1 + " WHERE " + Contract.UndoNumbers.COLUMN_NUM_NUM +
                " = (SELECT MAX( " + Contract.UndoNumbers.COLUMN_NUM_NUM + " ) FROM " + Contract.UndoNumbers.TABLE_NAME_1 + " ) - " + undoCounter3Set, new String[]{});
        cursor.moveToFirst();
        prevNum3Set = (int) cursor.getLong(0);
        cursor.close();
        return String.valueOf(prevNum3Set);
    }


    public void deleteRow3Set() {

        beEnabled(undo_3Set, getDrawable(R.drawable.ic_undo_black_24dp));
        SQLiteDatabase db = mDbHelper3Set.getReadableDatabase();
        String insertQuery = " DELETE FROM " + Contract.UndoNumbers.TABLE_NAME_1 + " WHERE " + Contract.UndoNumbers.COLUMN_NUM_NUM +
                " IN ( SELECT " + Contract.UndoNumbers.COLUMN_NUM_NUM + " FROM " + Contract.UndoNumbers.TABLE_NAME_1 +
                " ORDER BY " + Contract.UndoNumbers.COLUMN_NUM_NUM + " DESC LIMIT " + undoCounter3Set + ")";
        db.execSQL(insertQuery);
        undoCounter3Set = 0;
    }


    public void updateColumnNumberDown3Set() {

        SQLiteDatabase db = mDbHelper3Set.getWritableDatabase();
        String insertQuery = " UPDATE " + Contract.ContractNames.TABLE_NAME + " SET " + Contract.ContractNames.COLUMN_NUMBER
                + " = ((SELECT " + Contract.ContractNames.COLUMN_NUMBER + " FROM " + Contract.ContractNames.TABLE_NAME +
                " WHERE " + Contract.ContractNames._ID + " = " + getLastIdfromUndoTable1Set() + ") - 1 ) WHERE " + Contract.ContractNames._ID + " = " + getLastIdfromUndoTable1Set();
        db.execSQL(insertQuery);
    }

    public void updateColumnNumberUp3Set() {
        SQLiteDatabase db = mDbHelper3Set.getWritableDatabase();
        String insertQuery = " UPDATE " + Contract.ContractNames.TABLE_NAME + " SET " + Contract.ContractNames.COLUMN_NUMBER
                + " = ((SELECT " + Contract.ContractNames.COLUMN_NUMBER + " FROM " + Contract.ContractNames.TABLE_NAME +
                " WHERE " + Contract.ContractNames._ID + " = " + getLastIdfromUndoTable1Set() + ") + 1 ) WHERE " + Contract.ContractNames._ID + " = " + getLastIdfromUndoTable1Set();
        db.execSQL(insertQuery);
    }

    public int getLastIdfromUndoTable1Set() {

        SQLiteDatabase db = mDbHelper3Set.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT " + Contract.UndoNumbers.COLUMN_NUM_ID + " FROM "
                + Contract.UndoNumbers.TABLE_NAME_1 + " WHERE " + Contract.UndoNumbers.COLUMN_NUM_NUM +
                " = (SELECT MAX( " + Contract.UndoNumbers.COLUMN_NUM_NUM + " ) FROM " + Contract.UndoNumbers.TABLE_NAME_1 + " ) - " + (undoCounter3Set - 1), new String[]{});
        cursor.moveToFirst();
        lastIdfromUT3Set = (int) cursor.getLong(0);
        cursor.close();
        return lastIdfromUT3Set;
    }

    public void goToHelp() {
        Intent helpIntent = new Intent(this, DataBase.class);
        startActivity(helpIntent);
    }

    public void saveResultGoToMM() {
        saveResult3Set();
        mDbHelper3Set.clearTable(Contract.UndoNumbers.TABLE_NAME_1);
        mDbHelper3Set.clearTable(Contract.ContractNames.TABLE_NAME);
        finish();
        Intent intent1 = new Intent(this, AA_MainActivity.class);
        startActivity(intent1);
    }

    public void saveResult3Set() {

        if (getIntent().getExtras().getBoolean("isNew")) {

            mDbHelper3Set.putName13("fullName", "matchMode", "courtSurface", pl1_1_3set, pl1_2_3set, pl2_1_3set, pl2_2_3set, Set3StatusMatch,
                    mDbHelper3Set.getStringFromNumDataFromUndoTable("ktbfps", undoCounter3Set), mDbHelper3Set.getStringFromNumDataFromUndoTable("ktbsps", undoCounter3Set),
                    mDbHelper3Set.getStringFromNumDataFromUndoTable("set1a", undoCounter3Set), A_pl_3Set_tb1, mDbHelper3Set.getStringFromNumDataFromUndoTable("set1b", undoCounter3Set), B_pl_3Set_tb1,
                    mDbHelper3Set.getStringFromNumDataFromUndoTable("set2a", undoCounter3Set), A_pl_3Set_tb2, mDbHelper3Set.getStringFromNumDataFromUndoTable("set2b", undoCounter3Set), B_pl_3Set_tb2,
                    mDbHelper3Set.getStringFromNumDataFromUndoTable("set3a", undoCounter3Set), -2, mDbHelper3Set.getStringFromNumDataFromUndoTable("set3b", undoCounter3Set), -2,
                    "0", -2, "0", -2, "0", -2, "0", -2, 7, 3, 6, 2, 5, 1, 4, 0, getDFB_3Set(tb1pl_3Set), getDFB_3Set(tb2pl_3Set), getDFB_3Set(tieBreakDone_3Set),
                    getDFB_3Set(stopMatchIsEnabled3Set), getDFB_3Set(tb_3Set), getDFB_3Set(SET_3_1_SET_a_DONE), getDFB_3Set(SET_3_1_SET_b_DONE),
                    getDFB_3Set(SET_3_2_SET_a_DONE), getDFB_3Set(SET_3_2_SET_b_DONE), 0, 0, 0, 0, getDFB_3Set(SET_3_1_Set), getDFB_3Set(SET_3_2_Set),
                    getDFB_3Set(SET_3_3_Set), 0, 0, getDFB_3Set(SET_3_1_SET_tb_DONE), getDFB_3Set(SET_3_2_SET_tb_DONE), 0, 0, 0, getDFB_3Set(SET_3_MATCH_DONE));
        } else {

            mDbHelper3Set.putName122("fullName", "matchMode", "courtSurface", pl1_1_3set, pl1_2_3set, pl2_1_3set, pl2_2_3set, Set3StatusMatch,
                    mDbHelper3Set.getStringFromNumDataFromUndoTable("ktbfps", undoCounter3Set), mDbHelper3Set.getStringFromNumDataFromUndoTable("ktbsps", undoCounter3Set),
                    mDbHelper3Set.getStringFromNumDataFromUndoTable("set1a", undoCounter3Set), A_pl_3Set_tb1, mDbHelper3Set.getStringFromNumDataFromUndoTable("set1b", undoCounter3Set), B_pl_3Set_tb1,
                    mDbHelper3Set.getStringFromNumDataFromUndoTable("set2a", undoCounter3Set), A_pl_3Set_tb2, mDbHelper3Set.getStringFromNumDataFromUndoTable("set2b", undoCounter3Set), B_pl_3Set_tb2,
                    mDbHelper3Set.getStringFromNumDataFromUndoTable("set3a", undoCounter3Set), -2, mDbHelper3Set.getStringFromNumDataFromUndoTable("set3b", undoCounter3Set), -2,
                    "0", -2, "0", -2, "0", -2, "0", -2, 7, 3, 6, 2, 5, 1, 4, 0, getDFB_3Set(tb1pl_3Set), getDFB_3Set(tb2pl_3Set), getDFB_3Set(tieBreakDone_3Set),
                    getDFB_3Set(stopMatchIsEnabled3Set), getDFB_3Set(tb_3Set), getDFB_3Set(SET_3_1_SET_a_DONE), getDFB_3Set(SET_3_1_SET_b_DONE),
                    getDFB_3Set(SET_3_2_SET_a_DONE), getDFB_3Set(SET_3_2_SET_b_DONE), 0, 0, 0, 0, getDFB_3Set(SET_3_1_Set), getDFB_3Set(SET_3_2_Set),
                    getDFB_3Set(SET_3_3_Set), 0, 0, getDFB_3Set(SET_3_1_SET_tb_DONE), getDFB_3Set(SET_3_2_SET_tb_DONE), 0, 0, 0, getDFB_3Set(SET_3_MATCH_DONE));
        }
    }

    public void noSaveResultGoToMM() {
        mDbHelper3Set.clearTable(Contract.UndoNumbers.TABLE_NAME_1);
        mDbHelper3Set.clearTable(Contract.ContractNames.TABLE_NAME);
        finish();
        Intent intent1 = new Intent(this, AA_MainActivity.class);
        startActivity(intent1);
    }

    public void saveAndGo3Set(View view) {
            saveResultGoToMM();
    }

    public void saveResultFromStat(String st) {

        Set3StatusMatch = st;
        if (getIntent().getExtras().getBoolean("isNew")) {

            mDbHelper3Set.putName13("fullName", "matchMode", "courtSurface", pl1_1_3set, pl1_2_3set, pl2_1_3set, pl2_2_3set, Set3StatusMatch,
                    mDbHelper3Set.getStringFromNumDataFromUndoTable("ktbfps", undoCounter3Set), mDbHelper3Set.getStringFromNumDataFromUndoTable("ktbsps", undoCounter3Set),
                    mDbHelper3Set.getStringFromNumDataFromUndoTable("set1a", undoCounter3Set), A_pl_3Set_tb1, mDbHelper3Set.getStringFromNumDataFromUndoTable("set1b", undoCounter3Set), B_pl_3Set_tb1,
                    mDbHelper3Set.getStringFromNumDataFromUndoTable("set2a", undoCounter3Set), A_pl_3Set_tb2, mDbHelper3Set.getStringFromNumDataFromUndoTable("set2b", undoCounter3Set), B_pl_3Set_tb2,
                    mDbHelper3Set.getStringFromNumDataFromUndoTable("set3a", undoCounter3Set), -2, mDbHelper3Set.getStringFromNumDataFromUndoTable("set3b", undoCounter3Set), -2,
                    "0", -2, "0", -2, "0", -2, "0", -2, 7, 3, 6, 2, 5, 1, 4, 0, getDFB_3Set(tb1pl_3Set), getDFB_3Set(tb2pl_3Set), getDFB_3Set(tieBreakDone_3Set),
                    getDFB_3Set(stopMatchIsEnabled3Set), getDFB_3Set(tb_3Set), getDFB_3Set(SET_3_1_SET_a_DONE), getDFB_3Set(SET_3_1_SET_b_DONE),
                    getDFB_3Set(SET_3_2_SET_a_DONE), getDFB_3Set(SET_3_2_SET_b_DONE), 0, 0, 0, 0, getDFB_3Set(SET_3_1_Set), getDFB_3Set(SET_3_2_Set),
                    getDFB_3Set(SET_3_3_Set), 0, 0, getDFB_3Set(SET_3_1_SET_tb_DONE), getDFB_3Set(SET_3_2_SET_tb_DONE), 0, 0, 0, getDFB_3Set(SET_3_MATCH_DONE));
        } else {

            mDbHelper3Set.putName122("fullName", "matchMode", "courtSurface", pl1_1_3set, pl1_2_3set, pl2_1_3set, pl2_2_3set, Set3StatusMatch,
                    mDbHelper3Set.getStringFromNumDataFromUndoTable("ktbfps", undoCounter3Set), mDbHelper3Set.getStringFromNumDataFromUndoTable("ktbsps", undoCounter3Set),
                    mDbHelper3Set.getStringFromNumDataFromUndoTable("set1a", undoCounter3Set), A_pl_3Set_tb1, mDbHelper3Set.getStringFromNumDataFromUndoTable("set1b", undoCounter3Set), B_pl_3Set_tb1,
                    mDbHelper3Set.getStringFromNumDataFromUndoTable("set2a", undoCounter3Set), A_pl_3Set_tb2, mDbHelper3Set.getStringFromNumDataFromUndoTable("set2b", undoCounter3Set), B_pl_3Set_tb2,
                    mDbHelper3Set.getStringFromNumDataFromUndoTable("set3a", undoCounter3Set), -2, mDbHelper3Set.getStringFromNumDataFromUndoTable("set3b", undoCounter3Set), -2,
                    "0", -2, "0", -2, "0", -2, "0", -2, 7, 3, 6, 2, 5, 1, 4, 0, getDFB_3Set(tb1pl_3Set), getDFB_3Set(tb2pl_3Set), getDFB_3Set(tieBreakDone_3Set),
                    getDFB_3Set(stopMatchIsEnabled3Set), getDFB_3Set(tb_3Set), getDFB_3Set(SET_3_1_SET_a_DONE), getDFB_3Set(SET_3_1_SET_b_DONE),
                    getDFB_3Set(SET_3_2_SET_a_DONE), getDFB_3Set(SET_3_2_SET_b_DONE), 0, 0, 0, 0, getDFB_3Set(SET_3_1_Set), getDFB_3Set(SET_3_2_Set),
                    getDFB_3Set(SET_3_3_Set), 0, 0, getDFB_3Set(SET_3_1_SET_tb_DONE), getDFB_3Set(SET_3_2_SET_tb_DONE), 0, 0, 0, getDFB_3Set(SET_3_MATCH_DONE));
        }

        mDbHelper3Set.clearTable(Contract.UndoNumbers.TABLE_NAME_1);
        mDbHelper3Set.clearTable(Contract.ContractNames.TABLE_NAME);
        finish();
        Intent intent1 = new Intent(this, AA_MainActivity.class);
        startActivity(intent1);
    }

    private void setWinnerSet_3(TextView tv1, TextView tv2, String str) {

        tv1.setBackgroundResource(R.drawable.frame_winner);
        tv2.setBackgroundResource(R.drawable.frame_winner);
        beEnabled(saveButton3Set, getDrawable(R.drawable.ic_save_black_24dp));
        stopMatchIsEnabled3Set = false;
        Set3StatusMatch = str;
    }

    public int getDFB_3Set(Boolean b) {
        int u = 0;
        if (b) {
            u = 1;
        }
        return u;
    }

    @Override
    public void onDestroy() {
        mDbHelper3Set.clearTable(Contract.UndoNumbers.TABLE_NAME_1);
        mDbHelper3Set.clearTable(Contract.ContractNames.TABLE_NAME);
        super.onDestroy();
    }
}
