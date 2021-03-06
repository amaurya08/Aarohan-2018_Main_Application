package org.poornima.aarohan.aarohan2018.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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

import org.json.JSONException;
import org.json.JSONObject;
import org.poornima.aarohan.aarohan2018.AarohanClasses.URLHelper;
import org.poornima.aarohan.aarohan2018.LoadingActivity;
import org.poornima.aarohan.aarohan2018.MainActivity;
import org.poornima.aarohan.aarohan2018.R;

import java.util.HashMap;
import java.util.Map;



public class FragmentOTP extends Fragment {
    /*private static final String TAG = "DEBUG";*/
    private TextView resend;
    private EditText otp;
    private String email;
    private Button verify;
    private ProgressDialog customLoading;
    private TextView countdownTextView;

    private FragmentActivity activity;
    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        activity = (FragmentActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customLoading = new ProgressDialog(context,ProgressDialog.THEME_HOLO_DARK);
        customLoading.setCancelable(false);
        SharedPreferences sharedPref = activity.getSharedPreferences("aarohan", Context.MODE_PRIVATE);
        email = sharedPref.getString("email", "");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        methodListener();
        customLoading.setMessage("Sending OTP...");
        customLoading.show();
        sendOTP(email);
    }

    private void init(View view) {
        otp = view.findViewById(R.id.otp);
        verify = view.findViewById(R.id.verify);
        resend = view.findViewById(R.id.resend);
        countdownTextView = view.findViewById(R.id.countdownTimer);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment_ot, container, false);
    }

    private void methodListener() {
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (otp.getText().toString().length() == 8) {
                    customLoading.setMessage("Verifying OTP, Please Wait...");
                    customLoading.show();
                    checkOTP(otp.getText().toString());
                } else
                    Toast.makeText(context, "Invalid OTP", Toast.LENGTH_SHORT).show();
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
                        customLoading.dismiss();
                       /* Log.d(TAG, "Checking OTP");*/
                        parseCheckOTPString(response);
                    } catch (Exception e) {
                        customLoading.dismiss();
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    customLoading.dismiss();
                    Toast.makeText(context, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
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
        RequestQueue queue = Volley.newRequestQueue(activity);
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
            Intent intent = new Intent(getActivity(), LoadingActivity.class);
            startActivity(intent);
            activity.finish();
        } else
            Toast.makeText(getActivity(), "" + message, Toast.LENGTH_SHORT).show();
    }

    private void makeSession(String message) {
        getActivity();
        SharedPreferences sharedPref = activity.getSharedPreferences("aarohan", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("email", email);

        editor.putString("otp", otp.getText().toString());

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
                        customLoading.dismiss();
                        /*Log.d(TAG, "OTP SENT");*/
                        parseStringOTP(response);
                    } catch (Exception e) {
                        //Toast.makeText(getActivity(), "" + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    customLoading.dismiss();
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
        RequestQueue queue = Volley.newRequestQueue(activity);
            queue.add(stringRequest);

    }

    private void parseStringOTP(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        String error = jsonObject.getString("error");
        String message = jsonObject.getString("message");
        if (error.equals("false")) {
            Toast.makeText(getActivity(), "OTP sent to Your Email-ID", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(activity, "Email ID is not Registered. Please Contact Web / Aplication Developer", Toast.LENGTH_SHORT).show();
    }


}
