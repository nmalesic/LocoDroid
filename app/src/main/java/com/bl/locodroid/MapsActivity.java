package com.bl.locodroid;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(50.7043768, 2.9174142);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        LatLng Albi = new LatLng(43.92, 2.14);
        mMap.addMarker(new MarkerOptions().position(Albi).title("Marker in Albi"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Albi, 5));
        LatLng Toulouse = new LatLng(43.60,1.44);
        mMap.addMarker(new MarkerOptions().position(Toulouse).title("Marker in Toulouse"));

        Circle cercle = mMap.addCircle(new CircleOptions()
                .center(Albi)
                .radius(50000)
                .strokeWidth(3)
                .strokeColor(0xFF333333)
                .fillColor(0x503333CC));

        Circle cercle2 = mMap.addCircle(new CircleOptions()
                .center(Toulouse)
                .radius(50000)
                .strokeWidth(3)
                .strokeColor(0xFF33AA33)
                .fillColor(0x50FF33CC));

        mMap.setTrafficEnabled(true);





    }
}
