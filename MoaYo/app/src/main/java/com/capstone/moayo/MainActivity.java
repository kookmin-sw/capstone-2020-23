package com.capstone.moayo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.capstone.moayo.dao.sqlite.DBHelper;
import com.capstone.moayo.storage.StorageFactory;
import com.capstone.moayo.storage.concrete.StorageFactoryCreator;

public class MainActivity extends AppCompatActivity {

    private StorageFactory factory = StorageFactoryCreator.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper mDB = factory.initDao(this);
        Button dbCreate = findViewById(R.id.db_create);

        dbCreate.setOnClickListener(v ->{

        });
    }
}
