package com.goganesh.bookshop.repository;

import com.goganesh.bookshop.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    Author findBySlug(String slug);

}
