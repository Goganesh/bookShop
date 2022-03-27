package com.goganesh.bookshop.controllers.api;

import com.goganesh.bookshop.exception.NoSuchBookAction;
import com.goganesh.bookshop.domain.Book;
import com.goganesh.bookshop.domain.User;
import com.goganesh.bookshop.dto.CardActionResponse;
import com.goganesh.bookshop.dto.ChangeBookStatusRequest;
import com.goganesh.bookshop.repository.Book2UserRepository;
import com.goganesh.bookshop.repository.Book2UserTypeRepository;
import com.goganesh.bookshop.repository.BookRepository;
import com.goganesh.bookshop.service.BookAction;
import com.goganesh.bookshop.service.impl.CartBookAction;
import com.goganesh.bookshop.service.impl.KeptBookAction;
import com.goganesh.bookshop.service.UserRegisterService;
import com.goganesh.bookshop.service.impl.UnlinkBookAction;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class ShopApiController {

    private final UserRegisterService userRegisterService;
    private final Book2UserRepository book2UserRepository;
    private final BookRepository bookRepository;
    private final Book2UserTypeRepository book2UserTypeRepository;

    public ShopApiController(UserRegisterService userRegisterService,
                             Book2UserRepository book2UserRepository,
                             BookRepository bookRepository,
                             Book2UserTypeRepository book2UserTypeRepository) {
        this.userRegisterService = userRegisterService;
        this.book2UserRepository = book2UserRepository;
        this.bookRepository = bookRepository;
        this.book2UserTypeRepository = book2UserTypeRepository;
    }

    @PostMapping("/books/changeBookStatus")
    public CardActionResponse changeBookStatus(@RequestBody ChangeBookStatusRequest changeBookStatusRequest) {

        String status = changeBookStatusRequest.getStatus();
        User user = userRegisterService.getCurrentUser();
        List<Book> books = changeBookStatusRequest.getBooksIds()
                .stream()
                .map(bookRepository::findBySlug)
                .collect(Collectors.toList());

        BookAction bookAction;
        switch (status) {
            case "KEPT":
                System.out.println("KEPT"); //todo replace to logging
                bookAction = new KeptBookAction(user, books, book2UserRepository, book2UserTypeRepository);
                break;
            case "UNLINK":
                System.out.println("UNLINK"); //todo replace to logging
                bookAction = new UnlinkBookAction(user, books, book2UserRepository, book2UserTypeRepository);
                break;
            case "CART":
                System.out.println("CART"); //todo replace to logging
                bookAction = new CartBookAction(user, books, book2UserRepository, book2UserTypeRepository);
                break;
            default:
                throw new NoSuchBookAction("There is no action - " + status);
        }

        bookAction.execute();

        return CardActionResponse.builder()
                .result(true)
                .build();
    }
}
