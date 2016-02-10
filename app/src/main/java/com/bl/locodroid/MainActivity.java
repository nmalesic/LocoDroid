package com.bl.locodroid;

/**
 * Created by SRABOIS on 05/02/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bl.locodroid.model.LocoModel;

public class MainActivity extends AppCompatActivity {

    LocoModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Log.i("EPITEZ", "Created activity 1");

        Button but_next = (Button)findViewById(R.id.Button01);
        but_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                //get context() pour récupérer la vue, puis on renseigne la classe vers laquelle on veut switcher
                Intent myIntent = new Intent(view.getContext(), MapsActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
        model = model.getInstance();

    }
}
