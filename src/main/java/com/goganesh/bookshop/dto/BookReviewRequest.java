package com.goganesh.bookshop.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BookReviewRequest {
    @NotBlank(message = "The bookId is required.")
    private final String bookId;

    @NotBlank(message = "The text is required.")
    private final String text;
}
