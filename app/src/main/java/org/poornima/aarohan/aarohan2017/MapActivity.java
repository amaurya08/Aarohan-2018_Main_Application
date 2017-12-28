package org.poornima.aarohan.aarohan2017;

import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    /*private LatLngBounds bound_camera = new LatLngBounds(
            new LatLng(26.665575, 75.753622), new LatLng(26.865575, 75.953622));*/
    private FloatingActionButton map_pce,map_piet,map_pgi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        init();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        pietmapmarker( mMap);
        map_pce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pcemapmarkers(mMap);
            }
        });

        map_piet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               pietmapmarker(mMap);
            }
        });
        map_pgi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pgimapmarker(mMap);
            }
        });
    }

    private void pgimapmarker(GoogleMap mMap) {
        mMap.clear();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom((new LatLngBounds(
                new LatLng(26.785340610802916, 75.85343688726425), new LatLng(26.785340610802916, 75.85343688726425))).getCenter(), (float) 18.0));

        Marker m7=mMap.addMarker(new MarkerOptions().position(new LatLng(26.78612118612679,75.8544360101223))
                .anchor(0.5f,0.5f).title("Poornima Group of Institute")
                .snippet("AAROHAN-2K18").icon(BitmapDescriptorFactory.fromResource(R.drawable.common_full_open_on_phone)));
        Marker m8=mMap.addMarker(new MarkerOptions().position(new LatLng(26.785720124322655,75.85376411676407))
                .anchor(0.5f,0.5f).title("Mess PGI")
                .snippet("AAROHAN-2K18").icon(BitmapDescriptorFactory.fromResource(R.mipmap.mess_marker)));
        Marker m9=mMap.addMarker(new MarkerOptions().position(new LatLng(26.785709349508345,75.85385799407959))
                .anchor(0.5f,0.5f).title("Cafeteria PGI")
                .snippet("AAROHAN-2K18").icon(BitmapDescriptorFactory.fromResource(R.mipmap.cafeteria_marker)));
    }

    private void pietmapmarker(GoogleMap mMap) {
        mMap.clear();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom((new LatLngBounds(
                new LatLng(26.76756790077633, 75.85027053952217), new LatLng(26.76756790077633, 75.85027053952217))).getCenter(), (float) 18.4));

        Marker m6=mMap.addMarker(new MarkerOptions().position(new LatLng(26.76853539015658,75.85068225860596))
                .anchor(0.5f,0.5f).title("Poornima institute of engineering and technology")
                .snippet("AAROHAN-2K18").icon(BitmapDescriptorFactory.fromResource(R.drawable.common_full_open_on_phone)));
        Marker m4=mMap.addMarker(new MarkerOptions().position(new LatLng(26.767290105273066,75.85026316344738))
                .anchor(0.5f,0.5f).title("Mess PIET")
                .snippet("AAROHAN-2K18").icon(BitmapDescriptorFactory.fromResource(R.mipmap.mess_marker)));
        Marker m5=mMap.addMarker(new MarkerOptions().position(new LatLng(26.767481688451472,75.85030071437359))
                .anchor(0.5f,0.5f).title("Cafeteria PIET")
                .snippet("AAROHAN-2K18").icon(BitmapDescriptorFactory.fromResource(R.mipmap.cafeteria_marker)));
    }
    private void pcemapmarkers(GoogleMap mMap) {
        mMap.clear();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom((new LatLngBounds(
                new LatLng(26.76535749189057, 75.8533738553524), new LatLng(26.76535749189057, 75.8533738553524))).getCenter(), (float) 18.4));

        Marker m1=mMap.addMarker(new MarkerOptions().position(new LatLng(26.76612084218324,75.85378054529428))
                .anchor(0.5f,0.5f).title("Poornima college of Engineering")
                .snippet("AAROHAN-2K18").icon(BitmapDescriptorFactory.fromResource(R.drawable.common_full_open_on_phone)));
        Marker m2=mMap.addMarker(new MarkerOptions().position(new LatLng(26.76548681277164,75.85407927632332))
                .anchor(0.5f,0.5f).title("Mess PCE")
                .snippet("AAROHAN-2K18").icon(BitmapDescriptorFactory.fromResource(R.mipmap.mess_marker)));
        Marker m3=mMap.addMarker(new MarkerOptions().position(new LatLng(26.765759823037087,75.85410341620445))
                .anchor(0.5f,0.5f).title("Cafeteria PCE")
                .snippet("AAROHAN-2K18").icon(BitmapDescriptorFactory.fromResource(R.mipmap.cafeteria_marker)));
    }

    private void init() {
        map_pce=(FloatingActionButton)findViewById(R.id.fabpce);
        map_piet=(FloatingActionButton)findViewById(R.id.fabpiet);
        map_pgi=(FloatingActionButton)findViewById(R.id.fabpgi);
    }


}
