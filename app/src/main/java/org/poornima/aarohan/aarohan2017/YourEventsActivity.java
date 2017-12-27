package org.poornima.aarohan.aarohan2017;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class YourEventsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_events);

        String[] taskname={"event 1","event 2","event 3","event 4","event 5","event 6","event 7"};
        String[] date={"1/1/18","3/1/18","4/1/18","7/1/18"};
        String[] time={"t1","t2","t3","t4"};

        ListAdapter myAdapter =new YourEventsAdapter(this,taskname);
        ListView mylistview=(ListView)findViewById(R.id.mylist);
        mylistview.setAdapter(myAdapter);
        mylistview.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                        String task=String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(YourEventsActivity.this,task,Toast.LENGTH_SHORT).show();
                    }
                }
        );


    }
}
