package com.montclair.mhaskes1.registerandlogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.montclair.mhaskes1.registerandlogin.model.User;
import com.montclair.mhaskes1.registerandlogin.util.Constants;

public class updateproperty extends AppCompatActivity {

    User user;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String NumberOfbedroom = "numberOfbedroom";
    public static final String NumberOfbaathroom = "numberOfbaathroom";
    public static final String StationDistance = "stationdistance";
    public static final String Age = "age";
    public static final String Stores = "stores";
    public static final String Price = "price";
    TextView numberOfbedroom,numberOfbathroom, stationdistance, age,stores, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateproperty);

        user = getIntent().getExtras().getParcelable("user");


        numberOfbedroom = (TextView) findViewById(R.id.tv_bedroom);
        numberOfbathroom = (TextView) findViewById(R.id.tv_bathroom);
        stationdistance = (TextView) findViewById(R.id.tv_station);
        age = (TextView) findViewById(R.id.tv_age);
        stores = (TextView) findViewById(R.id.tv_store);
        price = (TextView) findViewById(R.id.tv_price);

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(NumberOfbedroom)) {
            numberOfbedroom.setText(String.format("Number of Bathroom : %s" , sharedpreferences.getString(NumberOfbedroom, "")));
        }

        if (sharedpreferences.contains(NumberOfbaathroom)) {
            numberOfbathroom.setText(String.format("Number of Bedroom : %s" , sharedpreferences.getString(NumberOfbaathroom, "")));
        }
        if (sharedpreferences.contains(StationDistance)) {
            stationdistance.setText(String.format("Distnace form station : %s meters" , sharedpreferences.getString(StationDistance, "")));
        }
        if (sharedpreferences.contains(Age)) {
            age.setText(String.format("Age of house : %s years", sharedpreferences.getString(Age, "")));
        }
        if (sharedpreferences.contains(Stores)) {
            stores.setText(String.format("Number of Stores : %s", sharedpreferences.getString(Stores, "")));
        }
        if (sharedpreferences.contains(Price)) {
            price.setText(String.format("Expected price : $ %s per sq. feet", sharedpreferences.getString(Price, "")));
        }
    }


    public void gotoBuy(View view) {

        Intent intent = new Intent(updateproperty.this, buy.class);
        intent.putExtra("loginMsg", "Login User");
        intent.putExtra("user", user);
        startActivity(intent);
        finish();

    }

    public void gotoSell(View view) {
        Intent intent = new Intent(updateproperty.this, sell.class);
        intent.putExtra("loginMsg", "Login User");
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }

    public void gotoRent(View view) {
        Intent intent = new Intent(updateproperty.this, rent.class);
        intent.putExtra("loginMsg", "Login User");
        intent.putExtra("user", user);
        startActivity(intent);
        finish();

    }

    public void gotoPredict(View view) {
        Intent intent = new Intent(updateproperty.this, Predict.class);
        intent.putExtra("loginMsg", "Login User");
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }

    public void predictValue(View view) {
        Intent intent = new Intent(updateproperty.this,addproperty.class);
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
                Intent intent = new Intent(this, Login.class);
                intent.putExtra("loginMsg", "Login User");
                startActivity(intent);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
