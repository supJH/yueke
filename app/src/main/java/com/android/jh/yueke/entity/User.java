package com.android.jh.yueke.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Relcon on 2015/11/18.
 */
public class User {
    private String id;
    @SerializedName("user_name")
    private String userName;

    public User(String id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
