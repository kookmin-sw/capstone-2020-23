package com.capstone.moayo.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.capstone.moayo.R;

public class AboutActivity extends AppCompatActivity {

    private TextView project_title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        project_title = (TextView) findViewById(R.id.activity_about_tv_title);
        project_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "KMU SW 2020 Capstone Design.", Toast.LENGTH_SHORT).show();
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
