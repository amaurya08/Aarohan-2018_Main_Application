package org.poornima.aarohan.aarohan2017.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.poornima.aarohan.aarohan2017.Adapter.CustomAdapter;
import org.poornima.aarohan.aarohan2017.R;

/**
 * Created by ADMIN on 28-Dec-17.
 */

public class Fragment_one extends Fragment {

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.one_frag,null);
        final String [] events={"RoboSoccer","RoboWar","Circuitary","RoboRace","BallGripper","StairClimber","Sputgun","WaterRocketry"};
        ListAdapter Myadapter= new CustomAdapter(getActivity(),events);
        ListView mylist = (ListView) view.findViewById(R.id.List);
        mylist.setAdapter(Myadapter);
        mylist.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String event = events[i];
                        Toast.makeText(getActivity(),event,Toast.LENGTH_LONG).show();
                    }
                }
        );





        return view;
    }
}
