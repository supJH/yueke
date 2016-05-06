package com.android.jh.yueke.fragment.follow;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.jh.yueke.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FollowColumnFragment extends Fragment {
    public static final String FRAGMENT_NAME = "FollowColumnFragment";

    public FollowColumnFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_follow_recycler, container, false);
    }


}
