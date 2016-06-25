package com.example.qjm3662.newproject.Data;

/**
 * Created by qjm3662 on 2016/6/24 0024.
 */
public class UserBase {
    private int id;
    private String mobile;
    private String avatar = "111";
    private String sign;                    //签名
    private String userName;
    private String token;
    private String LoginToken;
    private int sex;
    private int followingEnable;        //我正在关注的是否公开
    private int followerEnable;         //关注我的是否公开
    private int noticeEnable;
    private int aboutNotice;         //关注我的是否公开
    private int updateNotice;         //关注我的是否公开


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



    public void setAboutNotice(int aboutNotice) {
        this.aboutNotice = aboutNotice;
    }

    public void setUpdateNotice(int updateNotice) {
        this.updateNotice = updateNotice;
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



    public int getAboutNotice() {
        return aboutNotice;
    }

    public int getUpdateNotice() {
        return updateNotice;
    }

    public int getFollowingEnable() {
        return followingEnable;
    }

    public void setFollowingEnable(int followingEnable) {
        this.followingEnable = followingEnable;
    }

    public int getFollowerEnable() {
        return followerEnable;
    }

    public void setFollowerEnable(int followerEnable) {
        this.followerEnable = followerEnable;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
