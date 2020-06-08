package com.capstone.moayo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import com.capstone.moayo.service.ShareService;
import com.capstone.moayo.service.concrete.ServiceFactoryCreator;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.util.Async.AsyncCallback;
import com.capstone.moayo.util.Async.AsyncExecutor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Callable;

public class ShareMenuAdapter extends RecyclerView.Adapter<ShareMenuAdapter.ViewHolder> {

    private ArrayList<CategoryDto> sharedBooks = new ArrayList<>();
    private ShareService shareService = ServiceFactoryCreator.getInstance().requestShareService(null);
    Callable<Integer> callable;

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView sharedBookPost;
        TextView nickName, comment, like_count, sharedDate;
        ImageButton like_btn;

        ViewHolder(View itemView) {
            super(itemView);

            sharedBookPost = itemView.findViewById(R.id.sharedBookPost);
            nickName = itemView.findViewById(R.id.nickName);
            comment = itemView.findViewById(R.id.comment);
            like_btn = (ImageButton) itemView.findViewById(R.id.recycler_share_like);
            like_count = (TextView) itemView.findViewById(R.id.recycler_share_like_count);
            sharedDate = itemView.findViewById(R.id.sharedDate);
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ShareMenuAdapter.ViewHolder vh, int position) {

        CategoryDto item = sharedBooks.get(position);

        Glide.with(vh.itemView.getContext())
                .load(item.getUrl())
                .into(vh.sharedBookPost);

        vh.nickName.setText(item.getTitle());
        vh.comment.setText(item.getDescription());
        vh.like_count.setText(Integer.toString(item.getLike()));
        if(item.isLiked()) vh.like_btn.setSelected(true);
        else vh.like_btn.setSelected(false);
        if(item.getTime() != null)
            vh.sharedDate.setText(item.getTime().split("T")[0]);
        vh.like_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: 도감 좋아요 백엔드통신

                //테스트 보여주기 용 로직.
                if(vh.like_btn.isSelected()) {
                    callable = () -> shareService.updateLike(item.getId(), false);
                } else {
                    callable = () -> shareService.updateLike(item.getId(), true);
//                    vh.like_count.setText(String.valueOf(Integer.parseInt(vh.like_count.getText().toString()) + 1));
                }

                AsyncCallback<Integer> likeCallback = new AsyncCallback<Integer>() {
                    @Override
                    public void onResult(Integer result) {
                        if(vh.like_btn.isSelected()) {
                            vh.like_btn.setSelected(false);
                            vh.like_count.setText(Integer.toString(Integer.parseInt(vh.like_count.getText().toString()) - 1));
                        } else {
                            vh.like_btn.setSelected(true);
                            vh.like_count.setText(Integer.toString(Integer.parseInt(vh.like_count.getText().toString()) + 1));
                        }
                    }

                    @Override
                    public void exceptionOccured(Exception e) {
                        Log.e("error in shareMenuAdapter", e.toString());
                    }

                    @Override
                    public void cancelled() {

                    }
                };

                new AsyncExecutor<Integer>().setCallable(callable).setCallback(likeCallback).execute();
                
            }
        });

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
