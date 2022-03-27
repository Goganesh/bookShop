package com.goganesh.bookshop.service;

import com.goganesh.bookshop.domain.Author;
import com.goganesh.bookshop.domain.Book;
import com.goganesh.bookshop.domain.Genre;
import com.goganesh.bookshop.domain.Tag;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;


public interface BookService {

    Book save(Book book);
    Book findBySlug(String slug);
    List<Book> findBooksBySlugIn(String[] slugs);

    Page<Book> getPageOfRecommendedBooks(Integer offset, Integer limit);
    Page<Book> getPageOfPopularBooks(Integer offset, Integer limit);
    Page<Book> getPageOfRecentBooks(LocalDate from , LocalDate to, Integer offset, Integer limit);
    Page<Book> getPageOfSearchResultBooks(String searchWord, Integer offset, Integer limit);
    Page<Book> getPageOfGenreBooks(Genre genre, Integer offset, Integer limit);
    Page<Book> getPageOfAuthorBooks(Author author, Integer offset, Integer limit);
    Page<Book> getPageOfTagBooks(Tag tag, Integer offset, Integer limit);
}
