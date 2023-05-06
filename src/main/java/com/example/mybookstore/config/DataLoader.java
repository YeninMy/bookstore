package com.example.mybookstore.config;


import com.example.mybookstore.entity.*;
import com.example.mybookstore.servise.BookService;

import javax.annotation.PostConstruct;

import com.example.mybookstore.servise.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

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
        bookService.saveBook(new Book("Dune", new Author("Frank Herbert"), Genre.SCIENCE_FICTION, 35.0, 50,"dune-cover.jpg"));
        bookService.saveBook(new Book("1984", new Author("George Orwell"), Genre.SCIENCE_FICTION, 45.0, 80));
        bookService.saveBook(new Book("Brave New World", new Author("Aldous Huxley"), Genre.SCIENCE_FICTION, 40.0, 70));
        bookService.saveBook(new Book("The Lord of the Rings", new Author("J.R.R. Tolkien"), Genre.FANTASY, 50.0, 90));
        bookService.saveBook(new Book("Harry Potter and the Philosopher's Stone", new Author("J.K. Rowling"), Genre.FANTASY, 30.0, 60));
        bookService.saveBook(new Book("The Hobbit", new Author("J.R.R. Tolkien"), Genre.FANTASY, 45.0, 80));
        bookService.saveBook(new Book("The Da Vinci Code", new Author("Dan Brown"), Genre.MYSTERY, 35.0, 50));
        bookService.saveBook(new Book("The Girl with the Dragon Tattoo", new Author("Stieg Larsson"), Genre.MYSTERY, 40.0, 70));
        bookService.saveBook(new Book("Gone Girl", new Author("Gillian Flynn"), Genre.MYSTERY, 50.0, 90));
        bookService.saveBook(new Book("The Silence of the Lambs", new Author("Thomas Harris"), Genre.THRILLER, 30.0, 60));
        bookService.saveBook(new Book("Jurassic Park", new Author("Michael Crichton"), Genre.THRILLER, 45.0, 80));
        bookService.saveBook(new Book("The Shining", new Author("Stephen King"), Genre.THRILLER, 35.0, 50));
        bookService.saveBook(new Book("Pride and Prejudice", new Author("Jane Austen"), Genre.ROMANCE, 40.0, 70));
        bookService.saveBook(new Book("Jane Eyre", new Author("Charlotte Bronte"), Genre.ROMANCE, 50.0, 90));
        bookService.saveBook(new Book("Wuthering Heights", new Author("Emily Bronte"), Genre.ROMANCE, 35.0, 50));
        bookService.saveBook(new Book("Dracula", new Author("Bram Stoker"), Genre.HORROR, 45.0, 80));
        bookService.saveBook(new Book("Frankenstein", new Author("Mary Shelley"), Genre.HORROR, 30.0, 60));
        bookService.saveBook(new Book("It", new Author("Stephen King"), Genre.HORROR, 40.0, 70));
        bookService.saveBook(new Book("The Great Gatsby", new Author("F. Scott Fitzgerald"), Genre.HISTORICAL_FICTION, 50.0, 90));
        bookService.saveBook(new Book("To Kill a Mockingbird", new Author("Harper Lee"), Genre.HISTORICAL_FICTION, 35.0, 50));
//        bookService.updateBookPrice(1,12.35);
        Person admin = new Person("Misha", "vbif555666@gmail.com", "stale555666");
        personService.savePerson(admin);
        personService.setAdminRoleToPerson(admin);
    }
}
