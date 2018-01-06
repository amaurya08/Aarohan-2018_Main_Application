package org.poornima.aarohan.aarohan2017.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.poornima.aarohan.aarohan2017.Adapter.MyEventAdapter;
import org.poornima.aarohan.aarohan2017.Adapter.SponsorAdapter;
import org.poornima.aarohan.aarohan2017.R;

/**
 * Created by ADMIN on 04-Jan-18.
 */

public class Frag_Sponsers extends Fragment {
    ListView sponserListview;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.frag_sponsers, container, false);

        String[] SponserName={"Pizza City","Dominos","Engineers Academy","Spicy King"};
        String[] SponserLogoUrl={ "http://i.imgur.com/rFLNqWI.jpg",
                "http://i.imgur.com/C9pBVt7.jpg",
                "http://i.imgur.com/rT5vXE1.jpg",
                "http://i.imgur.com/aIy5R2k.jpg"};


        SponsorAdapter sponsor_adapter=new SponsorAdapter(getContext(),SponserName,SponserLogoUrl);

        sponserListview=(ListView)view.findViewById(R.id.lv_sponsor);
        sponserListview.setAdapter(sponsor_adapter);
        return  view;
    }
}
