package com.example.mybookstore.config;


import com.example.mybookstore.entity.Author;
import com.example.mybookstore.entity.Book;
import com.example.mybookstore.entity.Genre;
import com.example.mybookstore.servise.BookService;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataLoader {
    private final BookService bookService;

    @Autowired
    public DataLoader(BookService bookService) {
        this.bookService = bookService;
    }

    @PostConstruct
    public void addPreparedListOfBooks() {
        bookService.saveBook(new Book("Dune", new Author("Frank Herbert"), Genre.SCIENCE_FICTION));
        bookService.saveBook(new Book("1984", new Author("George Orwell"), Genre.SCIENCE_FICTION));
        bookService.saveBook(new Book("Brave New World", new Author("Aldous Huxley"), Genre.SCIENCE_FICTION));
        bookService.saveBook(new Book("The Lord of the Rings", new Author("J.R.R. Tolkien"), Genre.FANTASY));
        bookService.saveBook(new Book("Harry Potter and the Philosopher's Stone", new Author("J.K. Rowling"), Genre.FANTASY));
        bookService.saveBook(new Book("The Hobbit", new Author("J.R.R. Tolkien"), Genre.FANTASY));
        bookService.saveBook(new Book("The Da Vinci Code", new Author("Dan Brown"), Genre.MYSTERY));
        bookService.saveBook(new Book("The Girl with the Dragon Tattoo", new Author("Stieg Larsson"), Genre.MYSTERY));
        bookService.saveBook(new Book("Gone Girl", new Author("Gillian Flynn"), Genre.MYSTERY));
        bookService.saveBook(new Book("The Silence of the Lambs", new Author("Thomas Harris"), Genre.THRILLER));
        bookService.saveBook(new Book("Jurassic Park", new Author("Michael Crichton"), Genre.THRILLER));
        bookService.saveBook(new Book("The Shining", new Author("Stephen King"), Genre.THRILLER));
        bookService.saveBook(new Book("Pride and Prejudice", new Author("Jane Austen"), Genre.ROMANCE));
        bookService.saveBook(new Book("Jane Eyre", new Author("Charlotte Bronte"), Genre.ROMANCE));
        bookService.saveBook(new Book("Wuthering Heights", new Author("Emily Bronte"), Genre.ROMANCE));
        bookService.saveBook(new Book("Dracula", new Author("Bram Stoker"), Genre.HORROR));
        bookService.saveBook(new Book("Frankenstein", new Author("Mary Shelley"), Genre.HORROR));
        bookService.saveBook(new Book("It", new Author("Stephen King"), Genre.HORROR));
        bookService.saveBook(new Book("The Great Gatsby", new Author("F. Scott Fitzgerald"), Genre.HISTORICAL_FICTION));
        bookService.saveBook(new Book("To Kill a Mockingbird", new Author("Harper Lee"), Genre.HISTORICAL_FICTION));
        bookService.updateBookPrice(1,12.35);
    }
}
