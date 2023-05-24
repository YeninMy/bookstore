package com.example.mybookstore.service;

import com.example.mybookstore.entity.Author;
import com.example.mybookstore.repository.AuthorRepo;
import com.example.mybookstore.servise.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AuthorServiceTest {
    @Mock
    AuthorRepo authorRepo;

    @InjectMocks
    AuthorService authorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveAuthor() {
        Author author = new Author();
        authorService.saveAuthor(author);
        verify(authorRepo, times(1)).save(author);
    }

    @Test
    void shouldFindAuthorByName() {
        String firstName = "Frank";
        String lastName = "Herbert";
        authorService.findByName(firstName, lastName);
        verify(authorRepo, times(1)).findByFirstNameAndLastName(firstName, lastName);
    }
}

