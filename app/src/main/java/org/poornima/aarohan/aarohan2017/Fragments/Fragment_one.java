package org.poornima.aarohan.aarohan2017.Fragments;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.poornima.aarohan.aarohan2017.Adapter.CustomAdapter;
import org.poornima.aarohan.aarohan2017.Adapter.EventAdapter;
import org.poornima.aarohan.aarohan2017.DBhandler.DatabaseHelper;
import org.poornima.aarohan.aarohan2017.Pojo.eventPojo;
import org.poornima.aarohan.aarohan2017.R;
import org.poornima.aarohan.aarohan2017.Tables.TableEventDetails;

import java.util.ArrayList;

/**
 * Created by ADMIN on 28-Dec-17.
 */

public class Fragment_one extends Fragment {
    ListView eventListview;
    private ArrayList<eventPojo> arrayList;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
    /*    LayoutInflater factory = LayoutInflater.from(getActivity());
        final View eventdialogview= factory.inflate(R.layout.dialog_event,null);
        final AlertDialog eventdialog = new AlertDialog.Builder(getActivity()).create();
        eventdialog.setView(eventdialogview);
        final TextView eveName =eventdialogview.findViewById(R.id.evename);
        View view=inflater.inflate(R.layout.one_frag,null);
        final String [] events={"RoboSoccer","RoboWar","Circuitary","RoboRace","BallGripper","StairClimber","Sputgun","WaterRocketry"};
        ListAdapter Myadapter= new CustomAdapter(getActivity(),events);
        ListView mylist = (ListView) view.findViewById(R.id.List);
        mylist.setAdapter(Myadapter);
        mylist.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String event = events[i];
                        eveName.setText(event);
                        eventdialog.show();

                    }
                }

        );
        eventdialog.dismiss();*/

        View view = inflater.inflate(R.layout.one_frag, container, false);
        arrayList = new ArrayList<>();
        fatchevents();
        EventAdapter eventAdapter=new EventAdapter(getContext(),arrayList);
        eventListview=(ListView)view.findViewById(R.id.lv_events);
        eventListview.setAdapter(eventAdapter);



        return view;
    }

    private void fatchevents() {

        DatabaseHelper db=new DatabaseHelper(getContext());
        Cursor cursor= db.getReadableDatabase().rawQuery("SELECT * FROM " + TableEventDetails.TABLE_NAME + " WHERE " + TableEventDetails.Event_date + "=?" + " AND " + TableEventDetails.Event_category + "=?" ,new String[]{getArguments().getString("day"),"Technical"});
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
