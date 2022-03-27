package com.goganesh.bookshop.filter;

import com.goganesh.bookshop.domain.UserDetailsImpl;
import com.goganesh.bookshop.service.UserRegisterService;
import com.goganesh.bookshop.service.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

@Component
public class TempUserRequestFilter extends OncePerRequestFilter {

    private String authTokenName;
    private UserDetailsService userDetailsService;
    private JWTUtil jwtUtil;
    private UserRegisterService userRegisterService;

    @Autowired
    public void setAuthTokenName(@Value("${auth.token.name}")String authTokenName) {
        this.authTokenName = authTokenName;
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setJwtUtil(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Autowired
    public void setUserService(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        Cookie[] cookies = httpServletRequest.getCookies();

        if (cookies == null || Arrays.stream(cookies).noneMatch(cookie -> cookie.getName().equals(authTokenName))) {

            String tempUserMail = "temp_" + UUID.randomUUID() + "@test.com";
            userRegisterService.registerTempUser(tempUserMail);

            UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(tempUserMail);
            String jwtToken = jwtUtil.generateToken(userDetails);
            httpServletResponse.addCookie(new Cookie(authTokenName, jwtToken));
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
