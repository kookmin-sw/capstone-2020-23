package com.capstone.moayo;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.tsengvn.typekit.TypekitContextWrapper;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context newBase) {
        // 커스텀 폰트 로드
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
