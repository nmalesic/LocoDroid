package com.bl.locodroid.model;


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

    private static final boolean ISGETNEIGHBOURSMOCK = true;

    /** Constructeur privé */
    private LocoModel()
    {
        userService = new UserService();
        localisationService = new LocalisationService();
    }

    /** Instance unique non préinitialisée */
    private static LocoModel INSTANCE = null;
    private UserService userService = null;
    private LocalisationService localisationService = null;

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
    private int radius = 5000;

    public User getUserConnected() {
        return userConnected;
    }

    public void setUserConnected(User userConnected) {
        this.userConnected = userConnected;
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
     * Mock
     * @return List of few mock users
     */
    private ArrayList<User> getNeighboursMock()
    {

        ArrayList<User> neighBours = new ArrayList<User>();

        // WebService Mock
        neighBours = userService.getNeighbours(new Location());



//
//        User a;
//        Location loc;
//        LocoAddress locoAddress;
//
//        a = new User("RABOIS","Sylvain","pion de 6","a@a.a","a","a", null, "0102030405","M","false");
//        loc = new Location("43.5563336","1.528394");
//        locoAddress = new LocoAddress("10 Avenue de Gameville","","31650","Saint-Orens-de-Gameville",loc);
//        locoAddress.setLocation(loc);
//        a.setAddress(locoAddress);
//        neighBours.add(a);
//
//        a = new User("CHAMAYOU","Olivier","objet composition détaché","b@b.b","b","b", null, "0602030405","M","false");
//        loc = new Location("43.5175497","1.5057399");
//        locoAddress = new LocoAddress("10 Rue du Pic du Midi","","31240","L'Union",loc);
//        locoAddress.setLocation(loc);
//        a.setAddress(locoAddress);
//        neighBours.add(a);
//
//        a = new User("COEURET","Fabrice","Singleton","c@c.c","c","c", null, "0702030405","M","false");
//        loc = new Location("43.5175497","1.5057399");
//        locoAddress = new LocoAddress("Place Clemence Isaure","","31320","Castanet-Tolosan",loc);
//        locoAddress.setLocation(loc);
//        a.setAddress(locoAddress);
//        neighBours.add(a);

        lastNeighBours = neighBours;
        return neighBours;
    }

    /**
     * Find users which have an address in the search radius in meter
     * @param center center of the search
     * @return List of users in the circle
     */
    private ArrayList<User> getNeighbours(Location center) {
        //Persistance persistance = PersistanceManager.getPersitanceSession(sessionScope); //GestionSession.getPersitanceSession(sessionScope);

        ArrayList<User> listUserInRadius = null;

        if (ISGETNEIGHBOURSMOCK == true) {
            listUserInRadius =  getNeighboursMock();
        } else {

            HashMap<String, User> listeUser = userService.getAllUser();

            listUserInRadius = new ArrayList<User>();

            for (Map.Entry<String, User> user : listeUser.entrySet()) {

                double dist = LocalisationUtil.distance(Double.parseDouble(user.getValue().getAddress().getLocation().lat)
                        , Double.parseDouble(center.lat)
                        , Double.parseDouble(user.getValue().getAddress().getLocation().lng)
                        , Double.parseDouble(center.lng)
                        , (double) 0
                        , (double) 0);

                //user.getKey();
                if (dist < getRadius() * 1000) {
                    listUserInRadius.add(user.getValue());
                }

            }
        }

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
