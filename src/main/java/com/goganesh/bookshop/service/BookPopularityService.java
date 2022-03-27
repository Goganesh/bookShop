package com.goganesh.bookshop.service;

import com.goganesh.bookshop.domain.Book;

import java.util.List;

public interface BookPopularityService {

    void calculateBookPopularity(Book book);
    void calculateBooksPopularity(List<Book> books);
}
