package org.poornima.aarohan.aarohan2017.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.poornima.aarohan.aarohan2017.R;

/**
 * Created by ADMIN on 04-Jan-18.
 */

public class Frag_Developers extends Fragment {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View customview = inflater.inflate(R.layout.frag_developer, container, false);
        return  customview;
    }
}
