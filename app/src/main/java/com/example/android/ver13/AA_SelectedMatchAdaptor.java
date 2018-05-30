package com.example.android.ver13;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.android.ver13.AA_Set1Activity.degreeDigits;


public class AA_SelectedMatchAdaptor extends ArrayAdapter<SelectedMatch> {

    Context mContext;

    String adve = "Ad";

    int numForMM = 0;


    public AA_SelectedMatchAdaptor(Context context, ArrayList<SelectedMatch> mySelectedMatches) {
        super(context, 0, mySelectedMatches);
    }

    @NonNull
    @Override

    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.aa_item_for_searched_match, parent, false);
        }


        // Get the {@link Word} object located at this position in the list
        SelectedMatch currentWord = getItem(position);

        TextView name1 = (TextView) listItemView.findViewById(R.id._smtV1);



        if (currentWord.getNameA2Text().equals("")) {
            name1.setText(currentWord.getNameA1Text());
        } else {
            name1.setText(currentWord.getNameA1Text() + "\n" + currentWord.getNameA2Text());
        }

        TextView name2 = (TextView) listItemView.findViewById(R.id._smtV7);

        if (currentWord.getNameB2Text().equals("")) {
            name2.setText(currentWord.getNameB1Text());
        } else {
            name2.setText(currentWord.getNameB1Text() + "\n" + currentWord.getNameB2Text());
        }

        TextView searchedSurface = (TextView) listItemView.findViewById(R.id._smtV22);

        switch (currentWord.getCourtTypeText()){
            case "11":
                searchedSurface.setText(R.string.hard_type)  ;
                break;
            case "12":
                searchedSurface.setText(R.string.clay_type)  ;
                break;
            case "13":
                searchedSurface.setText(R.string.grass_type)  ;
                break;
            case "14":
                searchedSurface.setText(R.string.iHard_type)  ;
                break;
            case "15":
                searchedSurface.setText(R.string.rubber_type)  ;
                break;
        }



        TextView searchedDate = (TextView) listItemView.findViewById(R.id._smtV17);
        searchedDate.setText(String.valueOf(currentWord.getDateText()));

        TextView searchedStatus = (TextView) listItemView.findViewById(R.id._smtV18);

        if (String.valueOf(currentWord.getMatchStatusText()).equals("firstPlayer")) {
            searchedStatus.setText(R.string.Finished);
            name1.setBackgroundResource(R.drawable.frame_winner_db);
            name2.setBackgroundResource(R.drawable.frame_3);
        } else if (String.valueOf(currentWord.getMatchStatusText()).equals("secondPlayer")) {
            searchedStatus.setText(R.string.Finished);
            name2.setBackgroundResource(R.drawable.frame_winner_db);
            name1.setBackgroundResource(R.drawable.frame_3);
        } else if (String.valueOf(currentWord.getMatchStatusText()).equals("Paused")) {
            searchedStatus.setText(R.string.Paused);
            name1.setBackgroundResource(R.drawable.frame_3);
            name2.setBackgroundResource(R.drawable.frame_3);
        } else if (String.valueOf(currentWord.getMatchStatusText()).equals("Finished")) {
            searchedStatus.setText(R.string.Finished);
            name1.setBackgroundResource(R.drawable.frame_3);
            name2.setBackgroundResource(R.drawable.frame_3);
        }


        TextView a1 = (TextView) listItemView.findViewById(R.id._smtV2);
        TextView b1 = (TextView) listItemView.findViewById(R.id._smtV8);
        TextView _set1a = (TextView) listItemView.findViewById(R.id._smtV3);
        TextView _set1b = (TextView) listItemView.findViewById(R.id._smtV9);
        TextView _set2a = (TextView) listItemView.findViewById(R.id._smtV4);
        TextView _set2b = (TextView) listItemView.findViewById(R.id._smtV10);
        TextView _set3a = (TextView) listItemView.findViewById(R.id._smtV5);
        TextView _set3b = (TextView) listItemView.findViewById(R.id._smtV11);
        TextView _set4a = (TextView) listItemView.findViewById(R.id._smtV6);
        TextView _set4b = (TextView) listItemView.findViewById(R.id._smtV12);
        TextView _set5a = (TextView) listItemView.findViewById(R.id._smtV19);
        TextView _set5b = (TextView) listItemView.findViewById(R.id._smtV20);
        TextView searchedMatchMode = (TextView) listItemView.findViewById(R.id._smtV16);


        if (currentWord.getSet5a() > 0 || currentWord.getSet5b() > 0) {
            _set1a.setBackgroundResource(R.drawable.frame_3);
            _set1b.setBackgroundResource(R.drawable.frame_3);
            _set2a.setBackgroundResource(R.drawable.frame_3);
            _set2b.setBackgroundResource(R.drawable.frame_3);
            _set3a.setBackgroundResource(R.drawable.frame_3);
            _set3b.setBackgroundResource(R.drawable.frame_3);
            _set4a.setBackgroundResource(R.drawable.frame_3);
            _set4b.setBackgroundResource(R.drawable.frame_3);
            _set5a.setBackgroundResource(R.drawable.frame_3);
            _set5b.setBackgroundResource(R.drawable.frame_3);
            _set1a.setText(String.valueOf(currentWord.getSet1a()));
            _set1b.setText(String.valueOf(currentWord.getSet1b()));
            _set2a.setText(String.valueOf(currentWord.getSet2a()));
            _set2b.setText(String.valueOf(currentWord.getSet2b()));
            _set3a.setText(String.valueOf(currentWord.getSet3a()));
            _set3b.setText(String.valueOf(currentWord.getSet3b()));
            _set4a.setText(String.valueOf(currentWord.getSet4a()));
            _set4b.setText(String.valueOf(currentWord.getSet4b()));
            _set5a.setText(String.valueOf(currentWord.getSet5a()));
            _set5b.setText(String.valueOf(currentWord.getSet5b()));

        } else if (currentWord.getSet4a() > 0 || currentWord.getSet4b() > 0) {
            _set1a.setBackgroundResource(R.drawable.frame_3);
            _set1b.setBackgroundResource(R.drawable.frame_3);
            _set2a.setBackgroundResource(R.drawable.frame_3);
            _set2b.setBackgroundResource(R.drawable.frame_3);
            _set3a.setBackgroundResource(R.drawable.frame_3);
            _set3b.setBackgroundResource(R.drawable.frame_3);
            _set4a.setBackgroundResource(R.drawable.frame_3);
            _set4b.setBackgroundResource(R.drawable.frame_3);
            _set1a.setText(String.valueOf(currentWord.getSet1a()));
            _set1b.setText(String.valueOf(currentWord.getSet1b()));
            _set2a.setText(String.valueOf(currentWord.getSet2a()));
            _set2b.setText(String.valueOf(currentWord.getSet2b()));
            _set3a.setText(String.valueOf(currentWord.getSet3a()));
            _set3b.setText(String.valueOf(currentWord.getSet3b()));
            _set4a.setText(String.valueOf(currentWord.getSet4a()));
            _set4b.setText(String.valueOf(currentWord.getSet4b()));

        } else if (currentWord.getSet3a() > 0 || currentWord.getSet3b() > 0) {
            _set1a.setBackgroundResource(R.drawable.frame_3);
            _set1b.setBackgroundResource(R.drawable.frame_3);
            _set2a.setBackgroundResource(R.drawable.frame_3);
            _set2b.setBackgroundResource(R.drawable.frame_3);
            _set3a.setBackgroundResource(R.drawable.frame_3);
            _set3b.setBackgroundResource(R.drawable.frame_3);
            _set1a.setText(String.valueOf(currentWord.getSet1a()));
            _set1b.setText(String.valueOf(currentWord.getSet1b()));
            _set2a.setText(String.valueOf(currentWord.getSet2a()));
            _set2b.setText(String.valueOf(currentWord.getSet2b()));
            _set3a.setText(String.valueOf(currentWord.getSet3a()));
            _set3b.setText(String.valueOf(currentWord.getSet3b()));

        } else if (currentWord.getSet2a() > 0 || currentWord.getSet2b() > 0) {
            _set1a.setBackgroundResource(R.drawable.frame_3);
            _set1b.setBackgroundResource(R.drawable.frame_3);
            _set2a.setBackgroundResource(R.drawable.frame_3);
            _set2b.setBackgroundResource(R.drawable.frame_3);
            _set1a.setText(String.valueOf(currentWord.getSet1a()));
            _set1b.setText(String.valueOf(currentWord.getSet1b()));
            _set2a.setText(String.valueOf(currentWord.getSet2a()));
            _set2b.setText(String.valueOf(currentWord.getSet2b()));

        } else if (currentWord.getSet1a() > 0 || currentWord.getSet1b() > 0) {
            _set1a.setBackgroundResource(R.drawable.frame_3);
            _set1b.setBackgroundResource(R.drawable.frame_3);
            _set1a.setText(String.valueOf(currentWord.getSet1a()));
            _set1b.setText(String.valueOf(currentWord.getSet1b()));
        }
        a1.setBackgroundResource(R.drawable.frame_3);
        b1.setBackgroundResource(R.drawable.frame_3);


        switch (currentWord.getMatchModeText()) {

            case "0":

                searchedMatchMode.setText(R.string.KTB);

                a1.setText(String.valueOf(currentWord.getA_Num()));
                b1.setText(String.valueOf(currentWord.getB_Num()));

                if (currentWord.getA_Num() >= 10 && currentWord.getB_Num() <= (currentWord.getA_Num() - 2)) {
                    a1.setBackgroundResource(R.drawable.frame_winner_db);
                }
                if (currentWord.getB_Num() >= 10 && currentWord.getA_Num() <= (currentWord.getB_Num() - 2)) {
                    b1.setBackgroundResource(R.drawable.frame_winner_db);
                }

                break;

            case "1":

                searchedMatchMode.setText(R.string.one_set);

                if (currentWord.getSet1a() == 7 || currentWord.getSet1b() == 7) {
                    a1.setText("0");
                    b1.setText("0");

                } else if (currentWord.getA_Num() == -2) {
                    a1.setText(adve);
                    b1.setText(String.valueOf(currentWord.getB_Num()));

                } else if (currentWord.getB_Num() == -2) {
                    b1.setText(adve);
                    a1.setText(String.valueOf(currentWord.getA_Num()));

                } else {
                    a1.setText(String.valueOf(currentWord.getA_Num()));
                    b1.setText(String.valueOf(currentWord.getB_Num()));
                }

                if ((currentWord.getSet1a_tb() >= 7 && currentWord.getSet1b_tb() <= currentWord.getSet1a_tb() - 2) ||
                        (currentWord.getSet1b_tb() >= 7 && currentWord.getSet1a_tb() <= currentWord.getSet1b_tb() - 2)) {

                    convert(currentWord.getSet1a_tb(), currentWord.getSet1b_tb(), _set1a, _set1b);
                }

                if ((currentWord.getSet1a() == 6 && currentWord.getSet1b() <= currentWord.getSet1a() - 2) || currentWord.getSet1a() == 7) {
                    _set1a.setBackgroundResource(R.drawable.frame_winner_db);
                }
                if ((currentWord.getSet1b() == 6 && currentWord.getSet1a() <= currentWord.getSet1b() - 2) || currentWord.getSet1b() == 7) {
                    _set1b.setBackgroundResource(R.drawable.frame_winner_db);
                }

                break;

            case "2":

                searchedMatchMode.setText(R.string.three_sets_KTB);

                if (currentWord.getA_Num() == -2) {
                    a1.setText(adve);
                    b1.setText(String.valueOf(currentWord.getB_Num()));

                } else if (currentWord.getB_Num() == -2) {
                    b1.setText(adve);
                    a1.setText(String.valueOf(currentWord.getA_Num()));

                } else {
                    a1.setText(String.valueOf(currentWord.getA_Num()));
                    b1.setText(String.valueOf(currentWord.getB_Num()));
                }


                if ((currentWord.getSet1a_tb() >= 7 && currentWord.getSet1b_tb() <= currentWord.getSet1a_tb() - 2) ||
                        (currentWord.getSet1b_tb() >= 7 && currentWord.getSet1a_tb() <= currentWord.getSet1b_tb() - 2)) {

                    convert(currentWord.getSet1a_tb(), currentWord.getSet1b_tb(), _set1a, _set1b);
                }

                if ((currentWord.getSet2a_tb() >= 7 && currentWord.getSet2b_tb() <= currentWord.getSet2a_tb() - 2) ||
                        (currentWord.getSet2b_tb() >= 7 && currentWord.getSet2a_tb() <= currentWord.getSet2b_tb() - 2)) {

                    convert(currentWord.getSet2a_tb(), currentWord.getSet2b_tb(), _set2a, _set2b);
                }

                if ((currentWord.getSet1a() == 6 && currentWord.getSet1b() <= currentWord.getSet1a() - 2) || currentWord.getSet1a() == 7) {
                    _set1a.setBackgroundResource(R.drawable.frame_winner_db);
                }
                if ((currentWord.getSet1b() == 6 && currentWord.getSet1a() <= currentWord.getSet1b() - 2) || currentWord.getSet1b() == 7) {
                    _set1b.setBackgroundResource(R.drawable.frame_winner_db);
                }
                if ((currentWord.getSet2a() == 6 && currentWord.getSet2b() <= currentWord.getSet2a() - 2) || currentWord.getSet2a() == 7) {
                    _set2a.setBackgroundResource(R.drawable.frame_winner_db);
                }
                if ((currentWord.getSet2b() == 6 && currentWord.getSet2a() <= currentWord.getSet2b() - 2) || currentWord.getSet2b() == 7) {
                    _set2b.setBackgroundResource(R.drawable.frame_winner_db);
                }
                if (currentWord.getSet3a() >= 10 && currentWord.getSet3b() <= (currentWord.getSet3a() - 2)) {
                    _set3a.setBackgroundResource(R.drawable.frame_winner_db);
                }
                if (currentWord.getSet3b() >= 10 && currentWord.getSet3a() <= (currentWord.getSet3b() - 2)) {
                    _set3b.setBackgroundResource(R.drawable.frame_winner_db);
                }


                break;

            case "3":

                searchedMatchMode.setText(R.string.Sets3US_short);

                if (currentWord.getA_Num() == -2) {
                    a1.setText(adve);
                    b1.setText(String.valueOf(currentWord.getB_Num()));

                } else if (currentWord.getB_Num() == -2) {
                    b1.setText(adve);
                    a1.setText(String.valueOf(currentWord.getA_Num()));

                } else {
                    a1.setText(String.valueOf(currentWord.getA_Num()));
                    b1.setText(String.valueOf(currentWord.getB_Num()));
                }

                if ((currentWord.getSet1a_tb() >= 7 && currentWord.getSet1b_tb() <= currentWord.getSet1a_tb() - 2) ||
                        (currentWord.getSet1b_tb() >= 7 && currentWord.getSet1a_tb() <= currentWord.getSet1b_tb() - 2)) {

                    convert(currentWord.getSet1a_tb(), currentWord.getSet1b_tb(), _set1a, _set1b);
                }

                if ((currentWord.getSet2a_tb() >= 7 && currentWord.getSet2b_tb() <= currentWord.getSet2a_tb() - 2) ||
                        (currentWord.getSet2b_tb() >= 7 && currentWord.getSet2a_tb() <= currentWord.getSet2b_tb() - 2)) {

                    convert(currentWord.getSet2a_tb(), currentWord.getSet2b_tb(), _set2a, _set2b);
                }

                if ((currentWord.getSet3a_tb() >= 7 && currentWord.getSet3b_tb() <= currentWord.getSet3a_tb() - 2) ||
                        (currentWord.getSet3b_tb() >= 7 && currentWord.getSet3a_tb() <= currentWord.getSet3b_tb() - 2)) {

                    convert(currentWord.getSet3a_tb(), currentWord.getSet3b_tb(), _set3a, _set3b);
                }

                if ((currentWord.getSet1a() == 6 && currentWord.getSet1b() <= currentWord.getSet1a() - 2) || currentWord.getSet1a() == 7) {
                    _set1a.setBackgroundResource(R.drawable.frame_winner_db);
                }
                if ((currentWord.getSet1b() == 6 && currentWord.getSet1a() <= currentWord.getSet1b() - 2) || currentWord.getSet1b() == 7) {
                    _set1b.setBackgroundResource(R.drawable.frame_winner_db);
                }
                if ((currentWord.getSet2a() == 6 && currentWord.getSet2b() <= currentWord.getSet2a() - 2) || currentWord.getSet2a() == 7) {
                    _set2a.setBackgroundResource(R.drawable.frame_winner_db);
                }
                if ((currentWord.getSet2b() == 6 && currentWord.getSet2a() <= currentWord.getSet2b() - 2) || currentWord.getSet2b() == 7) {
                    _set2b.setBackgroundResource(R.drawable.frame_winner_db);
                }

                if ((currentWord.getSet3a() == 6 && currentWord.getSet3b() <= currentWord.getSet3a() - 2) || currentWord.getSet3a() == 7) {
                    _set3a.setBackgroundResource(R.drawable.frame_winner_db);
                }
                if ((currentWord.getSet3b() == 6 && currentWord.getSet3a() <= currentWord.getSet3b() - 2) || currentWord.getSet3b() == 7) {
                    _set3b.setBackgroundResource(R.drawable.frame_winner_db);
                }

                break;

            case "4":

                searchedMatchMode.setText(R.string.three_sets);

                if (currentWord.getA_Num() == -2) {
                    a1.setText(adve);
                    b1.setText(String.valueOf(currentWord.getB_Num()));

                } else if (currentWord.getB_Num() == -2) {
                    b1.setText(adve);
                    a1.setText(String.valueOf(currentWord.getA_Num()));

                } else {
                    a1.setText(String.valueOf(currentWord.getA_Num()));
                    b1.setText(String.valueOf(currentWord.getB_Num()));
                }

                if ((currentWord.getSet1a_tb() >= 7 && currentWord.getSet1b_tb() <= currentWord.getSet1a_tb() - 2) ||
                        (currentWord.getSet1b_tb() >= 7 && currentWord.getSet1a_tb() <= currentWord.getSet1b_tb() - 2)) {

                    convert(currentWord.getSet1a_tb(), currentWord.getSet1b_tb(), _set1a, _set1b);
                }

                if ((currentWord.getSet2a_tb() >= 7 && currentWord.getSet2b_tb() <= currentWord.getSet2a_tb() - 2) ||
                        (currentWord.getSet2b_tb() >= 7 && currentWord.getSet2a_tb() <= currentWord.getSet2b_tb() - 2)) {

                    convert(currentWord.getSet2a_tb(), currentWord.getSet2b_tb(), _set2a, _set2b);
                }

                if ((currentWord.getSet1a() == 6 && currentWord.getSet1b() <= currentWord.getSet1a() - 2) || currentWord.getSet1a() == 7) {
                    _set1a.setBackgroundResource(R.drawable.frame_winner_db);
                }
                if ((currentWord.getSet1b() == 6 && currentWord.getSet1a() <= currentWord.getSet1b() - 2) || currentWord.getSet1b() == 7) {
                    _set1b.setBackgroundResource(R.drawable.frame_winner_db);
                }
                if ((currentWord.getSet2a() == 6 && currentWord.getSet2b() <= currentWord.getSet2a() - 2) || currentWord.getSet2a() == 7) {
                    _set2a.setBackgroundResource(R.drawable.frame_winner_db);
                }
                if ((currentWord.getSet2b() == 6 && currentWord.getSet2a() <= currentWord.getSet2b() - 2) || currentWord.getSet2b() == 7) {
                    _set2b.setBackgroundResource(R.drawable.frame_winner_db);
                }

                if ((currentWord.getSet3a() == 6 || currentWord.getSet3a() >= 7) && currentWord.getSet3b() <= currentWord.getSet3a() - 2) {
                    _set3a.setBackgroundResource(R.drawable.frame_winner_db);
                }
                if ((currentWord.getSet3b() == 6 || currentWord.getSet3b() >= 7) && currentWord.getSet3a() <= currentWord.getSet3b() - 2) {
                    _set3b.setBackgroundResource(R.drawable.frame_winner_db);
                }


                break;

            case "5":

                searchedMatchMode.setText(R.string.Sets5US_short);

                if (currentWord.getA_Num() == -2) {
                    a1.setText(adve);
                    b1.setText(String.valueOf(currentWord.getB_Num()));

                } else if (currentWord.getB_Num() == -2) {
                    b1.setText(adve);
                    a1.setText(String.valueOf(currentWord.getA_Num()));

                } else {
                    a1.setText(String.valueOf(currentWord.getA_Num()));
                    b1.setText(String.valueOf(currentWord.getB_Num()));
                }

                if ((currentWord.getSet1a_tb() >= 7 && currentWord.getSet1b_tb() <= currentWord.getSet1a_tb() - 2) ||
                        (currentWord.getSet1b_tb() >= 7 && currentWord.getSet1a_tb() <= currentWord.getSet1b_tb() - 2)) {

                    convert(currentWord.getSet1a_tb(), currentWord.getSet1b_tb(), _set1a, _set1b);
                }

                if ((currentWord.getSet2a_tb() >= 7 && currentWord.getSet2b_tb() <= currentWord.getSet2a_tb() - 2) ||
                        (currentWord.getSet2b_tb() >= 7 && currentWord.getSet2a_tb() <= currentWord.getSet2b_tb() - 2)) {

                    convert(currentWord.getSet2a_tb(), currentWord.getSet2b_tb(), _set2a, _set2b);
                }

                if ((currentWord.getSet3a_tb() >= 7 && currentWord.getSet3b_tb() <= currentWord.getSet3a_tb() - 2) ||
                        (currentWord.getSet3b_tb() >= 7 && currentWord.getSet3a_tb() <= currentWord.getSet3b_tb() - 2)) {

                    convert(currentWord.getSet3a_tb(), currentWord.getSet3b_tb(), _set3a, _set3b);
                }

                if ((currentWord.getSet4a_tb() >= 7 && currentWord.getSet4b_tb() <= currentWord.getSet4a_tb() - 2) ||
                        (currentWord.getSet4b_tb() >= 7 && currentWord.getSet4a_tb() <= currentWord.getSet4b_tb() - 2)) {

                    convert(currentWord.getSet4a_tb(), currentWord.getSet4b_tb(), _set4a, _set4b);
                }

                if ((currentWord.getSet5a_tb() >= 7 && currentWord.getSet5b_tb() <= currentWord.getSet5a_tb() - 2) ||
                        (currentWord.getSet5b_tb() >= 7 && currentWord.getSet5a_tb() <= currentWord.getSet5b_tb() - 2)) {

                    convert(currentWord.getSet5a_tb(), currentWord.getSet5b_tb(), _set5a, _set5b);
                }

                if ((currentWord.getSet1a() == 6 && currentWord.getSet1b() <= currentWord.getSet1a() - 2) || currentWord.getSet1a() == 7) {
                    _set1a.setBackgroundResource(R.drawable.frame_winner_db);
                }
                if ((currentWord.getSet1b() == 6 && currentWord.getSet1a() <= currentWord.getSet1b() - 2) || currentWord.getSet1b() == 7) {
                    _set1b.setBackgroundResource(R.drawable.frame_winner_db);
                }
                if ((currentWord.getSet2a() == 6 && currentWord.getSet2b() <= currentWord.getSet2a() - 2) || currentWord.getSet2a() == 7) {
                    _set2a.setBackgroundResource(R.drawable.frame_winner_db);
                }
                if ((currentWord.getSet2b() == 6 && currentWord.getSet2a() <= currentWord.getSet2b() - 2) || currentWord.getSet2b() == 7) {
                    _set2b.setBackgroundResource(R.drawable.frame_winner_db);
                }

                if ((currentWord.getSet3a() == 6 && currentWord.getSet3b() <= currentWord.getSet3a() - 2) || currentWord.getSet3a() == 7) {
                    _set3a.setBackgroundResource(R.drawable.frame_winner_db);
                }
                if ((currentWord.getSet3b() == 6 && currentWord.getSet3a() <= currentWord.getSet3b() - 2) || currentWord.getSet3b() == 7) {
                    _set3b.setBackgroundResource(R.drawable.frame_winner_db);
                }

                if ((currentWord.getSet4a() == 6 && currentWord.getSet4b() <= currentWord.getSet4a() - 2) || currentWord.getSet4a() == 7) {
                    _set4a.setBackgroundResource(R.drawable.frame_winner_db);
                }
                if ((currentWord.getSet4b() == 6 && currentWord.getSet4a() <= currentWord.getSet4b() - 2) || currentWord.getSet4b() == 7) {
                    _set4b.setBackgroundResource(R.drawable.frame_winner_db);
                }

                if ((currentWord.getSet5a() == 6 && currentWord.getSet5b() <= currentWord.getSet5a() - 2) || currentWord.getSet5a() == 7) {
                    _set5a.setBackgroundResource(R.drawable.frame_winner_db);
                }
                if ((currentWord.getSet5b() == 6 && currentWord.getSet5a() <= currentWord.getSet5b() - 2) || currentWord.getSet5b() == 7) {
                    _set5b.setBackgroundResource(R.drawable.frame_winner_db);
                }
                break;

            case "6":

                searchedMatchMode.setText(R.string.five_sets);

                if (currentWord.getA_Num() == -2) {
                    a1.setText(adve);
                    b1.setText(String.valueOf(currentWord.getB_Num()));

                } else if (currentWord.getB_Num() == -2) {
                    b1.setText(adve);
                    a1.setText(String.valueOf(currentWord.getA_Num()));

                } else {
                    a1.setText(String.valueOf(currentWord.getA_Num()));
                    b1.setText(String.valueOf(currentWord.getB_Num()));
                }

                if ((currentWord.getSet1a_tb() >= 7 && currentWord.getSet1b_tb() <= currentWord.getSet1a_tb() - 2) ||
                        (currentWord.getSet1b_tb() >= 7 && currentWord.getSet1a_tb() <= currentWord.getSet1b_tb() - 2)) {

                    convert(currentWord.getSet1a_tb(), currentWord.getSet1b_tb(), _set1a, _set1b);
                }

                if ((currentWord.getSet2a_tb() >= 7 && currentWord.getSet2b_tb() <= currentWord.getSet2a_tb() - 2) ||
                        (currentWord.getSet2b_tb() >= 7 && currentWord.getSet2a_tb() <= currentWord.getSet2b_tb() - 2)) {

                    convert(currentWord.getSet2a_tb(), currentWord.getSet2b_tb(), _set2a, _set2b);
                }

                if ((currentWord.getSet3a_tb() >= 7 && currentWord.getSet3b_tb() <= currentWord.getSet3a_tb() - 2) ||
                        (currentWord.getSet3b_tb() >= 7 && currentWord.getSet3a_tb() <= currentWord.getSet3b_tb() - 2)) {

                    convert(currentWord.getSet3a_tb(), currentWord.getSet3b_tb(), _set3a, _set3b);
                }

                if ((currentWord.getSet4a_tb() >= 7 && currentWord.getSet4b_tb() <= currentWord.getSet4a_tb() - 2) ||
                        (currentWord.getSet4b_tb() >= 7 && currentWord.getSet4a_tb() <= currentWord.getSet4b_tb() - 2)) {

                    convert(currentWord.getSet4a_tb(), currentWord.getSet4b_tb(), _set4a, _set4b);
                }

                if ((currentWord.getSet1a() == 6 && currentWord.getSet1b() <= currentWord.getSet1a() - 2) || currentWord.getSet1a() == 7) {
                    _set1a.setBackgroundResource(R.drawable.frame_winner_db);
                }
                if ((currentWord.getSet1b() == 6 && currentWord.getSet1a() <= currentWord.getSet1b() - 2) || currentWord.getSet1b() == 7) {
                    _set1b.setBackgroundResource(R.drawable.frame_winner_db);
                }
                if ((currentWord.getSet2a() == 6 && currentWord.getSet2b() <= currentWord.getSet2a() - 2) || currentWord.getSet2a() == 7) {
                    _set2a.setBackgroundResource(R.drawable.frame_winner_db);
                }
                if ((currentWord.getSet2b() == 6 && currentWord.getSet2a() <= currentWord.getSet2b() - 2) || currentWord.getSet2b() == 7) {
                    _set2b.setBackgroundResource(R.drawable.frame_winner_db);
                }

                if ((currentWord.getSet3a() == 6 && currentWord.getSet3b() <= currentWord.getSet3a() - 2) || currentWord.getSet3a() == 7) {
                    _set3a.setBackgroundResource(R.drawable.frame_winner_db);
                }
                if ((currentWord.getSet3b() == 6 && currentWord.getSet3a() <= currentWord.getSet3b() - 2) || currentWord.getSet3b() == 7) {
                    _set3b.setBackgroundResource(R.drawable.frame_winner_db);
                }

                if ((currentWord.getSet4a() == 6 && currentWord.getSet4b() <= currentWord.getSet4a() - 2) || currentWord.getSet4a() == 7) {
                    _set4a.setBackgroundResource(R.drawable.frame_winner_db);
                }
                if ((currentWord.getSet4b() == 6 && currentWord.getSet4a() <= currentWord.getSet4b() - 2) || currentWord.getSet4b() == 7) {
                    _set4b.setBackgroundResource(R.drawable.frame_winner_db);
                }

                if ((currentWord.getSet5a() == 6 || currentWord.getSet5a() >= 7) && currentWord.getSet5b() <= currentWord.getSet5a() - 2) {
                    _set5a.setBackgroundResource(R.drawable.frame_winner_db);
                }
                if ((currentWord.getSet5b() == 6 || currentWord.getSet5b() >= 7) && currentWord.getSet5a() <= currentWord.getSet5b() - 2) {
                    _set5b.setBackgroundResource(R.drawable.frame_winner_db);
                }
        }
        return listItemView;
    }

    private void convert(int mp, int yu, TextView tv1, TextView tv2) {
        String str = "";
        String str1s = "";
        for (int i = 0; i < String.valueOf(mp).length(); i++) {
            int k = String.valueOf(mp).length() - i;
            str = degreeDigits[Character.getNumericValue(String.valueOf(mp).charAt(k - 1))] + str;
        }

        for (int kol = 0; kol < String.valueOf(yu).length(); kol++) {
            int kik = String.valueOf(yu).length() - kol;
            str1s = degreeDigits[Character.getNumericValue(String.valueOf(yu).charAt(kik - 1))] + str1s;
        }

        tv1.setText(tv1.getText() + str);
        tv2.setText(tv2.getText() + str1s);

    }
}
