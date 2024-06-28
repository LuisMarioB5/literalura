package com.bonidev.literalura;

import com.bonidev.literalura.service.BookApiService;
import com.bonidev.literalura.service.BookMapper;
import com.bonidev.literalura.service.BookService;
import com.bonidev.literalura.service.PersonService;
import com.bonidev.literalura.view.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private BookService bookService;

	@Autowired
	private PersonService personService;

	@Autowired
	private BookApiService bookApiService;

	@Autowired
	private BookMapper bookMapper;

	public static void main(String[] args) {SpringApplication.run(LiteraluraApplication.class, args);}

	@Override
	public void run(String... args) {
		Principal principal = new Principal(bookMapper, bookService, personService, bookApiService);
		principal.showMenu();
	}
}
