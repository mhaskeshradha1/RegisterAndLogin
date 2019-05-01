package com.montclair.mhaskes1.registerandlogin;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.montclair.mhaskes1.registerandlogin.model.User;
import com.montclair.mhaskes1.registerandlogin.util.Constants;

import java.util.ArrayList;

public class LineActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);

        ps = getIntent().getExtras().getIntArray("ps");
        svm = getIntent().getExtras().getIntArray("svm");

        station = getIntent().getExtras().getString("station");
        store = getIntent().getExtras().getString("store");;
        age = getIntent().getExtras().getString("age");

        TextView tv = (TextView) findViewById(R.id.tv_desc);
        tv.setText(String.format("Distance to Station: %s meter, Stores nearby: %s, Age: %s years", station, store, age));

        plotLine();

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

        /*LimitLine upper_limit = new LimitLine(130f, "Upper Limit");
        upper_limit.setLineWidth(4f);
        upper_limit.enableDashedLine(10f, 10f, 0f);
        upper_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        upper_limit.setTextSize(10f);

        LimitLine lower_limit = new LimitLine(-30f, "Lower Limit");
        lower_limit.setLineWidth(4f);
        lower_limit.enableDashedLine(10f, 10f, 0f);
        lower_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        lower_limit.setTextSize(10f);*/

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
       // leftAxis.addLimitLine(upper_limit);
       // leftAxis.addLimitLine(lower_limit);
       // leftAxis.setAxisMaxValue(220f);
        //leftAxis.setAxisMinValue(-50f);
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

        for(int i = -20; i < 10; i++){
            yVals.add(new Entry(i, ps[i+20]));
        }
/*

        yVals.add(new Entry(60, 0));
        yVals.add(new Entry(48, 1));
        yVals.add(new Entry(70.5f, 2));
        yVals.add(new Entry(100, 3));
        yVals.add(new Entry(180.9f, 4));
*/

        return yVals;
    }

    private ArrayList<Entry> setYAxisValues2(){
        ArrayList<Entry> yVals = new ArrayList<Entry>();

        for(int i = -20; i < 10; i++){
            yVals.add(new Entry(i, svm[i+20]));
        }
/*

        yVals.add(new Entry(60, 0));
        yVals.add(new Entry(48, 1));
        yVals.add(new Entry(70.5f, 2));
        yVals.add(new Entry(100, 3));
        yVals.add(new Entry(180.9f, 4));
*/

        return yVals;
    }

    private void setData() {
        ArrayList<String> xVals = setXAxisValues();

        ArrayList<Entry> yVals = setYAxisValues();

        LineDataSet set1;

        // create a dataset and give it a type
        set1 = new LineDataSet(yVals, "Price of Home");

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


/*

        LineDataSet set2;

        // create a dataset and give it a type
        set2 = new LineDataSet(setYAxisValues2(), "Price of Home");

        set2.setFillAlpha(Color.RED);
        // set1.setFillColor(Color.RED);

        // set the line to be drawn like this "- - - - - -"
        //   set1.enableDashedLine(10f, 5f, 0f);
        // set1.enableDashedHighlightLine(10f, 5f, 0f);
        set2.setColor(Color.RED);
        set2.setCircleColor(Color.RED);
        set2.setLineWidth(1f);
        set2.setCircleRadius(3f);
        set2.setDrawCircleHole(false);
        set2.setValueTextSize(9f);
        set2.setDrawFilled(true);

        ArrayList<ILineDataSet> dataSets2 = new ArrayList<ILineDataSet>();
        dataSets.add(set2); // add the datasets

*/

        LineData data = new LineData(set1);

     //   data.addDataSet(set2);

        // set data
        mChart.setData(data);

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
