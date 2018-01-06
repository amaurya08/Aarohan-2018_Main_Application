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

import org.poornima.aarohan.aarohan2017.R;

/**
 * Created by kuldeep on 04-01-2018.
 */


public class SponsorAdapter extends ArrayAdapter<String> {

    private String[] SponserName;
    private String[] SponserLogoUrl;


    public SponsorAdapter(Context context, String[] SponserName, String[] SponserLogoUrl)
    {
        super(context, R.layout.sponser_list_row, SponserName);
        this.SponserName=SponserName;
        this.SponserLogoUrl=SponserLogoUrl;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View CustomView = layoutInflater.inflate(R.layout.sponser_list_row, parent, false);

        ((TextView)CustomView.findViewById(R.id.name_sponser)).setText(SponserName[position]);
       ImageView sponserlogImageView= (ImageView)CustomView.findViewById(R.id.image_sponser);

        Picasso.with(getContext())
                .load(SponserLogoUrl[position])
                .placeholder(R.drawable.placeholder)
               .error(R.drawable.error)
                .resize(600,200)
                .into(sponserlogImageView);
        return CustomView;
    }

}