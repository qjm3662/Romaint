package com.example.qjm3662.newproject.Data;

import java.util.Calendar;

/**
 * Created by qjm3662 on 2016/5/16 0016.
 */
public class Final_Static_data {
    public static final int REQUEST_CODE_REG_TO_LOGIN = 1;
    public static final int REQUEST_CODE_REG_TO_Main = 2;
    public static final int REQUEST_CODE_LOG_FORGET_ = 3;
    public static final int REQUEST_CODE_LOG_LOGING = 4;
    public static final int REQUEST_CODE_LOG_BACK = 4;


    public static final String NET_HOST = "http://139.129.131.240";
    public static final String NET_PORT = "3000";
    public static final String URL_LOGIN = NET_HOST + ":" + NET_PORT + "/api/login";
    public static final String URL_REGISTER = NET_HOST + ":" + NET_PORT + "/api/register";
    public static final String URL_GET_TOKEN = NET_HOST + ":" + NET_PORT + "/api/getToken";
    public static final String URL_GET_DEBUG_LOGIN = NET_HOST + ":" + NET_PORT + "/api/debugLogin";
    public static final String URL_GET_USER_INFO = NET_HOST + ":" + NET_PORT + "/api/token/userinfo";
    public static final String URL_GET_USER_INFO_UPDATE = NET_HOST + ":" + NET_PORT + "/api/token/userinfo/update";
    public static final String URL_ADD_STORY = NET_HOST + ":" + NET_PORT + "/api/token/addstory";

//    Calendar.YEAR;      //——年份
//
//    Calendar.MONTH——月份
//
//    Calendar.DATE——日期
//
//    Calendar.DAY_OF_MONTH——日期，和上面的字段意义完全相同
//
//    Calendar.HOUR——12小时制的小时
//
//    Calendar.HOUR_OF_DAY——24小时制的小时
//
//    Calendar.MINUTE——分钟
//
//    Calendar.SECOND——秒
//
//    Calendar.DAY_OF_WEEK——星期几

}
