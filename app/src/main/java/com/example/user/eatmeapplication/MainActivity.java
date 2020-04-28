package com.example.user.eatmeapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.concurrent.ExecutionException;

public class MainActivity extends ActionBarActivity  implements View.OnClickListener   {
    private ImageButton scanBtn;
    private EditText editText;
    private Button sendBtn;
    private Switch SwitchNetwork;
    String userState;
    private User user;
    private String toggle;
    public static Bus bus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences sharedPreferences=getSharedPreferences(getString(R.string.PREF_FILE),MODE_PRIVATE);
        userState=sharedPreferences.getString(getString(R.string.USER_STATE),"NotRegistered");

        if(userState.equals("NotRegistered")){
            startActivity(new Intent(this,UserActivity.class));

        }else{
            toggle=sharedPreferences.getString("Switch","On");
            System.out.println(toggle);
            user =CreateUser();
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setLogo(R.mipmap.ic_launcher);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
            setContentView(R.layout.activity_main);
            scanBtn = (ImageButton)findViewById(R.id.scanButton);
            sendBtn = (Button) findViewById(R.id.send_button);
            editText=(EditText) findViewById(R.id.editText);
            SwitchNetwork=(Switch)findViewById(R.id.switchNetwork);
            SwitchNetwork.setOnClickListener((View.OnClickListener) this);
            if(toggle.equals("On")){
                SwitchNetwork.setChecked(false);
                SwitchNetwork.performClick();
            }else {
                System.out.println(toggle + "im at off status");
                SwitchNetwork.setChecked(true);
                SwitchNetwork.performClick();
            }
            scanBtn.setOnClickListener((View.OnClickListener) this);
            sendBtn.setOnClickListener((View.OnClickListener) this);

        }
        bus = new Bus(ThreadEnforcer.MAIN);
        bus.register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences=getSharedPreferences(getString(R.string.PREF_FILE),MODE_PRIVATE);
        userState=sharedPreferences.getString(getString(R.string.USER_STATE),"NotRegistered");
        if(userState.equals("NotRegistered")){
            startActivity(new Intent(this,UserActivity.class));
        }else{
            toggle=sharedPreferences.getString("Switch","On");
            user =CreateUser();
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setLogo(R.mipmap.ic_launcher);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
            setContentView(R.layout.activity_main);
            scanBtn = (ImageButton)findViewById(R.id.scanButton);
            sendBtn = (Button) findViewById(R.id.send_button);
            editText=(EditText) findViewById(R.id.editText);
            SwitchNetwork=(Switch)findViewById(R.id.switchNetwork);
            SwitchNetwork.setOnClickListener((View.OnClickListener) this);
            scanBtn.setOnClickListener((View.OnClickListener) this);
            sendBtn.setOnClickListener((View.OnClickListener) this);
            if(toggle.equals("On")){
                SwitchNetwork.setChecked(false);
                SwitchNetwork.performClick();
            }else{
                System.out.println(toggle+"im at off status");
                SwitchNetwork.setChecked(true);
                SwitchNetwork.performClick();

            }
        }
    }

    private User CreateUser() {
        String path="https://eatme-162419.firebaseio.com/" +"users/"+userState.split("@")[0]+".json";
        QueryProductFromFB http_rq= new QueryProductFromFB();
        String stringToJsonObject= null;
        try {
            stringToJsonObject = http_rq.execute(path).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if(!(stringToJsonObject.equals("null"))) {
            JsonToObjectFB reader = new JsonToObjectFB();
            return reader.getUser(stringToJsonObject);
        }
        return null;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_bar,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()) {
            case R.id.user_id:
                startActivity(new Intent(this,UserActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.scanButton) {
            IntentIntegrator integrator = new IntentIntegrator(this);
            integrator.setOrientationLocked(false);
            integrator.initiateScan();
        }
        if(v.getId()==R.id.send_button) {
            try {
                if(SwitchNetwork.isChecked()){
                    readProduct( editText.getText().toString());
                }else{
                    readProduct_local(editText.getText().toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(v.getId()==R.id.switchNetwork){
            SharedPreferences sharedPreferences=getSharedPreferences(getString(R.string.PREF_FILE),MODE_PRIVATE);
            if(SwitchNetwork.isChecked()){
                System.out.println("1111111111111111111111111111111111111111111111");
                SwitchNetwork.setText("networkON");
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("Switch", "On");
                editor.commit(); //important, otherwise it wouldn't save.


            }else{
                SwitchNetwork.setText("networkOFF");
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("Switch", "Off");
                editor.commit(); //important, otherwise it wouldn't save
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) { //retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        String scanContent = scanningResult.getContents();
        if (scanContent != null) {
            try {
                if(SwitchNetwork.isChecked()){
                    readProduct( scanContent);
                }else{
                    readProduct_local(scanContent);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }



        /*read data : http request using JsonToObjectFB class*/
    public void readProduct(String scanContent ) throws IOException, ExecutionException, InterruptedException {
        if (scanContent != null && !scanContent.equals("")) {
            String path="https://eatme-162419.firebaseio.com/products/"+scanContent+".json";
            QueryProductFromFB http_rq= new QueryProductFromFB();
            String stringToJsonObject=http_rq.execute(path).get();
            if(!(stringToJsonObject.equals("null"))){
                JsonToObjectFB productReader = new JsonToObjectFB();
                Product product = productReader.getProduct(stringToJsonObject);
                Intent intentProdcutActivity = new Intent(this, ProductActivity.class);
                intentProdcutActivity.putExtra("PrdouctClass", (Serializable) product);
                intentProdcutActivity.putExtra("UserClass", (Serializable) user);
                startActivity(intentProdcutActivity);
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "product has not found ", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }
        }else{
            Toast toast = Toast.makeText(getApplicationContext(), "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

        /*local json file : using JsonToProductLocal class*/
    public void readProduct_local(String scanContent ) throws IOException {
        if (scanContent != null && !scanContent.equals("")) {
            InputStream inputStream = getApplicationContext().getAssets().open("json_products.json");
            JsonToProductLocal productReader = new JsonToProductLocal(scanContent, inputStream);
            Product product = productReader.getProduct();
            if (product == null) {
                Toast toast = Toast.makeText(getApplicationContext(), "product has not found ", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }
            Intent intentProdcutActivity = new Intent(this, ProductActivity.class);
            intentProdcutActivity.putExtra("PrdouctClass", (Serializable) product);
            User user_=new User(true,true,true,true,true,true,true,true,true,true);
            intentProdcutActivity.putExtra("UserClass", (Serializable) user_);

            startActivity(intentProdcutActivity);
        }else{
            Toast toast = Toast.makeText(getApplicationContext(), "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
