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

    public void saveAuthor(Author author){
        authorRepo.save(author);
    }
}
