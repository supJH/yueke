package com.android.jh.yueke.fragment.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.jh.yueke.R;
import com.android.jh.yueke.adapter.HomeRecyclerAdapter;
import com.android.jh.yueke.entity.HeadLine;
import com.android.jh.yueke.json.HeadlineDetailResponse;
import com.android.jh.yueke.net.NetAccessUtils;
import com.android.jh.yueke.net.YuekeClient;
import com.android.jh.yueke.utils.CacheUtils;
import com.android.jh.yueke.view.RecyclerItemDecoration;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class HomeFragment extends Fragment {
    private static final String HOME_FRAGMENT_CACHE_CONTENT_FILE = "HomeFragmentContent.cache";
    private RecyclerView mRecyclerView;
    private List<HeadLine> mList = new LinkedList<>();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private HomeRecyclerAdapter mAdapter;

    public static HomeFragment newInstance(Context context) {
        HomeFragment fragment = new HomeFragment();

        return fragment;
    }

    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                fetchHomeContent();
            }
        });
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));

        //mSwipeRefreshLayout.setRefreshing(true);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.home_recycler);

        //启动Fragment 先读本地存储的文件数据；
        List<HeadLine> list = CacheUtils.getCacheList(getActivity(), HOME_FRAGMENT_CACHE_CONTENT_FILE);
        if(list != null){
            mList = list;
        }

        mAdapter = new HomeRecyclerAdapter(getActivity(), mList);
        mRecyclerView.setAdapter(mAdapter);


//        mList = new ArrayList();
//        for (int i = 0; i < 10; i++) {
//            mList.add("" + i);
//        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //mRecyclerView.setLayoutManager(new MyLinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        //为保证背景的selector生效
//        mRecyclerView.setFocusable(true);
//        mRecyclerView.setClickable(true);
//        mRecyclerView.setFocusableInTouchMode(true);


        mRecyclerView.setHasFixedSize(true);

        RecyclerView.ItemDecoration itemDecoration = new RecyclerItemDecoration(getActivity(), RecyclerItemDecoration.VERTICAL_LIST);
        mRecyclerView.addItemDecoration(itemDecoration);

        return view;
    }

    public void fetchHomeContent() {
        if (!NetAccessUtils.isNetworkAccess(getActivity())) {
            Toast.makeText(getContext(), "网络连接出错，请检查网络设置！", Toast.LENGTH_LONG).show();
            mSwipeRefreshLayout.setRefreshing(false);
            return;
        }
        YuekeClient zhihuClient = new YuekeClient(getActivity());
        zhihuClient.getYuekeContents("/headline/T1348647909107/0-20.html", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                String responseStr = response.toString();
                // 有网络时，将原来的缓存文件清空，并将最新的数据显示在频幕上
                //将数据保存到CacheDir下
                CacheUtils.deleteCache(getActivity(),HOME_FRAGMENT_CACHE_CONTENT_FILE);
                CacheUtils.saveCache(getActivity(), responseStr, HOME_FRAGMENT_CACHE_CONTENT_FILE);

                HeadlineDetailResponse headlineDetailResponse = HeadlineDetailResponse.parseJSON(responseStr);
                mList = headlineDetailResponse.getContents();

                /**
                 * 后期再加入上拉刷新时，获取的数据并不用cache
                 * 只有下拉刷新，才将文件缓存到cache
                 */

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
            /* @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getActivity(),"获取home content失败",Toast.LENGTH_SHORT).show();
            }*/
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);

                fetchHomeContent();
            }
        });
    }

}
