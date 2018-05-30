package com.example.android.ver13;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.ver13.data.Contract;
import com.example.android.ver13.data.TennisHelper;

public class HelpActivity extends AppCompatActivity {
    private TennisHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        mDbHelper = new TennisHelper(this);

        getSupportActionBar().setTitle("Помощь");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    private void displaySaveTable() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] project = {
                Contract.StatTableSave._ID,
                Contract.StatTableSave.COLUMN_NUM_NUM_st,
                Contract.StatTableSave.COLUMN_KTBFPS_st,
                Contract.StatTableSave.COLUMN_KTBSPS_st,
                Contract.StatTableSave.COLUMN_SET1A_st,
                Contract.StatTableSave.COLUMN_SET1A_st_tb,
                Contract.StatTableSave.COLUMN_SET1B_st,
                Contract.StatTableSave.COLUMN_SET1B_st_tb,
                Contract.StatTableSave.COLUMN_SET2A_st,
                Contract.StatTableSave.COLUMN_SET2A_st_tb,
                Contract.StatTableSave.COLUMN_SET2B_st,
                Contract.StatTableSave.COLUMN_SET2B_st_tb,
                Contract.StatTableSave.COLUMN_SET3A_st,
                Contract.StatTableSave.COLUMN_SET3A_st_tb,
                Contract.StatTableSave.COLUMN_SET3B_st,
                Contract.StatTableSave.COLUMN_SET3B_st_tb,
                Contract.StatTableSave.COLUMN_SET4A_st,
                Contract.StatTableSave.COLUMN_SET4A_st_tb,
                Contract.StatTableSave.COLUMN_SET4B_st,
                Contract.StatTableSave.COLUMN_SET4B_st_tb,
                Contract.StatTableSave.COLUMN_SET5A_st,
                Contract.StatTableSave.COLUMN_SET5A_st_tb,
                Contract.StatTableSave.COLUMN_SET5B_st,
                Contract.StatTableSave.COLUMN_SET5B_st_tb,
                Contract.StatTableSave.COLUMN_DATE,

        };
        Cursor cursor1 = db.query(
                Contract.StatTableSave.STATISTIC_SAVE,   // The table to query
                project,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);
        TextView displayView1 = (TextView) findViewById(R.id.textView);
        try {
            int idColumnIndex1 = cursor1.getColumnIndex(Contract.StatTableSave._ID);
            int numberNumColumnIndex1 = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_NUM_NUM_st);
            int kTBFPSColumnIndex1 = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_KTBFPS_st);
            int kTBSPSColumnIndex1 = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_KTBSPS_st);
            int set1aColumnIndex1 = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_SET1A_st);
            int set1aColumnIndex1_tb = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_SET1A_st_tb);
            int set1bColumnIndex1 = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_SET1B_st);
            int set1bColumnIndex1_tb = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_SET1B_st_tb);
            int set2aColumnIndex1 = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_SET2A_st);
            int set2aColumnIndex1_tb = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_SET2A_st_tb);
            int set2bColumnIndex1 = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_SET2B_st);
            int set2bColumnIndex1_tb = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_SET2B_st_tb);
            int set3aColumnIndex1 = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_SET3A_st);
            int set3aColumnIndex1_tb = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_SET3A_st_tb);
            int set3bColumnIndex1 = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_SET3B_st);
            int set3bColumnIndex1_tb = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_SET3B_st_tb);
            int set4aColumnIndex1 = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_SET4A_st);
            int set4aColumnIndex1_tb = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_SET4A_st_tb);
            int set4bColumnIndex1 = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_SET4B_st);
            int set4bColumnIndex1_tb = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_SET4B_st_tb);
            int set5aColumnIndex1 = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_SET5A_st);
            int set5aColumnIndex1_tb = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_SET5A_st_tb);
            int set5bColumnIndex1 = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_SET5B_st);
            int set5bColumnIndex1_tb = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_SET5B_st_tb);
            int set5bColumnIndex1_date = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_DATE);
            while (cursor1.moveToNext()) {
                int currentID = cursor1.getInt(idColumnIndex1);
                int currentNumberNum = cursor1.getInt(numberNumColumnIndex1);
                int currentKTBFPS = cursor1.getInt(kTBFPSColumnIndex1);
                int currentKTBSPS = cursor1.getInt(kTBSPSColumnIndex1);
                int currentSet1a = cursor1.getInt(set1aColumnIndex1);
                int currentSet1a_tb = cursor1.getInt(set1aColumnIndex1_tb);
                int currentSet1b = cursor1.getInt(set1bColumnIndex1);
                int currentSet1b_tb = cursor1.getInt(set1bColumnIndex1_tb);
                int currentSet2a = cursor1.getInt(set2aColumnIndex1);
                int currentSet2a_tb = cursor1.getInt(set2aColumnIndex1_tb);
                int currentSet2b = cursor1.getInt(set2bColumnIndex1);
                int currentSet2b_tb = cursor1.getInt(set2bColumnIndex1_tb);
                int currentSet3a = cursor1.getInt(set3aColumnIndex1);
                int currentSet3a_tb = cursor1.getInt(set3aColumnIndex1_tb);
                int currentSet3b = cursor1.getInt(set3bColumnIndex1);
                int currentSet3b_tb = cursor1.getInt(set3bColumnIndex1_tb);
                int currentSet4a = cursor1.getInt(set4aColumnIndex1);
                int currentSet4a_tb = cursor1.getInt(set4aColumnIndex1_tb);
                int currentSet4b = cursor1.getInt(set4bColumnIndex1);
                int currentSet4b_tb = cursor1.getInt(set4bColumnIndex1_tb);
                int currentSet5a = cursor1.getInt(set5aColumnIndex1);
                int currentSet5a_tb = cursor1.getInt(set5aColumnIndex1_tb);
                int currentSet5b = cursor1.getInt(set5bColumnIndex1);
                int currentSet5b_tb = cursor1.getInt(set5bColumnIndex1_tb);
                String currentSet5b_date = cursor1.getString(set5bColumnIndex1_date);
                displayView1.append(("\n" + currentID + "- " + currentNumberNum + "- " +
                        currentKTBFPS + "- " + currentKTBSPS + "- " +
                        currentSet1a + "- " + currentSet1a_tb + "- " +
                        currentSet1b + "- " + currentSet1b_tb + "- " +
                        currentSet2a + "- " + currentSet2a_tb + "- " +
                        currentSet2b + "- " + currentSet2b_tb + "- " +
                        currentSet3a + "- " + currentSet3a_tb + "- " +
                        currentSet3b + "- " + currentSet3b_tb + "- " +
                        currentSet4a + "- " + currentSet4a_tb + "- " +
                        currentSet4b + "- " + currentSet4b_tb + "- " +
                        currentSet5a + "- " + currentSet5a_tb + "- " +
                        currentSet5b + "- " + currentSet5b_tb + "- " + currentSet5b_date));
            }
        } finally {
            cursor1.close();
            displayView1.append("\n");
        }
    }

    public void display(View view) {
        displaySaveTable();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void showName(View view) {
        TextView vt = (TextView) findViewById(R.id.textView20);
        //  vt.setText(mDbHelper.getName(0));
    }

    public void showStatSave(View view) {
        displayStatisticSaveTable();
    }

    private void displayStatisticSaveTable() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] project = {
                Contract.StatTableSave._ID,
                Contract.StatTableSave.COLUMN_A_NAME_SAVE,
                Contract.StatTableSave.COLUMN_B_NAME_SAVE,
                Contract.StatTableSave.COLUMN_A_Winner,
                Contract.StatTableSave.COLUMN_B_Winner,
                Contract.StatTableSave.COLUMN_A_Ace,
                Contract.StatTableSave.COLUMN_B_Ace,
                Contract.StatTableSave.COLUMN_A_UE,
                Contract.StatTableSave.COLUMN_B_UE,
                Contract.StatTableSave.COLUMN_A_DE,
                Contract.StatTableSave.COLUMN_B_DE,
                Contract.StatTableSave.COLUMN_MM,
                Contract.StatTableSave.COLUMN_COURT_TYPE,
                Contract.StatTableSave.COLUMN_ADD,
                Contract.StatTableSave.COLUMN_A_NAME_1,
                Contract.StatTableSave.COLUMN_A_NAME_2,
                Contract.StatTableSave.COLUMN_B_NAME_1,
                Contract.StatTableSave.COLUMN_B_NAME_2,
                Contract.StatTableSave.COLUMN_STOP_MATCH_IS_ENABLED,
                Contract.StatTableSave.COLUMN_MATCH_STATUS
        };
        Cursor cursor1 = db.query(
                Contract.StatTableSave.STATISTIC_SAVE,   // The table to query
                project,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);
        TextView displayView12 = (TextView) findViewById(R.id.textView21);
        try {
            int idColumnIndex1 = cursor1.getColumnIndex(Contract.StatTableSave._ID);
            int numberNumColumnIndex1 = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_A_NAME_SAVE);
            int kTBFPSColumnIndex1 = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_B_NAME_SAVE);
            int kTBSPSColumnIndex1 = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_A_Winner);
            int fPSColumnIndex1 = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_B_Winner);
            int sPSColumnIndex1 = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_A_Ace);
            int set1aColumnIndex1 = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_B_Ace);
            int set1bColumnIndex1 = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_A_UE);
            int set2aColumnIndex1 = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_B_UE);
            int set2bColumnIndex1 = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_A_DE);
            int set3aColumnIndex1 = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_B_DE);
            int set3bColumnIndex1 = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_MM);
            int set4aColumnIndex1 = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_COURT_TYPE);
            int tbQIndex1 = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_ADD);
            int tbQIndex12 = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_A_NAME_1);
            int tbQIndex13 = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_A_NAME_2);
            int tbQIndex14 = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_B_NAME_1);
            int tbQIndex15 = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_B_NAME_2);
            int tRbQIndex15 = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_STOP_MATCH_IS_ENABLED);
            int tbQIndex16 = cursor1.getColumnIndex(Contract.StatTableSave.COLUMN_MATCH_STATUS);
            while (cursor1.moveToNext()) {
                int currentID = cursor1.getInt(idColumnIndex1);
                String currentNumberNum = cursor1.getString(numberNumColumnIndex1);
                String currentKTBFPS = cursor1.getString(kTBFPSColumnIndex1);
                int currentKTBSPS = cursor1.getInt(kTBSPSColumnIndex1);
                int currentFPS = cursor1.getInt(fPSColumnIndex1);
                int currentSPS = cursor1.getInt(sPSColumnIndex1);
                int currentSet1a = cursor1.getInt(set1aColumnIndex1);
                int currentSet1b = cursor1.getInt(set1bColumnIndex1);
                int currentSet2a = cursor1.getInt(set2aColumnIndex1);
                int currentSet2b = cursor1.getInt(set2bColumnIndex1);
                int currentSet3a = cursor1.getInt(set3aColumnIndex1);
                String currentSet3b = cursor1.getString(set3bColumnIndex1);
                String currentSet4a = cursor1.getString(set4aColumnIndex1);
                int currenttbQ = cursor1.getInt(tbQIndex1);
                String currentSet4a1 = cursor1.getString(tbQIndex12);
                String currentSet4a2 = cursor1.getString(tbQIndex13);
                String currentSet4a3 = cursor1.getString(tbQIndex14);
                String currentSet4a4 = cursor1.getString(tbQIndex15);
                int cvurrenttbQ = cursor1.getInt(tRbQIndex15);
                String currentSet4a5 = cursor1.getString(tbQIndex16);
                displayView12.append(("\n" + currentID + "- " + currentNumberNum + "- " +
                        currentKTBFPS + "- " + currentKTBSPS + "- " +
                        currentFPS + "- " + currentSPS + "- " +
                        currentSet1a + "- " + currentSet1b + "- " +
                        currentSet2a + "- " + currentSet2b + "- " +
                        currentSet3a + "- " + currentSet3b + "- " +
                        currentSet4a + "- " +
                        currenttbQ + "- " + currentSet4a1 + "- " +
                        currentSet4a2 + "- " + currentSet4a3 + "- " +
                        currentSet4a4 + "- " + cvurrenttbQ + "- " + currentSet4a5));
            }
        } finally {
            cursor1.close();
            displayView12.append("\n");
        }
    }

    public int hhh() {
        int miIdSaveTable = -5;
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT MIN(numnum) FROM statistic_SAVE ", new String[]{});
        if (cursor != null && cursor.moveToFirst()) {
            miIdSaveTable = (int) cursor.getLong(0); //The 0 is the column index, we only have 1 column, so the index is 0
        }
        cursor.close();
        return miIdSaveTable;

    }

    public void sid(View view) {
        TextView displayViewd = (TextView) findViewById(R.id.textView22);
        // displayViewd.setText(mDbHelper.gettDistinctANames());
    }
}
