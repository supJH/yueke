package com.android.jh.yueke.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.android.jh.yueke.R;
import com.android.jh.yueke.fragment.question.QuestionAddFragment;

public class AddQuestionActivity extends AppCompatActivity {

    private String[] mAddQestTabNames = new String[]{"问题", "描述", "话题"};

    private TabLayout mTabLayout;
    private ViewPager mPager;
    private AddQuestionPagerAdapter mAapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ;
        mPager = (ViewPager) findViewById(R.id.add_question_view_pager);

        mTabLayout = (TabLayout) findViewById(R.id.add_question_tab);
        for (int i = 0; i < mAddQestTabNames.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(mAddQestTabNames[i]));
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
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        mAapter = new AddQuestionPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mAapter);
        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

    }

    public class AddQuestionPagerAdapter extends FragmentStatePagerAdapter {

        public AddQuestionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return QuestionAddFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return mAddQestTabNames.length;
        }
    }

}
