package com.bl.locodroid.user.service;

import com.bl.locodroid.localisation.domain.Location;
import com.bl.locodroid.user.domain.User;
import com.bl.locodroid.user.webservice.UserWebService;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by SRABOIS on 09/02/2016 apres l'apero et avant les pizzas
 */
public class UserService implements IUserService {

    private User user;

    @Override
    public User getLocalUser() {
        return null;
    }

    @Override
    public boolean setLocalUser(User user) {
        return false;
    }

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
    public ArrayList<User> getAllUser() {
        UserWebService userWebService = new UserWebService();
        return userWebService.getAllUser();
    }

    @Override
    public ArrayList<User> getNeighbours(Location center) {
        UserWebService userWebService = new UserWebService();
        return userWebService.getNeighbours(center);
    }
}
