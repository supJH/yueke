package com.android.jh.yueke.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;
import com.android.jh.yueke.R;

/**
 * Created by Relcon on 2015/11/26.
 */
public class SwipeCloseGestureDetector extends GestureDetector.SimpleOnGestureListener {

    private Context mContext;
    private Class<? extends Activity> forwardClass;

    private static final int SWIPE_MIN_DISTANCE = 20;
    private static final int SWIPE_THRESHOLD_VELOCITYX = 200;
    private static final int SWIPE_THRESHOLD_MIN_DEGREES = -30;
    private static final int SWIPE_THRESHOLD_MAX_DEGREES = 30;

    public SwipeCloseGestureDetector(Context context) {
        this(context, null);
    }

    public SwipeCloseGestureDetector(Context context, @Nullable Class<? extends Activity> forwardClass) {
        super();
        mContext = context;
        if (forwardClass != null) {
            this.forwardClass = forwardClass;
        }
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float slope = (e2.getY() - e1.getY()) / (e2.getX() - e1.getX());
        float angle = (float) Math.atan(slope);
        float degree = (float) Math.toDegrees(angle);

        try {
            if (degree > SWIPE_THRESHOLD_MIN_DEGREES && degree < SWIPE_THRESHOLD_MAX_DEGREES && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITYX) {
                if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE) {

                    Intent parentIntent = NavUtils.getParentActivityIntent((Activity) mContext);
                    if (parentIntent != null) {
                        mContext.startActivity(parentIntent);
                       ((Activity) mContext).overridePendingTransition(
                                R.anim.slide_in_left, R.anim.slide_out_right);
                        ((Activity) mContext).finish();
                        return true;
                    }
                } else if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE) {
                    if (forwardClass != null) {
                        mContext.startActivity(new Intent(mContext, forwardClass));
                        ((Activity) mContext).overridePendingTransition(
                               R.anim.slide_in_right, R.anim.slide_out_left);
                        ((Activity) mContext).finish();
                        return true;
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}
