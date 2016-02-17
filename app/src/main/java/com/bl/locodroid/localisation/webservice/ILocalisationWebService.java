package com.bl.locodroid.localisation.webservice;

import com.bl.locodroid.localisation.domain.LocoAddress;
import com.bl.locodroid.localisation.GRoute;

/**
 * Created by nmalesic on 09/02/2016.
 */
public interface ILocalisationWebService {
    LocoAddress getGAddress(String Address);
    GRoute getGRoute(String Address1, String Address2);
}
