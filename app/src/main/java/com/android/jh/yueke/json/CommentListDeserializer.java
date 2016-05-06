package com.android.jh.yueke.json;

import com.android.jh.yueke.entity.Comment;
import com.android.jh.yueke.entity.CommentList;
import com.android.jh.yueke.entity.YuekeContents;
import com.android.jh.yueke.entity.YuekeDetail;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/5.
 */
public class CommentListDeserializer implements JsonDeserializer<CommentList> {



    @Override
    public CommentList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        List<Comment> commentList = new ArrayList<>();

        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            Comment comment = context.deserialize(entry.getValue(), Comment.class);
            commentList.add(comment);
        }
        return new CommentList(commentList);

    }
}
