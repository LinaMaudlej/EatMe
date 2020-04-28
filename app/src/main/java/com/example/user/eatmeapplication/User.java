package com.example.user.eatmeapplication;

import java.io.Serializable;

/**
 * Created by User on 4/4/2017.
 */

public class User implements Serializable {

    private boolean userKashir = true;
    private boolean userHalal = true;
    private boolean userVegan = true;
    private boolean userVegetarian = true;
    private boolean userSoy = true;
    private boolean userEggs = true;
    private boolean userFish = true;
    private boolean userGluten = true;
    private boolean userNuts = true;
    private boolean userLactose = true;

    public User(boolean userKashir, boolean userHalal, boolean userVegan, boolean userVegetarian, boolean userSoy, boolean userEggs, boolean userFish, boolean userGluten, boolean userNuts, boolean userLactose) {
        this.userKashir = userKashir;
        this.userHalal = userHalal;
        this.userVegan = userVegan;
        this.userVegetarian = userVegetarian;
        this.userSoy = userSoy;
        this.userEggs = userEggs;
        this.userFish = userFish;
        this.userGluten = userGluten;
        this.userNuts = userNuts;
        this.userLactose = userLactose;
    }
    public User(){

    }


    public boolean isUserVegan() {
        return userVegan;
    }

    public void setUserVegan(boolean userVegan) {
        this.userVegan = userVegan;
    }

    public boolean isUserKashir() {
        return userKashir;
    }

    public void setUserKashir(boolean userKashir) {
        this.userKashir = userKashir;
    }

    public boolean isUserHalal() {
        return userHalal;
    }

    public void setUserHalal(boolean userHalal) {
        this.userHalal = userHalal;
    }

    public boolean isUserVegetarian() {
        return userVegetarian;
    }

    public void setUserVegetarian(boolean userVegetarian) {
        this.userVegetarian = userVegetarian;
    }

    public boolean isUserSoy() {
        return userSoy;
    }

    public void setUserSoy(boolean userSoy) {
        this.userSoy = userSoy;
    }

    public boolean isUserEggs() {
        return userEggs;
    }

    public void setUserEggs(boolean userEggs) {
        this.userEggs = userEggs;
    }

    public boolean isUserFish() {
        return userFish;
    }

    public void setUserFish(boolean userFish) {
        this.userFish = userFish;
    }

    public boolean isUserGluten() {
        return userGluten;
    }

    public void setUserGluten(boolean userGluten) {
        this.userGluten = userGluten;
    }

    public boolean isUserNuts() {
        return userNuts;
    }

    public void setUserNuts(boolean userNuts) {
        this.userNuts = userNuts;
    }

    public boolean isUserLactose() {
        return userLactose;
    }

    public void setUserLactose(boolean userLactose) {
        this.userLactose = userLactose;
    }



}
