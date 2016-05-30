package com.example.qjm3662.newproject;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.qjm3662.newproject.Data.StoryDB;

/**
 * Created by qjm3662 on 2016/5/30 0030.
 */
public class App extends Application {
    private SQLiteDatabase dbRead;
    private StoryDB storyDB;
    @Override
    public void onCreate() {
        super.onCreate();
        storyDB = new StoryDB(this);
    }
}
