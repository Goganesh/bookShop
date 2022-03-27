package com.goganesh.bookshop.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "invalid_token")
@Data
@NoArgsConstructor
public class InvalidToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String token;

    public InvalidToken(String token) {
        this.token = token;
    }
}
