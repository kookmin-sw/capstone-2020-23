package com.capstone.moayo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.capstone.moayo.R;
import com.capstone.moayo.service.CategoryService;
import com.capstone.moayo.service.ShareService;
import com.capstone.moayo.service.concrete.ServiceFactoryCreator;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.util.Async.AsyncCallback;
import com.capstone.moayo.util.Async.AsyncExecutor;
import com.capstone.moayo.util.DogamStatus;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class NewShareActivity extends AppCompatActivity {

    CategoryService categoryService;
    ShareService shareService;
    Spinner spinner;
    ArrayAdapter<String> spinner_adapter;
    ArrayList<String> spinner_list;
    Button submit_btn;
    EditText nickname, password, content;
    List<CategoryDto> categories;
    RadioButton mutableBtn, immutableBtn;
    RadioGroup radioGroup;
    Boolean isMutable;
    TextInputLayout tl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_share);
        categoryService = ServiceFactoryCreator.getInstance().requestCategoryService(getApplicationContext());
        shareService = ServiceFactoryCreator.getInstance().requestShareService(getApplicationContext());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        spinner_list = new ArrayList<>();
        categories = new ArrayList<>();

        nickname = (EditText) findViewById(R.id.activity_share_et_nickname);
        password = (EditText) findViewById(R.id.activity_share_et_password);
        content = (EditText) findViewById(R.id.activity_share_et_content);
        tl = (TextInputLayout)findViewById(R.id.content_layout);
        spinner = (Spinner) findViewById(R.id.activity_share_sp_target);

        tl.setCounterEnabled(true);

        mutableBtn = (RadioButton) findViewById(R.id.activity_share_rb_mutable);
        immutableBtn = (RadioButton) findViewById(R.id.activity_share_rb_immutable);
        //수정불가 모드로 초기화.
        immutableBtn.setChecked(true);
        isMutable = false;

        radioGroup = (RadioGroup) findViewById(R.id.activity_share_rg_type);
        radioGroup.setOnCheckedChangeListener(radioGroupButtonChangeListener);

        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        //spinner에 들어갈 나의 도감 리스트 가져옴
        Callable<List<CategoryDto>> callable = () -> categoryService.findAll();
        AsyncCallback<List<CategoryDto>> callback = new AsyncCallback<List<CategoryDto>>() {
            @Override
            public void onResult(List<CategoryDto> result) {
                for(CategoryDto categoryDto : result) {
                    if(categoryDto.getStatus() == DogamStatus.NonShare) categories.add(categoryDto);
                }
                for (CategoryDto elem : categories) {
                    spinner_list.add(elem.getTitle());
                }
                if(spinner_list.isEmpty()) {
                    spinner_list.add("공유할 도감이 없습니다.");
                    submit_btn.setEnabled(false);
                }
                spinner_adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, spinner_list);
                spinner.setAdapter(spinner_adapter);

                if (getIntent().getSerializableExtra("target_category") != null) {
                    CategoryDto target = (CategoryDto) getIntent().getSerializableExtra("target_category");
                    spinner.setSelection(spinner_list.indexOf(target.getTitle()));
                    spinner.setEnabled(false);
//                    Log.d("getSerializable", target.toString());
                }
            }

            @Override
            public void exceptionOccured(Exception e) {
                e.printStackTrace();
            }

            @Override
            public void cancelled() {
            }
        };
        new AsyncExecutor<List<CategoryDto>>() {
            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }
        }.setCallable(callable).setCallback(callback).execute();

        submit_btn = (Button) findViewById(R.id.activity_share_btn_submit);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategoryDto share_category = categories.get((int) spinner.getSelectedItemId());
//                Log.d("Submit Data", nickname.getText().toString() + password.getText().toString() + content.getText().toString());
//                Log.d("seleted_node", share_category.toString());

                share_category.setWriter(nickname.getText().toString());
                share_category.setPassword(password.getText().toString());
                share_category.setDescription(content.getText().toString());

                //TODO: 도감 공유 백엔드 통신
                Callable<String> createCall = null;
                if(isMutable)
                    createCall = () -> shareService.registerDogam(share_category, 0);
                else
                    createCall = () -> shareService.registerDogam(share_category, 1);

                AsyncCallback<String> createCallback = new AsyncCallback<String>() {
                    @Override
                    public void onResult(String result) {
                        if(!NewShareActivity.this.isFinishing() && result.equals("0"))
                            Toast.makeText(getApplicationContext(), String.format("도감 '%s'가 정상적으로 공유되었습니다.", share_category.getTitle()), Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(getApplicationContext(), String.format("도감 '%s' 공유에 실패했습니다.", share_category.getTitle()), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(NewShareActivity.this, ShareMenuActivity.class);
                        startActivity(intent);

                        Log.d("register share result", result);
                    }

                    @Override
                    public void exceptionOccured(Exception e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void cancelled() {

                    }
                };

                new AsyncExecutor<String>().setCallable(createCall).setCallback(createCallback).execute();
            }
        });

    }

    //라디오 그룹 클릭 리스너
    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            if(i == R.id.activity_share_rb_mutable){
                Toast.makeText(NewShareActivity.this, "수정 가능 버튼이 눌렸습니다.", Toast.LENGTH_SHORT).show();
                password.setEnabled(false);
                isMutable = true;
            } else if(i == R.id.activity_share_rb_immutable){
                Toast.makeText(NewShareActivity.this, "수정 불가능 버튼이 눌렸습니다.", Toast.LENGTH_SHORT).show();
                password.setEnabled(true);
                isMutable = false;
            }
        }
    };

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
