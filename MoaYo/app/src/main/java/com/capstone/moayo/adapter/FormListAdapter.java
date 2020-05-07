package com.capstone.moayo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.capstone.moayo.R;
import com.capstone.moayo.entity.Keyword;

import java.util.ArrayList;

public class FormListAdapter extends ArrayAdapter {
    public FormListAdapter(Context context, ArrayList keywords) {
        super(context, 0, keywords);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_keyword, parent, false);
        }

        // Get the data item for this position
        Keyword keyword = (Keyword) getItem(position);

        // Lookup view for data population
        TextView title = (TextView) convertView.findViewById(R.id.keyword);
        TextView count = (TextView) convertView.findViewById(R.id.num_of_tags);
        // Populate the data into the template view using the data object
        title.setText((String)keyword.getKeyword());
        count.setText(Integer.toString(keyword.getNumOfTags()));

        // Return the completed view to render on screen
        return convertView;
    }
}
