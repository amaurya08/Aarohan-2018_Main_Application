package org.poornima.aarohan.aarohan2017;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.imangazaliev.circlemenu.CircleMenu;
import com.imangazaliev.circlemenu.CircleMenuButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CircleMenu circleMenu = findViewById(R.id.circleMenu);
        circleMenu.setOnItemClickListener(new CircleMenu.OnItemClickListener() {
            @Override
            public void onItemClick(CircleMenuButton menuButton) {
                switch (menuButton.getId()) {

                    case R.id.your_events:
                        switchActivity(YourEventsActivity.class);
                        break;
                    case R.id.maps:
                        switchActivity(MapsActivity.class);
                        break;
                    case R.id.schedule:
                        switchActivity(ScheduleActivity.class);
                        break;
                    case R.id.your_profile:
                        switchActivity(ProfileActivity.class);
                        break;
                    case R.id.contact_us:
                        switchActivity(ContactUsActivity.class);
                        break;
                    case R.id.developers:
                        switchActivity(DevelopersActivity.class);
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "None CLicked", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
    private void switchActivity(final Class myclass){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, myclass));
            }
        },600);
    }

}
