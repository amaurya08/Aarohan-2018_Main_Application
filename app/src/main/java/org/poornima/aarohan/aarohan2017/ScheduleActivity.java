package org.poornima.aarohan.aarohan2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class ScheduleActivity extends AppCompatActivity {
    CardView cd1,cd2,cd3,cd4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        init();
        listeners();
    }
    private void listeners() {
        cd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ScheduleActivity.this,BottomActivity.class);
                startActivity(intent);
            }
        });
        cd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ScheduleActivity.this,BottomActivity.class);
                startActivity(intent);
            }
        });
        cd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ScheduleActivity.this,BottomActivity.class);
                startActivity(intent);
            }
        });
        cd4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ScheduleActivity.this,BottomActivity.class);
                startActivity(intent);
            }
        });
    }
    private void init()
    {
        cd1=findViewById(R.id.day1);
        cd2=findViewById(R.id.day2);
        cd3=findViewById(R.id.day3);
        cd4=findViewById(R.id.day4);
    }
}
