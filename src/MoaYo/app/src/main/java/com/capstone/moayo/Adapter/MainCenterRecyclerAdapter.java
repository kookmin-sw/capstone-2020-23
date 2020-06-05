package com.capstone.moayo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.capstone.moayo.R;
import com.capstone.moayo.activity.BookDetailActivity;
import com.capstone.moayo.service.dto.CategoryDto;

import java.util.ArrayList;
import java.util.Collections;

public class MainCenterRecyclerAdapter extends RecyclerView.Adapter<MainCenterRecyclerAdapter.ViewHolder> {

    private ArrayList<CategoryDto> sharedBooks = new ArrayList<>();

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView sharedBookPost;
        TextView nickName, comment;
        ImageButton like;
        TextView likeCount;

        ViewHolder(View itemView) {
            super(itemView);

            sharedBookPost = itemView.findViewById(R.id.sharedBookPost);
            nickName = itemView.findViewById(R.id.nickName);
            comment = itemView.findViewById(R.id.comment);

            like = itemView.findViewById(R.id.like);
            likeCount = itemView.findViewById(R.id.likeCount);

            like.setOnClickListener(new View.OnClickListener() {
                int count = 0;
                @Override
                public void onClick(View v) {
                    like.setSelected(true);
                    count ++ ;
                    likeCount.setText(count+"");
                }
            });
        }
    }
    @NonNull
    @Override
    public MainCenterRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i){

        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.recycler_main_center, parent, false) ;
        MainCenterRecyclerAdapter.ViewHolder vh = new MainCenterRecyclerAdapter.ViewHolder(view) ;

        return vh ;
    }

    @Override
    public void onBindViewHolder(@NonNull MainCenterRecyclerAdapter.ViewHolder vh, int position) {

        CategoryDto item = sharedBooks.get(position);

        Glide.with(vh.itemView.getContext())
            .load(item.getUrl())
            .into(vh.sharedBookPost);

        vh.nickName.setText(item.getTitle());
        vh.comment.setText(item.getDescription());

        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BookDetailActivity.class);
                // intent에 CategoryNode 객체를 담아 DetailActivty로 전달함.
                intent.putExtra("category", item);
                v.getContext().startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return sharedBooks.size();
    }

    public void setItems(ArrayList<CategoryDto> items) {
        Collections.reverse(items);
        this.sharedBooks = items;
    }
}
