package com.example.mybookstore.controller;

import com.example.mybookstore.entity.Book;
import com.example.mybookstore.servise.BookService;
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
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }
    /**
     * Change this number to change the number of books per page.
     */
    private static final int pageSize = 3;
    /**
     * Returns a page with all books.
     */
    @GetMapping("/books")
    public String getAllBooks(Model model,
                              @RequestParam(required = false) String search,
                              @RequestParam(defaultValue = "0") int page,
                              HttpSession session) {
        String sortOption = (String) session.getAttribute("sortOption");
        addBookAttributesToModel(model, search, sortOption, page, session);
        return "books";
    }
    /**
     * Returns a page with all books sorted.
     */
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
    /**
     * Used to add attributes to previous methods.
     */
    private void addBookAttributesToModel(Model model, String search, String sortOption, int page, HttpSession session) {
        if (sortOption == null) {
            sortOption = "name";
        }
        Page<Book> books = bookService.getSortedExistingBooks(search, sortOption, page, pageSize, session);
        model.addAttribute("books", books);
        model.addAttribute("search", search);
        model.addAttribute("sortOption", sortOption);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", books.getTotalPages());
    }
}
