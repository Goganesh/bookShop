package com.goganesh.bookshop.controllers.page;

import com.goganesh.bookshop.domain.Author;
import com.goganesh.bookshop.dto.AuthorPageDto;
import com.goganesh.bookshop.dto.SearchWordDto;
import com.goganesh.bookshop.dto.UserPageDto;
import com.goganesh.bookshop.repository.AuthorRepository;
import com.goganesh.bookshop.service.UserRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class AuthorsPageController {

    private final AuthorRepository authorRepository;
    private final UserRegisterService userRegisterService;

    @ModelAttribute("currentUser")
    public UserPageDto user(){
        return new UserPageDto(userRegisterService.getCurrentUser());
    }

    @ModelAttribute("authorsMap")
    public Map<String,List<Author>> authorsMap(){
        return new TreeMap(authorRepository.findAll()
                .stream()
                .map(AuthorPageDto::new)
                .collect(Collectors.groupingBy((AuthorPageDto a) -> a.getName().substring(0,1))));
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto(){
        return new SearchWordDto();
    }

    @GetMapping("/authors")
    public String authorsPage(){
        return "authors/index";
    }
}
