package org.poornima.aarohan.aarohan2017.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.poornima.aarohan.aarohan2017.Pojo.eventPojo;
import org.poornima.aarohan.aarohan2017.Pojo.sponserPojo;
import org.poornima.aarohan.aarohan2017.R;

import java.util.ArrayList;

/**
 * Created by kuldeep on 04-01-2018.
 */


public class EventAdapter extends ArrayAdapter{


  private ArrayList arraylist;

    public EventAdapter(Context context, ArrayList<eventPojo> objects)
    {
        super(context, R.layout.event_list_row_layout, objects);
        arraylist=objects;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View CustomView = layoutInflater.inflate(R.layout.event_list_row_layout, parent, false);
        eventPojo ep= (eventPojo) arraylist.get(position);

        ((TextView)CustomView.findViewById(R.id.event_name)).setText(ep.getEvent_name());
        ((TextView)CustomView.findViewById(R.id.event_time)).setText(ep.getEvent_time());
        ((TextView)CustomView.findViewById(R.id.event_venue)).setText(ep.getEvent_location());
       ImageView sponserlogImageView= (ImageView)CustomView.findViewById(R.id.event_img);

        Picasso.with(getContext())
                .load("http://aarohan.poornima.org/"+ep.getEvent_image_location())
                .placeholder(R.drawable.placeholder)
               .error(R.drawable.error)
                .resize(600,400)
                .into(sponserlogImageView);
        return CustomView;
    }

}