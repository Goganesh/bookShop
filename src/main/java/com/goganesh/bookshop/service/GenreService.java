package com.goganesh.bookshop.service;

import com.goganesh.bookshop.domain.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> findRootGenres();
    Genre findBySlug(String slug);
}
