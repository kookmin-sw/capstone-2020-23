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
//import com.capstone.moayo.model.SavedPost;
//
//import java.util.ArrayList;
//
//public class adapter_result1 extends RecyclerView.Adapter<adapter_result1.ViewHolder> {
//
//    private ArrayList<SavedPost> saveditems = new ArrayList<>();
//
//    // 아이템 뷰를 저장하는 뷰홀더 클래스.
//    public class ViewHolder extends RecyclerView.ViewHolder {
//
//        ImageView savedPost;
//        TextView savedTag;
//
//        ViewHolder(View itemView) {
//            super(itemView) ;
//
//            // 뷰 객체에 대한 참조. (hold strong reference)
//            savedPost = itemView.findViewById(R.id.savedPost);
//            savedTag = itemView.findViewById(R.id.savedTag);
//
//        }
//    }
//
//
//    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
//    @NonNull
//    @Override
//    public adapter_result1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
//
//        Context context = parent.getContext() ;
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
//
//        View view = inflater.inflate(R.layout.recycler1_result, parent, false) ;
//        adapter_result1.ViewHolder vh = new adapter_result1.ViewHolder(view) ;
//
//
//        return vh ;
//    }
//
//    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
//    @Override
//    public void onBindViewHolder(@NonNull adapter_result1.ViewHolder vh, int position) {
//
//        SavedPost item = saveditems.get(position);
//
//        Glide.with(vh.itemView.getContext()).load(item.getUrl()).into(vh.savedPost);
//
//        vh.savedTag.setText(item.getTag());
//
//    }
//
//    // getItemCount() - 전체 데이터 갯수 리턴.
//    @Override
//    public int getItemCount() {
//        return saveditems.size();
//    }
//
//    public void setItems(ArrayList<SavedPost> items) {
//        this.saveditems = items;
//    }
//
//}
//
