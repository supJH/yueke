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
import com.android.jh.yueke.fragment.follow.FollowQuestionFragment;
import com.android.jh.yueke.fragment.follow.FollowTopicFragment;

public class FollowFragment extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager mPager;
    private String[] tabNameList = new String[]{"关注的问题", "关注的收藏", "关注的话题", "关注的专栏"};

    public FollowFragment() {
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

        View view = inflater.inflate(R.layout.fragment_follow, container, false);

        mPager = (ViewPager) view.findViewById(R.id.follow_pager);

        mTabLayout = (TabLayout) view.findViewById(R.id.follow_tab);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        for (int i = 0; i < tabNameList.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(tabNameList[i]));
        }
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

        FollowPagerAdapter mAdapter = new FollowPagerAdapter(getChildFragmentManager());
        mPager.setAdapter(mAdapter);
        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        return view;
    }

    public class FollowPagerAdapter extends FragmentStatePagerAdapter {

        public FollowPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            Class fragmentClass = null;
            switch (position) {
                case 0:
                    fragmentClass = FollowQuestionFragment.class;
                    break;
                case 1:
                    fragmentClass = FollowQuestionFragment.class;
                    break;
                case 2:
                    fragmentClass = FollowTopicFragment.class;
                    break;
                case 3:
                    fragmentClass = FollowQuestionFragment.class;
                    break;
                default:
                    break;
            }
            if (null != fragmentClass) {
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return tabNameList.length;
        }
    }

}
