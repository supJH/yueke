package com.android.jh.yueke.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.jh.yueke.R;

/**
 * Created by Administrator on 2015/11/25.
 */
public class MsgSettingsAdapter extends BaseAdapter {

    private String[] items;
    private Context mContext;

    public MsgSettingsAdapter(Context context,String[] items){
        super();
        mContext = context;
        this.items = items;
    }
    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.msg_mail_settings_item,parent,false);
        }
        TextView itemTv = (TextView) convertView.findViewById(R.id.msg_mail_item_tv);
        itemTv.setText(items[position]);
        return convertView;
    }
}
