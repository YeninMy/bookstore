package com.example.mybookstore.servise;

import com.example.mybookstore.entity.Book;
import com.example.mybookstore.entity.Person;
import com.example.mybookstore.entity.Wishlist;
import com.example.mybookstore.repository.WishlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {
    private final WishlistRepo wishlistRepo;
    private final PersonService personService;

    @Autowired
    public WishlistService(WishlistRepo wishlistRepo, PersonService personService) {
        this.wishlistRepo = wishlistRepo;
        this.personService = personService;
    }

    public void saveWishlist(Wishlist wishlist) {
        wishlistRepo.save(wishlist);
    }

    public void addBookToWishlist(Person person, Book book) {

        Wishlist wishlist = wishlistRepo.findByPerson(person);

        if (wishlist == null) {
            wishlist = new Wishlist();
            wishlist.setPerson(person);
        }
        wishlist.getBooks().add(book);
        person.setBooksWanted(person.getBooksWanted() + 1);
        personService.updatePerson(person);
        wishlistRepo.save(wishlist);
    }

    public void removeBookFromWishlist(Person person, Book book) {
        Wishlist wishlist = wishlistRepo.findByPerson(person);
        wishlist.getBooks().remove(book);
        person.setBooksWanted(person.getBooksWanted() - 1);
        personService.updatePerson(person);
        wishlistRepo.save(wishlist);
    }

    public Wishlist getWishlistByPerson(Person person) {
        return wishlistRepo.findByPerson(person);
    }
}
