package com.goganesh.bookshop.controllers.page;

import com.goganesh.bookshop.domain.Genre;
import com.goganesh.bookshop.dto.*;
import com.goganesh.bookshop.service.BookRatingService;
import com.goganesh.bookshop.service.BookService;
import com.goganesh.bookshop.service.GenreService;
import com.goganesh.bookshop.service.UserRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class GenrePageController {

    private final GenreService genreService;
    private final BookService bookService;
    private final BookRatingService bookRatingService;
    private final UserRegisterService userRegisterService;

    @ModelAttribute("currentUser")
    public UserPageDto user(){
        return new UserPageDto(userRegisterService.getCurrentUser());
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto(){
        return new SearchWordDto();
    }

    @GetMapping("/genres/{slug}")
    public String genrePage(@PathVariable(value = "slug", required = true) String slug,
                             Model model){
        Genre genre = genreService.findBySlug(slug);
        model.addAttribute("genrePageDto", new GenrePageDto(genre));
        model.addAttribute("genreBooks", new BooksPageDto(bookService.getPageOfGenreBooks(genre,1, 20).getContent()
                .stream()
                .map(book -> new BookPageDto(book, bookRatingService.findRatingByBook(book)))
                .collect(Collectors.toList())));
        return "genres/slug";
    }
}
