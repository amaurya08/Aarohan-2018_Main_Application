package org.poornima.aarohan.aarohan2017;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.ramotion.circlemenu.CircleMenuView;

public class MainActivity extends AppCompatActivity {

    private ImageView aarohan_selfi, imageView;
    private  CircleMenuView circleMenu;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        methodListener();

        if (checkSession()) {
            imageView.setImageDrawable(getDrawable(R.drawable.logout_four_fity));
        } else {
            imageView.setImageDrawable(getDrawable(R.drawable.login_four_fifty));
        }

    }

    private void init() {
        aarohan_selfi = findViewById(R.id.header);
        circleMenu = findViewById(R.id.circleMenu);
        imageView = findViewById(R.id.login_logout);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }
    private void googleLogOut() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mGoogleSignInClient.signOut();
        startActivity(new Intent(MainActivity.this,MainActivity.class));
    }
    private void revokeAccess() {

        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(MainActivity.this, "Have A Good Day :)", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,MainActivity.class));
                        finish();

                    }
                });
    }
    private void methodListener() {
        imageView.setOnClickListener(new View.OnClickListener() {
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
                    editor.commit();
                    googleLogOut();
                    revokeAccess();
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
                                                    switchActivity(YourEventsActivity.class);
                                                    break;
                                                case 2:
                                                    switchActivity(ScheduleActivity.class);
                                                    break;
                                                case 3:
                                                    switchActivity(MapActivity.class);
                                                    break;
                                                case 4:
                                                    switchActivity(ContactUsActivity.class);
                                                    break;
                                                case 5:
                                                    switchActivity(DevelopersActivity.class);
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
