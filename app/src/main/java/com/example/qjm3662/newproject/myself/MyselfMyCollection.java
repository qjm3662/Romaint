package com.example.qjm3662.newproject.myself;

import java.util.ArrayList;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.example.qjm3662.newproject.Finding.Finding_ListAdapter;
import com.example.qjm3662.newproject.Finding.Finding_Listinfo;
import com.example.qjm3662.newproject.R;

public class MyselfMyCollection extends Activity {

	private TextView text_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.myself_mycollection);
		super.onCreate(savedInstanceState);

		text_back = (TextView)findViewById(R.id.mycollection_back);

		text_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
//				Intent intent = new Intent();
//				intent.setClass(MyselfMyCollection.this, TabActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//				startActivity(intent);
				finish();

			}
		});

		ListView myAttentionListview = (ListView)findViewById(R.id.mycollection_list);

		ArrayList<Finding_Listinfo> arraylist = new ArrayList<>();
		for(int i = 0;i<10;i++)
			arraylist.add(new Finding_Listinfo(
					"快让自己的签名变得长一点吧......",
					"昵称", "14:14",
					"从前有座山，山里有座庙，庙里有个老和尚和一个小和尚。老和尚再跟小和尚讲故事：从前有座山，山里有......",
					"有一个很长的故事叫山里的故事", R.drawable.img_my));

		Finding_ListAdapter adapter = new Finding_ListAdapter(this,arraylist);

		myAttentionListview.setAdapter(adapter);

	}

}
