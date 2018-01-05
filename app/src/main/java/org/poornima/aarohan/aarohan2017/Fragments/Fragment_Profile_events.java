package org.poornima.aarohan.aarohan2017.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.poornima.aarohan.aarohan2017.Adapter.MyEventAdapter;
import org.poornima.aarohan.aarohan2017.R;

public class Fragment_Profile_events extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_fragment__profile_events, container, false);
        String[] task_name={"Event 1","Event 2","Event 3","Event 4"};
        String[] task_date={"1/1/18","3/1/18","4/1/18","7/1/18"};
        String[] task_time={"t1","t2","t3","t4"};
        ListAdapter myEventAdapter =new MyEventAdapter(getContext(),task_name,task_time,task_date);
        ListView myEventlistview=(ListView)view.findViewById(R.id.myeventlistview);
        myEventlistview.setAdapter(myEventAdapter);
        myEventlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        return view;

    }


}
