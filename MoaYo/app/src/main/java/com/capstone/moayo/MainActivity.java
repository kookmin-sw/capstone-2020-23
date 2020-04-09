package com.capstone.moayo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.capstone.moayo.entity.Category;
import com.capstone.moayo.service.CategoryService;
import com.capstone.moayo.service.concrete.ServiceFactoryCreator;

public class MainActivity extends AppCompatActivity {
    private Button createBtn;
    private CategoryService categoryService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoryService = ServiceFactoryCreator.getInstance().requestCategoryService();
        createBtn = findViewById(R.id.createBtn);

        createBtn.setOnClickListener(v -> {
            Category category = new Category();
            String result = categoryService.createCategory(category);

            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        });
    }
}
