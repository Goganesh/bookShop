package com.goganesh.bookshop.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class BookRateRequest {
    @NotBlank(message = "The bookId is required.")
    private final String bookId;

    @NotNull(message = "The value of bags is required.")
    @Range(min = 1, max = 5, message = "The number of bags must be greater than 0 and less than 6.")
    private final int value;
}
