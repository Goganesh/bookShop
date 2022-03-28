package com.goganesh.bookshop.filter;

import com.goganesh.bookshop.domain.UserDetailsImpl;
import com.goganesh.bookshop.repository.InvalidTokenRepository;
import com.goganesh.bookshop.service.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    private final String authTokenName;
    private final UserDetailsService userDetailsService;
    private final JWTUtil jwtUtil;
    private final InvalidTokenRepository invalidTokenRepository;

    public JWTRequestFilter(@Value("${auth.token.name}")String authTokenName,
                            UserDetailsService userDetailsService,
                            JWTUtil jwtUtil,
                            InvalidTokenRepository invalidTokenRepository) {
        this.authTokenName = authTokenName;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.invalidTokenRepository = invalidTokenRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = null;
        String username = null;
        Cookie[] cookies = httpServletRequest.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(authTokenName)) {
                    token = cookie.getValue();
                    if (invalidTokenRepository.findByToken(token).isEmpty()) {
                        username = jwtUtil.extractUsername(token);
                    }
                }

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
                    if (jwtUtil.validateToken(token, userDetails)) {
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
