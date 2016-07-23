package com.example.qjm3662.newproject.Data;

import java.util.List;

/**
 * Created by qjm3662 on 2016/7/17 0017.
 */
public class CommentItem_add extends CommentItem{
    private List<CommentItem> RevComment;

    public List<CommentItem> getRevComment() {
        return RevComment;
    }

    public void setRevComment(List<CommentItem> revComment) {
        RevComment = revComment;
    }
}
