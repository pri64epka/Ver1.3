package com.example.android.ver13;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.android.ver13.data.TennisHelper;

import java.util.ArrayList;


public class AA_SearchActivity extends AppCompatActivity {

    private TennisHelper mDbHelperSearch;

    AutoCompleteTextView mAutoCompleteTextView1;
    AutoCompleteTextView mAutoCompleteTextView2;
    private ArrayAdapter<String> mAutoCompleteAdapter1;
    private ArrayAdapter<String> mAutoCompleteAdapter2;
    private CheckBox hardCheckBox, clayCheckBox, grassCheckBox, inhardCheckbox, rubberChecBox, ktbCheckBox, set1CheckBox,
            set3ktbCheckBox, set3usCheckBox, set3CheckBox, set5usCheckBox, set5CheckBox;
    Button searchMatchBtn;
    BroadcastReceiver broadcast_reciever;
    Context context;

    private static final String TAG = "AA_SearchActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aa_activity_search);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        context = AA_SearchActivity.this;

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.Search));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        broadcast_reciever = new BroadcastReceiver() {

            @Override
            public void onReceive(Context arg0, Intent intent) {
                String action = intent.getAction();
                if (action.equals("finish")) {

                    finish();
                }
            }
        };

        context.registerReceiver(broadcast_reciever, new IntentFilter("finish"));


        searchMatchBtn = (Button) findViewById(R.id.SearchInDBbutton);
        searchMatchBtn.setText(R.string.Search);

        hardCheckBox = (CheckBox) findViewById(R.id.checkBox2);
        hardCheckBox.setText(R.string.hard_type);

        clayCheckBox = (CheckBox) findViewById(R.id.checkBox4);
        clayCheckBox.setText(R.string.clay_type);

        grassCheckBox = (CheckBox) findViewById(R.id.checkBox6);
        grassCheckBox.setText(R.string.grass_type);

        inhardCheckbox = (CheckBox) findViewById(R.id.checkBox8);
        inhardCheckbox.setText(R.string.iHard_type);

        rubberChecBox = (CheckBox) findViewById(R.id.checkBox10);
        rubberChecBox.setText(R.string.rubber_type);

        ktbCheckBox = (CheckBox) findViewById(R.id.checkBox);
        ktbCheckBox.setText(R.string.KTB);
//////////////////////////////////////////////////////////////////////
        set1CheckBox = (CheckBox) findViewById(R.id.checkBox3);
        set1CheckBox.setText(R.string.one_set);

        set3ktbCheckBox = (CheckBox) findViewById(R.id.checkBox5);
        set3ktbCheckBox.setText(R.string.three_sets_KTB);

        set3usCheckBox = (CheckBox) findViewById(R.id.checkBox7);
        set3usCheckBox.setText(R.string.three_sets_US);

        set3CheckBox = (CheckBox) findViewById(R.id.checkBox9);
        set3CheckBox.setText(R.string.three_sets);

        set5usCheckBox = (CheckBox) findViewById(R.id.checkBox11);
        set5usCheckBox.setText(R.string.five_sets_US);

        set5CheckBox = (CheckBox) findViewById(R.id.checkBox13);
        set5CheckBox.setText(R.string.five_sets);


        mDbHelperSearch = new TennisHelper(this);

        mAutoCompleteTextView1 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        mAutoCompleteTextView2 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);

        mAutoCompleteTextView1.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

                if (mAutoCompleteTextView1.getText().toString().length() > 0) {
                    mAutoCompleteTextView2.setEnabled(true);

                } else {
                    mAutoCompleteTextView2.setText("");
                    mAutoCompleteTextView2.setEnabled(false);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

        });

        mAutoCompleteTextView2.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

        });

        mAutoCompleteAdapter1 = new ArrayAdapter<>(AA_SearchActivity.this, android.R.layout.simple_dropdown_item_1line, mDbHelperSearch.getDistinctNames());

        mAutoCompleteTextView1.setAdapter(mAutoCompleteAdapter1);

        mAutoCompleteAdapter2 = new ArrayAdapter<>(AA_SearchActivity.this, android.R.layout.simple_dropdown_item_1line, mDbHelperSearch.getDistinctNames());

        mAutoCompleteTextView2.setAdapter(mAutoCompleteAdapter2);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void onBackPressed() {
        finish();
        Intent i = new Intent(this, AA_MainActivity.class);
        startActivity(i);
    }

    public void search_DB(View view) {

        Intent intent = new Intent(this, SearchedMatchsActivity.class);

        String s1 = mAutoCompleteTextView1.getText().toString().trim();

        if (s1.equals("")) {

            if ((getNumSelCheckboxes() == 0 | getNumSelCheckboxes() == 5) & (getNumSelCheckboxesMM() == 0 | getNumSelCheckboxesMM() == 7)) {
                Toast.makeText(getBaseContext(), getString(R.string.please_specify_request), Toast.LENGTH_SHORT).show();

            } else if (getNumSelCheckboxes() == 0 | getNumSelCheckboxes() == 5) {

                if (listByMM().isEmpty()) {
                    Toast.makeText(getBaseContext(), getString(R.string.no_matches_with_sp), Toast.LENGTH_SHORT).show();

                } else {

                    intent.putExtra("user1", listByMM());
                    startActivity(intent);
                }
            } else if (getNumSelCheckboxesMM() == 0 | getNumSelCheckboxesMM() == 7) {
                if (listBySurface().isEmpty()) {
                    Toast.makeText(getBaseContext(), getString(R.string.no_matches_with_sp), Toast.LENGTH_SHORT).show();
                } else {
                    intent.putExtra("user1", listBySurface());
                    startActivity(intent);
                }
            } else {
                if (isItTrue(listBySurface(), listByMM()).isEmpty()) {
                    Toast.makeText(getBaseContext(),getString(R.string.no_matches_with_sp), Toast.LENGTH_SHORT).show();
                } else {
                    intent.putExtra("user1", isItTrue(listBySurface(), listByMM()));
                    startActivity(intent);
                }
            }
        } else {
            if ((getNumSelCheckboxes() == 0 | getNumSelCheckboxes() == 5) & (getNumSelCheckboxesMM() == 0 | getNumSelCheckboxesMM() == 7)) {

                if (listByNames().isEmpty()) {

                    Toast.makeText(getBaseContext(),getString(R.string.no_matches_with_sp), Toast.LENGTH_SHORT).show();
                    
                } else {
                    intent.putExtra("user1", listByNames());
                    startActivity(intent);
                }
            } else if (getNumSelCheckboxes() == 0 | getNumSelCheckboxes() == 5) {
                if (isItTrue(listByNames(), listByMM()).isEmpty()) {
                    Toast.makeText(getBaseContext(),getString(R.string.no_matches_with_sp), Toast.LENGTH_SHORT).show();
                } else {
                    intent.putExtra("user1", isItTrue(listByNames(), listByMM()));
                    startActivity(intent);
                }
            } else if (getNumSelCheckboxesMM() == 0 | getNumSelCheckboxesMM() == 7) {
                if (isItTrue(listByNames(), listBySurface()).isEmpty()) {
                    Toast.makeText(getBaseContext(),getString(R.string.no_matches_with_sp), Toast.LENGTH_SHORT).show();
                } else {
                    intent.putExtra("user1", isItTrue(listByNames(), listBySurface()));
                    startActivity(intent);
                }
            } else {
                if (listIdBy3Conditions(listByNames(), listBySurface(), listByMM()).isEmpty()) {
                    Toast.makeText(getBaseContext(), getString(R.string.no_matches_with_sp), Toast.LENGTH_SHORT).show();
                } else {
                    intent.putExtra("user1", listIdBy3Conditions(listByNames(), listBySurface(), listByMM()));
                    startActivity(intent);
                }
            }
        }
    }

    public ArrayList<Integer> listByNames() {

        ArrayList<Integer> num = new ArrayList<>();

        String s1 = mAutoCompleteTextView1.getText().toString().trim();
        String s2 = mAutoCompleteTextView2.getText().toString().trim();

        if (s2.equals("")) {
            for (int y = 0; y < mDbHelperSearch.getListNumId1(s1).size(); y++) {
                num.add(mDbHelperSearch.getListNumId1(s1).get(y));
            }
        } else {
            for (int y = 0; y < mDbHelperSearch.getListNumId(s1, s2).size(); y++) {
                num.add(mDbHelperSearch.getListNumId(s1, s2).get(y));
            }
        }
        return num;
    }

    public int getNumSelCheckboxes() {

        int y = 0;
        if (hardCheckBox.isChecked()) {
            y++;
        }
        if (clayCheckBox.isChecked()) {
            y++;
        }
        if (grassCheckBox.isChecked()) {
            y++;
        }
        if (inhardCheckbox.isChecked()) {
            y++;
        }
        if (rubberChecBox.isChecked()) {
            y++;
        }
        return y;
    }

    public ArrayList<String> getValueSelCheckboxes() {

        ArrayList<String> values = new ArrayList<>();

        if (hardCheckBox.isChecked()) {
            values.add("11");
        }
        if (clayCheckBox.isChecked()) {
            values.add("12");
        }
        if (grassCheckBox.isChecked()) {
            values.add("13");
        }
        if (inhardCheckbox.isChecked()) {
            values.add("14");
        }
        if (rubberChecBox.isChecked()) {
            values.add("15");
        }
        return values;
    }

    public ArrayList<Integer> listBySurface() {

        ArrayList<Integer> n = new ArrayList<>();

        switch (getNumSelCheckboxes()) {

            case 1:
                for (int i = 0; i < mDbHelperSearch.getIdBy1Surface(getValueSelCheckboxes().get(0)).size(); i++) {
                    n.add(mDbHelperSearch.getIdBy1Surface(getValueSelCheckboxes().get(0)).get(i));
                }
                break;
            case 2:
                for (int i = 0; i < mDbHelperSearch.getIdBy2Surface(getValueSelCheckboxes().get(0), getValueSelCheckboxes().get(1)).size(); i++) {
                    n.add(mDbHelperSearch.getIdBy2Surface(getValueSelCheckboxes().get(0), getValueSelCheckboxes().get(1)).get(i));
                }
                break;
            case 3:
                for (int i = 0; i < mDbHelperSearch.getIdBy3Surface(getValueSelCheckboxes().get(0), getValueSelCheckboxes().get(1), getValueSelCheckboxes().get(2)).size(); i++) {
                    n.add(mDbHelperSearch.getIdBy3Surface(getValueSelCheckboxes().get(0), getValueSelCheckboxes().get(1), getValueSelCheckboxes().get(2)).get(i));
                }
                break;
            case 4:
                for (int i = 0; i < mDbHelperSearch.getIdBy4Surface(getValueSelCheckboxes().get(0), getValueSelCheckboxes().get(1), getValueSelCheckboxes().get(2), getValueSelCheckboxes().get(3)).size(); i++) {
                    n.add(mDbHelperSearch.getIdBy4Surface(getValueSelCheckboxes().get(0), getValueSelCheckboxes().get(1), getValueSelCheckboxes().get(2), getValueSelCheckboxes().get(3)).get(i));
                }
                break;
        }
        return n;
    }

    public ArrayList<Integer> isItTrue(ArrayList<Integer> i1, ArrayList<Integer> i2) {

        ArrayList<Integer> o = new ArrayList<>();

        if (i1.size() >= i2.size()) {
            for (int t = 0; t < i2.size(); t++) {
                if (i1.contains(i2.get(t))) {
                    o.add(i2.get(t));
                }
            }
        } else if (i1.size() < i2.size()) {

            for (int t = 0; t < i1.size(); t++) {
                if (i2.contains(i1.get(t))) {
                    o.add(i1.get(t));
                }
            }
        }
        return o;
    }

    public ArrayList<Integer> listIdBy3Conditions(ArrayList<Integer> a1, ArrayList<Integer> a2, ArrayList<Integer> a3) {

        ArrayList<Integer> semi = new ArrayList<>();
        ArrayList<Integer> full = new ArrayList<>();

        if ((a1.size() >= a2.size()) & (a2.size() >= a3.size())) {

            for (int i = 0; i < a3.size(); i++) {
                if (a2.contains(a3.get(i))) {
                    semi.add(a3.get(i));
                }
            }
            for (int i = 0; i < semi.size(); i++) {
                if (a1.contains(semi.get(i))) {
                    full.add(semi.get(i));
                }
            }
        } else if ((a1.size() >= a2.size()) & (a2.size() <= a3.size()) & (a1.size() >= a3.size())) {

            for (int i = 0; i < a2.size(); i++) {
                if (a3.contains(a2.get(i))) {
                    semi.add(a2.get(i));
                }
            }
            for (int i = 0; i < semi.size(); i++) {
                if (a1.contains(semi.get(i))) {
                    full.add(semi.get(i));
                }
            }
        } else if ((a1.size() >= a2.size()) & (a2.size() <= a3.size()) & (a1.size() <= a3.size())) {

            for (int i = 0; i < a2.size(); i++) {
                if (a1.contains(a2.get(i))) {
                    semi.add(a2.get(i));
                }
            }
            for (int i = 0; i < semi.size(); i++) {
                if (a3.contains(semi.get(i))) {
                    full.add(semi.get(i));
                }
            }
        } else if ((a1.size() <= a2.size()) & (a2.size() <= a3.size())) {

            for (int i = 0; i < a1.size(); i++) {
                if (a2.contains(a1.get(i))) {
                    semi.add(a1.get(i));
                }
            }
            for (int i = 0; i < semi.size(); i++) {
                if (a3.contains(semi.get(i))) {
                    full.add(semi.get(i));
                }
            }
        } else if ((a1.size() <= a2.size()) & (a2.size() >= a3.size()) & (a1.size() <= a3.size())) {

            for (int i = 0; i < a1.size(); i++) {
                if (a3.contains(a1.get(i))) {
                    semi.add(a1.get(i));
                }
            }
            for (int i = 0; i < semi.size(); i++) {
                if (a2.contains(semi.get(i))) {
                    full.add(semi.get(i));
                }
            }
        } else if ((a1.size() <= a2.size()) & (a2.size() >= a3.size()) & (a1.size() >= a3.size())) {

            for (int i = 0; i < a3.size(); i++) {
                if (a1.contains(a3.get(i))) {
                    semi.add(a3.get(i));
                }
            }
            for (int i = 0; i < semi.size(); i++) {
                if (a2.contains(semi.get(i))) {
                    full.add(semi.get(i));
                }
            }
        }
        return full;

    }

    public ArrayList<String> getValueSelCheckboxesMM() {

        ArrayList<String> values = new ArrayList<>();

        if (ktbCheckBox.isChecked()) {
            values.add("0");
        }
        if (set1CheckBox.isChecked()) {
            values.add("1");
        }
        if (set3ktbCheckBox.isChecked()) {
            values.add("2");
        }
        if (set3usCheckBox.isChecked()) {
            values.add("3");
        }
        if (set3CheckBox.isChecked()) {
            values.add("4");

        }
        if (set5usCheckBox.isChecked()) {
            values.add("5");

        }
        if (set5CheckBox.isChecked()) {
            values.add("6");
        }
        return values;
    }

    public int getNumSelCheckboxesMM() {

        int y = 0;
        if (ktbCheckBox.isChecked()) {
            y++;
        }
        if (set1CheckBox.isChecked()) {
            y++;
        }
        if (set3ktbCheckBox.isChecked()) {
            y++;
        }
        if (set3usCheckBox.isChecked()) {
            y++;
        }
        if (set3CheckBox.isChecked()) {
            y++;
        }
        if (set5usCheckBox.isChecked()) {
            y++;
        }
        if (set5CheckBox.isChecked()) {
            y++;
        }
        return y;
    }

    public ArrayList<Integer> listByMM() {

        ArrayList<Integer> n = new ArrayList<>();

        switch (getNumSelCheckboxesMM()) {

            case 0:///Maybe here must be break

                break;
            case 1:
                for (int i = 0; i < mDbHelperSearch.getIdBy1MM(getValueSelCheckboxesMM().get(0)).size(); i++) {
                    n.add(mDbHelperSearch.getIdBy1MM(getValueSelCheckboxesMM().get(0)).get(i));
                }
                break;
            case 2:
                for (int i = 0; i < mDbHelperSearch.getIdBy2MM(getValueSelCheckboxesMM().get(0), getValueSelCheckboxesMM().get(1)).size(); i++) {
                    n.add(mDbHelperSearch.getIdBy2MM(getValueSelCheckboxesMM().get(0), getValueSelCheckboxesMM().get(1)).get(i));
                }
                break;
            case 3:
                for (int i = 0; i < mDbHelperSearch.getIdBy3MM(getValueSelCheckboxesMM().get(0), getValueSelCheckboxesMM().get(1), getValueSelCheckboxesMM().get(2)).size(); i++) {
                    n.add(mDbHelperSearch.getIdBy3MM(getValueSelCheckboxesMM().get(0), getValueSelCheckboxesMM().get(1), getValueSelCheckboxesMM().get(2)).get(i));
                }
                break;
            case 4:
                for (int i = 0; i < mDbHelperSearch.getIdBy4MM(getValueSelCheckboxesMM().get(0), getValueSelCheckboxesMM().get(1), getValueSelCheckboxesMM().get(2), getValueSelCheckboxesMM().get(3)).size(); i++) {
                    n.add(mDbHelperSearch.getIdBy4MM(getValueSelCheckboxesMM().get(0), getValueSelCheckboxesMM().get(1), getValueSelCheckboxesMM().get(2), getValueSelCheckboxesMM().get(3)).get(i));
                }
                break;
            case 5:
                for (int i = 0; i < mDbHelperSearch.getIdBy5MM(getValueSelCheckboxesMM().get(0), getValueSelCheckboxesMM().get(1), getValueSelCheckboxesMM().get(2), getValueSelCheckboxesMM().get(3), getValueSelCheckboxesMM().get(4)).size(); i++) {
                    n.add(mDbHelperSearch.getIdBy5MM(getValueSelCheckboxesMM().get(0), getValueSelCheckboxesMM().get(1), getValueSelCheckboxesMM().get(2), getValueSelCheckboxesMM().get(3), getValueSelCheckboxesMM().get(4)).get(i));
                }
                break;
            case 6:
                for (int i = 0; i < mDbHelperSearch.getIdBy6MM(getValueSelCheckboxesMM().get(0), getValueSelCheckboxesMM().get(1), getValueSelCheckboxesMM().get(2), getValueSelCheckboxesMM().get(3), getValueSelCheckboxesMM().get(4), getValueSelCheckboxesMM().get(5)).size(); i++) {
                    n.add(mDbHelperSearch.getIdBy6MM(getValueSelCheckboxesMM().get(0), getValueSelCheckboxesMM().get(1), getValueSelCheckboxesMM().get(2), getValueSelCheckboxesMM().get(3), getValueSelCheckboxesMM().get(4), getValueSelCheckboxesMM().get(5)).get(i));
                }
                break;
        }
        return n;
    }



    @Override
    public void onStart() {
        super.onStart();

        Log.d(TAG, "onStart: Called");
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: Called");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: Called");
    }
    @Override
    public void onStop() {
        super.onStop();

        Log.d(TAG, "onStop: Called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcast_reciever);
        Log.d(TAG, "onDestroy: Called");
    }

}
