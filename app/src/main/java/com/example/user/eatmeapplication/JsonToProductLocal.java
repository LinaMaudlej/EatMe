package com.example.user.eatmeapplication;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

public class JsonToProductLocal {
    public Product product;
    private boolean found=false;
    private String barcode;
    public InputStream is;


    public JsonToProductLocal(String barcode, InputStream is) {
        this.barcode = barcode;
        this.is=is;
        this.product=jsonCreateObject();
    }

    public Product getProduct() {
        if(found==true) {
            return product;
        } else {
            return null;
        }
    }
    /***********private********************/
    public String loadJSONFromAsset() {

        String json = null;
        try {
            //  InputStream is = getApplicationContext().getAssets().open("json_products.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }catch(Exception e){
            e.printStackTrace();
        }
        return json;
    }
    public   Product jsonCreateObject() {
        Product myProduct= new Product();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray arr=obj.getJSONArray("products");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject jo_inside = arr.getJSONObject(i);
                if(jo_inside.getString("barcode").equals(barcode)){
                    found=true;
                    //TODO
                    myProduct.setName(jo_inside.getString("name"));
                    myProduct.setSafeFood(jo_inside.getBoolean("safeFood"));
                    myProduct.setImage(jo_inside.getString("image"));

                    myProduct.setRating(jo_inside.getDouble("rating"));
                    myProduct.setNumUsersRated(jo_inside.getInt("numUsersRated"));

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

                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return myProduct;
    }
}
