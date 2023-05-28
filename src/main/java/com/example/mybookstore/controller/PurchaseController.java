package com.example.mybookstore.controller;

import com.example.mybookstore.entity.Book;
import com.example.mybookstore.entity.Person;
import com.example.mybookstore.entity.Purchase;
import com.example.mybookstore.servise.BookService;
import com.example.mybookstore.servise.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class PurchaseController {
    private final PurchaseService purchaseService;
    private final BookService bookService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService, BookService bookService) {
        this.purchaseService = purchaseService;
        this.bookService = bookService;
    }

    /**
     * Returns a cart page.
     */
    @GetMapping("/cart")
    public String cart(Model model, @AuthenticationPrincipal Person person) {
        Purchase purchase = purchaseService.getOpenPurchaseByPerson(person);
        if (purchase == null) {
            purchase = new Purchase();
            purchase.setPerson(person);

            purchaseService.savePurchase(purchase);
        }
        List<Book> books = purchase.getBooks();
        double totalPrice = books.stream().mapToDouble(Book::getPrice).sum();
        model.addAttribute("books", books);
        model.addAttribute("totalPrice", totalPrice);
        return "cart";
    }

    /**
     * Used to add a book to the cart from a page with a specific book.
     */
    @PostMapping("/cart/add/{bookId}")
    public String addToPurchases(@AuthenticationPrincipal Person person, @PathVariable int bookId) {
        Book book = bookService.getBookById(bookId);
        purchaseService.addBookToPurchases(person, book);
        return "redirect:/books/book/" + bookId;
    }

    /**
     * Used to add a book to the cart from a page with all books.
     */
    @PostMapping("/cart/add-book/{bookId}")
    public String addBookToPurchases(@AuthenticationPrincipal Person person, @PathVariable int bookId) {
        Book book = bookService.getBookById(bookId);
        purchaseService.addBookToPurchases(person, book);
        return "redirect:/books";
    }
    /**
     * Used to add a book to the cart from a page with books by chosen genre.
     */
    @PostMapping("/cart/add-book-genre/{bookId}")
    public String addBookByGenreToPurchases(@AuthenticationPrincipal Person person, @PathVariable int bookId, @RequestParam String selectedGenre, RedirectAttributes redirectAttributes) {
        Book book = bookService.getBookById(bookId);
        purchaseService.addBookToPurchases(person, book);
        redirectAttributes.addAttribute("genre", selectedGenre);
        return "redirect:/books/genre/{genre}";
    }
    /**
     * Used to remove book from cart.
     */
    @PostMapping("/cart/remove/{bookId}")
    public String removeFromPurchases(@AuthenticationPrincipal Person person, @PathVariable int bookId) {
        Book book = bookService.getBookById(bookId);
        purchaseService.removeBookFromPurchases(person, book);
        return "redirect:/cart";
    }
    /**
     * Used to make a purchase.
     */
    @PostMapping("/cart/checkout")
    public String checkout(@AuthenticationPrincipal Person person) {
        Purchase purchase = purchaseService.getOpenPurchaseByPerson(person);
        purchase.setDate(LocalDate.now());
        purchaseService.checkout(person);
        purchaseService.savePurchase(purchase);
        return "redirect:/";
    }
    /**
     * Returns a page with closed purchases.
     */
    @GetMapping("/purchases")
    public String purchases(Model model, @AuthenticationPrincipal Person person) {
        List<Purchase> purchases = purchaseService.getClosedPurchaseByPerson(person);
        model.addAttribute("purchases", purchases);
        return "purchases";
    }
}

