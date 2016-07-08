package com.example.qjm3662.newproject.myself.collection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.qjm3662.newproject.App;
import com.example.qjm3662.newproject.Data.StoryBean;
import com.example.qjm3662.newproject.R;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import lib.lhh.fiv.library.FrescoImageView;

/**
 * Created by qjm3662 on 2016/7/2 0002.
 */
public class Collection_Adapter extends BaseAdapter{

    private LayoutInflater inflater = null;

    public Collection_Adapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private class ViewHolder {
        private TextView nickname;
        private TextView flags;
        private TextView title;
        private TextView time;
        private TextView introduce;
        private FrescoImageView icon;
        private TextView sign;
    }


    @Override
    public int getCount() {
        return App.Public_Collected_StoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return App.Public_Collected_StoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder =new ViewHolder();
            convertView = inflater.inflate(R.layout.listview, null);
            viewHolder.flags = (TextView) convertView.findViewById(R.id.finding_listView_flags);
            viewHolder.time = (TextView) convertView.findViewById(R.id.finding_listView_time);
            viewHolder.title = (TextView) convertView.findViewById(R.id.finding_listView_title);
            viewHolder.icon = (FrescoImageView) convertView.findViewById(R.id.finding_listView_icon_my);
            viewHolder.introduce = (TextView) convertView.findViewById(R.id.finding_listView_introduce);
            viewHolder.nickname = (TextView) convertView.findViewById(R.id.finding_listView_nickname);
            viewHolder.sign = (TextView) convertView.findViewById(R.id.finding_listview_gexingqianming);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //时间操作
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        sdr.setTimeZone(TimeZone.getTimeZone("GMT+8"));// 中国北京时间，东八区
        StoryBean listInfo = App.Public_Collected_StoryList.get(position);
        if(listInfo != null){
            if(listInfo.getCreatedAt() != null){
                Date date = new Date(Long.parseLong(listInfo.getCreatedAt()) * 1000L);
                String s_date = sdr.format(date);
                viewHolder.time.setText(s_date);
            }
            viewHolder.title.setText(listInfo.getTitle());
            viewHolder.flags.setText(listInfo.getFlags());
            viewHolder.introduce.setText(listInfo.getContent());
            if(listInfo.getUser() != null){
                viewHolder.icon.loadView(listInfo.getUser().getAvatar(), R.mipmap.ic_launcher);
                viewHolder.nickname.setText(listInfo.getUser().getUserName());
                viewHolder.sign.setText(listInfo.getUser().getSign());
            }
        }
        return convertView;
    }
}
