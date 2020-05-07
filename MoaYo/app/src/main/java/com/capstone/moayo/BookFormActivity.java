package com.capstone.moayo;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class BookFormActivity extends AppCompatActivity {
//    FragmentManager fm;
//    FragmentTransaction tran;
//    FormFragment firstFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_form);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("도감 생성");

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

//        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left );
        transaction.replace(R.id.form_frame, new FormMainFragment()).commit();
    }

    //프래그먼트와 프래그먼트끼리 직접접근을하지않는다. 프래그먼트와 엑티비티가 접근함
    public void onFragmentChange(int index) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        fragmentTransaction.addToBackStack(null);

        switch (index) {
            case 0:
                fragmentTransaction.replace(R.id.form_frame, new FormMainFragment()).commit();
                break;
            case 1:
                fragmentTransaction.replace(R.id.form_frame, new FormEditFragment()).commit();
                break;
            default:
                fragmentTransaction.replace(R.id.form_frame, new FormMainFragment()).commit();
                break;
        }
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
