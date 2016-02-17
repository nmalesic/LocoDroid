package com.bl.locodroid.localisation.service;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.app.Service;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.bl.locodroid.localisation.GRoute;
import com.bl.locodroid.localisation.domain.LocoAddress;
import com.bl.locodroid.localisation.service.ILocalisationService;

import java.io.IOException;
import java.util.List;

/**
 * Created by nmalesic on 10/02/2016.
 */
public class LocalisationService extends Service implements ILocalisationService {

    private static final String TAG = "LocalisationService";
    Geocoder geocoder;

    public LocalisationService() {
        this.geocoder = new Geocoder(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public LocoAddress getLocoAddress(String address) {
        List<Address> adr = null;
        LocoAddress gaddress = null;
        try {
            adr = geocoder.getFromLocationName(address,1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (adr != null) {
            Log.i(TAG, "getLocoAddress: "+ adr.toString());
            gaddress = new LocoAddress(adr.get(0).getAddressLine(0),adr.get(0).getAddressLine(1),adr.get(0).getPostalCode(),adr.get(0).getLocality(),null);
        } else {
            Log.i(TAG, "getLocoAddress: null ");
        }
        return null;
    }

    @Override
    public GRoute getGRoute(String Address1, String Address2) {

        return null;
    }

}
