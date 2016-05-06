package com.android.jh.yueke.net;

import android.content.Context;

import com.android.jh.yueke.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

/**
 * Created by Relcon on 2015/11/18.
 */
public class YuekeClient {
    private AsyncHttpClient client;
    private Context context;

    public YuekeClient(Context context) {
        this.context = context;
        this.client = new AsyncHttpClient();
        client.setConnectTimeout(5000);
        client.setResponseTimeout(5000);
    }


    public void getYuekeContents(String path, JsonHttpResponseHandler handler) {
        String baseUrl = context.getResources().getString(R.string.base_url);
        String url = baseUrl + path;
        client.get(url, handler);
    }

    public void getCommentContents(String path, JsonHttpResponseHandler handler) {
        String baseUrl = context.getResources().getString(R.string.comment_base_url);
        String url = baseUrl + path;
        client.get(url, handler);
    }


}
