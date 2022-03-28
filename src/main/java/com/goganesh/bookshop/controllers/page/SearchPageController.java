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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class SearchPageController {

    private final BookService bookService;
    private final BookRatingService bookRatingService;
    private final UserRegisterService userRegisterService;

    @ModelAttribute("currentUser")
    public UserPageDto user(){
        return new UserPageDto(userRegisterService.getCurrentUser());
    }

    @ModelAttribute("searchResults")
    public BooksPageDto searchResults(){
        return new BooksPageDto();
    }

    @GetMapping(value = { "/search", "/search/{searchWord}"})
    public String getSearchResults(@PathVariable(value = "searchWord", required = false)String searchWord,
                                   Model model) {
        model.addAttribute("searchWordDto", new SearchWordDto(searchWord));
        model.addAttribute("searchResults", new BooksPageDto(bookService.getPageOfSearchResultBooks(searchWord, 0, 20).getContent()
                .stream()
                .map(book -> new BookPageDto(book, bookRatingService.findRatingByBook(book)))
                .collect(Collectors.toList())));

        return "search/index";
    }

}
