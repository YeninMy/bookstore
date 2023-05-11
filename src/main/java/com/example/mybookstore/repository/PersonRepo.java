package com.example.mybookstore.repository;

import com.example.mybookstore.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepo extends JpaRepository<Person, Integer> {
    Optional<Person> findAllByEmail(String email);

    Person findByUsername(String username);

}
