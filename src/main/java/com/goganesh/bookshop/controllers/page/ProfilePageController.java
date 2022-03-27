package com.goganesh.bookshop.controllers.page;

import com.goganesh.bookshop.dto.SearchWordDto;
import com.goganesh.bookshop.dto.UserPageDto;
import com.goganesh.bookshop.service.impl.UserRegisterServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@AllArgsConstructor
public class ProfilePageController {

    private final UserRegisterServiceImpl userRegister;

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto(){
        return new SearchWordDto();
    }

    @GetMapping("/profile")
    public String handleProfile(Model model) {
        model.addAttribute("curUsr", new UserPageDto(userRegister.getCurrentUser()));
        return "profile";
    }
}
