package com.android.jh.yueke.utils;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Relcon on 2015/11/26.
 */
public class ViewUtils {
    public static void disableViewGroup(ViewGroup viewGroup){
        for (int i = 0 ;i < viewGroup.getChildCount();i++){
            View childView = viewGroup.getChildAt(i);
            if (childView instanceof  ViewGroup){
                disableViewGroup((ViewGroup)childView);
            }else{
                childView.setEnabled(false);
            }
        }
    }

    public static void enableViewGroup(ViewGroup viewGroup){
        for (int i = 0 ;i < viewGroup.getChildCount();i++){
            View childView = viewGroup.getChildAt(i);
            if (childView instanceof  ViewGroup){
                disableViewGroup((ViewGroup)childView);
            }else{
                childView.setEnabled(true);
            }
        }
    }

}
