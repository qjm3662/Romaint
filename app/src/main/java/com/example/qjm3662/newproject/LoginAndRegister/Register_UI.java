package com.example.qjm3662.newproject.LoginAndRegister;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qjm3662.newproject.Data.Final_Static_data;
import com.example.qjm3662.newproject.Main_UI.MainActivity;
import com.example.qjm3662.newproject.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

public class Register_UI extends Activity implements View.OnClickListener {

    private TextView register_to_login;
    private Button btn_register;
    private Intent intent;
    private EditText et_phone_number;
    private EditText et_password;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__ui);
        context = Register_UI.this;

        register_to_login = (TextView) findViewById(R.id.register_to_login);
        btn_register = (Button) findViewById(R.id.register_btn);
        et_phone_number = (EditText) findViewById(R.id.register_phone);
        et_password = (EditText) findViewById(R.id.register_password);

        register_to_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);

    }

    public void register() {
        if (et_phone_number.getText().toString().equals("")) {
            Toast.makeText(Register_UI.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
        } else if (et_password.getText().toString().equals("")) {
            Toast.makeText(Register_UI.this, "密码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            OkHttpUtils
                    .post()
                    .url(Final_Static_data.URL_REGISTER)
                    .addParams("mobile", et_phone_number.getText().toString())
                    .addParams("password", et_password.getText().toString())
                    .addParams("userName", "未命名")
                    .addParams("avatar","http://cdnq.duitang.com/uploads/item/201410/08/20141008104934_vhuuX.jpeg")
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e) {
                            System.out.println(e.toString());
                            LoginAndRegisterOperator.Login_error_tip(Register_UI.this,"手机号不存在或已注册！",et_password);
                        }

                        @Override
                        public void onResponse(String response) {
                            System.out.println(response);
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(response);
                                System.out.println(jsonObject);
                                if(jsonObject.getBoolean("status")){
                                    Toast.makeText(Register_UI.this, "注册成功", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(context, MainActivity.class));
                                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                                    finish();
                                }else{
                                    LoginAndRegisterOperator.Login_error_tip(Register_UI.this,"手机号不存在或已注册！",et_password);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                LoginAndRegisterOperator.Login_error_tip(Register_UI.this,"手机号不存在或已注册！",et_password);
                            }
                        }
                    });
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_to_login:
                intent = new Intent(Register_UI.this,LoginActivity.class);
                startActivityForResult(intent,Final_Static_data.REQUEST_CODE_REG_TO_LOGIN);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
                break;
            case R.id.register_btn:
                register();
                break;
        }
    }

}
