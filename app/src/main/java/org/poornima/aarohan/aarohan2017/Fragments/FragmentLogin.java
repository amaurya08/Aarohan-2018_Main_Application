package org.poornima.aarohan.aarohan2017.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
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
import com.crashlytics.android.Crashlytics;

import org.json.JSONException;
import org.json.JSONObject;
import org.poornima.aarohan.aarohan2017.AarohanClasses.CustomLoading;
import org.poornima.aarohan.aarohan2017.AarohanClasses.NetWorkManager;
import org.poornima.aarohan.aarohan2017.AarohanClasses.URLHelper;
import org.poornima.aarohan.aarohan2017.R;

import java.util.HashMap;
import java.util.Map;

import io.fabric.sdk.android.Fabric;

public class FragmentLogin extends Fragment {
    private Button submit;
    public static final String TAG = "DEBUG";
    private TextInputLayout inputLayout;
    private TextInputEditText emailEditText;
    private String email;
    private String emailMatcher;
    private CustomLoading customLoading;

    public FragmentLogin() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Fabric.with(getActivity(),new Crashlytics());
        View view = inflater.inflate(R.layout.fragment_fragment_login, container, false);
        init(view);
        methodListener();

        return view;
    }
    private void methodListener() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (NetWorkManager.checkInternetAccess(getActivity())) {
                    email = emailEditText.getText().toString();
                    if (email.matches(emailMatcher) && email.length() > 0) {
                        customLoading.show();
                        verifyEmail(email);
                    } else {
                        inputLayout.setError("Invalid Email");
                    }
                } else {
                    Toast.makeText(getActivity(), "No Internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void init(View view) {
        emailMatcher = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        emailEditText = view.findViewById(R.id.email);
        submit = view.findViewById(R.id.submit_email);
        customLoading = new CustomLoading(getActivity());
        inputLayout = view.findViewById(R.id.email_layout);
        email = "";
    }


    private void verifyEmail(final String s) {

            StringRequest request = new StringRequest(Request.Method.POST, URLHelper.verifyEmail, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    customLoading.cancel();

                    verifyParseString(response, s);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    customLoading.cancel();
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
    }


    private void verifyParseString(String response, String s) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            String error = jsonObject.getString("error");
            String message = jsonObject.getString("message");
            if (error.equals("false")) {
                getActivity();
                SharedPreferences sharedPref = getActivity().getSharedPreferences("aarohan", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("email",email);
                editor.apply();
                changeFragment(new FragmentOTP());
            } else {
                Toast.makeText(getActivity(), ""+message, Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
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
