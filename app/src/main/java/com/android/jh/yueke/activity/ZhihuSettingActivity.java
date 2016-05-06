package com.android.jh.yueke.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.android.jh.yueke.R;
import com.android.jh.yueke.fragment.settings.MainSettingsFragment;

public class ZhihuSettingActivity extends SwipeCloseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_zhihu_setting);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("设置");

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.swipe_close_content_container,new MainSettingsFragment()).commit();
    }

}
