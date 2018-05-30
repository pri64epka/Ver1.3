package com.example.android.ver13;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.ver13.data.Contract;
import com.example.android.ver13.data.TennisHelper;

public class DataBase extends AppCompatActivity {
    private TennisHelper mDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aa_activity_data_base);
        mDbHelper = new TennisHelper(this);
    }
    private void displayContentDatabase() {
        TextView displayView = (TextView) findViewById(R.id.textView);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] projection = {
                Contract.ContractNames._ID,
                Contract.ContractNames.COLUMN_NAME,
                Contract.ContractNames.COLUMN_TYPE,
                Contract.ContractNames.COLUMN_NUMBER,
                Contract.ContractNames.COLUMN_DAY,
                Contract.ContractNames.COLUMN_MONTH,
                Contract.ContractNames.COLUMN_YEAR,
                Contract.ContractNames.COLUMN_MATCH_MODE,
                Contract.ContractNames.COLUMN_COURT_SURFACE,
                Contract.ContractNames.COLUMN_MATCH_NUMBER,};
        Cursor cursor = db.query(
                Contract.ContractNames.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);
        //  TextView displayView = (TextView) findViewById(R.id.textViewA);
        try {
            int idColumnIndex = cursor.getColumnIndex(Contract.ContractNames._ID);
            int nameColumnIndex = cursor.getColumnIndex(Contract.ContractNames.COLUMN_NAME);
            int typeColumnIndex = cursor.getColumnIndex(Contract.ContractNames.COLUMN_TYPE);
            int numberColumnIndex = cursor.getColumnIndex(Contract.ContractNames.COLUMN_NUMBER);
            int dayColumnIndex = cursor.getColumnIndex(Contract.ContractNames.COLUMN_DAY);
            int monthColumnIndex = cursor.getColumnIndex(Contract.ContractNames.COLUMN_MONTH);
            int yearColumnIndex = cursor.getColumnIndex(Contract.ContractNames.COLUMN_YEAR);
            int mmColumnIndex = cursor.getColumnIndex(Contract.ContractNames.COLUMN_MATCH_MODE);
            int csColumnIndex = cursor.getColumnIndex(Contract.ContractNames.COLUMN_COURT_SURFACE);
            int mnColumnIndex = cursor.getColumnIndex(Contract.ContractNames.COLUMN_MATCH_NUMBER);

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentType = cursor.getString(typeColumnIndex);
                int currentNumber = cursor.getInt(numberColumnIndex);
                int currentDay = cursor.getInt(dayColumnIndex);
                int currentMonth = cursor.getInt(monthColumnIndex);
                int currentYear = cursor.getInt(yearColumnIndex);
                String currentMatchMode = cursor.getString(mmColumnIndex);
                String currentCourtSurface = cursor.getString(csColumnIndex);
                int currentMn = cursor.getInt(mnColumnIndex);

                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentType + " - " +
                        currentNumber + " - " +
                        currentDay + "." +
                        currentMonth + "." +
                        currentYear + " - " +
                currentMatchMode + " - " +
                currentCourtSurface + "-"+
                currentMn));
            }
        } finally {
            cursor.close();
            displayView.append("\n");
        }
    }
    public void displayDBNames (View view){
        displayContentDatabase();
    }

    private void displayContentUndoTable() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] project = {
                Contract.UndoNumbers._ID,
                Contract.UndoNumbers.COLUMN_NUM_NUM,
                Contract.UndoNumbers.COLUMN_NUM_ID,
                Contract.UndoNumbers.COLUMN_KTBFPS,
                Contract.UndoNumbers.COLUMN_KTBSPS,
                Contract.UndoNumbers.COLUMN_FPS,
                Contract.UndoNumbers.COLUMN_SPS,
                Contract.UndoNumbers.COLUMN_SET1A,
                Contract.UndoNumbers.COLUMN_SET1B,
                Contract.UndoNumbers.COLUMN_SET2A,
                Contract.UndoNumbers.COLUMN_SET2B,
                Contract.UndoNumbers.COLUMN_SET3A,
                Contract.UndoNumbers.COLUMN_SET3B,
                Contract.UndoNumbers.COLUMN_SET4A,
                Contract.UndoNumbers.COLUMN_SET4B,
                Contract.UndoNumbers.COLUMN_SET5A,
                Contract.UndoNumbers.COLUMN_SET5B,
                Contract.UndoNumbers.COLUMN_tbQ
        };
        Cursor cursor1 = db.query(
                Contract.UndoNumbers.TABLE_NAME_1,   // The table to query
                project,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);
        TextView displayView1 = (TextView) findViewById(R.id.textView2);
        try {
            int idColumnIndex1 = cursor1.getColumnIndex(Contract.UndoNumbers._ID);
            int numberNumColumnIndex1 = cursor1.getColumnIndex(Contract.UndoNumbers.COLUMN_NUM_NUM);
            int numberColumnIndex1 = cursor1.getColumnIndex(Contract.UndoNumbers.COLUMN_NUM_ID);
            int kTBFPSColumnIndex1 = cursor1.getColumnIndex(Contract.UndoNumbers.COLUMN_KTBFPS);
            int kTBSPSColumnIndex1 = cursor1.getColumnIndex(Contract.UndoNumbers.COLUMN_KTBSPS);
            int fPSColumnIndex1 = cursor1.getColumnIndex(Contract.UndoNumbers.COLUMN_FPS);
            int sPSColumnIndex1 = cursor1.getColumnIndex(Contract.UndoNumbers.COLUMN_SPS);
            int set1aColumnIndex1 = cursor1.getColumnIndex(Contract.UndoNumbers.COLUMN_SET1A);
            int set1bColumnIndex1 = cursor1.getColumnIndex(Contract.UndoNumbers.COLUMN_SET1B);
            int set2aColumnIndex1 = cursor1.getColumnIndex(Contract.UndoNumbers.COLUMN_SET2A);
            int set2bColumnIndex1 = cursor1.getColumnIndex(Contract.UndoNumbers.COLUMN_SET2B);
            int set3aColumnIndex1 = cursor1.getColumnIndex(Contract.UndoNumbers.COLUMN_SET3A);
            int set3bColumnIndex1 = cursor1.getColumnIndex(Contract.UndoNumbers.COLUMN_SET3B);
            int set4aColumnIndex1 = cursor1.getColumnIndex(Contract.UndoNumbers.COLUMN_SET4A);
            int set4bColumnIndex1 = cursor1.getColumnIndex(Contract.UndoNumbers.COLUMN_SET4B);
            int set5aColumnIndex1 = cursor1.getColumnIndex(Contract.UndoNumbers.COLUMN_SET5A);
            int set5bColumnIndex1 = cursor1.getColumnIndex(Contract.UndoNumbers.COLUMN_SET5B);
            int tbQIndex1 = cursor1.getColumnIndex(Contract.UndoNumbers.COLUMN_tbQ);
            while (cursor1.moveToNext()) {
                int currentID = cursor1.getInt(idColumnIndex1);
                int currentNumberNum = cursor1.getInt(numberNumColumnIndex1);
                int currentNumber = cursor1.getInt(numberColumnIndex1);
                int currentKTBFPS = cursor1.getInt(kTBFPSColumnIndex1);
                int currentKTBSPS = cursor1.getInt(kTBSPSColumnIndex1);
                int currentFPS = cursor1.getInt(fPSColumnIndex1);
                int currentSPS = cursor1.getInt(sPSColumnIndex1);
                int currentSet1a = cursor1.getInt(set1aColumnIndex1);
                int currentSet1b = cursor1.getInt(set1bColumnIndex1);
                int currentSet2a = cursor1.getInt(set2aColumnIndex1);
                int currentSet2b = cursor1.getInt(set2bColumnIndex1);
                int currentSet3a = cursor1.getInt(set3aColumnIndex1);
                int currentSet3b = cursor1.getInt(set3bColumnIndex1);
                int currentSet4a = cursor1.getInt(set4aColumnIndex1);
                int currentSet4b = cursor1.getInt(set4bColumnIndex1);
                int currentSet5a = cursor1.getInt(set5aColumnIndex1);
                int currentSet5b = cursor1.getInt(set5bColumnIndex1);
                int currenttbQ = cursor1.getInt(tbQIndex1);

                displayView1.append(("\n" + currentID + "- " + currentNumberNum + "- " +
                        currentNumber + "- " + currentKTBFPS + "- " + currentKTBSPS + "- " +
                        currentFPS + "- " + currentSPS + "- " +
                        currentSet1a + "- " + currentSet1b + "- " +
                        currentSet2a + "- " + currentSet2b + "- " +
                        currentSet3a + "- " + currentSet3b + "- " +
                        currentSet4a + "- " + currentSet4b + "- " +
                        currentSet5a + "- " + currentSet5b + "- " +
                        currenttbQ ));
            }
        } finally {
            cursor1.close();
            displayView1.append("\n");
        }
    }
    public void displayUndoTable (View view) {
        displayContentUndoTable();
    }
}
