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
import com.android.jh.yueke.entity.HeadLine;
import com.squareup.picasso.Picasso;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Relcon on 2015/11/10.
 */
public class HomeRecyclerAdapter extends RecyclerView.Adapter {
    private List<HeadLine> mList;
    private Set<String> remeberSet = new HashSet<>();
    private Context mContext;

    private String imagesrc;

    public static final String POST_ID = "post_id";
    public static final String SOURCE = "source";
    public static final String TITLE = "title";
    public static final String P_TIME = "p_time";

    public HomeRecyclerAdapter(Context context, List list) {
        mList = list;
        for (HeadLine h : mList) {
            remeberSet.add(h.getDocid());
        }
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.home_recycler_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(context, view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HeadLine headline = mList.get(position);
        imagesrc = headline.getImagesrc();
        ((ViewHolder) holder).titleTv.setText(headline.getTitle());
        ((ViewHolder) holder).digestTv.setText(headline.getDigest());
        ((ViewHolder) holder).replyCountTv.setText(headline.getReplycount() + "跟帖");
        ((ViewHolder) holder).headLine = headline;
        SharedPreferences sp = mContext.getSharedPreferences("Config",Context.MODE_PRIVATE);
        if (!sp.getBoolean("none_pics",false)){
            Picasso.with(mContext).load(imagesrc).into(((ViewHolder) holder).image);

        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addAll(List<HeadLine> list) {
        for (HeadLine h : list) {
            if (remeberSet.add(h.getDocid()))
                mList.add(h);
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Context mContext;
        private LinearLayout headlineLl;
        private ImageView image;
        private TextView titleTv;
        private TextView digestTv;
        private TextView replyCountTv;
        private HeadLine headLine;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        public ViewHolder(Context context, View itemView) {
            super(itemView);
            mContext = context;

            titleTv = (TextView) itemView.findViewById(R.id.headline_title);
            digestTv = (TextView) itemView.findViewById(R.id.headline_digest);
            replyCountTv = (TextView) itemView.findViewById(R.id.headline_reply_count);
            image = (ImageView) itemView.findViewById(R.id.headline_image);

            headlineLl = (LinearLayout) itemView.findViewById(R.id.headline_ll);

            headlineLl.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.headline_ll:
                    //Toast.makeText(mContext, "Content被点击", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, YuekeDetailActivity.class);
                    intent.putExtra(TITLE,headLine.getTitle());
                    intent.putExtra(POST_ID, headLine.getPostid());
                    intent.putExtra(SOURCE, headLine.getSource());
                    intent.putExtra(P_TIME, headLine.getPtime());
                    mContext.startActivity(intent);
                    ((Activity) mContext).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    break;
                default:
                    break;
            }
        }
    }
}
