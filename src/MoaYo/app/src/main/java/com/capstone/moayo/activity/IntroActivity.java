package com.capstone.moayo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.capstone.moayo.R;

public class IntroActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_TIME = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        ImageView image = (ImageView)findViewById(R.id.splash_logo);
        Animation anima = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
        image.startAnimation(anima);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run(){
                startActivity(new Intent(getApplication(), MainActivity.class));
                /* 스플래시 액티비티를 스택에서 제거. */
                IntroActivity.this.finish();

            }
        }, SPLASH_DISPLAY_TIME);
    }
}
