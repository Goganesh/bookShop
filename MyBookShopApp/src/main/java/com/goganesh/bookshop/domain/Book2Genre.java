package com.goganesh.bookshop.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "book2genre")
@Data
public class Book2Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;
}