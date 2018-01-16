package org.poornima.aarohan.aarohan2017;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import io.fabric.sdk.android.Fabric;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    /*private LatLngBounds bound_camera = new LatLngBounds(
            new LatLng(26.665575, 75.753622), new LatLng(26.865575, 75.953622));*/
    private FloatingActionButton map_pce,map_piet,map_pgi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the menu_btn_map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        init();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

       mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
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
        Marker m13=mMap.addMarker(new MarkerOptions().position(new LatLng(26.785627939767345,75.85351198911667))
                .anchor(0.5f,0.5f).title("Accomodation")
                .snippet("AAROHAN-2K18").icon(BitmapDescriptorFactory.fromResource(R.mipmap.accomodation)));
        Marker m19=mMap.addMarker(new MarkerOptions().position(new LatLng(26.78634386460614,75.85416778922081))
                .anchor(0.5f,0.5f).title("HelpDesk(PGI)")
                .snippet("AAROHAN-2K18").icon(BitmapDescriptorFactory.fromResource(R.mipmap.helpdesk)));

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
        Marker m10=mMap.addMarker(new MarkerOptions().position(new LatLng(26.767979803203044,75.85102155804634))
                .anchor(0.5f,0.5f).title("Arbuda Convention Center")
                .snippet("AAROHAN-2K18").icon(BitmapDescriptorFactory.fromResource(R.mipmap.arbuda)));
        Marker m11=mMap.addMarker(new MarkerOptions().position(new LatLng(26.768549758748648,75.85058838129044))
                .anchor(0.5f,0.5f).title("ATM")
                .snippet("AAROHAN-2K18").icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker)));
        Marker m17=mMap.addMarker(new MarkerOptions().position(new LatLng(26.76846474455253,75.85053943097591))
                .anchor(0.5f,0.5f).title("Help Desk(PIET)")
                .snippet("AAROHAN-2K18").icon(BitmapDescriptorFactory.fromResource(R.mipmap.helpdesk)));
        Marker m20=mMap.addMarker(new MarkerOptions().position(new LatLng(26.76840128320972,75.85087940096855))
                .anchor(0.5f,0.5f).title("PARKING)")
                .snippet("AAROHAN-2K18").icon(BitmapDescriptorFactory.fromResource(R.mipmap.parking)));
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
        Marker m11=mMap.addMarker(new MarkerOptions().position(new LatLng(26.766188495726425,75.85360586643219))
                .anchor(0.5f,0.5f).title("IDBI ATM")
                .snippet("AAROHAN-2K18").icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker)));
        Marker m12=mMap.addMarker(new MarkerOptions().position(new LatLng(26.765254514046642,75.85266709327698))
                .anchor(0.5f,0.5f).title("Accomodation")
                .snippet("AAROHAN-2K18").icon(BitmapDescriptorFactory.fromResource(R.mipmap.accomodation)));
          Marker m16=mMap.addMarker(new MarkerOptions().position(new LatLng(26.766023253372975,75.85363671183586))
                .anchor(0.5f,0.5f).title("Help Desk(PCE)")
                .snippet("AAROHAN-2K18").icon(BitmapDescriptorFactory.fromResource(R.mipmap.helpdesk)));
        Marker m22=mMap.addMarker(new MarkerOptions().position(new LatLng(26.76599691036687,75.85394516587257))
                .anchor(0.5f,0.5f).title("Parking")
                .snippet("AAROHAN-2K18").icon(BitmapDescriptorFactory.fromResource(R.mipmap.parking)));
        Marker m23=mMap.addMarker(new MarkerOptions().position(new LatLng(26.766892569146968,75.85278242826462))
                .anchor(0.5f,0.5f).title("AXIS ATM")
                .snippet("AAROHAN-2K18").icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker)));
        Marker m24=mMap.addMarker(new MarkerOptions().position(new LatLng(26.76711289054533,75.85298627614975))
                .anchor(0.5f,0.5f).title("SBI ATM")
                .snippet("AAROHAN-2K18").icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker)));


    }

    private void init() {
        map_pce = findViewById(R.id.fabpce);
        map_piet = findViewById(R.id.fabpiet);
        map_pgi = findViewById(R.id.fabpgi);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
