package com.capstone.moayo.dao.sqlite;

import android.provider.BaseColumns;

public final class StorageInfo{
    public static final class CreateStorage implements BaseColumns{
        // tb_category
        public static final String CATEGORYID = "co_id";
        public static final String CATEGORYTITLE = "co_title";
        public static final String CATEGORYPARENT = "co_parent";
        public static final String CATEGORYLEVEL = "co_level";
        public static final String _TABLENAME0 = "tb_category";
        public static final String _CREATE0 = "create table if not exists " + _TABLENAME0 +"(" +
                CATEGORYID + " integer primary key autoincrement, " +
                CATEGORYTITLE + " text not null, "+
                CATEGORYPARENT + " integer, "+
                CATEGORYLEVEL + " integer not null );";

        // tb_content
        public static final String CID = "co_id";
        public static final String CPARENT= "co_parent";
        public static final String CLEVEL = "co_level";
        public static final String URL = "co_url";
        public static final String IMGURL = "co_imgUrl";
        public static final String INFO = "co_info";
        public static final String HASHTAG = "co_hashTag";
        public static final String _TABLENAME1 = "tb_content";
        public static final String _CREATE1 = "create table if not exists " + _TABLENAME1 + "(" +
                CID + " integer primary key autoincrement, "+
                CPARENT + " integer, " +
                CLEVEL + " integer, " +
                URL + " text, " +
                IMGURL + " text, " +
                INFO + " text, " +
                HASHTAG + "text, " +
                "constraint fk_parent foreign key(co_parent) references tb_category(co_id));";
    }
}
