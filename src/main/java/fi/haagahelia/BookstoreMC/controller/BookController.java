package fi.haagahelia.BookstoreMC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

}
