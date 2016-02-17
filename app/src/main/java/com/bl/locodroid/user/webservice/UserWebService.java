package com.bl.locodroid.user.webservice;

import com.bl.locodroid.localisation.domain.Location;
import com.bl.locodroid.user.domain.User;

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
        return null;
    }

    @Override
    public ArrayList<User> getNeighbours(Location center) {
        return null;
    }
}
