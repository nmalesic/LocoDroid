package com.bl.locodroid.localisation;

import com.bl.locodroid.user.User;

import java.util.ArrayList;

/**
 * Created by nmalesic on 05/02/2016.
 */
public interface ILocalisationService {
    LocoAddress getLocoAddress(String Address);
    GRoute getGRoute(String Address1, String Address2);

}
