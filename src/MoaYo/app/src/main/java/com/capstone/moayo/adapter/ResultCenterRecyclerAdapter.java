package com.capstone.moayo.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.capstone.moayo.R;
import com.capstone.moayo.service.dto.InstantPost;

import java.util.ArrayList;

public class ResultCenterRecyclerAdapter extends RecyclerView.Adapter<ResultCenterRecyclerAdapter.ViewHolder> {

    private ArrayList<InstantPost> items = new ArrayList<>();

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    private OnItemClickListener mListener = null ;
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView newPost;


        ViewHolder(View itemView) {
            super(itemView);

            newPost = itemView.findViewById(R.id.newPost);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        if (mListener != null) {
                            mListener.onItemClick(v, pos) ;
                        }
                    }

                }
            });
        }
    }

    @NonNull
    @Override
    public ResultCenterRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.recycler_result_center, parent, false);
        ResultCenterRecyclerAdapter.ViewHolder vh = new ResultCenterRecyclerAdapter.ViewHolder(itemView);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ResultCenterRecyclerAdapter.ViewHolder vh, int position) {

        InstantPost item = items.get(position);

        Glide.with(vh.itemView.getContext()).load(item.getSrc()).into(vh.newPost);

    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(ArrayList<InstantPost> items) {
        this.items = items;
    }


//    @Override
//    public void onScrolled(RecyclerView recyclerView, int dx, int dy)
//    {
//        super.onScrolled(recyclerView, dx, dy);
//        StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
//
//        if(manager.findLastVisibleItemPosition() == manager.getItemCount()-1)
//        {
//            // We have reached the end of the recycler view.
//        }
//    }

}

