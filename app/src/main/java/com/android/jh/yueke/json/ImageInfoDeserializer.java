package com.android.jh.yueke.json;


import com.android.jh.yueke.entity.ImageInfo;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by Administrator on 2016/5/5.
 */
public class ImageInfoDeserializer implements JsonDeserializer<ImageInfo> {

    private String alt = "";
    private String src = "";
    @Override
    public ImageInfo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            alt = json.getAsJsonObject().get("alt").getAsString();
            src = json.getAsJsonObject().get("src").getAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ImageInfo(alt,src);
    }
}
