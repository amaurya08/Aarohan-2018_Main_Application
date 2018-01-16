package org.poornima.aarohan.aarohan2018.Fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;



import org.poornima.aarohan.aarohan2018.AarohanClasses.ScheduleEventDetails;
import org.poornima.aarohan.aarohan2018.Adapter.EventAdapter;
import org.poornima.aarohan.aarohan2018.DBhandler.DatabaseHelper;
import org.poornima.aarohan.aarohan2018.Pojo.eventPojo;
import org.poornima.aarohan.aarohan2018.R;
import org.poornima.aarohan.aarohan2018.Tables.TableEventDetails;

import java.util.ArrayList;



/**
 * Created by ADMIN on 28-Dec-17.
 */

public class Fragment_four extends Fragment {
    ListView eventListview;
    private ArrayList<eventPojo> arrayList;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.four_frag, container, false);
        arrayList = new ArrayList<>();
        fatchevents();
        EventAdapter eventAdapter=new EventAdapter(getContext(),arrayList);
        eventListview=(ListView)view.findViewById(R.id.lv_events);
        eventListview.setAdapter(eventAdapter);
        eventListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ScheduleEventDetails.showEventDetails(view,position,getContext(),getActivity());
            }
        });
        return view;
    }
    private void fatchevents() {
        DatabaseHelper db=new DatabaseHelper(getContext());
        Cursor cursor= db.getReadableDatabase().rawQuery("SELECT * FROM " + TableEventDetails.TABLE_NAME + " WHERE " + TableEventDetails.Event_date + "=?" + " AND " + TableEventDetails.Event_category + "=?" ,new String[]{getArguments().getString("day"),"Cultural"});
        while(cursor.moveToNext()){
            arrayList.add(new eventPojo(cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9),
                    cursor.getString(10),
                    cursor.getString(11),
                    cursor.getString(12),
                    cursor.getString(13),
                    cursor.getString(14)));
        }
        cursor.close();
    }
}
