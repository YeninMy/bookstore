package com.example.mybookstore.repository;

import com.example.mybookstore.entity.Person;
import com.example.mybookstore.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepo extends JpaRepository<Wishlist, Integer> {
    Wishlist findByPerson(Person person);

}
