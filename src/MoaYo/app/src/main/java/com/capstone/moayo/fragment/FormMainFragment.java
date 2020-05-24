package com.capstone.moayo.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class FormMainFragment extends Fragment {

    private View view;
    private Button start_btn;

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
                .setText("도감 생성",null,null, null);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_form_main, container, false);

        EditText title_et = (EditText) view.findViewById(R.id.create_book_title);
        ImageView bookform_logo = (ImageView) view.findViewById(R.id.bookformImg);
        ImageView create_text = (ImageView) view.findViewById(R.id.create);
        ImageView guide = (ImageView) view.findViewById(R.id.guide);
        TextView guide_text = (TextView) view.findViewById(R.id.guide_text);

        title_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                if (text.length() > 0) {
                    title_et.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.edit_primary));
                    start_btn.setBackgroundColor(Color.parseColor("#EFE5FD"));
                    start_btn.setTextColor(Color.parseColor("#6200EE"));
                    bookform_logo.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.logo_primary));
                    create_text.setVisibility(View.VISIBLE);
                    guide.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.guied_text_primary));
                    guide_text.setText("버튼을 눌러 다음 단계로!");
                    guide_text.setTextColor(Color.parseColor("#6200EE"));

                }
                else{
                    title_et.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.edit_gray));
                    start_btn.setBackgroundColor(Color.parseColor("#ffffff"));
                    start_btn.setTextColor(Color.parseColor("#707070"));
                    bookform_logo.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.logo_gray));
                    create_text.setVisibility(View.GONE);
                    guide.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.guide_text_gray));
                    guide_text.setText("도감의 이름이 됩니다.");
                    guide_text.setTextColor(Color.parseColor("#000000"));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        start_btn = (Button) view.findViewById(R.id.start_form_btn);
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = title_et.getText().toString();

                if(!title.isEmpty()) {
                    ((BookFormActivity)getActivity()).initRootNode(title);
                    ((BookFormActivity)getActivity()).onChangeLevel(2, null);
                } else {
                    Toast.makeText(getContext(), "도감 명을 입력해주세요..!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }



}
