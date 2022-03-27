package com.goganesh.bookshop;

import com.goganesh.bookshop.repository.BookRepository;
import com.goganesh.bookshop.service.BookPopularityService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class MyBookShopAppApplication implements CommandLineRunner {
	private final BookPopularityService BookPopularityService;
	private final BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(MyBookShopAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		BookPopularityService.calculateBooksPopularity(bookRepository.findAll());
	}
}
