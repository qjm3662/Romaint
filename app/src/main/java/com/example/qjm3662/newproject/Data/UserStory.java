package com.example.qjm3662.newproject.Data;

/**
 * Created by qjm3662 on 2016/6/9 0009.
 */
public class UserStory {
    //,"UserStory":
    // {"isLike":1,
    // "isCollection":1,
    // "UserId":1,
    // "StoryId":"e4efe255-ba30-4e38-b14a-ff259482f67f"}
    private int isLike;
    private int isCollection;
    private int UserId;
    private String StoryId;

    public void setIsLike(int isLike) {
        this.isLike = isLike;
    }

    public void setIsCollection(int isCollection) {
        this.isCollection = isCollection;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public void setStoryId(String storyId) {
        StoryId = storyId;
    }

    public int getIsLike() {
        return isLike;
    }

    public int getIsCollection() {
        return isCollection;
    }

    public int getUserId() {
        return UserId;
    }

    public String getStoryId() {
        return StoryId;
    }
}
