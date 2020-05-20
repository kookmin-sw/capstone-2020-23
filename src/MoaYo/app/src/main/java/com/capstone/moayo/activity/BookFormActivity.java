package com.capstone.moayo.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.capstone.moayo.R;
import com.capstone.moayo.entity.Category;
import com.capstone.moayo.entity.CategoryNode;
import com.capstone.moayo.fragment.FormEditFragment;
import com.capstone.moayo.fragment.FormMainFragment;

import java.util.ArrayList;

public class BookFormActivity extends AppCompatActivity implements FormEditFragment.OnChangeLevelListener {
    private FragmentManager fm;
    private FragmentTransaction tran;
    private Category category;
    private CategoryNode rootNode;
    private CategoryNode currentNode;
    private TextView toolbar_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_form);

        Toolbar toolbar = (Toolbar)findViewById(R.id.form_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        toolbar_title = (TextView) findViewById(R.id.form_tv_title);
        toolbar_title.setText("도감 생성");


        onChangeLevel(0, null);

    }

    @Override
    public void onChangeLevel(int fraglvl, CategoryNode selectedNode) {

        Fragment temp = null;

        fm = getSupportFragmentManager();
        tran = fm.beginTransaction();
//        tran.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        tran.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
                R.anim.slide_in_left, R.anim.slide_out_right);
        if(selectedNode != null ) {
            currentNode = selectedNode;
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

    public CategoryNode addNode(CategoryNode node) {
        currentNode.addLowLayer(node);
        return currentNode;
    }

    public CategoryNode removeNode(CategoryNode node) {
        ArrayList<CategoryNode> lowLayerList = (ArrayList) currentNode.getLowLayer();
        for(CategoryNode target : lowLayerList) {
            if(target == node) {
                lowLayerList.remove(target);
                break;
            }
        }
        currentNode.setLowLayer(lowLayerList);

        return currentNode;
    }

    public void onSubmit() {
        //사용자로부터 작성된 도감의 루트노드를 생성한 Category 객체에 등록.
        category = new Category(rootNode.getTitle(), null, null, rootNode);
        Log.d("category", category.toString());
        Toast.makeText(getApplicationContext(), "도감 '"+category.getTitle() + "'이 정상적으로 저장되었습니다.", Toast.LENGTH_SHORT).show();
//        Log.d("rootNode", category.getRootNode().toString());

        //--------Backend 통신----------
        //service - CategoryService - create()
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
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