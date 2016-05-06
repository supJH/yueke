package com.android.jh.yueke.json;

import com.android.jh.yueke.entity.Comment;
import com.android.jh.yueke.entity.HeadLine;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Type;

/**
 * Created by Administrator on 2016/5/5.
 */
public class CommentDeserializer implements JsonDeserializer<Comment> {

    private String userName;
    private String userImg;
    private String commentTime;
    private String commentContent;
    private String commentInfo;
    private String voteNum;
    private String id;


    @Override
    public Comment deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            userName = json.getAsJsonObject().get("n").getAsString();
            userImg = json.getAsJsonObject().get("timg").getAsString();
            commentTime = json.getAsJsonObject().get("t").getAsString();
            commentContent = json.getAsJsonObject().get("b").getAsString();
            commentInfo = json.getAsJsonObject().get("f").getAsString();
            voteNum = json.getAsJsonObject().get("v").getAsString();
            id = json.getAsJsonObject().get("p").getAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Comment(userName,userImg,commentTime,commentContent,commentInfo,voteNum,id);
    }
}
