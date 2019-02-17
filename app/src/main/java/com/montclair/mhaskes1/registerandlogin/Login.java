package com.montclair.mhaskes1.registerandlogin;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    public static final int REGISTER_USER = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void gotoRegisteration(View view) {
        Intent registerIntent = new Intent(this, Registration.class);
        registerIntent.putExtra("loginMsg", "Register New KalaKrutee.com User");
        startActivityForResult(registerIntent, REGISTER_USER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        //always disable login button
        findViewById(R.id.login).setEnabled(false);

        if(requestCode == REGISTER_USER && resultCode == RESULT_OK){

            //TODO : enable button with successful user registration message
            findViewById(R.id.login).setEnabled(true);
            Toast.makeText(this, "Successful Registration ", Toast.LENGTH_LONG).show();
        }
        if(requestCode == REGISTER_USER && resultCode == RESULT_CANCELED){

            //TODO : enable button with successful user registration message
            Toast.makeText(this, "Registration Cancelled", Toast.LENGTH_LONG).show();
            findViewById(R.id.login).setEnabled(false);
        }

        super.onActivityResult(requestCode, resultCode, data);

    }
}
