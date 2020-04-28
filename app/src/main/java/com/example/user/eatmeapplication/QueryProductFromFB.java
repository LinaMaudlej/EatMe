package com.example.user.eatmeapplication;

import android.os.AsyncTask;
import android.os.StrictMode;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by User on 4/5/2017.
 */

public class QueryProductFromFB extends AsyncTask<String,String,String> {
    int fileLength=0;
    InputStream in;
    String json;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String path=params[0];
        try {
            URL url=new URL(path);
            try {
                URLConnection urlConnection=url.openConnection();
                urlConnection.connect();
                fileLength=urlConnection.getContentLength();
                in = new BufferedInputStream(urlConnection.getInputStream());
                int size=fileLength;
                System.out.println("size is"+size);
                byte[] buffer = new byte[size];
                in.read(buffer);
                json = new String(buffer, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return json;
    }


    @Override
    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);

    }

}


