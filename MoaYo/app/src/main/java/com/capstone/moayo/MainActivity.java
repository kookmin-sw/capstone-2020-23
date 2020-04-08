package com.capstone.moayo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ToolbarWidgetWrapper;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        //리소스 파일에서 추가한 툴바를 앱바로 지정하기
//        Toolbar mainToolBar = (Toolbar) findViewById(R.id.mainToolBar);
//        setSupportActionBar(mainToolBar);
//
//        getSupportActionBar().setTitle(""); //앱바에서 제목을 없애고 activity_main.xml에서 설정한 제목이 뜨게 설정
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);


        Button temp = (Button) findViewById(R.id.tempButton);

        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

    }

    //mainToolBar에 menu.xml을 인플레이트함

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {


        //menu.xml에서 지정한 item 이벤트 추가
        switch (item.getItemId()) {


            default:
            {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.mainDisplay);
                drawer.openDrawer(Gravity.LEFT);

                return true;
            }

            case R.id.moveMY:
                // User chose the "Settings" item, show the app settings UI
                Toast.makeText(getApplicationContext(), "나의 도감으로 이동함", Toast.LENGTH_LONG).show();
                return true;

//            default:
//                Toast.makeText(getApplicationContext(),"메뉴 Tab 펼쳐짐", Toast.LENGTH_LONG).show();
//                return true;

        }

    }
}

