package com.example.android.ver13.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.ver13.AA_NewMatchActivity.currDay;
import static com.example.android.ver13.AA_NewMatchActivity.currMon;
import static com.example.android.ver13.AA_NewMatchActivity.currYear;
import static com.example.android.ver13.SearchedMatchsActivity.selId;


public class TennisHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = TennisHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "statistica.db";
    private static final int DATABASE_VERSION = 1;


    public TennisHelper(Context cotext) {
        super(cotext, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_NAMES_TABLE = "CREATE TABLE " + Contract.ContractNames.TABLE_NAME +
                " (" + Contract.ContractNames._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Contract.ContractNames.COLUMN_NAME + " TEXT, "
                + Contract.ContractNames.COLUMN_TYPE + " TEXT, "
                + Contract.ContractNames.COLUMN_NUMBER + " INTEGER, "
                + Contract.ContractNames.COLUMN_MATCH_MODE + " TEXT, "
                + Contract.ContractNames.COLUMN_COURT_SURFACE + " TEXT, "
                + Contract.ContractNames.COLUMN_DAY + " INTEGER, "
                + Contract.ContractNames.COLUMN_MONTH + " INTEGER, "
                + Contract.ContractNames.COLUMN_YEAR + " INTEGER, "
                + Contract.ContractNames.COLUMN_MATCH_NUMBER + " INTEGER);";
        db.execSQL(SQL_CREATE_NAMES_TABLE);

        String SQL_CREATE_STAT_SAVE_TABLE = "CREATE TABLE " + Contract.StatTableSave.STATISTIC_SAVE +
                " (" + Contract.StatTableSave._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Contract.StatTableSave.COLUMN_A_NAME_SAVE + " TEXT, "
                + Contract.StatTableSave.COLUMN_B_NAME_SAVE + " TEXT, "
                + Contract.StatTableSave.COLUMN_NUM_NUM_st + " INTEGER, "
                + Contract.StatTableSave.COLUMN_KTBFPS_st + " INTEGER DEFAULT 0 , "
                + Contract.StatTableSave.COLUMN_KTBSPS_st + " INTEGER DEFAULT 0 , "
                + Contract.StatTableSave.COLUMN_SET1A_st + " INTEGER DEFAULT 0 , "
                + Contract.StatTableSave.COLUMN_SET1A_st_tb + " INTEGER DEFAULT -3, "
                + Contract.StatTableSave.COLUMN_SET1B_st + " INTEGER DEFAULT 0 , "
                + Contract.StatTableSave.COLUMN_SET1B_st_tb + " INTEGER DEFAULT -3 , "
                + Contract.StatTableSave.COLUMN_SET2A_st + " INTEGER DEFAULT 0 , "
                + Contract.StatTableSave.COLUMN_SET2A_st_tb + " INTEGER DEFAULT -3 , "
                + Contract.StatTableSave.COLUMN_SET2B_st + " INTEGER DEFAULT 0 , "
                + Contract.StatTableSave.COLUMN_SET2B_st_tb + " INTEGER DEFAULT -3 , "
                + Contract.StatTableSave.COLUMN_SET3A_st + " INTEGER DEFAULT 0 , "
                + Contract.StatTableSave.COLUMN_SET3A_st_tb + " INTEGER DEFAULT -3 , "
                + Contract.StatTableSave.COLUMN_SET3B_st + " INTEGER DEFAULT 0 , "
                + Contract.StatTableSave.COLUMN_SET3B_st_tb + " INTEGER DEFAULT -3 , "
                + Contract.StatTableSave.COLUMN_SET4A_st + " INTEGER DEFAULT 0 , "
                + Contract.StatTableSave.COLUMN_SET4A_st_tb + " INTEGER DEFAULT -3 , "
                + Contract.StatTableSave.COLUMN_SET4B_st + " INTEGER DEFAULT 0 , "
                + Contract.StatTableSave.COLUMN_SET4B_st_tb + " INTEGER DEFAULT -3 , "
                + Contract.StatTableSave.COLUMN_SET5A_st + " INTEGER DEFAULT 0 , "
                + Contract.StatTableSave.COLUMN_SET5A_st_tb + " INTEGER DEFAULT -3 , "
                + Contract.StatTableSave.COLUMN_SET5B_st + " INTEGER DEFAULT 0 , "
                + Contract.StatTableSave.COLUMN_SET5B_st_tb + " INTEGER DEFAULT -3 , "
                + Contract.StatTableSave.COLUMN_A_Winner + " INTEGER DEFAULT 0 , "
                + Contract.StatTableSave.COLUMN_B_Winner + " INTEGER DEFAULT 0 , "
                + Contract.StatTableSave.COLUMN_A_Ace + " INTEGER DEFAULT 0 , "
                + Contract.StatTableSave.COLUMN_B_Ace + " INTEGER DEFAULT 0 , "
                + Contract.StatTableSave.COLUMN_A_UE + " INTEGER DEFAULT 0 , "
                + Contract.StatTableSave.COLUMN_B_UE + " INTEGER DEFAULT 0 , "
                + Contract.StatTableSave.COLUMN_A_DE + " INTEGER DEFAULT 0 , "
                + Contract.StatTableSave.COLUMN_B_DE + " INTEGER DEFAULT 0 , "
                + Contract.StatTableSave.COLUMN_MM + " TEXT, "
                + Contract.StatTableSave.COLUMN_COURT_TYPE + " TEXT, "
                + Contract.StatTableSave.COLUMN_ADD + " INTEGER DEFAULT 0 , "
                + Contract.StatTableSave.COLUMN_A_NAME_1 + " TEXT, "
                + Contract.StatTableSave.COLUMN_A_NAME_2 + " TEXT, "
                + Contract.StatTableSave.COLUMN_B_NAME_1 + " TEXT, "
                + Contract.StatTableSave.COLUMN_B_NAME_2 + " TEXT, "
                + Contract.StatTableSave.COLUMN_DATE + " TEXT, "
                + Contract.StatTableSave.COLUMN_TB1PL + " INTEGER DEFAULT 0, "
                + Contract.StatTableSave.COLUMN_TB2PL + " INTEGER DEFAULT 0, "
                + Contract.StatTableSave.COLUMN_TIE_BREAK_DONE + " INTEGER DEFAULT 0, "
                + Contract.StatTableSave.COLUMN_STOP_MATCH_IS_ENABLED + " INTEGER DEFAULT 0, "
                + Contract.StatTableSave.COLUMN_TB + " INTEGER DEFAULT 0, "
                + Contract.StatTableSave.COLUMN_1_SET_A_DONE + " INTEGER DEFAULT 0, "
                + Contract.StatTableSave.COLUMN_1_SET_B_DONE + " INTEGER DEFAULT 0, "
                + Contract.StatTableSave.COLUMN_2_SET_A_DONE + " INTEGER DEFAULT 0, "
                + Contract.StatTableSave.COLUMN_2_SET_B_DONE + " INTEGER DEFAULT 0, "
                + Contract.StatTableSave.COLUMN_3_SET_A_DONE + " INTEGER DEFAULT 0, "
                + Contract.StatTableSave.COLUMN_3_SET_B_DONE + " INTEGER DEFAULT 0, "
                + Contract.StatTableSave.COLUMN_4_SET_A_DONE + " INTEGER DEFAULT 0, "
                + Contract.StatTableSave.COLUMN_4_SET_B_DONE + " INTEGER DEFAULT 0, "
                + Contract.StatTableSave.COLUMN_1_SET + " INTEGER DEFAULT 0, "
                + Contract.StatTableSave.COLUMN_2_SET + " INTEGER DEFAULT 0, "
                + Contract.StatTableSave.COLUMN_3_SET + " INTEGER DEFAULT 0, "
                + Contract.StatTableSave.COLUMN_4_SET + " INTEGER DEFAULT 0, "
                + Contract.StatTableSave.COLUMN_5_SET + " INTEGER DEFAULT 0, "
                + Contract.StatTableSave.COLUMN_1_SET_TB_DONE + " INTEGER DEFAULT 0, "
                + Contract.StatTableSave.COLUMN_2_SET_TB_DONE + " INTEGER DEFAULT 0, "
                + Contract.StatTableSave.COLUMN_3_SET_TB_DONE + " INTEGER DEFAULT 0, "
                + Contract.StatTableSave.COLUMN_4_SET_TB_DONE + " INTEGER DEFAULT 0, "
                + Contract.StatTableSave.COLUMN_5_SET_TB_DONE + " INTEGER DEFAULT 0, "
                + Contract.StatTableSave.COLUMN_MATCH_DONE + " INTEGER DEFAULT 0, "
                + Contract.StatTableSave.COLUMN_MATCH_STATUS + " TEXT DEFAULT 'Paused'); ";

        db.execSQL(SQL_CREATE_STAT_SAVE_TABLE);

        String SQL_CREATE_NUMBERS_TABLE = "CREATE TABLE " + Contract.UndoNumbers.TABLE_NAME_1 +
                " (" + Contract.UndoNumbers._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Contract.UndoNumbers.COLUMN_NUM_NUM + " INTEGER DEFAULT 0 , "
                + Contract.UndoNumbers.COLUMN_NUM_ID + " INTEGER DEFAULT 0 , "
                + Contract.UndoNumbers.COLUMN_KTBFPS + " INTEGER DEFAULT 0 , "
                + Contract.UndoNumbers.COLUMN_KTBSPS + " INTEGER DEFAULT 0 , "
                + Contract.UndoNumbers.COLUMN_FPS + " INTEGER DEFAULT 0 , "
                + Contract.UndoNumbers.COLUMN_SPS + " INTEGER DEFAULT 0 , "
                + Contract.UndoNumbers.COLUMN_SET1A + " INTEGER DEFAULT 0 , "
                + Contract.UndoNumbers.COLUMN_SET1B + " INTEGER DEFAULT 0 , "
                + Contract.UndoNumbers.COLUMN_SET2A + " INTEGER DEFAULT 0 , "
                + Contract.UndoNumbers.COLUMN_SET2B + " INTEGER DEFAULT 0 , "
                + Contract.UndoNumbers.COLUMN_SET3A + " INTEGER DEFAULT 0 , "
                + Contract.UndoNumbers.COLUMN_SET3B + " INTEGER DEFAULT 0 , "
                + Contract.UndoNumbers.COLUMN_SET4A + " INTEGER DEFAULT 0 , "
                + Contract.UndoNumbers.COLUMN_SET4B + " INTEGER DEFAULT 0 , "
                + Contract.UndoNumbers.COLUMN_SET5A + " INTEGER DEFAULT 0 , "
                + Contract.UndoNumbers.COLUMN_SET5B + " INTEGER DEFAULT 0 , "
                + Contract.UndoNumbers.COLUMN_tbQ + " INTEGER DEFAULT 0); ";
        db.execSQL(SQL_CREATE_NUMBERS_TABLE);

   /*     for (int g = 0; g < 20; g++) {

            int f = -2;
            String SQL_CREATE_20_RESULTS_TABLE = "INSERT into " + Contract.StatTableSave.STATISTIC_SAVE +
                    " (" + Contract.StatTableSave.COLUMN_ADD + "," + Contract.StatTableSave.COLUMN_NUM_NUM_st + " ) VALUES " + "(" + f + "," + g + ");";
            db.execSQL(SQL_CREATE_20_RESULTS_TABLE);

        }
        */
    }

    public List<String> getDistinctANames() {

        List<String> records = new ArrayList<>();
        String getSt;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT  DISTINCT full_A_NameSAVE FROM statistic_SAVE  WHERE full_A_NameSAVE IS NOT NULL ", new String[]{});
        while (cursor.moveToNext()) {
            getSt = cursor.getString(cursor.getColumnIndex(Contract.StatTableSave.COLUMN_A_NAME_SAVE));
            records.add(getSt);
        }
        cursor.close();
        return records;
    }

    public List<String> getDistinctBNames() {

        List<String> records = new ArrayList<>();
        String getSt;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT  DISTINCT full_B_NameSAVE FROM statistic_SAVE WHERE full_B_NameSAVE IS NOT NULL ", new String[]{});
        while (cursor.moveToNext()) {
            getSt = cursor.getString(cursor.getColumnIndex(Contract.StatTableSave.COLUMN_B_NAME_SAVE));
            records.add(getSt);
        }
        cursor.close();
        return records;
    }

    public List<String> getDistinctNames() {

        List<String> records = new ArrayList<>();

        for (int i = 0; i < getDistinctANames().size(); i++) {
            records.add(getDistinctANames().get(i));
        }
        for (int y = 0; y < getDistinctBNames().size(); y++) {
            if (!records.contains(getDistinctBNames().get(y))) {
                records.add(getDistinctBNames().get(y));
            }
        }
        return records;
    }

    public ArrayList<Integer> getListNumId(String st1, String st2) {

        ArrayList<Integer> records = new ArrayList<>();
        int qId;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT _ID FROM statistic_SAVE  WHERE  (full_A_NameSAVE = '" + st1 + "' AND full_B_NameSAVE = '" + st2 + "') OR (full_A_NameSAVE = '" + st2 + "' AND full_B_NameSAVE = '" + st1 + "')", new String[]{});
        while (cursor.moveToNext()) {
            qId = cursor.getInt(0);
            records.add(qId);
        }
        cursor.close();
        return records;
    }

    public ArrayList<Integer> getListNumId1(String st1) {

        ArrayList<Integer> records = new ArrayList<>();
        int qId;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT _ID FROM statistic_SAVE  WHERE  full_A_NameSAVE = '" + st1 + "' OR full_B_NameSAVE = '" + st1 + "'", new String[]{});
        while (cursor.moveToNext()) {
            qId = cursor.getInt(0);
            records.add(qId);
        }
        cursor.close();
        return records;
    }

    public ArrayList<Integer> getIdBy1Surface(String st1) {

        ArrayList<Integer> records = new ArrayList<>();
        int qId;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT _ID FROM statistic_SAVE  WHERE  _court_type = '" + st1 + "'", new String[]{});
        while (cursor.moveToNext()) {
            qId = cursor.getInt(0);
            records.add(qId);
        }
        cursor.close();
        return records;
    }

    public ArrayList<Integer> getIdBy2Surface(String st1, String st2) {

        ArrayList<Integer> records = new ArrayList<>();
        int qId;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT _ID FROM statistic_SAVE  WHERE  _court_type = '" + st1 + "' OR _court_type = '" + st2 + "'", new String[]{});
        while (cursor.moveToNext()) {
            qId = cursor.getInt(0);
            records.add(qId);
        }
        cursor.close();
        return records;
    }

    public ArrayList<Integer> getIdBy3Surface(String st1, String st2, String st3) {

        ArrayList<Integer> records = new ArrayList<>();
        int qId;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT _ID FROM statistic_SAVE  WHERE  _court_type = '" + st1 + "' OR _court_type = '" + st2 + "' OR _court_type = '" + st3 + "' ", new String[]{});
        while (cursor.moveToNext()) {
            qId = cursor.getInt(0);
            records.add(qId);
        }
        cursor.close();
        return records;
    }

    public ArrayList<Integer> getIdBy4Surface(String st1, String st2, String st3, String st4) {

        ArrayList<Integer> records = new ArrayList<>();
        int qId;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT _ID FROM statistic_SAVE  WHERE  _court_type = '" + st1 + "' OR _court_type = '" + st2 + "' OR _court_type = '" + st3 + "' OR _court_type = '" + st4 + "'", new String[]{});
        while (cursor.moveToNext()) {
            qId = cursor.getInt(0);
            records.add(qId);
        }
        cursor.close();
        return records;
    }

    public ArrayList<Integer> getIdBy1MM(String st1) {

        ArrayList<Integer> records = new ArrayList<>();
        int qId;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT _ID FROM statistic_SAVE  WHERE  _match_mode = '" + st1 + "'", new String[]{});
        while (cursor.moveToNext()) {
            qId = cursor.getInt(0);
            records.add(qId);
        }
        cursor.close();
        return records;
    }

    public ArrayList<Integer> getIdBy2MM(String st1, String st2) {

        ArrayList<Integer> records = new ArrayList<>();
        int qId;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT _ID FROM statistic_SAVE  WHERE  _match_mode = '" + st1 + "' OR _match_mode = '" + st2 + "'", new String[]{});
        while (cursor.moveToNext()) {
            qId = cursor.getInt(0);
            records.add(qId);
        }
        cursor.close();
        return records;
    }

    public ArrayList<Integer> getIdBy3MM(String st1, String st2, String st3) {

        ArrayList<Integer> records = new ArrayList<>();
        int qId;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT _ID FROM statistic_SAVE  WHERE  _match_mode = '" + st1 + "' OR _match_mode = '" + st2 + "' OR _match_mode = '" + st3 +
                "' ", new String[]{});
        while (cursor.moveToNext()) {
            qId = cursor.getInt(0);
            records.add(qId);
        }
        cursor.close();
        return records;
    }

    public ArrayList<Integer> getIdBy4MM(String st1, String st2, String st3, String st4) {

        ArrayList<Integer> records = new ArrayList<>();
        int qId;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT _ID FROM statistic_SAVE  WHERE  _match_mode = '" + st1 + "' OR _match_mode = '" + st2 + "' OR _match_mode = '" + st3 +
                "' OR _match_mode = '" + st4 + "'", new String[]{});
        while (cursor.moveToNext()) {
            qId = cursor.getInt(0);
            records.add(qId);
        }
        cursor.close();
        return records;
    }

    public ArrayList<Integer> getIdBy5MM(String st1, String st2, String st3, String st4, String st5) {

        ArrayList<Integer> records = new ArrayList<>();
        int qId;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT _ID FROM statistic_SAVE  WHERE  _match_mode = '" + st1 + "' OR _match_mode = '" + st2 + "' OR _match_mode = '" + st3 +
                "' OR _match_mode = '" + st4 + "' OR _match_mode = '" + st5 + "'", new String[]{});
        while (cursor.moveToNext()) {
            qId = cursor.getInt(0);
            records.add(qId);
        }
        cursor.close();
        return records;
    }

    public ArrayList<Integer> getIdBy6MM(String st1, String st2, String st3, String st4, String st5, String st6) {

        ArrayList<Integer> records = new ArrayList<>();
        int qId;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT _ID FROM statistic_SAVE  WHERE  _match_mode = '" + st1 + "' OR _match_mode = '" + st2 + "' OR _match_mode = '" + st3 +
                "' OR _match_mode = '" + st4 + "' OR _match_mode = '" + st5 + "' OR _match_mode = '" + st6 + "'", new String[]{});
        while (cursor.moveToNext()) {
            qId = cursor.getInt(0);
            records.add(qId);
        }
        cursor.close();
        return records;
    }

    public int getLastId() {

        int lastId;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT _ID FROM statistic  ORDER BY _ID DESC limit 1 ", new String[]{});
        cursor.moveToFirst();
        lastId = cursor.getInt(0);
        cursor.close();
        return lastId;
    }

    public String getStringDataToSaveInStat(String str, int y) {
        String name;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT " + str + " FROM statistic  WHERE  _ID =" + (getLastId() - y), new String[]{});
        cursor.moveToFirst();
        name = cursor.getString(0).trim();
        cursor.close();
        return name;

    }

    public void clearTable(String st) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(st, null, null);
    }

    public String getNameById(String n1, int der) {
        String getSt;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT " + n1 + " FROM statistic_SAVE  WHERE " + Contract.StatTableSave._ID + " = " + der, new String[]{});
        cursor.moveToFirst();
        getSt = cursor.getString(0);
        cursor.close();
        return getSt;
    }

    public void deleteRowFromStatisticSaveTable(int i) {


        SQLiteDatabase db = this.getReadableDatabase();
        String insertQuery = " DELETE FROM " + Contract.StatTableSave.STATISTIC_SAVE + " WHERE " + Contract.StatTableSave._ID +
                "=" + i;
        db.execSQL(insertQuery);

    }

    public int getNumById(String n1, int der) {
        int getSt;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT " + n1 + " FROM statistic_SAVE  WHERE " + Contract.StatTableSave._ID + " = " + der, new String[]{});
        cursor.moveToFirst();
        getSt = cursor.getInt(cursor.getColumnIndex(n1));
        cursor.close();
        return getSt;
    }

    private String getDate() {
        String name;
        name = getStringDataToSaveInStat("day", 0) + "." + getStringDataToSaveInStat("month", 0) + "." + getStringDataToSaveInStat("year", 0);
        return name;

    }

    private int getNumberData(int y) {

        int name;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT number FROM statistic  WHERE  _ID =" + (getLastId() - y), new String[]{});
        cursor.moveToFirst();
        name = cursor.getInt(0);
        cursor.close();
        return name;

    }

    private int getMinIdStatSaveTable() {
        int i;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT MIN(_ID) FROM statistic_SAVE WHERE ADD_save = -2 ", new String[]{});
        cursor.moveToFirst();
        i = (int) cursor.getLong(0);
        cursor.close();
        return i;
    }

    public void putFreeRowInStatisticSaveTable(int i){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values1 = new ContentValues();
        values1.put(Contract.StatTableSave.COLUMN_ADD, -2);
        values1.put(Contract.StatTableSave.COLUMN_A_NAME_SAVE, "");
        values1.put(Contract.StatTableSave.COLUMN_B_NAME_SAVE, "");
        values1.put(Contract.StatTableSave.COLUMN_MM, "");
        values1.put(Contract.StatTableSave.COLUMN_COURT_TYPE, "");
        values1.put(Contract.StatTableSave.COLUMN_A_NAME_1, "");
        values1.put(Contract.StatTableSave.COLUMN_A_NAME_2, "");
        values1.put(Contract.StatTableSave.COLUMN_B_NAME_1, "");
        values1.put(Contract.StatTableSave.COLUMN_B_NAME_2, "");

        db.update(Contract.StatTableSave.STATISTIC_SAVE, values1, Contract.StatTableSave._ID + " = ?", new String[]{String.valueOf(i)});
    }

    public void putName13(String fName, String modeM, String surface, String na1, String na2, String nb1, String nb2,
                         String mSt, String pa, String pb, String s1a, int s1a_tb, String s1b, int s1b_tb, String s2a, int s2a_tb, String s2b, int s2b_tb,
                         String s3a, int s3a_tb, String s3b, int s3b_tb, String s4a, int s4a_tb, String s4b, int s4b_tb,
                         String s5a, int s5a_tb, String s5b, int s5b_tb, int z1, int z2, int z3, int z4, int z5, int z6, int z7, int z8, int bv1, int bv2, int bv3, int bv4,
                         int bv5, int bv6, int bv7, int bv8, int bv9, int bv10, int bv11, int bv12, int bv13, int bv14, int bv15, int bv16, int bv17, int bv18, int bv19,
                         int bv20, int bv21, int bv22, int bv23, int bv24) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values1 = new ContentValues();

        values1.put(Contract.StatTableSave.COLUMN_ADD, 99);
        values1.put(Contract.StatTableSave.COLUMN_A_NAME_SAVE, getStringDataToSaveInStat(fName, z1));
        values1.put(Contract.StatTableSave.COLUMN_B_NAME_SAVE, getStringDataToSaveInStat(fName, z2));
        values1.put(Contract.StatTableSave.COLUMN_A_Winner, getNumberData(z1));
        values1.put(Contract.StatTableSave.COLUMN_B_Winner, getNumberData(z2));
        values1.put(Contract.StatTableSave.COLUMN_A_Ace, getNumberData(z3));
        values1.put(Contract.StatTableSave.COLUMN_B_Ace, getNumberData(z4));
        values1.put(Contract.StatTableSave.COLUMN_A_UE, getNumberData(z5));
        values1.put(Contract.StatTableSave.COLUMN_B_UE, getNumberData(z6));
        values1.put(Contract.StatTableSave.COLUMN_A_DE, getNumberData(z7));
        values1.put(Contract.StatTableSave.COLUMN_B_DE, getNumberData(z8));
        values1.put(Contract.StatTableSave.COLUMN_MM, getStringDataToSaveInStat(modeM, z2));
        values1.put(Contract.StatTableSave.COLUMN_COURT_TYPE, getStringDataToSaveInStat(surface, z2));
        values1.put(Contract.StatTableSave.COLUMN_A_NAME_1, na1);
        values1.put(Contract.StatTableSave.COLUMN_A_NAME_2, na2);
        values1.put(Contract.StatTableSave.COLUMN_B_NAME_1, nb1);
        values1.put(Contract.StatTableSave.COLUMN_B_NAME_2, nb2);
        values1.put(Contract.StatTableSave.COLUMN_MATCH_STATUS, mSt);
        values1.put(Contract.StatTableSave.COLUMN_DATE, getDate());
        values1.put(Contract.StatTableSave.COLUMN_KTBFPS_st, pa);
        values1.put(Contract.StatTableSave.COLUMN_KTBSPS_st, pb);
        //////////////////////////////////////////////////////////////////
        values1.put(Contract.StatTableSave.COLUMN_SET1A_st, s1a);
        values1.put(Contract.StatTableSave.COLUMN_SET1A_st_tb, s1a_tb);
        values1.put(Contract.StatTableSave.COLUMN_SET1B_st, s1b);
        values1.put(Contract.StatTableSave.COLUMN_SET1B_st_tb, s1b_tb);
        /////////////////////////////////////////////////////////////////
        values1.put(Contract.StatTableSave.COLUMN_SET2A_st, s2a);
        values1.put(Contract.StatTableSave.COLUMN_SET2A_st_tb, s2a_tb);
        values1.put(Contract.StatTableSave.COLUMN_SET2B_st, s2b);
        values1.put(Contract.StatTableSave.COLUMN_SET2B_st_tb, s2b_tb);
        ////////////////////////////////////////////////////////////////
        values1.put(Contract.StatTableSave.COLUMN_SET3A_st, s3a);
        values1.put(Contract.StatTableSave.COLUMN_SET3A_st_tb, s3a_tb);
        values1.put(Contract.StatTableSave.COLUMN_SET3B_st, s3b);
        values1.put(Contract.StatTableSave.COLUMN_SET3B_st_tb, s3b_tb);
        ///////////////////////////////////////////////////////////////
        values1.put(Contract.StatTableSave.COLUMN_SET4A_st, s4a);
        values1.put(Contract.StatTableSave.COLUMN_SET4A_st_tb, s4a_tb);
        values1.put(Contract.StatTableSave.COLUMN_SET4B_st, s4b);
        values1.put(Contract.StatTableSave.COLUMN_SET4B_st_tb, s4b_tb);
        ///////////////////////////////////////////////////////////////
        values1.put(Contract.StatTableSave.COLUMN_SET5A_st, s5a);
        values1.put(Contract.StatTableSave.COLUMN_SET5A_st_tb, s5a_tb);
        values1.put(Contract.StatTableSave.COLUMN_SET5B_st, s5b);
        values1.put(Contract.StatTableSave.COLUMN_SET5B_st_tb, s5b_tb);
        ///////////////////////////////////////////////////////////////
        values1.put(Contract.StatTableSave.COLUMN_TB1PL, bv1);
        values1.put(Contract.StatTableSave.COLUMN_TB2PL, bv2);
        values1.put(Contract.StatTableSave.COLUMN_TIE_BREAK_DONE, bv3);
        values1.put(Contract.StatTableSave.COLUMN_STOP_MATCH_IS_ENABLED, bv4);
        values1.put(Contract.StatTableSave.COLUMN_TB, bv5);
        values1.put(Contract.StatTableSave.COLUMN_1_SET_A_DONE, bv6);
        values1.put(Contract.StatTableSave.COLUMN_1_SET_B_DONE, bv7);
        values1.put(Contract.StatTableSave.COLUMN_2_SET_A_DONE, bv8);
        values1.put(Contract.StatTableSave.COLUMN_2_SET_B_DONE, bv9);
        values1.put(Contract.StatTableSave.COLUMN_3_SET_A_DONE, bv10);
        values1.put(Contract.StatTableSave.COLUMN_3_SET_B_DONE, bv11);
        values1.put(Contract.StatTableSave.COLUMN_4_SET_A_DONE, bv12);
        values1.put(Contract.StatTableSave.COLUMN_4_SET_B_DONE, bv13);
        values1.put(Contract.StatTableSave.COLUMN_1_SET, bv14);
        values1.put(Contract.StatTableSave.COLUMN_2_SET, bv15);
        values1.put(Contract.StatTableSave.COLUMN_3_SET, bv16);
        values1.put(Contract.StatTableSave.COLUMN_4_SET, bv17);
        values1.put(Contract.StatTableSave.COLUMN_5_SET, bv18);
        values1.put(Contract.StatTableSave.COLUMN_1_SET_TB_DONE, bv19);
        values1.put(Contract.StatTableSave.COLUMN_2_SET_TB_DONE, bv20);
        values1.put(Contract.StatTableSave.COLUMN_3_SET_TB_DONE, bv21);
        values1.put(Contract.StatTableSave.COLUMN_4_SET_TB_DONE, bv22);
        values1.put(Contract.StatTableSave.COLUMN_5_SET_TB_DONE, bv23);
        values1.put(Contract.StatTableSave.COLUMN_MATCH_DONE, bv24);

        long newRowId = db.insert(Contract.StatTableSave.STATISTIC_SAVE, null, values1);
    }

    public void putName122(String fName, String modeM, String surface, String na1, String na2, String nb1, String nb2,
                          String mSt, String pa, String pb, String s1a, int s1a_tb, String s1b, int s1b_tb, String s2a, int s2a_tb, String s2b, int s2b_tb,
                          String s3a, int s3a_tb, String s3b, int s3b_tb, String s4a, int s4a_tb, String s4b, int s4b_tb,
                          String s5a, int s5a_tb, String s5b, int s5b_tb, int z1, int z2, int z3, int z4, int z5, int z6, int z7, int z8, int bv1, int bv2, int bv3, int bv4,
                          int bv5, int bv6, int bv7, int bv8, int bv9, int bv10, int bv11, int bv12, int bv13, int bv14, int bv15, int bv16, int bv17, int bv18, int bv19,
                          int bv20, int bv21, int bv22, int bv23, int bv24) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values1 = new ContentValues();

        values1.put(Contract.StatTableSave.COLUMN_ADD, 99);
        values1.put(Contract.StatTableSave.COLUMN_A_NAME_SAVE, getStringDataToSaveInStat(fName, z1));
        values1.put(Contract.StatTableSave.COLUMN_B_NAME_SAVE, getStringDataToSaveInStat(fName, z2));
        values1.put(Contract.StatTableSave.COLUMN_A_Winner, getNumberData(z1));
        values1.put(Contract.StatTableSave.COLUMN_B_Winner, getNumberData(z2));
        values1.put(Contract.StatTableSave.COLUMN_A_Ace, getNumberData(z3));
        values1.put(Contract.StatTableSave.COLUMN_B_Ace, getNumberData(z4));
        values1.put(Contract.StatTableSave.COLUMN_A_UE, getNumberData(z5));
        values1.put(Contract.StatTableSave.COLUMN_B_UE, getNumberData(z6));
        values1.put(Contract.StatTableSave.COLUMN_A_DE, getNumberData(z7));
        values1.put(Contract.StatTableSave.COLUMN_B_DE, getNumberData(z8));
        values1.put(Contract.StatTableSave.COLUMN_MM, getStringDataToSaveInStat(modeM, z2));
        values1.put(Contract.StatTableSave.COLUMN_COURT_TYPE, getStringDataToSaveInStat(surface, z2));
        values1.put(Contract.StatTableSave.COLUMN_A_NAME_1, na1);
        values1.put(Contract.StatTableSave.COLUMN_A_NAME_2, na2);
        values1.put(Contract.StatTableSave.COLUMN_B_NAME_1, nb1);
        values1.put(Contract.StatTableSave.COLUMN_B_NAME_2, nb2);
        values1.put(Contract.StatTableSave.COLUMN_MATCH_STATUS, mSt);
        values1.put(Contract.StatTableSave.COLUMN_DATE, getDate());
        values1.put(Contract.StatTableSave.COLUMN_KTBFPS_st, pa);
        values1.put(Contract.StatTableSave.COLUMN_KTBSPS_st, pb);
        //////////////////////////////////////////////////////////////////
        values1.put(Contract.StatTableSave.COLUMN_SET1A_st, s1a);
        values1.put(Contract.StatTableSave.COLUMN_SET1A_st_tb, s1a_tb);
        values1.put(Contract.StatTableSave.COLUMN_SET1B_st, s1b);
        values1.put(Contract.StatTableSave.COLUMN_SET1B_st_tb, s1b_tb);
        /////////////////////////////////////////////////////////////////
        values1.put(Contract.StatTableSave.COLUMN_SET2A_st, s2a);
        values1.put(Contract.StatTableSave.COLUMN_SET2A_st_tb, s2a_tb);
        values1.put(Contract.StatTableSave.COLUMN_SET2B_st, s2b);
        values1.put(Contract.StatTableSave.COLUMN_SET2B_st_tb, s2b_tb);
        ////////////////////////////////////////////////////////////////
        values1.put(Contract.StatTableSave.COLUMN_SET3A_st, s3a);
        values1.put(Contract.StatTableSave.COLUMN_SET3A_st_tb, s3a_tb);
        values1.put(Contract.StatTableSave.COLUMN_SET3B_st, s3b);
        values1.put(Contract.StatTableSave.COLUMN_SET3B_st_tb, s3b_tb);
        ///////////////////////////////////////////////////////////////
        values1.put(Contract.StatTableSave.COLUMN_SET4A_st, s4a);
        values1.put(Contract.StatTableSave.COLUMN_SET4A_st_tb, s4a_tb);
        values1.put(Contract.StatTableSave.COLUMN_SET4B_st, s4b);
        values1.put(Contract.StatTableSave.COLUMN_SET4B_st_tb, s4b_tb);
        ///////////////////////////////////////////////////////////////
        values1.put(Contract.StatTableSave.COLUMN_SET5A_st, s5a);
        values1.put(Contract.StatTableSave.COLUMN_SET5A_st_tb, s5a_tb);
        values1.put(Contract.StatTableSave.COLUMN_SET5B_st, s5b);
        values1.put(Contract.StatTableSave.COLUMN_SET5B_st_tb, s5b_tb);
        ///////////////////////////////////////////////////////////////
        values1.put(Contract.StatTableSave.COLUMN_TB1PL, bv1);
        values1.put(Contract.StatTableSave.COLUMN_TB2PL, bv2);
        values1.put(Contract.StatTableSave.COLUMN_TIE_BREAK_DONE, bv3);
        values1.put(Contract.StatTableSave.COLUMN_STOP_MATCH_IS_ENABLED, bv4);
        values1.put(Contract.StatTableSave.COLUMN_TB, bv5);
        values1.put(Contract.StatTableSave.COLUMN_1_SET_A_DONE, bv6);
        values1.put(Contract.StatTableSave.COLUMN_1_SET_B_DONE, bv7);
        values1.put(Contract.StatTableSave.COLUMN_2_SET_A_DONE, bv8);
        values1.put(Contract.StatTableSave.COLUMN_2_SET_B_DONE, bv9);
        values1.put(Contract.StatTableSave.COLUMN_3_SET_A_DONE, bv10);
        values1.put(Contract.StatTableSave.COLUMN_3_SET_B_DONE, bv11);
        values1.put(Contract.StatTableSave.COLUMN_4_SET_A_DONE, bv12);
        values1.put(Contract.StatTableSave.COLUMN_4_SET_B_DONE, bv13);
        values1.put(Contract.StatTableSave.COLUMN_1_SET, bv14);
        values1.put(Contract.StatTableSave.COLUMN_2_SET, bv15);
        values1.put(Contract.StatTableSave.COLUMN_3_SET, bv16);
        values1.put(Contract.StatTableSave.COLUMN_4_SET, bv17);
        values1.put(Contract.StatTableSave.COLUMN_5_SET, bv18);
        values1.put(Contract.StatTableSave.COLUMN_1_SET_TB_DONE, bv19);
        values1.put(Contract.StatTableSave.COLUMN_2_SET_TB_DONE, bv20);
        values1.put(Contract.StatTableSave.COLUMN_3_SET_TB_DONE, bv21);
        values1.put(Contract.StatTableSave.COLUMN_4_SET_TB_DONE, bv22);
        values1.put(Contract.StatTableSave.COLUMN_5_SET_TB_DONE, bv23);
        values1.put(Contract.StatTableSave.COLUMN_MATCH_DONE, bv24);

        db.update(Contract.StatTableSave.STATISTIC_SAVE, values1, Contract.StatTableSave._ID + " = ?", new String[]{String.valueOf(selId)});
    }

    public void putName12(String fName, String modeM, String surface, String na1, String na2, String nb1, String nb2,
                          String mSt, String pa, String pb, String s1a, int s1a_tb, String s1b, int s1b_tb, String s2a, int s2a_tb, String s2b, int s2b_tb,
                          String s3a, int s3a_tb, String s3b, int s3b_tb, String s4a, int s4a_tb, String s4b, int s4b_tb,
                          String s5a, int s5a_tb, String s5b, int s5b_tb, int z1, int z2, int z3, int z4, int z5, int z6, int z7, int z8) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values1 = new ContentValues();

        values1.put(Contract.StatTableSave.COLUMN_ADD, 99);
        values1.put(Contract.StatTableSave.COLUMN_A_NAME_SAVE, getStringDataToSaveInStat(fName, z1));
        values1.put(Contract.StatTableSave.COLUMN_B_NAME_SAVE, getStringDataToSaveInStat(fName, z2));
        values1.put(Contract.StatTableSave.COLUMN_A_Winner, getNumberData(z1));
        values1.put(Contract.StatTableSave.COLUMN_B_Winner, getNumberData(z2));
        values1.put(Contract.StatTableSave.COLUMN_A_Ace, getNumberData(z3));
        values1.put(Contract.StatTableSave.COLUMN_B_Ace, getNumberData(z4));
        values1.put(Contract.StatTableSave.COLUMN_A_UE, getNumberData(z5));
        values1.put(Contract.StatTableSave.COLUMN_B_UE, getNumberData(z6));
        values1.put(Contract.StatTableSave.COLUMN_A_DE, getNumberData(z7));
        values1.put(Contract.StatTableSave.COLUMN_B_DE, getNumberData(z8));
        values1.put(Contract.StatTableSave.COLUMN_MM, getStringDataToSaveInStat(modeM, z2));
        values1.put(Contract.StatTableSave.COLUMN_COURT_TYPE, getStringDataToSaveInStat(surface, z2));
        values1.put(Contract.StatTableSave.COLUMN_A_NAME_1, na1);
        values1.put(Contract.StatTableSave.COLUMN_A_NAME_2, na2);
        values1.put(Contract.StatTableSave.COLUMN_B_NAME_1, nb1);
        values1.put(Contract.StatTableSave.COLUMN_B_NAME_2, nb2);
        values1.put(Contract.StatTableSave.COLUMN_MATCH_STATUS, mSt);
        values1.put(Contract.StatTableSave.COLUMN_DATE, getDate());
        values1.put(Contract.StatTableSave.COLUMN_KTBFPS_st, pa);
        values1.put(Contract.StatTableSave.COLUMN_KTBSPS_st, pb);
        //////////////////////////////////////////////////////////////////
        values1.put(Contract.StatTableSave.COLUMN_SET1A_st, s1a);
        values1.put(Contract.StatTableSave.COLUMN_SET1A_st_tb, s1a_tb);
        values1.put(Contract.StatTableSave.COLUMN_SET1B_st, s1b);
        values1.put(Contract.StatTableSave.COLUMN_SET1B_st_tb, s1b_tb);
        /////////////////////////////////////////////////////////////////
        values1.put(Contract.StatTableSave.COLUMN_SET2A_st, s2a);
        values1.put(Contract.StatTableSave.COLUMN_SET2A_st_tb, s2a_tb);
        values1.put(Contract.StatTableSave.COLUMN_SET2B_st, s2b);
        values1.put(Contract.StatTableSave.COLUMN_SET2B_st_tb, s2b_tb);
        ////////////////////////////////////////////////////////////////
        values1.put(Contract.StatTableSave.COLUMN_SET3A_st, s3a);
        values1.put(Contract.StatTableSave.COLUMN_SET3A_st_tb, s3a_tb);
        values1.put(Contract.StatTableSave.COLUMN_SET3B_st, s3b);
        values1.put(Contract.StatTableSave.COLUMN_SET3B_st_tb, s3b_tb);
        ///////////////////////////////////////////////////////////////
        values1.put(Contract.StatTableSave.COLUMN_SET4A_st, s4a);
        values1.put(Contract.StatTableSave.COLUMN_SET4A_st_tb, s4a_tb);
        values1.put(Contract.StatTableSave.COLUMN_SET4B_st, s4b);
        values1.put(Contract.StatTableSave.COLUMN_SET4B_st_tb, s4b_tb);
        ///////////////////////////////////////////////////////////////
        values1.put(Contract.StatTableSave.COLUMN_SET5A_st, s5a);
        values1.put(Contract.StatTableSave.COLUMN_SET5A_st_tb, s5a_tb);
        values1.put(Contract.StatTableSave.COLUMN_SET5B_st, s5b);
        values1.put(Contract.StatTableSave.COLUMN_SET5B_st_tb, s5b_tb);

        db.update(Contract.StatTableSave.STATISTIC_SAVE, values1, Contract.StatTableSave._ID + " = ?", new String[]{String.valueOf(selId)});
    }
    public void putName1(String fName, String modeM, String surface, String na1, String na2, String nb1, String nb2,
                          String mSt, String pa, String pb, String s1a, int s1a_tb, String s1b, int s1b_tb, String s2a, int s2a_tb, String s2b, int s2b_tb,
                          String s3a, int s3a_tb, String s3b, int s3b_tb, String s4a, int s4a_tb, String s4b, int s4b_tb,
                          String s5a, int s5a_tb, String s5b, int s5b_tb, int z1, int z2, int z3, int z4, int z5, int z6, int z7, int z8) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values1 = new ContentValues();

        values1.put(Contract.StatTableSave.COLUMN_ADD, 99);
        values1.put(Contract.StatTableSave.COLUMN_A_NAME_SAVE, getStringDataToSaveInStat(fName, z1));
        values1.put(Contract.StatTableSave.COLUMN_B_NAME_SAVE, getStringDataToSaveInStat(fName, z2));
        values1.put(Contract.StatTableSave.COLUMN_A_Winner, getNumberData(z1));
        values1.put(Contract.StatTableSave.COLUMN_B_Winner, getNumberData(z2));
        values1.put(Contract.StatTableSave.COLUMN_A_Ace, getNumberData(z3));
        values1.put(Contract.StatTableSave.COLUMN_B_Ace, getNumberData(z4));
        values1.put(Contract.StatTableSave.COLUMN_A_UE, getNumberData(z5));
        values1.put(Contract.StatTableSave.COLUMN_B_UE, getNumberData(z6));
        values1.put(Contract.StatTableSave.COLUMN_A_DE, getNumberData(z7));
        values1.put(Contract.StatTableSave.COLUMN_B_DE, getNumberData(z8));
        values1.put(Contract.StatTableSave.COLUMN_MM, getStringDataToSaveInStat(modeM, z2));
        values1.put(Contract.StatTableSave.COLUMN_COURT_TYPE, getStringDataToSaveInStat(surface, z2));
        values1.put(Contract.StatTableSave.COLUMN_A_NAME_1, na1);
        values1.put(Contract.StatTableSave.COLUMN_A_NAME_2, na2);
        values1.put(Contract.StatTableSave.COLUMN_B_NAME_1, nb1);
        values1.put(Contract.StatTableSave.COLUMN_B_NAME_2, nb2);
        values1.put(Contract.StatTableSave.COLUMN_MATCH_STATUS, mSt);
        values1.put(Contract.StatTableSave.COLUMN_DATE, getDate());
        values1.put(Contract.StatTableSave.COLUMN_KTBFPS_st, pa);
        values1.put(Contract.StatTableSave.COLUMN_KTBSPS_st, pb);
        //////////////////////////////////////////////////////////////////
        values1.put(Contract.StatTableSave.COLUMN_SET1A_st, s1a);
        values1.put(Contract.StatTableSave.COLUMN_SET1A_st_tb, s1a_tb);
        values1.put(Contract.StatTableSave.COLUMN_SET1B_st, s1b);
        values1.put(Contract.StatTableSave.COLUMN_SET1B_st_tb, s1b_tb);
        /////////////////////////////////////////////////////////////////
        values1.put(Contract.StatTableSave.COLUMN_SET2A_st, s2a);
        values1.put(Contract.StatTableSave.COLUMN_SET2A_st_tb, s2a_tb);
        values1.put(Contract.StatTableSave.COLUMN_SET2B_st, s2b);
        values1.put(Contract.StatTableSave.COLUMN_SET2B_st_tb, s2b_tb);
        ////////////////////////////////////////////////////////////////
        values1.put(Contract.StatTableSave.COLUMN_SET3A_st, s3a);
        values1.put(Contract.StatTableSave.COLUMN_SET3A_st_tb, s3a_tb);
        values1.put(Contract.StatTableSave.COLUMN_SET3B_st, s3b);
        values1.put(Contract.StatTableSave.COLUMN_SET3B_st_tb, s3b_tb);
        ///////////////////////////////////////////////////////////////
        values1.put(Contract.StatTableSave.COLUMN_SET4A_st, s4a);
        values1.put(Contract.StatTableSave.COLUMN_SET4A_st_tb, s4a_tb);
        values1.put(Contract.StatTableSave.COLUMN_SET4B_st, s4b);
        values1.put(Contract.StatTableSave.COLUMN_SET4B_st_tb, s4b_tb);
        ///////////////////////////////////////////////////////////////
        values1.put(Contract.StatTableSave.COLUMN_SET5A_st, s5a);
        values1.put(Contract.StatTableSave.COLUMN_SET5A_st_tb, s5a_tb);
        values1.put(Contract.StatTableSave.COLUMN_SET5B_st, s5b);
        values1.put(Contract.StatTableSave.COLUMN_SET5B_st_tb, s5b_tb);

        db.update(Contract.StatTableSave.STATISTIC_SAVE, values1, Contract.StatTableSave._ID + " = ?", new String[]{String.valueOf(selId)});
    }


    public void insertKTBFPSandKTBSPS(int i, int i2) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Contract.UndoNumbers.COLUMN_NUM_NUM, getMaxNumNum() + 1);// WARNING!!!!!!
        values.put(Contract.UndoNumbers.COLUMN_KTBFPS, i);
        values.put(Contract.UndoNumbers.COLUMN_KTBSPS, i2);
        long newRowId = db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values);
    }


    public void insert1SetScore(int i, int i2, int i3, int i4) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Contract.UndoNumbers.COLUMN_NUM_NUM, getMaxNumNum() + 1);// WARNING!!!!!!
        values.put(Contract.UndoNumbers.COLUMN_KTBFPS, i);
        values.put(Contract.UndoNumbers.COLUMN_KTBSPS, i2);
        values.put(Contract.UndoNumbers.COLUMN_SET1A, i3);
        values.put(Contract.UndoNumbers.COLUMN_SET1B, i4);

        long newRowId = db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values);
    }

    public void insert3SetKTBScore(int i,int i2,int i3,int i4,int i5,int i6,int i7,int i8) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Contract.UndoNumbers.COLUMN_NUM_NUM, getMaxNumNum() + 1);// WARNING!!!!!!
        values.put(Contract.UndoNumbers.COLUMN_KTBFPS, i);
        values.put(Contract.UndoNumbers.COLUMN_KTBSPS, i2);
        values.put(Contract.UndoNumbers.COLUMN_SET1A, i3);
        values.put(Contract.UndoNumbers.COLUMN_SET1B, i4);
        values.put(Contract.UndoNumbers.COLUMN_SET2A, i5);
        values.put(Contract.UndoNumbers.COLUMN_SET2B, i6);
        values.put(Contract.UndoNumbers.COLUMN_SET3A, i7);
        values.put(Contract.UndoNumbers.COLUMN_SET3B, i8);

        long newRowId = db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values);
    }

    public void insert5SetUSScore(int i,int i2,int i3,int i4,int i5,int i6,int i7,int i8,int i9,int i10,int i11,int i12) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Contract.UndoNumbers.COLUMN_NUM_NUM, getMaxNumNum() + 1);// WARNING!!!!!!
        values.put(Contract.UndoNumbers.COLUMN_KTBFPS, i);
        values.put(Contract.UndoNumbers.COLUMN_KTBSPS, i2);
        values.put(Contract.UndoNumbers.COLUMN_SET1A, i3);
        values.put(Contract.UndoNumbers.COLUMN_SET1B, i4);
        values.put(Contract.UndoNumbers.COLUMN_SET2A, i5);
        values.put(Contract.UndoNumbers.COLUMN_SET2B, i6);
        values.put(Contract.UndoNumbers.COLUMN_SET3A, i7);
        values.put(Contract.UndoNumbers.COLUMN_SET3B, i8);
        values.put(Contract.UndoNumbers.COLUMN_SET4A, i9);
        values.put(Contract.UndoNumbers.COLUMN_SET4B, i10);
        values.put(Contract.UndoNumbers.COLUMN_SET5A, i11);
        values.put(Contract.UndoNumbers.COLUMN_SET5B, i12);

        long newRowId = db.insert(Contract.UndoNumbers.TABLE_NAME_1, null, values);
    }

    public void insertPlayers(String n1, String n2, String n3, String n4) {

        int nomer[] = {getNumById("A_winner", selId), getNumById("A_ace", selId), getNumById("A_ue", selId), getNumById("A_de", selId)};

        for (int p = 0; p < nomer.length; p++) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(Contract.ContractNames.COLUMN_NAME, n1 + " " + n2);
            values.put(Contract.ContractNames.COLUMN_NUMBER, nomer[p]);
            values.put(Contract.ContractNames.COLUMN_DAY, currDay());
            values.put(Contract.ContractNames.COLUMN_MONTH, currMon());
            values.put(Contract.ContractNames.COLUMN_YEAR, currYear());
            values.put(Contract.ContractNames.COLUMN_MATCH_MODE, getNumById("_match_mode", selId));
            values.put(Contract.ContractNames.COLUMN_COURT_SURFACE, getNumById("_court_type", selId));
            values.put(Contract.ContractNames.COLUMN_MATCH_NUMBER, 0);
            long newRowId = db.insert(Contract.ContractNames.TABLE_NAME, null, values);
        }

        int nomer2[] = {getNumById("B_winner", selId), getNumById("B_ace", selId), getNumById("B_ue", selId), getNumById("B_de", selId)};

        for (int p = 0; p < nomer2.length; p++) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(Contract.ContractNames.COLUMN_NAME, n3 + " " + n4);
            values.put(Contract.ContractNames.COLUMN_NUMBER, nomer2[p]);
            values.put(Contract.ContractNames.COLUMN_DAY, currDay());
            values.put(Contract.ContractNames.COLUMN_MONTH, currMon());
            values.put(Contract.ContractNames.COLUMN_YEAR, currYear());
            values.put(Contract.ContractNames.COLUMN_MATCH_MODE, getNumById("_match_mode", selId));
            values.put(Contract.ContractNames.COLUMN_COURT_SURFACE, getNumById("_court_type", selId));
            values.put(Contract.ContractNames.COLUMN_MATCH_NUMBER, 0);
            long newRowId = db.insert(Contract.ContractNames.TABLE_NAME, null, values);
        }
    }

    public String getStringFromNumDataFromUndoTable(String jg, int uC) {

        String prevKTBFPSNum;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT " + jg + " FROM "
                + Contract.UndoNumbers.TABLE_NAME_1 + " WHERE " + Contract.UndoNumbers.COLUMN_NUM_NUM +
                " = (SELECT MAX( " + Contract.UndoNumbers.COLUMN_NUM_NUM + " ) FROM " + Contract.UndoNumbers.TABLE_NAME_1 + " ) - " + uC, new String[]{});
        cursor.moveToFirst();
        prevKTBFPSNum = cursor.getString(0);
        cursor.close();
        return String.valueOf(prevKTBFPSNum);
    }

    public int getNumOfEmptyRows() {
        int i;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT COUNT (ADD_save) FROM statistic_SAVE WHERE ADD_save = -2  ", new String[]{});
        cursor.moveToFirst();
        i = (int) cursor.getLong(0);
        cursor.close();
        return i;
    }

    public int getMaxNumNum() {

        int maxNumNum;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT MAX( " + Contract.UndoNumbers.COLUMN_NUM_NUM + " ) FROM " + Contract.UndoNumbers.TABLE_NAME_1, new String[]{});
        cursor.moveToFirst();
        maxNumNum = cursor.getInt(0);
        cursor.close();
        return maxNumNum;
    }

    public int getIDfromName(String st1, String st2) {

        int name;
        // int nj = 0;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(" SELECT _ID FROM statistic_SAVE  WHERE  full_A_NameSAVE = '" + st1 + "' AND full_B_NameSAVE = '" + st2 + "'", new String[]{});
        cursor.moveToFirst();
      /*  while (cursor.moveToNext()) {
            name = (int) cursor.getLong(0);
            nj += name;
        }*/

        name = (int) cursor.getLong(0);

        cursor.close();

        return name;

    }

    public int getNumDataFromUTable(String st, int y) {

        int name;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT " + st + " FROM undoTable  WHERE  _ID =" + (getLastId() - y), new String[]{});
        cursor.moveToFirst();
        name = cursor.getInt(0);
        cursor.close();
        return name;

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}

