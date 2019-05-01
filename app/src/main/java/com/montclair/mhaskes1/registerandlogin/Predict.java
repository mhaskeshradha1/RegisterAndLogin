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

public class Predict extends AppCompatActivity {

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
        setContentView(R.layout.activity_predict);


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

    public void predictValue(View view) {

        Double bedDouble = new Double(bed.getText().toString());
        final Double bathDouble = new Double(bath.getText().toString());
        Double stationDouble = new Double(station.getText().toString());
        Double storeDouble = new Double(store.getText().toString());
        Double ageDouble = new Double(age.getText().toString());
        Double priceDouble = new Double(expected.getText().toString());

        Double addValue = 0.0;

        Log.d("Buy Activity ", "onPredict: addValue " + addValue);
        Log.d("Buy Activity ", "onPredict: bathDouble " + bathDouble);
        Log.d("Buy Activity ", "onPredict: bedDouble " + bedDouble);


        if(bathDouble > bedDouble ){
            addValue = (bathDouble - bedDouble) * 1.5;
        }

        Log.d("Buy Activity ", "onPredict: After bedbath compare addValue " + addValue);

        addValue = GuessPrice.predict(ageDouble, stationDouble, storeDouble) + addValue;


        final Integer aDouble = GuessPrice.predict(ageDouble - 15 , stationDouble, storeDouble).intValue() + addValue.intValue();
        final Integer bDouble = GuessPrice.predict(ageDouble - 10, stationDouble, storeDouble).intValue() + addValue.intValue();
        final Integer cDouble = GuessPrice.predict(ageDouble, stationDouble, storeDouble).intValue() + addValue.intValue();
        final Integer dDouble = GuessPrice.predict(ageDouble + 10, stationDouble, storeDouble).intValue() + addValue.intValue();
        final Integer fDouble = GuessPrice.predict(ageDouble + 15, stationDouble, storeDouble).intValue() + addValue.intValue();

        final int[] priceGraph = new int[30];
        final int[] priceSVMGraph = new int[30];

        for(int i = 0; i < 30; i++ ){
            priceGraph[i] = GuessPrice.predict(ageDouble - 20 + i , stationDouble, storeDouble).intValue() + addValue.intValue();
           // priceSVMGraph[i] = GuessPrice.predictSVM(ageDouble - 20 + i , stationDouble, storeDouble).intValue() + addValue.intValue();
        }

        Log.d("Buy Activity ", "onPredict: After GuessPrice addValue " + addValue);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(String.format("Your Expected : %s \nOur Prediction : %s ", priceDouble, addValue))
                .setTitle("Prediction value")
                .setPositiveButton("Predict", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!

                        Intent intent = new Intent(getApplicationContext(),
                                BarActivity.class);

                        intent.putExtra("as", aDouble);
                        intent.putExtra("bs", bDouble);
                        intent.putExtra("cs", cDouble);
                        intent.putExtra("ds", dDouble);
                        intent.putExtra("fs", fDouble);

                        intent.putExtra("bed", bed.getText().toString());
                        intent.putExtra("bath", bath.getText().toString());
                        intent.putExtra("station", station.getText().toString());
                        intent.putExtra("store", store.getText().toString());
                        intent.putExtra("age", age.getText().toString());

                        intent.putExtra("ps", priceGraph);
                        intent.putExtra("svm", priceSVMGraph);

                        intent.putExtra("loginMsg", "Prdict me");
                        intent.putExtra("user", user);

                        startActivity(intent);

                        finish();
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
