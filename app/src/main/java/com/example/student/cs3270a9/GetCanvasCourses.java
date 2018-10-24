package com.example.student.cs3270a9;

import android.os.AsyncTask;
import android.util.Log;

import com.example.student.cs3270a9.Authorization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class GetCanvasCourses extends AsyncTask<String, Integer, String> {

    private String rawJSON;

    @Override
    protected String doInBackground(String... strings) {

        try{
            URL url = new URL("https://weber.instructure.com//api/v1/courses");
            HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + Authorization.AUTH_TOKEN);

            connection.connect();

            int status = connection.getResponseCode();

            switch(status){
                case 200:
                case 201:
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                rawJSON = br.readLine();

                Log.d("test", "rawJson length " + rawJSON.length());
                Log.d("test", "rawJson first 256: " + rawJSON.substring(0, 256));
                break;
            }


        } catch (MalformedURLException e) {
            Log.d("test", "Bad URL Unable to connect");
        } catch (IOException e) {
            Log.d("test", "Unable to connect to internet, i/o");
        }

        return rawJSON;
    }

    @Override
    protected void onPostExecute(String s) {



        super.onPostExecute(s);
    }
}
