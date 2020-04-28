package com.example.user.eatmeapplication;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.client.Firebase;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by User on 11/15/2016.
 */
public class UserActivity extends ActionBarActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener {
    private LinearLayout Prof_section;
   // private LinearLayout userCheckBox;
    private Button SignOut;
    private SignInButton SignIn;
    private TextView Name, Email;
    private ImageView Prof_Pic;
    private GoogleApiClient googleApiClient;
    private static final int REQ_CODE = 9001;
    String state;
    private MultiSelection my_dialog;
    private Button diatryPrefences;
    private Boolean Done=false;
    /****************for firebase*****************/
    private Firebase firebase;
    int flagGetDataFB=0;
    /****************for firebase*****************/
    /****************for dialog*****************/
    ArrayList<String>  list;
    String[] items;
    boolean[] checkedItems;
    /****************for dialog*****************/
    /****************for dialog*****************/
    @SuppressLint("ValidFragment")
     public  class MultiSelection extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder bulider = new AlertDialog.Builder(getActivity());
            bulider.setTitle("Choose your dietary preferences").setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                    if (isChecked) {
                        list.add(items[which]);
                    } else if(list.contains(items[which])){
                        list.remove(items[which]);
                    }
                }
            }).setPositiveButton("Confirm", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    for(int i=0;i<checkedItems.length;i++){
                        String path=Email.getText().toString().split("@")[0];
                        switch (i){
                            case 0:
                                path = path +"/userVegan";
                                break;
                            case 1:
                                path = path +"/userVegetarian";
                                break;
                            case 2:
                                path = path +"/userHalal";
                                break;
                            case 3:
                                path = path +"/userKashir";
                                break;
                            case 4:
                                path = path +"/userEggs";
                                break;
                            case 5:
                                path = path +"/userFish";
                                break;
                            case 6:
                                path = path +"/userGluten";
                                break;
                            case 7:
                                path = path +"/userNuts";
                                break;
                            case 8:
                                path = path +"/userLactose";
                                break;
                            case 9:
                                path = path +"/userSoy";
                                break;
                            default:
                              return;
                        }
                        if(checkedItems[i]==true){
                            firebase.child("users").child(path).setValue(true);
                        }else{
                            firebase.child("users").child(path).setValue(false);
                        }
                    }
                }
            });
            bulider.setNegativeButton("Dismiss",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            bulider.setNeutralButton("Clear all",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    for(int i=0;i<checkedItems.length;i++){
                        checkedItems[i]=false;

                    }
                    for(int i=0;i<checkedItems.length;i++) {
                        String path = Email.getText().toString().split("@")[0];
                        switch (i) {
                            case 0:
                                path = path + "/userVegan";
                                break;
                            case 1:
                                path = path + "/userVegetarian";
                                break;
                            case 2:
                                path = path + "/userHalal";
                                break;
                            case 3:
                                path = path + "/userKashir";
                                break;
                            case 4:
                                path = path + "/userEggs";
                                break;
                            case 5:
                                path = path + "/userFish";
                                break;
                            case 6:
                                path = path + "/userGluten";
                                break;
                            case 7:
                                path = path + "/userNuts";
                                break;
                            case 8:
                                path = path + "/userLactose";
                                break;
                            case 9:
                                path = path + "/userSoy";
                                break;
                            default:
                                return;
                        }
                        firebase.child("users").child(path).setValue(false);
                    }
                    list.clear();
                }
            });
            return bulider.create();
        }

    }
    /****************for dialog*****************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_user);
        Prof_section = (LinearLayout) findViewById(R.id.prof_section);
        SignOut = (Button) findViewById(R.id.bn_logout);
        SignIn = (SignInButton) findViewById(R.id.bn_login);
        Name = (TextView) findViewById(R.id.name);
        Email = (TextView) findViewById(R.id.email);
        Prof_Pic = (ImageView) findViewById(R.id.prof_pic);
        SignIn.setOnClickListener(this);
        SignOut.setOnClickListener(this);
        SignIn.setVisibility(View.GONE);
        Prof_section.setVisibility(View.GONE);
        /*****************for dialog*******************/
        diatryPrefences=(Button) findViewById(R.id.DiatryPrefences);
        diatryPrefences.setOnClickListener(this);
        items=getResources().getStringArray(R.array.diatry_choices);
        list = new ArrayList<>();
        checkedItems=new boolean[items.length];
        /****************for firebase*****************/
        firebase = new Firebase("https://eatme-162419.firebaseio.com/");
        /********************************************/
        SharedPreferences sharedPreferences=UserActivity.this.getSharedPreferences(getString(R.string.PREF_FILE),MODE_PRIVATE);
        state=sharedPreferences.getString(getString(R.string.USER_STATE),"NotRegistered");
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();
        if (!(state.equals("NotRegistered"))) {
            SignIn();
        } else {
            SignIn.setVisibility(View.VISIBLE);
            flagGetDataFB=1;
        }
     }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bn_login:
                SignIn();
                //state=Email.getText().toString(); saving email in sign in
               // saveInfo(v);
                break;
            case R.id.bn_logout:
                SignOut();
                state= "NotRegistered";
                clearInfo(v);
                break;
            case R.id.DiatryPrefences:
                String path_1="https://eatme-162419.firebaseio.com/" +"users/"+Email.getText().toString().split("@")[0]+".json";
                checkBoxFromFB(path_1);
                break;
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void SignIn() {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, REQ_CODE);
    }

    private void SignOut() {

        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                UpdateUI(false);
            }
        });

    }

    private void handleResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            String name = account.getDisplayName();
            String email = account.getEmail();
            state=email; //save email for shared prefences
            saveInfo(null);
            if (account.getPhotoUrl() != null) {
                String img_url = account.getPhotoUrl().toString();
                Glide.with(this).load(img_url).into(Prof_Pic);
            } else {
                Prof_Pic.setImageResource(R.drawable.photo);
            }

            Name.setText(name);
            Email.setText(email);
            UpdateUI(true);


        } else {
            UpdateUI(false);
        }
    }

    private void UpdateUI(boolean isLogin) {
        System.out.println(isLogin);
        if (isLogin) {
            Prof_section.setVisibility(View.VISIBLE);
            SignIn.setVisibility(View.GONE);
            if(flagGetDataFB==1){
                String path_2="https://eatme-162419.firebaseio.com/" +"users/"+Email.getText().toString().split("@")[0]+".json";
                checkBoxFromFB(path_2);
            }
        } else {
            Prof_section.setVisibility(View.GONE);
            SignIn.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }
    }

    public void saveInfo(View view) {
        SharedPreferences sharedPreferences=UserActivity.this.getSharedPreferences(getString(R.string.PREF_FILE),MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(getString(R.string.USER_STATE), state);
        editor.commit(); //important, otherwise it wouldn't save.
    }

    public void clearInfo(View view) {
        SharedPreferences sharedPreferences=UserActivity.this.getSharedPreferences(getString(R.string.PREF_FILE),MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.commit(); //important, otherwise it wouldn't save.
    }

    void checkBoxFromFB(String path){
        QueryProductFromFB http_rq= new QueryProductFromFB();
        String stringToJsonObject= null;
        try {
            stringToJsonObject = http_rq.execute(path).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(stringToJsonObject);
        if(!(stringToJsonObject.equals("null"))){
            JsonToObjectFB productReader = new JsonToObjectFB();
            User user = productReader.getUser(stringToJsonObject);
            checkedItems[0]= user.isUserVegan();
            checkedItems[1]=user.isUserVegetarian();
            checkedItems[2]= user.isUserHalal();
            checkedItems[3]=user.isUserKashir();
            checkedItems[4]=user.isUserEggs();
            checkedItems[5]= user.isUserFish();
            checkedItems[6]=user.isUserGluten();
            checkedItems[7]=user.isUserNuts();
            checkedItems[8]=user.isUserLactose();
            checkedItems[9]= user.isUserSoy();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "new user", Toast.LENGTH_SHORT);
            toast.show();
        }

        my_dialog= new MultiSelection();
        my_dialog.show(getFragmentManager(),"multi_demo");
    }
    @Override
    public void onBackPressed() {
        Intent i=new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }


}

