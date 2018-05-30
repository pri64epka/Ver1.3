package com.example.android.ver13;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.example.android.ver13.AA_HoleMatchesStatFragment.acesOverall_One;
import static com.example.android.ver13.AA_HoleMatchesStatFragment.acesOverall_Second;
import static com.example.android.ver13.AA_HoleMatchesStatFragment.deOverall_One;
import static com.example.android.ver13.AA_HoleMatchesStatFragment.deOverall_Second;
import static com.example.android.ver13.AA_HoleMatchesStatFragment.oneName;
import static com.example.android.ver13.AA_HoleMatchesStatFragment.secondName;
import static com.example.android.ver13.AA_HoleMatchesStatFragment.ueOverall_One;
import static com.example.android.ver13.AA_HoleMatchesStatFragment.ueOverall_Second;
import static com.example.android.ver13.AA_HoleMatchesStatFragment.winnersOverall_One;
import static com.example.android.ver13.AA_HoleMatchesStatFragment.winnersOverall_Second;


public class AA_Hints421Fragment extends Fragment {

    int _winners_42, _aces_42, _DE_42, _UE_42;

    public AA_Hints421Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.aa_activity_hints421, container, false);
        return rootView;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {

        String list_by_W_42[] = {getString(R.string.hint_by_winner_1), getString(R.string.hint_by_winner_2), getString(R.string.hint_by_winner_3),
                getString(R.string.hint_by_winner_4), getString(R.string.hint_by_winner_5)};

        String list_by_UE_42[] = {getString(R.string.hint_by_UE_1), getString(R.string.hint_by_UE_2),
                getString(R.string.hint_by_UE_3), getString(R.string.hint_by_UE_4)};

        String lins_by_DE_42[] = {getString(R.string.hint_by_DE_1), getString(R.string.hint_by_DE_2),
                getString(R.string.hint_by_DE_3), getString(R.string.hint_by_DE_4)};

        String list_by_A_42[] = {getString(R.string.hint_by_ace_1), getString(R.string.hint_by_ace_2), getString(R.string.hint_by_ace_3)};

        if(getView()!=null){
            TextView header_42 = (TextView) getView().findViewById(R.id.headerTV_42);
            TextView DDt_42 = (TextView) getView().findViewById(R.id.aDDtoTV_42);
            TextView hint1A_42 = (TextView) getView().findViewById(R.id.hint1ATV_42);
            TextView hint2A_42 = (TextView) getView().findViewById(R.id.hint2ATV_42);
            TextView hint3A_42 = (TextView) getView().findViewById(R.id.hint3ATV_42);
            TextView hint4A_42 = (TextView) getView().findViewById(R.id.hint4ATV_42);

            DDt_42.setText(oneName);
            header_42.setText(getString(R.string.some_hints) + " " + secondName);

            hint1A_42.setText(list_by_W_42[getHint1_42()]);
            hint2A_42.setText(list_by_A_42[getHint2_42()]);
            hint3A_42.setText(list_by_UE_42[getHint3_42()]);
            hint4A_42.setText(lins_by_DE_42[getHint4_42()]);
        }
    }

    private int getHint1_42() {

        if ((winnersOverall_One > winnersOverall_Second) && (winnersOverall_Second <= 0.3 * winnersOverall_One)) {
            _winners_42 = 0;
        } else if (winnersOverall_One > winnersOverall_Second) {
            _winners_42 = 1;
        } else if ((winnersOverall_One < winnersOverall_Second) && (winnersOverall_One <= 0.3 * winnersOverall_Second)) {
            _winners_42 = 2;
        } else if (winnersOverall_One < winnersOverall_Second) {
            _winners_42 = 3;
        } else if (winnersOverall_One == winnersOverall_Second) {
            _winners_42 = 4;
        }
        return _winners_42;
    }

    private int getHint2_42() {

        if (acesOverall_One > acesOverall_Second) {
            _aces_42 = 0;
        } else if (acesOverall_One < acesOverall_Second) {
            _aces_42 = 1;
        } else if (acesOverall_One == acesOverall_Second) {
            _aces_42 = 2;
        }
        return _aces_42;
    }

    private int getHint3_42() {

        if (ueOverall_One > ueOverall_Second) {
            _UE_42 = 0;
        } else if (ueOverall_One < ueOverall_Second) {
            _UE_42 = 1;
        } else if (ueOverall_One == ueOverall_Second && ueOverall_One != 0) {
            _UE_42 = 2;
        } else if (ueOverall_One == 0 && ueOverall_Second == 0) {
            _UE_42 = 3;
        }
        return _UE_42;
    }

    private int getHint4_42() {

        if (deOverall_One > deOverall_Second) {
            _DE_42 = 0;
        } else if (deOverall_One < deOverall_Second) {
            _DE_42 = 1;
        } else if (deOverall_One == deOverall_Second && deOverall_One != 0) {
            _DE_42 = 2;
        } else if (deOverall_One == 0 && deOverall_Second == 0) {
            _DE_42 = 3;
        }
        return _DE_42;
    }
}
