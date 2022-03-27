package com.goganesh.bookshop.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "file_download")
@Data
public class FileDownload {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "book_id")
    private int bookId;

    private int count;

}
