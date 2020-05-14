package com.capstone.moayo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.capstone.moayo.adapter.FormListAdapter;
import com.capstone.moayo.entity.Category;
import com.capstone.moayo.entity.CategoryNode;
//import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class FormEditFragment extends Fragment implements BottomSheetFragment.OnAddNodeListener{

    public interface OnChangeLevelListener{
        void onChangeLevel(int fragId, CategoryNode selectedNode);
    }

    private View view;
    private ArrayList<CategoryNode> items;
    private FormListAdapter adapter;
    private ListView listView;
    private Button add_btn, back_btn;
    private OnChangeLevelListener lvl_callback;
    private BottomSheetFragment bottomSheet;
    private CategoryNode currentNode;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            lvl_callback = (OnChangeLevelListener) context;
        }catch(ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement OnChangeBodyListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_form_edit, container, false);

        if (getArguments() != null) {
            currentNode = (CategoryNode) getArguments().getSerializable("currentNode");
            Toast.makeText(getContext(), currentNode.getTitle(), Toast.LENGTH_SHORT).show();

            items = (ArrayList<CategoryNode>) currentNode.getLowLayer();
        }

        adapter = new FormListAdapter(getContext(), items);
        listView = (ListView) view.findViewById(R.id.form_list_view);
        listView.setAdapter(adapter);

        back_btn = (Button) view.findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BookFormActivity)getActivity()).onChangeLevel(currentNode.getLevel(), currentNode.getParent());
            }
        });

        add_btn = (Button) view.findViewById(R.id.add_keyword_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input_et = (EditText) view.findViewById(R.id.input_keyword);

                String word = input_et.getText().toString();        // EditText에 입력된 문자열값을 얻기

                if (!word.isEmpty()) {                        // 입력된 text 문자열이 비어있지 않으면
                    input_et.setText("");                           // EditText 입력란 초기화

                    Bundle args = new Bundle();
                    args.putString("keyword", word);
                    args.putSerializable("parentNode", currentNode);
                    showDialog(args);
                } else {
                    Toast.makeText(getContext(), "키워드를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public void showDialog(Bundle args) {
        bottomSheet = BottomSheetFragment.getInstance();
        bottomSheet.setOnAddNodeListener(this);
        bottomSheet.setArguments(args);
        bottomSheet.show(getFragmentManager(),"bottomSheet");
    }

    public void onAddNode(CategoryNode newNode) {
        currentNode = ((BookFormActivity)getActivity()).addNode(newNode);
        items = (ArrayList<CategoryNode>) currentNode.getLowLayer();
        adapter.notifyDataSetChanged();

//        if(category != null) {
////            this.items.add(newNode);
//            CategoryNode tempNode = (CategoryNode) category.getSelectCategoryNode();
//            tempNode.addLowLayer(newNode);
//            category.setSelectCategoryNode(tempNode);
//            refreshItem();
//        } else {
//            //show error log
//        }
    }

//    public void refreshItem() {
//        items = (ArrayList<CategoryNode>) category.getSelectCategoryNode().getLowLayer();
//        adapter.notifyDataSetChanged();
//    }

}

