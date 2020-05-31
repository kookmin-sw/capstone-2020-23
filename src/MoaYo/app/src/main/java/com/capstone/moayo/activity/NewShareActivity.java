package com.capstone.moayo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import com.capstone.moayo.BaseActivity;
import com.capstone.moayo.R;
import com.capstone.moayo.service.CategoryService;
import com.capstone.moayo.service.concrete.ServiceFactoryCreator;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.util.Async.AsyncCallback;
import com.capstone.moayo.util.Async.AsyncExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class NewShareActivity extends BaseActivity {

    CategoryService categoryService;
    Spinner spinner;
    ArrayAdapter<String> spinner_adapter;
    ArrayList<String> spinner_list;
    Button submit_btn;
    EditText nickname, password, content;
    List<CategoryDto> categories;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_share);
        categoryService = ServiceFactoryCreator.getInstance().requestCategoryService(getApplicationContext());

        spinner_list = new ArrayList<>();

        nickname = (EditText) findViewById(R.id.activity_share_et_nickname);
        password = (EditText) findViewById(R.id.activity_share_et_password);
        content = (EditText) findViewById(R.id.activity_share_et_content);
        spinner = (Spinner) findViewById(R.id.activity_share_sp_target);

        //spinner에 들어갈 나의 도감 리스트 가져옴
        Callable<List<CategoryDto>> callable = () -> categoryService.findAll();
        AsyncCallback<List<CategoryDto>> callback = new AsyncCallback<List<CategoryDto>>() {
            @Override
            public void onResult(List<CategoryDto> result) {
                categories = result;
                for(CategoryDto elem:result){
                    spinner_list.add(elem.getTitle());
                }
                spinner_adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, spinner_list);
                spinner.setAdapter(spinner_adapter);
            }

            @Override
            public void exceptionOccured(Exception e) {
                e.printStackTrace();
            }

            @Override
            public void cancelled() {
            }
        };
        new AsyncExecutor<List<CategoryDto>>(){
            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }
        }.setCallable(callable).setCallback(callback).execute();

        


        submit_btn = (Button) findViewById(R.id.activity_share_btn_submit);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: 도감 공유 백엔드 통신 and 공유화면 전환.
//                Log.d("Submit Data", nickname.getText().toString() + password.getText().toString() + content.getText().toString());
                CategoryDto share_node = categories.get((int) spinner.getSelectedItemId());
                Log.d("seleted_node", share_node.toString());

                Intent intent = new Intent(NewShareActivity.this, ShareMenuActivity.class);
                startActivity(intent);

            }
        });

    }

    // Inflate menu.xml in toolBar.
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }


    //menu button onclick
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            default: {
                onBackPressed();
                return true;
            }
        }
    }
}
