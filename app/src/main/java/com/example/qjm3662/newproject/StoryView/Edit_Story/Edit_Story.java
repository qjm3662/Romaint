package com.example.qjm3662.newproject.StoryView.Edit_Story;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
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
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qjm3662.newproject.App;
import com.example.qjm3662.newproject.Data.Story;
import com.example.qjm3662.newproject.Data.StoryBean;
import com.example.qjm3662.newproject.Data.StoryDB;
import com.example.qjm3662.newproject.Data.User;
import com.example.qjm3662.newproject.NetWorkOperator;
import com.example.qjm3662.newproject.R;
import com.example.qjm3662.newproject.StoryView.StoryFragment;
import com.example.qjm3662.newproject.StoryView.Story_pre;
import com.example.qjm3662.newproject.Tool.Tool;
import com.example.qjm3662.newproject.myself.MyDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.util.ArrayList;

public class Edit_Story extends Activity implements View.OnClickListener {

    private ImageView img_exit;
    private ImageView img_flag;
    private ImageView img_tick;
    private ImageView img_is_public;
    private EditText et_title;
    private EditText et_input;
    private TextView tv_flag;
    private int a = 0, b = 0;

    //标记是新建还是编辑
    private boolean JUDGE;

    //判断故事的来源
    private int flag;
    public static String FLAG_WHERE_ARE_YOU_FROM = "where";
    public static int FLAG_FROM_ONLINE_ARTICLE = 1;

    private String path;

    private Intent intent;

    //储存图片路径
    private static ArrayList<String> list;
    //用来保存内容中<img>标签的index
    private static ArrayList<Integer> index_int = new ArrayList<Integer>();
    //屏幕宽度
    private int width;
    private String content;
    private Editable edit_text;
    private int position;
    public boolean judge = true;
    private Date date = null;
    private StoryBean story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__story);
        init();
        intent = getIntent();
        JUDGE = intent.getBooleanExtra("JUDGE", false);
        flag = intent.getIntExtra(FLAG_WHERE_ARE_YOU_FROM, 0);

        //获得EditText的编辑器
        edit_text = et_input.getEditableText();
        //判断是新建还是编辑，false则为新建
        if (JUDGE) {
            position = intent.getIntExtra("position", 0);
            if(flag == FLAG_FROM_ONLINE_ARTICLE){   //自己上传的文章
                story = App.Public_My_Article_StoryList.get(position);
            }else{      //自己本地的文章
                story = App.StoryList.get(position);
            }
            et_title.setText(story.getTitle());
            content = story.getContent();
            getImg_Src(story.getContent());
            a = story.getPublicEnable();
            if (a != 0) {
                img_is_public.setImageResource(R.drawable.img_public);
            } else {
                img_is_public.setImageResource(R.drawable.img_privacy);
            }
            tv_flag.setText(story.getFlags());
            display();
        } else {
            img_is_public.setVisibility(View.INVISIBLE);
            tv_flag.setVisibility(View.INVISIBLE);
        }


        if (intent.getBooleanExtra(Story_pre.COMU_CODE_READ, false)) {
            display();
            save_story();
            judge = false;
        }
    }

    private void init() {
        img_exit = (ImageView) findViewById(R.id.img_x);
        img_flag = (ImageView) findViewById(R.id.img_flag);
        img_tick = (ImageView) findViewById(R.id.img_tick);
        img_is_public = (ImageView) findViewById(R.id.img_is_public);
        et_input = (EditText) findViewById(R.id.et_input);
        et_title = (EditText) findViewById(R.id.et_tile);
        tv_flag = (TextView) findViewById(R.id.tv_flag);

        img_exit.setOnClickListener(this);
        img_flag.setOnClickListener(this);
        img_tick.setOnClickListener(this);
        img_is_public.setOnClickListener(this);
        et_input.setOnClickListener(this);
        et_title.setOnClickListener(this);
        tv_flag.setOnClickListener(this);

        //获取手机的高度和宽度
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        width = metric.widthPixels;     // 屏幕宽度（像素）

        //设置可滑动
        et_input.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_x:
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
            case R.id.img_flag:
                String content1 = null;
                if (JUDGE) {
                    content1 = story.getFlags();
                }else if(!tv_flag.getText().toString().equals("标签")){
                    content1 = tv_flag.getText().toString();
                }
                ShowDialog(content1, "编辑标签", 0);
                break;
            case R.id.img_tick:
                if(flag == 1){      //更新修改已上传的文章
                    System.out.println("FLAG_FROM_ONLINE_ARTICLE == 1");
                }else{
                    if(JUDGE){
                        //先将内容保存，并更新主页面的数据
                        save_story();
                        StoryFragment.refreshStoryListView();
                        finish();
                        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    }else{
                        ShowDialog(null, null, 1);
                    }
                }
                break;
            case R.id.img_is_public:
                change();
                break;
            case R.id.et_input:
                break;
            case R.id.et_tile:
                break;
            case R.id.tv_flag:
                break;
        }
    }

    private void ShowDialog(String content, String title, final int flag) {
        final MyDialog myDialog = new MyDialog(this, R.style.myDialogTheme, content, title, "输入标签", flag);
        myDialog.show();
        myDialog.setClickListener(new MyDialog.ClickListenerInterface() {
            @Override
            public void doConfirm() {
                if(flag == 0){
                    tv_flag.setText(myDialog.getDialog_et().getText().toString());
                    tv_flag.setVisibility(View.VISIBLE);
                }else{
                    img_is_public.setImageResource(R.drawable.img_public);
                    img_is_public.setVisibility(View.VISIBLE);
                    a = 1;
                    //先将内容保存，并更新主页面的数据
                    save_story();
                    StoryFragment.refreshStoryListView();
                    finish();
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                }
                myDialog.dismiss();
            }

            @Override
            public void doCancel() {
                if(flag != 0){
                    img_is_public.setImageResource(R.drawable.img_privacy);
                    img_is_public.setVisibility(View.VISIBLE);
                    a = 0;
                    //先将内容保存，并更新主页面的数据
                    save_story();
                    StoryFragment.refreshStoryListView();
                    finish();
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                }
                myDialog.dismiss();
            }
        });
    }

    private void change() {
        if (a == 0) {
            img_is_public.setImageResource(R.drawable.img_public);
            a = 1;
        } else {
            img_is_public.setImageResource(R.drawable.img_privacy);
            a = 0;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //从图库选择图片后相应以下动作
        if (resultCode == RESULT_OK && requestCode == 0)

        {
            ContentResolver resolver = getContentResolver();
            // 获得图片的uri
            Uri originalUri = data.getData();

            //获取文件的绝对路径
            try {
                Bitmap bm = BitmapFactory.decodeStream(resolver.openInputStream(originalUri));
                if (bm != null) {
                    float multiple = (float) width / (float) bm.getWidth();
                    bm = Tool.resize_bitmap(bm, width - 80, multiple * bm.getHeight() - 80);
                    insertPic(bm, originalUri);
                } else {
                    Toast.makeText(Edit_Story.this, "获取图片失败",
                            Toast.LENGTH_SHORT).show();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
    }

    /**
     * 根据URI插入图片
     *
     * @param bitmap
     * @param uri
     */

    private void insertPic(Bitmap bitmap, Uri uri) {

        // 根据Bitmap对象创建ImageSpan对象
        ImageSpan imageSpan = new ImageSpan(Edit_Story.this, bitmap);

        // 创建一个SpannableString对象，以便插入用ImageSpan对象封装的图像
        //System.out.println(Tool.getPath(this,uri));
        String path = Tool.getPath(this, uri);
        //<img alt="" src="1.jpg"></img>
        path = "<img" + path + "></img>";

        SpannableString spannableString = new SpannableString(path);

        // 用ImageSpan对象替换指定的字符串
        spannableString.setSpan(imageSpan, 0, path.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 将选择的图片追加到EditText中光标所在位置
        int index = et_input.getSelectionStart(); // 获取光标所在位置
        Editable edit_text = et_input.getEditableText();
        if (index < 0 || index >= edit_text.length()) {
            edit_text.append('\n');
            edit_text.append(spannableString);
            System.out.println("ONCE");

            edit_text.append('\n');
        } else {
            edit_text.insert(index, spannableString);
        }

        String s = et_input.getText().toString();
        content = s;
        System.out.println(s.length());
        System.out.println(content);
        getImg_Src(content);
    }

    /**
     * 解析字符串函数
     * <p/>
     * 解析字符串内容，将其中包含的图片路径存到list中
     * 每个图片的前后索引值存在index_list中
     *
     * @param content
     */
    public void getImg_Src(String content) {
        list = new ArrayList<String>();
        int index_pre = content.indexOf("<img");
        int index_next = 0;
        index_int.clear();
        while (content.indexOf("<img", index_next) != -1) {

            index_pre = content.indexOf("<img", index_next);
            //图片标签索引的前值添加进index_int
            index_int.add(index_pre);

            index_next = content.indexOf("</img>", index_pre);
            //图片标签索引的后值添加进index_int
            index_int.add(index_next + 6);

            //将包含的路径放到list中
            list.add(content.substring(index_pre + 4, index_next - 1));
        }

        System.out.println("getImg_Src== " + list.toString());

    }


    /**
     * 图文详情页面选择图片
     */
    public void getImage() {
        intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }


    /**
     * 保存故事函数（保存到数据库中）
     * 存在则更新，不存在则插入
     */
    private void save_story() {
        ContentValues cv = new ContentValues();
        //获取当前时间
        date = getDate();
        System.out.println("Date : ======>" + date);
        cv.put(StoryDB.COLUMN_NAME_TITLE, et_title.getText().toString());
        cv.put(StoryDB.COLUMN_NAME_CONTENT, et_input.getText().toString());
        cv.put(StoryDB.COLUMN_NAME_PUBLIC_ENABLE, a);
        cv.put(StoryDB.COLUMN_NAME_CREATE_AT, String.valueOf(date));
        cv.put(StoryDB.COLUMN_NAME_FLAGS, tv_flag.getText().toString());
        Story story;
        System.out.println(JUDGE + String.valueOf(getIntent().getIntExtra("ID", 1) + ""));
        if(!App.dbWrite.isOpen()){
            System.out.println("WTF, 谁把你关了");
            App.openDB();
        }
        if (JUDGE) {
            //更新SQLite数据
            App.dbWrite.update(StoryDB.TABLE_NAME_STORY, cv, StoryDB.COLUMN_NAME_ID + "=?", new String[]{String.valueOf(getIntent().getIntExtra("ID", 1))
            });
            story = App.StoryList.get(getIntent().getIntExtra("position", -1));
            story.setTitle(et_title.getText().toString());
            story.setContent(et_input.getText().toString());
            story.setCreatedAt(String.valueOf(date));
            story.setPublicEnable(a);
            story.setFlags(tv_flag.getText().toString());

            App.StoryList.set(getIntent().getIntExtra("position", -1), story);
            System.out.println("update success");
        } else {
            //创建新故事
            story = new Story();
            story.setTitle(et_title.getText().toString());
            story.setContent(et_input.getText().toString());
            story.setCreatedAt(String.valueOf(date));
            story.setPublicEnable(a);
            story.setFlags(tv_flag.getText().toString());
            App.dbWrite.insert(StoryDB.TABLE_NAME_STORY, null, cv);
            App.StoryList.add(story);
            System.out.println("create success");
        }

    }

    /**
     * 显示函数，将list、index_list和content组合显示出图文混排
     */
    public void display() {
        System.out.println("Display== " + list.toString());
        for (int i = 0; i < index_int.size(); i++) {
            if ((i + 1) % 2 == 0) {
                path = list.get((i + 1) / 2 - 1);
                File file = new File(path);
                if (file.exists()) {
                    Bitmap bm = BitmapFactory.decodeFile(path);
                    float multiple = (float) width / (float) bm.getWidth();
                    bm = Tool.resize_bitmap(bm, width - 80, multiple * bm.getHeight() - 80);
                    Tool.insertPic(bm, path, Edit_Story.this, et_input);
                }
            }
            if (i == 0) {
                assert content != null;
                edit_text.append(content.substring(0, index_int.get(0)));
            } else if (i % 2 != 0 && i < index_int.size() - 1) {
                edit_text.append("    ");
                edit_text.append(content.substring(index_int.get(i), index_int.get(i + 1)));
            }
        }

        assert content != null;
        int start = 0, end = 0;
        if (index_int.size() != 0) {
            start = index_int.get(index_int.size() - 1);
        }
        end = content.length();
        edit_text.append(content.substring(start, end));
    }

    public Date getDate() {
        return new Date(System.currentTimeMillis());
    }
}
