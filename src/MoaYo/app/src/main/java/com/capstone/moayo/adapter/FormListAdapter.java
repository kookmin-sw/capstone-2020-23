package com.capstone.moayo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.capstone.moayo.activity.BookFormActivity;
import com.capstone.moayo.R;
import com.capstone.moayo.entity.CategoryNode;

import java.util.ArrayList;

public class FormListAdapter extends ArrayAdapter {

    private ImageButton trans_btn;
    private Context mContext;
    private CategoryNode node;

    public FormListAdapter(Context context, ArrayList keywords) {
        super(context, 0, keywords);
        mContext = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_form_item, parent, false);
        }

        // Get the data item for this position
        node = (CategoryNode) getItem(position);

        // Lookup view for data population
        TextView title = (TextView) convertView.findViewById(R.id.keyword);
        TextView count = (TextView) convertView.findViewById(R.id.num_of_tags);
        trans_btn = (ImageButton) convertView.findViewById(R.id.trans_layer_btn);

        trans_btn.setFocusable(false);
        trans_btn.setFocusableInTouchMode(false);
        trans_btn.setClickable(true);

        // Populate the data into the template view using the data object
        title.setText((String)node.getTitle());

        //leaf node라면 계층이동 버튼을 가리고, 자식노드의 수를 표시하지 않는다.
        if(node.getLevel() > 2) {
            trans_btn.setVisibility(View.INVISIBLE);
        } else {
            //        count.setText(Integer.toString(node.getHashtags().size()));
            count.setText("num of child : " + Integer.toString(node.getLowLayer().size()));
        }

        trans_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookFormActivity activity = (BookFormActivity) mContext;
                node = (CategoryNode) getItem(position);
                activity.onChangeLevel(3, node);
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }
}
