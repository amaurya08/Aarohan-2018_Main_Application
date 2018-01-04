package org.poornima.aarohan.aarohan2017;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.poornima.aarohan.aarohan2017.Fragments.Fragment_Profile_Barcode;
import org.poornima.aarohan.aarohan2017.Fragments.Fragment_Profile_events;
import org.poornima.aarohan.aarohan2017.Fragments.Fragment_four;
import org.poornima.aarohan.aarohan2017.Fragments.Fragment_one;
import org.poornima.aarohan.aarohan2017.Fragments.Fragment_three;
import org.poornima.aarohan.aarohan2017.Fragments.Fragment_two;

import java.util.ArrayList;
import java.util.List;

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
        adapter.addFragment(new Fragment_Profile_Barcode(), "My BarCode");
        adapter.addFragment(new Fragment_Profile_events(), "My Events");
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
