package com.bl.locodroid.localisation;

/**
 * coords latitude and longitude in google maps format
 * corresponding
 * to an adress typed in a search area
 * or an adress typed in the user profil form
 * Created by nmalesic on 05/02/2016.
 */
public class LocoAddress {

    // profile address
    private int id;
    private String address1;
    private String address2;
    private String codePostal;
    private String city;


    // address used in search
    private String onelineAddress;

    // Coords
    private Location location;



    public LocoAddress(String adresse1, String adresse2, String codePostal, String city, Location location) {
        super();
        this.address1 = adresse1;
        this.address2 = adresse2;
        this.codePostal = codePostal;
        this.city = city;
        this.location = location;

    }

    public LocoAddress(String onelineAddress, Location location) {
        super();
        this.setOnelineAddress(onelineAddress);
        this.location = location;

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

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOnelineAddress() {
        return onelineAddress;
    }

    public void setOnelineAddress(String onelineAddress) {
        this.onelineAddress = onelineAddress;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Is adresse geocoded ?
     * @return
     */
    public boolean isGeocode() {
        return (this.location != null);
    }

    /**
     * Is adresse Multi Line ?
     * @return
     */
    public boolean isMultiLineAddress() {
        return (this.address1 != null && this.address2 != null && this.codePostal != null && this.city != null );
    }

    /**
     * Is adresse Oneline ?
     * @return
     */
    public boolean isOnelineAddress() {
        return (this.getOnelineAddress() != null);
    }


}
