package com.capstone.moayo;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import com.capstone.moayo.Adapter.PagerAdapter;

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
        ArrayList<CategoryNode> myBookList = new ArrayList<>();

//      Node <<Movie>>
        CategoryNode movie = new CategoryNode("영화");

        CategoryNode romance = new CategoryNode("로맨스");
        CategoryNode thriller = new CategoryNode("스릴러");

        CategoryNode horror = new CategoryNode("호러");
        CategoryNode scary_movie = new CategoryNode("무서운영화");
        horror.addChild(scary_movie);

        CategoryNode comedy = new CategoryNode("코미디");
        CategoryNode fantasy = new CategoryNode("판타지");

        movie.addChild(romance);
        movie.addChild(thriller);
        movie.addChild(horror);
        movie.addChild(comedy);
        movie.addChild(fantasy);


//      Node <<Fashion>>
        CategoryNode fashion = new CategoryNode("패션");

        CategoryNode pants = new CategoryNode("하의");
        CategoryNode denim_pants = new CategoryNode("데님바지");
        CategoryNode cotton_pants = new CategoryNode("코튼바지");
        CategoryNode leather_pants = new CategoryNode("레더바지");

        pants.addChild(denim_pants);
        pants.addChild(cotton_pants);
        pants.addChild(leather_pants);

        CategoryNode top = new CategoryNode("상의");
        CategoryNode hood = new CategoryNode("후드");

        top.addChild(hood);

        CategoryNode outer = new CategoryNode("아우터");
        CategoryNode denim_jacket = new CategoryNode("데님자켓");
        CategoryNode cotton_jacket = new CategoryNode("코튼자켓");
        CategoryNode leather_jacket = new CategoryNode("레더자켓");

        outer.addChild(denim_jacket);
        outer.addChild(cotton_jacket);
        outer.addChild(leather_jacket);

        fashion.addChild(pants);
        fashion.addChild(top);
        fashion.addChild(outer);

        myBookList.add(fashion);
        myBookList.add(movie);

        return myBookList;
    }
}