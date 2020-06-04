package com.capstone.moayo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.capstone.moayo.BaseActivity;
import com.capstone.moayo.R;
import com.capstone.moayo.data.CategoryData_Dummy;
import com.capstone.moayo.entity.Category;
import com.capstone.moayo.service.CategoryService;
import com.capstone.moayo.service.PostService;
import com.capstone.moayo.service.ShareService;
import com.capstone.moayo.service.concrete.ServiceFactoryCreator;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.storage.map.MemoryMap;
import com.capstone.moayo.util.Async.AsyncCallback;
import com.capstone.moayo.util.Async.AsyncExecutor;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class IntroActivity extends BaseActivity {

    private final int SPLASH_DISPLAY_TIME = 2500;
    CategoryService categoryService;
    PostService postService;
    ShareService shareService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        categoryService = ServiceFactoryCreator.getInstance().requestCategoryService(getApplicationContext());
        postService = ServiceFactoryCreator.getInstance().requestPostService(getApplicationContext());
        shareService = ServiceFactoryCreator.getInstance().requestShareService(getApplicationContext());


        ImageView image = (ImageView)findViewById(R.id.splash_logo);
        Animation anima = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
        image.startAnimation(anima);
        init();
        showInitResult();

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

    private void init() {
        categoryService.init();
        postService.init();
    }

    private void showInitResult() {
        Collection<Category> categoryDtoList = MemoryMap.getInstance().getCategoryMap().values();
        for(Category category : categoryDtoList) {
            System.out.println(category.toString());
        }
    }
}
