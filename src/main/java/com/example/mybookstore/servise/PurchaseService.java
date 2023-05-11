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

    @Autowired
    public PurchaseService(PurchaseRepo purchaseRepo, PersonService personService) {
        this.purchaseRepo = purchaseRepo;
        this.personService = personService;
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
        personService.updatePerson(person);
        purchaseRepo.save(openPurchase);
    }
    public Purchase checkout(Person person) {
        Purchase openPurchase = purchaseRepo.findByPersonAndClosed(person, false);
        if (openPurchase != null) {
            openPurchase.setClosed(true);
            person.setBooksChosen(0);
            personService.updatePerson(person);
            return purchaseRepo.save(openPurchase);
        }

        return null;
    }

    public Purchase getOpenPurchaseByPerson(Person person) {

        return purchaseRepo.findByPersonAndClosed(person, false);
    }

    public List<Purchase> getClosedPurchaseByPerson(Person person) {

        return purchaseRepo.findAllByPersonAndClosed(person, true);
    }
}
