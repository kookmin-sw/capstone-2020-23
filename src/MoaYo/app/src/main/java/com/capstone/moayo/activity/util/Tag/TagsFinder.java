package com.capstone.moayo.util.Tag;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class TagsFinder{

    private String relevantURL ="https://www.tagsfinder.com/ko-kr/related/";
    private String similarURL = "https://www.tagsfinder.com/ko-kr/similar/";

    public void getRelevantTags(String tag) throws Exception {
        NetworkTask networkTask = new NetworkTask(relevantURL + tag,null);
        networkTask.execute();
    }
    public void getSimilarTags(String tag){
        NetworkTask networkTask = new NetworkTask(similarURL + tag,null);
        networkTask.execute();
    }
    public class NetworkTask extends AsyncTask<Void, Void, String>{
        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values) {
            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... params){
            String result;
            RequestHttpConnection requestHttpConnection = new RequestHttpConnection();
            result = requestHttpConnection.request(url,values);
            return result;
        }
        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);
            Document document = Jsoup.parse(s);
            // 이 부분을 수정 바랍니다.
            System.out.println(document.getElementById("hashtagy").text());
        }
    }
}
