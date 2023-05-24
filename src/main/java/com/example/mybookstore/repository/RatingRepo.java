package com.example.mybookstore.repository;

import com.example.mybookstore.entity.Book;
import com.example.mybookstore.entity.Person;
import com.example.mybookstore.entity.Purchase;
import com.example.mybookstore.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepo extends JpaRepository<Rating, Integer> {

    Optional<Rating> findByPersonAndBook(Person person, Book book);


}
