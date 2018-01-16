package org.poornima.aarohan.aarohan2017.AarohanClasses;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import org.poornima.aarohan.aarohan2017.DBhandler.DatabaseHelper;
import org.poornima.aarohan.aarohan2017.R;
import org.poornima.aarohan.aarohan2017.Tables.TableEventDetails;

/**
 * Created by kuldeep on 14-01-2018.
 */

public class ScheduleEventDetails {

         public static void showEventDetails(View view, int position, Context FragmentContext,Context ActivityContext){
                String ename="",cname="",cnamecemail="",ccontact="",ecategory="",etype="",elocation="",edate="",etime="",edetail="";
                DatabaseHelper db=new DatabaseHelper(FragmentContext);
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
                LayoutInflater factory = LayoutInflater.from(ActivityContext);
                final View dilog_view= factory.inflate(R.layout.dialog_schedule_listitem_event_details,null);
                final AlertDialog dialog_Event_detail = new AlertDialog.Builder(ActivityContext).create();
                dialog_Event_detail.setView(dilog_view);
                ((TextView)dilog_view.findViewById(R.id.d_event_name)).setText(Html.fromHtml(ename));
                ((TextView)dilog_view.findViewById(R.id.d_co_name)).setText(Html.fromHtml(cname));
                ((TextView)dilog_view.findViewById(R.id.d_co_email)).setText(Html.fromHtml(cnamecemail));
                ((TextView)dilog_view.findViewById(R.id.d_co_contact_no)).setText(Html.fromHtml(ccontact));
                ((TextView)dilog_view.findViewById(R.id.d_event_category)).setText(Html.fromHtml("Category:-"+ecategory));
                ((TextView)dilog_view.findViewById(R.id.d_event_type)).setText(Html.fromHtml("Type:-"+etype));
                ((TextView)dilog_view.findViewById(R.id.d_event_location)).setText(Html.fromHtml("Location:-"+elocation));
                ((TextView)dilog_view.findViewById(R.id.d_event_date)).setText(Html.fromHtml("Date:-"+edate));
                ((TextView)dilog_view.findViewById(R.id.d_event_time)).setText(Html.fromHtml("Time:-"+etime));
                ((TextView)dilog_view.findViewById(R.id.d_event_detail)).setText(Html.fromHtml("About:-"+edetail));
                dialog_Event_detail.show();
                Window window = dialog_Event_detail.getWindow();
                // window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                Animation animation = new ScaleAnimation((float) 1.0, (float) 1.0,
                        (float) 0, (float) 1.0);
                animation.setDuration(500);
                dilog_view.startAnimation(animation);
         }
}
