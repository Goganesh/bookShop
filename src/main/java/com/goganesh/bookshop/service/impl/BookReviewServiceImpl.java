package com.goganesh.bookshop.service.impl;

import com.goganesh.bookshop.domain.Book;
import com.goganesh.bookshop.domain.BookReview;
import com.goganesh.bookshop.domain.User;
import com.goganesh.bookshop.dto.BookReviewRequest;
import com.goganesh.bookshop.exception.NoSuchUserException;
import com.goganesh.bookshop.repository.BookRepository;
import com.goganesh.bookshop.repository.BookReviewRepository;
import com.goganesh.bookshop.service.BookReviewService;
import com.goganesh.bookshop.service.UserRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class BookReviewServiceImpl implements BookReviewService {

    private final BookRepository bookRepository;
    private final UserRegisterService userRegisterService;
    private final BookReviewRepository bookReviewRepository;

    @Override
    @Transactional
    public void handleBookReviewRequest(BookReviewRequest bookReviewRequest) {
        User user = userRegisterService.getCurrentUser();
        if (!user.getRole().equals("USER")) {
            throw new NoSuchUserException("there is no such user");
        }

        Book book = bookRepository.findBySlug(bookReviewRequest.getBookId());

        String text = bookReviewRequest.getText();

        BookReview bookReview = new BookReview();
        bookReview.setBook(book);
        bookReview.setUser(user);
        bookReview.setText(text);
        bookReview.setTime(LocalDateTime.now());

        bookReviewRepository.save(bookReview);
    }
}
