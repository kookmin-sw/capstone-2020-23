package com.capstone.moayo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.capstone.moayo.R;
import com.capstone.moayo.adapter.BookPagerAdapter;
import com.capstone.moayo.util.DogamStatus;
import com.google.android.material.tabs.TabLayout;
import com.capstone.moayo.service.CategoryService;
import com.capstone.moayo.service.concrete.ServiceFactoryCreator;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.util.Async.AsyncCallback;
import com.capstone.moayo.util.Async.AsyncExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;


public class BookManageActivity extends AppCompatActivity implements View.OnClickListener{
    private ViewPager viewPager ;
    private BookPagerAdapter pagerAdapter ;
    private CategoryService categoryService;
    private TextView numOfBook, createTV, numOfShare, numOfLike;
    private TabLayout tabLayout;
    private ArrayList<CategoryDto> userBookData, userSharedData, userLikedData;
    private LinearLayout shareLayout, likeLayout, totalLayout;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_manage);
        categoryService = ServiceFactoryCreator.getInstance().requestCategoryService(getApplicationContext());

        //리소스 파일에서 추가한 툴바를 앱바로 지정하기
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        viewPager = (ViewPager) findViewById(R.id.pager);

        numOfBook = (TextView) findViewById(R.id.num_of_book);
        createTV = (TextView) findViewById(R.id.activity_manage_tv_create);
        numOfShare = (TextView) findViewById(R.id.num_of_share);

        shareLayout = (LinearLayout) findViewById(R.id.activity_manage_ll_share);
        likeLayout = (LinearLayout) findViewById(R.id.activity_manage_ll_like);
        totalLayout = (LinearLayout) findViewById(R.id.activity_manage_ll_total);

        //TabLayout
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager, true);


        createTV.setOnClickListener(this);
        shareLayout.setOnClickListener(this);
        likeLayout.setOnClickListener(this);
        totalLayout.setOnClickListener(this);


        Callable<List<CategoryDto>> callable = () -> categoryService.findAll();
        AsyncCallback<List<CategoryDto>> callback = new AsyncCallback<List<CategoryDto>>() {
            @Override
            public void onResult(List<CategoryDto> result) {
                userBookData = (ArrayList<CategoryDto>) result;
                //ViewPager
                pagerAdapter = new BookPagerAdapter(getSupportFragmentManager(), userBookData);
                viewPager.setAdapter(pagerAdapter);

                //유저가 가진 도감의 총 개수 표시.
                numOfBook.setText(Integer.toString(userBookData.size()));

                userSharedData = new ArrayList<>();
                userBookData.forEach(obj -> {
                    if(obj.getStatus() == DogamStatus.Sharing) {
                        userSharedData.add(obj);
                    }
                });
                numOfShare.setText(Integer.toString(userSharedData.size()));

                userLikedData = new ArrayList<>();
                userLikedData.forEach(obj -> {
                    if(obj.isLiked()) userLikedData.add(obj);
                });
                if(!userLikedData.isEmpty())
                    numOfLike.setText(Integer.toString(userLikedData.size()));
            }

            @Override
            public void exceptionOccured(Exception e) {
                e.printStackTrace();
            }

            @Override
            public void cancelled() { }
        };


        new AsyncExecutor<List<CategoryDto>>(){
            @Override
            protected void onProgressUpdate(Void... values) {

                super.onProgressUpdate(values);
            }
        }.setCallable(callable).setCallback(callback).execute();


//        //Spinner
//        Spinner bookTypeSpinner = (Spinner)findViewById(R.id.bookManageSpinner);
//        ArrayAdapter bookTypeAdapter = ArrayAdapter.createFromResource(this,
//                R.array.book_manage_spinner, android.R.layout.simple_spinner_dropdown_item);
//        bookTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        bookTypeSpinner.setAdapter(bookTypeAdapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.activity_manage_ll_share:
                break;

            case R.id.activity_manage_ll_like:
                break;

            case R.id.activity_manage_ll_total:
                break;

            case R.id.activity_manage_tv_create:
                Intent intent = new Intent(BookManageActivity.this, BookFormActivity.class);
                BookManageActivity.this.startActivity(intent);
                break;
        }
    }

    //mainToolBar에 menu.xml을 인플레이트함
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_bookmanage, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        //menu_bookmanage.xml에서 지정한 item 이벤트 추가
        switch (item.getItemId()) {
            default: {
                Intent intent = new Intent(BookManageActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            }
            case R.id.createBook:
                // User chose the "Settings" item, show the app settings UI
                Intent intent = new Intent(BookManageActivity.this, BookFormActivity.class);
                BookManageActivity.this.startActivity(intent);

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                return true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        userBookData = (ArrayList<CategoryDto>) categoryService.findAll();
        pagerAdapter = new BookPagerAdapter(getSupportFragmentManager(), userBookData);
        viewPager.setAdapter(pagerAdapter);

    }
}