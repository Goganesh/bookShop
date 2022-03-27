package com.goganesh.bookshop.service.impl;

import com.goganesh.bookshop.domain.User;
import com.goganesh.bookshop.repository.UserRepository;
import com.goganesh.bookshop.domain.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(s);
        if(user!=null){
            return new UserDetailsImpl(user);
        }else{
            throw new UsernameNotFoundException("user not found doh!"); //todo write correct text
        }
    }
}
