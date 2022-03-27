package com.goganesh.bookshop.repository;

import com.goganesh.bookshop.domain.Book2UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Book2UserTypeRepository extends JpaRepository<Book2UserType, Integer> {
    Book2UserType findByCode(String code);
}
