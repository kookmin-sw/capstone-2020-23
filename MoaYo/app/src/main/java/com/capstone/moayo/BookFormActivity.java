package com.capstone.moayo;

import android.os.Bundle;
import android.widget.Toast;

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
    private CategoryNode rootNode;
    private CategoryNode currentNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_form);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("도감 생성");

        onChangeLevel(0, null);
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
    public void onChangeLevel(int fraglvl, CategoryNode selectedNode) {

        Fragment temp = null;

        fm = getSupportFragmentManager();
        tran = fm.beginTransaction();
        tran.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);

        if(selectedNode != null ) {
//            this.category.setSelectCategoryNode(selectedNode);
            currentNode = selectedNode;
            Toast.makeText(getApplicationContext(), "this is " + currentNode.getTitle(), Toast.LENGTH_SHORT).show();
        } else {
            currentNode = rootNode;
        }


        Bundle bundle = new Bundle();
        bundle.putSerializable("currentNode", currentNode);


        switch (fraglvl) {
            case 0:
                if ((temp = fm.findFragmentByTag("main")) == null)
                    temp = new FormMainFragment();
                temp.setArguments(bundle);
                tran.replace(R.id.form_frame, temp, "main");
                break;
            case 2:
                if ((temp = fm.findFragmentByTag("level2")) == null)
                    temp = new FormEditFragment();
                temp.setArguments(bundle);
                tran.replace(R.id.form_frame, temp, "level2");
                break;
            case 3:
                if ((temp = fm.findFragmentByTag("level3")) == null)
                    temp = new FormEditFragment();
                temp.setArguments(bundle);
                tran.replace(R.id.form_frame, temp, "level3");
                break;
            default:
                break;
        }
        tran.addToBackStack(null);
        tran.commit();
    }

    public void initCategory(String title) {
        if(rootNode == null) {
            rootNode = new CategoryNode(title, null, 1);
//            category = new Category(title, null, null, null);
//            category.setRootNode(rootNode);
//            createDummy(category);
        } else {
            rootNode.setTitle(title);
        }
    }

    public CategoryNode addNode(CategoryNode newNode) {
        currentNode.addLowLayer(newNode);
        return currentNode;
    }

//    public void createDummy(Category category) {
//        CategoryNode lvlOne = nodeFactory("가수", null, 1);
//        CategoryNode lvlTwo1 = nodeFactory("트로트", lvlOne, 2);
//        CategoryNode lvlTwo2 = nodeFactory("발라드", lvlOne, 2);
//        CategoryNode lvlTwo3 = nodeFactory("힙합", lvlOne, 2);
//
//        lvlOne.addLowLayer(lvlTwo1);
//        lvlOne.addLowLayer(lvlTwo2);
//        lvlOne.addLowLayer(lvlTwo3);
//
//        category.setRootNode(lvlOne);
//        category.setSelectCategoryNode(lvlOne);
//    }

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