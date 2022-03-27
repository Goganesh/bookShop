package com.goganesh.bookshop.repository;

import com.goganesh.bookshop.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

    List<Genre> findByParentIsNull();
    Genre findBySlug(String slug);
}
