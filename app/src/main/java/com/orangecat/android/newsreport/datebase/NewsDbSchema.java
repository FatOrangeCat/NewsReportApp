package com.orangecat.android.newsreport.datebase;

/**
 * Created by xwang17 on 11/26/15.
 */
public class NewsDbSchema {
    public static final class NewsTable {
        public static final String NAME = "newses";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String PUBLICED = "publiced";
        }
    }
}
