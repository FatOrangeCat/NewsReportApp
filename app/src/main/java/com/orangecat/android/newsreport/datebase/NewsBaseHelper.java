package com.orangecat.android.newsreport.datebase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by xwang17 on 11/26/15.
 */
public class NewsBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "crimeBase.db";

    public NewsBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + NewsDbSchema.NewsTable.NAME + "("
                        + " _id integer primary key autoincrement, " + ""
                        + NewsDbSchema.NewsTable.Cols.UUID + ", "
                        + NewsDbSchema.NewsTable.Cols.TITLE + ", "
                        + NewsDbSchema.NewsTable.Cols.DATE + ", "
                        + NewsDbSchema.NewsTable.Cols.PUBLICED + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
