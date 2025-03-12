package fi.haagahelia.BookstoreMC;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fi.haagahelia.BookstoreMC.domain.Book;
import fi.haagahelia.BookstoreMC.domain.BookstoreRepository;
import fi.haagahelia.BookstoreMC.domain.Category;
import fi.haagahelia.BookstoreMC.domain.CategoryRepository;
import fi.haagahelia.BookstoreMC.domain.User;
import fi.haagahelia.BookstoreMC.domain.UserRepository;

@DataJpaTest
public class BookStoreJpaTest {
    @Autowired
	private BookstoreRepository bookRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Test
	public void createNewBook() {
        // Create a new book
        Book book = new Book("Title", "Author", 2025, 7728312, 25, null);
        
        // Save the book
        bookRepository.save(book);
        
        // Verify the book has an ID after saving (successful creation)
        assertThat(book.getId()).isNotNull();
        
        // Verify book can be found after creation
        assertThat(bookRepository.findById(book.getId()).isPresent()).isTrue();
        
        // Verify book properties are correct
        Book foundBook = bookRepository.findById(book.getId()).get();
        assertThat(foundBook.getTitle()).isEqualTo("Title");
        assertThat(foundBook.getAuthor()).isEqualTo("Author");
        assertThat(foundBook.getPublicationYear()).isEqualTo(2025);
        assertThat(foundBook.getIsbn()).isEqualTo("7728312");
        assertThat(foundBook.getPrice()).isEqualTo(25);
	}

	@Test
    public void deleteUser() {
        // Create a new user
        User user = new User("testuser", "password", "user@test.com", "USER");
        
        // Save the user
        userRepository.save(user);
        Long userId = user.getId();
        
        // Verify user exists
        assertThat(userRepository.findById(userId).isPresent()).isTrue();
        
        // Delete the user
        userRepository.delete(user);
        
        // Verify user no longer exists
        assertThat(userRepository.findById(userId).isPresent()).isFalse();
    }

	@Test
    public void searchCategoryByName() {
        // Create new categories
        Category category1 = new Category("Fiction");
        Category category2 = new Category("History");
    
        // Save categories
        categoryRepository.save(category1);
        categoryRepository.save(category2);
        
        // Search by name
        List<Category> fictionCategories = categoryRepository.findByName("Fiction");
        List<Category> nonFictionCategories = categoryRepository.findByName("History");
    }


}

