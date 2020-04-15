package com.capstone.moayo;

//import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;


import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class PageFragment extends Fragment implements OnClickListener {

    private ArrayList<CategoryNode> book_list;


    public static PageFragment create(ArrayList book) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putSerializable("key", book);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        book_list= (ArrayList<CategoryNode>) getArguments().getSerializable("key");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.page, container, false);


        for(int i = 1; i <= book_list.size(); ++i) {
            int bookID = getResources().getIdentifier("bookBtn" + i, "id", getContext().getPackageName());
            Button bookBtnView = (Button) rootView.findViewById(bookID);
            bookBtnView.setText(book_list.get(i-1).title);

            bookBtnView.setOnClickListener(this);
        }

        return rootView;
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
    
        Intent intent = new Intent(getActivity(), BookDetailActivity.class);
        startActivity(intent);

    }
}

