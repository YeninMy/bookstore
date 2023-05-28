package com.example.mybookstore.controller;

import com.example.mybookstore.entity.Book;
import com.example.mybookstore.entity.Genre;
import com.example.mybookstore.servise.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class GenreController {

    private final BookService bookService;

    /**
     * Change this number to change the number of books per page.
     */
    private static final int pageSize = 3;

    @Autowired
    public GenreController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Returns a page with books by chosen genre.
     */
    @GetMapping("/books/genre/{genre}")
    public String getBooksByGenre(@PathVariable String genre, Model model,
                                  @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Book> booksPage = bookService.getPagesBooksByGenre(Genre.valueOf(genre), pageable);
        model.addAttribute("booksPage", booksPage);
        model.addAttribute("selectedGenre", Genre.getDisplayNameForGenre(genre));
        return "genre";
    }
}
