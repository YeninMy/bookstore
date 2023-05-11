package com.example.mybookstore.servise;

import com.example.mybookstore.entity.Author;
import com.example.mybookstore.entity.Book;
import com.example.mybookstore.entity.Genre;
import com.example.mybookstore.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class BookService {
    private final BookRepo bookRepo;
    private final AuthorService authorService;

    @Autowired
    public BookService(BookRepo bookRepo, AuthorService authorService) {
        this.bookRepo = bookRepo;
        this.authorService = authorService;
    }

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    public void saveBook(final Book book) {
        for (Author author : book.getAuthors()) {
            author.getBooks().add(book);
            authorService.saveAuthor(author);
        }

        bookRepo.save(book);
    }

    public Book getBookById(int id) {
        return bookRepo.findBookById(id);
    }

    public Book updateBookPrice(int id, Double newPrice) {
        Book book = bookRepo.findBookById(id);
        book.setPrice(newPrice);
        return bookRepo.save(book);
    }

    public List<Book> getBooksByGenre(Genre genre) {
        return bookRepo.findAllByGenre(genre);
    }

    public Book getRandomBook() {
        List<Book> books = bookRepo.findAll();
        Random random = new Random();
        int randomIndex = random.nextInt(books.size());
        return books.get(randomIndex);
    }
}


