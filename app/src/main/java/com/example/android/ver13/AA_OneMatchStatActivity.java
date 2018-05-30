package com.example.android.ver13;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class AA_OneMatchStatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.aa_activity_new);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.Statistic));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        AA_SimpleViewPagerAdapter adapter = new AA_SimpleViewPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

    }



    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
