package org.poornima.aarohan.aarohan2017;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class AccmodationActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_accmodation);
        getSupportActionBar().setTitle("Accommodation");
        textView=(TextView)findViewById(R.id.accomodation);
        Animation anim = new AlphaAnimation(0.5f,1.0f);
        anim.setDuration(300);
        anim.setStartOffset(15);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        textView.startAnimation(anim);
    }
    public void browser(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://aarohan.poornima.org/auth/google"));
        startActivity(browserIntent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}


