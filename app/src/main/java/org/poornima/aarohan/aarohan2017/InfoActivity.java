package org.poornima.aarohan.aarohan2017;

import android.content.ContentValues;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
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
import org.poornima.aarohan.aarohan2017.Fragments.Frag_AboutAarohan;
import org.poornima.aarohan.aarohan2017.Fragments.Frag_OurTeam;
import org.poornima.aarohan.aarohan2017.Fragments.Frag_Sponsers;
import org.poornima.aarohan.aarohan2017.Tables.TableSponserDetails;

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
        loadSponsersDetails();
        viewpager = findViewById(R.id.viewpager);
        setupViewPager(viewpager);
        tablayout = findViewById(R.id.tabs);
        tablayout.setupWithViewPager(viewpager);
    }
    private void loadSponsersDetails() {
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, URLHelper.getsponsorsDetails, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        parseSponserDetail(response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(InfoActivity.this, "Error in loding sponsers", Toast.LENGTH_SHORT).show();
                }
            });
            stringRequest.setRetryPolicy(
                    new DefaultRetryPolicy(
                            10000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                    )
            );

            RequestQueue queue = Volley.newRequestQueue(InfoActivity.this);
            queue.add(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void parseSponserDetail(String response) throws JSONException {

        JSONObject jsonObject = new JSONObject(response);
        String error = jsonObject.getString("error");
        Log.d("DEBUG",""+error);

        if(error.equals("false"))
        {
            JSONArray jsonArray = new JSONArray(jsonObject.getString("message"));
            Log.d("DEBUG",jsonArray.toString());

            DatabaseHelper db = new DatabaseHelper(InfoActivity.this);
            TableSponserDetails.deleteTableData(db.getWritableDatabase(),"delete from "+TableSponserDetails.TABLE_NAME);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectNode = jsonArray.getJSONObject(i);
                String sponser_name = jsonObjectNode.getString("spons_name");
                String sponser_image_url = jsonObjectNode.getString("spons_img_location");
                ContentValues cv = new ContentValues();
                cv.put(TableSponserDetails.SNAME, sponser_name);
                cv.put(TableSponserDetails.SURL, sponser_image_url);

                TableSponserDetails.insert(db.getWritableDatabase(), cv);

            }
        }
        else{
            Toast.makeText(this, "Dataarsing error", Toast.LENGTH_SHORT);
        }
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
