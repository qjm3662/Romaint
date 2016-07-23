package com.example.qjm3662.newproject.Data;

/**
 * Created by qjm3662 on 2016/7/17 0017.
 */
public class CommentItem {

    private int id;
    private String content;
    private String updatedAt;
    private String createdAt;
    private String RevCommentId;
    private String UserId;
    private String StoryId;

    public CommentItem() {
        this.createdAt = String.valueOf(System.currentTimeMillis()/1000);
    }

    private UserBase userBase;

    @Override
    public String toString() {
        return "CommentItem{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", RevCommentId='" + RevCommentId + '\'' +
                ", UserId='" + UserId + '\'' +
                ", StoryId='" + StoryId + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getRevCommentId() {
        return RevCommentId;
    }

    public void setRevCommentId(String revCommentId) {
        RevCommentId = revCommentId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getStoryId() {
        return StoryId;
    }

    public void setStoryId(String storyId) {
        StoryId = storyId;
    }

    public UserBase getUserBase() {
        return userBase;
    }

    public void setUserBase(UserBase userBase) {
        this.userBase = userBase;
    }
}
