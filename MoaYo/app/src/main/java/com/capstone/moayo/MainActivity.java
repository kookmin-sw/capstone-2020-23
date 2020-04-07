package com.capstone.moayo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mybook_btn = (Button)findViewById(R.id.myBookButton);
        mybook_btn.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(MainActivity.this, BookManageActivity.class);
                        startActivity(intent);

                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left );
                    }
                }
        );


    }
}
