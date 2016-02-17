package com.bl.locodroid.user;

/**
 * Created by SRABOIS on 10/02/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.bl.locodroid.ProfileActivity;
import com.bl.locodroid.R;
import com.bl.locodroid.localisation.Location;
import com.bl.locodroid.localisation.LocoAddress;
import com.bl.locodroid.model.LocoModel;

import java.util.ArrayList;

public class UserListActivity extends Activity {

    LocoModel model;

    UserAdapter userAdapter;

    ListView mListView;

    ArrayList<User> neighBours = new ArrayList<User>();

    String adresse = "Place Clemence Isaure 31320 Castanet-Tolosan";
    User a;
    Location loc;
    LocoAddress locoAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);

        a = new User("RABOIS", "Sylvain", "pion de 6", "a@a.a", "a", "a", null, "0102030405", "M", "false");
        loc = new Location("43.5563336", "1.528394");
        locoAddress = new LocoAddress("10 Avenue de Gameville", "", "31650", "Saint-Orens-de-Gameville", loc);
        neighBours.add(a);

        a = new User("CHAMAYOU", "Olivier", "objet composition détaché", "b@b.b", "b", "b", null, "0602030405", "M", "false");
        loc = new Location("43.5175497", "1.5057399");
        locoAddress = new LocoAddress("10 Rue du Pic du Midi", "", "31240", "L'Union", loc);
        neighBours.add(a);

        a = new User("COEURET", "Fabrice", "Singleton", "c@c.c", "c", "c", null, "0702030405", "M", "false");
        loc = new Location("43.5175497", "1.5057399");
        locoAddress = new LocoAddress("Place Clemence Isaure", "", "31320", "Castanet-Tolosan", loc);
        neighBours.add(a);

        mListView = (ListView) findViewById(R.id.list_user);

        //ArrayAdapter<User> adapter = new ArrayAdapter<User>(UserListActivity.this, R.layout.list_view_row, R.id.lastName, neighBours);
        UserAdapter adapter = new UserAdapter(UserListActivity.this, R.layout.list_view_row, neighBours);
        mListView.setAdapter(adapter);



        mListView.setOnItemClickListener(new ListClickHandler());

        Button but_prev = (Button) findViewById(R.id.btn_back);
        but_prev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                //intention sans parametre pour indiquer le retour en arriere
                Intent myIntent = new Intent();
                setResult(RESULT_OK, myIntent);
                finish();
            }
        });
    }


    public class ListClickHandler implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {
            // TODO Auto-generated method stub
            TextView listText = (TextView) view.findViewById(R.id.lastName);
            String text = listText.getText().toString();
            Intent intent = new Intent(UserListActivity.this, ProfileActivity.class);
            intent.putExtra("selected-item", text);
            startActivity(intent);

        }

    }



}
