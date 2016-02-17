package com.bl.locodroid.localisation.service;

import com.bl.locodroid.localisation.GRoute;
import com.bl.locodroid.localisation.domain.LocoAddress;

/**
 * Created by nmalesic on 05/02/2016.
 */
public interface ILocalisationService {
    LocoAddress getLocoAddress(String Address);
    GRoute getGRoute(String Address1, String Address2);

}
