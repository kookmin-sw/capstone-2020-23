package com.capstone.moayo.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.annotation.Nullable;
import com.capstone.moayo.BaseActivity;
import com.capstone.moayo.R;

public class AboutActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


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
