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

/**
 * A simple {@link Fragment} subclass.
 */
public class AA_Hints422Fragment extends Fragment {

    int _winners_2_42, _aces_2_42, _DE_2_42, _UE_2_42;

    public AA_Hints422Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.aa_activity_hints422, container, false);

        return rootView;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {

        String list_by_W_2_42[] = {getString(R.string.hint_by_winner_1), getString(R.string.hint_by_winner_2), getString(R.string.hint_by_winner_3),
                getString(R.string.hint_by_winner_4), getString(R.string.hint_by_winner_5)};

        String list_by_UE_2_42[] = {getString(R.string.hint_by_UE_1), getString(R.string.hint_by_UE_2),
                getString(R.string.hint_by_UE_3), getString(R.string.hint_by_UE_4)};

        String list_by_DE_2_42[] = {getString(R.string.hint_by_DE_1), getString(R.string.hint_by_DE_2),
                getString(R.string.hint_by_DE_3), getString(R.string.hint_by_DE_4)};

        String list_by_A_2_42[] = {getString(R.string.hint_by_ace_1), getString(R.string.hint_by_ace_2), getString(R.string.hint_by_ace_3)};

        if (getView() != null) {

            TextView header2_42 = (TextView) getView().findViewById(R.id.headerTV2_42);
            TextView DDt2_42 = (TextView) getView().findViewById(R.id.aDDtoTV2_42);
            TextView hint1B_42 = (TextView) getView().findViewById(R.id.hint1BTV_42);
            TextView hint2B_42 = (TextView) getView().findViewById(R.id.hint2BTV_42);
            TextView hint3B_42 = (TextView) getView().findViewById(R.id.hint3BTV_42);
            TextView hint4B_42 = (TextView) getView().findViewById(R.id.hint4BTV_42);

            DDt2_42.setText(secondName);
            header2_42.setText(getString(R.string.some_hints) + " " + oneName);

            hint1B_42.setText(list_by_W_2_42[getHint1B_42()]);
            hint2B_42.setText(list_by_A_2_42[getHint2B_42()]);
            hint3B_42.setText(list_by_UE_2_42[getHint3B_42()]);
            hint4B_42.setText(list_by_DE_2_42[getHint4B_42()]);


        }
    }

    private int getHint1B_42() {

        if ((winnersOverall_One > winnersOverall_Second) && (winnersOverall_Second <= 0.3 * winnersOverall_One)) {
            _winners_2_42 = 2;
        } else if (winnersOverall_One > winnersOverall_Second) {
            _winners_2_42 = 3;
        } else if ((winnersOverall_One < winnersOverall_Second) && (winnersOverall_One <= 0.3 * winnersOverall_Second)) {
            _winners_2_42 = 0;
        } else if (winnersOverall_One < winnersOverall_Second) {
            _winners_2_42 = 1;
        } else if (winnersOverall_One == winnersOverall_Second) {
            _winners_2_42 = 4;
        }
        return _winners_2_42;
    }

    private int getHint2B_42() {

        if (acesOverall_One > acesOverall_Second) {
            _aces_2_42 = 1;
        } else if (acesOverall_One < acesOverall_Second) {
            _aces_2_42 = 0;
        } else if (acesOverall_One == acesOverall_Second) {
            _aces_2_42 = 2;
        }
        return _aces_2_42;
    }

    private int getHint3B_42() {

        if (ueOverall_One > ueOverall_Second) {
            _UE_2_42 = 1;
        } else if (ueOverall_One < ueOverall_Second) {
            _UE_2_42 = 0;
        } else if (ueOverall_One == ueOverall_Second && ueOverall_One != 0) {
            _UE_2_42 = 2;
        } else if (ueOverall_One == 0 && ueOverall_Second == 0) {
            _UE_2_42 = 3;
        }
        return _UE_2_42;
    }

    private int getHint4B_42() {

        if (deOverall_One > deOverall_Second) {
            _DE_2_42 = 1;
        } else if (deOverall_One < deOverall_Second) {
            _DE_2_42 = 0;
        } else if (deOverall_One == deOverall_Second && deOverall_One != 0) {
            _DE_2_42 = 2;
        } else if (deOverall_One == 0 && deOverall_Second == 0) {
            _DE_2_42 = 3;
        }
        return _DE_2_42;
    }

}
