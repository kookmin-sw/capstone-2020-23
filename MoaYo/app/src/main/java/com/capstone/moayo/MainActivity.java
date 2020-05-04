package com.capstone.moayo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.moayo.Adapter.adapter_main1;
import com.capstone.moayo.Adapter.adapter_main2;
//import com.capstone.moayo.R;
import com.capstone.moayo.data.MyBookData_Sample;
import com.capstone.moayo.data.SharedData_Sample;
import com.capstone.moayo.service.CategoryService;
import com.capstone.moayo.service.DataBindingService;
import com.capstone.moayo.storage.StorageFactory;

public class MainActivity extends AppCompatActivity {
    private Button createBtn, requestDataBtn, DBButton, findBtn, deleteBtn, getTagBtn;

    private CategoryService categoryService;
    private DataBindingService dataBindingService;
    private StorageFactory storageFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        //리소스 파일에서 추가한 툴바를 앱바로 지정하기
//        Toolbar mainToolBar = (Toolbar) findViewById(R.id.mainToolBar);
//        setSupportActionBar(mainToolBar);
//
//        getSupportActionBar().setTitle(""); //앱바에서 제목을 없애고 activity_main.xml에서 설정한 제목이 뜨게 설정
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);


        TextView createBook = (TextView) findViewById(R.id.createBook);
        TextView shareBook = (TextView) findViewById(R.id.shareBook);
        TextView myBook = (TextView) findViewById(R.id.myBook);

        ImageButton myBookPlus = (ImageButton) findViewById(R.id.myBookPlus);
        ImageButton shareBookPlus = (ImageButton) findViewById(R.id.shareBookPlus);

        //메뉴 탭의 환결설정 버튼을 임시로 ResultActivity로 이동하는 버튼으로 설정함
        TextView setting = (TextView) findViewById(R.id.setting);

        createBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "BookFormActivity로 이동함", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, BookFormActivity.class);
                MainActivity.this.startActivity(intent);

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left );
            }
        });

        shareBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "ShareMenuActivity로 이동함", Toast.LENGTH_LONG).show();
            }
        });

        myBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BookManageActivity.class);
                MainActivity.this.startActivity(intent);

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left );
            }
        });


        //ResultActivity로 이동하기 위한 임시 버튼
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                MainActivity.this.startActivity(intent);

            }
        });

        myBookPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BookManageActivity.class);
                MainActivity.this.startActivity(intent);

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left );
            }
        });

        shareBookPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "ShareMenuActivity로 이동함", Toast.LENGTH_LONG).show();
            }
        });


        // 나의 도감 리사이클러뷰 (리사이클러뷰 1)
        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        RecyclerView recyclerView = findViewById(R.id.recycler1_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)) ;

        // 리사이클러뷰에 객체 지정.
        adapter_main1 adapter = new adapter_main1();
        recyclerView.setAdapter(adapter) ;

        adapter.setItems(new MyBookData_Sample().getItems());


        // 추천 공유도감 리사이클러뷰 (리사이클러뷰 2)
        RecyclerView recyclerView2 = findViewById(R.id.recycler2_main);
        recyclerView2.setLayoutManager(new GridLayoutManager(this,1));


        adapter_main2 adapter2 = new adapter_main2();
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

            default:
            {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.mainDisplay);
                drawer.openDrawer(Gravity.LEFT);

                return true;
            }

            case R.id.moveMY:
                // User chose the "Settings" item, show the app settings UI
                Intent intent = new Intent(MainActivity.this, BookManageActivity.class);
                MainActivity.this.startActivity(intent);

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left );
                return true;

//            default:
//                Toast.makeText(getApplicationContext(),"메뉴 Tab 펼쳐짐", Toast.LENGTH_LONG).show();
//                return true;

        }
    }
}
