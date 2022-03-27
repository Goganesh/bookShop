package com.goganesh.bookshop.controllers.api;

import com.goganesh.bookshop.dto.BookReviewRequest;
import com.goganesh.bookshop.dto.CardActionResponse;
import com.goganesh.bookshop.service.BookReviewService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ReviewController {

    private final BookReviewService bookReviewService;

    @PostMapping("/bookReview")
    public CardActionResponse reviewBook(@Valid @RequestBody BookReviewRequest bookReviewRequest) {
        bookReviewService.handleBookReviewRequest(bookReviewRequest);

        return CardActionResponse.builder()
                .result(true)
                .build();
    }
}
