package com.example.qjm3662.newproject;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
	
	private static Context context;
	
	public void onCreate() {

		context = getApplicationContext();
	}
	
	/*
	 * SlideAdapter����    mInflater = (LayoutInflater)MyApplication.getContext()
	 *.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 *������ ���޷�����
	 */
	/*
	 * SlideAdapter����    slideView = new SlideView(MyApplication.getContext());
	 *������ ���޷�����
	 */
	public static Context getContext() {
		return context;
	}
}
