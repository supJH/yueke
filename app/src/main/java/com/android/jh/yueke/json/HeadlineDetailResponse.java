package com.android.jh.yueke.json;

import com.android.jh.yueke.entity.HeadLine;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/5.
 */
public class HeadlineDetailResponse {

    @SerializedName("T1348647909107")
    private List<HeadLine> contents;

    public List<HeadLine> getContents() {
        return contents;
    }

    public HeadlineDetailResponse() {
        this.contents = new ArrayList<HeadLine>();
    }

    public static HeadlineDetailResponse parseJSON(String responseStr) {

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(HeadLine.class, new HeadlineDeserializer());

        Gson gson = builder.create();

        HeadlineDetailResponse response = null;
        try {
            response = gson.fromJson(responseStr, HeadlineDetailResponse.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return response;
    }
}
