package com.capstone.moayo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.capstone.moayo.service.CategoryService;
import com.capstone.moayo.service.concrete.ServiceFactoryCreator;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.service.dto.CategoryNodeDto;
import com.capstone.moayo.util.CategoryConvertor;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    private Button convertBtn;
    private Button createBtn;
    private Button modifyBtn;
    private CategoryService categoryService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        convertBtn = findViewById(R.id.convert);
        createBtn = findViewById(R.id.create);
        modifyBtn = findViewById(R.id.modify);

        categoryService = ServiceFactoryCreator.getInstance().requestCategoryService(getApplicationContext());

        convertBtn.setOnClickListener(v -> {
            CategoryDto testCategory = createCategory();
            CategoryNodeDto rootNode = testCategory.getRootNode();
            CategoryNodeDto secondNode = rootNode.getLowLayer().get(2);
            CategoryNodeDto thirdNode = secondNode.getLowLayer().get(3);
            String converting = CategoryConvertor.convertCategoryToJSON(secondNode, thirdNode);
            Log.d("convert result", converting);
        });

        createBtn.setOnClickListener(v -> {
            CategoryDto testCategory = createCategory();
            String result = categoryService.createCategory(testCategory);
            Log.d("create result", result);
        });

        modifyBtn.setOnClickListener(v -> {
            CategoryDto testCategory = createCategory();
            testCategory.setTitle("modify dummy dogam");
            testCategory.getRootNode().setTitle("modify node");

            String result = categoryService.modifyCategory(testCategory);

            Log.d("modify result", result);
        });
    }

    private CategoryDto createCategory() {
        CategoryNodeDto rootNode = new CategoryNodeDto("root node", null, 1);

        for(int i = 0; i < 5; i++) {
            CategoryNodeDto dummyNodeDto = new CategoryNodeDto("second" + i + "th node", rootNode, 2);
            for(int k = 0; k < 5; k++) {
                String hashtags = "second_hash" + k;
                dummyNodeDto.getHashtags().add(hashtags);
            }
            rootNode.getLowLayer().add(dummyNodeDto);
            for(int j = 0; j < 5; j++) {
                CategoryNodeDto dummyNodeDto2 = new CategoryNodeDto("third" + i + "th node", dummyNodeDto, 3);
                for(int k = 0; k < 5; k++) {
                    String hashtags = "third_hash" + k;
                    dummyNodeDto2.getHashtags().add(hashtags);
                }
                dummyNodeDto.getLowLayer().add(dummyNodeDto2);
            }
        }

        CategoryDto categoryDto = new CategoryDto("dummy dogam", "this is dummy dogam", "1234", null);
        categoryDto.setRootNode(rootNode);
        return categoryDto;
    }
}
