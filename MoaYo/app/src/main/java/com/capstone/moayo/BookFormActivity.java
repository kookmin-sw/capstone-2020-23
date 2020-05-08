package com.capstone.moayo;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class BookFormActivity extends AppCompatActivity implements FormEditFragment.OnChangeFormListener {
    private FragmentManager fm;
    private FragmentTransaction tran;
    private String dogamTitle = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_form);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("도감 생성");

        onChangeForm(0);
    }

    //프래그먼트와 프래그먼트끼리 직접접근을하지않는다. 프래그먼트와 엑티비티가 접근함
//    public void onFragmentChange(int index) {
//
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
//        fragmentTransaction.addToBackStack(null);
//
//        switch (index) {
//            case 0:
//                fragmentTransaction.replace(R.id.form_frame, new FormMainFragment()).commit();
//                break;
//            case 1:
//                fragmentTransaction.replace(R.id.form_frame, new FormEditFragment()).commit();
//                break;
//            default:
//                fragmentTransaction.replace(R.id.form_frame, new FormMainFragment()).commit();
//                break;
//        }
//    }

    @Override
    public void onChangeForm(int frag_id) {

        Fragment temp = null;

        fm = getSupportFragmentManager();
        tran = fm.beginTransaction();
        tran.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);

        Bundle bundle = new Bundle();
        bundle.putString("title", this.dogamTitle);

        switch (frag_id) {
            case 0:
                if ((temp = fm.findFragmentByTag("main")) == null)
                    temp = new FormMainFragment();
                tran.replace(R.id.form_frame, temp, "main");
                break;
            case 1:
                if ((temp = fm.findFragmentByTag("level1")) == null)
                    temp = new FormEditFragment();
                temp.setArguments(bundle);
                tran.replace(R.id.form_frame, temp, "level1");
                break;
            case 2:
                if ((temp = fm.findFragmentByTag("level2")) == null)
                    temp = new FormEditFragment();
                temp.setArguments(bundle);
                tran.replace(R.id.form_frame, temp, "level2");
                break;
            default:
                break;
        }
        tran.addToBackStack(null);
        tran.commit();
    }

    public void setTitle(String title) {
        this.dogamTitle = title;
    }


    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
        }
    }

}