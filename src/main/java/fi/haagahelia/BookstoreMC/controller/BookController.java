package fi.haagahelia.BookstoreMC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {
    @GetMapping("/index")
    public String showBookstore() {
        return "Welcome to the bookstore";
    }
}
