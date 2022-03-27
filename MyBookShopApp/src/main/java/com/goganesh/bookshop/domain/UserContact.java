package com.goganesh.bookshop.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_contact")
@Data
public class UserContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Enumerated(EnumType.STRING)
    private ContactType type;

    private short approved;

    private String code;

    @Column(name = "code_trails")
    private int codeTrails;

    @Column(name = "code_time")
    private LocalDateTime codeTime;

    private String contact;
}
