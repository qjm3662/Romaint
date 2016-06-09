package com.example.qjm3662.newproject.Data;

import java.util.List;

/**
 * Created by qjm3662 on 2016/6/9 0009.
 */
public class StoryBean {
    //int local_id;
    //    e4efe255-ba30-4e38-b14a-ff259482f67f"

    //{"updatedAt":
    // "1465184748",
    // "content":"\n<img\/storage\/emulated\/0\/Download\/389a95ca5f66f20f11dad194a3d2a242.png><\/img>\n",
    // "id":"27f1fda0-2b99-11e6-a72e-b5458f2b67eb",
    // "Users":[],"title":"sad a",
    // "flags":"故事",
    // "likeCount":0,
    // "createdAt":"1465184748",
    // "publicEnable":1,
    // "AuthorID":12}
    String id;
    String title;
    String flags;               //故事的标签
    String content;             //故事的内容
    int publicEnable;       //是否公开
    String createdAt;         //创建时间
    String updatedAt;         //上传（更新）时间
    int AuthorID;               //作者的Id
    int likeCount;              //点赞总数
    List<User> Users;

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPublicEnable(int publicEnable) {
        this.publicEnable = publicEnable;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setAuthorID(int authorID) {
        AuthorID = authorID;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public void setUsers(List<User> users) {
        Users = users;
    }


    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getFlags() {
        return flags;
    }

    public String getContent() {
        return content;
    }

    public int getPublicEnable() {
        return publicEnable;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public int getAuthorID() {
        return AuthorID;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public List<User> getUsers() {
        return Users;
    }
}
