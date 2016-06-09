package com.example.qjm3662.newproject.Data;

/**
 * Created by qjm3662 on 2016/5/17 0017.
 */
public class User {
    private int id;
    private String mobile;
    private String avatar = "111";
    private String sign;                    //签名
    private String username;
    private String token;
    private String LoginToken;
    private int sex;
    private int noticeEnable;
    private int followingEnable;        //我正在关注的是否公开
    private int followerEnable;         //关注我的是否公开
    private int aboutNotice;         //关注我的是否公开
    private int updateNotice;         //关注我的是否公开
    private UserStory UserStory;

    public void setId(int id) {
        this.id = id;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setLoginToken(String loginToken) {
        LoginToken = loginToken;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setNoticeEnable(int noticeEnable) {
        this.noticeEnable = noticeEnable;
    }

    public void setFollowingEnable(int followingEnable) {
        this.followingEnable = followingEnable;
    }

    public void setFollowerEnable(int followerEnable) {
        this.followerEnable = followerEnable;
    }

    public void setAboutNotice(int aboutNotice) {
        this.aboutNotice = aboutNotice;
    }

    public void setUpdateNotice(int updateNotice) {
        this.updateNotice = updateNotice;
    }

    public void setUserStory(com.example.qjm3662.newproject.Data.UserStory userStory) {
        UserStory = userStory;
    }

    public static void setInstance(User instance) {
        User.instance = instance;
    }

    public int getId() {
        return id;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getSign() {
        return sign;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public String getLoginToken() {
        return LoginToken;
    }

    public int getSex() {
        return sex;
    }

    public int getNoticeEnable() {
        return noticeEnable;
    }

    public int getFollowingEnable() {
        return followingEnable;
    }

    public int getFollowerEnable() {
        return followerEnable;
    }

    public int getAboutNotice() {
        return aboutNotice;
    }

    public int getUpdateNotice() {
        return updateNotice;
    }

    public com.example.qjm3662.newproject.Data.UserStory getUserStory() {
        return UserStory;
    }

    //private int collectedStoriesCount;      //收藏的故事数目


   // {"id":1,
    // "mobile":"18340861710",
    // "avatar":"www.baidu.com",
    // "sign":"test",
    // "userName":"changed",
    // "token":"VPpBrnWtvWDbVMGXgCvYPBhnkWM=",
    // "sex":1,"
    // noticeEnable":1,
    // "followingEnable":1,
    // "followerEnable":1,
    // "aboutNotice":1,
    // "updateNotice":1,


    public static final String USER_ID = "userID";
    public static final String USER_TOKEN = "token";
    public static final String USER_MOBILE = "mobile";
    public static final String USER_AVATAR = "avatar";
    public static final String USER_SIGN = "sign";
    public static final String USER_SEX = "sex";

    public static final String USER_USER_NAME = "userName";
    public static final String USER_LOGIN_TOKEN = "LoginToken";
    public static final String USER_NOTICE_ABLE = "noticeEnable";
    public static final String USER_FOLLOWINGENABLE = "followingEnable";
    public static final String USER_FOLLOWERENABLE = "followerEnable";
    public static final String USER_COLLECTED_STORIES_COUNT = "collectedStoriesCount";


    private static User instance = null;

    public static User getInstance(){
        if(instance == null){
            instance = new User();
        }
        return instance;
    }

}
