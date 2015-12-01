package com.orangecat.android.newsreportapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by xwang17 on 11/26/15.
 */
public class NewsPagerActivity extends AppCompatActivity {

    private static final String EXTRA_NEWS_ID = "com.orangecat.android.newsreportapp.news_id";
    private ViewPager mViewPager;
    private List<News> mNewses;

    public static Intent newIntent(Context pageContext, UUID newsId){
        Intent intent = new Intent(pageContext, NewsPagerActivity.class);
        intent.putExtra(EXTRA_NEWS_ID, newsId);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_pager);
        UUID newsId =(UUID) getIntent().getSerializableExtra(EXTRA_NEWS_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_news_pager_view_pager);
        mNewses = NewsLab.get(this).getNewses();
        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                News news = mNewses.get(position);
                return NewsFragment.newInstance(news.getId());
            }

            @Override
            public int getCount() {
                return mNewses.size();
            }
        });

        for (int i=0; i<mNewses.size(); i++){
            if (mNewses.get(i).getId().equals(newsId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

    }
}
