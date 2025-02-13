package fi.haagahelia.BookstoreMC.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import fi.haagahelia.BookstoreMC.domain.Book;
import fi.haagahelia.BookstoreMC.domain.BookstoreRepository;

@RestController
public class BookRestController {

    private BookstoreRepository bookRepository;

    // Constructor Injection
    public BookRestController(BookstoreRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // RESTful service to get all students
    @GetMapping("/books")
    public List<Book> bookListRest() {
        return (List<Book>) bookRepository.findAll();
    }

    // RESTful service to get student by id
    @GetMapping("/books/{id}")
    public Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
        return bookRepository.findById(bookId);
    }

    // RESTful service to save new student
    @PostMapping("/books")
    public Book saveNewBookRest(@RequestBody Book book) {
        return bookRepository.save(book);
    }
}
