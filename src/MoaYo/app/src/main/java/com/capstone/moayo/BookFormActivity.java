package com.capstone.moayo;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class BookFormActivity extends AppCompatActivity{
//    FragmentManager fm;
//    FragmentTransaction tran;
//    FormFragment firstFrag;
    Button start_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_form);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("도감 생성");

        start_btn = (Button) findViewById(R.id.start_form_btn);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.form_frame, new FormFragment());
        transaction.commit();

    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.start_form_btn:
////                setFrag(0);
//                break;
//        }
//    }
//    public void setFrag(int n) {
//        fm = getSupportFragmentManager();
//        tran = fm.beginTransaction();
//
//        switch (n) {
//            case 0:
//                tran.replace(R.id.form_frame, firstFrag);
//                tran.commit();
//                break;
//        }
//    }
}
