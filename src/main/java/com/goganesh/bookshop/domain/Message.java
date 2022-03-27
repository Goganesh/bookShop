package com.goganesh.bookshop.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "message")
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime time;

    @Column(name = "user_id")
    private int userId;

    private String email;

    private String name;

    private String subject;

    private String text;
}