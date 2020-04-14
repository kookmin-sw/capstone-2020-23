package com.capstone.moayo;

import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class BookDetailActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        // lvl 2
        ArrayList<HashMap<String, String>> groupData = new ArrayList<>();
        // lvl 3
        ArrayList<ArrayList<HashMap<String, String>>> childData = new ArrayList<>();

        // lvl 2에 요소를 추가
        HashMap<String, String> groupA = new HashMap<>();
        groupA.put("group", "상의");
        HashMap<String, String> groupB = new HashMap<>();
        groupB.put("group", "하의");

        groupData.add(groupA);
        groupData.add(groupB);

        // lvl 3에 요소를 추가
        ArrayList<HashMap<String, String>> childListA = new ArrayList<>();

        HashMap<String, String> childAA = new HashMap<>();
        childAA.put("group", "상의");
        childAA.put("name", "티셔츠");
        childListA.add(childAA);

        HashMap<String, String> childAB = new HashMap<>();
        childAB.put("group", "상의");
        childAB.put("name", "맨투맨");
        childListA.add(childAB);

        HashMap<String, String> childAC = new HashMap<>();
        childAC.put("group", "상의");
        childAC.put("name", "니트");
        childListA.add(childAC);

        HashMap<String, String> childAD = new HashMap<>();
        childAD.put("group", "상의");
        childAD.put("name", "후드/집업");
        childListA.add(childAD);

        childData.add(childListA);

        // 자식 리스트에 요소를 추가한다. (2)
        ArrayList<HashMap<String, String>> childListB = new ArrayList<>();

        HashMap<String, String> childBA = new HashMap<>();
        childBA.put("group", "하의");
        childBA.put("name", "데님");
        childListB.add(childBA);

        HashMap<String, String> childBB = new HashMap<>();
        childBB.put("group", "하의");
        childBB.put("name", "트레이닝");
        childListB.add(childBB);

        HashMap<String, String> childBC = new HashMap<>();
        childBC.put("group", "하의");
        childBC.put("name", "조거");
        childListB.add(childBC);

        HashMap<String, String> childBD = new HashMap<>();
        childBD.put("group", "하의");
        childBD.put("name", "슬랙스");
        childListB.add(childBD);

        HashMap<String, String> childBE = new HashMap<>();
        childBE.put("group", "하의");
        childBE.put("name", "코튼");
        childListB.add(childBE);

        childData.add(childListB);

        // 부모 리스트와 자식 리스트를 포함한 Adapter를 생성
        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this, groupData,
                android.R.layout.simple_expandable_list_item_1,
                new String[] {"group"}, new int[] { android.R.id.text1},
                childData, android.R.layout.simple_expandable_list_item_2,
                new String[] {"name", "group"}, new int[] {android.R.id.text1, android.R.id.text2});

        // ExpandableListView에 Adapter를 설정
        ExpandableListView listView
                = (ExpandableListView) findViewById(R.id.expandableListView);
        listView.setAdapter(adapter);
    }
}
