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


        CategoryNodeDto root_node = new CategoryNodeDto("test", null, 1);

        //Dummy data
        ArrayList<CategoryDto> dummy_data = new ArrayList<>();
        CategoryDto dummy1 = new CategoryDto("라라", "취향별 해외 여행지 도감 공유.", "00", null);
        dummy1.setUrl("https://ppss.kr/wp-content/uploads/2019/07/02-66-540x375.jpg");
        dummy1.setRootNode(root_node);
        dummy1.setStatus(DogamStatus.Sharing);
        CategoryDto dummy2 = new CategoryDto("집콕취미", "집에서 하기 좋을 것 같은 취미생활 도감 공유", "00", null);
        dummy2.setUrl("https://t1.daumcdn.net/tvpot/thumb/v57dfVRLYV0RD9TFF0tYMo0/thumb.png");
        dummy2.setRootNode(root_node);
        dummy2.setStatus(DogamStatus.Sharing);
        CategoryDto dummy3 = new CategoryDto("패션", "패션 테러리스트, 바로 너를 위한 패션 올인원 도감 공유한다.", "00", null);
        dummy3.setUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQE3V8opSeAE3KxavWe2wioK2aZknhF3AkGhTFawiQB1LpqPDrq&usqp=CAU");
        dummy3.setRootNode(root_node);
        dummy3.setStatus(DogamStatus.Sharing);

        dummy_data.add(dummy1);
        dummy_data.add(dummy2);
        dummy_data.add(dummy3);
        dummy_data.add(dummy1);
        dummy_data.add(dummy2);
        dummy_data.add(dummy3);
        //TODO: [백엔드 통신] 공유 도감데이터 가져와 Adapter에 등록.
        adapter.setItems(dummy_data);

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
