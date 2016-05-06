package com.android.jh.yueke.fragment.settings;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.jh.yueke.R;
import com.android.jh.yueke.utils.ViewUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainSettingsFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private TextView msgAndMailTv;
    private TextView pushMsgTv;
    private CheckBox nonePicsCb;
    private LinearLayout wifiAutoLoadImgLl;
    private CheckBox wifiAutoLoadImgCb;
    private CheckBox shakeFeedbackCb;

    public MainSettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_settings, container, false);

        msgAndMailTv = (TextView) view.findViewById(R.id.msg_mail_tv);
        msgAndMailTv.setOnClickListener(this);

        pushMsgTv = (TextView) view.findViewById(R.id.push_msg_tv);
        pushMsgTv.setOnClickListener(this);

        nonePicsCb = (CheckBox) view.findViewById(R.id.none_pics_cb);
        nonePicsCb.setOnCheckedChangeListener(this);
        wifiAutoLoadImgLl = (LinearLayout) view.findViewById(R.id.wifi_auto_load_img_ll);
        wifiAutoLoadImgCb = (CheckBox) view.findViewById(R.id.wifi_auto_load_img_cb);
        wifiAutoLoadImgCb.setOnCheckedChangeListener(this);
        shakeFeedbackCb = (CheckBox) view.findViewById(R.id.shake_feedback_cb);
        shakeFeedbackCb.setOnCheckedChangeListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        switch (v.getId()) {
            case R.id.msg_mail_tv:
                fm.beginTransaction().replace(R.id.swipe_close_content_container, new MsgSettingsFragment()).commit();
                break;
            case R.id.push_msg_tv:
                fm.beginTransaction().replace(R.id.swipe_close_content_container, new MsgSettingsFragment()).commit();
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SharedPreferences sp = getActivity().getSharedPreferences("Config", Context.MODE_PRIVATE);
        boolean isNonePics = sp.getBoolean("none_pics", false);
        nonePicsCb.setChecked(isNonePics);
        wifiAutoLoadImgCb.setChecked(sp.getBoolean("wifi_auto_load_img", false));
        if (isNonePics) {
            ViewUtils.disableViewGroup(wifiAutoLoadImgLl);
        }
        shakeFeedbackCb.setChecked(sp.getBoolean("shake_feedback", false));
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        SharedPreferences sp = getActivity().getSharedPreferences("Config", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        switch (buttonView.getId()) {
            case R.id.none_pics_cb:
                if (isChecked) {
                    ViewUtils.disableViewGroup(wifiAutoLoadImgLl);
                }else{
                    ViewUtils.enableViewGroup(wifiAutoLoadImgLl);
                }
                editor.putBoolean("none_pics", isChecked);
                break;
            case R.id.wifi_auto_load_img_cb:
                editor.putBoolean("wifi_auto_load_img", isChecked);
                break;
            case R.id.shake_feedback_cb:
                editor.putBoolean("shake_feedback", isChecked);
                break;
        }
        editor.commit();
    }


}
