package org.poornima.aarohan.aarohan2017.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import org.poornima.aarohan.aarohan2017.AarohanClasses.URLHelper;
import org.poornima.aarohan.aarohan2017.LoginActivity;
import org.poornima.aarohan.aarohan2017.OTPActivity;
import org.poornima.aarohan.aarohan2017.R;

import java.util.HashMap;
import java.util.Map;

public class FragmentLogin extends Fragment {
    private Button submit;
    public static final String TAG = "DEBUG";
    private EditText emailEditText;
    private String email;
    private ProgressDialog progressDialog;

    public FragmentLogin() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_login, container, false);
        init(view);
        methodListener();
        return view;

    }

    private void methodListener() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Verifying EMail");
                progressDialog.show();
                email = emailEditText.getText().toString();
                verifyEmail(email);
            }
        });
    }

    private void init(View view) {
        emailEditText = view.findViewById(R.id.email);
        submit = view.findViewById(R.id.submit_email);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Verifying Email...");
        progressDialog.setCancelable(false);
    }
    // TODO: Rename method, update argument and hook method into UI event

    private void verifyEmail(final String s) {
        try {
            Log.d(TAG, "Request Sent");
            StringRequest request = new StringRequest(Request.Method.POST, URLHelper.verifyEmail, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.cancel();
                    Log.d(TAG, "Response Recieved");
                    verifyParseString(response, s);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("TAG", error + "");
                    progressDialog.cancel();
                    Toast.makeText(getActivity(), "" + error, Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("email", s);
                    map.put("type", "STUDENT");
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
            RequestQueue queue = Volley.newRequestQueue(getActivity());
            queue.add(request);
        } catch (Exception e) {

        }

    }

    private void verifyParseString(String response, String s) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            String error = jsonObject.getString("error");
            String message = jsonObject.getString("message");
            if (error.equals("false")) {
                SharedPreferences sharedPref = getActivity().getSharedPreferences("aarohan", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("email",email);
                editor.apply();
                changeFragment(new FragmentOTP());
            } else {
                Toast.makeText(getActivity(), ""+message, Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void changeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

}