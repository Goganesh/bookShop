package com.goganesh.bookshop.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "document")
@Data
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "sort_index")
    private int sortIndex;

    private String slug;

    private String title;

    private String text;
}