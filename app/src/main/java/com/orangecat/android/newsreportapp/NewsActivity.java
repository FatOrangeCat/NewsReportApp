package com.orangecat.android.newsreportapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class NewsActivity extends SingleFragmentActivity {

    private static final String EXTRA_NEWS_ID =
            "com.orangecat.android.newsreportapp.news_id";

    public static Intent newIntent(Context packageContext, UUID newsId){
        Intent intent = new Intent(packageContext, NewsActivity.class);
        intent.putExtra(EXTRA_NEWS_ID, newsId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID newsId = (UUID) getIntent().getSerializableExtra(EXTRA_NEWS_ID);
        return NewsFragment.newInstance(newsId);
    }
}
