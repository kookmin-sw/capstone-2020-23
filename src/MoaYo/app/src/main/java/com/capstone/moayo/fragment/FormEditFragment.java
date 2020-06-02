package com.capstone.moayo.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.capstone.moayo.activity.BookDetailActivity;
import com.capstone.moayo.activity.BookFormActivity;
import com.capstone.moayo.R;
import com.capstone.moayo.adapter.FormListAdapter;
import com.capstone.moayo.service.dto.CategoryNodeDto;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;

public class FormEditFragment extends Fragment implements BottomSheetFragment.OnEditNodeListener, View.OnClickListener{

    public interface OnChangeLevelListener{
        void onChangeLevel(int fragId, CategoryNodeDto selectedNode);
    }

    private View view;
    private ArrayList<CategoryNodeDto> items;
    private FormListAdapter adapter;
    private ListView listView;
    private Button add_btn, save_btn;
    private ImageButton back_btn;
    private ImageView arrow_form;
    private TextView form_text, form_text2, form_text3;
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
                Bundle args = new Bundle();
                args.putString("MODE", "EDIT");
                args.putSerializable("parentNode", currentNode);
                args.putSerializable("selectedNode", items.get(position));
                showDialog(args);
            }
        });

        back_btn = (ImageButton) view.findViewById(R.id.back_btn);
        add_btn = (Button) view.findViewById(R.id.add_keyword_btn);
        EditText input_edit = (EditText) view.findViewById(R.id.input_keyword);
        form_text = (TextView) view.findViewById(R.id.form_text);
        form_text2 = (TextView) view.findViewById(R.id.form_text2);
        form_text3 = (TextView) view.findViewById(R.id.form_text3);
        arrow_form = (ImageView) view.findViewById(R.id.arrow_form);

        back_btn.setOnClickListener(this);
        add_btn.setOnClickListener(this);

        setText(currentNode,currentNode.getLevel());

        input_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                if (text.length() > 0) setNextMode(true);
                else setNextMode(false);

            }
            @Override
            public void afterTextChanged(Editable s) {}
        });


        return view;
    }

    public void onResume() {
        super.onResume();
        ((BookFormActivity) getActivity())
                .setToolbarContent(currentNode,currentNode.getLevel());
        // Set title bar

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_btn:
                ((BookFormActivity)getActivity()).onChangeLevel(currentNode.getLevel(), currentNode.getParent());
                break;

            case R.id.add_keyword_btn:
                MaterialEditText input_et = (MaterialEditText) view.findViewById(R.id.input_keyword);
                String word = input_et.getText().toString();        // EditText에 입력된 문자열값을 얻기
                if (!word.isEmpty()) {                        // 입력된 text 문자열이 비어있지 않으면
                    input_et.setText("");                           // EditText 입력란 초기화
                    Bundle args = new Bundle();
                    args.putString("MODE", "ADD");
                    args.putString("keyword", word);
                    args.putSerializable("parentNode", currentNode);
                    showDialog(args);
                } else { input_et.setError("입력되지 않았습니다."); }
                break;

            case R.id.bookSave:
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

    public void onSetNode(CategoryNodeDto node) {
        currentNode = ((BookFormActivity)getActivity()).setNode(node);
        items = (ArrayList<CategoryNodeDto>) currentNode.getLowLayer();
        adapter.notifyDataSetChanged();
    }

    public void onRemoveNode(CategoryNodeDto node) {
        currentNode = ((BookFormActivity)getActivity()).removeNode(node);
        items = (ArrayList<CategoryNodeDto>) currentNode.getLowLayer();
        adapter.notifyDataSetChanged();
    }

    private void setNextMode(boolean flag) {
        if (flag) {
            add_btn.setBackgroundColor(Color.parseColor("#663399"));
            add_btn.setTextColor(Color.parseColor("#ffffff"));
        }
        else{
            add_btn.setBackgroundColor(Color.parseColor("#e5e5e5"));
            add_btn.setTextColor(Color.parseColor("#707070"));
        }
    }

    public void setText(CategoryNodeDto currentNode, int level) {
        if(currentNode != null) this.currentNode = currentNode;

        form_text.setText("");
        form_text2.setText("");
        form_text3.setText("");

        switch (level) {
            case 1:
                form_text.setText(currentNode.getTitle());
                form_text2.setText(" 도감 목록 수정");
                form_text2.setTextColor(Color.parseColor("#663399"));
                arrow_form.setVisibility(View.INVISIBLE);

                break;
            case 2:
                form_text.setText(currentNode.getParent().getTitle());
                form_text2.setText(currentNode.getTitle());
                form_text3.setText(" 도감 목록 수정");
                arrow_form.setVisibility(View.VISIBLE);
                break;
        }
    }


}

