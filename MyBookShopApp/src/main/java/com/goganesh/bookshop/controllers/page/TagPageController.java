package com.goganesh.bookshop.controllers.page;

import com.goganesh.bookshop.domain.Tag;
import com.goganesh.bookshop.dto.BookPageDto;
import com.goganesh.bookshop.dto.BooksPageDto;
import com.goganesh.bookshop.dto.SearchWordDto;
import com.goganesh.bookshop.dto.TagPageDto;
import com.goganesh.bookshop.repository.TagRepository;
import com.goganesh.bookshop.service.BookRatingService;
import com.goganesh.bookshop.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class TagPageController {

    private final TagRepository tagRepository;
    private final BookService bookService;
    private final BookRatingService bookRatingService;

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto(){
        return new SearchWordDto();
    }

    @GetMapping("/tags/{slug}")
    public String authorsPage(@PathVariable(value = "slug", required = true) String slug,
                              Model model){
        Tag tag = tagRepository.findBySlug(slug);
        model.addAttribute("tagPageDto", new TagPageDto(tag));
        model.addAttribute("tagBooks", new BooksPageDto(bookService.getPageOfTagBooks(tag, 1, 20).getContent()
                .stream()
                .map(book -> new BookPageDto(book, bookRatingService.findRatingByBook(book)))
                .collect(Collectors.toList())));
        return "tags/index";
    }
}
