package com.capstone.moayo.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import com.capstone.moayo.BaseActivity;
import com.capstone.moayo.R;
import com.capstone.moayo.adapter.BookExpandableAdapter;
import com.capstone.moayo.service.CategoryService;
import com.capstone.moayo.service.concrete.ServiceFactoryCreator;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.service.dto.CategoryNodeDto;
import com.capstone.moayo.util.Async.AsyncCallback;
import com.capstone.moayo.util.Async.AsyncExecutor;
import com.capstone.moayo.util.DogamStatus;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.concurrent.Callable;


public class BookDetailActivity extends BaseActivity implements View.OnClickListener {

    private TextView toolbarTitle;
    private CategoryDto category;
    private CategoryNodeDto rootNode;
    private Button updateBtn, deleteBtn, shareBtn, backBtn, likeBtn, cancelBtn;
    private BottomSheetDialog bottomSheetDialog;
    private DogamStatus dogamStatus;

    private CategoryService categoryService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        categoryService = ServiceFactoryCreator.getInstance().requestCategoryService(getApplicationContext());

        //리소스 파일에서 추가한 툴바를 앱바로 지정하기
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        category = (CategoryDto) getIntent().getSerializableExtra("category");
        dogamStatus = category.getStatus();
        rootNode = category.getRootNode();

        toolbarTitle = (TextView) findViewById(R.id.detail_tv_title);
        toolbarTitle.setText(rootNode.getTitle());

        ExpandableListView myList = (ExpandableListView) findViewById(R.id.expandableListView);
        //create Data
        myList.setAdapter(new BookExpandableAdapter(this, category));
//        CustomAdapter mAdapter = new CustomAdapter (getApplicationContext(), R.layout.cmtview_custom, myList, MainActivity.this);

        TextView detail_text = (TextView) findViewById(R.id.detail_text);
        TextView detail_text2 = (TextView) findViewById(R.id.detail_text2);
        ImageView arrow_detail = (ImageView) findViewById(R.id.arrow_detail);

        detail_text.setText(rootNode.getTitle() + "");


        //listener for child click
//        myList.setOnChildClickListener(myListItemClicked);
        //listener for group click
//        myList.setOnGroupClickListener(myListGroupClicked);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_bookdetail, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        //menu.xml에서 지정한 item 이벤트 추가
        switch (item.getItemId()) {

            case R.id.bookDetailMenu: {

                bottomSheetDialog = new BottomSheetDialog(BookDetailActivity.this, R.style.BottomSheetDialogTheme);

                View bottomSheetView = LayoutInflater.from(getApplicationContext())
                        .inflate(R.layout.detail_bottom_menu, (LinearLayout) findViewById(R.id.bottomSheetContainer));

                updateBtn = bottomSheetView.findViewById(R.id.detail_btn_update);
                updateBtn.setOnClickListener(this);

                deleteBtn = bottomSheetView.findViewById(R.id.detail_btn_delete);
                deleteBtn.setOnClickListener(this);

                backBtn = bottomSheetView.findViewById(R.id.detail_btn_back);
                backBtn.setOnClickListener(this);

                shareBtn = bottomSheetView.findViewById(R.id.detail_btn_share);
                shareBtn.setOnClickListener(this);

                likeBtn = bottomSheetView.findViewById(R.id.detail_btn_like);
                likeBtn.setOnClickListener(this);

                cancelBtn = bottomSheetView.findViewById(R.id.detail_btn_cancel);
                cancelBtn.setOnClickListener(this);

                //도감 Status를 확인하여 비공유 도감(나의도감), 공유된 도감에 따른 버튼 view.
                switch (dogamStatus){
                    case NonShare:
                        likeBtn.setVisibility(View.GONE);
                        cancelBtn.setVisibility(View.GONE);
                        break;
                    default:
                        updateBtn.setVisibility(View.GONE);
                        deleteBtn.setVisibility(View.GONE);
                        shareBtn.setVisibility(View.GONE);
                        break;
                }

                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();

                return true;
            }

            default:
                onBackPressed();
                return true;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.detail_btn_update:
                //TODO: BookForm 화면으로 전환
                Intent intent_update = new Intent(BookDetailActivity.this, BookFormActivity.class);
                intent_update.putExtra("category", category);
                startActivity(intent_update);

                break;

            case R.id.detail_btn_delete:
                //TODO: 도감 삭제 후 BookManage 화면으로 전환
                //--------Backend 통신-----------
                Callable<String> callable = () -> categoryService.deleteDogam(category.getId());
                AsyncCallback<String> callback = new AsyncCallback<String>() {
                    @Override
                    public void onResult(String result) {
                        Intent intent1 = new Intent(BookDetailActivity.this, BookManageActivity.class);
                        startActivity(intent1);
                        Log.d("delete result", result);
                    }

                    @Override
                    public void exceptionOccured(Exception e) {
                        e.toString();
                    }

                    @Override
                    public void cancelled() {

                    }
                };

                new AsyncExecutor<String>().setCallable(callable).setCallback(callback).execute();
                Toast.makeText(getApplicationContext(), "도감 삭제 이벤트", Toast.LENGTH_SHORT).show();
                break;

            case R.id.detail_btn_share:
                //TODO: NewShareActivity로 화면 전환(도감 데이터 전달)
                Intent intent_share = new Intent(BookDetailActivity.this, NewShareActivity.class);
                intent_share.putExtra("target_category", category);
                startActivity(intent_share);

                break;

            case R.id.detail_btn_like:
                //TODO: 공유도감 좋아요
                break;

            case R.id.detail_btn_cancel:
                //TODO: PASSWORD 확인 후 공유도감 삭제(공유취소)

                EditText edittext = new EditText(this);
                edittext.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("공유도감 삭제");
                builder.setMessage("도감의 비밀번호를 입력하세요.");
                builder.setView(edittext);
                builder.setPositiveButton("입력",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),edittext.getText().toString() ,Toast.LENGTH_LONG).show();
                            }
                        });
                builder.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();


            break;

            case R.id.detail_btn_back:
                bottomSheetDialog.dismiss();
                break;



        }
    }





    //child listener
//    private OnChildClickListener myListItemClicked =  new OnChildClickListener() {
//
//        public boolean onChildClick(ExpandableListView parent, View v,
//                                    int groupPosition, int childPosition, long id) {
//
//            //get the group header
////            HeaderInfo headerInfo = deptList.get(groupPosition);
//            //get the child info
////            DetailInfo detailInfo =  headerInfo.getProductList().get(childPosition);
//            //display it or do something with it
////            Toast.makeText(getBaseContext(), "Clicked on Detail " + headerInfo.getName()
////                    + "/" + detailInfo.getName(), Toast.LENGTH_LONG).show();
////            Toast.makeText(getBaseContext(), "on click child!", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//
//    };

    //group listener
//    private OnGroupClickListener myListGroupClicked =  new OnGroupClickListener() {
//
//        public boolean onGroupClick(ExpandableListView parent, View v,
//                                    int groupPosition, long id) {
////            Toast.makeText(getBaseContext(), "on click group!", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//
//    };
}
