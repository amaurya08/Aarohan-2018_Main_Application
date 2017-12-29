package org.poornima.aarohan.aarohan2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

public class DevelopersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers);

        final String[] name = {"Ashish Maurya", "Mayank Jain", "Jagrati Katariya", "Bhumika", "Kuldeep "};
        ListAdapter Myadapter = new custom_developer(this, name);
        ListView mylist = (ListView) findViewById(R.id.List);
        mylist.setAdapter(Myadapter);

    }
}
