package com.capstone.moayo.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.capstone.moayo.R;
import com.capstone.moayo.adapter.ShareMenuAdapter;
import com.capstone.moayo.data.SharedData_Sample;

public class ShareMenuActivity extends AppCompatActivity {

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


        RecyclerView recyclerView2 = findViewById(R.id.recycler_shareMenu);
        recyclerView2.setLayoutManager(new GridLayoutManager(this,1));


        ShareMenuAdapter adapter2 = new ShareMenuAdapter();
        recyclerView2.setAdapter(adapter2);

        //아이템 로드
        adapter2.setItems(new SharedData_Sample().getItems());


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


    public boolean onOptionsItemSelected(MenuItem item) {

        //menu_bookmanage.xml에서 지정한 item 이벤트 추가
        switch (item.getItemId()) {

            default: {
                onBackPressed();
                return true;
            }

            case R.id.createBook:

                Toast.makeText(getApplicationContext(), "NewShareActivity로 이동함", Toast.LENGTH_LONG).show();

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                return true;


        }
    }
}
