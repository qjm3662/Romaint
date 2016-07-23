package com.example.qjm3662.newproject.Finding.Comment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qjm3662.newproject.App;
import com.example.qjm3662.newproject.Data.UserBase;
import com.example.qjm3662.newproject.NetWorkOperator;
import com.example.qjm3662.newproject.R;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Created by qjm3662 on 2016/7/9 0009.
 */
public class CommentAdapter extends BaseAdapter{

    private class ViewHolder {
        private TextView tv_title;
        private TextView tv_content;
        private ImageView img_avatar;
        private ImageView img_sex;
        private TextView tv_date;

    }

    //时间操作
    private SimpleDateFormat sdr_hm = new SimpleDateFormat("HH:mm");
    private SimpleDateFormat sdr_year = new SimpleDateFormat("yy");
    private SimpleDateFormat sdr_mounth = new SimpleDateFormat("MM");
    private SimpleDateFormat sdr_day = new SimpleDateFormat("dd");
    private SimpleDateFormat sdr_yM = new SimpleDateFormat("yy-MM");
    private SimpleDateFormat sdr_Md = new SimpleDateFormat("MM-dd");

    private LayoutInflater inflater = null;

    public CommentAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        sdr_hm.setTimeZone(TimeZone.getTimeZone("GMT+8"));// 中国北京时间，东八区
        sdr_year.setTimeZone(TimeZone.getTimeZone("GMT+8"));// 中国北京时间，东八区
        sdr_mounth.setTimeZone(TimeZone.getTimeZone("GMT+8"));// 中国北京时间，东八区
        sdr_day.setTimeZone(TimeZone.getTimeZone("GMT+8"));// 中国北京时间，东八区
        sdr_yM.setTimeZone(TimeZone.getTimeZone("GMT+8"));// 中国北京时间，东八区
        sdr_Md.setTimeZone(TimeZone.getTimeZone("GMT+8"));// 中国北京时间，东八区
    }

    @Override
    public int getCount() {
        return App.Public_Comment_List.size();
    }

    @Override
    public Object getItem(int position) {
        return App.Public_Comment_List.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.comment_list_item, null);
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.user_info_item_name);
            viewHolder.tv_content = (TextView) convertView.findViewById(R.id.user_info_item_sign);
            viewHolder.img_avatar = (ImageView) convertView.findViewById(R.id.user_info_item_head);
            viewHolder.img_sex = (ImageView) convertView.findViewById(R.id.user_info_item_sex);
            viewHolder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        UserBase userBase = App.Public_Comment_List.get(position).getUserBase();

        if(userBase == null){
            userBase = new UserBase();
        }
        viewHolder.tv_title.setText(userBase.getUserName() + "评论了文章" + App.comment_story.getTitle());
        viewHolder.tv_content.setText(App.Public_Comment_List.get(position).getContent());

        //1男2女
        if(userBase.getSex() == 1){
            viewHolder.img_sex.setImageResource(R.drawable.img_small_male);
        }else{
            viewHolder.img_sex.setImageResource(R.drawable.img_small_female);
        }

        Date date = new Date((long) (Double.parseDouble(App.Public_Comment_List.get(position).getCreatedAt()) * 1000L));
        Date cur_date = new Date(System.currentTimeMillis());
        String s_date;
        if(sdr_year.format(date).equals(sdr_year.format(cur_date))){
            if(sdr_mounth.format(date).equals(sdr_mounth.format(cur_date)) && sdr_day.format(date).equals(sdr_day.format(cur_date))){
                s_date = sdr_hm.format(date);
            }else{
                s_date = sdr_Md.format(date);
            }
        }else{
            s_date = sdr_yM.format(date);
        }
        viewHolder.tv_date.setText(s_date);
        NetWorkOperator.Set_Avatar(userBase.getAvatar(), viewHolder.img_avatar);
        return convertView;
    }
}
