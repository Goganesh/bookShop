package com.goganesh.bookshop.controllers.api;

import com.goganesh.bookshop.dto.BookRateRequest;
import com.goganesh.bookshop.dto.CardActionResponse;
import com.goganesh.bookshop.service.BookRatingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class RateController {

    private final BookRatingService bookRatingService;

    @PostMapping("/rateBook")
    public CardActionResponse rateBook(@Valid @RequestBody BookRateRequest bookRateRequest) {
        bookRatingService.handleBookRateRequest(bookRateRequest);

        return CardActionResponse.builder()
                .result(true)
                .build();
    }
}
