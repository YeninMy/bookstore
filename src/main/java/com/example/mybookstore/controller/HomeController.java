package com.example.mybookstore.controller;

import com.example.mybookstore.entity.Book;
import com.example.mybookstore.entity.Person;
import com.example.mybookstore.servise.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;

import org.springframework.security.authentication.AuthenticationTrustResolver;

import java.security.Principal;


@Controller
public class HomeController {

    private final BookService bookService;

    @Autowired
    public HomeController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("/")
    public String getHomePage(Model model, @AuthenticationPrincipal Person person) {
model.addAttribute("person", person);
        Book book = bookService.getRandomBook();
        model.addAttribute("book", book);
        return "home";
    }

}
