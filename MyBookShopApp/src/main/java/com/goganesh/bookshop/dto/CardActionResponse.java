package com.goganesh.bookshop.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardActionResponse {
    private boolean result;
    private String error;
}
