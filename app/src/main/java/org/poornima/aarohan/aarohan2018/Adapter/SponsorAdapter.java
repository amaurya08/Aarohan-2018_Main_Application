package org.poornima.aarohan.aarohan2018.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.poornima.aarohan.aarohan2018.Pojo.sponserPojo;
import org.poornima.aarohan.aarohan2018.R;

import java.util.ArrayList;

/**
 * Created by kuldeep on 04-01-2018.
 */


public class SponsorAdapter extends ArrayAdapter{


  private ArrayList arraylist;

    public SponsorAdapter(Context context,ArrayList<sponserPojo> objects)
    {
        super(context, R.layout.sponser_list_row, objects);
        arraylist=objects;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View CustomView = layoutInflater.inflate(R.layout.sponser_list_row, parent, false);
        sponserPojo sp= (sponserPojo) arraylist.get(position);

       // ((TextView)CustomView.findViewById(R.id.name_sponser)).setText(sp.getSponserName());
       ImageView sponserlogImageView= (ImageView)CustomView.findViewById(R.id.image_sponser);

        Picasso.with(getContext())
                .load(sp.getSponserUrl())
                .placeholder(R.drawable.placeholder)
               .error(R.drawable.error)
                .resize(600,200)
                .into(sponserlogImageView);
        return CustomView;
    }

}