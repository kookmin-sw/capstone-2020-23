package com.capstone.moayo;

import androidx.appcompat.app.ActionBar;
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
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.capstone.moayo.adapter.ExpandableAdapter;
import com.capstone.moayo.adapter.adapter_result1;
import com.capstone.moayo.adapter.adapter_result2;
import com.capstone.moayo.data.CategoryData_Dummy;
import com.capstone.moayo.data.ResultPost_Dummy;
import com.capstone.moayo.data.SavedPost_Dummy;

import com.capstone.moayo.entity.CategoryNode;

import com.capstone.moayo.model.NewPost;
import com.capstone.moayo.model.SavedPost;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private CategoryNode searchNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        searchNode = (CategoryNode) getIntent().getSerializableExtra("current_node");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("# "+searchNode.getTitle());


        // 저장된 게시물 리사이클러뷰 (리사이클러뷰 1)
        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        RecyclerView saved_recycler = findViewById(R.id.recycler1_result);
        saved_recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)) ;

        // 리사이클러뷰에 객체 지정.
        adapter_result1 saved_adapter = new adapter_result1();
        saved_recycler.setAdapter(saved_adapter) ;

        saved_adapter.setItems(requestSavedPost(searchNode));


        // 검색 게시물 리사이클러뷰 (리사이클러뷰 2)
        RecyclerView result_recycler = findViewById(R.id.recycler2_result);
        result_recycler.setLayoutManager(new GridLayoutManager(this,3));


        adapter_result2 result_adapter = new adapter_result2();
        result_recycler.setAdapter(result_adapter);

        //아이템 로드
        result_adapter.setItems(requestResultPost(searchNode));

        //Drawer
        ExpandableListView myList = (ExpandableListView)findViewById(R.id.drawer_expandableListView);
        //create Data
        myList.setAdapter(new ExpandableAdapter(this, (ArrayList<CategoryNode>) getDummyRoot(searchNode).getLowLayer(), searchNode));
    }

    //도감 검색결과 요청.
    private ArrayList<NewPost> requestResultPost(CategoryNode node) {
        //인탠트를 통해 받아온 검색 노드.
        Toast.makeText(getApplicationContext(), node.getTitle(), Toast.LENGTH_SHORT).show();


        if(node.getId() == 1) {
            return new ResultPost_Dummy().getSinger();
        } else {
            return new ResultPost_Dummy().getFood();
        }
        //Dummy Data(데님바지).
//        return new ResultPost_Dummy().getSinger();
    }

    //저장 게시물 요청.
    private ArrayList<SavedPost> requestSavedPost(CategoryNode node) {
        //Dummy Data(데님바지).
        if(node.getId() == 1) {
            return new SavedPost_Dummy().getSinger();
        } else {
            return new SavedPost_Dummy().getFood();
        }

    }

    private CategoryNode getDummyRoot (CategoryNode node) {
        //첫번째 index의 dummy data 가져옴
        if(node.getId() == 1) {
            return new CategoryData_Dummy().getItems().get(0);
        } else {
            return new CategoryData_Dummy().getItems().get(3);
        }

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
