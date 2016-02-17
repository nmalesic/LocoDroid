package com.bl.locodroid.test;

import com.bl.locodroid.localisation.Location;
import com.bl.locodroid.localisation.LocoAddress;
import com.bl.locodroid.model.LocoModel;
import com.bl.locodroid.user.User;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by nmalesic on 14/02/2016.
 */
public class LocoModelTest extends TestCase {

    ArrayList<User> neighBours;
    ArrayList<User> testNeighBours;
    LocoModel model;

    public void setUp() throws Exception {
        super.setUp();

        model = model.getInstance();
        ArrayList<User> testNeighBours = new ArrayList<User>();

        User a;
        Location loc;
        LocoAddress locoAddress;

        a = new User("RABOIS","Sylvain","pion de 6","a@a.a","a","a", null, "0102030405","M","false");
        loc = new Location("43.5563336","1.528394");
        locoAddress = new LocoAddress("10 Avenue de Gameville","","31650","Saint-Orens-de-Gameville",loc);
        testNeighBours.add(a);

        a = new User("CHAMAYOU","Olivier","objet composition détaché","b@b.b","b","b", null, "0602030405","M","false");
        loc = new Location("43.5175497","1.5057399");
        locoAddress = new LocoAddress("10 Rue du Pic du Midi","","31240","L'Union",loc);
        testNeighBours.add(a);

        a = new User("COEURET","Fabrice","Singleton","c@c.c","c","c", null, "0702030405","M","false");
        loc = new Location("43.5175497","1.5057399");
        locoAddress = new LocoAddress("Place Clemence Isaure","","31320","Castanet-Tolosan",loc);
        testNeighBours.add(a);


    }

    public void tearDown() throws Exception {

    }

    public void testGetInstance() throws Exception {

    }

    public void testGetNeighbours_Location() throws Exception {

    }

    public void testGetNeighbours_String() throws Exception {

    }

    public void testGetNeighbours() throws Exception {

        neighBours = model.getNeighbours();
        assertEquals("Nom","RABOIS",neighBours.get(0).getLastName());

    }

    public void testConnect() throws Exception {

    }

    public void testDisconnect() throws Exception {

    }

    public void testSubscribeUser() throws Exception {

    }

    public void testUpdateUser() throws Exception {

    }

    public void testDeleteUser() throws Exception {

    }
}