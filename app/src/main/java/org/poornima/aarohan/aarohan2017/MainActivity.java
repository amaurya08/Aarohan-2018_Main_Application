package org.poornima.aarohan.aarohan2017;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;
import com.ramotion.circlemenu.CircleMenuView;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {
private ImageView aarohan_selfi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aarohan_selfi=(ImageView)findViewById(R.id.header);
        CircleMenuView circleMenu = findViewById(R.id.circleMenu);
        circleMenu.setEventListener(new CircleMenuView.EventListener() {
                                        @Override
                                        public void onButtonClickAnimationStart(@NonNull CircleMenuView view, int buttonIndex) {
                                            switch(buttonIndex){
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
                startActivity(new Intent(getApplicationContext(),FaceFilterActivity.class));
            }
        });
    }

    private void switchActivity(final Class myclass) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, myclass));
            }
        }, 600);
    }

}
