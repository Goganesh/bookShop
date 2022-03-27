package com.goganesh.bookshop.dto;

import com.goganesh.bookshop.domain.Book;
import com.goganesh.bookshop.domain.Book2Author;
import lombok.Data;

import java.util.Comparator;
import java.util.stream.Collectors;

@Data
public class BookPageDto {

    private String slug;
    private String title;
    private String description;
    private String image;
    private String authors;
    private double discount;
    private int rating;
    private boolean isBestseller;
    private String status;
    private int price;
    private int discountPrice;

    public BookPageDto(Book book, int rating) {
        this.slug = book.getSlug();
        this.title = book.getTitle();
        this.description = book.getDescription();
        this.image = book.getImage();
        this.authors = book.getBook2AuthorLIst()
                .stream()
                .sorted((Comparator.comparingInt(Book2Author::getSortIndex)))
                .map(book2Author -> book2Author.getAuthor().getName())
                .collect(Collectors.joining(", "));
        this.discount = book.getDiscount();
        this.rating = rating;
        this.isBestseller = book.isBestseller();
        //todo
        this.status = null;
        this.price = book.getPrice();
        this.discountPrice = book.getPrice() - Math.toIntExact(Math.round(book.getDiscount() * book.getPrice()));
    }
}
