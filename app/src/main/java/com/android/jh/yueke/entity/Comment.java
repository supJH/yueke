package com.android.jh.yueke.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/5/6.
 */
public class Comment {

    @SerializedName("n")
    private String userName;

    @SerializedName("timg")
    private String userImg;

    @SerializedName("t")
    private String commentTime;

    @SerializedName("b")
    private String commentContent;

    @SerializedName("f")
    private String commentInfo;

    @SerializedName("v")
    private String voteNum;

    @SerializedName("p")
    private String id;

    public Comment(String userName, String userImg, String commentTime, String commentContent, String commentInfo, String voteNum, String id) {
        this.userName = userName;
        this.userImg = userImg;
        this.commentTime = commentTime;
        this.commentContent = commentContent;
        this.commentInfo = commentInfo;
        this.voteNum = voteNum;
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserImg() {
        return userImg;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public String getCommentInfo() {
        return commentInfo;
    }

    public String getVoteNum() {
        return voteNum;
    }

    public String getId() {
        return id;
    }
}

