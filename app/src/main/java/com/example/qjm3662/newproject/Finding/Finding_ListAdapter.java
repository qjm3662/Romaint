package com.example.qjm3662.newproject.Finding;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qjm3662.newproject.App;
import com.example.qjm3662.newproject.Data.StoryBean;
import com.example.qjm3662.newproject.Data.User;
import com.example.qjm3662.newproject.R;
import com.example.qjm3662.newproject.myself.MyselfMyCollection;

import lib.lhh.fiv.library.FrescoImageView;

public class Finding_ListAdapter extends BaseAdapter {

    //private ArrayList<Finding_Listinfo>finding_userlist;
    private LayoutInflater inflater = null;

    public Finding_ListAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public Finding_ListAdapter(MyselfMyCollection myselfMyCollection, ArrayList<Finding_Listinfo> arraylist) {

    }

    private class ViewHolder {
        private TextView nickname;
        private TextView flags;
        private TextView title;
        private TextView time;
        private TextView introduce;
        private FrescoImageView icon;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        //System.out.println("Adapter + " + App.Public_StoryList.size());
        //System.out.println("Adapter_user + " + App.Public_Story_User.size());

        return App.Public_StoryList.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return App.Public_StoryList.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
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
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        //时间操作
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        sdr.setTimeZone(TimeZone.getTimeZone("GMT+8"));// 中国北京时间，东八区
        StoryBean listInfo = App.Public_StoryList.get(i);
        Date date = new Date(Long.parseLong(listInfo.getCreatedAt()) * 1000L);
        String s_date = sdr.format(date);


        viewHolder.title.setText(listInfo.getTitle());
        viewHolder.flags.setText(listInfo.getFlags());
        viewHolder.time.setText(s_date);
        viewHolder.introduce.setText(listInfo.getContent());
        viewHolder.icon.loadView(listInfo.getUser().getAvatar(), R.mipmap.ic_launcher);
        viewHolder.nickname.setText(listInfo.getUser().getUserName());
        return view;
    }

}
