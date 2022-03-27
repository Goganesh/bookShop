package com.goganesh.bookshop.repository;

import com.goganesh.bookshop.domain.Book;
import com.goganesh.bookshop.domain.BookFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookFileRepository extends JpaRepository<BookFile, Integer> {

    List<BookFile> findByBook(Book book);
    BookFile findByHash(String hash);
}
