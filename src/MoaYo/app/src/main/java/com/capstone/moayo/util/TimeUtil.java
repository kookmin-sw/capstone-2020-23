package com.capstone.moayo.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    public static String getDate(String time) {
        String date = "";
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Log.d("share time", time);
            Date share_date = format.parse(time);
            Date current_date = format.parse(format.format(System.currentTimeMillis()));
            Log.d("share time", share_date.toString());
            Log.d("current time", current_date.toString());
            long diff = (current_date.getTime() - share_date.getTime()) / 1000;
            Log.d("different time", Long.toString(diff));

            if(diff < 60) {
                date = diff + "초 전";
            } else {
                diff = diff / 60;
                if(diff < 60) {
                    date = diff + "분 전";
                } else {
                    diff = diff / 60;
                    if(diff < 24) {
                        date = diff + "시간 전";
                    } else {
                        diff = diff / 24;
                        if(diff < 30) {
                            date = diff + "일 전";
                        } else {
                            date = time.split(" ")[0];
                        }
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
