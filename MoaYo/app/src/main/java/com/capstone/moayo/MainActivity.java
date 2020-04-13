package com.capstone.moayo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.capstone.moayo.dao.CategoryDao;
import com.capstone.moayo.dao.ContentDao;
import com.capstone.moayo.dao.DogamDao;
import com.capstone.moayo.dao.concrete.CategoryDaoImpl;
import com.capstone.moayo.dao.concrete.ContentDaoImpl;
import com.capstone.moayo.dao.concrete.DogamDaoImpl;
import com.capstone.moayo.dao.sqlite.DBHelper;
import com.capstone.moayo.entity.Category;
import com.capstone.moayo.entity.CategoryNode;
import com.capstone.moayo.entity.Content;
import com.capstone.moayo.service.CategoryService;
import com.capstone.moayo.service.DataBindingService;
import com.capstone.moayo.service.concrete.ServiceFactoryCreator;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.service.dto.CategoryNodeDto;
import com.capstone.moayo.util.Exception.NotRootException;
import com.capstone.moayo.storage.StorageFactory;
import com.capstone.moayo.storage.concrete.StorageFactoryCreator;
import com.capstone.moayo.util.Exception.DaoObjectNullException;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    private Button createBtn;
    private Button requestDataBtn;
    private Button DBButton;

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

        DBHelper mDBHelper = storageFactory.initDao(this);

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
//                mDBHelper.upgrade(mDBHelper.getWritableDB());
//                Toast.makeText(this,String.valueOf(dogamDao.insert(mDBHelper,"NewDogam","This is new Dogam",null)),Toast.LENGTH_SHORT).show();
//                Toast.makeText(this,String.valueOf(categoryDao.insert(mDBHelper,1,1,"패션",1)),Toast.LENGTH_SHORT).show();
//
//                Toast.makeText(this,String.valueOf(categoryDao.insert(mDBHelper,2,1,"상의",1)),Toast.LENGTH_SHORT).show();
//                Toast.makeText(this,String.valueOf(categoryDao.insert(mDBHelper,2,1,"하의",1)),Toast.LENGTH_SHORT).show();
//
//                Toast.makeText(this,String.valueOf(categoryDao.insert(mDBHelper,3,2,"아우터",1)),Toast.LENGTH_SHORT).show();
//                Toast.makeText(this,String.valueOf(categoryDao.insert(mDBHelper,3,2,"조끼",1)),Toast.LENGTH_SHORT).show();
//                Toast.makeText(this,String.valueOf(categoryDao.insert(mDBHelper,3,3,"청바지",1)),Toast.LENGTH_SHORT).show();
//                Toast.makeText(this,String.valueOf(categoryDao.insert(mDBHelper,3,3,"슬렉",1)),Toast.LENGTH_SHORT).show();

                categoryDao.selectByDogamId(mDBHelper,dogamDao.selectByTitle(mDBHelper,"NewDogam").getId());

            } catch (Exception e){
                System.out.println(e.getMessage());
            }

        });
        createBtn.setOnClickListener(v -> {
            try {
                CategoryNodeDto category = createCategory();
                String result = categoryService.createCategory(category);

                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
            } catch (NotRootException e) {
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
//        Gson gson = new Gson();
//        String result = gson.toJson(root);
//        Log.d("category", result);
        return root;
    }
}