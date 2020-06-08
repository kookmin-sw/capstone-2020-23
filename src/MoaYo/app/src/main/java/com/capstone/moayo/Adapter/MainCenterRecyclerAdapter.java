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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.Callable;

public class MainCenterRecyclerAdapter extends RecyclerView.Adapter<MainCenterRecyclerAdapter.ViewHolder> {

    private ArrayList<CategoryDto> sharedBooks = new ArrayList<>();
    private ShareService shareService = ServiceFactoryCreator.getInstance().requestShareService(null);
    private Callable<Integer> likeCallable;

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView sharedBookPost;
        TextView nickName, comment;
        ImageButton like;
        TextView likeCount, sharedDate;

        ViewHolder(View itemView) {
            super(itemView);

            sharedBookPost = itemView.findViewById(R.id.sharedBookPost);
            nickName = itemView.findViewById(R.id.nickName);
            comment = itemView.findViewById(R.id.comment);
            sharedDate = itemView.findViewById(R.id.sharedDate);

            like = itemView.findViewById(R.id.like);
            likeCount = itemView.findViewById(R.id.likeCount);
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MainCenterRecyclerAdapter.ViewHolder vh, int position) {

        CategoryDto item = sharedBooks.get(position);

        Glide.with(vh.itemView.getContext())
            .load(item.getUrl())
            .into(vh.sharedBookPost);

        vh.nickName.setText(item.getTitle());
        vh.comment.setText(item.getDescription());
        vh.likeCount.setText(Integer.toString(item.getLike()));

        if(item.getTime() != null)
            vh.sharedDate.setText(getDate(item.getTime()));

        vh.like.setOnClickListener(v-> {
            if(vh.like.isSelected()) likeCallable = () -> shareService.updateLike(item.getId(), false);
            else likeCallable = () -> shareService.updateLike(item.getId(), true);

            AsyncCallback<Integer> likeCallback = new AsyncCallback<Integer>() {
                @Override
                public void onResult(Integer result) {
                    if(vh.like.isSelected()) {
                        vh.like.setSelected(false);
                        vh.likeCount.setText(Integer.toString(Integer.parseInt(vh.likeCount.getText().toString()) - 1));
                    } else {
                        vh.like.setSelected(true);
                        vh.likeCount.setText(Integer.toString(Integer.parseInt(vh.likeCount.getText().toString()) + 1));
                    }
                }

                @Override
                public void exceptionOccured(Exception e) {
                    Log.e("error in MainCenterRecyclerAdapter", e.toString());
                }

                @Override
                public void cancelled() {

                }
            };

            new AsyncExecutor<Integer>().setCallable(likeCallable).setCallback(likeCallback).execute();
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
        Collections.reverse(items);
        this.sharedBooks = items;
    }

    private String getDate(String time) {
        String[] arr = time.split("T");
        String share_time = arr[0] + " " + arr[1];
        String date = "";
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date share_date = format.parse(share_time);
            Date current_date = format.parse(format.format(System.currentTimeMillis()));
            Log.d("share time", share_date.toString());
            Log.d("current time", current_date.toString());
            long diff = (current_date.getTime() - share_date.getTime()) / 1000;
            diff += 60*60*9;
            Log.d("different time", Long.toString(diff));

            if(diff < 60) {
                date = diff + "초 전";
            } else {
                diff = diff / 60;
                if(diff < 60) {
                    date = diff + "분 전";
                } else {
                    diff = diff / 60;
                    if(diff < 24) {
                        date = diff + "시간 전";
                    } else {
                        diff = diff / 24;
                        if(diff < 30) {
                            date = diff + "일 전";
                        } else {
                            date = arr[0];
                        }
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
//        Calendar calendar = Calendar.getInstance();
//        String diff = "";
//        if(calendar.get(Calendar.SECOND) - share_second < 60) {
//            diff = calendar.get(Calendar.SECOND) - share_second + " 초전";
//        } else if(calendar.get(Calendar.MINUTE) - share_minute < 60) {
//            diff = calendar.get(Calendar.MINUTE) - share_minute +" 분전";
//        } else if(calendar.get(Calendar.HOUR) - share_hour < 24) {
//            diff = calendar.get(Calendar.HOUR) - share_hour + " 시전";
//        } else if(calendar.get(Calendar.DATE) - share_day < 30) {
//            diff = calendar.get(Calendar.DATE) - share_day + " 일전";
//        } else {
//            diff = share_year + "-" + share_month +"-" + share_day;
//        }

//        return diff;
    }
}
