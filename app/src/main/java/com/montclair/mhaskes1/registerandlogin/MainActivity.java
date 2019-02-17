package com.montclair.mhaskes1.registerandlogin;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

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

    }

}
