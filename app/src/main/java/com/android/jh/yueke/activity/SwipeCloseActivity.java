package com.android.jh.yueke.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MenuItem;

import com.android.jh.yueke.R;

public class SwipeCloseActivity extends AppCompatActivity {
    private GestureDetector gestureDetector;
    private static final int MIN_THRESHOLD_VELOCITY = 200;
    private float _xDown;
    private float _yDown;
    private boolean isVelocityEnough;
    private boolean isSwipeLeft;
    private boolean isSwipeRight;
    // public SwipeCloseFrameLayout swipeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //getWindow().getDecorView().setBackgroundDrawable(null);

        setContentView(R.layout.activity_swipe_close);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        gestureDetector = new GestureDetector(new SwipeCloseGestureDetector(this,YuekeActivity.class));
        //swipeView = (SwipeCloseFrameLayout) findViewById(R.id.swipe_close_content_container);
//        view.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (gestureDetector.onTouchEvent(event)) return true;
//                return false;
//            }
//        });
    }


    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(0, R.anim.slide_out_right);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(0, R.anim.slide_out_right);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
