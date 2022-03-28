package com.goganesh.bookshop.dto;

import com.goganesh.bookshop.domain.User;
import lombok.Data;

@Data
public class UserPageDto {

    private String name;
    private String email;
    private String phone;
    private int balance;
    private Boolean isReg;

    public UserPageDto(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.balance = user.getBalance();
        this.isReg = !user.getRole().equals("TEMP_USER");
    }
}
