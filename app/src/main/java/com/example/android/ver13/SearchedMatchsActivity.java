package com.example.android.ver13;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.ver13.data.TennisHelper;

import java.util.ArrayList;

import static com.example.android.ver13.AA_NewMatchActivity.isNew;

public class SearchedMatchsActivity extends AppCompatActivity {

    private TennisHelper mDbHelper;
    public static ArrayList<Integer> numbers;
    public static int selId;
    Button overallStatisticButton;
    String ft;
    AlertDialog.Builder ad;
    Context context;

    //  private static final String TAG = "SearchedMatchActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aa_activity_searched_matchs);

        context = SearchedMatchsActivity.this;

        if (getSupportActionBar() != null) {

            getSupportActionBar().setTitle(getString(R.string.Result));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mDbHelper = new TennisHelper(this);

        numbers = getIntent().getExtras().getIntegerArrayList("user1");

        overallStatisticButton = (Button) findViewById(R.id._overallStatisticButton);

        ArrayList<String> nA1List = new ArrayList<>();
        ArrayList<String> nA2List = new ArrayList<>();
        ArrayList<String> nB1List = new ArrayList<>();
        ArrayList<String> nB2List = new ArrayList<>();

        ArrayList<Integer> sA_List = new ArrayList<>();
        ArrayList<Integer> sB_List = new ArrayList<>();

        ArrayList<Integer> set1A_List = new ArrayList<>();
        ArrayList<Integer> set1A_tb_List = new ArrayList<>();
        ArrayList<Integer> set1B_List = new ArrayList<>();
        ArrayList<Integer> set1B_tb_List = new ArrayList<>();

        ArrayList<Integer> set2A_List = new ArrayList<>();
        ArrayList<Integer> set2A_tb_List = new ArrayList<>();
        ArrayList<Integer> set2B_List = new ArrayList<>();
        ArrayList<Integer> set2B_tb_List = new ArrayList<>();

        ArrayList<Integer> set3A_List = new ArrayList<>();
        ArrayList<Integer> set3A_tb_List = new ArrayList<>();
        ArrayList<Integer> set3B_List = new ArrayList<>();
        ArrayList<Integer> set3B_tb_List = new ArrayList<>();

        ArrayList<Integer> set4A_List = new ArrayList<>();
        ArrayList<Integer> set4A_tb_List = new ArrayList<>();
        ArrayList<Integer> set4B_List = new ArrayList<>();
        ArrayList<Integer> set4B_tb_List = new ArrayList<>();

        ArrayList<Integer> set5A_List = new ArrayList<>();
        ArrayList<Integer> set5A_tb_List = new ArrayList<>();
        ArrayList<Integer> set5B_List = new ArrayList<>();
        ArrayList<Integer> set5B_tb_List = new ArrayList<>();

        ArrayList<String> nDate = new ArrayList<>();
        ArrayList<String> nMatchModeList = new ArrayList<>();
        ArrayList<String> nCourtTypeList = new ArrayList<>();
        ArrayList<String> nMatchStatusList = new ArrayList<>();

        for (int y = 0; y < numbers.size(); y++) {

            sA_List.add(mDbHelper.getNumById("ktbfpsST", numbers.get(y)));
            sB_List.add(mDbHelper.getNumById("ktbspsST", numbers.get(y)));
            set1A_List.add(mDbHelper.getNumById("set1aST", numbers.get(y)));
            set1A_tb_List.add(mDbHelper.getNumById("set1aST_tb", numbers.get(y)));
            set1B_List.add(mDbHelper.getNumById("set1bST", numbers.get(y)));
            set1B_tb_List.add(mDbHelper.getNumById("set1bST_tb", numbers.get(y)));

            set2A_List.add(mDbHelper.getNumById("set2aST", numbers.get(y)));
            set2A_tb_List.add(mDbHelper.getNumById("set2aST_tb", numbers.get(y)));
            set2B_List.add(mDbHelper.getNumById("set2bST", numbers.get(y)));
            set2B_tb_List.add(mDbHelper.getNumById("set2bST_tb", numbers.get(y)));

            set3A_List.add(mDbHelper.getNumById("set3aST", numbers.get(y)));
            set3A_tb_List.add(mDbHelper.getNumById("set3aST_tb", numbers.get(y)));
            set3B_List.add(mDbHelper.getNumById("set3bST", numbers.get(y)));
            set3B_tb_List.add(mDbHelper.getNumById("set3bST_tb", numbers.get(y)));

            set4A_List.add(mDbHelper.getNumById("set4aST", numbers.get(y)));
            set4A_tb_List.add(mDbHelper.getNumById("set4aST_tb", numbers.get(y)));
            set4B_List.add(mDbHelper.getNumById("set4bST", numbers.get(y)));
            set4B_tb_List.add(mDbHelper.getNumById("set4bST_tb", numbers.get(y)));

            set5A_List.add(mDbHelper.getNumById("set5aST", numbers.get(y)));
            set5A_tb_List.add(mDbHelper.getNumById("set5aST_tb", numbers.get(y)));
            set5B_List.add(mDbHelper.getNumById("set5bST", numbers.get(y)));
            set5B_tb_List.add(mDbHelper.getNumById("set5bST_tb", numbers.get(y)));

            nMatchModeList.add(mDbHelper.getNameById("_match_mode", numbers.get(y)));
            nA1List.add(mDbHelper.getNameById("name_A_1", numbers.get(y)));
            nA2List.add(mDbHelper.getNameById("name_A_2", numbers.get(y)));
            nB1List.add(mDbHelper.getNameById("name_B_1", numbers.get(y)));
            nB2List.add(mDbHelper.getNameById("name_B_2", numbers.get(y)));
            nDate.add(mDbHelper.getNameById("date", numbers.get(y)));
            nCourtTypeList.add(mDbHelper.getNameById("_court_type", numbers.get(y)));
            nMatchStatusList.add(mDbHelper.getNameById("match_status", numbers.get(y)));

            isTwoPlayers();
        }

        final ArrayList<SelectedMatch> mySelectedMatches = new ArrayList<>();

        for (int i = 0; i < numbers.size(); i++) {

            mySelectedMatches.add(new SelectedMatch(nA1List.get(i), nA2List.get(i), nB1List.get(i), nB2List.get(i), nDate.get(i), nMatchModeList.get(i), nCourtTypeList.get(i),
                    nMatchStatusList.get(i), set1A_List.get(i), set1A_tb_List.get(i), set1B_List.get(i), set1B_tb_List.get(i),
                    set2A_List.get(i), set2A_tb_List.get(i), set2B_List.get(i), set2B_tb_List.get(i),
                    set3A_List.get(i), set3A_tb_List.get(i), set3B_List.get(i), set3B_tb_List.get(i),
                    set4A_List.get(i), set4A_tb_List.get(i), set4B_List.get(i), set4B_tb_List.get(i),
                    set5A_List.get(i), set5A_tb_List.get(i), set5B_List.get(i), set5B_tb_List.get(i),
                    sA_List.get(i), sB_List.get(i)));
        }
        ListView _matchList = (ListView) findViewById(R.id.matchList);

        final AA_SelectedMatchAdaptor mySelectedMatchAdapter = new AA_SelectedMatchAdaptor(this, mySelectedMatches);

        _matchList.setAdapter(mySelectedMatchAdapter);


        _matchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                selId = numbers.get(position);
                Intent intent = new Intent(getBaseContext(), AA_OneMatchStatActivity.class);
                startActivity(intent);
            }
        });

        _matchList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {

                final int ppos = pos;

                ad = new AlertDialog.Builder(context);

                selId = numbers.get(pos);


                Log.v("long clicked", "pos: " + ppos + "-" + selId + mDbHelper.getNameById("_match_mode", selId));

                if (mDbHelper.getNameById("match_status", selId).equals("Paused")) {

                    final String conMatch = getString(R.string.continue_match);
                    final String delMatch = getString(R.string.delete_from_db);

                    final String[] reaDB = {conMatch, delMatch};
                    ad.setTitle(getString(R.string.choose_action))
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

                                            if (ft.equals(conMatch)) {

                                                continueMatch();
                                                closeSearchAct();
                                                finish();


                                            } else if (ft.equals(delMatch)) {

                                                mySelectedMatches.remove(ppos);

                                                numbers.remove(ppos);

                                                mySelectedMatchAdapter.notifyDataSetChanged();
                                                mDbHelper.deleteRowFromStatisticSaveTable(selId);

                                                Toast.makeText(getBaseContext(), "2", Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                    })
                            .setSingleChoiceItems(reaDB, -1,
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog,
                                                            int item) {
                                            ft = reaDB[item];
                                        }
                                    });
                    ad.show();


                } else {

                    Log.v("long clicked", "pos: " + "ad created");
                    ad.setMessage(getString(R.string.ad_alert_for_selected_match));
                    ad.setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int arg1) {

                            mySelectedMatches.remove(ppos);
                            numbers.remove(ppos);
                            mySelectedMatchAdapter.notifyDataSetChanged();
                            mDbHelper.deleteRowFromStatisticSaveTable(selId);
                          //  Log.v("long clicked", "pos: " + ppos + "-" + selId);

                        }
                    });

                    ad.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int arg1) {
                            dialog.cancel();

                        }
                    });
                    ad.setCancelable(true);
                    ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        public void onCancel(DialogInterface dialog) {
                        }
                    });
                    ad.show();
                }
                return true;
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_result_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.getData_item:
                Intent intent = new Intent(this, HelpInfoActivity.class);
                startActivity(intent);
                break;

            case R.id.help_item:
                Intent intent1 = new Intent(this, HelpInfoActivity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void goToOverallActivity(View view) {

        Intent int1 = new Intent(this, AA_OnlyTwoPlayersActivity.class);

        startActivity(int1);
    }

    public void isTwoPlayers() {

        ArrayList<String> records = new ArrayList<>();

        for (int i = 0; i < numbers.size(); i++) {

            if (!records.contains(mDbHelper.getNameById("full_A_NameSAVE", numbers.get(i)))) {
                records.add(mDbHelper.getNameById("full_A_NameSAVE", numbers.get(i)));
            }
            if (!records.contains(mDbHelper.getNameById("full_B_NameSAVE", numbers.get(i)))) {
                records.add(mDbHelper.getNameById("full_B_NameSAVE", numbers.get(i)));
            }
        }
        if (records.size() == 2 && numbers.size() >= 2) {

            overallStatisticButton.setEnabled(true);

        } else {

            overallStatisticButton.setEnabled(false);
        }
    }

    public void continueMatch() {

        isNew = false;

        switch (mDbHelper.getNameById("_match_mode", selId)) {
            case "0":
                Intent intent = new Intent(this, AA_KingTieBreakActivity.class);
                extraDataToContinuedMatch(intent);
                startActivity(intent);
                break;
            case "1":
                Intent intent1 = new Intent(this, AA_Set1Activity.class);
                extraDataToContinuedMatch(intent1);
                startActivity(intent1);
                break;
            case "2":
                Intent intent2 = new Intent(this, AA_Set3KTBActivity.class);
                extraDataToContinuedMatch(intent2);
                startActivity(intent2);
                break;
            case "3":
                Intent intent3 = new Intent(this, AA_Set3USActivity.class);
                extraDataToContinuedMatch(intent3);
                startActivity(intent3);
                break;
            case "4":
                Intent intent4 = new Intent(this, AA_Set3Activity.class);
                extraDataToContinuedMatch(intent4);
                startActivity(intent4);
                break;
            case "5":
                Intent intent5 = new Intent(this, AA_Set5USActivity.class);
                extraDataToContinuedMatch(intent5);
                startActivity(intent5);
                break;
            case "6":
                Intent intent6 = new Intent(this, AA_Set5Activity.class);
                extraDataToContinuedMatch(intent6);
                startActivity(intent6);
                break;
        }
    }

    public void extraDataToContinuedMatch(Intent i) {

        i.putExtra("isNew", isNew);
    }

    public void closeSearchAct() {

        Intent intent = new Intent("finish");
        sendBroadcast(intent);

    }
}
