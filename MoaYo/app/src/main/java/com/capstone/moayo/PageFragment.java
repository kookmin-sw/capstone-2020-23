package com.capstone.moayo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class PageFragment extends Fragment {

    private String[] book_info;


    public static PageFragment create(String[] book) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putStringArray("book", book);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        book_info = getArguments().getStringArray("book");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.page, container, false);


        for(int i = 1; i <= book_info.length; ++i) {
            int bookID = getResources().getIdentifier("bookBtn" + i, "id", getContext().getPackageName());
            Button bookBtnView = (Button) rootView.findViewById(bookID);
            bookBtnView.setText(book_info[i-1]);
        }



        return rootView;
    }
}

