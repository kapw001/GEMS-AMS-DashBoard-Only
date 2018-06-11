package cashkaro.com.dashboad.model;

import android.graphics.Color;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.EntryXComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by yasar on 30/8/17.
 */

public class Temp {

//
//    private void loadBarchartData() {
//
//        float groupSpace = 0.4f;
//        float barSpace = 0.0f; // x4 DataSet
//        float barWidth = 0.3f; // x4 DataSet
//        // (0.2 + 0.03) * 4 + 0.08 = 1.00 -> interval per "group"
//
//        int groupCount = 6;
//        int startYear = 0;
//        int endYear = startYear + groupCount;
//
//        List<BarEntry> entries = new ArrayList<>();
//        entries.add(new BarEntry(0f, 30f));
//        entries.add(new BarEntry(1f, 80f));
//        entries.add(new BarEntry(2f, 60f));
//        entries.add(new BarEntry(3f, 50f));
//        entries.add(new BarEntry(4f, 70f));
//        entries.add(new BarEntry(5f, 60f));
//
//        BarDataSet set1 = new BarDataSet(entries, "BarDataSet");
//
//        Collections.sort(entries, new EntryXComparator());
//
//
//        List<BarEntry> entries1 = new ArrayList<>();
//        entries1.add(new BarEntry(0f, 10f));
//        entries1.add(new BarEntry(1f, 40f));
//        entries1.add(new BarEntry(2f, 20f));
//        entries1.add(new BarEntry(3f, 90f));
//        entries1.add(new BarEntry(4f, 30f));
//        entries1.add(new BarEntry(5f, 60f));
//
//        Collections.sort(entries1, new EntryXComparator());
//        BarDataSet set2 = new BarDataSet(entries1, "Visitors 1");
//
//        ArrayList<IBarDataSet> iBarDataSets = new ArrayList<>();
//        iBarDataSets.add(set1);
//        iBarDataSets.add(set2);
////
//        set1.setColor(ColorTemplate.getHoloBlue());
//        set1.setHighLightColor(Color.rgb(244, 117, 117));
//
//        set2.setColor(Color.GREEN);
//
//
//        final ArrayList xAxisLabels = new ArrayList<>();
//        xAxisLabels.add("JAN");
//        xAxisLabels.add("FEB");
//        xAxisLabels.add("MAR");
//        xAxisLabels.add("APR");
//        xAxisLabels.add("MAY");
//        xAxisLabels.add("JUN");
//
////        mChart.setDescription("");    // Hide the description
////        mChart.getAxisLeft().setDrawLabels(false);
////        mChart.getAxisRight().setDrawLabels(false);
////        mChart.getXAxis().setDrawLabels(false);
//
//        XAxis xAxis = mBarChart.getXAxis();
////        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
////        xAxis.setDrawGridLines(false);
////        xAxis.setDrawAxisLine(false);
////        xAxis.setCenterAxisLabels(true);
//
//        xAxis.setGranularity(1f);
//        xAxis.setGranularityEnabled(true);
//        xAxis.setCenterAxisLabels(true);
//        xAxis.setDrawGridLines(false);
////        xAxis.setAxisMaximum(6);
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisLabels));

//        mBarChart.setDescription(null);
//        mBarChart.getLegend().setEnabled(false);
//        mBarChart.getAxisRight().setEnabled(false);
//
//
//        BarData data = new BarData(set1, set2);
//        data.setValueFormatter(new LargeValueFormatter());
//        data.setValueTextColor(Color.WHITE);
//        data.setValueTextSize(0f);
//
//        mBarChart.setPinchZoom(false);
//        mBarChart.setDoubleTapToZoomEnabled(false);
//        mBarChart.setData(data);
//        mBarChart.setFitBars(true);
//        mBarChart.animateXY(1000, 1000);
//
//
//        mBarChart.getBarData().setBarWidth(barWidth);
//        mBarChart.getXAxis().setAxisMinimum(0);
//        mBarChart.getXAxis().setAxisMaximum(0 + mBarChart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
//        mBarChart.groupBars(0, groupSpace, barSpace);
//        mBarChart.invalidate();
//
//    }



//
//    private void loadLinechartData() {
//
//        ArrayList<Entry> entries = new ArrayList<>();
//        entries.add(new Entry(0f, 20));
//        entries.add(new Entry(1f, 10));
//        entries.add(new Entry(2f, 40));
//        entries.add(new Entry(3f, 80));
//        entries.add(new Entry(4f, 10));
//        entries.add(new Entry(5f, 50));
//
//        Collections.sort(entries, new EntryXComparator());
//        LineDataSet set1 = new LineDataSet(entries, "Visitors 1");
//
//        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
//
//        ArrayList<Entry> entries1 = new ArrayList<>();
//        entries1.add(new Entry(0f, 90));
//        entries1.add(new Entry(1f, 30));
//        entries1.add(new Entry(2f, 60));
//        entries1.add(new Entry(3f, 30));
//        entries1.add(new Entry(4f, 40));
//        entries1.add(new Entry(5f, 55));
//
//        Collections.sort(entries1, new EntryXComparator());
//        LineDataSet set2 = new LineDataSet(entries1, "Visitors 1");
//
//
//        set2.setAxisDependency(YAxis.AxisDependency.LEFT);
//        set2.setLineWidth(3);
//
//        ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
//        iLineDataSets.add(set1);
//        iLineDataSets.add(set2);
//
//        set1.setColor(ColorTemplate.getHoloBlue());
//        set1.setLineWidth(3);
////        set1.setCircleColor(ColorTemplate.getHoloBlue());
////        set1.setLineWidth(2f);
//////        set1.setCircleRadius(3f);
////        set1.setFillAlpha(65);
////        set1.setFillColor(ColorTemplate.getHoloBlue());
////        set1.setHighLightColor(Color.rgb(244, 117, 117));
////        set1.setDrawCircleHole(true);
//
//        final ArrayList<String> xAxisLabels = new ArrayList<>();
//        xAxisLabels.add("JAN");
//        xAxisLabels.add("FEB");
//        xAxisLabels.add("MAR");
//        xAxisLabels.add("APR");
//        xAxisLabels.add("MAY");
//        xAxisLabels.add("JUN");
//
////        mChart.setDescription("");    // Hide the description
////        mChart.getAxisLeft().setDrawLabels(false);
////        mChart.getAxisRight().setDrawLabels(false);
////        mChart.getXAxis().setDrawLabels(false);
//
//        XAxis xAxis = mLineChart.getXAxis();
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setDrawGridLines(false);
//        xAxis.setDrawAxisLine(false);
//        xAxis.setGranularity(1f);
//        xAxis.setGranularityEnabled(true);
//        xAxis.setCenterAxisLabels(false);
//        xAxis.setAxisLineWidth(.5f);
//        xAxis.setValueFormatter(new IndexAxisValueFormatter(mMonths));
//
//
//        mLineChart.setDescription(null);
//        mLineChart.getLegend().setEnabled(false);
//        mLineChart.getAxisRight().setEnabled(false);
//
//
//        LineData data = new LineData(iLineDataSets);
//        data.setValueTextColor(Color.WHITE);
//        data.setValueTextSize(0f);
//
//
//        mLineChart.setPinchZoom(false);
//        mLineChart.setDoubleTapToZoomEnabled(false);
//        mLineChart.setData(data);
//        mLineChart.animateXY(1000, 1000);
//        //mChart.invalidate();
//
//        data.setValueFormatter(new LargeValueFormatter());
//
//
//        // dont forget to refresh the drawing
//        mLineChart.invalidate();
//
//    }
}
