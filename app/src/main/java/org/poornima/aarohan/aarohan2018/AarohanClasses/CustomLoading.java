package org.poornima.aarohan.aarohan2018.AarohanClasses;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.Window;

import org.poornima.aarohan.aarohan2018.R;

/**
 * Created by HeartBeat on 15-01-2018.
 */

public class CustomLoading {

    private AlertDialog alertDialog;
    private Context context;

    public CustomLoading(Context context) {
        this.context = context;
         alertDialog = new AlertDialog.Builder(context).create();
        LayoutInflater inflater = LayoutInflater.from(context);
        alertDialog.setView(inflater.inflate(R.layout.dialog_loading,null));
        alertDialog.setCancelable(false);
        //alertDialog = builder.create();
        Window window = alertDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

    }

    public void show() {
        alertDialog.show();
    }

    public void cancel() {
        alertDialog.dismiss();
    }
}
