package com.bl.locodroid;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.bl.locodroid.user.domain.User;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;


/**
 * Created by SRABOIS on 10/02/2016.
 */
public class ProfileActivity extends MenuActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        textView = (TextView) findViewById(R.id.textView);


        // get the intent from which this activity is called.
        Intent intent = getIntent();

        // fetch value from key-value pair and make it visible on TextView.
        String item = intent.getStringExtra("item_name");
        textView.setText("you selected "+item);
        //User u = (User) intent.getSerializableExtra("user");


        //textView.setText("you selected "+u.getFirstName() + " " + u.getLastName());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        menu.findItem(R.id.menu_connect).setVisible(false);
        menu.findItem(R.id.menu_profile).setVisible(false);
        menu.findItem(R.id.menu_register).setVisible(false);

        return true;

    }

}
