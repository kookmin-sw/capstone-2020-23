package com.moayo.server.dao;

import com.moayo.server.model.BookModel;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface BookDao {
    void insertBook(BookModel book);
    void updateBook(BookModel book);
    void deleteBookByModel(BookModel book);
    void deleteBookByNo(int no);
    BookModel getBook(int no);
//    List<HashMap<String,Object>> map = dao.getBookByWriter("me");
    List<HashMap<String,Object>> getBookByWriter(String writer);
}
