package com.capstone.moayo.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.capstone.moayo.BaseActivity;
import com.capstone.moayo.R;
import com.capstone.moayo.adapter.BookExpandableAdapter;
import com.capstone.moayo.adapter.ResultTopRecyclerAdapter;
import com.capstone.moayo.adapter.ResultCenterRecyclerAdapter;

import com.capstone.moayo.data.CategoryData_Dummy;
import com.capstone.moayo.service.PostService;
import com.capstone.moayo.service.SearchService;
import com.capstone.moayo.service.concrete.ServiceFactoryCreator;
import com.capstone.moayo.service.dto.CategoryNodeDto;
import com.capstone.moayo.service.dto.InstantPost;
import com.capstone.moayo.service.dto.PostDto;
import com.capstone.moayo.service.dto.RespondForm;
import com.capstone.moayo.util.Async.AsyncCallback;
import com.capstone.moayo.util.Async.AsyncExecutor;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class ResultActivity extends BaseActivity {

    private CategoryNodeDto searchNode;
    private CategoryNodeDto rootNode;

    private PostService postService;
    private SearchService searchService;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        postService = ServiceFactoryCreator.getInstance().requestPostService(getApplicationContext());
        searchService = ServiceFactoryCreator.getInstance().requestSearchService(getApplicationContext());

        //리소스 파일에서 추가한 툴바를 앱바로 지정하기
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        searchNode = (CategoryNodeDto) getIntent().getSerializableExtra("current_node");

        TextView textView = (TextView) findViewById(R.id.hashtagName);
        textView.setText("# " + searchNode.getTitle());

        // 저장된 게시물 리사이클러뷰 (리사이클러뷰 1)
        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        RecyclerView saved_recycler = findViewById(R.id.recycler1_result);
        saved_recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)) ;

        // 리사이클러뷰에 객체 지정.
        ResultTopRecyclerAdapter saved_adapter = new ResultTopRecyclerAdapter();
        saved_recycler.setAdapter(saved_adapter) ;

        progressBar = (ProgressBar) findViewById(R.id.activity_result_pb_circle);

        Callable<ArrayList<PostDto>> callable0 = () -> requestSavedPost(searchNode);
        AsyncCallback<ArrayList<PostDto>> callback0 = new AsyncCallback<ArrayList<PostDto>>() {
            @Override
            public void onResult(ArrayList<PostDto> result) {
                saved_adapter.setItems(requestSavedPost(searchNode));
                saved_adapter.notifyDataSetChanged();
            }

            @Override
            public void exceptionOccured(Exception e) {

            }

            @Override
            public void cancelled() {

            }
        };
        new AsyncExecutor<ArrayList<PostDto>>().setCallable(callable0).setCallback(callback0).execute();


        // 검색 게시물 리사이클러뷰 (리사이클러뷰 2)
        RecyclerView result_recycler = findViewById(R.id.recycler2_result);
        result_recycler.setLayoutManager(new GridLayoutManager(this,3));


        ResultCenterRecyclerAdapter result_adapter = new ResultCenterRecyclerAdapter();
        result_recycler.setAdapter(result_adapter);

        //아이템 로드
        Callable<ArrayList<InstantPost>> callable1 = () -> requestResultPost(searchNode);
        AsyncCallback<ArrayList<InstantPost>> callback1 = new AsyncCallback<ArrayList<InstantPost>>() {
            @Override
            public void onResult(ArrayList<InstantPost> result) {
                result_adapter.setItems(result);
                result_adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
                for(InstantPost post : result) Log.d("found result", post.toString());

            }

            @Override
            public void exceptionOccured(Exception e) {

            }

            @Override
            public void cancelled() {

            }
        };
        new AsyncExecutor<ArrayList<InstantPost>>(){
            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
                progressBar.setVisibility(View.VISIBLE);
            }
        }.setCallable(callable1).setCallback(callback1).execute();
        //Drawer
        ExpandableListView myList = (ExpandableListView)findViewById(R.id.drawer_expandableListView);
        //create Data
//        myList.setAdapter(new BookExpandableAdapter(this, (ArrayList<CategoryNodeDto>) getDummyRoot(searchNode).getLowLayer(), searchNode));
        getRootNode(searchNode);
        myList.setAdapter(new BookExpandableAdapter(this, (ArrayList<CategoryNodeDto>) rootNode.getLowLayer(), searchNode));

    }

    //도감 검색결과 요청.
    private ArrayList<InstantPost> requestResultPost(CategoryNodeDto node) {
        RespondForm foundForm = searchService.requestData(node.getParent(), node);
        ArrayList<InstantPost> foundPost = (ArrayList<InstantPost>) foundForm.getThrid_layer();

        return foundPost;
    }

    //저장 게시물 요청.
    private ArrayList<PostDto> requestSavedPost(CategoryNodeDto node) {
        ArrayList<PostDto> foundPost = (ArrayList<PostDto>) postService.findPostByCategoryNodeId(node.getId());
        return foundPost;
    }

    private void getRootNode(CategoryNodeDto node) {
        if(node.getParent() != null) {
            getRootNode(node.getParent());
        } else {
            rootNode = node;
        }
    }

//    private CategoryNodeDto getDummyRoot (CategoryNodeDto node) {
//        //첫번째 index의 dummy data 가져옴
//        if(node.getId() == 1) {
//            return new CategoryData_Dummy().getItems().get(0).getRootNode();
//        } else {
//            return new CategoryData_Dummy().getItems().get(3).getRootNode();
//        }
//
//    }


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
