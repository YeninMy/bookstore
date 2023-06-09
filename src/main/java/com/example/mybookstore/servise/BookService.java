package com.example.mybookstore.servise;

import com.example.mybookstore.entity.Author;
import com.example.mybookstore.entity.Book;
import com.example.mybookstore.entity.Genre;
import com.example.mybookstore.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Join;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.*;
import javax.persistence.criteria.Predicate;

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

    /**
     * Used to search for available books.
     */
    public List<Book> getAllExistingBooks() {
        List<Book> allBooks = getAllBooks();
        List<Book> existingBooks = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.getQuantity() > 0) {
                existingBooks.add(book);
            }
        }
        return existingBooks;
    }
    /**
     * Used to save a new book to the database.
     */
    @Transactional
    public void saveBook(final Book book) {
        for (Author author : book.getAuthors()) {
            List<Author> authors = new ArrayList<>();

            Author existingAuthor = authorService.findByName(author.getFirstName(), author.getLastName());
            if (existingAuthor != null) {
                existingAuthor.getBooks().add(book);
                authors.add(existingAuthor);
            } else {
                author.getBooks().add(book);
                authorService.saveAuthor(author);
                authors.add(author);
            }

            book.setAuthors(authors);
            bookRepo.save(book);
        }
    }
    /**
     * Used to update the data of an existing book.
     */
    public void updateBook(final Book book) {
        bookRepo.save(book);
    }

    public Book getBookById(int id) {
        return bookRepo.findBookById(id);
    }
    /**
     * Used to change the book price.
     */
    public void updateBookPrice(int id, Double newPrice) {
        Book book = bookRepo.findBookById(id);
        book.setPrice(newPrice);
        bookRepo.save(book);
    }
    /**
     * Used to change the book quantity.
     */
    public void updateBookQuantity(int id, int newQuantity) {
        Book book = bookRepo.findBookById(id);
        book.setQuantity(newQuantity);
        bookRepo.save(book);
    }
    /**
     * Used to change the book picture.
     */
    public void updateBookImage(int id, String newImage) {
        Book book = bookRepo.findBookById(id);
        book.setCoverImage(newImage);
        bookRepo.save(book);
    }
    /**
     * Used to search for available books by genre.
     */
    public List<Book> getBooksByGenre(Genre genre) {
        List<Book> allBooksByGenre = bookRepo.findAllByGenre(genre);
        List<Book> existingBooks = new ArrayList<>();
        for (Book book : allBooksByGenre) {
            if (book.getQuantity() > 0) {
                existingBooks.add(book);
            }
        }
        return existingBooks;
    }
    /**
     * Used to get available books by genre as pages.
     */
    public Page<Book> getPagesBooksByGenre(Genre genre, Pageable pageable) {
        List<Book> existingBooks = getBooksByGenre(genre);

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), existingBooks.size());

        return new PageImpl<>(existingBooks.subList(start, end), pageable, existingBooks.size());
    }
    /**
     * Used to get a random available book.
     */
    public Book getRandomBook() {
        List<Book> books = getAllExistingBooks();
        Random random = new Random();
        int randomIndex = random.nextInt(books.size());
        return books.get(randomIndex);
    }
    /**
     * Used to get sorted available books as pages.
     */
    public Page<Book> getSortedExistingBooks(String search, String sortBy, int page, int size, HttpSession session) {
        String finalSearch = getSearchValue(search, session);
        String finalSortBy = getSortValue(sortBy, session);

        Specification<Book> spec = createBookSpecification(finalSearch);
        Pageable pageable = getPageable(finalSortBy, page, size);

        return bookRepo.findAll(spec, pageable);
    }
    /**
     * Determines and returns the search parameter value to be used in book fetching operation.
     * This value is either retrieved from a HttpSession object or directly from the input parameter.
     */
    private String getSearchValue(String search, HttpSession session) {
        if (search != null) {
            session.setAttribute("search", search);
            return search.toLowerCase();
        } else {
            return (String) session.getAttribute("search");
        }
    }
    /**
     * Determines and returns the sort parameter value to be used in book fetching operation.
     * This value is either retrieved from a HttpSession object or directly from the input parameter.
     */
    private String getSortValue(String sortBy, HttpSession session) {
        if (sortBy != null) {
            session.setAttribute("sortBy", sortBy);
            return sortBy;
        } else {
            return (String) session.getAttribute("sortBy");
        }
    }
    /**
     * Creates a JPA Specification for Book entities based on the search parameter.
     * The specification can be used to fetch books that match the search criteria from a database.
     */
    private Specification<Book> createBookSpecification(final String finalSearch) {
        return (root, query, cb) -> {
            query.distinct(true);
            List<Predicate> predicates = new ArrayList<>();

            if (finalSearch != null) {
                Join<Book, Author> authorJoin = root.join("authors");
                List<Predicate> searchPredicates = new ArrayList<>();
                searchPredicates.add(cb.like(cb.lower(root.get("name")), "%" + finalSearch.toLowerCase() + "%"));
                searchPredicates.add(cb.like(cb.lower(authorJoin.get("firstName")), "%" + finalSearch.toLowerCase() + "%"));
                searchPredicates.add(cb.like(cb.lower(authorJoin.get("lastName")), "%" + finalSearch.toLowerCase() + "%"));

                List<Predicate> genrePredicates = new ArrayList<>();
                for (Genre genre : Genre.values()) {
                    if (genre.getDisplayName().toLowerCase().contains(finalSearch.toLowerCase())) {
                        genrePredicates.add(cb.equal(root.get("genre"), genre));
                    }
                }
                if (!genrePredicates.isEmpty()) {
                    searchPredicates.add(cb.or(genrePredicates.toArray(new Predicate[0])));
                }

                predicates.add(cb.or(searchPredicates.toArray(new Predicate[0])));
            }

            predicates.add(cb.greaterThan(root.get("quantity"), 0));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
    /**
     * Determines and returns a Pageable object to be used in book fetching operation.
     * This object defines the pagination and sorting behavior of the operation.
     */
    private Pageable getPageable(String sortBy, int page, int size) {
        if (sortBy == null) {
            return PageRequest.of(page, size);
        } else if (sortBy.equals("name")) {
            return PageRequest.of(page, size, Sort.by("name"));
        } else if (sortBy.equals("priceAsc")) {
            return PageRequest.of(page, size, Sort.by("price"));
        } else if (sortBy.equals("priceDesc")) {
            return PageRequest.of(page, size, Sort.by("price").descending());
        } else if (sortBy.equals("rating")) {
            return PageRequest.of(page, size, Sort.by("rating").descending());
        } else {
            return PageRequest.of(page, size);
        }
    }

}


