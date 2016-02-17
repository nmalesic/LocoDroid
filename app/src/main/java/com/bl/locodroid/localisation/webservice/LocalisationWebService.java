package com.bl.locodroid.localisation.webservice;

import com.bl.locodroid.localisation.GRoute;
import com.bl.locodroid.localisation.domain.LocoAddress;

/**
 * Created by nmalesic on 09/02/2016.
 */
public class LocalisationWebService implements ILocalisationWebService {
    @Override
    public LocoAddress getGAddress(String Address) {

        // Call the LocalisationWebService LocoMaps to download a LocoAddress corresponding to Address
        // if this address has already been recovered

        // If the LocalisationWebService LocoMaps is not reachable or this address is not known by him
        // Search in the local Storage to retrieve a LocoAddress corresponding to Address

        // If LocalisationWebService LocoMaps and local storage don't know this address,
        // Call GoogleMaps LocalisationWebService to retrieve a LocoAddress



        return null;
    }

    @Override
    public GRoute getGRoute(String Address1, String Address2) {

        // Call the LocalisationWebService LocoMaps to download a GRoute corresponding to a route between Address1 and Address2
        // if this Groute has already been recovered

        // If the LocalisationWebService LocoMaps is not reachable or the route between Address1 and Address2 is not known by him
        // Search in the local Storage to retrieve a GRoute corresponding to Address1 and Address2

        // If LocoMaps LocalisationWebService and local storage don't know this route between Address1 and Address2 ,
        // Call GoogleMaps LocalisationWebService to retrieve a GRoute

        return null;
    }
}
