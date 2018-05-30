package com.example.android.ver13;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AA_GraphicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aa_fragment_categoru);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new AA_GraphicFragment())
                .commit();
    }
}
