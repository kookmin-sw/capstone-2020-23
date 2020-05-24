package com.capstone.moayo.dao.sqlite;

import android.provider.BaseColumns;

public final class StorageInfo{
    public static final class CreateStorage implements BaseColumns{
        // tb_category
        public static final String FOREIGNKEY_ON = "PRAGMA foreign_keys = ON;";
        public static final String FOREIGNKEY_OFF = "PRAGMA foreign_keys = OFF";

        public static final String CATEGORYID = "co_id";
        public static final String CATEGORYTITLE = "co_title";
        public static final String CATEGORYPARENT = "co_parent";
        public static final String CATEGORYPARENTDOGAM = "co_parentDogam";
        public static final String CATEGORYLEVEL = "co_level";
        public static final String CATEGORYDOGAMID = "co_dogamId";
        public static final String _TABLENAME0 = "tb_category";
        public static final String _CREATE0 = "create table if not exists " + _TABLENAME0 +"(" +
                CATEGORYID + " integer, " +
                CATEGORYDOGAMID + " integer not null," +
                CATEGORYTITLE + " text not null, "+
                CATEGORYPARENT + " integer, "+
                CATEGORYPARENTDOGAM + " integer,"+
                CATEGORYLEVEL + " integer not null ,"+ "primary key(co_id,co_dogamId),"+
                "foreign key(co_dogamId) references tb_dogamlist(co_id) ON DELETE CASCADE," +
                "foreign key(co_parent,co_parentDogam) references tb_category(co_id,co_dogamId) ON DELETE CASCADE);";

        // tb_postHavingLike
        public static final String CID = "co_postId";
        public static final String URL = "co_url";
        public static final String IMGURL = "co_imgUrl";
        public static final String HASHTAG = "co_hashTag";
        public static final String LIKE = "co_like";
        public static final String _TABLENAME1 = "tb_postHavingLike";
        public static final String _CREATE1 = "create table if not exists " + _TABLENAME1 + "(" +
                CID + " integer primary key autoincrement, "+
                URL + " text, " +
                IMGURL + " text, " +
                HASHTAG + " text, " +
                LIKE  + " integer);";

        // tb_category_Post
        public static final String CPDOGAMID = "co_dogamId";
        public static final String CPCATEGORYID = "co_categoryId";
        public static final String CPPOSTID = "co_postId";
        public static final String _CPTABLENAME = "tb_category_post";
        public static final String _CPCREATE = "create table if not exists " + _CPTABLENAME + "("+
                CPDOGAMID + " integer ,"+
                CPCATEGORYID + " integer ,"+
                CPPOSTID + " integer ,"+ "primary key(co_dogamId,co_categoryId,co_postId), " +
                "foreign key(co_dogamId,co_categoryId) references tb_category(co_dogamId,co_id) ON DELETE CASCADE ON UPDATE CASCADE," +
                "foreign key(co_postId) references tb_postHavingLike(co_postId) ON DELETE CASCADE ON UPDATE CASCADE)";

        // tb_hashtag
        public static final String HHASHTAG = "co_hashtag";
        public static final String _HTABLENAME = "tb_hashtag";
        public static final String _HCREATE = "create table if not exists " + _HTABLENAME + "(" + HHASHTAG + " text primary key)";

        // tb_category_hashtag
        public static final String CHDOGAMID = "co_dogamId";
        public static final String CHCATEGORYID = "co_categoryId";
        public static final String CHHASHTAG = "co_hashtag";
        public static final String _CHTABLENAME = "tb_category_hashtag";
        public static final String _CHCREATE = "create table if not exists " + _CHTABLENAME + "(" +
                CHDOGAMID + " integer, " +
                CHCATEGORYID + " integer, " +
                CHHASHTAG + " text," + "primary key(co_dogamId,co_categoryId,co_hashtag)," +
                "foreign key(co_dogamId,co_categoryId) references tb_category(co_dogamId,co_id) ON DELETE CASCADE ON UPDATE CASCADE," +
                "foreign key(co_hashtag) references tb_hashtag(co_hashtag) ON DELETE CASCADE ON UPDATE CASCADE)";

        // tb_dogamlist
        public static final String DOGAMID = "co_id";
        public static final String DOGAMTITLE = "co_title";
        public static final String DOGAMDESCRIPTION = "co_description";
        public static final String DOGAMPASSWORD = "co_password";
        public static final String DOGAMSTATUS = "co_status";
        public static final String DOGAMURL = "co_url";
        public static final String _DOGAMTABLENAME = "tb_dogamlist";
        public static final String _DOGAMCREATE = "create table if not exists " + _DOGAMTABLENAME + "(" +
                DOGAMID + " integer primary key autoincrement, " +
                DOGAMTITLE + " text, " +
                DOGAMDESCRIPTION + " text, " +
                DOGAMPASSWORD + " text, " +
                DOGAMURL + " text, " +
                DOGAMSTATUS + " integer);";
    }
}
