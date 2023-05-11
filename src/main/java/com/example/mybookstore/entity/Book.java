package com.example.mybookstore.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
    private List<Author> authors;
    @Enumerated(EnumType.STRING)
    private Genre genre;

    private double price;

    private int quantity;

    private String coverImage;

    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
    private List<Purchase> purchases = new ArrayList<>();

    public Book() {
    }


    public Book(String name, List<Author> authors, Genre genre, double price, int quantity) {
        this.name = name;
        this.authors = authors;
        this.genre = genre;
        this.price = price;
        this.quantity = quantity;

    }

    public Book(String name, List<Author> authors, Genre genre, double price, int quantity, String coverImage) {
        this.name = name;
        this.authors = authors;
        this.genre = genre;
        this.price = price;
        this.quantity = quantity;
        this.coverImage = coverImage;
    }


}
