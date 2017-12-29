package org.poornima.aarohan.aarohan2017;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by ADMIN on 27-Dec-17.
 */

public class custom_developer extends ArrayAdapter<String> {


    public custom_developer(@NonNull Context context, String [] name) {
        super(context, R.layout.custom_develop,name);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater myinflator=LayoutInflater.from(getContext());
        View customview=myinflator.inflate(R.layout.custom_develop,parent,false);
        View customview2=myinflator.inflate(R.layout.custom_develop_two,parent,false);
        if(position%2==0)
        {
            String single=getItem(position);

            TextView mytext=(TextView) customview.findViewById(R.id.mytext);
            mytext.setText(single);
            return customview;
        }
        else
        {
            String single=getItem(position);

            TextView mytext=(TextView) customview2.findViewById(R.id.mytext);
            mytext.setText(single);
            return customview2;
        }


    }
}
