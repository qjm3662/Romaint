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
import com.example.qjm3662.newproject.Data.Story;
import com.example.qjm3662.newproject.Data.StoryBean;
import com.example.qjm3662.newproject.Data.User;
import com.example.qjm3662.newproject.Data.UserBase;
import com.example.qjm3662.newproject.Finding.Finding;
import com.example.qjm3662.newproject.LoginAndRegister.LoginAndRegisterOperator;
import com.example.qjm3662.newproject.Main_UI.MainActivity;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import okhttp3.Call;

/**
 * Created by qjm3662 on 2016/6/4 0004.
 */
public class NetWorkOperator {


    /**
     * 获得广场故事
     * @param flag  0表示下拉刷新， 1表示上拉加载更多
     */
    public static void Get_finding_story(final int flag) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpUtils
                        .post()
                        .url(Final_Static_data.URL_GET_PUBLIC_STORIES)
                        .addHeader("LoginToken", User.getInstance().getLoginToken())
                        .addParams("timestamps", String.valueOf(System.currentTimeMillis()))
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e) {
                                //System.out.println("error");
                            }

                            @Override
                            public void onResponse(String response) {
                                //System.out.println("RESPONSE  : " + response);
                                try {
                                    JSONObject object = new JSONObject(response);

                                    //如果登陆失效则重新获取Token,再次执行该函数
                                    if (object.getString("msg").equals("LoginToken")) {
                                        getNew_Token(User.getInstance().getToken());
                                        Get_finding_story(flag);
                                    }

                                    final Gson gson = new Gson();
                                    final JSONArray jsonObject = object.getJSONArray("msg");

                                    //如果下拉刷新，则清除链表数据
                                    if(flag == 0){
                                        App.Public_StoryList.clear();
                                        App.Public_Story_User.clear();
                                    }
                                    Handler handler = new Handler() {
                                        @Override
                                        public void handleMessage(Message msg) {
                                            super.handleMessage(msg);
                                            switch (msg.what) {
                                                case 1:
                                                    System.out.println("case1");
                                                    for (int i = 0; i < jsonObject.length(); i++) {
                                                        StoryBean story = null;
                                                        try {
                                                            //用Gson解析器，将返回的Json数据转为Story对象
                                                            story = gson.fromJson(jsonObject.get(i).toString(), StoryBean.class);

                                                            //将故事对应的用户信息加到故事里去
                                                            story.setUser(App.Public_Story_User.get(i));
                                                            if (!App.Public_StoryList.contains(story)) {
                                                                App.Public_StoryList.add(story);
                                                            }
                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }

                                                    }

                                                    //在所有数据获取完毕后，更新UI界面
                                                    Finding.adapter.notifyDataSetChanged();
                                                    Finding.finding_swipeRefreshLayout.setRefreshing(false);
                                                    break;
                                            }
                                        }
                                    };
                                    //通过故事中包含的作者的AuthorID获取作者信息
                                    GET_USER_INFORMATION_BY_ID(jsonObject, handler);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        }).start();
    }


    /**
     * 获取故事的用户信息
     *
     * @param jsonArray
     * @param handler
     */
    public static void GET_USER_INFORMATION_BY_ID(final JSONArray jsonArray, final Handler handler) {

        //用来标识用户信息返回的个数（每调用一次onResponse（），++）
        final int[] flag_if_over = {0};
        //暂时存储用户ID
        String id;

        //先创建对应数量的实例
        for (int i = 0; i < jsonArray.length(); i++) {
            App.Public_Story_User.add(new UserBase());
        }

        //获取用户信息操作
        for (int i = 0; i < jsonArray.length(); i++) {

            //用户的Json数据实例
            JSONObject jb_ = null;

            //用来标记这是第几个故事对应的用户信息（与  App.Public_Story_User.set(flag, userBase);  相配合）
            final int flag = i;
            try {
                jb_ = new JSONObject(jsonArray.get(i).toString());
                id = jb_.getString("AuthorID");
                System.out.println("ID : " + id);
                OkHttpUtils
                        .post()
                        .url(Final_Static_data.URL_GET_USER_INFO)
                        .addHeader("LoginToken", User.getInstance().getLoginToken())
                        .addParams("AuthorID", id)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e) {
                                //Error 处理
                                System.out.println(e);
                            }

                            @Override
                            public void onResponse(String response) {
                                Gson gson = new Gson();
                                try {
                                    flag_if_over[0]++;
                                    System.out.println("flag : " + flag);
                                    //将返回的用户信息转成Json数据
                                    JSONObject response_jb = new JSONObject(response);

                                    //System.out.println(response_jb);

                                    //将用户信息转为UserBase对象
                                    UserBase userBase = gson.fromJson(response_jb.getJSONObject("msg").getJSONObject("user").toString(), UserBase.class);
                                    App.Public_Story_User.set(flag, userBase);

                                    System.out.println(App.Public_Story_User.get(flag).getId());

                                    //System.out.println("finalI :"+finalI);
                                    //Log.e("GET_USER_INFO_FLAG",i_num[0] + "  " + finalI);

                                    //所有信息转换完毕，通知上层函数
                                    if (flag_if_over[0] == jsonArray.length()) {
                                        System.out.println("Handler + " + flag_if_over[0]);
                                        handler.sendEmptyMessage(1);
                                        System.out.println(User.getInstance().getUserName());
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


    /**
     * 上传故事
     *
     * @param context
     * @param story
     */
    public static void UpLoad_story(final Context context, final Story story) {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                System.out.println("Story : " + story.getTitle());
//                System.out.println(User.getInstance().getLoginToken());
                OkHttpUtils
                        .post()
                        .url(Final_Static_data.URL_ADD_STORY)
                        .addHeader("LoginToken", User.getInstance().getLoginToken())
                        .addParams("title", story.getTitle())
                        .addParams("flags", "故事")
                        .addParams("content", story.getContent())
                        .addParams("publicEnable", "1")
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e) {
                                if (User.getInstance().getLoginToken() == null) {
                                    Toast.makeText(context, "请先登录", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "上传失败", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    if (jsonObject.getString("msg").equals("LoginToken")) {
                                        getNew_Token(User.getInstance().getToken());
                                        UpLoad_story(context, story);
                                    } else {
                                        Toast.makeText(context, "上传成功", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        }).start();
    }


    /**
     * 失效后获取新Token
     *
     * @param token
     */
    public static void getNew_Token(String token) {
        OkHttpUtils
                .post()
                .url(Final_Static_data.URL_GET_TOKEN)
                .addParams("token", User.getInstance().getToken())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("msg");
                            if (jsonObject1.getString("token").equals(User.getInstance().getToken())) {
                                User.getInstance().setLoginToken(jsonObject1.getString("LoginToken"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


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
                                //System.out.println(response + "啦啦啦");
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


    /**
     * 获取用户信息
     * @param context
     */
    public static void getUserInfo(final Context context) {
        System.out.println("Begin getUserInfo!!!!!");
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
                                    user.setUserName(user_info.getString(User.USER_USER_NAME));
                                    user.setSex(user_info.getInt(User.USER_SEX));


                                    //
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
