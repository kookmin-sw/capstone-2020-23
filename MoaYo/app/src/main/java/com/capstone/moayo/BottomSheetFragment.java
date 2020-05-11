package com.capstone.moayo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

//import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class BottomSheetFragment extends BottomSheetDialogFragment {
    public static BottomSheetFragment getInstance() { return new BottomSheetFragment(); }

    private Button cancel_btn, add_btn;
    private TextView keyword;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_sheet, container,false);

        //추가하려는 키워드
        keyword = (TextView) view.findViewById(R.id.title_keyword);
        keyword.setText(getArguments().getString("keyword"));


        //검색된 연관 키워드들을 ArrayList에 담아 ListView에 보여줌.
        final ArrayList<String> tags = new ArrayList<String>();
        tags.add("hashtag1");
        tags.add("hashtag2");
        tags.add("hashtag3");
        final ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_multiple_choice, tags);

        final ListView listview = (ListView) view.findViewById(R.id.hashtag_list);
        listview.setAdapter(adapter);

        cancel_btn = (Button) view.findViewById(R.id.cancel_btn);
        cancel_btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

//        add_btn = (Button) view.findViewById(R.id.add_btn);
//        add_btn.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        return view;
    }


}

