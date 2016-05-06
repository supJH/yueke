package com.android.jh.yueke.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.jh.yueke.R;
import com.android.jh.yueke.activity.AddQuestionActivity;
import com.android.jh.yueke.fragment.main.ExploreFragment;
import com.android.jh.yueke.fragment.main.FollowFragment;
import com.android.jh.yueke.fragment.main.HomeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Relcon on 2015/11/13.
 */
public class DrawerMenuRecyclerAdapter extends RecyclerView.Adapter {
    private String[] mDrawerItemNames;
    private Context mContext;
    private DrawerLayout mDrawerLayout;
    private List<View> itemViews = new ArrayList<View>();

    public DrawerMenuRecyclerAdapter(Context context, String[] drawerItemNames, DrawerLayout drawerLayout) {
        mContext = context;
        mDrawerItemNames = drawerItemNames;
        mDrawerLayout = drawerLayout;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.drawer_menu_recycler_item, parent, false);
        itemViews.add(itemView);

        RecyclerView.ViewHolder viewHolder = new DrawerMenuViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int[] iconColorsId = mContext.getResources().getIntArray(R.array.DrawerIconColors);

        ImageView mDrawerMenuItemIcon = ((DrawerMenuViewHolder) holder).mDrawerMenuItemIcon;
        TextView mDrawerMenuItemText = ((DrawerMenuViewHolder) holder).mDrawerMenuItemText;

        mDrawerMenuItemIcon.setColorFilter(iconColorsId[position]);
        int drawableId = mContext.getResources().obtainTypedArray(R.array.DrawerIcons).getResourceId(position, -1);
        mDrawerMenuItemIcon.setImageDrawable(mContext.getResources().getDrawable(drawableId));
        mDrawerMenuItemText.setText(mDrawerItemNames[position]);

        if(0 == position){
            ((DrawerMenuViewHolder) holder).initItemSelected();
        }

    }

    @Override
    public int getItemCount() {
        return mDrawerItemNames.length;
    }

    public class DrawerMenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private static final int ITEM_HOME = 0;
        private static final int ITEM_EXPLORE = 1;
        private static final int ITEM_FOLLOW = 2;
        private static final int ITEM_COLLECT = 3;
        private static final int ITEM_DRAFT = 4;
        private static final int ITEM_QUESTION = 5;

        private ImageView mDrawerMenuItemIcon;
        private TextView mDrawerMenuItemText;

        public DrawerMenuViewHolder(View itemView) {
            super(itemView);

            mDrawerMenuItemIcon = (ImageView) itemView.findViewById(R.id.drawer_item_icon);
            mDrawerMenuItemText = (TextView) itemView.findViewById(R.id.drawer_item_text);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clearAllItemSelected();
            selectDrawerItem(v);

        }

        public void clearAllItemSelected() {
            for (View itemView : itemViews) {
                itemView.setSelected(false);
            }
        }

        public void initItemSelected(){
            itemViews.get(0).setSelected(true);
        }

        public void selectDrawerItem(View view) {
            Fragment fragment = null;
            Class fragmentClass = null;
            Intent intent = null;

            switch (getAdapterPosition()) {
                case ITEM_HOME:
                    fragmentClass = HomeFragment.class;
                    break;
                case ITEM_EXPLORE:
                    fragmentClass = ExploreFragment.class;
                    break;
                case ITEM_FOLLOW:
                    fragmentClass = FollowFragment.class;
                    break;
                case ITEM_COLLECT:
                    fragmentClass = HomeFragment.class;
                    break;
                case ITEM_DRAFT:
                    fragmentClass = HomeFragment.class;
                    break;
                case ITEM_QUESTION:
                    intent = new Intent(mContext, AddQuestionActivity.class);
                    break;
                default:
                    return;
            }

            if (null != fragmentClass) {
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                FragmentManager fm = ((AppCompatActivity) mContext).getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.main_fragment_container, fragment).commit();

                view.setSelected(true);
                ((AppCompatActivity) mContext).setTitle(((TextView) itemView.findViewById(R.id.drawer_item_text)).getText());
                mDrawerLayout.closeDrawers();
                return;
            } else {
                ((AppCompatActivity) mContext).startActivity(intent);
            }
        }
    }
}
