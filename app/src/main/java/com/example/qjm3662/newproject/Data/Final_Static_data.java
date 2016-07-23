package com.example.qjm3662.newproject.Data;

/**
 *
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
    public static final String NET_PORT = "3030";

    //登陆
    public static final String URL_LOGIN = NET_HOST + ":" + NET_PORT + "/api/login";
    //注册
    public static final String URL_REGISTER = NET_HOST + ":" + NET_PORT + "/api/register";
    //失效后获得新LoginToken
    public static final String URL_GET_TOKEN = NET_HOST + ":" + NET_PORT + "/api/getToken";
    //测试登陆
    public static final String URL_GET_DEBUG_LOGIN = NET_HOST + ":" + NET_PORT + "/api/debugLogin";
    //获取用户信息
    public static final String URL_GET_USER_INFO = NET_HOST + ":" + NET_PORT + "/api/token/userinfo";
    //更新用户信息
    public static final String URL_GET_USER_INFO_UPDATE = NET_HOST + ":" + NET_PORT + "/api/token/userinfo/update";
    //添加故事
    public static final String URL_ADD_STORY = NET_HOST + ":" + NET_PORT + "/api/token/addstory";
    //获得个人故事
    public static final String URL_GET_USER_STORIES = NET_HOST + ":" + NET_PORT + "/api/token/storylist/1/10";
    //获得广场故事
    public static final String URL_GET_PUBLIC_STORIES = NET_HOST + ":" + NET_PORT + "/api/token/public/storylist/1/10";

    //关注某人
    public static final String URL_CONCERN_SB = NET_HOST + ":" + NET_PORT + "/api/token/userinfo/addfollow";

    //取消关注某人
    public static final String URL_UNCONCERN_SB = NET_HOST + ":" + NET_PORT + "/api/token/userinfo/delfollow";

    //点赞故事（isLike为false则为取消点赞）
    public static final String URL_CLICK_A_LIKE = NET_HOST + ":" + NET_PORT + "/api/token/story/like";

    //收藏故事
    public static final String URL_COLLECT = NET_HOST + ":" + NET_PORT + "/api/token/story/collect";

    //上传头像
    public static final String UP_FILE = NET_HOST + ":" + NET_PORT + "/upload";

    //给文章发表评论
    public static final String GIVE_COMMENT = NET_HOST + ":" + NET_PORT + "/api/token/comment/";/*<storyID>*/

    //获得评论列表
    public static final String GET_COMMENT_LIST = NET_HOST + ":" + NET_PORT + "/api/token/commentlist/";/*<storyID>*/
}
