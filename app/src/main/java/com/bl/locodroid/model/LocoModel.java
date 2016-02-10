package com.bl.locodroid.model;


import com.bl.locodroid.user.User;

import java.util.ArrayList;

/**
 * Created by nmalesic on 10/02/2016.
 */
public class LocoModel {

    /** Constructeur privé */
    private LocoModel()
    {}

    /** Instance unique non préinitialisée */
    private static LocoModel INSTANCE = null;

    /** Point d'accès pour l'instance unique du singleton */
    public static synchronized LocoModel getInstance()
    {
        if (INSTANCE == null)
        { 	INSTANCE = new LocoModel();
        }
        return INSTANCE;
    }


    private User userConnected;
    private ArrayList<User> lastNeighBours;


    public ArrayList<User> getNeighBours(User user) {
        return null;
    }

    public ArrayList<User> getNeighBours(String Address) {
        return null;
    }

    public User connect(String login, String password) {
        return null;
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
