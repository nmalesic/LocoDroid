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

    // adresse saisie dans le profil
    private int id;
    private String adresse1;
    private String adresse2;
    private String CodePostal;
    private String ville;
    private String result;

    // adresse saisie en zone de recherche
    private String addressSaisie;

    private Location location;

    // addresse google maps
    private GoogleGeoCodeResponse gcoord;


    public GAddress(String adresse1, String adresse2, String CodePostal, String ville, String result) {
        super();
        setTypeAddress2D(TypeAddress.PROFIL);
        this.adresse1 = adresse1;
        this.adresse2 = adresse2;
        this.CodePostal = CodePostal;
        this.ville = ville;
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
        setTypeAddress2D(TypeAddress.RECHERCHE);
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

    public String getAdresse1() {
        return adresse1;
    }

    public void setAdresse1(String adresse1) {
        this.adresse1 = adresse1;
    }

    public String getAdresse2() {
        return adresse2;
    }

    public void setAdresse2(String adresse2) {
        this.adresse2 = adresse2;
    }

    public String getCodePostal() {
        return CodePostal;
    }

    public void setCodePostal(String codePostal) {
        this.CodePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
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


    public TypeAddress getTypeAddress2D() {
        return typeAddress;
    }

    public void setTypeAddress2D(TypeAddress typeAddress2D) {
        this.typeAddress = typeAddress2D;
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
