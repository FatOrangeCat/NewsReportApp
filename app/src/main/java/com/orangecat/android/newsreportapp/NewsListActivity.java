package com.orangecat.android.newsreportapp;

import android.support.v4.app.Fragment;

/**
 * Created by xwang17 on 11/26/15.
 */
public class NewsListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        return new NewsListFragment();
    }
}
