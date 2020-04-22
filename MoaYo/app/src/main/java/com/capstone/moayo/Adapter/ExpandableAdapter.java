package com.capstone.moayo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.moayo.CategoryNode;
import com.capstone.moayo.R;
import com.capstone.moayo.ResultActivity;

import java.util.ArrayList;

//import android.widget.ExpandableListView;

public class ExpandableAdapter extends BaseExpandableListAdapter {
    Context mContext;
    ArrayList<CategoryNode> mBookData;

    public ExpandableAdapter(Context context, ArrayList<CategoryNode> book) {
        mContext = context;
        mBookData = book;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return mBookData.get(groupPosition).lowLayer.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return childPosition;
    }

    //ChildView에 데이터 뿌리기
    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        View view;
        if(convertView == null) {
            view = getChildGenericView();
        } else {
            view = convertView;
        }

        TextView text = (TextView)view.findViewById(R.id.text1);
        text.setText(mBookData.get(groupPosition).lowLayer.get(childPosition).title);

//        TextView sub_text = (TextView)view.findViewById(R.id.text2);
//        sub_text.setText(mBookData.get(groupPosition).title);

        ImageButton searchBtn = (ImageButton)view.findViewById(R.id.child_search_btn);
        searchBtn.setFocusable(false);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
            Intent intent = new Intent(mContext, ResultActivity.class);
            mContext.startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mBookData.get(groupPosition).lowLayer.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mBookData.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return mBookData.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //GroupView에 데이터 뿌리
    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded,
                             View convertView, final ViewGroup parent) {

        View view;
        if(convertView == null) {
            view = getParentGenericView();
        } else {
            view = convertView;
        }

        TextView text = (TextView)view.findViewById(R.id.text);
        text.setText(mBookData.get(groupPosition).title);

        ImageButton searchBtn = (ImageButton)view.findViewById(R.id.group_search_btn);
        searchBtn.setFocusable(false);


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
//                ExpandableListView expandableListView = (ExpandableListView)parent;
//
//                if (!isExpanded) {
//                    expandableListView.expandGroup(groupPosition);
//                }
//                else {
//                    expandableListView.collapseGroup(groupPosition);
//                }
//                Toast.makeText(mContext , "검색!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, ResultActivity.class);
                mContext.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        // TODO Auto-generated method stub
        return super.areAllItemsEnabled();
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return true;
    }

    //Child의 View의 XML을 생성
    public View getChildGenericView() {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.child_list_view, null);
        return view;
    }

    //Parent(Group)의 View의 XML을 생성
    public View getParentGenericView() {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.group_list_view, null);
        return view;
    }
}