package com.android.jh.yueke.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.android.jh.yueke.R;
import com.android.jh.yueke.adapter.HomeRecyclerAdapter;
import com.android.jh.yueke.fragment.main.YuekeDetailFragment;
import com.android.jh.yueke.view.SwipeCloseFrameLayout;

public class YuekeDetailActivity extends SwipeCloseActivity {
//    private RecyclerView recyclerView;
//    private DetailAnswerRecyclerAdapter mAdapter;
//    private List<String> contentItems = new ArrayList<String>();
//    private TextView mQusetionTv;

    private SwipeCloseFrameLayout swipeCloseFrameLayout;

    public SwipeCloseFrameLayout getSwipeCloseFrameLayout() {
        return swipeCloseFrameLayout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_yueke_detail);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        mQusetionTv = (TextView) findViewById(R.id.answer_detail_question_tv);
//
//        recyclerView = (RecyclerView) findViewById(R.id.answer_detail_recycler);
//        mAdapter = new DetailAnswerRecyclerAdapter(this, contentItems);
//        recyclerView.setAdapter(mAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setHasFixedSize(true);
//
//        YuekeClient client = new YuekeClient(this);
//        client.getZhihuContents("/answerDetail", new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                AnswerDetailResponse answerDetail = AnswerDetailResponse.parseJSON(response.toString());
//                contentItems = fetchAnswerContentItem(answerDetail.getAnswer());
//                String questionName = answerDetail.getQuestion().getQuestionName();
//                mQusetionTv.setText(questionName);
//                getSupportActionBar().setTitle(questionName);
//
//                mAdapter.addAll(contentItems);
//            }
//        });
         swipeCloseFrameLayout = (SwipeCloseFrameLayout) findViewById(R.id.swipe_close_content_container);

        Intent intent = getIntent();
        String title = intent.getStringExtra(HomeRecyclerAdapter.TITLE);
        String source = intent.getStringExtra(HomeRecyclerAdapter.SOURCE);
        String ptime = intent.getStringExtra(HomeRecyclerAdapter.P_TIME);
        String postid = intent.getStringExtra(HomeRecyclerAdapter.POST_ID);
        YuekeDetailFragment yuekeDetailFragment = YuekeDetailFragment.newInstance(title, source, ptime, postid);

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.swipe_close_content_container, yuekeDetailFragment).commit();

    }

//    public List<String> fetchAnswerContentItem(Answer answer) {
//        String contentTmp = Html.fromHtml(answer.getAnswerContent()).toString();
//        //String  contentTmp = answer.getAnswerContent().replaceAll("&gt;", ">").replaceAll("&lt;", "<").replaceAll("&quot;", "\"");
//        String[] textItems = contentTmp.split("<img.+?>");
//        Pattern patternImg = Pattern.compile("<img.+?>");
//        Pattern patternUrl = Pattern.compile("src=\"https.+?\"");
//
//        Matcher matcherImg = patternImg.matcher(contentTmp);
//        List<String> list = new ArrayList<String>();
//        Matcher matcherUrl = null;
//        int i = 0;
//        while (matcherImg.find()) {
//            list.add(textItems[i]);
//            String imageItem = matcherImg.group();
//
//            matcherUrl = patternUrl.matcher(imageItem);
//            matcherUrl.find();
//            String imageUrlTmp = matcherUrl.group();
//            String imageUrl = imageUrlTmp.substring(5, imageUrlTmp.length()-1);
//
//            list.add(imageUrl);
//            i++;
//        }
//        if (textItems.length == i) {
//            list.add(textItems[i-1]);
//        }
//        return list;
//    }

//    public List<String> fetchAnswerContentItem(Answer answer) {
//
//        List<String> list = new ArrayList<String>();
//
//        list.add(Html.fromHtml(answer.getAnswerContent()).toString());
//
//        return list;
//    }


}
