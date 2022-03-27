package com.goganesh.bookshop.controllers.page;

import com.goganesh.bookshop.domain.Book;
import com.goganesh.bookshop.domain.BookFile;
import com.goganesh.bookshop.dto.BookFilePageDto;
import com.goganesh.bookshop.dto.BookPageDto;
import com.goganesh.bookshop.dto.SearchWordDto;
import com.goganesh.bookshop.repository.BookFileRepository;
import com.goganesh.bookshop.service.BookRatingService;
import com.goganesh.bookshop.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class BookPageController {

    private final BookService bookService;
    private final BookFileRepository bookFileRepository;
    private final BookRatingService bookRatingService;

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto(){
        return new SearchWordDto();
    }

    @GetMapping("/books/{slug}")
    public String booksRecentPage(@PathVariable(value = "slug", required = true) String slug,
                                  Model model){
        Book book = bookService.findBySlug(slug);
        List<BookFile> bookFiles = bookFileRepository.findByBook(book);

        model.addAttribute("bookPageDto", new BookPageDto(book, bookRatingService.findRatingByBook(book)));
        model.addAttribute("bookFilePageDtos", bookFiles.stream()
                .map(BookFilePageDto::new)
                .collect(Collectors.toList()));

        return "books/slug";
    }
}
