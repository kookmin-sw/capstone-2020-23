package com.capstone.moayo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;


import com.capstone.moayo.R;
import com.capstone.moayo.adapter.BookPagerAdapter;
import com.capstone.moayo.data.CategoryData_Dummy;
import com.capstone.moayo.entity.CategoryNode;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.service.dto.CategoryNodeDto;

import java.util.ArrayList;


public class BookManageActivity extends AppCompatActivity {
    private ViewPager viewPager ;
    private BookPagerAdapter pagerAdapter ;

    private ArrayList<CategoryDto> userBookData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_manage);

        //리소스 파일에서 추가한 툴바를 앱바로 지정하기
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

//       TEST DATA 생성하여 변수에 할당.
        userBookData = createData();

        //ViewPager
        viewPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new BookPagerAdapter(getSupportFragmentManager(), userBookData);
        viewPager.setAdapter(pagerAdapter) ;

        //유저가 가진 도감의 총 개수 표시.
        TextView numOfBook = (TextView) findViewById(R.id.num_of_book);
        numOfBook.setText(Integer.toString(userBookData.size()));


        //Spinner
        Spinner bookTypeSpinner = (Spinner)findViewById(R.id.bookManageSpinner);
        ArrayAdapter bookTypeAdapter = ArrayAdapter.createFromResource(this,
                R.array.book_manage_spinner, android.R.layout.simple_spinner_dropdown_item);
        bookTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bookTypeSpinner.setAdapter(bookTypeAdapter);
    }

//    create test data
    private ArrayList<CategoryDto> createData () {
        return new CategoryData_Dummy().getItems();
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
                onBackPressed();
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