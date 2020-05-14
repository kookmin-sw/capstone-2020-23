package com.capstone.moayo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    private Button completeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_form);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("도감 생성");

        completeBtn = (Button) findViewById(R.id.complete_btn);
        completeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmit();
            }
        });

        onChangeLevel(0, null);

    }

    @Override
    public void onChangeLevel(int fraglvl, CategoryNode selectedNode) {

        Fragment temp = null;

        fm = getSupportFragmentManager();
        tran = fm.beginTransaction();
        tran.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);

        if(selectedNode != null ) {
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

    public void initRootNode(String title) {
        if(rootNode == null) {
            rootNode = new CategoryNode(title, null, 1);
        } else {
            rootNode.setTitle(title);
        }
    }

    public CategoryNode addNode(CategoryNode newNode) {
        currentNode.addLowLayer(newNode);
        return currentNode;
    }

    public void onSubmit() {
        //사용자로부터 작성된 도감의 루트노드를 생성한 Category 객체에 등록.
        category = new Category(rootNode.getTitle(), null, null, rootNode);
//        String info = category.toString();
        Log.d("category", category.toString());
        Log.d("rootNode", category.getRootNode().toString());

        //--------Backend 통신----------
    }


//    public CategoryNode nodeFactory(String title, CategoryNode parentNode, int level) {
//        CategoryNode node = new CategoryNode(title, parentNode, level);
//        return node;
//    }


    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
        }
    }

}