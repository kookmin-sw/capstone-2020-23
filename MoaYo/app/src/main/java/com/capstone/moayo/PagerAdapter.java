package com.capstone.moayo;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class PagerAdapter extends FragmentStatePagerAdapter {

//   이곳에서 내부 DB에 저장된 본인의 도감 데이터를 받아와 자료구조/구조체에 담을 것.
//   테스트를 위해 현재는 String array 데이터만을 보낸다.
//   페이지네이터의 역할 수행
//    private final String[][] galBook = new String[][] {
//        {"동물", "연예인", "맛집", "코딩", "영화", "간식", "전공", "학교", "게임" },
//        {"동물2", "연예인2", "맛집2", "코딩2", "영화2", "간식2", "전공2", "학교2", "게임2" },
//        {"동물3", "연예인3", "맛집3", "코딩3", "영화3", "간식3", "전공3", "학교3", "게임3" }
//    };

    private ArrayList<CategoryNode> createData () {
        ArrayList<CategoryNode> myBookList = new ArrayList<>();

        CategoryNode fashion = new CategoryNode("패션");

        CategoryNode pants = new CategoryNode("하의");
        CategoryNode denim_pants = new CategoryNode("데님바지");
        CategoryNode cotton_pants = new CategoryNode("코튼바지");
        CategoryNode leather_pants = new CategoryNode("레더바지");

        pants.addChild(denim_pants);
        pants.addChild(cotton_pants);
        pants.addChild(leather_pants);

        CategoryNode top = new CategoryNode("상의");
        CategoryNode hood = new CategoryNode("후드");

        top.addChild(hood);

        CategoryNode outer = new CategoryNode("아우터");
        CategoryNode denim_jacket = new CategoryNode("데님자켓");
        CategoryNode cotton_jacket = new CategoryNode("코튼자켓");
        CategoryNode leather_jacket = new CategoryNode("레더자켓");

        outer.addChild(denim_jacket);
        outer.addChild(cotton_jacket);
        outer.addChild(leather_jacket);

        fashion.addChild(pants);
        fashion.addChild(top);
        fashion.addChild(outer);

        myBookList.add(fashion);

        return myBookList;
    }

//    private List<ArrayList> split (ArrayList<CategoryNode> list) {
//        int size = list.size();
//        int count = 9;
//        List<ArrayList> split_list = new ArrayList<ArrayList>();
//
//        if(size <= count){
//            split_list.add(list);
//        } else {
//            int pre = size / count;
//            int last = size % count;
//        }
//    }

    public static <T> ArrayList<ArrayList<T>> split(ArrayList<T> resList, int count) {
        if (resList == null || count <1)
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

    private final ArrayList<CategoryNode> myBookList = createData();
    private final ArrayList<ArrayList<CategoryNode>> pages = split(myBookList, 9);






    PagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return myBookList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.create(pages.get(position));
    }
}
