package com.orangecat.android.newsreport.datebase;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.orangecat.android.newsreportapp.News;

import java.util.Date;
import java.util.UUID;

/**
 * Created by xwang17 on 11/26/15.
 */
public class NewsCursorWrapper extends CursorWrapper {
    public NewsCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public News getNews() {
        String uuidString = getString(getColumnIndex(NewsDbSchema.NewsTable.Cols.UUID));
        String title = getString(getColumnIndex(NewsDbSchema.NewsTable.Cols.TITLE));
        long date = getLong(getColumnIndex(NewsDbSchema.NewsTable.Cols.DATE));
        int isSolved = getInt(getColumnIndex(NewsDbSchema.NewsTable.Cols.PUBLICED));

        News news = new News(UUID.fromString(uuidString));
        news.setTitle(title);
        news.setDate(new Date(date));
        news.setPubliced(isSolved != 0);

        return news;
    }
}
