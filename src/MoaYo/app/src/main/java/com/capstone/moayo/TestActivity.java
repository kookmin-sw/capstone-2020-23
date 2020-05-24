package com.capstone.moayo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.capstone.moayo.dao.concrete.DaoFactoryCreator;
import com.capstone.moayo.dao.sqlite.DBHelper;
import com.capstone.moayo.data.CategoryData_Dummy;
import com.capstone.moayo.entity.CategoryNode;
import com.capstone.moayo.service.CategoryService;
import com.capstone.moayo.service.SearchService;
import com.capstone.moayo.service.ServiceFactory;
import com.capstone.moayo.service.ShareService;
import com.capstone.moayo.service.concrete.ServiceFactoryCreator;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.service.dto.CategoryNodeDto;
import com.capstone.moayo.service.dto.RespondForm;
import com.capstone.moayo.util.Async.AsyncCallback;
import com.capstone.moayo.util.Async.AsyncExecutor;
import com.capstone.moayo.util.DogamStatus;
import com.capstone.moayo.util.Tag.TagsFinder;
import com.capstone.moayo.util.retrofit.ShareResponse;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

import retrofit2.Call;

public class TestActivity extends AppCompatActivity {

    Button findBtn;
    Button createBtn;
    Button initBtn;
    Button requestBtn;
    Button tagBtn;

    CategoryService categoryService;
    SearchService searchService;
    ShareService shareService;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        findBtn = findViewById(R.id.find);
        createBtn = findViewById(R.id.create);
        initBtn = findViewById(R.id.init);
        requestBtn = findViewById(R.id.requestPost);
        tagBtn = findViewById(R.id.getTag);

        categoryService = ServiceFactoryCreator.getInstance().requestCategoryService(getApplicationContext());
        searchService = ServiceFactoryCreator.getInstance().requestSearchService(getApplicationContext());
        shareService = ServiceFactoryCreator.getInstance().requestShareService(getApplicationContext());
        dbHelper = DaoFactoryCreator.getInstance().initDao(getApplicationContext());

        createBtn.setOnClickListener(v -> {
            Callable<Integer> callable = () -> {
                List<CategoryDto> categoryDtoList = categoryService.findAll();
                if(categoryDtoList.isEmpty()) {
                    List<CategoryDto> dummy = new CategoryData_Dummy().getItems();
                    for(CategoryDto categoryDto : dummy)
                        categoryService.createCategory(categoryDto);
                }
                return 1;
            };
            AsyncCallback<Integer> callback = new AsyncCallback<Integer>() {
                @Override
                public void onResult(Integer result) {
                    Log.d("success result", Integer.toString(result));
                }

                @Override
                public void exceptionOccured(Exception e) {
                    e.printStackTrace();
                }

                @Override
                public void cancelled() {

                }
            };

            new AsyncExecutor<Integer>().setCallable(callable).setCallback(callback).execute();
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

        requestBtn.setOnClickListener(v -> {
            CategoryDto categoryDto = new CategoryDto("dummy", "dummy data", "1234", "", null);
            CategoryNodeDto secondNode = new CategoryNodeDto("secondNode", null, 2);
            CategoryNodeDto thirdNode = new CategoryNodeDto("thirdNode", null, 3);

            secondNode.getHashtags().add("중식");
            secondNode.getHashtags().add("중국요리");
            secondNode.getHashtags().add("맛집");

            thirdNode.getHashtags().add("짜장면");
            thirdNode.getHashtags().add("차이나타운");
            thirdNode.getHashtags().add("중국집");

            Callable<RespondForm> callable = () -> searchService.requestData(secondNode, thirdNode);
            AsyncCallback<RespondForm> callback = new AsyncCallback<RespondForm>() {
                @Override
                public void onResult(RespondForm result) {
                    System.out.println(result.getThrid_layer().toString());
                    System.out.println(result.getSecond_layer_cache().toString());
                    System.out.println(result.getThird_layer_cache().toString());
                }

                @Override
                public void exceptionOccured(Exception e) {
                    e.printStackTrace();
                }

                @Override
                public void cancelled() {

                }
            };

            new AsyncExecutor<RespondForm>().setCallable(callable).setCallback(callback).execute();
//            CategoryDto categoryDto = createCategory();

//            Callable<String> callable = () -> shareService.deleteDogam(61);
//            AsyncCallback<String> callback = new AsyncCallback<String>() {
//                @Override
//                public void onResult(String result) {
//                    Log.d("result", result);
//                }
//
//                @Override
//                public void exceptionOccured(Exception e) {
//
//                }
//
//                @Override
//                public void cancelled() {
//
//                }
//            };
//
//            new AsyncExecutor<String>().setCallable(callable).setCallback(callback).execute();
        });

        tagBtn.setOnClickListener(v -> {
            Callable<List<String>> callable = () -> TagsFinder.getSimilarTags("청바지");
            AsyncCallback<List<String>> callback = new AsyncCallback<List<String>>() {
                @Override
                public void onResult(List<String> result) {
                    for(String r : result) {
                        System.out.println(r);
                    }
                }

                @Override
                public void exceptionOccured(Exception e) {

                }

                @Override
                public void cancelled() {

                }
            };

            new AsyncExecutor<List<String>>().setCallable(callable).setCallback(callback).execute();
//            TagsFinder tagsFinder = new TagsFinder();
//            try {
//                tagsFinder.getSimilarTags("중식");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
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
