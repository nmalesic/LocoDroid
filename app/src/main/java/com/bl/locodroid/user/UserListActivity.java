package com.bl.locodroid.user;

/**
 * Created by SRABOIS on 10/02/2016.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bl.locodroid.MapsActivity;
import com.bl.locodroid.MapsUserActivity;
import com.bl.locodroid.MenuActivity;
import com.bl.locodroid.ProfileActivity;
import com.bl.locodroid.R;
import com.bl.locodroid.localisation.domain.Location;
import com.bl.locodroid.localisation.domain.LocoAddress;
import com.bl.locodroid.model.LocoModel;
import com.bl.locodroid.user.domain.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class UserListActivity extends MenuActivity implements SeekBar.OnSeekBarChangeListener{

    protected static ProgressDialog dialog;
    LocoModel model;

    UserAdapter userAdapter;

    ListView mListView;

    ArrayList<User> neighBours = new ArrayList<>();

    //User a;
    //Location loc;
    //LocoAddress locoAddress;

    SeekBar seekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);

//        a = new User("RABOIS", "Sylvain", "pion de 6", "a@a.a", "a", "a", null, "0102030405", "M", "false");
//        loc = new Location("43.5563336", "1.528394");
//        locoAddress = new LocoAddress("10 Avenue de Gameville", "", "31650", "Saint-Orens-de-Gameville", loc);
//        locoAddress.setLocation(loc);
//        a.setAddress(locoAddress);
////        neighBours.add(a);
        model = LocoModel.getInstance(this);
//        model.context = this;
//        model.setUserConnected(a);



        mListView = (ListView) findViewById(R.id.list_user);

        new GetListUserTask().execute();
        //neighBours = model.getNeighbours();



        //ArrayAdapter<User> adapter = new ArrayAdapter<User>(UserListActivity.this, R.layout.list_view_row, R.id.lastName, neighBours);
        userAdapter = new UserAdapter(UserListActivity.this, R.layout.list_view_row, neighBours);
        mListView.setAdapter(userAdapter);

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

        Button but_map = (Button)findViewById(R.id.ButtonMap);

        but_map.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                //get context() pour récupérer la vue, puis on renseigne la classe vers laquelle on veut switcher
                Intent myIntent = new Intent(view.getContext(), MapsUserActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        seekBar=(SeekBar)findViewById(R.id.seekBar1);
        seekBar.setOnSeekBarChangeListener(this);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        //Toast.makeText(getApplicationContext(),"seekbar progress: "+progress, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //Toast.makeText(getApplicationContext(),"seekbar start: ", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
       // Toast.makeText(getApplicationContext(),"seekbar stop: ", Toast.LENGTH_SHORT).show();
                        model.setRadius(seekBar.getProgress());
                         new GetListUserTask().execute();


    }


    public class ListClickHandler implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {
            // TODO Auto-generated method stub
            TextView s_text = (TextView) view.findViewById(R.id.lastName);
            String text = s_text.getText().toString();

            Intent intent = new Intent(UserListActivity.this, ProfileActivity.class);
            intent.putExtra("item_name", text);
            User u = neighBours.get(position);
            intent.putExtra("user", u);

            startActivity(intent);

        }

    }

    class GetListUserTask extends AsyncTask<Void, Integer, ArrayList<User>> {

        /** * Le AtomicBoolean pour lancer et stopper la Thread */
        private AtomicBoolean isThreadRunnning = new AtomicBoolean();
        /** * Le AtomicBoolean pour mettre en pause et relancer la Thread */
        private AtomicBoolean isThreadPausing = new AtomicBoolean();

        @Override
        protected void onPreExecute(){
            dialog = ProgressDialog.show(UserListActivity.this, "", getString(R.string.background_doing));
        }

        @Override
        protected ArrayList<User> doInBackground(Void... params) {
            ArrayList<User> result = null;
            //if (isThreadRunnning.get()) {
            result = model.getNeighbours();
            //}
            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<User> result) {

            neighBours = result;
            UserAdapter adapter = new UserAdapter(UserListActivity.this, R.layout.list_view_row, neighBours);


            TextView nb = (TextView) findViewById(R.id.user_nb);
            if (neighBours.size()==0){
                nb.setText("Aucun voisin trouvé dans un rayon de " + model.getRadius() + " km");
            } else {
                nb.setText( neighBours.size() + " voisins trouvés dans un rayon de  " + model.getRadius() + " km" );
            }

            mListView.setAdapter(adapter);
            dialog.dismiss();
            dialog = null;
        }
    }



}
