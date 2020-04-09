package com.capstone.moayo;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

//   이곳에서 내부 DB에 저장된 본인의 도감 데이터를 받아와 자료구조/구조체에 담을 것.
//   테스트를 위해 현재는 String array 데이터만을 보낸다.
//   페이지네이터의 역할 수행
    private final String[][] galBook = new String[][] {
        {"동물", "연예인", "맛집", "코딩", "영화", "간식", "전공", "학교", "게임" },
        {"동물2", "연예인2", "맛집2", "코딩2", "영화2", "간식2", "전공2", "학교2", "게임2" },
        {"동물3", "연예인3", "맛집3", "코딩3", "영화3", "간식3", "전공3", "학교3", "게임3" }
    };

    PagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return galBook.length;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.create(galBook[position]);
    }
}
