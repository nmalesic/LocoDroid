package com.bl.locodroid.user.service;

import com.bl.locodroid.localisation.domain.Location;
import com.bl.locodroid.user.domain.User;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by nmalesic on 05/02/2016.
 */
public interface IUserService {
    User getLocalUser();
    boolean setLocalUser(User user);
    User connect(String login, String password);
    boolean disconnect();
    boolean subscribeUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(User user);

    ArrayList<User> getAllUser();
    ArrayList<User> getNeighbours(Location center, int radius);
}
