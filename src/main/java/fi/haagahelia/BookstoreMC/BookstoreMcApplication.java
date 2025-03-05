package fi.haagahelia.BookstoreMC;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import fi.haagahelia.BookstoreMC.domain.BookstoreRepository;
import fi.haagahelia.BookstoreMC.domain.Book;
import fi.haagahelia.BookstoreMC.domain.Category;
import fi.haagahelia.BookstoreMC.domain.CategoryRepository;
import fi.haagahelia.BookstoreMC.domain.User;
import fi.haagahelia.BookstoreMC.domain.UserRepository;

@SpringBootApplication
public class BookstoreMcApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreMcApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreMcApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookstoreRepository repository, CategoryRepository cRepository,
			UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return (args) -> {
			log.info("Add some categories");

			Category category1 = new Category("Fiction");
			Category category2 = new Category("Non-fiction");
			Category category3 = new Category("History");

			cRepository.save(category1);
			cRepository.save(category2);
			cRepository.save(category3);

			repository.save(new Book("Agent Sonya", "Ben Macintyre", 2016, 771236, 20, category1));
			repository.save(new Book("Mythos", "Stephen Fry", 2009, 779461, 15, category3));
			repository.save(new Book("New Moon", "Stephanie Meyer", 2007, 774719, 25.5, category1));

			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

			User user1 = new User("user", passwordEncoder.encode("user"), "ROLE_USER", "user@test.fi");
			User user2 = new User("admin", passwordEncoder.encode("admin"), "ROLE_ADMIN", "admin@test.fi");

			userRepository.save(user1);
			userRepository.save(user2);
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
