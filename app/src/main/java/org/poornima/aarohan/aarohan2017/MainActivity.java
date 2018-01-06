package org.poornima.aarohan.aarohan2017;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.ramotion.circlemenu.CircleMenuView;

import org.poornima.aarohan.aarohan2017.AarohanClasses.URLHelper;

public class MainActivity extends AppCompatActivity {

    private ImageView  Loginlogout,aarohan_selfi;
    private  CircleMenuView circleMenu;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        methodListener();

        if (checkSession()) {
            Loginlogout.setImageDrawable(getDrawable(R.drawable.logout_four_fity));
        } else {
            Loginlogout.setImageDrawable(getDrawable(R.drawable.login_four_fifty));
        }

    }


    private void init() {

        aarohan_selfi=(ImageView)findViewById(R.id.selfi);
        circleMenu = findViewById(R.id.circleMenu);
        Loginlogout = findViewById(R.id.login_logout);
    }

    private void methodListener() {
        Loginlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkSession())
                {
                    SharedPreferences sharedPref = getSharedPreferences("aarohan", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("email","");
                    editor.putString("otp","");
                    editor.putString("sid","");
                    editor.putBoolean("is",false);
                    editor.apply();
                }
                else
                {
                    startActivity(new Intent(MainActivity.this,PromptUserLogin.class));
                    finish();
                }
            }
        });


        circleMenu.setEventListener(new CircleMenuView.EventListener() {
                                        @Override
                                        public void onButtonClickAnimationStart(@NonNull CircleMenuView view, int buttonIndex) {
                                            switch (buttonIndex) {
                                                case 0:
                                                    switchActivity(ProfileActivity.class);
                                                    break;
                                                case 1:
                                                    switchActivity(ScheduleActivity.class);
                                                    break;
                                                case 2:
                                                  switchActivity(WorkshopActivity.class);
                                                    break;
                                                case 3:
                                                    switchActivity(MapActivity.class);
                                                    break;
                                                case 4:
                                                    switchActivity(AccmodationActivity.class);
                                                    break;
                                                case 5:
                                                   switchActivity(InfoActivity.class);
                                                    break;
                                                default:
                                                    Toast.makeText(MainActivity.this, ":)", Toast.LENGTH_SHORT).show();
                                                    break;

                                            }
                                        }

                                        @Override
                                        public void onMenuOpenAnimationStart(@NonNull CircleMenuView view) {

                                        }

                                        @Override
                                        public void onMenuCloseAnimationEnd(@NonNull CircleMenuView view) {

                                        }
                                    }

        );
        //openind camera activity
        aarohan_selfi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), FaceFilterActivity.class));
            }
        });
    }
//switiching Activity on menu item click
    private void switchActivity(final Class myclass) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, myclass));
            }
        }, 600);
    }

    public Boolean checkSession() {
        SharedPreferences sharedPref = getSharedPreferences("aarohan", MODE_PRIVATE);
        if (sharedPref.getBoolean("is", false))
            return true;
        else
            return false;

    }
}
