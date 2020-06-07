package com.capstone.moayo.activity;

import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.capstone.moayo.CustomDialog;
import com.capstone.moayo.R;
import com.capstone.moayo.adapter.BookExpandableAdapter;
import com.capstone.moayo.service.CategoryService;
import com.capstone.moayo.service.PostService;
import com.capstone.moayo.service.ShareService;
import com.capstone.moayo.service.concrete.ServiceFactoryCreator;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.service.dto.CategoryNodeDto;
import com.capstone.moayo.service.dto.PostDto;
import com.capstone.moayo.util.Async.AsyncCallback;
import com.capstone.moayo.util.Async.AsyncExecutor;
import com.capstone.moayo.util.DogamStatus;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.concurrent.Callable;


public class BookDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView toolbarTitle;
    private TextView detail_text;
    private CategoryDto category;
    private CategoryNodeDto rootNode;
   
    private CustomDialog customDialog;
    private Button updateBtn, deleteBtn, shareBtn, backBtn, likeBtn, cancelBtn, sharingBtn;
    BottomSheetDialog bottomSheetDialog;
    private DogamStatus dogamStatus;

    private ExpandableListView myList;

    private CategoryService categoryService;
    private ShareService shareService;
    private PostService postService;

    private boolean isLiked;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        categoryService = ServiceFactoryCreator.getInstance().requestCategoryService(getApplicationContext());
        shareService = ServiceFactoryCreator.getInstance().requestShareService(getApplicationContext());
        postService = ServiceFactoryCreator.getInstance().requestPostService(getApplicationContext());
        //리소스 파일에서 추가한 툴바를 앱바로 지정하기
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        toolbarTitle = (TextView) findViewById(R.id.detail_tv_title);
        myList = (ExpandableListView) findViewById(R.id.expandableListView);
        detail_text = (TextView) findViewById(R.id.detail_text);
        TextView detail_text2 = (TextView) findViewById(R.id.detail_text2);

        category = (CategoryDto) getIntent().getSerializableExtra("category");
        isLiked = shareService.findDogamLiked(category.getId());

        //create Data
        if(category.getRootNode() == null) {
            Callable<CategoryDto> callable = () -> shareService.findDogamById(category.getId());
            AsyncCallback<CategoryDto> callback = new AsyncCallback<CategoryDto>() {
                @Override
                public void onResult(CategoryDto result) {
                    category = result;
                    toolbarTitle.setText(category.getTitle());
                    myList.setAdapter(new BookExpandableAdapter(getApplicationContext(), category));
                    dogamStatus = category.getStatus();
                    rootNode = category.getRootNode();
                    detail_text.setText(rootNode.getTitle() + "");
                }

                @Override
                public void exceptionOccured(Exception e) {

                }

                @Override
                public void cancelled() {

                }
            };
            new AsyncExecutor<CategoryDto>().setCallable(callable).setCallback(callback).execute();
        } else {
            toolbarTitle.setText(category.getTitle());
            myList.setAdapter(new BookExpandableAdapter(this, category));
            dogamStatus = category.getStatus();
            rootNode = category.getRootNode();
            detail_text.setText(rootNode.getTitle() + "");

        }

//        CustomAdapter mAdapter = new CustomAdapter (getApplicationContext(), R.layout.cmtview_custom, myList, MainActivity.this);
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean onOptionsItemSelected(MenuItem item) {

        //menu.xml에서 지정한 item 이벤트 추가
        switch (item.getItemId()) {

            case R.id.bookDetailMenu: {

                bottomSheetDialog = new BottomSheetDialog(
                        BookDetailActivity.this, R.style.BottomSheetDialogTheme
                );
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

                sharingBtn = bottomSheetView.findViewById(R.id.detail_btn_sharing);
                sharingBtn.setOnClickListener(this);

                //도감 Status를 확인하여 비공유 도감(나의도감), 공유된 도감에 따른 버튼 view.
                switch (dogamStatus) {
                    case NonShare: //공유, 수정, 삭제
                        likeBtn.setVisibility(View.GONE);
                        cancelBtn.setVisibility(View.GONE);
                        sharingBtn.setVisibility(View.GONE);
                        break;
                    case Shared_Mutable:
                    case Shared_Immutable: //저장, 좋아요(좋아요취소)
                        shareBtn.setVisibility(View.GONE);
                        updateBtn.setVisibility(View.GONE);
                        cancelBtn.setVisibility(View.GONE);
                        deleteBtn.setVisibility(View.GONE);
                        if(isLiked) {
                            likeBtn.setText("좋아요 취소");
                        }
                        break;
                    case Sharing: // 수정, 취소, 삭제
                        likeBtn.setVisibility(View.GONE);
                        shareBtn.setVisibility(View.GONE);
                        sharingBtn.setVisibility(View.GONE);
                        break;
                    case Sharing_Mutable: // 수정, 삭제
                        shareBtn.setVisibility(View.GONE);
                        cancelBtn.setVisibility(View.GONE);
                        likeBtn.setVisibility(View.GONE);
                        sharingBtn.setVisibility(View.GONE);
                        break;
                    case Sharing_Immutable: //삭제
                        sharingBtn.setVisibility(View.GONE);
                        shareBtn.setVisibility(View.GONE);
                        updateBtn.setVisibility(View.GONE);
                        cancelBtn.setVisibility(View.GONE);
                        likeBtn.setVisibility(View.GONE);
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
                bottomSheetDialog.dismiss();

                break;

            case R.id.detail_btn_delete:
                bottomSheetDialog.dismiss();
                //TODO: 도감 삭제 후 BookManage 화면으로 전환
                delete();
                break;

            case R.id.detail_btn_share:
                //TODO: NewShareActivity로 화면 전환(도감 데이터 전달)
                Intent intent_share = new Intent(BookDetailActivity.this, NewShareActivity.class);
                intent_share.putExtra("target_category", category);
                startActivity(intent_share);
                bottomSheetDialog.dismiss();

                break;

            case R.id.detail_btn_like:
                Callable<Integer> likeCallable;
                //TODO: 공유도감 좋아요
                if(isLiked)
                    likeCallable = () -> shareService.updateLike(category.getId(), false);
                else
                    likeCallable = () -> shareService.updateLike(category.getId(), true);
                AsyncCallback<Integer> likeCallback = new AsyncCallback<Integer>() {
                    @Override
                    public void onResult(Integer result) {
                        if(result == 0) {
                            if (isLiked) {
                                Toast.makeText(getApplicationContext(), String.format("cancel like '%s'", category.getTitle()), Toast.LENGTH_SHORT).show();
                                likeBtn.setText("좋아요");
                                isLiked = false;
                            } else {
                                Toast.makeText(getApplicationContext(), String.format("like '%s'", category.getTitle()), Toast.LENGTH_SHORT).show();
                                likeBtn.setText("좋아요 취소");
                                isLiked = true;
                            }
                        }
                        else
                            Toast.makeText(getApplicationContext(), String.format("도감 %s 추가에 실패했습니다.", category.getTitle()), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void exceptionOccured(Exception e) {
                        Log.e("like error", e.toString());
                    }

                    @Override
                    public void cancelled() {

                    }
                };

                new AsyncExecutor<Integer>().setCallable(likeCallable).setCallback(likeCallback).execute();
                bottomSheetDialog.dismiss();
                break;

            case R.id.detail_btn_cancel:
                //TODO: PASSWORD 확인 후 공유도감 삭제(공유취소)
                if(category.getPassword() != "") {
                    EditText edittext = new EditText(this);
                    edittext.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("공유도감 삭제");
                    builder.setMessage("도감의 비밀번호를 입력하세요.");
                    builder.setView(edittext);
                    builder.setPositiveButton("입력",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    if (edittext.getText().toString().equals(category.getPassword())) {
                                        cancelShare();
                                    }
                                    else
                                        Toast.makeText(getApplicationContext(), "잘못된 비밀번호입니다.", Toast.LENGTH_SHORT).show();
                                }
                            });
                    builder.setNegativeButton("취소",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    builder.show();
                } else {
                    cancelShare();
                }
            break;

            case R.id.detail_btn_sharing:
                //TODO: 도감 공유 받기
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("공유 도감 저장");
                if(category.getStatus() == DogamStatus.Shared_Immutable)
                    builder.setMessage("해당 도감은 수정이 불가능합니다.\n 저장하시겠습니까?");
                else
                    builder.setMessage("해당 도감은 수정이 가능합니다.\n 저장하시겠습니까?");
                builder.setPositiveButton("저장", (dialog, which) -> {
                    create();
                });
                builder.setNegativeButton("취소", (dialog, which) -> {
                   dialog.dismiss();
                });
                builder.show();
                break;
            case R.id.detail_btn_back:
                bottomSheetDialog.dismiss();
                break;

        }
    }

    private void create() {
        Callable<String> callable = () ->  {
            String result = categoryService.createCategory(category);
            int dogamId = Integer.parseInt(result);
            CategoryDto foundCategory = categoryService.findCategoryById(dogamId);
            for(int i = 0; i < foundCategory.getRootNode().getLowLayer().size(); i++) {
                CategoryNodeDto secondNode = foundCategory.getRootNode().getLowLayer().get(i);
                if(!category.getRootNode().getLowLayer().get(i).getPosts().isEmpty()) {
                    for(PostDto postDto : category.getRootNode().getLowLayer().get(i).getPosts())
                        result = postService.createPost(postDto, secondNode.getId(), dogamId);
                }
                for(int j = 0; j < foundCategory.getRootNode().getLowLayer().get(i).getLowLayer().size(); j++) {
                    CategoryNodeDto thirdNode = secondNode.getLowLayer().get(j);
                    if(!category.getRootNode().getLowLayer().get(i).getLowLayer().get(j).getPosts().isEmpty()) {
                        for(PostDto postDto : category.getRootNode().getLowLayer().get(i).getLowLayer().get(j).getPosts())
                            result = postService.createPost(postDto, thirdNode.getId(), dogamId);
                    }
                }
            }

            return result;
        };
        AsyncCallback<String> callback = new AsyncCallback<String>() {
            @Override
            public void onResult(String result) {
                Intent intent1 = new Intent(BookDetailActivity.this, BookManageActivity.class);
                startActivity(intent1);
                Log.d("create category", result);
            }

            @Override
            public void exceptionOccured(Exception e) {
                e.printStackTrace();
            }

            @Override
            public void cancelled() {

            }
        };
        View.OnClickListener positiveListener = v -> {
            new AsyncExecutor<String>().setCallable(callable).setCallback(callback).execute();
        };

        View.OnClickListener negativeListener = v -> {
            customDialog.dismiss();
        };
        customDialog = new CustomDialog(this, positiveListener,negativeListener,
                "도감 저장","'" + category.getTitle() + "' 도감을 저장하시겠습니까?");
        customDialog.setCancelable(true);
        customDialog.setCanceledOnTouchOutside(true);
        customDialog.show();
    }

    private void delete() {
        Callable<String> callable = () -> categoryService.deleteDogam(category.getId());

        View.OnClickListener positiveListener = new View.OnClickListener() {
            public void onClick(View v) {
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
                Toast.makeText(getApplicationContext(), "'"+ category.getTitle() +"' 도감이 정상적으로 삭제되었습니다.", Toast.LENGTH_SHORT).show();
            }
        };
        // 취소버튼 리스너
        View.OnClickListener negativeListener = new View.OnClickListener() {
            public void onClick(View v) {
                customDialog.dismiss();
            }
        };
        customDialog = new CustomDialog(this, positiveListener,negativeListener,
                "도감 삭제","'" + category.getTitle() + "' 도감을 삭제하시겠습니까?");
        customDialog.setCancelable(true);
        customDialog.setCanceledOnTouchOutside(true);
        customDialog.show();
    }

    private void cancelShare() {
        Callable<String> callable = () -> shareService.deleteDogam(category.getId(), category.getSharedDogamId());
        AsyncCallback<String> callback = new AsyncCallback<String>() {
            @Override
            public void onResult(String result) {
                Intent intent1 = new Intent(BookDetailActivity.this, BookManageActivity.class);
                startActivity(intent1);
                Log.d("delete result", result);
                if(Integer.parseInt(result) == 0)
                    Toast.makeText(getApplicationContext(), "'"+ category.getTitle() +"' 도감이 정상적으로 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "도감 삭제 오류", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void exceptionOccured(Exception e) {

            }

            @Override
            public void cancelled() {

            }
        };

        new AsyncExecutor<String>().setCallable(callable).setCallback(callback).execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
