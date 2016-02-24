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
        User response = new User();

        HttpURLConnection conn = null;
        try {

            //URL url = new URL("http://www.locomaps.com/user/getAllUser");
            //URL url = new URL("localhost:8080/LocoMaps/user/getAllUser");
            URL url = new URL("http://locomaps.cloudapp.net/LocoMaps/user?connectuser="+ login + "&pwd=" + password);


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

            response = new Gson().fromJson(responseStrBuilder.toString(), User.class);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return response;

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
    public ArrayList<User> getAllUser() {
        //HashMap<String, User> responseHashMap = new HashMap<String, User>();
        ArrayList<User> response = new ArrayList<User>();

            HttpURLConnection conn = null;
            try {

                //URL url = new URL("http://www.locomaps.com/user/getAllUser");
                //URL url = new URL("localhost:8080/LocoMaps/user/getAllUser");
                URL url = new URL("http://locomaps.cloudapp.net/LocoMaps/user?alluser");


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

//        for (User u: response) {
//            responseHashMap.put(u.getEmail(),u);
//        }
//        responseHashMap

        return response;
    }

    @Override
    public ArrayList<User> getNeighbours(Location center, int radius) {

        ArrayList<User> response = new ArrayList<User>();

        HttpURLConnection conn = null;
        try {

            //URL url = new URL("http://www.locomaps.com/user/getAllUser");
            //URL url = new URL("localhost:8080/LocoMaps/user/getAllUser");
            URL url = new URL("http://locomaps.cloudapp.net/LocoMaps/user?getNeighbours&lat="+center.getLat()+"&lng="+center.getLng()+"&radius="+radius);

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

//        for (User u: response) {
//            responseHashMap.put(u.getEmail(),u);
//        }
//        responseHashMap

        return response;

    }
}
