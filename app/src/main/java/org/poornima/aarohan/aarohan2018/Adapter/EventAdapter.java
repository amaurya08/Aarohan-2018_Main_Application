package org.poornima.aarohan.aarohan2018.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.poornima.aarohan.aarohan2018.Pojo.eventPojo;
import org.poornima.aarohan.aarohan2018.R;

import java.util.ArrayList;

/**
 * Created by kuldeep on 04-01-2018.
 */


public class EventAdapter extends ArrayAdapter{


  private ArrayList arraylist;
    Context context;
    public EventAdapter(Context context, ArrayList<eventPojo> objects)
    {
        super(context, R.layout.schedule_eventlist_row_layout, objects);
        arraylist=objects;
        this.context=context;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View CustomView = layoutInflater.inflate(R.layout.schedule_eventlist_row_layout, parent, false);
        eventPojo ep= (eventPojo) arraylist.get(position);

        ((TextView)CustomView.findViewById(R.id.event_name)).setText(Html.fromHtml(ep.getEvent_name()));
        ((TextView)CustomView.findViewById(R.id.event_time)).setText(Html.fromHtml(ep.getEvent_time()));
        /*String temp = ep.getEvent_location();
        temp = temp.substring(3,temp.length()-4);*/
        ((TextView)CustomView.findViewById(R.id.event_venue)).setText(Html.fromHtml(ep.getEvent_location()));
       ImageView sponserlogImageView= (ImageView)CustomView.findViewById(R.id.event_img);

        Picasso.with(getContext())
                .load("http://aarohan.poornima.org/"+ep.getEvent_image_location())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .resize(140,140)
                .into(sponserlogImageView);

       /* Animation animation= new ScaleAnimation((float) 1.0, (float) 1.0,
            (float) 0, (float) 1.0);*/
       Animation animation= AnimationUtils.loadAnimation(context, R.anim.listview_anim);
        animation.setDuration(500);
        CustomView.startAnimation(animation);
        animation = null;

        return CustomView;
    }

}