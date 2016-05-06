package com.android.jh.yueke.json;

import com.android.jh.yueke.entity.ImageInfo;
import com.android.jh.yueke.entity.YuekeContents;
import com.android.jh.yueke.entity.YuekeDetail;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/18.
 */
public class YuekeContentsDeserializer implements JsonDeserializer<YuekeContents> {
    private String body = "";
    private boolean picnews = false;
    private List<ImageInfo> img;
    private String replyBoard = "";

    @Override
    public YuekeContents deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        try {
            body = json.getAsJsonObject().get("body").getAsString();
            picnews = json.getAsJsonObject().get("picnews").getAsBoolean();
            JsonObject jsonObject = json.getAsJsonObject().get("img").getAsJsonObject();
            List<ImageInfo> img = new ArrayList<>();
            for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                ImageInfo imageInfo = context.deserialize(entry.getValue(), ImageInfo.class);
                img.add(imageInfo);
            }
            replyBoard = json.getAsJsonObject().get("replyBoard").getAsString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new YuekeContents(body, img,picnews,replyBoard);


    }
}
