package com.goganesh.bookshop.controllers.page;

import com.goganesh.bookshop.domain.Author;
import com.goganesh.bookshop.dto.*;
import com.goganesh.bookshop.repository.AuthorRepository;
import com.goganesh.bookshop.service.BookRatingService;
import com.goganesh.bookshop.service.BookService;
import com.goganesh.bookshop.service.UserRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class AuthorPageController {

    private final AuthorRepository authorRepository;
    private final BookService bookService;
    private final BookRatingService bookRatingService;
    private final UserRegisterService userRegisterService;

    @ModelAttribute("currentUser")
    public UserPageDto user(){
        return new UserPageDto(userRegisterService.getCurrentUser());
    }

    @ModelAttribute("authorsMap")
    public Map<String,List<Author>> authorsMap(){
        return new TreeMap(authorRepository.findAll()
                .stream()
                .map(AuthorPageDto::new)
                .collect(Collectors.groupingBy((AuthorPageDto a) -> a.getName().substring(0,1))));
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto(){
        return new SearchWordDto();
    }

    @GetMapping("/authors/{slug}")
    public String authorPage(@PathVariable(value = "slug", required = true) String slug,
                             Model model){
        Author author = authorRepository.findBySlug(slug);
        model.addAttribute("authorPageDto", new AuthorPageDto(author));
        model.addAttribute("authorBooks", new BooksPageDto(bookService.getPageOfAuthorBooks(author, 1, 20).getContent()
                .stream()
                .map(book -> new BookPageDto(book, bookRatingService.findRatingByBook(book)))
                .collect(Collectors.toList())));

        return "authors/slug";
    }
}
