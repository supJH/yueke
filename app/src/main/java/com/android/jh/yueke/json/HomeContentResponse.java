package com.android.jh.yueke.json;

import com.android.jh.yueke.entity.Answer;
import com.android.jh.yueke.entity.HeadLine;
import com.android.jh.yueke.entity.Question;
import com.android.jh.yueke.entity.Topic;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Relcon on 2015/11/18.
 */
public class HomeContentResponse {
    @SerializedName("home_contents")
    private List<HeadLine> contents;

    public List<HeadLine> getContents() {
        return contents;
    }

    public HomeContentResponse() {
        this.contents = new ArrayList<HeadLine>();
    }

    public static HomeContentResponse parseJSON(String responseStr) {

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Question.class, new QuestionDeserializer());
        builder.registerTypeAdapter(Answer.class, new AnswerDeserializer());

        Gson gson = builder.create();

        HomeContentResponse response = gson.fromJson(responseStr, HomeContentResponse.class);
        return response;
    }

}
