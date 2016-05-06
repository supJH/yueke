package com.android.jh.yueke.fragment.question;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import com.android.jh.yueke.R;

public class QuestionAddFragment extends Fragment {
    public static final String FRAGMENT_POSITION = "fragment_position";
    private int fragmentPositionIndex;
    private EditText mContentEt;
    private CheckBox mAnonymousCb;

    public static QuestionAddFragment newInstance(int position) {
        QuestionAddFragment fragment = new QuestionAddFragment();
        Bundle args = new Bundle();
        args.putInt(FRAGMENT_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    public QuestionAddFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fragmentPositionIndex = getArguments().getInt(FRAGMENT_POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = null;
        if (fragmentPositionIndex == 0 ){
            view = inflater.inflate(R.layout.fragment_question_add_brief, container, false);
            mAnonymousCb = (CheckBox) view.findViewById(R.id.question_fragment_anonymous_cb);
        }else{
            view = inflater.inflate(R.layout.fragment_question_add_detail,container,false);
        }

        mContentEt = (EditText) view.findViewById(R.id.question_fragment_content_et);

        switch (fragmentPositionIndex) {
            case 0:
                mContentEt.setHint("写下你的问题");
                break;
            case 1:
                mContentEt.setHint("添加问题的补充说明...");
                break;
            case 2:
                mContentEt.setHint("添加相关话题");
                break;
        }
        return view;
    }

}
