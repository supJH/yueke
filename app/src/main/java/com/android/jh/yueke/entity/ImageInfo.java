package com.android.jh.yueke.entity;

/**
 * Created by Administrator on 2016/5/5.
 */
public class ImageInfo {
    private String alt;
    private String src;

    public ImageInfo(String alt, String src) {
        this.alt = alt;
        this.src = src;
    }

    public String getAlt() {
        return alt;
    }

    public String getSrc() {
        return src;
    }
}
