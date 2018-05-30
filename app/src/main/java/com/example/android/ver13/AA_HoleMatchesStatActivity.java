package com.example.android.ver13;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



public class AA_HoleMatchesStatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aa_fragment_cat_for_two_pl);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_for_two, new AA_HoleMatchesStatFragment())
                .commit();
    }
}
