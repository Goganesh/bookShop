package com.goganesh.bookshop.controllers.page;

import com.goganesh.bookshop.dto.GenrePageDto;
import com.goganesh.bookshop.dto.SearchWordDto;
import com.goganesh.bookshop.dto.UserPageDto;
import com.goganesh.bookshop.service.GenreService;
import com.goganesh.bookshop.service.UserRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class GenresPageController {

    private final GenreService genreService;
    private final UserRegisterService userRegisterService;

    @ModelAttribute("currentUser")
    public UserPageDto user(){
        return new UserPageDto(userRegisterService.getCurrentUser());
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto(){
        return new SearchWordDto();
    }

    @ModelAttribute("genres")
    public List<GenrePageDto> genresList(){
         return genreService.findRootGenres()
                .stream()
                .map(GenrePageDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/genres")
    public String genresPage(){
        return "genres/index";
    }
}
