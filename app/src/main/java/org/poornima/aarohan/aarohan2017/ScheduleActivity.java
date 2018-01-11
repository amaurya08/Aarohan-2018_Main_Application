package org.poornima.aarohan.aarohan2017;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
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
import org.poornima.aarohan.aarohan2017.Tables.TableEventDetails;
import org.poornima.aarohan.aarohan2017.Tables.TableSponserDetails;

public class ScheduleActivity extends AppCompatActivity implements View.OnClickListener {
    RelativeLayout day1event,day2event,day3event,day4event,day5event;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        init();
        day1event.setOnClickListener(this);
        day2event.setOnClickListener(this);
        day3event.setOnClickListener(this);
        day4event.setOnClickListener(this);
        day5event.setOnClickListener(this);
        loadEventDetails();
    }

    private void loadEventDetails() {
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, URLHelper.eventData, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        parseEventDetails(response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(ScheduleActivity.this, "Error in loding Event Details", Toast.LENGTH_SHORT).show();
                }
            });
            stringRequest.setRetryPolicy(
                    new DefaultRetryPolicy(
                            10000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                    )
            );
            RequestQueue queue = Volley.newRequestQueue(ScheduleActivity.this);
            queue.add(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void parseEventDetails(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        String error = jsonObject.getString("error");
        Log.d("DEBUG",""+error);

        if(error.equals("false"))
        {
            JSONArray jsonArray = new JSONArray(jsonObject.getString("message"));
            Log.d("DEBUG",jsonArray.toString());

            DatabaseHelper db = new DatabaseHelper(ScheduleActivity.this);
            TableSponserDetails.deleteTableData(db.getWritableDatabase(),"delete from "+ TableEventDetails.TABLE_NAME);

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

               long j= TableEventDetails.insert(db.getWritableDatabase(), cv);

               Log.d("DEBUG","value inserted in event table"+j);
            }
        }
        else{
            Toast.makeText(this, "DataParsing error", Toast.LENGTH_SHORT).show();
        }
    }

    private void init() {
        day1event = findViewById(R.id.day1layout);
        day2event = findViewById(R.id.day2layout);
        day3event = findViewById(R.id.day3layout);
        day4event = findViewById(R.id.day4layout);
        day5event = findViewById(R.id.day5layout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.day1layout:
                callNextActivity("30/01/2018");
                break;
            case R.id.day2layout:
                callNextActivity("31/01/2018");
                break;
            case R.id.day3layout:
                callNextActivity("01/02/2018");
                break;
            case R.id.day4layout:
                callNextActivity("02/02/2018");
                break;
            case R.id.day5layout:
                callNextActivity("03/02/2018");
                break;
                default:
        }

    }

    private void callNextActivity(String day) {
     Intent intent=new Intent(ScheduleActivity.this,ScheduleEventListActivity.class);
     intent.putExtra("day",day);
     startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
