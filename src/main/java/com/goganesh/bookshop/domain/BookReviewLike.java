package com.goganesh.bookshop.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "book_review_like")
@Data
public class BookReviewLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "review_id")
    private int reviewId;

    @Column(name = "user_id")
    private int userId;

    private LocalDateTime time;

    private short value;

}