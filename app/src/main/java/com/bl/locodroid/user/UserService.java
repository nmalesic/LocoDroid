package com.bl.locodroid.user;

/**
 * Created by nmalesic on 05/02/2016.
 */
public interface UserService {
    User getLocalUser(String login, String password);
    boolean setLocalUser(User user);
    User connect(String login, String password);
    boolean disconnect();
    boolean inscription(User user);
    boolean modifUser(User user);
}
