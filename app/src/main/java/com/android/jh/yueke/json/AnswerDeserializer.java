package com.android.jh.yueke.json;

import com.android.jh.yueke.entity.Answer;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by Administrator on 2015/11/18.
 */
public class AnswerDeserializer implements JsonDeserializer<Answer>{
    @Override
    public Answer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String id = json.getAsJsonObject().get("id").getAsString();
        String userId = json.getAsJsonObject().get("user_id").getAsString();
        String answerContent = json.getAsJsonObject().get("answer_content").getAsString();
        int voteUp = json.getAsJsonObject().get("vote_up").getAsInt();

        return new Answer(id,answerContent,voteUp,userId);
    }
}
