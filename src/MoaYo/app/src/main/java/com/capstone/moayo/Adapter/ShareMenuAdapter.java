package com.capstone.moayo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.capstone.moayo.R;
import com.capstone.moayo.activity.BookDetailActivity;
import com.capstone.moayo.service.dto.CategoryDto;

import java.util.ArrayList;

public class ShareMenuAdapter extends RecyclerView.Adapter<ShareMenuAdapter.ViewHolder> {

    private ArrayList<CategoryDto> sharedBooks = new ArrayList<>();

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView sharedBookPost;
        TextView nickName, comment;

        ViewHolder(View itemView) {
            super(itemView);

            sharedBookPost = itemView.findViewById(R.id.sharedBookPost);
            nickName = itemView.findViewById(R.id.nickName);
            comment = itemView.findViewById(R.id.comment);
        }
    }
    @NonNull
    @Override
    public ShareMenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i){

        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.recycler_share_menu, parent, false);
        ShareMenuAdapter.ViewHolder vh = new ShareMenuAdapter.ViewHolder(view);

        return vh ;
    }

    @Override
    public void onBindViewHolder(@NonNull ShareMenuAdapter.ViewHolder vh, int position) {

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
        this.sharedBooks = items;
    }
}
