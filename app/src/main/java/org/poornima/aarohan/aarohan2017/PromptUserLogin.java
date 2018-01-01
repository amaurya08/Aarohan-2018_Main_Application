package org.poornima.aarohan.aarohan2017;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;
import org.poornima.aarohan.aarohan2017.AarohanClasses.NetWorkManager;
import org.poornima.aarohan.aarohan2017.AarohanClasses.URLHelper;

import java.util.HashMap;
import java.util.Map;

public class PromptUserLogin extends AppCompatActivity {
    private com.google.android.gms.common.SignInButton loginButton;
    private TextView skipTextView;
    private static final int RC_SIGN_IN = 0;
    private static final String TAG = "DEBUG";
    private final static String errorNoInternet = "No Internet Access";
    private GoogleSignInClient mGoogleSignInClient;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt_user_login);
        init(); //will Initialize All The Variables
        methodListener(); //All Click Listeners
        googleLogOut();
        revokeAccess();
        //Creating Google Sign Options And Client


    }


    //All Method Listeners
    private void methodListener() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.sign_in_button:
                        if (NetWorkManager.checkInternetAccess(PromptUserLogin.this)) {
                            signIn();
                        } else
                            Toast.makeText(PromptUserLogin.this, errorNoInternet, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        skipTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PromptUserLogin.this, MainActivity.class));
                finish();
            }
        });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            updateUI(account);
        } catch (ApiException e) {
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }

    private void updateUI(GoogleSignInAccount account) {
        if (NetWorkManager.checkInternetAccess(PromptUserLogin.this)) {
            progressDialog.setMessage("Verifying Account....");
            progressDialog.setCancelable(false);
            progressDialog.show();
            checkUser(account);
        } else
            Toast.makeText(this, errorNoInternet, Toast.LENGTH_SHORT).show();
    }

    private void checkUser(GoogleSignInAccount account) {
        final GoogleSignInAccount acc = account;
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URLHelper.verifyEmail, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    parseString(response, acc);
                    progressDialog.cancel();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.cancel();
                    Toast.makeText(PromptUserLogin.this, "Please Check Your Internet Connection or Please Select a Google Account", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("email", acc.getEmail());
                    return map;
                }
            };
            stringRequest.setRetryPolicy(
                    new DefaultRetryPolicy(
                            5000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                    )
            );

            RequestQueue queue = Volley.newRequestQueue(PromptUserLogin.this);
            queue.add(stringRequest);

        } catch (Exception e) {
            Log.d(TAG, "" + e.toString());
        }

    }

    private void
    parseString(String response, GoogleSignInAccount account) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            String error = jsonObject.getString("error");
            String message = jsonObject.getString("message");
            if (error.equals("false")) {
                Toast.makeText(this, "Email Verified", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PromptUserLogin.this, LoginActivity.class);
                intent.putExtra("email", account.getEmail());
                startActivity(intent);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                mGoogleSignInClient.signOut()
                        .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(PromptUserLogin.this, "Please Login with Registered Email-ID", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        init();
        googleLogOut();
        revokeAccess();
    }

    @Override
    protected void onResume() {
        super.onResume();
        googleLogOut();
        revokeAccess();
    }

    private void init() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestProfile()
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        progressDialog = new ProgressDialog(PromptUserLogin.this);
        loginButton = findViewById(R.id.sign_in_button);
        skipTextView = findViewById(R.id.skip_text);
    }

    private void googleLogOut() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mGoogleSignInClient.signOut();
    }

    private void revokeAccess() {

        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                    }
                });
    }
}
