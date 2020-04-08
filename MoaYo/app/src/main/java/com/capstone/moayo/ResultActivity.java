package com.capstone.moayo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);

//        //사용자 도감 탭 펼치기 전, 탭 화면 객체 참조
//        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.resultDisplay);
//
//        //사용자 도감 탭 화면 객체 참조
//        final View drawerView = (View) findViewById(R.id.categoryList);
//
//        //도감 리스트 화면을 열고 닫을 버튼 객체 참조
//        Button btn_categoryOpen = (Button) findViewById(R.id.categoryOpen);
//        Button btn_categoryClose = (Button) findViewById(R.id.categoryClose);
//
//        //도감 리스트 화면 여는 버튼리스너
//        btn_categoryOpen.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                drawerLayout.openDrawer(drawerView);
//            }
//        });
//
//        //도감 리스트 화면 닫는 버튼리스너
//        btn_categoryClose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawerLayout.closeDrawer(drawerView);
//            }
//        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_result, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {


        //menu.xml에서 지정한 item 이벤트 추가
        switch (item.getItemId()) {


            case R.id.myCate:
            {
                //사용자 도감 탭 펼치기 전, 탭 화면 객체 참조
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.resultDisplay);
                drawer.openDrawer(Gravity.RIGHT);

                return true;
            }
            default:
                onBackPressed();
                return true;
//            default:
//                Toast.makeText(getApplicationContext(),"메뉴 Tab 펼쳐짐", Toast.LENGTH_LONG).show();
//                return true;

        }

    }
}
