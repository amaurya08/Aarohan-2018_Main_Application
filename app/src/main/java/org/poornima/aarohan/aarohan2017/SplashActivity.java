package org.poornima.aarohan.aarohan2017;
//TODO 1. SPlash Activity

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;

import org.poornima.aarohan.aarohan2017.AarohanClasses.CustomLoading;

import io.fabric.sdk.android.Fabric;

public class SplashActivity extends AppCompatActivity {
    private boolean back = false;
    private CustomLoading customLoading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
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

        if (back) {
            super.onBackPressed();
            return;
        }
        this.back = true;
        Toast.makeText(this, "Please Click Twice to Exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                back = false;
            }
        }, 2000);

    }
}


