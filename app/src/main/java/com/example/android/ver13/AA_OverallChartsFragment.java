package com.example.android.ver13;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.ver13.data.TennisHelper;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;

import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.example.android.ver13.AA_HoleMatchesStatFragment.acesOverall_One;
import static com.example.android.ver13.AA_HoleMatchesStatFragment.acesOverall_Second;
import static com.example.android.ver13.AA_HoleMatchesStatFragment.deOverall_One;
import static com.example.android.ver13.AA_HoleMatchesStatFragment.deOverall_Second;
import static com.example.android.ver13.AA_HoleMatchesStatFragment.oneName;
import static com.example.android.ver13.AA_HoleMatchesStatFragment.oneNameAcesArray;
import static com.example.android.ver13.AA_HoleMatchesStatFragment.oneNameDEArray;
import static com.example.android.ver13.AA_HoleMatchesStatFragment.oneNameUEArray;
import static com.example.android.ver13.AA_HoleMatchesStatFragment.oneNameWinnersArray;
import static com.example.android.ver13.AA_HoleMatchesStatFragment.secondName;
import static com.example.android.ver13.AA_HoleMatchesStatFragment.secondNameAcesArray;
import static com.example.android.ver13.AA_HoleMatchesStatFragment.secondNameDEArray;
import static com.example.android.ver13.AA_HoleMatchesStatFragment.secondNameUEArray;
import static com.example.android.ver13.AA_HoleMatchesStatFragment.secondNameWinnersArray;
import static com.example.android.ver13.AA_HoleMatchesStatFragment.ueOverall_One;
import static com.example.android.ver13.AA_HoleMatchesStatFragment.ueOverall_Second;
import static com.example.android.ver13.AA_HoleMatchesStatFragment.winnersOverall_One;
import static com.example.android.ver13.AA_HoleMatchesStatFragment.winnersOverall_Second;
import static com.example.android.ver13.SearchedMatchsActivity.numbers;

/**
 * A simple {@link Fragment} subclass.
 */
public class AA_OverallChartsFragment extends Fragment {

    private BarChart overall_winner_barChart;
    private PieChart overall_winner_pieChart;
    private BarChart overall_aces_barChart;
    private PieChart overall_aces_pieChart;
    private BarChart overall_ue_barChart;
    private PieChart overall_ue_pieChart;
    private BarChart overall_de_barChart;
    private PieChart overall_de_pieChart;

    TextView winner_overall_charts, pcDW, bcDW, ace_overall_charts, bcDA, pcDA, ue_overall_charts, bcDUE, pcDUE, de_overall_charts, bcDDE, pcDDE;


    private TennisHelper mDbHelper;


    public AA_OverallChartsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.aa_activity_overall_charts, container, false);
        mDbHelper = new TennisHelper(getContext());
        return rootView;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {

        if (getView() != null) {

            winner_overall_charts = (TextView) getView().findViewById(R.id._winner_overall_charts);
            ace_overall_charts = (TextView) getView().findViewById(R.id._ace_overall_charts);
            ue_overall_charts = (TextView) getView().findViewById(R.id._ue_overall_charts);
            de_overall_charts = (TextView) getView().findViewById(R.id._de_overall_charts);

            overall_winner_barChart = (BarChart) getView().findViewById(R.id._overall_winner_barChart);
            overall_winner_pieChart = (PieChart) getView().findViewById(R.id._overall_winner_pieChart);
            pcDW = (TextView) getView().findViewById(R.id._pcDW);
            bcDW = (TextView) getView().findViewById(R.id._bcDW);

            overall_aces_barChart = (BarChart) getView().findViewById(R.id._overall_aces_barChart);
            overall_aces_pieChart = (PieChart) getView().findViewById(R.id._overall_aces_pieChart);
            pcDA = (TextView) getView().findViewById(R.id._pcDA);
            bcDA = (TextView) getView().findViewById(R.id._bcDA);

            overall_ue_barChart = (BarChart) getView().findViewById(R.id._overall_ue_barChart);
            overall_ue_pieChart = (PieChart) getView().findViewById(R.id._overall_ue_pieChart);
            pcDUE = (TextView) getView().findViewById(R.id._pcDUE);
            bcDUE = (TextView) getView().findViewById(R.id._bcDUE);

            overall_de_barChart = (BarChart) getView().findViewById(R.id._overall_de_barChart);
            overall_de_pieChart = (PieChart) getView().findViewById(R.id._overall_de_pieChart);
            pcDDE = (TextView) getView().findViewById(R.id._pcDDE);
            bcDDE = (TextView) getView().findViewById(R.id._bcDDE);


        }

        if (winnersOverall_One != 0 || winnersOverall_Second != 0) {

            setOverallBarChart(oneNameWinnersArray, secondNameWinnersArray, oneName, secondName, overall_winner_barChart, bcDW, getString(R.string.desc_for_barchart_winner_overall_charts));
            setOverallPieChart(winnersOverall_One, winnersOverall_Second, oneName, secondName, overall_winner_pieChart, getString(R.string.desc_for_piechart_winner_overall_charts), pcDW);
        } else {
            overall_winner_barChart.setNoDataText(getString(R.string.no_dat_to_chart));
            overall_winner_pieChart.setNoDataText(getString(R.string.no_dat_to_chart));
        }


        if (acesOverall_One != 0 || acesOverall_Second != 0) {

            setOverallBarChart(oneNameAcesArray, secondNameAcesArray, oneName, secondName, overall_aces_barChart, bcDA, getString(R.string.desc_for_barchart_ace_overall_charts));
            setOverallPieChart(acesOverall_One, acesOverall_Second, oneName, secondName, overall_aces_pieChart, getString(R.string.desc_for_piechart_ace_overall_charts), pcDA);
        } else {
            overall_aces_barChart.setNoDataText(getString(R.string.no_dat_to_chart));
            overall_aces_pieChart.setNoDataText(getString(R.string.no_dat_to_chart));
        }

        if (ueOverall_One != 0 || ueOverall_Second != 0) {

            setOverallBarChart(oneNameUEArray, secondNameUEArray, oneName, secondName, overall_ue_barChart, bcDUE, getString(R.string.desc_for_barchart_ue_overall_charts));
            setOverallPieChart(ueOverall_One, ueOverall_Second, oneName, secondName, overall_ue_pieChart, getString(R.string.desc_for_piechart_ue_overall_charts), pcDUE);

        } else {
            overall_ue_barChart.setNoDataText(getString(R.string.no_dat_to_chart));
            overall_ue_pieChart.setNoDataText(getString(R.string.no_dat_to_chart));
        }


        if (deOverall_One != 0 || deOverall_Second != 0) {
            setOverallBarChart(oneNameDEArray, secondNameDEArray, oneName, secondName, overall_de_barChart, bcDDE, getString(R.string.desc_for_barchart_de_overall_charts));
            setOverallPieChart(deOverall_One, deOverall_Second, oneName, secondName, overall_de_pieChart, getString(R.string.desc_for_piechart_de_overall_charts), pcDDE);


        }
        else {
            overall_de_barChart.setNoDataText(getString(R.string.no_dat_to_chart));
            overall_de_pieChart.setNoDataText(getString(R.string.no_dat_to_chart));
        }


    }

    public class MyValueFormatter implements IValueFormatter {

        private DecimalFormat mFormat;

        public MyValueFormatter() {
            mFormat = new DecimalFormat("###,###,##0"); // use one decimal
        }

        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            // write your logic here
            return mFormat.format(value); // e.g. append a dollar-sign
        }
    }

    public class MyXAxis implements IAxisValueFormatter {


        private String[] mValues;

        public MyXAxis(String[] nValues) {
            this.mValues = nValues;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mValues[(int) value];
        }
    }

    public void setOverallPieChart(Integer al_1, Integer al_2, String oneN, String secondN, PieChart pC, String s, TextView tv) {

        ArrayList<PieEntry> entries = new ArrayList<>();


        if (al_1 != 0) {
            entries.add(new PieEntry(al_1, oneN));
        }
        if (al_2 != 0) {
            entries.add(new PieEntry(al_2, secondN));
        }


        PieDataSet set = new PieDataSet(entries, "");
        set.setValueFormatter(new MyValueFormatter());

        set.setSliceSpace(3f);
        set.setColors(getResources().getColor(R.color.aqua), getResources().getColor(R.color.orange));

        PieData data = new PieData(set);

        pC.setData(data);
        pC.invalidate();
        pC.animateXY(3000, 3000);
        pC.getDescription().setText("");
        pC.setDrawSliceText(false);
        pC.setCenterText(String.valueOf(numbers.size()));

        DecimalFormat oneDForm = new DecimalFormat("0.0");

        tv.setText(s + "\n\n" + oneN + " - " + oneDForm.format((float) al_1 / numbers.size()) + "\n" + secondN + " - " + oneDForm.format((float) al_2 / numbers.size()));

    }

    public void setOverallBarChart(ArrayList<Integer> al_1, ArrayList<Integer> al_2, String oneN, String secondN, BarChart bC, TextView tv, String s2) {

        ArrayList<BarEntry> barEntries = new ArrayList<>();

        for (int i = 0; i < al_1.size(); i++) {
            barEntries.add(new BarEntry(i, al_1.get(i)));
        }
        ArrayList<BarEntry> barEntries1 = new ArrayList<>();

        for (int i = 0; i < al_2.size(); i++) {
            barEntries1.add(new BarEntry(i, al_2.get(i)));
        }

        String[] values1 = new String[numbers.size() + 3];

        values1[0] = "stub2";
        values1[numbers.size() + 1] = "stub2";
        values1[numbers.size() + 2] = "stub3";

        for (int i = 1; i < numbers.size() + 1; i++) {
            values1[i] = mDbHelper.getNameById("date", numbers.get(i - 1));
        }


        BarDataSet barDataSet = new BarDataSet(barEntries, oneN);
        barDataSet.setValueFormatter(new MyValueFormatter());
        barDataSet.setColor(getResources().getColor(R.color.aqua));
        barDataSet.setValueTextSize(12);

        BarDataSet barDataSet1 = new BarDataSet(barEntries1, secondN);
        barDataSet1.setValueFormatter(new MyValueFormatter());
        barDataSet1.setColor(getResources().getColor(R.color.orange));
        barDataSet1.setValueTextSize(12);

        float groupSpace = 0.06f;
        float barSpace = 0.02f;
        float barWidth = 0.45f;

        BarData data1 = new BarData(barDataSet, barDataSet1);
        data1.setBarWidth(barWidth);
        bC.setData(data1);
        bC.invalidate();
        bC.groupBars(1f, groupSpace, barSpace);
        bC.getAxisRight().setEnabled(false);
        bC.getAxisLeft().setEnabled(false);
        bC.animateY(3000);
        bC.getDescription().setText("");
        bC.setScaleEnabled(false);


        XAxis xAxis = bC.getXAxis();

        xAxis.setValueFormatter(new MyXAxis(values1));

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setCenterAxisLabels(true);
        xAxis.setGranularity(1);
        xAxis.setAxisMinimum(1);
        xAxis.setAxisMaximum(barEntries1.size() + 1);

        if (barEntries1.size() > 4) {
            float h = (float) barEntries1.size() / 4;
            bC.zoom(h, 1, 1, 1);
        }
        tv.setText(s2);
    }
}
