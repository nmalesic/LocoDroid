package com.bl.locodroid.localstorage;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by fcoeuret on 10/02/2016.
 */
public interface ILocalStorageDB {

    /**
     * Create Local database
     * @return true = Create OK / false = Create KO
     */
    public Boolean createLocalDatabase(SQLiteDatabase db);


    /**
     * Connect to Local Database
     * @return true = Connect OK / false = Connect KO
     */
    public Boolean connectLocalDatabase();

    /**
     * Disconnect to Local Database
     * @return true = disconnect OK / false = disconnect KO
     */
    public Boolean disconnectLocalDatabase();

}
