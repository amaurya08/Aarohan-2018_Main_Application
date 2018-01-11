package org.poornima.aarohan.aarohan2017;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class WorkshopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workshop);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
