package com.example.android.ver13;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.android.ver13.data.TennisHelper;

import static com.example.android.ver13.SearchedMatchsActivity.selId;


public class AA_FullMatchStatFragment extends Fragment {
    TextView fmsName1, fmsName2, fmsW1, fmsW2, fmsAce1, fmsAce2, fmsUE1, fmsUE2, fmsDE1, fmsDE2;
    private TennisHelper mDbHelper;

    public static int numberOfWinners_A;
    public static int numberOfWinners_B;
    public static int numberOfAces_A;
    public static int numberOfAces_B;
    public static int numberOfDE_A;
    public static int numberOfDE_B;
    public static int numberOfuE_A;
    public static int numberOfuE_B;


    public AA_FullMatchStatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.aa_activity_full_match_stat, container, false);
        mDbHelper = new TennisHelper(getContext());
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        if (getView() != null) {

            fmsName1 = (TextView) getView().findViewById(R.id._fmsName1);
            fmsName1.setText(mDbHelper.getNameById("full_A_NameSAVE", selId));

            fmsName2 = (TextView) getView().findViewById(R.id._fmsName2);
            fmsName2.setText(mDbHelper.getNameById("full_B_NameSAVE", selId));


            fmsW1 = (TextView) getView().findViewById(R.id._fmsW1);
            fmsW1.setText(String.valueOf(mDbHelper.getNumById("A_winner", selId)));
            numberOfWinners_A = Integer.valueOf(fmsW1.getText().toString());

            fmsW2 = (TextView) getView().findViewById(R.id._fmsW2);
            fmsW2.setText(String.valueOf(mDbHelper.getNumById("B_winner", selId)));
            numberOfWinners_B = Integer.valueOf(fmsW2.getText().toString());

            fmsAce1 = (TextView) getView().findViewById(R.id._fmsAce1);
            fmsAce1.setText(String.valueOf(mDbHelper.getNumById("A_ace", selId)));
            numberOfAces_A = Integer.valueOf(fmsAce1.getText().toString());

            fmsAce2 = (TextView) getView().findViewById(R.id._fmsAce2);
            fmsAce2.setText(String.valueOf(mDbHelper.getNumById("B_ace", selId)));
            numberOfAces_B = Integer.valueOf(fmsAce2.getText().toString());

            fmsUE1 = (TextView) getView().findViewById(R.id._fmsUE1);
            fmsUE1.setText(String.valueOf(mDbHelper.getNumById("A_ue", selId)));
            numberOfuE_A = Integer.valueOf(fmsUE1.getText().toString());

            fmsUE2 = (TextView) getView().findViewById(R.id._fmsUE2);
            fmsUE2.setText(String.valueOf(mDbHelper.getNumById("B_ue", selId)));
            numberOfuE_B = Integer.valueOf(fmsUE2.getText().toString());

            fmsDE1 = (TextView) getView().findViewById(R.id._fmsDE1);
            fmsDE1.setText(String.valueOf(mDbHelper.getNumById("A_de", selId)));
            numberOfDE_A = Integer.valueOf(fmsDE1.getText().toString());

            fmsDE2 = (TextView) getView().findViewById(R.id._fmsDE2);
            fmsDE2.setText(String.valueOf(mDbHelper.getNumById("B_de", selId)));
            numberOfDE_B = Integer.valueOf(fmsDE2.getText().toString());
        }
    }
}


