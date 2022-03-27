package com.goganesh.bookshop.controllers.api;

import com.goganesh.bookshop.domain.Author;
import com.goganesh.bookshop.domain.Genre;
import com.goganesh.bookshop.domain.Tag;
import com.goganesh.bookshop.dto.BookPageDto;
import com.goganesh.bookshop.dto.BooksPageDto;
import com.goganesh.bookshop.repository.AuthorRepository;
import com.goganesh.bookshop.repository.TagRepository;
import com.goganesh.bookshop.service.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class DataApiController {

    private final BookService bookService;
    private final GenreService genreService;
    private final AuthorRepository authorRepository;
    private final TagRepository tagRepository;
    private final BookRatingService bookRatingService;

    @GetMapping("/books/tag/{slug}")
    @ResponseBody
    public BooksPageDto getBooksTag(@RequestParam("offset") Integer offset,
                                       @RequestParam("limit") Integer limit,
                                       @PathVariable(value = "slug", required = true) String slug) {
        Tag tag = tagRepository.findBySlug(slug);
        return new BooksPageDto(bookService.getPageOfTagBooks(tag, offset, limit).getContent()
                .stream()
                .map(book -> new BookPageDto(book, bookRatingService.findRatingByBook(book)))
                .collect(Collectors.toList()));
    }

    @GetMapping("/books/author/{slug}")
    @ResponseBody
    public BooksPageDto getBooksAuthor(@RequestParam("offset") Integer offset,
                                      @RequestParam("limit") Integer limit,
                                      @PathVariable(value = "slug", required = true) String slug) {
        Author author = authorRepository.findBySlug(slug);
        return new BooksPageDto(bookService.getPageOfAuthorBooks(author, offset, limit).getContent()
                .stream()
                .map(book -> new BookPageDto(book, bookRatingService.findRatingByBook(book)))
                .collect(Collectors.toList()));
    }

    @GetMapping("/books/genre/{slug}")
    @ResponseBody
    public BooksPageDto getBooksGenre(@RequestParam("offset") Integer offset,
                                      @RequestParam("limit") Integer limit,
                                      @PathVariable(value = "slug", required = true) String slug) {
        Genre genre = genreService.findBySlug(slug);
        return new BooksPageDto(bookService.getPageOfGenreBooks(genre, offset, limit).getContent()
                .stream()
                .map(book -> new BookPageDto(book, bookRatingService.findRatingByBook(book)))
                .collect(Collectors.toList()));
    }

    @GetMapping("/books/recommended")
    @ResponseBody
    public BooksPageDto getBooksRecommended(@RequestParam("offset") Integer offset,
                                            @RequestParam("limit") Integer limit) {

        return new BooksPageDto(bookService.getPageOfRecommendedBooks(offset, limit).getContent()
                .stream()
                .map(book -> new BookPageDto(book, bookRatingService.findRatingByBook(book)))
                .collect(Collectors.toList()));
    }

    @GetMapping("/books/popular")
    @ResponseBody
    public BooksPageDto getBooksPopular(@RequestParam("offset") Integer offset,
                                     @RequestParam("limit") Integer limit) {

        return new BooksPageDto(bookService.getPageOfPopularBooks(offset, limit).getContent()
                .stream()
                .map(book -> new BookPageDto(book, bookRatingService.findRatingByBook(book)))
                .collect(Collectors.toList()));
    }

    @GetMapping("/books/recent")
    @ResponseBody
    public BooksPageDto getBooksRecent(@RequestParam("offset") Integer offset,
                                        @RequestParam("limit") Integer limit,
                                        @RequestParam("from") String fromDate,
                                        @RequestParam("to") String toDate) {
        LocalDate from = LocalDate.parse(fromDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        LocalDate to = LocalDate.parse(toDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        return new BooksPageDto(bookService.getPageOfRecentBooks(from, to, offset, limit).getContent()
                .stream()
                .map(book -> new BookPageDto(book, bookRatingService.findRatingByBook(book)))
                .collect(Collectors.toList()));
    }

    @GetMapping("/search/{searchWord}")
    @ResponseBody
    public BooksPageDto getNextSearchPage(
            @RequestParam("offset") Integer offset,
            @RequestParam("limit") Integer limit,
            @PathVariable(value = "searchWord", required = true) String searchWord){
        return new BooksPageDto(bookService.getPageOfSearchResultBooks(searchWord, offset, limit).getContent()
                .stream()
                .map(book -> new BookPageDto(book, bookRatingService.findRatingByBook(book)))
                .collect(Collectors.toList()));
    }

}
