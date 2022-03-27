package com.goganesh.bookshop.repository;

import com.goganesh.bookshop.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {

    Tag findBySlug(String slug);
}
