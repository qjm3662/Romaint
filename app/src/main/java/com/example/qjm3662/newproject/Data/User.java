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
    private boolean noticeEnable;
    private boolean followingEnable;        //我正在关注的是否公开
    private boolean followerEnable;         //关注我的是否公开
    private int collectedStoriesCount;      //收藏的故事数目


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

    public int getSex() {
        return sex;
    }

    public boolean isNoticeEnable() {
        return noticeEnable;
    }

    public boolean isFollowingEnable() {
        return followingEnable;
    }

    public boolean isFollowerEnable() {
        return followerEnable;
    }

    public int getCollectedStoriesCount() {
        return collectedStoriesCount;
    }

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

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setNoticeEnable(boolean noticeEnable) {
        this.noticeEnable = noticeEnable;
    }

    public void setFollowingEnable(boolean followingEnable) {
        this.followingEnable = followingEnable;
    }

    public void setFollowerEnable(boolean followerEnable) {
        this.followerEnable = followerEnable;
    }

    public void setCollectedStoriesCount(int collectedStoriesCount) {
        this.collectedStoriesCount = collectedStoriesCount;
    }

    public static void setInstance(User instance) {
        User.instance = instance;
    }

    public String getLoginToken() {
        return LoginToken;
    }

    public void setLoginToken(String loginToken) {
        LoginToken = loginToken;
    }
}
