package org.poornima.aarohan.aarohan2017.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.crashlytics.android.Crashlytics;

import org.poornima.aarohan.aarohan2017.Adapter.MyEventAdapter;
import org.poornima.aarohan.aarohan2017.DBhandler.DatabaseHelper;
import org.poornima.aarohan.aarohan2017.Pojo.myeventsPojo;
import org.poornima.aarohan.aarohan2017.R;
import org.poornima.aarohan.aarohan2017.Tables.TableMyeventsDetails;

import java.util.ArrayList;

import io.fabric.sdk.android.Fabric;

public class Fragment_Profile_events extends Fragment {

/*TextView eventname,eventtime,eventdate;*/
    private ArrayList<myeventsPojo> arrayList;
    ListView myeventsListview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Fabric.with(getActivity(),new Crashlytics());
        View view = inflater.inflate(R.layout.empty_layout, container, false);
        if (checkSession()) {
            view = inflater.inflate(R.layout.fragment_fragment__profile_events, container, false);
            arrayList = new ArrayList<>();
            MyEventAdapter myEventAdapter = new MyEventAdapter(getContext(), arrayList);
            myeventsListview = (ListView) view.findViewById(R.id.myeventlistview);
            myeventsListview.setAdapter(myEventAdapter);
     /*   String[] task_name={"Event 1","Event 2","Event 3","Event 4"};
        String[] task_date={"1/1/18","3/1/18","4/1/18","7/1/18"};
        String[] task_time={"t1","t2","t3","t4"};
        ListAdapter myEventAdapter =new MyEventAdapter(getContext(),task_name,task_time,task_date);
        ListView myEventlistview=(ListView)view.findViewById(R.id.myeventlistview);
        myEventlistview.setAdapter(myEventAdapter);
        myEventlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });*/
/*
        init(view);
*/
            fatchmyeventsValue();
            return view;
        }
        return view;

    }

    public Boolean checkSession() {
        SharedPreferences sharedPref;
        getActivity();
        sharedPref = getActivity().getSharedPreferences("aarohan", Context.MODE_PRIVATE);
        return sharedPref.getBoolean("is", false);

    }


    private void fatchmyeventsValue() {
        DatabaseHelper db = new DatabaseHelper(getContext());
        Cursor cursor = db.getReadableDatabase().rawQuery("select * from " + TableMyeventsDetails.TABLE_NAME, null);
      //  Log.d("DEBUG","cursor :: " + cursor.toString());
        while(cursor.moveToNext()){
            arrayList.add(new myeventsPojo(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)
            ,cursor.getString(5)));
         //   Log.d("DEBUG", "" + cursor.getString(1) + cursor.getString(2) + cursor.getString(3) + cursor.getString(4) + cursor.getString(5));
        }
        cursor.close();
    }
   /* private void init(View view) {
        eventname=view.findViewById(R.id.myeventname);
        eventtime=view.findViewById(R.id.myeventtime);
        eventdate=view.findViewById(R.id.myeventdate);
    }*/


}
