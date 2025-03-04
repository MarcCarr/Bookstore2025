package fi.haagahelia.BookstoreMC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fi.haagahelia.BookstoreMC.domain.Book;
import fi.haagahelia.BookstoreMC.domain.BookstoreRepository;
import fi.haagahelia.BookstoreMC.domain.CategoryRepository;

@Controller
public class BookController {
    @Autowired
    private BookstoreRepository repository;

    @Autowired
    private CategoryRepository cRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
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
        model.addAttribute("categories", cRepository.findAll());
        return "addbook";
    }

    // Save new book into list
    @PostMapping("/save")
    public String saveNewBook(@ModelAttribute Book book) {
        repository.save(book);
        return "redirect:/booklist";
    }

    // Deleting a book
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id, Model model) {
        repository.deleteById(id);
        return "redirect:/booklist";
    }

    // Edit existing book
    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", repository.findById(id).get()); // .get() Extracts actual Book. If no book is found
                                                                   // -> Error
        return "editbook";
    }

    @PostMapping("/save/{id}")
    public String saveEditedBook(@PathVariable("id") Long id, @ModelAttribute Book book) {
        book.setId(id);
        repository.save(book);
        return ("redirect:/booklist");
    }

}
