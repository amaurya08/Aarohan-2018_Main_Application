package org.poornima.aarohan.aarohan2017;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PromptUserLogin extends AppCompatActivity {
    private Button loginButton, register;
    private TextView skipTextView;
    private static final int RC_SIGN_IN = 0;
    private static final String TAG = "DEBUG";
    private final static String errorNoInternet = "No Internet Access";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt_user_login);
        init(); //will Initialize All The Variables
        methodListener(); //All Click Listeners

    }

    private void methodListener() {

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PromptUserLogin.this, LoginActivity.class));
                finish();
            }
        });

        skipTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PromptUserLogin.this, MainActivity.class));
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://aarohan.poornima.org"));
                startActivity(browserIntent);
            }
        });
    }

    private void init() {
        progressDialog = new ProgressDialog(PromptUserLogin.this);
        loginButton = findViewById(R.id.sign_in_button);
        skipTextView = findViewById(R.id.skip_text);
        register = findViewById(R.id.register_button);

    }
}
