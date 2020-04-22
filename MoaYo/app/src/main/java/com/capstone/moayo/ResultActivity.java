package com.capstone.moayo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.capstone.moayo.Adapter.adapter_result1;
import com.capstone.moayo.Adapter.adapter_result2;
import com.capstone.moayo.data.RecommendData_Sample;
import com.capstone.moayo.data.SavedData_Sample;

public class ResultActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // 저장된 게시물 리사이클러뷰 (리사이클러뷰 1)
        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        RecyclerView recyclerView = findViewById(R.id.recycler1_result);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)) ;

        // 리사이클러뷰에 객체 지정.
        adapter_result1 adapter = new adapter_result1();
        recyclerView.setAdapter(adapter) ;

        adapter.setItems(new SavedData_Sample().getItems());


        // 추천 게시물 리사이클러뷰 (리사이클러뷰 2)
        RecyclerView recyclerView2 = findViewById(R.id.recycler2_result);
        recyclerView2.setLayoutManager(new GridLayoutManager(this,3));


        adapter_result2 adapter2 = new adapter_result2();
        recyclerView2.setAdapter(adapter2);

        //아이템 로드
        adapter2.setItems(new RecommendData_Sample().getItems());
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
