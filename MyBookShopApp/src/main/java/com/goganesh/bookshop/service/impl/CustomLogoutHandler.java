package com.goganesh.bookshop.service.impl;

import com.goganesh.bookshop.domain.InvalidToken;
import com.goganesh.bookshop.repository.InvalidTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

@Service
public class CustomLogoutHandler implements LogoutHandler {

    private final String authTokenName;
    private final InvalidTokenRepository invalidTokenRepository;

    public CustomLogoutHandler(@Value("${auth.token.name}")String authTokenName,
                               InvalidTokenRepository invalidTokenRepository) {
        this.authTokenName = authTokenName;
        this.invalidTokenRepository = invalidTokenRepository;
    }

    @Override
    @Transactional
    public void logout(HttpServletRequest request, HttpServletResponse response,
                       Authentication authentication) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(authTokenName)) {
                invalidTokenRepository.save(new InvalidToken(cookie.getValue()));
            }
        }

    }
}
