package com.bl.locodroid.localstorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.bl.locodroid.localisation.Address_component;
import com.bl.locodroid.localisation.Location;
import com.bl.locodroid.localisation.LocoAddress;
import com.bl.locodroid.user.User;

import org.xml.sax.helpers.ParserFactory;

import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fcoeuret on 10/02/2016.
 */
public class LocalStorageDB extends SQLiteOpenHelper implements ILocalStorage {

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
    public static final String USER_ANONYME = "UserAnonyme";

    //Script Create and Drop Table USER
    public static final String USER_TABLE_CREATE =
        "CREATE TABLE " + USER_TABLE_NAME + " (" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, " +
                USER_LASTNAME + " VARCHAR (40) NOT NULL, " +
                USER_FIRSTNAME + " VARCHAR (40) NOT NULL, " +
                USER_PSEUDO + " VARCHAR (50) NOT NULL, " +
                USER_EMAIL + " VARCHAR (80) UNIQUE NOT NULL, " +
                USER_PASSWORD + " VARCHAR (30) NOT NULL, " +
                USER_SEX + " VARCHAR (10), " +
                USER_SMOKER + " BOOLEAN, " +
                USER_TELEPHONE + " VARCHAR (20)," +
                USER_CONNECTED_USER + " BOOLEAN," +
                USER_ANONYME + " BOOLEAN)";
    public static final String USER_TABLE_DROP = "DROP TABLE IF EXISTS " + USER_TABLE_NAME;
    public static final String USER_ANONYME_CREATE = "INSERT INTO " + USER_TABLE_NAME + " VALUES(null,\"ANONYME\",\"ANONYME\",\"ANONYME\",\"ANONYME\",\"ANONYME\",\"\",0,\"\",0,1)";

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

    //Script Create and Drop Table ADDRESS
    public static final String ADDRESS_TABLE_CREATE =
            "CREATE TABLE " + ADDRESS_TABLE_NAME + " (" + ADDRESS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, " +
                    ADDRESS_ID_USER + " INTEGER NOT NULL REFERENCES " + USER_TABLE_NAME + " (" + USER_ID + "), " +
                    ADDRESS_ADDRESS_1 + " VARCHAR (50), " +
                    ADDRESS_ADDRESS_2 + " VARCHAR (50), " +
                    ADDRESS_POSTAL_CODE + " VARCHAR (5), " +
                    ADDRESS_CITY + " VARCHAR (80), " +
                    ADDRESS_LATITUDE + " VARCHAR (20), " +
                    ADDRESS_LONGITUDE + " VARCHAR (20), " +
                    ADDRESS_TYPE_ADDRESS + " VARCHAR (10), " +
                    ADDRESS_ONELINE_ADDRESS + " VARCHAR (1000), " +
                    " CONSTRAINT FK_USER_ADDRESS FOREIGN KEY (" + ADDRESS_ID_USER + ") REFERENCES " + USER_TABLE_NAME + " (" + ADDRESS_ID_USER + "))";
    public static final String ADDRESS_TABLE_DROP = "DROP TABLE IF EXISTS " + ADDRESS_TABLE_NAME;

    //Table NEIGHBOUR
    public static final String NEIGHBOUR_TABLE_NAME = "NEIGHBOUR";

    //Attributes Table NEIGHBOUR
    public static final String NEIGHBOUR_ID = "IdNeighbour";
    public static final String NEIGHBOUR_ID_USER = "IdUser";
    public static final String NEIGHBOUR_ID_USER_NEIGHBOUR = "IdUserNeighbour";
    public static final String NEIGHBOUR_DISTANCE = "Distance";

    //Script Create and Drop Table NEIGHBOUR
    public static final String NEIGHBOUR_TABLE_CREATE =
            "CREATE TABLE " + NEIGHBOUR_TABLE_NAME + " (" + NEIGHBOUR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, " +
                    NEIGHBOUR_ID_USER + " INTEGER CONSTRAINT FK_Neighbour_User REFERENCES " + USER_TABLE_NAME +  "(" + USER_ID + "), " +
                    NEIGHBOUR_ID_USER_NEIGHBOUR + " INTEGER NOT NULL, " +
                    NEIGHBOUR_DISTANCE + " DOUBLE)";
    public static final String NEIGHBOUR_TABLE_DROP = "DROP TABLE IF EXISTS " + NEIGHBOUR_TABLE_NAME;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * Consutructeur of SQLite database
     * @param context Context
     * @param name name of local SQLite database
     * @param factory factory
     * @param version version of database
     */
    public LocalStorageDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        SQLiteDatabase db = this.getWritableDatabase();
        dropTable(db);
        onCreate(db);
        addListUserTest();
    }

    /**
     * Drop all tables in SQLite database
     * @param db SQLite database
     */
    public void dropTable(SQLiteDatabase db)
    {
        db.execSQL(NEIGHBOUR_TABLE_DROP);
        db.execSQL(ADDRESS_TABLE_DROP);
        db.execSQL(USER_TABLE_DROP);
    }

    /**
     * Create Local SQLite database
     * @param db database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER_TABLE_CREATE);
        db.execSQL(USER_ANONYME_CREATE);
        db.execSQL(ADDRESS_TABLE_CREATE);
        db.execSQL(NEIGHBOUR_TABLE_CREATE);
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

    /**
     * Disconnect of local SQLite database
     * @return true = OK / false = KO
     */
    @Override
    public Boolean disconnectLocalDatabase() {
        return null;
    }

    /**
     * Add UserLocal in SQLite database
     * @param user
     * @return true = OK / false = KO
     */
    @Override
    public boolean addUserLocal(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //cv.put(USER_ID,userlocal.getUser().getId());
        cv.put(USER_LASTNAME, user.getLastName());
        cv.put(USER_FIRSTNAME, user.getFirstName());
        cv.put(USER_PSEUDO, user.getPseudo());
        cv.put(USER_EMAIL, user.getEmail());
        cv.put(USER_PASSWORD, user.getPassword());
        cv.put(USER_SEX, user.getSex());
        cv.put(USER_SMOKER, user.getSmoker());
        cv.put(USER_TELEPHONE, user.getTelephone());
        cv.put(USER_CONNECTED_USER, false);
        long i = db.insert(USER_TABLE_NAME, null, cv);  //Return id saved
        if (i == -1)
        {
            //Erreur lors de l'ajout
            return false;
        }
        else
        {
            //Récupération de l'Id de l'user créé
            //User userForId = new User();
            //userForId = getUserLocalByEmail(user.getEmail());
            addAddressUserLocal((int)i, user.getAddress());
            //addAddressUserLocal(userForId.getId(), user.getAddress());
            //Fermeture de la base
            db.close();
            return true;
        }
    }

    /**
     * Add UserLocal Address in SQLite database
     * @param idUser idUser
     * @param address address
     * @return true = OK / false = KO
     */
    private boolean addAddressUserLocal(int idUser, LocoAddress address)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //cv.put(ADDRESS_ID,address.getId());
        cv.put(ADDRESS_ID_USER,idUser);
        cv.put(ADDRESS_ADDRESS_1, address.getAddress1());
        cv.put(ADDRESS_ADDRESS_2, address.getAddress2());
        cv.put(ADDRESS_POSTAL_CODE, address.getCodePostal());
        cv.put(ADDRESS_CITY, address.getCity());
        cv.put(ADDRESS_LATITUDE, address.getLocation().getLat());
        cv.put(ADDRESS_LONGITUDE, address.getLocation().getLng());
        cv.put(ADDRESS_ONELINE_ADDRESS, address.getOnelineAddress());
        long i = db.insert(ADDRESS_TABLE_NAME, null, cv);
        if (i == -1)
        {
            //Erreur lors de l'ajout
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * Get a UserLocal from SQLite database
     * @param email Email of User
     * @return UserLocal
     */
    @Override
    public User getUserLocalByEmail(String email)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        User user = new User();
        user = null;
        ContentValues cv = new ContentValues();
        //Log.i("requete", "SELECT * FROM " + USER_TABLE_NAME + " U LEFT JOIN " + ADDRESS_TABLE_NAME + " A ON U." + USER_ID + " = A." + ADDRESS_ID_USER + " WHERE " + USER_EMAIL + " = '" + email + "'");
        Cursor cursor = db.rawQuery("SELECT * FROM " + USER_TABLE_NAME + " U LEFT JOIN " + ADDRESS_TABLE_NAME + " A ON U." + USER_ID + " = A." + ADDRESS_ID_USER + " WHERE " + USER_EMAIL + " = '" + email + "'", null);
        if (cursor != null) {
            cursor.moveToFirst();
            user = informUser(cursor);
        }
        //Fermeture de la base
        db.close();
        return user;
    }

    /**
     * Add a list of Neighbour in SQLite database
     * @param idUser
     * @param listNeighbour
     * @return true = OK / false = KO
     */
    public boolean addListLocalNeighbour(int idUser, ArrayList<User> listNeighbour)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = null;
        String distance = "5.5";
        boolean result = true;
        long j = 0;
        for (int i = 0; i <listNeighbour.size();i++)
        {
            cv = new ContentValues();
            cv.put(NEIGHBOUR_ID_USER,idUser);
            cv.put(NEIGHBOUR_ID_USER_NEIGHBOUR,listNeighbour.get(i).getId());
            cv.put(NEIGHBOUR_DISTANCE,distance);
            j = db.insert(NEIGHBOUR_TABLE_NAME, null, cv);
            if (j == -1)
            {
                //Erreur lors de l'ajout
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Get a list of User Neighbour in SQLite database
     * @param idUser Identifiant User
     * @return List of User Neighbour
     */
    @Override
    public ArrayList<User> getListLocalNeighbour(int idUser)
    {
        ArrayList<User> listUser = new ArrayList<User>();
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        Cursor cursor = db.rawQuery("SELECT * FROM " + NEIGHBOUR_TABLE_NAME + " R" +
                " LEFT JOIN " + USER_TABLE_NAME + " U ON R." + NEIGHBOUR_ID_USER_NEIGHBOUR + " = U." + USER_ID +
                " LEFT JOIN " + ADDRESS_TABLE_NAME + " A ON U." + USER_ID + " = A." + ADDRESS_ID_USER +
                " WHERE R." + NEIGHBOUR_ID_USER + " = " + idUser, null);
        if (cursor != null)
        {
            cursor.moveToFirst();
            do {
                listUser.add(informUser(cursor));
                }while (cursor.moveToNext());
        }
        //Fermeture de la base
        db.close();
        return listUser;
    }

    /**
     * Inform attributes of User with his address and location
     * @param cursor cursor of User
     * @return User
     */
    private User informUser(Cursor cursor)
    {
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
        int idAddress = cursor.getInt(cursor.getColumnIndex(ADDRESS_ID));
        String address1 = cursor.getString(cursor.getColumnIndex(ADDRESS_ADDRESS_1));
        String address2 = cursor.getString(cursor.getColumnIndex(ADDRESS_ADDRESS_2));
        String cp = cursor.getString(cursor.getColumnIndex(ADDRESS_POSTAL_CODE));
        String city = cursor.getString(cursor.getColumnIndex(ADDRESS_CITY));
        Location location = new Location();
        location.setLat(cursor.getString(cursor.getColumnIndex(ADDRESS_LATITUDE)));
        location.setLng(cursor.getString(cursor.getColumnIndex(ADDRESS_LONGITUDE)));
        LocoAddress address = new LocoAddress(address1,  address2, cp, city, location);
        address.setOnelineAddress(cursor.getString(cursor.getColumnIndex(ADDRESS_ONELINE_ADDRESS)));
        address.setId(idAddress);
        user.setAddress(address);
        return user;
    }


    /**
     * Test local SQLite database with creation of users...
     */
    public void addListUserTest() {
        ArrayList<User> listNeighbour = new ArrayList<User>();
        SQLiteDatabase db = this.getWritableDatabase();
        User user = new User(); //ID = 2
        user.setId(2);
        user.setFirstName("Fabrice");
        user.setLastName("COEURET");
        user.setPseudo("fcoeuret");
        user.setEmail("fcoeuret@segilog.com");
        user.setPassword("11111111");
        user.setSex("Masculin");
        user.setSmoker("Non");
        user.setTelephone("1111111111");
        Location location = new Location();
        location.setLat("43.5167");
        location.setLng("1.5");
        LocoAddress address = new LocoAddress("4 place Clémence ISAURE","Appart 24","31320", "CASTANET-TOLOSAN",location);
        address.setOnelineAddress(address.getAddress1() + "," + address.getAddress2() + "," + address.getCodePostal() + "," + address.getCity());
        user.setAddress(address);
        addUserLocal(user);

        user = new User(); //ID = 3
        user.setId(3);
        user.setFirstName("Olivier");
        user.setLastName("CHAMAYOU");
        user.setPseudo("ochamayou");
        user.setEmail("olivier.chamayou@berger-levrault.fr");
        user.setPassword("222222");
        user.setSex("Masculin");
        user.setSmoker("Oui");
        user.setTelephone("2222222222");
        location = new Location();
        location.setLat("43.6043");
        location.setLng("1.4437");
        address = new LocoAddress("Place du Capitole", "", "31000","TOULOUSE",location);
        address.setOnelineAddress(address.getAddress1() + "," + address.getAddress2() + "," + address.getCodePostal() + "," + address.getCity());
        user.setAddress(address);
        addUserLocal(user);

        user = new User(); //ID = 4
        user.setId(4);
        user.setFirstName("Nicolas");
        user.setLastName("MALESIC");
        user.setPseudo("nmalesic");
        user.setEmail("nmalesic@segilog.com");
        user.setPassword("333333");
        user.setSex("Masculin");
        user.setSmoker("Non");
        user.setTelephone("3333333333");
        location = new Location();
        location.setLat("43.5167");
        location.setLng("1.5");
        address = new LocoAddress("4 place Clémence ISAURE","Appart 24","31320", "CASTANET-TOLOSAN",location);
        address.setOnelineAddress(address.getAddress1() + "," + address.getAddress2() + "," + address.getCodePostal() + "," + address.getCity());
        user.setAddress(address);
        addUserLocal(user);
        listNeighbour.add(user);

        user = new User(); //ID = 5
        user.setId(5);
        user.setFirstName("Sylvain");
        user.setLastName("RABOIS");
        user.setPseudo("srabois");
        user.setEmail("sylvain.rabois@berger-levrault.fr");
        user.setPassword("444444");
        user.setSex("Masculin");
        user.setSmoker("Non");
        user.setTelephone("4444444444");
        location = new Location();
        location.setLat("43.55");
        location.setLng("1.5333");
        address = new LocoAddress("12 rue de Gameville","","31650","ST ORENS DE GAMEVILLE",location);
        address.setOnelineAddress(address.getAddress1() + "," + address.getAddress2() + "," + address.getCodePostal() + "," + address.getCity());
        user.setAddress(address);
        addUserLocal(user);
        listNeighbour.add(user);

        user = new User();
        user = getUserLocalByEmail("fcoeuret@segilog.com");

        addListLocalNeighbour(user.getId(),listNeighbour);

        ArrayList<User> ln = new ArrayList<User>();
        ln = getListLocalNeighbour(2);
     //List of neighbour

    }

}
