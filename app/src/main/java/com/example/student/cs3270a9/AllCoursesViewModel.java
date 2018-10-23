package com.example.student.cs3270a9;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.student.cs3270a9.db.AppDatabase;
import com.example.student.cs3270a9.db.Course;

import java.util.List;

public class AllCoursesViewModel extends ViewModel {

    private LiveData<List<Course>> courseList;

    public LiveData<List<Course>> getCourseList(Context c) {

        if (courseList != null){
            return courseList;
        }

        return courseList = AppDatabase.getInstance(c).courseDAO().getAll();
    }
}
