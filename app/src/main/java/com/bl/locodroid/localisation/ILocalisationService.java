package com.bl.locodroid.localisation;

import com.bl.locodroid.user.User;

import java.util.ArrayList;

/**
 * Created by nmalesic on 05/02/2016.
 */
public interface ILocalisationService {
    GAddress getGAddress(String Adress);
    GRoute getGRoute(String Adress1, String Adress2);
    ArrayList<User> getNeighBours(User user);
    ArrayList<User> getNeighBours(GAddress gAddress);
}
