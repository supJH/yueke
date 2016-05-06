package com.android.jh.yueke.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Relcon on 2015/11/18.
 */
public class Topic {
    private String id;
    @SerializedName("topic_image_url")
    private String topicImageUrl;
    @SerializedName("topic_name")
    private String topicName;


    public Topic(String id, String topicImageUrl, String topicName) {
        this.id = id;
        this.topicImageUrl = topicImageUrl;
        this.topicName = topicName;
    }

    public String getId() {
        return id;
    }

    public String getTopicImageUrl() {
        return topicImageUrl;
    }

    public String getTopicName() {
        return topicName;
    }
}
