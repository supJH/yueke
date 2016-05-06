package com.android.jh.yueke.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Relcon on 2015/11/10.
 */
public class RecyclerItemDecoration extends RecyclerView.ItemDecoration {

    public static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    private int mOrientation;
    private Drawable mDividerDrawable;

    public RecyclerItemDecoration(Context context, int orientation) {
        final TypedArray typedArray = context.obtainStyledAttributes(ATTRS);
        mDividerDrawable = typedArray.getDrawable(0);
        typedArray.recycle();
        setmOrientation(orientation);
    }

    private void setmOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent) {
        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else if (mOrientation == HORIZONTAL_LIST) {
            drawHorizontal(c, parent);
        }
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            final View childView = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) childView.getLayoutParams();

            final int left = childView.getRight() + params.rightMargin;
            final int right = left + mDividerDrawable.getIntrinsicHeight();

            mDividerDrawable.setBounds(left, top, right, bottom);
            mDividerDrawable.draw(c);
        }
    }

    public void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            final View childView = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) childView.getLayoutParams();

            final int top = childView.getBottom() + params.bottomMargin;
            final int bottom = top + mDividerDrawable.getIntrinsicHeight();

            mDividerDrawable.setBounds(left, top, right, bottom);
            mDividerDrawable.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        if (mOrientation == VERTICAL_LIST) {
            outRect.set(0, 0, 0, mDividerDrawable.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, mDividerDrawable.getIntrinsicWidth(), 0);
        }
    }
}
