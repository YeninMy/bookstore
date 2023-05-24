package com.example.mybookstore.servise;

import com.example.mybookstore.entity.Book;
import com.example.mybookstore.entity.Person;
import com.example.mybookstore.entity.Rating;
import com.example.mybookstore.repository.RatingRepo;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    private final RatingRepo ratingRepo;

    public RatingService(RatingRepo ratingRepo) {
        this.ratingRepo = ratingRepo;
    }

    public void rateBook(Person person, Book book, double mark) {

        Rating rating = ratingRepo.findByPersonAndBook(person, book).orElse(new Rating());

        rating.setPerson(person);
        rating.setBook(book);
        rating.setMark(mark);

        ratingRepo.save(rating);
    }

}
