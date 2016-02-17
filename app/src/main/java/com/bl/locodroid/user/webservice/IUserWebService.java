package com.bl.locodroid.user.webservice;

import com.bl.locodroid.localisation.domain.Location;
import com.bl.locodroid.user.domain.User;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by nmalesic on 17/02/2016.
 */
public interface IUserWebService {
    User connect(String login, String password);
    boolean disconnect();
    boolean subscribeUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(User user);

    HashMap<String,User> getAllUser();
    ArrayList<User> getNeighbours(Location center);
}
