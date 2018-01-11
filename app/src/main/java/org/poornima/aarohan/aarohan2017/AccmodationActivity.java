package org.poornima.aarohan.aarohan2017;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AccmodationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accmodation);

    }
public void browser(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://aarohan.poornima.org.com"));
        startActivity(browserIntent);
    }
}
