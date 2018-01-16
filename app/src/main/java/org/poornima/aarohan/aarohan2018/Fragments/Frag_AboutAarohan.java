package org.poornima.aarohan.aarohan2018.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import org.poornima.aarohan.aarohan2018.R;


/**
 * Created by ADMIN on 04-Jan-18.
 */

public class Frag_AboutAarohan extends Fragment {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        View customview = inflater.inflate(R.layout.frag_aboutaarohan, container, false);
        return  customview;
    }
}
