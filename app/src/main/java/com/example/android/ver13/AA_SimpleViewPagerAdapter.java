package com.example.android.ver13;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;



public class AA_SimpleViewPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;


    public AA_SimpleViewPagerAdapter(Context context, FragmentManager fm) {

        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0) {

            return new AA_FullMatchStatFragment();

        } else if (position == 1) {

            return new AA_GraphicFragment();

        } else if (position == 2) {

            return new AA_HintsFragment();

        } else {
            return new AA_Hints2Fragment();
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
                return mContext.getString(R.string.Statistic);

            case 1:
                return mContext.getString(R.string.Charts);

            case 2:
                return mContext.getString(R.string.Hints);

            case 3:
                return mContext.getString(R.string.Hints);
        }
        return null;
    }

}
