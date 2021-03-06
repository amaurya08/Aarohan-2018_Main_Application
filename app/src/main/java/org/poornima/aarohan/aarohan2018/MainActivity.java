package org.poornima.aarohan.aarohan2018;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.ramotion.circlemenu.CircleMenuView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.poornima.aarohan.aarohan2018.AarohanClasses.URLHelper;
import org.poornima.aarohan.aarohan2018.DBhandler.DatabaseHelper;
import org.poornima.aarohan.aarohan2018.Tables.ProfileTable;
import org.poornima.aarohan.aarohan2018.Tables.TableMyeventsDetails;

import java.util.HashMap;
import java.util.Map;



public class MainActivity extends AppCompatActivity {
    private Button aarohan_selfi,Loginlogout;
    private CircleMenuView circleMenu;
    private boolean back = false;
    View toplay;

    private FloatingActionMenu fam;
    private FloatingActionButton fab1, fab2, fab3;

    private void init() {
        aarohan_selfi = findViewById(R.id.selfi);
        circleMenu = findViewById(R.id.circleMenu);
        Loginlogout = findViewById(R.id.login_logout);
        fab1 = findViewById(R.id.fab1);
        fab2 = findViewById(R.id.fab2);
        fam =  findViewById(R.id.fab_menu);
    }

    private void methodListener() {
        Loginlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkSession()) {
                    logout();
                    SharedPreferences sharedPref = getSharedPreferences("aarohan", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("email", "");
                    editor.putString("stu_name", "");
                    editor.putString("otp", "");
                    editor.putString("sid", "");
                    editor.putBoolean("is", false);
                    editor.apply();
                    DatabaseHelper db = new DatabaseHelper(MainActivity.this);
                    ProfileTable.clearProfile(db.getWritableDatabase(), "delete from " + ProfileTable.tablename);
                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(MainActivity.this, PromptUserLogin.class));
                    finish();
                }
            }
        });

        circleMenu.setEventListener(new CircleMenuView.EventListener() {
                                        @Override
                                        public void onButtonClickAnimationStart(@NonNull CircleMenuView view, int buttonIndex) {
                                            switch (buttonIndex) {
                                                case 0:
                                                    switchActivity(ProfileActivity.class);
                                                    break;
                                                case 1:
                                                    switchActivity(ScheduleActivity.class);
                                                    break;
                                                case 2:
                                                    switchActivity(MapActivity.class);
                                                    break;
                                                case 3:
                                                    switchActivity(AccmodationActivity.class);
                                                    break;
                                                case 4:
                                                    switchActivity(InfoActivity.class);
                                                    break;
                                                default:
                                                    Toast.makeText(MainActivity.this, ":)", Toast.LENGTH_SHORT).show();
                                                    break;
                                            }
                                        }
                                        @Override
                                        public void onMenuOpenAnimationStart(@NonNull CircleMenuView view) {
                                        }
                                        @Override
                                        public void onMenuCloseAnimationEnd(@NonNull CircleMenuView view) {
                                        }
                                    }

        );
        //openind camera activity
        aarohan_selfi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                        ) {
                    Toast.makeText(MainActivity.this, "Need permission !", Toast.LENGTH_SHORT).show();
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            3);
                } else
                    startActivity(new Intent(getApplicationContext(), FaceFilterActivity.class));
            }
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = getFacebookPageURL(MainActivity.this);
                facebookIntent.setData(Uri.parse(facebookUrl));
                startActivity(facebookIntent);
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent likeIng = new Intent(Intent.ACTION_VIEW,  Uri.parse("http://instagram.com/aarohanpoornima"));
                likeIng.setPackage("com.instagram.android");
                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/aarohanpoornima")));
                }
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            requestPermission();
        if (checkSession()) {
            Loginlogout.setText("Log Out");
            /*profileAPI();*/
        } else {
            Loginlogout.setText("Log In");
        }
        toplay = findViewById(R.id.overlayscreen);
        if(isFirstTime()){
            toplay.setVisibility(View.INVISIBLE);
        }
        methodListener();
    }


    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
        || ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
       || ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA},
                    1);
        }
    }

   /* private void requestStorage() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                ) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    2);
        }
    }

    private void requestCamera() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.CAMERA},
                    3);
        }
    }*/
/*

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Toast.makeText(this, "Location Permission Granted", Toast.LENGTH_SHORT).show();
                    requestStorage();
                } else {
                    Toast.makeText(this, "Location Permission Denied", Toast.LENGTH_SHORT).show();
                    requestPermissionLoc();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            case 2:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Toast.makeText(this, "Storage Permission Granted", Toast.LENGTH_SHORT).show();
                    requestCamera();
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    Toast.makeText(this, "Storage Permission Denied", Toast.LENGTH_SHORT).show();
                    requestStorage();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            case 3:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Camera Permission Granted", Toast.LENGTH_SHORT).show();
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    Toast.makeText(this, "Camera Permission Denied", Toast.LENGTH_SHORT).show();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
        }
    }
*/

    private boolean isFirstTime()
    {
        SharedPreferences prefernces = getPreferences(MODE_PRIVATE);
        boolean ranBefore = prefernces.getBoolean("ranbefore",false);
        if(!ranBefore)
        {
            SharedPreferences.Editor editor=prefernces.edit();
            editor.putBoolean("ranbefore",true);
            editor.apply();
            toplay.setVisibility(View.VISIBLE);
            toplay.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    toplay.setVisibility(View.INVISIBLE);
                    return false;
                }
            });
        }
        return ranBefore;

    }
    @Override
    protected void onRestart() {
        super.onRestart();
    }

/*
    private void profileMyeventAPI() {
        StringRequest stringRequest;
        stringRequest = new StringRequest(Request.Method.POST, URLHelper.studenteventdetails, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //  Log.d("DEBUG",""+response);
                    parsestudenteventDetail(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                   // Toast.makeText(MainActivity.this, ""+errorString, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
              //  Toast.makeText(MainActivity.this, errorString, Toast.LENGTH_SHORT).show();
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
                map.put("type","STUDENT");
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(stringRequest);
    }
    private void parsestudenteventDetail(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        String error = jsonObject.getString("error");
        //   Log.d("DEBUG", "" + error);

        if (error.equals("false")) {
            JSONArray jsonArray = new JSONArray(jsonObject.getString("message"));
            //  Log.d("DEBUG", jsonArray.toString());

            DatabaseHelper db = new DatabaseHelper(MainActivity.this);
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

    private void profileAPI() {
        StringRequest request;
        request = new StringRequest(Request.Method.POST, URLHelper.ProfileData, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //    Log.d("DEBUG", "response recieved");
                parseProfile(response);
                profileMyeventAPI();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
              //  Toast.makeText(MainActivity.this, "" + errorString, Toast.LENGTH_SHORT).show();
                //  Log.d("DEBUG", "" + error.getMessage());
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
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
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

                SharedPreferences sharedPref = getSharedPreferences("aarohan",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("stu_name",stu_name);
                editor.apply();


                DatabaseHelper db = new DatabaseHelper(MainActivity.this);
                long x = ProfileTable.insertDetails(db.getWritableDatabase(), cv);
                //  Log.d("DEBUG", "" + x);
            } else {
              //  Toast.makeText(this, ""+errorString, Toast.LENGTH_SHORT).show();
                //   Log.d("DEBUG",""+jsonObject.getString("message"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
*/

    public static String FACEBOOK_URL = "https://www.facebook.com/PoornimaAarohan";
    public static String FACEBOOK_PAGE_ID = "PoornimaAarohan";

    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }
    private void logout() {
        try {
            //    Log.d("DEBUG", "Request Sent");
            StringRequest request = new StringRequest(Request.Method.POST, URLHelper.logOut, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(MainActivity.this, "Bye Bye", Toast.LENGTH_SHORT).show();
                    // Log.d("DEBUG", "Response Recieved");
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //   Log.d("TAG", error + "");
                  //  Toast.makeText(MainActivity.this, "" + errorString, Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> map = new HashMap<>();
                    SharedPreferences sharedPref = getSharedPreferences("aarohan", MODE_PRIVATE);
                    String emailprof = sharedPref.getString("email", "");
                    String otpprof = sharedPref.getString("otp", "");
                    map.put("email",emailprof);
                    map.put("otp",otpprof);
                    map.put("type","STUDENT");
                    return map;
                }
            };
            request.setRetryPolicy(
                    new DefaultRetryPolicy(
                            10000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                    )
            );
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
            queue.add(request);
        } catch (Exception e) {
            Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    //switiching Activity on menu item click
    private void switchActivity(final Class myclass) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, myclass));
            }
        }, 700);
    }

    public Boolean checkSession() {
        SharedPreferences sharedPref = getSharedPreferences("aarohan", MODE_PRIVATE);
        return sharedPref.getBoolean("is", false);

    }
    @Override
    public void onBackPressed() {

        if(back){
            super.onBackPressed();
            return ;
        }
        this.back=true;
        Toast.makeText(this, "Please Click Twice to Exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                back=false;
            }
        },2000);

    }
}

