package com.capstone.moayo.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.capstone.moayo.PageFragment;
import com.capstone.moayo.model.CategoryNode;

import java.util.ArrayList;


public class PagerAdapter extends FragmentStatePagerAdapter {

//   이곳에서 내부 DB에 저장된 본인의 도감 데이터를 받아와 자료구조/구조체에 담을 것.
//   테스트를 위해 현재는 String array 데이터만을 보낸다.
//   페이지네이터의 역할 수행

    private ArrayList<ArrayList<CategoryNode>> pages;


    public static <T> ArrayList<ArrayList<T>> split(ArrayList<T> resList, int count) {
        if (resList == null || count < 1)
            return null;
        ArrayList<ArrayList<T>> ret = new ArrayList<ArrayList<T>>();
        int size = resList.size();
        if (size <= count) {
            // 데이터 부족 count 지정 크기
            ret.add(resList);
        } else {
            int pre = size / count;
            int last = size % count;
            // 앞 pre 개 집합, 모든 크기 다 count 가지 요소
            for (int i = 0; i <pre; i++) {
                ArrayList<T> itemList = new ArrayList<T>();
                for (int j = 0; j <count; j++) {
                    itemList.add(resList.get(i * count + j));
                }
                ret.add(itemList);
            }
            // last 진행이 처리
            if (last > 0) {
                ArrayList<T> itemList = new ArrayList<T>();
                for (int i = 0; i <last; i++) {
                    itemList.add(resList.get(pre * count + i));
                }
                ret.add(itemList);
            }
        }
        return ret;
    }

//    private ArrayList<CategoryNode> myBookList = createData();
//    private ArrayList<ArrayList<CategoryNode>> pages = split(myBookList, 9);


    public PagerAdapter(FragmentManager fragmentManager, ArrayList<CategoryNode> userBookData) {
        super(fragmentManager);
        pages = split(userBookData, 9);
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    @Override
    public Fragment getItem(int position) {

        return PageFragment.create(pages.get(position));
    }
}
