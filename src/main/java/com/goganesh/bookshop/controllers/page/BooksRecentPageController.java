package com.goganesh.bookshop.controllers.page;

import com.goganesh.bookshop.dto.BookPageDto;
import com.goganesh.bookshop.dto.BooksPageDto;
import com.goganesh.bookshop.dto.SearchWordDto;
import com.goganesh.bookshop.dto.UserPageDto;
import com.goganesh.bookshop.service.BookRatingService;
import com.goganesh.bookshop.service.BookService;
import com.goganesh.bookshop.service.UserRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class BooksRecentPageController {

    private final BookService bookService;
    private final BookRatingService bookRatingService;
    private final UserRegisterService userRegisterService;

    @ModelAttribute("currentUser")
    public UserPageDto user(){
        return new UserPageDto(userRegisterService.getCurrentUser());
    }

    @ModelAttribute("recentBooks")
    public BooksPageDto bookList(){
        return new BooksPageDto(bookService.getPageOfRecentBooks(LocalDate.now().minusDays(30), LocalDate.now(),1, 20).getContent()
                .stream()
                .map(book -> new BookPageDto(book, bookRatingService.findRatingByBook(book)))
                .collect(Collectors.toList()));
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto(){
        return new SearchWordDto();
    }

    @GetMapping("/books/recent")
    public String booksRecentPage(){
        return "books/recent";
    }
}
