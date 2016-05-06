package com.android.jh.yueke.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.jh.yueke.R;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by Relcon on 2015/11/20.
 */
public class DetailAnswerRecyclerAdapter extends RecyclerView.Adapter {
    private List<String> mItemList;
    private Context mContext;

    public DetailAnswerRecyclerAdapter(Context context, List itemList) {
        mContext = context;
        mItemList = itemList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.answer_detail_recycler_item, parent, false);

        AnswerDetailItemViewHolder holder = new AnswerDetailItemViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String itemStr = mItemList.get(position);
        TextView itemText = ((AnswerDetailItemViewHolder) holder).answerDetailItemText;

        ImageView itemImage = ((AnswerDetailItemViewHolder) holder).answerDetailItemImage;
        switch (position % 2) {
            case 0:
                itemImage.setVisibility(View.GONE);
                itemText.setVisibility(View.VISIBLE);
                itemText.setText(Html.fromHtml(itemStr));
                //itemText.setText(itemStr);
                //itemText.setMovementMethod(LinkMovementMethod.getInstance());
//                URLImageParser p = new URLImageParser(itemText, mContext);
//                Spanned htmlSpan = Html.fromHtml(itemStr, p, null);
//                itemText.setText(htmlSpan);
                return;
            case 1:
                itemImage.setVisibility(View.VISIBLE);
                itemText.setVisibility(View.GONE);
                Picasso.with(mContext).load(itemStr).into(itemImage);
                return;
        }
    }

    public void addAll(List list) {
        mItemList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    private class AnswerDetailItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView answerDetailItemText;
        private ImageView answerDetailItemImage;

        public AnswerDetailItemViewHolder(View itemView) {
            super(itemView);

            answerDetailItemText = (TextView) itemView.findViewById(R.id.answer_detail_item_tv);
            answerDetailItemText.setOnClickListener(this);

            answerDetailItemImage = (ImageView) itemView.findViewById(R.id.answer_detail_item_iv);
            answerDetailItemImage.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
