package com.bl.locodroid.model;


import android.content.Context;

import com.bl.locodroid.localisation.service.LocalisationService;
import com.bl.locodroid.localisation.LocalisationUtil;
import com.bl.locodroid.localisation.domain.Location;
import com.bl.locodroid.localisation.domain.LocoAddress;
import com.bl.locodroid.user.domain.User;
import com.bl.locodroid.user.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nmalesic on 10/02/2016.
 */
public class LocoModel {

    /** Constructeur privé */
    private LocoModel(Context context)
    {
        this.context = context;
        userService = new UserService(context);
        localisationService = new LocalisationService();
    }

    /** Instance unique non préinitialisée */
    private static LocoModel INSTANCE = null;
    private UserService userService = null;
    private LocalisationService localisationService = null;

    public Context context = null;

    /** Point d'accès pour l'instance unique du singleton */
    public static synchronized LocoModel getInstance(Context context)
    {
        if (INSTANCE == null)
        { 	INSTANCE = new LocoModel(context);

        }
        return INSTANCE;
    }


    private User userConnected;
    private ArrayList<User> lastNeighBours;
    private int radius = 5;

    public User getUserConnected() {
        //userConnected = userService.getLocalUser();
        return userConnected;
    }

    public void setUserConnected(User userConnected) {
        this.userConnected = userConnected;
        //userService.setLocalUser(userConnected);
    }

    public ArrayList<User> getLastNeighBours() {
        return lastNeighBours;
    }

    public void setLastNeighBours(ArrayList<User> lastNeighBours) {
        this.lastNeighBours = lastNeighBours;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }


    /**
     * Find users which have an address in the search radius in meter
     * @param center center of the search
     * @return List of users in the circle
     */
    private ArrayList<User> getNeighbours(Location center) {

        ArrayList<User> listUserInRadius = null;
        userService.context = context;

        listUserInRadius = userService.getNeighbours(center,getRadius());

        lastNeighBours = listUserInRadius;
        return listUserInRadius;
    }


    /**
     * Search Neighbours from oneline address
     * @param address oneline address
     * @return List of users in the circle
     */
    public ArrayList<User> getNeighbours(String address) {

        LocoAddress locoAddress = localisationService.getLocoAddress(address);
        return getNeighbours(locoAddress);
    }

    /**
     * Search Neighbours from user's address
     * @param user
     * @return List of users in the circle
     */
    public ArrayList<User> getNeighbours(User user) {
        LocoAddress locoAddress = user.getAddress();
        return getNeighbours(locoAddress);
    }

    private ArrayList<User> getNeighbours(LocoAddress locoAddress) {
        ArrayList<User> listUserInRadius = null;
        Location center = locoAddress.getLocation();
        listUserInRadius = getNeighbours(center);

        return listUserInRadius;
    }

    /**
     * Search Neighbours from address of the connected user
     * @return List of users in the circle
     */
    public ArrayList<User> getNeighbours() {
        ArrayList<User> listUserInRadius = null;
        if (getUserConnected() == null) {
            // Impossible to retrieve a Neighbours list because no user is connected
            // Exception ???
        } else {
            listUserInRadius = new ArrayList<User>();

            // Calculate Neighbours around the User address
            LocoAddress locoAddress = getUserConnected().getAddress();

            listUserInRadius = getNeighbours(locoAddress);
        }
        return listUserInRadius;
    }

    public User connect(String login, String password) {
        return userService.connect(login, password);
    }

    public boolean disconnect() {
        return false;
    }

    public boolean subscribeUser(User user) {
        return false;
    }

    public boolean updateUser(User user) {
        return false;
    }

    public boolean deleteUser(User user) {
        return false;
    }



}
