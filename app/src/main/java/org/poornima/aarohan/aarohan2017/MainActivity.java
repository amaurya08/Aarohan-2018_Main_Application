package org.poornima.aarohan.aarohan2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.imangazaliev.circlemenu.CircleMenu;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CircleMenu circleMenu = findViewById(R.id.circleMenu);
    }
}
