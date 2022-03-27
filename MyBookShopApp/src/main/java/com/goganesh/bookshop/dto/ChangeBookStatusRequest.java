package com.goganesh.bookshop.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChangeBookStatusRequest {
    private final List<String> booksIds;
    private final String status;
}
