package com.bl.locodroid;

/**
 * Created by SRABOIS on 05/02/2016.
 */

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bl.locodroid.localstorage.LocalStorageDB;
import com.bl.locodroid.model.LocoModel;
import com.bl.locodroid.user.UserListActivity;

import java.util.Locale;

public class MainActivity extends MenuActivity {

    LocoModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //Log.i("EPITEZ", "Created activity 1");
        model = model.getInstance();


        if (model.getUserConnected()==null){
            Button but_connect = (Button) findViewById(R.id.ButtonConnect);
            but_connect.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent myIntent = new Intent(view.getContext(),LoginActivity.class);
                    startActivityForResult(myIntent, 0);
                }
            });

            Button but_register = (Button) findViewById(R.id.ButtonRegister);
            but_register.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent myIntent = new Intent(view.getContext(), RegisterActivity.class);
                    startActivityForResult(myIntent, 0);
                }
            });
        }

       Button but_search = (Button) findViewById(R.id.ButtonMap);
       but_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //get context() pour récupérer la vue, puis on renseigne la classe vers laquelle on veut switcher
                Intent myIntent = new Intent(view.getContext(), MapsActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });



        //test geocoding
        //LocalisationService localisationService = new LocalisationService();
        //LocoAddress locoAddress = localisationService.getLocoAddress("1 Rue du bon coin, FRELINGHIEN");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        menu.findItem(R.id.menu_connect).setVisible(false);
        menu.findItem(R.id.menu_disconnect).setVisible(false);
        menu.findItem(R.id.menu_profile).setVisible(false);
        menu.findItem(R.id.menu_register).setVisible(false);
        menu.findItem(R.id.menu_search).setVisible(false);
        menu.findItem(R.id.menu_about).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        return true;

    }




}
