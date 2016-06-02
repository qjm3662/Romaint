package com.example.qjm3662.newproject;

import android.app.Application;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.qjm3662.newproject.Data.Story;
import com.example.qjm3662.newproject.Data.StoryDB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qjm3662 on 2016/5/30 0030.
 */
public class App extends Application {

    //可读数据库
    public static SQLiteDatabase dbRead;
    private StoryDB storyDB;
    public static SQLiteDatabase dbWrite;

    //存储本地故事对象列表
    public static List<Story> StoryList = new ArrayList<Story>();
    @Override
    public void onCreate() {
        super.onCreate();

        //获取数据库
        storyDB = new StoryDB(this);
        dbRead = storyDB.getReadableDatabase();
        dbWrite = storyDB.getWritableDatabase();

        getStory_from_SQLite();
    }



    private void getStory_from_SQLite() {
        Cursor c = dbRead.query(StoryDB.TABLE_NAME_STORY,null,null,null,null,null,null);
        while(c.moveToNext()) {
            Story story = new Story();
            story.setTitle(c.getString(c.getColumnIndex(StoryDB.COLUMN_NAME_TITLE)));
            story.setContent(c.getString(c.getColumnIndex(StoryDB.COLUMN_NAME_CONTENT)));
            story.setLocal_id(c.getInt(c.getColumnIndex(StoryDB.COLUMN_NAME_ID)));

            System.out.println(c.getString(c.getColumnIndex(StoryDB.COLUMN_NAME_TITLE)));
            StoryList.add(story);
        }
    }
}
