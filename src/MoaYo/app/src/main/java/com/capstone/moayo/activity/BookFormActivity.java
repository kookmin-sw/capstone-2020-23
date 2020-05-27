package com.capstone.moayo.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.capstone.moayo.BaseActivity;
import com.capstone.moayo.R;
import com.capstone.moayo.fragment.FormEditFragment;
import com.capstone.moayo.fragment.FormMainFragment;
import com.capstone.moayo.service.CategoryService;

import com.capstone.moayo.service.concrete.ServiceFactoryCreator;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.service.dto.CategoryNodeDto;
import com.capstone.moayo.util.Async.AsyncCallback;
import com.capstone.moayo.util.Async.AsyncExecutor;

import java.util.ArrayList;
import java.util.concurrent.Callable;


public class BookFormActivity extends BaseActivity implements FormEditFragment.OnChangeLevelListener, View.OnClickListener{
    private FragmentManager fm;
    private FragmentTransaction tran;
    private CategoryDto category;
    private CategoryNodeDto rootNode;
    private CategoryNodeDto currentNode;
    private CategoryService categoryService;
    private TextView level1_title_tv, level2_title_tv, level3_title_tv, arrow_tv_1, arrow_tv_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_form);

        Toolbar toolbar = (Toolbar)findViewById(R.id.form_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_clear);

        arrow_tv_1 = (TextView) findViewById(R.id.arrow1);
        arrow_tv_2 = (TextView) findViewById(R.id.arrow2);
        level1_title_tv = (TextView) findViewById(R.id.form_tv_title_1);
        level2_title_tv = (TextView) findViewById(R.id.form_tv_title_2);
        level3_title_tv = (TextView) findViewById(R.id.form_tv_title_3);

        level1_title_tv.setOnClickListener(this);
        level2_title_tv.setOnClickListener(this);

        categoryService = ServiceFactoryCreator.getInstance().requestCategoryService(getApplicationContext());
        onChangeLevel(1, null);
    }


    public void setToolbarContent(CategoryNodeDto currentNode, int level) {
        if(currentNode != null) this.currentNode = currentNode;

        arrow_tv_1.setText("");
        arrow_tv_2.setText("");
        level1_title_tv.setText("");
        level2_title_tv.setText("");
        level3_title_tv.setText("");

        switch (level) {
            case 0:
                level1_title_tv.setText("도감 생성");
                break;
            case 1:
                level1_title_tv.setText(currentNode.getTitle());
                arrow_tv_1.setText(" > ");
                level2_title_tv.setText("...");

                break;
            case 2:
                level1_title_tv.setText(currentNode.getParent().getTitle());
                arrow_tv_1.setText(" > ");
                level2_title_tv.setText(currentNode.getTitle());
                arrow_tv_2.setText(" > ");
                level3_title_tv.setText("...");

                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.form_tv_title_1:
                onChangeLevel(1, null);
                break;

            case R.id.form_tv_title_2:
                onChangeLevel(2, currentNode.getParent());
                break;

        }
    }



    @Override
    public void onChangeLevel(int fraglvl, CategoryNodeDto selectedNode) {

        Fragment temp = null;

        fm = getSupportFragmentManager();
        tran = fm.beginTransaction();
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
            case 1:
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
//        tran.addToBackStack(null);
        tran.commit();
    }

    public void initRootNode(String title) {
        if(rootNode == null) {
            rootNode = new CategoryNodeDto(title, null, 1);
        } else {
            rootNode.setTitle(title);
        }
    }

    public CategoryNodeDto getRootNode() {
        return rootNode;
    }

    public CategoryNodeDto addNode(CategoryNodeDto node) {
        currentNode.getLowLayer().add(node);
        return currentNode;
    }

    public CategoryNodeDto removeNode(CategoryNodeDto node) {
        ArrayList<CategoryNodeDto> lowLayerList = (ArrayList) currentNode.getLowLayer();
        for(CategoryNodeDto target : lowLayerList) {
            if(target == node) {
                lowLayerList.remove(target);
                break;
            }
        }
        currentNode.setLowLayer(lowLayerList);

        return currentNode;
    }

    public void onSubmit() {
        //--------Backend 통신----------
        //사용자로부터 작성된 도감의 루트노드를 생성한 Category 객체에 등록.
        category = new CategoryDto(rootNode.getTitle(), null, null,"https://image.flaticon.com/icons/png/512/130/130304.png",  rootNode);
        Log.d("category", category.toString());
        Callable<String> callable = () -> categoryService.createCategory(category);
        AsyncCallback<String> callback = new AsyncCallback<String>() {
            @Override
            public void onResult(String result) {
                Log.d("create", result);
                Toast.makeText(getApplicationContext(), "도감 '"+category.getTitle() + "'이 정상적으로 저장되었습니다.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void exceptionOccured(Exception e) {
            }
            @Override
            public void cancelled() {
            }
        };

        new AsyncExecutor<String>().setCallable(callable).setCallback(callback).execute();
//        Log.d("rootNode", category.getRootNode().toString());

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_bookform, menu);
        return true;
    }

    //toolbar button event handling
    public boolean onOptionsItemSelected(MenuItem item) {
        //menu_bookform.xml에서 지정한 item 이벤트 추가
        switch (item.getItemId()) {

            case android.R.id.home: {
                new AlertDialog.Builder(this)
                        .setTitle("도감 생성")
                        .setMessage("\n도감 생성을 취소하시겠습니까?")
//                        .setIcon(android.R.drawable.ic_menu_save)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // 확인시 처리 로직
                                Intent intent = new Intent(BookFormActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }})
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // 취소시 처리 로직
//                                finish();
                            }})
                        .show();
                return true;
            }

            case R.id.bookSave:
                //TODO : 도감생성 확인 로직.
//                onSubmit();
                if(rootNode != null ){
                    new AlertDialog.Builder(this)
                            .setTitle("도감 저장")
                            .setMessage("\n도감을 저장할까요?")
//                            .setIcon(R.drawable.ic_save)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    onSubmit();
                                    Intent intent = new Intent(BookFormActivity.this, BookManageActivity.class);
                                    startActivity(intent);

                                }})
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                }})
                            .show();
                    return true;
                } else {
                    Toast.makeText(getApplicationContext(), "아직 도감이 작성되지 않았습니다.", Toast.LENGTH_SHORT).show();
                    return true;
                }

        }
        return super.onOptionsItemSelected(item);
    }


}