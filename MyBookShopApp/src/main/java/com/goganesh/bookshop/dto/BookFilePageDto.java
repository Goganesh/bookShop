package com.goganesh.bookshop.dto;

import com.goganesh.bookshop.domain.BookFile;
import lombok.Data;

@Data
public class BookFilePageDto {

    private final String hash;
    private final String path;
    private final String type;

    public BookFilePageDto(BookFile bookFile) {
        this.hash = bookFile.getHash();
        this.path = bookFile.getPath();
        this.type = bookFile.getType().getName();
    }
}
