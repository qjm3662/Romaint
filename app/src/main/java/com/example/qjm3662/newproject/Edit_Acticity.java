package com.example.qjm3662.newproject;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.qjm3662.newproject.Data.Story;
import com.example.qjm3662.newproject.Data.StoryDB;

public class Edit_Acticity extends Activity implements View.OnClickListener {

    private ImageView img_isPublic;
    private int a = 0;
    private TextView cancel;
    private TextView save;
    private StoryDB storyDB;
    private SQLiteDatabase dbRead;
    private SQLiteDatabase dbWrite;
    private EditText et_title;
    private EditText et_input;
    public static final int REQUEST_CODE_SAVE = 0;
    public static final String EDIT_TITLE = "edit_title";
    public static final String EDIT_CONTENT = "edit_content";
    private boolean JUDGE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__acticity);

        storyDB = new StoryDB(this);
        dbRead = storyDB.getReadableDatabase();
        dbWrite = storyDB.getWritableDatabase();



        TextClock tc = (TextClock) findViewById(R.id.tc);
        img_isPublic = (ImageView) findViewById(R.id.img_isPublic);
        cancel = (TextView) findViewById(R.id.cancel);
        save = (TextView) findViewById(R.id.save);
        et_input = (EditText) findViewById(R.id.et_input);
        et_title = (EditText) findViewById(R.id.et_tile);

        img_isPublic.setOnClickListener(this);
        cancel.setOnClickListener(this);
        save.setOnClickListener(this);

        Intent intent = getIntent();
        JUDGE = intent.getBooleanExtra("JUDGE",false);
        if(JUDGE){
            et_title.setText(intent.getStringExtra(EDIT_TITLE));
            et_input.setText(intent.getStringExtra(EDIT_CONTENT));
            System.out.println("233");
        }
    }

    @Override
    protected void onDestroy() {
        dbWrite.close();
        dbRead.close();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_isPublic:
                change();
                break;
            case R.id.cancel:
                finish();
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                break;
            case R.id.save:
                save_story();
                startActivityForResult(new Intent(Edit_Acticity.this,MainActivity.class),REQUEST_CODE_SAVE);
                finish();
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                break;
        }
    }

    private void change() {
        if(a == 0){
            img_isPublic.setImageResource(R.drawable.img_public);
            a = 1;
        }else{
            img_isPublic.setImageResource(R.drawable.img_privacy);
            a = 0;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case StoryFragment.REQUEST_CODE_READ:
                et_title.setText(data.getStringExtra(EDIT_TITLE));
                et_input.setText(data.getStringExtra(EDIT_CONTENT));
                System.out.println("233");
            break;
        }
    }

    private void save_story(){
        ContentValues cv = new ContentValues();
        cv.put(StoryDB.COLUMN_NAME_TITLE,et_title.getText().toString());
        cv.put(StoryDB.COLUMN_NAME_CONTENT, et_input.getText().toString());
        cv.put(StoryDB.COLUMN_NAME_PUBLIC_ENABLE,a == 0);
        if (JUDGE){
            dbWrite.update(StoryDB.TABLE_NAME_STORY, cv, StoryDB.COLUMN_NAME_ID + "=?", new String[]{String.valueOf(getIntent().getIntExtra("ID", 1))
            });
            System.out.println("update success");
        }else{
            dbWrite.insert(StoryDB.TABLE_NAME_STORY, null, cv);
            System.out.println("create success");
        }

    }
}
