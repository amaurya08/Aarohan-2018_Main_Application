package org.poornima.aarohan.aarohan2017;
//TODO 1. SPlash Activity

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

public class SplashActivity extends AppCompatActivity {
    private ProgressBar mProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mProgress = findViewById(R.id.splash_screen_progress_bar);
        mProgress.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startApp();
            }
        },3000);
    }

    private void handlerCallBack() {

    }

    private void startApp() {
        //Checking Session Whether User is Logged in or Not
        if (checkSession()) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            //If no user is logged in it will prompt for login
            Intent intent = new Intent(SplashActivity.this, PromptUserLogin.class);
            startActivity(intent);
            finish();
        }
    }

    public Boolean checkSession() {
        SharedPreferences sharedPref = getSharedPreferences("aarohan", MODE_PRIVATE);
        return sharedPref.getBoolean("is", false);
    }

}


