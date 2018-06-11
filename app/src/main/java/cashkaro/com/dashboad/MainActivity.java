package cashkaro.com.dashboad;

import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.List;

import cashkaro.com.dashboad.adapter.GridViewRecyclerAdapter;
import cashkaro.com.dashboad.model.SchoolList;

public class MainActivity extends AppCompatActivity implements OnChartValueSelectedListener, View.OnClickListener, GridViewRecyclerAdapter.OnItemClickListener {
    protected BarChart mChart;

    protected String[] mMonths = new String[]{
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec"
    };

    protected String[] mParties = new String[]{
            "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
            "Party Y", "Party Z"
    };

    protected Typeface mTfRegular;
    protected Typeface mTfLight;


    private RelativeLayout linelay, barlay;
    private ImageView linechartimg, barchartimg;

    private RecyclerView schoolSelectedListView;

    private GridViewRecyclerAdapter gridViewRecyclerAdapter;

    private List<SchoolList> schoolListList;

    private ImageView leftarrow, rightarrow;
    public int selectedPosition = 0;
    private GridLayoutManager gridLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setCustomView(R.layout.abs_layout);
//
//        View view = getSupportActionBar().getCustomView();
//        TextView title = (TextView) view.findViewById(R.id.title);
//
//        title.setText("Dashboard");

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

        mChart = (BarChart) findViewById(R.id.chart);
        mChart.setOnChartValueSelectedListener(this);

//        Chart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);

        mChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawGridBackground(false);
        // mChart.setDrawYLabels(false);

        setData(12, 50);
//
//        schoolListList.add(new SchoolList("School 1", ""));
//        schoolListList.add(new SchoolList("School 2", ""));
//        schoolListList.add(new SchoolList("School 3", ""));
//        schoolListList.add(new SchoolList("School 4", ""));
//        schoolListList.add(new SchoolList("School 5", ""));

        gridViewRecyclerAdapter = new GridViewRecyclerAdapter(this, schoolListList);


// set a GridLayoutManager with default vertical orientation and 3 number of columns
        gridLayoutManager = new GridLayoutManager(this, 7);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        gridRecyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        schoolSelectedListView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)); // set LayoutManager to RecyclerView
//        schoolSelectedListView.addItemDecoration(new DividerItemDecoration(this, 0));
//        tabrecyclerView.addItemDecoration(new EqualSpaceItemDecoration(5));
//        tabrecyclerView.addItemDecoration(new SimpleItemDecorator(5));
        schoolSelectedListView.setHasFixedSize(true);
        schoolSelectedListView.setAdapter(gridViewRecyclerAdapter);


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
                Toast.makeText(getApplicationContext(), "Item 1 Selected", Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private static final String TAG = "MainActivity";

    private void changeschoolSelectedListViewPosition(int id) {


//        if (schoolListList.size() > 0 && selectedPosition + 2 <= schoolListList.size()) {
//
//            switch (id) {
//
//                case R.id.leftarrow:
//                    selectedPosition--;
//                    schoolSelectedListView.scrollToPosition(selectedPosition);
//
//                    break;
//
//                case R.id.rightarrow:
//                    selectedPosition++;
//                    schoolSelectedListView.scrollToPosition(selectedPosition);
//                    break;
//
//            }
//
//        }


    }

    protected float getRandom(float range, float startsfrom) {
        return (float) (Math.random() * range) + startsfrom;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    private void setData(int count, float range) {

        float start = 1f;

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        for (int i = (int) start; i < start + count + 1; i++) {
            float mult = (range + 1);
            float val = (float) (Math.random() * mult);

            if (Math.random() * 100 < 25) {
                yVals1.add(new BarEntry(i, val, getResources().getDrawable(R.mipmap.ic_launcher)));
            } else {
                yVals1.add(new BarEntry(i, val));
            }
        }

        BarDataSet set1;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "The year 2017");

//            set1.setDrawIcons(false);

            set1.setColors(ColorTemplate.MATERIAL_COLORS);

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setValueTypeface(mTfLight);
            data.setBarWidth(0.9f);

            mChart.setData(data);
        }
    }

    protected RectF mOnValueSelectedRectF = new RectF();


    @Override
    public void onValueSelected(Entry e, Highlight h) {

        if (e == null)
            return;

        RectF bounds = mOnValueSelectedRectF;
        mChart.getBarBounds((BarEntry) e, bounds);
        MPPointF position = mChart.getPosition(e, YAxis.AxisDependency.LEFT);

        Log.i("bounds", bounds.toString());
        Log.i("position", position.toString());

        Log.i("x-index",
                "low: " + mChart.getLowestVisibleX() + ", high: "
                        + mChart.getHighestVisibleX());

        MPPointF.recycleInstance(position);
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


        switch (id) {

            case R.id.linelay:
                linelay.setBackgroundResource(R.drawable.selectedstate);
                linechartimg.setImageResource(R.drawable.ic_linechart_enabled);
                break;

            case R.id.barlay:

                barlay.setBackgroundResource(R.drawable.rightselectedstate);
                barchartimg.setImageResource(R.drawable.ic_barchart_enabled);

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
}
