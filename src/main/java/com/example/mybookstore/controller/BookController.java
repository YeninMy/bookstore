package com.example.mybookstore.controller;

import com.example.mybookstore.entity.Book;
import com.example.mybookstore.entity.Person;
import com.example.mybookstore.servise.BookService;
import com.example.mybookstore.servise.CommentService;
import com.example.mybookstore.servise.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookController {
    private final BookService bookService;
    private final CommentService commentService;
    private final RatingService ratingService;

    @Autowired
    public BookController(BookService bookService, CommentService commentService, RatingService ratingService) {
        this.bookService = bookService;
        this.commentService = commentService;
        this.ratingService = ratingService;
    }
    /**
     * Returns a page with specific book.
     */
    @GetMapping("/books/book/{id}")
    public String getBook(@PathVariable int id, Model model) {
        Book book = bookService.getBookById(id);
        List<Book> booksList = bookService.getBooksByGenre(book.getGenre());
        booksList.remove(book);
        List<Book> books = booksList.stream().limit(3).collect(Collectors.toList());
        model.addAttribute("book", book);
        model.addAttribute("books", books);
        return "book";
    }
    /**
     * Used to add a comment.
     */
    @PostMapping("/new-comment/{bookId}")
    public String changeBookQuantity(@PathVariable int bookId, @AuthenticationPrincipal Person user, @RequestParam String comment) {
        Book book = bookService.getBookById(bookId);
        commentService.saveComment(user, book, comment);
        return "redirect:/books/book/" + bookId;
    }
    /**
     * Used to rate a book.
     */
    @PostMapping("/rate-book/{bookId}")
    public String rateBook(@PathVariable int bookId, @AuthenticationPrincipal Person user, @RequestParam int mark) {
        Book book = bookService.getBookById(bookId);
        ratingService.rateBook(user, book, mark);

        return "redirect:/books/book/" + bookId;
    }

}