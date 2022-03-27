package com.goganesh.bookshop.service.impl;

import com.goganesh.bookshop.domain.Book;
import com.goganesh.bookshop.domain.Book2User;
import com.goganesh.bookshop.domain.Book2UserType;
import com.goganesh.bookshop.domain.User;
import com.goganesh.bookshop.repository.Book2UserRepository;
import com.goganesh.bookshop.repository.Book2UserTypeRepository;
import com.goganesh.bookshop.service.BookAction;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UnlinkBookAction implements BookAction {

    private final User user;
    private final List<Book> books;
    private final Book2UserRepository book2UserRepository;
    private final Book2UserTypeRepository book2UserTypeRepository;

    @Override
    public void execute() {

        Book2UserType kept = book2UserTypeRepository.findByCode("KEPT");//todo
        Book2UserType cart = book2UserTypeRepository.findByCode("CART");//todo

        List<Book2User> keptBook2Users = books.stream()
                .map(book -> book2UserRepository.findByUserAndBook(user, book))
                .flatMap(Collection::stream)
                .filter(book2User -> book2User.getBook2UserType().equals(kept) || book2User.getBook2UserType().equals(cart))
                .collect(Collectors.toList());

        book2UserRepository.deleteAll(keptBook2Users);
    }
}
