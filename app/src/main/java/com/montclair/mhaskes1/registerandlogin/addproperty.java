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

public class addproperty extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    EditText numberOfbedroom,numberOfbathroom, stationdistance, age,stores, price;
    TextView email;
    public static final String mypreference = "mypref";
    public static final String NumberOfbedroom = "numberOfbedroom";
    public static final String NumberOfbaathroom = "numberOfbaathroom";
    public static final String StationDistance = "stationdistance";
    public static final String Age = "age";
    public static final String Stores = "stores";
    public static final String Price = "price";
    //public static final String Email = "emailKey";

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproperty);

        user = getIntent().getExtras().getParcelable("user");

        numberOfbedroom = (EditText) findViewById(R.id.et_bedroom);
        numberOfbathroom = (EditText) findViewById(R.id.et_bedroom);
        stationdistance = (EditText) findViewById(R.id.et_station);
        age = (EditText) findViewById(R.id.et_age);
        stores = (EditText) findViewById(R.id.et_store);
        price = (EditText) findViewById(R.id.et_price);

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(NumberOfbedroom)) {
            numberOfbedroom.setText(sharedpreferences.getString(NumberOfbedroom, ""));
        }

        if (sharedpreferences.contains(NumberOfbaathroom)) {
            numberOfbathroom.setText(sharedpreferences.getString(NumberOfbaathroom, ""));
        }
        if (sharedpreferences.contains(StationDistance)) {
            stationdistance.setText(sharedpreferences.getString(StationDistance, ""));
        }
        if (sharedpreferences.contains(Age)) {
            age.setText(sharedpreferences.getString(Age, ""));
        }
        if (sharedpreferences.contains(Stores)) {
            stores.setText(sharedpreferences.getString(Stores, ""));
        }
        if (sharedpreferences.contains(Price)) {
            price.setText(sharedpreferences.getString(Price, ""));
        }
    }

    public void gotoSell(View view) {
        Intent intent = new Intent(addproperty.this, sell.class);
        intent.putExtra("loginMsg", "Login User");
        intent.putExtra("user", user);
        startActivity(intent);

        finish();
    }

    public void gotoRent(View view) {
        Intent intent = new Intent(addproperty.this, rent.class);
        intent.putExtra("loginMsg", "Login User");
        intent.putExtra("user", user);
        startActivity(intent);
        finish();

    }

    public void gotoPredict(View view) {
        Intent intent = new Intent(addproperty.this, Predict.class);
        intent.putExtra("loginMsg", "Login User");
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }

    public void gotoBuy(View view) {

        Intent loginCredIntent = new Intent(this, buy.class);
        loginCredIntent.putExtra("loginMsg", "Login User");
        loginCredIntent.putExtra("user", user);
        startActivity(loginCredIntent);
        finish();

    }

    public void predictValue(View view) {
       // Intent intent = new Intent(addproperty.this, .class);
        //startActivity(intent);
        String bed = numberOfbedroom.getText().toString();
        String bath = numberOfbathroom.getText().toString();
        String dist = stationdistance.getText().toString();
        String st = stores.getText().toString();
        String houseage = age.getText().toString();
        String rate = price.getText().toString();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(NumberOfbaathroom,bath);
        editor.putString(NumberOfbedroom,bed);
        editor.putString(Stores,st);
        editor.putString(Age,houseage);
        editor.putString(Price,rate);
        editor.putString(StationDistance, dist);
        editor.commit();

        Intent intent = new Intent(addproperty.this, updateproperty.class);
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
