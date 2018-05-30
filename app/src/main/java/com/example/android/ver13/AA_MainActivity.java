package com.example.android.ver13;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;



public class AA_MainActivity extends AppCompatActivity {



    Context context;
    private static final String TAG = "AA_MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aa_activity_main);

        context = AA_MainActivity.this;

        Log.d(TAG, "onCreate");

        Button newMatchButton = (Button) findViewById(R.id.newMatchButton);
        Button dataBaseButton = (Button) findViewById(R.id.dataBaseButton);
        Button helpButton = (Button) findViewById(R.id.helpButton);
        Button aboutButton = (Button) findViewById(R.id.aboutButton);

        newMatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newMatchIntent = new Intent(AA_MainActivity.this, AA_NewMatchActivity.class);
                startActivity(newMatchIntent);
                finish();

            }
        });
        dataBaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dataBaseIntent = new Intent(AA_MainActivity.this, AA_SearchActivity.class);
                startActivity(dataBaseIntent);
                finish();

            }
        });

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent helpIntent = new Intent(AA_MainActivity.this, AA_TrueHelpActivity.class);
                startActivity(helpIntent);

            }
        });

        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aboutIntent = new Intent(AA_MainActivity.this, AA_AboutAppActivity.class);
                startActivity(aboutIntent);

            }
        });


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
        Log.d(TAG, "onDestroy: Called");
    }
}
