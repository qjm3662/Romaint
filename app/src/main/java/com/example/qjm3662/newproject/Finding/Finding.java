package com.example.qjm3662.newproject.Finding;

import java.util.ArrayList;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qjm3662.newproject.R;

public class Finding extends ListFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	View view = inflater.inflate(R.layout.finding, container, false);
    	ArrayList<Finding_Listinfo>arraylist = new ArrayList<>();
    	for(int i = 0;i<3;i++){    			
    		arraylist.add(new Finding_Listinfo(
					"快让自己的签名变得长一点吧......",
					"昵称", "14:14",
					"从前有座山，山里有座庙，庙里有个老和尚和一个小和尚。老和尚再跟小和尚讲故事：从前有座山，山里有......",
					"有一个很长的故事叫山里的故事", R.drawable.img_my));
    	}
    	Finding_ListAdapter adapter = new Finding_ListAdapter(getActivity(), arraylist);
   		setListAdapter(adapter);
    	return view;
    }
}
