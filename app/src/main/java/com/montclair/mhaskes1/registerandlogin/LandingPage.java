package com.montclair.mhaskes1.registerandlogin;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.montclair.mhaskes1.registerandlogin.ml.GuessPrice;
import com.montclair.mhaskes1.registerandlogin.ml.GuessTransit;
import com.montclair.mhaskes1.registerandlogin.model.User;
import com.montclair.mhaskes1.registerandlogin.util.Constants;

import java.io.InputStreamReader;
import java.io.Reader;

public class LandingPage extends AppCompatActivity {

    User user;

    Spinner line = null;
    Spinner station = null;
    Spinner day = null;
    Spinner hour = null;

    String lineString = null;
    String stationString = null;
    String dayString = null;
    String hourString = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        user = getIntent().getExtras().getParcelable("user");


        line = (Spinner) findViewById(R.id.sp_line);
        station = (Spinner) findViewById(R.id.sp_station);
        day = (Spinner) findViewById(R.id.sp_day);
        hour = (Spinner) findViewById(R.id.sp_hour);

        line.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lineString = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                lineString = null;
            }
        });
        station.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stationString = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                stationString = null;
            }
        });
        day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dayString = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                dayString = null;
            }
        });
        hour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hourString = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                hourString = null;
            }
        });

        //((TextView)findViewById(R.id.tv_lp_wm)).setText(String.format("Welcome, %s", user.getFirstName()));

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

    public void predictValue(View view) {

        String predict = "0";
        Reader trainerData = new InputStreamReader(getResources().openRawResource(R.raw.njtrain));

        try{
            predict = GuessTransit.predictString(trainerData, lineString, stationString, dayString, hourString);
        } catch (Exception e){
            e.printStackTrace();
        }

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(String.format("Your Train is predicted to be late by : %s min", predict))
                .setTitle("Predicted Delay")
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Dismiss", null);
        // Create the AlertDialog object and return it
        builder.create();
        builder.show();

    }
}
