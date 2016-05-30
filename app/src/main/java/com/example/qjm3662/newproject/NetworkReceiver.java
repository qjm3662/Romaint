package com.example.qjm3662.newproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by qjm3662 on 2016/5/22 0022.
 */
public class NetworkReceiver extends BroadcastReceiver{
    private final static String TAG = "CHANGE";

//    private void check_network(Context context) {
//        //获取网络管理类
//        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        //得到当前可以使用的网络
//        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
//        //判断当前网络类型
//        if (activeNetworkInfo != null) {
//            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
//                Log.d(TAG, "当前网络： "+ activeNetworkInfo.getTypeName());
//            } else if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
//                Log.d(TAG, "当前网络： "+ activeNetworkInfo.getTypeName());
//            }
//            NetworkInfo.State state = activeNetworkInfo.getState();
//            if (state.ordinal() ==NetworkInfo.State.CONNECTED.ordinal()) {
//                Log.d(TAG, activeNetworkInfo.getTypeName() + "网络可用");
//            }
//        } else {
//            Log.d(TAG, "无网络可用");
//        }
//    }
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("TAG", "intent============>>>>" + intent.toString());

        if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {// 这个监听wifi的打开与关闭，与wifi的连接无关
            int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
            Log.e("H3c", "wifiState" + wifiState);
            switch (wifiState) {
                case WifiManager.WIFI_STATE_DISABLED:
                    break;
                case WifiManager.WIFI_STATE_DISABLING:
                    break;
                //
            }
        }
        // 这个监听wifi的连接状态即是否连上了一个有效无线路由，当上边广播的状态是WifiManager.WIFI_STATE_DISABLING，和WIFI_STATE_DISABLED的时候，根本不会接到这个广播。
        // 在上边广播接到广播是WifiManager.WIFI_STATE_ENABLED状态的同时也会接到这个广播，当然刚打开wifi肯定还没有连接到有效的无线
        if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction())) {
            Parcelable parcelableExtra = intent
                    .getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            if (null != parcelableExtra) {
                NetworkInfo networkInfo = (NetworkInfo) parcelableExtra;
                NetworkInfo.State state = networkInfo.getState();
                boolean isConnected = state == NetworkInfo.State.CONNECTED;// 当然，这边可以更精确的确定状态
                Log.e("H3c", "isConnected" + isConnected);
                if (isConnected) {
                } else {

                }
            }
        }
//        // 这个监听网络连接的设置，包括wifi和移动数据的打开和关闭。.
//        // 最好用的还是这个监听。wifi如果打开，关闭，以及连接上可用的连接都会接到监听。见log
//        // 这个广播的最大弊端是比上边两个广播的反应要慢，如果只是要监听wifi，我觉得还是用上边两个配合比较合适
//        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
//                 ConnectivityManager manager = (ConnectivityManager) context
//                    .getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo gprs = manager
//                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
//            NetworkInfo wifi = manager
//                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//            Log.i(TAG, "网络状态改变:" + wifi.isConnected() + " 3g:" + gprs.isConnected());
//            NetworkInfo info = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
//            if (info != null) {
//                Log.e("H3c", "info.getTypeName()" + info.getTypeName());
//                Log.e("H3c", "getSubtypeName()" + info.getSubtypeName());
//                Log.e("H3c", "getState()" + info.getState());
//                Log.e("H3c", "getDetailedState()"
//                        + info.getDetailedState().name());
//                Log.e("H3c", "getDetailedState()" + info.getExtraInfo());
//                Log.e("H3c", "getType()" + info.getType());
//
//                if (NetworkInfo.State.CONNECTED == info.getState()) {
//                } else if (info.getType() == 1) {
//                    if (NetworkInfo.State.DISCONNECTING == info.getState()) {
//
//                    }
//                }
//            }
//        }

//        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
//            Bundle bundle = intent.getExtras();
//            NetworkInfo aNetworkInfo = (NetworkInfo) bundle
//                    .get(ConnectivityManager.EXTRA_NETWORK_INFO);
//
//            Log.d(TAG, "广播接受");
//            check_network(context);
//            if (aNetworkInfo != null) {
//                if (aNetworkInfo.isConnected())
//                {
//                    System.out.println("connecte");
//                } else
//                {
//                    System.out.println("not connect");
//                }
//            }
//
//            if (aNetworkInfo != null) {
//                if (aNetworkInfo.isAvailable())
//                {
//                    System.out.println("available");
//                } else
//                {
//                    System.out.println("not available");
//                }
//            }
//
//            if (aNetworkInfo != null) {
//                System.out.println(aNetworkInfo.getDetailedState());
//            }
//        }

    }
}
