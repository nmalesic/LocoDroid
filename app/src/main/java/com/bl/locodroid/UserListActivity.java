package com.bl.locodroid;

/**
 * Created by SRABOIS on 10/02/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

public class UserListActivity extends ActionBarActivity {

    ListView mListView;
    List<String> prenoms = Arrays.asList("Antoine", "Benoit", "Cyril", "David", "Eloise", "Florent",
            "Gerard", "Hugo", "Ingrid", "Jonathan", "Kevin", "Logan",
            "Mathieu", "Noemie", "Olivia", "Philippe", "Quentin", "Romain",
            "Sophie", "Tristan", "Ulric", "Vincent", "Willy", "Xavier",
            "Yann", "Zoé");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);

        mListView = (ListView) findViewById(R.id.listView);

        //android.R.layout.simple_list_item_1 est une vue disponible de base dans le SDK android,
        //Contenant une TextView avec comme identifiant "@android:id/text1"

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(UserListActivity.this, android.R.layout.simple_list_item_1, prenoms);
        mListView.setAdapter(adapter);

        prenoms.set(0, "GREGOIRE");

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



}
