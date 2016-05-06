package com.android.jh.yueke.fragment.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.jh.yueke.R;
import com.android.jh.yueke.fragment.explore.HotColtFragment;
import com.android.jh.yueke.fragment.explore.HotMonthTodayFragment;


public class ExploreFragment extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager mPager;

    private String[] mTabNameList = new String[]{"热门推荐", "热门收藏", "本月热榜", "今日热榜"};

    public ExploreFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        mPager = (ViewPager) view.findViewById(R.id.explore_view_pager);
        FragmentStatePagerAdapter mAdapter = new ExplorePagerAdapter(getChildFragmentManager());
        mPager.setAdapter(mAdapter);

        mTabLayout = (TabLayout) view.findViewById(R.id.explore_tab_layout);
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        for (int i = 0; i < mTabNameList.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(mTabNameList[i]));
        }

        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        return view;
    }

    public class ExplorePagerAdapter extends FragmentStatePagerAdapter {

        public ExplorePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new HotMonthTodayFragment();
                    break;
                case 1:
                    fragment = new HotColtFragment();
                    break;
                case 2:
                    fragment = new HotMonthTodayFragment();
                    break;
                case 3:
                    fragment = new HotMonthTodayFragment();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return mTabNameList.length;
        }
    }

}
