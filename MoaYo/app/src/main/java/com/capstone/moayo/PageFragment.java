package com.capstone.moayo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

public class PageFragment extends Fragment {

    private String book_info;


    public static PageFragment create(String book) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putString("book", book);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        book_info = getArguments().getString("book");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.page, container, false);

        TextView bookView = (TextView) rootView.findViewById(R.id.title);
        bookView.setText(book_info);
        return rootView;
    }
}
