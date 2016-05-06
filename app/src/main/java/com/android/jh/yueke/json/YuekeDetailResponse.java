package com.android.jh.yueke.json;

import com.android.jh.yueke.entity.ImageInfo;
import com.android.jh.yueke.entity.YuekeContents;
import com.android.jh.yueke.entity.YuekeDetail;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.util.Map;

/**
 * Created by Administrator on 2016/5/5.
 */
public class YuekeDetailResponse {

    private Map<String,YuekeContents> contents;

    public YuekeDetailResponse(Map<String, YuekeContents> contents) {
        this.contents = contents;
    }

    public Map<String, YuekeContents> getContents() {
        return contents;
    }

    public static YuekeDetailResponse parseJSON(String responseStr) {

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(YuekeDetail.class, new YuekeDetailDeserializer());

        Gson gson = builder.create();


       YuekeDetail yuekeDetail = null;
        try {
            yuekeDetail = gson.fromJson(responseStr, YuekeDetail.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return new YuekeDetailResponse(yuekeDetail.getMap());
    }
}
