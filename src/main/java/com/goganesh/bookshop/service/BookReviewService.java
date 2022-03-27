package com.goganesh.bookshop.service;

import com.goganesh.bookshop.dto.BookReviewRequest;

public interface BookReviewService {

    void handleBookReviewRequest(BookReviewRequest bookReviewRequest);
}
