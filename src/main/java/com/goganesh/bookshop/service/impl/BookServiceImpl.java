package com.goganesh.bookshop.service.impl;

import com.goganesh.bookshop.domain.Author;
import com.goganesh.bookshop.domain.Book;
import com.goganesh.bookshop.domain.Genre;
import com.goganesh.bookshop.domain.Tag;
import com.goganesh.bookshop.repository.BookRepository;
import com.goganesh.bookshop.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> findBooksBySlugIn(String[] slugs) {
        return bookRepository.findBySlugIn(slugs);
    }

    @Override
    public Book findBySlug(String slug) {
        return bookRepository.findBySlug(slug);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Page<Book> getPageOfTagBooks(Tag tag, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset,limit);
        return bookRepository.findByTag(tag, nextPage);
    }

    @Override
    public Page<Book> getPageOfAuthorBooks(Author author, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset,limit);
        return bookRepository.findByAuthor(author, nextPage);
    }

    @Override
    public Page<Book> getPageOfGenreBooks(Genre genre, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset,limit);
        return bookRepository.findByGenre(genre, nextPage);
    }

    @Override
    public Page<Book> getPageOfRecommendedBooks(Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset,limit);
        return bookRepository.findAll(nextPage);
    }

    @Override
    public Page<Book> getPageOfPopularBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset,limit, Sort.by("popularity").descending());
        return bookRepository.findAll(nextPage);
    }

    @Override
    public Page<Book> getPageOfRecentBooks(LocalDate from, LocalDate to, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset,limit);
        return bookRepository.findByPubDateBetween(from, to, nextPage);
    }

    @Override
    public Page<Book> getPageOfSearchResultBooks(String searchWord, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset,limit);
        return bookRepository.findByTitleContaining(searchWord, nextPage);
    }
}
