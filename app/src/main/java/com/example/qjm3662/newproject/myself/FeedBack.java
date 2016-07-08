package com.example.qjm3662.newproject.myself;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qjm3662.newproject.Data.User;
import com.example.qjm3662.newproject.NetWorkOperator;
import com.example.qjm3662.newproject.R;

public class FeedBack extends Activity implements View.OnClickListener {

    private ImageView img_bar_left;
    private ImageView img_bar_right;
    private TextView tv_bar_center;
    private EditText et_sign;
    private TextView tv_bat_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sign);
        img_bar_left = (ImageView) findViewById(R.id.cloud_imageView_story);
        img_bar_right = (ImageView) findViewById(R.id.add_imageView_story);
        tv_bar_center = (TextView) findViewById(R.id.tv_bar_center);
        et_sign = (EditText) findViewById(R.id.sign_edit);
        tv_bat_right = (TextView) findViewById(R.id.tv_bar_right_text);

        img_bar_left.setImageResource(R.drawable.img_arrow_register);
        img_bar_right.setVisibility(View.INVISIBLE);
        tv_bar_center.setText("反馈");
        et_sign.setHint("肆意BB吧~");
//        et_sign.setText(User.getInstance().getSign());

        img_bar_left.setOnClickListener(this);
        tv_bat_right.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cloud_imageView_story:
                finish();
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                break;
            case R.id.tv_bar_right_text:
                NetWorkOperator.getUserInfo(this, String.valueOf(User.getInstance().getId()));
                finish();
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                break;
        }
    }
}
