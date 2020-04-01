package com.moayo.server.dao;

import com.moayo.server.model.BookModel;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDao {
    void insertBook(BookModel book);
    void updateBook(BookModel book);
    void deleteBookByModel(BookModel book);
    void deleteBookByNo(int no);
    BookModel getBook(int no);
}
