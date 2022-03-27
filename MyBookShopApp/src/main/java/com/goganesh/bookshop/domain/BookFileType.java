package com.goganesh.bookshop.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "book_file_type")
public class BookFileType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String description;

}