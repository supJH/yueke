package com.android.jh.yueke.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.android.jh.yueke.R;

/**
 * Created by Relcon on 2015/11/27.
 */
public class SwipeCloseFrameLayout extends FrameLayout {

    private static final int MIN_THRESHOLD_VELOCITY = 200;
    private float _xDown;
    private float _yDown;
    private float distanceX;
    private long originalDownTime;
    private boolean isDrag;
    private Intent nextActivityIntent;

    public SwipeCloseFrameLayout(Context context) {
        super(context);
    }

    public SwipeCloseFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SwipeCloseFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SwipeCloseFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getActionMasked();
        System.out.println("onInterceptTouchEvent...................");
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                isDrag = false;
                _xDown = ev.getX();
                _yDown = ev.getY();
                originalDownTime = ev.getEventTime();
                System.out.println("onInterceptTouchEvent+++++++++ACTION_DOWN...................");
                return false;
            case MotionEvent.ACTION_MOVE:
                System.out.println("onInterceptTouchEvent+++++++++ACTION_MOVE...................");
                isDrag = true;
                if (Math.abs(ev.getY() - _yDown) > Math.abs(ev.getX() - _xDown)) {
                    return false;
                }
                return true;
            default:
                return false;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("onTouchEvent+++++++++ACTION_DOWN...................");
                return true;
            case MotionEvent.ACTION_POINTER_DOWN:
                return true;
            case MotionEvent.ACTION_MOVE:
                System.out.println("onTouchEvent+++++++++ACTION_MOVE...................");
                float moveDistanceY = event.getY() - _yDown;
                if (Math.abs(moveDistanceY) > 40) {
                    return false;
                }
                return true;
            case MotionEvent.ACTION_POINTER_UP:
                return true;
            case MotionEvent.ACTION_UP:
                System.out.println("onTouchEvent+++++++++ACTION_UP...................");
                float xUp = event.getX();
                float yUp = event.getY();

                distanceX = xUp - _xDown;
                float velocity = 1000 * distanceX / (event.getEventTime() - originalDownTime);

                if (Math.abs(velocity) > MIN_THRESHOLD_VELOCITY && Math.abs(yUp - _yDown) < 40) {
                    if (distanceX > 120) {
                        Intent parentIntent = NavUtils.getParentActivityIntent(((Activity) getContext()));
                        if (parentIntent != null) {
                            ((Activity) getContext()).finish();
                            //getContext().startActivity(parentIntent);
                            ((Activity) getContext()).overridePendingTransition(
                                    0, R.anim.slide_out_right);
                            return true;
                        }
                    }
                    if (distanceX < -120) {
                        if(nextActivityIntent != null){
                            getContext().startActivity(nextActivityIntent);
                            ((Activity) getContext()).overridePendingTransition(
                                    R.anim.slide_in_right, R.anim.slide_out_left);
                        }
                        // ((Activity) getContext()).finish();
//                        ((Activity) getContext()).overridePendingTransition(
//                                R.anim.slide_in_right, R.anim.slide_out_left);
                        return true;
                    }
                }
                return false;
            case MotionEvent.ACTION_CANCEL:
                return true;
            default:
                return false;
        }
    }

    public void setNextActivityIntent(Intent intent){
        nextActivityIntent = intent;
    }


}
