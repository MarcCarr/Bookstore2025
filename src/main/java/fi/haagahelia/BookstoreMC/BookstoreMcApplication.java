package fi.haagahelia.BookstoreMC;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.BookstoreMC.domain.BookstoreRepository;

@SpringBootApplication
public class BookstoreMcApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreMcApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookstoreRepository repository) {
		return (args) -> {
			// Your code...add some demo data to db
		};
	}

}
