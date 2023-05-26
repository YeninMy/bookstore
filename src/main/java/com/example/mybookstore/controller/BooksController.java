package com.example.mybookstore.controller;

import com.example.mybookstore.entity.Book;
import com.example.mybookstore.servise.BookService;
import com.example.mybookstore.servise.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;


@Controller
@SessionAttributes("books")
public class BooksController {
    private final BookService bookService;

    @Autowired
    public BooksController(BookService bookService, CommentService commentService) {
        this.bookService = bookService;
    }

    private static final int pageSize = 3;

    @GetMapping("/books")
    public String getAllBooks(Model model,
                              @RequestParam(required = false) String search,
                              @RequestParam(defaultValue = "0") int page,
                              HttpSession session) {
        String sortOption = (String) session.getAttribute("sortOption");
        addBookAttributesToModel(model, search, sortOption, page, session);
        return "books";
    }

    @GetMapping("/books/sort")
    public String sortBooks(Model model,
                            @RequestParam(required = false) String sortOption,
                            @RequestParam(defaultValue = "0") int page,
                            HttpSession session) {
        session.setAttribute("sortOption", sortOption);
        String search = (String) session.getAttribute("search");
        addBookAttributesToModel(model, search, sortOption, page, session);
        return "books";
    }

    private void addBookAttributesToModel(Model model, String search, String sortOption, int page, HttpSession session) {
        Page<Book> books = bookService.getSortedExistingBooks(search, sortOption, page, pageSize, session);
        model.addAttribute("books", books);
        model.addAttribute("search", search);
        model.addAttribute("sortOption", sortOption);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", books.getTotalPages());
    }

}
