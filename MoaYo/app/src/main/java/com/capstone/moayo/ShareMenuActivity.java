package com.capstone.moayo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.capstone.moayo.Adapter.ShareMenuAdapter;
import com.capstone.moayo.data.SharedData_Sample;

public class ShareMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_menu);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("공유 게시판");


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


    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_sharemenu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        //menu.xml에서 지정한 item 이벤트 추가
        switch (item.getItemId()) {

            case R.id.newShare:
            {
                //사용자 도감 탭 펼치기 전, 탭 화면 객체 참조
                Toast.makeText(getApplicationContext(), "NewShareActivity로 이동함", Toast.LENGTH_LONG).show();
                return true;
            }
            default:
                onBackPressed();
                return true;
        }

    }
}
