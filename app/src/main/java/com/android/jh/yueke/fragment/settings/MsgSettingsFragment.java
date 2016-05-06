package com.android.jh.yueke.fragment.settings;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.jh.yueke.R;
import com.android.jh.yueke.adapter.MsgSettingsAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MsgSettingsFragment extends Fragment {

    private ListView msgMailLv;
    private MsgSettingsAdapter mAdapter;

    public MsgSettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_msg_settings, container, false);
        msgMailLv = (ListView) view.findViewById(R.id.msg_mail_settings_lv);
        String[] msgMailSettings = getActivity().getResources().getStringArray(R.array.MsgMailSettingsTexts);
        mAdapter = new MsgSettingsAdapter(getActivity(),msgMailSettings);
        msgMailLv.setAdapter(mAdapter);
        return view;
    }

}
