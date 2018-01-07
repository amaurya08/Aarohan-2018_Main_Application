package org.poornima.aarohan.aarohan2017;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.poornima.aarohan.aarohan2017.Fragments.Frag_AboutAarohan;
import org.poornima.aarohan.aarohan2017.Fragments.Frag_OurTeam;
import org.poornima.aarohan.aarohan2017.Fragments.Frag_Sponsers;

import java.util.ArrayList;
import java.util.List;

public class InfoActivity extends AppCompatActivity {
    private TabLayout tablayout;
    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        viewpager = findViewById(R.id.viewpager);
        setupViewPager(viewpager);
        tablayout = findViewById(R.id.tabs);
        tablayout.setupWithViewPager(viewpager);
    }

    public void setupViewPager(ViewPager viewpager) {
        ViewPagerAdapter adapter =new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Frag_AboutAarohan(),"About");
        adapter.addFragment(new Frag_Sponsers(),"Sponsers");
        adapter.addFragment(new Frag_OurTeam(),"Our Team");
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
        public void addFragment(Fragment fragment,String title)
        {
            mFragmentList.add(fragment);
            getmFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getmFragmentTitleList.get(position);
        }
    }
    }
