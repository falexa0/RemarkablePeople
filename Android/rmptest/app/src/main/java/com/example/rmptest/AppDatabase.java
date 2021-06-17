package com.example.rmptest;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserEntity.class, PostsEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String dbName = "rmp";
    private static AppDatabase INSTANCE;

    public static synchronized AppDatabase getDataBase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, dbName).fallbackToDestructiveMigration().
                    allowMainThreadQueries().build();

        }
        return  INSTANCE;
    }

    public abstract UserDao getUserDao();
    public abstract PostsEntityDao getPostsEntityDao();



}
