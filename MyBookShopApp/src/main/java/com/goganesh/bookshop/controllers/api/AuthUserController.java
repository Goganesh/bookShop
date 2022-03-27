package com.goganesh.bookshop.controllers.api;

import com.goganesh.bookshop.exception.NoJwtTokenException;
import com.goganesh.bookshop.domain.User;
import com.goganesh.bookshop.dto.ContactConfirmationPayload;
import com.goganesh.bookshop.dto.ContactConfirmationResponse;
import com.goganesh.bookshop.dto.RegistrationForm;
import com.goganesh.bookshop.dto.SearchWordDto;
import com.goganesh.bookshop.repository.UserRepository;
import com.goganesh.bookshop.service.UserRegisterService;
import com.goganesh.bookshop.service.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthUserController {

    private final String authTokenName;
    private final UserRegisterService userRegisterService;
    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;

    public AuthUserController(@Value("${auth.token.name}")String authTokenName,
                              UserRegisterService userRegisterService,
                              JWTUtil jwtUtil,
                              UserRepository userRepository) {
        this.authTokenName = authTokenName;
        this.userRegisterService = userRegisterService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/requestContactConfirmation")
    @ResponseBody
    public ContactConfirmationResponse handleRequestContactConfirmation(@RequestBody ContactConfirmationPayload contactConfirmationPayload) {
        ContactConfirmationResponse response = new ContactConfirmationResponse();
        response.setResult("true");
        return response;
    }

    @PostMapping("/approveContact")
    @ResponseBody
    public ContactConfirmationResponse handleApproveContact(@RequestBody ContactConfirmationPayload payload) {
        ContactConfirmationResponse response = new ContactConfirmationResponse();
        response.setResult("true");
        return response;
    }

    @PostMapping("/reg")
    public String handleUserRegistration(RegistrationForm registrationForm, Model model) {
        userRegisterService.registerNewUser(registrationForm);
        model.addAttribute("searchWordDto", new SearchWordDto());
        model.addAttribute("regOk", true);
        return "signin";
    }

    @PostMapping("/login")
    @ResponseBody
    public ContactConfirmationResponse handleLogin(@RequestBody ContactConfirmationPayload payload,
                                                   HttpServletRequest httpServletRequest,
                                                   HttpServletResponse httpServletResponse) {
        //todo relocate to service authservice
        Cookie tempUserCookie = WebUtils.getCookie(httpServletRequest, authTokenName);
        if (tempUserCookie == null) {
            throw new NoJwtTokenException("There is no jwt token in cookies");
        }

        String tempUserName = jwtUtil.extractUsername(tempUserCookie.getValue());
        User tempUser = userRepository.findByEmail(tempUserName);

        ContactConfirmationResponse loginResponse = userRegisterService.login(payload);

        userRegisterService.mergeTempUserDataToUser(tempUser, userRegisterService.getCurrentUser());
        userRegisterService.blockTempUser(tempUser);

        Cookie cookie = new Cookie(authTokenName, loginResponse.getResult());
        httpServletResponse.addCookie(cookie);
        return loginResponse;
    }
}
