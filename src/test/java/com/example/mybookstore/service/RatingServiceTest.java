package com.example.mybookstore.service;

import com.example.mybookstore.entity.Book;
import com.example.mybookstore.entity.Person;
import com.example.mybookstore.entity.Rating;
import com.example.mybookstore.repository.RatingRepo;
import com.example.mybookstore.servise.RatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.mockito.ArgumentCaptor;
import static org.junit.jupiter.api.Assertions.*;

public class RatingServiceTest {
    private RatingRepo ratingRepo;
    private RatingService ratingService;

    @BeforeEach
    public void setUp() {
        ratingRepo = Mockito.mock(RatingRepo.class);
        ratingService = new RatingService(ratingRepo);
    }

    @Test
    public void rateBookWhenRatingDoesNotExist() {
        Person person = new Person();
        Book book = new Book();
        int mark = 5;

        when(ratingRepo.findByPersonAndBook(any(Person.class), any(Book.class))).thenReturn(Optional.empty());

        ratingService.rateBook(person, book, mark);

        ArgumentCaptor<Rating> ratingCaptor = ArgumentCaptor.forClass(Rating.class);
        verify(ratingRepo, times(1)).findByPersonAndBook(person, book);
        verify(ratingRepo, times(1)).save(ratingCaptor.capture());

        Rating savedRating = ratingCaptor.getValue();
        assertEquals(person, savedRating.getPerson());
        assertEquals(book, savedRating.getBook());
        assertEquals(mark, savedRating.getMark());
    }

    @Test
    public void rateBookWhenRatingExists() {
        Person person = new Person();
        Book book = new Book();
        int mark = 5;
        Rating rating = new Rating();

        when(ratingRepo.findByPersonAndBook(any(Person.class), any(Book.class))).thenReturn(Optional.of(rating));

        ratingService.rateBook(person, book, mark);

        ArgumentCaptor<Rating> ratingCaptor = ArgumentCaptor.forClass(Rating.class);
        verify(ratingRepo, times(1)).findByPersonAndBook(person, book);
        verify(ratingRepo, times(1)).save(ratingCaptor.capture());

        Rating savedRating = ratingCaptor.getValue();
        assertEquals(person, savedRating.getPerson());
        assertEquals(book, savedRating.getBook());
        assertEquals(mark, savedRating.getMark());
    }
}

