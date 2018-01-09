package org.poornima.aarohan.aarohan2017.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.poornima.aarohan.aarohan2017.LoginActivity;
import org.poornima.aarohan.aarohan2017.MainActivity;
import org.poornima.aarohan.aarohan2017.PromptUserLogin;
import org.poornima.aarohan.aarohan2017.R;


public class FragmentPromptUser extends Fragment {
    private Button loginButton, register;
    private TextView skipTextView;
    private static final int RC_SIGN_IN = 0;
    private static final String TAG = "DEBUG";
    private final static String errorNoInternet = "No Internet Access";
    private ProgressDialog progressDialog;
    public FragmentPromptUser() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prompt_user, container, false);
        init(view); //will Initialize All The Variables
        methodListener(); //All Click Listeners
        // Inflate the layout for this fragment
        return view;
    }
    private void methodListener() {

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new FragmentLogin());
            }
        });

        skipTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://aarohan.poornima.org"));
                startActivity(browserIntent);
            }
        });
    }

    private void init(View view) {
        progressDialog = new ProgressDialog(getActivity());
        loginButton = view.findViewById(R.id.sign_in_button);
        skipTextView = view.findViewById(R.id.skip_text);
        register = view.findViewById(R.id.register_button);

    }
    public void changeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}
