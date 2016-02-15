package com.bl.locodroid;

/**
 * Created by SRABOIS on 10/02/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.bl.locodroid.model.LocoModel;
import com.bl.locodroid.user.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserListActivity extends Activity {

    LocoModel model;

    ListView mListView;
    List<String> prenoms = Arrays.asList("Antoine", "Benoit", "Cyril", "David", "Eloise", "Florent",
            "Gerard", "Hugo", "Ingrid", "Jonathan", "Kevin", "Logan",
            "Mathieu", "Noemie");

    ArrayList<User> liste = model.getNeighBours();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);

        mListView = (ListView) findViewById(R.id.listView);

        //android.R.layout.simple_list_item_1 est une vue disponible de base dans le SDK android,
        //Contenant une TextView avec comme identifiant "@android:id/text1"

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(UserListActivity.this, R.layout.list_view_row, R.id.listText, prenoms);

         ArrayAdapter<User> adapter = new ArrayAdapter<User>(UserListActivity.this, R.layout.list_view_row, R.id.listText, liste);
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
            TextView listText = (TextView) view.findViewById(R.id.listText);
            String text = listText.getText().toString();
            Intent intent = new Intent(UserListActivity.this, ProfileActivity.class);
            intent.putExtra("selected-item", text);
            startActivity(intent);

        }

    }



}
