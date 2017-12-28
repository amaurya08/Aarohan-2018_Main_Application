package org.poornima.aarohan.aarohan2017;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import org.poornima.aarohan.aarohan2017.Fragments.Fragment_four;
import org.poornima.aarohan.aarohan2017.Fragments.Fragment_one;
import org.poornima.aarohan.aarohan2017.Fragments.Fragment_three;
import org.poornima.aarohan.aarohan2017.Fragments.Fragment_two;

public class BottomActivity extends AppCompatActivity {

    final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    changeFragment(new Fragment_one());
                    return true;
                case R.id.navigation_dashboard:
                    changeFragment(new Fragment_two());
                    return true;
                case R.id.navigation_notifications:
                    changeFragment(new Fragment_three());
                    return true;
                case R.id.cultural:
                    changeFragment(new Fragment_four());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom);
        changeFragment(new Fragment_one());

        BottomNavigationView navigation = findViewById(R.id.navi);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    public void changeFragment(android.support.v4.app.Fragment fragment)
    {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction=fm.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.replace(R.id.containers,fragment);
        transaction.commit();

    }
}
