package com.capstone.moayo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.capstone.moayo.dao.CategoryDao;
import com.capstone.moayo.dao.DogamDao;
import com.capstone.moayo.dao.concrete.CategoryDaoImpl;
import com.capstone.moayo.dao.concrete.DogamDaoImpl;
import com.capstone.moayo.dao.sqlite.DBHelper;
import com.capstone.moayo.service.CategoryService;
import com.capstone.moayo.service.DataBindingService;
import com.capstone.moayo.service.concrete.ServiceFactoryCreator;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.service.dto.CategoryNodeDto;
import com.capstone.moayo.storage.StorageFactory;
import com.capstone.moayo.storage.concrete.StorageFactoryCreator;
import com.capstone.moayo.util.Exception.DaoObjectNullException;
import com.capstone.moayo.util.Tag.RequestHttpConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class MainActivity extends AppCompatActivity {
    private Button createBtn;
    private Button requestDataBtn;
    private Button DBButton;
    private Button findBtn;
    private Button deleteBtn;
    private Button getTagBtn;

    private CategoryService categoryService;
    private DataBindingService dataBindingService;
    private StorageFactory storageFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoryService = ServiceFactoryCreator.getInstance().requestCategoryService(getApplicationContext());
        dataBindingService = ServiceFactoryCreator.getInstance().requestDataBindingService(getApplicationContext());
        storageFactory = StorageFactoryCreator.getInstance();

        createBtn = findViewById(R.id.createBtn);
        requestDataBtn = findViewById(R.id.dataBtn);
        DBButton = findViewById(R.id.DB);
        Button DBDel = findViewById(R.id.DBdel);
        findBtn = findViewById(R.id.findBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        getTagBtn = findViewById(R.id.GetTag);

        DBHelper mDBHelper = storageFactory.initDao(this);

        getTagBtn.setOnClickListener(v->{
            // OOTD 대신 다른 태그 넣어서 확인해봐. 아직 한글은 안해봄, print로 출력하니 run에서 확인할 것.
            String url = "https://www.tagsfinder.com/ko-kr/related/OOTD";
            class NetworkTask extends AsyncTask<Void, Void, String> {

                private String url;
                private ContentValues values;

                public NetworkTask(String url, ContentValues values) {

                    this.url = url;
                    this.values = values;
                }

                @Override
                protected String doInBackground(Void... params) {

                    String result; // 요청 결과를 저장할 변수.
                    RequestHttpConnection requestHttpURLConnection = new RequestHttpConnection();
                    result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.

                    return result;
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    Document document = Jsoup.parse(s);
                    Element result = document.getElementById("hashtagy");
                    System.out.println(result);
                }
            }
            NetworkTask networkTask = new NetworkTask(url,null);
            networkTask.execute();
        });

        DBDel.setOnClickListener(v->{
            try {
                CategoryDao categoryDao = CategoryDaoImpl.getInstance();
//                if(categoryDao.delete(mDBHelper,1)){
//                    Toast.makeText(this,"True",Toast.LENGTH_SHORT).show();
//                }
//                categoryDao.selectAll(mDBHelper);
            }catch(DaoObjectNullException e){
                    System.out.print("");
            }
        });
        DBButton.setOnClickListener(v->{
            try{

                CategoryDao categoryDao = CategoryDaoImpl.getInstance();
                DogamDao dogamDao = DogamDaoImpl.getInstance();
                mDBHelper.upgrade(mDBHelper.getWritableDB());
                Toast.makeText(this,String.valueOf(dogamDao.insert(mDBHelper,"NewDogam","This is new Dogam",null)),Toast.LENGTH_SHORT).show();
                Toast.makeText(this,String.valueOf(categoryDao.insert(mDBHelper,1,1,"패션",1)),Toast.LENGTH_SHORT).show();

                Toast.makeText(this,String.valueOf(categoryDao.insert(mDBHelper,2,1,"상의",1)),Toast.LENGTH_SHORT).show();
                Toast.makeText(this,String.valueOf(categoryDao.insert(mDBHelper,2,1,"하의",1)),Toast.LENGTH_SHORT).show();

                Toast.makeText(this,String.valueOf(categoryDao.insert(mDBHelper,3,2,"아우터",1)),Toast.LENGTH_SHORT).show();
                Toast.makeText(this,String.valueOf(categoryDao.insert(mDBHelper,3,2,"조끼",1)),Toast.LENGTH_SHORT).show();
                Toast.makeText(this,String.valueOf(categoryDao.insert(mDBHelper,3,3,"청바지",1)),Toast.LENGTH_SHORT).show();
                Toast.makeText(this,String.valueOf(categoryDao.insert(mDBHelper,3,3,"슬렉",1)),Toast.LENGTH_SHORT).show();

                categoryDao.selectByDogamId(mDBHelper,dogamDao.selectByTitle(mDBHelper,"NewDogam").getId());


            } catch (Exception e){
                System.out.println(e.getMessage());
            }

        });
        createBtn.setOnClickListener(v -> {
            try {
                mDBHelper.upgrade(mDBHelper.getWritableDB());
                CategoryNodeDto category = createCategory();
                CategoryDto categoryDto = new CategoryDto("sample dogam", "this is sample category", "1234", category);
                String result = categoryService.createCategory(categoryDto);

                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        findBtn.setOnClickListener(v -> {
            try {

                CategoryDto category = categoryService.findCategoryById(1);
                Log.d("found category", category.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        deleteBtn.setOnClickListener(v -> {
            try {
                String result = categoryService.deleteCategory(1);
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

//        requestDataBtn.setOnClickListener(v -> {
//            CategoryNodeDto category = createCategory();
//            dataBindingService.requestData(category);
//        });
    }

    public CategoryNodeDto createCategory() {
        CategoryNodeDto root = new CategoryNodeDto("L1", null, 1);
        for(int i = 0; i < 3; i++) {
            CategoryNodeDto secondNode = new CategoryNodeDto("L2_" + i, root, 2);
            root.getLowLayer().add(secondNode);
            for(int j = 0; j < 5; j++) {
                CategoryNodeDto thirdNode = new CategoryNodeDto("L3+" + j, secondNode, 3);
                secondNode.getLowLayer().add(thirdNode);
            }
        }
        return root;
    }
}
