package org.poornima.aarohan.aarohan2017.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.poornima.aarohan.aarohan2017.Pojo.myeventsPojo;
import org.poornima.aarohan.aarohan2017.Pojo.sponserPojo;
import org.poornima.aarohan.aarohan2017.R;

import java.util.ArrayList;

import static org.poornima.aarohan.aarohan2017.R.layout.myevent_customlist_row;

/**
 * Created by kuldeep on 04-01-2018.
 */


public class MyEventAdapter extends ArrayAdapter {
    private ArrayList arraylist;

    public MyEventAdapter(Context context, ArrayList<myeventsPojo> objects) {
        super(context,R.layout.myevent_customlist_row,objects);
        arraylist=objects;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View CustomView = layoutInflater.inflate(myevent_customlist_row, parent, false);
        myeventsPojo sp= (myeventsPojo) arraylist.get(position);

        ((TextView)CustomView.findViewById(R.id.myeventname)).setText(sp.getEventname());
        ((TextView)CustomView.findViewById(R.id.myeventtime)).setText(sp.getEventtime());
        ((TextView)CustomView.findViewById(R.id.myeventdate)).setText(sp.getEventdate());
        return CustomView;
    }

}