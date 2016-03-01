package com.bl.locodroid;

import com.bl.locodroid.user.domain.User;
import com.bl.locodroid.user.service.UserService;

import org.junit.Test;
//import org.testing.annotations.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    UserService userService;

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testUser(){
        User u = new User();
        assertEquals(User.class, u.getClass());
    }

    @Test
    public void testAllUsersIfOneConnected(){

        userService.setLocalUser(new User());
        ArrayList<User> liste = userService.getAllUser();
        assertEquals(1, liste.size());
    }
}

