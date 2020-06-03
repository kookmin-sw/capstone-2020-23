package com.capstone.moayo.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.capstone.moayo.BaseActivity;
import com.capstone.moayo.R;
import com.capstone.moayo.adapter.ShareMenuAdapter;
import com.capstone.moayo.data.SharedData_Sample;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.service.dto.CategoryNodeDto;
import com.capstone.moayo.util.DogamStatus;

import java.util.ArrayList;

public class ShareMenuActivity extends BaseActivity implements View.OnClickListener  {

    ImageButton search_btn;
    EditText search_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_menu);

        //리소스 파일에서 추가한 툴바를 앱바로 지정하기
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);


        search_text = (EditText) findViewById(R.id.activity_share_et_search);
        search_btn = (ImageButton) findViewById(R.id.activity_share_btn_search);
        search_btn.setOnClickListener(this);

        RecyclerView recyclerView = findViewById(R.id.recycler_shareMenu);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));


        ShareMenuAdapter adapter = new ShareMenuAdapter();
        recyclerView.setAdapter(adapter);





        //TODO: [백엔드 통신] 공유 도감데이터 가져와 Adapter에 등록.
        adapter.setItems(new SharedData_Sample().getItems()); //Dummy data

        Spinner ShareTypeSpinner = (Spinner)findViewById(R.id.shareMenuSpinner);
        ArrayAdapter shareTypeAdapter = ArrayAdapter.createFromResource(this,
                R.array.share_menu_spinner, android.R.layout.simple_spinner_dropdown_item);
        shareTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ShareTypeSpinner.setAdapter(shareTypeAdapter);
    }

    //mainToolBar에 menu.xml을 인플레이트함
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_bookmanage, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_share_btn_search:
                String input = search_text.getText().toString();
                Log.d("search_data", input);
        }
    }




            public boolean onOptionsItemSelected(MenuItem item) {

        //menu_bookmanage.xml에서 지정한 item 이벤트 추가
        switch (item.getItemId()) {

            default: {
                onBackPressed();
                return true;
            }

            case R.id.createBook:
//                Toast.makeText(getApplicationContext(), "NewShareActivity로 이동함", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ShareMenuActivity.this, NewShareActivity.class);
                startActivity(intent);

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                return true;


        }
    }
}
