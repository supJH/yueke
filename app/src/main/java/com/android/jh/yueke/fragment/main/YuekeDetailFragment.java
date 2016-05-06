package com.android.jh.yueke.fragment.main;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

import com.android.jh.yueke.R;
import com.android.jh.yueke.activity.CommentActivity;
import com.android.jh.yueke.activity.YuekeDetailActivity;
import com.android.jh.yueke.adapter.HomeRecyclerAdapter;
import com.android.jh.yueke.entity.ImageInfo;
import com.android.jh.yueke.entity.YuekeContents;
import com.android.jh.yueke.json.YuekeDetailResponse;
import com.android.jh.yueke.net.NetAccessUtils;
import com.android.jh.yueke.net.YuekeClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * A simple {@link Fragment} subclass.
 */
public class YuekeDetailFragment extends Fragment {
    public static final String REPLY_BOARD = "reply_board";
    private WebView webView;
    private String title = "";
    private String postid = "";
    private String source = "";
    private String ptime = "";
    private String replyBoard = "";
    private Intent nextIntent = null;

    public YuekeDetailFragment() {
        super();
    }


    public static YuekeDetailFragment newInstance(String title, String source, String ptime, String postid) {
        YuekeDetailFragment fragment = new YuekeDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(HomeRecyclerAdapter.TITLE, title);
        bundle.putString(HomeRecyclerAdapter.SOURCE, source);
        bundle.putString(HomeRecyclerAdapter.P_TIME, ptime);
        bundle.putString(HomeRecyclerAdapter.POST_ID, postid);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_yueke_detail, container, false);


        Bundle bundle = getArguments();
        title = bundle.getString(HomeRecyclerAdapter.TITLE);
        source = bundle.getString(HomeRecyclerAdapter.SOURCE);
        ptime = bundle.getString(HomeRecyclerAdapter.P_TIME);
        postid = bundle.getString(HomeRecyclerAdapter.POST_ID);

        webView = (WebView) view.findViewById(R.id.yueke_detail_webview);

        fetchYuekeDetailContent();


        return view;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public void fetchYuekeDetailContent() {
        if (!NetAccessUtils.isNetworkAccess(getActivity())) {
            Toast.makeText(getContext(), "网络连接出错，请检查您的网络！", Toast.LENGTH_LONG).show();
            return;
        }
        final YuekeClient yuekeClient = new YuekeClient(getActivity());
        yuekeClient.getYuekeContents("/" + postid + "/full.html", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                String responseStr = response.toString();
                // 有网络时，将原来的缓存文件清空，并将最新的数据显示在频幕上
                //将数据保存到CacheDir下

                YuekeDetailResponse yuekeDetailResponse = YuekeDetailResponse.parseJSON(responseStr);
                Map<String, YuekeContents> contents = yuekeDetailResponse.getContents();
                YuekeContents yuekeContents = new ArrayList<YuekeContents>(contents.values()).get(0);

                boolean picnews = yuekeContents.isPicnews();
                String body = yuekeContents.getBody();

                replyBoard = yuekeContents.getReplyBoard();
                nextIntent = new Intent(getActivity(), CommentActivity.class);
                nextIntent.putExtra(REPLY_BOARD, replyBoard);
                nextIntent.putExtra(HomeRecyclerAdapter.POST_ID,postid);
                ((YuekeDetailActivity) getActivity()).getSwipeCloseFrameLayout().setNextActivityIntent(nextIntent);

                StringBuilder sb = new StringBuilder();
                sb.append("<h2>" + title + "</h2><p><span style='padding-right:10px;'>" + source + "</span><span>" + ptime + "</span></p>");
                List<ImageInfo> list = yuekeContents.getImg();
                SharedPreferences sp = getActivity().getSharedPreferences("Config", Context.MODE_PRIVATE);
                if (!sp.getBoolean("none_pics", false) && picnews == true && list != null && list.size() > 0) {
                    String imageurl = list.get(0).getSrc();
                    sb.append("<img src='" + imageurl + "' width='100%'/>");
                }
                sb.append(body);

//                webView.getSettings().setDefaultTextEncodingName("GBK") ;
                webView.loadData(sb.toString(), "text/html;charset=UTF-8", null);


                /**
                 * 后期再加入上拉刷新时，获取的数据并不用cache
                 * 只有下拉刷新，才将文件缓存到cache
                 */


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


}
