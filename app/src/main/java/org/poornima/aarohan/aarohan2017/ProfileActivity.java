package org.poornima.aarohan.aarohan2017;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.poornima.aarohan.aarohan2017.AarohanClasses.URLHelper;
import org.poornima.aarohan.aarohan2017.Fragments.Fragment_Profile_Barcode;
import org.poornima.aarohan.aarohan2017.Fragments.Fragment_Profile_events;
import org.poornima.aarohan.aarohan2017.Fragments.Fragment_Profile_workshop;
import org.poornima.aarohan.aarohan2017.Fragments.Fragment_four;
import org.poornima.aarohan.aarohan2017.Fragments.Fragment_one;
import org.poornima.aarohan.aarohan2017.Fragments.Fragment_three;
import org.poornima.aarohan.aarohan2017.Fragments.Fragment_two;

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
        profileAPI();
    }

    private void profileAPI() {
        StringRequest request = new StringRequest(Request.Method.POST, URLHelper.ProfileData, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
            parseProfile(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>map = new HashMap<>();
                SharedPreferences sharedPref = getSharedPreferences("aarohan", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                String emailprof = sharedPref.getString("email","");
                String otpprof = sharedPref.getString("otp","");
                map.put("email",emailprof);
                map.put("otp",otpprof);
                return map;
            }
        };
    }
    private void parseProfile(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            String error = jsonObject.getString("error");
            if(error.equals("false")){
                String message = jsonObject.getString("message");
                JSONArray jsonArray = new JSONArray(message);
            }
            else{
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

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
