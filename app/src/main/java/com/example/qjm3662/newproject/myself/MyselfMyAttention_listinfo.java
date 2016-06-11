package com.example.qjm3662.newproject.myself;

public class MyselfMyAttention_listInfo {
	
	private int img_user;
	private String user_text;
	
	public MyselfMyAttention_listInfo(int img, String text){
		
		img_user = img;
		user_text = text;
		
	}
	
	public int getImg(){
		return img_user;
	}
	
	public String getName(){
		return user_text;
	}

}
