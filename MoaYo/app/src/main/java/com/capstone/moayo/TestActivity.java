package com.capstone.moayo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.capstone.moayo.dao.concrete.DaoFactoryCreator;
import com.capstone.moayo.dao.sqlite.DBHelper;
import com.capstone.moayo.service.CategoryService;
import com.capstone.moayo.service.SearchService;
import com.capstone.moayo.service.concrete.ServiceFactoryCreator;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.service.dto.CategoryNodeDto;
import com.capstone.moayo.service.dto.InstantPost;
import com.capstone.moayo.service.dto.RequestForm;
import com.capstone.moayo.service.dto.RespondForm;
import com.capstone.moayo.util.CategoryConvertor;
import com.capstone.moayo.util.Tag.TagsFinder;

import java.util.List;

public class TestActivity extends AppCompatActivity {

    private Button convertBtn;
    private Button createBtn;
    private Button modifyBtn;
    private Button findBtn;
    private Button initBtn;
    private Button removeBtn;
    private CategoryService categoryService;
    private SearchService searchService;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        convertBtn = findViewById(R.id.convert);
        createBtn = findViewById(R.id.create);
        modifyBtn = findViewById(R.id.modify);
        findBtn = findViewById(R.id.find);
        initBtn = findViewById(R.id.init);
        removeBtn = findViewById(R.id.remove);

        categoryService = ServiceFactoryCreator.getInstance().requestCategoryService(getApplicationContext());
        searchService = ServiceFactoryCreator.getInstance().requestSearchService(getApplicationContext());
        dbHelper = DaoFactoryCreator.getInstance().initDao(getApplicationContext());

        convertBtn.setOnClickListener(v -> {
            CategoryDto testCategory = createCategory();
            CategoryNodeDto rootNode = testCategory.getRootNode();
            CategoryNodeDto secondNode = rootNode.getLowLayer().get(2);
            List<String> hashtagList = secondNode.getHashtags();

            hashtagList.set(0, "cafe");
            hashtagList.set(1, "커피");
            hashtagList.set(2, "카페");
            hashtagList.set(3, "coffee");
            hashtagList.set(4, "일상");
            secondNode.setHashtags(hashtagList);

            CategoryNodeDto thirdNode = secondNode.getLowLayer().get(3);
            List<String> hashtagList1 = thirdNode.getHashtags();
            hashtagList1.set(0, "스타벅스");
            hashtagList1.set(1, "starbucks");
            hashtagList1.set(2, "daily");
            hashtagList1.set(3, "데일리");
            hashtagList1.set(4, "coffee");
            thirdNode.setHashtags(hashtagList1);

            RequestForm requsetForm = CategoryConvertor.generateForm(secondNode, thirdNode);

            RespondForm respondForm = searchService.requestData(requsetForm);
            InstantPost post = respondForm.getSecond_layer().get(3);
            Log.d("response result: text", post.getText());
            Log.d("response result: url", post.getUrl());
            Log.d("response result: src", post.getSrc());
            Log.d("response result: like", String.format("%d", post.getLike()));
            Log.d("response result: cache", respondForm.getSecond_layer_cache()[3]);

            requsetForm.setSecond_layer_cache(respondForm.getSecond_layer_cache());
            requsetForm.setThird_layer_cache(respondForm.getThird_layer_cache());

            RespondForm respondForm1 = searchService.requestData(requsetForm);
            InstantPost post1 = respondForm1.getSecond_layer().get(3);
            Log.d("response result: text", post1.getText());
            Log.d("response result: url", post1.getUrl());
            Log.d("response result: src", post1.getSrc());
            Log.d("response result: like", String.format("%d", post1.getLike()));
            Log.d("response result: cache", respondForm1.getSecond_layer_cache()[3]);
        });

        createBtn.setOnClickListener(v -> {
            CategoryDto testCategory = createCategory();
            String result = categoryService.createCategory(testCategory);
            Log.d("create result", result);
        });

        modifyBtn.setOnClickListener(v -> {
            CategoryDto testCategory = categoryService.findCategoryById(1);
            testCategory.setTitle("modify dummy dogam");
            testCategory.getRootNode().setTitle("modify node");

            String result = categoryService.modifyCategory(testCategory);

            Log.d("modify result", result);
        });

        findBtn.setOnClickListener(v -> {
            List<CategoryDto> foundCategory = categoryService.findAll();
            for(CategoryDto categoryDto : foundCategory)
                Log.d("found category", categoryDto.toString());
        });

        initBtn.setOnClickListener(v -> {
            dbHelper.upgrade(dbHelper.getWritableDB());
        });

        removeBtn.setOnClickListener(v -> {
            CategoryDto foundCategory = categoryService.findCategoryById(1);
            String result = categoryService.deleteCategoryNode(18);
            Log.d("delete result", result);
//            result = categoryService.deleteDogam(1);
//            Log.d("delete result", result);
        });
    }

    private CategoryDto createCategory() {
        CategoryNodeDto rootNode = new CategoryNodeDto("root node", null, 1);

        for(int i = 0; i < 5; i++) {
            CategoryNodeDto dummyNodeDto = new CategoryNodeDto("second" + i + "th node", rootNode, 2);
            for(int k = 0; k < 5; k++) {
                String hashtags = "second" + k + "hash";
                dummyNodeDto.getHashtags().add(hashtags);
            }
            rootNode.getLowLayer().add(dummyNodeDto);
            for(int j = 0; j < 5; j++) {
                CategoryNodeDto dummyNodeDto2 = new CategoryNodeDto("third" + j + "th node", dummyNodeDto, 3);
                for(int k = 0; k < 5; k++) {
                    String hashtags = "third" + k + "hash";
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
