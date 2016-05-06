package com.android.jh.yueke.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.android.jh.yueke.R;
import com.android.jh.yueke.adapter.HomeRecyclerAdapter;
import com.android.jh.yueke.fragment.main.CommentFragment;
import com.android.jh.yueke.fragment.main.YuekeDetailFragment;
import com.android.jh.yueke.view.SwipeCloseFrameLayout;

public class CommentActivity extends SwipeCloseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("评论");

        SwipeCloseFrameLayout swipeCloseFrameLayout = (SwipeCloseFrameLayout) findViewById(R.id.swipe_close_content_container);

        Intent intent = getIntent();
        String replyBoard = intent.getStringExtra(YuekeDetailFragment.REPLY_BOARD);
        String postid  = intent.getStringExtra(HomeRecyclerAdapter.POST_ID);
        CommentFragment commentFragment = CommentFragment.newInstance(replyBoard,postid);

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.swipe_close_content_container, commentFragment).commit();


    }

}
