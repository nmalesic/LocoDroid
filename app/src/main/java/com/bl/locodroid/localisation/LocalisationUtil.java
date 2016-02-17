package com.bl.locodroid.localisation;

import android.location.Address;

import com.bl.locodroid.localisation.domain.Location;
import com.bl.locodroid.localisation.domain.LocoAddress;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by nmalesic on 08/02/2016.
 */
public class LocalisationUtil {

    public static GoogleGeoCodeResponse result2GCoord(String result) {
        // Récupération complète des info de la coordonnées
        GoogleGeoCodeResponse gsonCoords = null;
        if (result != null) {

            final GsonBuilder gsonBuilder = new GsonBuilder();
            final Gson gson = gsonBuilder.create();
            gsonCoords = gson.fromJson(result, GoogleGeoCodeResponse.class);
        }
        return gsonCoords;
    }

    public static String GCoord2result(GoogleGeoCodeResponse gsonCoords) {
        String result = null;
        if (gsonCoords != null) {
            final GsonBuilder gsonBuilder = new GsonBuilder();
            final Gson gson = gsonBuilder.create();
            result = gson.toJson(gsonCoords, GoogleGeoCodeResponse.class);
        }
        return result;
    }

    public static LocoAddress convertAndroidAddress2LocoAddress(Address address) {
        LocoAddress locoAddress = new LocoAddress(
                address.getFeatureName() + " " + address.getThoroughfare(),
                address.getSubThoroughfare(),
                address.getPostalCode(),
                address.getLocality(),
                (address.hasLatitude()!=false && address.hasLongitude() != false)?new Location(String.valueOf(address.getLatitude()),String.valueOf(address.getLongitude())):null
        );
        return locoAddress;
    }



    /*
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     * @returns Distance in Meters
     */
    public static double distance(double lat1, double lat2, double lon1,
                                  double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        Double latDistance = Math.toRadians(lat2 - lat1);
        Double lonDistance = Math.toRadians(lon2 - lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }
}
