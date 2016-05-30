package com.example.qjm3662.newproject.Finding;

public class Finding_Listinfo {
	private String geqian,nickname,title,time,jianjie;
	private int icon;
	
	public Finding_Listinfo(String geqian,String nickname,String time,String jianjie,String title,int icon){
		this.geqian = geqian;
		this.icon = icon;
		this.jianjie = jianjie;
		this.nickname = nickname;
		this.time = time;
		this.title = title;
	}
	
	public String get_geqian(){
		return geqian;
	}
	public String get_title(){
		return title;
	}
	public String get_time(){
		return time;
	}
	public String get_jianjie(){
		return jianjie;
	}
	public String get_nickname(){
		return nickname;
	}
	public int get_icon(){
		return icon;
	}
	
}
