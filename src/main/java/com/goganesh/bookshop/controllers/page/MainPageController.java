package com.goganesh.bookshop.controllers.page;

import com.goganesh.bookshop.dto.BookPageDto;
import com.goganesh.bookshop.dto.BooksPageDto;
import com.goganesh.bookshop.dto.SearchWordDto;
import com.goganesh.bookshop.dto.TagPageDto;
import com.goganesh.bookshop.repository.TagRepository;
import com.goganesh.bookshop.service.BookRatingService;
import com.goganesh.bookshop.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class MainPageController {

    private final BookService bookService;
    private final TagRepository tagRepository;
    private final BookRatingService bookRatingService;

    @ModelAttribute("recommendedBooks")
    public BooksPageDto recommendedBooks(){
        return new BooksPageDto(bookService.getPageOfRecommendedBooks(1, 20).getContent()
                .stream()
                .map(book -> new BookPageDto(book, bookRatingService.findRatingByBook(book)))
                .collect(Collectors.toList()));
    }

    @ModelAttribute("recentBooks")
    public BooksPageDto recentBooks(){
        return new BooksPageDto(bookService.getPageOfRecentBooks(LocalDate.of(2020, 1, 8), LocalDate.of(2020, 1, 8), 1, 20).getContent()
                .stream()
                .map(book -> new BookPageDto(book, bookRatingService.findRatingByBook(book)))
                .collect(Collectors.toList()));
    }

    @ModelAttribute("popularBooks")
    public BooksPageDto popularBooks(){
        return new BooksPageDto(bookService.getPageOfPopularBooks(1, 20).getContent()
                .stream()
                .map(book -> new BookPageDto(book, bookRatingService.findRatingByBook(book)))
                .collect(Collectors.toList()));
    }

    @ModelAttribute("tags")
    public List<TagPageDto> tags(){
        return tagRepository.findAll()
                .stream()
                .map(TagPageDto::new)
                .collect(Collectors.toList());
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto(){
        return new SearchWordDto();
    }

    @GetMapping("/")
    public String mainPage(){
        return "index";
    }

}
