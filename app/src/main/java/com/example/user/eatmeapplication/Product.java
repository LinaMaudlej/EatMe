package com.example.user.eatmeapplication;

import android.support.v7.app.ActionBarActivity;

import java.io.Serializable;

/**
 * Created by User on 11/29/2016.
 */


public class Product  implements Serializable {
    private String barcode;
    private String name;
    private String image;
    private String type;
    private String company;
    private String description;

    private double volume;
    private double rating;
    private int numUsersRated;

    private boolean recycle;
    private boolean sugarFree;
    private boolean safeFood;
    private boolean kashir;
    private boolean halal;
    private boolean vegan;
    private boolean vegetarian;
    private boolean lactose;
    private boolean eggs;
    private boolean nuts;
    private boolean fish;
    private boolean gluten;
    private boolean soy;
    private double calories;
    private double proteins;
    private double fats;
    private double carbohydrates;


    public Product(){}

//    public Product(String barcode, String name, String image, Type type, String company, String description, double volume, int review, boolean recycle, boolean sugarFree, boolean safeFood, boolean kashir, boolean halal, boolean vegan, boolean vegetarian, boolean lactose, boolean eggs, boolean nuts, boolean fish, boolean gluten, boolean soy, double calories, double proteins, double fats, double carbohydrates, double rating, int numUsersRated) {
//        this.barcode = barcode;
//        this.name = name;
//        this.image = image;
//        this.type = type;
//        this.company = company;
//        this.description = description;
//        this.volume = volume;
//        this.review = review;
//        this.recycle = recycle;
//        this.sugarFree = sugarFree;
//        this.safeFood = safeFood;
//        this.kashir = kashir;
//        this.halal = halal;
//        this.vegan = vegan;
//        this.vegetarian = vegetarian;
//        this.lactose = lactose;
//        this.eggs = eggs;
//        this.nuts = nuts;
//        this.fish = fish;
//        this.gluten = gluten;
//        this.soy = soy;
//        this.calories = calories;
//        this.proteins = proteins;
//        this.fats = fats;
//        this.carbohydrates = carbohydrates;
//        this.rating = rating;
//        this.numUsersRated = numUsersRated;
//    }


    /**
     * getters and setters
     **/
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNumUsersRated() {
        return numUsersRated;
    }

    public void setNumUsersRated(int numUsersRated) {
        this.numUsersRated = numUsersRated;
    }

    public boolean isRecycle() {
        return recycle;
    }

    public void setRecycle(boolean recycle) {
        this.recycle = recycle;
    }

    public boolean isSugarFree() {
        return sugarFree;
    }

    public void setSugarFree(boolean sugarFree) {
        this.sugarFree = sugarFree;
    }

    public boolean isSafeFood() {
        return safeFood;
    }

    public void setSafeFood(boolean safeFood) {
        this.safeFood = safeFood;
    }

    public boolean isKashir() {
        return kashir;
    }

    public void setKashir(boolean kashir) {
        this.kashir = kashir;
    }

    public boolean isHalal() {
        return halal;
    }

    public void setHalal(boolean halal) {
        this.halal = halal;
    }

    public boolean isVegan() {
        return vegan;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public boolean isLactose() {
        return lactose;
    }

    public void setLactose(boolean lactose) {
        this.lactose = lactose;
    }

    public boolean isEggs() {
        return eggs;
    }

    public void setEggs(boolean eggs) {
        this.eggs = eggs;
    }

    public boolean isNuts() {
        return nuts;
    }

    public void setNuts(boolean nuts) {
        this.nuts = nuts;
    }

    public boolean isFish() {
        return fish;
    }

    public void setFish(boolean fish) {
        this.fish = fish;
    }

    public boolean isGluten() {
        return gluten;
    }

    public void setGluten(boolean gluten) {
        this.gluten = gluten;
    }

    public boolean isSoy() {
        return soy;
    }

    public void setSoy(boolean soy) {
        this.soy = soy;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }
}
