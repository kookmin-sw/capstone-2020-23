package com.capstone.moayo;

import android.app.ActionBar;
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
import com.capstone.moayo.entity.Keyword;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class FormEditFragment extends Fragment implements BottomSheetFragment.OnAddKeywordListener{

    public interface OnChangeFormListener{
        void onChangeForm(int frag_id);
    }

    private View view;
    private ArrayList<Keyword> items;
    private FormListAdapter adapter;
    private ListView listView;
    private Button add_btn;
    private OnChangeFormListener cfListener;
    private BottomSheetFragment bottomSheet;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            cfListener = (OnChangeFormListener) context;
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

        items = getListData();

        adapter = new FormListAdapter(getContext(), items);
        listView = (ListView) view.findViewById(R.id.form_list_view);
        listView.setAdapter(adapter);

//        String title = this.getArguments().getString("title");
//        ActionBar actionBar = ((BookFormActivity)getActivity()).getActionBar();
//        if(title != null) {
//            actionBar.setTitle(title);
//        }



        add_btn = (Button) view.findViewById(R.id.add_keyword_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input_et = (EditText) view.findViewById(R.id.input_keyword);

                String word = input_et.getText().toString();        // EditText에 입력된 문자열값을 얻기

                if (!word.isEmpty()) {                        // 입력된 text 문자열이 비어있지 않으면
//                    Keyword add_word = new Keyword(word);
//                    items.add(add_word);                          // items 리스트에 입력된 문자열 추가
                    input_et.setText("");                           // EditText 입력란 초기화
                    adapter.notifyDataSetChanged();           // 리스트 목록 갱신

                    Bundle args = new Bundle();
                    args.putString("keyword", word);
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
        bottomSheet.setOnAddKeywordListener(this);
        bottomSheet.setArguments(args);
        bottomSheet.show(getFragmentManager(),"bottomSheet");
    }

    public void onAddKeyword(Keyword add_word) {
        this.items.add(add_word);
        adapter.notifyDataSetChanged();
//        Toast.makeText(getContext(), "good job "+word, Toast.LENGTH_SHORT).show();
    }


    private ArrayList<Keyword> getListData() {
        ArrayList<Keyword> list = new ArrayList<>();

        Keyword key1 = new Keyword("가수");
        Keyword key2 = new Keyword("싱어");
        Keyword key3 = new Keyword("뮤지션");
        Keyword key4 = new Keyword("노래");

        list.add(key1);
        list.add(key2);
        list.add(key3);
        list.add(key4);

        return list;

    }
}

