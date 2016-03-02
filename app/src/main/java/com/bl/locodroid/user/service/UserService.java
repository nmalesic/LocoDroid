package com.bl.locodroid.user.service;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.bl.locodroid.localisation.domain.Location;
import com.bl.locodroid.localstorage.LocalStorageDB;
import com.bl.locodroid.user.domain.User;
import com.bl.locodroid.user.webservice.UserWebService;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by SRABOIS on 09/02/2016 apres l'apero et avant les pizzas
 */
public class UserService implements IUserService {

    private User user;
    public Context context = null;

    public UserService(Context context) {
        this.context = context;
    }

    @Override
    public User getLocalUser() {
        LocalStorageDB localStorageDB = new LocalStorageDB(context,null,null,1);
        this.user = localStorageDB.getUserLocal();
        return this.user;
    }
    @Override
    public boolean setLocalUser(User user) {
        this.user = user;
        LocalStorageDB localStorageDB = new LocalStorageDB(context,null,null,1);
        return localStorageDB.addUserLocal(user);

    }

    @Override
    public User connect(String login, String password) {
        UserWebService userWebService = new UserWebService();
        return userWebService.connect(login, password);
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
        UserWebService userWebService = new UserWebService();
        return userWebService.getAllUser();
    }

    @Override
    public ArrayList<User> getNeighbours(Location center, int radius) {
        ArrayList<User> response = null;
        UserWebService userWebService = new UserWebService();
        response = userWebService.getNeighbours(center, radius);

//        if (response == null) {
//            // WebService is not responding
//            // Try to retrieve last result from local database
//            LocalStorageDB localStorageDB = new LocalStorageDB(context,null,null,1);
//            response = localStorageDB.getListLocalNeighbour(1);
//        } else {
//            // WebService is responding
//            // Save last result in local database
//            LocalStorageDB localStorageDB = new LocalStorageDB(context,null,null,1);
//            localStorageDB.addListLocalNeighbour(1,response);
//        }

        return response;
    }

}
