package com.example.android.ver13;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AA_AboutAppActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aa_activity_about_app);

        String APP_VERSION_NUMBER = "1.00";

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.about));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        TextView versionTV = (TextView)findViewById(R.id._versionTV);
        versionTV.setText(getString(R.string.version)+ " "+ APP_VERSION_NUMBER);

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    public void writeToDev(View view){
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"andrejtimo6enko@gmail.com"});
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }
}
