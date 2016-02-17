package com.bl.locodroid.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.test.ServiceTestCase;
import android.util.Log;

import com.bl.locodroid.MainActivity;
import com.bl.locodroid.MapsActivity;
import com.bl.locodroid.localstorage.LocalStorageDB;

import junit.framework.TestCase;

import java.lang.reflect.Method;

/**
 * Created by fcoeuret on 14/02/2016.
 */
public class LocalStorageDBTest extends TestCase {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "LocoDroid_Test.db";
    private LocalStorageDB localDB;
    private MapsActivity activity = new MapsActivity();
    private SQLiteDatabase db;
    private Context context;
    SQLiteDatabase.CursorFactory factory =  null;


    /**
     * @return The {@link Context} of the test project.
     */
    private Context getTestContext()
    {
        try
        {
            Method getTestContext = ServiceTestCase.class.getMethod("getTestContext");
            return (Context) getTestContext.invoke(this);
        }
        catch (final Exception exception)
        {
            exception.printStackTrace();
            return null;
        }
    }

    public void setUp() throws Exception {
        super.setUp();

        //context = getTestContext();
        localDB = new LocalStorageDB(context, DATABASE_NAME, factory, DATABASE_VERSION);

    }

//    public void tearDown() throws Exception {
//
//    }
//
//    public void testOnCreate() throws Exception {
//
//    }
//
//    public void testOnUpgrade() throws Exception {
//
//    }


//    public void testConnectLocalDatabase() throws Exception {
//        //assertEquals(1,2);
//    }
//
//    public void testDisconnectLocalDatabase() throws Exception {
//
//    }
}