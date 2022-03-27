package com.goganesh.bookshop.controllers.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FaqPageController {

    @GetMapping("/faq")
    public String faqPage(){
        return "faq";
    }
}
