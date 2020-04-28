package com.example.user.eatmeapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by User on 12/5/2016.
 */

public class ProductActivity  extends ActionBarActivity {

    private ImageView imageProduct;
    private RatingBar ratingBar;
    private ImageButton sendRating;
    private View diatryLaws;
    private View allergies;
    private TextView productName;
    private ImageView productKashir;
    private ImageView productVegan;
    private ImageView productHalal;
    private ImageView productVegetarian;
    private ImageView productRecycle;
    private ImageView productSugarFree;
    private ImageView productSoy;
    private ImageView productEggs;
    private ImageView productFish;
    private ImageView productGluten;
    private ImageView productNuts;
    private ImageView productLactose;
    private User user;
    private boolean userKashir;
    private boolean userHalal;
    private boolean userVegan;
    private boolean userVegetarian;
    private boolean userSoy;
    private boolean userEggs;
    private boolean userFish;
    private boolean userGluten;
    private boolean userNuts;
    private boolean userLactose;

    private int ColorProduct = Color.RED;
    private Button graphButoon;
    private Product product;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_product);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        imageProduct = (ImageView) findViewById(R.id.imageProduct);
        //ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        //sendRating = (ImageButton) findViewById(R.id.sendButton);
        productName = (TextView) findViewById(R.id.productName);
        productRecycle = (ImageView) findViewById(R.id.productRecycle);
        productKashir = (ImageView) findViewById(R.id.prodcutKashir);
        productVegan = (ImageView) findViewById(R.id.prodcutVegan);
        productHalal = (ImageView) findViewById(R.id.prodcutHalal);
        productVegetarian = (ImageView) findViewById(R.id.prodcutVegetarian);
        diatryLaws = (View) findViewById(R.id.diatryLaws);
        allergies = (View) findViewById(R.id.allergies);
        productSugarFree = (ImageView) findViewById(R.id.sugarFree);
        productSoy = (ImageView) findViewById(R.id.productSoy);
        productEggs = (ImageView) findViewById(R.id.productEggs);
        productFish = (ImageView) findViewById(R.id.productFish);
        productGluten = (ImageView) findViewById(R.id.productGluten);
        productNuts = (ImageView) findViewById(R.id.productNuts);
        productLactose = (ImageView) findViewById(R.id.productLactose);

        Intent i = getIntent();
        product = (Product) i.getSerializableExtra("PrdouctClass");
        user = (User) i.getSerializableExtra("UserClass");
        userKashir=user.isUserKashir();
        userHalal=user.isUserHalal();
        userVegan=user.isUserVegan();
        userVegetarian=user.isUserVegetarian();
        userSoy=user.isUserSoy();
        userEggs=user.isUserEggs();
        userFish=user.isUserFish();
        userGluten=user.isUserGluten();
        userNuts=user.isUserNuts();
        userLactose=user.isUserLactose();
//by user choice
        if (!userVegan) {
            productVegan.setVisibility(View.GONE);
        }
        if (!userVegetarian) {
            productVegetarian.setVisibility(View.GONE);
        }
        if (!userHalal) {
            productHalal.setVisibility(View.GONE);
        }
        if (!userKashir) {
            productKashir.setVisibility(View.GONE);
        }
        productSoy.setVisibility(View.GONE);
        productEggs.setVisibility(View.GONE);
        productNuts.setVisibility(View.GONE);
        productGluten.setVisibility(View.GONE);
        productLactose.setVisibility(View.GONE);
        productFish.setVisibility(View.GONE);
        productSugarFree.setVisibility(View.GONE);
        productRecycle.setVisibility(View.GONE);
//give me the product to show the details

        diatryLawsView();
        allergiesView();
       // onButtonClickListener();

        /***************half pi*****************/
        ListView lv= (ListView) findViewById(R.id.listGraph);
        HalfPieChart pie =new HalfPieChart(product.getCalories(),9*product.getFats(),4*product.getProteins(),4*product.getCarbohydrates());
        pie.createHalf(lv,getApplicationContext());
        /***************half pi*****************/

        productName.setText(product.getName());
        if (product.isRecycle() == true) {
            productRecycle.setImageResource(R.drawable.recycle);
            productRecycle.setVisibility(View.VISIBLE);
        }
        if (product.isSugarFree() == true) {
            productSugarFree.setImageResource(R.drawable.sugarfree);
            productSugarFree.setVisibility(View.VISIBLE);

        }
        //diet setction
        if (product.isVegan() == true) { //add user choice
            productVegan.setImageResource(R.drawable.vegan);
        } else {
            productVegan.setImageResource(R.drawable.notvegan);
        }
        if (product.isVegetarian() == true) { //add user choice
            productVegetarian.setImageResource(R.drawable.vegetarian);
        } else {
            productVegetarian.setImageResource(R.drawable.notvegetarian);
        }
        if (product.isHalal() == true) { //add user choice
            productHalal.setImageResource(R.drawable.halal);
        } else {
            productHalal.setImageResource(R.drawable.nothalal);
        }
        if (product.isKashir() == true) { //add user choice
            productKashir.setImageResource(R.drawable.kashir);
        } else {
            productKashir.setImageResource(R.drawable.notkashir);
        }
        if (product.isSoy() == true && userSoy == true) {
            productSoy.setVisibility(View.VISIBLE);
            productSoy.setImageResource(R.drawable.soy);
        }
        if (product.isEggs() == true && userEggs == true) {
            productEggs.setVisibility(View.VISIBLE);
            productEggs.setImageResource(R.drawable.eggs);
        }
        if (product.isGluten() == true && userGluten == true) {
            productGluten.setVisibility(View.VISIBLE);
            productGluten.setImageResource(R.drawable.gluten);
        }
        if (product.isLactose() == true && userLactose == true) {
            productLactose.setVisibility(View.VISIBLE);
            productLactose.setImageResource(R.drawable.lactose);
        }
        if (product.isFish() == true && userFish == true) {
            productFish.setVisibility(View.VISIBLE);
            productFish.setImageResource(R.drawable.fish);
        }
        if (product.isNuts() == true && userNuts == true) {
            productNuts.setVisibility(View.VISIBLE);
            productNuts.setImageResource(R.drawable.nuts);
        }
        ColorProduct = checkGreenColorProduct();
        try {
            imageProduct.setImageBitmap(getRoundedShape(drawableFromUrl(product.getImage()), ColorProduct));
        } catch (IOException e) {
            Resources res = getResources();
            int id = R.drawable.unavailableimge;
            imageProduct.setImageBitmap(getRoundedShape(BitmapFactory.decodeResource(res, id), ColorProduct));
            Toast.makeText(ProductActivity.this,
                    String.valueOf("unavailable image.Check your Internet Connection!"),
                    Toast.LENGTH_SHORT).show();
        }
        if(!product.isSafeFood()){
            new AlertDialog.Builder(this)
                    .setTitle("Alert!!!!!")
                    .setMessage("this product is not approved by the ministry of health ,do you want to continue?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                            onBackPressed();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }

//    public void listenerForRatingBar() {
//        ratingBar.setOnRatingBarChangeListener(
//                new RatingBar.OnRatingBarChangeListener() {
//                    @Override
//                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                        ratingBar.setRating(rating);
//                    }
//                }
//        );
//    }

//    public void onButtonClickListener() {
//        sendRating.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        int numUsers = product.getNumUsersRated();
//                        product.setNumUsersRated(numUsers + 1);
//                        System.out.println("num of useres is " + numUsers);
//                        System.out.println("rating is " + product.getRating());
//                        double ratingtemp = (product.getRating() * numUsers + ratingBar.getRating()) / product.getNumUsersRated();
//                        Toast.makeText(ProductActivity.this,
//                                String.valueOf("You just rated this product " + ratingBar.getRating() + " of 5"),
//                                Toast.LENGTH_SHORT).show();
//                        System.out.println("num of useres is " + product.getNumUsersRated());
//                        System.out.println("rating is " + ratingtemp);
//                        product.setRating(ratingtemp);
//                        // ratingBar.setIsIndicator(true);
//                        ratingBar.setRating((float) ratingtemp);
//                    }
//                }
//        );
//    }

    private void diatryLawsView() {
        if (!userKashir && !userVegan && !userVegetarian && !userHalal) {
            diatryLaws.setVisibility(View.GONE);
        }
    }

    private void allergiesView() {
        if (userNuts && product.isNuts() || userLactose && product.isLactose() || userEggs && product.isEggs() ||
                userSoy && product.isSoy() || userFish && product.isFish() || userGluten && product.isGluten()) {
            allergies.setVisibility(View.VISIBLE);
            return;
        }
        allergies.setVisibility(View.GONE);
    }

    /*
    returns the color circle of product, is it is green, then it is ok, else it is not.
     */
    private int checkGreenColorProduct() {
        if (product == null) {
            return -1;
        } else {
            if (!product.isSafeFood()) {
                return Color.RED;
            }
            if (userHalal && !product.isHalal() || userKashir && !product.isKashir() ||
                    userVegetarian && !product.isVegetarian() || userVegan && !product.isVegan()) {
                return Color.RED;
            }
            if (userSoy && product.isSoy() || userFish && product.isFish() ||
                    userEggs && product.isEggs() || userGluten && product.isGluten() ||
                    userLactose && product.isLactose() || userNuts && product.isNuts()) {
                return Color.RED;
            }
            return Color.GREEN;
        }
    }

    private Bitmap drawableFromUrl(String url) throws java.net.MalformedURLException, java.io.IOException {
        Bitmap x;
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestProperty("User-agent", "Mozilla/4.0");
        connection.connect();
        InputStream input = connection.getInputStream();
        x = BitmapFactory.decodeStream(input);
        return x;
    }

    public Bitmap getRoundedShape(Bitmap scaleBitmapImage, int color) {
        int targetWH = 400; //tagret width hight
        Bitmap targetBitmap = Bitmap.createBitmap(targetWH,
                targetWH, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(targetBitmap);
        Path path = new Path();
        float WH = ((float) targetWH - 1) / 2;
        path.addCircle(WH, WH,
                (Math.min(((float) targetWH), ((float) targetWH)) / 2),
                Path.Direction.CCW);
        canvas.clipPath(path);
        Bitmap sourceBitmap = scaleBitmapImage;
        canvas.drawBitmap(sourceBitmap,
                new Rect(0, 0, sourceBitmap.getWidth(),
                        sourceBitmap.getHeight()),
                new Rect(0, 0, targetWH, targetWH), null);
        Paint paint = new Paint();
        paint.setShader(null);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(color);
        paint.setStrokeWidth(10);
        canvas.drawCircle(WH, WH, 200, paint);
        return targetBitmap;
    }
}