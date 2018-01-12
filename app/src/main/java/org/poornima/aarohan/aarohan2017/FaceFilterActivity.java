package org.poornima.aarohan.aarohan2017;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

import org.poornima.aarohan.aarohan2017.camera.CameraSourcePreview;
import org.poornima.aarohan.aarohan2017.camera.GraphicOverlay;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FaceFilterActivity extends AppCompatActivity {
    private static final String TAG = "FaceTracker";
    private CameraSource mCameraSource = null;
    private CameraSourcePreview mPreview;
    private GraphicOverlay mGraphicOverlay;
    private Button click_img;

    private static final int RC_HANDLE_GMS = 9001;
    private static final int RC_HANDLE_CAMERA_PERM = 2;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_face_filter);

        mPreview = (CameraSourcePreview) findViewById(R.id.preview);
        mGraphicOverlay = (GraphicOverlay) findViewById(R.id.faceOverlay);
        click_img = (Button) findViewById(R.id.btn_img_click);


        // Check for the camera permission before accessing the camera.  If the
        // permission is not granted yet, request permission.
        int rc = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (rc == PackageManager.PERMISSION_GRANTED) {
            createCameraSource();
        } else {
            requestCameraPermission();
        }
       /* checkPermissionStatus();*/

        click_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean storagepermissionstatus=isStoragePermissionGranted();
                if(storagepermissionstatus){
                    mCameraSource.takePicture(myShutterCallback,
                            myPictureCallback_JPG);
                }
                else{
                    Toast.makeText(getApplicationContext(),
                            "Storage permission required",Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    /* private void checkPermissionStatus() {
         if (ContextCompat.checkSelfPermission(FaceFilterActivity.this, Manifest.permission.CAMERA) < 0
                 || ContextCompat.checkSelfPermission(FaceFilterActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) < 0
                 || ContextCompat.checkSelfPermission(FaceFilterActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) < 0)
         {
             String string1[] = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
             ActivityCompat.requestPermissions(FaceFilterActivity.this, string1, 1);
             int rc = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA);

             if (rc == PackageManager.PERMISSION_GRANTED) {
                 createCameraSource();
             }
         }
         else{
             int rc = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
             if (rc == PackageManager.PERMISSION_GRANTED) {
                 createCameraSource();
             }

         }

     }*/
    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }
    //.......for clicking image and saving into storage.........
    CameraSource.ShutterCallback myShutterCallback=new CameraSource.ShutterCallback() {
        @Override
        public void onShutter() {
            // MediaPlayer.create(SecondCamera.this,R.raw.camera_click).start();
            AudioManager mgr = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            mgr.playSoundEffect(AudioManager.FLAG_PLAY_SOUND);
        }
    };


    CameraSource.PictureCallback myPictureCallback_JPG=new CameraSource.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data) {
            //to get width and height of the screen
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = displayMetrics.heightPixels;
            int width = displayMetrics.widthPixels;
//mirror() rotate camera capture
            Bitmap cameraBitmap = mirror(BitmapFactory.decodeByteArray(data, 0, data.length));
            Bitmap cameraScaledBitmap = Bitmap.createScaledBitmap(cameraBitmap, width, height, true);
            int wid = cameraScaledBitmap.getWidth();
            int hgt = cameraScaledBitmap.getHeight();
            Bitmap newImage = Bitmap.createBitmap(wid, hgt, Bitmap.Config.ARGB_8888);
//it take the screenshot of particular layout or view.....used to create mask bitmap
            View u = findViewById(R.id.faceOverlay);
            u.setDrawingCacheEnabled(true);
            org.poornima.aarohan.aarohan2017.camera.GraphicOverlay z = (org.poornima.aarohan.aarohan2017.camera.GraphicOverlay) findViewById(R.id.faceOverlay);
            int totalHeight = z.getHeight();
            int totalWidth = z.getWidth();
            u.layout(0, 0, totalWidth, totalHeight);
            u.buildDrawingCache(true);

            Bitmap overlayBitmap = Bitmap.createBitmap(u.getDrawingCache());
            u.setDrawingCacheEnabled(false);
//Scaling mask image and draw camera capture and mask on a caanwas
            Bitmap overlayScaledBitmap = Bitmap.createScaledBitmap(overlayBitmap, wid, hgt, true);
            Canvas canvas = new Canvas(newImage);

            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            paint.setStrokeWidth(3);
            paint.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ComicSansMSRegular.ttf"));

          /*  paint.setStyle(Paint.Style.FILL);
            canvas.drawPaint(paint);
            paint.setColor(Color.WHITE);
            paint.setTextSize(30);
            paint.setFakeBoldText(true);*/


            canvas.drawBitmap(cameraScaledBitmap , 0, 0, null);
            canvas.drawBitmap(overlayScaledBitmap , 0, 0, null);

            canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.icons_camera_64),10,height-150,paint);
            paint.setTextSize(30);
            canvas.drawText("AAROHAN-2018", 120, totalHeight-45, paint);
            String clickName=(getSharedPreferences("aarohan", MODE_PRIVATE)).getString("stu_name","");
            paint.setTextSize(20);
            if(!clickName.equals("")){
                canvas.drawText("By- "+clickName, 140, totalHeight-20, paint);
            }





//creating the path to storethe final combined canvas image
            File storagePath = new File(Environment.getExternalStorageDirectory(),"Aarohan_Images");
            storagePath.mkdirs();
            String finalName = Long.toString(System.currentTimeMillis());
            File myImage = new File(storagePath, finalName + ".jpg");
            String photoPath = "/Aarohan_Images" +"/" + finalName + ".jpg";
//writing the image into storage
            try {
                FileOutputStream fos = new FileOutputStream(myImage);
                newImage.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.close();
            } catch (IOException e) {
                Toast.makeText(FaceFilterActivity.this, "Pic not saved", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(FaceFilterActivity.this, "Pic saved in: " + photoPath, Toast.LENGTH_SHORT).show();
            newImage.recycle();
            cameraBitmap.recycle();

            Intent i = new Intent(FaceFilterActivity.this, PicPreview.class);
            i.putExtra("filepath", photoPath);
            startActivity(i);
        }
    };
    //flif camera image like mirror image
    public static Bitmap mirror(Bitmap srcBitmap) {
        Matrix matrix = new Matrix();
        matrix.preScale(-1.0f, 1.0f);
        return Bitmap.createBitmap(srcBitmap, 0, 0, srcBitmap.getWidth(), srcBitmap.getHeight(), matrix, true);
    }
    //checkinng for camera permission
    private void requestCameraPermission() {
        Log.w(TAG, "Camera permission is not granted. Requesting permission");
        final String[] permissions = new String[]{Manifest.permission.CAMERA};
        if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            ActivityCompat.requestPermissions(this, permissions, RC_HANDLE_CAMERA_PERM);
            return;
        }
        final Activity thisActivity = this;
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(thisActivity, permissions, RC_HANDLE_CAMERA_PERM);
            }
        };
        Snackbar.make(mGraphicOverlay, R.string.permission_camera_rationale,
                Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.ok, listener)
                .show();
    }
    //creating and starting camera
    private void createCameraSource() {
        Context context = getApplicationContext();
        //Detecting face
        FaceDetector detector = new FaceDetector.Builder(context)
                .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                .setMode(FaceDetector.ACCURATE_MODE)
                .build();
        detector.setProcessor(
                new MultiProcessor.Builder<>(new GraphicFaceTrackerFactory())
                        .build());
        if (!detector.isOperational()) {
            // Note: The first time that an app using face API is installed on a device, GMS will
            // download a native library to the device in order to do detection.  Usually this
            // completes before the app is run for the first time.  But if that download has not yet
            // completed, then the above call will not detect any faces.
            //
            // isOperational() can be used to check if the required native library is currently
            // available.  The detector will automatically become operational once the library
            // download completes on device.
            Log.w(TAG, "Face detector dependencies are not yet available.");
        }
        //for screen size of the phone to preventing straching of image
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        mCameraSource = new CameraSource.Builder(context, detector)
                .setRequestedPreviewSize(height, width)
                .setFacing(CameraSource.CAMERA_FACING_FRONT)
                .setRequestedFps(15.0f)
                .build();
    }

    /**
     * Restarts the camera.
     */
    @Override
    protected void onResume() {
        super.onResume();
        startCameraSource();
    }

    /**
     * Stops the camera.
     */
    @Override
    protected void onPause() {
        super.onPause();
        mPreview.stop();
    }

    /**
     * Releases the resources associated with the camera source, the associated detector, and the
     * rest of the processing pipeline.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCameraSource != null) {
            mCameraSource.release();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }

    /**
     * Callback for the result from requesting permissions. This method
     * is invoked for every call on {@link #requestPermissions(String[], int)}.
     * <p>
     * <strong>Note:</strong> It is possible that the permissions request interaction
     * with the user is interrupted. In this case you will receive empty permissions
     * and results arrays which should be treated as a cancellation.
     * </p>
     *
     * @param requestCode  The request code passed in {@link #requestPermissions(String[], int)}.
     * @param permissions  The requested permissions. Never null.
     * @param grantResults The grant results for the corresponding permissions
     *                     which is either {@link PackageManager#PERMISSION_GRANTED}
     *                     or {@link PackageManager#PERMISSION_DENIED}. Never null.
     * @see #requestPermissions(String[], int)
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode != RC_HANDLE_CAMERA_PERM) {
            Log.d(TAG, "Got unexpected permission result: " + requestCode);
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            return;
        }
        if (grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "Camera permission granted - initialize the camera source");
            // we have permission, so create the camerasource
            createCameraSource();
            return;
        }
        Log.e(TAG, "Permission not granted: results len = " + grantResults.length +
                " Result code = " + (grantResults.length > 0 ? grantResults[0] : "(empty)"));
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Face Tracker sample")
                .setMessage(R.string.no_camera_permission)
                .setPositiveButton(R.string.ok, listener)
                .show();
    }
    //==============================================================================================
    // Camera Source Preview
    //==============================================================================================

    /**
     * Starts or restarts the camera source, if it exists.  If the camera source doesn't exist yet
     * (e.g., because onResume was called before the camera source was created), this will be called
     * again when the camera source is created.
     */
    private void startCameraSource() {
        // check that the device has play services available.
        int code = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(
                getApplicationContext());
        if (code != ConnectionResult.SUCCESS) {
            Dialog dlg =
                    GoogleApiAvailability.getInstance().getErrorDialog(this, code, RC_HANDLE_GMS);
            dlg.show();
        }
        if (mCameraSource != null) {
            try {
                mPreview.start(mCameraSource, mGraphicOverlay);
            } catch (IOException e) {
                Log.e(TAG, "Unable to start camera source.", e);
                mCameraSource.release();
                mCameraSource = null;
            }
        }
    }
    //==============================================================================================
    // Graphic Face Tracker
    //==============================================================================================

    /**
     * Factory for creating a face tracker to be associated with a new face.  The multiprocessor
     * uses this factory to create face trackers as needed -- one for each individual.
     */
    private class GraphicFaceTrackerFactory implements MultiProcessor.Factory<Face> {
        @Override
        public Tracker<Face> create(Face face) {
            return new GraphicFaceTracker(mGraphicOverlay);
        }
    }

    /**
     * Face tracker for each detected individual. This maintains a face graphic within the app's
     * associated face overlay.
     */
    private class GraphicFaceTracker extends Tracker<Face> {
        private GraphicOverlay mOverlay;
        private FaceGraphic mFaceGraphic;
        GraphicFaceTracker(GraphicOverlay overlay) {
            mOverlay = overlay;
            mFaceGraphic = new FaceGraphic(overlay);
        }
        /**
         * Start tracking the detected face instance within the face overlay.
         */
        @Override
        public void onNewItem(int faceId, Face item) {
            mFaceGraphic.setId(faceId);
        }
        /**
         * Update the position/characteristics of the face within the overlay.
         */
        @Override
        public void onUpdate(FaceDetector.Detections<Face> detectionResults, Face face) {
            mOverlay.add(mFaceGraphic);
            mFaceGraphic.updateFace(face);
        }
        /**
         * Hide the graphic when the corresponding face was not detected.  This can happen for
         * intermediate frames temporarily (e.g., if the face was momentarily blocked from
         * view).
         */
        @Override
        public void onMissing(FaceDetector.Detections<Face> detectionResults) {
            mOverlay.remove(mFaceGraphic);
        }
        /**
         * Called when the face is assumed to be gone for good. Remove the graphic annotation from
         * the overlay.
         */
        @Override
        public void onDone() {
            mOverlay.remove(mFaceGraphic);

        }
    }


}