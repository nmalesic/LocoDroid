package com.bl.locodroid.model;


import com.bl.locodroid.localisation.GAddress;
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


    public ArrayList<User> getNeighBours()
    {
        if (userConnected == null) {
            // Impossible to retrieve a Neighbours list because no user is connected
            // Exception ???
        }

        // Calculate Neighbours around the User address
        //GAddress gaddress = userConnected.getAddress();


        // Bouchon
        ArrayList<User> neighBours = new ArrayList<User>();

        User a = new User("RABOIS","Sylvain","pion de 6","a@a.a","a","a", null, "0102030405","M","false");
        neighBours.add(a);
        a = new User("CHAMAYOU","Olivier","objet composition détaché","b@b.b","b","b", null, "0602030405","M","false");
        neighBours.add(a);
        a = new User("COEURET","Fabrice","Singleton","c@c.c","c","c", null, "0702030405","M","false");
        neighBours.add(a);


        return neighBours;
    }

    public ArrayList<User> getNeighBours(String Address) {

        return getNeighBours();
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
