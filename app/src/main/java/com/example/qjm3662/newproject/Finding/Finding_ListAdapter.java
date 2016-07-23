package com.example.qjm3662.newproject.Finding;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.qjm3662.newproject.App;
import com.example.qjm3662.newproject.Data.StoryBean;
import com.example.qjm3662.newproject.R;

import lib.lhh.fiv.library.FrescoImageView;

public class Finding_ListAdapter extends BaseAdapter {

    private LayoutInflater inflater = null;
    //时间操作
    private SimpleDateFormat sdr_hm = new SimpleDateFormat("HH:mm");
    private SimpleDateFormat sdr_year = new SimpleDateFormat("yy");
    private SimpleDateFormat sdr_mounth = new SimpleDateFormat("MM");
    private SimpleDateFormat sdr_day = new SimpleDateFormat("dd");
    private SimpleDateFormat sdr_yM = new SimpleDateFormat("yy-MM");
    private SimpleDateFormat sdr_Md = new SimpleDateFormat("MM-dd");

    public Finding_ListAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        sdr_hm.setTimeZone(TimeZone.getTimeZone("GMT+8"));// 中国北京时间，东八区
        sdr_year.setTimeZone(TimeZone.getTimeZone("GMT+8"));// 中国北京时间，东八区
        sdr_mounth.setTimeZone(TimeZone.getTimeZone("GMT+8"));// 中国北京时间，东八区
        sdr_day.setTimeZone(TimeZone.getTimeZone("GMT+8"));// 中国北京时间，东八区
        sdr_yM.setTimeZone(TimeZone.getTimeZone("GMT+8"));// 中国北京时间，东八区
        sdr_Md.setTimeZone(TimeZone.getTimeZone("GMT+8"));// 中国北京时间，东八区
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
        // TODO Auto-generated method stub
        return App.Public_StoryList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return App.Public_StoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.listview, null);
            viewHolder.flags = (TextView) view.findViewById(R.id.finding_listView_flags);
            viewHolder.time = (TextView) view.findViewById(R.id.finding_listView_time);
            viewHolder.title = (TextView) view.findViewById(R.id.finding_listView_title);
            viewHolder.icon = (FrescoImageView) view.findViewById(R.id.finding_listView_icon_my);
            viewHolder.introduce = (TextView) view.findViewById(R.id.finding_listView_introduce);
            viewHolder.nickname = (TextView) view.findViewById(R.id.finding_listView_nickname);
            viewHolder.sign = (TextView) view.findViewById(R.id.finding_listview_gexingqianming);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }




        StoryBean listInfo = App.Public_StoryList.get(i);
        if(listInfo != null){
            if(listInfo.getCreatedAt() != null){
                Date date = new Date(Long.parseLong(listInfo.getCreatedAt()) * 1000L);
                Date cur_date = new Date(System.currentTimeMillis());
                String s_date = "";

                if(sdr_year.format(date).equals(sdr_year.format(cur_date))){
                    if(sdr_mounth.format(date).equals(sdr_mounth.format(cur_date)) && sdr_day.format(date).equals(sdr_day.format(cur_date))){
                        s_date = sdr_hm.format(date);
                    }else{
                        s_date = sdr_Md.format(date);
                    }
                }else{
                    s_date = sdr_yM.format(date);
                }


                viewHolder.time.setText(s_date);
            }
            viewHolder.title.setText(listInfo.getTitle());
            viewHolder.flags.setText(listInfo.getFlags());
            viewHolder.introduce.setText(listInfo.getContent());
            if(listInfo.getUser() != null){
                viewHolder.icon.loadView(listInfo.getUser().getAvatar(), R.drawable.img_defaultavatar);
                viewHolder.nickname.setText(listInfo.getUser().getUserName());
                viewHolder.sign.setText(listInfo.getUser().getSign());
            }
        }
        return view;
    }

}
