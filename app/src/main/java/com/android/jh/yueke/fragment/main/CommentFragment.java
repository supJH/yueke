package com.android.jh.yueke.fragment.main;


import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
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
import com.android.jh.yueke.adapter.HotCommentRecyclerAdapter;
import com.android.jh.yueke.entity.Comment;
import com.android.jh.yueke.entity.CommentList;
import com.android.jh.yueke.entity.HeadLine;
import com.android.jh.yueke.json.HotCommentResponse;
import com.android.jh.yueke.net.NetAccessUtils;
import com.android.jh.yueke.net.YuekeClient;
import com.android.jh.yueke.utils.CacheUtils;
import com.android.jh.yueke.view.RecyclerItemDecoration;
import com.android.jh.yueke.view.RoundImageHelper;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONObject;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommentFragment extends Fragment {


    private static final String REPLY_BOARD = "reply_board";

    private String replyBoard = "";
    private String postid = "";
    private List<Comment> hotCommentList = new ArrayList<>();

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private HotCommentRecyclerAdapter mAdapter;
    private RecyclerView mRecyclerView;




    public CommentFragment() {
        super();
    }


    public static CommentFragment newInstance(String replyBoard, String postid) {
        CommentFragment fragment = new CommentFragment();
        Bundle bundle = new Bundle();
        bundle.putString(YuekeDetailFragment.REPLY_BOARD, replyBoard);
        bundle.putString(HomeRecyclerAdapter.POST_ID, postid);

        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        Bundle bundle = getArguments();
        replyBoard = bundle.getString(YuekeDetailFragment.REPLY_BOARD);
        postid = bundle.getString(HomeRecyclerAdapter.POST_ID);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mSwipeRefreshLayout.isRefreshing()){
                    fetchHotComments();
                }
            }
        });
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));

        //mSwipeRefreshLayout.setRefreshing(true);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.home_recycler);

        mAdapter = new HotCommentRecyclerAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRecyclerView.setHasFixedSize(true);

        RecyclerView.ItemDecoration itemDecoration = new RecyclerItemDecoration(getActivity(), RecyclerItemDecoration.VERTICAL_LIST);
        mRecyclerView.addItemDecoration(itemDecoration);

        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public void fetchHotComments() {
        if (!NetAccessUtils.isNetworkAccess(getActivity())) {
            Toast.makeText(getContext(), "网络连接出错，请检查您的网络！", Toast.LENGTH_LONG).show();
            return;
        }
        final YuekeClient yuekeClient = new YuekeClient(getActivity());
        yuekeClient.getCommentContents("/hot/" + replyBoard + "/"+postid+"/0/10/10/2/2", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                String responseStr = response.toString();
                // 有网络时，将原来的缓存文件清空，并将最新的数据显示在频幕上
                //将数据保存到CacheDir下

                HotCommentResponse hotCommentResponse = HotCommentResponse.parseJSON(responseStr);
                List<CommentList> reponseList = hotCommentResponse.getHotPosts();
                for (CommentList commentList : reponseList){
                    Comment comment = commentList.getCommentList().get(0);
                    hotCommentList.add(comment);
                }


                /**
                 * 后期再加入上拉刷新时，获取的数据并不用cache
                 * 只有下拉刷新，才将文件缓存到cache
                 */

                mAdapter.addAll(hotCommentList);
                mSwipeRefreshLayout.setRefreshing(false);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

                if (getContext() != null) {
                    Toast.makeText(getContext(), "当前网络状况不佳，请重试", Toast.LENGTH_LONG).show();
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
                if(mSwipeRefreshLayout.isRefreshing()){

                    fetchHotComments();
                }
            }
        });
    }
}
