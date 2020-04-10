package com.capstone.moayo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.capstone.moayo.dao.sqlite.DBHelper;
import com.capstone.moayo.entity.Category;
import com.capstone.moayo.entity.CategoryNode;
import com.capstone.moayo.service.CategoryService;
import com.capstone.moayo.service.DataBindingService;
import com.capstone.moayo.service.concrete.ServiceFactoryCreator;

public class MainActivity extends AppCompatActivity {
    private Button createBtn;
    private Button requestDataBtn;

    private CategoryService categoryService;
    private DataBindingService dataBindingService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoryService = ServiceFactoryCreator.getInstance().requestCategoryService();
        dataBindingService = ServiceFactoryCreator.getInstance().requestDataBindingService();

        createBtn = findViewById(R.id.createBtn);
        requestDataBtn = findViewById(R.id.dataBtn);

        createBtn.setOnClickListener(v -> {
            Category category = createCategory();
            String result = categoryService.createCategory(category);

            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        });

        requestDataBtn.setOnClickListener(v -> {
            Category category = createCategory();
            dataBindingService.requestData(category);
        });
    }

    public Category createCategory() {
        CategoryNode root = new CategoryNode("L1", null, 1);
        for(int i = 0; i < 3; i++) {
            CategoryNode secondNode = new CategoryNode("L2_" + i, root, 2);
            for(int j = 0; j < 5; j++) {
                CategoryNode thirdNode = new CategoryNode("L3+" + j, secondNode, 3);
                secondNode.getLowLevel().add(thirdNode);
            }
            root.getLowLevel().add(secondNode);
        }
        return new Category(root);
    }
}
