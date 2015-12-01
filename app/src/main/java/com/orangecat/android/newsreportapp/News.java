package com.orangecat.android.newsreportapp;

import java.util.Date;
import java.util.UUID;

/**
 * Created by xwang17 on 11/26/15.
 */
public class News {


    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mPubliced;


    public News() {
        // Genereate uuid
        mId = UUID.randomUUID();
        mDate = new Date();
    }
    public News(UUID id) {
        // Genereate uuid
        mId = id;
        mDate = new Date();
    }


    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isPubliced() {
        return mPubliced;
    }

    public void setPubliced(boolean publiced) {
        mPubliced = publiced;
    }


    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

}
