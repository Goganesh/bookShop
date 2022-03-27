package com.goganesh.bookshop.service.impl;

import com.goganesh.bookshop.domain.Book;
import com.goganesh.bookshop.domain.Book2User;
import com.goganesh.bookshop.repository.Book2UserRepository;
import com.goganesh.bookshop.service.BookPopularityService;
import com.goganesh.bookshop.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class BookPopularityServiceImpl implements BookPopularityService {

    private final BookService bookService;
    private final Book2UserRepository book2UserRepository;

    @Override
    @Transactional
    public void calculateBookPopularity(Book book) {
        Double bookPopularity = book2UserRepository.findByBook(book)
                .stream()
                .map(Book2User::getBook2UserType)
                .map(book2UserType -> {
                    switch (book2UserType.getCode()) {
                        case "PAID":
                            return 1.0d;
                        case "CART":
                            return 0.7d;
                        case "KEPT":
                            return 0.4d;
                        default:
                            return 0.0d;
                    }
                })
                .reduce(0.0d, Double::sum);

        book.setPopularity(bookPopularity);

        bookService.save(book);
    }

    @Override
    public void calculateBooksPopularity(List<Book> books) {
        books.forEach(this::calculateBookPopularity);
    }
}
