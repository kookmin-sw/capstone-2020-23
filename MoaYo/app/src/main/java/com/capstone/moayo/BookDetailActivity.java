package com.capstone.moayo;

import android.os.Bundle;
import android.widget.ExpandableListView;
//import android.widget.ExpandableListView.OnChildClickListener;
//import android.widget.ExpandableListView.OnGroupClickListener;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.capstone.moayo.Adapter.ExpandableAdapter;
import com.capstone.moayo.model.CategoryNode;


public class BookDetailActivity extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        CategoryNode rootNode = (CategoryNode) getIntent().getSerializableExtra("categoryNode");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(rootNode.title);

        ExpandableListView myList = (ExpandableListView)findViewById(R.id.expandableListView);
        //create Data
        myList.setAdapter(new ExpandableAdapter(this, rootNode.lowLayer));

        //listener for child click
//        myList.setOnChildClickListener(myListItemClicked);
        //listener for group click
//        myList.setOnGroupClickListener(myListGroupClicked);

    }

    //child listener
//    private OnChildClickListener myListItemClicked =  new OnChildClickListener() {
//
//        public boolean onChildClick(ExpandableListView parent, View v,
//                                    int groupPosition, int childPosition, long id) {
//
//            //get the group header
////            HeaderInfo headerInfo = deptList.get(groupPosition);
//            //get the child info
////            DetailInfo detailInfo =  headerInfo.getProductList().get(childPosition);
//            //display it or do something with it
////            Toast.makeText(getBaseContext(), "Clicked on Detail " + headerInfo.getName()
////                    + "/" + detailInfo.getName(), Toast.LENGTH_LONG).show();
////            Toast.makeText(getBaseContext(), "on click child!", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//
//    };

    //group listener
//    private OnGroupClickListener myListGroupClicked =  new OnGroupClickListener() {
//
//        public boolean onGroupClick(ExpandableListView parent, View v,
//                                    int groupPosition, long id) {
////            Toast.makeText(getBaseContext(), "on click group!", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//
//    };
}
