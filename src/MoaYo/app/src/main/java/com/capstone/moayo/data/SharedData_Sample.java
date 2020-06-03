package com.capstone.moayo.data;

import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.service.dto.CategoryNodeDto;
import com.capstone.moayo.util.DogamStatus;

import java.util.ArrayList;

public class SharedData_Sample {

    ArrayList<CategoryDto> dummy_data = new ArrayList<>();

    public ArrayList<CategoryDto> getItems() {

        CategoryNodeDto root_node = new CategoryNodeDto("test", null, 1);

        CategoryDto dummy1 = new CategoryDto("라라", "취향별 해외 여행지 도감 공유.", "00", null);
        dummy1.setUrl("https://ppss.kr/wp-content/uploads/2019/07/02-66-540x375.jpg");
        dummy1.setRootNode(root_node);
        dummy1.setStatus(DogamStatus.Sharing);
        CategoryDto dummy2 = new CategoryDto("집콕취미", "집에서 하기 좋을 것 같은 취미생활 도감 공유", "00", null);
        dummy2.setUrl("https://t1.daumcdn.net/tvpot/thumb/v57dfVRLYV0RD9TFF0tYMo0/thumb.png");
        dummy2.setRootNode(root_node);
        dummy2.setStatus(DogamStatus.Sharing);
        CategoryDto dummy3 = new CategoryDto("패션", "패션 테러리스트, 바로 너를 위한 패션 올인원 도감 공유한다.", "00", null);
        dummy3.setUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQE3V8opSeAE3KxavWe2wioK2aZknhF3AkGhTFawiQB1LpqPDrq&usqp=CAU");
        dummy3.setRootNode(root_node);
        dummy3.setStatus(DogamStatus.Sharing);

        dummy_data.add(dummy1);
        dummy_data.add(dummy2);
        dummy_data.add(dummy3);
        dummy_data.add(dummy1);
        dummy_data.add(dummy2);
        dummy_data.add(dummy3);

        return dummy_data;
    }
}
