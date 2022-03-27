package com.goganesh.bookshop.service;

import com.goganesh.bookshop.domain.Book;
import com.goganesh.bookshop.dto.BookRateRequest;

public interface BookRatingService {

    int findRatingByBook(Book book);
    void handleBookRateRequest(BookRateRequest bookRateRequest);
}
