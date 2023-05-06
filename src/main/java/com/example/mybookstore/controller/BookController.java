package com.example.mybookstore.controller;

import com.example.mybookstore.entity.Book;
import com.example.mybookstore.entity.Genre;
import com.example.mybookstore.servise.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String getAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/books/genre/{genre}")
    public String getBooksByGenre(@PathVariable String genre, Model model) {
        List<Book> books = bookService.getBooksByGenre(Genre.valueOf(genre));
        model.addAttribute("books", books);
        model.addAttribute("selectedGenre", Genre.getDisplayNameForGenre(genre));
        return "genre";
    }
    @PostMapping("/add-book")
    public String addBook(Book book) {
        bookService.saveBook(book);
        return "redirect:/";
    }

    @GetMapping("/books/book/{id}")
    public String getBooksByGenre(@PathVariable int id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "book";
    }


}