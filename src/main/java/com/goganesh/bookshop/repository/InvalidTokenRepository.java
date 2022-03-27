package com.goganesh.bookshop.repository;

import com.goganesh.bookshop.domain.InvalidToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvalidTokenRepository extends JpaRepository<InvalidToken, Integer> {

    List<InvalidToken> findByToken(String token);
}
