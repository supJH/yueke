package com.android.jh.yueke.utils;

import android.content.Context;

import com.android.jh.yueke.entity.HeadLine;
import com.android.jh.yueke.json.HeadlineDetailResponse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Relcon on 2015/12/4.
 */
public class CacheUtils {

    public static File getCacheDir(Context context) {
        return context.getCacheDir();
    }

    /**
     * when swipe to refresh, storage the data in the CacheDir.Before this,first delete the old cache file
     * @param context
     * @param cacheContent
     * @param cacheFileName
     */
    public static void saveCache(Context context, String cacheContent,String cacheFileName) {
        File cacheFile = new File(getCacheDir(context), cacheFileName);
        FileOutputStream fos = null;
        try {
           fos = new FileOutputStream(cacheFile);
            fos.write(cacheContent.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(fos != null){
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * delete the cache file;
     * <p>when you swipe to refresh,you should call this before save the data </p>
     * @param context
     * @param cacheFileName
     */
    public static void deleteCache(Context context,String cacheFileName) {
        File cacheFile = new File(getCacheDir(context),cacheFileName);
        if (cacheFile.exists()){
            cacheFile.delete();
        }
    }

    public static String getCacheStr(Context context,String cacheFileName){
        FileInputStream fis = null;
        BufferedReader br = null;
        String cacheContent = "";
        try {
            fis = new FileInputStream(new File(getCacheDir(context),cacheFileName));
            br = new BufferedReader(new InputStreamReader(fis));
            cacheContent = br.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null){
                try {
                    br.close();
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return cacheContent;
    }

    public static List<HeadLine> getCacheList(Context context, String cacheFileName){
        String cacheStr = getCacheStr(context,cacheFileName);
        if(cacheStr.length()> 0 ){
            HeadlineDetailResponse headlineDetailResponse = HeadlineDetailResponse.parseJSON(cacheStr);
            List<HeadLine> list = headlineDetailResponse.getContents();
            return list;
        }
        return null;
    }

}
