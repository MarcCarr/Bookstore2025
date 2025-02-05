package fi.haagahelia.BookstoreMC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fi.haagahelia.BookstoreMC.domain.Book;
import fi.haagahelia.BookstoreMC.domain.BookstoreRepository;

@Controller
public class BookController {
    private BookstoreRepository repository;

    public BookController(BookstoreRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/booklist")
    public String booklist(Model model) {
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }

    // Open add new book form
    @GetMapping("/add")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }

    // Save new book into list
    @PostMapping("/save")
    public String saveNewBook(@ModelAttribute Book book) {
        repository.save(book);
        return "redirect:/booklist";
    }

    // Deleting a book
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id, Model model) {
        repository.deleteById(id);
        return "redirect:/booklist";
    }
}
