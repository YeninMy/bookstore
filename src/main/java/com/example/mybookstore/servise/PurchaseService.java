package com.example.mybookstore.servise;

import com.example.mybookstore.entity.Book;
import com.example.mybookstore.entity.Person;
import com.example.mybookstore.entity.Purchase;
import com.example.mybookstore.repository.PurchaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {
    private final PurchaseRepo purchaseRepo;
    private final PersonService personService;
    private final BookService bookService;

    @Autowired
    public PurchaseService(PurchaseRepo purchaseRepo, PersonService personService, BookService bookService) {
        this.purchaseRepo = purchaseRepo;
        this.personService = personService;
        this.bookService = bookService;
    }

    public void savePurchase(Purchase purchase) {
        purchaseRepo.save(purchase);
    }

    public void addBookToPurchases(Person person, Book book) {

        Purchase openPurchase = purchaseRepo.findByPersonAndClosed(person, false);

        if (openPurchase == null) {
            openPurchase = new Purchase();
            openPurchase.setPerson(person);
        }
        openPurchase.getBooks().add(book);
        person.setBooksChosen(person.getBooksChosen()+1);
        personService.updatePerson(person);
        purchaseRepo.save(openPurchase);
    }
    public void removeBookFromPurchases(Person person, Book book) {

        Purchase openPurchase = purchaseRepo.findByPersonAndClosed(person, false);

        openPurchase.getBooks().remove(book);
        person.setBooksChosen(person.getBooksChosen()-1);
        bookService.saveBook(book);
        personService.updatePerson(person);
        purchaseRepo.save(openPurchase);
    }
    public void checkout(Person person) {
        Purchase openPurchase = purchaseRepo.findByPersonAndClosed(person, false);
        if (openPurchase != null) {
            openPurchase.setClosed(true);
            List<Book> boughtBooks = openPurchase.getBooks();
            for (Book book : boughtBooks) {
                book.setQuantity(book.getQuantity()-1);
                bookService.saveBook(book);

            }
            person.setBooksChosen(0);
            personService.updatePerson(person);
            purchaseRepo.save(openPurchase);

        }
    }

    public Purchase getOpenPurchaseByPerson(Person person) {

        return purchaseRepo.findByPersonAndClosed(person, false);
    }

    public List<Purchase> getClosedPurchaseByPerson(Person person) {

        return purchaseRepo.findAllByPersonAndClosed(person, true);
    }
}
