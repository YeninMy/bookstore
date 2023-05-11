package com.example.mybookstore.repository;

import com.example.mybookstore.entity.Person;
import com.example.mybookstore.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepo extends JpaRepository<Purchase, Integer> {
    List<Purchase> findByPerson(Person person);

    Purchase findByPersonAndClosed(Person person, boolean closed);
    List<Purchase> findAllByPersonAndClosed(Person person, boolean closed);

}
