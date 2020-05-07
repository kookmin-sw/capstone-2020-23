package com.capstone.moayo;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import com.capstone.moayo.adapter.PagerAdapter;
import com.capstone.moayo.data.CategoryData_Dummy;
import com.capstone.moayo.entity.CategoryNode;

import java.util.ArrayList;


public class BookManageActivity extends AppCompatActivity {
    private ViewPager viewPager ;
    private PagerAdapter pagerAdapter ;

    private ArrayList<CategoryNode> userBookData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_manage);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("나의 도감");

        actionBar.setDisplayHomeAsUpEnabled(true);

//       TEST DATA 생성하여 변수에 할당.
        userBookData = createData();

        //ViewPager
        viewPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), userBookData);
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
    private ArrayList<CategoryNode> createData () {
        return new CategoryData_Dummy().getItems();
    }
}