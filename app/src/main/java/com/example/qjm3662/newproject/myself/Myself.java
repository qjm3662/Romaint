package com.example.qjm3662.newproject.myself;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.style.EasyEditSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qjm3662.newproject.Data.Final_Static_data;
import com.example.qjm3662.newproject.Data.User;
import com.example.qjm3662.newproject.LoginAndRegister.LoginActivity;
import com.example.qjm3662.newproject.NetWorkOperator;
import com.example.qjm3662.newproject.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import lib.lhh.fiv.library.FrescoImageView;
import okhttp3.Call;

public class Myself extends Fragment {

	private ViewGroup mySetting;
	private ViewGroup myAttention;
	private ViewGroup myCollection;

	private FrescoImageView img_avater;
	private Context context;
	private TextView tv_name;
	private TextView tv_sign;
	private User_info_receiver get_info_receiver;
	private ViewGroup myChange;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.myself, container,false);
		context = view.getContext();

		get_info_receiver = new User_info_receiver();
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("GET_INFO"); //为BroadcastReceiver指定action，即要监听的消息名字。
		context.registerReceiver(get_info_receiver,intentFilter); //注册监听




		mySetting = (ViewGroup)view.findViewById(R.id.myself_setting_layout_);
		myAttention = (ViewGroup)view.findViewById(R.id.myself_myAttention_layout_);
		myCollection = (ViewGroup) view.findViewById(R.id.myself_myCollection_layout_);
		tv_name = (TextView) view.findViewById(R.id.myself_myName_layout_nickname);
		tv_sign = (TextView) view.findViewById(R.id.myself_myName_layout_gexingqianming);
		myChange = (ViewGroup) view.findViewById(R.id.myself_Change_user);

		img_avater = (FrescoImageView) view.findViewById(R.id.myself_myName_layout_img);

		img_avater.loadView(User.getInstance().getAvatar(), R.mipmap.ic_launcher);
		tv_name.setText(User.getInstance().getUsername());
		tv_sign.setText(User.getInstance().getSign());


		myChange.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//启动登陆界面
				Intent intent = new Intent(view.getContext(), LoginActivity.class);
				intent.putExtra("JUDGE",false);
				startActivity(intent);
			}
		});

		mySetting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				final User user = User.getInstance();
//				Intent intent = new Intent(getActivity(),MyselfMynameSet.class)
//				.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//				startActivity(intent);
				new Thread(new Runnable() {
					@Override
					public void run() {
						OkHttpUtils
								.post()
								.url(Final_Static_data.URL_GET_USER_INFO_UPDATE)
								.addHeader("LoginToken", User.getInstance().getLoginToken())
								.addParams("avatar", user.getAvatar())
								.addParams("userName", "253")
								.addParams("sign", "Lost time will never find again!")
								.addParams("sex", String.valueOf(1))
								.addParams("updateNotice","1")
								.addParams("noticeEnable","1")
								.addParams("followingEnable","1")
								.addParams("followerEnable","1")
								.addParams("aboutNotice","1")
								.build()
								.execute(new StringCallback() {
									@Override
									public void onError(Call call, Exception e) {

									}

									@Override
									public void onResponse(String response) {
										System.out.println(response);
										try {
											JSONObject j = new JSONObject(response);
											if(j.getBoolean("status")){
												Intent intent = new Intent();
												intent.setAction("GET_INFO");
												context.sendBroadcast(intent);//传递过去
											}
											NetWorkOperator.getUserInfo(context);
										} catch (JSONException e) {
											e.printStackTrace();
										}
									}
								});
					}
				}).start();
			}
		});
		
		myAttention.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getActivity(), MyselfMyAttention.class)
				.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				
			}
		});
		
		myCollection.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getActivity(),MyselfMyCollection.class)
				.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
		
		return view;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		context.unregisterReceiver(get_info_receiver);
	}

	private class User_info_receiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			System.out.println("收到");
			System.out.println(User.getInstance().getAvatar());
			img_avater.loadView(User.getInstance().getAvatar(), R.mipmap.ic_launcher);
			tv_name.setText(User.getInstance().getUsername());
			tv_sign.setText(User.getInstance().getSign());
		}
	}
}