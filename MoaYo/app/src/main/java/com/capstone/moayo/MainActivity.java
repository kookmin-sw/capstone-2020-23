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
import com.capstone.moayo.dao.CategoryDao;
import com.capstone.moayo.dao.CategoryHashtagDao;
import com.capstone.moayo.dao.CategoryPostDao;
import com.capstone.moayo.dao.DogamDao;
import com.capstone.moayo.dao.HashtagDao;
import com.capstone.moayo.dao.PostDao;
import com.capstone.moayo.dao.concrete.CategoryDaoImpl;
import com.capstone.moayo.dao.concrete.CategoryHashtagDaoImpl;
import com.capstone.moayo.dao.concrete.CategoryPostDaoImpl;
import com.capstone.moayo.dao.concrete.DogamDaoImpl;
import com.capstone.moayo.dao.concrete.HashtagDaoImpl;
import com.capstone.moayo.dao.concrete.PostDaoImpl;
import com.capstone.moayo.dao.mapping.CategoryHashTagMapping;
import com.capstone.moayo.dao.mapping.CategoryPostMapping;
import com.capstone.moayo.dao.mapping.DogamMapping;
import com.capstone.moayo.dao.mapping.HashTagMapping;
import com.capstone.moayo.dao.mapping.PostMapping;
import com.capstone.moayo.dao.sqlite.DBHelper;
import com.capstone.moayo.data.MyBookData_Sample;
import com.capstone.moayo.data.SharedData_Sample;
import com.capstone.moayo.entity.CategoryNode;
import com.capstone.moayo.service.CategoryService;
import com.capstone.moayo.service.DataBindingService;
import com.capstone.moayo.storage.StorageFactory;
import com.capstone.moayo.storage.concrete.StorageFactoryCreator;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button createBtn;
    private Button requestDataBtn;
    private Button DBButton;
    private Button findBtn;
    private Button deleteBtn;
    private Button getTagBtn;

    private CategoryService categoryService;
    private DataBindingService dataBindingService;
    private StorageFactory storageFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        StorageFactory sf = StorageFactoryCreator.getInstance();
//        DBHelper mDB = sf.initDao(this);
//
//        try {
//            DogamDao dogamDao = DogamDaoImpl.getInstance();
//            CategoryDao categoryDao = CategoryDaoImpl.getInstance();
//            HashtagDao hashtagDao = HashtagDaoImpl.getInstance();
//            PostDao postDao = PostDaoImpl.getInstance();
//            CategoryPostDao categoryPostDao = CategoryPostDaoImpl.getInstance();
//            CategoryHashtagDao categoryHashtagDao = CategoryHashtagDaoImpl.getInstance();
//
//            mDB.upgrade(mDB.getWritableDB());
//
//            long dogamId = dogamDao.insert(mDB,"NewDogam2","This is new Dogam2","12345");
//            long rootCategoryId = categoryDao.rootInsert(mDB,1,0,"음식",(int)dogamId,(int)dogamId);
////            categoryDao.rootUpdate(mDB,(int)rootCategoryId,1,(int)rootCategoryId,"음식",(int)dogamId,(int)dogamId);
//            hashtagDao.insert(mDB,new HashTagMapping("먹스타그램"));
//            long postId = postDao.insert(mDB,new PostMapping(0,"url","imgUrl","info","hashtag"));
//
//
//            long categoryPostId = categoryPostDao.insert(mDB,new CategoryPostMapping((int)dogamId,(int)rootCategoryId,(int)postId));
//            long categoryHashId = categoryHashtagDao.insert(mDB,new CategoryHashTagMapping((int)dogamId,(int)rootCategoryId,"먹스타그램"));
//
//            long result = categoryDao.insert(mDB,2,(int)rootCategoryId,"중국음식",(int)dogamId,(int)dogamId);
//            System.out.println(result);
//            CategoryNode cn = categoryDao.selectByDogamId(mDB,1);
//            System.out.println(cn.toString());
//
//            DogamMapping[] dm = dogamDao.selectAll(mDB);
//            for(DogamMapping m : dm){
//                System.out.println(m.getId());
//            }
//
//            List<HashTagMapping> hm = hashtagDao.selectAll(mDB);
//            for(HashTagMapping m : hm){
//                System.out.println(m.getHashtag());
//            }
//
//            System.out.println(postDao.selectById(mDB,1).getInfo());
//
//            List<CategoryPostMapping> cpm = categoryPostDao.selectByDogamId(mDB,1);
//            for(CategoryPostMapping m : cpm){
//                System.out.println(m.getCategoryId());
//            }
//
//            List<CategoryHashTagMapping> chm = categoryHashtagDao.selectByDogamId(mDB,1);
//            for(CategoryHashTagMapping m : chm){
//                System.out.println(m.getHashtag());
//            }
//
//        }catch (Exception e ){
//            e.printStackTrace();
//        }

//        //리소스 파일에서 추가한 툴바를 앱바로 지정하기
//        Toolbar mainToolBar = (Toolbar) findViewById(R.id.mainToolBar);
//        setSupportActionBar(mainToolBar);
//
//        getSupportActionBar().setTitle(""); //앱바에서 제목을 없애고 activity_main.xml에서 설정한 제목이 뜨게 설정
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setCustomView(R.layout.logo);

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
