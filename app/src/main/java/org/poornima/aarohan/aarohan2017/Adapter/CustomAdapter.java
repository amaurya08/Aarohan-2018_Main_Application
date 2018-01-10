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
 * Created by ADMIN on 28-Dec-17.
 */

public class CustomAdapter extends ArrayAdapter<String> {

    public CustomAdapter(@NonNull Context context, String [] events) {
        super(context, R.layout.event_list_row_layout,events);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater myinflator=LayoutInflater.from(getContext());
        View customview=myinflator.inflate(R.layout.event_list_row_layout,parent,false);
       /* String single=getItem(position);
        TextView mytext=(TextView) customview.findViewById(R.id.mytext);
        mytext.setText(single);*/
        return customview;
    }
}
