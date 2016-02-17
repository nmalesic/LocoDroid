package com.bl.locodroid.localstorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.bl.locodroid.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fcoeuret on 10/02/2016.
 */
public class LocalStorageDB extends SQLiteOpenHelper implements ILocalStorageDB {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "LocoDroid.db";
    private LocalStorageDB localDB;
    private Context context;
    //LocalStorageDB mDbHelper = new LocalStorageDB(getContext());


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
    //public static final String USER_CONNECTED_USER = "ConnectedUSer";

    //Script Create database
    public static final String DATABASE_CREATE = "CREATE LOCODROID.DB";

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
                USER_TELEPHONE + " VARCHAR (20))"; //+
                //USER_CONNECTED_USER + " BOOLEAN UNIQUE ON CONFLICT ABORT);";

    //private SQLiteDatabase db = localDB.getReadableDatabase();

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public LocalStorageDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(LocalStorageDB.DATABASE_CREATE);
        db.execSQL(USER_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(USER_TABLE_CREATE);
    }

    //@Override
    public Boolean createLocalDatabase(SQLiteDatabase db) {
        //db.execSQL(USER_TABLE_CREATE);

        return null;
    }

    @Override
    public Boolean connectLocalDatabase() {


        return null;
    }


    @Override
    public Boolean disconnectLocalDatabase() {


        return null;
    }

    public boolean subscribeUser(UserLocal userlocal)
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

    public ArrayList<UserLocal> readListUserLocal()
    {
        ArrayList<UserLocal> listUser = new ArrayList<UserLocal>();
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        Cursor resultSet = db.rawQuery("SELECT * FROM " + USER_TABLE_NAME,null);
        if (resultSet != null)
        {
            resultSet.moveToFirst();
            //Log.i("nb", resultSet.getCount() + "");
            do {
                UserLocal userLocal = new UserLocal();
                User user = new User();
                user.setId(resultSet.getInt(resultSet.getColumnIndex(USER_ID)));
                user.setLastName(resultSet.getString(resultSet.getColumnIndex(USER_LASTNAME)));
                user.setFirstName(resultSet.getString(resultSet.getColumnIndex(USER_FIRSTNAME)));
                user.setPseudo(resultSet.getString(resultSet.getColumnIndex(USER_PSEUDO)));
                user.setEmail(resultSet.getString(resultSet.getColumnIndex(USER_EMAIL)));
                user.setPassword(resultSet.getString(resultSet.getColumnIndex(USER_PASSWORD)));
                user.setSex(resultSet.getString(resultSet.getColumnIndex(USER_SEX)));
                user.setSmoker(resultSet.getString(resultSet.getColumnIndex(USER_SMOKER)));
                user.setTelephone(resultSet.getString(resultSet.getColumnIndex(USER_TELEPHONE)));
                userLocal.setUser(user);
                listUser.add(userLocal);
                }while (resultSet.moveToNext());
        }
        return listUser;
   }


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
