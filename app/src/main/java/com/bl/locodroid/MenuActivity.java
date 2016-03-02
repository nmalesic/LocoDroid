package com.bl.locodroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bl.locodroid.user.UserListActivity;

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
                    Intent myIntent_about = new Intent(MenuActivity.this, AboutActivity.class);
                    startActivity(myIntent_about);
                    return true;
                case R.id.menu_quit:
                    finish();
                    return true;
                case R.id.menu_register:
                    Intent myIntent_register = new Intent(MenuActivity.this, RegisterActivity.class);
                    startActivity(myIntent_register);
                    return true;
                case R.id.menu_connect:
                    Intent myIntent_connect = new Intent(MenuActivity.this, LoginActivity.class);
                    startActivity(myIntent_connect);
                    //Toast.makeText(this, "menu_connect selected", Toast.LENGTH_LONG).show();
                    return true;
                case R.id.menu_disconnect:
                    ///action de deconnexion, pas encore implementé
                    Toast.makeText(this, R.string.logout, Toast.LENGTH_LONG).show();
                    return true;
                case R.id.menu_home:
                    Intent myIntent_home = new Intent(MenuActivity.this, MainActivity.class);
                    startActivity(myIntent_home);
                    return true;

                case R.id.menu_search:
                    Intent myIntent_list = new Intent(MenuActivity.this, UserListActivity.class);
                    startActivity(myIntent_list);
                    return true;

                case R.id.menu_profile:
                    Intent myIntent_profile = new Intent(MenuActivity.this, UserProfileActivity.class);
                    startActivity(myIntent_profile);
                    return true;
                default:return true;
            }
        }

    }
