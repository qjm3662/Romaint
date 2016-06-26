package com.example.qjm3662.newproject.Finding;

import java.util.ArrayList;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qjm3662.newproject.App;
import com.example.qjm3662.newproject.Data.User;
import com.example.qjm3662.newproject.NetWorkOperator;
import com.example.qjm3662.newproject.R;
import com.example.qjm3662.newproject.StoryView.Edit_Acticity;
import com.example.qjm3662.newproject.StoryView.Main2Activity;

public class Finding extends ListFragment implements SwipeRefreshLayout.OnRefreshListener{


    public static  Finding_ListAdapter adapter;
    private View view;
    private static final int REFREASH = 0x110;
    public static SwipeRefreshLayout finding_swipeRefreshLayout;

    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch(msg.what){
                case REFREASH:
                    if(User.getInstance().getLoginToken() != null ){
                        NetWorkOperator.Get_finding_story(0,String.valueOf(System.currentTimeMillis()));
                    }else{
                        Toast.makeText(view.getContext(), "请先登录", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.finding, container, false);
        ArrayList<Finding_Listinfo> arraylist = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            arraylist.add(new Finding_Listinfo(
                    "快让自己的签名变得长一点吧......",
                    "昵称", "14:14",
                    "从前有座山，山里有座庙，庙里有个老和尚和一个小和尚。老和尚再跟小和尚讲故事：从前有座山，山里有......",
                    "有一个很长的故事叫山里的故事", R.drawable.img_my));
        }

        finding_swipeRefreshLayout = (SwipeRefreshLayout)
                view.findViewById(R.id.finding_swipeRefreshLayout);
        finding_swipeRefreshLayout.setOnRefreshListener(this);


        view.findViewById(R.id.up_flush).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetWorkOperator.Get_finding_story(1,App.Public_StoryList.get(App.Public_StoryList.size()-1).getUpdatedAt());
            }
        });

        adapter = new Finding_ListAdapter(getActivity());
        setListAdapter(adapter);
        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        //打开编辑界面，查看故事
        Intent i = new Intent(v.getContext(),Main2Activity.class);
        i.putExtra(Edit_Acticity.EDIT_TITLE, App.Public_StoryList.get(position).getTitle());
        i.putExtra(Edit_Acticity.EDIT_CONTENT, App.Public_StoryList.get(position).getContent());
        //加一个标记，true表示是已有的故事查看
        i.putExtra("JUDGE",false);
        i.putExtra("position",position);
        startActivity(i);
    }

    @Override
    public void onRefresh() {
        handler.sendEmptyMessageDelayed(REFREASH,0);
    }
}
