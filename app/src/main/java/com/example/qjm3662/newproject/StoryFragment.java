package com.example.qjm3662.newproject;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.StringDef;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.qjm3662.newproject.Data.Story;
import com.example.qjm3662.newproject.Data.StoryDB;

public class StoryFragment extends Fragment implements OnItemClickListener, View.OnClickListener {
	
	private static final String TAG = "StoryFragment";
	private ListViewCompat mListView;
	private View view;
	public SlideAdapter slideAdapter;
	private ImageView img_add;
	private SimpleCursorAdapter adapter = null;
	private SQLiteDatabase dbRead;
	private StoryDB storyDB;
	public static final int REQUEST_CODE_READ = 2;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_story, container, false);
		initView();



		return view;
	}
	
	private void initView() { 
		mListView = (ListViewCompat) view.findViewById(android.R.id.list);
		slideAdapter = new SlideAdapter(getActivity());
		mListView.setAdapter(slideAdapter);
		img_add = (ImageView) view.findViewById(R.id.add_imageView_story);
		img_add.setOnClickListener(this);
		mListView.setOnItemClickListener(this);
		refreshStoryListView();
	}

//	@Override
//	public void onListItemClick(ListView l, View v, int position, long id) {
//		super.onListItemClick(l, v, position, id);
//		Cursor c = adapter.getCursor();
//		c.moveToPosition(position);
//		System.out.println(c.getString(c.getColumnIndex(StoryDB.COLUMN_NAME_TITLE)));
//		Intent i = new Intent(view.getContext(),Edit_Acticity.class);
//		i.putExtra(Edit_Acticity.EDIT_TITLE, c.getString(c.getColumnIndex(StoryDB.COLUMN_NAME_TITLE)));
//		i.putExtra(Edit_Acticity.EDIT_CONTENT,c.getString(c.getColumnIndex(StoryDB.COLUMN_NAME_CONTENT)));
//		System.out.println(c.getString(c.getColumnIndex(StoryDB.COLUMN_NAME_TITLE)));
//		i.putExtra("JUDGE",true);
//		i.putExtra("ID",c.getInt(c.getColumnIndex(StoryDB.COLUMN_NAME_ID)));
//		this.startActivityForResult(i,2);
//	}

	public void refreshStoryListView() {
		//更新数据（查询出所有的数据）
		slideAdapter.notifyDataSetChanged();
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		 Toast.makeText(getActivity(), ""+position, Toast.LENGTH_SHORT).show();
		Intent i = new Intent(view.getContext(),Edit_Acticity.class);
		i.putExtra(Edit_Acticity.EDIT_TITLE, App.StoryList.get(position).getTitle());
		i.putExtra(Edit_Acticity.EDIT_CONTENT,App.StoryList.get(position).getContent());
		i.putExtra("JUDGE",true);
		i.putExtra("ID",App.StoryList.get(position).getLocal_id());
		i.putExtra("position",position);
		this.startActivityForResult(i,5);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.add_imageView_story:
				startActivityForResult(new Intent(view.getContext(),Edit_Acticity.class),5);
				break;
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode){
			case 5:
				System.out.println("OK");
				slideAdapter.notifyDataSetChanged();
				break;
		}
	}
}
