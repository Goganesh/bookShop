package com.goganesh.bookshop.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "book2user_type")
@Data
public class Book2UserType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String code;

    private String name;
}
