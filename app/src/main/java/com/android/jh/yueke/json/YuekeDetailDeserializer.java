package com.android.jh.yueke.json;

import com.android.jh.yueke.entity.Answer;
import com.android.jh.yueke.entity.YuekeContents;
import com.android.jh.yueke.entity.YuekeDetail;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/18.
 */
public class YuekeDetailDeserializer implements JsonDeserializer<YuekeDetail> {
    @Override
    public YuekeDetail deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        YuekeContents yuekeContents = null;
        String key = null;
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            yuekeContents = context.deserialize(entry.getValue(), YuekeContents.class);
            key = entry.getKey();
        }
        Map<String, YuekeContents> map = new HashMap<>();
        map.put(key, yuekeContents);
        return new YuekeDetail(map);
    }
}
