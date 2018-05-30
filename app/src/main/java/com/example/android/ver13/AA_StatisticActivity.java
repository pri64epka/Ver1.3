package com.example.android.ver13;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.ver13.data.Contract;
import com.example.android.ver13.data.TennisHelper;

public class AA_StatisticActivity extends AppCompatActivity {

    private TennisHelper mDbHelperStat;
    String na1_str, na2_str, numCurStat;
    int lastIdStat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aa_activity_statistic);

        if (getSupportActionBar() != null) {

            getSupportActionBar().setTitle(getString(R.string.Statistic));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mDbHelperStat = new TennisHelper(this);

        TextView na_1 = (TextView) findViewById(R.id.na1);
        na_1.setText(getNamePlayer(na1_str, 7));

        TextView na_2 = (TextView) findViewById(R.id.na2);
        na_2.setText(getNamePlayer(na2_str, 1));

        TextView _w1 = (TextView) findViewById(R.id.w1);
        TextView _ace1 = (TextView) findViewById(R.id.ace1);

        TextView _ue1 = (TextView) findViewById(R.id.ue1);
        TextView _de1 = (TextView) findViewById(R.id.de1);

        TextView _w2 = (TextView) findViewById(R.id.w2);
        TextView _ace2 = (TextView) findViewById(R.id.ace2);

        TextView _ue2 = (TextView) findViewById(R.id.ue2);
        TextView _de2 = (TextView) findViewById(R.id.de2);

        _w1.setText(curNumberOfPoints(7));
        setZero(_w1);

        _ace1.setText(curNumberOfPoints(6));
        setZero(_ace1);

        _ue1.setText(curNumberOfPoints(5));
        setZero(_ue1);

        _de1.setText(curNumberOfPoints(4));
        setZero(_de1);

        _w2.setText(curNumberOfPoints(3));
        setZero(_w2);

        _ace2.setText(curNumberOfPoints(2));
        setZero(_ace2);

        _ue2.setText(curNumberOfPoints(1));
        setZero(_ue2);

        _de2.setText(curNumberOfPoints(0));
        setZero(_de2);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public String getNamePlayer(String st, int i) {
        SQLiteDatabase db = mDbHelperStat.getWritableDatabase();
        Cursor cursor = db.rawQuery(" SELECT " + Contract.ContractNames.COLUMN_NAME + " FROM statistic  WHERE _ID = " + (getLastId() - i), new String[]{});
        if (cursor.moveToFirst()) {
            st = cursor.getString(cursor.getColumnIndex("fullName"));
        }
        cursor.close();
        return st;
    }

    public int getLastId() {
        SQLiteDatabase db = mDbHelperStat.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT _ID FROM statistic  ORDER BY _ID DESC limit 1 ", new String[]{});
        cursor.moveToFirst();
        lastIdStat = (int) cursor.getLong(0);
        cursor.close();
        return lastIdStat;
    }

    public String curNumberOfPoints(int p) {
        SQLiteDatabase db = mDbHelperStat.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT number FROM statistic  WHERE _ID = " + Integer.toString(getLastId() - p), new String[]{});
        if (cursor.moveToFirst()) {
            numCurStat = cursor.getString(cursor.getColumnIndex("number"));
        }
        cursor.close();
        return numCurStat;
    }

    public void setZero(TextView vt) {
        if (vt.getText().toString().equals("")) {
            vt.setText("0");
        }
    }
}