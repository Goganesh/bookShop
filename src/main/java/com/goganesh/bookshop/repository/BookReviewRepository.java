package com.goganesh.bookshop.repository;

import com.goganesh.bookshop.domain.BookReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReviewRepository extends JpaRepository<BookReview, Integer> {
}
