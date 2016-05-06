package com.android.jh.yueke.json;

import com.android.jh.yueke.entity.HeadLine;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by Administrator on 2016/5/5.
 */
public class HeadlineDeserializer implements JsonDeserializer<HeadLine> {

    private String postid = "";
    private String docid = "";
    private String title = "";
    private String digest = "";
    private String source = "";
    private String ptime = "";
    private String imgsrc = "";
    private int replyCount = 0;
    @Override
    public HeadLine deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            postid = json.getAsJsonObject().get("postid").getAsString();
            docid = json.getAsJsonObject().get("docid").getAsString();
            title = json.getAsJsonObject().get("title").getAsString();
            digest = json.getAsJsonObject().get("digest").getAsString();
            source = json.getAsJsonObject().get("source").getAsString();
            ptime = json.getAsJsonObject().get("ptime").getAsString();
            imgsrc = json.getAsJsonObject().get("imgsrc").getAsString();
            replyCount = json.getAsJsonObject().get("replyCount").getAsInt();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new HeadLine(postid,docid,title,digest,source,ptime,imgsrc,replyCount);
    }
}
