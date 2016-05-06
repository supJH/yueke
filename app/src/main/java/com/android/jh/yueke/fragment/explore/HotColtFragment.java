package com.android.jh.yueke.fragment.explore;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.jh.yueke.R;
import com.android.jh.yueke.adapter.ExploreRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotColtFragment extends Fragment {

    public static final String FRAGMENT_NAME = "HotColtFragment";
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private List mList;
    private ExploreRecyclerAdapter mAdapter;

    public HotColtFragment() {
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

        mRecyclerView = (RecyclerView) view.findViewById(R.id.explore_recycler);

        mList = new ArrayList();
        for (int i = 0; i < 10; i++) {
            mList.add("" + i);
        }

        mAdapter = new ExploreRecyclerAdapter(mList, FRAGMENT_NAME);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }


}
