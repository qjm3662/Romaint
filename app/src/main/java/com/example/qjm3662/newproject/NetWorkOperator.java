package com.example.qjm3662.newproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qjm3662.newproject.Data.Final_Static_data;
import com.example.qjm3662.newproject.Data.User;
import com.example.qjm3662.newproject.LoginAndRegister.LoginAndRegisterOperator;
import com.example.qjm3662.newproject.Main_UI.MainActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

/**
 * Created by qjm3662 on 2016/6/4 0004.
 */
public class NetWorkOperator {



    /**
     * 登陆函数
     *
     * @param et_username
     * @param et_password
     * @param connect_flag
     * @param context
     * @return
     */

    public static void Login(EditText et_username, final EditText et_password, boolean connect_flag, final Context context) {
        System.out.println(connect_flag);
        if (et_username.getText().toString().equals("")) {
            Toast.makeText(context, "用户名不能为空", Toast.LENGTH_SHORT).show();
        } else if (et_password.getText().toString().equals("")) {
            Toast.makeText(context, "密码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            if (!connect_flag) {
                Toast.makeText(context, "请检查网络连接", Toast.LENGTH_SHORT).show();
            } else {
                OkHttpUtils
                        .post()
                        .url(Final_Static_data.URL_LOGIN)
                        .addParams("mobile", et_username.getText().toString())
                        .addParams("password", et_password.getText().toString())
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e) {
                                LoginAndRegisterOperator.Login_error_tip(context, "账号或密码错误！", et_password);
                            }

                            @Override
                            public void onResponse(String response) {
                                System.out.println(response + "啦啦啦");
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    JSONObject jsonObject1 = jsonObject.getJSONObject("msg");
                                    System.out.println(jsonObject1);

                                    if (jsonObject.getBoolean("status")) {
                                        Toast.makeText(context, "登陆成功", Toast.LENGTH_SHORT).show();

                                        //将用户信息存储到SharePreferences中
                                        SharedPreferences sp = context.getSharedPreferences("User_", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sp.edit();
                                        editor.putString("user_info", String.valueOf(jsonObject1));
                                        editor.apply();

                                        System.out.println("getUser_info");

                                        //获取用户信息
                                        User user = User.getInstance();
                                        user.setId(jsonObject1.getInt(User.USER_ID));
                                        user.setLoginToken(jsonObject1.getString(User.USER_LOGIN_TOKEN));
                                        user.setToken(jsonObject1.getString(User.USER_TOKEN));
                                        getUserInfo(context);

                                        context.startActivity(new Intent(context, MainActivity.class));
                                        ((Activity) context).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                                        ((Activity) context).finish();
                                    } else {
                                        System.out.println("Callback error");
                                        LoginAndRegisterOperator.Login_error_tip(context, "账号或密码错误！", et_password);
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    LoginAndRegisterOperator.Login_error_tip(context, "账号或密码错误！", et_password);
                                }
                            }
                        });
            }
        }
    }


    /**
     * 注册函数
     *
     * @param et_password
     * @param et_phone_number
     * @param context
     * @return
     */

    public static void register(final EditText et_password, EditText et_phone_number, final Context context) {

        if (et_phone_number.getText().toString().equals("")) {
            Toast.makeText(context, "手机号不能为空", Toast.LENGTH_SHORT).show();
        } else if (et_password.getText().toString().equals("")) {
            Toast.makeText(context, "密码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            OkHttpUtils
                    .post()
                    .url(Final_Static_data.URL_REGISTER)
                    .addParams("mobile", et_phone_number.getText().toString())
                    .addParams("password", et_password.getText().toString())
                    .addParams("userName", "未命名")
                    .addParams("avatar", "http://cdnq.duitang.com/uploads/item/201410/08/20141008104934_vhuuX.jpeg")
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e) {
                            System.out.println(e.toString());
                            LoginAndRegisterOperator.Login_error_tip(context, "手机号不存在或已注册！", et_password);
                        }

                        @Override
                        public void onResponse(String response) {
                            System.out.println(response);
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(response);
                                JSONObject object = jsonObject.getJSONObject("msg");
                                System.out.println(jsonObject);
                                if (jsonObject.getBoolean("status")) {

                                    Toast.makeText(context, "注册成功", Toast.LENGTH_SHORT).show();

                                    //将用户信息存储到SharePreferences中
                                    SharedPreferences sp = context.getSharedPreferences("User_", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sp.edit();
                                    editor.putString("user_info", String.valueOf(object));
                                    editor.apply();


                                    context.startActivity(new Intent(context, MainActivity.class));
                                    ((Activity) context).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                                    ((Activity) context).finish();
                                } else {
                                    System.out.println("Callback error");
                                    LoginAndRegisterOperator.Login_error_tip(context, "手机号不存在或已注册！", et_password);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                LoginAndRegisterOperator.Login_error_tip(context, "手机号不存在或已注册！", et_password);
                            }
                        }
                    });
        }
    }


    public static void getUserInfo(final Context context) {
        System.out.println(User.getInstance().getLoginToken());
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpUtils
                        .post()
                        .url(Final_Static_data.URL_GET_USER_INFO)
                        .addHeader("LoginToken", User.getInstance().getLoginToken())
                        .addParams("AuthorID", String.valueOf(User.getInstance().getId()))
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e) {
                                System.out.println("E_R_R_O_R");
                            }

                            @Override
                            public void onResponse(String response) {
                                try {


                                    JSONObject jsonObject = new JSONObject(response);
                                    JSONObject object = jsonObject.getJSONObject("msg");
                                    JSONObject user_info = object.getJSONObject("user");
                                    System.out.println(object);

                                    User user = User.getInstance();
                                    //将用户信息存储到SharePreferences中
                                    SharedPreferences sp = context.getSharedPreferences("User", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sp.edit();
                                    editor.putString("user_info", jsonObject.getString("msg"));
                                    editor.apply();

                                    System.out.println(user_info.getString(User.USER_AVATAR));
                                    System.out.println(user.getAvatar());
                                    user.setAvatar(user_info.getString(User.USER_AVATAR));
                                    //user.setCollectedStoriesCount(user_info.getInt(User.USER_COLLECTED_STORIES_COUNT));
                                    user.setSign(user_info.getString(User.USER_SIGN));
                                    user.setUsername(user_info.getString(User.USER_USER_NAME));
                                    user.setSex(user_info.getInt(User.USER_SEX));


                                    Intent intent = new Intent();
                                    intent.setAction("GET_INFO");
                                    context.sendBroadcast(intent);//传递过去

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
            }
        }).start();

    }

}
