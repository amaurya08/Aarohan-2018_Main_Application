package org.poornima.aarohan.aarohan2017;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    private  ProgressBar mProgress;
    TextView splash_prog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mProgress = (ProgressBar) findViewById(R.id.splash_screen_progress_bar);
        splash_prog=(TextView)findViewById(R.id.splash_progress);


      new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
                startApp();
                finish();
            }
        }).start();
    }

        private void doWork() {
            for (int progress=0; progress<100; progress+=1) {
                try {
                    Thread.sleep(30);
                    splash_prog.setText("Loading..."+String.valueOf(progress)+"%");
                    mProgress.setProgress(progress);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private void startApp() {
            Intent intent = new Intent(SplashActivity.this, PromptUserLogin.class);
            startActivity(intent);
        }

}
