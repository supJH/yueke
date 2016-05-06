package com.android.jh.yueke.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Relcon on 2015/11/19.
 */
public class NetAccessUtils {

    private static NetworkInfo getNetworkInfo(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return  manager.getActiveNetworkInfo();
    }


    public static boolean isNetworkAccess(Context context){
        NetworkInfo info = getNetworkInfo(context);
        return info != null && info.isConnectedOrConnecting();
    }

    public static boolean isWifi(Context context){
        NetworkInfo info = getNetworkInfo(context);
        if(info != null && info.isConnectedOrConnecting()){
            return false;
        }

        return info.getType() == ConnectivityManager.TYPE_WIFI;

    }

}
