package com.example.student.cs3270a9.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

// This is the course database access object (DAO), similar to an interface
@Dao
public interface CourseDAO {

    // get all courses
    @Query("select * from course")
    LiveData<List<Course>> getAll();

    // get a single course by primary key
    @Query("select * from course where _id = :id")
    List<Course> loadById(int id);

    // edit selected course
    @Update
    void editCourse(Course course);

    //delete selected course
    @Delete
    void delete(Course course);

    // add one or more courses
    @Insert
    void insert(Course... courses);
}
