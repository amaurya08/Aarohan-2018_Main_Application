package org.poornima.aarohan.aarohan2017.Fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.poornima.aarohan.aarohan2017.Adapter.CustomAdapter;
import org.poornima.aarohan.aarohan2017.R;

/**
 * Created by ADMIN on 28-Dec-17.
 */

public class Fragment_two extends Fragment {

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View eventdialogview= factory.inflate(R.layout.dialog_event,null);
        final AlertDialog eventdialog = new AlertDialog.Builder(getActivity()).create();
        eventdialog.setView(eventdialogview);
        final TextView eveName =eventdialogview.findViewById(R.id.evename);
        View view=inflater.inflate(R.layout.sec_frag,null);
        final String [] events={"100m Race","200m Race","RelayRace","Box Cricket","BallGripper","StairClimber"};
        ListAdapter Myadapter= new CustomAdapter(getActivity(),events);
        ListView mylist = (ListView) view.findViewById(R.id.List1);
        mylist.setAdapter(Myadapter);
        mylist.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String event = events[i];
                        eveName.setText(event);
                        eventdialog.show();
                    }
                }
        );

        eventdialog.dismiss();



        return view;
    }
}
