package org.poornima.aarohan.aarohan2018.Fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;



import org.poornima.aarohan.aarohan2018.Adapter.SponsorAdapter;
import org.poornima.aarohan.aarohan2018.DBhandler.DatabaseHelper;
import org.poornima.aarohan.aarohan2018.Pojo.sponserPojo;
import org.poornima.aarohan.aarohan2018.R;
import org.poornima.aarohan.aarohan2018.Tables.TableSponserDetails;

import java.util.ArrayList;



/**
 * Created by ADMIN on 04-Jan-18.
 */

public class Frag_Sponsers extends Fragment {
    ListView sponserListview;
    private ArrayList<sponserPojo> arrayList;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.frag_sponsers, container, false);
        arrayList = new ArrayList<>();
        fatchsponsers();
        SponsorAdapter sponsor_adapter=new SponsorAdapter(getContext(),arrayList);
        sponserListview=(ListView)view.findViewById(R.id.lv_sponsor);
        sponserListview.setAdapter(sponsor_adapter);

   /*     String[] SponserName={"Pizza City","Dominos","Engineers Academy","Spicy King"};
        String[] SponserLogoUrl={ "http://i.imgur.com/rFLNqWI.jpg",
                "http://i.imgur.com/C9pBVt7.jpg",
                "http://i.imgur.com/rT5vXE1.jpg",
                "http://i.imgur.com/aIy5R2k.jpg"};


        SponsorAdapter sponsor_adapter=new SponsorAdapter(getContext(),SponserName,SponserLogoUrl);

        sponserListview=(ListView)view.findViewById(R.id.lv_sponsor);
        sponserListview.setAdapter(sponsor_adapter);*/
        return  view;
    }

    private void fatchsponsers() {
        DatabaseHelper db=new DatabaseHelper(getContext());
       Cursor cursor= db.getReadableDatabase().rawQuery("SELECT * FROM " + TableSponserDetails.TABLE_NAME  , null);
       while(cursor.moveToNext()){
           arrayList.add(new sponserPojo(cursor.getString(1),cursor.getString(2)));
       }
       cursor.close();
    }
}
