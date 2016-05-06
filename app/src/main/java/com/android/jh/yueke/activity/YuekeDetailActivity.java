package com.android.jh.yueke.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.android.jh.yueke.R;
import com.android.jh.yueke.adapter.HomeRecyclerAdapter;
import com.android.jh.yueke.fragment.main.YuekeDetailFragment;
import com.android.jh.yueke.view.SwipeCloseFrameLayout;

public class YuekeDetailActivity extends SwipeCloseActivity {
//    private RecyclerView recyclerView;
//    private DetailAnswerRecyclerAdapter mAdapter;
//    private List<String> contentItems = new ArrayList<String>();
//    private TextView mQusetionTv;

    private SwipeCloseFrameLayout swipeCloseFrameLayout;

    public SwipeCloseFrameLayout getSwipeCloseFrameLayout() {
        return swipeCloseFrameLayout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        swipeCloseFrameLayout = (SwipeCloseFrameLayout) findViewById(R.id.swipe_close_content_container);

        Intent intent = getIntent();
        String title = intent.getStringExtra(HomeRecyclerAdapter.TITLE);
        String source = intent.getStringExtra(HomeRecyclerAdapter.SOURCE);
        String ptime = intent.getStringExtra(HomeRecyclerAdapter.P_TIME);
        String postid = intent.getStringExtra(HomeRecyclerAdapter.POST_ID);
        YuekeDetailFragment yuekeDetailFragment = YuekeDetailFragment.newInstance(title, source, ptime, postid);

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.swipe_close_content_container, yuekeDetailFragment).commit();

    }


}
