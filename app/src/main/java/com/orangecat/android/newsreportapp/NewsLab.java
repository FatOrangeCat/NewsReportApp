package com.orangecat.android.newsreportapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.orangecat.android.newsreport.datebase.NewsBaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by xwang17 on 11/26/15.
 */
public class NewsLab {
    private static NewsLab sNewsLab;

    private List<News> mNewses;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static NewsLab get(Context context) {
        if (sNewsLab == null) {
            if (sNewsLab == null) {
                sNewsLab = new NewsLab(context);
            }
        }
        return sNewsLab;
    }

    public List<News> getNewses() {
        return mNewses;
    }

    private NewsLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new NewsBaseHelper(mContext).getWritableDatabase();
        mNewses = new ArrayList<>();
        for (int i=0; i<100; i++) {
            News news = new News();
            news.setTitle("report #" + i);
            news.setPubliced(i%2==0);
            mNewses.add(news);
        }
    }

    public News getNews(UUID id) {
        for (News news : mNewses) {
            if (news.getId().equals(id)) {
                return news;
            }
        }
        return null;
    }
}
