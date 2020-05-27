package com.capstone.moayo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;


import com.capstone.moayo.BaseActivity;
import com.capstone.moayo.R;
import com.capstone.moayo.adapter.BookPagerAdapter;
import com.capstone.moayo.data.CategoryData_Dummy;

import com.google.android.material.tabs.TabLayout;
import com.capstone.moayo.service.CategoryService;
import com.capstone.moayo.service.concrete.ServiceFactoryCreator;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.util.Async.AsyncCallback;
import com.capstone.moayo.util.Async.AsyncExecutor;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;


public class BookManageActivity extends BaseActivity {
    private ViewPager viewPager ;
    private BookPagerAdapter pagerAdapter ;
    private CategoryService categoryService;
    private TextView numOfBook;
    private TabLayout tabLayout;
    private ArrayList<CategoryDto> userBookData;

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

        viewPager = (ViewPager) findViewById(R.id.pager);
        numOfBook = (TextView) findViewById(R.id.num_of_book);
        TextView createBook2 = (TextView) findViewById(R.id.createBook2);

        //TabLayout
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager, true);

        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        createBook2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookManageActivity.this, BookFormActivity.class);
                BookManageActivity.this.startActivity(intent);
            }
        });

//       TEST DATA 생성하여 변수에 할당.
        Callable<List<CategoryDto>> callable = () -> categoryService.findAll();
        AsyncCallback<List<CategoryDto>> callback = new AsyncCallback<List<CategoryDto>>() {
            @Override
            public void onResult(List<CategoryDto> result) {
                userBookData = (ArrayList<CategoryDto>) result;
                //ViewPager
                pagerAdapter = new BookPagerAdapter(getSupportFragmentManager(), userBookData);
                viewPager.setAdapter(pagerAdapter) ;

                //유저가 가진 도감의 총 개수 표시.
                numOfBook.setText(Integer.toString(userBookData.size()));
            }

            @Override
            public void exceptionOccured(Exception e) {
                e.printStackTrace();
            }

            @Override
            public void cancelled() {

            }
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

//    create test data
    private List<CategoryDto> getUserCategories () {
        //-----backend 통신-----
        List<CategoryDto> categoryDtoList = categoryService.findAll();
        return categoryDtoList;
//        categoryService = new ConcreteCategoryService(getApplicationContext());
//        ArrayList<CategoryDto> categories_data = new ArrayList<>();
//
//        categories_data.add(categoryService.findCategoryById(0));
//        return  categories_data;
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
//                onBackPressed();
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
}