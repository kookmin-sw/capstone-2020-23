package com.capstone.moayo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.drm.DrmStore;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class BookManageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_manage);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("나의 도감");

        actionBar.setDisplayHomeAsUpEnabled(true);

        //Spinner
        Spinner bookTypeSpinner = (Spinner)findViewById(R.id.bookManageSpinner);
        ArrayAdapter bookTypeAdapter = ArrayAdapter.createFromResource(this,
                R.array.book_manage_spinner, android.R.layout.simple_spinner_dropdown_item);
        bookTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bookTypeSpinner.setAdapter(bookTypeAdapter);

    }
}