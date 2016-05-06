package com.android.jh.yueke.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/5/5.
 */
public class YuekeContents {
    private String body;
    private List<ImageInfo> img;
    private boolean picnews;
    private String replyBoard;

    public YuekeContents(String body, List<ImageInfo> img, boolean picnews, String replyBoard) {
        this.body = body;
        this.img = img;
        this.picnews = picnews;
        this.replyBoard = replyBoard;
    }

    public String getBody() {
        return body;
    }

    public List<ImageInfo> getImg() {
        return img;
    }

    public boolean isPicnews() {
        return picnews;
    }

    public String getReplyBoard() {
        return replyBoard;
    }
}
