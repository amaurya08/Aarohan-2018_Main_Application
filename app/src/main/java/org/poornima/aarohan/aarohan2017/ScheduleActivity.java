package org.poornima.aarohan.aarohan2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class ScheduleActivity extends AppCompatActivity implements View.OnClickListener {
    RelativeLayout day1event,day2event,day3event,day4event,day5event;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        init();
        day1event.setOnClickListener(this);
        day2event.setOnClickListener(this);
        day3event.setOnClickListener(this);
        day4event.setOnClickListener(this);
        day5event.setOnClickListener(this);
    }

    private void init() {
        day1event=(RelativeLayout)findViewById(R.id.day1layout);
        day2event=(RelativeLayout)findViewById(R.id.day2layout);
        day3event=(RelativeLayout)findViewById(R.id.day3layout);
        day4event=(RelativeLayout)findViewById(R.id.day4layout);
        day5event=(RelativeLayout)findViewById(R.id.day5layout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.day1layout:
                startActivity(new Intent(ScheduleActivity.this,ScheduleEventListActivity.class));
                break;
            case R.id.day2layout:
                break;
            case R.id.day3layout:
                break;
            case R.id.day4layout:
                break;
            case R.id.day5layout:
                break;
                default:
        }

    }
}
