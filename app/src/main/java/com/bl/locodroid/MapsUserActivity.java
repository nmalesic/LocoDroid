package com.bl.locodroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bl.locodroid.localisation.domain.Location;
import com.bl.locodroid.localisation.domain.LocoAddress;
import com.bl.locodroid.model.LocoModel;
import com.bl.locodroid.user.UserAdapter;
import com.bl.locodroid.user.UserListActivity;
import com.bl.locodroid.user.domain.User;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class MapsUserActivity extends MenuActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    protected static ProgressDialog dialog;
    LocoModel model;

    ArrayList<User> neighBours = new ArrayList<>();

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

           model = LocoModel.getInstance(this);

        class GetMapsUserTask extends AsyncTask<Void, Integer, ArrayList<User>> {

            /** * Le AtomicBoolean pour lancer et stopper la Thread */
            private AtomicBoolean isThreadRunnning = new AtomicBoolean();
            /** * Le AtomicBoolean pour mettre en pause et relancer la Thread */
            private AtomicBoolean isThreadPausing = new AtomicBoolean();

            @Override
            protected void onPreExecute() {
                dialog = ProgressDialog.show(MapsUserActivity.this, "", getString(R.string.background_doing));
            }

            @Override
            protected ArrayList<User> doInBackground(Void... params) {
                ArrayList<User> result = null;
                //if (isThreadRunnning.get()) {
                result = model.getNeighbours();
                //}
                return result;
            }

            @Override
            protected void onPostExecute(ArrayList<User> result) {
                neighBours = result;

                dialog.dismiss();
                dialog = null;
                LatLng a;
                for (User u : neighBours) {
                    loc = u.getAddress().getLocation();
                    a = new LatLng(Double.parseDouble(loc.getLat()), Double.parseDouble(loc.getLng()));
                    mMap.addMarker(new MarkerOptions().position(a).title(u.getLastName() + " " + u.getFirstName()));
                }

                
            }
        }

        new GetMapsUserTask().execute();


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
        

        LatLng Labege = new LatLng(43.543254, 1.512209);
        mMap.addMarker(new MarkerOptions().position(Labege).title("BERGER-LEVRAULT").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        Double lat = Double.parseDouble(model.getUserConnected().getAddress().getLocation().getLat());
        Double lng = Double.parseDouble(model.getUserConnected().getAddress().getLocation().getLng());
        LatLng centre = new LatLng(lat, lng);

        Circle cercle = mMap.addCircle(new CircleOptions()
                .center(centre)
                        .radius(model.getRadius()*1000)
                        .strokeWidth(3)
                        .strokeColor(0xFF333333)
                        .fillColor(0x503333CC));

        // Construct a CameraPosition focusing on Mountain View and animate the camera to that position.
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(centre)  // Sets the center of the map to Mountain View
                .zoom(12)
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
    }

