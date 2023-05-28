package com.example.mybookstore.controller;

import com.example.mybookstore.entity.Book;
import com.example.mybookstore.entity.Person;
import com.example.mybookstore.servise.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    private final BookService bookService;

    @Autowired
    public HomeController(BookService bookService) {
        this.bookService = bookService;
    }
    /**
     * Returns a home page.
     */
    @GetMapping("/")
    public String getHomePage(Model model, @AuthenticationPrincipal Person user) {
        model.addAttribute("user", user);
        Book book = bookService.getRandomBook();
        model.addAttribute("book", book);
        return "home";
    }

}
