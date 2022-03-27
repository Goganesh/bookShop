package com.goganesh.bookshop.service.impl;

import com.goganesh.bookshop.domain.Book;
import com.goganesh.bookshop.domain.BookRating;
import com.goganesh.bookshop.domain.User;
import com.goganesh.bookshop.dto.BookRateRequest;
import com.goganesh.bookshop.repository.BookRatingRepository;
import com.goganesh.bookshop.repository.BookRepository;
import com.goganesh.bookshop.service.BookRatingService;
import com.goganesh.bookshop.service.UserRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BookRatingServiceImpl implements BookRatingService {

    private final UserRegisterService userRegisterService;
    private final BookRepository bookRepository;
    private final BookRatingRepository bookRatingRepository;

    @Override
    public int findRatingByBook(Book book) {
        List<BookRating> bookRatingList  = bookRatingRepository.findByBook(book);
        int rating = 0;
        if (bookRatingList.size() > 0) {
            rating = bookRatingList.stream()
                    .map(BookRating::getRating)
                    .reduce(0, Integer::sum) / bookRatingList.size();
        }

        return rating;
    }

    @Override
    @Transactional
    public void handleBookRateRequest(BookRateRequest bookRateRequest) {
        Book book = bookRepository.findBySlug(bookRateRequest.getBookId());
        User user = userRegisterService.getCurrentUser();
        int rating = bookRateRequest.getValue();

        BookRating bookRating = bookRatingRepository.findByUserAndBook(user, book);
        if (bookRating == null) {
            bookRating = new BookRating();
            bookRating.setUser(user);
            bookRating.setBook(book);
        }

        bookRating.setRating(rating);
        bookRating.setTime(LocalDateTime.now());

        bookRatingRepository.save(bookRating);
    }
}
