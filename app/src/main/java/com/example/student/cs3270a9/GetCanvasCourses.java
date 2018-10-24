package com.example.student.cs3270a9;

import android.os.AsyncTask;
import android.util.Log;

import com.example.student.cs3270a9.Authorization;
import com.example.student.cs3270a9.db.Course;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class GetCanvasCourses extends AsyncTask<String, Integer, String> {

    private String rawJSON;
    private OnCourseListComplete mCallBack;

    interface OnCourseListComplete{
        void processCourseList(List<Course> courses);
    }

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
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        List<Course> courses;

        try{
            courses = parseJson(result);
            if (courses != null){
                if(mCallBack != null && mCallBack instanceof OnCourseListComplete) {
                    mCallBack.processCourseList(courses);
                }else{
                    throw new Exception("Must use onCourseListComplete interface");
                }
            }
        }catch (Exception e){
            Log.d("test", e.getMessage());
        }

    }

    private List<Course> parseJson(String result) {

        GsonBuilder gsonb = new GsonBuilder();
        Gson gson = gsonb.create();
        List<Course> courses = null;

        try {
            courses = Arrays.asList(gson.fromJson(rawJSON, Course[].class));
            Log.d("test", "courses count from pasejson" +courses.size());
        }catch (Exception e){
        Log.d("test", "error at praseJson " +e.getMessage());
    }
        return courses;
    }

    public void setmCallBack(OnCourseListComplete listener) {
        mCallBack = listener;
    }
}
