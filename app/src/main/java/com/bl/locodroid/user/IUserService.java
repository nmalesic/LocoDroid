package com.bl.locodroid.user;

/**
 * Created by nmalesic on 05/02/2016.
 */
public interface IUserService {
    User getLocalUser();
    boolean setLocalUser(User user);
    User connect(String login, String password);
    boolean disconnect();
    boolean subscribeUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(User user);
}
