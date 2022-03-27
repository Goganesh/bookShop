package com.goganesh.bookshop.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "genre")
@Data
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany
    @JoinColumn(name= "parent_id")
    @ToString.Exclude
    private List<Genre> childGenres;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Genre parent;

    private String slug;

    private String name;
}
