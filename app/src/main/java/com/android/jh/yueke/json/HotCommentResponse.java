package com.android.jh.yueke.json;

import com.android.jh.yueke.entity.Comment;
import com.android.jh.yueke.entity.CommentList;
import com.android.jh.yueke.entity.YuekeDetail;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016/5/6.
 */
public class HotCommentResponse {

    private List<CommentList> hotPosts;

    public HotCommentResponse(List<CommentList> hotPosts) {
        this.hotPosts = hotPosts;
    }

    public List<CommentList> getHotPosts() {
        return hotPosts;
    }

    public static HotCommentResponse parseJSON(String responseStr) {

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(CommentList.class, new CommentListDeserializer());
        builder.registerTypeAdapter(Comment.class,new CommentDeserializer());

        Gson gson = builder.create();

        HotCommentResponse hotCommentResponse = null;
        try {
            hotCommentResponse = gson.fromJson(responseStr, HotCommentResponse.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return  hotCommentResponse;
    }
}
