package org.poornima.aarohan.aarohan2018;
//TODO 1. SPlash Activity

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;



import org.poornima.aarohan.aarohan2018.AarohanClasses.CustomLoading;



public class SplashActivity extends AppCompatActivity {
    private CustomLoading customLoading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        customLoading = new CustomLoading(SplashActivity.this);
        customLoading.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startApp();
            }
        },3000);
    }

    private void startApp() {
        //Checking Session Whether User is Logged in or Not
        if (checkSession()) {
            customLoading.cancel();
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            customLoading.cancel();
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

    @Override
    public void onBackPressed() {
       finish();
    }

}

