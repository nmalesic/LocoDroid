package com.bl.locodroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bl.locodroid.model.LocoModel;

/**
 * Created by SRABOIS on 24/02/2016.
 */
public class AboutActivity extends AppCompatActivity {

    LocoModel model = LocoModel.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        if (model.getUserConnected()==null){
            Toast.makeText(this,R.string.logout ,Toast.LENGTH_LONG).show();
        } else {
            String login = getString(R.string.action_connected) + model.getUserConnected().getFirstName() + " " +
                    model.getUserConnected().getLastName() + " ( " + model.getUserConnected().getPseudo() + " ) " ;

            Toast.makeText(this, login, Toast.LENGTH_LONG).show();

        }


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

