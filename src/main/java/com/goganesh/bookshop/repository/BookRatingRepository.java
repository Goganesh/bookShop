package com.goganesh.bookshop.repository;

import com.goganesh.bookshop.domain.Book;
import com.goganesh.bookshop.domain.BookRating;
import com.goganesh.bookshop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRatingRepository extends JpaRepository<BookRating, Integer> {

    BookRating findByUserAndBook(User user, Book book);
    List<BookRating> findByBook(Book book);
}
