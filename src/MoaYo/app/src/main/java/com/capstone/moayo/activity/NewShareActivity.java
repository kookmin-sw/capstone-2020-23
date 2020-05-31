package com.capstone.moayo.activity;

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

import java.util.ArrayList;

public class NewShareActivity extends BaseActivity {

    Spinner spinner;
    ArrayAdapter<String> spinner_adapter;
    Button submit_btn;
    EditText nickname, password, content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_share);

        ArrayList<String> spinner_list = new ArrayList<>();
        spinner_list.add("도감1");
        spinner_list.add("도감2");
        spinner_list.add("도감3");
        spinner_list.add("도감4");
        spinner_list.add("도감5");

        nickname = (EditText) findViewById(R.id.activity_share_et_nickname);
        password = (EditText) findViewById(R.id.activity_share_et_password);
        content = (EditText) findViewById(R.id.activity_share_et_content);

        spinner = (Spinner) findViewById(R.id.activity_share_sp_target);
        spinner_adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, spinner_list);
        spinner.setAdapter(spinner_adapter);

        submit_btn = (Button) findViewById(R.id.activity_share_btn_submit);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Submit Data", nickname.getText().toString() + password.getText().toString() + content.getText().toString());
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
