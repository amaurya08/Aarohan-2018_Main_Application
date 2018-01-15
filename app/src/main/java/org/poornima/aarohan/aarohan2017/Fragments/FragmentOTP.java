package org.poornima.aarohan.aarohan2017.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.crashlytics.android.Crashlytics;

import org.json.JSONException;
import org.json.JSONObject;
import org.poornima.aarohan.aarohan2017.AarohanClasses.CustomLoading;
import org.poornima.aarohan.aarohan2017.AarohanClasses.NetWorkManager;
import org.poornima.aarohan.aarohan2017.AarohanClasses.URLHelper;
import org.poornima.aarohan.aarohan2017.MainActivity;
import org.poornima.aarohan.aarohan2017.R;

import java.util.HashMap;
import java.util.Map;

import io.fabric.sdk.android.Fabric;

public class FragmentOTP extends Fragment {
    /*private static final String TAG = "DEBUG";*/
    private TextView resend;
    private EditText otp;
    private String email;
    private Button verify;
    private CustomLoading customLoading;
    private TextView countdownTextView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Fabric.with(getActivity(),new Crashlytics());
        View view = inflater.inflate(R.layout.fragment_fragment_ot, container, false);
        init(view);
        methodListener();
        customLoading.show();
        if (NetWorkManager.checkInternetAccess(getActivity())) {
            sendOTP(email);
        } else {
            Toast.makeText(getActivity(), "Enable Network Access", Toast.LENGTH_SHORT).show();
        }
        return view;
    }


    private void init(View view) {
        getActivity();
        SharedPreferences sharedPref = getActivity().getSharedPreferences("aarohan", Context.MODE_PRIVATE);
        email = sharedPref.getString("email","");
        otp = view.findViewById(R.id.otp);
        customLoading = new CustomLoading(getActivity());
        verify = view.findViewById(R.id.verify);
        resend = view.findViewById(R.id.resend);
        countdownTextView = view.findViewById(R.id.countdownTimer);
    }

    private void methodListener() {
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (otp.getText().toString().length() == 8) {
                    customLoading.show();
                        checkOTP(otp.getText().toString());
                } else
                    Toast.makeText(getActivity(), "Invalid OTP", Toast.LENGTH_SHORT).show();
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
        sendOTP(email);
        countdown();
    }

    private void countdown() {
        new CountDownTimer(20000, 1000) {

            public void onTick(long millisUntilFinished) {
                String setText="Retry after: " + millisUntilFinished / 1000 +"seconds";
                countdownTextView.setText(setText);
            }
            public void onFinish() {
                resend.setVisibility(View.VISIBLE);
                countdownTextView.setVisibility(View.INVISIBLE);
            }
        }.start();
    }

    private void checkOTP(final String text) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLHelper.verifyOTP, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        customLoading.cancel();
                       /* Log.d(TAG, "Checking OTP");*/
                        parseCheckOTPString(response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    customLoading.cancel();
                    Toast.makeText(getActivity(), "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("email", email);
                    map.put("otp", text);
                    map.put("type","STUDENT");
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
            RequestQueue queue = Volley.newRequestQueue(getActivity());
            queue.add(stringRequest);
            /*RequestQueue queue = Volley.newRequestQueue(OTPActivity.this);
            queue.add(stringRequest);*/
    }

    private void parseCheckOTPString(String response) throws JSONException {

        JSONObject jsonObject = new JSONObject(response);
        String error = jsonObject.getString("error");
        String message = jsonObject.getString("message");
        if (error.equals("false")) {
            makeSession(message);
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        } else
            Toast.makeText(getActivity(), "" + message, Toast.LENGTH_SHORT).show();
    }

    private void makeSession(String message) {
        getActivity();
        SharedPreferences sharedPref = getActivity().getSharedPreferences("aarohan", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("email", email);
        Crashlytics.setUserIdentifier(message);
        editor.putString("otp", otp.getText().toString());
        Crashlytics.setUserEmail(email);
        editor.putString("sid", message);
        editor.putBoolean("is", true);
        /*Log.d(TAG, "Making Session with " + email + " " + otp.getText().toString() + " " + message);*/
        editor.apply();
    }

    private void sendOTP(final String email) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URLHelper.sendOTP, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        customLoading.cancel();
                        /*Log.d(TAG, "OTP SENT");*/
                        parseStringOTP(response);
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), "" + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    customLoading.cancel();
                    Toast.makeText(getActivity(), "" + error.toString(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("email", email);
                    map.put("type","STUDENT");
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
            RequestQueue queue = Volley.newRequestQueue(getActivity());
            queue.add(stringRequest);

    }

    private void parseStringOTP(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        String error = jsonObject.getString("error");
        String message = jsonObject.getString("message");
        if (error.equals("false")) {
            Toast.makeText(getActivity(), "" + message, Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(getActivity(), "Email ID is not Registered. Please Contact Web / Aplication Developer", Toast.LENGTH_SHORT).show();
    }


}
