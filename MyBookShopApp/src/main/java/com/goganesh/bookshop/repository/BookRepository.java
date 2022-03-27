package com.goganesh.bookshop.repository;

import com.goganesh.bookshop.domain.Author;
import com.goganesh.bookshop.domain.Book;
import com.goganesh.bookshop.domain.Genre;
import com.goganesh.bookshop.domain.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT bg.book FROM Book2Genre bg WHERE bg.genre = :genre")
    Page<Book> findByGenre(@Param("genre") Genre genre, Pageable nextPage);
    @Query("SELECT ba.book FROM Book2Author ba WHERE ba.author = :author")
    Page<Book> findByAuthor(@Param("author") Author author, Pageable nextPage);
    @Query("SELECT bt.book FROM Book2Tag bt WHERE bt.tag = :tag")
    Page<Book> findByTag(@Param("tag") Tag tag, Pageable nextPage);

    Book findBySlug(String slug);
    Page<Book> findByTitleContaining(String bookTitle, Pageable nextPage);
    Page<Book> findByPubDateBetween(LocalDate pubDateStart, LocalDate pubDateFinish, Pageable nextPage);
    List<Book> findBySlugIn(String[] slugs);
}
