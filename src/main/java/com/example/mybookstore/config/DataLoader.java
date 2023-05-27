package com.example.mybookstore.config;

import com.example.mybookstore.entity.*;
import com.example.mybookstore.servise.BookService;

import javax.annotation.PostConstruct;

import com.example.mybookstore.servise.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.EnumSet;

@Service
public class DataLoader {
    private final BookService bookService;
    private final PersonService personService;

    @Autowired
    public DataLoader(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

    @PostConstruct
    public void addPreparedListOfBooks() {
        Author frankHerbert = new Author("Frank", "Herbert");
        Author georgeOrwell = new Author("George", "Orwell");
        Author aldousHuxley = new Author("Aldous", "Huxley");
        Author jrrTolkien = new Author("J.R.R.", "Tolkien");
        Author jkRowling = new Author("J.K.", "Rowling");
        Author danBrown = new Author("Dan", "Brown");
        Author stiegLarsson = new Author("Stieg", "Larsson");
        Author gillianFlynn = new Author("Gillian", "Flynn");
        Author thomasHarris = new Author("Thomas", "Harris");
        Author michaelCrichton = new Author("Michael", "Crichton");
        Author stephenKing = new Author("Stephen", "King");
        Author janeAusten = new Author("Jane", "Austen");
        Author charlotteBronte = new Author("Charlotte", "Bronte");
        Author emilyBronte = new Author("Emily", "Bronte");
        Author bramStoker = new Author("Bram", "Stoker");
        Author maryShelley = new Author("Mary", "Shelley");
        Author fScottFitzgerald = new Author("F. Scott", "Fitzgerald");
        Author harperLee = new Author("Harper", "Lee");

        bookService.saveBook(new Book("Dune", Collections.singletonList(frankHerbert),
                Genre.SCIENCE_FICTION, 35.0, 50, "https://drive.google.com/file/d/10Js8xNSBc_qaoVQzAd19akE3npBeqc6I/view?usp=share_link"));
        bookService.saveBook(new Book("1984", Collections.singletonList(georgeOrwell),
                Genre.SCIENCE_FICTION, 45.0, 80,"https://drive.google.com/file/d/114Fb2MyoRcFbL1WGQGXyRjD7V3vWD46u/view?usp=share_link"));
        bookService.saveBook(new Book("Brave New World", Collections.singletonList(aldousHuxley),
                Genre.SCIENCE_FICTION, 40.0, 70,"https://drive.google.com/file/d/1mPJMlR21Opkp-1FP43In_Dgk2Y5d5G8j/view?usp=share_link"));
        bookService.saveBook(new Book("The Lord of the Rings", Collections.singletonList(jrrTolkien),
                Genre.FANTASY, 50.0, 90,"https://drive.google.com/file/d/1rurQghNPNoNWUX3KQfhSfW8UPcFBVPqu/view?usp=share_link"));
        bookService.saveBook(new Book("Harry Potter and the Philosopher's Stone", Collections.singletonList(jkRowling),
                Genre.FANTASY, 30.0, 60,"https://drive.google.com/file/d/1uGiDka-xAaeWCClmkGx2k3phK2tu9XRz/view?usp=share_link"));
        bookService.saveBook(new Book("The Hobbit", Collections.singletonList(jrrTolkien),
                Genre.FANTASY, 45.0, 80,"https://drive.google.com/file/d/1kwzQ-qPlOG25yhnB-51uMoc1dVkgxAi_/view?usp=share_link"));
        bookService.saveBook(new Book("The Da Vinci Code", Collections.singletonList(danBrown),
                Genre.MYSTERY, 35.0, 50,"https://drive.google.com/file/d/1ufOVGlfTANnfsljBA0P-Og1ESbVZpXLg/view?usp=share_link"));
        bookService.saveBook(new Book("The Girl with the Dragon Tattoo", Collections.singletonList(stiegLarsson),
                Genre.MYSTERY, 40.0, 70,"https://drive.google.com/file/d/1PaDkm1T0EGLCS2mJ4ZcVYW4Pb5H_1d3Y/view?usp=share_link"));
        bookService.saveBook(new Book("Gone Girl", Collections.singletonList(gillianFlynn),
                Genre.MYSTERY, 50.0, 90,"https://drive.google.com/file/d/10DlXxkfi38Tsr8GfYEaLvcD1A2skpSlo/view?usp=share_link"));
        bookService.saveBook(new Book("The Silence of the Lambs", Collections.singletonList(thomasHarris),
                Genre.THRILLER, 30.0, 60,"https://drive.google.com/file/d/19juDHkipiQ8mqkKwQkU-B3CO7ZtUH4wq/view?usp=share_link"));
        bookService.saveBook(new Book("Jurassic Park", Collections.singletonList(michaelCrichton),
                Genre.THRILLER, 45.0, 80,"https://drive.google.com/file/d/1nT4oTqhSv6EnFcsH0X670aOMyWXsHXQ9/view?usp=share_link"));
        bookService.saveBook(new Book("The Shining", Collections.singletonList(stephenKing),
                Genre.THRILLER, 35.0, 50,"https://drive.google.com/file/d/14PxzockHKCXSbjJ7_x5Bg8vnScc7FWEh/view?usp=share_link"));
        bookService.saveBook(new Book("Pride and Prejudice", Collections.singletonList(janeAusten),
                Genre.ROMANCE, 40.0, 70,"https://drive.google.com/file/d/15Dclld55S-lo0F6lZ32WR43IMyWv776f/view?usp=share_link"));
        bookService.saveBook(new Book("Jane Eyre", Collections.singletonList(charlotteBronte),
                Genre.ROMANCE, 50.0, 90,"https://drive.google.com/file/d/1Qlc9OWM2T3Y_iOS7QF1JP1f8ucB03XBc/view?usp=share_link"));
        bookService.saveBook(new Book("Wuthering Heights", Collections.singletonList(emilyBronte),
                Genre.ROMANCE, 35.0, 50,"https://drive.google.com/file/d/1u8RHCxUb41vV1TkuWD__190tFhfTlwMm/view?usp=share_link"));
        bookService.saveBook(new Book("Dracula", Collections.singletonList(bramStoker),
                Genre.HORROR, 45.0, 80,"https://drive.google.com/file/d/1545lK9I6ZxAl921jTIRd-U90Jyi_kG3D/view?usp=share_link"));
        bookService.saveBook(new Book("Frankenstein", Collections.singletonList(maryShelley),
                Genre.HORROR, 30.0, 60,"https://drive.google.com/file/d/1e6RU00LKR5q7B2SKS1RCids9sT9bpicd/view?usp=share_link"));
        bookService.saveBook(new Book("It", Collections.singletonList(stephenKing),
                Genre.HORROR, 40.0, 70,"https://drive.google.com/file/d/1v383Fj8DdLmF5D6ovCmOPjCqv71LAl5U/view?usp=share_link"));
        bookService.saveBook(new Book("The Great Gatsby", Collections.singletonList(fScottFitzgerald),
                Genre.HISTORICAL_FICTION, 50.0, 90,"https://drive.google.com/file/d/1267REgqNs35K-TrGgBSg8WNonBxXY1n7/view?usp=share_link"));
        bookService.saveBook(new Book("To Kill a Mockingbird", Collections.singletonList(harperLee),
                Genre.HISTORICAL_FICTION, 35.0, 50,"https://drive.google.com/file/d/1cfmkurLu25WFrt7eHeR06hZfoiiuugpK/view?usp=share_link"));

        Person admin = new Person("Misha", "vbif555666@gmail.com", "stale555666",
                EnumSet.of(Role.USER));
        personService.savePerson(admin);
        personService.setAdminRoleToPerson(admin);
        Person user = new Person("Sveta", "stale@meta.ua", "stale555666",
                EnumSet.of(Role.USER));
        personService.savePerson(user);
    }
}
