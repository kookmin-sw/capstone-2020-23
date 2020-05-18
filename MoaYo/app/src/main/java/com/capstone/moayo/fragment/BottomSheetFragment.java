package com.capstone.moayo.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.capstone.moayo.R;
import com.capstone.moayo.entity.CategoryNode;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class BottomSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener {
    public static BottomSheetFragment getInstance() { return new BottomSheetFragment(); }

    public interface OnEditNodeListener {
        void onAddNode(CategoryNode node);
        void onRemoveNode(CategoryNode node);
    }
    private CategoryNode node;
    private CategoryNode parentNode;
    private String word;
    private Button cancel_btn, save_btn;
    private ImageButton delete_btn;
    private TextView keyword;
    private OnEditNodeListener callback;

    private String FORM_MODE;

    private ListView listview;
    private ArrayAdapter adapter;
    private ArrayList<String> tags;

    public void setOnEditNodeListener(OnEditNodeListener callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final BottomSheetDialog d = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        // view hierarchy is inflated after dialog is shown
        d.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                //Disables outside touch
                d.getWindow().findViewById(R.id.touch_outside).setOnClickListener(null);
                //Prevents dragging behavior
                View content = d.getWindow().findViewById(R.id.design_bottom_sheet);
                ((CoordinatorLayout.LayoutParams) content.getLayoutParams()).setBehavior(null);
            }
        });
        return d;
    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            View bottomSheet = dialog.findViewById(R.id.design_bottom_sheet);
            bottomSheet.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT; //setting full size Dialog.
        }
//        final View view = getView();
//        view.post(new Runnable() {
//            @Override
//            public void run() {
//                View parent = (View) view.getParent();
//                CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) (parent).getLayoutParams();
//                CoordinatorLayout.Behavior behavior = params.getBehavior();
//                BottomSheetBehavior bottomSheetBehavior = (BottomSheetBehavior) behavior;
//                bottomSheetBehavior.setPeekHeight(0);
//                parent.setBackgroundColor(Color.WHITE);
//            }
//        });
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_hashtag_form, container,false);

        keyword = (TextView) view.findViewById(R.id.dialog_tag_tv_keyword);
        cancel_btn = (Button) view.findViewById(R.id.dialog_tag_btn_cancel);
        save_btn = (Button) view.findViewById(R.id.dialog_tag_btn_save);
        delete_btn = (ImageButton) view.findViewById(R.id.dialog_tag_btn_delete);

        FORM_MODE = getArguments().getString("MODE");
        parentNode = (CategoryNode) getArguments().getSerializable("parentNode");


        switch (FORM_MODE) {
            case "ADD":
                word = getArguments().getString("keyword");
                keyword.setText(word);
                delete_btn.setVisibility(View.INVISIBLE);
                break;
            case "EDIT":
                node = (CategoryNode) getArguments().getSerializable("selectedNode");
                keyword.setText(node.getTitle());
                break;
        }

        //검색된 연관 키워드들을 ArrayList에 담아 ListView에 보여줌.
        tags = new ArrayList<String>();
        tags.add("hashtag1");
        tags.add("hashtag2");
        tags.add("hashtag3");
        tags.add("hashtag4");
        tags.add("hashtag5");
        tags.add("hashtag6");
        tags.add("hashtag7");
        tags.add("hashtag8");
        tags.add("hashtag10");
        tags.add("hashtag9");
        tags.add("hashtag9");
        tags.add("hashtag9");
        tags.add("hashtag9");



        adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_multiple_choice, tags);
        listview = (ListView) view.findViewById(R.id.dialog_tag_lv_hashtags);
        listview.setAdapter(adapter);

        cancel_btn.setOnClickListener(this);
        save_btn.setOnClickListener(this);
        delete_btn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_tag_btn_cancel:
                dismiss();
                break;

            case R.id.dialog_tag_btn_save:
                if(FORM_MODE == "ADD") {
                    add();
                } else {
                    edit();
                }
                dismiss();
                break;
            case R.id.dialog_tag_btn_delete:
                delete();
                dismiss();
                break;
        }
    }

//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                if (Build.VERSION.SDK_INT < 16) {
//                    view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                } else {
//                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                }
//                BottomSheetDialog dialog = (BottomSheetDialog) getDialog();
//                FrameLayout bottomSheet = (FrameLayout)
//                        dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
//                BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
//                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//                behavior.setPeekHeight(0); // Remove this line to hide a dark background if you manually hide the dialog.
//            }
//        });
//    }


    private void add() {
        node = new CategoryNode(word, parentNode, parentNode.getLevel()+1);

        //node 객체에 선택된 해시태그들을 담음
        ArrayList<String> hashtags = new ArrayList<>();
        SparseBooleanArray checkedTags = listview.getCheckedItemPositions();
        for (int i = adapter.getCount()-1; i >= 0; i--) {
            if (checkedTags.get(i)) {
                hashtags.add(tags.get(i));
            }
        }
        node.setHashtags((List) hashtags);
//        Log.d("node_tags", node.getHashtags().toString());
        listview.clearChoices(); // 모든 선택상태 초기화.

        callback.onAddNode(node);
    }

    private void edit() {

    }

    private void delete() {
        callback.onRemoveNode(node);
    }


}

