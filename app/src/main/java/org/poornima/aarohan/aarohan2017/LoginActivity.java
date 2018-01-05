package org.poornima.aarohan.aarohan2017;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.poornima.aarohan.aarohan2017.AarohanClasses.URLHelper;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
private Button submit;
private EditText emailEditText;
private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_selection);
        init();
        methodListener();
    }

    private void methodListener() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                verifyEmail(emailEditText.getText().toString());
            }
        });
    }

    private void init() {
        emailEditText = findViewById(R.id.email);
        submit = findViewById(R.id.submit_email);
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Verifying Email...");
        progressDialog.setCancelable(false);
    }
    private void verifyEmail(final String s) {
        try{
            StringRequest request = new StringRequest(Request.Method.POST, URLHelper.verifyEmail, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.cancel();
                    verifyParseString(response,s);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("TAG",error+"");
                    progressDialog.cancel();
                    Toast.makeText(LoginActivity.this, ""+error, Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap <String,String> map = new HashMap<>();
                    map.put("email",s);

                    return map;
                }
            }
                    ;
        }
        catch(Exception e){
            
        }
    }

    private void verifyParseString(String response,String s) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            String error = jsonObject.getString("error");
            String message = jsonObject.getString("message");
            if(error.equals("false")){
                Intent intent = new Intent(LoginActivity.this,OTPActivity.class);
                intent.putExtra("email",s);
                startActivity(intent);
                finish();
            }
            else{

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
