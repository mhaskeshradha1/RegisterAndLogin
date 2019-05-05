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
import android.widget.EditText;

import com.montclair.mhaskes1.registerandlogin.ml.GuessPrice;
import com.montclair.mhaskes1.registerandlogin.model.User;
import com.montclair.mhaskes1.registerandlogin.util.Constants;

public class buy extends AppCompatActivity {

    User user;
    EditText bed = null;
    EditText bath = null;
    EditText station = null;
    EditText store = null;
    EditText age = null;
    EditText expected = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);


        user = getIntent().getExtras().getParcelable("user");

        bed = findViewById(R.id.et_bedroom);
        bath = findViewById(R.id.et_bathroom);
        station = findViewById(R.id.et_station);
        store = findViewById(R.id.et_store);
        age = findViewById(R.id.et_age);
        expected = findViewById(R.id.et_price);



    }


    public void gotoBuy(View view) {

        Intent loginCredIntent = new Intent(this, buy.class);
        loginCredIntent.putExtra("loginMsg", "Login User");
        loginCredIntent.putExtra("user", user);
        startActivityForResult(loginCredIntent, Constants.QUESTIONS_PAGE);
        finish();

    }

    public void gotoSell(View view) {

        Intent loginCredIntent = new Intent(this, sell.class);
        loginCredIntent.putExtra("loginMsg", "Login User");
        loginCredIntent.putExtra("user", user);
        startActivityForResult(loginCredIntent, Constants.QUESTIONS_PAGE);
        finish();

    }

    public void gotoRent(View view) {

        Intent loginCredIntent = new Intent(this, rent.class);
        loginCredIntent.putExtra("loginMsg", "Login User");
        loginCredIntent.putExtra("user", user);
        startActivityForResult(loginCredIntent, Constants.QUESTIONS_PAGE);
        finish();

    }

    public void gotoPredict(View view) {

        Intent loginCredIntent = new Intent(this, Predict.class);
        loginCredIntent.putExtra("loginMsg", "Login User");
        loginCredIntent.putExtra("user", user);
        startActivityForResult(loginCredIntent, Constants.QUESTIONS_PAGE);
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
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    public void predictValue(View view) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Fetching list of properties matching your criteria")
                .setTitle("Buy Listing")
                .setPositiveButton("View Listing", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!

                        Intent loginCredIntent = new Intent(buy.this, LandingPage.class);
                        loginCredIntent.putExtra("loginMsg", "Login User");
                        loginCredIntent.putExtra("user", user);
                        startActivityForResult(loginCredIntent, Constants.LANDING_PAGE);
                    }
                })
                .setNegativeButton("Dismiss", null);
        // Create the AlertDialog object and return it
        builder.create();
        builder.show();


    }
}
