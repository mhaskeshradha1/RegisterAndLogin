package com.montclair.mhaskes1.registerandlogin;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.montclair.mhaskes1.registerandlogin.model.User;
import com.montclair.mhaskes1.registerandlogin.util.Constants;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * Activity for plotting graph
 *
 */
public class BarActivity extends AppCompatActivity {

    /**
     * barchart is object used to plot graph
     *
     */
    BarChart barchart;
    LineChart mChart;

    /**
     * variable to store ratio
     */
    Float aFloat;
    Float bFloat;
    Float cFloat;
    Float dFloat;
    Float fFloat;

    String bed = null;
    String bath = null;
    String station = null;
    String store = null;
    String age = null;

    User user;

    int[] ps = null;
    int[] svm = null;

    /**
     * Method to recive data and plot it
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);
        barchart = (BarChart) findViewById(R.id.barChart);


        user = getIntent().getExtras().getParcelable("user");

        aFloat = new Float(getIntent().getExtras().getInt("as"));
        bFloat = new Float(getIntent().getExtras().getInt("bs"));
        cFloat = new Float(getIntent().getExtras().getInt("cs"));
        dFloat = new Float(getIntent().getExtras().getInt("ds"));
        fFloat = new Float(getIntent().getExtras().getInt("fs"));

        station = getIntent().getExtras().getString("station");
        store = getIntent().getExtras().getString("store");;
        age = getIntent().getExtras().getString("age");

        ps = getIntent().getExtras().getIntArray("ps");
        svm = getIntent().getExtras().getIntArray("svm");

        TextView tv = (TextView) findViewById(R.id.tv_desc);
        tv.setText(String.format("Distance to Station: %s meter, Stores nearby: %s, Age: %s years", station, store, age));

        plotBar();
        //plotLine();

    }

    private void plotLine(){

        mChart = (LineChart) findViewById(R.id.linechart);
        //mChart.setOnChartGestureListener(this);
        //mChart.setOnChartValueSelectedListener(this);
        mChart.setDrawGridBackground(false);

        // add data
        setData();

        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();

        // modify the legend ...
        // l.setPosition(LegendPosition.LEFT_OF_CHART);
        l.setForm(Legend.LegendForm.LINE);

        Description d = new Description();
        d.setText("Line Chart");
        // no description text
        mChart.setDescription(d);

        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        // mChart.setScaleXEnabled(true);
        // mChart.setScaleYEnabled(true);

        LimitLine upper_limit = new LimitLine(130f, "Upper Limit");
        upper_limit.setLineWidth(4f);
        upper_limit.enableDashedLine(10f, 10f, 0f);
        upper_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        upper_limit.setTextSize(10f);

        LimitLine lower_limit = new LimitLine(-30f, "Lower Limit");
        lower_limit.setLineWidth(4f);
        lower_limit.enableDashedLine(10f, 10f, 0f);
        lower_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        lower_limit.setTextSize(10f);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
        leftAxis.addLimitLine(upper_limit);
        leftAxis.addLimitLine(lower_limit);
        leftAxis.setAxisMaxValue(220f);
        leftAxis.setAxisMinValue(-50f);
        //leftAxis.setYOffset(20f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);

        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(true);

        mChart.getAxisRight().setEnabled(false);

        //mChart.getViewPortHandler().setMaximumScaleY(2f);
        //mChart.getViewPortHandler().setMaximumScaleX(2f);

        mChart.animateX(2500, Easing.EaseInOutQuart);

        //  dont forget to refresh the drawing
        mChart.invalidate();
    }


    private ArrayList<String> setXAxisValues(){
        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("10");
        xVals.add("20");
        xVals.add("30");
        xVals.add("30.5");
        xVals.add("40");

        return xVals;
    }

    private ArrayList<Entry> setYAxisValues(){
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        yVals.add(new Entry(60, 0));
        yVals.add(new Entry(48, 1));
        yVals.add(new Entry(70.5f, 2));
        yVals.add(new Entry(100, 3));
        yVals.add(new Entry(180.9f, 4));

        return yVals;
    }

    private void setData() {
        ArrayList<String> xVals = setXAxisValues();

        ArrayList<Entry> yVals = setYAxisValues();

        LineDataSet set1;

        // create a dataset and give it a type
        set1 = new LineDataSet(yVals, "DataSet 1");

        set1.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        // set the line to be drawn like this "- - - - - -"
        //   set1.enableDashedLine(10f, 5f, 0f);
        // set1.enableDashedHighlightLine(10f, 5f, 0f);
        set1.setColor(Color.BLACK);
        set1.setCircleColor(Color.BLACK);
        set1.setLineWidth(1f);
        set1.setCircleRadius(3f);
        set1.setDrawCircleHole(false);
        set1.setValueTextSize(9f);
        set1.setDrawFilled(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set1); // add the datasets

        LineData data = new LineData(set1);

        // set data
        mChart.setData(data);

    }

    /**
     * Utility method to plot graph
     */
    private void plotBar(){

        final HashMap<Float, String> xAxisLabelMap = new HashMap<>();
        xAxisLabelMap.put(50f, "Aft 15");
        xAxisLabelMap.put(40f, "Aft 10");
        xAxisLabelMap.put(30f, "Today");
        xAxisLabelMap.put(20f, "Bef 10");
        xAxisLabelMap.put(10f, "Bef 15");

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(50f,fFloat));
        barEntries.add(new BarEntry(40f,dFloat));
        barEntries.add(new BarEntry(30f,cFloat));
        barEntries.add(new BarEntry(20f,bFloat));
        barEntries.add(new BarEntry(10f,aFloat));
        BarDataSet barDataSet = new BarDataSet(barEntries,"Prices For years");

        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData theData = new BarData(barDataSet );
        theData.setBarWidth(6.0f);

        barchart.setData(theData);


        barchart.setFitBars(true);
        barchart.setPinchZoom(false);

        XAxis xAxis = barchart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return xAxisLabelMap.get(value);
            }
        });
        xAxis.setTextSize(12f);


        Legend l = barchart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.CIRCLE);
        l.setFormSize(9f);
        l.setTextSize(14f);
        l.setXEntrySpace(4f);
        l.setWordWrapEnabled(true);
        l.setTextColor(Color.BLACK);

    }

    /**
     *
     * Onclick eevnt handler to enter another student data
     *
     * @param view
     */
    public void goHome(View view) {
        Intent intent = new Intent(getApplicationContext(),
                Predict.class);
        intent.putExtra("loginMsg", "Login User");
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }

    public void plotLine(View view) {

        Intent intent = new Intent(getApplicationContext(),
                LineActivity.class);

        intent.putExtra("bed", bed);
        intent.putExtra("bath", bath);
        intent.putExtra("station", station);
        intent.putExtra("store", store);
        intent.putExtra("age", age);

        intent.putExtra("ps", ps);
        intent.putExtra("svm", svm);


        intent.putExtra("loginMsg", "Prdict me");
        intent.putExtra("user", user);

        startActivity(intent);

        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case R.id.action_logout:
                Intent loginCredIntent = new Intent(this, Login.class);
                loginCredIntent.putExtra("loginMsg", "Login User");
                startActivityForResult(loginCredIntent, Constants.LOGIN_USER);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
