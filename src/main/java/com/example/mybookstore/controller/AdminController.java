package com.example.mybookstore.controller;

import com.example.mybookstore.entity.Book;
import com.example.mybookstore.entity.Comment;
import com.example.mybookstore.entity.Person;
import com.example.mybookstore.servise.BookService;
import com.example.mybookstore.servise.CommentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    private final BookService bookService;
    private final CommentService commentService;

    public AdminController(BookService bookService, CommentService commentService) {
        this.bookService = bookService;
        this.commentService = commentService;
    }
    /**
     * Returns a page with an admin panel for adding books.
     */
    @GetMapping("/admin-panel")
    public String getAdminPage(@AuthenticationPrincipal Person user) {
        if (user == null || !user.isAdmin()) {
            return "home";
        }
        return "admin";
    }
    /**
     * Used to add books to the database.
     */
    @PostMapping("/add-book")
    public String addBook(@ModelAttribute Book book, @AuthenticationPrincipal Person user) {
        if (user == null || !user.isAdmin()) {
            return "redirect:/";
        }
        bookService.saveBook(book);
        return "redirect:/admin-panel";
    }
    /**
     * Used to change the book price.
     */
    @PostMapping("/change-price/{bookId}")
    public String changeBookPrice(@PathVariable int bookId, @RequestParam double newPrice, @AuthenticationPrincipal Person user) {
        if (user == null || !user.isAdmin()) {
            return "redirect:/";
        }
        bookService.updateBookPrice(bookId, newPrice);
        return "redirect:/books";
    }
    /**
     * Used to change the book quantity.
     */
    @PostMapping("/change-quantity/{bookId}")
    public String changeBookQuantity(@PathVariable int bookId, @RequestParam int newQuantity, @AuthenticationPrincipal Person user) {
        if (user == null || !user.isAdmin()) {
            return "redirect:/";
        }
        bookService.updateBookQuantity(bookId, newQuantity);
        return "redirect:/books";
    }
    /**
     * Used to change the book picture.
     */
    @PostMapping("/change-image/{bookId}")
    public String changeBookImage(@PathVariable int bookId, @RequestParam String newImage, @AuthenticationPrincipal Person user) {
        if (user == null || !user.isAdmin()) {
            return "redirect:/";
        }
        bookService.updateBookImage(bookId, newImage);
        return "redirect:/books";
    }
    /**
     * Used to set the quantity of books to 0.
     */
    @PostMapping("/delete-book/{bookId}")
    public String deleteBook(@PathVariable int bookId, @AuthenticationPrincipal Person user) {
        if (user == null || !user.isAdmin()) {
            return "redirect:/";
        }
        bookService.updateBookQuantity(bookId, 0);
        return "redirect:/books";
    }
    /**
     * Used to delete a comment..
     */
    @PostMapping("/delete-comment")
    public String deleteComment(@RequestParam int bookId, @RequestParam int commentId, @AuthenticationPrincipal Person user) {
        if (user == null || !user.isAdmin()) {
            return "redirect:/";
        }
        Comment comment = commentService.getCommentById(commentId);
        commentService.deleteComment(comment);
        return "redirect:/books/book/" + bookId;
    }
}
