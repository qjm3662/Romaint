package com.example.qjm3662.newproject.myself;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qjm3662.newproject.R;

public class MyselfMyAttention_listAdapter extends BaseAdapter {

	private ArrayList<MyselfMyAttention_listInfo> arraylist;
	private Context context;
	
	public MyselfMyAttention_listAdapter(Context context,ArrayList<MyselfMyAttention_listInfo> arraylist){
		this.arraylist = arraylist;
		this.context = context;
	}
	
	private class ViewHolder{
		private TextView user_name;
		private ImageView user_img;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arraylist.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arraylist.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {
		
		ViewHolder viewHolder = null;
		
		if(view == null)
		{
			viewHolder = new ViewHolder();
			view = LayoutInflater.from(context).inflate(R.layout.myattention_listview, null);
			viewHolder.user_img = (ImageView)view.findViewById(R.id.myattention_listview_img);
			viewHolder.user_name = (TextView)view.findViewById(R.id.myattention_listview_text);
			view.setTag(viewHolder);
		}
		else {
			viewHolder = (ViewHolder)view.getTag();
		}

		MyselfMyAttention_listInfo userinfo = arraylist.get(i);
		viewHolder.user_img.setImageAlpha(userinfo.getImg());
		viewHolder.user_name.setText(userinfo.getName());
		
		return view;
	}

}
