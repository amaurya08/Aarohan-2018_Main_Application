package org.poornima.aarohan.aarohan2017;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONException;
import org.json.JSONObject;
import org.poornima.aarohan.aarohan2017.AarohanClasses.NetWorkManager;
import org.poornima.aarohan.aarohan2017.AarohanClasses.URLHelper;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "DEBUG";
    private TextView resend;
    private EditText otp;
    private String intentEmail;
    private Button verify;
    private ProgressDialog progressDialog;
    
    private TextView countdownTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        methodListener();
        progressDialog.setMessage("Sending OTP....");
        progressDialog.setCancelable(false);
        progressDialog.show();
        if (NetWorkManager.checkInternetAccess(LoginActivity.this)) {
            sendOTP(intentEmail);
        } else
            Toast.makeText(LoginActivity.this, "No Internet", Toast.LENGTH_SHORT).show();
    }

    private void init() {
        intentEmail = getIntent().getStringExtra("email");
        otp = findViewById(R.id.otp);
        progressDialog = new ProgressDialog(LoginActivity.this);
        verify = findViewById(R.id.verify);
        resend = findViewById(R.id.resend);
        
        countdownTextView=(TextView)findViewById(R.id.countdownTimer);
    }

    private void methodListener() {
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (otp.getText().toString().length() == 8) {
                    progressDialog.setMessage("Verifying OTP....");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    if (NetWorkManager.checkInternetAccess(LoginActivity.this)) {
                        checkOTP(otp.getText().toString());
                    } else
                        Toast.makeText(LoginActivity.this, "No Internet", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(LoginActivity.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
            }
        });
        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resendotp();
            }
        });
    }

    private void resendotp() {
        resend.setVisibility(View.INVISIBLE);
        countdownTextView.setVisibility(View.VISIBLE);
        sendOTP(intentEmail);
        countdown();
    }

    private void countdown() {
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                countdownTextView.setText("Retry after: " + millisUntilFinished / 1000 +"seconds");
            }
            public void onFinish() {
                resend.setVisibility(View.VISIBLE);
                countdownTextView.setVisibility(View.INVISIBLE);
            }
        }.start();
    }

    private void checkOTP(final String text) {
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URLHelper.verifyOTP, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        progressDialog.cancel();
                        Log.d(TAG, "Checking OTP");
                        parseCheckOTPString(response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.cancel();
                    Toast.makeText(LoginActivity.this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("email", intentEmail);
                    map.put("otp", text);
                    return map;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
            queue.add(stringRequest);

        } catch (Exception e) {
            Log.d(TAG, "" + e.toString());
        }
    }

    private void parseCheckOTPString(String response) throws JSONException {

        JSONObject jsonObject = new JSONObject(response);
        String error = jsonObject.getString("error");
        String message = jsonObject.getString("message");
        if (error.equals("false")) {
            makeSession(message);
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else
            Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }

    private void makeSession(String message) {
        SharedPreferences sharedPref = getSharedPreferences("aarohan", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("email", intentEmail);
        editor.putString("otp", otp.getText().toString());
        editor.putString("sid", message);
        editor.putBoolean("is", true);
        Log.d(TAG, "Making Session with " + intentEmail + " " + otp.getText().toString() + " " + message);
        editor.apply();
    }

    private void sendOTP(final String intentEmail) {
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URLHelper.sendOTP, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        progressDialog.cancel();
                        Log.d(TAG, "OTP SENT");
                        parseStringOTP(response);
                    } catch (Exception e) {
                        Toast.makeText(LoginActivity.this, "" + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.cancel();
                    Toast.makeText(LoginActivity.this, "" + error.toString(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("email", intentEmail);
                    return map;
                }
            };
            stringRequest.setRetryPolicy(
                    new DefaultRetryPolicy(
                            10000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                    )
            );
            RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
            queue.add(stringRequest);

        } catch (Exception e) {
            Log.d(TAG, "" + e.toString());
        }
    }

    private void parseStringOTP(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        String error = jsonObject.getString("error");
        String message = jsonObject.getString("message");
        if (error.equals("false")) {
            Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Email ID is not Registered. Please Contact Web / Aplication Developer", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
