package com.example.qjm3662.newproject.myself;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.qjm3662.newproject.R;

public class MyselfMynameSet extends Activity {
	private TextView text_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.myself_mynameset);
		super.onCreate(savedInstanceState);

		text_back = (TextView)findViewById(R.id.myattention_back);
		text_back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}
}
