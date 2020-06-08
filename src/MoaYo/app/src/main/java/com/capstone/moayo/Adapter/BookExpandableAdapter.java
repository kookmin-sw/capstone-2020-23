package com.capstone.moayo.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.capstone.moayo.R;
import com.capstone.moayo.activity.BookDetailActivity;
import com.capstone.moayo.activity.BookFormActivity;
import com.capstone.moayo.activity.MainActivity;
import com.capstone.moayo.activity.ResultActivity;
import com.capstone.moayo.entity.Category;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.service.dto.CategoryNodeDto;

import java.util.ArrayList;


public class BookExpandableAdapter extends BaseExpandableListAdapter {
    Context mContext;
    CategoryDto selectCategory;
    ArrayList<CategoryNodeDto> categoryNodes;
    CategoryNodeDto selectedNode;

    public BookExpandableAdapter(Context context, CategoryDto category) {
        mContext = context;
        selectCategory = category;
        categoryNodes = (ArrayList<CategoryNodeDto>) category.getRootNode().getLowLayer();
        selectedNode = null;
    }

    public BookExpandableAdapter(Context context, CategoryDto category, CategoryNodeDto selected_node) {
        mContext = context;
        selectCategory = category;
        categoryNodes = (ArrayList<CategoryNodeDto>) category.getRootNode().getLowLayer();
        selectedNode = selected_node;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return categoryNodes.get(groupPosition).getLowLayer().get(childPosition);
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
        final CategoryNodeDto currentNode = categoryNodes.get(groupPosition).getLowLayer().get(childPosition);

        if(convertView == null) {
            view = getChildGenericView();
        } else {
            view = convertView;
        }

        TextView text = (TextView)view.findViewById(R.id.text1);
        text.setText(currentNode.getTitle());


        if (isSelectedNode(currentNode, selectedNode) == true) {
            text.setTextColor(Color.parseColor("#663399"));
        }

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(mContext, ResultActivity.class);
                intent.putExtra("current_node", currentNode);
                intent.putExtra("category", selectCategory);
                mContext.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

        return view;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return categoryNodes.get(groupPosition).getLowLayer().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return categoryNodes.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return categoryNodes.size();
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
        final CategoryNodeDto currentNode = categoryNodes.get(groupPosition);

        if(convertView == null) {
            view = getParentGenericView();
        } else {
            view = convertView;
        }

        TextView text = (TextView)view.findViewById(R.id.text);
        ImageButton searchBtn = (ImageButton)view.findViewById(R.id.group_search_btn);

        text.setText(currentNode.getTitle());
        searchBtn.setFocusable(false);
//        searchBtn.setVisibility(View.VISIBLE);


        if (isExpanded){
            text.setSelected(true);
            searchBtn.setVisibility(View.VISIBLE);
        } else {
            text.setSelected(false);
            searchBtn.setVisibility(View.INVISIBLE);
        }

        if (isSelectedNode(currentNode, selectedNode) == true) {
            text.setTextColor(Color.parseColor("#663399"));
        }

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
                intent.putExtra("current_node", currentNode);
                intent.putExtra("category", selectCategory);
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
        View view = inflater.inflate(R.layout.expandable_listview_child, null);
        return view;
    }

    //Parent(Group)의 View의 XML을 생성
    public View getParentGenericView() {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.expandable_listview_parent, null);
        return view;

    }

//    private boolean hasSelectedNode(CategoryNode parent, CategoryNode selected) {
//
//        if(selected != null) {
//            ArrayList<CategoryNode> childs = (ArrayList<CategoryNode>) parent.lowLayer;
//            for(int i = 0; i < childs.size(); ++i) {
//                if (childs.get(i).title.equals(selected.title)) {
//                    Log.d("state", "is contained");
//                    return true;
//                }
//            }
//            Log.d("state", "is not contained");
//            return false;
//        } else {
//            return false;
//        }
//    }

    private boolean isSelectedNode(CategoryNodeDto target, CategoryNodeDto selected) {
        if(target == null || selected == null) {
            return false;
        } else {
            if (target.getTitle().equals(selected.getTitle()))  return true; else return false;
        }
    }
}
