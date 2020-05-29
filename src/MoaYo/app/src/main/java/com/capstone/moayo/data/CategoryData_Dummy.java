package com.capstone.moayo.data;

import com.capstone.moayo.entity.CategoryNode;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.service.dto.CategoryNodeDto;

import java.util.ArrayList;

public class CategoryData_Dummy {

    ArrayList<CategoryDto> nodes;

    public CategoryData_Dummy() {
        nodes = new ArrayList<>();
    }

    public ArrayList<CategoryDto> getItems() {
        //      Node <<Movie>>
        CategoryDto movieDogam = new CategoryDto("영화", null, null, null);
        movieDogam.setUrl("https://t1.daumcdn.net/movie/da2e6f0663514ba3aaf1f003733d08831560262646934");

        CategoryNodeDto movie = new CategoryNodeDto("영화", null, 1);
        CategoryNodeDto romance = new CategoryNodeDto("로맨스", movie, 2);
        CategoryNodeDto thriller = new CategoryNodeDto("스릴러", movie, 2);
        CategoryNodeDto horror = new CategoryNodeDto("호러", movie,2);
        CategoryNodeDto comedy = new CategoryNodeDto("코미디", movie, 2);
        CategoryNodeDto fantasy = new CategoryNodeDto("판타지", movie, 2);

        CategoryNodeDto scary_movie = new CategoryNodeDto("무서운영화", horror, 3);

        ArrayList<CategoryNodeDto> horror_lowlayer = new ArrayList<>();
        horror_lowlayer.add(scary_movie);
        horror.setLowLayer(horror_lowlayer);

        ArrayList<CategoryNodeDto> movie_lowlayer = new ArrayList<>();
        movie_lowlayer.add(romance);
        movie_lowlayer.add(thriller);
        movie_lowlayer.add(horror);
        movie_lowlayer.add(fantasy);

        movie.setLowLayer(movie_lowlayer);
        movieDogam.setRootNode(movie);

        //      Node <<Fashion>>
        CategoryDto fashionDogam = new CategoryDto("패션", null, null, null);
        fashionDogam.setUrl("https://usercontents-c.styleshare.io/images/19518035/640x-");
        CategoryNodeDto fashion = new CategoryNodeDto("패션", null, 1);
        fashionDogam.setRootNode(fashion);
//
//        CategoryNode pants = new CategoryNode("하의", fashion, );
//        CategoryNode denim_pants = new CategoryNode("데님바지","");
//        CategoryNode cotton_pants = new CategoryNode("코튼바지","");
//        CategoryNode leather_pants = new CategoryNode("레더바지","");
//
//        pants.addChild(denim_pants);
//        pants.addChild(cotton_pants);
//        pants.addChild(leather_pants);
//
//        CategoryNode top = new CategoryNode("상의","");
//        CategoryNode hood = new CategoryNode("후드","");
//
//        top.addChild(hood);
//
//        CategoryNode outer = new CategoryNode("아우터","");
//        CategoryNode denim_jacket = new CategoryNode("데님자켓","");
//        CategoryNode cotton_jacket = new CategoryNode("코튼자켓","");
//        CategoryNode leather_jacket = new CategoryNode("레더자켓","");
//
//        outer.addChild(denim_jacket);
//        outer.addChild(cotton_jacket);
//        outer.addChild(leather_jacket);
//
//        fashion.addChild(pants);
//        fashion.addChild(top);
//        fashion.addChild(outer);

        //  Node <<Singer>>
        CategoryDto singerDogam = new CategoryDto("가수", null, null, null);
        CategoryNodeDto singer = new CategoryNodeDto("가수", null, 1);
        singerDogam.setUrl("https://img1.daumcdn.net/thumb/R720x0.q80/?scode=mtistory2&fname=http%3A%2F%2Fcfile5.uf.tistory.com%2Fimage%2F99C86B485DCC0E3314477E");
        CategoryNodeDto trot = new CategoryNodeDto("트로트", singer, 2);
        CategoryNodeDto ballad = new CategoryNodeDto("발라드", singer, 2);
        CategoryNodeDto hiphop = new CategoryNodeDto("힙합", singer, 2);
        CategoryNodeDto rock = new CategoryNodeDto("락", singer, 2);
        CategoryNodeDto dance = new CategoryNodeDto("댄스", singer, 2);

        CategoryNodeDto yoosanseul = new CategoryNodeDto("유산슬", trot, 3);
        CategoryNodeDto songgain = new CategoryNodeDto("송가인", trot, 3);
        CategoryNodeDto jangyunjung = new CategoryNodeDto("장윤정", trot, 3);
        CategoryNodeDto hongginyoung = new CategoryNodeDto("홍진영", trot, 3);

        ArrayList<CategoryNodeDto> trot_lowlayer = new ArrayList<>();
        trot_lowlayer.add(yoosanseul);
        trot_lowlayer.add(songgain);
        trot_lowlayer.add(jangyunjung);
        trot_lowlayer.add(hongginyoung);

        trot.setLowLayer(trot_lowlayer);

        ArrayList<CategoryNodeDto> singer_lowlayer = new ArrayList<>();
        singer_lowlayer.add(trot);
        singer_lowlayer.add(ballad);
        singer_lowlayer.add(hiphop);
        singer_lowlayer.add(rock);
        singer_lowlayer.add(dance);

        singer.setLowLayer(singer_lowlayer);
        singerDogam.setRootNode(singer);

        //맛집
        CategoryDto foodDogam = new CategoryDto("맛집", null, null, null);
        CategoryNodeDto food = new CategoryNodeDto("맛집", null, 1);
        foodDogam.setUrl("https://www.polinews.co.kr/data/photos/20190102/art_15469376601633_583641.jpg");

        CategoryNodeDto korean_food = new CategoryNodeDto("한식", food, 2);
        CategoryNodeDto japenese_food = new CategoryNodeDto("일식", food, 2);
        CategoryNodeDto chinese_food = new CategoryNodeDto("중식", food, 2);
        CategoryNodeDto snack = new CategoryNodeDto("분식", food, 2);
        CategoryNodeDto fast_food = new CategoryNodeDto("패스트푸드", food, 2);
        CategoryNodeDto dessert = new CategoryNodeDto("디저트", food, 2);

        CategoryNodeDto blacknoodle = new CategoryNodeDto("짜장면", chinese_food, 3);
        CategoryNodeDto jjamppong = new CategoryNodeDto("짬뽕", chinese_food, 3);
        CategoryNodeDto sour_pork = new CategoryNodeDto("탕수육", chinese_food, 3);
        CategoryNodeDto yoosanseul_food = new CategoryNodeDto("유산슬", chinese_food, 3);
        CategoryNodeDto palbochae = new CategoryNodeDto("팔보채", chinese_food, 3);
        CategoryNodeDto china_chicken = new CategoryNodeDto("깐풍기", chinese_food, 3);

        ArrayList<CategoryNodeDto> chinese_lowlayer = new ArrayList<>();
        chinese_lowlayer.add(blacknoodle);
        chinese_lowlayer.add(jjamppong);
        chinese_lowlayer.add(sour_pork);
        chinese_lowlayer.add(yoosanseul_food);
        chinese_lowlayer.add(palbochae);
        chinese_lowlayer.add(china_chicken);
        chinese_food.setLowLayer(chinese_lowlayer);

        ArrayList<CategoryNodeDto> food_lowlayer = new ArrayList<>();
        food_lowlayer.add(korean_food);
        food_lowlayer.add(japenese_food);
        food_lowlayer.add(chinese_food);
        food_lowlayer.add(snack);
        food_lowlayer.add(fast_food);
        food_lowlayer.add(dessert);
        food.setLowLayer(food_lowlayer);

        foodDogam.setRootNode(food);

        //가구
        CategoryDto furnitureDogam = new CategoryDto("가구", null, null, null);
        CategoryNodeDto furniture = new CategoryNodeDto("가구", null, 1);
        furnitureDogam.setUrl("https://post-phinf.pstatic.net/MjAxODA2MTVfMjI3/MDAxNTI5MDI0OTEyNzkw.nZysM1B1Cph53JAl2hPk91kh5p1C7P1EZfV_vfiSGsMg.CYE_l4wAKZ-qKfmEFH_WdZ0uDz7x-GYsd3e3Hr1232Ag.PNG/6.png?type=w800_q75");
        furnitureDogam.setRootNode(furniture);
        //여행
        CategoryDto travelDogam = new CategoryDto("여행", null, null, null);
        CategoryNodeDto travel = new CategoryNodeDto("여행", null, 1);
        travelDogam.setUrl("https://image.edaily.co.kr/images/Photo/files/NP/S/2016/06/PS16060300126.jpg");
        travelDogam.setRootNode(travel);
        //Dummy CategoryNodeDto
        CategoryDto dummyDogam = new CategoryDto("더미", null, null, null);
        CategoryNodeDto dummy = new CategoryNodeDto("더미", null, 1);
        dummyDogam.setUrl("https://image.flaticon.com/icons/png/512/130/130304.png");

        dummyDogam.setRootNode(dummy);

        nodes.add(singerDogam);
        nodes.add(fashionDogam);
        nodes.add(movieDogam);
        nodes.add(foodDogam);
        nodes.add(furnitureDogam);
        nodes.add(travelDogam);
        nodes.add(dummyDogam);
        nodes.add(dummyDogam);
        nodes.add(dummyDogam);
        nodes.add(dummyDogam);
        nodes.add(dummyDogam);
        nodes.add(dummyDogam);
        nodes.add(dummyDogam);
        nodes.add(dummyDogam);


        return nodes;
    }
}


