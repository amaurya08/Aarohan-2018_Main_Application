package org.poornima.aarohan.aarohan2017.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.poornima.aarohan.aarohan2017.R;

/**
 * Created by kuldeep on 04-01-2018.
 */


public class MyEventAdapter extends ArrayAdapter<String> {

    private String[] event_name;
    private String[] event_time;
    private String[] event_date;

    public MyEventAdapter(Context context, String[] event_name,String[] event_time,String[] event_date)
    {
        super(context, R.layout.myevent_customlist_row, event_name);
        this.event_name=event_name;
        this.event_date=event_date;
        this.event_time=event_time;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View CustomView = layoutInflater.inflate(R.layout.myevent_customlist_row, parent, false);

        ((TextView)CustomView.findViewById(R.id.myeventname)).setText(event_name[position]);
        ((TextView)CustomView.findViewById(R.id.myeventtime)).setText(event_time[position]);
        ((TextView)CustomView.findViewById(R.id.myeventdate)).setText(event_date[position]);

        return CustomView;
    }

}