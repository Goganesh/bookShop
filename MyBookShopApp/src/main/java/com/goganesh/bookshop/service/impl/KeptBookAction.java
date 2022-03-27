package com.goganesh.bookshop.service.impl;

import com.goganesh.bookshop.domain.Book;
import com.goganesh.bookshop.domain.Book2User;
import com.goganesh.bookshop.domain.User;
import com.goganesh.bookshop.repository.Book2UserRepository;
import com.goganesh.bookshop.repository.Book2UserTypeRepository;
import com.goganesh.bookshop.service.BookAction;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class KeptBookAction implements BookAction {

    private final User user;
    private final List<Book> books;
    private final Book2UserRepository book2UserRepository;
    private final Book2UserTypeRepository book2UserTypeRepository;

    @Override
    public void execute() {

        List<Book2User> book2UsersKept = new ArrayList<>();

        for (Book book : books) {
             List<Book2User> book2Users = book2UserRepository.findByUserAndBook(user, book);
             if (book2Users.isEmpty()) {
                 book2UsersKept.add(createNewKeptBook2User(user, book));
             } else {
                 //todo what if i found: same type, or different type
             }
        }

        book2UserRepository.saveAll(book2UsersKept);
    }

    private Book2User createNewKeptBook2User(User user, Book book) {
        Book2User kept = new Book2User();
        kept.setBook(book);
        kept.setTime(LocalDateTime.now());
        kept.setBook2UserType(book2UserTypeRepository.findByCode("KEPT")); //todo
        kept.setEnabled(true);
        kept.setUser(user);
        return kept;
    }
}
