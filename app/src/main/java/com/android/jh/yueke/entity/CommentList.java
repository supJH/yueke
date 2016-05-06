package com.android.jh.yueke.entity;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/6.
 */
public class CommentList {
    private List<Comment> commentList;

    public CommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }
}
