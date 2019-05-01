package com.montclair.mhaskes1.registerandlogin;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.montclair.mhaskes1.registerandlogin.DataProvider.LoginProvider;
import com.montclair.mhaskes1.registerandlogin.ml.GuessPrice;
import com.montclair.mhaskes1.registerandlogin.service.LoginService;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Splash Screen Activity
 *
 * Shows logo for 2 sec and in same time loads
 * Machine Learning IRIS data Model
 *
 */
public class MainActivity extends AppCompatActivity {

    /**
     * ON create to show logo and Model loading for
     * Predicting flower
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        ColorDrawable whiteBG = new ColorDrawable(getResources().getColor(R.color.colorWhite));
        getSupportActionBar().setBackgroundDrawable(whiteBG);
        getSupportActionBar().setElevation(0);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),
                        Login.class);
                startActivity(intent);
                finish();
            }
        }, 2000);

        LoginProvider.Initialize(getApplicationContext());

        try {
            //Load model
            Log.d("Main Activity ", "onCreate: Loading ML " + GuessPrice.knn);
            ;
            Reader trainerData = new InputStreamReader(getResources().openRawResource(R.raw.re_train));
            Reader testData = new InputStreamReader(getResources().openRawResource(R.raw.re_test));
            Reader svmData = new InputStreamReader(getResources().openRawResource(R.raw.svm));
            GuessPrice.loadResource(trainerData, testData, svmData);
            Log.d("Main Activity ", "onCreate: Loaded ML " + GuessPrice.knn);
            Log.d("Main Activity ", "Predict: " +
                    GuessPrice.predict(30, 34, 12));
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
