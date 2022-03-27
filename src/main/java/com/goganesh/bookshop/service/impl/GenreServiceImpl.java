package com.goganesh.bookshop.service.impl;

import com.goganesh.bookshop.domain.Genre;
import com.goganesh.bookshop.repository.GenreRepository;
import com.goganesh.bookshop.service.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public List<Genre> findRootGenres() {
        return genreRepository.findByParentIsNull();
    }

    @Override
    public Genre findBySlug(String slug) {
        return genreRepository.findBySlug(slug);
    }
}
