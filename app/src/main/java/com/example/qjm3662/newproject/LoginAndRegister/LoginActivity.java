package com.example.qjm3662.newproject.LoginAndRegister;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qjm3662.newproject.Data.Final_Static_data;
import com.example.qjm3662.newproject.Main_UI.MainActivity;
import com.example.qjm3662.newproject.NetworkReceiver;
import com.example.qjm3662.newproject.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img_back;
    private LayoutInflater inflater;
    private RelativeLayout layout;
    private PopupWindow my_window;
    private Button btn_login;
    private TextView tv_forget_password;
    private EditText et_username;
    private EditText et_password;
    private boolean connect_flag = false;
    private ConnectivityManager manager;
    private NetworkReceiver receiver;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_ui);

        img_back = (ImageView) findViewById(R.id.bar_back);
        btn_login = (Button) findViewById(R.id.login_btn);
        tv_forget_password = (TextView) findViewById(R.id.forget_password);
        et_username = (EditText) findViewById(R.id.login_phone);
        et_password = (EditText) findViewById(R.id.login_password);
        context = LoginActivity.this;

        img_back.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        tv_forget_password.setOnClickListener(this);
        


    }

    public void SetConect_flag(){
        //得到网络连接信息
        manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        //去进行判断网络是否连接
        if(networkInfo != null){
            System.out.println(networkInfo.getTypeName());
            tv_forget_password.setText(networkInfo.getTypeName());
            switch (networkInfo.getTypeName()){
                case "WIFI":
                    if(networkInfo.isAvailable()&&networkInfo.isConnected()){
                        connect_flag = true;
                    }else{
                        tv_forget_password.setText("对不起");
                        connect_flag = false;
                    }
                    break;
                case "MOBILE":case "mobile":
                    connect_flag = true;
                    break;
            }
        }else{
            tv_forget_password.setText("没有检测到4G网络");
            connect_flag = false;
        }

    }
    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.bar_back:
                Intent intent = new Intent(context,Register_UI.class);
                startActivityForResult(intent, Final_Static_data.REQUEST_CODE_LOG_BACK);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
                break;
            case R.id.login_btn:
                Login();
                break;
            case R.id.forget_password:
                break;
        }
    }
    public void Login(){
        SetConect_flag();
        System.out.println(connect_flag);
        if(et_username.getText().toString().equals("")){
            Toast.makeText(context, "用户名不能为空", Toast.LENGTH_SHORT).show();
        }else if(et_password.getText().toString().equals("")){
            Toast.makeText(context, "密码不能为空", Toast.LENGTH_SHORT).show();
        }else {
            if(!connect_flag){
                Toast.makeText(context, "请检查网络连接", Toast.LENGTH_SHORT).show();
            }else{
                OkHttpUtils
                        .post()
                        .url(Final_Static_data.URL_LOGIN)
                        .addParams("mobile", et_username.getText().toString())
                        .addParams("password", et_password.getText().toString())
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e) {
                                LoginAndRegisterOperator.Login_error_tip(context,"账号或密码错误！",et_password);
                            }

                            @Override
                            public void onResponse(String response) {
                                System.out.println(response + "啦啦啦");
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    JSONObject jsonObject1 = jsonObject.getJSONObject("msg");

                                    if (jsonObject.getBoolean("status")) {
                                        Toast.makeText(context, "登陆成功", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(context, MainActivity.class));
                                        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                                        finish();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    LoginAndRegisterOperator.Login_error_tip(context,"账号或密码错误！",et_password);
                                }
                            }
                        });
            }
        }
    }
}
