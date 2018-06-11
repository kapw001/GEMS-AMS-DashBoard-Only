package cashkaro.com.dashboad;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
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
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.EntryXComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cashkaro.com.dashboad.adapter.GridViewRecyclerAdapter;
import cashkaro.com.dashboad.model.CountTest;
import cashkaro.com.dashboad.model.DataSet;
import cashkaro.com.dashboad.model.DataSetUpdate;
import cashkaro.com.dashboad.model.PassValuesTwoActivity;
import cashkaro.com.dashboad.model.SchoolList;
import cashkaro.com.dashboad.model.TestList;
import cashkaro.com.dashboad.model.VisitorList;
import cashkaro.com.dashboad.request.WebRequest;

public class DashboardActivityBackup extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnChartValueSelectedListener, View.OnClickListener, GridViewRecyclerAdapter.OnItemClickListener, OnChartGestureListener {
    protected BarChart mBarChart;
    private LineChart mLineChart;

    protected String[] mMonths = new String[]{
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec"
    };

    protected Typeface mTfRegular;
    protected Typeface mTfLight;
    private static final String TAG = "MainActivity";

    private RelativeLayout linelay, barlay;
    private ImageView linechartimg, barchartimg;

    private RecyclerView schoolSelectedListView;


    private GridViewRecyclerAdapter gridViewRecyclerAdapter;
    private List<SchoolList> schoolListList;

    private ImageView leftarrow, rightarrow;
    public int selectedPosition = 0;
    private LinearLayoutManager linearLayoutManager;

    private ImageView filter;

    private PassValuesTwoActivity passValuesTwoActivity = null;


    private TextView schoolsselected, startrange, endrange, totalvisitorcount;

    private WebRequest webRequest;

    private TextView maxvisitorindays, maximunvisitorhours;
    private Map<Integer, Map<Integer, List<CountTest>>> schoolCountByMonthMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Check if the version of Android is Lollipop or higher
        if (Build.VERSION.SDK_INT >= 21) {

            // Set the status bar to dark-semi-transparentish
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);


        }
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        webRequest = WebRequest.getWebRequest(this);

        schoolsselected = (TextView) findViewById(R.id.schoolsselected);
        startrange = (TextView) findViewById(R.id.startrange);
        endrange = (TextView) findViewById(R.id.endrange);
        totalvisitorcount = (TextView) findViewById(R.id.totalvisitorcount);
        maxvisitorindays = (TextView) findViewById(R.id.maxvisitorindays);
        maximunvisitorhours = (TextView) findViewById(R.id.maximunvisitorhours);

        startrange.setText(cashkaro.com.dashboad.Utils.getMonthByName(cashkaro.com.dashboad.Utils.minusDays(cashkaro.com.dashboad.Utils.convertDateToString(new Date()))) + "," + cashkaro.com.dashboad.Utils.getDaysByNumber(cashkaro.com.dashboad.Utils.minusDays(cashkaro.com.dashboad.Utils.convertDateToString(new Date()))));
        endrange.setText(cashkaro.com.dashboad.Utils.getMonthByName(cashkaro.com.dashboad.Utils.convertDateToString(new Date())) + "," + cashkaro.com.dashboad.Utils.getDaysByNumber(cashkaro.com.dashboad.Utils.convertDateToString(new Date())));


        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        schoolListList = new ArrayList<>();

        schoolSelectedListView = (RecyclerView) findViewById(R.id.schoollistselectedview);

        leftarrow = (ImageView) findViewById(R.id.leftarrow);
        rightarrow = (ImageView) findViewById(R.id.rightarrow);

        leftarrow.setOnClickListener(this);
        rightarrow.setOnClickListener(this);

        linelay = (RelativeLayout) findViewById(R.id.linelay);
        barlay = (RelativeLayout) findViewById(R.id.barlay);

        linechartimg = (ImageView) findViewById(R.id.linechartimg);
        barchartimg = (ImageView) findViewById(R.id.barchartimg);

        linelay.setOnClickListener(this);
        barlay.setOnClickListener(this);


        mTfRegular = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        mTfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");


        gridViewRecyclerAdapter = new GridViewRecyclerAdapter(this, schoolListList);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        schoolSelectedListView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView

        schoolSelectedListView.setHasFixedSize(true);
        schoolSelectedListView.setAdapter(gridViewRecyclerAdapter);

        schoolSelectedListView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int firstVisiblePosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();

                Log.e(TAG, "onScrolled: " + firstVisiblePosition);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


            }
        });


        mBarChart = (BarChart) findViewById(R.id.barchart);
        mLineChart = (LineChart) findViewById(R.id.linechart);

        changeImage(R.id.linelay);


        webRequest.getSchoolList(webRequest.getParms(), new WebRequest.CallBackWebResponse() {
            @Override
            public void showSchoolData(List<SchoolList> schoolLists) {
                gridViewRecyclerAdapter.update(schoolLists);

                List<Integer> universityIdlist = new ArrayList<Integer>();

                for (int i = 0; i < schoolLists.size(); i++) {
                    universityIdlist.add(schoolLists.get(i).getId());
                }

                getVisitorData(cashkaro.com.dashboad.Utils.minusDays(cashkaro.com.dashboad.Utils.convertDateToString(new Date())), cashkaro.com.dashboad.Utils.convertDateToString(new Date()), universityIdlist);


            }

            @Override
            public void showVisitorData(List<VisitorList> visitorLists) {

            }

            @Override
            public void fail(String failMsg) {

                Toast.makeText(DashboardActivityBackup.this, "" + failMsg, Toast.LENGTH_SHORT).show();

            }
        });


    }


    public void getVisitorData(String startdate, String enddate, List<Integer> uList) {
        webRequest.getVisitorData(webRequest.getParms(startdate, enddate, uList), new WebRequest.CallBackWebResponse() {
            @Override
            public void showSchoolData(List<SchoolList> schoolLists) {


            }

            @Override
            public void showVisitorData(List<VisitorList> visitorLists) {


                seperateDate(visitorLists);

                loadDataChart(visitorLists);
            }

            @Override
            public void fail(String failMsg) {

            }
        });
    }


    private void loadDataChart(List<VisitorList> visitorLists) {
        schoolCountByMonthMap = new HashMap<Integer, Map<Integer, List<CountTest>>>();


        List<TestList> testLists = new ArrayList<>();

        for (int i = 0; i < visitorLists.size(); i++) {
            VisitorList visitorList = visitorLists.get(i);
            if (visitorList.getDataSet().size() > 0) {
                TestList testList = new TestList();
                testList.setId(visitorList.getId());
                testList.setIntegerListMap(loadData(visitorList.getId(), visitorList.getDataSet()));
                testLists.add(testList);
                schoolCountByMonthMap.put(visitorList.getId(), loadData(visitorList.getId(), visitorList.getDataSet()));
            }
//            schoolCountByMonthMap.put(visitorList.getId(), loadData(visitorList.getId(), visitorList.getDataSet()));
        }

//        loadChart();


        List<String> xAxisLabels = cashkaro.com.dashboad.Utils.getMonthsFromTwoDatesS(cashkaro.com.dashboad.Utils.minusDays(cashkaro.com.dashboad.Utils.convertDateToString(new Date())), cashkaro.com.dashboad.Utils.convertDateToString(new Date()));

        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setCenterAxisLabels(false);
        xAxis.setAxisLineWidth(.5f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisLabels));

        mLineChart.setDescription(null);
        mLineChart.getLegend().setEnabled(false);
        mLineChart.getAxisRight().setEnabled(false);


        mLineChart.setPinchZoom(false);
        mLineChart.setDoubleTapToZoomEnabled(false);
        final ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();

        for (int i = 0; i < testLists.size(); i++) {
            TestList testList = testLists.get(i);
            if (testList.getIntegerListMap() != null) {

                List<CountTest> countTestsList = testList.getIntegerListMap().get(testList.getId());
                ArrayList<Entry> entries = new ArrayList<>();
                Log.e(TAG, "loadDataChart: countTestsList  " + countTestsList.size());
                for (int j = 0; j < countTestsList.size(); j++) {
                    CountTest countTest = countTestsList.get(j);
                    Log.e(TAG, "loadDataChart: " + countTest.toString());
                    entries.add(new Entry(j + 0f, countTest.getCount()));
                }

                Collections.sort(entries, new EntryXComparator());
                LineDataSet set1 = new LineDataSet(entries, "Visitors " + i);
                set1.setAxisDependency(YAxis.AxisDependency.LEFT);
                set1.setLineWidth(3);
                iLineDataSets.add(set1);
            }
        }

        Log.e(TAG, "loadDataChart:ILineDataSet size  " + iLineDataSets.size());


        LineData data = new LineData(iLineDataSets);
        data.setValueTextColor(Color.WHITE);
        data.setValueTextSize(0f);

        mLineChart.setData(data);
        mLineChart.animateXY(1000, 1000);
        //mChart.invalidate();

        data.setValueFormatter(new LargeValueFormatter());

        // dont forget to refresh the drawing
        mLineChart.invalidate();


    }


    private void loadChart() {

        List<String> xAxisLabels = cashkaro.com.dashboad.Utils.getMonthsFromTwoDatesS(cashkaro.com.dashboad.Utils.minusDays(cashkaro.com.dashboad.Utils.convertDateToString(new Date())), cashkaro.com.dashboad.Utils.convertDateToString(new Date()));

        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setCenterAxisLabels(false);
        xAxis.setAxisLineWidth(.5f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisLabels));

        mLineChart.setDescription(null);
        mLineChart.getLegend().setEnabled(false);
        mLineChart.getAxisRight().setEnabled(false);


        mLineChart.setPinchZoom(false);
        mLineChart.setDoubleTapToZoomEnabled(false);


        Iterator<Map.Entry<Integer, Map<Integer, List<CountTest>>>> it = schoolCountByMonthMap.entrySet().iterator();
        final ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
        while (it.hasNext()) {

            Map.Entry<Integer, Map<Integer, List<CountTest>>> pair = it.next();

            Log.e(TAG, "loadChart: " + pair.getKey());

            List<CountTest> cL = pair.getValue().get(pair.getKey());//.get(visitorLists.get(0).getId());
            ArrayList<Entry> entries = new ArrayList<>();
            for (int i = 0; i < cL.size(); i++) {
                CountTest countTest = cL.get(i);
                entries.add(new Entry(i, countTest.getCount()));
            }

            Collections.sort(entries, new EntryXComparator());
            LineDataSet set1 = new LineDataSet(entries, "Visitors 1");
            set1.setAxisDependency(YAxis.AxisDependency.LEFT);
            set1.setLineWidth(3);
            iLineDataSets.add(set1);

            Log.e(TAG, "loadChart:  SIze " + iLineDataSets.size());
            if (iLineDataSets.size() > 0) {
                LineData data = new LineData(iLineDataSets);
                data.setValueTextColor(Color.WHITE);
                data.setValueTextSize(0f);

                mLineChart.setData(data);
                mLineChart.animateXY(1000, 1000);
                //mChart.invalidate();

                data.setValueFormatter(new LargeValueFormatter());

                // dont forget to refresh the drawing
                mLineChart.invalidate();
            }

        }


//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                LineData data = new LineData(iLineDataSets);
//                data.setValueTextColor(Color.WHITE);
//                data.setValueTextSize(0f);
//
//                mLineChart.setData(data);
//                mLineChart.animateXY(1000, 1000);
//                //mChart.invalidate();
//
//                data.setValueFormatter(new LargeValueFormatter());
//
//                // dont forget to refresh the drawing
//                mLineChart.invalidate();
//            }
//        }, 5000);


//        for (Map.Entry<Integer, Map<Integer, List<CountTest>>> entry : schoolCountByMonthMap.entrySet()) {
//            System.out.println(entry.getKey() + "/" + entry.getValue());
//
//            List<CountTest> cL = entry.getValue().get(entry.getKey());//.get(visitorLists.get(0).getId());
//            ArrayList<Entry> entries = new ArrayList<>();
//            for (int i = 0; i < cL.size(); i++) {
//                CountTest countTest = cL.get(i);
//                entries.add(new Entry(i + 0f, countTest.getCount()));
//            }
//
//            Collections.sort(entries, new EntryXComparator());
//            LineDataSet set1 = new LineDataSet(entries, "Visitors 1");
//            set1.setAxisDependency(YAxis.AxisDependency.LEFT);
//            set1.setLineWidth(3);
//
//            XAxis xAxis = mLineChart.getXAxis();
//            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//            xAxis.setDrawGridLines(false);
//            xAxis.setDrawAxisLine(false);
//            xAxis.setGranularity(1f);
//            xAxis.setGranularityEnabled(true);
//            xAxis.setCenterAxisLabels(false);
//            xAxis.setAxisLineWidth(.5f);
//            xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisLabels));
//
//            mLineChart.setDescription(null);
//            mLineChart.getLegend().setEnabled(false);
//            mLineChart.getAxisRight().setEnabled(false);
//
//
//            LineData data = new LineData(set1);
//            data.setValueTextColor(Color.WHITE);
//            data.setValueTextSize(0f);
//
//
//            mLineChart.setPinchZoom(false);
//            mLineChart.setDoubleTapToZoomEnabled(false);
//            mLineChart.setData(data);
//            mLineChart.animateXY(1000, 1000);
//            //mChart.invalidate();
//
//            data.setValueFormatter(new LargeValueFormatter());
//
//
//            // dont forget to refresh the drawing
//            mLineChart.invalidate();
//        }
    }


    private Map<Integer, List<CountTest>> loadData(int id, List<DataSet> dataSetList1) {
        List<DataSet> dataSetList = dataSetList1;
        List<String> mL = new ArrayList<>();
        for (int i = 0; i < dataSetList.size(); i++) {
            DataSet dataSet = dataSetList.get(i);
            mL.add(cashkaro.com.dashboad.Utils.getMonthByName(cashkaro.com.dashboad.Utils.convertDateToString(cashkaro.com.dashboad.Utils.convertStringToDateN(dataSet.getCheckIn()))));
        }
        Set<String> mLSet = new HashSet<>();
        mLSet.addAll(mL);

        List<CountTest> list = new ArrayList<>();
        for (String s : mLSet) {
            CountTest countTest = new CountTest();
            countTest.setName(s);
            int c = 0;
            for (int i = 0; i < mL.size(); i++) {

                String s1 = mL.get(i);
                if (s.equalsIgnoreCase(s1)) {
                    c++;
                }
            }
            countTest.setCount(c);
            list.add(countTest);
        }
        Map<Integer, List<CountTest>> listMap = new HashMap<>();
        listMap.put(id, list);
//
//        for (int i = 0; i < list.size(); i++) {
//            CountTest countTest = list.get(i);
//
//            Log.e(TAG, "loadData: " + countTest.getName() + "   :    " + countTest.getCount());
//        }

        return listMap;

    }


    private void seperateDate(List<VisitorList> visitorLists) {
        List<VisitorList> vList = visitorLists;

        List<DataSet> listNewUpdate = new ArrayList<>();
        List<DataSetUpdate> setdateList = new ArrayList<>();

        for (int i = 0; i < vList.size(); i++) {
            List<DataSet> dataSetList = vList.get(i).getDataSet();
            for (int j = 0; j < dataSetList.size(); j++) {
                DataSet dataSet = dataSetList.get(j);
                listNewUpdate.add(dataSet);
                setdateList.add(new DataSetUpdate(cashkaro.com.dashboad.Utils.convertStringToDateWithTime(dataSet.getCheckIn())));
            }
        }


        totalvisitorcount.setText("" + listNewUpdate.size());


        List<String> weekDaysList = new ArrayList<>();
        List<String> hoursList = new ArrayList<>();

        for (int i = 0; i < setdateList.size(); i++) {
            DataSetUpdate dataSetUpdate = setdateList.get(i);
            weekDaysList.add(cashkaro.com.dashboad.Utils.getDayByFullName(cashkaro.com.dashboad.Utils.convertDateToString(dataSetUpdate.getDate())));
            hoursList.add(cashkaro.com.dashboad.Utils.getHoursU(dataSetUpdate.getDate()));

//            Log.e(TAG, "seperateDate:be h " + cashkaro.com.dashboad.Utils.getHoursU(dataSetUpdate.getDate()));

        }


//        Log.e(TAG, "seperateDate: hours list size  " + hoursList.size());
//
//        for (int i = 0; i < hoursList.size(); i++) {
//            Log.e(TAG, "seperateDate: h " + hoursList.get(i));
//        }

//        for (int i = 0; i < hoursList.size(); i++) {
//            Log.e(TAG, "seperateDate: " + hoursList.get(i));
//        }

        Set<String> dataSets = new HashSet<>();
        dataSets.addAll(weekDaysList);
        List<List<String>> liInSide = new ArrayList<>();

        for (String dataSet : dataSets) {

            List<String> oneByOne = new ArrayList<>();
            for (int i = 0; i < weekDaysList.size(); i++) {
                String dataSet1 = weekDaysList.get(i);
                if (dataSet.equalsIgnoreCase(dataSet1)) {
                    oneByOne.add(dataSet1);
                }
            }

            liInSide.add(oneByOne);
        }

        Set<String> hoursSet = new HashSet<>();
        hoursSet.addAll(hoursList);

//        Log.e(TAG, "seperateDate: hours set size " + hoursSet.size());


        List<List<String>> liInSideHours = new ArrayList<>();

        for (String dataSet : hoursSet) {
            List<String> oneByOne = new ArrayList<>();
            for (int i = 0; i < hoursList.size(); i++) {
                String dataSet1 = hoursList.get(i);
                if (dataSet.equalsIgnoreCase(dataSet1)) {
                    oneByOne.add(dataSet1);
                }
            }
            liInSideHours.add(oneByOne);
        }


        List<Integer> countList = new ArrayList<>();

        for (int i = 0; i < liInSide.size(); i++) {

//            Log.e(TAG, "seperateDate: Hours  list size " + liInSide.get(i).size());
            countList.add(liInSide.get(i).size());
        }

        List<Integer> countListHours = new ArrayList<>();

        for (int i = 0; i < liInSideHours.size(); i++) {

//            Log.e(TAG, "seperateDate: particular list size liInSideHours  " + liInSideHours.get(i).size() + "   " + liInSideHours.get(i).get(0));
            countListHours.add(liInSideHours.get(i).size());
        }


        int maxPos = getMaxValuesPosition(countList);

        String maxvisitorindaystxt = liInSide.get(maxPos).get(0);

//        Log.e(TAG, "seperateDate: " + maxvisitorindaystxt);
        maxvisitorindays.setText(maxvisitorindaystxt);

//
        int maxPosHours = getMaxValuesPosition(countListHours);

        String maxvisitorHours = liInSideHours.get(maxPosHours).get(0);

//        Log.e(TAG, "seperateDate: " + maxvisitorHours);

        maximunvisitorhours.setText(maxvisitorHours);


    }


    private int getMaxValuesPosition(List<Integer> countList) {
        List<Integer> list = countList;
        int limit = list.size();
        int max = Integer.MIN_VALUE;
        int maxPos = -1;
        for (int i = 0; i < limit; i++) {
            int value = list.get(i);
            if (value > max) {
                max = value;
                maxPos = i;
            }
        }

        return maxPos;
    }


    private void loadLinechartData() {

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0f, 20));
        entries.add(new Entry(1f, 10));
        entries.add(new Entry(2f, 40));
        entries.add(new Entry(3f, 80));
        entries.add(new Entry(4f, 10));
        entries.add(new Entry(5f, 50));

        Collections.sort(entries, new EntryXComparator());
        LineDataSet set1 = new LineDataSet(entries, "Visitors 1");

        set1.setAxisDependency(YAxis.AxisDependency.LEFT);

        ArrayList<Entry> entries1 = new ArrayList<>();
        entries1.add(new Entry(0f, 90));
        entries1.add(new Entry(1f, 30));
        entries1.add(new Entry(2f, 60));
        entries1.add(new Entry(3f, 30));
        entries1.add(new Entry(4f, 40));
        entries1.add(new Entry(5f, 55));

        Collections.sort(entries1, new EntryXComparator());
        LineDataSet set2 = new LineDataSet(entries1, "Visitors 1");


        set2.setAxisDependency(YAxis.AxisDependency.LEFT);
        set2.setLineWidth(3);

        ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
        iLineDataSets.add(set1);
        iLineDataSets.add(set2);

        set1.setColor(ColorTemplate.getHoloBlue());
        set1.setLineWidth(3);
//        set1.setCircleColor(ColorTemplate.getHoloBlue());
//        set1.setLineWidth(2f);
////        set1.setCircleRadius(3f);
//        set1.setFillAlpha(65);
//        set1.setFillColor(ColorTemplate.getHoloBlue());
//        set1.setHighLightColor(Color.rgb(244, 117, 117));
//        set1.setDrawCircleHole(true);

        final ArrayList<String> xAxisLabels = new ArrayList<>();
        xAxisLabels.add("JAN");
        xAxisLabels.add("FEB");
        xAxisLabels.add("MAR");
        xAxisLabels.add("APR");
        xAxisLabels.add("MAY");
        xAxisLabels.add("JUN");

//        mChart.setDescription("");    // Hide the description
//        mChart.getAxisLeft().setDrawLabels(false);
//        mChart.getAxisRight().setDrawLabels(false);
//        mChart.getXAxis().setDrawLabels(false);

        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setCenterAxisLabels(false);
        xAxis.setAxisLineWidth(.5f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(mMonths));


        mLineChart.setDescription(null);
        mLineChart.getLegend().setEnabled(false);
        mLineChart.getAxisRight().setEnabled(false);


        LineData data = new LineData(iLineDataSets);
        data.setValueTextColor(Color.WHITE);
        data.setValueTextSize(0f);


        mLineChart.setPinchZoom(false);
        mLineChart.setDoubleTapToZoomEnabled(false);
        mLineChart.setData(data);
        mLineChart.animateXY(1000, 1000);
        //mChart.invalidate();

        data.setValueFormatter(new LargeValueFormatter());


        // dont forget to refresh the drawing
        mLineChart.invalidate();

    }


    private void loadBarchartData() {

        float groupSpace = 0.4f;
        float barSpace = 0.0f; // x4 DataSet
        float barWidth = 0.3f; // x4 DataSet
        // (0.2 + 0.03) * 4 + 0.08 = 1.00 -> interval per "group"

        int groupCount = 6;
        int startYear = 0;
        int endYear = startYear + groupCount;

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 30f));
        entries.add(new BarEntry(1f, 80f));
        entries.add(new BarEntry(2f, 60f));
        entries.add(new BarEntry(3f, 50f));
        entries.add(new BarEntry(4f, 70f));
        entries.add(new BarEntry(5f, 60f));

        BarDataSet set1 = new BarDataSet(entries, "BarDataSet");

        Collections.sort(entries, new EntryXComparator());


        List<BarEntry> entries1 = new ArrayList<>();
        entries1.add(new BarEntry(0f, 10f));
        entries1.add(new BarEntry(1f, 40f));
        entries1.add(new BarEntry(2f, 20f));
        entries1.add(new BarEntry(3f, 90f));
        entries1.add(new BarEntry(4f, 30f));
        entries1.add(new BarEntry(5f, 60f));

        Collections.sort(entries1, new EntryXComparator());
        BarDataSet set2 = new BarDataSet(entries1, "Visitors 1");

        ArrayList<IBarDataSet> iBarDataSets = new ArrayList<>();
        iBarDataSets.add(set1);
        iBarDataSets.add(set2);
//
        set1.setColor(ColorTemplate.getHoloBlue());
        set1.setHighLightColor(Color.rgb(244, 117, 117));

        set2.setColor(Color.GREEN);


        final ArrayList xAxisLabels = new ArrayList<>();
        xAxisLabels.add("JAN");
        xAxisLabels.add("FEB");
        xAxisLabels.add("MAR");
        xAxisLabels.add("APR");
        xAxisLabels.add("MAY");
        xAxisLabels.add("JUN");

//        mChart.setDescription("");    // Hide the description
//        mChart.getAxisLeft().setDrawLabels(false);
//        mChart.getAxisRight().setDrawLabels(false);
//        mChart.getXAxis().setDrawLabels(false);

        XAxis xAxis = mBarChart.getXAxis();
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setDrawGridLines(false);
//        xAxis.setDrawAxisLine(false);
//        xAxis.setCenterAxisLabels(true);

        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawGridLines(false);
//        xAxis.setAxisMaximum(6);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisLabels));

        mBarChart.setDescription(null);
        mBarChart.getLegend().setEnabled(false);
        mBarChart.getAxisRight().setEnabled(false);


        BarData data = new BarData(set1, set2);
        data.setValueFormatter(new LargeValueFormatter());
        data.setValueTextColor(Color.WHITE);
        data.setValueTextSize(0f);

        mBarChart.setPinchZoom(false);
        mBarChart.setDoubleTapToZoomEnabled(false);
        mBarChart.setData(data);
        mBarChart.setFitBars(true);
        mBarChart.animateXY(1000, 1000);


        mBarChart.getBarData().setBarWidth(barWidth);
        mBarChart.getXAxis().setAxisMinimum(0);
        mBarChart.getXAxis().setAxisMaximum(0 + mBarChart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
        mBarChart.groupBars(0, groupSpace, barSpace);
        mBarChart.invalidate();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);//Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter:
                startActivityForResult(new Intent(DashboardActivityBackup.this, FilterActivity.class).putExtra("passvalues", passValuesTwoActivity), 2);
                overridePendingTransition(R.animator.enter, R.animator.exit);
//                Toast.makeText(getApplicationContext(), "Item 1 Selected", Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        Toast.makeText(this, "Called", Toast.LENGTH_SHORT).show();

        if (resultCode == 2 && requestCode == 2) {

            passValuesTwoActivity = (PassValuesTwoActivity) data.getSerializableExtra("passvalues");

            schoolsselected.setText(passValuesTwoActivity.getSchoolselectedcount());
//            startrange.setText(passValuesTwoActivity.getStartDate());
//            endrange.setText(passValuesTwoActivity.getEnddate());
            String startDate = passValuesTwoActivity.getStartDate();
            String endDate = passValuesTwoActivity.getEnddate();

            startrange.setText(cashkaro.com.dashboad.Utils.getMonthByName(cashkaro.com.dashboad.Utils.getStartDateOfMonth(cashkaro.com.dashboad.Utils.convertStringToDate(startDate))) + "," + cashkaro.com.dashboad.Utils.getDaysByNumber(cashkaro.com.dashboad.Utils.getStartDateOfMonth(cashkaro.com.dashboad.Utils.convertStringToDate(startDate))));
            endrange.setText(cashkaro.com.dashboad.Utils.getMonthByName(cashkaro.com.dashboad.Utils.getEndDateOfMonth(cashkaro.com.dashboad.Utils.convertStringToDate(endDate))) + "," + cashkaro.com.dashboad.Utils.getDaysByNumber(cashkaro.com.dashboad.Utils.getEndDateOfMonth(cashkaro.com.dashboad.Utils.convertStringToDate(endDate))));


            List<String> schoolList = passValuesTwoActivity.getSchoolListList();

            List<SchoolList> schoolLists = new ArrayList<>();
            for (int i = 0; i < schoolList.size(); i++) {
                String schoolName = schoolList.get(i);

            }
            gridViewRecyclerAdapter.update(schoolLists);

        } else {
            Log.e(TAG, "onActivityResult: There is no data come ");
        }


    }


    private void changeschoolSelectedListViewPosition(int id) {

        int Lpos = linearLayoutManager.findLastCompletelyVisibleItemPosition();

        int Fpos = linearLayoutManager.findFirstVisibleItemPosition();

        if (schoolListList.size() > 0) {
//            if (pos >= 0 && pos <= schoolListList.size()) {

            switch (id) {

                case R.id.leftarrow:
                    if (Fpos >= 1) {
                        Fpos--;
                        schoolSelectedListView.smoothScrollToPosition(Fpos);
                        Log.e(TAG, "changeschoolSelectedListViewPosition: left " + Fpos);

                    }
                    return;

                case R.id.rightarrow:
                    Lpos++;
                    schoolSelectedListView.smoothScrollToPosition(Lpos);
                    Log.e(TAG, "changeschoolSelectedListViewPosition:  right  " + Lpos);
                    return;

            }

//            }
        }


    }

    protected float getRandom(float range, float startsfrom) {
        return (float) (Math.random() * range) + startsfrom;
    }


    protected RectF mOnValueSelectedRectF = new RectF();


    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }


    @Override
    public void onNothingSelected() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.linelay:
                changeImage(R.id.linelay);

                break;

            case R.id.barlay:
                changeImage(R.id.barlay);
                break;

            case R.id.leftarrow:
                changeschoolSelectedListViewPosition(R.id.leftarrow);
                break;
            case R.id.rightarrow:
                changeschoolSelectedListViewPosition(R.id.rightarrow);
                break;

        }
    }


    private void changeImage(int id) {

        linelay.setBackgroundResource(R.drawable.unselectedstate);
        barlay.setBackgroundResource(R.drawable.rightunselectedstate);

        linechartimg.setImageResource(R.drawable.ic_linechart_disabled);
        barchartimg.setImageResource(R.drawable.ic_barchart_disabled);

        mBarChart.setVisibility(View.GONE);
        mLineChart.setVisibility(View.GONE);

        switch (id) {

            case R.id.linelay:
                linelay.setBackgroundResource(R.drawable.selectedstate);
                linechartimg.setImageResource(R.drawable.ic_linechart_enabled);
                mLineChart.setVisibility(View.VISIBLE);
                loadLinechartData();
                break;

            case R.id.barlay:

                barlay.setBackgroundResource(R.drawable.rightselectedstate);
                barchartimg.setImageResource(R.drawable.ic_barchart_enabled);
                mBarChart.setVisibility(View.VISIBLE);
                loadBarchartData();
                break;

        }

    }

    @Override
    public void position(SchoolList itemName) {

    }

    @Override
    public void scrollPosition(int pos) {


        Log.e(TAG, "scrollPosition: " + pos + "  se  " + schoolSelectedListView.getChildAdapterPosition(schoolSelectedListView.getFocusedChild()));
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "START, x: " + me.getX() + ", y: " + me.getY());
    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "END, lastGesture: " + lastPerformedGesture);

        // un-highlight values after the gesture is finished and no single-tap
        if (lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP)
            mLineChart.highlightValues(null); // or highlightTouch(null) for callback to onNothingSelected(...)
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i("LongPress", "Chart longpressed.");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.i("DoubleTap", "Chart double-tapped.");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i("SingleTap", "Chart single-tapped.");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        Log.i("Fling", "Chart flinged. VeloX: " + velocityX + ", VeloY: " + velocityY);
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.i("Scale / Zoom", "ScaleX: " + scaleX + ", ScaleY: " + scaleY);
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.i("Translate / Move", "dX: " + dX + ", dY: " + dY);
    }
}


//  List<String> mList = cashkaro.com.dashboad.Utils.getMonthsFromTwoDatesS(cashkaro.com.dashboad.Utils.minusDays(cashkaro.com.dashboad.Utils.convertDateToString(new Date())), cashkaro.com.dashboad.Utils.convertDateToString(new Date()));