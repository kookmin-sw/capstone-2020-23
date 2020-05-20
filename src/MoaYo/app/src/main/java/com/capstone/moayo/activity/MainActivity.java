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

import com.capstone.moayo.R;
import com.capstone.moayo.adapter.MainTopRecyclerAdapter;
import com.capstone.moayo.adapter.MainCenterRecyclerAdapter;
//import com.capstone.moayo.R;
import com.capstone.moayo.data.CategoryData_Dummy;
import com.capstone.moayo.data.SharedData_Sample;
import com.capstone.moayo.service.CategoryService;
import com.capstone.moayo.service.DataBindingService;
import com.capstone.moayo.storage.StorageFactory;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends AppCompatActivity {
    private Button createBtn, requestDataBtn, DBButton, findBtn, deleteBtn, getTagBtn;

    private CategoryService categoryService;
    private DataBindingService dataBindingService;
    private StorageFactory storageFactory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //리소스 파일에서 추가한 툴바를 앱바로 지정하기
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowTitleEnabled(false); //앱바에서 제목을 없애고 activity_main.xml에서 설정한 제목이 뜨게 설정
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.menu);

        ImageButton myBookPlus = (ImageButton)findViewById(R.id.myBookPlus);
        ImageButton shareBookPlus = (ImageButton)findViewById(R.id.shareBookPlus);

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
        RecyclerView recyclerView = findViewById(R.id.recycler1_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // 리사이클러뷰에 객체 지정.
        MainTopRecyclerAdapter adapter = new MainTopRecyclerAdapter();
        recyclerView.setAdapter(adapter);

        adapter.setItems(new CategoryData_Dummy().getItems());


        // 공유도감 리사이클러뷰 (리사이클러뷰 2)
        RecyclerView recyclerView2 = findViewById(R.id.recycler2_main);
//        recyclerView2.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView2.setLayoutManager(new GridLayoutManager(this,1){
            @Override
            public boolean canScrollVertically() { // 세로스크롤 막기
                return false;
            }

            @Override
            public boolean canScrollHorizontally() { //가로 스크롤막기
                return false;
            }
        });


        MainCenterRecyclerAdapter adapter2 = new MainCenterRecyclerAdapter();
        recyclerView2.setAdapter(adapter2);

        //아이템 로드
        adapter2.setItems(new SharedData_Sample().getItems());

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

                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        MainActivity.this, R.style.BottomSheetDialogTheme
                );
                View bottomSheetView = LayoutInflater.from(getApplicationContext())
                        .inflate(R.layout.main_bottom_menu, (LinearLayout)findViewById(R.id.bottomSheetContainer));

                bottomSheetView.findViewById(R.id.createBook).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, BookFormActivity.class);
                        MainActivity.this.startActivity(intent);
                        bottomSheetDialog.dismiss();

                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                });

                bottomSheetView.findViewById(R.id.shareBook).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, ShareMenuActivity.class);
                        MainActivity.this.startActivity(intent);
                        bottomSheetDialog.dismiss();

                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                });

                bottomSheetView.findViewById(R.id.myBook).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, BookManageActivity.class);
                        MainActivity.this.startActivity(intent);
                        bottomSheetDialog.dismiss();

                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                });

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

//            default:
//                Toast.makeText(getApplicationContext(),"메뉴 Tab 펼쳐짐", Toast.LENGTH_LONG).show();
//                return true;

        }
    }
}
