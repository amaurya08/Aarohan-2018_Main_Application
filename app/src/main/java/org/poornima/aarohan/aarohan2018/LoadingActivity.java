package org.poornima.aarohan.aarohan2018;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
import org.poornima.aarohan.aarohan2018.AarohanClasses.NetWorkManager;
import org.poornima.aarohan.aarohan2018.AarohanClasses.URLHelper;
import org.poornima.aarohan.aarohan2018.DBhandler.DatabaseHelper;
import org.poornima.aarohan.aarohan2018.Tables.ProfileTable;
import org.poornima.aarohan.aarohan2018.Tables.TableEventDetails;
import org.poornima.aarohan.aarohan2018.Tables.TableMyeventsDetails;
import org.poornima.aarohan.aarohan2018.Tables.TableSponserDetails;

import java.util.HashMap;
import java.util.Map;

public class LoadingActivity extends AppCompatActivity {
    private TextView statusMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        init();
        if (NetWorkManager.checkInternetAccess(LoadingActivity.this)) {
            if (checkSession()) {
                loadProfile();
            } else {
                loadSchedule();
            }
        } else {
            statusMessage.setText("No Internet Access");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(LoadingActivity.this, MainActivity.class));
                    finish();
                }
            }, 1000);
        }

    }

    private void init() {
        statusMessage = findViewById(R.id.message_status);
    }

    private boolean checkSession() {
        SharedPreferences sharedPref = getSharedPreferences("aarohan", MODE_PRIVATE);
        return sharedPref.getBoolean("is", false);
    }

    /*Loading Profile*/
    private void loadProfile() {
        StringRequest request;
        request = new StringRequest(Request.Method.POST, URLHelper.ProfileData, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseProfile(response);
                statusMessage.setText("Loading My Events...");
                loadMyEvents();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                statusMessage.setText("Error Loading Profile\nLoading My Events...");
                loadMyEvents();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                SharedPreferences sharedPref = getSharedPreferences("aarohan", MODE_PRIVATE);
                String emailprof = sharedPref.getString("email", "");
                String otpprof = sharedPref.getString("otp", "");
                map.put("email", emailprof);
                map.put("otp", otpprof);
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(LoadingActivity.this);
        queue.add(request);
    }

    private void parseProfile(String response) {
        try {

            JSONObject jsonObject = new JSONObject(response);
            String error = jsonObject.getString("error");
            if (error.equals("false")) {
                String message = jsonObject.getString("message");
                JSONArray jsonArray = new JSONArray(message);
                JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                //  String stu_id = jsonObject1.getString("stu_id");
                String stu_email = jsonObject1.getString("stu_email");
                String stu_college = jsonObject1.getString("stu_college");
                String stu_reg_no = jsonObject1.getString("stu_reg_no");
                String stu_name = jsonObject1.getString("stu_name");
                String stu_contact = jsonObject1.getString("stu_contact");
                ContentValues cv = new ContentValues();
                cv.put(ProfileTable.Col_mail, stu_email);
                cv.put(ProfileTable.Col_college, stu_college);
                cv.put(ProfileTable.Col_mobileno, stu_contact);
                cv.put(ProfileTable.Col_rid, stu_reg_no);
                cv.put(ProfileTable.Col_name, stu_name);

                SharedPreferences sharedPref = getSharedPreferences("aarohan", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("stu_name", stu_name);
                editor.apply();


                DatabaseHelper db = new DatabaseHelper(LoadingActivity.this);
                long x = ProfileTable.insertDetails(db.getWritableDatabase(), cv);
                //  Log.d("DEBUG", "" + x);
            } else {
                //  Toast.makeText(this, ""+errorString, Toast.LENGTH_SHORT).show();
                //   Log.d("DEBUG",""+jsonObject.getString("message"));
            }
        } catch (JSONException e) {
            statusMessage.setText("Bad Message parsing");
        }
    }

    /* Loading My Events */

    private void loadMyEvents() {
        StringRequest stringRequest;
        stringRequest = new StringRequest(Request.Method.POST, URLHelper.studenteventdetails, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    parsestudenteventDetail(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                statusMessage.setText("Loading Schedule...");
                loadSchedule();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                statusMessage.setText("Error in Loading My Events...\nLoading Schedule...");
                loadSchedule();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                SharedPreferences sharedPref = getSharedPreferences("aarohan", MODE_PRIVATE);
                String emailprof = sharedPref.getString("email", "");
                String otpprof = sharedPref.getString("otp", "");
                map.put("email", emailprof);
                map.put("otp", otpprof);
                map.put("type", "STUDENT");
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(LoadingActivity.this);
        queue.add(stringRequest);
    }

    private void parsestudenteventDetail(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        String error = jsonObject.getString("error");
        //   Log.d("DEBUG", "" + error);

        if (error.equals("false")) {
            JSONArray jsonArray = new JSONArray(jsonObject.getString("message"));
            //  Log.d("DEBUG", jsonArray.toString());

            DatabaseHelper db = new DatabaseHelper(LoadingActivity.this);
            TableMyeventsDetails.deleteTableData(db.getWritableDatabase(), "delete from " + TableMyeventsDetails.TABLE_NAME);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectNode = jsonArray.getJSONObject(i);
                String event_name = jsonObjectNode.getString("event_name");
                String event_time = jsonObjectNode.getString("event_time");
                String event_date = jsonObjectNode.getString("event_date");
                String event_map_coordinates_long = jsonObjectNode.getString("event_map_coordinates_long");
                String event_map_coordinates_latt = jsonObjectNode.getString("event_map_coordinates_latt");
                ContentValues cv = new ContentValues();
                cv.put(TableMyeventsDetails.Col_eventname, event_name);
                cv.put(TableMyeventsDetails.Col_eventtime, event_time);
                cv.put(TableMyeventsDetails.Col_eventdate, event_date);
                cv.put(TableMyeventsDetails.Col_eventmaplong, event_map_coordinates_long);
                cv.put(TableMyeventsDetails.Col_eventmaplati, event_map_coordinates_latt);
                TableMyeventsDetails.insertDetails(db.getWritableDatabase(), cv);
                //    Log.d("DEBUG", "DATA INSERTED" + j);
            }
        } else {
            //Toast.makeText(this, "" , Toast.LENGTH_SHORT).show();
        }
    }

    /* Loading Schedule*/
    private void loadSchedule() {
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, URLHelper.eventData, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        parseEventDetails(response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    statusMessage.setText("Loading Sponsors...");
                    loadSponsors();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    statusMessage.setText("Error in loading Schedule...\nLoading Sponsors...");
                    loadSponsors();
                }
            });
            stringRequest.setRetryPolicy(
                    new DefaultRetryPolicy(
                            10000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                    )
            );
            RequestQueue queue = Volley.newRequestQueue(LoadingActivity.this);
            queue.add(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void parseEventDetails(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        String error = jsonObject.getString("error");
        //Log.d("DEBUG",""+error);
        if (error.equals("false")) {
            JSONArray jsonArray = new JSONArray(jsonObject.getString("message"));
//            Log.d("DEBUG",jsonArray.toString());

            DatabaseHelper db = new DatabaseHelper(LoadingActivity.this);
            TableSponserDetails.deleteTableData(db.getWritableDatabase(), "delete from " + TableEventDetails.TABLE_NAME);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectNode = jsonArray.getJSONObject(i);
                ContentValues cv = new ContentValues();
                cv.put(TableEventDetails.Event_name, jsonObjectNode.getString("event_name"));
                cv.put(TableEventDetails.Event_category, jsonObjectNode.getString("event_category"));
                cv.put(TableEventDetails.Event_participation_category, jsonObjectNode.getString("event_participation_category"));
                cv.put(TableEventDetails.Event_type, jsonObjectNode.getString("event_type"));
                cv.put(TableEventDetails.Event_detail, jsonObjectNode.getString("event_detail"));
                cv.put(TableEventDetails.Event_location, jsonObjectNode.getString("event_location"));
                cv.put(TableEventDetails.Event_date, jsonObjectNode.getString("event_date"));
                cv.put(TableEventDetails.Event_time, jsonObjectNode.getString("event_time"));
                cv.put(TableEventDetails.Co_name, jsonObjectNode.getString("co_name"));
                cv.put(TableEventDetails.Co_email, jsonObjectNode.getString("co_email"));
                cv.put(TableEventDetails.Co_contact_no, jsonObjectNode.getString("co_contact_no"));
                cv.put(TableEventDetails.Event_map_coordinates_long, jsonObjectNode.getString("event_map_coordinates_long"));
                cv.put(TableEventDetails.Event_map_coordinates_latt, jsonObjectNode.getString("event_map_coordinates_latt"));
                cv.put(TableEventDetails.Event_image_location, jsonObjectNode.getString("event_image_location"));

                long j = TableEventDetails.insert(db.getWritableDatabase(), cv);

                //  Log.d("DEBUG","value inserted in event table"+j);
            }
        } else {
            Toast.makeText(this, "Error Loading Data", Toast.LENGTH_SHORT).show();
        }
    }

    /*Loading Sponsors Details*/
    private void loadSponsors() {
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, URLHelper.getsponsorsDetails, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        parseSponserDetail(response);
                    } catch (Exception e) {
                        Toast.makeText(LoadingActivity.this, "Error in loading Sponsors", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(LoadingActivity.this, "Error in loading sponsors", Toast.LENGTH_SHORT).show();
                }
            });
            stringRequest.setRetryPolicy(
                    new DefaultRetryPolicy(
                            10000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                    )
            );

            RequestQueue queue = Volley.newRequestQueue(LoadingActivity.this);
            queue.add(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseSponserDetail(String response) throws JSONException {

        JSONObject jsonObject = new JSONObject(response);
        String error = jsonObject.getString("error");
        //   Log.d("DEBUG",""+error);

        if (error.equals("false")) {
            JSONArray jsonArray = new JSONArray(jsonObject.getString("message"));
            //  Log.d("DEBUG",jsonArray.toString());

            DatabaseHelper db = new DatabaseHelper(LoadingActivity.this);
            TableSponserDetails.deleteTableData(db.getWritableDatabase(), "delete from " + TableSponserDetails.TABLE_NAME);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectNode = jsonArray.getJSONObject(i);
                String sponser_name = jsonObjectNode.getString("spons_name");
                String sponser_image_url = jsonObjectNode.getString("spons_img_location");
                ContentValues cv = new ContentValues();
                cv.put(TableSponserDetails.SNAME, sponser_name);
                cv.put(TableSponserDetails.SURL, sponser_image_url);

                TableSponserDetails.insert(db.getWritableDatabase(), cv);

            }
        } else {
            Toast.makeText(this, "Error in Loading Data", Toast.LENGTH_SHORT).show();

        }
        startActivity(new Intent(LoadingActivity.this,MainActivity.class));
        finish();
    }


}
