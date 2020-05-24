package com.capstone.moayo.util.Tag;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Arrays;
import java.util.List;

public class TagsFinder{

    private static final String relevantURL ="https://www.tagsfinder.com/ko-kr/related/";
    private static final String similarURL = "https://www.tagsfinder.com/ko-kr/similar/";

    public static List<String> getRelevantTags(String tag) throws Exception {
        RequestHttpConnection requestHttpConnection = new RequestHttpConnection();
        String result = requestHttpConnection.request(relevantURL+tag, null);
        Document document = Jsoup.parse(result);
        String tags = document.getElementById("hashtagy").text();
        tags = tags.replaceAll("#", "");
        String[] hashtags = tags.split(" ");
        for(String hashtag : hashtags) System.out.println(hashtag);
        return Arrays.asList(hashtags);
    }
    public static List<String> getSimilarTags(String tag) {
        RequestHttpConnection requestHttpConnection = new RequestHttpConnection();
        String result = requestHttpConnection.request(similarURL + tag, null);
        Document document = Jsoup.parse(result);
        String tags = document.getElementById("hashtagy").text();
        tags = tags.replaceAll("#", "");
        String[] hashtags = tags.split(" ");
        for(String hashtag : hashtags) System.out.println(hashtag);
        return Arrays.asList(hashtags);
    }
}
