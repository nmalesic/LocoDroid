package com.bl.locodroid.localstorage;

import android.database.sqlite.SQLiteDatabase;

import com.bl.locodroid.user.domain.User;

import java.util.ArrayList;

/**
 * Created by nmalesic on 09/02/2016.
 */
public interface ILocalStorage {

    /**
     * Create Local SQLite database
     * @param db database
     */
    public void onCreate(SQLiteDatabase db);

    /**
     * Upgrade Local SQLite database
     * @param db Database
     * @param oldVersion Number of old version
     * @param newVersion Number of new version
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);

    /**
     * Connect to Local SQLite Database
     * @return true = Connect OK / false = Connect KO
     */
    public Boolean connectLocalDatabase();

    /**
     * Disconnect to Local SQLite Database
     * @return true = disconnect OK / false = disconnect KO
     */
    public Boolean disconnectLocalDatabase();

    /**
     * Add User in SQLite database
     * @param user
     * @return true = OK / false = KO
     */
    public boolean addUserLocal(User user);

    /**
     * Get a User from SQLite database
     * @param email Email of User
     * @return User
     */
    public User getUserLocalByEmail(String email);

    /**
     * Add a list of Neighbour in SQLite database
     * @param idUser
     * @param listNeighbour
     * @return true = OK / false = KO
     */
    public boolean addListLocalNeighbour(int idUser, ArrayList<User> listNeighbour);

    /**
     * Get a list of User Neighbour in SQLite database
     * @param idUser Identifiant User
     * @return List of User Neighbour
     */
    public ArrayList<User> getListLocalNeighbour(int idUser);

    /**
     * Delete a liste of neighbour in SQLite database
     * @param idUser
     * @return true = OK / false = KO
     */
    public boolean deleteListLocalNeighbour(int idUser);

//    /**
//     * Connect or disconnect a user in SQLite database
//     * @param user User to connect or disconnect
//     * @param connect true = User to connect / false = User to disconnect
//     * @return true = OK / false = KO
//     */
//    public boolean ConnectOrDisconnectUserLocal(User user,boolean connect);

}
