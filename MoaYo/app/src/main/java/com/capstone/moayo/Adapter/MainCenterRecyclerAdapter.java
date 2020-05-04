package com.capstone.moayo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.capstone.moayo.R;
import com.capstone.moayo.model.SharedBook;

import java.util.ArrayList;

public class MainCenterRecyclerAdapter extends RecyclerView.Adapter<MainCenterRecyclerAdapter.ViewHolder> {

    private ArrayList<SharedBook> sharedBooks = new ArrayList<>();

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
    public MainCenterRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i){

        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.main_center_recycler, parent, false) ;
        MainCenterRecyclerAdapter.ViewHolder vh = new MainCenterRecyclerAdapter.ViewHolder(view) ;

        return vh ;
    }

    @Override
    public void onBindViewHolder(@NonNull MainCenterRecyclerAdapter.ViewHolder vh, int position) {

        SharedBook item = sharedBooks.get(position);

        Glide.with(vh.itemView.getContext())
                .load(item.getUrl())
                .into(vh.sharedBookPost);

        vh.nickName.setText(item.getNickName());
        vh.comment.setText(item.getComment());
    }

    @Override
    public int getItemCount() {
        return sharedBooks.size();
    }

    public void setItems(ArrayList<SharedBook> items) {
        this.sharedBooks = items;
    }
}
