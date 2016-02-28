package com.bl.locodroid;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bl.locodroid.model.LocoModel;
import com.bl.locodroid.user.domain.User;
import com.bl.locodroid.view.ViewTools;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * Created by SRABOIS on 10/02/2016.
 */
public class ProfileActivity extends MenuActivity implements OnMapReadyCallback {
    TextView textView;
    LocoModel model;
    private GoogleMap mMap;
    User neighbourgh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        model = LocoModel.getInstance();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.profile_map);
        mapFragment.getMapAsync(this);

        // get the intent from which this activity is called.
        Intent intent = getIntent();

        // fetch value from key-value pair and make it visible on TextView.
        //String item = intent.getStringExtra("item_name");
        //textView.setText("you selected "+item);

        neighbourgh = (User) intent.getSerializableExtra("user");
        if (neighbourgh != null){
            ViewTools.setTextViewWithEmpty((TextView) findViewById(R.id.pro_FirstName), neighbourgh.getFirstName());
            ViewTools.setTextViewWithEmpty((TextView) findViewById(R.id.pro_LastName), neighbourgh.getLastName());
            ViewTools.setTextViewWithEmpty((TextView) findViewById(R.id.pro_Pseudo), "(" + neighbourgh.getPseudo() + ")");
            ViewTools.setTextViewWithEmpty((TextView) findViewById(R.id.pro_Address1), neighbourgh.getAdress1());
            ViewTools.setTextViewWithEmpty((TextView) findViewById(R.id.pro_Address2), neighbourgh.getAdress2());
            ViewTools.setTextViewWithEmpty((TextView) findViewById(R.id.pro_CodePostal), neighbourgh.getCodePostal());
            ViewTools.setTextViewWithEmpty((TextView) findViewById(R.id.pro_City), neighbourgh.getCity());
            ViewTools.setTextViewWithEmpty((TextView) findViewById(R.id.pro_Email), neighbourgh.getEmail());
            ViewTools.setTextViewWithEmpty((TextView) findViewById(R.id.pro_Telephone), neighbourgh.getTelephone());

            ImageView mMail = (ImageView) findViewById(R.id.imageViewMail);
            mMail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "mail", Toast.LENGTH_SHORT).show();

                }
            });

            ImageView mPhone = (ImageView) findViewById(R.id.imageViewPhone);
            mPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "phone", Toast.LENGTH_SHORT).show();

                }
            });

            ImageView mSms = (ImageView) findViewById(R.id.imageViewSms);
            mSms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "sms", Toast.LENGTH_SHORT).show();

                }
            });

            if (neighbourgh.getEmail().isEmpty()) {
                ((LinearLayout)findViewById(R.id.pro_mailgroup)).setVisibility(View.GONE);
            }
            if (neighbourgh.getTelephone().isEmpty()) {
                ((LinearLayout)findViewById(R.id.pro_phonegroup)).setVisibility(View.GONE);
            }

           /* ((TextView) findViewById(R.id.pro_FirstName)).setText(neighbourgh.getFirstName());
            ((TextView) findViewById(R.id.pro_LastName)).setText(neighbourgh.getLastName());
            ((TextView) findViewById(R.id.pro_Pseudo)).setText("("+neighbourgh.getPseudo()+")");
            ((TextView) findViewById(R.id.pro_Address1)).setText(neighbourgh.getAdress1());
            if (neighbourgh.getAdress2().isEmpty()) {
                ((TextView) findViewById(R.id.pro_Address2)).setHeight(0);
            } else {
                ((TextView) findViewById(R.id.pro_Address2)).setText(neighbourgh.getAdress2());
            }

            ((TextView) findViewById(R.id.pro_CodePostal)).setText(neighbourgh.getCodePostal());
            ((TextView) findViewById(R.id.pro_City)).setText(neighbourgh.getCity());
            ((TextView) findViewById(R.id.pro_Email)).setText(neighbourgh.getEmail());
            ((TextView) findViewById(R.id.pro_Telephone)).setText(neighbourgh.getTelephone());
*/

        }



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        menu.findItem(R.id.menu_connect).setVisible(false);
        menu.findItem(R.id.menu_profile).setVisible(false);
        menu.findItem(R.id.menu_register).setVisible(false);

        return true;

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng Labege = new LatLng(43.543254, 1.512209);
        mMap.addMarker(new MarkerOptions().position(Labege).title("NOTRE TRES CHER BERGER-LEVRAULT").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        LatLng userPosition = new LatLng(Double.parseDouble(neighbourgh.getAddress().getLocation().getLat()), Double.parseDouble(neighbourgh.getAddress().getLocation().getLng()));
        mMap.addMarker(new MarkerOptions().position(userPosition).title(neighbourgh.getFirstName() + " " + neighbourgh.getLastName()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        LatLng myPosition = new LatLng(Double.parseDouble(model.getUserConnected().getAddress().getLocation().getLat()), Double.parseDouble(model.getUserConnected().getAddress().getLocation().getLng()));
        mMap.addMarker(new MarkerOptions().position(myPosition).title(model.getUserConnected().getFirstName() + " " + model.getUserConnected().getLastName()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

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
