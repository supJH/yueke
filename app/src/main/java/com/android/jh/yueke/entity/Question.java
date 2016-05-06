package com.android.jh.yueke.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Relcon on 2015/11/18.
 */
public class Question {
    private String id;
    @SerializedName("question_name")
    private String questionName;

    public Question(String id, String questionName) {
        this.id = id;
        this.questionName = questionName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getQuestionName() {
        return questionName;
    }
}
