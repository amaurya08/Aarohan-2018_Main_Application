package org.poornima.aarohan.aarohan2017.AarohanClasses;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.Window;

import org.poornima.aarohan.aarohan2017.R;
import org.poornima.aarohan.aarohan2017.SplashActivity;

/**
 * Created by HeartBeat on 15-01-2018.
 */

public class CustomLoading {
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    private Context context;

    public CustomLoading(Context context) {
        this.context = context;
        builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        builder.setView(inflater.inflate(R.layout.dialog_loading,null));
        builder.setCancelable(false);
        alertDialog = builder.create();
        Window window = alertDialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }

    public void show() {
        alertDialog.show();
    }

    public void cancel() {
        alertDialog.cancel();
    }
}
