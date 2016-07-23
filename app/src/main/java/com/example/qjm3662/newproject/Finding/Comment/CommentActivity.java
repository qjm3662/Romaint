package com.example.qjm3662.newproject.Finding.Comment;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qjm3662.newproject.App;
import com.example.qjm3662.newproject.ChangeModeBroadCastReceiver;
import com.example.qjm3662.newproject.Data.CommentItem;
import com.example.qjm3662.newproject.Data.CommentItem_add;
import com.example.qjm3662.newproject.Data.User;
import com.example.qjm3662.newproject.NWO_2;

import com.example.qjm3662.newproject.R;
import com.yalantis.phoenix.PullToRefreshView;

public class CommentActivity extends ListActivity implements View.OnClickListener {

    private ImageView img_bar_left;
    private ImageView img_bar_right;
    private TextView tv_bar_center;
    private TextView tv_bar_right;

    private ImageView img_send;
    private EditText et_input;
    private CommentAdapter adapter;
    public static PullToRefreshView mPullToRefreshView;
    private Context context;

    private ChangeModeBroadCastReceiver receiver;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 0:
                    adapter.notifyDataSetChanged();
                    System.out.println("Com : 0");
                    System.out.println(App.Public_Comment_List.size());
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (App.Switch_state_mode) {
            this.setTheme(R.style.AppTheme_night);
        } else {
            this.setTheme(R.style.AppTheme_day);
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction("CHANGE_MODE");
        receiver = new ChangeModeBroadCastReceiver(this);
        registerReceiver(receiver, filter);

        setContentView(R.layout.activity_comment);
        context = this;
        initBar();
        adapter = new CommentAdapter(this);
        setListAdapter(adapter);
        App.Public_Comment_List.clear();
        NWO_2.GetCommentList(this, App.comment_story.getId(), handler);
        mPullToRefreshView = (PullToRefreshView) findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                NWO_2.GetCommentList(context, App.comment_story.getId(), handler);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    private void initBar() {
        img_bar_left = (ImageView) findViewById(R.id.cloud_imageView_story);
        img_bar_right = (ImageView) findViewById(R.id.add_imageView_story);
        tv_bar_center = (TextView) findViewById(R.id.tv_bar_center);
        tv_bar_right = (TextView) findViewById(R.id.tv_bar_right_text);
        img_bar_left.setImageResource(R.drawable.img_arrow_register);
        img_bar_right.setVisibility(View.INVISIBLE);
        tv_bar_right.setVisibility(View.INVISIBLE);
        tv_bar_center.getText().length();
        tv_bar_center.setText("评论");

        img_send = (ImageView) findViewById(R.id.img_send);
        img_send.setOnClickListener(this);
        et_input = (EditText) findViewById(R.id.et_input);
        img_bar_left.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cloud_imageView_story:
                onBackPressed();
                break;
            case R.id.img_send:
                NWO_2.GiveComment(this, App.comment_story.getId(), et_input.getText().toString(), handler);
                CommentItem_add commentItem_add = new CommentItem_add();
                commentItem_add.setUserBase(User.getInstance());
                commentItem_add.setContent(et_input.getText().toString());
                App.Public_Comment_List.add(new CommentItem_add());
                for (int i = App.Public_Comment_List.size() - 1; i > 0; i--) {
                    App.Public_Comment_List.set(i, App.Public_Comment_List.get(i - 1));
                }
                App.Public_Comment_List.set(0, commentItem_add);
                et_input.clearFocus();
                et_input.setText("");
                adapter.notifyDataSetChanged();
                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(getCurrentFocus()
                                        .getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
