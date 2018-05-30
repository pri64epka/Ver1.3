package com.example.android.ver13;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.ver13.data.TennisHelper;

import static com.example.android.ver13.AA_FullMatchStatFragment.numberOfAces_A;
import static com.example.android.ver13.AA_FullMatchStatFragment.numberOfAces_B;
import static com.example.android.ver13.AA_FullMatchStatFragment.numberOfDE_A;
import static com.example.android.ver13.AA_FullMatchStatFragment.numberOfDE_B;
import static com.example.android.ver13.AA_FullMatchStatFragment.numberOfWinners_A;
import static com.example.android.ver13.AA_FullMatchStatFragment.numberOfWinners_B;
import static com.example.android.ver13.AA_FullMatchStatFragment.numberOfuE_A;
import static com.example.android.ver13.AA_FullMatchStatFragment.numberOfuE_B;
import static com.example.android.ver13.SearchedMatchsActivity.selId;

public class AA_Hints2Fragment extends Fragment {

    private TennisHelper mDbHelper;
    int _winners, _aces, _DE, _UE;

    public AA_Hints2Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.aa_activity_hints2, container, false);
        mDbHelper = new TennisHelper(getContext());
        return rootView;
    }


    public void onViewCreated(View view, Bundle savedInstanceState) {

        String list_by_W[] = {getString(R.string.hint_by_winner_1), getString(R.string.hint_by_winner_2), getString(R.string.hint_by_winner_3),
                getString(R.string.hint_by_winner_4), getString(R.string.hint_by_winner_5)};

        String list_by_UE[] = {getString(R.string.hint_by_UE_1), getString(R.string.hint_by_UE_2),
                getString(R.string.hint_by_UE_3), getString(R.string.hint_by_UE_4)};

        String list_by_DE[] = {getString(R.string.hint_by_DE_1), getString(R.string.hint_by_DE_2),
                getString(R.string.hint_by_DE_3), getString(R.string.hint_by_DE_4)};

        String list_by_A[] = {getString(R.string.hint_by_ace_1), getString(R.string.hint_by_ace_2), getString(R.string.hint_by_ace_3)};

        if (getView() != null) {
            TextView header2 = (TextView) getView().findViewById(R.id.headerTV2);
            TextView DDt2 = (TextView) getView().findViewById(R.id.aDDtoTV2);
            TextView hint1B = (TextView) getView().findViewById(R.id.hint1BTV);
            TextView hint2B = (TextView) getView().findViewById(R.id.hint2BTV);
            TextView hint3B = (TextView) getView().findViewById(R.id.hint3BTV);
            TextView hint4B = (TextView) getView().findViewById(R.id.hint4BTV);
            DDt2.setText(mDbHelper.getNameById("full_B_NameSAVE", selId));
            header2.setText(getString(R.string.some_hints) + " " + mDbHelper.getNameById("full_A_NameSAVE", selId));

            hint1B.setText(list_by_W[getHint1B()]);
            hint2B.setText(list_by_A[getHint2B()]);
            hint3B.setText(list_by_UE[getHint3B()]);
            hint4B.setText(list_by_DE[getHint4B()]);
        }
    }

    private int getHint1B() {

        if ((numberOfWinners_A > numberOfWinners_B) && (numberOfWinners_B <= 0.3 * numberOfWinners_A)) {
            _winners = 2;
        } else if (numberOfWinners_A > numberOfWinners_B) {
            _winners = 3;
        } else if ((numberOfWinners_A < numberOfWinners_B) && (numberOfWinners_A <= 0.3 * numberOfWinners_B)) {
            _winners = 0;
        } else if (numberOfWinners_A < numberOfWinners_B) {
            _winners = 1;
        } else if (numberOfWinners_A == numberOfWinners_B) {
            _winners = 4;
        }
        return _winners;
    }

    private int getHint2B() {

        if (numberOfAces_A > numberOfAces_B) {
            _aces = 1;
        } else if (numberOfAces_A < numberOfAces_B) {
            _aces = 0;
        } else if (numberOfAces_A == numberOfAces_B) {
            _aces = 2;
        }
        return _aces;
    }

    private int getHint3B() {

        if (numberOfuE_A > numberOfuE_B) {
            _UE = 1;
        } else if (numberOfuE_A < numberOfuE_B) {
            _UE = 0;
        } else if (numberOfuE_A == numberOfuE_B && numberOfuE_A != 0) {
            _UE = 2;
        } else if (numberOfuE_A == 0 && numberOfuE_B == 0) {
            _UE = 3;
        }
        return _UE;
    }

    private int getHint4B() {

        if (numberOfDE_A > numberOfDE_B) {
            _DE = 1;
        } else if (numberOfDE_A < numberOfDE_B) {
            _DE = 0;
        } else if (numberOfDE_A == numberOfDE_B && numberOfDE_A != 0) {
            _DE = 2;
        } else if (numberOfDE_A == 0 && numberOfDE_B == 0) {
            _DE = 3;
        }
        return _DE;
    }
}