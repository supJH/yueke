package com.android.jh.yueke.entity;

import com.android.jh.yueke.fragment.settings.MainSettingsFragment;

import java.util.Map;

/**
 * Created by Administrator on 2016/5/5.
 */
public class YuekeDetail {

    private Map<String,YuekeContents> map;

    public YuekeDetail(Map<String, YuekeContents> map) {
        this.map = map;
    }

    public Map<String, YuekeContents> getMap() {
        return map;
    }
}
