package com.example.qjm3662.newproject.StoryView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.qjm3662.newproject.R;
import com.example.qjm3662.newproject.Tool.Tool;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private EditText tv;
    private TextView read_title;
    private String path;
    private Editable edit_text;
    private static ArrayList<String> list;
    private boolean JUDGE;
    private String content;
    private LinearLayout linearLayout;
    private String title;
    private Intent intent;
    //布局的宽度
    private int width;
    public static final String COMU_CODE_READ = "READ";

    //用来保存内容中<img>标签的index
    private static ArrayList<Integer> index_int = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        init();
        display();
    }

    public void display() {
        System.out.println("Content : "+content);
        for (int i = 0; i < index_int.size(); i++) {
            if ((i + 1) % 2 == 0) {
                path = list.get((i + 1) / 2 - 1);
                File file = new File(path);
                System.out.println("path :" + path);
                System.out.println(file.exists());
                if (file.exists()) {
                    Bitmap bm = BitmapFactory.decodeFile(path);
                    float multiple = (float) width / (float) bm.getWidth();
                    bm = Tool.resize_bitmap(bm, width - 80, multiple * bm.getHeight() - 80);
                    Tool.insertPic(bm, path,Main2Activity.this,tv);
                    System.out.println("Second");
                }
            }
            if (i == 0) {
                assert content != null;
                edit_text.append(content.substring(0, index_int.get(0)));
            } else if (i % 2 != 0 && i < index_int.size() - 1) {
                //System.out.println(i);
                edit_text.append(content.substring(index_int.get(i), index_int.get(i + 1)));
            }
        }


        //显示图片之后的文字
        assert content != null;
        int start = 0, end = 0;
        if (index_int.size() != 0) {
            start = index_int.get(index_int.size() - 1);
        }
        System.out.println("begin");
        System.out.println(content);

        System.out.println(start);
        end = content.length();
        System.out.println(end);
        edit_text.append(content.substring(start, end));
    }

    private void init() {
        tv = (EditText) findViewById(R.id.textView);
        read_title = (TextView) findViewById(R.id.read_title);
        read_title.setOnClickListener(this);

        //设置可滑动
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());
        linearLayout = (LinearLayout) findViewById(R.id.linear);
        //width = tv.getMaxWidth();

        //获取屏幕的高度和宽度
        width = getWidth();

        intent = getIntent();
        JUDGE = intent.getBooleanExtra("JUDGE", false);
        System.out.println("look : " + JUDGE);
        if (JUDGE) {
            content = intent.getStringExtra(Edit_Acticity.EDIT_CONTENT);
            title = intent.getStringExtra(Edit_Acticity.EDIT_TITLE);
            read_title.setText(title);
            getImg_Src(content);
            System.out.println("233");
            System.out.println(content);
        }

        //获得EditText的编辑器
        edit_text = tv.getEditableText();
        if (edit_text == null) {
            System.out.println("null");
        }
    }

    public static List<String> getImg_Src(String content){
        list = new ArrayList<String>();
        int index_pre = content.indexOf("<img");
        int index_next = 0;
        index_int.clear();
        while(content.indexOf("<img",index_next) != -1){

            index_pre = content.indexOf("<img",index_next);
            //图片标签索引的前值添加进index_int
            index_int.add(index_pre);

            index_next = content.indexOf("</img>",index_pre);
            //图片标签索引的后值添加进index_int
            index_int.add(index_next + 6);

            //将包含的路径放到list中
            list.add(content.substring(index_pre + 4,index_next-1));
        }
        System.out.println(list.toString());
        System.out.println(index_int.toString());
        for(int i = 0; i < index_int.size();i++){
            if(i == 0){
                System.out.println(content.substring(0,index_int.get(0)));
            }else if(i % 2 != 0 && i < index_int.size() - 1){
                System.out.println(i);
                System.out.println(content.substring(index_int.get(i),index_int.get(i + 1)));
            }
        }
        return list;
    }




    public int getWidth() {
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width_ = metric.widthPixels;     // 屏幕宽度（像素）
        return width_;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.read_title:
                intent.putExtra(COMU_CODE_READ,true);
                intent.setClass(Main2Activity.this, Edit_Acticity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                break;
        }
    }
}
