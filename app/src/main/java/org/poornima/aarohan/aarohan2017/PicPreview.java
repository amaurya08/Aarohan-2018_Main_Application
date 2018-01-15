package org.poornima.aarohan.aarohan2017;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.crashlytics.android.Crashlytics;

import java.io.File;

import io.fabric.sdk.android.Fabric;

public class PicPreview extends AppCompatActivity {

    private ImageView previewImageview;
    RelativeLayout cancelLayout,saveLayout,shareLaayout;
    private String finalimagepath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_pic_preview);

        cancelLayout = findViewById(R.id.layout_cancel);
        saveLayout = findViewById(R.id.layout_save);
        shareLaayout = findViewById(R.id.layout_share);

        previewImageview = findViewById(R.id.image_preview_id);


        finalimagepath = (String) getIntent().getExtras().get("filepath");
        File imgFile = new File(Environment.getExternalStorageDirectory() + finalimagepath);
        if (imgFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
       // Bitmap myBitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+st);
            previewImageview.setImageBitmap(myBitmap);
        }
        cancelLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteStoredImage();
            }
        });
        saveLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),FaceFilterActivity.class));
                finish();
            }
        });
        shareLaayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/*");
                //  share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File("file:///assets/epic/adv.png")));
                share.putExtra(Intent.EXTRA_STREAM, Uri.parse(Environment.getExternalStorageDirectory()+finalimagepath));
                PicPreview.this.startActivity(Intent.createChooser(share, "Share AAROHAN-2K18 Selfie With"));
            }
        });
    }

    private void deleteStoredImage() {
        File file = new File(Environment.getExternalStorageDirectory()+finalimagepath);
        boolean deleted = file.delete();
        if(deleted)
        {
            startActivity(new Intent(getApplicationContext(),FaceFilterActivity.class));
            finish();;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        /*deleteStoredImage();*/
        finish();
    }
}
