package com.example.qjm3662.newproject.LoginAndRegister;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.qjm3662.newproject.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

/**
 * Created by qjm3662 on 2016/5/19 0019.
 */
public class LoginAndRegisterOperator {
    private MyHandler handler;
    public static  PopupWindow my_window;

    public static interface CallBack{
        public void onError();
    }
    private CallBack callBack;

    public CallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }


    public static  void Login_error_tip(Context context, String s, View view){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.popwindow_layout,null);
        ((TextView)layout.findViewById(R.id.error_text)).setText(s);
        my_window = new PopupWindow(layout,RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        //设置如下四条信息，当点击其他区域使其隐藏，要在show之前配置
        my_window.setFocusable(true);
        my_window.setOutsideTouchable(true);
        my_window.update();
        //设置窗口弹出的效果（淡出）
        my_window.setAnimationStyle(android.R.anim.fade_out);
        my_window.setTouchable(true);
        Log.e("dsd",my_window.isAboveAnchor()+"");
        my_window.showAtLocation(view, Gravity.CENTER_HORIZONTAL, 0, 0);
        TimePicker timePicker = new TimePicker(context);
        timePicker.postDelayed(new Runnable() {
            @Override
            public void run() {
                my_window.dismiss();
            }
        },2000);
    }


    public class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case -1:

                    break;
            }
        }
    }
}
