package org.poornima.aarohan.aarohan2017;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.crashlytics.android.Crashlytics;

import org.poornima.aarohan.aarohan2017.Fragments.Fragment_four;
import org.poornima.aarohan.aarohan2017.Fragments.Fragment_one;
import org.poornima.aarohan.aarohan2017.Fragments.Fragment_three;
import org.poornima.aarohan.aarohan2017.Fragments.Fragment_two;

import java.util.ArrayList;
import java.util.List;

import io.fabric.sdk.android.Fabric;

public class ScheduleEventListActivity extends AppCompatActivity {


    private Bundle data_daybundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_schedule_event_list);

         data_daybundle = new Bundle();
        data_daybundle.putString("day",getIntent().getStringExtra("day"));
        getSupportActionBar().setTitle(getIntent().getStringExtra("dayCount")+"");

        ViewPager viewpager = findViewById(R.id.viewpager);
        setupViewPager(viewpager);
        TabLayout tablayout = findViewById(R.id.tabs);
        tablayout.setupWithViewPager(viewpager);
    }
    public void setupViewPager(ViewPager viewpager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Fragment event_Technical_fragment=new Fragment_one();
        Fragment event_Club_fragment=new Fragment_two();
        Fragment event_Sports_fragment=new Fragment_three();
        Fragment event_Cultural_fragment=new Fragment_four();

        event_Technical_fragment.setArguments(data_daybundle);
        event_Club_fragment.setArguments(data_daybundle);
        event_Sports_fragment.setArguments(data_daybundle);
        event_Cultural_fragment.setArguments(data_daybundle);


        adapter.addFragment(event_Technical_fragment, "Technical");
        adapter.addFragment(event_Club_fragment, "Club");
        adapter.addFragment(event_Sports_fragment, "Sports");
        adapter.addFragment(event_Cultural_fragment, "Cultural");
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}