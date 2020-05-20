package com.capstone.moayo.data;

import com.capstone.moayo.model.SavedPost;
import com.capstone.moayo.model.SharedBook;

import java.util.ArrayList;

public class SharedData_Sample {

    ArrayList<SharedBook> shareBooks = new ArrayList<>();

    public ArrayList<SharedBook> getItems() {

        SharedBook share1 = new SharedBook("https://ppss.kr/wp-content/uploads/2019/07/02-66-540x375.jpg",
            "라라", "취향별 해외 여행지 도감 공유, 코로나 끝나면 가보고 저장해야지ㅠㅠ");

        SharedBook share2 = new SharedBook("https://t1.daumcdn.net/tvpot/thumb/v57dfVRLYV0RD9TFF0tYMo0/thumb.png",
                "집콕이", "집에서 하기 좋을 것 같은 취미 생활 도감 공유!");

        SharedBook share3 = new SharedBook("https://pbs.twimg.com/media/Dmt4mAeU4AAVu18.jpg",
                "마포인", "마포구 미식가의 맛집 도감 공유합니다.");

        SharedBook share4 = new SharedBook("https://i.pinimg.com/originals/f2/e4/07/f2e407393d65e49f762cbf351dd4f82f.jpg",
                "은우", "은우의 착장 도감, 다들 필요하실 것 같아 공유합니당 ㅎㅎㅎ");

        shareBooks.add(share1);
        shareBooks.add(share2);
        shareBooks.add(share3);
        shareBooks.add(share4);

        shareBooks.add(share1);
        shareBooks.add(share2);
        shareBooks.add(share3);
        shareBooks.add(share4);

        return shareBooks;
    }
}
