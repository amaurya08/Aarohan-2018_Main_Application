package org.poornima.aarohan.aarohan2017;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class YourEventsAdapter extends ArrayAdapter<String> {

    private String[] eventname;
    private String[] time;

    public YourEventsAdapter(Context context, String[] eventname)
    {
        super(context, R.layout.custom_row, eventname);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View CustomView = layoutInflater.inflate(R.layout.custom_row, parent, false);
        String singleevent = getItem(position);
        TextView t1;
        t1 = CustomView.findViewById(R.id.mytext1);
        t1.setText(singleevent);
        return CustomView;
    }

}
