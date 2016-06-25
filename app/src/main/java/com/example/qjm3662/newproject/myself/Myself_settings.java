package com.example.qjm3662.newproject.myself;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.qjm3662.newproject.Data.Final_Static_data;
import com.example.qjm3662.newproject.Data.User;
import com.example.qjm3662.newproject.NetWorkOperator;
import com.example.qjm3662.newproject.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

public class Myself_settings extends Activity {

    private EditText et_userName;
    private EditText et_personNote;
    private EditText et_avatar;
    private Button btn_saveChange;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myself_settings);

        final User user = User.getInstance();
        final Context context = Myself_settings.this;

        et_userName = (EditText) findViewById(R.id.et_userName);
        et_personNote = (EditText) findViewById(R.id.et_personNote);
        et_avatar = (EditText) findViewById(R.id.et_avatar);

        et_userName.setText(user.getUserName());
        et_personNote.setText(user.getSign());
        et_avatar.setText(user.getAvatar());

        btn_saveChange = (Button) findViewById(R.id.btn_saveChange);
        btn_saveChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //user.setUserName();
                new Thread(new Runnable() {
					@Override
					public void run() {
						OkHttpUtils
								.post()
								.url(Final_Static_data.URL_GET_USER_INFO_UPDATE)
								.addHeader("LoginToken", user.getLoginToken())
								.addParams("avatar", et_avatar.getText().toString())
								.addParams("userName", et_userName.getText().toString())
								.addParams("sign", et_personNote.getText().toString())
								.addParams("sex", String.valueOf(1))
								.addParams("updateNotice","1")
								.addParams("noticeEnable","1")
								.addParams("followingEnable","1")
								.addParams("followerEnable","1")
								.addParams("aboutNotice","1")
								.build()
								.execute(new StringCallback() {
                                    @Override
                                    public void onError(okhttp3.Call call, Exception e) {

                                    }

                                    @Override
                                    public void onResponse(String response) {
                                        System.out.println(response);
                                        try {
                                            JSONObject j = new JSONObject(response);
                                            if(j.getBoolean("status")){
                                                NetWorkOperator.getUserInfo(context);
                                            }


                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
					}
				}).start();
                finish();
            }
        });
    }
}
