package com.bl.locodroid.webservice;

import com.bl.locodroid.localisation.GAddress;
import com.bl.locodroid.localisation.GRoute;

/**
 * Created by nmalesic on 09/02/2016.
 */
public interface IWebService {
    GAddress getGAddress(String Address);
    GRoute getGRoute(String Address1, String Address2);
}
