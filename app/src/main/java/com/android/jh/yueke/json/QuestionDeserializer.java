package com.android.jh.yueke.json;

import com.android.jh.yueke.entity.Question;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by Administrator on 2015/11/18.
 */
public class QuestionDeserializer implements JsonDeserializer<Question>{
    @Override
    public Question deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String id = json.getAsJsonObject().get("id").getAsString();
        String questionName = json.getAsJsonObject().get("question_name").getAsString();
        return new Question(id,questionName);
    }
}
