package com.goganesh.bookshop.controllers.page;

import com.goganesh.bookshop.dto.SearchWordDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@AllArgsConstructor
public class MyPageController {

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto(){
        return new SearchWordDto();
    }

    @GetMapping("/my")
    public String handleMy() {
        return "my";
    }
}
