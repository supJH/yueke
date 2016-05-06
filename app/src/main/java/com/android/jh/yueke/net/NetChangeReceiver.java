package com.android.jh.yueke.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;

/**
 * Created by Relcon on 2015/11/19.
 */
public class NetChangeReceiver extends BroadcastReceiver{

    public SwipeRefreshLayout mSwipeRefreshLayout;

    public NetChangeReceiver(SwipeRefreshLayout swipeRefreshLayout){
        mSwipeRefreshLayout = swipeRefreshLayout;
    }

    public NetChangeReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("网络发生改变！");
    }
}
