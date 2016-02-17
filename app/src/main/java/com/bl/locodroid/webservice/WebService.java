package com.bl.locodroid.webservice;

import com.bl.locodroid.localisation.LocoAddress;
import com.bl.locodroid.localisation.GRoute;

/**
 * Created by nmalesic on 09/02/2016.
 */
public class WebService implements IWebService {
    @Override
    public LocoAddress getGAddress(String Address) {

        // Call the WebService LocoMaps to download a LocoAddress corresponding to Address
        // if this address has already been recovered

        // If the WebService LocoMaps is not reachable or this address is not known by him
        // Search in the local Storage to retrieve a LocoAddress corresponding to Address

        // If WebService LocoMaps and local storage don't know this address,
        // Call GoogleMaps WebService to retrieve a LocoAddress



        return null;
    }

    @Override
    public GRoute getGRoute(String Address1, String Address2) {

        // Call the WebService LocoMaps to download a GRoute corresponding to a route between Address1 and Address2
        // if this Groute has already been recovered

        // If the WebService LocoMaps is not reachable or the route between Address1 and Address2 is not known by him
        // Search in the local Storage to retrieve a GRoute corresponding to Address1 and Address2

        // If LocoMaps WebService and local storage don't know this route between Address1 and Address2 ,
        // Call GoogleMaps WebService to retrieve a GRoute

        return null;
    }
}
