package com.bl.locodroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bl.locodroid.model.LocoModel;
import com.bl.locodroid.user.UserListActivity;

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
import com.bl.locodroid.user.domain.User;

/**
 * Created by SRABOIS on 22/02/2016.
 */
public class MenuActivity  extends AppCompatActivity {


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.main_menu, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            switch (item.getItemId()){
                case R.id.menu_about:
                    Intent myIntent_about = new Intent(MenuActivity.this, MapsActivity.class);
                    startActivity(myIntent_about);
                    return true;
                case R.id.menu_quit:
                    finish();
                case R.id.menu_register:
                    Intent myIntent_register = new Intent(MenuActivity.this, RegisterActivity.class);
                    startActivity(myIntent_register);
                    return true;
                case R.id.menu_connect:
                    //Intent myIntent_connect = new Intent(MainActivity.this, LoginActivity.class);
                    //startActivity(myIntent_connect);
                    Toast.makeText(this, "menu_connect selected", Toast.LENGTH_LONG).show();
                    return true;

                case R.id.menu_list:
                    Intent myIntent_list = new Intent(MenuActivity.this, UserListActivity.class);
                    startActivity(myIntent_list);
                    return true;
                default:return true;
            }
        }

    }
