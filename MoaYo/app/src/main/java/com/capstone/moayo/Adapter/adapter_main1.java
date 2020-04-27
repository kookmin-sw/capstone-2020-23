//package com.capstone.moayo.Adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.capstone.moayo.R;
//import com.capstone.moayo.model.MyBook;
//
//import java.util.ArrayList;
//
//public class adapter_main1 extends RecyclerView.Adapter<adapter_main1.ViewHolder> {
//
//    private ArrayList<MyBook> myBooks = new ArrayList<>();
//
//    // 아이템 뷰를 저장하는 뷰홀더 클래스.
//    public class ViewHolder extends RecyclerView.ViewHolder {
//
//        ImageView myBookPost;
//        TextView myBookName;
//
//        ViewHolder(View itemView) {
//            super(itemView) ;
//
//            // 뷰 객체에 대한 참조. (hold strong reference)
//            myBookPost = itemView.findViewById(R.id.myBookPost);
//            myBookName = itemView.findViewById(R.id.myBookName);
//
//        }
//    }
//
//
//    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
//    @NonNull
//    @Override
//    public adapter_main1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
//
//        Context context = parent.getContext() ;
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
//
//        View view = inflater.inflate(R.layout.recycler1_main, parent, false) ;
//        adapter_main1.ViewHolder vh = new adapter_main1.ViewHolder(view) ;
//
//
//        return vh ;
//    }
//
//    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
//    @Override
//    public void onBindViewHolder(@NonNull adapter_main1.ViewHolder vh, int position) {
//
//        MyBook item = myBooks.get(position);
//
//        Glide.with(vh.itemView.getContext()).load(item.getUrl()).into(vh.myBookPost);
//
//        vh.myBookName.setText(item.getName());
//
//    }
//
//    // getItemCount() - 전체 데이터 갯수 리턴.
//    @Override
//    public int getItemCount() {
//        return myBooks.size();
//    }
//
//    public void setItems(ArrayList<MyBook> items) {
//        this.myBooks = items;
//    }
//
//}