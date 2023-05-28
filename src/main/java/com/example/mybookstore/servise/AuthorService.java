package com.example.mybookstore.servise;

import com.example.mybookstore.entity.Author;
import com.example.mybookstore.repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    private final AuthorRepo authorRepo;

    @Autowired
    public AuthorService(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    public void saveAuthor(Author author) {
        authorRepo.save(author);
    }

    /**
     * Used to search for an author by first and last name.
     */
    public Author findByName(String firstName, String lastName) {
        return authorRepo.findByFirstNameAndLastName(firstName, lastName);
    }
}
