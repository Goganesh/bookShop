package com.goganesh.bookshop.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "author")
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String photo;

    private String slug;

    private String name;

    private String description;

}
