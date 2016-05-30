package com.example.qjm3662.newproject.Finding;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qjm3662.newproject.R;

public class Finding_ListAdapter extends BaseAdapter{

	private ArrayList<Finding_Listinfo>finding_userlist;
	private LayoutInflater inflater = null;
	
	public Finding_ListAdapter(Context context,ArrayList<Finding_Listinfo>finding_userlist){
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.finding_userlist = finding_userlist;
	}
	
	private class ViewHolder{
		private TextView nickname;
		private TextView geqian;
		private TextView title;
		private TextView time;
		private TextView jianjie;
		private ImageView icon;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return finding_userlist.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return finding_userlist.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {
		ViewHolder viewHolder = null;
		if(view == null){
			 viewHolder = new ViewHolder();
			 view = inflater.inflate(R.layout.listview, null);
			 viewHolder.geqian = (TextView)view.findViewById(R.id.finding_listview_gexingqianming);
			 viewHolder.time = (TextView)view.findViewById(R.id.finding_listview_time);
			 viewHolder.title = (TextView)view.findViewById(R.id.finding_listView_title);
			 viewHolder.icon = (ImageView)view.findViewById(R.id.finding_listview_icon_my);
			 viewHolder.jianjie = (TextView)view.findViewById(R.id.finding_listView_introduce);
			 viewHolder.nickname = (TextView)view.findViewById(R.id.finding_listview_nickname);
			 view.setTag(viewHolder);
		 }
		 else{
			 viewHolder = (ViewHolder)view.getTag();
		 }
			 Finding_Listinfo listinfo = finding_userlist.get(i);
			 
			 viewHolder.title.setText(listinfo.get_title());
			 viewHolder.geqian.setText(listinfo.get_geqian());
			 viewHolder.time.setText(listinfo.get_time());
			 viewHolder.nickname.setText(listinfo.get_nickname());
			 viewHolder.jianjie.setText(listinfo.get_jianjie());
			 viewHolder.icon.setImageResource(listinfo.get_icon());
		
		return view;
	}

}
