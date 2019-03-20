package com.montclair.mhaskes1.registerandlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.montclair.mhaskes1.registerandlogin.model.User;
import com.montclair.mhaskes1.registerandlogin.util.Constants;

public class LandingPage extends AppCompatActivity {

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        user = getIntent().getExtras().getParcelable("user");

        ((TextView)findViewById(R.id.tv_lp_wm)).setText(String.format("Welcome, %s", user.getFirstName()));

    }

    public void startQuiz(View view) {

        Intent loginCredIntent = new Intent(this, Questions.class);
        loginCredIntent.putExtra("loginMsg", "Login User");
        loginCredIntent.putExtra("user", user);
        startActivityForResult(loginCredIntent, Constants.QUESTIONS_PAGE);

    }
}
