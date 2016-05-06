package com.android.jh.yueke.json;

import com.android.jh.yueke.entity.Answer;
import com.android.jh.yueke.entity.Question;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Relcon on 2015/11/20.
 */
public class AnswerDetailResponse {
    private Answer answer;

    public Question getQuestion() {
        return question;
    }

    public Answer getAnswer() {
        return answer;
    }

    private Question question;
    public static AnswerDetailResponse parseJSON(String responseStr){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Answer.class,new AnswerDeserializer());
        builder.registerTypeAdapter(Question.class,new QuestionDeserializer());
        Gson gson = builder.create();

        AnswerDetailResponse response = gson.fromJson(responseStr,AnswerDetailResponse.class);
        return response;
    }
}
