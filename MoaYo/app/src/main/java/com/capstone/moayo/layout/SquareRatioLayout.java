package com.capstone.moayo.layout;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;


public class SquareRatioLayout extends LinearLayout {
    public SquareRatioLayout(Context context) {
        super(context);
    }

    public SquareRatioLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareRatioLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SquareRatioLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int heightMeasureSpec, int widthMeasureSpec) {
        // Set a square layout.
        super.onMeasure(heightMeasureSpec, heightMeasureSpec);
    }
}
