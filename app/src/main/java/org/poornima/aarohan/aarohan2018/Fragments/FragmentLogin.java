package org.poornima.aarohan.aarohan2018.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import org.json.JSONException;
import org.json.JSONObject;
import org.poornima.aarohan.aarohan2018.AarohanClasses.CustomLoading;
import org.poornima.aarohan.aarohan2018.AarohanClasses.NetWorkManager;
import org.poornima.aarohan.aarohan2018.AarohanClasses.URLHelper;
import org.poornima.aarohan.aarohan2018.R;

import java.util.HashMap;
import java.util.Map;



public class FragmentLogin extends Fragment {

    private FragmentActivity activity;
    private Context context;



    private Button submit;
    private TextInputLayout inputLayout;
    private TextInputEditText emailEditText;
    private String email;

    private String emailMatcher;
    private CustomLoading customLoading;
    public FragmentLogin() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_fragment_login, container, false);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        Log.d("DEBUG", context.getPackageName());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        email = "";
        emailMatcher = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        activity = (FragmentActivity) context;
        customLoading = new CustomLoading(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        methodListener();
    }

    private void methodListener() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (NetWorkManager.checkInternetAccess(context)) {
                    email = emailEditText.getText().toString();
                    if (email.matches(emailMatcher) && email.length() > 0) {
                        customLoading.show();
                        verifyEmail(email);
                    } else {
                        inputLayout.setError("Invalid Email");
                    }
                } else {
                    Toast.makeText(context, "No Internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void init(View view) {

        emailEditText = view.findViewById(R.id.email);
        submit = view.findViewById(R.id.submit_email);

        inputLayout = view.findViewById(R.id.email_layout);

    }


    private void verifyEmail(String email) {
        final String s = email;

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
                    Toast.makeText(context, "" + error, Toast.LENGTH_SHORT).show();
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
        RequestQueue queue = Volley.newRequestQueue(context);
            queue.add(request);
    }


    private void verifyParseString(String response, String s) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            String error = jsonObject.getString("error");
            String message = jsonObject.getString("message");
            if (error.equals("false")) {
                SharedPreferences sharedPref = context.getSharedPreferences("aarohan", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("email", s);
                editor.apply();
                changeFragment(new FragmentOTP());
            } else {
                Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            Toast.makeText(context, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void changeFragment(Fragment fragment) {

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.replace(R.id.fragment_container, fragment);
        transaction.remove(this);
        transaction.commitAllowingStateLoss();
    }

}
