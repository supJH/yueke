package com.android.jh.yueke.entity;

/**
 * Created by Administrator on 2016/4/28.
 */
public class HeadLine {

    private String postid;
    private String docid;

    private String title;
    private String digest;
    private String source;
    private String ptime;
    private String imgsrc;

    private int replyCount;

    public HeadLine(String postid, String docid, String title, String digest, String source, String ptime, String imagesrc, int replyCount) {
        this.postid = postid;
        this.docid = docid;
        this.title = title;
        this.digest = digest;
        this.source = source;
        this.ptime = ptime;
        this.imgsrc = imagesrc;
        this.replyCount = replyCount;
    }

    public String getImagesrc() {
        return imgsrc;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public String getPostid() {
        return postid;
    }

    public String getDocid() {
        return docid;
    }

    public String getTitle() {
        return title;
    }

    public String getDigest() {
        return digest;
    }

    public String getSource() {
        return source;
    }

    public String getPtime() {
        return ptime;
    }


    public int getReplycount() {
        return replyCount;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null) return false;
        if (this == o) return true;

        if (o.getClass() != this.getClass()) {
            return false;
        }
        HeadLine h = (HeadLine) o;
        return this.docid == h.docid;
    }

    @Override
    public int hashCode() {
        return docid.hashCode();
    }
}

