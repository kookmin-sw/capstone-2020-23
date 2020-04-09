package com.capstone.moayo;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private final String[] galBook = new String[] {
            "첫번째 도감 페이지",
            "두번째 도감 페이지",
            "세번째 도감 페이지",
            "네번째 도감 페이지"
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
