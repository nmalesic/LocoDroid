package com.bl.locodroid.localstorage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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
     * Add UserLocal in SQLite database
     * @param userlocal
     * @return true = OK / false = KO
     */
    public boolean addUserLocal(UserLocal userlocal);

    /**
     * Get a UserLocal from SQLite database
     * @param email Email of User
     * @return UserLocal
     */
    public UserLocal getUserLocalByEmail(String email);

    /**
     * Get a list of UserLocal
     * @return List of USerLocal
     */
    public ArrayList<UserLocal> getListUserLocal();


    /**
     * Close Local SQLite database
     */
    public void Close();
}
