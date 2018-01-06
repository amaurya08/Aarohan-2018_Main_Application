package org.poornima.aarohan.aarohan2017.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.poornima.aarohan.aarohan2017.DBhandler.DatabaseHelper;
import org.poornima.aarohan.aarohan2017.R;
import org.poornima.aarohan.aarohan2017.Tables.ProfileTable;


public class Fragment_Profile_Barcode extends Fragment {
    TextView textname, textemail, textcollege;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment__profile__barcode, container, false);

        init(view);
        fatchProfileValue();

        return view;
    }

    private void init(View view) {
        textname = (TextView) view.findViewById(R.id.profname);
        textemail = (TextView) view.findViewById(R.id.profemail);
        textcollege = (TextView) view.findViewById(R.id.profcollege);
    }

    private void fatchProfileValue() {
        DatabaseHelper db = new DatabaseHelper(getContext());
        Cursor cursor = db.getReadableDatabase().rawQuery("select * from " + ProfileTable.tablename, null);
        Log.d("DEBUG","cursor :: " + cursor.toString());
        while(cursor.moveToNext()){
            textname.setText(cursor.getString(1));
            textemail.setText(cursor.getString(2));
            textcollege.setText(cursor.getString(4));
        }
    }

}
