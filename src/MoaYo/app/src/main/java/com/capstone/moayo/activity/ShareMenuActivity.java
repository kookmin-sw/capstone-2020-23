package com.capstone.moayo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.moayo.R;
import com.capstone.moayo.adapter.ShareMenuAdapter;
import com.capstone.moayo.service.ShareService;
import com.capstone.moayo.service.concrete.ServiceFactoryCreator;
import com.capstone.moayo.util.Async.AsyncCallback;
import java.util.List;
import java.util.concurrent.Callable;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.util.Async.AsyncExecutor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class ShareMenuActivity extends AppCompatActivity implements View.OnClickListener {

    private ShareService shareService;
    private ShareMenuAdapter adapter;
    private FloatingActionButton sort, add, my, open;
    private AlertDialog dialog;

    boolean isMenuOpen = false;
    boolean sortfragment = false; // false = current, true = like

    private List<CategoryDto> categoryDtoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_menu);

        shareService = ServiceFactoryCreator.getInstance().requestShareService(getApplicationContext());

        //리소스 파일에서 추가한 툴바를 앱바로 지정하기
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);


        RecyclerView recyclerView = findViewById(R.id.recycler_shareMenu);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

        adapter = new ShareMenuAdapter();
        recyclerView.setAdapter(adapter);

        //floating action menu 선언
        sort = (FloatingActionButton) findViewById(R.id.fabSub1);
        add = (FloatingActionButton) findViewById(R.id. fabSub2);
        my = (FloatingActionButton) findViewById(R.id.fabSub3);
        open = (FloatingActionButton) findViewById(R.id.fabMain);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if(dy > 0){
                    sort.hide();
                    add.hide();
                    my.hide();
                    open.hide();
                }
                else if(dy < 0) {
                    sort.show();
                    add.show();
                    my.show();
                    open.show();
                }
            }
        });

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuOpen();
            }
        });

        //아이템 로드
//        adapter.setItems(new SharedData_Sample().getItems());
        Callable<List<CategoryDto>> loadCallable = () -> shareService.findAll();
        AsyncCallback<List<CategoryDto>> loadCallback = new AsyncCallback<List<CategoryDto>>() {
            @Override
            public void onResult(List<CategoryDto> result) {
                adapter.setItems((ArrayList<CategoryDto>) result);
                adapter.notifyDataSetChanged();
                categoryDtoList = result;
            }

            @Override
            public void exceptionOccured(Exception e) {
            }

            @Override
            public void cancelled() {

            }
        };

        new AsyncExecutor<List<CategoryDto>>() {
            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }
        }.setCallable(loadCallable).setCallback(loadCallback).execute();
    }

    private void menuOpen(){
        if(!isMenuOpen) {

            open.animate().rotation(45);
            my.animate().translationY(-getResources().getDimension(R.dimen.my));
            add.animate().translationY(-getResources().getDimension(R.dimen.add));
            sort.animate().translationY(-getResources().getDimension(R.dimen.sort));

            my.setOnClickListener(this);
            add.setOnClickListener(this);
            sort.setOnClickListener(this);

            isMenuOpen = true;
        }
        else{
            open.animate().rotation(0);
            my.animate().translationY(0);
            add.animate().translationY(0);
            sort.animate().translationY(0);

            isMenuOpen = false;
        }
    }

    //mainToolBar에 menu.xml을 인플레이트함
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_sharemenu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        //menu_bookmanage.xml에서 지정한 item 이벤트 추가
        switch (item.getItemId()) {

            default: {
                onBackPressed();
                return true;
            }


            case R.id.menu_share_search:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                final View customLayout = getLayoutInflater().inflate(R.layout.dialog_book_search, null);
                builder.setView(customLayout);

                EditText search_keyword_et = customLayout.findViewById(R.id.dialog_search_et);
                Spinner search_type_sp = customLayout.findViewById(R.id.dialog_search_sp);
                Button search_bt = customLayout.findViewById(R.id.searchBt_dialog);

                search_bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String type = search_type_sp.getSelectedItem().toString();
                        String keyword = search_keyword_et.getText().toString();

                        if(!keyword.isEmpty()) {
                            int search_type_num = 1;
                            switch (type){
                                case "태그":
                                    search_type_num = 1;
                                case "닉네임":
                                    search_type_num = 2;
                            }

                            //TODO : 도감 검색 백엔드 통신.
//                                adapter.setItems((ArrayList<CategoryDto>) result);
//                                adapter.notifyDataSetChanged();
                            Toast.makeText(getApplicationContext(), type+ " - " + keyword ,Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(getApplicationContext(), "검색어를 입력해주세요",Toast.LENGTH_LONG).show();
                        }
                    }
                });


                dialog = builder.create();

                WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
                params.y = -30;
                dialog.getWindow().setAttributes(params);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();

                return true;
        }
    }

    @Override
    public void onClick(View v){
        int id = v.getId();

        switch (id) {
            case R.id.fabSub1:
                shareService = ServiceFactoryCreator.getInstance().requestShareService(getApplicationContext());
                //List<CategoryDto> categoryDtoList = shareService.findAll();
                if(!sortfragment){
                    adapter.setItems((ArrayList<CategoryDto>)shareService.sortByLike(categoryDtoList));
                    adapter.notifyDataSetChanged();
                    sortfragment = true;
                }else{
                    adapter.setItems((ArrayList<CategoryDto>)shareService.sortByTime(categoryDtoList));
                    adapter.notifyDataSetChanged();
                    sortfragment = false;
                }
                break;
            case R.id.fabSub2:
                Intent intent = new Intent(ShareMenuActivity.this, NewShareActivity.class);
                ShareMenuActivity.this.startActivity(intent);
                break;
            case R.id.fabSub3:
                Intent intent1 = new Intent(ShareMenuActivity.this, BookManageActivity.class);
                ShareMenuActivity.this.startActivity(intent1);
        }
    }
}
