package com.bl.locodroid.user;

import com.bl.locodroid.localisation.GAddress;

/**
 * Created by nmalesic on 05/02/2016.
 */
public class User {
    private int id;
    private String nomUtil;
    private String prenomUtil;
    private String pseudo;
    private String email;
    private String password;
    private String confirmPassword;

	/*
	 * private String adresse1; private String adresse2; private String CP;
	 * private String ville;
	 */

    private GAddress address;

    private String telephone;
    private String sexe;
    private String fumeur;

    /**
     * constructeur User
     */
    public User() {

    }

    /**
     * Constructeur User avec adresse Geocodée
     *
     * @param nomUtil
     * @param prenomUtil
     * @param pseudo
     * @param email
     * @param password
     * @param confirmPassword
     * @param address
     * @param telephone
     * @param sexe
     * @param fumeur
     */
    public User(String nomUtil, String prenomUtil, String pseudo, String email, String password, String confirmPassword,
                GAddress address, String telephone, String sexe, String fumeur) {
        super();
        this.nomUtil = nomUtil;
        this.prenomUtil = prenomUtil;
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.address = address;
        this.telephone = telephone;
        this.sexe = sexe;
        this.fumeur = fumeur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomUtil() {
        return nomUtil;
    }

    public void setNomUtil(String nomUtil) {
        this.nomUtil = nomUtil;
    }

    public String getPrenomUtil() {
        return prenomUtil;
    }

    public void setPrenomUtil(String prenomUtil) {
        this.prenomUtil = prenomUtil;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getAdresse1() {
        return this.address.getAdresse1();
    }

    public void setAdresse1(String adresse1) {
        this.address.setAdresse1(adresse1);
    }

    public String getAdresse2() {
        return this.address.getAdresse2();
    }

    public void setAdresse2(String adresse2) {
        this.address.setAdresse2(adresse2);
    }

    public String getCP() {
        return this.address.getCodePostal();
    }

    public void setCP(String CP) {
        this.address.setCodePostal(CP);
    }

    public String getVille() {
        return this.address.getVille();
    }

    public void setVille(String ville) {
        this.address.setVille(ville);
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getFumeur() {
        return fumeur;
    }

    public void setFumeur(String fumeur) {
        this.fumeur = fumeur;
    }

    public GAddress getAddress() {
        return address;
    }

    public void setAddress(GAddress address) {
        this.address = address;
    }

}
