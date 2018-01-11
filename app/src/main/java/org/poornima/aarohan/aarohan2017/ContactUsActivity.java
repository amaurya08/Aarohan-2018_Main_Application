package org.poornima.aarohan.aarohan2017;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ContactUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
