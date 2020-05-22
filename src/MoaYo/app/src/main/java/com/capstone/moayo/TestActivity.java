package com.capstone.moayo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.capstone.moayo.dao.concrete.DaoFactoryCreator;
import com.capstone.moayo.dao.sqlite.DBHelper;
import com.capstone.moayo.service.CategoryService;
import com.capstone.moayo.service.concrete.ServiceFactoryCreator;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.service.dto.CategoryNodeDto;
import com.capstone.moayo.util.Async.AsyncCallback;
import com.capstone.moayo.util.Async.AsyncExecutor;

import java.util.List;
import java.util.concurrent.Callable;

public class TestActivity extends AppCompatActivity {

    Button findBtn;
    Button createBtn;
    Button initBtn;

    CategoryService categoryService;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        findBtn = findViewById(R.id.find);
        createBtn = findViewById(R.id.create);
        initBtn = findViewById(R.id.init);

        categoryService = ServiceFactoryCreator.getInstance().requestCategoryService(getApplicationContext());
        dbHelper = DaoFactoryCreator.getInstance().initDao(getApplicationContext());

        createBtn.setOnClickListener(v -> {
            CategoryDto categoryDto = createCategory();
            Callable<String> callable = () -> categoryService.createCategory(categoryDto);
            AsyncCallback<String> callback = new AsyncCallback<String>() {
                @Override
                public void onResult(String result) {
                    Log.d("create category", result);
                }

                @Override
                public void exceptionOccured(Exception e) {
                    e.printStackTrace();
                }

                @Override
                public void cancelled() {
                    Log.d("create cancelled", "");
                }
            };

            new AsyncExecutor<String>().setCallable(callable).setCallback(callback).execute();
        });

        findBtn.setOnClickListener(v -> {
            Callable<List<CategoryDto>> callable = () -> categoryService.findAll();
            AsyncCallback<List<CategoryDto>> callback = new AsyncCallback<List<CategoryDto>>() {
                @Override
                public void onResult(List<CategoryDto> result) {
                    for(CategoryDto categoryDto : result) {
                        Log.d("find category", categoryDto.toString());
                    }
                }

                @Override
                public void exceptionOccured(Exception e) {
                    e.printStackTrace();
                }

                @Override
                public void cancelled() {
                    Log.d("find cancelled", "");
                }
            };

            new AsyncExecutor<List<CategoryDto>>().setCallable(callable).setCallback(callback).execute();
        });

        initBtn.setOnClickListener(v -> {
            SQLiteDatabase mDB = dbHelper.getWritableDB();
            dbHelper.upgrade(mDB);
        });
    }

    private CategoryDto createCategory() {
        CategoryDto categoryDto = new CategoryDto("dummy dogam", "this is dummy dogam", "1234", "https://www.naver.com", null);
        CategoryNodeDto rootNode = new CategoryNodeDto("root node", null, 1);
        for(int i = 0; i < 3; i++) {
            CategoryNodeDto secondNode = new CategoryNodeDto(i+"th secondNode", rootNode, 2);
            for(int j = 0; j < 3; j++) {
                secondNode.getHashtags().add(j + "th hashtag");
            }

            for(int k = 0; k < 3; k++) {
                CategoryNodeDto thirdNode = new CategoryNodeDto(k+"th thirdNode", secondNode, 3);
                for(int j = 0; j < 3; j++) {
                    thirdNode.getHashtags().add(j+"th hashtag");
                }
                secondNode.getLowLayer().add(thirdNode);
            }
            rootNode.getLowLayer().add(secondNode);
        }
        categoryDto.setRootNode(rootNode);

        return categoryDto;
    }
}
