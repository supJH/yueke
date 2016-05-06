package com.android.jh.yueke.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.jh.yueke.R;
import com.android.jh.yueke.activity.YuekeDetailActivity;
import com.android.jh.yueke.entity.Comment;
import com.android.jh.yueke.entity.HeadLine;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Relcon on 2015/11/10.
 */
public class HotCommentRecyclerAdapter extends RecyclerView.Adapter {
    private List<Comment> mList = new ArrayList<>();
    private Set<String> remeberSet = new HashSet<>();
    private Context mContext;

    private String imagesrc;
    private String userName;
    private String voteNum;
    private String commentInfo;
    private String commentContent;

    public static final String POST_ID = "post_id";
    public static final String SOURCE = "source";
    public static final String TITLE = "title";
    public static final String P_TIME = "p_time";

    public HotCommentRecyclerAdapter(Context context) {
        mContext = context;
    }

    public HotCommentRecyclerAdapter(Context context, List list) {
        mList = list;
        for (Comment c : mList) {
            remeberSet.add(c.getId());
        }
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.comment_recycler_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(context, view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Comment comment = mList.get(position);
        imagesrc = comment.getUserImg();
        userName = comment.getUserName();
        voteNum = comment.getVoteNum();
        commentInfo = comment.getCommentInfo();
        commentContent = comment.getCommentContent();


        ((ViewHolder) holder).userNameTv.setText(comment.getUserName());
        ((ViewHolder) holder).voteCountTv.setText(comment.getVoteNum());
        ((ViewHolder) holder).commentInfoTv.setText(comment.getCommentInfo());
        ((ViewHolder) holder).commentContentTv.setText(comment.getCommentContent());


        SharedPreferences sp = mContext.getSharedPreferences("Config",Context.MODE_PRIVATE);
        if (!sp.getBoolean("none_pics",false) && imagesrc != null && imagesrc.length() > 0){
            Picasso.with(mContext).load(imagesrc).into(((ViewHolder) holder).userIv);
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addAll(List<Comment> list) {
        for (Comment c : list) {
            if (remeberSet.add(c.getId()))
                mList.add(c);
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Context mContext;
        private LinearLayout headlineLl;
        private ImageView userIv;
        private TextView userNameTv;
        private TextView voteCountTv;
        private TextView commentInfoTv;
        private TextView commentContentTv;
        private Comment comment;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        public ViewHolder(Context context, View itemView) {
            super(itemView);
            mContext = context;
            userIv = (ImageView) itemView.findViewById(R.id.comment_user_image);

            userNameTv = (TextView) itemView.findViewById(R.id.comment_user_name);
            voteCountTv = (TextView) itemView.findViewById(R.id.comment_vote_count);
            commentInfoTv = (TextView) itemView.findViewById(R.id.comment_location_and_time);
            commentContentTv = (TextView) itemView.findViewById(R.id.comment_location_and_time);


        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                default:
                    break;
            }
        }
    }
}
