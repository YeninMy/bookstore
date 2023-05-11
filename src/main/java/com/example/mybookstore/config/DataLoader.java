package com.example.mybookstore.config;


import com.example.mybookstore.entity.*;
import com.example.mybookstore.servise.AuthorService;
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
    private final AuthorService authorService;

    @Autowired
    public DataLoader(BookService bookService, PersonService personService, AuthorService authorService) {
        this.bookService = bookService;
        this.personService = personService;
        this.authorService = authorService;
    }

    @PostConstruct
    public void addPreparedListOfBooks() {
        Author frankHerbert = new Author("Frank", "Herbert");
//        authorService.saveAuthor(frankHerbert);
        Author georgeOrwell = new Author("George", "Orwell");
//        authorService.saveAuthor(georgeOrwell);
        Author aldousHuxley = new Author("Aldous", "Huxley");
//        authorService.saveAuthor(aldousHuxley);
        Author jrrTolkien = new Author("J.R.R.", "Tolkien");
//        authorService.saveAuthor(jrrTolkien);
        Author jkRowling = new Author("J.K.", "Rowling");
//        authorService.saveAuthor(jkRowling);
        Author danBrown = new Author("Dan", "Brown");
//        authorService.saveAuthor(danBrown);
        Author stiegLarsson = new Author("Stieg", "Larsson");
//        authorService.saveAuthor(stiegLarsson);
        Author gillianFlynn = new Author("Gillian", "Flynn");
//        authorService.saveAuthor(gillianFlynn);
        Author thomasHarris = new Author("Thomas", "Harris");
//        authorService.saveAuthor(thomasHarris);
        Author michaelCrichton = new Author("Michael", "Crichton");
//        authorService.saveAuthor(michaelCrichton);
        Author stephenKing = new Author("Stephen", "King");
//        authorService.saveAuthor(stephenKing);
        Author janeAusten = new Author("Jane", "Austen");
//        authorService.saveAuthor(janeAusten);
        Author charlotteBronte = new Author("Charlotte", "Bronte");
//        authorService.saveAuthor(charlotteBronte);
        Author emilyBronte = new Author("Emily", "Bronte");
//        authorService.saveAuthor(emilyBronte);
        Author bramStoker = new Author("Bram", "Stoker");
//        authorService.saveAuthor(bramStoker);
        Author maryShelley = new Author("Mary", "Shelley");
//        authorService.saveAuthor(maryShelley);
        Author fScottFitzgerald = new Author("F. Scott", "Fitzgerald");
//        authorService.saveAuthor(fScottFitzgerald);
        Author harperLee = new Author("Harper", "Lee");
//        authorService.saveAuthor(harperLee);

        bookService.saveBook(new Book("Dune", Collections.singletonList(frankHerbert), Genre.SCIENCE_FICTION, 35.0, 50, "dune-cover.jpg"));
        bookService.saveBook(new Book("1984", Collections.singletonList(georgeOrwell), Genre.SCIENCE_FICTION, 45.0, 80));
        bookService.saveBook(new Book("Brave New World", Collections.singletonList(aldousHuxley), Genre.SCIENCE_FICTION, 40.0, 70));
        bookService.saveBook(new Book("The Lord of the Rings", Collections.singletonList(jrrTolkien), Genre.FANTASY, 50.0, 90));
        bookService.saveBook(new Book("Harry Potter and the Philosopher's Stone", Collections.singletonList(jkRowling), Genre.FANTASY, 30.0, 60));
        bookService.saveBook(new Book("The Hobbit", Collections.singletonList(jrrTolkien), Genre.FANTASY, 45.0, 80));
        bookService.saveBook(new Book("The Da Vinci Code", Collections.singletonList(danBrown), Genre.MYSTERY, 35.0, 50));
        bookService.saveBook(new Book("The Girl with the Dragon Tattoo", Collections.singletonList(stiegLarsson), Genre.MYSTERY, 40.0, 70));
        bookService.saveBook(new Book("Gone Girl", Collections.singletonList(gillianFlynn), Genre.MYSTERY, 50.0, 90));
        bookService.saveBook(new Book("The Silence of the Lambs", Collections.singletonList(thomasHarris), Genre.THRILLER, 30.0, 60));
        bookService.saveBook(new Book("Jurassic Park", Collections.singletonList(michaelCrichton), Genre.THRILLER, 45.0, 80));
        bookService.saveBook(new Book("The Shining", Collections.singletonList(stephenKing), Genre.THRILLER, 35.0, 50));
        bookService.saveBook(new Book("Pride and Prejudice", Collections.singletonList(janeAusten), Genre.ROMANCE, 40.0, 70));
        bookService.saveBook(new Book("Jane Eyre", Collections.singletonList(charlotteBronte), Genre.ROMANCE, 50.0, 90));
        bookService.saveBook(new Book("Wuthering Heights", Collections.singletonList(emilyBronte), Genre.ROMANCE, 35.0, 50));
        bookService.saveBook(new Book("Dracula", Collections.singletonList(bramStoker), Genre.HORROR, 45.0, 80));
        bookService.saveBook(new Book("Frankenstein", Collections.singletonList(maryShelley), Genre.HORROR, 30.0, 60));
        bookService.saveBook(new Book("It", Collections.singletonList(stephenKing), Genre.HORROR, 40.0, 70));
        bookService.saveBook(new Book("The Great Gatsby", Collections.singletonList(fScottFitzgerald), Genre.HISTORICAL_FICTION, 50.0, 90));
        bookService.saveBook(new Book("To Kill a Mockingbird", Collections.singletonList(harperLee), Genre.HISTORICAL_FICTION, 35.0, 50));

//        bookService.updateBookPrice(1,12.35);
        Person admin = new Person("Misha", "vbif555666@gmail.com", "stale555666");
        personService.savePerson(admin);
        personService.setAdminRoleToPerson(admin);
    }
}
