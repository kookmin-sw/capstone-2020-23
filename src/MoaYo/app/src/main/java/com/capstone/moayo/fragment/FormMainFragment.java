package com.capstone.moayo.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.capstone.moayo.activity.BookFormActivity;
import com.capstone.moayo.R;
import com.capstone.moayo.service.dto.CategoryNodeDto;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class FormMainFragment extends Fragment {

    private View view;
    private Button start_btn;
    private CategoryNodeDto rootNode;
    private EditText title_et;
    private ImageView bookform_logo;
    private TextInputLayout tl;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onResume(){
        super.onResume();
        // Set title bar
        ((BookFormActivity) getActivity())
                .setToolbarContent(null,0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_form_main, container, false);

        title_et = (EditText) view.findViewById(R.id.create_book_title);
        bookform_logo = (ImageView) view.findViewById(R.id.bookformImg);
        tl = (TextInputLayout)view.findViewById(R.id.title_layout);

        title_et.addTextChangedListener(new TextWatcher() {
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

        start_btn = (Button) view.findViewById(R.id.start_form_btn);
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = title_et.getText().toString();

                if(!title.isEmpty()) {
                    ((BookFormActivity)getActivity()).initRootNode(title);
                    ((BookFormActivity)getActivity()).onChangeLevel(2, null);
                } else { tl.setError("도감 명이 입력되지 않았습니다."); }

            }
        });

        rootNode = ((BookFormActivity)getActivity()).getRootNode();
        if(rootNode != null) {
            title_et.setText(rootNode.getTitle());
            setNextMode(true);
        }

        return view;
    }

    private void setNextMode(boolean flag) {
        if (flag) {
            tl.setError(null);
            start_btn.setBackgroundColor(Color.parseColor("#6200EE"));
            start_btn.setTextColor(Color.parseColor("#ffffff"));
            bookform_logo.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.logo_primary));
        } else {
            start_btn.setBackgroundColor(Color.parseColor("#F2F2F2"));
            start_btn.setTextColor(Color.parseColor("#707070"));
            bookform_logo.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.logo_gray));
        }
    }

}
