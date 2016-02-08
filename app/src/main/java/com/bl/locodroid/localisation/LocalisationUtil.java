package com.bl.locodroid.localisation;

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
}
