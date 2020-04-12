package com.capstone.moayo;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.capstone.moayo.model.Movie;

import java.util.ArrayList;

public class adapter_result2 extends RecyclerView.Adapter<adapter_result2.ViewHolder> {

    private ArrayList<Movie> items = new ArrayList<>();

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivMovie;
        TextView tvTitle, tvContent, tvGenre;

        ViewHolder(View itemView) {
            super(itemView);

            ivMovie = itemView.findViewById(R.id.iv_item_movie);

            tvTitle = itemView.findViewById(R.id.tv_item_movie_title);
            tvContent = itemView.findViewById(R.id.tv_item_movie_content);
            tvGenre = itemView.findViewById(R.id.tv_item_movie_genre);
        }
    }

    @NonNull
    @Override
    public adapter_result2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.recycler2_result, parent, false);
        adapter_result2.ViewHolder vh = new adapter_result2.ViewHolder(itemView);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_result2.ViewHolder vh, int position) {

        Movie item = items.get(position);

        Glide.with(vh.itemView.getContext()).load(item.getUrl()).into(vh.ivMovie);

        vh.tvTitle.setText(item.getTitle());
        vh.tvContent.setText(item.getContent());
        vh.tvGenre.setText(item.getGenre());

    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(ArrayList<Movie> items) {
        this.items = items;
    }


}

