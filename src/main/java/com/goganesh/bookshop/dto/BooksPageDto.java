package com.goganesh.bookshop.dto;

import com.goganesh.bookshop.domain.Book;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class BooksPageDto {
    private int count;
    private List<BookPageDto> books;

    public BooksPageDto(List<BookPageDto> books) {
        this.books = books;
        this.count = books.size();
    }
}
