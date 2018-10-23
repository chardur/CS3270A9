package com.example.student.cs3270a9.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

// the database for the app, this needs to be abstract and extend RoomDatabase
@Database(entities = {Course.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    //singleton pattern, using static instances
    private static AppDatabase instance;

    // get instance of database
    public static AppDatabase getInstance(Context context){
        if (instance !=null) {
            return instance;
        }

        instance = Room.databaseBuilder(context, AppDatabase.class, "course-database").build();
        return instance;
    }

    // access to the DAO
    public abstract CourseDAO courseDAO();
}
