package com.capstone.moayo;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.capstone.moayo.entity.Category;
import com.capstone.moayo.entity.CategoryNode;

public class BookFormActivity extends AppCompatActivity implements FormEditFragment.OnChangeLevelListener {
    private FragmentManager fm;
    private FragmentTransaction tran;
    private Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_form);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("도감 생성");

        onChangeLevel(0);
    }

//    public void onFragmentChange(int index) {
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
    public void onChangeLevel(int frag_id) {

        Fragment temp = null;

        fm = getSupportFragmentManager();
        tran = fm.beginTransaction();
        tran.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);

        Bundle bundle = new Bundle();

//        bundle.putString("title", this.bookTitle);

        switch (frag_id) {
            case 0:
                if ((temp = fm.findFragmentByTag("main")) == null) {
                    temp = new FormMainFragment();
                }
                temp.setArguments(bundle);
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

    public void categoryFactory(String title) {
//        this.bookTitle = title;
        if(category == null) {
            category = new Category(title, null, null, null);
        } else {
            category.setTitle(title);
        }
    }

    public CategoryNode nodeFactory(String title, CategoryNode parentNode, int level) {
        CategoryNode node = new CategoryNode(title, parentNode, level);
        return node;
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