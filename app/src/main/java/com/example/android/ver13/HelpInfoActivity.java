package com.example.android.ver13;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HelpInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_info);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.Help));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
