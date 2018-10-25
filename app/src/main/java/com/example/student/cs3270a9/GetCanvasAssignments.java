package com.example.student.cs3270a9;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class GetCanvasAssignments extends AsyncTask<String, Integer, String> {

    private String rawJSON;
    private String courseID;
    private OnAssignmentComplete mCallback;

    interface OnAssignmentComplete {
        void processAssignmentList(Assignment[] assignments);
    }

    @Override
    protected String doInBackground(String... strings) {

        try{
            URL url = new URL("https://weber.instructure.com/api/v1/courses/"+courseID +"/assignments");
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
            }        } catch (MalformedURLException e) {
            Log.d("test", "Bad URL Unable to connect");
        } catch (IOException e) {
            Log.d("test", "Unable to connect to internet, i/o");
        }

        return rawJSON;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        Assignment[] assignments;
        try{
            assignments = parseJson(result);
            if (assignments != null){
                if(mCallback != null && mCallback instanceof OnAssignmentComplete) {
                    mCallback.processAssignmentList(assignments);
                }else{
                    throw new Exception("must implement OnAssignmentComplete interface");
                }
            }
        }catch (Exception e) {
            Log.d("test", e.getMessage());
        }


    }

    private Assignment[] parseJson(String result) {
        GsonBuilder gsonb = new GsonBuilder();
        Gson gson = gsonb.create();

        Assignment[] assignments = null;
        try{
            assignments = gson.fromJson(rawJSON, Assignment[].class);
            Log.d("test", "Assignment count " +assignments.length);
        }catch (Exception e){
            Log.d("test", e.getMessage());
        }

        return assignments;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public void setmCallback(OnAssignmentComplete listener){
        mCallback = listener;
    }
}
