package org.poornima.aarohan.aarohan2018;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class WorkshopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_workshop);
        getSupportActionBar().setTitle("Workshops");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
