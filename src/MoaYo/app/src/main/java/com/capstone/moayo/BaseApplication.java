package com.capstone.moayo;

import android.app.Application;
import android.graphics.Typeface;

import com.tsengvn.typekit.Typekit;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this,"font/nanum__normal.ttf"))
                .addBold(Typekit.createFromAsset(this,"font/nanum__bold.ttf"));

    }
}
