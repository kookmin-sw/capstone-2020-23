package com.capstone.moayo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.capstone.moayo.activity.BookFormActivity;
import com.capstone.moayo.R;
import com.capstone.moayo.adapter.FormListAdapter;
import com.capstone.moayo.entity.CategoryNode;
import com.capstone.moayo.service.dto.CategoryNodeDto;

import java.util.ArrayList;

public class FormEditFragment extends Fragment implements BottomSheetFragment.OnEditNodeListener, View.OnClickListener{

    public interface OnChangeLevelListener{
        void onChangeLevel(int fragId, CategoryNodeDto selectedNode);
    }

    private View view;
    private ArrayList<CategoryNodeDto> items;
    private FormListAdapter adapter;
    private ListView listView;
    private Button add_btn, back_btn, save_btn;
    private OnChangeLevelListener lvl_callback;
    private BottomSheetFragment bottomSheet;
    private CategoryNodeDto currentNode;

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
            currentNode = (CategoryNodeDto) getArguments().getSerializable("currentNode");
            items = (ArrayList<CategoryNodeDto>) currentNode.getLowLayer();
        }

        adapter = new FormListAdapter(getContext(), items);
        listView = (ListView) view.findViewById(R.id.form_list_view);
        listView.setAdapter(adapter);

        //onclick event
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getContext(), items.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                Bundle args = new Bundle();
                args.putString("MODE", "EDIT");
                args.putSerializable("parentNode", currentNode);
                args.putSerializable("selectedNode", items.get(position));
                showDialog(args);
            }
        });

        back_btn = (Button) view.findViewById(R.id.back_btn);
        add_btn = (Button) view.findViewById(R.id.add_keyword_btn);
        save_btn = (Button) view.findViewById(R.id.save_btn);

        back_btn.setOnClickListener(this);
        add_btn.setOnClickListener(this);
        save_btn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_btn:
                ((BookFormActivity)getActivity()).onChangeLevel(currentNode.getLevel(), currentNode.getParent());
                break;

            case R.id.add_keyword_btn:
                EditText input_et = (EditText) view.findViewById(R.id.input_keyword);
                String word = input_et.getText().toString();        // EditText에 입력된 문자열값을 얻기
                if (!word.isEmpty()) {                        // 입력된 text 문자열이 비어있지 않으면
                    input_et.setText("");                           // EditText 입력란 초기화

                    Bundle args = new Bundle();
                    args.putString("MODE", "ADD");
                    args.putString("keyword", word);
                    args.putSerializable("parentNode", currentNode);
                    showDialog(args);
                } else { Toast.makeText(getContext(), "키워드를 입력해주세요.", Toast.LENGTH_SHORT).show(); }
                break;

            case R.id.save_btn:
                ((BookFormActivity)getActivity()).onSubmit();
                break;
        }
    }

    public void showDialog(Bundle args) {
        bottomSheet = BottomSheetFragment.getInstance();
        bottomSheet.setOnEditNodeListener(this);
        bottomSheet.setArguments(args);
        bottomSheet.show(getFragmentManager(),"bottomSheet");
    }

    public void onAddNode(CategoryNodeDto node) {
        currentNode = ((BookFormActivity)getActivity()).addNode(node);
        items = (ArrayList<CategoryNodeDto>) currentNode.getLowLayer();
        adapter.notifyDataSetChanged();
    }

    public void onRemoveNode(CategoryNodeDto node) {
        currentNode = ((BookFormActivity)getActivity()).removeNode(node);
        items = (ArrayList<CategoryNodeDto>) currentNode.getLowLayer();
        adapter.notifyDataSetChanged();
    }


}

