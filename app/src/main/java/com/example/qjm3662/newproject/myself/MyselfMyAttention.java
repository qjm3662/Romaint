package com.example.qjm3662.newproject.myself;

import java.util.ArrayList;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import com.example.qjm3662.newproject.R;

public class MyselfMyAttention extends Activity {

	private TextView text_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.myself_myattention);
		super.onCreate(savedInstanceState);

		text_back = (TextView)findViewById(R.id.myattention_back);

		text_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

		ListView myAttentionListview = (ListView)findViewById(R.id.myattention_list);

		ArrayList<MyselfMyAttention_listInfo> arraylist = new ArrayList<>();
		for(int i = 0;i<10;i++)
			arraylist.add(new MyselfMyAttention_listInfo(R.mipmap.ic_launcher, "卡卡西"+i));

		MyselfMyAttention_listAdapter adapter = new MyselfMyAttention_listAdapter(this,arraylist);

		myAttentionListview.setAdapter(adapter);

	}

}
