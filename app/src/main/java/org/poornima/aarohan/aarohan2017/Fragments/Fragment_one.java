package org.poornima.aarohan.aarohan2017.Fragments;

import android.app.Dialog;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;

import org.poornima.aarohan.aarohan2017.Adapter.EventAdapter;
import org.poornima.aarohan.aarohan2017.DBhandler.DatabaseHelper;
import org.poornima.aarohan.aarohan2017.Pojo.eventPojo;
import org.poornima.aarohan.aarohan2017.R;
import org.poornima.aarohan.aarohan2017.Tables.TableEventDetails;

import java.util.ArrayList;

import io.fabric.sdk.android.Fabric;

/**
 * Created by ADMIN on 28-Dec-17.
 */

public class Fragment_one extends Fragment {
    ListView eventListview;
    private ArrayList<eventPojo> arrayList;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        Fabric.with(getActivity(),new Crashlytics());
        View view = inflater.inflate(R.layout.one_frag, container, false);
        arrayList = new ArrayList<>();
        fatchevents();
        EventAdapter eventAdapter=new EventAdapter(getContext(),arrayList);
        eventListview=(ListView)view.findViewById(R.id.lv_events);
        eventListview.setAdapter(eventAdapter);

        eventListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showEventDetails(view,position);
            }
        });

        return view;
    }

    private void showEventDetails(View view, int position) {
        String ename="",cname="",cnamecemail="",ccontact="",ecategory="",etype="",elocation="",edate="",etime="",edetail="";
        DatabaseHelper db=new DatabaseHelper(getContext());
        Cursor cursor= db.getReadableDatabase().rawQuery("SELECT * FROM " + TableEventDetails.TABLE_NAME + " WHERE " + TableEventDetails.Event_name + "=?" ,new String[]{((TextView)view.findViewById(R.id.event_name)).getText().toString()});
        while(cursor.moveToNext()){
            ename=cursor.getString(1);
            ecategory=cursor.getString(2);
            etype=cursor.getString(4);
            edetail=cursor.getString(5);
            elocation=cursor.getString(6);
            edate=cursor.getString(7);
            etime=cursor.getString(8);
            cname=cursor.getString(9);
            cnamecemail=cursor.getString(10);
            ccontact=cursor.getString(11);
        }
        cursor.close();



        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View dilog_view= factory.inflate(R.layout.dialog_schedule_listitem_event_details,null);
        final AlertDialog dialog_Event_detail = new AlertDialog.Builder(getActivity()).create();
        dialog_Event_detail.setView(dilog_view);

        ((TextView)dilog_view.findViewById(R.id.d_event_name)).setText(ename);
        ((TextView)dilog_view.findViewById(R.id.d_co_name)).setText(cname);
        ((TextView)dilog_view.findViewById(R.id.d_co_email)).setText(cnamecemail);
        ((TextView)dilog_view.findViewById(R.id.d_co_contact_no)).setText(ccontact);
        ((TextView)dilog_view.findViewById(R.id.d_event_category)).setText(ecategory);
        ((TextView)dilog_view.findViewById(R.id.d_event_type)).setText(etype);
        ((TextView)dilog_view.findViewById(R.id.d_event_location)).setText(elocation);
        ((TextView)dilog_view.findViewById(R.id.d_event_date)).setText(edate);
        ((TextView)dilog_view.findViewById(R.id.d_event_time)).setText(etime);
        ((TextView)dilog_view.findViewById(R.id.d_event_detail)).setText(edetail);


        dialog_Event_detail.show();
        Window window = dialog_Event_detail.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        Animation animation = new ScaleAnimation((float) 1.0, (float) 1.0,
                (float) 0, (float) 1.0);
        animation.setDuration(500);
        dilog_view.startAnimation(animation);



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
