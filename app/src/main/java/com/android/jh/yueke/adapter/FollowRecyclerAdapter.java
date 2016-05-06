package com.android.jh.yueke.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.jh.yueke.R;
import com.android.jh.yueke.fragment.follow.FollowQuestionFragment;
import com.android.jh.yueke.fragment.follow.FollowTopicFragment;

import java.util.List;

/**
 * Created by Administrator on 2015/11/10.
 */
public class FollowRecyclerAdapter extends RecyclerView.Adapter{
    private List mList;
    private String mActivateFragmentName;

    public FollowRecyclerAdapter(List list,String activateFragmentName){
        mList = list;
        mActivateFragmentName = activateFragmentName;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = null;

        switch (mActivateFragmentName){
            case FollowQuestionFragment.FRAGMENT_NAME:
                view = inflater.inflate(R.layout.follow_question_recycler_item,parent,false);
                break;
            case FollowTopicFragment.FRAGMENT_NAME:
                view = inflater.inflate(R.layout.follow_topic_recycler_item,parent,false);
                break;
            default:
                break;
        }

        RecyclerView.ViewHolder holder = new FollowViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class FollowViewHolder extends  RecyclerView.ViewHolder{

        public FollowViewHolder(View itemView) {
            super(itemView);
        }
    }


}
