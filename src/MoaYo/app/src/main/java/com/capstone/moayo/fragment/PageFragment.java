package com.capstone.moayo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.capstone.moayo.activity.BookDetailActivity;
import com.capstone.moayo.R;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.util.DogamStatus;;

import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;



public class PageFragment extends Fragment implements OnClickListener {

    private ArrayList<CategoryDto> book_list;

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
        book_list= (ArrayList<CategoryDto>) getArguments().getSerializable("key");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.page, container, false);


//        해당 페이지의 담긴 도감만큼 버튼을 초기화, 남는 버튼은 Invisible 설정.
        for(int i = 1; i <= 9; ++i) {

            //textView
            int bookID = getResources().getIdentifier("bookBtn" + i, "id", getContext().getPackageName());
            TextView bookBtnView = (TextView) rootView.findViewById(bookID);

            //cicleImageView
            int ImageID = getResources().getIdentifier("myBookPost" +i ,"id",getContext().getPackageName());
            CircleImageView circleImageView = (CircleImageView) rootView.findViewById(ImageID);

            int ic_sharing = getResources().getIdentifier("sharing_status" +i ,"id",getContext().getPackageName());
            int ic_shared = getResources().getIdentifier("shared_status" +i ,"id",getContext().getPackageName());

            ImageView sharing_img = (ImageView) rootView.findViewById(ic_sharing);
            ImageView shared_img = (ImageView) rootView.findViewById(ic_shared);
            sharing_img.setVisibility(View.GONE);
            shared_img.setVisibility(View.GONE);

            if (i <= book_list.size()) {
                final CategoryDto book = book_list.get(i-1);
                bookBtnView.setText((String)book.getTitle());
                bookBtnView.setTag(book);
                Glide.with(getContext()).load(book.getUrl()).into(circleImageView);

                switch (book.getStatus()) {
                    case Sharing:
                        sharing_img.setVisibility(View.VISIBLE);
                        shared_img.setVisibility(View.GONE);
                        break;
                    case Sharing_Immutable:
                    case Sharing_Mutable:
                        sharing_img.setVisibility(View.GONE);
                        shared_img.setVisibility(View.VISIBLE);
                        break;
                    default:
                        sharing_img.setVisibility(View.GONE);
                        shared_img.setVisibility(View.GONE);
                        break;

                }

                circleImageView.setTag(book_list.get(i-1));
                circleImageView.setOnClickListener(this);
            } else {
                bookBtnView.setVisibility(View.INVISIBLE);
                circleImageView.setVisibility(View.INVISIBLE);
                sharing_img.setVisibility(View.GONE);
                shared_img.setVisibility(View.GONE);
            }
        }

        return rootView;
    }
    @Override
    public void onClick(View v) {
        v.setSelected(true);
        // Button View Tag 값으로 저장된 카테고리 객체를 가져옴.

        CategoryDto category = (CategoryDto) v.getTag();

        Intent intent = new Intent(getActivity(), BookDetailActivity.class);
        // intent에 CategoryNode 객체를 담아 DetailActivty로 전달함.
        intent.putExtra("category", category);
        intent.putExtra("categoryList", book_list);
        startActivity(intent);
        v.setSelected(false);


    }
}

