package org.poornima.aarohan.aarohan2017;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.poornima.aarohan.aarohan2017.AarohanClasses.URLHelper;
import org.poornima.aarohan.aarohan2017.DBhandler.DatabaseHelper;
import org.poornima.aarohan.aarohan2017.Fragments.Fragment_Profile_Barcode;
import org.poornima.aarohan.aarohan2017.Fragments.Fragment_Profile_events;
import org.poornima.aarohan.aarohan2017.Fragments.Fragment_Profile_workshop;
import org.poornima.aarohan.aarohan2017.Fragments.Fragment_four;
import org.poornima.aarohan.aarohan2017.Fragments.Fragment_one;
import org.poornima.aarohan.aarohan2017.Fragments.Fragment_three;
import org.poornima.aarohan.aarohan2017.Fragments.Fragment_two;
import org.poornima.aarohan.aarohan2017.Tables.ProfileTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {
    private TabLayout tablayout;
    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        viewpager = findViewById(R.id.profile_viewpager);
        tablayout = findViewById(R.id.profile_sliding_tabs);
        
        setupViewPager(viewpager);
        tablayout.setupWithViewPager(viewpager);

    }


    private void setupViewPager(ViewPager viewpager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment_Profile_Barcode(), "My Profile");
        adapter.addFragment(new Fragment_Profile_events(), "My Events");
        adapter.addFragment(new Fragment_Profile_workshop(), "My Workshops");
        viewpager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> getmFragmentTitleList = new ArrayList<>();
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }
        @Override
        public int getCount() {

            return mFragmentList.size();
        }
        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            getmFragmentTitleList.add(title);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return getmFragmentTitleList.get(position);
        }
    }
}
