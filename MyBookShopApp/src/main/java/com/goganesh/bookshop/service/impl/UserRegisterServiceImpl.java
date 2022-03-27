package com.goganesh.bookshop.service.impl;

import com.goganesh.bookshop.domain.Book2User;
import com.goganesh.bookshop.domain.User;
import com.goganesh.bookshop.domain.UserDetailsImpl;
import com.goganesh.bookshop.dto.ContactConfirmationPayload;
import com.goganesh.bookshop.dto.ContactConfirmationResponse;
import com.goganesh.bookshop.dto.RegistrationForm;
import com.goganesh.bookshop.repository.Book2UserRepository;
import com.goganesh.bookshop.repository.UserRepository;
import com.goganesh.bookshop.service.UserRegisterService;
import com.goganesh.bookshop.service.utils.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserRegisterServiceImpl implements UserRegisterService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Book2UserRepository book2UserRepository;

    @Override
    @Transactional
    public void blockTempUser(User tempUser) {
        tempUser.setEnabled(true);
        book2UserRepository.findByUser(tempUser)
                .forEach(book2User ->  {
                    book2User.setEnabled(false);
                    book2UserRepository.save(book2User);
                });

        userRepository.save(tempUser);
    }

    @Override
    @Transactional
    public void deleteBlockedTempUser(User tempUser) {
        if (!tempUser.isEnabled()) {
            book2UserRepository.findByUser(tempUser)
                    .forEach(book2UserRepository::delete);

            userRepository.delete(tempUser);
        } else {
            //todo throw some exception;
        }

    }

    @Override
    public void mergeTempUserDataToUser(User tempUser, User user){
        List<Book2User> book2UsersNew = book2UserRepository.findByUser(tempUser)
                .stream()
                .map(book2User -> {
                    Book2User book2UserNew = new Book2User();
                    book2UserNew.setBook(book2User.getBook());
                    book2UserNew.setUser(user);
                    book2UserNew.setEnabled(false);
                    book2UserNew.setBook2UserType(book2User.getBook2UserType());
                    book2UserNew.setTime(book2User.getTime());

                    return book2UserNew;
                })
                .collect(Collectors.toList());

        book2UserRepository.saveAll(book2UsersNew);
    }

    @Override
    public ContactConfirmationResponse login(ContactConfirmationPayload payload) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(payload.getContact(), payload.getCode()));
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(payload.getContact());

        String jwtToken = jwtUtil.generateToken(userDetails);
        ContactConfirmationResponse response = new ContactConfirmationResponse();
        response.setResult(jwtToken);
        return response;
    }

    @Override
    public User getCurrentUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return userDetails.getUser();
    }

    @Transactional
    @Override
    public void registerNewUser(RegistrationForm registrationForm) {

        if (userRepository.findByEmail(registrationForm.getEmail()) == null) {

            User user = new User();
            user.setName(registrationForm.getName());
            user.setHash(UUID.randomUUID().toString());//todo
            user.setRegTime(LocalDateTime.now());
            user.setEmail(registrationForm.getEmail());
            user.setPhone(registrationForm.getPhone());
            user.setPassword(passwordEncoder.encode(registrationForm.getPassword()));
            user.setEnabled(true);
            user.setRole("USER"); //todo

            userRepository.save(user);
        }
    }

    @Override
    public void registerTempUser(String email) {
        User user = new User();
        user.setName(email);
        user.setHash(UUID.randomUUID().toString());
        user.setRegTime(LocalDateTime.now());
        user.setEmail(email);
        user.setPhone(email);
        user.setPassword(email);
        user.setEnabled(true);
        user.setRole("TEMP_USER"); //todo

        userRepository.save(user);
    }
}
