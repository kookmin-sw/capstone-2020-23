package com.capstone.moayo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.capstone.moayo.BaseActivity;
import com.capstone.moayo.R;
import com.capstone.moayo.adapter.BookExpandableAdapter;
import com.capstone.moayo.service.CategoryService;
import com.capstone.moayo.service.concrete.ServiceFactoryCreator;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.service.dto.CategoryNodeDto;
import com.capstone.moayo.util.Async.AsyncCallback;
import com.capstone.moayo.util.Async.AsyncExecutor;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.concurrent.Callable;


public class BookDetailActivity extends BaseActivity implements View.OnClickListener {

    private TextView toolbarTitle;
    private CategoryDto category;
    private CategoryNodeDto rootNode;
    private Button updateBtn, deleteBtn, shareBtn;

    private CategoryService categoryService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        categoryService = ServiceFactoryCreator.getInstance().requestCategoryService(getApplicationContext());

        //리소스 파일에서 추가한 툴바를 앱바로 지정하기
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        category = (CategoryDto) getIntent().getSerializableExtra("category");
        rootNode = category.getRootNode();

        toolbarTitle = (TextView) findViewById(R.id.detail_tv_title);
        toolbarTitle.setText(rootNode.getTitle());

        ExpandableListView myList = (ExpandableListView) findViewById(R.id.expandableListView);
        //create Data
        myList.setAdapter(new BookExpandableAdapter(this, (ArrayList<CategoryNodeDto>) rootNode.getLowLayer()));
//        CustomAdapter mAdapter = new CustomAdapter (getApplicationContext(), R.layout.cmtview_custom, myList, MainActivity.this);

        TextView detail_text = (TextView) findViewById(R.id.detail_text);
        TextView detail_text2 = (TextView) findViewById(R.id.detail_text2);
        ImageView arrow_detail = (ImageView) findViewById(R.id.arrow_detail);

        detail_text.setText(rootNode.getTitle() + "");


        //listener for child click
//        myList.setOnChildClickListener(myListItemClicked);
        //listener for group click


//        myList.setOnGroupClickListener(myListGroupClicked);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_bookdetail, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        //menu.xml에서 지정한 item 이벤트 추가
        switch (item.getItemId()) {

            case R.id.bookDetailMenu: {

                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        BookDetailActivity.this, R.style.BottomSheetDialogTheme
                );
                View bottomSheetView = LayoutInflater.from(getApplicationContext())
                        .inflate(R.layout.detail_bottom_menu, (LinearLayout) findViewById(R.id.bottomSheetContainer));

                updateBtn = bottomSheetView.findViewById(R.id.detail_btn_update);
                updateBtn.setOnClickListener(this);

                deleteBtn = bottomSheetView.findViewById(R.id.detail_btn_delete);
                deleteBtn.setOnClickListener(this);

                bottomSheetView.findViewById(R.id.detail_btn_back).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });

                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();

                return true;
            }

            default:
                onBackPressed();
                return true;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.detail_btn_update:
                //TODO: BookForm 화면으로 전환
                Intent intent = new Intent(BookDetailActivity.this, BookFormActivity.class);
                intent.putExtra("category", category);
                startActivity(intent);

                break;

            case R.id.detail_btn_delete:
                //TODO: 도감 삭제 후 BookManage 화면으로 전환
                //--------Backend 통신-----------
                Callable<String> callable = () -> categoryService.deleteDogam(category.getId());
                AsyncCallback<String> callback = new AsyncCallback<String>() {
                    @Override
                    public void onResult(String result) {
                        Intent intent1 = new Intent(BookDetailActivity.this, BookManageActivity.class);
                        startActivity(intent1);
                        Log.d("delete result", result);
                    }

                    @Override
                    public void exceptionOccured(Exception e) {
                        e.toString();
                    }

                    @Override
                    public void cancelled() {

                    }
                };

                new AsyncExecutor<String>().setCallable(callable).setCallback(callback).execute();
                Toast.makeText(getApplicationContext(), "도감 삭제 이벤트", Toast.LENGTH_SHORT).show();
                break;

        }
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
