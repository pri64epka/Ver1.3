package com.example.android.ver13;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.ver13.data.Contract;
import com.example.android.ver13.data.TennisHelper;

import java.util.Calendar;

public class AA_NewMatchActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static String[] goalType = new String[4];

    String[] matchMode_array = new String[7];

    String[] full_description = new String[7];

    //   long matchNumberB = 0;
    private TennisHelper mDbHelper;
    public static boolean isNew;

    EditText firstPlayer, firstPlayerSurname, secondPlayer, secondPlayerSurname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aa_activity_new_match);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        isNew = true;

        setMatchModeSpinner();

        firstPlayer = (EditText) findViewById(R.id.firstPlayerET);
        firstPlayerSurname = (EditText) findViewById(R.id.firstPlayerSurnameET);
        secondPlayer = (EditText) findViewById(R.id.secondPlayerET);
        secondPlayerSurname = (EditText) findViewById(R.id.secondPlayerSurnameET);

        matchMode_array[0] = getResources().getString(R.string.King_tie_break);
        matchMode_array[1] = getResources().getString(R.string.one_set);
        matchMode_array[2] = getResources().getString(R.string.three_sets_KTB);
        matchMode_array[3] = getResources().getString(R.string.three_sets_US);
        matchMode_array[4] = getResources().getString(R.string.three_sets);
        matchMode_array[5] = getResources().getString(R.string.five_sets_US);
        matchMode_array[6] = getResources().getString(R.string.five_sets);

        full_description[0] = getResources().getString(R.string.match_desc_KTB);
        full_description[1] = getResources().getString(R.string.match_desc_1_set);
        full_description[2] = getResources().getString(R.string.match_desc_3_set_KTB);
        full_description[3] = getResources().getString(R.string.match_desc_3_set_US);
        full_description[4] = getResources().getString(R.string.match_desc_3_set);
        full_description[5] = getResources().getString(R.string.match_desc_5_set_US);
        full_description[6] = getResources().getString(R.string.match_desc_5_set);

        goalType[0] = getResources().getString(R.string.Winner);
        goalType[1] = getResources().getString(R.string.Ace);
        goalType[2] = getResources().getString(R.string.Unenforced_error);
        goalType[3] = getResources().getString(R.string.Double_error);

        mDbHelper = new TennisHelper(this);
    }

    public void setMatchModeSpinner() {
        Spinner spinnerMM = (Spinner) findViewById(R.id.SpinnerMatchMode);
        spinnerMM.setOnItemSelectedListener(this);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), matchMode_array, full_description);
        spinnerMM.setAdapter(customAdapter);
    }

    public void startButton(View view) {

        if (firstPlayer.getText().toString().trim().matches("")) {
            Toast.makeText(this, R.string.toast_1_pl, Toast.LENGTH_SHORT).show();
            return;
        }
        if (secondPlayer.getText().toString().trim().matches("")) {
            Toast.makeText(this, R.string.toast_2_pl, Toast.LENGTH_SHORT).show();
            return;
        }

        insertFirstSecondPlayer(firstPlayer, firstPlayerSurname);
        insertFirstSecondPlayer(secondPlayer, secondPlayerSurname);

        switch (getMatchMode()) {
            case 0:
                mDbHelper.insertKTBFPSandKTBSPS(0, 0);
                Intent intent = new Intent(this, AA_KingTieBreakActivity.class);
                intentPut(intent);
                startActivity(intent);
                break;
            case 1:
                mDbHelper.insert1SetScore(0, 0, 0, 0);
                Intent intent1 = new Intent(this, AA_Set1Activity.class);
                intentPut(intent1);
                startActivity(intent1);
                break;
            case 2:
                mDbHelper.insert3SetKTBScore(0, 0, 0, 0, 0, 0, 0, 0);
                Intent intent2 = new Intent(this, AA_Set3KTBActivity.class);
                intentPut(intent2);
                startActivity(intent2);
                break;
            case 3:
                mDbHelper.insert3SetKTBScore(0, 0, 0, 0, 0, 0, 0, 0);
                Intent intent3 = new Intent(this, AA_Set3USActivity.class);
                intentPut(intent3);
                startActivity(intent3);
                break;
            case 4:
                mDbHelper.insert3SetKTBScore(0, 0, 0, 0, 0, 0, 0, 0);
                Intent intent4 = new Intent(this, AA_Set3Activity.class);
                intentPut(intent4);
                startActivity(intent4);
                break;
            case 5:
                mDbHelper.insert5SetUSScore(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
                Intent intent5 = new Intent(this, AA_Set5USActivity.class);
                intentPut(intent5);
                startActivity(intent5);
                break;
            case 6:
                mDbHelper.insert5SetUSScore(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
                Intent intent6 = new Intent(this, AA_Set5Activity.class);
                intentPut(intent6);
                startActivity(intent6);
                break;
        }
        finish();

    }

    public static int currDay() {
        Calendar rightNow = Calendar.getInstance();
        return rightNow.get(Calendar.DAY_OF_MONTH);
    }

    public static int currMon() {
        Calendar rightNow = Calendar.getInstance();
        return rightNow.get(Calendar.MONTH) + 1;

    }

    public static int currYear() {
        Calendar rightNow = Calendar.getInstance();
        return rightNow.get(Calendar.YEAR);
    }

    public int getIntCourtSurface() {

        int numOfCourtSurface = 0;
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.RGroup);
        int selectedId = radiogroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(selectedId);

        if (radioButton.getText().toString().equals(getString(R.string.hard_type))) {
            numOfCourtSurface = 11;
        } else if (radioButton.getText().toString().equals(getString(R.string.clay_type))) {
            numOfCourtSurface = 12;
        } else if (radioButton.getText().toString().equals(getString(R.string.grass_type))) {
            numOfCourtSurface = 13;
        } else if (radioButton.getText().toString().equals(getString(R.string.iHard_type))) {
            numOfCourtSurface = 14;
        } else if (radioButton.getText().toString().equals(getString(R.string.rubber_type))) {
            numOfCourtSurface = 15;
        }

        return numOfCourtSurface;
    }


    public int getMatchMode() {
        Spinner spinnerMM = (Spinner) findViewById(R.id.SpinnerMatchMode);
        spinnerMM.getSelectedItemPosition();
        return spinnerMM.getSelectedItemPosition();
    }

    public void insertFirstSecondPlayer(EditText et1, EditText et2) {

        for (String s : goalType) {
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(Contract.ContractNames.COLUMN_NAME, et1.getText().toString().trim() + " " + et2.getText().toString().trim());
            values.put(Contract.ContractNames.COLUMN_TYPE, s);
            values.put(Contract.ContractNames.COLUMN_DAY, currDay());
            values.put(Contract.ContractNames.COLUMN_MONTH, currMon());
            values.put(Contract.ContractNames.COLUMN_YEAR, currYear());
            values.put(Contract.ContractNames.COLUMN_MATCH_MODE, String.valueOf(getMatchMode()));
            values.put(Contract.ContractNames.COLUMN_COURT_SURFACE, String.valueOf(getIntCourtSurface()));
            values.put(Contract.ContractNames.COLUMN_MATCH_NUMBER, 0);
            long newRowId = db.insert(Contract.ContractNames.TABLE_NAME, null, values);
        }
    }

    public void intentPut(Intent i) {
        i.putExtra("user", firstPlayer.getText().toString().trim());
        i.putExtra("userSur", firstPlayerSurname.getText().toString().trim());
        i.putExtra("der", secondPlayer.getText().toString().trim());
        i.putExtra("derSur", secondPlayerSurname.getText().toString().trim());
        i.putExtra("isNew", isNew);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void onBackPressed() {
        finish();
        Intent i = new Intent(this, AA_MainActivity.class);
        startActivity(i);
    }
}


