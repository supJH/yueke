package com.android.jh.yueke.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Relcon on 2015/11/18.
 */
public class Answer {
    private String id;
    @SerializedName("answer_content")
    private String answerContent;
    @SerializedName("vote_up")
    private int voteUp;
    @SerializedName("user_id")
    private String userId;

    public Answer(String id, String answerContent, int voteUp, String userId) {
        this.id = id;
        this.answerContent = answerContent;
        this.voteUp = voteUp;
        this.userId = userId;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public int getVoteUp() {
        return voteUp;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }
}
