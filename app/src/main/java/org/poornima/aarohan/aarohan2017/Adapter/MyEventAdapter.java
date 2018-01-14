package org.poornima.aarohan.aarohan2017.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.poornima.aarohan.aarohan2017.Pojo.myeventsPojo;
import org.poornima.aarohan.aarohan2017.Pojo.sponserPojo;
import org.poornima.aarohan.aarohan2017.R;

import java.util.ArrayList;
import java.util.Locale;

import static org.poornima.aarohan.aarohan2017.R.layout.myevent_customlist_row;

/**
 * Created by kuldeep on 04-01-2018.
 */


public class MyEventAdapter extends ArrayAdapter {
    private ArrayList arraylist;
    Context context;
    public MyEventAdapter(Context context, ArrayList<myeventsPojo> objects) {
        super(context,R.layout.myevent_customlist_row,objects);
        arraylist=objects;
        this.context = context;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View CustomView = layoutInflater.inflate(myevent_customlist_row, parent, false);
        myeventsPojo sp= (myeventsPojo) arraylist.get(position);
        final myeventsPojo temp =sp;
        ((TextView)CustomView.findViewById(R.id.myeventname)).setText(sp.getEventname());
        ((TextView)CustomView.findViewById(R.id.myeventtime)).setText(sp.getEventtime());
        ((TextView)CustomView.findViewById(R.id.myeventdate)).setText(sp.getEventdate());
        RelativeLayout mapLayout = CustomView.findViewById(R.id.myeventlocation);
        mapLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = String.format(Locale.ENGLISH, "geo:%f,%f", Float.parseFloat(temp.getMaplati()), Float.parseFloat(temp.getMaplongi()));
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setPackage("com.google.android.apps.maps");
                context.startActivity(intent);
            }
        });
        return CustomView;
    }

}