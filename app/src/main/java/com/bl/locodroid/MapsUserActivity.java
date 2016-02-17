package com.bl.locodroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.bl.locodroid.localisation.Location;
import com.bl.locodroid.localisation.LocoAddress;
import com.bl.locodroid.model.LocoModel;
import com.bl.locodroid.user.User;
import com.bl.locodroid.user.UserListActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsUserActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    LocoModel model ;

    ArrayList<User> neighBours = new ArrayList<User>();

    User a;
    Location loc;
    LocoAddress locoAddress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_users);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Button but_prev = (Button) findViewById(R.id.btn_back);
        but_prev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                //intention sans parametre pour indiquer le retour en arriere
                Intent myIntent = new Intent();
                setResult(RESULT_OK, myIntent);
                finish();
            }
        });

        a = new User("RABOIS", "Sylvain", "pion de 6", "a@a.a", "a", "a", null, "0102030405", "M", "false");
        loc = new Location("43.5563336", "1.528394");
        locoAddress = new LocoAddress("10 Avenue de Gameville", "", "31650", "Saint-Orens-de-Gameville", loc);
        locoAddress.setLocation(loc);
        a.setAddress(locoAddress);
        neighBours.add(a);
        model = LocoModel.getInstance();
        model.setUserConnected(a);
        neighBours = model.getNeighbours();
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
        LatLng a;

        for (User u : neighBours)
            {
                loc = u.getAddress().getLocation();
                a = new LatLng(Double.parseDouble(loc.getLat()), Double.parseDouble(loc.getLng()));
                mMap.addMarker(new MarkerOptions().position(a).title(u.getLastName()+ " " + u.getFirstName()));
            }

        LatLng Labege = new LatLng(43.5333,1.5333);


        /* LatLng Albi = new LatLng(43.92, 2.14);
        mMap.addMarker(new MarkerOptions().position(Albi).title("Marker in Albi"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Albi, 5));
        LatLng Toulouse = new LatLng(43.60,1.44);
        mMap.addMarker(new MarkerOptions().position(Toulouse).title("Marker in Toulouse")); */



        // Construct a CameraPosition focusing on Mountain View and animate the camera to that position.
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(Labege)  // Sets the center of the map to Mountain View
                .zoom(12)                   // Sets the zoom
                //.bearing(90)                // Sets the orientation of the camera to east
                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));





    }
}
