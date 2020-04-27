package com.capstone.moayo.dao.sqlite;

import android.provider.BaseColumns;

public final class StorageInfo{
    public static final class CreateStorage implements BaseColumns{
        // tb_category
        public static final String FOREIGNKEY_ON = "PRAGMA foreign_keys = ON;";

        public static final String CATEGORYID = "co_id";
        public static final String CATEGORYTITLE = "co_title";
        public static final String CATEGORYPARENT = "co_parent";
        public static final String CATEGORYLEVEL = "co_level";
        public static final String CATEGORYDOGAMID = "co_dogamId";
        public static final String _TABLENAME0 = "tb_category";
        public static final String _CREATE0 = "create table if not exists " + _TABLENAME0 +"(" +
                CATEGORYID + " integer primary key autoincrement, " +
                CATEGORYTITLE + " text not null, "+
                CATEGORYPARENT + " integer, "+
                CATEGORYLEVEL + " integer not null ,"+
                CATEGORYDOGAMID + " integer not null," +
                "constraint fk_dogamId foreign key(co_dogamId) references tb_dogamlist(co_id) ON DELETE CASCADE );";

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
                HASHTAG + " text, " +
                "constraint fk_parent foreign key(co_parent) references tb_category(co_id) ON DELETE CASCADE );";

        // tb_dogamlist
        public static final String DOGAMID = "co_id";
        public static final String DOGAMTITLE = "co_title";
        public static final String DOGAMDESCRIPTION = "co_description";
        public static final String DOGAMPASSWORD = "co_password";
        public static final String _DOGAMTABLENAME = "tb_dogamlist";
        public static final String _DOGAMCREATE = "create table if not exists " + _DOGAMTABLENAME + "(" +
                DOGAMID + " integer primary key autoincrement, " +
                DOGAMTITLE + " text, " +
                DOGAMDESCRIPTION + " text, " +
                DOGAMPASSWORD + " text);";
    }
}
