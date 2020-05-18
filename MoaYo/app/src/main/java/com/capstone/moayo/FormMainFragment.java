package com.capstone.moayo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_form_main, container, false);

        start_btn = (Button) view.findViewById(R.id.start_form_btn);
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText title_et = (EditText) view.findViewById(R.id.create_book_title);
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
