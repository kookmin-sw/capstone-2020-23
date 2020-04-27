//package com.capstone.moayo.Adapter;
//
//import android.content.Context;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import com.bumptech.glide.Glide;
//import com.capstone.moayo.R;
//import com.capstone.moayo.model.NewPost;
//
//import java.util.ArrayList;
//
//public class adapter_result2 extends RecyclerView.Adapter<adapter_result2.ViewHolder> {
//
//    private ArrayList<NewPost> items = new ArrayList<>();
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//
//        ImageView newPost;
//
//
//        ViewHolder(View itemView) {
//            super(itemView);
//
//            newPost = itemView.findViewById(R.id.newPost);
//        }
//    }
//
//    @NonNull
//    @Override
//    public adapter_result2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
//        Context context = parent.getContext();
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        View itemView = inflater.inflate(R.layout.recycler2_result, parent, false);
//        adapter_result2.ViewHolder vh = new adapter_result2.ViewHolder(itemView);
//
//        return vh;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull adapter_result2.ViewHolder vh, int position) {
//
//        NewPost item = items.get(position);
//
//        Glide.with(vh.itemView.getContext()).load(item.getUrl()).into(vh.newPost);
//
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return items.size();
//    }
//
//    public void setItems(ArrayList<NewPost> items) {
//        this.items = items;
//    }
//
//
//}
//
