package com.example.qjm3662.newproject;

import android.app.Application;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.qjm3662.newproject.Data.Story;
import com.example.qjm3662.newproject.Data.StoryDB;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

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

    public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void onCreate() {
        super.onCreate();

        //获取数据库
        storyDB = new StoryDB(this);
        dbRead = storyDB.getReadableDatabase();
        dbWrite = storyDB.getWritableDatabase();

        App.format.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));// 中国北京时间，东八区

        getStory_from_SQLite();
    }


    private void getStory_from_SQLite() {
        Cursor c = dbRead.query(StoryDB.TABLE_NAME_STORY, null, null, null, null, null, null);
        while (c.moveToNext()) {
            Story story = new Story();
            story.setTitle(c.getString(c.getColumnIndex(StoryDB.COLUMN_NAME_TITLE)));
            story.setContent(c.getString(c.getColumnIndex(StoryDB.COLUMN_NAME_CONTENT)));
            story.setLocal_id(c.getInt(c.getColumnIndex(StoryDB.COLUMN_NAME_ID)));
            story.setCreatedAt(c.getString(c.getColumnIndex(StoryDB.COLUMN_NAME_CREATE_AT)));
            System.out.println("Item Date : ======" + story.getCreatedAt());
            StoryList.add(story);
        }
    }
}
