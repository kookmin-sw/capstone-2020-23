package com.capstone.moayo.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.capstone.moayo.R;
import com.capstone.moayo.adapter.MainTopRecyclerAdapter;
import com.capstone.moayo.adapter.MainCenterRecyclerAdapter;

import com.capstone.moayo.data.SharedData_Sample;
import com.capstone.moayo.service.CategoryService;
import com.capstone.moayo.service.ShareService;
import com.capstone.moayo.service.concrete.ServiceFactoryCreator;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.util.Async.AsyncCallback;
import com.capstone.moayo.util.Async.AsyncExecutor;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button createBtn, shareBtn, bookManageBtn, aboutBtn, requestDataBtn, DBButton, findBtn, deleteBtn, getTagBtn;
    private BottomSheetDialog bottomSheetDialog;


    private CategoryService categoryService;
    private ShareService shareService;
    private RecyclerView topRecyclerView, centerRecyclerView;
    private MainTopRecyclerAdapter mainTopRecyclerAdapter;
    private MainCenterRecyclerAdapter mainCenterRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoryService = ServiceFactoryCreator.getInstance().requestCategoryService(getApplicationContext());
        shareService = ServiceFactoryCreator.getInstance().requestShareService(getApplicationContext());

        //리소스 파일에서 추가한 툴바를 앱바로 지정하기
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowTitleEnabled(false); //앱바에서 제목을 없애고 activity_main.xml에서 설정한 제목이 뜨게 설정
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        ImageButton myBookPlus = (ImageButton)findViewById(R.id.myBookPlus);
        ImageButton shareBookPlus = (ImageButton)findViewById(R.id.shareBookPlus);

        TextView emptyView = (TextView) findViewById(R.id.empty_view);

        myBookPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BookManageActivity.class);
                MainActivity.this.startActivity(intent);

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });


        shareBookPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ShareMenuActivity.class);
                MainActivity.this.startActivity(intent);

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });


        // 나의 도감 리사이클러뷰 (리사이클러뷰 1)
        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        topRecyclerView = findViewById(R.id.recycler1_main);
        topRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // 리사이클러뷰에 객체 지정.
        mainTopRecyclerAdapter = new MainTopRecyclerAdapter();
        topRecyclerView.setAdapter(mainTopRecyclerAdapter);

        Callable<List<CategoryDto>> myBookcallable = () -> categoryService.findAll();
        AsyncCallback<List<CategoryDto>> myBookcallback = new AsyncCallback<List<CategoryDto>>() {
            @Override
            public void onResult(List<CategoryDto> result) {
//                Log.d("----------------category found----------------" , result.toString());
                mainTopRecyclerAdapter.setItems((ArrayList<CategoryDto>) result);
                mainTopRecyclerAdapter.notifyDataSetChanged();

                if (result.isEmpty()) {
                    topRecyclerView.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                }
                else {
                    topRecyclerView.setVisibility(View.VISIBLE);
                    emptyView.setVisibility(View.GONE);
                }
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
        }.setCallable(myBookcallable).setCallback(myBookcallback).execute();


        // 공유도감 리사이클러뷰 (리사이클러뷰 2)
        centerRecyclerView = findViewById(R.id.recycler2_main);
//        recyclerView2.setLayoutManager(new GridLayoutManager(this, 1));
        centerRecyclerView.setLayoutManager(new GridLayoutManager(this,1){
            @Override
            public boolean canScrollVertically() { // 세로스크롤 막기
                return false;
            }

            @Override
            public boolean canScrollHorizontally() { //가로 스크롤막기
                return false;
            }
        });


        mainCenterRecyclerAdapter = new MainCenterRecyclerAdapter();
        centerRecyclerView.setAdapter(mainCenterRecyclerAdapter);

        Callable<List<CategoryDto>> shareBookcallable = () -> shareService.findAll();
        AsyncCallback<List<CategoryDto>> shareBookcallback = new AsyncCallback<List<CategoryDto>>() {
            @Override
            public void onResult(List<CategoryDto> result) {
//                Log.d("----------------category found----------------" , result.toString());
                mainCenterRecyclerAdapter.setItems((ArrayList<CategoryDto>) result);
                mainCenterRecyclerAdapter.notifyDataSetChanged();

                if (result.isEmpty()) {
                    centerRecyclerView.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                }
                else {
                    centerRecyclerView.setVisibility(View.VISIBLE);
                    emptyView.setVisibility(View.GONE);
                }
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
        }.setCallable(shareBookcallable).setCallback(shareBookcallback).execute();

        //아이템 로드
        mainCenterRecyclerAdapter.setItems(new SharedData_Sample().getItems());
    }



    //mainToolBar에 menu.xml을 인플레이트함
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {

        //menu.xml에서 지정한 item 이벤트 추가
        switch (item.getItemId()) {

            default: {
//                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.mainDisplay);
//                drawer.openDrawer(Gravity.LEFT);

                bottomSheetDialog = new BottomSheetDialog(
                        MainActivity.this, R.style.BottomSheetDialogTheme
                );

                View bottomSheetView = LayoutInflater.from(getApplicationContext())
                        .inflate(R.layout.main_bottom_menu, (LinearLayout)findViewById(R.id.bottomSheetContainer));

                createBtn = bottomSheetView.findViewById(R.id.createBook);
                createBtn.setOnClickListener(this);

                shareBtn = bottomSheetView.findViewById(R.id.shareBook);
                shareBtn.setOnClickListener(this);

                bookManageBtn = bottomSheetView.findViewById(R.id.myBook);
                bookManageBtn.setOnClickListener(this);

                aboutBtn = bottomSheetView.findViewById(R.id.setting);
                aboutBtn.setOnClickListener(this);

                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();

                return true;
            }

            case R.id.moveMY:
                // User chose the "Settings" item, show the app settings UI
                Intent intent = new Intent(MainActivity.this, BookManageActivity.class);
                MainActivity.this.startActivity(intent);

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                return true;

        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.createBook:
                intent = new Intent(MainActivity.this, BookFormActivity.class);
                MainActivity.this.startActivity(intent);
                bottomSheetDialog.dismiss();

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;

            case R.id.shareBook:
                intent = new Intent(MainActivity.this, ShareMenuActivity.class);
                MainActivity.this.startActivity(intent);
                bottomSheetDialog.dismiss();

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;

            case R.id.myBook:
                intent = new Intent(MainActivity.this, BookManageActivity.class);
                MainActivity.this.startActivity(intent);
                bottomSheetDialog.dismiss();

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;

            case R.id.setting:
                intent = new Intent(MainActivity.this, AboutActivity.class);
                MainActivity.this.startActivity(intent);
                bottomSheetDialog.dismiss();

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        }
    }


}
