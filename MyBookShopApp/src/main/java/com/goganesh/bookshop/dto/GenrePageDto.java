package com.goganesh.bookshop.dto;

import com.goganesh.bookshop.domain.Genre;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class GenrePageDto {
    private String name;
    private String slug;
    private int bookCount;
    private List<GenrePageDto> childGenrePageDtos;

    public GenrePageDto(Genre genre) {
        this.name = genre.getName();
        this.slug = genre.getSlug();
        //todo
        this.bookCount = 0;
        this.childGenrePageDtos = genre.getChildGenres()
                .stream()
                .map(GenrePageDto::new)
                .collect(Collectors.toList());
    }
}
