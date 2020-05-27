package com.capstone.moayo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.capstone.moayo.BaseActivity;
import com.capstone.moayo.R;
import com.capstone.moayo.adapter.BookExpandableAdapter;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.service.dto.CategoryNodeDto;

import java.util.ArrayList;


public class BookDetailActivity extends BaseActivity {

    private TextView toolbar_title;
    private CategoryDto category;
    private CategoryNodeDto rootNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        //리소스 파일에서 추가한 툴바를 앱바로 지정하기
        Toolbar toolbar = (Toolbar)findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        category = (CategoryDto) getIntent().getSerializableExtra("category");
        rootNode = category.getRootNode();

        toolbar_title = (TextView) findViewById(R.id.detail_tv_title);
        toolbar_title.setText(rootNode.getTitle());

        ExpandableListView myList = (ExpandableListView)findViewById(R.id.expandableListView);
        //create Data
        myList.setAdapter(new BookExpandableAdapter(this, (ArrayList<CategoryNodeDto>) rootNode.getLowLayer()));
//        CustomAdapter mAdapter = new CustomAdapter (getApplicationContext(), R.layout.cmtview_custom, myList, MainActivity.this);

        TextView detail_text = (TextView)findViewById(R.id.detail_text);
        TextView detail_text2 = (TextView)findViewById(R.id.detail_text2);
        ImageView arrow_detail = (ImageView)findViewById(R.id.arrow_detail);

        detail_text.setText(rootNode.getTitle() + "");

        //listener for child click
//        myList.setOnChildClickListener(myListItemClicked);
        //listener for group click


//        myList.setOnGroupClickListener(myListGroupClicked);

    }

    //child listener
//    private OnChildClickListener myListItemClicked =  new OnChildClickListener() {
//
//        public boolean onChildClick(ExpandableListView parent, View v,
//                                    int groupPosition, int childPosition, long id) {
//
//            //get the group header
////            HeaderInfo headerInfo = deptList.get(groupPosition);
//            //get the child info
////            DetailInfo detailInfo =  headerInfo.getProductList().get(childPosition);
//            //display it or do something with it
////            Toast.makeText(getBaseContext(), "Clicked on Detail " + headerInfo.getName()
////                    + "/" + detailInfo.getName(), Toast.LENGTH_LONG).show();
////            Toast.makeText(getBaseContext(), "on click child!", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//
//    };

    //group listener
//    private OnGroupClickListener myListGroupClicked =  new OnGroupClickListener() {
//
//        public boolean onGroupClick(ExpandableListView parent, View v,
//                                    int groupPosition, long id) {
////            Toast.makeText(getBaseContext(), "on click group!", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//
//    };
}
