package com.android.jh.yueke.fragment.explore;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.jh.yueke.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotRecmdFragment extends Fragment {

    public static final String FRAGMENT_NAME = "HotRecmdFragment";
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public HotRecmdFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_explore_recycler, container, false);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.explore_swipe_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));

        return view;
    }
}
