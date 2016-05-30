package com.example.qjm3662.newproject.Finding;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qjm3662.newproject.R;

public class Fragment_title extends Fragment {

	private ImageView actionbar_left;
	private ImageView actionbar_right;
	private TextView actionbar_title;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.title_fragment,container,false);
		actionbar_left = (ImageView)view.findViewById(R.id.actionbar_left);
		actionbar_title = (TextView)view.findViewById(R.id.actionbar_title_text);
		actionbar_right = (ImageView)view.findViewById(R.id.actionbar_right);
		
		actionbar_title.setText("иб¤о");
		
		return view;
	}
}
