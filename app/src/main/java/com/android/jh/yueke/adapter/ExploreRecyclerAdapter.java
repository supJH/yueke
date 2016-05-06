package com.android.jh.yueke.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.jh.yueke.R;
import com.android.jh.yueke.fragment.explore.HotColtFragment;
import com.android.jh.yueke.fragment.explore.HotMonthTodayFragment;

import java.util.List;

/**
 * Created by Relcon on 2015/11/11.
 */
public class ExploreRecyclerAdapter  extends RecyclerView.Adapter{
    private List mList;
    private String mActivateFragmentName;

    public ExploreRecyclerAdapter(List list,String activateFragmentName){
        mList = list;
        mActivateFragmentName = activateFragmentName;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = null;
        switch (mActivateFragmentName){
            case HotMonthTodayFragment.FRAGMENT_NAME:
                view = inflater.inflate(R.layout.explore_month_today_recycler_item,parent,false);
                break;
            case HotColtFragment.FRAGMENT_NAME:
                view = inflater.inflate(R.layout.explore_collect_recycler_item,parent,false);
                break;
            default:
                view = inflater.inflate(R.layout.explore_month_today_recycler_item,parent,false);
                break;
        }

        RecyclerView.ViewHolder viewHolder = new ExploreViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ExploreViewHolder extends RecyclerView.ViewHolder{

        public ExploreViewHolder(View itemView) {
            super(itemView);
        }
    }
}
