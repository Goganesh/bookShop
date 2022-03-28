package com.goganesh.bookshop.controllers.page;

import com.goganesh.bookshop.dto.SearchWordDto;
import com.goganesh.bookshop.dto.UserPageDto;
import com.goganesh.bookshop.service.UserRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@AllArgsConstructor
public class FaqPageController {
    private final UserRegisterService userRegisterService;

    @ModelAttribute("currentUser")
    public UserPageDto user(){
        return new UserPageDto(userRegisterService.getCurrentUser());
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto(){
        return new SearchWordDto();
    }

    @GetMapping("/faq")
    public String faqPage(){
        return "faq";
    }
}
