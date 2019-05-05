package com.montclair.mhaskes1.registerandlogin;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.montclair.mhaskes1.registerandlogin.model.User;
import com.montclair.mhaskes1.registerandlogin.util.Constants;

public class rent extends AppCompatActivity {

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);


        user = getIntent().getExtras().getParcelable("user");

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

    }

    public void gotoRent(View view) {

        Intent loginCredIntent = new Intent(this, rent.class);
        loginCredIntent.putExtra("loginMsg", "Login User");
        loginCredIntent.putExtra("user", user);
        startActivityForResult(loginCredIntent, Constants.QUESTIONS_PAGE);

    }

    public void gotoPredict(View view) {

        Intent loginCredIntent = new Intent(this, Predict.class);
        loginCredIntent.putExtra("loginMsg", "Login User");
        loginCredIntent.putExtra("user", user);
        startActivityForResult(loginCredIntent, Constants.QUESTIONS_PAGE);

    }

    public void predictValue(View view) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Fetching list of properties matching your criteria")
                .setTitle("Rent Listing")
                .setPositiveButton("View Listing", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                        Intent loginCredIntent = new Intent(rent.this, LandingPage.class);
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
