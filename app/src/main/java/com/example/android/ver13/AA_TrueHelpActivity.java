package com.example.android.ver13;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class AA_TrueHelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aa_activity_true_help);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.Help));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void showHelp(View view){
        Intent intent = new Intent(this,HelpActivity.class);
        startActivity(intent);
    }
}
