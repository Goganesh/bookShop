package com.goganesh.bookshop.dto;

import com.goganesh.bookshop.domain.Author;
import lombok.Data;

@Data
public class AuthorPageDto {

    private final String name;
    private final String slug;
    private final String description;
    private final String photo;
    private final int bookCount;

    public AuthorPageDto(Author author) {
        this.name = author.getName();
        this.slug = author.getSlug();
        this.description = author.getDescription();
        this.photo = author.getPhoto();
        //todo
        this.bookCount = 0;
    }
}
