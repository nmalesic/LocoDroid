package com.bl.locodroid;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends MenuActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Button but_next = (Button)findViewById(R.id.Button01);
        but_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

        //get context() pour récupérer la vue, puis on renseigne la classe vers laquelle on veut switcher
        Intent myIntent = new Intent(view.getContext(), LoginActivity.class);
        startActivityForResult(myIntent, 0);
            }
        });
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
//        LatLng sydney = new LatLng(50.7043768, 2.9174142);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


//        LatLng Albi = new LatLng(43.92, 2.14);
//        mMap.addMarker(new MarkerOptions().position(Albi).title("Marker in Albi"));
        LatLng Toulouse = new LatLng(43.60,1.44);
        mMap.addMarker(new MarkerOptions().position(Toulouse).title("Oh Toulouse !"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Toulouse, 8));


//        Circle cercle = mMap.addCircle(new CircleOptions()
//                .center(Albi)
//                .radius(50000)
//                .strokeWidth(3)
//                .strokeColor(0xFF333333)
//                .fillColor(0x503333CC));


        //mMap.setTrafficEnabled(true);

        // Construct a CameraPosition focusing on Mountain View and animate the camera to that position.
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(Toulouse)  // Sets the center of the map to Mountain View
                .zoom(12)                   // Sets the zoom
                //.bearing(90)                // Sets the orientation of the camera to east
                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


    }
}
