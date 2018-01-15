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
import org.poornima.aarohan.aarohan2017.R;

import java.util.ArrayList;

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


                // Create a Uri from an intent string. Use the result to create an Intent.
                /*Uri gmmIntentUri = Uri.parse("google.streetview:cbll=46.414382,10.013988");
                * Uri gmmIntentUri = Uri.parse("geo:37.7749,-122.4194");*/
                Uri gmmIntentUri = Uri.parse("geo:" + temp.getMaplati() + "," + temp.getMaplongi());

// Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
// Make the Intent explicit by setting the Google Maps package
                mapIntent.setPackage("com.google.android.apps.maps");

// Attempt to start an activity that can handle the Intent
                context.startActivity(mapIntent);

                /*String uri = "https://www.google.co.in/maps/@" +  + "15z";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                // intent.setPackage("com.google.android.apps.maps");
                context.startActivity(intent);*/
            }
        });
        return CustomView;
    }

}