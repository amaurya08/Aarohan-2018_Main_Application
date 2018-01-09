package org.poornima.aarohan.aarohan2017;
//TODO SPlash Activity
//intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
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
import org.poornima.aarohan.aarohan2017.Tables.TableSponserDetails;

public class SplashActivity extends AppCompatActivity {
    private ProgressBar mProgress;
    //  TextView splash_prog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mProgress = findViewById(R.id.splash_screen_progress_bar);
        mProgress.setVisibility(View.VISIBLE);
        loadSponsersDetails();
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
                    Toast.makeText(SplashActivity.this, "Error in loding sponsers", Toast.LENGTH_SHORT).show();
                }
            });
            stringRequest.setRetryPolicy(
                    new DefaultRetryPolicy(
                            10000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                    )
            );

            RequestQueue queue = Volley.newRequestQueue(SplashActivity.this);
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

            DatabaseHelper db = new DatabaseHelper(SplashActivity.this);
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
         mProgress.setVisibility(View.INVISIBLE);
        startApp();
    }


    private void startApp() {
        //Checking Session Whether User is Logged in or Not
        if (checkSession()) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            //If no user is logged in it will prompt for login
            Intent intent = new Intent(SplashActivity.this, PromptUserLogin.class);
            startActivity(intent);
            finish();
        }
    }

    public Boolean checkSession() {
        SharedPreferences sharedPref = getSharedPreferences("aarohan", MODE_PRIVATE);
        if (sharedPref.getBoolean("is", false))
            return true;
        else
            return false;

    }

}


