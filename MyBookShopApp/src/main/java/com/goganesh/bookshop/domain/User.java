package com.goganesh.bookshop.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "reg_time")
    private LocalDateTime regTime;

    private String hash;
    private int balance;
    private String name;
    private String email;
    private String phone;
    private String password;

    @Column(name = "is_enabled")
    private boolean isEnabled;

    private String role;

}
