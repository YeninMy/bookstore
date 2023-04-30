package com.example.mybookstore.repository;

import com.example.mybookstore.entity.Book;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {
    @Override
    List<Book> findAll(Sort sort);

}
