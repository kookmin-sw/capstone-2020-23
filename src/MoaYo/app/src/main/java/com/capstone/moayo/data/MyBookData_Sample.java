package com.capstone.moayo.data;

import com.capstone.moayo.model.MyBook;
import com.capstone.moayo.model.NewPost;

import java.util.ArrayList;

public class MyBookData_Sample {

    ArrayList<MyBook> items = new ArrayList<>();

    public ArrayList<MyBook> getItems() {

        MyBook book1 = new MyBook("https://lh3.googleusercontent.com/proxy/iz7mqs_RA1uYHQgxJwMR0c3-lo5DstNhpHgF7_s4JITb4eyBJLQmAcj1xdLTiKdw_O2THJLP8otJDdY9VCiwz2SmU8CkwQGHLjLZeEGAUItXyScsOJsusjMVbLMUz4Y",
                "가수");

        MyBook book2 = new MyBook("https://usercontents-c.styleshare.io/images/19518035/640x-",
                "패션");

        MyBook book3 = new MyBook("https://t1.daumcdn.net/movie/da2e6f0663514ba3aaf1f003733d08831560262646934","영화");

        MyBook book4 = new MyBook("https://www.polinews.co.kr/data/photos/20190102/art_15469376601633_583641.jpg",
                "맛집");

        MyBook book5 = new MyBook("https://post-phinf.pstatic.net/MjAxODA2MTVfMjI3/MDAxNTI5MDI0OTEyNzkw.nZysM1B1Cph53JAl2hPk91kh5p1C7P1EZfV_vfiSGsMg.CYE_l4wAKZ-qKfmEFH_WdZ0uDz7x-GYsd3e3Hr1232Ag.PNG/6.png?type=w800_q75",
                "가구");

        MyBook book6 = new MyBook("https://travelpost.kr/wp-content/uploads/2016/02/78-01.jpg", "여행");


        items.add(book1);
        items.add(book2);
        items.add(book3);
        items.add(book4);
        items.add(book5);
        items.add(book6);


        return items;
    }
}
