package com.bl.locodroid.user.webservice;

import android.util.Log;

import com.bl.locodroid.localisation.domain.Location;
import com.bl.locodroid.localisation.domain.LocoAddress;
import com.bl.locodroid.user.domain.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by nmalesic on 17/02/2016.
 */
public class UserWebService implements IUserWebService {
    @Override
    public User connect(String login, String password) {
        return null;
    }

    @Override
    public boolean disconnect() {
        return false;
    }

    @Override
    public boolean subscribeUser(User user) {
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(User user) {
        return false;
    }

    @Override
    public HashMap<String, User> getAllUser() {
        HashMap<String, User> responseHashMap = new HashMap<String, User>();
        ArrayList<User> response = new ArrayList<User>();

            HttpURLConnection conn = null;
            try {

                URL url = new URL("http://www.locomaps.com/user/getAllUser");

                conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(2000);
                conn.setReadTimeout(10000);
                conn.connect();


                // expect HTTP 200 OK, so we don't mistakenly save error report
                // instead of the file
                if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    Log.i("DownloadTask", "Response " + conn.getResponseCode());
                    return null;  //"Server returned HTTP " + connection.getResponseCode() + " " + connection.getResponseMessage();
                }


                InputStream is = conn.getInputStream();
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                StringBuilder responseStrBuilder = new StringBuilder();

                String inputStr;
                while ((inputStr = streamReader.readLine()) != null)
                    responseStrBuilder.append(inputStr);

                Type listType = new TypeToken<ArrayList<User>>() {
                }.getType();
                response = new Gson().fromJson(responseStrBuilder.toString(), listType);

                //jsonArray = new JSONArray(responseStrBuilder.toString());


            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        for (User u: response) {
            responseHashMap.put(u.getEmail(),u);
        }

        return responseHashMap;
    }

    @Override
    public ArrayList<User> getNeighbours(Location center) {

        //Mock

        // Simulation requete Web
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayList<User> neighBours = new ArrayList<User>();

        User a;
        Location loc;
        LocoAddress locoAddress;

        a = new User("webRABOIS","Sylvain","pion de 6","a@a.a","a","a", null, "0102030405","M","false");
        loc = new Location("43.5563336","1.528394");
        locoAddress = new LocoAddress("10 Avenue de Gameville","","31650","Saint-Orens-de-Gameville",loc);
        locoAddress.setLocation(loc);
        a.setAddress(locoAddress);
        neighBours.add(a);

        a = new User("webCHAMAYOU","Olivier","objet composition detache","b@b.b","b","b", null, "0602030405","M","false");
        loc = new Location("43.6575","1.4853");
        locoAddress = new LocoAddress("10 Rue du Pic du Midi","","31240","L Union",loc);
        locoAddress.setLocation(loc);
        a.setAddress(locoAddress);
        neighBours.add(a);

        a = new User("webCOEURET","Fabrice","Singleton","c@c.c","c","c", null, "0702030405","M","false");
        loc = new Location("43.5175497","1.5057399");
        locoAddress = new LocoAddress("Place Clemence Isaure","","31320","Castanet-Tolosan",loc);
        locoAddress.setLocation(loc);
        a.setAddress(locoAddress);
        neighBours.add(a);


        return neighBours;

    }
}
