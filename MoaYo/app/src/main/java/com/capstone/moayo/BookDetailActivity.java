package com.capstone.moayo;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BookDetailActivity extends AppCompatActivity{

    private ArrayList<BookData> createData() {
        ArrayList<BookData> book_list = new ArrayList<>();
        BookData book = new BookData("상의");
        book.addChild("후드/집업");
        book.addChild("맨투맨");
        book.addChild("니트");

        book_list.add(book);

        return book_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("패션");

        ExpandableListView myList = (ExpandableListView)findViewById(R.id.expandableListView);
        //create Data
        myList.setAdapter(new ExpandableAdapter(this, createData()));

        //listener for child click
        myList.setOnChildClickListener(myListItemClicked);
        //listener for group click
        myList.setOnGroupClickListener(myListGroupClicked);

    }

    //child listener
    private OnChildClickListener myListItemClicked =  new OnChildClickListener() {

        public boolean onChildClick(ExpandableListView parent, View v,
                                    int groupPosition, int childPosition, long id) {

            //get the group header
//            HeaderInfo headerInfo = deptList.get(groupPosition);
            //get the child info
//            DetailInfo detailInfo =  headerInfo.getProductList().get(childPosition);
            //display it or do something with it
//            Toast.makeText(getBaseContext(), "Clicked on Detail " + headerInfo.getName()
//                    + "/" + detailInfo.getName(), Toast.LENGTH_LONG).show();
//            Toast.makeText(getBaseContext(), "on click child!", Toast.LENGTH_SHORT).show();
            return false;
        }

    };

    //group listener
    private OnGroupClickListener myListGroupClicked =  new OnGroupClickListener() {

        public boolean onGroupClick(ExpandableListView parent, View v,
                                    int groupPosition, long id) {
//            Toast.makeText(getBaseContext(), "on click group!", Toast.LENGTH_SHORT).show();

            return false;
        }

    };
}
