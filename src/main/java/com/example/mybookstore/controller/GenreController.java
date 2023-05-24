package com.example.mybookstore.controller;

import com.example.mybookstore.entity.Book;
import com.example.mybookstore.entity.Genre;
import com.example.mybookstore.servise.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class GenreController {

    private final BookService bookService;

    @Autowired
    public GenreController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books/genre/{genre}")
    public String getBooksByGenre(@PathVariable String genre, Model model) {

        List<Book> books = bookService.getBooksByGenre(Genre.valueOf(genre));
        model.addAttribute("books", books);
        model.addAttribute("selectedGenre", Genre.getDisplayNameForGenre(genre));
        return "genre";
    }
}
