package com.bl.locodroid.localstorage;

import android.content.Context;

import com.bl.locodroid.user.User;
import com.bl.locodroid.user.UserService;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by fcoeuret on 09/02/2016.
 */
public class UserLocal implements UserService{

    private User user;
    private static final String nomFichier = "locoDroid.txt";

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Constructeur UserLocal
     */
    public UserLocal()
    {

    }

    @Override
    public User getLocalUser()
    {
        FileInputStream fis = null;
        User user = null;
        try
        {
//            fis = openFileInput(nomFichier);
//            byte[] buffer = new byte[1024];
//            StringBuilder content = new StringBuilder();
//            while ((fis.read(buffer)) != -1)
//            {
//                content.append(new String(buffer));
//                //user = ((User)content);
//            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            return user;
        }
    }

    @Override
    public boolean setLocalUser(User user)
    {
        Boolean res = true;
        FileOutputStream fos = null;
        try
        {
//            fos = openFileOutput(nomFichier, Context.ACCOUNT_SERVICE);
//            fos.write(user.toString().getBytes());
//            fos.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            res = false;
        }
        finally
        {
            return res;
        }
        //return false;
    }

    @Override
    public User connect(String login, String password) {
        return null;
    }

    @Override
    public boolean disconnect() {
        return false;
    }

    @Override
    public boolean subscribeUser(User user) {
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(User user) {
        return false;
    }
}
