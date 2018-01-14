package org.poornima.aarohan.aarohan2017;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.crashlytics.android.Crashlytics;

import org.poornima.aarohan.aarohan2017.Fragments.Fragment_Profile_Barcode;
import org.poornima.aarohan.aarohan2017.Fragments.Fragment_Profile_events;
import org.poornima.aarohan.aarohan2017.Fragments.Fragment_Profile_workshop;

import java.util.ArrayList;
import java.util.List;

import io.fabric.sdk.android.Fabric;


public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_profile);

        ViewPager viewpager = findViewById(R.id.profile_viewpager);
        TabLayout tablayout = findViewById(R.id.profile_sliding_tabs);
        
        setupViewPager(viewpager);
        tablayout.setupWithViewPager(viewpager);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
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
