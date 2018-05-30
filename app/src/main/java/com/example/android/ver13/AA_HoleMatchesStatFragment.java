package com.example.android.ver13;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.ver13.data.TennisHelper;

import java.util.ArrayList;

import static com.example.android.ver13.SearchedMatchsActivity.numbers;



public class AA_HoleMatchesStatFragment extends Fragment {

    TextView fmsName1_42, fmsName2_42, fmsW1_42, fmsW2_42, fmsAce1_42, fmsAce2_42, fmsUE1_42, fmsUE2_42, fmsDE1_42, fmsDE2_42;

    public static ArrayList<Integer> oneNameWinnersArray;
    public static ArrayList<Integer> secondNameWinnersArray;
    public static ArrayList<Integer> oneNameAcesArray;
    public static ArrayList<Integer> secondNameAcesArray;
    public static ArrayList<Integer> oneNameUEArray;
    public static ArrayList<Integer> secondNameUEArray;
    public static ArrayList<Integer> oneNameDEArray;
    public static ArrayList<Integer> secondNameDEArray;
    private TennisHelper mDbHelper;
    public static String oneName, secondName, _1_oneName, _2_oneName, _1_secondName, _2_secondName;

    public static int winnersOverall_One = 0;
    public static int winnersOverall_Second = 0;
    public static int acesOverall_One = 0;
    public static int acesOverall_Second = 0;
    public static int ueOverall_One = 0;
    public static int ueOverall_Second = 0;
    public static int deOverall_One = 0;
    public static int deOverall_Second = 0;

    public AA_HoleMatchesStatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.aa_activity_hole_matches_stat, container, false);

        mDbHelper = new TennisHelper(getContext());

        oneNameWinnersArray = new ArrayList<>();
        secondNameWinnersArray = new ArrayList<>();
        oneNameAcesArray = new ArrayList<>();
        secondNameAcesArray = new ArrayList<>();
        oneNameUEArray = new ArrayList<>();
        secondNameUEArray = new ArrayList<>();
        oneNameDEArray = new ArrayList<>();
        secondNameDEArray = new ArrayList<>();

        oneName = mDbHelper.getNameById("full_A_NameSAVE", numbers.get(0));
        secondName = mDbHelper.getNameById("full_B_NameSAVE", numbers.get(0));
        _1_oneName = mDbHelper.getNameById("name_A_1", numbers.get(0));
        _2_oneName = mDbHelper.getNameById("name_A_2", numbers.get(0));
        _1_secondName = mDbHelper.getNameById("name_B_1", numbers.get(0));
        _2_secondName = mDbHelper.getNameById("name_B_2", numbers.get(0));


        for (int i = 0; i < numbers.size(); i++) {

            if (oneName.equals(mDbHelper.getNameById("full_A_NameSAVE", numbers.get(i)))) {

                oneNameWinnersArray.add(mDbHelper.getNumById("A_winner", numbers.get(i)));
                oneNameAcesArray.add(mDbHelper.getNumById("A_ace", numbers.get(i)));
                oneNameUEArray.add(mDbHelper.getNumById("A_ue", numbers.get(i)));
                oneNameDEArray.add(mDbHelper.getNumById("A_de", numbers.get(i)));

                secondNameWinnersArray.add(mDbHelper.getNumById("B_winner", numbers.get(i)));
                secondNameAcesArray.add(mDbHelper.getNumById("B_ace", numbers.get(i)));
                secondNameUEArray.add(mDbHelper.getNumById("B_ue", numbers.get(i)));
                secondNameDEArray.add(mDbHelper.getNumById("B_de", numbers.get(i)));

                Log.v("long clicked", "pos: ");

            } else {

                oneNameWinnersArray.add(mDbHelper.getNumById("B_winner", numbers.get(i)));
                oneNameAcesArray.add(mDbHelper.getNumById("B_ace", numbers.get(i)));
                oneNameUEArray.add(mDbHelper.getNumById("B_ue", numbers.get(i)));
                oneNameDEArray.add(mDbHelper.getNumById("B_de", numbers.get(i)));

                secondNameWinnersArray.add(mDbHelper.getNumById("A_winner", numbers.get(i)));
                secondNameAcesArray.add(mDbHelper.getNumById("A_ace", numbers.get(i)));
                secondNameUEArray.add(mDbHelper.getNumById("A_ue", numbers.get(i)));
                secondNameDEArray.add(mDbHelper.getNumById("A_de", numbers.get(i)));

                Log.v("long clicked", "pos: ");
            }
        }


        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        if (getView() != null) {
            fmsName1_42 = (TextView) getView().findViewById(R.id._fmsName1_42);
            fmsName1_42.setText(oneName);

            fmsName2_42 = (TextView) getView().findViewById(R.id._fmsName2_42);
            fmsName2_42.setText(secondName);

            fmsW1_42 = (TextView) getView().findViewById(R.id._fmsW1_42);
            winnersOverall_One = getOverallQuantity(oneNameWinnersArray);
            fmsW1_42.setText(String.valueOf(winnersOverall_One));

            fmsW2_42 = (TextView) getView().findViewById(R.id._fmsW2_42);
            winnersOverall_Second = getOverallQuantity(secondNameWinnersArray);
            fmsW2_42.setText(String.valueOf(winnersOverall_Second));

            fmsAce1_42 = (TextView) getView().findViewById(R.id._fmsAce1_42);
            acesOverall_One = getOverallQuantity(oneNameAcesArray);
            fmsAce1_42.setText(String.valueOf(acesOverall_One));

            fmsAce2_42 = (TextView) getView().findViewById(R.id._fmsAce2_42);
            acesOverall_Second = getOverallQuantity(secondNameAcesArray);
            fmsAce2_42.setText(String.valueOf(acesOverall_Second));

            fmsUE1_42 = (TextView) getView().findViewById(R.id._fmsUE1_42);
            ueOverall_One = getOverallQuantity(oneNameUEArray);
            fmsUE1_42.setText(String.valueOf(ueOverall_One));

            fmsUE2_42 = (TextView) getView().findViewById(R.id._fmsUE2_42);
            ueOverall_Second = getOverallQuantity(secondNameUEArray);
            fmsUE2_42.setText(String.valueOf(ueOverall_Second));

            fmsDE1_42 = (TextView) getView().findViewById(R.id._fmsDE1_42);
            deOverall_One = getOverallQuantity(oneNameDEArray);
            fmsDE1_42.setText(String.valueOf(deOverall_One));

            fmsDE2_42 = (TextView) getView().findViewById(R.id._fmsDE2_42);
            deOverall_Second = getOverallQuantity(secondNameDEArray);
            fmsDE2_42.setText(String.valueOf(deOverall_Second));

        }

    }

    public int getOverallQuantity(ArrayList<Integer> aL) {
        int oq = 0;
        for (int i = 0; i < aL.size(); i++) {
            oq = oq + aL.get(i);
        }
        return oq;
    }
}
