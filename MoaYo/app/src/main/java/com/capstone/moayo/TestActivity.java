package com.capstone.moayo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.capstone.moayo.dao.CategoryDao;
import com.capstone.moayo.dao.concrete.DaoFactoryCreator;
import com.capstone.moayo.dao.sqlite.DBHelper;
import com.capstone.moayo.entity.Model.ModelForm;
import com.capstone.moayo.service.CategoryService;
import com.capstone.moayo.service.PostService;
import com.capstone.moayo.service.SearchService;
import com.capstone.moayo.service.ShareService;
import com.capstone.moayo.service.concrete.ServiceFactoryCreator;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.service.dto.CategoryNodeDto;
import com.capstone.moayo.service.dto.InstantPost;
import com.capstone.moayo.service.dto.PostDto;
import com.capstone.moayo.service.dto.RequestForm;
import com.capstone.moayo.service.dto.RespondForm;
import com.capstone.moayo.util.Async.AsyncCallback;
import com.capstone.moayo.util.Async.AsyncExecutor;
import com.capstone.moayo.util.CategoryConvertor;
import com.capstone.moayo.util.ShareUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import retrofit2.Call;

public class TestActivity extends AppCompatActivity {

    private Button convertBtn;
    private Button createBtn;
    private Button modifyBtn;
    private Button findBtn;
    private Button initBtn;
    private Button removeBtn;
    private Button createPostBtn;
    private Button findPost;
    private Button removePost;
    private Button convertFormBtn;
    private Button requestBtn;

    private CategoryService categoryService;
    private PostService postService;
    private SearchService searchService;
    private ShareService shareService;
    private DBHelper dbHelper;

    private List<CategoryDto> dogams;

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
        createPostBtn = findViewById(R.id.createPost);
        findPost = findViewById(R.id.findPost);
        removePost = findViewById(R.id.removePost);
        convertFormBtn = findViewById(R.id.convertForm);
        requestBtn = findViewById(R.id.requestDogam);

        categoryService = ServiceFactoryCreator.getInstance().requestCategoryService(getApplicationContext());
        searchService = ServiceFactoryCreator.getInstance().requestSearchService(getApplicationContext());
        postService = ServiceFactoryCreator.getInstance().requestPostService(getApplicationContext());
        dbHelper = DaoFactoryCreator.getInstance().initDao(getApplicationContext());
        shareService = ServiceFactoryCreator.getInstance().requestShareService(getApplicationContext());

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
            final RespondForm[] respondForm = {null};
            Callable<RespondForm> callable = () -> searchService.requestData(requsetForm);
            AsyncCallback<RespondForm> callback = new AsyncCallback<RespondForm>() {
                @Override
                public void onResult(RespondForm result) {
                    respondForm[0] = result;
                }

                @Override
                public void exceptionOccured(Exception e) {

                }

                @Override
                public void cancelled() {

                }
            };
            new AsyncExecutor<RespondForm>().setCallable(callable).setCallback(callback).execute();

            InstantPost post = respondForm[0].getSecond_layer().get(3);
            Log.d("response result: text", post.getText());
            Log.d("response result: url", post.getUrl());
            Log.d("response result: src", post.getSrc());
            Log.d("response result: like", String.format("%d", post.getLike()));
            Log.d("response result: cache", respondForm[0].getSecond_layer_cache()[3]);

            requsetForm.setSecond_layer_cache(respondForm[0].getSecond_layer_cache());
            requsetForm.setThird_layer_cache(respondForm[0].getThird_layer_cache());

            new AsyncExecutor<RespondForm>().setCallable(callable).setCallback(callback).execute();

            InstantPost post1 = respondForm[0].getSecond_layer().get(3);
            Log.d("response result: text", post1.getText());
            Log.d("response result: url", post1.getUrl());
            Log.d("response result: src", post1.getSrc());
            Log.d("response result: like", String.format("%d", post1.getLike()));
            Log.d("response result: cache", respondForm[0].getSecond_layer_cache()[3]);
        });

        createBtn.setOnClickListener(v -> {
            CategoryDto testCategory = createCategory();
            Callable<String> callable = () -> categoryService.createCategory(testCategory);
            AsyncCallback<String> callback = new AsyncCallback<String>() {
                @Override
                public void onResult(String result) {
                    Log.d("create result", result);
                }

                @Override
                public void exceptionOccured(Exception e) {
                    e.printStackTrace();
                }

                @Override
                public void cancelled() {

                }
            };
            new AsyncExecutor<String>().setCallable(callable).setCallback(callback).execute();
        });

        modifyBtn.setOnClickListener(v -> {

            CategoryDto testCategory = dogams.get(0);

            testCategory.setTitle("modify dummy dogam");
            testCategory.getRootNode().setTitle("modify node");
            testCategory.getRootNode().getLowLayer().get(2).getHashtags().set(2, "modify hash tag");

            Callable<String> callable1 = () -> categoryService.modifyCategory(testCategory);
            AsyncCallback<String> callback1 = new AsyncCallback<String>() {
                @Override
                public void onResult(String result) {
                    Log.d("modify result", result);
                }

                @Override
                public void exceptionOccured(Exception e) {

                }

                @Override
                public void cancelled() {

                }
            };

            new AsyncExecutor<String>().setCallable(callable1).setCallback(callback1).execute();
        });

        findBtn.setOnClickListener(v -> {
            Callable<List<CategoryDto>> callable = () -> categoryService.findAll();
            AsyncCallback<List<CategoryDto>> callback = new AsyncCallback<List<CategoryDto>>() {
                @Override
                public void onResult(List<CategoryDto> result) {
                    dogams = result;
                    for(CategoryDto categoryDto : dogams) {
                        Log.d("found Category", categoryDto.toString());
                    }
                }

                @Override
                public void exceptionOccured(Exception e) {
                    e.printStackTrace();
                }

                @Override
                public void cancelled() {

                }
            };
            new AsyncExecutor<List<CategoryDto>>().setCallable(callable).setCallback(callback).execute();
        });

        initBtn.setOnClickListener(v -> {
            dbHelper.upgrade(dbHelper.getWritableDB());
        });

        removeBtn.setOnClickListener(v -> {
            CategoryDto foundCategory = categoryService.findCategoryById(1);
            String result = categoryService.deleteCategoryNode(2);
            Log.d("delete result", result);
//            result = categoryService.deleteDogam(1);
//            Log.d("delete result", result);
        });

        createPostBtn.setOnClickListener(v -> {
            InstantPost newPost = new InstantPost("dummy post", "dummy url", "dummy src", 123);
            InstantPost newPost1 = new InstantPost("dummy post", "dummy url1", "dummy src", 123);
            InstantPost newPost2 = new InstantPost("dummy post", "dummy url2", "dummy src", 123);
            InstantPost newPost3 = new InstantPost("dummy post", "dummy url3", "dummy src", 123);
            InstantPost newPost4 = new InstantPost("dummy post", "dummy url4", "dummy src", 123);
            InstantPost newPost5 = new InstantPost("dummy post", "dummy url5", "dummy src", 123);
            postService.createPost(newPost, 2, 1);
            postService.createPost(newPost1, 5, 1);
            postService.createPost(newPost2, 8, 1);
            postService.createPost(newPost3, 16, 1);
            postService.createPost(newPost4, 24, 1);
            postService.createPost(newPost5, 17, 1);
        });

        findPost.setOnClickListener(v -> {
            List<PostDto> foundPost = postService.findPostByCategoryNodeId(2);
            Log.d("found post", foundPost.toString());
        });

        removePost.setOnClickListener(v -> {
            List<PostDto> foundPosts = postService.findPostByCategoryNodeId(2);

            for(PostDto postDto : foundPosts) {
                Log.d("found post", postDto.toString());
                postService.deletePostById(postDto.getCategoryNodeId(), postDto.getId());
            }
        });

        convertFormBtn.setOnClickListener(v -> {
            CategoryDto foundCategory = categoryService.findCategoryById(1);
            List<PostDto> postDtos = postService.findPostByCategoryNodeId(foundCategory.getRootNode().getLowLayer().get(1).getLowLayer().get(1).getId());
            foundCategory.getRootNode().getLowLayer().get(1).getLowLayer().get(1).setPosts(postDtos);
            ModelForm form = ShareUtil.convertDogamToModelForm(foundCategory);
            logLargeString(form.toString());
            CategoryDto categoryDto = ShareUtil.convertFormToDogam(form);
            logLargeString(categoryDto.toString());
        });

        requestBtn.setOnClickListener(v -> {
//            CategoryDto foundCategoryDto = shareService.loadDogam(30);
//            Log.d("dogam", foundCategoryDto.toString());
        });
    }

    public void logLargeString(String str) {
        if (str.length() > 3000) {
            Log.d("d", str.substring(0, 3000));
            logLargeString(str.substring(3000));
        } else {
            Log.d("d", str); // continuation
        }
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

    private CategoryDto createPost(CategoryDto categoryDto) {
        CategoryDto dogam = new CategoryDto(categoryDto.getTitle(), categoryDto.getDescription(), categoryDto.getPassword(), categoryDto.getRootNode());
        CategoryNodeDto rootNode = dogam.getRootNode();
        for(int i  = 0; i < 3; i++)
            rootNode.getPosts().add(new PostDto("IMGUrl-rootNode", "url", "hash"+i, i, rootNode.getId(), categoryDto.getId()));

        for(CategoryNodeDto secondNodeDto : rootNode.getLowLayer()) {
            for(int i = 0; i < 3; i++)
                secondNodeDto.getPosts().add(new PostDto("IMGURL-secondNode", "url", "hash"+i, i, secondNodeDto.getId(), categoryDto.getId()));
            for(CategoryNodeDto thirdNodeDto : secondNodeDto.getLowLayer()) {
                for(int i = 0; i < 3; i++)
                    thirdNodeDto.getPosts().add(new PostDto("IMGURL-thirdNode", "url", "hash"+i, i, thirdNodeDto.getId(), categoryDto.getId()));
            }
        }
        return dogam;
    }
}
