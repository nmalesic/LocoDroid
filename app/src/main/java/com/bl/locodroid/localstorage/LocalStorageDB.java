package com.bl.locodroid.localstorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bl.locodroid.user.domain.User;

import java.util.ArrayList;

/**
 * Created by fcoeuret on 10/02/2016.
 */
public abstract class LocalStorageDB extends SQLiteOpenHelper implements ILocalStorage {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "LocoDroid.db";
    private LocalStorageDB localDB;
    private Context context;

    //Table USER
    public static final String USER_TABLE_NAME = "USER";

    //Attributes Table USER
    public static final String USER_ID = "IdUser";
    public static final String USER_LASTNAME = "LastName";
    public static final String USER_FIRSTNAME = "FisrtName";
    public static final String USER_PSEUDO = "Pseudo";
    public static final String USER_EMAIL = "Email";
    public static final String USER_PASSWORD = "Password";
    public static final String USER_SEX = "Sex";
    public static final String USER_SMOKER = "Smoker";
    public static final String USER_TELEPHONE = "Telephone";
    public static final String USER_CONNECTED_USER = "ConnectedUSer";

    //Script Create Table USER
    public static final String USER_TABLE_CREATE =
        "CREATE TABLE " + USER_TABLE_NAME + " (" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, " +
                USER_LASTNAME + " VARCHAR (40) NOT NULL, " +
                USER_FIRSTNAME + " VARCHAR (40) NOT NULL, " +
                USER_PSEUDO + " VARCHAR (50) NOT NULL, " +
                USER_EMAIL + " VARCHAR (80) UNIQUE NOT NULL, " +
                USER_PASSWORD + " VARCHAR (30) NOT NULL, " +
                USER_SEX + " VARCHAR (10), " +
                USER_SMOKER + " BOOLEAN, " +
                USER_TELEPHONE + " VARCHAR (20))" +
                USER_CONNECTED_USER + " BOOLEAN)";

    //Table ADDRESS
    public static final String ADDRESS_TABLE_NAME = "ADDRESS";

    //Attributes Table ADDRESS
    public static final String ADDRESS_ID = "IdAddress";
    public static final String ADDRESS_ID_USER = "IdUser";
    public static final String ADDRESS_ADDRESS_1 = "Address1";
    public static final String ADDRESS_ADDRESS_2 = "Address2";
    public static final String ADDRESS_POSTAL_CODE = "PostalCode";
    public static final String ADDRESS_CITY = "City";
    public static final String ADDRESS_LATITUDE = "Latitude";
    public static final String ADDRESS_LONGITUDE = "Longitude";
    public static final String ADDRESS_TYPE_ADDRESS = "TypeAddress";
    public static final String ADDRESS_ONELINE_ADDRESS = "OneLineAddress";

    //Script Create Table ADDRESS
    public static final String ADDRESS_TABLE_CREATE =
            "CREATE TABLE " + ADDRESS_TABLE_NAME + " (" + ADDRESS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, " +
                    ADDRESS_ID_USER + " INTEGER NOT NULL, " +
                    ADDRESS_ADDRESS_1 + " VARCHAR (50), " +
                    ADDRESS_ADDRESS_2 + " VARCHAR (50), " +
                    ADDRESS_POSTAL_CODE + " VARCHAR (5), " +
                    ADDRESS_CITY + " VARCHAR (80), " +
                    ADDRESS_LATITUDE + " VARCHAR (20), " +
                    ADDRESS_LONGITUDE + " VARCHAR (20), " +
                    ADDRESS_TYPE_ADDRESS + " VARCHAR (10)" +
                    ADDRESS_ONELINE_ADDRESS + " VARCHAR (1000)," +
                    " CONSTRAINT FK_USER_ADDRESS FOREIGN KEY (" + ADDRESS_ID_USER + ") REFERENCES " + USER_TABLE_NAME + " (" + ADDRESS_ID_USER + ")";

    //Table RIDE
    public static final String RIDE_TABLE_NAME = "RIDE";

    //Attributes Table RIDE
    public static final String RIDE_ID = "IdRide";
    public static final String RIDE_ID_USER = "IdUser";
    public static final String RIDE_ID_USER_ONRIDE = "IdUserOnRide";
    public static final String RIDE_DISTANCE = "Distance";

    //Script Create Table RIDE
    public static final String RIDE_TABLE_CREATE =
            "CREATE TABLE " + RIDE_TABLE_NAME + " (" + RIDE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, " +
                    RIDE_ID_USER + " INTEGER CONSTRAINT FK_Ride_User REFERENCES " + USER_TABLE_NAME +  "(" + USER_ID + "), " +
                    RIDE_ID_USER_ONRIDE + " INTEGER NOT NULL, " +
                    RIDE_DISTANCE + " DOUBLE";


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public LocalStorageDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * Create Local SQLite database
     * @param db database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(LocalStorageDB.DATABASE_CREATE);
        db.execSQL(USER_TABLE_CREATE);
        db.execSQL(ADDRESS_TABLE_CREATE);
        db.execSQL(RIDE_TABLE_CREATE);
    }

    /**
     * Upgrade Local SQLite database
     * @param db Database
     * @param oldVersion Number of old version
     * @param newVersion Number of new version
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL(USER_TABLE_CREATE);
    }

    /**
     * Connect to Local SQLite Database
     * @return true = Connect OK / false = Connect KO
     */
    @Override
    public Boolean connectLocalDatabase() {
        return null;
    }

    @Override
    public Boolean disconnectLocalDatabase() {
        return null;
    }

    /**
     * Add UserLocal in SQLite database
     * @param userlocal
     * @return true = OK / false = KO
     */
    @Override
    public boolean addUserLocal(UserLocal userlocal)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_ID,userlocal.getUser().getId());
        cv.put(USER_LASTNAME, userlocal.getUser().getLastName());
        cv.put(USER_FIRSTNAME, userlocal.getUser().getFirstName());
        cv.put(USER_PSEUDO, userlocal.getUser().getPseudo());
        cv.put(USER_EMAIL, userlocal.getUser().getEmail());
        cv.put(USER_PASSWORD, userlocal.getUser().getPassword());
        cv.put(USER_SEX, userlocal.getUser().getSex());
        cv.put(USER_SMOKER, userlocal.getUser().getSmoker());
        cv.put(USER_TELEPHONE, userlocal.getUser().getTelephone());
        //cv.put(USER_CONNECTED_USER, true);
        //int i= db.update(USER_TABLE_NAME,cv, USER_ID + " = ?", new String[] {String.valueOf(userlocal.getUser().getId())});
        long i = db.insert(USER_TABLE_NAME, null, cv);
        if (i > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Get a UserLocal from SQLite database
     * @param email Email of User
     * @return UserLocal
     */
   /* @Override
    public UserLocal getUserLocalByEmail(String email)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        UserLocal userLocal = new UserLocal();
        userLocal = null;
        ContentValues cv = new ContentValues();
        Cursor cursor = db.rawQuery("SELECT * FROM " + USER_TABLE_NAME + " WHERE " + USER_EMAIL + " = " + email, null);
        if (cursor != null) {
            cursor.moveToFirst();
            userLocal = informUserLocal(cursor);
        }
        return userLocal;
    } */

    /**
     * Get a list of User Neigbour in SQLite database
     * @return List of User Neighbour
     */
    /* @Override
    public ArrayList<UserLocal> getListLocalNeighbour()
    {
        ArrayList<UserLocal> listUser = new ArrayList<UserLocal>();
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        Cursor cursor = db.rawQuery("SELECT * FROM " + USER_TABLE_NAME + " WHERE " + USER_CONNECTED_USER + " <> 1",null);
        if (cursor != null)
        {
            cursor.moveToFirst();
            do {
                listUser.add(informUserLocal(cursor));
                }while (cursor.moveToNext());
        }
        return listUser;
    } */

    /**
     * Close Local SQLite database
     */
    @Override
    public void Close()
    {

    }

    /**
     * Inform attributes of UserLocal
     * @param cursor cursor of UserLocal
     * @return USerLocal
     */
    /* private UserLocal informUserLocal(Cursor cursor)
    {
        UserLocal ul = new UserLocal();
        User user = new User();
        user.setId(cursor.getInt(cursor.getColumnIndex(USER_ID)));
        user.setLastName(cursor.getString(cursor.getColumnIndex(USER_LASTNAME)));
        user.setFirstName(cursor.getString(cursor.getColumnIndex(USER_FIRSTNAME)));
        user.setPseudo(cursor.getString(cursor.getColumnIndex(USER_PSEUDO)));
        user.setEmail(cursor.getString(cursor.getColumnIndex(USER_EMAIL)));
        user.setPassword(cursor.getString(cursor.getColumnIndex(USER_PASSWORD)));
        user.setSex(cursor.getString(cursor.getColumnIndex(USER_SEX)));
        user.setSmoker(cursor.getString(cursor.getColumnIndex(USER_SMOKER)));
        user.setTelephone(cursor.getString(cursor.getColumnIndex(USER_TELEPHONE)));
        ul.setUser(user);
        return ul;
    } */



//    LocalStorageDB localdb = new LocalStorageDB(this.getBaseContext(),LocalStorageDB.DATABASE_NAME,null,1);
//    SQLiteDatabase db = localdb.getWritableDatabase();
//    UserLocal localUser = new UserLocal();
//    User user = new User();
//    user.setId(35);
//    user.setFirstName("CHAMAYOU");
//    user.setLastName("Olivier");
//    user.setPseudo("ochamayou");
//    user.setEmail("olivier.chamayou@berger-levrault.fr");
//    user.setPassword("777777");
//    user.setSex("Masculin");
//    user.setSmoker("Oui");
//    user.setTelephone("0544113322");
//    localUser.setUser(user);
//    localdb.addUserLocal(localUser);
//
//    ArrayList<UserLocal> list = new ArrayList<UserLocal>();
//    list = localdb.readListUserLocal();
//
//    //localdb.createLocalDatabase;


}
