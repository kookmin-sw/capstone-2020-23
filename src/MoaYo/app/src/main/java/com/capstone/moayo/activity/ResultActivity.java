package com.capstone.moayo.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.moayo.BaseActivity;
import com.capstone.moayo.R;
import com.capstone.moayo.adapter.BookExpandableAdapter;
import com.capstone.moayo.adapter.ResultTopRecyclerAdapter;
import com.capstone.moayo.adapter.ResultCenterRecyclerAdapter;

import com.capstone.moayo.data.CategoryData_Dummy;
import com.capstone.moayo.entity.Post;
import com.capstone.moayo.service.CategoryService;
import com.capstone.moayo.service.PostService;
import com.capstone.moayo.service.SearchService;
import com.capstone.moayo.service.ServiceFactory;
import com.capstone.moayo.service.concrete.ServiceFactoryCreator;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.service.dto.CategoryNodeDto;
import com.capstone.moayo.service.dto.InstantPost;
import com.capstone.moayo.service.dto.PostDto;
import com.capstone.moayo.service.dto.RespondForm;
import com.capstone.moayo.util.Async.AsyncCallback;
import com.capstone.moayo.util.Async.AsyncExecutor;
import com.wang.avi.AVLoadingIndicatorView;
import com.capstone.moayo.util.DogamStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class ResultActivity extends BaseActivity {

    private CategoryNodeDto searchNode;
    private CategoryDto selectCategory;

    private PostService postService;
    private SearchService searchService;
    private CategoryService categoryService;

    private AVLoadingIndicatorView progressBar;

    private List<InstantPost> searchPost;
    private List<PostDto> savePost;

    private int doubleClickFlag = 0;
    private int save_double_flag = 0;
    private final long  CLICK_DELAY = 250;

    private AsyncExecutor saveExecutor, searchExecutor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        postService = ServiceFactoryCreator.getInstance().requestPostService(getApplicationContext());
        searchService = ServiceFactoryCreator.getInstance().requestSearchService(getApplicationContext());
        categoryService = ServiceFactoryCreator.getInstance().requestCategoryService(getApplicationContext());

        searchPost = new ArrayList<>();
        savePost = new ArrayList<>();

        //리소스 파일에서 추가한 툴바를 앱바로 지정하기
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        searchNode = (CategoryNodeDto) getIntent().getSerializableExtra("current_node");
        selectCategory = (CategoryDto) getIntent().getSerializableExtra("category");

        TextView textView = (TextView) findViewById(R.id.hashtagName);
        textView.setText("# " + searchNode.getTitle());

        TextView emptyView = (TextView) findViewById(R.id.empty_view);
        TextView result_drawer_title = (TextView) findViewById(R.id.result_drawer_title);
        TextView current_tag = (TextView) findViewById(R.id.result_drawer_tag);

        result_drawer_title.setText(selectCategory.getTitle());

        current_tag.setText("현재 검색 태그 : " + searchNode.getTitle());

        // 저장된 게시물 리사이클러뷰
        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        RecyclerView saved_recycler = findViewById(R.id.recycler1_result);
        saved_recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)) ;

        // 리사이클러뷰에 객체 지정.
        ResultTopRecyclerAdapter saved_adapter = new ResultTopRecyclerAdapter();
        saved_adapter.setOnItemClickListener(new ResultTopRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                save_double_flag++;
                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        if(save_double_flag == 1) {
                            PostDto postDto = savePost.get(position);
                            Intent viewIntent = new Intent("android.intent.action.VIEW",
                                    Uri.parse("https://www.instagram.com/p/" + postDto.getUrl()));
                            v.getContext().startActivity(viewIntent);
                        }
                        save_double_flag = 0;
                    }
                };

                if(save_double_flag == 1) {
                    handler.postDelayed(runnable, CLICK_DELAY);
                } else if (save_double_flag == 2) {
                    save_double_flag = 0;
                    PostDto postDto = savePost.get(position);
                    Callable<String> callable = () -> postService.deletePostById(postDto.getCategoryNodeId(), postDto.getId());
                    AsyncCallback<String> callback = new AsyncCallback<String>() {
                        @Override
                        public void onResult(String result) {
                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                            savePost.remove(postDto);
                            saved_adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void exceptionOccured(Exception e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void cancelled() {

                        }
                    };
                    new AsyncExecutor<String>().setCallback(callback).setCallable(callable).execute();
                }
            }
        });
        saved_recycler.setAdapter(saved_adapter);

        progressBar = (AVLoadingIndicatorView) findViewById(R.id.activity_result_pb_circle);

        saved_recycler.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);


        Callable<ArrayList<PostDto>> callable0 = () -> requestSavedPost(searchNode);
        AsyncCallback<ArrayList<PostDto>> callback0 = new AsyncCallback<ArrayList<PostDto>>() {
            @Override
            public void onResult(ArrayList<PostDto> result) {
                Log.d("PostDto:result data info", result.toString());
                savePost.addAll(result);
                saved_adapter.setItems((ArrayList<PostDto>) savePost);
                saved_adapter.notifyDataSetChanged();

                if (savePost.isEmpty()) {
                    saved_recycler.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                } else {
                    saved_recycler.setVisibility(View.VISIBLE);
                    emptyView.setVisibility(View.GONE);
                }


            }

            @Override
            public void exceptionOccured(Exception e) {

            }

            @Override
            public void cancelled() {

            }
        };
        saveExecutor = (AsyncExecutor) new AsyncExecutor<ArrayList<PostDto>>().setCallable(callable0).setCallback(callback0).execute();


        // 검색 게시물 리사이클러뷰
        RecyclerView result_recycler = findViewById(R.id.recycler2_result);
        result_recycler.setLayoutManager(new GridLayoutManager(this,3));


        ResultCenterRecyclerAdapter result_adapter = new ResultCenterRecyclerAdapter();
        result_recycler.setAdapter(result_adapter);

        result_adapter.setOnItemClickListener(new ResultCenterRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
//                // TODO : 아이템 클릭 이벤트를 ResultActivity 에서 처리.
//                InstantPost selected_item = searchPost.get(position) ;
//                Intent viewIntent = new Intent("android.intent.action.VIEW",
//                                        Uri.parse("https://www.instagram.com/p/" + selected_item.getUrl()));
//                v.getContext().startActivity(viewIntent);
                doubleClickFlag++;
                Handler handler = new Handler();
                Runnable clickRunnable = new Runnable() {
                    @Override
                    public void run() {
                        if(doubleClickFlag == 1) {
//                         todo 클릭 이벤트
                            InstantPost selected_item = searchPost.get(position) ;
                            Intent viewIntent = new Intent("android.intent.action.VIEW",
                                    Uri.parse("https://www.instagram.com/p/" + selected_item.getUrl()));
                            v.getContext().startActivity(viewIntent);
                            Toast.makeText(getApplicationContext(), "single click", Toast.LENGTH_SHORT).show();
                        }
                        doubleClickFlag = 0;
                    }
                };

                if( doubleClickFlag == 1 ) {
                    handler.postDelayed( clickRunnable, CLICK_DELAY );
                }else if( doubleClickFlag == 2 ) {
                    doubleClickFlag = 0;
                    // todo 더블클릭 이벤트
                    if(selectCategory.getStatus() == DogamStatus.Shared_Immutable || selectCategory.getStatus() == DogamStatus.Shared_Mutable) {
                        Toast.makeText(getApplicationContext(), String.format("먼저 도감 '%s'를 저장해야 합니다.", selectCategory.getTitle()), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Callable<PostDto> callable = () -> postService.createPost(searchPost.get(position), searchNode.getId(),selectCategory.getId());
                    AsyncCallback<PostDto> callback = new AsyncCallback<PostDto>() {
                        @Override
                        public void onResult(PostDto result) {
                            savePost.add(result);
                            saved_adapter.setItems((ArrayList<PostDto>) savePost);
                            selectCategory.setUrl(result.getImgUrl());
                            saved_adapter.notifyDataSetChanged();

                            if (savePost.isEmpty()) {
                                saved_recycler.setVisibility(View.GONE);
                                emptyView.setVisibility(View.VISIBLE);
                            } else {
                                saved_recycler.setVisibility(View.VISIBLE);
                                emptyView.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void exceptionOccured(Exception e) {

                        }

                        @Override
                        public void cancelled() {

                        }
                    };
                    new AsyncExecutor<PostDto>().setCallable(callable).setCallback(callback).execute();
                    Toast.makeText(getApplicationContext(), "double click", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //아이템 로드
        Callable<ArrayList<InstantPost>> callable1 = () -> requestResultPost(searchNode);
        AsyncCallback<ArrayList<InstantPost>> callback1 = new AsyncCallback<ArrayList<InstantPost>>() {
            @Override
            public void onResult(ArrayList<InstantPost> result) {
                searchPost.addAll(result);
                result_adapter.setItems((ArrayList<InstantPost>) searchPost);
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
        searchExecutor = (AsyncExecutor) new AsyncExecutor<ArrayList<InstantPost>>(){
            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
                progressBar.setVisibility(View.VISIBLE);
            }
        }.setCallable(callable1).setCallback(callback1).execute();



        result_recycler.setOnScrollChangeListener(new RecyclerView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                if(!result_recycler.canScrollVertically(-1)) {
//                    Toast.makeText(getApplicationContext(), "최상단", Toast.LENGTH_SHORT).show();
//                } else if(!result_recycler.canScrollVertically(1)) {
//                    Toast.makeText(getApplicationContext(), "최하단", Toast.LENGTH_SHORT).show();
//                }
                if(!result_recycler.canScrollVertically(1)) {
                    Callable<ArrayList<InstantPost>> callable1 = () -> (ArrayList<InstantPost>) searchService.requestData(searchNode.getParent(), searchNode);
                    AsyncCallback<ArrayList<InstantPost>> callback1 = new AsyncCallback<ArrayList<InstantPost>>() {
                        @Override
                        public void onResult(ArrayList<InstantPost> result) {
                            searchPost.addAll(result);
                            result_adapter.setItems((ArrayList<InstantPost>) searchPost);
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
                    searchExecutor = (AsyncExecutor) new AsyncExecutor<ArrayList<InstantPost>>(){
                        @Override
                        protected void onProgressUpdate(Void... values) {
                            super.onProgressUpdate(values);
                            progressBar.setVisibility(View.VISIBLE);
                        }
                    }.setCallable(callable1).setCallback(callback1).execute();
                    Toast.makeText(getApplicationContext(), "최하단", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //Drawer
        ExpandableListView myList = (ExpandableListView)findViewById(R.id.drawer_expandableListView);

        //create Data
        myList.setAdapter(new BookExpandableAdapter(this, selectCategory, searchNode));

    }

    //도감 검색결과 요청.
    private ArrayList<InstantPost> requestResultPost(CategoryNodeDto node) {
        List<InstantPost> respondPost = searchService.requestData(node.getParent(), node);
        ArrayList<InstantPost> foundPost = (ArrayList<InstantPost>) respondPost;

        return foundPost;
    }

    //저장 게시물 요청.
    private ArrayList<PostDto> requestSavedPost(CategoryNodeDto node) {
        ArrayList<PostDto> foundPost = new ArrayList<>();
        switch (selectCategory.getStatus()) {
            case Sharing:
            case NonShare:
            case Sharing_Immutable:
            case Sharing_Mutable:
                foundPost = (ArrayList<PostDto>) postService.findPostByCategoryNodeId(node.getId());
                break;
            case Shared_Immutable:
            case Shared_Mutable:
                foundPost = (ArrayList<PostDto>) node.getPosts();
                break;
        }
        return foundPost;
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

    @Override
    protected void onDestroy() {
        savePost.clear();
        searchPost.clear();
        searchService.initCache();
        saveExecutor.cancel(true);
        searchExecutor.cancel(true);
        super.onDestroy();
    }
}
