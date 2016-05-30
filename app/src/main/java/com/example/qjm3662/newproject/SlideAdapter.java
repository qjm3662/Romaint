package com.example.qjm3662.newproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.qjm3662.newproject.Data.Story;
import com.example.qjm3662.newproject.Data.StoryDB;
import com.example.qjm3662.newproject.model.MessageItem;
import com.example.qjm3662.newproject.model.MessageItemList;


public class SlideAdapter extends BaseAdapter implements SlideView.OnSlideListener {

	private static final String TAG = "SlideAdapter";
	private LayoutInflater mInflater;
	private SlideView mLastSlideViewWithStatusOn;
	private Context context;

	SlideAdapter(Context context) {
		super();
		this.context = context;
		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return App.StoryList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return App.StoryList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		SlideView slideView = (SlideView) convertView;
		/******新发现******/
		/*
		 * 在通过adapter生成view时，convertView并不是一直是一个，也就是说下面的if语句会运行多次
		 * 执行的次数应该就是第一次显示ListView时在屏幕上显示的item的个数
		 * 也就是说  第七个item的convertView 就是第一个item的convertView
		 */

		/*
		 * 调用notifyDataSetChanged()方法  会重新调用getView方法
		 * 但和生成ListView时不一样，这次使用的 concertView还是以前的
		 * 也就是说 这次调用 listView不会进入if语句
		 */

		if(slideView == null){
			//Log.e(TAG,"进来了吗");
			View itemView = mInflater.inflate(R.layout.listview, null);
			slideView = new SlideView(context);
			slideView.setContentView(itemView);

			holder = new ViewHolder(slideView);
			slideView.setOnSlideListener(this);
			slideView.setTag(holder);
		} else {
			holder = (ViewHolder) slideView.getTag();
		}

		Story item = App.StoryList.get(position);
		item.slideView = slideView;
		//调用notifyDataSetChanged()后重新绘制ListView时 使用的还是上次的convertView
		//也就是说convertView的状态不会改变  比如打开状态
		//这时就需要手动滑回去  也就是下一行代码
		//注意shrink()不能用smoothScrollTo() 用这个会缓慢的滑回去  效果就是删掉了item2
		//重绘ListView  原本显示item2信息的convertView 显示item3的信息
		//并且缓慢的由 打开 到关闭
		//所以应该直接调用  scrollTo()
		item.slideView.shrink();

		holder.tv_title.setText(item.getTitle());
		holder.tv_content.setText(item.getContent());
		//为什么不会产生 线程问题？ 为什么可以在getView中对tv_delete操作？
		holder.tv_delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//Log.e(TAG,"position"+position);
				App.StoryList.remove(position);
				//App.dbWrite.delete(StoryDB.TABLE_NAME_STORY,)
				notifyDataSetChanged();
			}
		});
		return slideView;
	}

	@Override
	public void onSlide(View view, int status) {
		if (mLastSlideViewWithStatusOn != null && mLastSlideViewWithStatusOn != view) {
			mLastSlideViewWithStatusOn.shrink();
		}

		if(status == SLIDE_STATUS_ON) {
			mLastSlideViewWithStatusOn = (SlideView) view;
		}

	}

	private static class ViewHolder {
		public TextView tv_title;
		public TextView tv_content;
		public TextView tv_delete;

		ViewHolder(View view) {
			tv_title = (TextView)view.findViewById(R.id.finding_listView_title);
			tv_content = (TextView) view.findViewById(R.id.finding_listView_introduce);
			tv_delete = (TextView)view.findViewById(R.id.delete_slide);
		}
	}

}