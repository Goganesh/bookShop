package com.goganesh.bookshop.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "balance_transaction")
@Data
public class BalanceTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;

    private LocalDateTime time;

    private int value;

    @Column(name = "book_id")
    private int bookId;

    @Column(name = "description")
    private String description;
}
