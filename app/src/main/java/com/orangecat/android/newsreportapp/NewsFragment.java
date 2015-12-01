package com.orangecat.android.newsreportapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;

/**
 * Created by xwang17 on 11/26/15.
 */
public class NewsFragment extends Fragment{

    private static final String ARG_NEWS_ID = "news_id";
    private static final String DIALOG_DATE = "DialogDate";

    private static final int REQUEST_DATE = 0;

    private News mNews;
    private EditText mTitileField;
    private Button mDateButton;
    private CheckBox mPublicedCheckBox;

    public static NewsFragment newInstance(UUID newsId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_NEWS_ID, newsId);

        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID newsId = (UUID) getArguments().getSerializable(ARG_NEWS_ID);
        mNews = NewsLab.get(getActivity()).getNews(newsId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_news,container,false);
        mTitileField = (EditText)v.findViewById(R.id.news_title);
        mTitileField.setText(mNews.getTitle());
        mTitileField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mNews.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // nothing
            }
        });

        mDateButton = (Button)v.findViewById(R.id.news_date);
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mNews.getDate());
                dialog.setTargetFragment(NewsFragment.this, REQUEST_DATE);
                dialog.show(fm, DIALOG_DATE);
            }
        });
        mPublicedCheckBox = (CheckBox)v.findViewById(R.id.news_publiced);
        mPublicedCheckBox.setChecked(mNews.isPubliced());
        mPublicedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mNews.setPubliced(isChecked);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if(requestCode == REQUEST_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mNews.setDate(date);
            updateDate();
        }

    }

    private void updateDate() {
        mDateButton.setText(mNews.getDate().toString());
    }
}
