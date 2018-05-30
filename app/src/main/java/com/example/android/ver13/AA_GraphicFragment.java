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
import com.github.mikephil.charting.components.YAxis;
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

import static com.example.android.ver13.AA_FullMatchStatFragment.numberOfAces_A;
import static com.example.android.ver13.AA_FullMatchStatFragment.numberOfAces_B;
import static com.example.android.ver13.AA_FullMatchStatFragment.numberOfDE_A;
import static com.example.android.ver13.AA_FullMatchStatFragment.numberOfDE_B;
import static com.example.android.ver13.AA_FullMatchStatFragment.numberOfWinners_A;
import static com.example.android.ver13.AA_FullMatchStatFragment.numberOfWinners_B;
import static com.example.android.ver13.AA_FullMatchStatFragment.numberOfuE_A;
import static com.example.android.ver13.AA_FullMatchStatFragment.numberOfuE_B;
import static com.example.android.ver13.SearchedMatchsActivity.selId;


public class AA_GraphicFragment extends Fragment {

    private TennisHelper mDbHelper;

    private BarChart barChart;
    private PieChart pieChart;
    private PieChart pieChart2;
    int wA, wB, aA, aB, ueA, ueB, deA, deB;
    String nA, nB;



    public AA_GraphicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.aa_activity_graphic, container, false);
        mDbHelper = new TennisHelper(getContext());
        return rootView;
    }


    public void onViewCreated(View view, Bundle savedInstanceState) {

        wA = numberOfWinners_A;
        wB = numberOfWinners_B;
        aA = numberOfAces_A;
        aB = numberOfAces_B;
        ueA = numberOfuE_A;
        ueB = numberOfuE_B;
        deA = numberOfDE_A;
        deB = numberOfDE_B;

        String descToPC[] = {getString(R.string.Winner), getString(R.string.Ace), getString(R.string.UE), getString(R.string.DE)};
        Integer dataForPlA[] = {wA, aA, ueB, deB};
        Integer dataForPlB[] = {wB, aB, ueA, deA};

        nA = mDbHelper.getNameById("full_A_NameSAVE", selId);
        nB = mDbHelper.getNameById("full_B_NameSAVE", selId);

        barChart = (BarChart) getView().findViewById(R.id._barChart);

        TextView barChartDesc_1 = (TextView) getView().findViewById(R.id.descBar_12);

        barChartDesc_1.setText(getString(R.string.BarChartDescFor1Match)+ "\n\n" + getString(R.string.UEdesc) + "\n"  + getString(R.string.DEdesc));

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, wA));
        barEntries.add(new BarEntry(1, aA));
        barEntries.add(new BarEntry(2, ueA));
        barEntries.add(new BarEntry(3, deA));

        ArrayList<BarEntry> barEntries1 = new ArrayList<>();
        barEntries1.add(new BarEntry(0, wB));
        barEntries1.add(new BarEntry(1, aB));
        barEntries1.add(new BarEntry(2, ueB));
        barEntries1.add(new BarEntry(3, deB));

        BarDataSet barDataSet = new BarDataSet(barEntries, nA);
        barDataSet.setValueTextSize(12);
        barDataSet.setColor(getResources().getColor(R.color.aqua));
        barDataSet.setValueFormatter(new MyValueFormatter());

        BarDataSet barDataSet1 = new BarDataSet(barEntries1, nB);
        barDataSet1.setColor(getResources().getColor(R.color.orange));
        barDataSet1.setValueFormatter(new MyValueFormatter());
        barDataSet1.setValueTextSize(12);

        float groupSpace = 0.06f;
        float barSpace = 0.02f;
        float barWidth = 0.45f;

        BarData data1 = new BarData(barDataSet, barDataSet1);
        data1.setBarWidth(barWidth);

        barChart.setData(data1);
        barChart.setFitBars(true);
        barChart.invalidate();
        barChart.groupBars(1f, groupSpace, barSpace);
        barChart.getAxisRight().setEnabled(false);
        barChart.getAxisLeft().setEnabled(false);
        barChart.animateY(3000);
        barChart.getDescription().setText("");
        barChart.setScaleEnabled(false);
        barChart.setTouchEnabled(false);

        String[] values1 = new String[]{"stub1", getString(R.string.Winner), getString(R.string.Ace), getString(R.string.UE), getString(R.string.DE), "stub2", "stub3"};

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new MyXAxis(values1));
        xAxis.setAxisLineColor(getResources().getColor(R.color.black));
        xAxis.setAxisLineWidth(2f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setCenterAxisLabels(true);
        xAxis.setGranularity(1);
        xAxis.setAxisMinimum(1);
        xAxis.setAxisMaximum(barEntries1.size() + 1);

        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setAxisMinimum(-0.1f);

        pieChart = (PieChart) getView().findViewById(R.id._pieChart);
        pieChart2 = (PieChart) getView().findViewById(R.id._pieChart2);

        TextView pieChartDesc = (TextView) getView().findViewById(R.id.descPie);
        pieChartDesc.setText(getString(R.string.PieChartDescForPlayer) + "\n\n" + getString(R.string.descForCenterNumber) + "\n\n" +
                      getString(R.string.UEdesc) + "\n"  + getString(R.string.DEdesc));

        TextView name1PC = (TextView) getView().findViewById(R.id._name1PC);
        TextView name2PC = (TextView) getView().findViewById(R.id._name2PC);
        name1PC.setText(nA);
        name2PC.setText(nB);

        ArrayList<PieEntry> entries = new ArrayList<>();

        for (int i = 0; i < dataForPlA.length; i++) {
            if (dataForPlA[i] != 0) {
                entries.add(new PieEntry(dataForPlA[i], descToPC[i]));
            }
        }

        ArrayList<PieEntry> entries1 = new ArrayList<>();
        for (int i = 0; i < dataForPlB.length; i++) {
            if (dataForPlB[i] != 0) {
                entries1.add(new PieEntry(dataForPlB[i], descToPC[i]));
            }
        }


        PieDataSet set = new PieDataSet(entries, "");
        PieDataSet set1 = new PieDataSet(entries1, "");

        set.setSliceSpace(3f);
        set.setColors(getResources().getColor(R.color.yellow), getResources().getColor(R.color.red),
                getResources().getColor(R.color.deepskyblue), getResources().getColor(R.color.green));

        set1.setSliceSpace(3f);
        set1.setColors(getResources().getColor(R.color.yellow), getResources().getColor(R.color.red),
                getResources().getColor(R.color.deepskyblue), getResources().getColor(R.color.green));


        PieData data = new PieData(set);
        PieData data2 = new PieData(set1);
        pieChart.setData(data);
        pieChart.invalidate();
        pieChart.animateXY(3000, 3000);
        pieChart.getDescription().setText("");
        pieChart.setDrawSliceText(false);
        pieChart.setUsePercentValues(true);
        pieChart.setCenterText(String.valueOf(wA + aA + ueB + deB));

        pieChart2.setData(data2);
        pieChart2.invalidate();
        pieChart2.animateXY(3000, 3000);
        pieChart2.setUsePercentValues(true);
        pieChart2.setDrawSliceText(false);
        pieChart2.getDescription().setText("");
        pieChart2.setCenterText(String.valueOf(wB + aB + ueA + deA));


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

}
