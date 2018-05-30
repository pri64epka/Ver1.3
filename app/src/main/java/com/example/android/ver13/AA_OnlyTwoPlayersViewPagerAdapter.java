package com.example.android.ver13;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;



public class AA_OnlyTwoPlayersViewPagerAdapter extends FragmentPagerAdapter {

    private Context vContext;

    public AA_OnlyTwoPlayersViewPagerAdapter(Context context, FragmentManager fm) {

        super(fm);
        vContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0) {

            return new AA_HoleMatchesStatFragment();

        } else if (position == 1) {

            return new AA_OverallChartsFragment();

        } else if (position == 2) {

            return new AA_Hints421Fragment();

        } else {

            return new AA_Hints422Fragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return vContext.getString(R.string.Statistic);

            case 1:
                return vContext.getString(R.string.Charts);

            case 2:
                return vContext.getString(R.string.Hints);

            case 3:
                return vContext.getString(R.string.Hints);
        }
        return null;
    }


}
