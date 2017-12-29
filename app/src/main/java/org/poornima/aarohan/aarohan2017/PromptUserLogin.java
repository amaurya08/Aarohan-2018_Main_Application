package org.poornima.aarohan.aarohan2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PromptUserLogin extends AppCompatActivity {
 private Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt_user_login);
        init();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PromptUserLogin.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void init(){
        b=findViewById(R.id.login_button);
    }
}
