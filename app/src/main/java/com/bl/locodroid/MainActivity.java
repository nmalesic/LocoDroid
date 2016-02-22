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

public class MainActivity extends AppCompatActivity {

    LocoModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //Log.i("EPITEZ", "Created activity 1");

       Button but_next = (Button) findViewById(R.id.Button01);

       // LocalStorageDB localdb = new LocalStorageDB(this.getBaseContext(),"LocoDroid.db",null,1);

    

        but_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                //get context() pour récupérer la vue, puis on renseigne la classe vers laquelle on veut switcher
                Intent myIntent = new Intent(view.getContext(), UserListActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
       Button but_map = (Button) findViewById(R.id.ButtonMap);


        but_map.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                //get context() pour récupérer la vue, puis on renseigne la classe vers laquelle on veut switcher
                Intent myIntent = new Intent(view.getContext(), MapsActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
        model = model.getInstance();

        //test geocoding
        //LocalisationService localisationService = new LocalisationService();
        //LocoAddress locoAddress = localisationService.getLocoAddress("1 Rue du bon coin, FRELINGHIEN");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;

    }



    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_about:
                Intent myIntent_about = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(myIntent_about);
                return true;
            case R.id.menu_quit:
                finish();
            case R.id.menu_register:
                Intent myIntent_register = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(myIntent_register);
                return true;

            case R.id.menu_connect:
                //Intent myIntent_connect = new Intent(MainActivity.this, LoginActivity.class);
                //startActivity(myIntent_connect);
                Toast.makeText(this,"menu_connect selected",Toast.LENGTH_LONG).show();
                return true;
            default:return true;
        }
    }

    }
