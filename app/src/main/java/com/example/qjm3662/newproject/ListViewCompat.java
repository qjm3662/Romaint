package com.example.qjm3662.newproject;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import com.example.qjm3662.newproject.Data.Story;
import com.example.qjm3662.newproject.Slide.SlideView;


public class ListViewCompat extends ListView {
	
	private static final String TAG = "ListViewCompat";
	
	private SlideView mFocusedItenView;

	public ListViewCompat(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public ListViewCompat(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ListViewCompat(Context context) {
		super(context);
	}
	
	public void shrinkListItem(int position) {
		View item = getChildAt(position);
		
		if(item != null) {
			try {
				((SlideView) item).shrink();
			} catch (ClassCastException e) {
				e.printStackTrace();
			}
		}
	}

	//点击删除按钮 不会出触发
	public boolean onTouchEvent (MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			int x = (int) event.getX();
			int y = (int) event.getY();
			int position = pointToPosition(x, y);
			Log.e(TAG,"position" + position);
			if (position != INVALID_POSITION) {
				Story item = (Story) getItemAtPosition(position);
				mFocusedItenView = item.slideView;
				Log.e(TAG,"mFocusedItenView=" + mFocusedItenView);
			}
		}
		default:
			break;
		}
		
		if(mFocusedItenView != null) {
			mFocusedItenView.onRequireTouchEvent(event);
		}
		
		return super.onTouchEvent(event);
	}
}
