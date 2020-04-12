package com.capstone.moayo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.capstone.moayo.data.SampleData;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);

        // 리사이클러뷰에 표시할 데이터 리스트 생성.
        ArrayList<String> list = new ArrayList<>();
        for (int i=0; i<100; i++) {
            list.add(String.format("SAVE %d", i)) ;
        }

        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        RecyclerView recyclerView = findViewById(R.id.recycler1_result);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)) ;

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        adapter_result1 adapter = new adapter_result1(list);
        recyclerView.setAdapter(adapter) ;


        // 추천 게시물 리사이클러뷰 (리사이클러뷰 2)
        RecyclerView recyclerView2 = findViewById(R.id.recycler2_result);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        adapter_result2 adapter2 = new adapter_result2();
        recyclerView2.setAdapter(adapter2);

        //아이템 로드
        adapter2.setItems(new SampleData().getItems());
    }




    //액션바에 menu_resul.xml 내용 지정
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_result, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {


        //menu.xml에서 지정한 item 이벤트 추가
        switch (item.getItemId()) {


            case R.id.myBook:
            {
                //사용자 도감 탭 펼치기 전, 탭 화면 객체 참조
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.resultDisplay);
                drawer.openDrawer(Gravity.RIGHT);

                return true;
            }
            default:
                onBackPressed();
                return true;
        }

    }
}
