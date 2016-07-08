package com.example.qjm3662.newproject.myself.collection;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qjm3662.newproject.R;

public class collection extends ListActivity implements View.OnClickListener {

    private ImageView img_bar_left;
    private ImageView img_bar_right;
    private TextView tv_bar_center;
    private TextView tv_bar_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        initBar();
        Collection_Adapter adapter = new Collection_Adapter(this);
        setListAdapter(adapter);
    }

    private void initBar() {
        img_bar_left = (ImageView) findViewById(R.id.cloud_imageView_story);
        img_bar_right = (ImageView) findViewById(R.id.add_imageView_story);
        tv_bar_center = (TextView) findViewById(R.id.tv_bar_center);
        tv_bar_right = (TextView) findViewById(R.id.tv_bar_right_text);

        img_bar_left.setImageResource(R.drawable.img_arrow_register);
        img_bar_right.setVisibility(View.INVISIBLE);
        tv_bar_right.setVisibility(View.INVISIBLE);
        tv_bar_center.setText("收藏");

        img_bar_left.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cloud_imageView_story:
                onBackPressed();
                break;
        }
    }
}
