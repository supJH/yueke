



# Yueke Android APP
An Android App can get headline news from NetEase server.This is only for learning.

****
###　　　　　　　　　　　　Author:Ji Hui
###　　　　　　　　　 Github:https://github.com/supJH

****

#App Overview
![overview](https://github.com/supJH/Pictures/blob/master/yueke/yueke_overview.gif)

##Function

1. Down pull touch gesture to refreshing data.
2. Self-defining Gesture for right or Left swipe to forward or backward.
3. Navigation Drawer.
4. Round ImageView display in Comment Activity. 
5. Headline news cache in file way. 
	
-------
#App Intrdouction

##1 Dependencies
```groovy
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    testCompile 'junit:junit:4.12'
    compile 'com.android.support:appcompat-v7:23.3.0'
    compile 'com.android.support:design:23.3.0'
    compile 'com.android.support:cardview-v7:23.3.0'
    compile 'com.squareup.picasso:picasso:2.5.2'
    compile 'com.loopj.android:android-async-http:1.4.9'
    compile 'com.google.code.gson:gson:2.4'
    compile 'com.android.support:support-v4:23.3.0'
}

```

  
##2 How Yueke Android APP to work

###How to get JSON data
Using Fiddler4 for windows, we can catch the IP package in the Netease News APP for android.After analysing,we get the headline news http url.With the URL, we can get the headline news JSON data.In this APP we use GSON library for the response string deserialize.

HomeFragment.java:

	public void fetchHomeContent() {

        if (!NetAccessUtils.isNetworkAccess(getActivity())) {
            Toast.makeText(getContext(), "网络连接出错，请检查网络设置！", Toast.LENGTH_LONG).show();
            mSwipeRefreshLayout.setRefreshing(false);
            return;
        }

        YuekeClient yuekeClient = new YuekeClient(getActivity());
        yuekeClient.getYuekeContents("/headline/T1348647909107/0-20.html", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                String responseStr = response.toString();
                // 有网络时，将原来的缓存文件清空，并将最新的数据显示在频幕上
                //将数据保存到CacheDir下
                CacheUtils.deleteCache(getActivity(),HOME_FRAGMENT_CACHE_CONTENT_FILE);
                CacheUtils.saveCache(getActivity(), responseStr, HOME_FRAGMENT_CACHE_CONTENT_FILE);

                //使用Gson进行反序列化
                HeadlineDetailResponse headlineDetailResponse = HeadlineDetailResponse.parseJSON(responseStr);
                mList = headlineDetailResponse.getContents();
                mAdapter.addAll(mList);
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

                if (getContext() != null) {
                    Toast.makeText(getContext(), "当前网络状况不佳，请重试", Toast.LENGTH_LONG).show();
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
           
        });
    }

**also see:HeadlineDetailResponse.java;HeadlineDeserializer.java;**

###How to present in RecyclerView
After parding the JSON data to Java Object entity, we inflat the data to RecyclerView.RecyclerAdapter.

HomeFragment.java:

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //........

        mRecyclerView = (RecyclerView) view.findViewById(R.id.home_recycler);

        //启动Fragment 先读本地存储的文件数据；
        List<HeadLine> list = CacheUtils.getCacheList(getActivity(), HOME_FRAGMENT_CACHE_CONTENT_FILE);
        if(list != null){
            mList = list;
        }

        //设置RecyclerView.RecyclerAdapter
        mAdapter = new HomeRecyclerAdapter(getActivity(), mList);
        mRecyclerView.setAdapter(mAdapter);

        //设置RecyclerView的LayoutManager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);

        //添加RecyclerView间的分割线
        RecyclerView.ItemDecoration itemDecoration = new RecyclerItemDecoration(getActivity(), RecyclerItemDecoration.VERTICAL_LIST);
        mRecyclerView.addItemDecoration(itemDecoration);

        return view;
    }

**also see: HomeRecyclerAdapter.java**

###How to realize self-defining gesture
Self-defining view **SwipeCloseFrameLayout** override the method **onInterceptTouchEvent(MotionEvent ev)** and **onTouchEvent(MotionEvent event)**

SwipeCloseFrameLayout.java:

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
                
                return false;
            case MotionEvent.ACTION_MOVE:
                //当滑动距离满足条件时，中断MotionEvent.ACTION_MOVE
				//交给SwipeCloseFrameLayout的onTouchEvent处理
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
                return true;
            case MotionEvent.ACTION_POINTER_DOWN:
                return true;
            case MotionEvent.ACTION_MOVE:                
                float moveDistanceY = event.getY() - _yDown;
				//当滑动的速度不符合条件时，中断处理；交给SwipeCloseFrameLayout内的
				//child view处理,MotionEvent.ACTION_MOVE之后的事件不再
				//SwipeCloseFrameLayout的onTouchEvent（）处理
                if (Math.abs(moveDistanceY) > 40) {
                    return false;
                }
                return true;
            case MotionEvent.ACTION_POINTER_UP:
                return true;
            case MotionEvent.ACTION_UP:
                //当手指离开频幕的时候，判断是否触发手势动作
                float xUp = event.getX();
                float yUp = event.getY();

                distanceX = xUp - _xDown;
                float velocity = 1000 * distanceX / (event.getEventTime() - originalDownTime);
				//满足滑动速率和斜率时，触发手势动作
                if (Math.abs(velocity) > MIN_THRESHOLD_VELOCITY && Math.abs(yUp - _yDown) < 40) {
					//左滑返回parent activity
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
					//右滑前进到下一个Activity
                    if (distanceX < -120) {
                        if(nextActivityIntent != null){
                            getContext().startActivity(nextActivityIntent);
                            ((Activity) getContext()).overridePendingTransition(
                                    R.anim.slide_in_right, R.anim.slide_out_left);
                        }
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



----
