package com.example.qjm3662.newproject.Data;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by qjm3662 on 2016/5/29 0029.
 */
public class Story {
    String id;
    String title;
    String flags;               //故事的标签
    String content;             //故事的内容
    boolean publicEnable;       //是否公开
    Calendar createdAt;         //创建时间
    Calendar updatedAt;         //上传（更新）时间
    int AuthorID;               //作者的Id
    int likeCount;              //点赞总数
    boolean isOwn;              //是否是自己的故事

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

    public boolean isPublicEnable() {
        return publicEnable;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public Calendar getUpdatedAt() {
        return updatedAt;
    }

    public int getAuthorID() {
        return AuthorID;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public boolean isOwn() {
        return isOwn;
    }

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

    public void setPublicEnable(boolean publicEnable) {
        this.publicEnable = publicEnable;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Calendar updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setAuthorID(int authorID) {
        AuthorID = authorID;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public void setOwn(boolean own) {
        isOwn = own;
    }
}
