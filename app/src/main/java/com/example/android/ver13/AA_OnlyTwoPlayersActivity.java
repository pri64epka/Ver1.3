package com.example.android.ver13;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AA_OnlyTwoPlayersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aa_activity_only_two_players);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.Overall_statistic));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        ViewPager viewPager2p = (ViewPager) findViewById(R.id.viewpager_2p);
        AA_OnlyTwoPlayersViewPagerAdapter adapter2p = new AA_OnlyTwoPlayersViewPagerAdapter(this, getSupportFragmentManager());
        viewPager2p.setAdapter(adapter2p);

        TabLayout tabLayout2p = (TabLayout) findViewById(R.id.sliding_tabs_2p);
        tabLayout2p.setupWithViewPager(viewPager2p);

    }



    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
