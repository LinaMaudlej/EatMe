package com.example.user.eatmeapplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

/**
 * Created by User on 4/5/2017.
 */

public class JsonToObjectFB {
    private Product myProduct;
    private User user;
    public JsonToObjectFB() {
         myProduct = new Product();
         user = new User();
    }
    public Product getProduct(String stringToJsonObject) {
        jsonCreateProduct(stringToJsonObject);
        return myProduct;
    }
    public User getUser(String stringToJsonObject){
        jsonCreateUser(stringToJsonObject);
        return user;
    }
    private void jsonCreateUser(String stringToJsonObject) {
        JSONObject jo_inside = null;
        try {
            jo_inside = new JSONObject(stringToJsonObject);
            user.setUserVegan(Boolean.parseBoolean(jo_inside.getString("userVegan")));
            user.setUserVegetarian(Boolean.parseBoolean(jo_inside.getString("userVegetarian")));
            user.setUserHalal(Boolean.parseBoolean(jo_inside.getString("userHalal")));
            user.setUserKashir(Boolean.parseBoolean(jo_inside.getString("userKashir")));
            user.setUserEggs(Boolean.parseBoolean(jo_inside.getString("userEggs")));
            user.setUserFish(Boolean.parseBoolean(jo_inside.getString("userFish")));
            user.setUserGluten(Boolean.parseBoolean(jo_inside.getString("userGluten")));
            user.setUserNuts(Boolean.parseBoolean(jo_inside.getString("userNuts")));
            user.setUserLactose(Boolean.parseBoolean(jo_inside.getString("userLactose")));
            user.setUserSoy(Boolean.parseBoolean(jo_inside.getString("userSoy")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void jsonCreateProduct(String stringToJsonObject) {

        try {
            JSONObject jo_inside = new JSONObject(stringToJsonObject);
            myProduct.setName(jo_inside.getString("name"));
            myProduct.setSafeFood(jo_inside.getBoolean("safeFood"));
            myProduct.setImage(jo_inside.getString("image"));
            myProduct.setRating(jo_inside.getDouble("rating"));
            myProduct.setNumUsersRated(jo_inside.getInt("rating"));
            myProduct.setCompany(jo_inside.getString("company"));
            myProduct.setDescription(jo_inside.getString("description"));
            myProduct.setVolume(jo_inside.getDouble("volume"));
            myProduct.setRecycle(jo_inside.getBoolean("recycle"));
            myProduct.setSugarFree(jo_inside.getBoolean("sugarFree"));
            myProduct.setKashir(jo_inside.getBoolean("kashir"));
            myProduct.setHalal(jo_inside.getBoolean("halal"));
            myProduct.setVegan(jo_inside.getBoolean("vegan"));
            myProduct.setVegetarian(jo_inside.getBoolean("vegetarian"));
            myProduct.setLactose(jo_inside.getBoolean("lactose"));
            myProduct.setEggs(jo_inside.getBoolean("eggs"));
            myProduct.setNuts(jo_inside.getBoolean("nuts"));
            myProduct.setFish(jo_inside.getBoolean("fish"));
            myProduct.setGluten(jo_inside.getBoolean("gluten"));
            myProduct.setSoy(jo_inside.getBoolean("soy"));
            myProduct.setCalories(jo_inside.getDouble("calories"));
            myProduct.setProteins(jo_inside.getDouble("proteins"));
            myProduct.setFats(jo_inside.getDouble("fats"));
            myProduct.setCarbohydrates(jo_inside.getDouble("carbohydrates"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}