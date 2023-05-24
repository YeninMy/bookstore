package com.example.mybookstore.controller;

import com.example.mybookstore.entity.Book;
import com.example.mybookstore.entity.Person;
import com.example.mybookstore.entity.Wishlist;
import com.example.mybookstore.servise.BookService;
import com.example.mybookstore.servise.PurchaseService;
import com.example.mybookstore.servise.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class WishlistController {

    private final WishlistService wishlistService;
    private final BookService bookService;
    private final PurchaseService purchaseService;
    @Autowired
    public WishlistController(WishlistService wishlistService, BookService bookService, PurchaseService purchaseService) {
        this.wishlistService = wishlistService;
        this.bookService = bookService;

        this.purchaseService = purchaseService;
    }

    @GetMapping("/wishlist")
    public String wishlist(Model model, @AuthenticationPrincipal Person person) {
        Wishlist wishlist = wishlistService.getWishlistByPerson(person);
        if(wishlist == null){
            wishlist = new Wishlist();
            wishlist.setPerson(person);
            wishlistService.saveWishlist(wishlist);
        }
        List<Book> books = wishlist.getBooks();
        model.addAttribute("books", books);
        return "wishlist";
    }

    @PostMapping("/wishlist/add/{bookId}")
    public String addToWishlist(@AuthenticationPrincipal Person person, @PathVariable int bookId) {
        Book book = bookService.getBookById(bookId);
        wishlistService.addBookToWishlist(person,book);
        return "redirect:/books/book/" + bookId;
    }
    @PostMapping ("/wishlist/add-book/{bookId}")
    public String addBookToWishlist(@AuthenticationPrincipal Person person, @PathVariable int bookId) {
        Book book = bookService.getBookById(bookId);
        wishlistService.addBookToWishlist(person,book);
        return "redirect:/books";
    }
    @PostMapping ("/wishlist/remove/{bookId}")
    public String removeFromWishlist(@AuthenticationPrincipal Person person, @PathVariable int bookId) {
        Book book = bookService.getBookById(bookId);
        wishlistService.removeBookFromWishlist(person,book);
        return "redirect:/wishlist";
    }

    @PostMapping ("/wishlist/cart/add-book/{bookId}")
    public String addToPurchases(@AuthenticationPrincipal Person person, @PathVariable int bookId) {
        Book book = bookService.getBookById(bookId);
        purchaseService.addBookToPurchases(person,book);
        wishlistService.removeBookFromWishlist(person,book);
        return "redirect:/wishlist";
    }


}
