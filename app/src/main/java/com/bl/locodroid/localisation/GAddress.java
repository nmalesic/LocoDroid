package com.bl.locodroid.localisation;

/**
 * coords latitude and longitude in google maps format
 * corresponding
 * to an adress typed in a search area
 * or an adress typed in the user profil form
 * Created by nmalesic on 05/02/2016.
 */
public class GAddress {
    private TypeAddress typeAddress;
    private boolean geocode = false;

    // profile address
    private int id;
    private String address1;
    private String address2;
    private String cp;
    private String city;
    private String result;

    // address used in search
    private String addressSaisie;

    private Location location;

    // address google maps
    private GoogleGeoCodeResponse gcoord;


    public GAddress(String adresse1, String adresse2, String CodePostal, String ville, String result) {
        super();
        setTypeAddress(TypeAddress.PROFIL);
        this.address1 = adresse1;
        this.address2 = adresse2;
        this.cp = CodePostal;
        this.city = ville;
        this.gcoord = LocalisationUtil.result2GCoord(result);
        setGeocode(gcoord != null);
        if (isGeocode()) {
            this.location = new Location(this.gcoord.geometry.location.lat,this.gcoord.geometry.location.lng);
        } else {
            this.location = new Location("","");
        }

        this.result = result;

    }

    public GAddress(String addressSaisie, String result) {
        super();
        setTypeAddress(TypeAddress.RECHERCHE);
        this.addressSaisie = addressSaisie;
        this.gcoord = LocalisationUtil.result2GCoord(result);
        setGeocode(gcoord != null);
        if (isGeocode()) {
            this.location = new Location(this.gcoord.geometry.location.lat,this.gcoord.geometry.location.lng);
        } else {
            this.location = new Location("","");
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public GoogleGeoCodeResponse getGcoord() {
        return gcoord;
    }

    public void setGcoord(GoogleGeoCodeResponse gcoord) {
        this.gcoord = gcoord;
        setGeocode(gcoord != null);
    }

    public String getAddressSaisie() {
        return addressSaisie;
    }

    public void setAddressSaisie(String addressSaisie) {
        this.addressSaisie = addressSaisie;
    }


    public TypeAddress getTypeAddress() {
        return typeAddress;
    }

    public void setTypeAddress(TypeAddress typeAddress) {
        this.typeAddress = typeAddress;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    /**
     * l'adresse est-elle géocodée ?
     * @return
     */
    public boolean isGeocode() {
        return geocode;
    }

    private void setGeocode(boolean geocode) {
        this.geocode = geocode;
    }


}
