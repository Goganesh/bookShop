package com.goganesh.bookshop.controllers.page;

import com.goganesh.bookshop.domain.Book;
import com.goganesh.bookshop.domain.Book2User;
import com.goganesh.bookshop.domain.Book2UserType;
import com.goganesh.bookshop.domain.User;
import com.goganesh.bookshop.dto.BookPageDto;
import com.goganesh.bookshop.dto.SearchWordDto;
import com.goganesh.bookshop.repository.Book2UserRepository;
import com.goganesh.bookshop.repository.Book2UserTypeRepository;
import com.goganesh.bookshop.service.BookRatingService;
import com.goganesh.bookshop.service.BookService;
import com.goganesh.bookshop.service.UserRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class CartPageController {

    private final Book2UserRepository book2UserRepository;
    private final UserRegisterService userRegisterService;
    private final Book2UserTypeRepository book2UserTypeRepository;
    private final BookRatingService bookRatingService;

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto(){
        return new SearchWordDto();
    }

    @ModelAttribute("bookCart")
    public List<BookPageDto> cartBook() {
        User user = userRegisterService.getCurrentUser();
        Book2UserType cartType = book2UserTypeRepository.findByCode("CART");

        return book2UserRepository.findByUser(user)
                .stream()
                .filter(book2User -> book2User.getBook2UserType().equals(cartType))
                .map(Book2User::getBook)
                .map(book -> new BookPageDto(book, bookRatingService.findRatingByBook(book)))
                .collect(Collectors.toList());
    }

    @GetMapping("/cart")
    public String handleCartRequest() {
        return "cart";
    }
}
