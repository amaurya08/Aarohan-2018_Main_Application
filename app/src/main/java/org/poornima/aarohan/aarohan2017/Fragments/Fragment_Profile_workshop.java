package org.poornima.aarohan.aarohan2017.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.poornima.aarohan.aarohan2017.R;

public class Fragment_Profile_workshop extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.empty_layout, container, false);
        if (checkSession()) {
            view = inflater.inflate(R.layout.fragment_fragment__profile_workshop, container, false);
            return view;
        }
        return view;
    }

    public Boolean checkSession() {
        SharedPreferences sharedPref;
        getActivity();
        sharedPref = getActivity().getSharedPreferences("aarohan", Context.MODE_PRIVATE);
        return sharedPref.getBoolean("is", false);

    }
}
