package com.example.qjm3662.newproject;

import com.example.qjm3662.newproject.Data.Final_Static_data;
import com.example.qjm3662.newproject.Data.Story;
import com.example.qjm3662.newproject.Data.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by qjm3662 on 2016/6/9 0009.
 */
public class GetNetWorkInfomationTest {
    public static void main(String[] args){
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
                                System.out.println("error");
                            }

                            @Override
                            public void onResponse(String response) {
                                System.out.println("RESPONSE  : " + response);
                                try {
                                    JSONObject object = new JSONObject(response);
                                    String jsonObject = object.getString("msg");
                                    System.out.println(jsonObject);
                                    Gson gson = new Gson();
                                    List<Story> story = gson.fromJson(jsonObject,new TypeToken<ArrayList<Story>>(){}.getType());
                                    System.out.println(story.toString());

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        }).start();
    }
}
