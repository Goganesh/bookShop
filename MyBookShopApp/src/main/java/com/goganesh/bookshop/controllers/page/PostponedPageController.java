package com.goganesh.bookshop.controllers.page;

import com.goganesh.bookshop.domain.Book2User;
import com.goganesh.bookshop.domain.Book2UserType;
import com.goganesh.bookshop.domain.User;
import com.goganesh.bookshop.dto.BookPageDto;
import com.goganesh.bookshop.dto.SearchWordDto;
import com.goganesh.bookshop.repository.Book2UserRepository;
import com.goganesh.bookshop.repository.Book2UserTypeRepository;
import com.goganesh.bookshop.service.BookRatingService;
import com.goganesh.bookshop.service.UserRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class PostponedPageController {

    private final UserRegisterService userRegisterService;
    private final Book2UserRepository book2UserRepository;
    private final Book2UserTypeRepository book2UserTypeRepository;
    private final BookRatingService bookRatingService;

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto(){
        return new SearchWordDto();
    }

    @ModelAttribute("keptBooks")
    public List<BookPageDto> keptBooks() {
        User user = userRegisterService.getCurrentUser();
        Book2UserType book2UserType = book2UserTypeRepository.findByCode("KEPT"); //todo
        return book2UserRepository.findByUserAndBook2UserType(user, book2UserType)
                .stream()
                .map(Book2User::getBook)
                .map(book -> new BookPageDto(book, bookRatingService.findRatingByBook(book)))
                .collect(Collectors.toList());
    }

    @GetMapping("/postponed")
    public String postponedPage(){
        return "postponed";
    }

}
