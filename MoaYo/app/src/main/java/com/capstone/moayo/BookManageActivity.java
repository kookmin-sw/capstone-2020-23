package com.capstone.moayo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.drm.DrmStore;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class BookManageActivity extends AppCompatActivity {
    private ViewPager viewPager ;
    private PagerAdapter pagerAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_manage);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("나의 도감");

        actionBar.setDisplayHomeAsUpEnabled(true);


        //ViewPager
        viewPager = (ViewPager) findViewById(R.id.pager) ;
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter) ;

    }
}