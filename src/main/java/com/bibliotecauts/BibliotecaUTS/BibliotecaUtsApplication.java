package com.bibliotecauts.BibliotecaUTS;

import com.bibliotecauts.BibliotecaUTS.Entities.Book;
import com.bibliotecauts.BibliotecaUTS.Entities.User;
import com.bibliotecauts.BibliotecaUTS.Repository.BookRepository;
import com.bibliotecauts.BibliotecaUTS.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BibliotecaUtsApplication {

	public static void main(String[] args) {

		SpringApplication.run(BibliotecaUtsApplication.class, args);

	}
	@Bean
	CommandLineRunner init(UserRepository userRepository, BookRepository bookRepository) {
		return args -> {
			User user = new User();
			user.setEmail("user@test.com");
			user.setPassword("user");
			userRepository.save(user);

			Book book1 = new Book();
			book1.setTitle("Libro ejemplo");
			book1.setAuthor("autor");
			book1.setUser(user);
			bookRepository.save(book1);
		};
	}

}
