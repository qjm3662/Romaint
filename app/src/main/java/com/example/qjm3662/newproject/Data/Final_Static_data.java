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


    //服务器地址
    public static final String NET_HOST = "http://139.129.131.240";
    //端口号
    public static final String NET_PORT = "3000";

    //登陆
    public static final String URL_LOGIN = NET_HOST + ":" + NET_PORT + "/api/login";
    //注册
    public static final String URL_REGISTER = NET_HOST + ":" + NET_PORT + "/api/register";
    //失效后获得新LoginToken
    public static final String URL_GET_TOKEN = NET_HOST + ":" + NET_PORT + "/api/getToken";
    public static final String URL_GET_DEBUG_LOGIN = NET_HOST + ":" + NET_PORT + "/api/debugLogin";
    public static final String URL_GET_USER_INFO = NET_HOST + ":" + NET_PORT + "/api/token/userinfo";
    public static final String URL_GET_USER_INFO_UPDATE = NET_HOST + ":" + NET_PORT + "/api/token/userinfo/update";
    public static final String URL_ADD_STORY = NET_HOST + ":" + NET_PORT + "/api/token/addstory";
    public static final String URL_GET_USER_STORIES = NET_HOST + ":" + NET_PORT + "/api/token/storylist/1/10";
    public static final String URL_GET_PUBLIC_STORIES = NET_HOST + ":" + NET_PORT + "/api/token/public/storylist/1/10";



    //06-06 11:19:12.877 21303-21303/com.example.qjm3662.newproject I/System.out: RESPONSE  :
    // {
    // "status":true,
    // "msg":
    // [{"id":"27f1fda0-2b99-11e6-a72e-b5458f2b67eb","title":"sad a",
    // "flags":"故事","content":"\n<img/storage/emulated/0/Download/389a95ca5f66f20f11dad194a3d2a242.png></img>\n"
    // ,"publicEnable":1,"updatedAt":"1465184748","createdAt":"1465184748","AuthorID":12,"Users":[],"likeCount":0},
    // {"id":"4b851810-2b3a-11e6-a72e-b5458f2b67eb",
    // "title":"this is a test","flags":"what","content":"this is a test","publicEnable":1,"updatedAt":"1465144006","createdAt":"1465144006","AuthorID":1,"Users":[],"likeCount":0},{"id":"e4efe255-ba30-4e38-b14a-ff259482f67f","title":" 这是一篇文章","flags":"","content":"hello world","publicEnable":1,"updatedAt":"1465143982","createdAt":"1465143982","AuthorID":8,"Users":[{"id":1,"mobile":"18340861710","avatar":"www.baidu.com","sign":"test","userName":"changed","token":"VPpBrnWtvWDbVMGXgCvYPBhnkWM=","sex":1,"noticeEnable":1,"followingEnable":1,"followerEnable":1,"aboutNotice":1,"updateNotice":1,"UserStory":{"isLike":1,"isCollection":1,"UserId":1,"StoryId":"e4efe255-ba30-4e38-b14a-ff259482f67f"}},{"id":2,"mobile":"18340861711","avatar":"http://www.baidu.com","sign":"hello world","userName":"test","token":"qjLzo8VG/TqKbLiqVMvGT3QKT6k=","sex":2,"noticeEnable":1,"followingEnable":1,"followerEnable":1,"aboutNotice":1,"updateNotice":1,"UserStory":{"isLike":1,"isCollection":1,"UserId":2,"StoryId":"e4efe255-ba30-4e38-b14a-ff259482f67f"}},{"id":3,"mobile":"18340861712","avatar":"http://www.baidu.com","sign":"hello world","userName":"test","token":"U1lJ7bAWaOxNJ+FjhIPIIMyXpyY=","sex":2,"noticeEnable":0,"followingEnable":0,"followerEnable":0,"aboutNotice":0,"updateNotice":0,"UserStory":{"isLike":1,"isCollection":1,"UserId":3,"StoryId":"e4efe255-ba30-4e38-b14a-ff259482f67f"}},{"id":4,"mobile":"18340861713","avatar":"http://www.baidu.com","sign":"hello world","userName":"test","token":"NQzvpjjez36vTdfzAteYwz43TGA=","sex":2,"noticeEnable":1,"followingEnable":1,"followerEnable":1,"aboutNotice":1,"updateNotice":1,"UserStory":{"isLike":1,"isCollection":1,"UserId":4,"StoryId":"e4efe255-ba30-4e38-b14a-ff259482f67f"}},{"id":5,"mobile":"18340861714","avatar":"http://www.baidu.com","sign":"hello world","userName":"test","token":"SBxHnkvfvToq50HCiFf/1TwzFTw=","sex":2,"noticeEnable":0,"followingEnable":0,"followerEnable":0,"aboutNotice":0,"updateNotice":0,"UserStory":{"isLike":1,"isCollection":1,"UserId":5,"StoryId":"e4efe255-ba30-4e38-b14a-ff259482f67f"}},{"id":6,"mobile":"18340861715","avatar":"http://www.baidu.com","sign":"hello world","userName":"test","token":"NmR1wc24TXftdTyJvomk4eIr/p8=","sex":2,"noticeEnable":1,"followingEnable":1,"followerEnable":1,"aboutNotice":1,"updateNotice":1,"UserStory":{"isLike":1,"isCollection":1,"UserId":6,"StoryId":"e4efe255-ba30-4e38-b14a-ff259482f67f"}},{"id":7,"mobile":"18340861716","avatar":"http://www.baidu.com","sign":"hello world","userName":"test","token":"iTV9tyycKAefq4UXCysU37fd48Q=","sex":2,"noticeEnable":0,"followingEnable":0,"followerEnable":0,"aboutNotice":0,"updateNotice":0,"UserStory":{"isLike":1,"isCollection":1,"UserId":7,"StoryId":"e4efe255-ba30-4e38-b14a-ff259482f67f"}},{"id":8,"mobile":"18340861717","avatar":"http://www.baidu.com","sign":"hello world","userName":"test","token":"RUO25lg2tRXvEeTJ+NEaHK0TjJs=","sex":2,"noticeEnable":1,"followingEnable":1,"followerEnable":1,"aboutNotice":1,"updateNotice":1,"UserStory":{"isLike":1,"isCollection":1,"UserId":8,"StoryId":"e4efe255-ba30-4e38-b14a-ff259482f67f"}},{"id":9,"mobile":"18340861718","avatar":"http://www.baidu.com","sign":"hello world","userName":"test","token":"yGMPSuFY6eorgTMMRApUYgE2SU0=","sex":2,"noticeEnable":0,"followingEnable":0,"followerEnable":0,"aboutNotice":0,"updateNotice":0,"UserStory":{"isLike":1,"isCollection":1,"UserId":9,"StoryId":"e4efe255-ba30-4e38-b14a-ff259482f67f"}},{"id":10,"mobile":"18340861719","avatar":"http://www.baidu.com","sign":"hello world","userName":"test","token":"C9IQlklPEzFVPyJEDhmADd7xN7Q=","sex":2,"noticeEnable":1,"followingEnable":1,"followerEnable":1,"aboutNotice":1,"updateNotice":1,"UserStory":{"isLike":1,"isCollection


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
